<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="center">
        <h2>アカウント登録</h2>
                <table>
                    <tbody>
                        <tr>
                            <th>ユーザー名</th>
                            <td><input type="text" name="user" id="unm" size="40" maxlength="16"></td>
                        </tr>
                        <tr>
                            <th>パスワード</th>
                            <td><input type="password" name="pass" id="pwd" size="40" maxlength="20"></td>
                        </tr>
                        <tr>
                            <th>パスワード（確認用）</th>
                            <td><input type="password" name="pass" id="pwd" size="40" maxlength="20"></td>
                        </tr>
                    </tbody>
                </table>

        <p><input type="submit" value="登録する"></p>
         </div>
    </c:param>
</c:import>