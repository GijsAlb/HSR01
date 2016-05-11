
<?php
//error_reporting(0);
include "config.php";

function checkReisplan($db, $beginstation, $eindstation, $array){
	//hash en array meegeven in url. Hash om te checken of de array nog klopt
	$query = json_encode($array);
	$salt = "MichelKek123";
	$hash = $salt."".$query;
	$hash = hash("whirlpool", $hash);

	try{
		$stmt = $db->prepare("SELECT * FROM reisplan WHERE beginstation = :begin AND eindstation = :eind OR beginstation = :eind AND eindstation = :begin ");
		$stmt->bindParam(':begin', $beginstation);
		$stmt->bindParam(':eind', $eindstation);
		$stmt->execute();

		if($stmt->rowCount() == 0){
			echo "Geen treinkoeriers gevonden die uw pakketje kunnen meenemen";
		}else{
			echo "Er zijn treinkoerier beschikbaar voor het gekozen traject van uw pakketje <a href='includes/verstuur.php?array=".$query."&hash=".$hash."'><button class='button'>Verstuur pakketje</button></a>";
		}
	}catch(PDOExceptions $e){
		echo $e->getMessage();
	}
}

//zoek alle stations die dichtbij de afzender en ontvanger zitten
function stations($startCode, $endCode){
	//alle begin stations in array km komt meer in zoals prijs koeriers etc
	$afzenderStations = array();
	$ontvangerStations = array();

	$url = "https://maps.googleapis.com/maps/api/geocode/json?address=". $startCode ."&key=AIzaSyBAtBGzg51rSwEbmcb5udCffFUXQVhV4uA";
	//het respons in json, meteen decode zodat het als een array gebruikt kan worden
	$jsonresp = json_decode(file_get_contents($url), true);
	//als de status ok is pak de lengte- en breedtegraad
	if($jsonresp['status'] == "OK"){
		$aflat = $jsonresp['results'][0]['geometry']['location']['lat'];
		$aflng = $jsonresp['results'][0]['geometry']['location']['lng'];
	}

	//pak alle dichtbijzijnde stations van de gepakte lat en lng met een straal van 25km
	$url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=". $aflat ."+". $aflng ."&radius=20000&type=train_station&mode=transit&transit_mode=BICYCLE&key=AIzaSyBAtBGzg51rSwEbmcb5udCffFUXQVhV4uA";
	//het respons in json, meteen decode zodat het als een array gebruikt kan worden
	$jsonresp = json_decode(file_get_contents($url), true);

	//als de statys ok is doe alle stations in een array
	if($jsonresp['status'] == "OK"){
		for ($i=0; $i < 1; $i++) {
			$afzenderStations['afzender'] = $jsonresp['results'][$i]['name'];
		}
	}else{
		//is status false heb je pech houd het op
		return false; 
	}

	//url waar de request heen gaat met het goede adres
	$url = "https://maps.googleapis.com/maps/api/geocode/json?address=". $endCode ."&key=AIzaSyBAtBGzg51rSwEbmcb5udCffFUXQVhV4uA";
	//het respons in json, meteen decode zodat het als een array gebruikt kan worden
	$jsonresp = json_decode(file_get_contents($url), true);
	//als de status ok is pak de lengte- en breedtegraad
	if($jsonresp['status'] == "OK"){
		$ontlat = $jsonresp['results'][0]['geometry']['location']['lat'];
		$ontlng = $jsonresp['results'][0]['geometry']['location']['lng'];
	}

	//pak alle dichtbijzijnde stations van de gepakte lat en lng met een straal van 25km
	$url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=". $ontlat ."+". $ontlng ."&radius=20000&type=train_station&mode=transit&transit_mode=BICYCLE&key=AIzaSyBAtBGzg51rSwEbmcb5udCffFUXQVhV4uA";
	//het respons in json, meteen decode zodat het als een array gebruikt kan worden
	$jsonresp = json_decode(file_get_contents($url), true);

	//als de statys ok is doe alle stations in een array
	if($jsonresp['status'] == "OK"){
		for ($i=0; $i < 1; $i++) {
			$ontvangerStations['ontvanger'] = $jsonresp['results'][$i]['name'];
		}
		return array_merge($afzenderStations, $ontvangerStations);
	}else{
		//is status false heb je pech houd het op
		return false; 
	}
	//standaard is er niks dus return false
	return false;
}


//de reis van afzender naar station hoever, welke koerier etc
function reis($startCode, $beginstation, $endCode, $eindstation ){
	$afzenderArray = array();
	$afzenderArray['postcode'] = $startCode;
	$afzenderArray['station'] = $beginstation;

	$ontvangerArray = array();
	$ontvangerArray['postcode'] = $endCode;
	$ontvangerArray['station'] = $eindstation;

	//om te kijken hoever het is vanaf de afzender naar het begin station
	$url = "https://maps.googleapis.com/maps/api/directions/json?origin=". $startCode ."&destination=". str_replace(" ", "+", $beginstation) ."&mode=bicycling&key=AIzaSyBAtBGzg51rSwEbmcb5udCffFUXQVhV4uA";
	$jsonresp = json_decode(file_get_contents($url), true);
	if($jsonresp['status'] == "OK"){
		//de hoeveelheid in km
		$kmafzender = intval($jsonresp['routes'][0]['legs'][0]['distance']['text']);
		$koerier = "Fietskoerier";
		$prijs = 9;
		//standaard word er eerst gekeken binnen 4km omdat er fietspaden zijn waar auto's niet mogen komen
		if($kmafzender > 4){
			//als het meer dan 4 is worden er auto koeriers in gezet betekend andere route andere kilometers.
			$url = "https://maps.googleapis.com/maps/api/directions/json?origin=". $startCode ."&destination=". str_replace(" ", "+", $beginstation) ."&key=AIzaSyBAtBGzg51rSwEbmcb5udCffFUXQVhV4uA";
			$jsonresp = json_decode(file_get_contents($url), true);
			//de hoeveelheid in km
			$kmafzender = intval($jsonresp['routes'][0]['legs'][0]['distance']['text']);
			$koerier = "Pietersen koerier";
			$prijs = 10;
			if($kmafzender > 25){
				for ($i=25; $i < $kmafzender; $i++) { 
					$prijs += 0.39;
				}
			}
		}elseif($kmafzender > 31){
			$koerier = "Bodekoeriers";
			$prijs = 12.40;
			for ($i=32; $i < $kmafzender; $i++) { 
					$prijs += 0.40;
			}
		}

		$afzenderArray['aantalkm'] = $kmafzender;
		$afzenderArray['koerier'] = $koerier;
		$afzenderArray['prijs'] = $prijs;
	}else{
		echo "Er is iets verkeerd gegaan met het ophalen van de info probeer het later opnieuw of neem contact met ons op.";
		return false;
	}

	//om te kijken hoever het is vanaf het eind station naar de ontvanger
	$url = "https://maps.googleapis.com/maps/api/directions/json?origin=". $endCode ."&destination=". str_replace(" ", "+", $eindstation) ."&mode=bicycling&key=AIzaSyBAtBGzg51rSwEbmcb5udCffFUXQVhV4uA";
	$jsonresp = json_decode(file_get_contents($url), true);
	if($jsonresp['status'] == "OK"){
		//de hoeveelheid in km
		$kmontvang = intval($jsonresp['routes'][0]['legs'][0]['distance']['text']);
		$koerier = "Fietskoerier";
		$prijs = 9;
		//standaard word er eerst gekeken binnen 4km omdat er fietspaden zijn waar auto's niet mogen komen
		if($kmontvang > 4){
			//als het meer dan 4 is worden er auto koeriers in gezet betekend andere route andere kilometers.
			$url = "https://maps.googleapis.com/maps/api/directions/json?origin=". $endCode ."&destination=". str_replace(" ", "+", $eindstation)  ."&key=AIzaSyBAtBGzg51rSwEbmcb5udCffFUXQVhV4uA";
			$jsonresp = json_decode(file_get_contents($url), true);
			//de hoeveelheid in km
			$kmontvang = intval($jsonresp['routes'][0]['legs'][0]['distance']['text']);
			$koerier = "Pietersen koerier";
			$prijs = 10;
			if($kmontvang > 25){
				for ($i=25; $i < $kmontvang; $i++) { 
					$prijs += 0.39;
				}
			}
		}elseif($kmontvang > 31){
			$koerier = "Bodekoeriers";
			$prijs = 12.40;
			for ($i=32; $i < $kmontvang; $i++) { 
					$prijs += 0.40;
			}
		}

		$ontvangerArray['aantalkm'] = $kmontvang;
		$ontvangerArray['koerier'] = $koerier;
		$ontvangerArray['prijs'] = $prijs;

		return array($afzenderArray, $ontvangerArray);
		//return $ontvangerArray;
	}else{
		echo "Er is iets verkeerd gegaan met het ophalen van de info probeer het later opnieuw of neem contact met ons op.";
		return false;
	}
	return false;
}


//met adres uit database
//kijken welke stations er het dichtste bij de ontvanger zitten
//alle eindstations in array met km
if(!empty($_POST['afzender']) AND !empty($_POST['ontvanger'])){
	$afzender = htmlentities($_POST['afzender']);
	$ontvanger = htmlentities($_POST['ontvanger']);

	$stations = stations($afzender, $ontvanger);
	if($stations != false AND $stations['afzender'] != $stations['ontvanger']){
		$test = reis($afzender, $stations['afzender'], $ontvanger, $stations['ontvanger']);
		
		$totaalBedrag = ($test[0]['prijs'] + $test[1]['prijs'] + 2) * 1.2;
		
		echo "<table>";
		echo "<tr><th></th><th>Van</th><th>Naar</th></tr>";
		echo "<tr><th>Postcode</th><td>". $test[0]['postcode'] ."</td><td>". $test[1]['postcode'] ."</td></tr>";
		echo "<tr><th>Station</th><td>". $test[0]['station'] ."</td><td>". $test[1]['station'] ."</td></tr>";
		echo "<tr><th>Koerier</th><td>". $test[0]['koerier'] ."</td><td>". $test[1]['koerier'] ."</td></tr>";
		echo "<tr><th>Koerier km</th><td>". $test[0]['aantalkm'] ."</td><td>". $test[1]['aantalkm'] ."</td></tr>";
		echo "</table>";
		echo "Het totaal bedrag bedraagt: ". number_format($totaalBedrag, 2) ."Euro </br>";
		checkReisplan($db, $stations['afzender'], $stations['ontvanger'], $test);
	}else{
		echo "De 2 postcodes mogen niet in dezelfde buurt liggen";
	}
}
?>