package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Emoticon;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportShowServlet
 */
@WebServlet("/admin/report/show")
public class ReportShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // 顔文字ID
        int emoticonId;
        try {
            emoticonId = Integer.parseInt(request.getParameter("emoticon_id"));
        } catch(Exception e) {
            request.getSession().setAttribute("flush", "システムエラーが発生しました。");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin/report_show.jsp");
            rd.forward(request, response);
            return;
        }

        // 顔文字情報を取得
        Emoticon emoticon = em.find(Emoticon.class, emoticonId);

        // 顔文字に紐づく通報情報を取得
        List<Report> reports = em.createNamedQuery("getReasonsOfEmoticon_id", Report.class)
                .setParameter("emoticon_id", emoticonId)
                .getResultList();

        long reports_count = (long) em.createNamedQuery("getReportsCountByEmoticon_id", Long.class)
                .setParameter("emoticon_id", emoticonId)
                .getSingleResult();

        em.close();
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("emoticon", emoticon);

        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count); // 全件数

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin/report_show.jsp");
        rd.forward(request, response);
    }

}
