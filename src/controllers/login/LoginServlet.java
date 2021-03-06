package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());  //トークンID
        request.setAttribute("hasError", false);  //エラーチェック
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean check_result = false;

        String u_name = request.getParameter("u_name");
        String plain_pass = request.getParameter("pass");

        User u = null;

        if(u_name != null && !u_name.equals("") && plain_pass != null && !plain_pass.equals("")) { //ユーザー名及びパスワードが未記入でなく空白(スペース)でない場合
            EntityManager em = DBUtil.createEntityManager();

            String pass = EncryptUtil.getPasswordEncrypt( //plain_passはハッシュ化する前のパスワード
                    plain_pass,
                    (String)this.getServletContext().getAttribute("pepper")
                    );

            try {
                u = em.createNamedQuery("checkLoginU_nameAndPass", User.class) //登録されているか
                        .setParameter("u_name", u_name)
                        .setParameter("pass", pass)
                        .getSingleResult();
            } catch (NoResultException ex) {}

            em.close();

            if(u != null) { //登録されてた
                check_result = true;
            }
        }

        if(!check_result) {//登録されてない（不一致）
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true); //エラー発生
            request.setAttribute("u_name", u_name);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request, response);
        } else { //ログイン成功
            request.getSession().setAttribute("login_user", u);
            request.getSession().setAttribute("flush", "ログインしました。");
            response.sendRedirect(request.getContextPath() + "/top");
        }
    }
}
