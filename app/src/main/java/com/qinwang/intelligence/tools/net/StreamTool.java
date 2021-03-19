package com.qinwang.intelligence.tools.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;

public class StreamTool {
    private static final int BYTE_LENGTH = 1024;
    private static final String ENCODE = "UTF-8";

    /**
     * 从LinkedHashMap获取数据处理得到POST请求所需要传递的参数
     * @param map
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String Submit(LinkedHashMap<String, String> map) throws UnsupportedEncodingException {
        String firstKey = null;
        String firstValue = null;
        String Info = null;
        for (String key : map.keySet()){
            if (firstKey == null && firstValue == null){
                firstKey = key;
                firstValue = map.get(firstKey);
                Info = firstKey + "=" +
                        URLEncoder.encode(firstValue, ENCODE);
            }else {
                Info = Info + "&" + key + "=" +
                        URLEncoder.encode(map.get(key), ENCODE);
            }
        }
        return Info;
    }

    /**
     * 从数据流中读取数据
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] read(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();       // 创建字节输出流对象
        byte[] buffer = new byte[BYTE_LENGTH];
        int len = 0;
        // 按照缓冲区的大小，循环读取
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer,
                    0,
                    len);
        }
        inputStream.close();
        outputStream.close();
        return outputStream.toByteArray();
    }
}
