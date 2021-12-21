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

                <table>
                    <tbody>
                        <tr>
                            <th><label for="u_name">ユーザー名</label></th>
                            <td><input type="text" name="u_name" size="40" maxlength="16" value="${user.u_name}" /></td>
                        </tr>
                        <tr>
                            <th><label for="pass">パスワード</label></th>
                            <td><input type="password" name="pass" size="40" maxlength="20" /></td>
                        </tr>
                        <tr>
                            <th><label for="pass">パスワード（確認用）</label></th>
                            <td><input type="password" name="pass"  size="40" maxlength="20" /></td>
                        </tr>
                    </tbody>
                </table>

        <input type="hidden" name="_token" value="${_token}"/><button type="submit">登録する</button>
