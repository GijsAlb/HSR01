<!DOCTYPE html>
<?php

function autocomplete() {

    $stations = array();
    $return = "";
    $url = "http://webservices.ns.nl/ns-api-stations-v2";
    $ww = "h1xeA17XHDFc1qWpy_nVEoEvAyHvB8LVRP_I0tyY-QjrcuxXxln-8w";
    $gebr = "Gijs4u@gmail.com";
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_USERPWD, "$gebr:$ww");
    curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
    $output = curl_exec($ch);
    $info = curl_getinfo($ch);

    if ($info["http_code"] != 200) {
        print("We konden geen verbinding maken met ons systeem, probeer het later opnieuw!");
        $fout = true;
    }
    //parser voor xml zodat je het kan gebruiken
    $xmlobj = simplexml_load_string($output);
    //dan encode en decode meteen en de true maakt het een array zonder true heb je een jsonobject
    $array = json_decode(json_encode($xmlobj), true);

    for ($i = 0; $i < count($array["Station"]); $i++) {
        if ($array['Station'][$i]['Land'] == "NL") {
            $stations[] = $array['Station'][$i]['Namen']['Lang'];
        }
    }
    curl_close($ch);


    for ($i = 0; $i < count($stations); $i++) {
        echo "array(\"label\" => \"$stations[$i]\", \"value\" => \"$stations[$i]\"), <br>";
    }
}

autocomplete();
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>

    </body>
</html>
