$(document).ready(function () {

    function clearNavSelection() {
        $('.list-group-item').removeClass("active");
        $('.tab-panel').addClass("hidden");
    }

    function clearResponseMessage() {
        $("#response-message").removeClass("alert-success").removeClass("alert-info").removeClass("alert-warning").removeClass("alert-danger");
    }

    function clearIndexResponseMessage() {
        $("#index-response-message").removeClass("alert-success").removeClass("alert-info").removeClass("alert-warning").removeClass("alert-danger");
    }

    $("#profile-list").click(function () {
        clearNavSelection();
        $("#profile-panel").removeClass("hidden");
        $("#profile-list").addClass("active");
        displayResponseInfoMessage("What's in your mind? :)");
    });

    $("#friends-list").click(function () {
        clearNavSelection();
        $("#friends-panel").removeClass("hidden");
        $("#friends-list").addClass("active");
        displayResponseInfoMessage("Use search to find your friends");
        $('#searchContactsTable tr:not(:first)').remove();
        getFriends();
    });

    $("#hobbies-list").click(function () {
        clearNavSelection();
        $("#hobbies-panel").removeClass("hidden");
        $("#hobbies-list").addClass("active");
        displayResponseInfoMessage("Click Add Hobby and fill out the form");
    });

    $("#messages-list").click(function () {
        clearNavSelection();
        $("#messages-panel").removeClass("hidden");
        $("#messages-list").addClass("active");
        displayResponseInfoMessage("Click Send or Received to see your conversation");
    });

    $("#received").click(function () {
        $("#sendMessages").addClass("hidden");
        $("#receivedMessages").removeClass("hidden");
        $("#send").addClass("active");
        $("#received").removeClass("active");
        getReceivedMessages();
    });

    $("#send").click(function () {
        $("#receivedMessages").addClass("hidden");
        $("#sendMessages").removeClass("hidden");
        $("#received").addClass("active");
        $("#send").removeClass("active");
        getSendMessages();
    });

    $("#places-list").click(function () {
        clearNavSelection();
        $("#places-panel").removeClass("hidden");
        $("#places-list").addClass("active");
        displayResponseInfoMessage("Click Add Place and fill out the form");
    });

    $("#news-list").click(function () {
        clearNavSelection();
        $("#news-panel").removeClass("hidden");
        $("#news-list").addClass("active");
        displayResponseInfoMessage("News feed.To see the latest news click Refresh");
        getNews();
    });

    $("#aboutModal").click(function () {
        $("#about").modal("show");
    })

    $("#servicesModal").click(function () {
        $("#services").modal("show");
    })

    $("#contactInformationModal").click(function () {
        $("#contactInformation").modal("show");
    })

    $(function () {
        $("#birthDate").datepicker({
            dateFormat: "yy-mm-dd"
        }).val()
    });

    function updateContactProfile(contactDto) {
        var date = contactDto.birthDate[0] + "-" + contactDto.birthDate[1] + "-" + contactDto.birthDate[2];
        $("#contactFirstName").text(contactDto.firstName);
        $("#contactLastName").text(contactDto.lastName);
        $("#contactBirthDate").text(date);
        $("#contactPhone").text(contactDto.phone);
        displayResponseSuccessMessage("Contact information updated");
    };

    $("#editProfile").click(function () {
        var firstName = $('#firstName').val();
        var lastName = $('#lastName').val();
        var birthDate = $('#birthDate').val();
        var phone = $('#phone').val();
        var json = '{"firstName": "' + firstName + '", "lastName": "' + lastName + '", "birthDate": "' + birthDate + '","phone": "' + phone + '"}';
        $.ajax({
            type: "POST",
            url: '/editProfile',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                if ((data.firstName == null) && (data.lastName == null) && (data.birthDate == null) && (data.phone == null)) {
                    displayResponseErrorMessage("Fill at least one field!");
                } else {
                    updateContactProfile(data)
                }

            }
        });
        $('#editProfileModal').modal('toggle');
    });

    $("#addHobby").click(function () {
        var title = $('#hobbyTitle').val();
        var description = $('#hobbyDescription').val();
        var json = '{"title": "' + title + '", "description": "' + description + '"}';
        $.ajax({
            type: "POST",
            url: '/addHobby',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                if ((data.id == null) && (data.title != "")) {
                    displayResponseWarningMessage("You already has this hobby");
                } else if (data.id != null) {
                    $("<tr><td>" + "&rArr; " + "<strong>" + data.title + "</strong>" + "  " + data.description + "</td></strong>").insertAfter($("#hobbyTableFirstRow"));
                    displayResponseSuccessMessage("Hobby successfully added");
                } else {
                    displayResponseErrorMessage("Please, fill the title of Hobby");
                }
            }
        });
        $('#addHobbyModal').modal('toggle');
    });

    $("#addPlace").click(function () {
        var title = $('#placeTitle').val();
        var description = $('#placeDescription').val();
        var longitude = $('#placeLongitude').val();
        var latitude = $('#placeLatitude').val();
        var json = '{"title": "' + title + '", "description": "' + description + '","longitude": "' + longitude + '", "latitude": "' + latitude + '"}';
        $.ajax({
            type: "POST",
            url: '/addPlace',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                var description;
                var longitude;
                var latitude;

                if (data.description == "") {
                    description = ""
                } else (description = data.description);
                if (data.longitude == "") {
                    longitude = ""
                } else (longitude = "   longitude: " + data.longitude);
                if (data.latitude == "") {
                    latitude = ""
                } else (latitude = "   latitude: " + data.latitude);

                if ((data.id == null) && (data.title != "")) {
                    displayResponseWarningMessage("You already has this Place");
                } else if (data.id != null) {
                    $("<tr><td>" + "&rArr; " + "<strong>" + data.title + "</strong>" + " " + description + longitude + latitude + "</td></tr>").insertAfter($("#placeTableFirstRow"));
                    displayResponseSuccessMessage("Place successfully added");
                } else {
                    displayResponseErrorMessage("Please, fill the title of Place");
                }

            },
            error: function () {
                displayResponseErrorMessage("Coordinates isn't correct!");
            }
        });

        $('#addPlaceModal').modal('toggle');
    });

    $("#addPost").click(function () {
        var content = $('#postContent').val();
        var json = '{"content": "' + content + '"}';
        $.ajax({
            type: "POST",
            url: '/addPost',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                if (data.content == "") {
                    displayResponseErrorMessage("Not fill content of the Post! Please, do it and try again");
                } else {
                    var date = data.post_date[0] + "-" + data.post_date[1] + "-" + data.post_date[2]
                        + "  " + data.post_date[3] + ":" + data.post_date[4] + ":" + data.post_date[5];
                    $("<tr><td>" + date + "&rArr; " + data.content + "</td></tr>").insertAfter($("#postTableFirstRow"));
                    displayResponseSuccessMessage("Post successfully added");
                }
            }
        });
        $('#addPostModal').modal('toggle');
    });

    $("#register").click(function () {
        var firstName = $('#indexFirstName').val();
        var lastName = $('#indexLastName').val();
        var username = $('#indexUsername').val();
        var password = $('#indexPassword').val();
        var json = '{"firstName": "' + firstName + '", "lastName": "' + lastName + '", "username": "' + username + '","password": "' + password + '"}';
        $.ajax({
            type: "POST",
            url: '/register',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                if (data.id == 0) {
                    displayIndexErrorMessage("Username already exists!");
                } else {
                    var errorData = "";
                    var isDataCorrect = true;
                    if (data.firstName == "") {
                        errorData += " First Name ";
                        isDataCorrect = false
                    }

                    if (data.lastName == "") {
                        errorData += " Last Name ";
                        isDataCorrect = false
                    }

                    if (data.username == "") {
                        errorData += " Username ";
                        isDataCorrect = false
                    }

                    if (data.password == "") {
                        errorData += " Password ";
                        isDataCorrect = false
                    }


                    if (isDataCorrect == true) {
                        displayIndexSuccessMessage("Now you can Sign In using your Username and Password ;)");
                    } else {
                        displayIndexErrorMessage("Fill the fields: " + errorData);
                    }
                }

            }
        });
    });

    $("#searchContacts").click(function () {
        var searchData = $('#searchContactsForm').val();
        $.ajax({
            type: "POST",
            url: '/searchContacts',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: searchData,
            success: function (data) {
                $('#searchContactsTable tr:not(:first)').remove();
                $.each(data, function (index, value) {
                    var birthDate;
                    (value.birthDate == null) ? birthDate = "" : birthDate = value.birthDate[0] + "-" + value.birthDate[1] + "-" + value.birthDate[2];

                    var button = $("<button class='btn btn-success'>Add Friend</button>");
                    button.click(function () {
                        addFriend(this.id);
                    });
                    button.appendTo($("<tr><td>" + "&rArr; " + value.firstName + "   " + value.lastName + "   " + birthDate + "</td></tr>").insertAfter($("#searchContactsTableFirstRow"))).attr("id", value.id);
                });
                if (data.length == 0) {
                    displayResponseWarningMessage("Nothing found");
                }
            }
        });
    });

    $("#clearSearchPeopleList").click(function () {
        $('#searchContactsTable tr:not(:first)').remove();
        displayResponseWarningMessage("Search results cleared");
    });

    function addFriend(id) {
        $.ajax({
                type: "POST",
                url: '/addFriend',
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: id,
                success: function (data) {
                    if (data.id != null) {
                        var button = $("<button class='btn btn-primary btn-send-message'><span class='glyphicon glyphicon-envelope'></span>Message</button>" +
                            "<button class='btn btn-primary btn-remove-friend'><span class='glyphicon glyphicon-remove'></span>Remove</button>");
                        button.appendTo($("<tr id=" + data.id + "><td>" + "&rArr; " + data.firstName + "   "
                            + data.lastName + "</td></tr>").insertAfter($("#myFriendsTableFirstRow")));
                        displayResponseSuccessMessage("Now you're friends with " + data.firstName + " " + data.lastName);
                    } else {
                        displayResponseErrorMessage("You are already friends! Did you forget maybe? :) ");
                    }
                }
            }
        );
    }

    $(document).on('click', '.btn-send-message', function () {
        id = $(this).closest('tr').attr('id');
        $("#sendMessageModal").val(id).modal("show");
    });

    $(document).on('click', '.btn-remove-friend', function () {
        id = $(this).closest('tr').attr('id');
        $("#removeFriendModal").val(id).modal("show");
    });

    $("#sendMessage").click(function () {
        var id = $('#sendMessageModal').val();
        var content = $('#messsageContent').val();
        var json = '{"id": "' + id + '", "content": "' + content + '"}';
        var find;
        $.ajax({
            type: "POST",
            url: '/message',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                if (data.content == "") {
                    displayResponseErrorMessage("Not fill content of the message!");
                } else {
                    displayResponseSuccessMessage("Message sent!");
                    getSendMessages();
                }

            }
        });
        $('#sendMessageModal').modal('toggle');
    });

    $("#removeFriend").click(function () {
        var id = $('#removeFriendModal').val();
        $.ajax({
                type: "POST",
                url: '/removeFriend',
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: id,
                success: function (data) {
                    getFriends();
                    displayResponseSuccessMessage(data.firstName + " " + data.lastName + " removed from your friends");
                }
            }
        );
        $('#removeFriendModal').modal('toggle');
    });

    function getFriends() {
        $.get("/friends", function (data) {
            $('#myFriends tr:not(:first)').remove();
            $.each(data, function (index, value) {
                var button = $("<button class='btn btn-primary btn-send-message'><span class='glyphicon glyphicon-envelope'></span>Message</button>" +
                    "<button class='btn btn-primary btn-remove-friend'><span class='glyphicon glyphicon-remove'></span>Remove</button>");
                button.appendTo($("<tr id=" + value.id + "><td>" + "&rArr; " + value.firstName + "   "
                    + value.lastName + "</td></tr>").insertAfter($("#myFriendsTableFirstRow")));
            });
        });
    }

    function getSendMessages() {
        $.get("/sendMessages", function (data) {
            $('#sendMessages tr:not(:first)').remove();
            $.each(data, function (index, value) {
                var date = value.message_date[0] + "-" + value.message_date[1] + "-" + value.message_date[2]
                    + "  " + value.message_date[3] + ":" + value.message_date[4] + ":" + value.message_date[5];

                var button = $("<button class='btn btn-primary'>Message</button>");
                button.click(function () {
                    $("#sendMessageModal").val(this.id).modal("show");
                });
                button.appendTo($("<tr><td>" + value.recipient +
                    "</td><td>" + value.content + "</td><td>" + date + "</td></tr>").insertAfter($("#sendMessagesTableFirstRow"))).attr("id", value.contact_to);

            });
            if (data.length == 0) {
                $("<tr><td>-</td><td>-</td><td>-</td>   </tr>").insertAfter($("#sendMessagesTableFirstRow"));
            }
        });
    }

    function getReceivedMessages() {
        $.get("/receivedMessages", function (data) {
            $('#receivedMessages tr:not(:first)').remove();
            $.each(data, function (index, value) {
                var date = value.message_date[0] + "-" + value.message_date[1] + "-" + value.message_date[2]
                    + "  " + value.message_date[3] + ":" + value.message_date[4] + ":" + value.message_date[5];

                var button = $("<button class='btn btn-primary'>Message</button>");
                button.click(function () {
                    $("#sendMessageModal").val(this.id);
                    $("#sendMessageModal").modal("show");
                });
                button.appendTo($("<tr><td>" + value.sender +
                    "</td><td>" + value.content + "</td><td>" + date + "</td></tr>").insertAfter($("#receivedMessagesTableFirstRow"))).attr("id", value.contact_from);
            });
            if (data.length == 0) {
                $("<tr><td>-</td><td>-</td><td>-</td>   </tr>").insertAfter($("#receivedMessagesTableFirstRow"));
            }
        });
    }

    function getNews() {
        $.get("/getNews", function (data) {
            $('#newsTable tr:not(:first)').remove();
            $.each(data, function (index, value) {
                var date = value.post_date[0] + "-" + value.post_date[1] + "-" + value.post_date[2]
                    + "  " + value.post_date[3] + ":" + value.post_date[4] + ":" + value.post_date[5];
                ($("<tr><td>" + value.content +
                    "</td><td>" + value.contactDto.firstName + "  " + value.contactDto.lastName + "</td><td>" + date + "</td></tr>").insertAfter($("#newsTableFirstRow")));
            });
            if (data.length == 0) {
                $("<tr><td>-</td><td>-</td><td>-</td>   </tr>").insertAfter($("#newsTableFirstRow"));
            }
        });
    }


    $("#refreshNews").click(function () {
        getNews();
    })


    function getPosts() {
        $.get("/posts", function (data) {
            $('#postsTable tr:not(:first)').remove();
            $.each(data, function (index, value) {
                var date = value.post_date[0] + "-" + value.post_date[1] + "-" + value.post_date[2]
                    + "  " + value.post_date[3] + ":" + value.post_date[4] + ":" + value.post_date[5];
                $("<tr><td>" + date + "&rArr; " + value.content + "</td></tr>").insertAfter($("#postTableFirstRow"));
            });
        });
    }

    function displayIndexErrorMessage(data) {
        clearIndexResponseMessage();
        $('#index-response-message').addClass("alert-danger").text(data);
    }

    function displayIndexSuccessMessage(data) {
        clearIndexResponseMessage();
        $('#index-response-message').addClass("alert-success").text(data);

    }

    function displayResponseErrorMessage(data) {
        clearResponseMessage();
        $('#response-message').addClass("alert-danger").text(data);
    }

    function displayResponseSuccessMessage(data) {
        clearResponseMessage();
        $('#response-message').addClass("alert-success").text(data);
    }

    function displayResponseWarningMessage(data) {
        clearResponseMessage();
        $('#response-message').addClass("alert-warning").text(data);
    }

    function displayResponseInfoMessage(data) {
        clearResponseMessage();
        $('#response-message').addClass("alert-info").text(data);
    }

    $(window).load(getPosts())

})

