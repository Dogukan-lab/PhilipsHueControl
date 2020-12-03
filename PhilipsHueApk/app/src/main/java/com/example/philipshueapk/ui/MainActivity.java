package com.example.philipshueapk.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.philipshueapk.DataSaver;
import com.example.philipshueapk.HttpHandler;
import com.example.philipshueapk.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpHandler.INSTANCE.init();

        // load the user specified lamp names
        DataSaver.loadLampNames(this);

    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"saving lamp names");
        // save the user specified lamp names when the app gets closed or the user changes to another app
        DataSaver.saveLampNames(this);
    }
}