<!DOCTYPE html>
<?php
//includes en start sessie
session_start();
include("Includes/config.php");

$foutmelding = "";

if (!isset($_SESSION["ingelogd"])) {
    print("Je moet ingelogd zijn om deze pagina te bekijken!");
    die();
}
try {

    if (isset($_POST['bevestigen'])) {
        try {
            print ("Uw notificatie status is gewijzigt");

            $aan_afmelden = $_POST['aan/afmelden'];

            $stmt = $db->prepare("UPDATE treinkoerier SET actief = ? WHERE idtreinkoerier = ?");
            $stmt->execute(array($aan_afmelden, $_SESSION["id"]));
        } catch (PDOException $e) {
            print("Foutmelding, probeer het later opnieuw");
            echo $e->getMessage();
        }
    }
} catch (PDOException $e) {
    print("Er is iets misgegaan, probeer het later opnieuw");
}
?>


<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form action="aanmeldenafmeldennotificaties.php" method="post">
            <h3>Aanmelden/afmelden voor notificaties</h3>

            <h4>Aanmelden
                <input name="aan/afmelden" selected type="radio" id="optie1" value="1" checked="" >
            </h4>  
             
            <h4>Afmelden
                <input name="aan/afmelden" type="radio" id="optie2" value="0">
            </h4>  
            <p>
                <input type="submit" name="bevestigen" value="verzenden">
            </p>
        </form>
    </body>
</html>

