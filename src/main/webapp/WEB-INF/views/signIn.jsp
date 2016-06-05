<%--
  Created by IntelliJ IDEA.
  User: SD
  Date: 04.02.2016
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>FOC- Makes someone happy</title>

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/input.css"/>
    <link rel="stylesheet" href="/resources/css/template.css"/>

    <script src="/resources/jquery/jquery-2.2.2.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="/resources/jquery/jquery-ui/jquery-ui.js"></script>

    <script src="/resources/js/signUp.js"></script>
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
<div style="margin: auto; width: 50%;">

    <form class="form-horizontal">
        <h3 id="message" style="padding-top: 2em"></h3>
        <fieldset>
            <legend style="text-align: center;">Create new user</legend>
            <div class="form-group">
                <label for="email" class="col-lg-2 control-label">Email</label>

                <div class="col-lg-10">
                    <input path="email" class="form-control" id="email" placeholder="Email" type="text"/>
                    <%--<form:errors path="email"/>--%>
                </div>
            </div>
            <div class="form-group">
                <label for="firstName" class="col-lg-2 control-label">First Name</label>

                <div class="col-lg-10">
                    <input path="firstName" class="form-control" id="firstName" placeholder="First Name" type="text"/>
                    <%--<form:errors path="firstName"/>--%>
                </div>
            </div>
            <div class="form-group">
                <label for="lastName" class="col-lg-2 control-label">Last Name</label>

                <div class="col-lg-10">
                    <input path="lastName" class="form-control" id="lastName" placeholder="Last Name" type="text"/>
                    <%--<form:errors path="lastName"/>--%>
                </div>
            </div>
            <div class="form-group">
                <label for="pwd" class="col-lg-2 control-label">Password</label>

                <div class="col-lg-10">
                    <input path="password" class="form-control" id="pwd" placeholder="Password" type="password"/>
                    <%-- <form:errors path="password"/>--%>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button id="signUp" type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>

</body>
</html>