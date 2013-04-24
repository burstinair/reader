package burst.json;

import burst.reflect.PropertyInfo;
import burst.reflect.TypeUtils;

public class JsonObjectReflectionParser extends JsonObjectParserBase
{
    @Override
    protected Class GetFieldType(String field, Object res, Class targetType) throws JsonException
    {
        PropertyInfo curMemberInfo = TypeUtils.getProperty(targetType, field);
        if (curMemberInfo != null)
            return curMemberInfo.getType();
        else
            throw new JsonException("The Object does not contains a field named %s.", field);
    }

    @Override
    protected void SetFieldValue(String field, Object value, Object res, Class targetType) throws JsonException
    {
        PropertyInfo curMemberInfo = TypeUtils.getProperty(targetType, field);
        try {
            curMemberInfo.setValue(res, value);
        } catch (Throwable ex) {
            throw new JsonException(ex, "The Object does not contains a field named %s.", field);
        }
    }

    @Override
    protected Object CreateResult(Class type) throws JsonException
    {
        return JsonUtils.newInstance(type);
    }
}