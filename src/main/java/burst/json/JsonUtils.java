package burst.json;

import burst.StringUtils;
import burst.reflect.PropertyInfo;
import burst.reflect.TypeUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/// <summary>
/// 提供Json编码、解析的一般方法。
/// </summary>
public final class JsonUtils
{
    static final char DoubleQuote = '\"';
    static final char SingleQuote = '\'';
    static final char Underline = '_';
    static final char Colon = ':';
    static final char Comma = ',';
    static final char LeftBrace = '{';
    static final char RightBrace = '}';
    static final char LeftBracket = '[';
    static final char RightBracket = ']';
    static final char BackSlash = '\\';
    static final char Enter = '\r';
    static final char HalfEnter = 'r';
    static final char Wrap = '\n';
    static final char HalfWrap = 'n';
    static final char Table = '\t';
    static final char HalfTable = 't';
    static final String True = "true";
    static final String False = "false";
    static final char Point = '.';
    static final String Empty = "";

    static final JsonObjectParser JsonObjectParser = new JsonObjectParser();
    static final JsonDictionaryParser JsonDictionaryParser = new JsonDictionaryParser();
    static final JsonObjectReflectionParser JsonObjectReflectionParser = new JsonObjectReflectionParser();
    static final Class ObjectType = Object.class;

    /// <summary>
    /// 将源字符串中的CR、LF、Tab和引号进行转移字符(\)编码。
    /// </summary>
    /// <param name="source">源字符串</param>
    /// <returns>编码后的字符串</returns>
    public static String EncodeCRLFTabQuote(String source)
    {
        if (source == null)
            return null;
        StringBuilder res = new StringBuilder();
        int p = 0;
        while (p < source.length())
        {
            switch (source.charAt(p))
            {
                case BackSlash:
                    res.append("\\\\");
                    ++p;
                    break;
                case Wrap:
                    res.append("\\n");
                    ++p;
                    break;
                case Enter:
                    res.append("\\r");
                    ++p;
                    break;
                case Table:
                    res.append("\\t");
                    ++p;
                    break;
                case SingleQuote:
                    res.append("\\'");
                    ++p;
                    break;
                case DoubleQuote:
                    res.append("\\\"");
                    ++p;
                    break;
                default:
                    res.append(source.charAt(p));
                    ++p;
                    break;
            }
        }
        return res.toString();
    }

    /// <summary>
    /// 根据层深与指定格式配置生成缩进
    /// </summary>
    /// <param name="layer">层深</param>
    /// <param name="option">格式配置</param>
    /// <returns>缩进</returns>
    public static String CalcIndent(int layer, JsonFormatOption option)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < layer; ++i)
            builder.append(option.getIndentation());
        return builder.toString();
    }

    /// <summary>
    /// 将指定的对象编码为Json字符串。
    /// </summary>
    /// <param name="s">指定的对象</param>
    /// <returns>编码后的字符串</returns>
    public static String Serialize(Object s) throws JsonException
    {
        return Serialize(s, null, null, JsonFormatOption.Compression, 0);
    }

    /// <summary>
    /// 将指定对象中具有指定JsonGroupAttribute的字段根据给定的JsonObjectSerializerBase对象编码为Json字符串。
    /// </summary>
    /// <param name="s">指定的对象</param>
    /// <param name="group">指定JsonGroupAttribute的名称</param>
    /// <param name="serializer_dic">给定的JsonObjectSerializerBase对象的集合</param>
    /// <returns>编码后的字符串</returns>
    public static String Serialize(Object s, String group, Map<Class, JsonObjectSerializerBase> serializer_dic, JsonFormatOption format_option) throws JsonException
    {
        return Serialize(s, group, serializer_dic, format_option, 0);
    }

    static String Format(char left, char right, FormatDelegate iterator, String indent, JsonFormatOption option) throws JsonException
    {
        StringBuilder res = new StringBuilder();
        res.append(left);
        String child_indent = indent + option.getIndentation();
        SkipHolder skip = new SkipHolder();
        boolean first = true;
        String next = iterator.proceed(skip);
        while (next != null)
        {
            if (!skip.skip)
            {
                if(first) {
                    first = false;
                } else {
                    res.append(Comma);
                }
                res.append(option.getNewLine());
                res.append(child_indent);
                res.append(next);
            }
            skip.skip = false;
            next = iterator.proceed(skip);
        }
        res.append(option.getNewLine());
        res.append(indent);
        res.append(right);
        return res.toString();
    }

    static String Serialize(final Object s, final String group, final Map<Class, JsonObjectSerializerBase> serializer_dic, final JsonFormatOption format_option, final int layer) throws JsonException
    {
        String indent = CalcIndent(layer, format_option);

        if (s == null)
            return "\"\"";
        else if (serializer_dic != null && serializer_dic.containsKey(s.getClass()))
            return serializer_dic.get(s.getClass()).SerializeToJsonString(s, layer, format_option);
        else if (s instanceof IJsonSerializeObject)
            return ((IJsonSerializeObject)s).SerializeToJsonString(layer, format_option);
        else if (TypeUtils.isValueType(s.getClass()))
            return String.format("\"%s\"",
                EncodeCRLFTabQuote(TypeUtils.serialize(s))
            );
        else if (s instanceof Map)
        {
            final String format = "\"%s\"%s%s";
            final Iterator enumerator = ((Map)s).entrySet().iterator();
            return Format(LeftBrace, RightBrace, new FormatDelegate() {
                public String proceed(SkipHolder skip) throws JsonException {
                    if (enumerator.hasNext())
                    {
                        Map.Entry es = (Map.Entry)enumerator.next();
                        return String.format(format,
                            es.getKey(),
                            format_option.getColon(),
                            Serialize(es.getValue(), group, serializer_dic, format_option, layer + 1)
                        );
                    }
                    return null;
                }
            }, indent, format_option);
        }
        else if (s instanceof Iterable)
        {
            final Iterator enumerator = ((Iterable)s).iterator();
            return Format(LeftBracket, RightBracket, new FormatDelegate() {
                public String proceed(SkipHolder skip) throws JsonException {
                    if (enumerator.hasNext()) {
                        return Serialize(enumerator.next(), group, serializer_dic, format_option, layer + 1);
                    }
                    return null;
                }
            }, indent, format_option);
        }
        else
        {
            final String format = "\"%s\"%s%s";
            final Iterator enumerator = TypeUtils.getProperties(s.getClass()).iterator();
            return Format(LeftBrace, RightBrace, new FormatDelegate() {
                public String proceed(SkipHolder skip) throws JsonException {
                    if (enumerator.hasNext())
                    {
                        PropertyInfo propertyInfo = (PropertyInfo)enumerator.next();
                        if (propertyInfo.isProperty() && propertyInfo.getGetMethod() == null) {
                            skip.skip = true;
                        } else if (propertyInfo.getAnnotationOnRead(JsonIgnore.class) == null) {
                            if (group != null)
                            {
                                JsonGroup jga = (JsonGroup)propertyInfo.getAnnotationOnRead(JsonGroup.class);
                                if (jga == null)
                                    skip.skip = true;
                                else if (!group.equals(jga.value()))
                                    skip.skip = true;
                            }
                        }
                        else
                            skip.skip = true;
                        if (skip.skip)
                            return Empty;
                        else {
                            try {
                                return String.format(format,
                                    propertyInfo.getName(),
                                    format_option.getColon(),
                                    Serialize(propertyInfo.getValue(s), group, serializer_dic, format_option, layer + 1)
                                );
                            } catch (Throwable ex) {
                                throw new JsonException(ex, "Can't get value of field '%s'.", propertyInfo.getName());
                            }
                        }
                    }
                    return null;
                }
            }, indent, format_option);
        }
    }

    static int ParseJsonArray(String jsonstr, Map<Class, JsonObjectParserBase> parser_dic, int offset, List res, Class elementType) throws JsonException
    {
        int state = 0, l = jsonstr.length() - 1;
        ParsePosition i = new ParsePosition();
        i.position = offset;
        while (i.position < l)
        {
            ++i.position;
            if (jsonstr.charAt(i.position) == RightBracket)
                return i.position + 1;
            else if (state == 0)
            {
                if (Character.isWhitespace(jsonstr.charAt(i.position)))
                    continue;
                res.add(ParseAs(jsonstr, parser_dic, elementType, i.position, i));
                --i.position;
                state = 1;
            }
            else if (state == 1)
            {
                if (Character.isWhitespace(jsonstr.charAt(i.position)))
                    continue;
                if (jsonstr.charAt(i.position) == Comma)
                    state = 0;
                else
                    throw new JsonException("Json Array Parse Error at %d.", i);
            }
        }
        throw new JsonException("Json Array Parse Error at %d.", i);
    }
    static Object ParseAs(String jsonstr, Map<Class, JsonObjectParserBase> parser_dic, Class ast, int offset, ParsePosition endIndex) throws JsonException
    {
        endIndex.position = -1;
        JsonType res_type = JsonType.None;
        boolean dic_contains = parser_dic != null && parser_dic.containsKey(ast);
        if (IJsonDeserializeObject.class.isAssignableFrom(ast) || dic_contains)
            res_type = JsonType.IJsonObject;
        else if (Map.class.isAssignableFrom(ast))
            res_type = JsonType.Map;
        else if (ast.isArray())
            res_type = JsonType.Array;
        else if (List.class.isAssignableFrom(ast))
            res_type = JsonType.List;
        else if (ast.equals(Object.class))
            res_type = JsonType.Object;
        else if (ast.equals(String.class))
            res_type = JsonType.String;
        else if (TypeUtils.isValueType(ast))
            res_type = JsonType.ValueType;

        ResultHolder value = new ResultHolder();
        if (jsonstr.charAt(offset) == LeftBrace)
        {
            JsonObjectParserBase parser = null;
            if (dic_contains)
                parser = parser_dic.get(ast);
            if (parser == null)
            {
                if (res_type == JsonType.IJsonObject)
                    parser = JsonObjectParser;
                else if (res_type == JsonType.Map || res_type == JsonType.Object)
                    parser = JsonDictionaryParser;
                else
                    parser = JsonObjectReflectionParser;
            }
            endIndex.position = parser.ParseJsonObject(jsonstr, parser_dic, offset, value, ast);
        }
        else if (jsonstr.charAt(offset) == LeftBracket)
        {
            Class elementType;
            if (res_type == JsonType.List)
            {
                value.result = newInstance(ast);
                //if (ast)
                //    elementType = ast.GetGenericArguments()[0];
                //else
                elementType = ObjectType;
            }
            else if (res_type == JsonType.Array)
            {
                value.result = new ArrayList<Object>();
                elementType = ast.getComponentType();
            }
            else if (res_type == JsonType.Object)
            {
                value.result = new ArrayList<Object>();
                elementType = ObjectType;
            }
            else
                throw new JsonException("Target Class does not match JsonArray require.");
            endIndex.position = ParseJsonArray(jsonstr, parser_dic, offset, (List)value, elementType);
        }
        else if (jsonstr.charAt(offset) == DoubleQuote || jsonstr.charAt(offset) == SingleQuote)
        {
            StringBuilder res = new StringBuilder();
            for (int i = offset + 1; i < jsonstr.length(); ++i)
            {
                if (jsonstr.charAt(i) == BackSlash)
                {
                    char signal = jsonstr.charAt(i + 1);
                    switch (signal)
                    {
                        case HalfWrap:
                            res.append(Wrap);
                            break;
                        case HalfEnter:
                            res.append(Enter);
                            break;
                        case HalfTable:
                            res.append(Table);
                            break;
                        default:
                            res.append(signal);
                            break;
                    }
                    ++i;
                }
                else if (jsonstr.charAt(i) == jsonstr.charAt(offset))
                {
                    endIndex.position = i + 1;
                    value.result = res.toString();
                    break;
                }
                else
                    res.append(jsonstr.charAt(i));
            }
        }
        if (endIndex.position == -1 && offset + 4 <= jsonstr.length())
            if (jsonstr.substring(offset, offset + 4).equalsIgnoreCase(True))
            {
                value.result = true;
                endIndex.position = offset + 4;
            }
        if (endIndex.position == -1 && offset + 5 <= jsonstr.length())
            if (jsonstr.substring(offset, offset + 5).equalsIgnoreCase(False))
            {
                value.result = false;
                endIndex.position = offset + 5;
            }
        if(endIndex.position == -1)
        {
            boolean isFloat = false;
            for (int j = offset; j < jsonstr.length(); j++)
            {
                if (jsonstr.charAt(j) == Point)
                    if (isFloat)
                        throw new JsonException("Number Parse Error at %d(%s).", offset, jsonstr.substring(offset, j));
                    else
                        isFloat = true;
                else if (!Character.isDigit(jsonstr.charAt(j)))
                {
                    try
                    {
                        if (isFloat)
                            value.result = Double.parseDouble(jsonstr.substring(offset, j));
                        else
                            value.result = Integer.parseInt(jsonstr.substring(offset, j));
                    }
                    catch (Throwable ex)
                    {
                        throw new JsonException("Number Parse Error at %d(%s).", offset, jsonstr.substring(offset, j));
                    }
                    endIndex.position = j;
                    break;
                }
            }
        }
        if (endIndex.position == -1)
            throw new JsonException("Json Parse Error at %d.", offset);
        if (res_type == JsonType.IJsonObject || res_type == JsonType.Object ||
            res_type == JsonType.Map || res_type == JsonType.List || res_type == JsonType.None)
            return value;
        else if (res_type == JsonType.Array)
            return ((List<Object>)value).toArray();
        else if (res_type == JsonType.String)
            if (value.result instanceof String || TypeUtils.isValueType(value.result.getClass()))
                return value.toString();
            else
                return jsonstr.substring(offset, endIndex.position);
        else if (res_type == JsonType.ValueType)
        {
            if (value.result instanceof String)
                try {
                    return TypeUtils.parse((String)(value.result), ast);
                } catch (Throwable ex) {
                    throw new JsonException(ex, "Can't parse %s as %s.", value.result, ast);
                }
            else if (TypeUtils.isValueType(value.result.getClass()))
                return value;
        }
        throw new JsonException("Json Parse Error at %d.", offset);
    }

    /// <summary>
    /// 将指定的Json字符串解析为制定类型的对象，若指定的类型为Object，则解析为Dictionary&lt;String, Object>。
    /// </summary>
    /// <param name="jsonstr">要解析的Json字符串</param>
    /// <param name="t">指定的类型</param>
    /// <returns>解析后的对象</returns>
    public static Object ParseAs(String jsonstr, Class t) throws JsonException
    {
        if (StringUtils.isNullOrEmpty(jsonstr))
            return null;
        ParsePosition _t = new ParsePosition();
        return ParseAs(jsonstr.trim(), null, t, 0, _t);
    }

    /// <summary>
    /// 将指定的Json字符串根据指定的对应类型的JsonObjectParserBase对象解析为制定类型的对象，若指定的类型为Object，则解析为Dictionary&lt;String, Object>。
    /// </summary>
    /// <param name="jsonstr">要解析的Json字符串</param>
    /// <param name="t">指定的类型</param>
    /// <param name="parser_dic">指定的对应类型的JsonObjectParserBase对象的集合</param>
    /// <returns>解析后的对象</returns>
    public static Object ParseAs(String jsonstr, Class t, Map<Class, JsonObjectParserBase> parser_dic) throws JsonException
    {
        if (StringUtils.isNullOrEmpty(jsonstr))
            return null;
        ParsePosition _t = new ParsePosition();
        return ParseAs(jsonstr.trim(), null, t, 0, _t);
    }

    static Object newInstance(Class type) throws JsonException {
        try {
            return type.newInstance();
        } catch (Throwable ex) {
            throw new JsonException(ex, "Can't create new instance of %s.", type);
        }
    }
}
