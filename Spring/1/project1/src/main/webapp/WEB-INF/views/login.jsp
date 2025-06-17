<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/login.css" />
</head>
<body>
    <div class="container">
        <div class="header">
            <span class="login-icon">🔐</span>
            <h1>로그인</h1>
            <p>계정에 로그인하세요</p>
        </div>
        
        <form action="login_action" method="post" style="background:none; border:none; padding:0; width:100%; box-sizing:border-box;">
            <div class="form-group">
                <input type="text" name="id" placeholder="아이디를 입력해주세요" required />
            </div>
            
            <div class="form-group">
                <input type="password" name="pwd" placeholder="비밀번호를 입력해주세요" required />
            </div>
            
            <button type="submit" class="submit-btn">로그인</button>
        </form>
        
        <div class="links">
            <a href="${pageContext.request.contextPath}/join">회원가입</a>
            <a href="${pageContext.request.contextPath}/">메인으로</a>
        </div>
    </div>
</body>
</html>