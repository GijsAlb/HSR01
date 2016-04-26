<!doctype html>
<?php require_once("includes/config.php"); ?>
<script type="text/javascript" src="includes/wachtwoordcheck.js"></script>
<htmL>
    <head>

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
    $foutwachtwoord = "";
    $foutwachtwoordbevestig = "";
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
            $foutwachtwoordbevestig = "Wachtwoorden komen niet overeen";
            $fout = true;
        }
        if (ctype_alnum($_POST["wachtwoord"]) == true) {
            $foutwachtwoord = "Het wachtwoord moet minimaal 1 symbool bevatten";
        }
        //check zodat je geen datum in de toekomst kan kiezen
        if ($vandaag < $_POST["gbdatum"]) {
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
                if ($e->errorInfo[1] == 1062) {
                    ("E-mailadres is reeds in gebruik!");
                }
            }
        }
    }
    ?>
    <body> <!-- inlogformulier -->
        <form method="post" action="registreren.php">
            <h1>Registreren</h1>

            <p>
                Voornaam:*
                <input type="text" name="voornaam" value="<?php print($voornaam); ?>" required>
            </p>

            <p>
                Achternaam:*
                <input type="text" name="achternaam" value="<?php print($achternaam); ?>" required>
            </p>

            <p>
                E-mail:*
                <input type="email" name="email" value="<?php print($email); ?>" required>
            </p>

            <p>
                Wachtwoord:*
                <input type="password" name="wachtwoord" value="" required>
            </p>

            <p>
                Wachtwoord bevestigen:*
                <input type="password" name="wachtwoordbevestig" value="" required>
            </p>

            <p>
                Geboortedatum:*
                <input type="date" name="gbdatum" placeholder="dag-maand-jaar" value="<?php print($gbdatum); ?>">
            </p>

            <p>
                Telnummer:*
                <input type="text" name="telnr" value="<?php print($telnr); ?>" required>
            </p>

            <p>
                Postcode:*
                <input type="text" name="postcode" value="<?php print($postcode) ?>" required>

                Huisnummer:*
                <input type="text" name="huisnr" value="<?php print($huisnr) ?>" required>
            </p>
            <input type="submit" name="submit" value="Registreren!">
        </form>
    </body>
</html>
