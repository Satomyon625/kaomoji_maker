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
import models.User;
import models.validators.EmoticonValidator;
import utils.DBUtil;

/**
 * Servlet implementation class EmoticonsCreateServlet
 */
@WebServlet("/user/emoticons/create")
public class EmoticonsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmoticonsCreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");//作成画面から遷移以外は不正アクセス
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Emoticon e = new Emoticon();

            e.setCreate_user((User)request.getSession().getAttribute("login_user"));
            e.setUpdated_user((User)request.getSession().getAttribute("login_user"));

            e.setEmoticon(request.getParameter("emoticon"));
            e.setCopy_number(0);
            e.setLike_number(0);
            e.setDelete_flag(false);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setCreated_at(currentTime);
            e.setUpdated_at(currentTime);

            List<String> errors = EmoticonValidator.validate(e);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("emoticon", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/emoticons/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                em.close();

                String n = request.getParameter("emoticon");
                request.getSession().setAttribute("new_emoticon",n);//登録完了画面に登録した顔文字を表示させる
                response.sendRedirect(request.getContextPath() + "/user/emoticons/completion");
            }

        }
    }

}
