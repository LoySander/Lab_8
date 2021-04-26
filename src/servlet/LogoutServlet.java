package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.ChatUser;


@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends ChatServlet {
    private static final long serialVersionUID=1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = (String) request.getSession().getAttribute("name");
        // Если в сессии имеется имя пользователя...
        if(name!=null) {
            // Получить объект, описывающий пользователя с таким именем
            ChatUser aUser = activeUsers.get(name);
            // Если идентификатор сессии пользователя, вошедшего под
            // этим именем, совпадает с идентификатором сессии
            // пользователя, пытающегося выйти из чата// (т.е. выходит тот же, кто и входил)
            if(aUser.getSessionId().equals((String) request.getSession().getId())) {
                // Удалить пользователя из списка активных
                // Т.к. запросы обрабатываются одновременно,
                // нужна синхронизация
                synchronized(activeUsers) {
                    activeUsers.remove(name);
                }

                // Сбросить имяпользователя в сессии
                request.getSession().setAttribute("name", null);
                // СброситьID сессии в cookie
                response.addCookie(new Cookie("sessionId", null));
                // Перенаправить наглавную страницу
                response.sendRedirect(response.encodeRedirectURL("/Laba8/"));
            }
            else{
                // Пользователь пытается аннулировать чужую сессию –// неделатьничего
                response.sendRedirect(response.encodeRedirectURL("/Laba8/view.jsp"));
            }
        }
        else{
            // Перенаправить пользователя на главное окно чата
            response.sendRedirect(response.encodeRedirectURL("/Laba8/view.jsp"));
        }
    }
}