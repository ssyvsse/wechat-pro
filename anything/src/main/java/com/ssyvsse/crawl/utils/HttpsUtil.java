package com.ssyvsse.crawl.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.commons.collections.MapUtils;
import org.apache.http.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author llb
 *
 * @Date 2018年1月18日
 */
public class HttpsUtil {
	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	private static SSLConnectionSocketFactory sslsf = null;
	private static PoolingHttpClientConnectionManager cm = null;
	private static SSLContextBuilder builder = null;
	static {
		try {
			builder = new SSLContextBuilder();
			// 全部信任 不做身份鉴定
			builder.loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			});
			sslsf = new SSLConnectionSocketFactory(builder.build(),
					new String[] { "TLSv1.2" }, null, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register(HTTP, new PlainConnectionSocketFactory()).register(HTTPS, sslsf).build();
			cm = new PoolingHttpClientConnectionManager(registry);
			cm.setMaxTotal(200);// max connection
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	     * httpClient post请求
	     * @param url 请求url
	     * @param header 头部信息
	     * @param param 请求参数 form提交适用
	     * @param entity 请求实体 json/xml提交适用
	     * @return 可能为空 需要处理
	     * @throws Exception
	     *
	     */
	    public static String post(String  url, Map<String, String> header, Map<String, String> param, HttpEntity entity) throws Exception {
	        String result = "";
	        CloseableHttpClient httpClient = null;
	        try {
	            httpClient = getHttpClient();
	            HttpPost httpPost = new HttpPost(url);
	            // 设置头信息
	            if (MapUtils.isNotEmpty(header)) {
	                for (Map.Entry<String, String> entry : header.entrySet()) {
	                    httpPost.addHeader(entry.getKey(), entry.getValue());
	                }
	            }
	            // 设置请求参数
	            if (MapUtils.isNotEmpty(param)) {
	                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	                for (Map.Entry<String, String> entry : param.entrySet()) {
	                    //给参数赋值
	                    formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
	                }
	                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
	                httpPost.setEntity(urlEncodedFormEntity);
	            }
	            // 设置实体 优先级高
	            if (entity != null) {
	                httpPost.setEntity(entity);
	            }
	            HttpResponse httpResponse = httpClient.execute(httpPost);
	            int statusCode = httpResponse.getStatusLine().getStatusCode();
	            if (statusCode == HttpStatus.SC_OK) {
	                HttpEntity resEntity = httpResponse.getEntity();
	                result = EntityUtils.toString(resEntity);
	            } else {
	            	readHttpResponse(httpResponse);
	            }
	        } catch (Exception e) {
	        	throw e;
	        } finally {
	            if (httpClient != null) {
	                httpClient.close();
	            }
	        }
	        return result;
	    }

	public static CloseableHttpClient getHttpClient() throws Exception {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm)
				.setConnectionManagerShared(true).build();
		return httpClient;
	}

	public static String readHttpResponse(HttpResponse httpResponse) throws ParseException, IOException {
		StringBuilder builder = new StringBuilder();
		// 获取响应消息实体
		HttpEntity entity = httpResponse.getEntity();
		// 响应状态
		builder.append("status:" + httpResponse.getStatusLine());
		builder.append("headers:");
		HeaderIterator iterator = httpResponse.headerIterator();
		while (iterator.hasNext()) {
			builder.append("\t" + iterator.next());
		}
		// 判断响应实体是否为空
		if (entity != null) {
			String responseString = EntityUtils.toString(entity);
			builder.append("response length:" + responseString.length());
			builder.append("response content:" + responseString.replace("\r\n", ""));
		}
		return builder.toString();
	}

	
	public static String crawl6hcPost(int year) {
		String url = "https://1680660.com/smallSix/findSmallSixHistory.do";
		Map<String, String> header = new HashMap<String,String>();
		header.put("Host", "1680660.com");
		header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:57.0) Gecko/20100101");
		header.put("Accept", "application/json, text/javascript, */*; q=0.01");
		header.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		header.put("Accept-Encoding", "gzip, deflate, br");
		header.put("Referer", "https://6hch.com/html/kaihistory.html");
		header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		header.put("Origin", "https://6hch.com");
		header.put("Connection","close");
		header.put("Pragma", "no-cache");
		header.put("Cache-Control", "no-cache");
		Map<String, String> param = new HashMap<String,String>();
		param.put("year", ""+year);
		param.put("type", "1");
		String result = null;
		try {
			result = HttpsUtil.post(url, header, param, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
