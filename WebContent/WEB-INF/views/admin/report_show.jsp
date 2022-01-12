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
            <h2>通報詳細</h2>
            <form action="<c:url value='/admin/report/save' />" method="POST">
                <table class="reports_detail">
                    <tbody>
                        <tr>
                            <th class="report">顔文字</th>
                            <td class="report"><input type="text" name="emoticon"
                                value='<c:out value="${emoticon.emoticon}"></c:out>' required />
                            </td>
                        </tr>
                        <tr>
                            <th class="report">登録者</th>
                            <td class="report"><c:out
                                    value="${emoticon.create_user.u_name}"></c:out></td>
                        </tr>
                        <tr>
                            <th class="report">登録日時</th>
                            <td class="report"><c:out value="${emoticon.created_at}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <th class="report">更新者</th>
                            <td class="report"><c:out
                                    value="${emoticon.updated_user.u_name}"></c:out></td>
                        </tr>
                        <tr>
                            <th class="report">更新日時</th>
                            <td class="report"><c:out value="${emoticon.updated_at}"></c:out>
                            </td>
                        </tr>
                        <tr>
                            <th class="report">削除可否</th>
                            <td class="report">
                                <p>
                                    <input type="checkbox" name="delete_flag" value="true"
                                        <c:if test="${emoticon.delete_flag}"> checked</c:if>>削除する
                                </p>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <table class="reports_list">
                    <tbody>
                        <tr>
                            <th class="report_no">No.</th>
                            <th class="report">通報理由</th>
                            <th class="report">通報日時</th>
                            <th class="report">通報ユーザー</th>
                            <th class="report">対応済み</th>
                        </tr>
                        <c:forEach var="report" items="${reports}" varStatus="status">
                            <tr>
                                <td class="report_no"><c:out value="${status.count}" /></td>
                                <td class="report"><c:out value="${report.reason}" /></td>
                                <td class="report"><c:out value="${report.created_at}" /></td>
                                <td class="report"><c:out value="${report.report_user}" /></td>
                                <td class="report">
                                    <input type="checkbox" name="deal_flag"
                                        value="<c:out value='${report.id}' />"
                                        <c:if test="${report.deal_flag}"> checked</c:if> />
                                    <input type="hidden" name="report_id" value="<c:out value='${report.id}' />" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br />
                （全 ${reports_count} 件）
                <input type="hidden" name="emoticon_id" value="${emoticon.id}" />
                <input type="hidden" name="_token" value="${_token}" />
                <br />
                <a href="<c:url value='/admin/report/index' />">通報一覧に戻る</a>
                <button type="submit">保存</button>
                <br /> <br />
            </form>
        </div>

    </c:param>
</c:import>