package controllers.toppage;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Emoticon;
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

            //パラメータ取得
            int checkId = Integer.parseInt(likeId);
            User user = (User)request.getSession().getAttribute("login_user");
            String checkUser = user.getU_name();

            Boolean checkResult = false;//登録判定

            try {
                //ユーザーが該当する顔文字にすでにいいね済みか件数取得
                 Long count = (Long)em.createNamedQuery("getLikeCountByEmoticon_idAndLike_user", Long.class)
                         .setParameter("emoticon_id", checkId)
                         .setParameter("like_user", checkUser)
                         .getSingleResult();

                 //件数判定
                 if(count == 0) {
                     //0件の場合は未登録なのでいいね登録する
                     Like l = new Like();

                     l.setEmoticon_id(Integer.parseInt(likeId));
                     l.setLike_user(user.getU_name());

                     Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                     l.setCreated_at(currentTime);

                     //顔文字テーブルのいいね数にカウント
                     Emoticon e = em.find(Emoticon.class, Integer.parseInt(likeId));
                     long num = e.getLike_number()+1;
                     e.setLike_number(num);//いいね数にカウント

                     em.getTransaction().begin();
                     em.persist(l);
                     em.persist(e);
                     em.getTransaction().commit();
                     checkResult = true;

                 } else {
                     //すでに登録されているので登録しない
                 }

                } catch (Exception er) {
                    er.printStackTrace();
                }
                em.close();

            if(checkResult != true) {//いいね登録済み
                request.getSession().setAttribute("flush", "いいね登録済みです。");
                response.sendRedirect(request.getContextPath() + "/top");

            } else {
                request.getSession().setAttribute("flush", "いいねしました。");
                response.sendRedirect(request.getContextPath() + "/top");
            }
}
}