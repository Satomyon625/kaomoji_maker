package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Category;
import models.Emoticon;
import utils.DBUtil;

/**
 * Servlet implementation class ToppageServlet
 */
@WebServlet("/top")
public class ToppageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToppageServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      EntityManager em = DBUtil.createEntityManager();
      String sort= "getAllEmoticons";//初期値

      if(request.getSession().getAttribute("emoticonsSort") != null) {//並び替え情報を保持してた場合(並び替えしてページネーションした)
         sort = (String) request.getSession().getAttribute("emoticonsSort");//並び替えを適用（２ページ目以降も）
      }

        // ページネーション
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        // 全ての顔文字を取得
        List<Emoticon> emoticons = em.createNamedQuery(sort, Emoticon.class)
                .setFirstResult(20 * (page - 1))
                .setMaxResults(20)
                .getResultList();

        long emoticons_count = (long)em.createNamedQuery("getEmoticonsCount", Long.class)
                                      .getSingleResult();



        String searchKeyword = "";
        if (request.getSession().getAttribute("searchKeyword") != null) {//カテゴリ検索を保持してたら
            searchKeyword = (String) request.getSession().getAttribute("searchKeyword");//検索ワードを格納
            boolean notEntryFlg = true;
            Category c = new Category();

            try {
            //検索ワードをカテゴリ名とし該当するidを取得

              c  = (Category)em.createNamedQuery("getCategoryName", Category.class)
                        .setParameter("category",searchKeyword)
                        .getSingleResult();
               }catch(Exception ex) {//ない場合
                   notEntryFlg = false;
            }
            if(notEntryFlg) {//ヒットした場合

             emoticons = em.createNamedQuery("getEmoticonsByCategoryId", Emoticon.class)//カテゴリに該当する顔文字だけ取得
                    .setParameter("category_id", c)
                    .setFirstResult(20 * (page - 1))
                    .setMaxResults(20)
                    .getResultList();

             emoticons_count = (long)em.createNamedQuery("getEmoticonsByCategoryIdCount", Long.class)//その件数取得
                                          .getSingleResult();
            }
        }
        em.close();

        request.setAttribute("searchKeyword", searchKeyword);//検索
        request.setAttribute("emoticonsSort",sort);//並び替え

        request.setAttribute("emoticons", emoticons);
        request.setAttribute("emoticons_count", emoticons_count);     // 全件数
        request.setAttribute("page", page);                   // ページ数

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/toppage/toppage.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("emoticons_sort");//並び替えの情報を取得（JPQL文）

        if (sort == null) {//選択しなかった場合
            sort = (String) request.getSession().getAttribute("emoticonsSort");//元のを適用（２ページ目以降も）
        } else {
            request.getSession().setAttribute("emoticonsSort", sort);//選択してたらそちらをセット
        }

        EntityManager em = DBUtil.createEntityManager();
     // ページネーション
        int page;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        // 全ての顔文字を取得
        List<Emoticon> emoticons = em.createNamedQuery(sort, Emoticon.class)//JPQLとして検索並び替え
                .setFirstResult(20 * (page - 1))
                .setMaxResults(20)
                .getResultList();

        long emoticons_count = (long)em.createNamedQuery("getEmoticonsCount", Long.class)
                                      .getSingleResult();

        String searchKeyword = request.getParameter("search_keyword");//検索、カテゴリキーワードを取得

        if(searchKeyword != null) {//検索ワード入力してた場合

            boolean notEntryFlg = true;
            Category c = new Category();

            try {
            //検索ワードをカテゴリ名とし該当するidを取得

            c = (Category)em.createNamedQuery("getCategoryName", Category.class)
                    .setParameter("category",searchKeyword)
                    .getSingleResult();
            }catch(Exception ex){
                notEntryFlg = false;
            }
            if(notEntryFlg) {//ヒットした場合
             emoticons = em.createNamedQuery("getEmoticonsByCategoryId", Emoticon.class)//カテゴリに該当する顔文字だけ取得
                    .setParameter("category_id", c)
                    .setFirstResult(20 * (page - 1))
                    .setMaxResults(20)
                    .getResultList();

             emoticons_count = (long)em.createNamedQuery("getEmoticonsByCategoryIdCount", Long.class)//その件数取得
                                          .setParameter("category_id", c)
                                          .getSingleResult();
            }

        }

        em.close();


        request.setAttribute("searchKeyword", searchKeyword);
        request.setAttribute("emoticonsSort", sort);//並び替えの選択状態を保持
        request.setAttribute("emoticons", emoticons);
        request.setAttribute("emoticons_count", emoticons_count);     // 全件数
        request.setAttribute("page", page);                   // ページ数

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/toppage/toppage.jsp");
        rd.forward(request, response);
    }

}
