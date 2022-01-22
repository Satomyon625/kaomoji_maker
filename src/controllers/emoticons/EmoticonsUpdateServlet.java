package controllers.emoticons;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Category;
import models.Emoticon;
import models.Transaction;
import models.User;
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

            } else {//顔文字の方は無事更新された

                try {//カテゴリを全く登録してない場合のため
                //前回カテゴリが登録されてたらトランザクションのレコードを一旦削除
                int transaction_count = (int)em.createNamedQuery("getCategoryCountByEmoticon", int.class)//その件数取得、そんなに多くないのでint型
                        .setParameter("emoticon_id", (Integer)(request.getSession().getAttribute("emoticon_id")))
                        .getSingleResult();

                for(int i = 0; i < transaction_count; i ++) {//顔文字idが該当するレコードの数
                Transaction t = em.find(Transaction.class, (Emoticon)(request.getSession().getAttribute("emoticon_id")));//顔文字id条件
                em.remove(t);//顔文字idが一致したレコードを削除
                }
                } catch(Exception ex) {//カテゴリ登録してない場合

                }

             /** カテゴリ登録、入力した方、顔文字新規登録時と同様 */

                String[] categories = request.getParameterValues("category");//入力されたカテゴリの数を取得、配列に格納

                em.getTransaction().begin();
                for(var i = 0; i < categories.length; ++i) {//カテゴリごとに、カテゴリマスタに重複してないか検索し、カテゴリマスタ,トランザクションに登録
                    if(!("".equals(categories[i]))) {//入力されてる
                        Category c = new Category();
                        boolean notEntryFlg = false;
                        Category entryCategory = new Category();
                try {
                    entryCategory = (Category)em.createNamedQuery("getCategoryName",Category.class)
                            .setParameter("category", categories[i])
                            .getSingleResult();

                } catch (NoResultException ne) {
                   notEntryFlg = true;
                }
                if(notEntryFlg) {//登録されてなかった場合、登録する
                    c.setCategory(categories[i]);
                    c.setCreate_user((User)request.getSession().getAttribute("login_user"));
                    c.setCreated_at(currentTime);
                    c.setUpdated_user((User)request.getSession().getAttribute("login_user"));
                    c.setUpdated_at(currentTime);
                    em.persist(c);
                } else {//登録されてる場合、登録しない
                    c = entryCategory;
                }
                Transaction t = new Transaction();
                t.setEmoticon_id(e);//idを取得し、書き込む
                t.setCategory_id(c);
                t.setCreate_user((User)request.getSession().getAttribute("login_user"));
                t.setCreated_at(currentTime);
                t.setUpdated_user((User)request.getSession().getAttribute("login_user"));
                t.setUpdated_at(currentTime);

                em.persist(t);//テーブル登録
                }
                }//for文、カテゴリ入力した分登録処理し終える


                try {
                String[] strCategory_c = request.getParameterValues("category_c");//デフォルトのチェックボックス
                Category[] category_c = new Category[strCategory_c.length];//カテゴリidをCategory型に変換

                for(var i = 0 ; i < category_c.length ; ++i) {//Category方の配列へ入れてく
                    Category c = em.find(Category.class, Integer.parseInt(strCategory_c[i]));//カテゴリid
                    category_c[i] = c;//トランザクションへ
                }
                for(var i = 0; i < category_c.length; ++i) {
                    Transaction t = new Transaction();
                    t.setEmoticon_id(e);//idを取得し、書き込む

                    t.setCategory_id(category_c[i]);//カテゴリid
                    t.setCreate_user((User)request.getSession().getAttribute("login_user"));
                    t.setCreated_at(currentTime);
                    t.setUpdated_user((User)request.getSession().getAttribute("login_user"));
                    t.setUpdated_at(currentTime);

                    em.persist(t);//テーブル登録
                }
                }catch(NullPointerException ne) {

                }

                em.getTransaction().commit();
                em.close();

                String n = request.getParameter("emoticon");
                request.getSession().setAttribute("new_emoticon",n);//登録完了画面に登録した顔文字を表示させる
                response.sendRedirect(request.getContextPath() + "/user/emoticons/completion");
        }
        }

}
}
