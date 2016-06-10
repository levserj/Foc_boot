<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>FOC- Makes someone happy</title>

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/leftAndRight.css"/>

    <script src="/resources/jquery/jquery-2.2.2.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/js/myPage.js"></script>
</head>
<body>
<nav class="navbar navbar-default" style="background-color: #222F3C">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/foc" style="padding-left: 3em"><img
                    src="<c:url value="/resources/images/logo.png"/>" width="400" height="45px"/></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="min-height: 80px">

            <form class="navbar-form navbar-left" role="search" style="padding-left: 10em; padding-top: 0.7em">
                <div class="form-group">
                    <input class="form-control" placeholder="Search" type="text">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <ul class="nav navbar-nav navbar-right" style="font-size: 26px; padding-top: 0.4em">
                <li><a href="/signUp">Sing up</a></li>
                <li><a href="/login">Sing in</a></li>
                <li><a href="/logout">Sign Out</a></li>
                <li><a href="/myPage">MyPage</a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="featured-section" style="text-align: center">
    <h2>MAKE SOMEONE HAPPY</h2>
    <input id="userId" type="hidden" value="${userId}">
    <input id="itemToEditId" type="hidden" value="">
    <h3 align="center">${requestScope.message}</h3>
</div>
<div class="left">
    <form id="itemForm" class="form-horizontal" style="padding-top: 5em">
        <fieldset>
            <legend style="padding-left: 7em">Add/edit giveaway item</legend>
            <div class="form-group">
                <br>
                <label for="title" class="col-lg-2 control-label">Title</label>
                <div class="col-lg-10">
                        <textarea class="form-control" rows="3" id="title"
                                  style="width: 100%; max-width: 100%"></textarea>
                    <span class="help-block"></span>
                </div>

                <label for="description" class="col-lg-2 control-label">Description</label>
                <div class="col-lg-10">
                        <textarea class="form-control" rows="3" id="description"
                                  style="width: 100%; max-width: 100%"></textarea>
                    <span class="help-block"></span>
                </div>
                <label class="col-lg-2 control-label"></label>
                <div class="col-lg-10">
                    <button id="addItem" type="submit" class="btn btn-primary">Save</button>
                </div>
                <br><br>
            </div>
        </fieldset>
    </form>
</div>

<div class="right" style="padding-top: 5em; padding-right: 2em">
    <div>
        <legend style="float: left">My giveaway items : <span id="activeTemplate"></span></legend>
    </div>

    <table style="table-layout: fixed; word-wrap: break-word;" class="table table-striped table-hover"
           id="templateQuestions">
        <thead>
        <tr>
            <th class="col-md-3">TITLE</th>
            <th class="col-md-8">DESCRIPTION</th>
            <th class="col-md-1"></th>
            <th class="col-md-1"></th>
        </tr>
        </thead>
        <tbody id="myItems">
        <!-- Populated by JS -->
        </tbody>
    </table>
</div>
</body>
</html>