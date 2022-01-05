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
               <br /><br />

               <c:forEach var="emoticon" items="${emoticons}" varStatus="status">
               <p class="list_line">
                <table class="emoticon_list">
                    <tbody>
                        <tr class="row${status.count % 2}">
                            <th class="emoticon" id="emoticon">${emoticon.emoticon}</th> <!-- 顔文字を表示 -->
                            <td class="emoticon_action"></td>
                            <td class="emoticon_action">
                            <c:if test="${sessionScope.login_user != null}">
                                <input type="button" class="like_b" value="いいね" onclick="clickBtn_l()" />&nbsp;
                            </c:if>
                            <input type="button" class="copy_b" id ="copy" value="コピー" onclick="ClickBtn_c('<c:out value='${emoticon.emoticon}' />')" />&nbsp;
                            <c:if test="${sessionScope.login_user != null}">
                                <button class="report_b" onclick="location.href='<c:url value='/user/report' />'">通報</button>
                            </c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <hr class="hr1">
               </c:forEach>

        <script>
        function ClickBtn_c(str) {
            var listener = function(e){

                e.clipboardData.setData("text/plain" , str);
                // 本来のイベントをキャンセル
                e.preventDefault();
                // 終わったら一応削除
                document.removeEventListener("copy", listener);
            }

            // コピーのイベントが発生したときに、クリップボードに書き込むようにしておく
            document.addEventListener("copy" , listener);

            // コピー
            document.execCommand("copy");

            }
        </script>

        <div id="pagination">
            （全 ${emoticons_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((emoticons_count - 1) / 20) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/top?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <br /><br/>
        </div>

    </c:param>
</c:import>