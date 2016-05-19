<?php
session_start();

$foutmelding = "";

date_default_timezone_set('Europe/Amsterdam');

// deze functie maakt een random string
function randStrGen($len) {
    $result = "";
    $chars = "abcdefghijklmnopqrstuvwxyz?!-0123456789";
    $charArray = str_split($chars);
    for ($i = 0; $i < $len; $i++) {
        $randItem = array_rand($charArray);
        $result .= "" . $charArray[$randItem];
    }
    return $result;
}
?>
<html>
    <head>
        <title>TZT wachtwoord vergeten</title>

    </head>
    <body>

        <?php
        if (isset($_POST['verstuur'])) {
            //set variable voor de rest
            $invoeremail = htmlentities($_POST['email']);
             $van = "TZT@gmail.com";
//query voor het zoeken of het mailadres in de database staat
            $pdo = new PDO("mysql:host=localhost;dbname=micheic28_tztdb;port=3307", "root", "usbw");
            $queryemail = $pdo->prepare("SELECT * FROM treinkoerier WHERE email=?");
            $queryemail->execute(array($invoeremail));

//niet te vinden
            if ($queryemail->rowCount() == 0) {
                $foutmelding = "Dit E-mailadres is niet bekend";

//maak een random string
            } else {
                
                
                $wachtwoord = randStrGen(10);
                $sha512wachtwoord = hash('sha512', $wachtwoord);
                try{ 
                $querynaam = $pdo->prepare("SELECT voornaam FROM treinkoerier WHERE email=?");
                $querynaam->execute(array($invoeremail));
                $naam = $querynaam->fetch();
                } catch (PDOException $e) {
                    print("Database is offline");
                }
                
//mail maken en versturen
                $naar = $invoeremail;
                $onderwerp = "Wachtwoord opnieuw aanvragen HSR-TZT.ga";
                $verhaal = "
                   <html>
                   <head>
                   <title>
                        Nieuw wachtwoord
                   </title>
                   <body>
                        <p>
                        Beste " . $naam[0] . " ,<br><br> Er is een nieuw wachtwoord aangevraagd voor uw gebruikersnaam.<br>Uw nieuwe tijdelijke wachtwoord is: " . $wachtwoord . "<br>U kunt met dit wachtwoord inloggen en via uw profiel uw wachtwoord aanpassen naar een nieuw wachtwoord.<br>
                        </p>
                   </body>
                   </html>
                   ";
                $headers = "MIME-Version: 1.0" . "\r\n";
                $headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";
                $headers .= 'From: ' . $van . ' ' . "\r\n";

                mail($naar, $onderwerp, $verhaal, $headers);

//wachtwoord veranderen in de database
                $queryveranderingwachtwoord = $pdo->prepare("UPDATE treinkoerier SET wachtwoord=? WHERE email=?");
                $queryveranderingwachtwoord->execute(array($sha512wachtwoord, $invoeremail));
            }
        }

        //pagina
        ?>
        <div id="koptekst">Nieuw wachtwoord aanvragen:</br></br></div>
        <div id="tekst1">Bent u uw wachtwoord kwijtgeraakt? dan kunt u hier een tijdelijk wachtwoord opvragen. U ontvangt per e-mail een nieuw wachtwoord die u tijdelijk kunt gebruiken. Na het inloggen met het tijdelijke wachtwoord kun je op je profiel pagina een nieuw wachtwoord kiezen.:</br></br></div>
        <div id="form">
            <form action="nieuw_wachtwoord_aanvragen.php" method="POST">
                <div class="email">E-mailadres:
                    <input type="text" placeholder="voorbeeld@gmail.com" name="email" required=""><br></div>
                <input id="knop" type="submit" value="Wachtwoord aanvragen" class="links" name="verstuur">
            </form></div>
        <div id="foutmelding">
<?php
if ($foutmelding != "") {
    print $foutmelding;
}
?>
        </div>
        <div id="lijn"></div>
        <div class="link">
            Terug naar de <a href="index.php">startpagina.</a><br>
            Nog geen TZT account? <a href="registreren.php">Maak nu een account aan!</a><br>
            terug naar de  <a href="inloggen.php">inlog pagina</a><br>
        </div>



    </body>
</html>
