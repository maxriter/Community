<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="description" content="SkillsUp Graduation Project">
    <meta name="author" content="Maxim Lysenko">

    <title>SkillsUp Graduation Project</title>

    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/resources/core/my-style.css">
    <link rel="stylesheet" href="/resources/core/jumbotron.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">


</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">SkillsUp Graduation Project</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <a href="/contact" class="btn btn-info btn-lg" role="button">Sign In</a>
        </div>
    </div>
</nav>


<div class="jumbotron">

    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-3">
            <div class="form">
                <div>
                    <div class="alert alert-info" role="alert" id="index-response-message">
                        Simple registration
                    </div>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="indexFirstName" var="firstName"
                           placeholder="First Name">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="indexLastName" var="lastName"
                           placeholder="Last Name">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="indexUsername" var="username"
                           placeholder="Username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="indexPassword" var="password"
                           placeholder="Password">
                </div>
                <button type="button" class="btn btn-success" id="register">Register</button>
            </div>
        </div>
        <div class="col-md-3">
        </div>
        <div class="col-md-3">
        </div>
    </div>

</div>

<div class="container">


    <hr>

</div>


<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/core/my-script.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

</body>
</html>
