<?php
session_start();
if(!isset($_SESSION["ingelogd"])) {
    die();
}
?>


<script>
alert("U bent ingelogd");
window.setTimeout(function(){ window.location = "index.php"; },0);
</script>

