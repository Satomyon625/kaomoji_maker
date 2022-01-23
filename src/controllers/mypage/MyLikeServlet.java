package controllers.mypage;

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
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class MyLikeServlet
 */
@WebServlet("/user/mypage/like")
public class MyLikeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLikeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        User u_name = (User)request.getSession().getAttribute("login_user");//ログインユーザー
        String l_name = u_name.getU_name();

        // ページネーション
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        List<Emoticon> emoticons = em.createNamedQuery("getEmoticonsByMyLike", Emoticon.class)
                .setParameter("like_user",l_name)
                .setFirstResult(20 * (page - 1))
                .setMaxResults(20)
                .getResultList();

                long emoticons_count = (long)em.createNamedQuery("getEmoticonsByMyLikeCount", Long.class)//その件数取得
                                      .setParameter("like_user", l_name)
                                      .getSingleResult();

                request.setAttribute("emoticons", emoticons);
                request.setAttribute("emoticons_count", emoticons_count);     // 全件数

                em.close();
                request.setAttribute("page", page);

             // フラッシュメッセージがセッションスコープにセットされていたら
                // リクエストスコープに保存する（セッションスコープからは削除）
                if(request.getSession().getAttribute("flush") != null) {
                    request.setAttribute("flush", request.getSession().getAttribute("flush"));
                    request.getSession().removeAttribute("flush");
                }
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mypage/my_like.jsp");
                rd.forward(request, response);
    }

}
