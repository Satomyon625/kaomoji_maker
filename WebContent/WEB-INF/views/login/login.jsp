<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id= "center">
        <h2>ログイン</h2>
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
                </tbody>
            </table>

            <p><input type="submit" value="ログイン"></p>
            <p>アカウントをお持ちでない方は...↓</p>
            <p><input type="submit" value="新規アカウント作成"></p>
        </div>
    </c:param>
</c:import>