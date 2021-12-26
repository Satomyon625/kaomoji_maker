package controllers.toppage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserTop
 */
@WebServlet("/user/top")
public class UserTopServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTopServlet() {
        super();

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }
        List<Mark> marks = em.createNamedQuery("getAllMarks", Mark.class)
                                  .setFirstResult(20 * (page - 1))
                                  .setMaxResults(20)
                                  .getResultList();

        long marks_count = (long)em.createNamedQuery("getMarksCount", Long.class)
                                     .getSingleResult();

        em.close();

        request.setAttribute("marks", marks);
        request.setAttribute("marks_count", marks_count);
        request.setAttribute("page", page);
 */
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/toppage/usertop.jsp");
        rd.forward(request, response);
    }

}
