<?php
function generateBarcode($length) {
    $karakters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    $lengteKarakters = strlen($karakters);
    $barcode = "TZT";
    for ($i = 0; $i < $length; $i++) {
        $barcode .= $karakters[rand(0, $lengteKarakters - 1)];
    }
    return $barcode;
}