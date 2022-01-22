package controllers.emoticons;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Emoticon;
import utils.DBUtil;

/**
 * Servlet implementation class EmoticonsDestroyServlet
 */
@WebServlet("/user/emoticons/destroy")
public class EmoticonsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmoticonsDestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token =request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Emoticon e = em.find(Emoticon.class, (Integer)(request.getSession().getAttribute("emoticon_id")));
            e.setDelete_flag(true);
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "削除が完了しました。");

            response.sendRedirect(request.getContextPath() + "/user/mypage/index");
        }
    }

}
