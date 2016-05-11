
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
    <div class="top-image">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="menucontainer">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand page-scroll" href="#page-top"><img id="logoimg" src="tzt.png"/> </a></br>
                    <span style='color:#FF6600; margin-left:15px;'>THE <groen style='color:#4EC148'>GREEN</groen> WAY</span>
                </div>

                <!-- Collect the nav links for toggling -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav navbar-left">
                        <li class="hidden">
                             <a class="page-scroll" href="#page-top"></a>
                        </li>
                        <li>
                           <a class="page-scroll" href="#treinkoerier"><span>Treinkoerier worden</span></a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#over"><span>Over TZT</span></a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#contact"><span>Contact</span></a>
                        </li>
                        <li>
                            <a class="page-scroll" href="#nieuws"><span>Nieuws</span></a>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                           <span class='volg'>Volg ons op</span>
                        </li>
                        <li>
                            <a><span><img style='height:50px;' src="https://thebellseend.files.wordpress.com/2012/10/facebook-twitter-logo-icon.jpg"></span></a>
                        </li>
                    </ul>
                </div> <!-- /.navbar-collapse -->      
            </div> <!-- /.container -->      
        </nav>
    </div> <!-- /.top-image -->

    <!--  Section -->
    <section id="about" class="about-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 leftbox">
                	<input type='search' placeholder="Zoeken..."><span class="glyphicon glyphicon-search" style='color:#4EC148' aria-hidden="true"></span></br>
                	Heb je directe vragen?</br>
                	Bel dan: 0800-2636<span class="glyphicon glyphicon-earphone" style='color:#4EC148' aria-hidden="true"></span></br>
                	Of sms naar: 2636<span class="glyphicon glyphicon-comment" style='color:#4EC148' aria-hidden="true"></span></br></br>
                	<p style="font-family:CtB;font-size:22px;">Heb je feedback voor ons?</p>
                	<input type='submit' class="button" value='Feedback geven'>
                </div>
                <div class="col-lg-6 middlebox">
                	<h1>Tarief berekenen</h1>

                	<p>
                		<form method='post'>
							Van: <input placeholder='8376HL' class='zipcode' type='text' name='afzender' pattern='[1-9][0-9]{3}[a-zA-Z]{2}'>
							Naar: <input placeholder='1043EB' class='zipcode' type='text' name='ontvanger' pattern='[1-9][0-9]{3}[a-zA-Z]{2}'> 
							<input type='submit' class='button' value='Berekenen' name='zipsubmit'>
						</form>
                	</p>
                </div>
                <div class="col-lg-3 rightbox">
                	Login mijnTZT</br>
                	<input type='text' placeholder='gebruikersnaam'></br>
                	<input type='password' style='margin-top:2px;' placeholder='wachtwoord'></br>
                	<input type='submit' style='margin-top:2px;' class='button'></br></br>
                	Track & trace</br>
                	<input type='text' placeholder='Pakketnummer'></br>
                	<input type='submit' style='margin-top:2px;' class='button'>
                </div>
            </div>
        </div>
    </section>
    <section id="maincontent" class="maincontent-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 maincontent">
                    <img src="manmetdoos.png" class='mainman'>
                   <span style='color:#FF6600;'><h1>TZT nu nog <groen style='color:#4EC148'>groener!</groen></h1></span>
                    <p>
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
    <section id="footer" class="footer-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <a href="#page-top"><img class='smallimg' src="tzt.png"/> </a>
                    	TZT HEUJ
                </div>
            </div>
        </div>
    </section> 

    <!-- JavaScript -->
    <script src="http://michelvaartjes.nl/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="http://michelvaartjes.nl/js/bootstrap.min.js"></script>

    <script src="http://michelvaartjes.nl/js/jquery.easing.min.js"></script>
    <script src="http://michelvaartjes.nl/js/scrolling-nav.js"></script>
</body>

</html>
