<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
     <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {

            font-family: 'Lexend Deca', sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            overflow: hidden;
            margin: 0;
            /* border: 1px solid orangered; */

        }

        .form {
            line-height: 3rem;
            border: 1px solid orangered;
            border-radius: 0.9rem;
            padding: 3rem;
            display: flex;
            flex-direction: column;
        }

    </style>
</head>

<body>

	
    <div class="form">
        <h4>Login Form</h4>
        <span>${cookie.loginerr.value}</span>
        <form action="logincheck" method="post">
            <div class="input1">
                <label for="uid">UID </label>
                <input type="text"  name="uid" id="uid">
            </div>

            <div class="input1">
                <label for="pwd">Password </label>
                <input  type="text" name="password" id="pwd">
            </div>

            <div>
                <input type="submit" value="login">
                <input type="reset" value="cancel">
            </div>


        </form>
        
        <span>New user ?</span>
        <a href="SignupForm.html">Signup</a>
    </div>


</body>
<!-- //how to create form in html? -->

</html>