<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <div id= "center">
            <h2>いいねした顔文字</h2>
                <c:forEach var="emoticon" items="${emoticons}" varStatus="status">
               <p class="list_line">
                <table class="emoticon_list">
                    <tbody>
                        <tr class="row${status.count % 2}">
                            <th class="emoticon" id="emoticon">${emoticon.emoticon}</th> <!-- 顔文字を表示 -->
                            <td class="emoticon_action">
                            </td>
                            <td class="emoticon_action">
                                <input type="button" class="copy_b" id ="copy" value="コピー" onclick="ClickBtn_c('<c:out value='${emoticon.emoticon}' />');saveCopy('<c:out value='${emoticon.id}' />');" />
                            </td>
                            <td class="emoticon_action">
                            </td>
                        </tr>
                    </tbody>
                </table>
                <hr class="hr1">
               </c:forEach>
        </div>
        <div id="pagination">
            （全 ${emoticons_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((emoticons_count - 1) / 20) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/user/mypage/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <br /><br/>

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

    </c:param>
</c:import>