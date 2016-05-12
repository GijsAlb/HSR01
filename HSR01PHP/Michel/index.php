
<!DOCTYPE html>
<html lang="nl">

<head>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Michel Vaartjes">

    <title>TZT HSR01</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >

    <!-- CSS -->
    <link href="css.css" rel="stylesheet">
    

</head>

<body id="page-top">
    <?php
        include_once "includes/header.php"
    ?>
    <!--  Section -->
    <section id="about" class="about-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 leftbox">
                	<input type='search' style='width:90%' placeholder="Zoeken..."><span class="glyphicon glyphicon-search" style='color:#4EC148' aria-hidden="true"></span></br>
                	Heb je directe vragen?</br>
                	Bel dan: 0800-2636<span class="glyphicon glyphicon-earphone" style='color:#4EC148' aria-hidden="true"></span></br>
                	Of sms naar: 2636<span class="glyphicon glyphicon-comment" style='color:#4EC148' aria-hidden="true"></span></br></br><img class='pijl' src="img/pijltje.png">
                	<p class='feedtitle'>Heb je feedback voor ons?</p>
                	<input type='submit' class="button" value='Feedback geven'>
                </div>
                <div class="col-lg-6 middlebox">
                	<h1>Tarief berekenen</h1>

                	<p>
                		<form id='form' method='POST'>
							Van: <input placeholder='8376HL' class='zipcode' type='text' id='afzender' name='afzender' pattern='[1-9][0-9]{3}[a-zA-Z]{2}' required>
							Naar: <input placeholder='1043EB' class='zipcode' type='text' id='ontvanger' name='ontvanger' pattern='[1-9][0-9]{3}[a-zA-Z]{2}' required>
							<input type='submit' class='button' value='Berekenen' id='bereken'>
						</form>

                	</p>
                </div>
                <div class="col-lg-3 rightbox">
                	Login mijnTZT</br>
                	<input style='width:100%' type='text' placeholder='Gebruikersnaam'></br>
                	<input style='width:100%' type='password' style='margin-top:2px;' placeholder='Wachtwoord'></br>
                	<input type='submit' style='margin-top:2px;' class='button'></br></br>
                	Track & trace</br>
                	<input style='width:100%' type='text' placeholder='Pakketnummer'></br>
                	<input type='submit' style='margin-top:2px;' class='button'>
                </div>
            </div>
        </div>
    </section>
    <section id="maincontent" class="maincontent-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 maincontent">
                    <img src="img/manmetdoos.png" class='mainman'>
                   <span style='color:#FF6600;'><h1>TZT nu nog <groen style='color:#4EC148'>groener!</groen></h1></span>
                    <p style='font-family:calibri'>
                        In het afgelopen kwartaal is uit cijfers gebleken dat TZT top
                        20% groener is geworden. Het aantal pakketjes dat bia het OV wordt
                        verzonden is gestegen. Dat betekent dat er minder pakketten met bussen
                        vervoerd hoefden te worden.
                        <button style='margin-left:25%' class='button'> Lees verder</button>
                    </p>
                </div>
            </div>
        </div>
    </section>
    <!-- mobiele versie -->
    <section id="tariefmobiel" class="tariefmobiel-section">
        <div class="container">
            <div class="row">
                <div class="col-md-8 tariefmobiel mobiel">
                    <h1>Tarief berekenen</h1>

                    <p>
                        <form id='form' method='POST'>
                            Van: <input placeholder='8376HL' class='zipcode' type='text' id='afzender' name='afzender' pattern='[1-9][0-9]{3}[a-zA-Z]{2}' required>
                            Naar: <input placeholder='1043EB' class='zipcode' type='text' id='ontvanger' name='ontvanger' pattern='[1-9][0-9]{3}[a-zA-Z]{2}' required>
                            <input type='submit' class='button' value='Berekenen' id='bereken'>
                        </form>

                    </p>
                </div>
            </div>
        </div>
    </section>
    <section id="zoekbalkmobiel" class="zoekbalkmobiel-section">
        <div class="container">
            <div class="row">
                <div class="col-md-8 zoekbalkmobiel mobiel">
                    <input class='mobielzoek' type='search' placeholder="Zoeken...">&nbsp;<span class="glyphicon glyphicon-search" style='color:#4EC148' aria-hidden="true"></span></br>
                </div>
            </div>
        </div>
    </section>

    <section id="infomobiel" class="infomobiel-section">
        <div class="container">
            <div class="row">
                <div class="col-md-8 infomobiel mobiel">
                    <p>
                    Heb je directe vragen?</br>
                    Bel dan: 0800-2636<span class="glyphicon glyphicon-earphone" style='color:#4EC148' aria-hidden="true"></span></br>
                    Of sms naar: 2636<span class="glyphicon glyphicon-comment" style='color:#4EC148' aria-hidden="true"></span></br>
                    <span class='volg'>Volg ons op<a>&nbsp;&nbsp;<img style='width:110px;' src="img/social.png"></a></span>
                    </p>
                </div>
            </div>
        </div>
    </section>
    <!-- eind mobiele versie  -->     
    <section id="footer" class="footer-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    	TZT HEUJ
                </div>
            </div>
        </div>
    </section> 

    <!-- Modal -->
    <div id="myModal" class="modal fade" data-keyboard="false" data-backdrop="static" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Berekening</h4>
          </div>
          <div class="modal-body" style='font-family:calibri'>
            <img id='loading' style='display:none' src='img/loading.gif'>
          </div>
          <div class="modal-footer">
            <button type="button" class="modelclose btn btn-default" data-dismiss="modal">Sluit</button>
          </div>
        </div>

      </div>
    </div>

    <!-- JavaScript -->
    <script src="http://michelvaartjes.nl/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="http://michelvaartjes.nl/js/bootstrap.min.js"></script>

    <script src="http://michelvaartjes.nl/js/jquery.easing.min.js"></script>
    <script src="http://michelvaartjes.nl/js/scrolling-nav.js"></script>
</body>

    <script type="text/javascript">
        $(document).ready(function(){
            $('#form').submit(function(e){
                e.preventDefault();
                $('#myModal').modal('show');
                $('#loading').show();
                var afzender = $("#afzender").val();
                var ontvanger = $("#ontvanger").val();

                $.ajax({
                      url: "includes/berekenFunc.php",
                      method: "POST",
                      data:{"afzender": afzender, "ontvanger": ontvanger},
                      success: function(html){
                        $('#loading').hide();
                        $('.modal-body').append(html);
                      },
                });
                $('.modelclose').click(function(){
                    location.reload();
                });
            });
        });
    </script>

</html>
