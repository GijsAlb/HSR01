<!doctype html>
<htmL><head></head>

    <?php
// variabelen die ingevoerde data opslaan.
    $gebruikersnaam = "";
    $wachtwoord = "";
    $wachtwoordvoorprint = "";
    $wachtwoordbevestig = "";
    $mail = "";
    $mailbevestig = "";
    $postcode = "";
    $voornaam = "";
    $achternaam = "";
    $telnummer = "";
    $straatnaam = "";
    $huisnummer = "";
    $woonplaats = "";
// variabelen die gebruikt worden voor foutmeldingen.
    $ongeldig = false;
    $foutmeldingGebruiker = "";
    $foutmeldingWachtwoord = "";
    $foutmeldingWachtwoordbevestig = "";
    $foutmeldingMail = "";
    $foutmeldingMailbevestig = "";
    $foutmeldingPostcode = "";
    $foutmeldingVoornaam = "";
    $foutmeldingAchternaam = "";
    $foutmeldingTelnummer = "";
    $foutmeldingStraatnaam = "";
    $foutmeldingHuisnummer = "";
    $foutmeldingWoonplaats = "";
    if (isset($_POST["submitKnop"])) {
        /* gebruikersnaamCheck.
         * gebruikersnaamveld ingevuld? -> $gebruikersnaam = gelijk aan invoer indien geen ongeldige karakters(anti-XSS).
         * anders: gebruikersnaamveld leeg? -> Geef foutmelding: "*Voer een geldige gebruikersnaam in." */
        if (!empty($_POST["gebruikersnaam"])) {
            if (antiXSS($_POST["gebruikersnaam"]) !== false) {
                $gebruikersnaam = antiXSS($_POST["gebruikersnaam"]);
            } else {
                $foutmeldingGebruiker = "Je gebruikersnaam bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingGebruiker = "*Voer een geldige gebruikersnaam in.<br>";
        }
        /*  wachtwoordnaamCheck.
         * wachtwoordveld ingevuld? -> $wachtwoord = gelijk aan invoer indien geen ongeldige karakters(anti-XSS) en/of andere fouten.
         * anders: gebruikersnaamveld leeg? -> Geef foutmelding: "*Voer een wachtwoord in." */
        if (!empty($_POST["wachtwoord"])) {
            if (antiXSS($_POST["wachtwoord"]) !== false) {
                #Als het wachtwoord minder dat 6 karakters bevat, print foutmelding en zorg dat niets geupdate wordt met $fout.
                if (strlen($_POST["wachtwoord"]) < 6) {
                    $foutmeldingWachtwoord = "Je wachtwoord moet minimaal 6 karakters bevatten";
                } #Als het wachtwoord geen alphanumerieke karakters bevat, print foutmelding en zorg dat niets geupdate wordt met $fout.
                else if (ctype_alnum($_POST["wachtwoord"]) == false) {
                    $foutmeldingWachtwoord = "Je wachtwoord moet minimaal 1 symbool bevatten.";
                }
                if (ctype_alnum($_POST["wachtwoord"]) == false && strlen($_POST["wachtwoord"]) > 5) {
                    $wachtwoordvoorprint = $_POST["wachtwoord"];
                    $wachtwoord = hash("SHA512", $_POST["wachtwoord"]);
                }
            } else {
                $foutmeldingWachtwoord = "Je wachtwoord bevat teveel ongeldige karakters! Alleen letters, cijfers en een teken zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingWachtwoord = "*Voer een wachtwoord in.<br>";
        }

        if (!empty($_POST['wachtwoordBevestig'])) {
            if (antiXSS($_POST["wachtwoordBevestig"]) !== false) {
                $wachtwoordbevestig = $_POST["wachtwoordBevestig"];
            } else {
                $foutmeldingWachtwoordbevestig = "Je bevestigde wachtwoord bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
            if ($_POST["wachtwoordBevestig"] == $_POST["wachtwoord"]) {
                $wachtwoordbevestig = $_POST["wachtwoordBevestig"];
            } else {
                $foutmeldingWachtwoordbevestig = "*De ingevoerde wachtwoorden komen niet overeen";
            }
        }
        /* mailCheck.
         * mailveld ingevuld? -> $mail = gelijk aan invoer indien geen ongeldige karakters(anti-XSS).
         * anders: mailveld leeg? -> Geef foutmelding: "*Voer uw email-adres in." */
        if (!empty($_POST["mail"])) {
            if (antiXSS($_POST["mail"]) !== false) {
                $mail = $_POST["mail"];
            } else {
                $foutmeldingMail = "Je email bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingMail = "*Voer uw mail-adres in.";
        }

        if (!empty($_POST['mailBevestig'])) {
            if (antiXSS($_POST["mailBevestig"]) !== false) {
                $mailbevestig = $_POST["mailBevestig"];
            } else {
                $foutmeldingWachtwoordbevestig = "Je bevestigde e-mailadres bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
            if ($_POST["mailBevestig"] == $_POST["mail"]) {
                $mailbevestig = $_POST["mailBevestig"];
            } else {
                $foutmeldingMailbevestig = "*De ingevoerde emailadressen komen niet overeen";
            }
        }
        /* postcodeCheck.%
         * Postcodeveld ingevuld en bevat geen ongeldige karakters? -> $postcode = gelijk aan invoer. indien geen ongeldige karakters(anti-XSS).
         * anders: Postcodeveld ingevuld maar bevat ongeldige karakters? -> Geef foutmelding: "*Voer een geldige postcode in."
         * anders: Postcodeveld leeg? -> Geef foutmelding: "*Voer uw postcode in." */
        if (postcodeCheck($_POST["postcode"]) !== false) {
            if (antiXSS($_POST["postcode"]) !== false) {
                $postcode = postcodeCheck($_POST["postcode"]);
            } else {
                $foutmeldingPostcode = "Je postcode bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else if (!empty($_POST["postcode"])) {
            if (antiXSS($_POST["postcode"]) !== false) {
                $postcode = postcodeCheck($_POST["postcode"]);
            } else {
                $foutmeldingPostcode = "Je postcode bevat ongeldige karakters/is niet geldig! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingPostcode = "*Voer uw postcode in.";
        }
        /* voornaamCheck.
         * voornaamveld ingevuld? -> $voornaaminput = gelijk aan invoer indien geen ongeldige karakters(anti-XSS).
         * anders: voornaamveld leeg? -> Geef foutmelding: "*Voer uw voornaam in." */
        if (!empty($_POST["voornaam"])) {
            if (antiXSS($_POST["voornaam"]) !== false) {
                $voornaaminput = $_POST["voornaam"];
                #voornaamlager zorgt dat de voornaam geen hoofdletters bevat.(format geven aan voornaam)
                $voornaamlager = strtolower($voornaaminput);
                #zorgt ervoor dat de eerste letter van de voornaam een hoofdletter is.
                $voornaam = ucfirst($voornaamlager);
            } else {
                $foutmeldingVoornaam = "Je voornaam bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingVoornaam = "*Voer uw voornaam in.";
        }
        /* achternaamCheck.
         * achternaamveld ingevuld? -> $achternaaminput = gelijk aan invoer indien geen ongeldige karakters(anti-XSS).
         * anders: achternaamveld leeg? -> Geef foutmelding: "*Voer uw achternaam in." */
        if (!empty($_POST["achternaam"])) {
            if (antiXSS($_POST["achternaam"]) !== false) {
                $achternaaminput = $_POST["achternaam"];
                #achternaamlager zorgt dat de achternaam geen hoofdletters bevat.(format geven aan achternaamnaam)
                $achternaamlager = strtolower($achternaaminput);
                #zorgt ervoor dat de eerste letter van de voornaam een hoofdletter is.
                $achternaam = ucfirst($achternaamlager);
            } else {
                $foutmeldingAchternaam = "Je achternaam bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingAchternaam = "*Voer uw achternaam in.";
        }
        /* telnummerCheck.
         * telnummerveld ingevuld? -> $telnummerinput = gelijk aan invoer indien geen ongeldige karakters(anti-XSS).
         * anders: telnummerveld leeg? -> Doe niets want telnummer is niet verplicht */
        if (!empty($_POST["telnummer"])) {
            if (antiXSS($_POST["telnummer"]) !== false) {
                $telnummerinput = $_POST["telnummer"];
                #zorgt dat er geen spaties in het telefoonnummer staan.
                $telnummer = str_replace(" ", "", $telnummerinput);
            } else {
                $foutmeldingTelnummer = "Je telefoonnummer bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingTelnummer = "*Voer je telefoonnummer in.";
        }
        /* straatnaamCheck.
         * straatnaamveld ingevuld? -> $straatnaaminput = gelijk aan invoer indien geen ongeldige karakters(anti-XSS).
         * anders: straatnaamveld leeg? -> Geef foutmelding: "*Voer uw straatnaam in." */
        if (!empty($_POST["straatnaam"])) {
            if (antiXSS($_POST["straatnaam"]) !== false) {
                $straatnaaminput = $_POST["straatnaam"];
                #straatnaamlager zorgt dat de achternaam geen hoofdletters bevat.(format geven aan straatnaam)
                $straatnaamlager = strtolower($straatnaaminput);
                #zorgt ervoor dat de eerste letter van de straatnaam een hoofdletter is.
                $straatnaam = ucfirst($straatnaamlager);
            } else {
                $foutmeldingStraatnaam = "Je straatnaam bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingStraatnaam = "*Voer uw straatnaam in.";
        }
        /* huisnummerCheck.
         * huisnummerveld ingevuld? -> $huisnummer = gelijk aan invoer indien geen ongeldige karakters(anti-XSS).
         * anders: huisnummerveld leeg? -> Geef foutmelding: "*Voer uw huisnummer in." */
        if (!empty($_POST["huisnummer"])) {
            if (antiXSS($_POST["huisnummer"]) !== false) {
                $huisnummer = $_POST["huisnummer"];
            } else {
                $foutmeldingHuisnummer = "Je huisnummer bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingHuisnummer = "*Voer uw huisnummer in.";
        }
        /* woonplaatsCheck.S
         * woonplaatsveld ingevuld? -> $woonplaats = gelijk aan invoer indien geen ongeldige karakters(anti-XSS).
         * anders: woonplaatsveld leeg? -> Geef foutmelding: "*Voer uw woonplaats in." */
        if (!empty($_POST["woonplaats"])) {
            if (antiXSS($_POST["woonplaats"]) !== false) {
                #Dan staat de toenmalig lege variable $woonplaatsinput gelijk aan wat er ingevuld is in het formulier bij het woonplaatsveld.
                $woonplaatsinput = $_POST["woonplaats"];
                #zorgt ervoor dat van woonplaatsinput elk karakter een hoofdletter is.
                $woonplaats = strtoupper($woonplaatsinput);
            } else {
                $foutmeldingWoonplaats = "Je woonplaats bevat ongeldige karakters! Alleen letters en cijfers zijn toegestaan.";
                $ongeldig = true;
            }
        } else {
            $foutmeldingWoonplaats = "*Voer uw woonplaats in.";
        }
        if (!empty($_POST["gebruikersnaam"]) && !empty($_POST["wachtwoord"]) && !empty($_POST["wachtwoordBevestig"]) && !empty($_POST["mail"]) && !empty($_POST["mailBevestig"]) && !empty($_POST["postcode"]) && !empty($_POST["voornaam"]) && !empty($_POST["achternaam"]) && !empty($_POST["straatnaam"]) && !empty($_POST["huisnummer"]) && !empty($_POST["woonplaats"]) && $ongeldig == false) {
            try {
                $stmt = $pdo->prepare("INSERT INTO ACCOUNT(gebruikersnaam, wachtwoord, email, postcode, type, voornaam, achternaam, straatnaam, telnummer, huisnummer, woonplaats)
                VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                $stmt->execute(array($gebruikersnaam, $wachtwoord, $mail, $postcode, "K", $voornaam, $achternaam, $straatnaam, $telnummer, $huisnummer, $woonplaats));
                print("<br/><br/><h2>Registratie voltooid!</h2>");
            } catch (PDOException $e) {
                if (strpos($e, 'PRIMARY') !== false) {
                    print("<br/><br/><h2>De gebruikersnaam is reeds in gebruik!</h2>");
                }
                if (strpos($e, 'Email') !== false) {
                    print("<br/><br/><h2>Het e-mail adres is reeds in gebruik!</h2>");
                }
            }
        }
    }
    ?>
    <body>
        <form method="post" action="registreren.php">
            <h1>Registreren</h1>
            <p>Gebruikersnaam:*</p>
            <input type="text" name="gebruikersnaam" placeholder="Gebruikersnaam"value=<?php print($gebruikersnaam) ?> >
            <p> <?php print($foutmeldingGebruiker); ?> </p> <br>

            <p>Wachtwoord:*</p>
            <input type = "password" name = "wachtwoord" placeholder = "Wachtwoord" value = <?php print($wachtwoordvoorprint) ?> >
            <p> <?php print($foutmeldingWachtwoord); ?> </p> <br>

            <p>Wachtwoord Bevestigen:*</p>
            <input type="password" name="wachtwoordBevestig" placeholder="Bevestigen Wachtwoord" value= <?php print ($wachtwoordbevestig) ?> >
            <p> <?php print ($foutmeldingWachtwoordbevestig); ?> </p> <br>

            <p>Voornaam:*</p>
            <input type="text" name ="voornaam" placeholder="Voornaam" value=<?php print($voornaam) ?> >
            <p> <?php print($foutmeldingVoornaam); ?> </p> <br>

            <p>Achternaam:*</p>
            <input type="text" name="achternaam" placeholder="Achternaam" value=<?php print($achternaam) ?> >
            <p> <?php print($foutmeldingAchternaam); ?> </p> <br>

            <p>E-mail:*</p>
            <input type="email" name="mail" placeholder="E-mail" value=<?php print($mail) ?> >
            <p> <?php print($foutmeldingMail); ?> </p> <br>

            <p>E-mail Bevestigen:*</p>
            <input type="email" name="mailBevestig" placeholder ="Bevestigen E-mail" value=<?php print ($mailbevestig) ?> >
            <p> <?php print ($foutmeldingMailbevestig); ?> </p> <br>

            <p>Straatnaam:*</p>
            <input type="text" name="straatnaam" placeholder="Straatnaam" value=<?php print($straatnaam) ?> >
            <p> <?php print($foutmeldingStraatnaam); ?> </p><br>

            <p>Huisnummer:*</p>
            <input type="text" name="huisnummer" placeholder="Huisnummer" value=<?php print($huisnummer) ?> >
            <p> <?php print($foutmeldingHuisnummer); ?> </p> <br>

            <p>Postcode:*</p>
            <input type="text" name="postcode" placeholder="Postcode" value=<?php print($postcode) ?> >
            <p> <?php print($foutmeldingPostcode); ?> </p> <br>

            <p>Woonplaats:*</p>
            <input type="text" name="woonplaats" placeholder="Woonplaats" value=<?php print($woonplaats) ?> >
            <p> <?php print($foutmeldingWoonplaats); ?> </p> <br>

            <p>Telnummer*:</p>
            <input type="tel" name="telnummer" placeholder="Telnummer" value=<?php print($telnummer) ?> >
            <p> <?php print($foutmeldingTelnummer); ?> </p> <br>

            <p>Velden met * zijn verplicht.</p><br><br>
            <input type="submit" name="submitKnop" value="Registreren" >
        </form>
    </body>
</html>