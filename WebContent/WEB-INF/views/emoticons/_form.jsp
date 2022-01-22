<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

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
<% List<Integer> defaultCategories = (List)request.getAttribute("category_c");%>
    <input id="happy" type="checkbox" name="category_c" value="1"
        <% if(defaultCategories != null && defaultCategories.contains(1)) { %> checked <% } %>
    ><label for="happy">嬉しい</label>
    <input id="sad" type="checkbox" name="category_c" value="2"
        <% if(defaultCategories != null && defaultCategories.contains(2)) { %> checked <% } %>
    ><label for="sad">悲しい</label>
    <input id="smile" type="checkbox" name="category_c" value="3"
        <% if(defaultCategories != null && defaultCategories.contains(3)) { %> checked <% } %>
    ><label for="smile">笑う</label>
    <input id="cute" type="checkbox" name="category_c" value="4"
        <% if(defaultCategories != null && defaultCategories.contains(4)) { %> checked <% } %>
    ><label for="cute">可愛い</label>
    <br>
    <input id="angry" type="checkbox" name="category_c" value="5"
        <% if(defaultCategories != null && defaultCategories.contains(5)) { %> checked <% } %>
    ><label for="angry">怒る</label>
    <input id="surprised" type="checkbox" name="category_c" value="6"
        <% if(defaultCategories != null && defaultCategories.contains(6)) { %> checked <% } %>
    ><label for="surprised">驚く</label>
    <input id="sleep" type="checkbox" name="category_c" value="7"
        <% if(defaultCategories != null && defaultCategories.contains(7)) { %> checked <% } %>
    ><label for="sleep">寝る</label>
    <input id="greeting" type="checkbox" name="category_c" value="8"
        <% if(defaultCategories != null && defaultCategories.contains(8)) { %> checked <% } %>
    ><label for="greeting">挨拶</label>
<br /><br />

<label for="other_category">その他のカテゴリ(追加)※３つまで</label><br />
<c:forEach var="inputCategory" items="${inputCategories}">
  <input type="text" name="category" value="${inputCategory}">
</c:forEach>
<c:forEach var="i" begin="1" end="${n_enough}" step="1">
  <input type="text" name="category" >
</c:forEach>
<input type="hidden" name="_token" value="${_token}" />