package com.jinyeob.sendstop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "AAAAFAAGEWU:APA91bH2Rfck_sGSUuOt9cqePSURgxuXRBc04fAWPSgKSnFXh3SBRXexVIzoqmN7uU9CDd5u95gzm6BNUOSYRUbUcHf4cWyLDAT9oTOFhtan-8q10XgWpqIbyfzD059FVLKtHcw9FQoK";
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPostToFCM();
            }
        });
    }

    private void sendPostToFCM() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // FMC 메시지 생성 start
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("body", "Stop");
                    notification.put("title", "제목");
                    root.put("notification", notification);
                    root.put("to", "fhFGIfVNEzw:APA91bHM_Hm-D0gyqqTE70ZG0zGCP9IxjUHxfRxd5ILZE6hVvqrEsNm8wdZlMXe2KH9I4nnPqr0oYzREeUWCh7QgGrO_WgGDeR-X_JiyoFJSp595idJTpK3tfTQbYb7BYh6iKuePIY35");
                    // FMC 메시지 생성 end

                    URL Url = new URL(FCM_MESSAGE_URL);
                    HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = conn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    conn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
