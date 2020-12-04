package com.example.philipshueapk;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * As the name of this class suggests, this class saves data such as the lamp names.
 */
public class DataSaver {
    final static String prefName = "LampNames";
    final static String TAG = DataSaver.class.getCanonicalName();

    /**
     * This method saves the lamp names into a shared pref.
     * This method gets called when the user gets off the application, so it triggers an OnPause()
     * @param context the context of a fragment and/or mainActivity
     */
    public static void saveLampNames(Context context) {
        Log.d(TAG,"Saving lamp names");
        SharedPreferences.Editor editor = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit();
        String[] names = HttpHandler.INSTANCE.getLampNames();
        for (int i = 0; i < names.length; i++) {
            String nameToPut = names[i] == null ? "" : names[i];
            editor.putString("name" + (i + 1), nameToPut);
        }
        editor.apply();
    }

    /**
     * The loader for all the current lamp names.
     * @param context the context of a fragment and/or mainActivity
     */
    public static void loadLampNames(Context context) {
        Log.d(TAG,"Loading lamp names");
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        String[] names = HttpHandler.INSTANCE.getLampNames();
        for (int i = 0; i < names.length; i++) {
            String lampName = sharedPreferences.getString("name" + (i+1),"");
            if (!lampName.equals("")) {
                names[i] = lampName;
                HttpHandler.INSTANCE.renameLamp(i+1,lampName);
            }
        }
    }

    /**
     * This method clears the shared pref of the current lamp names.
     * @param context the context of a fragment and/or mainActivity
     */
    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


}
