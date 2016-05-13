<?php

try{
	$userN = "micheic28_tzt";
	$passW = "tztuserkek420";

	$db = new PDO('mysql:dbname=micheic28_tztdb;host=michelvaartjes.nl;', $userN, $passW);
}catch(PDOException $e){
	echo "Connectie naar de database is mislukt: ". $e->getMessage();
}





?>
