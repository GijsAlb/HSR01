<!DOCTYPE html>
<?php
//includes en start sessie
session_start();
include("Includes/config.php");
?>
<?php
$email = "";
$wachtwoord = "";
$foutingelogd = "";
$wachtwoordinput = "";
//inlogcontrole
if (isset($_POST["submit"])) {
    //hash wachtwoord + id uit database
    $email = htmlentities($_POST["email"]);
    $wachtwoordinput = htmlentities($_POST["wachtwoord"]);
    $wachtwoord = hash("sha512", $wachtwoordinput);

    $stmt = $db->prepare("SELECT idtreinkoerier, wachtwoord FROM treinkoerier WHERE email=?");
    $stmt->execute(array($email));
    $wachtwoorddb = $stmt->fetch();


    //controle of inloggegevens correct zijn
    if ($wachtwoord == $wachtwoorddb[1]) {
        $_SESSION["ingelogd"] = true;
        $_SESSION["id"] = $wachtwoorddb[0];
        echo "Succesvol ingelogd";
    } else {
        $foutingelogd = "Onjuiste inloggegevens.";
    }
}
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <!-- formulieren met inputvelden+submit en evt foutmeldingen -->
        <form method="post" action="inloggen.php">
            <p><?php print($foutingelogd); ?></p>
            <p>E-mail:
                <input type="email" name="email" placeholder="Uw e-mail adres" value="<?php print($email) ?>" >
            </p>

            <p>Wachtwoord:
                <input type="password" name="wachtwoord" placeholder="Uw wachtwoord" value="<?php print($wachtwoordinput) ?>" required>
            </p>

            <input type="submit" name="submit" value="Inloggen!">
        </form>
    </body>
</html>
