package controllers.admin;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Emoticon;
import models.Report;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class ReportSaveServlet
 */
@WebServlet("/admin/report/save")
public class ReportSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportSaveServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 顔文字ID
        int emoticonId;
        try {
            emoticonId = Integer.parseInt(request.getParameter("emoticon_id"));
        } catch(Exception e) {
            request.getSession().setAttribute("flush", "通報情報の更新に失敗しました。");
            response.sendRedirect(request.getContextPath() + "/admin/report/index");
            return;
        }
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            em.getTransaction().begin();
            // 顔文字情報を取得
            Emoticon emoticon = em.find(Emoticon.class, emoticonId);

            /** 顔文字情報の更新 */
            emoticon.setEmoticon(request.getParameter("emoticon"));
            String deleteFlag = request.getParameter("delete_flag");
            if (deleteFlag == null) {
                emoticon.setDelete_flag(false);
            } else {
                emoticon.setDelete_flag(true);
            }


            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            User loginUser = (User)request.getSession().getAttribute("login_user");
            emoticon.setUpdated_at(currentTime);
            emoticon.setUpdated_user(loginUser);
            em.persist(emoticon);

            /** 通報情報の更新 */
            String[] reportIds = request.getParameterValues("report_id");
            String[] dealReportIds = request.getParameterValues("deal_flag");
            Map<String, Boolean> dealReportIdMap = new HashMap<String, Boolean>();
            if (dealReportIds != null) {
                for (String dealReportId:dealReportIds) {
                    dealReportIdMap.put(dealReportId, true);
                }
            }
            for (String reportId:reportIds) {
                int id = Integer.parseInt(reportId);
                Report report = em.find(Report.class, id);
                report.setDeal_flag(dealReportIdMap.containsKey(reportId));
                report.setUpdated_at(currentTime);
                report.setUpdate_user(loginUser.getU_name());
                em.persist(report);
            }
            em.getTransaction().commit();
            em.close();


            // フラッシュメッセージがセッションスコープにセットされていたら
            // リクエストスコープに保存する（セッションスコープからは削除）
            if (request.getSession().getAttribute("flush") != null) {
                request.setAttribute("flush", request.getSession().getAttribute("flush"));
                request.getSession().removeAttribute("flush");
            }
            request.getSession().setAttribute("flush", "通報情報を正常に更新しました。");
            response.sendRedirect(request.getContextPath() + "/admin/report/index");

        } else {
            em.close();
            request.getSession().setAttribute("flush", "通報情報の更新に失敗しました。");
            response.sendRedirect(request.getContextPath() + "/admin/report/index");

        }
    }

}
