<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="index_Bean" scope="request" class="externalBean.IndexBean"/>

<jsp:setProperty name="index_Bean" property="*"/>

<%
    String result = "";
    String color = "white";
    //String dynamicIP = "localhost";
    String dynamicIP = index_Bean.startDB();

    if (request.getParameter("changeIPSubmit") != null) {

        result = index_Bean.changeIP();
        if (result.equals("Successo")) color = "blue";
        else color = "red";
    }
%>

<!DOCTYPE html>
<html >
<head>
    <!-- Site made with Mobirise Website Builder v4.9.1, https://mobirise.com -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v4.9.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
    <link rel="shortcut icon" href="assets/images/mbr-130x130.jpg" type="image/x-icon">
    <meta name="description" content="">
    <title>Home</title>
    <link rel="stylesheet" href="assets/mobirise-icons/mobirise-icons.css">
    <link rel="stylesheet" href="assets/tether/tether.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="assets/dropdown/css/style.css">
    <link rel="stylesheet" href="assets/theme/css/style.css">
    <link rel="stylesheet" href="assets/mobirise/css/mbr-additional.css" type="text/css">



</head>
<body>
<section class="menu cid-qTkzRZLJNu" once="menu" id="menu1-0">



    <nav class="navbar navbar-expand beta-menu navbar-dropdown align-items-center navbar-fixed-top navbar-toggleable-sm">
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
                    <a href="index.jsp">
                         <img src="assets/images/mbr-130x130.jpg" alt="Mobirise" title="" style="height: 3.8rem;">
                    </a>
                </span>
                <span class="navbar-caption-wrap"><a class="navbar-caption text-white display-4" href="index.jsp">FERSA</a></span>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav nav-dropdown" data-app-modern-menu="true"><li class="nav-item dropdown open">
                <a class="nav-link link text-white dropdown-toggle display-4" data-toggle="dropdown-submenu" aria-expanded="false">

                    Simulazione</a><div class="dropdown-menu"><a class="text-white dropdown-item display-4" href="othersubsystem.jsp" aria-expanded="false">Other SubSystem</a></div>
            </li></ul>
            <div class="navbar-buttons mbr-section-btn"><a class="btn btn-sm btn-white-outline display-4" href="login.jsp">Login
            </a> <a class="btn btn-sm btn-primary display-4" href="register.jsp">Register
            </a></div>
        </div>
    </nav>
</section>

<section class="engine"><a href="https://mobirise.info/n">free simple site templates</a></section><section class="cid-qTkA127IK8 mbr-fullscreen mbr-parallax-background" id="header2-1" style="z-index: 0; background-image: url(/FERSA/assets/images/mbr-1-1920x1280.jpg);  position: relative;">



    <div class="mbr-overlay" style="opacity: 0.6; background-color: rgb(118, 118, 118);"></div>

    <div class="container align-center">
        <div class="row justify-content-md-center">
            <div class="mbr-white col-md-10">
                <h1 class="mbr-section-title mbr-bold pb-3 mbr-fonts-style display-1" style="margin-top: 75px;">
                    FERSA
                </h1>
                <h3 class="mbr-section-subtitle align-center mbr-light pb-3 mbr-fonts-style display-2">Flat Evaluating, Renting, Sharing, and Advertising</h3>
                <p class="mbr-text pb-3 mbr-fonts-style display-5">Piattaforma libera, nata per mettere in comunicazione proprietari di appartamenti e studenti.
                    <br>Il servizio è e sarà completamente gratuito, e privo di fastidiosi annunci. Vuoi provarlo? Iscriviti, bastano pochi passi!</p>
                <div class="mbr-section-btn"><a class="btn btn-md btn-white-outline display-4" href="login.jsp">LOGIN</a>
                    <a class="btn btn-md btn-primary display-4" href="register.jsp">REGISTER</a></div>

                <form name="changeIp_form" action="index.jsp" method="post">
                    <p class="mbr-text pb-3 mbr-fonts-style display-5">
                        <br>
                    <p style="color:white; background-color:<%=color%>;"><%=result%></p>
                    <br>
                    IP: <input name="IPtext" type="text" id="IPtext" value=<%=dynamicIP%>>
                    <input name="changeIPSubmit" type="submit" id="changeIPSubmit" value="Change IP" class="btn btn-success-outline" style="padding-bottom: 0.4rem;padding-top: 0.4rem;">
                    <br>
                    </p>
                </form>
            </div>
        </div>
    </div>

    <div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; overflow: hidden; pointer-events: none; z-index: -100;" id="jarallax-container-0"><div style="background-position: 100% 100%; background-size: auto; background-repeat: no-repeat; background-image: none; position: relative; top: 0px; left: 0px; width: 866px; height: 637.04px; overflow: hidden; pointer-events: none; margin-top: 0.479993px; transform: translate3d(0px, 23.0856px, 0px);"></div></div></section>


<script src="aassets/jquery/jquery.min.js"></script>
<script src="assets/popper/popper.min.js"></script>
<script src="assets/tether/tether.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/smoothscroll/smooth-scroll.js"></script>
<script src="assets/dropdown/js/script.min.js"></script>
<script src="assets/touchswipe/jquery.touch-swipe.min.js"></script>
<script src="assets/parallax/jarallax.min.js"></script>
<script src="assets/theme/js/script.js"></script>


</body>
</html>