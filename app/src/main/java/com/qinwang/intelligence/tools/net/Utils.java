package com.qinwang.intelligence.tools.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;

import javax.net.ssl.HttpsURLConnection;

public class Utils {

    private static final int DELAY_TIME = 5 * 1000;
    private static final int SUCCESS_RESPONSE_CODE = 200;

    /**
     * 设置GET请求
     * @param path
     * @return
     * @throws Exception
     */
    public static HttpURLConnection getSet(String path) throws Exception{
//        HttpURLConnection connection = (HttpURLConnection)new URL(path).openConnection();
        HttpURLConnection connection = requestJudgment(path);
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(DELAY_TIME);
        return connection;
    }

    /**
     * 设置POST请求
     * @param path
     * @return
     * @throws Exception
     */
    public static HttpURLConnection postSet(String path) throws Exception{
//        HttpURLConnection connection = (HttpURLConnection)new URL(path).openConnection();
        HttpURLConnection connection = requestJudgment(path);
        connection.setRequestMethod("POST");
        connection.setReadTimeout(DELAY_TIME);
        connection.setConnectTimeout(DELAY_TIME);
        connection.setDoInput(true);        //设置运行输入
        connection.setDoOutput(true);       //设置运行输出:
        connection.setUseCaches(false);     //Post方式不能缓存,需手动设置为false
        return connection;
    }

    /**
     * GET请求
     * @param connection
     * @return
     * @throws Exception
     */
    public static byte[] getUtil(HttpURLConnection connection) throws Exception{
        byte[] bytes = null;
        if (connection.getResponseCode() != SUCCESS_RESPONSE_CODE){
            throw new RuntimeException("请求失败");
        }
        bytes = StreamTool.read(connection.getInputStream());
        return bytes;
    }

    /**
     * POST请求
     * @param connection
     * @param map
     * @return
     * @throws Exception
     */
    public static byte[] postUtil(HttpURLConnection connection, LinkedHashMap<String, String> map) throws Exception{
        byte[] bytes = null;
        String data = StreamTool.Submit(map);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(data.getBytes());
        outputStream.flush();
        if (connection.getResponseCode() != SUCCESS_RESPONSE_CODE){
            throw new RuntimeException("请求失败");
        }
        InputStream inputStream = connection.getInputStream();      // 获取响应的输入流对象
        bytes = StreamTool.read(inputStream);
        return bytes;
    }

    /**
     * 判断是HTTP请求还是HTTPS请求
     * @param path
     * @return
     * @throws Exception
     */
    public static HttpURLConnection requestJudgment(String path) throws  Exception{
        HttpURLConnection httpURLConnection;
        HttpsURLConnection httpsURLConnection;
        URL url = new URL(path);
        if (url.getProtocol().toLowerCase().equals("https")){               //url.getProtocol()获取协议；toLowerCase()将字符串转换为小写
            SkipValidation.trustAllHosts();
            httpsURLConnection = (HttpsURLConnection)url.openConnection();  //openConnection()获取httpsURLConnection对象实例
            httpsURLConnection.setHostnameVerifier(SkipValidation.DO_NOT_VERIFY);
            httpURLConnection = httpsURLConnection;
        }else {
            httpURLConnection = (HttpURLConnection)url.openConnection();
        }
        return httpURLConnection;
    }
}
