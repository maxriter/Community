<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin page</title>

    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

</head>
<body>

<div class="container-fluid">
    <div class="page-header text-centred">
        <a href="<c:url value="/index.jsp"/> ">Go to Start Page</a>

        <div id="logout" class="navbar-collapse collapse">
            <a href="/logout" class="btn btn-info" role="button">Logout</a>
        </div>
        <div class="col-lg-offset-4 col-lg-4">
            <div class="alert alert-info " role="alert" id="response-message">
                Admin Panel
            </div>
        </div>

    </div>
    <div class="row">
        <div class="col-lg-offset-1 col-lg-10">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#" id="registration-tab">Contact Registration</a></li>
                <li role="presentation"><a href="#" id="contact-tab">Contact Service</a></li>
                <li role="presentation"><a href="#" id="hobby-tab">Hobby Service</a></li>
                <li role="presentation"><a href="#" id="place-tab">Places Service</a></li>
                <li role="presentation"><a href="#" id="message-tab">Message Service</a></li>
            </ul>
        </div>
    </div>

    <div class="top-buffer tab-panel" id="registration-panel">

        <div class="row top-buffer">
            <div class="row">
                <div class="col-md-3">
                </div>
                <div class="col-md-3">
                    <h2><span class="glyphicon glyphicon-user"></span>Simple registration form</h2>

                    <p></p>

                    <div class="form">
                        <div class="form-group">
                            <label for="first-name">First Name</label>
                            <input type="text" class="form-control" id="first-name" var="firstName"
                                   placeholder="First Name">
                        </div>
                        <div class="form-group">
                            <label for="last-name">Last Name</label>
                            <input type="text" class="form-control" id="last-name" var="lastName"
                                   placeholder="Last Name">
                        </div>
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" var="username"
                                   placeholder="Username">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="text" class="form-control" id="password" var="password"
                                   placeholder="Password">
                        </div>
                        <div class="form-group">
                            <label for="roles">Select role</label>
                            <select class="form-control" id="roles">
                                <c:forEach items="${roles}" var="role">
                                    <option>${role.type}</option>
                                </c:forEach>
                            </select>
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
    </div>

    <div class="top-buffer tab-panel hidden" id="contact-panel">
        <div class="row top-buffer">
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
                <button type="button" class="btn btn-primary" id="get-all-contacts">Get All Contacts</button>
                <table class="table table-hover" id="all-contacts-table">
                    <tbody>
                    <tr id="all-contacts-table-first-row">
                        <td>ID</td>
                        <td>Username</td>
                        <td>First Name</td>
                        <td>Last Name</td>
                        <td>Birth Date</td>
                        <td>Phone</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-4">
            </div>
        </div>
    </div>

    <div class="top-buffer tab-panel hidden" id="hobby-panel">
        <div class="row top-buffer">
            <div class="row top-buffer">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                    <button type="button" class="btn btn-primary" id="get-all-hobbies">Get All Hobbies</button>
                    <table class="table table-hover" id="all-hobbies-table">
                        <tbody>
                        <tr id="all-hobbies-table-first-row">
                            <td>ID</td>
                            <td>Title</td>
                            <td>Description</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-4">
                </div>
            </div>
        </div>
    </div>

    <div class="top-buffer tab-panel hidden" id="place-panel">
        <div class="row top-buffer">
            <div class="col-md-4">
            </div>
            <div class="col-md-4">
                <button type="button" class="btn btn-primary" id="get-all-places">Get All Places</button>
                <table class="table table-hover" id="all-places-table">
                    <tbody>
                    <tr id="all-places-table-first-row">
                        <td>ID</td>
                        <td>Title</td>
                        <td>Description</td>
                        <td>Latitude</td>
                        <td>Longitude</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-4">
            </div>
        </div>
    </div>

    <div class="top-buffer tab-panel hidden" id="message-panel">
        <div class="row top-buffer">
            <div class="col-md-4">
            </div>
            <div class="col-md-4">

            </div>
            <div class="col-md-4">
            </div>
        </div>
    </div>

</div>

</body>

<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/core/admin-script.js"></script>

</html>