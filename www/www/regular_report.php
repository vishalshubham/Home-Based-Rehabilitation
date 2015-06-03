<?php

$databasehost = "localhost";
$databasename = "db_home_rehab";
$databaseusername ="root";
$databasepassword = "";
$response = array();
$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());
mysql_query("SET CHARACTER SET utf8");
$pid = "Jerry";
$bp = $_POST['bp'];
$med = $_POST['med'];
$cig = $_POST['cig'];
$weight = $_POST['weight'];
$date = $_POST['date'];
$sth = mysql_query("INSERT INTO `regular_report`(`pid`,`resting_BP`, `medication_use`, `cigarette_amount`, `body_weight`,`time`) VALUES ('$pid','$bp','$med','$cig','$weight','$date');");

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
