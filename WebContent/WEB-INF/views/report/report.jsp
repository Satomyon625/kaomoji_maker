<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
<c:param name="content">
    <c:choose>
            <c:when test="${emoticon != null }">
    <div id="center">
        <h2>顔文字を通報</h2>
        <p>通報理由の記入をお願いします。</p>
        <p>こちらの顔文字に対する対処のご参考にさせていただきますm(_ _)m</p>
        <c:out value="${emoticon.emoticon}" />
        <br/><br/>
        <form method="POST" action="<c:url value='/user/report' />">
        <c:import url="_form.jsp" />

        </form>
    </div>
    </c:when>
    <c:otherwise>
    <div id="center">
        <h2>お探しのデータは見つかりませんでした。</h2>
        </div>
    </c:otherwise>
    </c:choose>
    <div id="center">
     <p><a href="<c:url value='/top'/>">顔文字一覧に戻る</a></p>
    </div>
</c:param>
</c:import>