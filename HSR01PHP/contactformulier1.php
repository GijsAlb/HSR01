<!DOCTYPE html>
<?php
//includes en start sessie
session_start();
include("Includes/config.php");


$foutmelding = "";

date_default_timezone_set('Europe/Amsterdam');



?>
<html>
    <head>
        <title> Contactformulier </title>

    </head>
    

        <?php
        if (isset($_POST['verstuur'])) {
            //set variable voor de rest
            $invoeremail = ("TZT@gmail.com");

//mail maken en versturen
                $naar = $invoeremail;
                $onderwerp = "";
                $verhaal = "
                   <html>
                   <head>
                   <title>
                        Nieuw wachtwoord
                   </title>
                   <body>
                        <p>
                        " . $bericht . "<br>
                        </p>
                   </body>
                   </html>
                   ";
                $headers = "MIME-Version: 1.0" . "\r\n";
                $headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";
                $headers .= 'From: <webmail@HSR-TZT.nl>' . "\r\n";

        mail($naar, $onderwerp, $verhaal, $headers); } 
        
?>
    
      <form action="nieuw_wachtwoord_aanvragen.php" method="POST">
                <div class="email">E-mailadres:
                    <input type="text" placeholder="voorbeeld@gmail.com" name="email" required=""><br></div>
                <input id="knop" type="submit" value="Wachtwoord aanvragen" class="links" name="verstuur">
            </form>