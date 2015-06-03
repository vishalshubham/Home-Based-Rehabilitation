<?php
	session_start();
	$_SESSION['did'] = "";
	header('Location: /home/login.html');
?>