<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div id="center">
        <h2>顔文字の登録が完了しました！</h2><br />
        <c:if test="${new_emoticon != null }">
            <div id="emoticon_success">
                <h2><c:out value="${new_emoticon }"></c:out></h2>
            </div>
        </c:if>
        <br /><br />
        <button onclick="location.href='<c:url value='/user/emoticons/new' />'">新たに顔文字追加</button><br/><br/>
        <button class="tweet_b" style="color:white" onclick="ClickBtn_t('<c:out value='${new_emoticon}' />')" >Twitterにシェアする！</button>
         </div>
    </c:param>
</c:import>

<script>
function ClickBtn_t(str){
    document.location.href = "https://twitter.com/intent/tweet?";
    const baseUrl = 'https://twitter.com/intent/tweet?';
    const text = ['text', '顔文字を作成したよ！ 作成した顔文字 ->',str];
    const hashtags = ['hashtags', ['顔文字メーカー'].join(',')];
    const url = ['url', location.href];
    const via = ['via', 'tos'];
    const query = new URLSearchParams([text, hashtags, url, via]).toString();
    const shareUrl = '${baseUrl}${query}';
}
</script>