package com.example.notificationwithbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManger;
    private EditText editTextTitle;
    private EditText editTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManger=NotificationManagerCompat.from(MainActivity.this);
        editTextTitle=findViewById(R.id.edit_text_title);
        editTextMessage=findViewById(R.id.edit_Message);
    }
    public void SendonChannel1(View view) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();          //class we want to open
//open the activity
        Intent activityIntent=new Intent(MainActivity.this,MainActivity.class);
     //wrapper around normal intent end over notification manager and allow us to execute the intent
                                                                                                                      //Flag: what happen we recreate this pending intent with new intent

        PendingIntent contentIntent=PendingIntent.getActivity(MainActivity.this,0,activityIntent,0);

       Intent broadcastIntent=new Intent(MainActivity.this,NotificationReceiver.class);
       broadcastIntent.putExtra("ToastMessage",message);
PendingIntent actionIntent=PendingIntent.getBroadcast(MainActivity.this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);//flag update current mean we create pending intent it create extra from intent

        Notification notification=new NotificationCompat.Builder(this,App.Channel_1_ID).setSmallIcon(R.drawable.ic_baseline_notifications_24).

                setContentTitle(title).setContentText(message).
                setPriority(NotificationCompat.PRIORITY_HIGH).
                setCategory(NotificationCompat.CATEGORY_MESSAGE).
                setColor(Color.BLUE).
                setStyle(new NotificationCompat.InboxStyle().
                        addLine("This is Line 1")
                        .addLine("This is Line 2")
                        .addLine("This is Line 3")
                        .addLine("This is Line 4")
                        .addLine("This is Line 5")
                        .addLine("This is Line 6")
                        .addLine("This is Line 7").

                        setBigContentTitle("Big Content Title").
                        setSummaryText("Summary Text")).
                setContentIntent(contentIntent).
                setOnlyAlertOnce(true). //only make sound or vibrate once when first time notification recieve after than ohter notification dont do any sound or vibrate
                setAutoCancel(true).//when we click on the notification it get cancel
                addAction(R.mipmap.ic_launcher,"Toast",actionIntent).
                build();
        notificationManger.notify(1,notification);




    }
    public void SendonChannel2(View view) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();          //class we want to open
//open the activity
        Intent activityIntent=new Intent(MainActivity.this,MainActivity.class);
        //wrapper around normal intent end over notification manager and allow us to execute the intent
        //Flag: what happen we recreate this pending intent with new intent

        PendingIntent contentIntent=PendingIntent.getActivity(MainActivity.this,0,activityIntent,0);

        Intent broadcastIntent=new Intent(MainActivity.this,NotificationReceiver.class);
        broadcastIntent.putExtra("ToastMessage",message);
        PendingIntent actionIntent=PendingIntent.getBroadcast(MainActivity.this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);//flag update current mean we create pending intent it create extra from intent
//Large icon in notification using bitmap
        Bitmap largeIcon= BitmapFactory.decodeResource(getResources(),R.drawable.p);
        Notification notification=new NotificationCompat.Builder(this,App.Channel_2_ID).setSmallIcon(R.drawable.ic_baseline_notification_important_24).

                setContentTitle(title).
                setContentText(message).
                setLargeIcon(largeIcon).
                setStyle(new NotificationCompat.BigTextStyle().
                        bigText(getString(R.string.message)).
                        setBigContentTitle("Big Content Title").
                        setSummaryText("Summary Text")).
                setPriority(NotificationCompat.PRIORITY_HIGH).
                setCategory(NotificationCompat.CATEGORY_MESSAGE).
                setColor(Color.BLUE).
                setContentIntent(contentIntent).
               // setOnlyAlertOnce(true). //only make sound or vibrate once when first time notification recieve after than ohter notification dont do any sound or vibrate
                setAutoCancel(true).//when we click on the notification it get cancel
                addAction(R.mipmap.ic_launcher,"Toast",actionIntent).
                build();
        notificationManger.notify(2,notification);
    }
}