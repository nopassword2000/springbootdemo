package com.restpool;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.impl.io.DefaultHttpResponseParserFactory;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class HttpCli {

    static PoolingHttpClientConnectionManager manager = null;

    static CloseableHttpClient httpClient;

    public static synchronized  CloseableHttpClient getHttpClient() throws Exception{

        if (httpClient != null){
            return httpClient;
        }

        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
        HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", /*SSLConnectionSocketFactory.getSystemSocketFactory()*/sslConnectionSocketFactory).build();

        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(
                DefaultHttpRequestWriterFactory.INSTANCE,
                DefaultHttpResponseParserFactory.INSTANCE);

        DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;

        manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connectionFactory, dnsResolver);

        SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        manager.setDefaultSocketConfig(defaultSocketConfig);

        manager.setMaxTotal(300);
        manager.setDefaultMaxPerRoute(200);
        manager.setValidateAfterInactivity(5 * 1000);

        RequestConfig defalutRequestConfig = RequestConfig.custom()
                .setConnectTimeout(2 * 1000)
                .setSocketTimeout(5 * 1000)
                .setConnectionRequestTimeout(2000)
                .build();

        httpClient = HttpClients.custom()
                .setConnectionManager(manager)
                .setConnectionManagerShared(false)
                .evictIdleConnections(60, TimeUnit.SECONDS)
                .evictExpiredConnections()
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                .setDefaultRequestConfig(defalutRequestConfig)
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                .setRetryHandler( new DefaultHttpRequestRetryHandler(0, false))
                .setSSLContext(sslContext)
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                 try {
                     httpClient.close();
                 }catch ( IOException ex){
                     ex.printStackTrace();
                 }
            }
        });

        return httpClient;
    }
}
