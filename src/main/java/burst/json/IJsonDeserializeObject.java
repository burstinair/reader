package burst.json;

/// <summary>
/// 表示自定义了Json解析相关方法。
/// </summary>
public interface IJsonDeserializeObject
{
    /// <summary>
    /// 使用解析Json字符串得到的字段给对象赋值。
    /// </summary>
    /// <param name="fieldName">字段名</param>
    /// <param name="value">值</param>
    void SetFieldValue(String fieldName, Object value);

    /// <summary>
    /// 获取指定字段的类型。
    /// </summary>
    /// <param name="fieldName">字段名</param>
    /// <returns>该字段的类型</returns>
    Class GetFieldType(String fieldName);
}
