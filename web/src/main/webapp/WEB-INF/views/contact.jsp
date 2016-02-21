<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="Max Lysenko">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">

    <title>SkillsUp Graduation Project</title>

    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/resources/core/my-style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">


    <jsp:useBean id="contact" scope="request" type="socialnetwork.community.api.model.ContactDto"/>
    <jsp:useBean id="hobbies" scope="request" type="java.util.List"/>
    <jsp:useBean id="places" scope="request" type="java.util.List"/>

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-brand">SkillsUp Graduation Project </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#about" id="aboutModal">About</a>
                </li>
                <li>
                    <a href="#services" id="servicesModal">Services</a>
                </li>
                <li>
                    <a href="#contactInformation" id="contactInformationModal">Contact</a>
                </li>
                <li>
                    <a href="/logout">Logout</a>
                </li>
            </ul>
            <sec: authorize access="hasRole('ADMIN')">
                <div id="navbar" class="navbar-collapse collapse">
                    <a href="/admin" class="btn btn-info" role="button">Admin</a>
                </div>
            </sec:>
        </div>

    </div>

</nav>

<div class="container">
    <div class="page-header text-centred">

    </div>
    <div class="row">

        <div class="col-md-3">

            <div class="list-group">
                <a href="#" class="list-group-item active" id="profile-list"><span
                        class="glyphicon glyphicon-user"></span>Profile</a>
                <a href="#" class="list-group-item" id="friends-list"><span class="glyphicon glyphicon-globe"></span>Friends</a>
                <a href="#" class="list-group-item" id="hobbies-list"><span
                        class="glyphicon glyphicon-headphones"></span>Hobbies</a>
                <a href="#" class="list-group-item" id="messages-list"><span
                        class="glyphicon glyphicon-envelope"></span>Messages</a>
                <a href="#" class="list-group-item" id="places-list"><span class="glyphicon glyphicon-plane"></span>Places</a>
                <a href="#" class="list-group-item" id="news-list"><span
                        class="glyphicon glyphicon-send"></span>News</a>
            </div>
        </div>

        <div class="col-md-9">

            <div class="well">
                <div>
                    <div class="alert alert-info" role="alert" id="response-message">
                        Welcome to Community!
                    </div>
                </div>

                <div class="top-buffer tab-panel" id="profile-panel">
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td>First Name</td>
                            <td id="contactFirstName">${contact.firstName}</td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td id="contactLastName">${contact.lastName}</td>
                        </tr>
                        <tr>
                            <td>Birth Date</td>
                            <td id="contactBirthDate">${contact.birthDate}</td>
                        </tr>
                        <tr>
                            <td>Phone</td>
                            <td id="contactPhone">${contact.phone}</td>
                        </tr>
                        </tbody>
                    </table>
                    <button class="btn btn-default btn-success" data-toggle="modal" data-target="#addPostModal">
                        Add Post
                    </button>
                    <button class="btn btn-default btn-warning" data-toggle="modal" data-target="#editProfileModal">
                        Edit Profile
                    </button>
                    <p></p>
                    <table class="table table-hover" id="postsTable">
                        <tbody>
                        <tr id="postTableFirstRow"/>
                        </tbody>
                    </table>

                    <!-- Modal Add Post-->
                    <div class="modal fade" id="addPostModal" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <h4 class="modal-title">What are you thinking now?</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form">
                                        <div class="form-group">
                                            <label for="postContent">Post</label>
                                            <input type="text" class="form-control" id="postContent" var="content"
                                                   placeholder="Content">
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" id="addPost">Add</button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Modal Edit Profile-->
                    <div class="modal fade" id="editProfileModal" tabindex="-1" role="dialog">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <h4 class="modal-title" id="edit">Please, enter new data</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form">
                                        <div class="form-group">
                                            <label for="firstName">First Name</label>
                                            <input type="text" class="form-control" id="firstName" var="firstName"
                                                   placeholder="First Name">
                                        </div>
                                        <div class="form-group">
                                            <label for="lastName">Last Name</label>
                                            <input type="text" class="form-control" id="lastName" var="lastName"
                                                   placeholder="Last Name">
                                        </div>
                                        <div class="form-group">
                                            <label for="birthDate">Birth Date</label>
                                            <input type="date" class="form-control" id="birthDate" var="birthDate"
                                                   placeholder="Birth Date">
                                        </div>
                                        <div class="form-group">
                                            <label for="phone">Phone</label>
                                            <input type="text" class="form-control" id="phone" var="phone"
                                                   placeholder="Phone">
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" id="editProfile">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="top-buffer tab-panel hidden" id="friends-panel">
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <a class="navbar-brand">Find People</a>
                        </div>
                        <div>
                            <form class="navbar-form navbar-left" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchContactsForm" var="searchData"
                                           var="searchData" placeholder="Enter First or Last name">
                                </div>
                                <button type="button" class="btn btn-default" id="searchContacts">Search</button>
                                <button type="button" class="btn btn-default" id="clearSearchPeopleList">Clear</button>
                            </form>
                        </div>
                    </nav>
                    <table class="table table-hover" id="searchContactsTable">
                        <tbody>
                        <tr id="searchContactsTableFirstRow"/>
                        </tbody>
                    </table>
                    <p></p>

                    <h3 class="glyphicon  glyphicon-globe"> My Friends <h3/>
                        <table class="table table-hover" id="myFriends">
                            <tbody>
                            <tr id="myFriendsTableFirstRow"/>

                            </tbody>
                        </table>
                </div>
                <div class="top-buffer tab-panel hidden" id="hobbies-panel">
                    <button class="btn btn-default btn-success" data-toggle="modal" data-target="#addHobbyModal">
                        Add Hobby
                    </button>
                    <p></p>

                    <h3 id="myHobbies" class="glyphicon glyphicon-headphones"> My Hobbies <h3/>
                        <table class="table table-hover" id="hobbyTable">
                            <tbody>
                            <tr id="hobbyTableFirstRow"/>
                            <c:forEach items="${hobbies}" var="hobby">
                                <tr>
                                    <td>&rArr; <strong>${hobby.title}</strong> ${hobby.description}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                </div>

                <!-- Modal for adding Hobby-->
                <div class="modal fade" id="addHobbyModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">Please, specified title and description</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form">
                                    <div class="form-group">
                                        <label for="hobbyTitle">Title</label>
                                        <input type="text" class="form-control" id="hobbyTitle" var="title"
                                               placeholder="Title">
                                    </div>
                                    <div class="form-group">
                                        <label for="hobbyDescription">Description</label>
                                        <input type="text" class="form-control" id="hobbyDescription" var="description"
                                               placeholder="Description">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id="addHobby">Add</button>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="top-buffer tab-panel hidden" id="messages-panel">

                    <h3 class="glyphicon glyphicon-list"> My Conversation <h3/>
                        <nav class="navbar navbar-default" role="navigation">
                            <div class="navbar-header">
                                <a class="navbar-brand">Select messages &rArr;</a>
                            </div>
                            <div>
                                <ul class="nav navbar-nav">
                                    <li><a href="#" id="send">Send</a></li>
                                    <li><a href="#" id="received">Received</a></li>
                                </ul>
                            </div>
                        </nav>
                        <p></p>
                        <table class="table table-hover hidden" id="sendMessages">
                            <tbody>
                            <tr id="sendMessagesTableFirstRow"/>
                            <td>Contact</td>
                            <td>Message</td>
                            <td>Date</td>
                            </tr>
                            </tbody>
                        </table>

                        <table class="table table-hover hidden" id="receivedMessages">
                            <tbody>
                            <tr id="receivedMessagesTableFirstRow"/>
                            <td>Contact</td>
                            <td>Message</td>
                            <td>Date</td>
                            </tr>
                            </tbody>
                        </table>
                </div>

                <div class="top-buffer tab-panel hidden" id="places-panel">
                    <button class="btn btn-default btn-success" data-toggle="modal" data-target="#addPlaceModal">
                        Add Place
                    </button>
                    <p></p>

                    <h3 class="glyphicon glyphicon-plane"> My Places <h3/>
                        <table class="table table-hover" id="placesTable">
                            <tbody>
                            <tr id="placeTableFirstRow"/>
                            <c:forEach items="${places}" var="place">
                                <tr>
                                    <td>&rArr; ${place.title} (${place.description} , longitude: ${place.longitude},
                                        latitude: ${place.latitude} )
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                </div>

                <!-- Modal for adding Place-->
                <div class="modal fade" id="addPlaceModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">Please, specified title, description, longitude, latitude</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form">
                                    <div class="form-group">
                                        <label for="placeTitle">Title</label>
                                        <input type="text" class="form-control" id="placeTitle" var="title"
                                               placeholder="Title">
                                    </div>
                                    <div class="form-group">
                                        <label for="placeDescription">Description</label>
                                        <input type="text" class="form-control" id="placeDescription" var="description"
                                               placeholder="Description">
                                    </div>
                                    <div class="form-group">
                                        <label for="placeLongitude">Longitude</label>
                                        <input type="text" class="form-control" id="placeLongitude" var="longitude"
                                               placeholder="Longitude">
                                    </div>
                                    <div class="form-group">
                                        <label for="placeLatitude">Latitude</label>
                                        <input type="text" class="form-control" id="placeLatitude" var="latitude"
                                               placeholder="Latitude">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" id="addPlace">Add</button>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="top-buffer tab-panel hidden" id="news-panel">

                    <h3 class="glyphicon glyphicon-send"> News <h3/>
                        <button type="button" class="btn btn-primary" id="refreshNews">Refresh</button>
                        <table class="table table-hover" id="newsTable">
                            <tbody>
                            <tr id="newsTableFirstRow"/>
                            <td>Post</td>
                            <td>Friend</td>
                            <td>Date</td>
                            </tr>
                            </tbody>
                        </table>
                        <p></p>
                </div>

            </div>

        </div>

    </div>

    <!-- Modal for send Message-->
    <div class="modal fade" id="sendMessageModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Please, enter content of message</h4>
                </div>
                <div class="modal-body">
                    <div class="form">
                        <div class="form-group">
                            <label for="placeTitle">Content</label>
                            <input type="text" class="form-control" id="messsageContent" var="content"
                                   placeholder="Content">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="sendMessage">Send</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal remove friend dialog-->
    <div class="modal fade" id="removeFriendModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Remove Friend</h4>
                </div>
                <div class="modal-body">
                    <div class="form">
                        <div class="form-group">
                            <h3>Do you want to remove a friend?</h3>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                    <button type="button" class="btn btn-danger" id="removeFriend">Yes</button>
                </div>
            </div>
        </div>
    </div>

</div>


<div class="container">

    <hr>

    <footer>

    </footer>

</div>

<!-- Modal About-->
<div class="modal fade" id="about" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">About us</h4>
            </div>
            <div class="modal-body">
                SkillsUp Graduation Project, 2015
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Services-->
<div class="modal fade" id="services" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Services</h4>
            </div>
            <div class="modal-body">
                <ul>
                    <li>Find friends</li>
                    <li>Send messages</li>
                    <li>Add hobbies</li>
                    <li>Add places</li>
                    <li>News</li>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Contact Information-->
<div class="modal fade" id="contactInformation" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Contact Information</h4>
            </div>
            <div class="modal-body">
                <ul class="list-group">
                    <li class="list-group-item">Java Developer : Maxim Lysenko (SkillsUp)</li>
                    <li class="list-group-item">City : Dnipropetrovsk</li>
                    <li class="list-group-item">Email: lysenko.mk@gmail.com</li>
                    <li class="list-group-item">Phone: 380970790879</li>
                    <li class="list-group-item">Skype: maxim_lysenko</li>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/core/my-script.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

</body>

</html>

