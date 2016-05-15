<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SD
  Date: 04.02.2016
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>FOC- Makes someone happy</title>
    <meta charset="utf-8">

    <script src="/resources/jquery/jquery-2.2.2.min.js"></script>
    <script src="/resources/jquery/jquery-ui/jquery-ui.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>

    <script src="/resources/js/signUp.js"></script>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/skin.css"/>">
</head>
<body class="home">
<div id="wrap">
    <div id="header"><img src="<c:url value="/resources/images/logo.png"/>" width="400" height="49"/>

        <div id="nav">
            <ul class="menu">
                <li><a href="${pageContext.request.contextPath}/signUp">Sing up</a></li>
                <li><a href="${pageContext.request.contextPath}/forms/signIn.jsp">Sing in</a></li>
            </ul>
        </div>
        <!--end nav-->
    </div>
    <!--end header-->
    <div id="featured-section">
        <div class="container">
            <form id="contact" modelAttribute="userForm" action=# method="post">
                <h3>Create new user</h3>
                <fieldset>
                    <input id="firstName" placeholder="firstName" tabindex="2" required>
                </fieldset>
                <fieldset>
                    <input id="lastName" placeholder="lastName" tabindex="2" required>
                </fieldset>
                <fieldset>
                    <input id="email" placeholder="E-mail" type="email" tabindex="2" required>
                </fieldset>
                <fieldset>
                    <input id="pwd" placeholder="Password" type="password" tabindex="3" required>
                </fieldset>
                <fieldset>
                    <button name="Add" type="submit" id="signUp" data-Add="...Sending">Sign Up</button>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>
