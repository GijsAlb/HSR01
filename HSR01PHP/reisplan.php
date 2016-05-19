<!doctype html>
<?php
session_start();
include("Includes/config.php");
?>
<?php
if (!isset($_SESSION["ingelogd"])) {
    print("Je moet ingelogd zijn om deze pagina te bekijken!");
    die();
}
date_default_timezone_set("Europe/Amsterdam");
$vandaag = date("d-m-Y");
$beginstation = "";
$eindstation = "";
$fout = false;
$foutbegin = "";
$fouteind = "";
$foutnietzosnugger = false;
$foutdatum = "";
$foutvasttraject = FALSE;

try {
    $stmt = $db->prepare("SELECT COUNT(*) FROM reisplan WHERE treinreiziger_idtreinreiziger = ? AND tijdelijk = 0");
    $stmt->execute(array($_SESSION["id"]));
    $dbvasttraject = $stmt->fetch();
} catch (PDOException $e) {
    print("Er is iets misgegaan, probeer het later opnieuw");
}
////kan weg(evt wijzigen traject formulier)
//if ($dbvasttraject[0] != 0) {
//
//    print("U heeft al een vast traject, wilt u uw vaste traject wijzigen? klik dan <a href='wijzigenreisplan.php'>hier</a>");
//     $foutvasttraject = TRUE;
//}

if (isset($_POST["knop"])) {
    $url = "http://webservices.ns.nl/ns-api-stations-v2";
    $ww = "h1xeA17XHDFc1qWpy_nVEoEvAyHvB8LVRP_I0tyY-QjrcuxXxln-8w";
    $gebr = "Gijs4u@gmail.com";
    $beginstation = htmlentities($_POST["beginstation"]);
    $eindstation = htmlentities($_POST["eindstation"]);
    $beginbool = false;
    $eindbool = false;
    $tijdelijk = false;
    $datum = null;

    if (!empty($_POST["datum"])) {
        $datumOud = $_POST["datum"];
        $datumNieuw = date("d-m-Y", strtotime($datumOud));
        if ($datumNieuw >= $vandaag) {
            $datum = $_POST["datum"];
            $tijdelijk = true;
        } else {
            $foutdatum = "De datum moet vandaag zijn/in de toekomst liggen.";
            $fout = true;
        }
    }

    if ($beginstation == $eindstation) {
        print("Fout: het beginstation is hetzelfde als het eindstation!");
        $foutnietzosnugger = true;
    }
    if ($foutnietzosnugger == false) {
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_USERPWD, "$gebr:$ww");
        curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        $output = curl_exec($ch);
        $info = curl_getinfo($ch);

        if ($info["http_code"] != 200) {
            print("We konden geen verbinding maken met ons systeem, probeer het later opnieuw!");
            $fout = true;
        }

        //parser voor xml zodat je het kan gebruiken
        $xmlobj = simplexml_load_string($output);
        //dan encode en decode meteen en de true maakt het een array zonder true heb je een jsonobject
        $array = json_decode(json_encode($xmlobj), true);
        for ($i = 0; $i < count($array["Station"]); $i++) {
            if ($beginstation == $array['Station'][$i]['Namen']['Lang']) {
                $beginbool = true;
            }
            if ($eindstation == $array['Station'][$i]['Namen']['Lang']) {
                $eindbool = true;
            }
        }
        curl_close($ch);

        if ($beginbool == false) {
            $foutbegin = "Beginstation niet gevonden!";
            $fout = true;
        }
        if ($eindbool == false) {
            $fouteind = "Eindstation niet gevonden!";
            $fout = true;
        }
//        if ($foutvasttraject == true) {
//            print ("<h2> U heeft al een vast traject, u kunt uw vaste traject alleen wijzigen. </h2>");
//            $fout = TRUE;
//
//        }

        if ($beginbool == true && $eindbool == true && $fout == false) {
            try {
                print("<h1> Reisplan succesvol doorgegeven! </h1>");
                $stmt = $db->prepare("INSERT INTO reisplan(beginstation,eindstation,tijdelijk,datum,treinreiziger_idtreinreiziger)VALUES(?,?,?,?,?)");
                $stmt->execute(array($beginstation, $eindstation, $tijdelijk, $datum, $_SESSION["id"]));
            } catch (PDOException $e) {
                print("Foutmelding, probeer het later opnieuw");
                echo $e->getMessage();
            }
        }
    }
}
?>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title></title>
        <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
        <!--  JS functies -->
        <script>
            $(function () {
                $("#beginstation").autocomplete({
                    source: "stationlijst.php",
                    minLength: 2
                });
            });
            $(function () {
                $("#eindstation").autocomplete({
                    source: "stationlijst.php",
                    minLength: 2
                });
            });
            $(document).ready(function () {
                $('#tijdelijk').click(function () {
                    $('#datum').toggle();
                    $('#datumlabel').toggle();
                });
            });
        </script>
    </head>
    <body>
        <form action="reisplan.php" method="post">
            <p>
                <label>Beginstation</label>
                <input type="text" name="beginstation" id="beginstation" value="<?php print($beginstation) ?>" required>
                <?php print($foutbegin); ?>
            </p>
            <p>
                <label>Eindstation.</label>
                <input type="text" name="eindstation" id="eindstation" value="<?php print($eindstation) ?>" required>
                <?php print($fouteind); ?>
            </p>
            <p>
                <input type="checkbox" id="tijdelijk" name="traject"  value="tijdelijk">
                <label>Tijdelijk traject</label>
            </p>
            <p>
                <input type="checkbox" name="traject" value="vast">
                <label>Vast traject(Maximaal 1 vast traject per account)</label>
            </p>
            <p>
                <input type="date" id="datum" name="datum" style="display:none" placeholder="dd-mm-jjjj" value="">
                <label id="datumlabel" style ="display: none">Datum moet vandaag zijn/in de toekomst liggen</label>
                <?php print($foutdatum); ?>
            </p>
            <input type="submit" name="knop" value="Doorgeven reisplan!">
        </form>

    </body>
</html>
