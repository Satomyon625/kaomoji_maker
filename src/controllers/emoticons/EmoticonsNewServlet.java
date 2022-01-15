package controllers.emoticons;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Emoticon;


/**
 * Servlet implementation class EmoticonsNew
 */
@WebServlet("/user/emoticons/new")
public class EmoticonsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmoticonsNewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Emoticon e = new Emoticon();
        request.setAttribute("emoticon", e);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/emoticons/new.jsp");
        rd.forward(request, response);
    }

}
