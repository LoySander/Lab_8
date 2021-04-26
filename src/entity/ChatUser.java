package entity;

public class ChatUser {

    // Имяпользователя
    private String name;
    // Последнее время взаимодействия с сервером в количестве микросекунд,
    // прошедших с 1 января1970 года
    private long lastInteractionTime;
    private long timelast = 0;
    // Идентификатор Java-сессии пользователя
    private String sessionId;
    public ChatUser(String name,long lastInteractionTime,String sessionId) {
        super();
        this.name= name;
        this.lastInteractionTime= lastInteractionTime;
        this.sessionId= sessionId;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name= name;
    }
    public long getLastInteractionTime() {
        return lastInteractionTime;
    }
    public void setLastInteractionTime(long lastInteractionTime) {

        this.lastInteractionTime= lastInteractionTime;
    }
    public String getSessionId()
    {
        return sessionId;
    }
    public void setSessionId(String sessionId) {

        this.sessionId= sessionId;
    }
    public void setTimelast(long timelast)
    {
        this.timelast = timelast;
    }
    public long getTimelast()
    {
        return timelast;
    }

}