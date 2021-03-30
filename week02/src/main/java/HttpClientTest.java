
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientTest {
    public static void main(String[] args) {
        try {
            String response = getHttpTest("http://127.0.0.1:8801");
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getHttpTest(String url) throws IOException {
        String result = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = response.getStatusLine().getStatusCode() + "|" +
                        responseEntity.getContentLength() + "|" +
                        EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        } finally {
            // 释放资源
            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                response.close();
            }
        }
        return result;
    }

}