package com.iee.filedownload.common;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.util.StringUtils;import java.io.UnsupportedEncodingException;import java.text.MessageFormat;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.*;/** * description: * author:          包维君 * createDate:      2017/9/25 */public class StringHelper {    //region 空字符串处理    public static String getEmpty() {        return "";    }    /**     * 判断是否为空字符串（NULL、Empty、Blank）     *     * @param s     * @return     */    public static boolean isEmpty(String s) {        if (null == s || 0 == s.length() || 0 == s.trim().length()) {            return true;        }        return false;    }    /**     * 将NULL转为空字符串     *     * @param s     * @return     */    public static String asEmpty(String s) {        return s == null ? StringHelper.getEmpty() : s.trim();    }    //endregion    public static byte[] getBytes(String s) throws UnsupportedEncodingException {        return s.getBytes("UTF-8");    }    //region 首字母大小写    private static String capitalize(String s, boolean isUpperCase) {        if (isEmpty(s)) {            return s;        }        String first = substring(s, 0, 1);        String remain = s.substring(1);        first = isUpperCase ? first.toUpperCase() : first.toLowerCase();        return first + remain;    }    /**     * 首字母大写     *     * @param s     * @return     */    public static String toUpperFirst(String s) {        return capitalize(s, true);    }    /**     * 首字母小写     *     * @param s     * @return     */    public static String toLowerFirst(String s) {        return capitalize(s, false);    }    //endregion    //region 截断和补齐    /**     * 截断字符串     *     * @param s     * @param length     * @return     */    public static String truncate(String s, int length) {        return s.length() > length ? substring(s, 0, length) + "···" : s;    }    //从开始位置截断指定长度字符串    public static String substring(String s, int beginIndex, int length) {        return s.substring(beginIndex, beginIndex + length);    }    private static String pad(String s, int length, String pad, boolean isLeft) {        if (s.length() < length) {            int len = length - s.length();            for (int i = 0; i < len; i++) {                s = isLeft ? (pad + s) : (s + pad);            }        }        return s;    }    /**     * 补齐左边字符串     *     * @param s     * @param length     * @param pad     * @return     */    public static String padLeft(String s, int length, String pad) {        return pad(s, length, pad, true);    }    /**     * 补齐右边字符串     *     * @param s     * @param length     * @param pad     * @return     */    public static String padRight(String s, int length, String pad) {        return pad(s, length, pad, false);    }    /**     * 剔除开始位置字符串     *     * @param s     * @param start     * @return     */    public static String trimStart(String s, String start) {        return s.startsWith(start) ? s.substring(start.length()) : s;    }    /**     * 剔除结束位置字符串     *     * @param s     * @param end     * @return     */    public static String trimEnd(String s, String end) {        return s.endsWith(end) ? substring(s, 0, s.length() - end.length()) : s;    }    //endregion    //region 替换查找    public static String replace(String s, String newChar, String... oldChars) {        for (String old : oldChars) {            s = s.replace(old, newChar);        }        return s;    }    public static String replaceAll(String s, String newChar, String... oldChars) {        for (String old : oldChars) {            s = s.replaceAll(old, newChar);        }        return s;    }    //endregion    public static String join(String join, List list) {        return String.join(join, list);    }    /**     * 字符串包裹     *     * @param s     * @param wrap     * @return     */    public static String wrap(String s, String wrap) {        return wrap + s + wrap;    }    public static String wrap(String s, String left, String right) {        return left + s + right;    }    /**     * 按分隔符来拼接路径     *     * @param separator     * @param repeatSeparator     * @param paths     * @return     */    public static String combine(String separator, String repeatSeparator, String... paths) {        String basePath = StringHelper.trimEnd(paths[0], separator);        String path = "";        if (paths.length > 1) {            for (int i = 1; i < paths.length; i++) {                path = path + separator + paths[i];            }        }        path = path.replaceAll(repeatSeparator, separator);        return basePath + path;    }    public static Logger logger = LoggerFactory.getLogger(StringUtils.class);    public static String resultEmpty(String string){        return null != string && !"".equals(string.trim())?string:"";    }    /**     * 请参考{@link MessageFormat#format(String, Object...)}，例子：     *     * <p/>     * <code>format("Hello ''{0}'', you are welcome!","xiaoming")</code><br/>     *     * <p/>     * 注意如果要使用单引号的话，必须使用两个连续的单引号表示     */    public static String format(String format,Object... args){        return MessageFormat.format(format, args);    }    /**     * @return 指定的字符串是否为<code>null</code>或者空字符串（包含空白字符也被认为是空字符串）     */    public static boolean isNotEmpty(String string){        return null != string && !"".equals(string.trim());    }    public static String join(Object[] objects){        return join(objects,",");    }    public static String join(Object[] objects,char seperator){        return join(objects,String.valueOf(seperator));    }    public static String join(Object[] objects,String seperator){        if(null == objects || objects.length == 0){            return "";        }        if(objects.length == 1){            return null == objects[0]?"":objects[0].toString();        }        StringBuilder string = new StringBuilder();        for(Object object : objects){            string.append(seperator).append(object);        }        return string.substring(seperator.length()).toString();    }    public static int  stringToInt (String str,int len)    {        return  Integer.parseInt(str,len);    }    public static String[] split(String string){        return split(string,",");    }    /**     * 和{@link String#split(String)}相同的功能，只是分隔字符串不需要使用正则表达式的写法     */    public static String[] split(String string,String seperator){        if(null == string){            return new String[]{};        }        StringTokenizer tokens = new StringTokenizer(string.trim(), seperator, false);        String[] result = new String[ tokens.countTokens() ];        int i=0;        while ( tokens.hasMoreTokens() ) {            result[i++] = tokens.nextToken().trim();        }        return result;    }    /**     * 把字符串转换为大写，如果传入的字符串是<code>null</code>则直接返回<code>null</code>     */    public static String upperCase(String string){        if(null == string){            return null;        }        return string.toUpperCase();    }    public static  String Separator(){        String os = System.getProperty("os.name");        if(os.toLowerCase().startsWith("win"))        {            return "\\\\\\\\";        }        return "";    }    /***     * @author 包维君     * 将string数据转换成 Date     * */    public static Date String2Date(String strDate){        if(StringUtils.isEmpty(strDate))        {            return null;        }        Date date = new Date();        try {            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");            date = sdf.parse(strDate);        } catch (ParseException e) {            e.printStackTrace();        }        return date;    }    /**     * @author 包维君     * 方法名称:transMapToString     * 传入参数:map     * 返回值:String 形如 username'chenziwen^password'1234     * 将map转换json     */    public static String transMapToString(Map<?,?> map)    {        if(null!=map&&map.size()>0)        {            Map.Entry<?,?> entry;            StringBuffer sb = new StringBuffer("{");            for(Iterator<?> iterator = map.entrySet().iterator(); iterator.hasNext();)            {                entry = (Map.Entry<?,?>)iterator.next();                sb.append(entry.getKey().toString());                sb.append( ":" );                sb.append(null==entry.getValue()?"": entry.getValue().toString());                sb.append (iterator.hasNext() ? "," : "");            }            sb.append("}");            return sb.toString();        }else{            return "";        }    }    /**     * @author 包维君     * 对象是否为空     * */    public static Object isNotObjectEmpty(Object obj){        return null!=obj?obj:"";    }    public static String toDoubleCase(String input)    {        if (null != input) {            char[] chars = input.toCharArray();            for (int i = 0; i < chars.length; ++i) {                char c = chars[i];                if (c == ' ')                    chars[i] = 12288;                else if ((c >= '!') && (c <= '~')) {                    chars[i] = (char)(c + 65248);                }            }            return new String(chars);        }        return input;    }    public static String toSingleCase(String input)    {        if (null != input) {            char[] chars = input.toCharArray();            for (int i = 0; i < chars.length; ++i) {                char c = chars[i];                if (c == 12288)                    chars[i] = ' ';                else if ((c >= 65281) && (c <= 65374)) {                    chars[i] = (char)(c - 65248);                }            }            return new String(chars);        }        return input;    }    public static boolean equals(String a, String b)    {        if (null != a)        {            return a.equals(b);        }        return (null == b);    }    public static String replace(String template, String placeholder, String replacement)    {        int loc = template.indexOf(placeholder);        if (loc < 0) {            return template;        }        return template.substring(0, loc) + replacement + replace(template.substring(loc + placeholder.length()), placeholder, replacement);    }    public static String safeGetString(String s)    {        if (null == s) {            return "";        }        return s;    }    public static String safeGetTrimString(String s)    {        if (null == s) {            return "";        }        return s.trim();    }    public static String toString(String[] array, String seperator)    {        StringBuffer buff = new StringBuffer();        int i = 0;        for (i = 0; i < array.length - 1; ++i) {            buff.append(array[i]).append(seperator);        }        buff.append(array[i]);        return buff.toString();    }    public static String[] split(String seperators, String list, boolean include)    {        StringTokenizer tokens = new StringTokenizer(list, seperators, include);        String[] result = new String[tokens.countTokens()];        int i = 0;        while (tokens.hasMoreTokens()) {            result[(i++)] = tokens.nextToken();        }        return result;    }    public static int toInt(String value)    {        int intValue = 0;        try {            intValue = Integer.parseInt(value);        } catch (Throwable e) {        }        return intValue;    }    public static long toLong(String value)    {        long longValue = 0L;        try {            longValue = Long.parseLong(value);        } catch (Throwable e) {        }        return longValue;    }    public static boolean toBoolean(String value, boolean defaultValue)    {        if ("1".equals(value))            return true;        if ("0".equals(value))            return false;        if ("true".equalsIgnoreCase(value))            return true;        if ("false".equalsIgnoreCase(value)) {            return false;        }        return defaultValue;    }}