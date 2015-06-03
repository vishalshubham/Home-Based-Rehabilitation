<?php

$databasehost = "localhost";
$databasename = "cs792";
$databaseusername ="root";
$databasepassword = "";
$response = array();
$con = mysql_connect($databasehost,$databaseusername,$databasepassword) or die(mysql_error());
mysql_select_db($databasename) or die(mysql_error());
mysql_query("SET CHARACTER SET utf8");
//$query = file_get_contents("php://input");
//$query = "SELECT * FROM prescription";
$query = $_POST['s'];
$sth = mysql_query($query);

if (mysql_errno()) {
    header("HTTP/1.1 500 Internal Server Error");
    echo $query.'\n';
    echo mysql_error();
}
else
{
	$response["success"] = 1;
    $response["message"] = "Post Available!";
    $response["posts"]   = array();
    
    while($r = mysql_fetch_assoc($sth)) {
        $post             = array();
        $post["did"] = $r["did"];
        $post["subject"] = $r["subject"];
        $post["Date"]  = $r["Date"];
		
		array_push($response["posts"], $post);
		
    }
	
    echo json_encode($response);
}
?>