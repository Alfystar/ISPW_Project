<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<%
    if (request.getParameter("loginSubmit") != null){
%>

<jsp:forward page="userpage.html"/>

<%
} else if (request.getParameter("recoverSubmit") != null) {
%>

<jsp:forward page="recoverCredentials.html"/>
<%
    }
%>

<!DOCTYPE html>
<html class="desktop mbr-site-loaded"><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><head><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style><link type="text/css" id="dark-mode" rel="stylesheet" href=""><style type="text/css" id="dark-mode-custom-style"></style>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <!-- Site made with Mobirise Website Builder v4.9.1, https://mobirise.com -->
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="generator" content="Mobirise v4.9.1, mobirise.com">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
  <link rel="shortcut icon" href="login_files/mbr-130x130.jpg" type="image/x-icon">
  <meta name="description" content="Web Creator Description">
  <title>Login</title>
  <link rel="stylesheet" href="login_files/mobirise-icons.css">
  <link rel="stylesheet" href="login_files/tether.css">
  <link rel="stylesheet" href="login_files/bootstrap.css">
  <link rel="stylesheet" href="login_files/bootstrap-grid.css">
  <link rel="stylesheet" href="login_files/bootstrap-reboot.css">
  <link rel="stylesheet" href="login_files/style_002.css">
  <link rel="stylesheet" href="login_files/style.css">
  <link rel="stylesheet" href="login_files/mbr-additional.css" type="text/css">
  
  
  
<style type="text/css" id="jarallax-clip-0">#jarallax-container-0 {
           clip: rect(0 866px 636.4000244140625px 0);
           clip: rect(0, 866px, 636.4000244140625px, 0);
        }</style></head>
<body class=""><section id="top-1" class="engine"><a href="https://mobirise.ws/">Mobirise Website Builder</a> v4.9.1</section>
  <section class="menu cid-qTkzRZLJNu" once="menu" id="menu1-3">

    

    <nav class="navbar navbar-expand beta-menu navbar-dropdown align-items-center navbar-fixed-top navbar-toggleable-sm">
        <button class="navbar-toggler navbar-toggler-right collapsed" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                    <a href="http://localhost:8080/ISPW_Project/web/index.html">
                         <img src="login_files/mbr-130x130.jpg" alt="Mobirise" title="" style="height: 3.8rem;">
                    </a>
                </span>
                <span class="navbar-caption-wrap"><a class="navbar-caption text-white display-4" href="http://localhost:8080/ISPW_Project/web/index.html">FERSA</a></span>
            </div>
        </div>
        <div class="navbar-collapse collapse" id="navbarSupportedContent" style="">
            <ul class="navbar-nav nav-dropdown" data-app-modern-menu="true"><li class="nav-item dropdown">
                    <a class="nav-link link text-white dropdown-toggle display-4" href="https://mobirise.com/" data-toggle="dropdown-submenu" aria-expanded="false">
                        
                        Simulazione</a><div class="dropdown-menu"><a class="text-white dropdown-item display-4" href="http://localhost:8080/ISPW_Project/web/othersubsystem.html" aria-expanded="false">Other SubSystem</a></div>
                </li></ul>
            <div class="navbar-buttons mbr-section-btn"><a class="btn btn-sm btn-white-outline display-4" href="http://localhost:8080/ISPW_Project/web/login.jsp">Login
                    </a> <a class="btn btn-sm btn-primary display-4" href="http://localhost:8080/ISPW_Project/web/register.html">Register
                    </a></div>
        </div>
    </nav>
</section>

<section class="engine"><a href="https://mobirise.info/q">free responsive web templates</a></section><section class="header9 cid-rgnzKPALQD mbr-parallax-background" id="header9-4" style="z-index: 0; background-image: none; position: relative;">

    

    <div class="mbr-overlay" style="opacity: 0.8; background-color: rgb(255, 255, 255);">
    </div>

    <div class="container">
        <div class="media-container-column mbr-white col-lg-8 col-md-10">
            <h1 class="mbr-section-title align-left mbr-bold pb-3 mbr-fonts-style display-1" style="padding-bottom: 0.1rem;">
                Login</h1>
            <h3 class="mbr-section-subtitle align-left mbr-light pb-3 mbr-fonts-style display-2">Inserisci le tue credenziali per iniziare!</h3>
    
<p class="mbr-text pb-3 mbr-fonts-style display-5"> Nickname:
        <br>
        <input name="firstname" type="text">
        </p>
 
<p class="mbr-text pb-3 mbr-fonts-style display-5"> Password:
        <br>
        <input name="lastname" type="password">
        </p>
<p class="mbr-text pb-3 mbr-fonts-style display-5"> 
<input type="submit" name="loginSubmit" value="Login" class="btn btn-outline-primary" style="margin-left: 0px;margin-top: 1.2rem;">
  
<br>
  
<input type="submit" name="recoverSubmit" value="Credenziali perse?" class="btn btn-secondary-outline" style="margin-left: 0px;margin-top: 2rem;padding-left: 1rem;padding-right: 1rem;padding-bottom: 0.1rem;padding-top: 0.1rem;margin-right: 0px;">
 </p>
            
            
        </div>
    </div>

    
<div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; overflow: hidden; pointer-events: none; z-index: -100;" id="jarallax-container-0"><div style="background-position: 50% 50%; background-size: cover; background-repeat: no-repeat; background-image: url(&quot;http://localhost:8080/ISPW_Project/web/assets/images/mbr-1-1920x1280.jpg&quot;); position: fixed; top: 0px; left: 0px; width: 866px; height: 637.04px; overflow: hidden; pointer-events: none; margin-top: 0.479993px; transform: translate3d(0px, 23.0856px, 0px);"></div></div></section>


  <script src="login_files/jquery.js"></script>
  <script src="login_files/popper.js"></script>
  <script src="login_files/tether.js"></script>
  <script src="login_files/bootstrap.js"></script>
  <script src="login_files/smooth-scroll.js"></script>
  <script src="login_files/script_002.js"></script>
  <script src="login_files/jquery_002.js"></script>
  <script src="login_files/jarallax.js"></script>
  <script src="login_files/script.js"></script>


</body></html>