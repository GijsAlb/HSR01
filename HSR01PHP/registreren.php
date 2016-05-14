<!doctype html>
<?php require_once("includes/config.php"); ?>
<script type="text/javascript" src="includes/wachtwoordcheck.js"></script>
<html>
    <head>
        <link rel="shortcut icon" href="img/favicon.png" type="image/x-icon" />

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Michel en Justin">

        <title>Registreren</title>

        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >


        <link rel="stylesheet" href="css.css">
        <link rel="stylesheet" href="overOns.css">

    </head>

    <?php
    //Tijdzone = Amsterdam
    date_default_timezone_set("Europe/Amsterdam");
    //Haalt datum op
    $vandaag = date("d-m-Y");
    $voornaam = "";
    $achternaam = "";
    $email = "";
    $telnr = "";
    $postcode = "";
    $huisnr = "";
    $gbdatum = "";
    //als bevestigingsknop is ingedrukt...
    if (isset($_POST["submit"])) {
        //filter input tegen xss met html entities en haalt gegevens op
        $voornaam = htmlentities($_POST["voornaam"]);
        $achternaam = htmlentities($_POST["achternaam"]);
        $email = htmlentities($_POST["email"]);
        $wachtwoord = hash("sha512", htmlentities($_POST["wachtwoord"]));
        $wachtwoordbevestig = hash("sha512", htmlentities($_POST["wachtwoordbevestig"]));
        $telnr = htmlentities($_POST["telnr"]);
        $postcodeinput = htmlentities($_POST["postcode"]);
        $huisnr = htmlentities($_POST["huisnr"]);
        $gbdatum = htmlentities($_POST["gbdatum"]);
        //foutcontrole zodat database niet bijgewerkt wordt als er fout is.
        $fout = false;
        //postcode aan elkaar
        $postcode = str_replace(" ", "", $postcodeinput);
        //wachtwoorden overeenkomen
        if ($wachtwoord != $wachtwoordbevestig) {
            print("Wachtwoorden komen niet overeen.");
            $fout = true;
        }
        //check zodat je geen datum in de toekomst kan kiezen
        if ($vandaag > $_POST["gbdatum"]) {
            print("Je kan geen datum in de toekomst kiezen.");
            $fout = true;
        }
        /* berekent leeftijd, datum die geselecteerd is wordt omgezet in seconden en van de tijd van nu gehaald, wat overblijft wordt gedeeld door
          het aantal seconden in een jaar.
         */
        $leeftijd = floor((time() - strtotime($_POST["gbdatum"])) / 31556926);
        //check: leeftijd moet boven 18 zijn.
        if ($leeftijd < 18) {
            print("Je moet 18 jaar of ouder zijn om te registreren!");
            $fout = true;
        }
        //database bijwerken na goede invoer registratie
        if ($fout == false) {
            try {
                $stmt = $db->prepare("INSERT INTO treinkoerier(voornaam, achternaam, email, wachtwoord, gbdatum, telnr, postcode, huisnr)
                VALUES(?,?,?,?,?,?,?,?)");
                $stmt->execute(array($voornaam, $achternaam, $email, $wachtwoord, $gbdatum, $telnr, $postcode, $huisnr));
                print("Registratie gelukt!");
            } catch (PDOException $e) {
                print($e->getMessage());
                if ($e->errorInfo[1] == 1062) {
                    ("E-mailadres is reeds in gebruik!");
                }
            }
        }
    }
    ?>
    <body id="page-top">
        <?php
        // include_once 'includes/header.php';
        ?>
        <!--  Section -->
        <section id="content" class="content-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10  contentbox">
                        <h1 class="tzttitle" >Registreren</h1>
                        <!-- inlogformulier -->
                        <form style="font-family: Calibri" method="post" action="registreren.php">


                            <p>Voornaam:*
                                <input style="margin-left: 120px;" type="text" name="voornaam" value="<?php print($voornaam); ?>" required></p>

                            <p>Achternaam:*
                                <input style="margin-left: 105px;" type="text" name="achternaam" value="<?php print($achternaam); ?>" required></p>

                            <p>E-mail:*
                                <input style="margin-left: 154px;" type="email" name="email" value="<?php print($email); ?>" required></p>

                            <p>Wachtwoord:*
                                <input style="margin-left: 100px;"type="password" name="wachtwoord" value="" required></p>

                            <p>Wachtwoord bevestigen:*
                                <input style="margin-left: 9px;"type="password" name="wachtwoordbevestig" value="" required></p>

                            <p>Geboortedatum:*
                                <input style="margin-left: 76px; width: 210px;"type="date" name="gbdatum" placeholder="dag-maand-jaar" value="<?php print($gbdatum); ?>">
                            </p>

                            <p>Telnummer:*
                                <input style="margin-left: 115px;" type="text" name="telnr" value="<?php print($telnr); ?>" required></p>

                            <p>
                                Postcode:*
                                <input style="margin-left: 135px;"type="text" name="postcode" value="<?php print($postcode) ?>" required></p>
                            <p>
                                Huisnummer:*
                                <input style="margin-left: 103px;"type="text" name="huisnr" value="<?php print($huisnr) ?>" required>
                            </p>

                            <p>
                                <input id="check1" type="checkbox" name="check" value="check1" required>
                                <label for="check1"> Ik ga akkoord met de algemene voorwaarden</label>
                            </p>

                            <input class="btn" style="margin-left: 0px !important;"type="submit" name="submit" value="Registreren!">
                        </form>
                        </p>
                    </div>

                </div>
            </div>
        </section>
        <section id="footer" class="footer-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <?php
                        //  include_once "includes/footer.php"
                        ?>
                    </div>
                </div>
            </div>
        </section>

        <!-- JavaScript -->
        <script src="http://michelvaartjes.nl/js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="http://michelvaartjes.nl/js/bootstrap.min.js"></script>

        <script src="http://michelvaartjes.nl/js/jquery.easing.min.js"></script>
        <script src="http://michelvaartjes.nl/js/scrolling-nav.js"></script>
    </body>
</html>
