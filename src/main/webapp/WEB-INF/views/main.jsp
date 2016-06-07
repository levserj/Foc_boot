<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>FOC- Makes someone happy</title>

    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/resources/css/input.css"/>
    <link rel="stylesheet" href="/resources/css/template.css"/>


    <script src="/resources/jquery/jquery-2.2.2.min.js"></script>

    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>

    <script src="/resources/js/main.js"></script>
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
            <a class="navbar-brand" href="#" style="padding-left: 3em"><img
                    src="<c:url value="/resources/images/logo.png"/>" width="400" height="45px"/></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" style="min-height: 80px">
            <%-- <ul class="nav navbar-nav">
                 <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                 <li><a href="#">Link</a></li>
                 <li class="dropdown">
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                     <ul class="dropdown-menu" role="menu">
                         <li><a href="#">Action</a></li>
                         <li><a href="#">Another action</a></li>
                         <li><a href="#">Something else here</a></li>
                         <li class="divider"></li>
                         <li><a href="#">Separated link</a></li>
                         <li class="divider"></li>
                         <li><a href="#">One more separated link</a></li>
                     </ul>
                 </li>
             </ul>--%>
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
<div id="featured-section" style="text-align: center">
    <h2>MAKE SOMEONE HAPPY</h2>

    <div id="circles"><img class="first" src="/resources/images/circle-red.png"/>
        <img style="padding-left: 2em" src="/resources/images/circle-pink.png"/>
        <img style="padding-left: 2em" src="/resources/images/circle-orange.png"/>
        <img style="padding-left: 2em" src="/resources/images/circle-yellow.png"/></div>
    <!--end circles-->
    <br><br>

    <h3 id="message" align="center">${requestScope.message}</h3>
</div>

<table style="table-layout: fixed; word-wrap: break-word;" class="table table-striped table-hover ">

    <tr>
        <th class="col-md-1">ID</th>
        <th class="col-md-3">TITLE</th>
        <th class="col-md-8">DESCRIPTION</th>
        <th class="col-md-2">OWNER's E-MAIL</th>
    </tr>

    <tbody id="mainTable">
    <!-- Populated by JS -->
    </tbody>
</table>

<!--end wrap-->
</body>

</html>