<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- il parametro scope indica quanto la classe externalBean.LoginBean rimane dentro tomcat
e con che dinamiche, request è per risolvere il form e poi morire  -->
<jsp:useBean id="login_Bean" scope="request" class="externalBean.LoginBean"/>

<jsp:setProperty name="login_Bean" property="*"/>

<%
    /*  abbiamo utilizzato sendRedirect() invece di jsp:forward in quanto l'URL
     *   in questo modo si aggiorna; questo è dovuto al fatto che con forward la
     *   comunicazione è nel server, e il browser del client non è cosciente del
     *   cambio di pagina, mentre con sendRedirect (che di contro è più lenta)
     *   viene esplicitamente trasferita la richiesta ad un altro indirizzo, che
     *   potrebbe essere locato ovunque.
     */
    String result = "";
    if (request.getParameter("loginSubmit") != null){
        result = login_Bean.validateLogin();
        if (result.equals("Successo")){
            String nkStr = login_Bean.getNickname();
            //session riguarda la sessione di un utente (finchè il client è collegato al server),
            //e permette di fare delle impostazioni globali
            session.setAttribute("nkSaved", nkStr);
            //response invia dei comandi sotto il cofano del client, in questo caso di cambiare pagina
            //ma in generale potrebbero essere comandi di pulizzia o simili dei quali non ci rendiamo conto
            response.sendRedirect("/FERSA/userpage.jsp");
            //interrompe la codifica della pagina dovendo cambiare schermata, pure per evitare
            //inconsistenze nello sviluppo della pagina
            return;
        }
    } else if (request.getParameter("recoverSubmit") != null) {
        response.sendRedirect("/FERSA/recoverCredentials.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html class="desktop mbr-site-loaded">
<link type="text/css" id="dark-mode" rel="stylesheet" href="">
<style type="text/css" id="dark-mode-custom-style"></style>
<head>
    <link type="text/css" rel="stylesheet" href="">
    <style type="text/css" ></style>
    <link type="text/css" rel="stylesheet" href="">
    <style type="text/css" ></style>
    <link type="text/css" rel="stylesheet" href="">
    <style type="text/css" ></style>
    <link type="text/css" rel="stylesheet" href="">
    <style type="text/css" ></style>
    <link type="text/css" rel="stylesheet" href="">
    <style type="text/css" id=></style>
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
            </div>
        </button>
        <div class="menu-logo">
            <div class="navbar-brand">
                <span class="navbar-logo">
                    <a href="/FERSA/index.jsp">
                         <img src="login_files/mbr-130x130.jpg" alt="Mobirise" title="" style="height: 3.8rem;">
                    </a>
                </span>
                <span class="navbar-caption-wrap"><a class="navbar-caption text-white display-4" href="/FERSA/index.jsp">FERSA</a></span>
            </div>
        </div>
        <div class="navbar-collapse collapse" id="navbarSupportedContent" style="">
            <ul class="navbar-nav nav-dropdown" data-app-modern-menu="true"><li class="nav-item dropdown">
                <a class="nav-link link text-white dropdown-toggle display-4" data-toggle="dropdown-submenu" aria-expanded="false">

                    Simulazione</a><div class="dropdown-menu"><a class="text-white dropdown-item display-4" href="/FERSA/othersubsystem.jsp" aria-expanded="false">Other SubSystem</a></div>
            </li></ul>
            <div class="navbar-buttons mbr-section-btn"><a class="btn btn-sm btn-white-outline display-4" href="/FERSA/login.jsp">Login
            </a> <a class="btn btn-sm btn-primary display-4" href="/FERSA/register.jsp">Register
            </a></div>
        </div>
    </nav>
</section>

<section class="engine"><a href="https://mobirise.info/q">free responsive web templates</a></section><section class="header9 cid-rgnzKPALQD mbr-parallax-background" id="header9-4" style="z-index: 0; background-image: url(/FERSA/assets/images/mbr-1-1920x1280.jpg);  position: relative;">



    <div class="mbr-overlay" style="opacity: 0.6; background-color: rgb(255, 255, 255);">
    </div>

    <div class="container">
        <div class="media-container-column mbr-white col-lg-8 col-md-10">
            <h1 class="mbr-section-title align-left mbr-bold pb-3 mbr-fonts-style display-1" style="padding-bottom: 0.1rem;">
                Login</h1>
            <h3 class="mbr-section-subtitle align-left mbr-light pb-3 mbr-fonts-style display-2">Inserisci le tue credenziali per iniziare!</h3>

            <p style="color:white; background-color:red;"><%=result%></p>

            <form name="login_form" action="login.jsp" method="post">
                <p class="mbr-text pb-3 mbr-fonts-style display-5">
                    Nickname:
                    <br>
                    <input name="nickname" type="text" id="nickname">
                    <br>
                    Password:
                    <br>
                    <input name="password" type="password" id="password">
                    <br>

                    <input name="loginSubmit" type="submit" id="loginSubmit" value="Login" class="btn btn-primary-outline" style="margin-left: 0px;margin-top: 1.2rem;">
            </form>
            <br>
            <form name="recovery_form" action="login.jsp" method="post">
                <p class="mbr-text pb-3 mbr-fonts-style display-5">
                    <input name="recoverSubmit" type="submit" id="recoverSubmit" value="Credenziali perse?" class="btn btn-secondary-outline" style="margin-left: 0px;margin-top: 2rem;padding-left: 1rem;padding-right: 1rem;padding-bottom: 0.1rem;padding-top: 0.1rem;margin-right: 0px;">
                </p>
            </form>




        </div>
    </div>


    <div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; overflow: hidden; pointer-events: none; z-index: -100;" id="jarallax-container-0"><div style="background-position: 100% 100%; background-size: cover; background-repeat: no-repeat; background-image: none; position: relative; top: 0px; left: 0px; width: 866px; height: 637.04px; overflow: hidden; pointer-events: none; margin-top: 0.479993px; transform: translate3d(0px, 23.0856px, 0px);"></div></div></section>


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