package burst.json;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-24
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */

interface FormatDelegate {
    String proceed(SkipHolder skip) throws JsonException;
}
