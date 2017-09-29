package app.devmedia.com.br.appdevmedia.async;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by erick.amorim on 29/09/2017.
 */

public class AsyncUsuarioHttpClient {

    private static final String BASE_URL = "http://172.16.2.56:8080/lojavirtual-web/rest/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        client.get(getAbsoluteUrl(url), params, responseHandler);

    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        client.post(getAbsoluteUrl(url), params, responseHandler);

    }

    private static String getAbsoluteUrl(String relativeUrl) {

        return BASE_URL + relativeUrl;

    }

}
