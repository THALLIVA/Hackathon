<?php

$response = array();

if (isset($_POST['mobile_number']) && isset($_POST['password']) && isset($_POST['hint']) && isset($_POST['email_id'])) 
{  
    $mobile_number = $_POST['mobile_number'];
    $password = $_POST['password'];
    $hint = $_POST['hint'];
    $email_id = $_POST['email_id'];  
	
    require_once __DIR__ . '/db_connect.php';
    $db = new DB_CONNECT();
    $result = mysql_query("Insert into login(mobile_number,password,hint,email_id) values('$mobile_number','$password','$hint','$email_id')");

    if ($result)
	{
        $response["success"] = 1;
        $response["message"] = "Registration successfully.";
        echo json_encode($response);
    }
	else
	{
      $response["success"] = 0;
      $response["message"] = "Oops! An error occurred.";
      echo json_encode($response);  
    }
 }
 
 else 
 {
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
    echo json_encode($response);
 } 
?>