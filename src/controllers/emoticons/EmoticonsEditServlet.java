package controllers.emoticons;

import java.io.IOException;
import java.util.ArrayList;
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
import models.Transaction;
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

        List<Transaction> category_c = em.createNamedQuery("getCategoryByDefault", Transaction.class)//デフォルトカテゴリidを抽出
                .setParameter("emoticon_id",e)
                .getResultList();

        List<Integer> defaultCategories = new ArrayList<Integer>();
        for(Transaction transaction :category_c) {
            defaultCategories.add(transaction.getCategory_id().getId());//デフォルトカテゴリidを格納
        }

        request.setAttribute("category_c", defaultCategories);

        List<Category> category_i = em.createNamedQuery("getCategoryByInput", Category.class)//入力カテゴリ名を抽出
                .setParameter("emoticon_id",e)
                .getResultList();

        List<String> inputCategories = new ArrayList<String>();
        for(Category category :category_i) {
            inputCategories.add(category.getCategory());//カテゴリ名を格納
        }
        int n_enough = 3 - inputCategories.size();//足りない数

        request.setAttribute("inputCategories", inputCategories);
        request.setAttribute("n_enough", n_enough);//足りない分のテキストボックス


        em.close();

        request.setAttribute("emoticon", e);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("emoticon_id", e.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/emoticons/edit.jsp");
        rd.forward(request, response);
    }

}
