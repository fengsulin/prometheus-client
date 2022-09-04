package com.lin.prometheus.prometheus.builder;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private final static Pattern namedFormatPattern = Pattern.compile("#\\{(?<key>.*?)}");
    public static String namedFormat(final String format, Map<String,? extends Object> kvs){
        final StringBuffer buffer = new StringBuffer();
        final Matcher match = namedFormatPattern.matcher(format);
        while (match.find()){
            final String key = match.group("key");
            final Object value = kvs.get(key);
            if (value != null)
                match.appendReplacement(buffer,value.toString());   // 用map集合中找到的value替换原字符串中匹配的字符串
            else if (kvs.containsKey(key))
                match.appendReplacement(buffer,null);
            else
                match.appendReplacement(buffer,"");
        }
        // 该方法是将匹配完后，后面可能还有未符合匹配规则的字符串，将剩余字符串加到buffer
        match.appendTail(buffer);
        return buffer.toString();
    }
}
