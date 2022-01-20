package controllers.report;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ReportServlet
 */
@WebServlet("/user/report")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Emoticon e = em.find(Emoticon.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.getSession().setAttribute("emoticon_id", e.getId());
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("emoticon", e);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/report/report.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Emoticon e = em.find(Emoticon.class, (Integer)(request.getSession().getAttribute("emoticon_id")));

            Integer id = (Integer)request.getSession().getAttribute("emoticon_id");//顔文字idを取得
            User user = (User)request.getSession().getAttribute("login_user");//ログインユーザー（通報者）
            String r_user = user.getU_name();
            user = e.getCreate_user();//作成したユーザー
            String create_user = user.getU_name();//UserからStringへ変換
            user = e.getUpdated_user();
            String updated_user = user.getU_name();

            Report r = new Report();//通報テーブルに書き込み
            r.setEmoticon_id(id);
            r.setReport_user(r_user);
            r.setCreate_user(create_user);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setCreated_at(currentTime);
            r.setUpdate_user(updated_user);
            r.setUpdated_at(currentTime);
            r.setDeal_flag(false);
            r.setReason(request.getParameter("report"));

            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "通報が完了しました。");
            response.sendRedirect(request.getContextPath() + "/top");
    }
    }

}
