package servlet;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.ChatMessage;
import entity.ChatUser;


@WebServlet(name = "NewMessageServlet")
public class NewMessageServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;
    private long time_permissible = 5;
    private int amount_message = 2;
    private int blocking = 10;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // По умолчанию используется кодировка ISO-8859. Так как мы
        // передаѐмданныевкодировкеUTF-8//
        // то необходимо установить соответствующую кодировкуHTTP-запроса
        request.setCharacterEncoding("UTF-8");
        // ИзвлечьизHTTP-запросапараметр'message'
        String message = (String)request.getParameter("message");
        // Еслисообщениенепустое, то
        if(message!=null&& !"".equals(message)) {
            int k = 0;
            long z = 0;
            // По имении зсессии получить ссылкуна объект ChatUser
            ChatUser author = activeUsers.get((String) request.getSession().getAttribute("name"));
            for(int i = 0;i<=messages.size()-1;i++){
                ChatMessage aMessage = messages.get(i);
                if(author == aMessage.getAuthor()){
                    k++;
                    z = aMessage.getTimestamp() - z;
                }
            }
            if(k >= 2 && z >= time_permissible){
                ///
            }
            else{
                synchronized(messages) {
                    // Добавить всписок сообщений новое
                    messages.add(new ChatMessage(message, author, Calendar.getInstance().getTimeInMillis()));
                }
            }
        }
        // Перенаправитьпользователянастраницусформойсообщения
        response.sendRedirect("/Laba8/compose_message.html");
    }
}

