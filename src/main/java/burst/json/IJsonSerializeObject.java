package burst.json;

/// <summary>
/// 表示自定义了Json编码相关方法。
/// </summary>
public interface IJsonSerializeObject
{
    /// <summary>
    /// 将对象编码为Json字符串。
    /// </summary>
    /// <returns>编码后的Json字符串</returns>
    String SerializeToJsonString(int layer, JsonFormatOption format_option);
}