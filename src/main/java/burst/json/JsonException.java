package burst.json;

public class JsonException extends Exception
{
    public JsonException(String message, Object... pms) {
        super(String.format(message, pms));
    }

    public JsonException(Throwable ex, String message, Object... pms) {
        super(String.format(message, pms), ex);
    }
}