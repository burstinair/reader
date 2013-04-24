package burst.json;

public class JsonObjectParser extends JsonObjectParserBase
{
    @Override
    protected Class GetFieldType(String field, Object res, Class type) throws JsonException
    {
        return ((IJsonDeserializeObject)res).GetFieldType(field);
    }

    @Override
    protected void SetFieldValue(String field, Object value, Object res, Class type) throws JsonException
    {
        ((IJsonDeserializeObject)res).SetFieldValue(field, value);
    }

    @Override
    protected Object CreateResult(Class type) throws JsonException
    {
        return JsonUtils.newInstance(type);
    }
}