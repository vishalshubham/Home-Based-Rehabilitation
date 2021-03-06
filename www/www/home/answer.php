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
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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
					<li><a href="homepage.php">Home</a></li>
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

<?php
define ( 'DB_NAME', 'db_home_rehab' );
define ( 'DB_USER', 'root' );
define ( 'DB_PASSWORD', '' );
define ( 'DB_HOST', 'localhost' );

// $username = "root";
// $password = "";
// $hostname = "localhost";

// connection to the database
$db_link = mysql_connect ( DB_HOST, DB_USER, DB_PASSWORD );

if (! $db_link) {
	die ( "Unable to connect to MySQL : " . mysql_error () );
}
// echo "Connected to MySQL<br>";

// select a database to work with
$db_selected = mysql_select_db ( DB_NAME, $db_link );

if (! $db_selected) {
	die ( "Could not use : " . DB_NAME . " - " . mysql_error () );
}

// execute the SQL query and return records

$get_qid = $_GET['qid'];

$query = "SELECT qid, pid, description, time, ifans FROM questions where qid = '" . mysql_real_escape_string($get_qid) . "'";

$result = mysql_query ($query);

$row = mysql_fetch_array ( $result );
	
	$qid = $row {'qid'};
	$pid = $row {'pid'};
	$description = $row {'description'};
	$time = $row {'time'};
	$ifans = $row {'ifans'};	

	$did = $_SESSION['did'];
	
?>

<form action="saveAnswer.php" class="login" method="post">
    <h1>Question:</h1>
    <input type="hidden" name="qid" value="<?php echo $qid ?>" >
    <input type="hidden" name="pid" value="<?php echo $pid ?>" >
    <input type="hidden" name="did" value="<?php echo $did ?>" >
    
    <input type="text" name="question" class="login-input" value="<?php echo $description ?>" autofocus readonly>
    <h1>Answer:</h1>
    <input type="text" name="answer" class="login-input" placeholder="Type Your Answer Here" required>
    <input type="submit" value="Save Answer" class="login-submit">
</form>

<?php 
// close the connection
mysql_close ( $db_link );
?>
	
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