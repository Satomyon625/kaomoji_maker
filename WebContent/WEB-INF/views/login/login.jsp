<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${hasError }">
            <div id="flush_error">
                ユーザー名かパスワードが間違っています。
            </div>
        </c:if>
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>
       <div id= "center">
        <h2>ログイン</h2>
         <form method="post" action="<c:url value='/login' />">
            <table>
                <tbody>
                    <tr>
                        <th><label for="u_name">ユーザー名</label></th>
                        <td><input type="text" name="u_name" size="40" maxlength="16" value="${u_name}" required/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><label for="pass">パスワード</label></th>
                        <td><input type="password" name="pass" size="40" maxlength="20" required/></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">ログイン</button>
        </form>
            <p>アカウントをお持ちでない方は...↓</p>
            <p><a href ="<c:url value='/newuser' />"><input type="submit" value="新規アカウント作成"></a></p>
        </div>
    </c:param>
</c:import>