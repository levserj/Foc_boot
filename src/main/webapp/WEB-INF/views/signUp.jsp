<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/skin.css"/>">
</head>
<body class="home">
<div id="wrap">
    <div id="header"><img src="<c:url value="/resources/images/logo.png"/>" width="400" height="49"/>

        <div id="nav">
            <ul class="menu">
                <li><a href="${pageContext.request.contextPath}/forms/signUp.jsp">Sing up</a></li>
                <li><a href="${pageContext.request.contextPath}/forms/signIn.jsp">Sing in</a></li>
            </ul>
        </div>
        <!--end nav-->
    </div>
    <!--end header-->
    <div id="featured-section">
        <div class="container">
            <form id="contact" action="${pageContext.request.contextPath}/SignUpServlet" method="post">
                <h3>Create new user</h3>
                <fieldset>
                    <input name="name" placeholder="Name" tabindex="2" required>
                </fieldset>
                <fieldset>
                    <input name="email" placeholder="E-mail" type="email" tabindex="2" required>
                </fieldset>
                <fieldset>
                    <input name="pwd" placeholder="Password" type="password" tabindex="3" required>
                </fieldset>
                <fieldset>
                    <button name="Add" type="Add" id="contact-Add" data-Add="...Sending">Sign Up</button>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>
