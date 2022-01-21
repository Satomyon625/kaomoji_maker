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
                    <h1>顔文字メーカー</h1>&nbsp;&nbsp;&nbsp;
                </div>
                <div id="header_menu" style="display:inline-flex">
                    <a href="<c:url value='/top' />">顔文字一覧</a>&nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_user != null}">
                    <a href="<c:url value='/user/emoticons/new' />">顔文字作成</a>&nbsp;
                    <select onChange="location.href=value;" name="mypage" id="mypage">
                        <option selected>マイページ</option>
                        <option value="<c:url value='/user/mypage/index' />">作成した顔文字</option>
                        <option value="<c:url value='/user/mypage/like' />">いいねした顔文字</option>
                    </select>&nbsp;
                    <a href="<c:url value='/logout' />">ログアウト</a>&nbsp;
                </c:if>
                <c:if test="${sessionScope.login_user == null}">
                    <a href="<c:url value='/login' />">ログイン</a>&nbsp;
                </c:if>
                </div>
            </div>

            <div id="content">
                <c:if test="${sessionScope.login_user != null }">
                    <div id="user_name">
                        ようこそ！&nbsp;<c:out value="${sessionScope.login_user.u_name }" />&nbsp;さん&nbsp;
                    </div>
                </c:if>
                ${param.content}
            </div>
            <div id="footer">
                by satomyo
                <br /><br />
            </div>
        </div>
    </body>
</html>