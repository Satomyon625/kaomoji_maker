package controllers.toppage;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Like;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/user/like")
public class LikeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String likeId = request.getParameter("like");
            EntityManager em = DBUtil.createEntityManager();

            Like l = new Like();

            l.setEmoticon_id(Integer.parseInt(likeId));
            User user = (User)request.getSession().getAttribute("login_user");

            l.setLike_user(user.getU_name());

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            l.setCreated_at(currentTime);

            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();

            em.close();

            request.getSession().setAttribute("flush", "いいねしました。");
            response.sendRedirect(request.getContextPath() + "/top");

}
}