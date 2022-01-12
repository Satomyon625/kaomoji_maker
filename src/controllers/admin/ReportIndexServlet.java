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

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportIndexServlet
 */
@WebServlet("/admin/report/index")
public class ReportIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportIndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        // ページネーション
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        // 全ての顔文字を取得
        List<Report> reports = em.createNamedQuery("getAllReportsByNotDeal_flag", Report.class)
                .setFirstResult(20 * (page - 1))
                .setMaxResults(20)
                .getResultList();

        long reports_count = (long)em.createNamedQuery("getAllReportsCountByNotDeal_flag", Long.class)
                                      .getSingleResult();

        em.close();

        request.setAttribute("searchKeyword", "");
        request.setAttribute("reportsSort", "");

        request.setAttribute("reports", reports);
        request.setAttribute("reports_count", reports_count);     // 全件数
        request.setAttribute("page", page);                   // ページ数

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/admin/report_index.jsp");
        rd.forward(request, response);
    }

}
