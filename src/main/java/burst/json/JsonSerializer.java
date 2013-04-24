package burst.json;

import java.util.HashMap;
import java.util.Map;

public class JsonSerializer
{
    public String Serialize(Object obj) throws JsonException
    {
        return JsonUtils.Serialize(obj, group, serializerRegistry, formatOption);
    }

    public String Serialize(Object obj, int layer, JsonFormatOption option) throws JsonException
    {
        return JsonUtils.Serialize(obj, group, serializerRegistry, option, layer);
    }

    public Object ParseAs(String s, Class t) throws JsonException
    {
        return JsonUtils.ParseAs(s, t, parserRegistry);
    }

    public Map<Class, JsonObjectParserBase> parserRegistry;
    public Map<Class, JsonObjectSerializerBase> serializerRegistry;
    public String group;
    public JsonFormatOption formatOption;

    public Map<Class, JsonObjectParserBase> getParserRegistry() {
        return parserRegistry;
    }

    public void setParserRegistry(Map<Class, JsonObjectParserBase> parserRegistry) {
        this.parserRegistry = parserRegistry;
    }

    public Map<Class, JsonObjectSerializerBase> getSerializerRegistry() {
        return serializerRegistry;
    }

    public void setSerializerRegistry(Map<Class, JsonObjectSerializerBase> serializerRegistry) {
        this.serializerRegistry = serializerRegistry;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public JsonFormatOption getFormatOption() {
        return formatOption;
    }

    public void setFormatOption(JsonFormatOption formatOption) {
        this.formatOption = formatOption;
    }

    public JsonSerializer()
    {
        parserRegistry = new HashMap<Class, JsonObjectParserBase>();
        serializerRegistry = new HashMap<Class, JsonObjectSerializerBase>();
        group = null;
        formatOption = JsonFormatOption.Compression;
    }

    public final static JsonSerializer Default = new JsonSerializer();
}
