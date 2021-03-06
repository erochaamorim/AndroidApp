package app.devmedia.com.br.appdevmedia.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import app.devmedia.com.br.appdevmedia.LoginActivity;
import app.devmedia.com.br.appdevmedia.async.AsyncUsuarioHttpClient;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * Created by erick.amorim on 25/10/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Refreshed token: ", refreshedToken);

        SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String login = preferences.getString("login", null);
        String senha = preferences.getString("senha", null);
        if(login != null && senha != null) {

            sendRegistrationToServer(login, refreshedToken);

        } else {

            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            return;

        }

    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String login, String token) {
        RequestParams params = new RequestParams();
        params.put("login", login);
        params.put("token", token);
        AsyncUsuarioHttpClient.post("fcm/sendToken", params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String resultado, Throwable throwable) {

                Log.e("Token reg error", "HttpCode: " + statusCode, throwable);
                Toast.makeText(MyFirebaseInstanceIDService.this, "Erro no registro do token: " + resultado, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String resultado) {
                if(Boolean.valueOf(resultado) ) {

                    Toast.makeText(MyFirebaseInstanceIDService.this, "Registro do token: " + resultado, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(MyFirebaseInstanceIDService.this, "Erro no registro do token.", Toast.LENGTH_SHORT).show();

                }
            }

        });
    }
}
