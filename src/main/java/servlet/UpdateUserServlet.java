package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        final String id = request.getParameter("id");
        UserService userService = UserService.getInstance();
        request.setAttribute("user", userService.getUser(id));
        request.getRequestDispatcher("/WEB-INF/update.jsp")
                .forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        final String id = request.getParameter("id");
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        UserService userService = UserService.getInstance();
        userService.updateUser(id, login, password);
        response.sendRedirect(request.getContextPath() + "/");
    }
}