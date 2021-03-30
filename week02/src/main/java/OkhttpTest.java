import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkhttpTest {
    public static void main(String[] args) {

        System.out.println(okHttpGet("http://127.0.0.1:8801"));
    }

    private static String okHttpGet(String url){
        String result = "";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;
    }
}
