<?php

$response = array();
 
$con = mysql_connect('localhost', 'root', '') or die(mysql_error());
$db = mysql_select_db('cs792') or die(mysql_error()) or die(mysql_error());

 
$result = mysql_query("SELECT * FROM answer") or die(mysql_error());
 
if (mysql_num_rows($result) > 0) {
    
    $response["answer"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        
        $product = array();
        $product["pid"] = $row["pid"];
        $product["qid"] = $row["qid"];
        $product["did"] = $row["did"];
        $product["content"] = $row["content"];
        $product["time"] = $row["time"];
 
        
        array_push($response["answer"], $product);
    }
    
    $response["success"] = 1;
 
    
    echo json_encode($response);
} else {
   
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    
    echo json_encode($response);
}
?>