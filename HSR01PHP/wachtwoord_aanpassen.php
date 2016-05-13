<?php
 
session_start();
 
//pagina layout        
//inlog check
 if (!isset($_SESSION["ingelogd"])) {
    print("Je moet ingelogd zijn om deze pagina te bekijken!");
    die();
}
$melding = "";
$foutmelding = "";
 
if(isset($_POST['opslaan'])){
     
    if($_POST['vorigewachwoord'] !== "") {
//post variable opslaan    
    $nieuwewachtwoord1 = htmlentities($_POST['nieuwewachwoord1']);
    $vorigewachtwoord = htmlentities($_POST['vorigewachwoord']);
    $nieuwewachtwoord2 = htmlentities($_POST['nieuwewachwoord2']);
     
//encryptie naar sha512    
    $vorigewachtwoordsha512 = hash( 'sha512', $vorigewachtwoord);
    $nieuwewachtwoord1sha512 = hash( 'sha512', $nieuwewachtwoord1);
    $nieuwewachtwoord2sha512 = hash( 'sha512', $nieuwewachtwoord2);
 
//checken oof nieuwewachtwoord 2 keer juist is ingevoerd    
    if($nieuwewachtwoord1 == $nieuwewachtwoord2){
 
//query opzoeken of het vorige wachtwoord juist is
    $pdo = new PDO("mysql:host=localhost;dbname=micheic28_tztdb;port=3307", "root", "usbw");
    $querywachtwoordcheck = $pdo->prepare("SELECT idtreinkoerier FROM treinkoerier WHERE wachtwoord=?");
    $querywachtwoordcheck->execute(array($vorigewachtwoordsha512));
    $idtreinkoerier = $querywachtwoordcheck->fetch();
    $idcheck = $idtreinkoerier[0];
        if ($querywachtwoordcheck->rowCount() >= 1 && $_SESSION['id'] == $idcheck) {
             
                if (strlen ($nieuwewachtwoord1) > 5 && preg_match('/[\'^£$!%&*()}{@#~?><>,|=_+¬-]/', $nieuwewachtwoord1 )){
                     
                $queryveranderingwachtwoord = $pdo->prepare("UPDATE treinkoerier SET wachtwoord=? WHERE idtreinkoerier=?");
                $queryveranderingwachtwoord->execute(array($nieuwewachtwoord1sha512, $idcheck));
            if($queryveranderingwachtwoord->rowCount() == 1){        
                $melding = "Uw wachtwoord is veranderd.";
                $_SESSION['pagina'] = "wachtwoordaanpassen";
                header("refresh:5;url=uitloggen.php");
            }   else{
                $foutmelding = "er is iets fout gegaan met het veranderen van het wachtwoord. meldt dit AUB aan ons.";
            }
        } else {
            $foutmelding = "Dit wachtwoord voldoet niet aan de eisen";
        }
    } else {
        $foutmelding = "uw vorige wachtwoord is fout ingevuld";
}} else {
    $foutmelding = "Nieuwe wachtwoorden komen niet met elkaar overheen";
}} else
{ 
    $foutmelding = "Niet alle velden zijn ingevuld";
}}
?>
 
<html>
    <head>
        <meta charset="UTF-8">
        <title>Wachtwoord wijzigen</title>
      
    </head>
    <body>
         
        <div>
            Wachtwoord aanpassen
        </div><br>
         
        <div> 
            Op deze pagina kunt u uw wachtwoord aanpassen. <br><br> Als u hier bent omdat u uw wachtwoord bent vergeten vul dan bij vorig wachtwoord het wachtwoord in die u via de mail ontvangen heeft.
        </div><br>
 
        <div>
            <form action="wachtwoord_aanpassen.php" method="POST">
                <label>Uw vorige wachtwoord:</label>
                <input type="password" placeholder="*****" name="vorigewachwoord" value="" required=""><br><br><br>
                <label>Uw nieuwe wachtwoord:</label>   
                <input type="password" placeholder="*****" name="nieuwewachwoord1" value="" required=""><br><br><br>
                <label>Nog een keer uw nieuwe wachtwoord:</label> 
                <input type="password" placeholder="*****" name="nieuwewachwoord2" value="" required=""><br><br><br>
                    <div>  Uw wachtwoord moet minstens uit 8 tekens bestaan en moet minimaal één speciale teken bevatten. </div>
                <input type="submit" id="knop" value="Opslaan" name="opslaan"><br>
            </form>
            <div>
                    <?php  
                    print $foutmelding;
                    print $melding;
                    ?>
            </div>
                 
        </div>
                
       
        
        
       
         
      
             

       
    </body>
</html>
