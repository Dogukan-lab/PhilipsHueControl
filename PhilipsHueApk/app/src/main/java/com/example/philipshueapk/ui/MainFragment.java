package com.example.philipshueapk.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.philipshueapk.DunnyData;
import com.example.philipshueapk.R;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements HueAdapter.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView lampRecycler;
    private View rootView;
    private ArrayList<DunnyData> testList;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_main, container, false);
        this.lampRecycler = this.rootView.findViewById(R.id.mainRecycler);
        this.lampRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        this.lampRecycler.setHasFixedSize(true);

        this.testList = new ArrayList<>();
        testList.add(new DunnyData());
        testList.add(new DunnyData());
        testList.add(new DunnyData());

        HueAdapter hueAdapter = new HueAdapter(getContext(), testList, this);
        this.lampRecycler.setAdapter(hueAdapter);
        return this.rootView;
    }

    @Override
    public void OnItemClick(int position) {
//        Navigation.findNavController(this.rootView);
        Log.d(TAG, "OnItemClick: WEJOW ITEM CLICKED!!!! OP POSITIE: " + position);
    }
}