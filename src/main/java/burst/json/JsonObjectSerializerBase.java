package burst.json;

/// <summary>
/// 表示为指定类型定义了Json编码相关方法。
/// </summary>
public abstract class JsonObjectSerializerBase
{
    /// <summary>
    /// 将对象编码为Json字符串。
    /// </summary>
    /// <returns>编码后的Json字符串</returns>
    public abstract String SerializeToJsonString(Object ori, int layer, JsonFormatOption format_option);
}
