public enum LogLevel {
    INFO(1),
    TRACE(2),
    DEBUG(3),
    ALERT(4),
    WARNING(5),
    CRITICAL(6),
    ERROR(7),
    FATAL(8);

    private final int value;
    LogLevel(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
