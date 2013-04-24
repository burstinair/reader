package burst.json;

import java.util.Map;

public abstract class JsonObjectParserBase
{
    protected abstract Class GetFieldType(String field, Object res, Class type) throws JsonException;
    protected abstract void SetFieldValue(String field, Object value, Object res, Class type) throws JsonException;
    protected abstract Object CreateResult(Class type) throws JsonException;

    int ParseJsonObject(String jsonstr, Map<Class, JsonObjectParserBase> parser_dic, int offset, ResultHolder res, Class targetType) throws JsonException
    {
        res.result = CreateResult(targetType);
        int i = offset, state = 0, cvp = 0;
        String curKey = null;
        while (i < jsonstr.length() - 1)
        {
            ++i;
            if (state == 0)
            {
                if (Character.isWhitespace(jsonstr.charAt(i)))
                    continue;
                if (jsonstr.charAt(i) != JsonUtils.DoubleQuote && jsonstr.charAt(i) != JsonUtils.SingleQuote)
                    throw new JsonException("Json Object Parse Error at %d.", i);
                else
                {
                    state = 1;
                    cvp = i + 1;
                }
            }
            else if (state == 1)
            {
                if (jsonstr.charAt(i) == jsonstr.charAt(cvp - 1))
                {
                    if (i == cvp)
                        throw new JsonException("Json Object Parse Error at %d.", i);
                    curKey = jsonstr.substring(cvp, i);
                    state = 2;
                }
                else if (!Character.isLetterOrDigit(jsonstr.charAt(i)) && jsonstr.charAt(i) != JsonUtils.Underline)
                    throw new JsonException("Json Object Parse Error at %d.", i);
            }
            else if (state == 2)
            {
                if (Character.isWhitespace(jsonstr.charAt(i)))
                    continue;
                if (jsonstr.charAt(i) != JsonUtils.Colon)
                    throw new JsonException("Json Object Parse Error at %d.", i);
                state = 3;
            }
            else if (state == 3)
            {
                if (Character.isWhitespace(jsonstr.charAt(i)))
                    continue;
                ParsePosition position = new ParsePosition();
                position.position = i;
                SetFieldValue(
                    curKey,
                    JsonUtils.ParseAs(
                        jsonstr,
                        parser_dic,
                        GetFieldType(curKey, res, targetType),
                        i, position
                    ),
                    res.result, targetType
                );
                i = position.position - 1;
                state = 4;
            }
            else if (state == 4)
            {
                if (Character.isWhitespace(jsonstr.charAt(i)))
                    continue;
                if (jsonstr.charAt(i) == JsonUtils.RightBrace)
                    return i + 1;
                if (jsonstr.charAt(i) == JsonUtils.Comma)
                    state = 0;
                else
                    throw new JsonException("Json Object Parse Error at %d.", i);
            }
        }
        throw new JsonException("Json Object Parse Error at %d.", i);
    }
}