<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>顔文字メーカー</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
            <div id="header_title">
             <h1>顔文字メーカー</h1>
             </div>
                <div id="header_menu">
                    <a>顔文字一覧</a>
                </div>
            </div>

            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                ©️satomyo
            </div>
        </div>
    </body>
</html>