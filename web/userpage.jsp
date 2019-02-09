<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="userPage_Bean" scope="request" class="externalBean.UserPageBean"/>

<jsp:setProperty name="userPage_Bean" property="*"/>

<%
    String nickN = (String) session.getAttribute("nkSaved");

    String[] valueData = userPage_Bean.getStringUsData(nickN);
    String result = "";
    String genere = "o";
    if (valueData[6].equals("WOMAN")) genere = "a";

    if (request.getParameter("changeData") != null){
%>
        <jsp:forward page="changeData.jsp"/>

<%
    } else if (request.getParameter("cancelMyself") != null){

        result = userPage_Bean.cancelUser(nickN);

        if(result.equals("Successo")){

            session.removeAttribute(nickN);
%>
            <jsp:forward page="index.jsp"/>
<%
        }
    }
%>

<!DOCTYPE html>
<html class="desktop mbr-site-loaded"><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><head><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <!-- Site made with Mobirise Website Builder v4.9.1, https://mobirise.com -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v4.9.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
    <link rel="shortcut icon" href="userpage_files/mbr-130x130.jpg" type="image/x-icon">
    <meta name="description" content="Web Creator Description">
    <title>UserPage</title>
    <link rel="stylesheet" href="userpage_files/mobirise-icons.css">
    <link rel="stylesheet" href="userpage_files/tether.css">
    <link rel="stylesheet" href="userpage_files/bootstrap.css">
    <link rel="stylesheet" href="userpage_files/bootstrap-grid.css">
    <link rel="stylesheet" href="userpage_files/bootstrap-reboot.css">
    <link rel="stylesheet" href="userpage_files/style_002.css">
    <link rel="stylesheet" href="userpage_files/style.css">
    <link rel="stylesheet" href="userpage_files/mbr-additional.css" type="text/css">



    <style type="text/css" id="jarallax-clip-0">#jarallax-container-0 {
        clip: rect(0 866px 606.7999877929688px 0);
        clip: rect(0, 866px, 606.7999877929688px, 0);
    }</style></head>
<body><section id="top-1" class="engine"><a href="https://mobirise.ws/">Mobirise Website Builder</a> v4.9.1</section>
<section class="menu cid-rgrhyq52oP" once="menu" id="menu1-b">



    <nav class="navbar navbar-expand beta-menu navbar-dropdown align-items-center navbar-fixed-top navbar-toggleable-sm navbar-short">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <div class="hamburger">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
        </button>
        <div class="menu-logo">
            <div class="navbar-brand">
                <span class="navbar-logo">
                    <a href="http://localhost:8080/unnamed/index.jsp">
                         <img src="userpage_files/mbr-130x130.jpg" alt="Mobirise" title="" style="height: 3.8rem;">
                    </a>
                </span>
                <span class="navbar-caption-wrap"><a class="navbar-caption text-white display-4" href="http://localhost:8080/unnamed/index.jsp">FERSA</a></span>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav nav-dropdown" data-app-modern-menu="true"><li class="nav-item dropdown">
                <a class="nav-link link text-white dropdown-toggle display-4"   data-toggle="dropdown-submenu" aria-expanded="false">

                    Simulazione</a><div class="dropdown-menu"><a class="text-white dropdown-item display-4" href="http://localhost:8080/unnamed/othersubsystem.jsp" aria-expanded="false">Other SubSystem</a></div>
            </li></ul>
            <div class="navbar-buttons mbr-section-btn"><a class="btn btn-sm btn-white-outline display-4" href="http://localhost:8080/unnamed/login.jsp">Login
            </a> <a class="btn btn-sm btn-primary display-4" href="http://localhost:8080/unnamed/register.jsp">Register
            </a></div>
        </div>
    </nav>
</section>

<section class="engine"><a href="https://mobirise.info/t">free amp templates</a></section><section class="header9 cid-rgrhyqMNgL mbr-parallax-background" id="header9-c" style="z-index: 0; background-image: url(http://localhost:8080/unnamed/assets/images/mbr-1-1920x1280.jpg);  position: relative;">



    <div class="mbr-overlay" style="opacity: 0.8; background-color: rgb(255, 255, 255);">
    </div>

    <div class="container">
        <div class="media-container-column mbr-white col-lg-8 col-md-10">

            <h1 class="mbr-section-title align-left mbr-bold pb-3 mbr-fonts-style display-1">
                Benvenut<%=genere%> <%=valueData[0]%> </h1>

            <h3 class="mbr-section-subtitle align-left mbr-light pb-3 mbr-fonts-style display-2">
                Da qui potrai gestire i tuoi dati, sia personali che di piattaforma.</h3>

            <p class="mbr-text pb-3 mbr-fonts-style display-5">
                Nome: <%=valueData[0]%> Cognome: <%=valueData[1]%>
                <br>
                CF: <%=valueData[2]%> Nickname: <%=valueData[3]%>
                <br>
                Email: <%=valueData[4]%> BirthDay: <%=valueData[5]%>
                <br>
                Gender: <%=valueData[6]%> Social Status: <%=valueData[7]%>
                <br>
                Ruoli:
                <br>
                Is Tenant?: <%=valueData[8]%> IsRenter?: <%=valueData[9]%>
                <br>
                Phone Number: <%=valueData[10]%> Indirizzo: <%=valueData[11]%>
                <br>
                Luogo di Nascita: <%=valueData[12]%> Nazionalita': <%=valueData[13]%>
            </p>

            <p class="mbr-text pb-3 mbr-fonts-style display-5">

            <form name="gotoChData_form" action="userpage.jsp" method="post">
                <input type="submit" name="changeData" value="Cambia Dati" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 0.8rem;margin-right: 0px;padding-left: 2rem;padding-right: 2rem;">
            </form>
            <form name="cancel_form" action="userpage.jsp" method="post">
                <br>
                <input type="submit" name="cancelMyself" value="Elimina Account" class="btn btn-secondary-outline" style="margin-left: 0px;margin-top: 0.8rem;margin-right: 0px;padding-left: 1rem;padding-right: 1rem;padding-top: 0.2rem;padding-bottom: 0.2rem;">
                <br>
                Result: <%=result%>
            </form>
            </p>
        </div>
    </div>


    <div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; overflow: hidden; pointer-events: none; z-index: -100;" id="jarallax-container-0"><div style="background-position: 100% 100%; background-size: cover; background-repeat: no-repeat; background-image: none; position: relative; top: 0px; left: 0px; width: 866px; height: 619.28px; overflow: hidden; pointer-events: none; margin-top: 9.36px; transform: translate3d(0px, -41.8976px, 0px);"></div></div></section>


<script src="userpage_files/jquery.js"></script>
<script src="userpage_files/popper.js"></script>
<script src="userpage_files/tether.js"></script>
<script src="userpage_files/bootstrap.js"></script>
<script src="userpage_files/smooth-scroll.js"></script>
<script src="userpage_files/script_002.js"></script>
<script src="userpage_files/jquery_002.js"></script>
<script src="userpage_files/jarallax.js"></script>
<script src="userpage_files/script.js"></script>



</body></html>