package com.example.philipshueapk.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.app.FragmentManager;
import android.os.Bundle;

import com.example.philipshueapk.HttpHandler;
import com.example.philipshueapk.R;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpHandler.INSTANCE.initHttpClient();
        HttpHandler.INSTANCE.getAllLights();

        // use without navcontroller: https://stackoverflow.com/questions/53902494/navigation-component-cannot-find-navcontroller
        // can also be done in onclick  Navigation.findNavController(view).navigate(action);

        /* putting something in an action to a destination:
            Bundle bundle = new Bundle();
            bundle.putString("amount", amount);
            Navigation.findNavController(view).navigate(R.id.confirmationAction, bundle);

            and then retrieving it:
            TextView tv = view.findViewById(R.id.textViewAmount);
            tv.setText(getArguments().getString("amount"));
         */
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        NavController navController = navHostFragment.getNavController();



    }
}