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
                            <td><input type="text" name="u_name" size="40" maxlength="16" value="${user.u_name}" required/></td>
                            <td>※16文字まで(他ユーザとの重複不可)</td>
                        </tr>
                        <tr>
                            <th><label for="pass">パスワード</label></th>
                            <td><input type="password" name="pass" id="pass" size="40" minlength="8" maxlength="20" required/></td>
                            <td>※半角英数字8文字以上20文字まで</td>
                        </tr>
                        <tr>
                            <th><label for="c_pass">パスワード（確認用）</label></th>
                            <td><input type="password" name="c_pass" id="c_pass" size="40" minlength="8" maxlength="20" oninput="CheckPass(this)" required/></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>

                <script  type="text/javascript">
                function CheckPass(input){
                    var pass = document.getElementById("pass").value;
                    var c_pass = input.value;
                    if(pass != c_pass){
                        input.setCustomValidity('パスワードが一致しません。');
                    }else{
                        input.setCustomValidity('');
                    }
                }
                </script>

        <input type="hidden" name="_token" value="${_token}"/><button type="submit">登録する</button>
