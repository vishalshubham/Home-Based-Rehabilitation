<?php

	session_start();
	if ($_SESSION['did'] == ""){
		header('Location: /home/sessionError.html');
	}

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
	
	session_start();
	$did = $_SESSION['did'];
	$qid = $_POST['qid'];
	$pid = $_POST['pid'];
	$question = $_POST['question'];
	$answer = $_POST['answer'];
	//echo $did . $pid . $qid . $question . $answer;
	
	//"INSERT INTO answers(pid, qid, did, content, time) VALUES ('$pid', '$qid', '$did', '$question', '')"
	
	
	//execute the SQL query and return records
	$result = mysql_query("INSERT INTO answers(pid, qid, did, content) VALUES ('$pid', '$qid', '$did', '$answer')");
	
	//fetch tha data from the database
 	//$row = mysql_fetch_array($result);
 	if(!$result){
 		header('Location: /home/homeError.html');
 	}
 	else {
 		$updateresult = mysql_query("update questions set ifans = 'Yes' where qid = '$qid'");
 		header('Location: /home/answerSuccess.html');
 	}
 	//close the connection
	mysql_close($db_link);
?>