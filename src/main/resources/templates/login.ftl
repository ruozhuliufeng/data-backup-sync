<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta name="keywords" content="DBS,数据备份,数据同步,数据云端存储,数据云端备份">
    <meta name="description" content="Data Backup Sync 支持将本地数据同步至云端存储，定时备份数据库">
    <meta name="author" content="ruozhuliufeng">
    <title>登录 - DBS</title>
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <#include "common/common_css.ftl" />
    <link rel="stylesheet" type="text/css" href="/css/animate.min.css">
    <style>
        .signin-box {
            background-color: rgba(255, 255, 255, .25);
            width: 420px;
        }

        .signin-box p:last-child {
            margin-bottom: 0px;
        }

        .signin-form .form-control {
            background: rgba(0, 0, 0, 0.3);
            color: #fff;
        }

        .signin-form .has-feedback {
            position: relative;
        }

        .signin-form .has-feedback .form-control {
            padding-left: 36px;
        }

        .signin-form .has-feedback .mdi {
            position: absolute;
            top: 0;
            left: 0;
            right: auto;
            width: 36px;
            height: 36px;
            line-height: 36px;
            z-index: 4;
            color: #dcdcdc;
            display: block;
            text-align: center;
            pointer-events: none;
        }

        .signin-form .has-feedback.row .mdi {
            left: 15px;
        }

        .signin-form .form-control::-webkit-input-placeholder {
            color: rgba(255, 255, 255, .8);
        }

        .signin-form .form-control:-moz-placeholder {
            color: rgba(255, 255, 255, .8);
        }

        .signin-form .form-control::-moz-placeholder {
            color: rgba(255, 255, 255, .8);
        }

        .signin-form .form-control:-ms-input-placeholder {
            color: rgba(255, 255, 255, .8);
        }

        .signin-form .custom-control-label::before {
            background: rgba(0, 0, 0, 0.3);
            border-color: rgba(0, 0, 0, 0.1);
        }

        .signin-form .form-check-input {
            background-color: transparent;
            border-color: #fff;
        }
    </style>
</head>

<body class="center-vh" style="background-image: url(/img/login-bg.jpg); background-size: cover;">
<div class="signin-box p-5 mb-0 mr-2 ml-2">
    <div class="text-center mb-3">
        <a href="/index"> <img alt="Data Backup Sync" src="/img/logo-sidebar.png"> </a>
    </div>
    <form action="/login" method="post" class="signin-form needs-validation" novalidate>
        <div class="mb-3 has-feedback">
            <span class="mdi mdi-account" aria-hidden="true"></span>
            <input type="text" class="form-control" id="username" placeholder="用户名" required>
        </div>

        <div class="mb-3 has-feedback">
            <span class="mdi mdi-lock" aria-hidden="true"></span>
            <input type="password" class="form-control" id="password" placeholder="密码" required>
        </div>

        <div class="mb-3">
            <div class="form-check text-white">
                <input type="checkbox" class="form-check-input" id="rememberme">
                <label class="form-check-label not-user-select" for="rememberme">5天内自动登录</label>
            </div>
        </div>

        <div class="mb-3 d-grid">
            <button class="btn btn-primary" type="submit">立即登录</button>
        </div>
    </form>

    <p class="text-center text-white">Copyright © 2023 <a target="_blank"
                                                          href="http://www.msop.tech">RUOZHULIUFENG</a>. All right
        reserved</p>
</div>
<#include "common/common_js.ftl" />
<script type="text/javascript" src="/js/lyear-loading.js"></script>
<script type="text/javascript" src="/js/bootstrap-notify.min.js"></script>
</body>
</html>
