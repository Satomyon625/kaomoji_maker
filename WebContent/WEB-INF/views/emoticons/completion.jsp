<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="center">
        <h3>顔文字の登録が完了しました！</h3><br />
        <c:if test="${new_emoticon != null }">
            <div id="emoticon_success">
                <c:out value="${new_emoticon }"></c:out>
            </div>
        </c:if>
        <br /><br />
        <button onclick="location.href='<c:url value='/user/emoticons/new' />'">新たに顔文字追加</button>
         </div>
    </c:param>
</c:import>