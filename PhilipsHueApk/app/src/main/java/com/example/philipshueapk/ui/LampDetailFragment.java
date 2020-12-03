package com.example.philipshueapk.ui;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.philipshueapk.HttpHandler;
import com.example.philipshueapk.R;
import com.example.philipshueapk.lamp.LampProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                node.put("on",b);
                try {
                    String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
                    HttpHandler.INSTANCE.setLampState(id,json);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        TextView nameView = getView().findViewById(R.id.lamp_detail_name);
        nameView.setText(lamp.getName());

        ColorPickerView colorPickerView = getView().findViewById(R.id.colorPickerView);
        colorPickerView.subscribe((color,fromUser,propagate) -> {

            String hexColor = String.format("#%06X", (0xFFFFFF & color));
            Log.d(TAG, "Color: " + ", was: " + color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d(TAG, "Color changed! " + hexColor);

            }
        });
    }

    /**
     *
     * @param colorStr e.g. "#FFFFFF"
     * @return
     */
    public static Color hex2Rgb(String colorStr) {
        int r = Integer.valueOf(colorStr.substring( 1, 3 ), 16);
        int g = Integer.valueOf(colorStr.substring( 3, 5 ), 16);
        int b = Integer.valueOf( colorStr.substring( 5, 7 ), 16 );
        return null;
    }


}