package controllers.emoticons;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Emoticon;
import utils.DBUtil;

/**
 * Servlet implementation class EmoticonsEditServlet
 */
@WebServlet("/user/emoticons/edit")
public class EmoticonsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmoticonsEditServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        //顔文字だけでなく、カテゴリーと、トランザクションも呼び出す

        Emoticon e = em.find(Emoticon.class, Integer.parseInt(request.getParameter("id")));//顔文字

       /* List<Transaction> category_c = em.createNamedQuery("getCategoryByDefault", Transaction.class)//デフォルトカテゴリ
                .setParameter("emoticon_id",e)
                .getResultList();

        request.setAttribute("category_c", category_c);
*/

        em.close();

        request.setAttribute("emoticon", e);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("emoticon_id", e.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/emoticons/edit.jsp");
        rd.forward(request, response);
    }

}
