package servlet;

import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/")
public class AuthorizationServlet extends HttpServlet {
    private UserServiceImpl service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        session.setAttribute("login", login);
        session.setAttribute("password", password);

        boolean result = service.isUserExist(login, password);

        if (result) {
            final String role = service.getUserByLoginAndPassword(login, password).getRole();
            moveToMenu(req, resp, role);
        } else {
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws IOException, ServletException {

        if (role.equals("admin")) {
            res.sendRedirect("/admin/users");
        } else {
            req.getRequestDispatcher("/user/hello").forward(req, res);
        }
    }
}