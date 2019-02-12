<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="recover_Bean" scope="request" class="externalBean.RecoverBean"/>

<jsp:setProperty name="recover_Bean" property="*"/>


<%
    String result = "";
    String colRes = "white";

    if (request.getParameter("recoverDataSubmit") != null){

        result = recover_Bean.validateRecover();

        if (result.equals("Successo")) colRes = "blue";
        else colRes = "red";

    } else if (request.getParameter("backHomeSubmit") != null) {

        response.sendRedirect("/FERSA/validate.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html class="desktop mbr-site-loaded"><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><head><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <!-- Site made with Mobirise Website Builder v4.9.1, https://mobirise.com -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v4.9.1, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
    <link rel="shortcut icon" href="recoverCredentials_files/mbr-130x130.jpg" type="image/x-icon">
    <meta name="description" content="Web Creator Description">
    <title>RecoverCredential</title>
    <link rel="stylesheet" href="recoverCredentials_files/mobirise-icons.css">
    <link rel="stylesheet" href="recoverCredentials_files/tether.css">
    <link rel="stylesheet" href="recoverCredentials_files/bootstrap.css">
    <link rel="stylesheet" href="recoverCredentials_files/bootstrap-grid.css">
    <link rel="stylesheet" href="recoverCredentials_files/bootstrap-reboot.css">
    <link rel="stylesheet" href="recoverCredentials_files/style_002.css">
    <link rel="stylesheet" href="recoverCredentials_files/style.css">
    <link rel="stylesheet" href="recoverCredentials_files/mbr-additional.css" type="text/css">



    <style type="text/css" id="jarallax-clip-0">#jarallax-container-0 {
        clip: rect(0 846px 764px 0);
        clip: rect(0, 846px, 764px, 0);
    }</style></head>
<body>
<section id="top-1" class="engine"><a href="https://mobirise.ws/">Mobirise Website Builder</a> v4.9.1</section>
<section class="menu cid-rgrgUvVfJb" once="menu" id="menu1-9">



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
                         <img src="recoverCredentials_files/mbr-130x130.jpg" alt="Mobirise" title="" style="height: 3.8rem;">
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

<section class="engine"><a href="https://mobirise.info/d">website maker</a></section><section class="header9 cid-rgrgUwNGcq mbr-parallax-background" id="header9-a" style="z-index: 0; background-image: url(/FERSA/assets/images/mbr-1-1920x1280.jpg);  position: relative;">



    <div class="mbr-overlay" style="opacity: 0.6; background-color: rgb(255, 255, 255);">
    </div>

    <div class="container">
        <div class="media-container-column mbr-white col-lg-8 col-md-10">
            <h1 class="mbr-section-title align-left mbr-bold pb-3 mbr-fonts-style display-1">
                Recupero Credenziali</h1>
            <h3 class="mbr-section-subtitle align-left mbr-light pb-3 mbr-fonts-style display-2">Inserisci le risposte per ottenere nickname e password</h3>


            <form name="backHome_form" action="recoverCredentials.jsp" method="post">
                <p class="mbr-text pb-3 mbr-fonts-style display-5">
                    <input type="submit" name="backHomeSubmit" value="Torna al Login" class="btn btn-info" style="margin-left: 0px;margin-top: 1rem;margin-right: 0px;margin-bottom: 0px;padding-left: 2rem;padding-right: 2rem;padding-top: 0.1rem;padding-bottom: 0.1rem;">
                </p>
            </form>

            <p style="color:white; background-color:<%=colRes%>;"><%=result%></p>

            <form name="recover_form" action="recoverCredentials.jsp" method="post">
                <p class="mbr-text pb-3 mbr-fonts-style display-5">
                    DOMANDE DI RECUPERO
                    <br>
                    What is the name of your favorite pet?
                    <input name="question1" type="text" style="margin-bottom: 5px;">
                    <br>
                    Who is your favorite actor, musician, or artist?  <input name="question2" type="text" style="margin-bottom: 5px;">
                    <br>
                    What is your favorite movie? <input name="question3" type="text" style="margin-bottom: 5px;">
                    <br>
                    What is the name of your first school? <input name="question4" type="text">
                    <br>
                    <br>
                    Nickname <input name="nickname" type="text">
                    <br>
                    New Password: <input name="newPW" type="password" style="margin-bottom: 1px;margin-top: 1px;">
                    <br>
                    Confirm Password: <input name="confirmPW" type="password" style="margin-top: 1px;margin-bottom: 1px;">
                    <br>

                    <input type="submit" name="recoverDataSubmit" value="Invia richiesta" class="btn btn-info" style="margin-left: 0px;margin-top: 2rem;margin-right: 0px;margin-bottom: 1rem;padding-left: 2rem;padding-right: 2rem;padding-top: 0.1rem;padding-bottom: 0.1rem;">
                </p>
            </form>

        </div>
    </div>


    <div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; overflow: hidden; pointer-events: none; z-index: -100;" id="jarallax-container-0"><div style="background-position: 100% 100%; background-size: cover; background-repeat: no-repeat; background-image: none; position: fixed; top: 0px; left: 0px; width: 846px; height: 713.6px; overflow: hidden; pointer-events: none; margin-top: -37.8px; transform: translate3d(0px, 25.391px, 0px);"></div></div></section>


<script src="recoverCredentials_files/jquery.js"></script>
<script src="recoverCredentials_files/popper.js"></script>
<script src="recoverCredentials_files/tether.js"></script>
<script src="recoverCredentials_files/bootstrap.js"></script>
<script src="recoverCredentials_files/smooth-scroll.js"></script>
<script src="recoverCredentials_files/script_002.js"></script>
<script src="recoverCredentials_files/jquery_002.js"></script>
<script src="recoverCredentials_files/jarallax.js"></script>
<script src="recoverCredentials_files/script.js"></script>



<style type="text/css"></style></body></html>