<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 목록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user_list.css" />
    <style>
       
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>회원 목록</h2>
            <p>관리자 전용 - 전체 회원 정보를 확인할 수 있습니다</p>
        </div>
        
        <div class="stats">
            <div class="stat-item">
                <span class="stat-number">${userList.size()}</span>
                <span class="stat-label">전체 회원</span>
            </div>
        </div>
        
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>아이디</th>
                        <th>이름</th>
                        <th>연락처</th>
                        <th>주소</th>
                        <th>가입일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td class="user-id">${user.id}</td>
                            <td class="user-name">${user.name}</td>
                            <td class="user-phone">${user.phone}</td>
                            <td class="user-address" title="${user.address}">${user.address}</td>
                            <td class="user-date">${user.created}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
        <div class="actions">
            <a href="${pageContext.request.contextPath}/" class="btn">메인으로</a>
        </div>
    </div>
</body>
</html>
