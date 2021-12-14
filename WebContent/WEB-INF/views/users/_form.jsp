<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>アカウント登録</h2>
       <p>
       ユーザー名 <input type="text" name="namae" size="40" maxlength="16"><br>
       パスワード <input type="password" name="pass" size="40" maxlength="20"><br>
       パスワード（確認用） <input type="password" name="pass" size="40" maxlength="20">
       </p>
        <p><input type="submit" value="送信する"></p>

    </c:param>
</c:import>