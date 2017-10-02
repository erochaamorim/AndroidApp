package app.devmedia.com.br.appdevmedia.async;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import app.devmedia.com.br.appdevmedia.util.Constantes;

/**
 * Created by erick.amorim on 29/09/2017.
 */

public class AsyncUsuarioHttpClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        client.get(getAbsoluteUrl(url), params, responseHandler);

    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        client.post(getAbsoluteUrl(url), params, responseHandler);

    }

    private static String getAbsoluteUrl(String relativeUrl) {

        return Constantes.URL_WS_BASE + relativeUrl;

    }

}
