package controllers.emoticons;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmoticonsCompletionServlet
 */
@WebServlet("/user/emoticons/completion")
public class EmoticonsCompletionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmoticonsCompletionServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("new_emoticon") != null) {//顔文字登録画面からちゃんと遷移した場合
            request.setAttribute("new_emoticon", request.getSession().getAttribute("new_emoticon"));

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/emoticons/completion.jsp");
            rd.forward(request, response);
        } else {//それ以外の場合（不正アクセス）
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/toppage/toppage.jsp");
            rd.forward(request, response);
        }

    }
}
