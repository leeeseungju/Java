<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>프로젝트</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/index.css" />
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>프로젝트</h1>
            <p>환영합니다! 원하시는 서비스를 선택해주세요.</p>
        </div>
        
        <div class="nav-grid">
            <a href="join" class="nav-item">
                <div class="nav-content">
                    <span class="nav-icon join-icon"></span>
                    <div class="nav-title">회원 가입</div>
                    <div class="nav-description">새로운 계정을 만들어보세요</div>
                </div>
            </a>
            
            <a href="login" class="nav-item">
                <div class="nav-content">
                    <span class="nav-icon login-icon"></span>
                    <div class="nav-title">로그인</div>
                    <div class="nav-description">기존 계정으로 로그인하세요</div>
                </div>
            </a>
            
            <a href="user_list" class="nav-item">
                <div class="nav-content">
                    <span class="nav-icon list-icon"></span>
                    <div class="nav-title">회원 리스트</div>
                    <div class="nav-description">전체 회원 정보를 확인하세요</div>
                </div>
            </a>
            
            <a href="mypage" class="nav-item">
                <div class="nav-content">
                    <span class="nav-icon mypage-icon"></span>
                    <div class="nav-title">마이 페이지</div>
                    <div class="nav-description">내 정보를 관리하세요</div>
                </div>
            </a>

    </div>
</body>
</html>