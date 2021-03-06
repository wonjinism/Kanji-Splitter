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
		
		function inputSplit(tag){
			var currentSplit = document.getElementById("kanjiSplit").value;
			var inputSplit = tag.innerHTML;
			document.getElementById("kanjiSplit").value = currentSplit + inputSplit;
		}
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
	<div class="main">
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
							</ul>
						</div>
					</div>
					<div class="right_card col-md-7">
						<div class="card">
							<ul class="list-group list-group-flush">
								<li class="story list-group-item">
									<span class="school badge badge-info">암기스토리</span><textarea id="kanjiStory" class="form-control" rows="3">${kanji.story}</textarea>
								</li>
								<li class="split list-group-item">
									<span class="school badge badge-info">구성요소</span><input id="kanjiSplit" type="text" class="form-control" value="${kanji.split}">
								</li>
							</ul>
						</div>
						<div class="card">
							<!-- //// -->
							<div class="row">
							  <div class="radical-tab col-3">
							    <div class="list-group" id="list-tab" role="tablist">
							      <a class="list-group-item list-group-item-action active" id="list-01-list" data-toggle="list" href="#list-01" role="tab" aria-controls="01">1 - 2</a>
							      <a class="list-group-item list-group-item-action" id="list-02-list" data-toggle="list" href="#list-02" role="tab" aria-controls="02">3 (1)</a>
							      <a class="list-group-item list-group-item-action" id="list-03-list" data-toggle="list" href="#list-03" role="tab" aria-controls="03">3 (2)</a>
							      <a class="list-group-item list-group-item-action" id="list-04-list" data-toggle="list" href="#list-04" role="tab" aria-controls="04">4 (1)</a>
							      <a class="list-group-item list-group-item-action" id="list-05-list" data-toggle="list" href="#list-05" role="tab" aria-controls="05">4 (2)</a>
							      <a class="list-group-item list-group-item-action" id="list-06-list" data-toggle="list" href="#list-06" role="tab" aria-controls="06">6</a>
							      <a class="list-group-item list-group-item-action" id="list-07-list" data-toggle="list" href="#list-07" role="tab" aria-controls="07">7 - 8</a>
							      <a class="list-group-item list-group-item-action" id="list-08-list" data-toggle="list" href="#list-08" role="tab" aria-controls="08">9 - 17</a>
							    </div>
							  </div>
							  <div class="radical-list col-9">
							    <div class="tab-content" id="nav-tabContent">
							      <div class="tab-pane fade show active" id="list-01" role="tabpanel" aria-labelledby="list-01-list">
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="점주">丶</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="한일">一</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="흐를이">乁</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="새을">乙</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="숨을은">乚</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="구결자야">乛</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="파임불">乀</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="삐침별">丿</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="갈고리궐">亅</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="뚫을곤">丨</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="감출혜몸">匸</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="구결자하">丷</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="아래하">丅</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="쌀포몸">勹</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="여덟팔">八</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="일곱칠">七</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="병부절">卩</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="병부절">㔾</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="들입">入</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="어진사람인발">儿</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="사람인변">亻</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="사람인">人</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="두이">二</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="또우">又</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="민엄호">厂</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="말씀언2">讠</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="열십">十</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="윗상">丄</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="마늘모">厶</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="이수변">冫</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="비수비">匕</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="점복">卜</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="튼입구몸">匚</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="구결자면">丆</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="민갓머리">冖</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="마칠료">了</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="힘력">力</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="돼지해머리">亠</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="선칼도방">刂</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="칼도">刀</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="이에내">乃</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="안석궤">几</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="얽힐구">丩</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="공교할교">丂</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="큰도랑괴">巜</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="멀경몸">冂</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="위튼입구몸">凵</button>
							      </div>
							      <div class="tab-pane fade" id="list-02" role="tabpanel" aria-labelledby="list-02-list">
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="둥글환">丸</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="구결자호">乊</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="시골향">乡</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="아래하">下</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="흙토">土</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="부탁할탁">乇</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="뒤져올치">夂</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="마디촌">寸</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="자축거릴촉">亍</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="풀초">䒑</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="개미허리">巛</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="내천">川</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="일천천">千</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="두인변">彳</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="책받침">辶</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="갈래차">叉</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="삼합집">亼</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="병부주">㔿</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="왼손좌">屮</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="칼도">刁</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="장정정">丁</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="병부절">卪</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="재주재">才</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="장수장변2">丬</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="어른장">丈</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="구기작">勺</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="아들자">子</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="민책받침">廴</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="칼날인">刄</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="칼날인">刃</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="주살익">弋</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="땅이름울">亐</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="이지러질휴">亏</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="이지러질휴">于</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="작을요">么</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="작을요">幺</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="절름발이왕">尢</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="우뚝할올">兀</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="다섯오">㐅</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="벨예">乂</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="엄호">广</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="잇달을이">也</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="가닥아">丫</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="심방변">忄</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="빨리날신">卂</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="밥식2">饣</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="주검시엄">尸</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="익힐습">习</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="삼수변">氵</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="재방변">扌</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="천천히걸을쇠발">夊</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="작을소">小</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="저녁석">夕</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="윗상">上</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="터럭삼">彡</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="석삼">三</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="뫼산">山</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="뱀사">巳</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="선비사">士</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="날비2">飞</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="우부방">阝</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="무릇범">凢</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="무릇범">凡</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="문문2">门</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="갓머리">宀</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="실사2">纟</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="망할망">兦</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="망할망">亾</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="망할망">亡</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="일만만">万</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="말마2">马</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="망치마">亇</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="구결자라">亽</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="큰대">大</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="계집녀">女</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="책상기">丌</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="몸기">己</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="활궁">弓</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="큰입구몸">囗</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="입구">口</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="아홉구">九</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="오랠구">久</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="걸을과">㐄</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="스물입발">廾</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="장인공">工</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="튼가로왈">彑</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="튼가로왈">彐</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="개사슴록변">犭</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="빌걸">乞</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="수건건">巾</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="방패간">干</button>
									<button type="button" class="btn btn-secondary" data-toggle="tooltip" data-placement="bottom" onclick="inputSplit(this)" title="장인공">コ</button>
							      </div>
							      <div class="tab-pane fade" id="list-03" role="tabpanel" aria-labelledby="list-03-list">03</div>
							      <div class="tab-pane fade" id="list-04" role="tabpanel" aria-labelledby="list-04-list">04</div>
							      <div class="tab-pane fade" id="list-05" role="tabpanel" aria-labelledby="list-05-list">05</div>
							      <div class="tab-pane fade" id="list-06" role="tabpanel" aria-labelledby="list-06-list">06</div>
							      <div class="tab-pane fade" id="list-07" role="tabpanel" aria-labelledby="list-07-list">07</div>
							      <div class="tab-pane fade" id="list-08" role="tabpanel" aria-labelledby="list-08-list">08</div>
							    </div>
							  </div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 text-center">
						<a class="updateKanjiBtn btn btn-primary btn-lg btn-block" href="" role="button">수정 완료</a>
					</div>
				</div>
			</div>
		</section>
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
</body>
</html>
