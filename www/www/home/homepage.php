<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Home Rehab</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/expandStylesheet.css">
<link href="css/prettify.css" type="text/css" rel="stylesheet">

<!-- Bootstrap Core CSS -->
<link href="csss/bootstrap.min.css" rel="stylesheet" type="text/css">

<!-- Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="csss/animate.css" rel="stylesheet" />
<!-- Squad theme CSS -->
<link href="csss/style.css" rel="stylesheet">
<link href="color/default.css" rel="stylesheet">
<?php
	session_start();
	if ($_SESSION['did'] == ""){
		header('Location: /home/sessionError.html');	
	}
?>


<style>
body {
	background-image: url("/home/images/bg1.jpg");
	background-attachment:fixed;
}

.content {
	display: none;
}

#demo-with-image .expander.expanded {
	padding-left: 13px;
	background-position: left center;
	background-repeat: no-repeat;
}

#demo-with-image .expander.collapsed {
	padding-left: 13px;
	background-position: left center;
	background-repeat: no-repeat;
}

#demo3.expanded .expander {
	font-weight: bold;
	font-size: 125%;
}

#demo3.expanded .content {
	display: inherit;
}

#demo3.collapsed .content {
	display: none;
}

#demo-nesting-sub1 {
	margin-left: 20px;
}

#demo-nesting-sub2 {
	margin-left: 40px;
}
</style>

<!-- Start WOWSlider.com HEAD section -->
<link rel="stylesheet" type="text/css" href="engine1/style.css" />
<script type="text/javascript" src="engine1/jquery.js"></script>
<!-- End WOWSlider.com HEAD section -->

</head>

<body>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<!-- Preloader -->
	<div id="preloader">
		<div id="load"></div>
	</div>

	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-main-collapse">
					<i class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand" href="homepage.php">
					<h1>HOME REHAB</h1>
				</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div
				class="collapse navbar-collapse navbar-right navbar-main-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="homepage.php">Home</a></li>
					<li><a href="regularReport.php">Regular Report</a></li>
					<li class="dropdown">
          				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Q & A <b class="caret"></b></a>
          				<ul class="dropdown-menu">
            				<li><a href="questions_all.php">All Questions</a></li>
							<li><a href="questions_unans.php">Unanswered Questions</a></li>
							<li><a href="questions_ans.php">Answered Questions</a></li>
          				</ul>
        			</li> 
        			<li class="dropdown">
          				<a href="#" class="dropdown-toggle" data-toggle="dropdown">Exercise <b class="caret"></b></a>
          				<ul class="dropdown-menu">
            				<li><a href="exercise_all.php">Exercise Details</a></li>
							<li><a href="exercise_before.php">Before Exercise Details</a></li>
							<li><a href="exercise_after.php">After Exercise Details</a></li>
							<li><a href="exercise_pat.php">Exercise Details of a patient</a></li>
          				</ul>
        			</li> 
        			<li><a href="logout.php">Logout</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

<!-- Start WOWSlider.com BODY section -->
<div class="marginn">
<div id="wowslider-container1">
<div class="ws_images"><ul>
		<li><img src="data1/images/1._technology_driven.jpg" alt="1. Technology Driven" title="1. Technology Driven" id="wows1_0"/></li>
		<li><img src="data1/images/2._synergy_between_medical__technology.jpg" alt="2. Synergy between Medical & Technology" title="2. Synergy between Medical & Technology" id="wows1_1"/></li>
		<li><a href="http://wowslider.com/vi"><img src="data1/images/3._patient_gets_the_best_healthcare.jpg" alt="html slider" title="3. Patient gets the best Healthcare" id="wows1_2"/></a></li>
		<li><img src="data1/images/4._home_feel_during_treatment.jpg" alt="4. Home feel during treatment" title="4. Home feel during treatment" id="wows1_3"/></li>
	</ul></div>
	<div class="ws_bullets"><div>
		<a href="#" title="1. Technology Driven"><span><img src="data1/tooltips/1._technology_driven.jpg" alt="1. Technology Driven"/>1</span></a>
		<a href="#" title="2. Synergy between Medical & Technology"><span><img src="data1/tooltips/2._synergy_between_medical__technology.jpg" alt="2. Synergy between Medical & Technology"/>2</span></a>
		<a href="#" title="3. Patient gets the best Healthcare"><span><img src="data1/tooltips/3._patient_gets_the_best_healthcare.jpg" alt="3. Patient gets the best Healthcare"/>3</span></a>
		<a href="#" title="4. Home feel during treatment"><span><img src="data1/tooltips/4._home_feel_during_treatment.jpg" alt="4. Home feel during treatment"/>4</span></a>
	</div></div><div class="ws_script" style="position:absolute;left:-99%"><a href="http://wowslider.com/vi">jquery gallery</a> by WOWSlider.com v7.8</div>
<div class="ws_shadow"></div>
</div>	
</div>
<script type="text/javascript" src="engine1/wowslider.js"></script>
<script type="text/javascript" src="engine1/script.js"></script>
<!-- End WOWSlider.com BODY section -->
<br><br><br>    
<div class="intro">
	
		<div class="slogan">
			<h2>WELCOME TO <span class="text_color">HOME REHAB</span> </h2>
			<h4>Opening home doors for better treatment</h4>
		</div>
		
    </div>

		
	<!-- Core JavaScript Files -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/jquery.scrollTo.js"></script>
	<script src="js/wow.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="js/custom.js"></script>


	<script type="text/javascript" src="css/prettify.js"></script>
	<script type="text/javascript" src="css/jquery.js"></script>
	<script type="text/javascript" src="css/simple-expand.js"></script>

	<script type="text/javascript">
        $(function () {
            $('.expander').simpleexpand();
            prettyPrint();
        });
    </script>

</body>
</html>