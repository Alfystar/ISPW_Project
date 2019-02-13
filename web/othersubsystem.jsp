<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="otherSubSyS_bean" scope="request" class="externalBean.OtherSubSystemBean"/>

<jsp:setProperty name="otherSubSyS_bean" property="*"/>

<%
    String opResult = "";
    String imgPath = "";

    if (request.getParameter("banSubmit") != null)
        opResult = otherSubSyS_bean.banUtente();

    else if (request.getParameter("unBanSubmit") != null)
        opResult = otherSubSyS_bean.unBanUtente();

    else if (request.getParameter("destroySubmit") != null)
        opResult = otherSubSyS_bean.destroyUtente();

    else if (request.getParameter("obtainPubDSubmit") != null) {
        String [] tmpResult = otherSubSyS_bean.ottieniPubDUtente();
        opResult = tmpResult[0];
        imgPath = "profileImage/" + tmpResult[1] + ".png";
    }

    else if (request.getParameter("obtainPriDSubmit") != null)
        opResult = otherSubSyS_bean.ottieniPriDUtente();

    else if (request.getParameter("obtainRoleSubmit") != null)
        opResult = otherSubSyS_bean.ottieniRuoliUtente();

    else if (request.getParameter("obtainStatusSubmit") != null)
        opResult = otherSubSyS_bean.ottieniStatoUtente();

    else if (request.getParameter("modSaveRole") != null)
        opResult = otherSubSyS_bean.modRuoliUtente();

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
    <title>OtherSubSystem</title>
    <link rel="stylesheet" href="othersubsystem_files/mobirise-icons.css">
    <link rel="stylesheet" href="othersubsystem_files/tether.css">
    <link rel="stylesheet" href="othersubsystem_files/bootstrap.css">
    <link rel="stylesheet" href="othersubsystem_files/bootstrap-grid.css">
    <link rel="stylesheet" href="othersubsystem_files/bootstrap-reboot.css">
    <link rel="stylesheet" href="othersubsystem_files/style_002.css">
    <link rel="stylesheet" href="othersubsystem_files/style.css">
    <link rel="stylesheet" href="othersubsystem_files/mbr-additional.css" type="text/css">



    <style type="text/css" id="jarallax-clip-0">#jarallax-container-0 {
        clip: rect(0 866px 10px 0);
        clip: rect(0, 866px, 10px, 0);
    }</style></head>
<body class=""><section id="top-1" class="engine"><a href="https://mobirise.ws/">Mobirise Website Builder</a> v4.9.1</section>
<section class="menu cid-rgnE9xqzT6" once="menu" id="menu1-5">



    <nav class="navbar navbar-expand beta-menu navbar-dropdown align-items-center navbar-fixed-top navbar-toggleable-sm">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <div class="hamburger">
                <span></span>
            </div>
        </button>
        <div class="menu-logo">
            <div class="navbar-brand">
                <span class="navbar-logo">
                    <a href="/FERSA/index.jsp">
                         <img src="othersubsystem_files/mbr-130x130.jpg" alt="Mobirise" title="" style="height: 3.8rem;">
                    </a>
                </span>
                <span class="navbar-caption-wrap"><a class="navbar-caption text-white display-4" href="/FERSA/index.jsp">FERSA</a></span>
            </div>
        </div>
        <div class="navbar-collapse collapse" id="navbarSupportedContent" style="">
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

<section class="engine"><a href="https://mobirise.info/z">best html templates</a></section><section class="header9 cid-rgnE9yb8VN mbr-parallax-background" id="header9-6" style="z-index: 0; background-image: url(/FERSA/assets/images/mbr-1-1920x1280.jpg);  position: relative;">

    <div class="mbr-overlay" style="opacity: 0.6; background-color: rgb(255, 255, 255);">
    </div>

    <div class="container">
        <div class="media-container-column mbr-white col-lg-8 col-md-10">
            <h1 class="mbr-section-title align-left mbr-bold pb-3 mbr-fonts-style display-1">
                OtherSubSystems</h1>

            <p class="mbr-text align-left pb-3 mbr-fonts-style display-5">
                Simulazione dei vari sottosistemi di Fersa.</p>

            <form name="others_form" action="othersubsystem.jsp" method="post">
                <p class="mbr-text pb-3 mbr-fonts-style display-5" style="margin-bottom: 1rem;">

                    Nickname User: <input name="nickInput" type="text" style="margin-bottom: 10px;">
                    <br>
                    New Role:<br>
                    Renter (Yes/No): <input name="renterInput" type="text" style="margin-bottom: 10px;">
                    <br>
                    Tenant (Yes/No): <input name="tenantInput" type="text" style="margin-bottom: 10px;">
                    <br>
                    <img src=<%=imgPath%> alt="Avatar Icon" style="width:64px;height:64px;">
                    <br>
                <p style="color:white; background-color:red;"><%=opResult%></p>
                <br>
                <input type="submit" name="banSubmit" value="Ban Utente" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 0.8rem;margin-right: 0px;padding-left: 2rem;padding-right: 2rem;">

                <input type="submit" name="unBanSubmit" value="UnBan Utente" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 0.8rem;padding-left: 2rem;padding-right: 2rem;margin-right: 0px;">

                <input type="submit" name="destroySubmit" value="Distruggi Utente" class="btn btn-secondary-outline" style="margin-left: 0px;margin-top: 0.8rem;padding-left: 2rem;padding-right: 2rem;">

                <input type="submit" name="obtainPubDSubmit" value="Ottieni Dati Pubblici" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 0.8rem;margin-right: 0px;padding-left: 2rem;padding-right: 2rem;">

                <input type="submit" name="obtainPriDSubmit" value="Ottieni Dati Privati" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 0.8rem;margin-right: 0px;padding-left: 2rem;padding-right: 2rem;">

                <input type="submit" name="obtainRoleSubmit" value="Ottieni Ruolo" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 0.8rem;margin-right: 0px;padding-left: 2rem;padding-right: 2rem;">

                <input type="submit" name="obtainStatusSubmit" value="Ottieni Status" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 0.8rem;margin-right: 0px;padding-left: 2rem;padding-right: 2rem;">

                <input type="submit" name="modSaveRole" value="Modifica e salva Ruolo" class="btn btn-info-outline" style="margin-left: 0px;margin-top: 0.8rem;margin-right: 0px;padding-left: 2rem;padding-right: 2rem;">
                </p>
            </form>

        </div>
    </div>

    <div style="position: absolute; top: 0px; left: 0px; width: 100%; height: 100%; overflow: hidden; pointer-events: none; z-index: -100;" id="jarallax-container-0"></div></section>


<script src="othersubsystem_files/jquery.js"></script>
<script src="othersubsystem_files/popper.js"></script>
<script src="othersubsystem_files/tether.js"></script>
<script src="othersubsystem_files/bootstrap.js"></script>
<script src="othersubsystem_files/smooth-scroll.js"></script>
<script src="othersubsystem_files/script_002.js"></script>
<script src="othersubsystem_files/jquery_002.js"></script>
<script src="othersubsystem_files/jarallax.js"></script>
<script src="othersubsystem_files/script.js"></script>



</body></html>