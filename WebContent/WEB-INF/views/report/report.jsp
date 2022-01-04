<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
<c:param name="content">
    <div id="center">
        <h2>顔文字を通報</h2>
        <p>通報理由の記入をお願いします。</p>
        <p>こちらの顔文字に対する対処のご参考にさせていただきますm(_ _)m</p>
        <form method="post" action="<c:url value='/user/report' />">
            <input type="text" name="report" size="60" value="" required />
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">通報する</button>
        </form>
    </div>
</c:param>
</c:import>