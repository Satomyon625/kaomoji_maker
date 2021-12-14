package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();

        // Userのインスタンスを生成
        User u = new User();

        // uの各フィールドにデータを代入
        String u_name = "myonkiti";
        u.setU_name(u_name);

        String pass = "myonkiti0306";
        u.setPass(pass);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());     // 現在の日時を取得
        u.setCreated_at(currentTime);
        u.setUpdated_at(currentTime);

        // データベースに保存
        em.persist(u);
        em.getTransaction().commit();

        //※データの中身表示のつもりだが、ID自動採番からユーザーネーム出そうとしてた
        response.getWriter().append(String.valueOf(u.getU_name()));

        em.close();
    }

}
