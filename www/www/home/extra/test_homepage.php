<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Doctor Login Form</title>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" type="text/css" media="screen" href="css/expandStylesheet.css">
  <link href="css/prettify.css" type="text/css" rel="stylesheet">
	
  <style>
  body {
        background-image: url("/home/images/bg1.jpg");
  } 
  
  .content {display:none;}
            
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
                font-weight:bold;
                font-size:125%;
            }
                
            #demo3.expanded .content {
                display:inherit;
            }
        
            #demo3.collapsed .content {
                display:none;
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
	echo "Connected to MySQL<br>";
	
	//select a database to work with
	$db_selected = mysql_select_db(DB_NAME,$db_link);
	
	if(!$db_selected){
		die("Could not use : " . DB_NAME . " - " . mysql_error());
	}
	echo "Connected to " . DB_NAME . " databases <br>";
	
	//execute the SQL query and return records
	$result = mysql_query("SELECT qid, pid, description, time FROM questions");
	//$result = mysql_query("SELECT did, password FROM tb_doc_login");
	
	while ($row = mysql_fetch_array($result)) {
		$description = $row{'description'};
		echo "Question no.:".$row{'qid'}. "Patient id:".$row{'pid'}. "Description:".$row{'description'}. "Time:".$row{'time'}. "<br>";
	}
	
	//close the connection
	mysql_close($db_link);
?>

<div id="demos_wrap" class="outer">
    <section id="demos" class="inner">

            <h1>
            Demos
            </h1>

            <!-- ********************************************
            demo-simplest
            ******************************************** -->
            <h6 id="demo-simplest-top">1. This is the simplest version</h6>
            <div class="demo-frame">
                <div id="demo-simplest">
                    <a class="expander collapsed" href="#">click me</a>
                    <div style="display: none;" class="content">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
 					</div>
                </div>
            </div>
            
        
          
        </section>
    </div>

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