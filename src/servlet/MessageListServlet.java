package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.ChatMessage;


@WebServlet(name = "MessageListServlet")
public class MessageListServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Установить кодировку HTTP-ответа UTF-8
        response.setCharacterEncoding("utf8");
        // Получить доступ к потокувыводаHTTP-ответа
        PrintWriter pw = response.getWriter();
        // Записть в потокHTML-разметку страницы
        pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='10'></head>");
        pw.println("<body>");
        // ВобратномпорядкезаписатьвпотокHTML-разметкудлякаждогосообщения
        for(int i=messages.size()-1; i>=0; i--) {
            ChatMessage aMessage = messages.get(i);
            if(aMessage.getFlag()) {
                pw.println("<div><strong>" + ""
                        + "</strong>" + aMessage.getMessage() + "</div>");

            } else {
                pw.println("<div><strong>"+ aMessage.getAuthor().getName() + "</strong>: "+ aMessage.getMessage() +  "</div>");
            }
        }//"</strong>: "+ aMessage.getTimestamp()+"
        pw.println("</body></html>");
    }
}