package com.example.philipshueapk.ui;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.philipshueapk.HttpHandler;
import com.example.philipshueapk.R;
import com.example.philipshueapk.lamp.LampProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import top.defaults.colorpicker.ColorObserver;
import top.defaults.colorpicker.ColorPickerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LampDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LampDetailFragment extends Fragment {

    private final String TAG = LampDetailFragment.class.getCanonicalName();
    private int id = 0;
    private LampProduct lamp;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LampDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LampDetailFragment newInstance(String param1, String param2) {
        LampDetailFragment fragment = new LampDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lamp_detail, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle b = getArguments();
        id = b.getInt("id");
        LampProduct lamp = (LampProduct) b.getSerializable("lamp");
        ToggleButton toggleButton = getView().findViewById(R.id.detail_togglebutton);
        toggleButton.setChecked(lamp.getState().getOn());
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                lamp.getState().setOn(b);
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode node = mapper.createObjectNode();
                node.put("on", b);
                try {
                    String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
                    HttpHandler.INSTANCE.setLampState(id, json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });

        EditText nameView = getView().findViewById(R.id.lamp_detail_name);
        nameView.setText(lamp.getName());

        nameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                HttpHandler.INSTANCE.renameLamp(id, nameView.getText().toString());
            }
        });

        ColorPickerView colorPickerView = getView().findViewById(R.id.colorPickerView);
        Log.d(TAG, "Current colour is: " + Color.valueOf(lamp.getState().calculateRGBColor()).toString());
        colorPickerView.setInitialColor(lamp.getState().calculateRGBColor());

        colorPickerView.subscribe((color, fromUser, propagate) -> {
            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            Log.d(TAG, "Color: " + ", was: " + color);


            Color color1 = Color.valueOf(color);
            float[] hsb = calculateHSBColor((int) (color1.red()*255),(int) (color1.green()*255),(int) (color1.blue()*255));
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            node.put("hue", hsb[0]);
            node.put("sat", hsb[1]);
            node.put("bri", hsb[2]);


            try {
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
                HttpHandler.INSTANCE.setLampState(id, json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

    private float[] calculateHSBColor(int red, int green, int blue) {
        float[] hsb = new float[3];
        Color.RGBToHSV(red, green, blue, hsb);
        Log.d(TAG, "rgb: " + red + ", " + green + ", " + blue);
        hsb[0] = (Math.round((hsb[0] / 360) * 65535));
        hsb[1] = (Math.round(hsb[1] * 255));
        hsb[2] = (Math.round(hsb[2] * 254));

        return hsb;
    }


}