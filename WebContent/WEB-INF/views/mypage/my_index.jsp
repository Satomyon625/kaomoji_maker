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
            <h2>自分が登録した顔文字</h2>
                <c:forEach var="emoticon" items="${emoticons}" varStatus="status">
               <p class="list_line">
                <table class="emoticon_list">
                    <tbody>
                        <tr class="row${status.count % 2}">
                            <th class="emoticon" id="emoticon">${emoticon.emoticon}</th> <!-- 顔文字を表示 -->
                            <td class="emoticon_action">
                            </td>
                            <td class="emoticon_action">
                            <button class="edit_b" onclick="location.href='<c:url value='/user/emoticons/edit?id=${emoticon.id}' />'">編集</button>
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

    </c:param>
</c:import>