<?php
//array en de hash ophalen
$array = $_GET['array'];
$givenhash = $_GET['hash'];

$salt = "MichelKek123";
//has maken om te checken of de array nog klopt
$hash = $salt."".$array;
$hash = hash("whirlpool", $hash);

//checken of de hash die in de url staat gelijk is met de hash die ik heb gemaakt
if($givenhash != $hash){
	header("location:index.php");
}
//json string decoden naar array
$array = json_decode($array, true);
//afzender info
$postcodeAf= $array[0]['postcode'];
$abkoerier = $array[0]['koerier'];
$abkm = $array[0]['aantalkm'];
$abkosten = $array[0]['prijs'];
//ontvanger info
$postcodeOnt = $array[1]['postcode'];
$cdkoerier = $array[1]['koerier'];
$cdkm = $array[1]['aantalkm'];
$cdkosten = $array[1]['prijs'];

$totaalBedrag = ($array[0]['prijs'] + $array[1]['prijs'] + 2) * 1.2;

if($_POST['submitpakket'] && !empty($_POST['email'])){
	//afzender info
	$naamAf = htmlentities($_POST['naamaf']);
	$email = htmlentities($_POST['email']);
	$huisnrAf = htmlentities($_POST['huisnraf']);
	//ontvanger info
	$naamOnt = htmlentities($_POST['naamaf']);
	$huisnrOnt = htmlentities($_POST['huisnront']);
	//pakket info
	$gewicht = htmlentities($_POST['gewicht']);
	$lengte = htmlentities($_POST['lengte']);
	$breedte = htmlentities($_POST['breedte']);
	$hoogte = htmlentities($_POST['hoogte']);

	if(($lengte * $breedte * $hoogte) < 75000){
		

	}else{
		echo "Pakketje is te groot";
	}

}
?>
<h1>Pakket aanmelden</h1>
<form method='POST' action="">
	<p>
		<h1>Uw gegevens</h1>
		Voornaam*:
		<input type="text" name='naamaf' placeholder="Voornaam" required></br>
		Achternaam*:
		<input type="text" name='naamaf' placeholder="Achternaam" required></br>
		Bedrijfsnaam:
		<input type="text" name='naamaf' placeholder="Bedrijfsnaam"></br>
		Uw email-adres*:
		<input type="email" name='email' placeholder="Uw email" required></br>
		Uw postcode*:
		<input type="text" name='postcodeaf' readonly="readonly" value='<?php echo $postcodeAf ?>'></br>
		Huisnummer*:
		<input type="text" name='huisnraf' placeholder='bv. 43A' required></br>
	</p>
	Naam ontvanger*:</br>
	<input type="text" name='naamont' placeholder='Naam ontvanger' required></br>
	Postcode&huisnummer ontvanger</br>
	<input type="text" name='postcodeont' readonly="readonly" value='<?php echo $postcodeOnt ?>'><input type="text" name='huisnront' placeholder='bv. 43A' required></br>
	<hr />
	Gewicht pakket (in kg)</br>
	<input type="number" name='gewicht' placeholder='Gewicht in kg' min="0" max="7" required></br>
	Grootte pakket
	<i>Het pakket mag max 50 bij 50 bij 30 zijn</i></br>
	Lengte(cm)<input type="number" name='lengte' placeholder='bv. 40' min="0" max="50" required></br>
	Breedte(cm)<input type="number" name='breedte' placeholder='bv. 40' min="0" max="50" required></br>
	Hoogte(cm)<input type="number" name='hoogte' placeholder='bv. 40' min="0" max="50" required></br>
	<hr />
	<a href=''>Service voorwaarden/algemene voorwaarden</a></br>
	<input type='checkbox' required>Ik ga akoord</br>
	<input type='submit' name='submitpakket' value='pakket aanmelden'>
</form>
