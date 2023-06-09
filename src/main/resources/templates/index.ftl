<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
		<meta name="keywords" content="DBS,数据备份,数据同步,数据云端存储,数据云端备份">
		<meta name="description" content="Data Backup Sync 支持将本地数据同步至云端存储，定时备份数据库">
		<meta name="author" content="ruozhuliufeng">
		<title>DBS - 数据备份与同步</title>
		<link rel="shortcut icon" type="image/x-icon" href="favicon.ico">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-touch-fullscreen" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="default">
		<#include "common/common_css.ftl">
	</head>

	<body class="lyear-index">
		<div class="lyear-layout-web">
			<div class="lyear-layout-container">
				<!--左侧导航-->
				<aside class="lyear-layout-sidebar">

					<!-- logo -->
					<div id="logo" class="sidebar-header">
						<a href="/index"><img src="/img/logo-sidebar.png" title="DBS" alt="数据备份同步" /></a>
					</div>
					<div class="lyear-layout-sidebar-info lyear-scroll">

						<nav class="sidebar-main">

							<ul class="nav-drawer">
								<li class="nav-item active">
									<a class="multitabs" href="/main" id="default-page">
										<i class="mdi mdi-home-city-outline"></i>
										<span>首页</span>
									</a>
								</li>
								<li class="nav-item nav-item-has-subnav">
									<a href="javascript:void(0)">
										<i class="mdi mdi-television-guide"></i>
										<span>存储库信息</span>
									</a>
									<ul class="nav nav-subnav">
										<li> <a class="multitabs" href="/storage/webdav/index">WebDAV</a> </li>
										<li> <a class="multitabs" href="/storage/oss/index">阿里云OSS</a> </li>
										<li> <a class="multitabs" href="/storage/bos/index">百度云BOS</a> </li>
										<li> <a class="multitabs" href="/storage/obs/index">华为云OBS</a> </li>
										<li> <a class="multitabs" href="/storage/cos/index">腾讯云COS</a> </li>
										<li> <a class="multitabs" href="/storage/kodo/index">七牛云KODO</a> </li>
									</ul>
								</li>
								<li class="nav-item nav-item-has-subnav">
									<a href="javascript:void(0)">
										<i class="mdi mdi-silo"></i>
										<span>数据备份</span>
									</a>
									<ul class="nav nav-subnav">
										<li> <a class="multitabs" href="/system/database/index">数据库信息</a> </li>
									</ul>
								</li>
								<li class="nav-item nav-item-has-subnav">
									<a href="javascript:void(0)">
										<i class="mdi mdi-map-outline"></i>
										<span>同步任务</span>
									</a>
									<ul class="nav nav-subnav">
										<li> <a class="multitabs" href="/system/task/index"> 任务信息 </a> </li>
									</ul>
								</li>
								<li class="nav-item nav-item-has-subnav">
									<a href="javascript:void(0)">
										<i class="mdi mdi-tune"></i>
										<span>其他信息</span>
									</a>
									<ul class="nav nav-subnav">
										<li> <a class="multitabs" href="#">日志记录</a> </li>
									</ul>
								</li>
							</ul>
						</nav>

						<div class="sidebar-footer">
							<p class="copyright">
								<span>Copyright &copy; 2023. </span>
								<a target="_blank" href="http://www.msop.tech"> RUOZHULIUFENG </a>
								<span> All rights reserved.</span>
							</p>
						</div>
					</div>

				</aside>
				<!--End 左侧导航-->

				<!--头部信息-->
				<header class="lyear-layout-header">

					<nav class="navbar">

						<div class="navbar-left">
							<div class="lyear-aside-toggler">
								<span class="lyear-toggler-bar"></span>
								<span class="lyear-toggler-bar"></span>
								<span class="lyear-toggler-bar"></span>
							</div>
						</div>

						<ul class="navbar-right d-flex align-items-center">
							<!--切换主题配色-->
							<li class="dropdown dropdown-skin">
								<span data-bs-toggle="dropdown" class="icon-item">
									<i class="mdi mdi-palette fs-5"></i>
								</span>
								<ul class="dropdown-menu dropdown-menu-end" data-stopPropagation="true">
									<li class="lyear-skin-title">
										<p>主题</p>
									</li>
									<li class="lyear-skin-li clearfix">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="site_theme"
												id="site_theme_1" value="default" checked="checked">
											<label class="form-check-label" for="site_theme_1"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="site_theme"
												id="site_theme_2" value="translucent-green">
											<label class="form-check-label" for="site_theme_2"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="site_theme"
												id="site_theme_3" value="translucent-blue">
											<label class="form-check-label" for="site_theme_3"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="site_theme"
												id="site_theme_4" value="translucent-yellow">
											<label class="form-check-label" for="site_theme_4"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="site_theme"
												id="site_theme_5" value="translucent-red">
											<label class="form-check-label" for="site_theme_5"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="site_theme"
												id="site_theme_6" value="translucent-pink">
											<label class="form-check-label" for="site_theme_6"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="site_theme"
												id="site_theme_7" value="translucent-cyan">
											<label class="form-check-label" for="site_theme_7"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="site_theme"
												id="site_theme_8" value="dark">
											<label class="form-check-label" for="site_theme_8"></label>
										</div>
									</li>
									<li class="lyear-skin-title">
										<p>LOGO</p>
									</li>
									<li class="lyear-skin-li clearfix">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_1"
												value="default" checked="checked">
											<label class="form-check-label" for="logo_bg_1"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_2"
												value="color_2">
											<label class="form-check-label" for="logo_bg_2"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_3"
												value="color_3">
											<label class="form-check-label" for="logo_bg_3"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_4"
												value="color_4">
											<label class="form-check-label" for="logo_bg_4"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_5"
												value="color_5">
											<label class="form-check-label" for="logo_bg_5"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_6"
												value="color_6">
											<label class="form-check-label" for="logo_bg_6"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_7"
												value="color_7">
											<label class="form-check-label" for="logo_bg_7"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="logo_bg" id="logo_bg_8"
												value="color_8">
											<label class="form-check-label" for="logo_bg_8"></label>
										</div>
									</li>
									<li class="lyear-skin-title">
										<p>头部</p>
									</li>
									<li class="lyear-skin-li clearfix">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="header_bg"
												id="header_bg_1" value="default" checked="checked">
											<label class="form-check-label" for="header_bg_1"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="header_bg"
												id="header_bg_2" value="color_2">
											<label class="form-check-label" for="header_bg_2"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="header_bg"
												id="header_bg_3" value="color_3">
											<label class="form-check-label" for="header_bg_3"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="header_bg"
												id="header_bg_4" value="color_4">
											<label class="form-check-label" for="header_bg_4"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="header_bg"
												id="header_bg_5" value="color_5">
											<label class="form-check-label" for="header_bg_5"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="header_bg"
												id="header_bg_6" value="color_6">
											<label class="form-check-label" for="header_bg_6"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="header_bg"
												id="header_bg_7" value="color_7">
											<label class="form-check-label" for="header_bg_7"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="header_bg"
												id="header_bg_8" value="color_8">
											<label class="form-check-label" for="header_bg_8"></label>
										</div>
									</li>
									<li class="lyear-skin-title">
										<p>侧边栏</p>
									</li>
									<li class="lyear-skin-li clearfix">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="sidebar_bg"
												id="sidebar_bg_1" value="default" checked="checked">
											<label class="form-check-label" for="sidebar_bg_1"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="sidebar_bg"
												id="sidebar_bg_2" value="color_2">
											<label class="form-check-label" for="sidebar_bg_2"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="sidebar_bg"
												id="sidebar_bg_3" value="color_3">
											<label class="form-check-label" for="sidebar_bg_3"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="sidebar_bg"
												id="sidebar_bg_4" value="color_4">
											<label class="form-check-label" for="sidebar_bg_4"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="sidebar_bg"
												id="sidebar_bg_5" value="color_5">
											<label class="form-check-label" for="sidebar_bg_5"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="sidebar_bg"
												id="sidebar_bg_6" value="color_6">
											<label class="form-check-label" for="sidebar_bg_6"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="sidebar_bg"
												id="sidebar_bg_7" value="color_7">
											<label class="form-check-label" for="sidebar_bg_7"></label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="sidebar_bg"
												id="sidebar_bg_8" value="color_8">
											<label class="form-check-label" for="sidebar_bg_8"></label>
										</div>
									</li>
								</ul>
							</li>
							<!--End 切换主题配色-->
							<!--个人头像内容-->
							<li class="dropdown">
								<a href="javascript:void(0)" data-bs-toggle="dropdown" class="dropdown-toggle">
									<span style="margin-left: 10px;"> 数据备份同步 </span>
								</a>
							</li>
							<!--End 个人头像内容-->
						</ul>

					</nav>

				</header>
				<!--End 头部信息-->

				<!--页面主要内容-->
				<main class="lyear-layout-content">

					<div id="iframe-content"></div>

				</main>
				<!--End 页面主要内容-->
			</div>
		</div>
		<#include "common/common_js.ftl">
		<script type="text/javascript" src="/js/perfect-scrollbar.min.js"></script>
		<script type="text/javascript" src="/js/bootstrap-multitabs/multitabs.min.js"></script>
		<script type="text/javascript" src="/js/jquery.cookie.min.js"></script>
		<script type="text/javascript" src="/js/index.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(e) {});
		</script>
	</body>
</html>
