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
    private static final long time_permissible = 10000;
    private static final int amount_message = 2;
    private static final long blocking = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // По умолчанию используется кодировка ISO-8859. Так как мы
        // передаѐмданныевкодировкеUTF-8//
        // то необходимо установить соответствующую кодировкуHTTP-запроса
        request.setCharacterEncoding("UTF-8");
        // ИзвлечьизHTTP-запросапараметр'message'
        String message = (String)request.getParameter("message");
        // Если сообщение не пустое, то
        int k = 0;
        long z = 0;
        long timelast = 0;

       // timelast = author.getTimelast();
       // if(timelast+blocking<=Calendar.getInstance().getTimeInMillis()){
        if(message!=null&& !"".equals(message)) {
            // По имени из сессии получить ссылку на объект ChatUse
            ChatUser author = activeUsers.get((String) request.getSession().getAttribute("name"));
            for(int i = 0;i<=messages.size()-1;i++){
                ChatMessage aMessage = messages.get(i);
                if(author == aMessage.getAuthor()){
                k++;
                if(k>=2) {
                    z = aMessage.getTimestamp() - z;
                }
                    timelast =aMessage.getTimestamp();
                    System.out.println("последнее сообщение отправлено "+timelast);
                    System.out.println("z "+ z);
                    System.out.println("a "+ aMessage.getAuthor());
                    System.out.println("a "+ author);
                }
            }
            System.out.println("------------------------ ");


            author.setTimelast(timelast);
            if(k >= 2 && z <= time_permissible){
                String systemMessage = " Извините, но вы привысили лимит сообщений ";
                synchronized (messages) {
                    ChatMessage str = new ChatMessage(systemMessage, author, Calendar.getInstance().getTimeInMillis());
                    messages.add(str);
                }
            }
            else {
                synchronized(messages) {
                    // Добавить всписок сообщений новое
                    messages.add(new ChatMessage(message, author, Calendar.getInstance().getTimeInMillis()));
                }
            }

        }
        // Перенаправитьпользователянастраницусформойсообщения
        response.sendRedirect("/Laba8/compose_message.jsp");
    }
}

