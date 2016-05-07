package com.edwin.example.checknetworkstate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTv_state_mobile;
    private TextView mTv_state_wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv_state_mobile = (TextView) findViewById(R.id.tv_network_state_mobile);
        mTv_state_wifi = (TextView) findViewById(R.id.tv_network_state_wifi);

    }

    public void checkInternet(View view) {

        ConnectivityManager connect = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        State state = connect.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        ConnectivityManager connect1 = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        State state1 = connect1.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        mTv_state_mobile.setText(state.toString());

        mTv_state_wifi.setText(state1.toString());

        if (state == State.CONNECTING || state == State.CONNECTED) {
            return;
        }
        if (state1 == State.CONNECTING || state1 == State.CONNECTED) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("当前网络无连接,是否跳转到网络设置界面?");

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

            }
        });

        builder.create().show();

    }


}
