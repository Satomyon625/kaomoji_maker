<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="emoticon">登録する顔文字を入力↓</label><br />
<input type="text" name="emoticon" value="${emoticon.emoticon}" size="60" required/>&nbsp;
<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>