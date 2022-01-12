<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>

        <div id="center">
            <h2>通報一覧</h2>
            <table class="reports_list">
                <tbody>
                    <tr>
                        <th class="report_no">No.</th>
                        <th class="report">通報理由</th>
                        <th class="report">通報日時</th>
                        <th class="report">通報ユーザー</th>
                        <th class="report_action"></th>
                    </tr>
                    <c:forEach var="report" items="${reports}" varStatus="status">
                        <tr>
                            <td class="report_no"><c:out value="${status.count}" /></td>
                            <td class="report"><c:out value="${report.reason}" /></td>
                            <td class="report"><c:out value="${report.created_at}" /></td>
                            <td class="report"><c:out value="${report.report_user}"></c:out></td>
                            <td class="report_action">
                                <a href="<c:url value='/admin/report/show?emoticon_id=${report.emoticon_id}' />">詳細</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div id="pagination">
                （全 ${reports_count} 件）<br />
                <c:forEach var="i" begin="1" end="${((reports_count - 1) / 20) + 1}"
                    step="1">
                    <c:choose>
                        <c:when test="${i == page}">
                            <c:out value="${i}" />&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value='/admin/report/index?page=${i}' />"><c:out
                                    value="${i}" /></a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <br /> <br />
        </div>

    </c:param>
</c:import>