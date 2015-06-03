<?php

	define('DB_NAME', 'db_home_rehab');
	define('DB_USER', 'root');
	define('DB_PASSWORD', '');
	define('DB_HOST', 'localhost');
	
	//$username = "root";
	//$password = "";
	//$hostname = "localhost";
	
	//connection to the database
	$db_link = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD);
	
	if(!$db_link){
		die("Unable to connect to MySQL : " . mysql_error());
	}
	//echo "Connected to MySQL<br>";
	
	//select a database to work with
	$db_selected = mysql_select_db(DB_NAME,$db_link);
	
	if(!$db_selected){
		die("Could not use : " . DB_NAME . " - " . mysql_error());
	}
	//echo "Connected to " . DB_NAME . " database <br>";
	
	$did = $_POST['username'];
	$password = $_POST['password']; 
	
	//execute the SQL query and return records
	$result = mysql_query("SELECT did, password FROM tb_doc_login where did = '$did' and password = '$password'");
	
	//fetch tha data from the database
	$row = mysql_fetch_array($result);
	if(!$row){
		header('Location: /home/loginError.html');
	}
	else {
	//	echo "DID: " . $did . " & Pass: " . $password;
		session_start();
		$_SESSION['did'] = $did;
		header('Location: /home/homepage.php');
	}
	//close the connection
	mysql_close($db_link);
?>