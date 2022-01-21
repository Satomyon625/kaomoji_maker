<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>

        <div id= "center">
            <h2>顔文字一覧</h2>
            <!-- 検索機能 -->
            <div style="display:inline-flex">
                <form method="POST" id="search" name="name" action="<c:url value='/top' />">
                   <label for="category_search">カテゴリ検索：</label>&nbsp;
                   <input type="search" autocomplete="on" name="search_keyword" value="<c:out value='${searchKeyword}' />"/>
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


                   <button  name="search_keyword" onclick="submit(this.form)">検索</button>&nbsp;
                <!-- 並べ替え表示 -->
                    <label for="emoticons_sort">並べ替え表示：</label>&nbsp;
                    <select name="emoticons_sort" id = "emoticons_sort" onchange="submit(this.form)">
                        <option value="getAllEmoticons" <c:if test="${emoticonsSort == 'getAllEmoticons' || emoticonsSort == 'getEmoticonsByCategoryId'}">selected</c:if>>新着順</option>
                        <option value="getAllEmoticonsByOld" <c:if test="${emoticonsSort == 'getAllEmoticonsByOld' || emoticonsSort == 'getEmoticonsByCategoryIdAndOld'}">selected</c:if>>古い順</option>
                        <option value="getAllEmoticonsByCopy" <c:if test="${emoticonsSort == 'getAllEmoticonsByCopy' || emoticonsSort == 'getEmoticonsByCategoryIdAndCopy'}">selected</c:if>>人気順(累計コピー数)</option>
                        <option value="getAllEmoticonsByLike" <c:if test="${emoticonsSort == 'getAllEmoticonsByLike' || emoticonsSort == 'getEmoticonsByCategoryIdAndLike'}">selected</c:if>>いいね順</option>
                    </select>
               </form>
               </div>
               <br /><br />

               <c:forEach var="emoticon" items="${emoticons}" varStatus="status">
               <p class="list_line">
                <table class="emoticon_list">
                    <tbody>
                        <tr class="row${status.count % 2}">
                            <th class="emoticon" id="emoticon">${emoticon.emoticon}</th> <!-- 顔文字を表示 -->

                            <td class="emoticon_action">
                            <c:if test="${sessionScope.login_user != null}">
                                <form method="POST" id="like" name="like" action="<c:url value='/user/like' />">
                                <input type="hidden" name="like" value="${emoticon.id}" /><button type="submit" class="like_b" >いいね</button>&nbsp;
                                </form>
                            </c:if>
                            </td>
                            <td class="emoticon_action">
                            <input type="button" class="copy_b" id ="copy" value="コピー" onclick="ClickBtn_c('<c:out value='${emoticon.emoticon}' />');saveCopy('<c:out value='${emoticon.id}' />');" />&nbsp;
                            </td>
                            <td class="emoticon_action">
                            <c:if test="${sessionScope.login_user != null}">
                                <button class="report_b" onclick="location.href='<c:url value='/user/report?id=${emoticon.id}' />'">通報</button>
                            </c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <hr class="hr1">
               </c:forEach>

        <script>

        function ClickBtn_c(str) {//クリップボードにコピー
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

            function saveCopy(id){//コピー件数にカウントさせるための非同期通信,顔文字id指定
                let request = {
                    emoticon_id : id//顔文字のidセット

                };
                let message = null;//成功した時のメッセージ

                if(message != null) {
                    console.log(message);
                }
                //ajaxでservletにリクエストを送信
                $.ajax({
                  type    : "POST",
                  url     : "/kaomoji_maker/saveCopy",
                  data    : request,
                  async   : false,
                  dataType: "json"
                }).done(function(data, status, xhr) {
                  //通信が成功した場合に受け取るメッセージ
                    message = data["message"];
                }).fail(function(xhr, status, error) {
                  // エラーの場合処理
                  alert("エラーが発生しました。");
                });
                return message;
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