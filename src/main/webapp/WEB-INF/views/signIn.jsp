<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>FOC- Makes someone happy</title>

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css"/>

    <script src="/resources/jquery/jquery-2.2.2.min.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>

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
                <li><a href="/signIn">Sing in</a></li>
                <li><a href="/logout">Sign Out</a></li>
                <li><a href="/myPage">MyPage</a></li>
            </ul>
        </div>
    </div>
</nav>
<div style="margin: auto; width: 50%;">

    <form class="form-horizontal" action="/login" method="post">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                <p style="text-align: center">
                    Login attempt failed, try again<br/>
                    Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </p>
            </div>
        </c:if>
        <c:if test="${not empty logout}">
            <div class="alert alert-success">
                <p style="text-align: center">${logout}</p>
            </div>
        </c:if>
        <fieldset>
            <legend style="text-align: center;">Sign In</legend>
            <div class="form-group">
                <label for="username" class="col-lg-2 control-label">Email</label>
                <div class="col-lg-10">
                    <input path="email" class="form-control" id="username" name="username" placeholder="Email"
                           type="text"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-lg-2 control-label">Password</label>
                <div class="col-lg-10">
                    <input path="password" class="form-control" id="password" name="password" placeholder="Password"
                           type="password"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <button id="signIn" type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>