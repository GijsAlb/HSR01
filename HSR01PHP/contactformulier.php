<!DOCTYPE html>
<?php
//includes en start sessie
session_start();
include("Includes/config.php");

$foutmelding = "";

date_default_timezone_set('Europe/Amsterdam');


if (isset($_POST['verzenden'])) {
    // variablen voor de gegevens 
    $invoeremail = htmlentities("TZT@gmail.com");
    $onderwerp = htmlentities($_POST['onderwerp']);
    $naam = htmlentities($_POST['naam']);
    $bericht = htmlentities($_POST['bericht']);
    $van = htmlentities($_POST['van']);
    $categorie_en_onderwerp =$_POST['categorie'] . ' ' . $_POST['onderwerp'];
    $categorie = $_POST['categorie'];
    
    print ("Uw aanvraag is verwerkt");
//mail maken en versturen
    $naar = $invoeremail;
    $onderwerp = "$categorie_en_onderwerp";
    $verhaal = "
                   <html>
                   <head>
                    <style> table {
                             border: 1px solid white;
                             width: 600px !important;
                    }
                    </style>
                   <title>
                        Nieuw wachtwoord
                   </title>
                   <body>
                 
                      <table > <tr> <td> 
                        " . $bericht . " 
                      </td>
                      </tr>
                      </table>
                        <p>
                        mvg " . $naam . " 
                       </p>
                       </div>
                   </body>
                   </html>
                   ";
    $headers = "MIME-Version: 1.0" . "\r\n";
    $headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";
    $headers .= 'From: ' . $van . ' ' . "\r\n";

    mail($naar, $onderwerp, $verhaal, $headers);
}
// hieronder formuliertje     
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form action="contactformulier.php" method="post">
            <h2>Contactformulier</h2>
            <p>
                <label>Naam:</label> <input type="text" name="naam" placeholder="Naam"  required>
            </p>
            <p>
                <label>E-mailadres:</label> <input type="email" name="van" placeholder="E-mailadres"  required>
            </p>
            <p>
                <label>Onderwerp:</label> <input type="text" name="onderwerp" placeholder="Onderwerp"  required>
            </p>
            <p> 
            <label>Categorie:</label> <select name="categorie" >
    <option value="(Algemeen)">Algemeen</option>
    <option value="(Klacht)">Klacht</option>
    <option value="(Compliment)">Compliment</option>
            </select> </p>
            
            <textarea name="bericht"  placeholder="Uw bericht" required=""  ></textarea>
            <p>
                <input type="submit" name="verzenden" value="verzenden">
            </p>
        </form>
    </body>
</html>
