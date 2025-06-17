<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/join.css" />
</head>
<body>
    <div class="container">
        <h1>회원가입</h1>
        <form action="join_action" method="post" style="background:none; border:none; padding:0; width:100%;">
            <div class="form-group">
                <input type="text" name="name" placeholder="이름을 입력해주세요" required />
            </div>
            <div class="form-group">
                <input type="text" name="id" placeholder="아이디를 입력해주세요" required />
            </div>
            <div class="form-group">
                <input type="password" name="pwd" placeholder="비밀번호를 입력해주세요" required />
            </div>
            <div class="form-group">
                <input type="password" name="pwd2" placeholder="비밀번호를 다시 입력해주세요" required />
            </div>
            <div class="form-group">
                <input type="tel" name="phone" placeholder="연락처를 입력해주세요" required />
            </div>
            <div class="form-group">
                <input type="text" name="address" placeholder="주소를 입력해주세요" required />
            </div>
            
            <button type="submit" class="submit-btn">가입하기</button>
        </form>
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/">메인화면</a>
        </div>
    </div>
</body>
</html>