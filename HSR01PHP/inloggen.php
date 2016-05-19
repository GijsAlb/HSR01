<!DOCTYPE html>
<html lang="nl">
    <?php
    session_start();
    include("Includes/config.php")
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

        $stmt = $pdo->prepare("SELECT idtreinkoerier, wachtwoord FROM treinkoerier WHERE email=?");
        $stmt->execute(array($email));
        $wachtwoorddb = $stmt->fetch();

        if ($wachtwoord == $wachtwoorddb[1]) {
            $_SESSION["ingelogd"] = true;
            $_SESSION["id"] = $wachtwoorddb[0];
            echo "Succesvol ingelogd";
        } else {
            $foutingelogd = "Onjuiste inloggegevens.";
        }
    }
    ?>



    <head>
        <link rel="shortcut icon" href="img/favicon.png" type="image/x-icon" />

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Michel en Justin">

        <title>Inloggen</title>

        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >

        <!-- CSS -->
        <link rel="stylesheet" href="css.css">
        <link rel="stylesheet" href="inloggen.css">

    </head>

    <body id="page-top">
        <?php
        // include 'includes/header.php';
        ?>

        <!--  Section -->
        <section id="content" class="content-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10  contentbox">
                        <h1 class="tzttitle">Inloggen</h1>
                        <form method="post" action="inloggen.php">
                            <p><?php print($foutingelogd); ?></p>
                            <p style="font-family: Calibri">E-mail:
                                <input style="margin-left: 60px; font-family: Calibri;" type="email" name="email" placeholder="Uw e-mail adres" value="<?php print($email) ?>" >
                            </p>

                            <p style="font-family: Calibri">Wachtwoord:
                                <input class="invulbalk"style="margin-left: 6px; font-family: Calibri;"type="password" name="wachtwoord" placeholder="Uw wachtwoord" value="<?php print($wachtwoordinput) ?>" required>
                            </p>

                            <a style="font-family: Calibri;" href="nieuw_wachtwoord_aanvragen.php">Wachtwoord vergeten?</a>
                            <br>
                            <input class="btn" style="margin-left: 0 !important; " type="submit" name="submit" value="Inloggen!">
                            <input href="nieuw_wachtwoord_aanvragen.php" class="btn" style="margin-left: 20px !important; " type="submit" name="registreren" value="Registreren!">
                        </form>

                        <!--   <br>
                           <p style="font-family: Calibri;">Heeft u nog geen account? </p>
                               <br>-->

                    </div>

                </div>
            </div>
        </section>
        <section id="footer" style="position: fixed !important;"class="footer-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <?php
                        // include "includes/footer.php"
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

