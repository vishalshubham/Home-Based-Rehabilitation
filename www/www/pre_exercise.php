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
$dcm = $_POST['dcm'];
$diz = $_POST['diz'];
$lm = $_POST['lm'];
$up = $_POST['up'];
$co = $_POST['co'];
$bp = $_POST['bp'];
$med = $_POST['med'];
$ou = $_POST['ou'];
$time = $_POST['time'];
$sth = mysql_query("INSERT INTO `beforeexer`(`pid`,`discomfort`, `dizziness`, `largemeal`, `upset`,`cold`,`BP`,`take_medicines`,`other_uncomfortable`,`time`) VALUES ('$pid',$dcm,$diz,$lm,$up,$co,'$bp',$med,'$ou','$time');");

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

