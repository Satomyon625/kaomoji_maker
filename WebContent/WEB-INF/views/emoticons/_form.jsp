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

<button type="submit">登録</button><br />
<p><span style="color:red">注意：文字化けの恐れがあるため、環境依存文字のご使用はお控えいただきますようお願いしますm(_ _)m</span></p>
<br /><br />

<label for="category">追加するカテゴリ↓</label><br />
<input id="happy" type="checkbox" id="嬉しい" name="category_c" value="1"<c:if test="${category.id == 1}">checked</c:if>><label for="嬉しい">嬉しい</label>
<input id="sad" type="checkbox" id="悲しい" name="category_c" value="2"<c:if test="${category.id == 2}">checked</c:if>><label for="悲しい">悲しい</label>
<input id="smile" type="checkbox" id="笑う" name="category_c" value="3"<c:if test="${category.id == 3}">checked</c:if>><label for="笑う">笑う</label>
<input id="cute" type="checkbox" id="可愛い" name="category_c" value="4"<c:if test="${category.id == 4}">checked</c:if>><label for="可愛い">可愛い</label>
<br />
<input id="angry" type="checkbox" id="怒る" name="category_c" value="5"<c:if test="${category.id == 5}">checked</c:if>><label for="怒る">怒る</label>
<input id="surprised" type="checkbox" id="驚く" name="category_c" value="6"<c:if test="${category.id == 6}">checked</c:if>><label for="驚く">驚く</label>
<input id="sleep" type="checkbox" id="寝る" name="category_c" value="7"<c:if test="${category.id == 7}">checked</c:if>><label for="寝る">寝る</label>
<input id="greeting" type="checkbox" id="挨拶" name="category_c" value="8"<c:if test="${category.id == 8}">checked</c:if>><label for="挨拶">挨拶</label>
<br /><br />

<label for="other_category">その他のカテゴリ(追加)※３つまで</label><br />
<input type="text" name="category" value="${tarnsaction.categry_id}">
<input type="text" name="category" value="${tarnsaction.categry_id}">
<input type="text" name="category" value="${tarnsaction.categry_id}">
<input type="hidden" name="_token" value="${_token}" />