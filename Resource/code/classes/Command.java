package classes;

public class Command {
    private final String message;
    private final Object[] attributes;

    public Command(String message, Object[] attributes) {
        this.message = message;
        this.attributes = attributes;
    }

    public String getMessage() {
        return message;
    }

    public Object[] getAttributes() {
        return attributes;
    }
}
