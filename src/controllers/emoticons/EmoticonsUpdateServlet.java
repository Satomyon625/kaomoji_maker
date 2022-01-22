package controllers.emoticons;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Emoticon;
import models.validators.EmoticonValidator;
import utils.DBUtil;

/**
 * Servlet implementation class EmoticonsUpdateServlet
 */
@WebServlet("/user/emoticons/update")
public class EmoticonsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmoticonsUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Emoticon e = em.find(Emoticon.class, (Integer)(request.getSession().getAttribute("emoticon_id")));

            e.setEmoticon(request.getParameter("emoticon"));
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setUpdated_at(currentTime);

            List<String> errors = EmoticonValidator.validate(e);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("emoticon", e);
                request.setAttribute("errors", errors);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/emoticons/edit.jsp");
                rd.forward(request, response);

            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                String n = request.getParameter("emoticon");
                request.getSession().setAttribute("new_emoticon",n);//登録完了画面に登録した顔文字を表示させる
                response.sendRedirect(request.getContextPath() + "/user/emoticons/completion");
        }
    }

}
}
