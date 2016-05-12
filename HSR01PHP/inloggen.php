<!DOCTYPE html>
<?php
session_start();

?>
<?php
$email = "";
$wachtwoord = "";
$foutingelogd = "";
$wachtwoordinput = "";

if (isset($_POST["submit"])) {
    $email = htmlentities($_POST["email"]);
    $wachtwoordinput = htmlentities($_POST["wachtwoord"]);
    $wachtwoord = hash("sha512", $wachtwoordinput);

    $stmt = $db->prepare("SELECT wachtwoord FROM treinkoerier WHERE email=? LIMIT 1");
    $stmt->execute(array($email));
    $wachtwoorddb = $stmt->fetch();

    print($wachtwoorddb[0]);
    print("</br><br>" . $wachtwoord . "<br>");
    //print_r($wachtwoorddb);
    if ($wachtwoord == $wachtwoorddb[0]) {
        $_SESSION["ingelogd"] = true;
        echo "Geluk! het werkt";
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
        Wachtwoord vergeten?  <a href="nieuw_wachtwoord_aanvragen.php">vraag nu een nieuw wachtwoord aan!</a><br> 
        Heeft u nog geen account?  <a href="nieuw_wachtwoord_aanvragen.php">vraag nu een account aan!</a><br> 
    </body>
</html>
