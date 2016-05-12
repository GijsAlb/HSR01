<!DOCTYPE html>
<?php
//includes en start sessie
session_start();
include("Includes/config.php");
?>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form action="contactformulier.php" method="post">
            <h2>Contactformulier</h2>
            <p>
                <label>Naam:</label> <input type="text" name="naam" placeholder="Naam" value="" required>
            </p>
            <p>
                <label>E-mailadres:</label> <input type="email" name="email" placeholder="E-mailadres" value="" required>
            </p>
            <p>
                <label>Onderwerp:</label> <input type="text" name="onderwerp" placeholder="Onderwerp" value="" required>
            </p>
            <textarea name="tekst" placeholder="Bericht"></textarea>
            <p>
                <input type="submit" name="knop" value="Verzenden">
            </p>
        </form>
    </body>
</html>
