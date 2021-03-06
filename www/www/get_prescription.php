<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all products from products table
$result = mysql_query("SELECT * FROM prescription") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["prescription"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["pid"] = $row["pid"];
        $product["did"] = $row["did"];
        $product["frequency"] = $row["frequency"];
        $product["targetHR"] = $row["targetHR"];
        $product["RPE"] = $row["RPE"];
 		$product["MET"] = $row["MET"];
        $product["duration"] = $row["duration"];
        $product["Calorie"] = $row["Calorie"];
		$product["Date"] = $row["Date"];


        // push single product into final response array
        array_push($response["prescription"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>
And the JSON response for above code
Listing all Products
{ 
"products": [ 
{ 
"pid": "1", 
"name": "iPhone 4S", 
"price": "300.00", 
"created_at": "2012-04-29 02:04:02", 
"updated_at": "0000-00-00 00:00:00"
}, 
{ 
"pid": "2", 
"name": "Macbook Pro", 
"price": "600.00", 
"created_at": "2012-04-29 02:04:51", 
"updated_at": "0000-00-00 00:00:00"
}, 
{ 
"pid": "3", 
"name": "Macbook Air", 
"price": "800.00", 
"created_at": "2012-04-29 02:05:57", 
"updated_at": "0000-00-00 00:00:00"
}, 
{ 
"pid": "4", 
"name": "OS X Lion", 
"price": "100.00", 
"created_at": "2012-04-29 02:07:14", 
"updated_at": "0000-00-00 00:00:00"
} 
], 
"success": 1
}
When products not found
{ 
"success": 0, 
"message": "No products found"
}