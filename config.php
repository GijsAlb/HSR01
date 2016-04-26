<?php

try{
	$userN = "usernamehere";
	$passW = "passwordhere";

	// $userN = "root";
	// $passW = "usbw";
	$db = new PDO('mysql:dbname=micheic28_tztdb;host=michelvaartjes.nl;', $userN, $passW);

	echo "jup!";
}catch(PDOException $e){
	echo "Connectie naar de database is mislukt: ". $e->getMessage();
}
?>