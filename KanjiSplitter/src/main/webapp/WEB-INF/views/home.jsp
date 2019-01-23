<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Kanji Splitter</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" >
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<link href="resources/css/style.css" rel="stylesheet" type="text/css">
	<script>
		$(function () {
			$('[data-toggle="tooltip"]').tooltip()
		})
	</script>
</head>
<body>
	<div class="header">
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
			<a class="navbar-brand" href="home">Kanji Splitter</a>
			<form class="form-inline my-2 my-lg-0" action="split">
				<input class="form-control mr-sm-2" name="search" type="search" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</nav>
	</div>
	<font color="red">${warning}</font>
	<c:if test="${SearchResult != null}">
	<div class="main">
		<c:forEach var="kanji" items="${SearchResult}">
		<section>
			<div class="container">
				<div class="row">
					<div class="left_card col-md-5 text-center">
						<div class="card">
							<ul class="list-group list-group-flush">
								<li class="type list-group-item">
									<c:if test="${kanji.school > 0 && kanji.school < 7}">
										<span class="school badge badge-info">소학교${kanji.school}</span>
									</c:if>
									<c:if test="${kanji.school > 6}">
										<span class="school badge badge-info">중고교</span>
									</c:if>
									<c:if test="${kanji.jlpt > 0}">
										<span class="school badge badge-info">JLPT${kanji.jlpt}</span>
									</c:if>
									<span class="jlpt badge badge-info"></span>
									<span class="stroke badge badge-info">${kanji.stroke}획</span>
								</li>
								<li class="kanji list-group-item">${kanji.kanji}</li>
								<li class="korean list-group-item">${kanji.korean}</li>
								
								<li class="split list-group-item">
									<c:if test="${kanji.splitList == null}">
										<a class="btn btn-primary" href="updateKanji?kanji_id=${kanji.kanji_id}">분해 정보가 없습니다.</a>
									</c:if>
									<c:if test="${kanji.splitList != null}">
										<c:forEach var="splitKanji" items="${kanji.splitList}">
											<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" title="${splitKanji.korean}">${splitKanji.kanji}</button>
										</c:forEach>
										<a class="btn btn-primary" href="split?search=${kanji.split}" role="button">Split More</a>
									</c:if>
								</li>
							</ul>
						</div>
					</div>
					<div class="right_card col-md-7">
						<div class="card">
							<ul class="list-group list-group-flush">
								<li class="story list-group-item">
									<c:if test="${kanji.story == null}">
										<div style="text-align: center">
										<a class="btn btn-primary" href="updateKanji?kanji_id=${kanji.kanji_id}">메모 정보가 없습니다.</a>
										</div>
									</c:if>
									${kanji.story}
								</li>
								<li class="details list-group-item">
									<span class="badge badge-secondary">음독</span> ${kanji.furigana}<br />
									<span class="badge badge-secondary">훈독</span> ${kanji.imi}<br />
								</li>
								<li class="tango list-group-item"></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
		</c:forEach>
	</div>
	</c:if>
	<div class="footer">
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-bottom">
			<a class="navbar-brand" href="home">Kanji Splitter</a>
			<form class="form-inline my-2 my-lg-0" action="split">
				<input class="form-control mr-sm-2" name="search" type="search" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</nav>
	</div>
</body>

<%-- 
	<h1>로그인</h1>
	<form id="loginAction" action="loginUser" method="post">
		id:<input type="text" name="user_id" value="${user_id}"/><font color="red">${id_warning}</font><br />
		pw:<input type="password" name="user_password" /><font color="red">${pw_warning}</font><br />
		<input type="submit" value="Sign in"/>
	</form>
	<a href="register">회원가입</a>
 --%>
</html>
