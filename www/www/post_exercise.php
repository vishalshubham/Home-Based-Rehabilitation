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
//$query = "SELECT * FpROM prescription";
$pid = "Jerry";
$dcm = $_POST['dcm'];
$diz = $_POST['diz'];
$rpe = $_POST['rpe'];
$ou = $_POST['ou'];
$time = $_POST['date'];
$sth = mysql_query("INSERT INTO `after_exer`(`pid`,`discomfort`, `dizziness`,`RPE`,`other_uncomfortable`,`time`) VALUES ('$pid','$dcm','$diz','$rpe','$ou','$time');");

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
