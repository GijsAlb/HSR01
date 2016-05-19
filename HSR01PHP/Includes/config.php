
<?php

/*
  try {
  $userN = "root";
  $passW = "usbw";
  // $userN = "root";
  // $passW = "usbw";
  $db = new PDO('mysql:dbname=micheic28_tztdb;host=localhost:8080;port=3307;', $userN, $passW);
  echo "jup!";
  } catch (PDOException $e) {
  echo "Connectie naar de database is mislukt: " . $e->getMessage();
  } */


$hostname = '';
$username = 'micheic28_tzt';
$password = 'tztuserkek420';

try {
    $str = "mysql:host=michelvaartjes.nl;dbname=micheic28_tztdb; port=3307";
    $db = new PDO($str, $username, $password);
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo $e->getMessage();
}
?>