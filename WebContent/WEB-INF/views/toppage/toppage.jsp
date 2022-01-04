<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>

        <div id= "center">
            <h2>顔文字一覧</h2>
            <!-- 検索機能、別途フォーム用JSP作成無しで検証 -->
                <form method="POST" action="<c:url value='/top' />">
                   <label for="category_search">カテゴリ検索：</label>&nbsp;
                   <input type="search" autocomplete="on" list="list"/>
                   <!-- 検索候補 -->
                   <datalist id="list">
                    <option value="嬉しい">
                    <option value="悲しい">
                    <option value="笑う">
                    <option value="可愛い">
                    <option value="怒る">
                    <option value="驚く">
                    <option value="寝る">
                    <option value="挨拶">
                   </datalist>

                   <input type="hidden" name="_token" value="${_token}" />
                   <button type="submit">検索</button>&nbsp;

                <!-- 並べ替え表示 検証段階-->

                    <label for="emoticons_sort">並べ替え表示：</label>&nbsp;
                    <select name="emoticons_sort" onchange="submit(this.form)">
                        <option value="new">新着順</option>
                        <option value="old">古い順</option>
                        <option value="popular">人気順(累計コピー数)</option>
                        <option value="like">いいね順</option>
                    </select>
               </form>


        </div>

    </c:param>
</c:import>