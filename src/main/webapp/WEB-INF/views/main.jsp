<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>FOC- Makes someone happy</title>
    <meta charset="utf-8">

    <script src="/resources/jquery/jquery-2.2.2.min.js"></script>
    <script src="/resources/jquery/jquery-ui/jquery-ui.js"></script>
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>

    <script src="/resources/js/main.js"></script>

    <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/skin.css">

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
        <h2></h2>

        <div id="circles"><img class="first" src="/resources/images/circle-red.png"/>
            <img src="/resources/images/circle-pink.png"/>
            <img src="/resources/images/circle-orange.png"/>
            <img src="/resources/images/circle-yellow.png"/></div>
        <!--end circles-->
        <br><br>

        <h3 align="center">${requestScope.message}</h3>
    </div>
    <!--end featured-section-->
</div>

<table class="simple-little-table" cellspacing='0' align="center">

    <tr>
        <th>ID</th>
        <th>TITLE</th>
        <th>DESCRIPTION</th>
        <th>OWNER</th>
    </tr>
    <tbody id="mainTable">
    <!-- Populated by JS -->
    </tbody>

</table>

<!--end wrap-->
</body>

</html>