<!DOCTYPE html>
<?php
session_start();
$_SESSION["foutinlog"] = 0;
include("Includes/config.php")
?>
<?php
$email = "";
$wachtwoord = "";
$foutingelogd = "";
$wachtwoordinput = "";

if (isset($_POST["submit"])) {
    if ($_SESSION["foutinlog"] > 2) {
        print("U heeft teveel foute inlogpogingen gedaan, probeer het later opnieuw!");
        die();
    }
    $email = htmlentities($_POST["email"]);
    $wachtwoordinput = htmlentities($_POST["wachtwoord"]);
    $wachtwoord = hash("sha512", $wachtwoordinput);

    $stmt = $db->prepare("SELECT idtreinkoerier, wachtwoord FROM treinkoerier WHERE email=?");
    $stmt->execute(array($email));
    $wachtwoorddb = $stmt->fetch();


    //print_r($wachtwoorddb);
    if ($wachtwoord == $wachtwoorddb[1]) {
        session_unregister("foutinlog");
        $_SESSION["ingelogd"] = true;
        $_SESSION["id"] = $wachtwoorddb[0];
        echo "Succesvol ingelogd";
        header("location: loggedin.php");
    } else {
        $foutingelogd = "Onjuiste inloggegevens.";
        $_SESSION["foutinlog"] ++;
    }
}
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
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
