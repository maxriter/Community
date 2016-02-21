$(document).ready(function () {

    function clearNavSelection() {
        $('.nav-tabs li').removeClass("active");
        $('.tab-panel').addClass("hidden");
    };

    $("#registration-tab").click(function () {
        clearNavSelection();
        $("#registration-panel").removeClass("hidden");
        $("#registration-tab").parent('li').addClass("active");
    });

    $("#contact-tab").click(function () {
        clearNavSelection();
        $("#contact-panel").removeClass("hidden");
        $("#contact-tab").parent('li').addClass("active");
    });

    $("#hobby-tab").click(function () {
        clearNavSelection();
        $("#hobby-panel").removeClass("hidden");
        $("#hobby-tab").parent('li').addClass("active");
    });

    $("#place-tab").click(function () {
        clearNavSelection();
        $("#place-panel").removeClass("hidden");
        $("#place-tab").parent('li').addClass("active");
    });

    $("#register").click(function () {
        var firstName = $('#first-name').val();
        var lastName = $('#last-name').val();
        var username = $('#username').val();
        var password = $('#password').val();
        var roles = $('#roles').val();
        var json = '{"firstName": "' + firstName + '", "lastName": "' + lastName + '", "username": "'
            + username + '","password": "' + password + '","roles": "' + roles + '"}';
        $.ajax({
            type: "POST",
            url: '/registrationContact',
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: json,
            success: function (data) {
                if (data.id == 0) {
                    displayResponseErrorMessage("Username already exists!");
                } else {
                    var errorData = "";
                    var isDataCorrect = true;
                    if (data.firstName == "") {
                        errorData += " First Name ";
                        isDataCorrect = false
                    }
                    ;
                    if (data.lastName == "") {
                        errorData += " Last Name ";
                        isDataCorrect = false
                    }
                    ;
                    if (data.username == "") {
                        errorData += " Username ";
                        isDataCorrect = false
                    }
                    ;
                    if (data.password == "") {
                        errorData += " Password ";
                        isDataCorrect = false
                    }
                    ;

                    if (isDataCorrect == true) {
                        displayResponseSuccessMessage("Registration success!");
                    } else {
                        displayResponseErrorMessage("Fill the fields: " + errorData);
                    }
                }
            }
        });
    })

    $("#get-all-contacts").click(function () {
        getAllContacts();
    })

    function getAllContacts() {
        $.get("/allContacts", function (data) {
                $('#all-contacts-table tr:not(:first)').remove();
                $.each(data, function (index, data) {
                    $("<tr>" +
                        "<td>" + data.id + "</td>" +
                        "<td>" + data.username + "</td>" +
                        "<td>" + data.firstName + "</td>" +
                        "<td>" + data.lastName + "</td>" +
                        "<td>" + data.birthDate + "</td>" +
                        "<td>" + data.phone + "</td>" +
                        "</tr>").insertAfter($("#all-contacts-table-first-row"));
                });
                if (data.length == 0) {
                    displayResponseWarningMessage("Contact table is empty");
                } else {
                    displayResponseSuccessMessage("Contact list updated")
                }
            }
        );
    }

    $("#get-all-hobbies").click(function () {
        getAllHobbies();
    })

    function getAllHobbies() {
        $.get("/allHobbies", function (data) {
                $('#all-hobbies-table tr:not(:first)').remove();
                $.each(data, function (index, data) {
                    $("<tr>" +
                        "<td>" + data.id + "</td>" +
                        "<td>" + data.title + "</td>" +
                        "<td>" + data.description + "</td>" +
                        "</tr>").insertAfter($("#all-hobbies-table-first-row"));
                });
                if (data.length == 0) {
                    displayResponseWarningMessage("Hobbies table is empty");
                } else {
                    displayResponseSuccessMessage("Hobbies list updated")
                }
            }
        );
    }

    $("#get-all-places").click(function () {
        getAllPlaces();
    })

    function getAllPlaces() {
        $.get("/allPlaces", function (data) {
                $('#all-places-table tr:not(:first)').remove();
                $.each(data, function (index, data) {
                    $("<tr>" +
                        "<td>" + data.id + "</td>" +
                        "<td>" + data.title + "</td>" +
                        "<td>" + data.description + "</td>" +
                        "<td>" + data.latitude + "</td>" +
                        "<td>" + data.longitude + "</td>" +
                        "</tr>").insertAfter($("#all-places-table-first-row"));
                });
                if (data.length == 0) {
                    displayResponseWarningMessage("Places table is empty");
                } else {
                    displayResponseSuccessMessage("Places list updated")
                }
            }
        );
    }

    function clearResponseMessage() {
        $("#response-message").removeClass("alert-success").removeClass("alert-info").removeClass("alert-warning").removeClass("alert-danger");
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

});