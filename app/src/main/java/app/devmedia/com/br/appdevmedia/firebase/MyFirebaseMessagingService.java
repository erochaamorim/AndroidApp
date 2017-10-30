package app.devmedia.com.br.appdevmedia.firebase;

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import app.devmedia.com.br.appdevmedia.MainActivity;
import app.devmedia.com.br.appdevmedia.entidades.ProdutoNotification;
import app.devmedia.com.br.appdevmedia.notification.NotificationUtil;

/**
 * Created by erick.amorim on 25/10/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private NotificationUtil notificationUtil = new NotificationUtil(getApplicationContext() );

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> dados = remoteMessage.getData();
        String titulo  = dados.get("titulo");
        String message  = dados.get("message");
        String data  = dados.get("data");
        String idProduto  = dados.get("id");
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        ProdutoNotification produtoNotification = new ProdutoNotification(Integer.parseInt(idProduto), titulo, message, data);
        i.putExtra("nf_produto", produtoNotification);
        notificationUtil.showSmallNotificationMsg(titulo, message, data, i);

    }

}
