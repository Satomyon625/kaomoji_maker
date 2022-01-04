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
<button type="submit">登録</button><br />
<p><span style="color:red">注意：文字化けの恐れがあるため、環境依存文字のご使用はお控えいただきますようお願いしますm(_ _)m</span></p>
<br /><br />

<label for="category">追加するカテゴリ↓</label><br />
<input id="happy" type="checkbox" name="category" value="嬉しい"><label for="happy">嬉しい</label>
<input id="sad" type="checkbox" name="category" value="悲しい"><label for="sad">悲しい</label>
<input id="smile" type="checkbox" name="category" value="笑う"><label for="smile">笑う</label>
<input id="cute" type="checkbox" name="category" value="可愛い"><label for="cute">可愛い</label>
<br />
<input id="angry" type="checkbox" name="category" value="怒る"><label for="angry">怒る</label>
<input id="surprised" type="checkbox" name="category" value="驚く"><label for="surprised">驚く</label>
<input id="sleep" type="checkbox" name="category" value="寝る"><label for="sleep">寝る</label>
<input id="greeting" type="checkbox" name="category" value="挨拶"><label for="greeting">挨拶</label>
<br /><br />

<label for="other_category">その他のカテゴリ(追加)</label><br />
<input type="text" name="other_category">&nbsp;
<input type="button" value="+ 追加" onclick="clickBtn1()" /><br /><br />
<div id="add1"></div>

<script>
function clickBtn1() {
    const add1 = document.getElementById("add1");
    if(!add1.hasChildNodes()){
        const input1 = document.createElement("input");
        input1.setAttribute("type", "text");
        input1.setAttribute("name", "other_category");
        add1.appendChild(input1);

        const input2 = document.createElement("input");
        input2.setAttribute("type", "button");
        input2.setAttribute("value", "+ 追加");
        input2.setAttribute("onclick", "clickBtn1()");
        add1.appendChild(input2);

        const input3 = document.createElement("br");
        add1.appendChild(input3);

        const input4 = document.createElement("br");
        add1.appendChild(input4);

        const input5 = document.createElement("div");
        input1.setAttribute("id", "add1");
        add1.appendChild(input5);
    }
}
</script>