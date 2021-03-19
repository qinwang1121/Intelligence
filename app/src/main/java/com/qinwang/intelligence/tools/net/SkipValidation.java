package com.qinwang.intelligence.tools.net;

import android.util.Log;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SkipValidation {

    /**
     * 将所有验证结果都设为true
     */
    public static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 不检查任何证书
     */
    public static void trustAllHosts(){
        final String TAG = "trustAllHost";
        //创建信任管理器
        TrustManager[] trustManagers = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        //客户端受信任
                        Log.i(TAG, "checkClientTrusted");
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        //服务器受信任
                        Log.i(TAG,"checkServerTrusted");
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
        //安装信任管理器
        try{
            // 获取一个SSLContext实例
            SSLContext sslContext = SSLContext.getInstance("TLS");
            // 初始化SSLContext实例
            sslContext.init(null,
                    trustManagers,
                    new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());   //信任所有证书
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
