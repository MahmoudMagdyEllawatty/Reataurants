package comhala.halawyat.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import comhala.halawyat.R;
import comhala.halawyat.ui.Notifications;

/**
 * Created by AHMED on 25/01/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    JSONObject jsonObject;
    private static final String TAG = "MyFirebaseMsgService";
    String mess = null;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e("dgg",remoteMessage.getData()+"g");

        try {
            if (!TextUtils.isEmpty(remoteMessage.getNotification().getBody())){
                mess=remoteMessage.getNotification().getBody();

            }
            else {
                mess = remoteMessage.getData().get("message");

            }
        }
        catch (Exception c){
            mess = remoteMessage.getData().get("message");

        }




        showNotification(getString(R.string.app_name), mess);

    }


    /**
     * Create and show a simple notification containing the received GCM message.
     */


    void showNotification(String title, String content) {
        Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification_snd);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.logog))
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[]{1000, 1000})
                .setSound(sound)
                .setAutoCancel(true);
        mBuilder.setSmallIcon(R.drawable.logog);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build();
            NotificationChannel channel = new NotificationChannel("default",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
            channel.setSound(sound, audioAttributes);

            mNotificationManager.createNotificationChannel(channel);

        }

        Intent intent;

        intent = new Intent(getApplicationContext(), Notifications.class);
        intent.putExtra("mss", mess);
//        MainActivity.tv_vname.setText(mess);
//        MainActivity.mess=mess;

        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }


}
