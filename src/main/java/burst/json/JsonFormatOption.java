package burst.json;

public class JsonFormatOption
{
    public static JsonFormatOption Compression = new JsonFormatOption(
        "",
        "",
        ":"
    );

    public static JsonFormatOption Readable = new JsonFormatOption(
        "    ",
        "\r\n",
        ": "
    );

    public String getNewLine() {
        return newLine;
    }

    public void setNewLine(String newLine) {
        this.newLine = newLine;
    }

    private String newLine;
    private String indentation;

    public String getIndentation() {
        return indentation;
    }

    public void setIndentation(String indentation) {
        this.indentation = indentation;
    }

    public String getColon() {
        return colon;
    }

    public void setColon(String colon) {
        this.colon = colon;
    }

    private String colon;

    public JsonFormatOption() { }

    public JsonFormatOption(String indentation, String newLine, String colon) {
        this.indentation = indentation;
        this.newLine = newLine;
        this.colon = colon;
    }
}
