package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    /**
     * Default constructor.
     */
    public LoginFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!servlet_path.matches("/css.*")) {
            HttpSession session = ((HttpServletRequest)request).getSession();

            User u = (User)session.getAttribute("login_user");

            if(servlet_path.matches("/user.*")) {//ユーザ用のページにアクセスした場合
                if(u == null) {//ログイン情報がない場合
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");//ログイン画面に遷移
                    return;
                }
            }
            if(servlet_path.equals("/login") || servlet_path.equals("/newuser")) { //ログイン画面またはアカウント登録画面にアクセスした場合
                if(u != null) { //ログイン中の場合
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                }
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }

}
