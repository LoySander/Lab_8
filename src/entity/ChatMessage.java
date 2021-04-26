package entity;

public class ChatMessage {
    // Текстсообщения
    private String message;
    // Авторсообщения
    private ChatUser author;
    // Временная метка сообщения (в микросекундах)
    private long timestamp;
    private Boolean flag = false;

    public ChatMessage(String message, ChatUser author, long timestamp) {
        super();
        this.message= message;
        this.author= author;
        this.timestamp= timestamp;
    }
    public String getMessage() {

        return message;
    }
    public void setMessage(String message)
    {
        this.message= message;
    }
    public ChatUser getAuthor() {

        return author;
    }
    public void setAuthor(ChatUser author) {

        this.author= author;
    }
    public long getTimestamp()
    {
        return timestamp;
    }
    public void setTimestamp(long timestamp)
    {
        this.timestamp= timestamp;
    }
    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}