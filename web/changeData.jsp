<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="changeData_Bean" scope="request" class="externalBean.ChangeDataBean"/>

<jsp:setProperty name="changeData_Bean" property="*"/>

<%
    String nickN = (String) session.getAttribute("nkSaved");
    String color = "white";
    String result = "";

    if (request.getParameter("changeDataSubmit") != null){

        result = changeData_Bean.validateChange(nickN);

        if (result.equals("Successo")) color = "blue";
        else color = "red";

    } else if (request.getParameter("backToUserSubmit") != null){


        response.sendRedirect("/FERSA/userpage.jsp");

    }
%>

<!DOCTYPE html>
<html class="desktop mbr-site-loaded"><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><head><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <!-- Site made with Mobirise Website Builder v4.9.1, https://mobirise.com -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v4.9.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
    <link rel="shortcut icon" href="changeData_files/mbr-130x130.jpg" type="image/x-icon">
    <meta name="description" content="Web Creator Description">
    <title>ChangeData</title>
    <link rel="stylesheet" href="changeData_files/mobirise-icons.css">
    <link rel="stylesheet" href="changeData_files/tether.css">
    <link rel="stylesheet" href="changeData_files/bootstrap.css">
    <link rel="stylesheet" href="changeData_files/bootstrap-grid.css">
    <link rel="stylesheet" href="changeData_files/bootstrap-reboot.css">
    <link rel="stylesheet" href="changeData_files/style_002.css">
    <link rel="stylesheet" href="changeData_files/style.css">
    <link rel="stylesheet" href="changeData_files/mbr-additional.css" type="text/css">



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
                    <a href="/FERSA/index.jsp">
                         <img src="changeData_files/mbr-130x130.jpg" alt="Mobirise" title="" style="height: 3.8rem;">
                    </a>
                </span>
                <span class="navbar-caption-wrap"><a class="navbar-caption text-white display-4" href="/FERSA/index.jsp">FERSA</a></span>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav nav-dropdown" data-app-modern-menu="true"><li class="nav-item dropdown">
                <a class="nav-link link text-white dropdown-toggle display-4"   data-toggle="dropdown-submenu" aria-expanded="false">

                    Simulazione</a><div class="dropdown-menu"><a class="text-white dropdown-item display-4" href="/FERSA/othersubsystem.jsp" aria-expanded="false">Other SubSystem</a></div>
            </li></ul>
            <div class="navbar-buttons mbr-section-btn"><a class="btn btn-sm btn-white-outline display-4" href="/FERSA/login.jsp">Login
            </a> <a class="btn btn-sm btn-primary display-4" href="/FERSA/register.jsp">Register
            </a></div>
        </div>
    </nav>
</section>

<section class="engine"><a href="https://mobirise.info/t">free amp templates</a></section><section class="header9 cid-rgrhyqMNgL mbr-parallax-background" id="header9-c" style="z-index: 0; background-image: url(/FERSA/assets/images/mbr-1-1920x1280.jpg);  position: relative;">



    <div class="mbr-overlay" style="opacity: 0.6; background-color: rgb(255, 255, 255);">
    </div>

    <div class="container">
        <div class="media-container-column mbr-white col-lg-8 col-md-10">

            <h1 class="mbr-section-title align-left mbr-bold pb-3 mbr-fonts-style display-1">Modifica dati </h1>

            <h3 class="mbr-section-subtitle align-left mbr-light pb-3 mbr-fonts-style display-2">Riempi i campi da modificare e conferma in fondo.</h3>

            <p style="color:white; background-color:<%=color%>;"><%=result%></p>


            <form name="backtoUs_form" action="changeData.jsp" method="post">
                <p class="mbr-text pb-3 mbr-fonts-style display-5">
                    <input type="submit" name="backToUserSubmit" value="Torna indietro" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 1rem;margin-right: 0px;margin-bottom: 0px;padding-right: 2rem;padding-left: 2rem;padding-bottom: 0.1rem;padding-top: 0.1rem;">
                </p>
            </form>

            <form name="changeD_form" action="changeData.jsp" method="post">
                <p class="mbr-text pb-3 mbr-fonts-style display-5">

                    Avatar (1-6): <input name="avatar" type="text" style="text-align:left; margin-bottom: 10px;">
                    <br>
                    1 <img src="profileImage/default-Avatar.png" alt="Avatar1 Icon" style="width:64px;height:64px;">
                    2 <img src="profileImage/rocket-Avatar.png" alt="Avatar2 Icon" style="width:64px;height:64px;">
                    3 <img src="profileImage/girl-Avatar.png" alt="Avatar3 Icon" style="width:64px;height:64px;">
                    4 <img src="profileImage/man-Avatar.png" alt="Avatar4 Icon" style="width:64px;height:64px;">
                    5 <img src="profileImage/girlStudent-Avatar.png" alt="Avatar5 Icon" style="width:64px;height:64px;">
                    6 <img src="profileImage/manStudent-Avatar.png" alt="Avatar6 Icon" style="width:64px;height:64px;">
                    <br>
                    Email: <input name="email" type="text" style="text-align:left; margin-top: 10px;">
                    <br>
                    Ruoli: (scrivere Yes/No nei relativi campi)
                    <br>
                    Tenant:<input name="tenant" type="text" style="text-align:left">
                    <br>
                    Renter: <input name="renter" type="text" style="text-align:left">
                    <br>
                    Social Status: <input name="socialStatus" type="text" style="text-align:left">
                    <br>
                    Phone Number: <input name="phoneNumber" type="text" style="text-align:left">
                    <br>
                    Indirizzo: <input name="address" type="text" style="text-align:left">
                    <br>
                    Nazionalita': <input name="nationality" type="text" style="text-align:left">
                    <br>
                    Per cambiare la password, inserire quella vecchia e la nuova
                    <br>
                    Old Password: <input name="oldPW" type="password" style="text-align:left">
                    <br>
                    New Password: <input name="newPW" type="password" style="text-align:left">
                    <br>
                    Confirm Password: <input name="confirmPW" type="password" style="text-align:left">
                    <br>
                    <input type="submit" name="changeDataSubmit" value="Conferma Dati" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 1rem;margin-right: 0px;margin-bottom: 0px;padding-right: 2rem;padding-left: 2rem;padding-bottom: 0.1rem;padding-top: 0.1rem;">

                </p>
            </form>

        </div>
    </div>


    <div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; overflow: hidden; pointer-events: none; z-index: -100;" id="jarallax-container-0"><div style="background-position: 100% 100%; background-size: cover; background-repeat: no-repeat; background-image: none; position: fixed; top: 0px; left: 0px; width: 866px; height: 619.28px; overflow: hidden; pointer-events: none; margin-top: 9.36px; transform: translate3d(0px, -41.8976px, 0px);"></div></div></section>


<script src="changeData_files/jquery.js"></script>
<script src="changeData_files/popper.js"></script>
<script src="changeData_files/tether.js"></script>
<script src="changeData_files/bootstrap.js"></script>
<script src="changeData_files/smooth-scroll.js"></script>
<script src="changeData_files/script_002.js"></script>
<script src="changeData_files/jquery_002.js"></script>
<script src="changeData_files/jarallax.js"></script>
<script src="changeData_files/script.js"></script>



</body></html>