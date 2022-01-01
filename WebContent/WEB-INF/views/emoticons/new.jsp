<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <div id="center">
            <h2>顔文字登録</h2>

                <form method="POST" action="<c:url value='/user/emoticons/create' />">
                    <c:import url="_form.jsp" />
                </form>
        </div>
    </c:param>
</c:import>