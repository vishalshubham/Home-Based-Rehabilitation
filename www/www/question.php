<?php

$databasehost = "localhost";
$databasename = "db_home_rehab";
$databaseusername ="root";
$databasepassword = "";
$response = array();
$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());
mysql_query("SET CHARACTER SET utf8");
//$query = file_get_contents("php://input");
//$query = "SELECT * FROM prescription";
$pid = "John Smith";
$qid = $_POST['q'];
$sub = $_POST['sub'];
$des = $_POST['note'];
$date = $_POST['date'];
//$time = $_POST['time'];
$sth = mysql_query("INSERT INTO `questions`(`qid`,`pid`, `sub`, `description`, `time`) VALUES ('$qid','$pid','$sub','$des','$date');");

if (mysql_errno()) {
    header("HTTP/1.1 500 Internal Server Error");
    echo $query.'\n';
    echo mysql_error();
}
else
{
	$response["success"] = 1;
    $response["message"] = "Inserted Successfully!";
    
	
    echo json_encode($response);
}
?>