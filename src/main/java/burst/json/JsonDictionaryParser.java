package burst.json;

import java.util.HashMap;
import java.util.Map;

public class JsonDictionaryParser extends JsonObjectParserBase
{
    @Override
    protected Class GetFieldType(String field, Object res, Class dic_type)
    {
        //if (dic_type.IsGenericType)
        //{
        //    Class[] rtt = dic_type.GetGenericArguments();
        //    if (rtt.length == 2)
        //        return rtt[1];
        //}
        return JsonUtils.ObjectType;
    }

    @Override
    protected void SetFieldValue(String field, Object value, Object res, Class dic_type)
    {
        //if ((Map)res).contains(field))
        //    ((Map)res)[field] = value;
        ((Map)res).put(field, value);
    }

    @Override
    protected Object CreateResult(Class type) throws JsonException
    {
        if (type == JsonUtils.ObjectType)
            return new HashMap<String, Object>();
        return JsonUtils.newInstance(type);
    }
}