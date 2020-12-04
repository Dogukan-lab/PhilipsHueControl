package com.example.philipshueapk.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.philipshueapk.HttpHandler;
import com.example.philipshueapk.LampsChangedListener;
import com.example.philipshueapk.R;
import com.example.philipshueapk.lamp.LampProduct;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 * The main fragmant also shows all of the lights connected to the hue bridge.
 */
public class MainFragment extends Fragment implements HueAdapter.OnItemClickListener, LampsChangedListener {

    final static String TAG = MainFragment.class.getCanonicalName();
    private RecyclerView lampRecycler;
    private View rootView;
    private ArrayList<LampProduct> lights;

    public MainFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpHandler.INSTANCE.addLampsChangedListener(this);
        Log.d(TAG, "onCreate main fragment");
    }

    /**
     * This method creates the recycler view in which the lights will be displayed.
     * @param inflater is the inflater for the view, this case this fragment.
     * @param container a container for all of the fragments.
     * @returns a view that has the recyclerview as well as the fragment layout.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_main, container, false);
        this.lampRecycler = this.rootView.findViewById(R.id.mainRecycler);
        this.lampRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        this.lampRecycler.setHasFixedSize(true);

        this.lights = HttpHandler.INSTANCE.getLamps();

        HueAdapter hueAdapter = new HueAdapter(getContext(), this.lights, this);
        this.lampRecycler.setAdapter(hueAdapter);

        HueAdapter.OnItemClickListener listener = this;
        SwipeRefreshLayout swipeRefreshLayout = this.rootView.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            HttpHandler.INSTANCE.getAllLights();
            lampRecycler.setAdapter(new HueAdapter(getContext(),HttpHandler.INSTANCE.getLamps(),listener));
            this.lampRecycler.getAdapter().notifyDataSetChanged();
            lampRecycler.scheduleLayoutAnimation();
        });
        return this.rootView;
    }

    /**
     * When a card inside of the recycler view has been clicked, then it collects the data for that card/light.
     * @param position the current position inside of the list of lights.
     */
    @Override
    public void OnItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",position+1);
        bundle.putSerializable("lamp", this.lights.get(position));

        Navigation.findNavController(this.rootView).navigate(R.id.action_mainFragment_to_lampDetailFragment,bundle);
        Log.d(TAG, "OnItemClick: WEJOW ITEM CLICKED!!!! OP POSITIE: " + position);
    }

    /**
     * This callbacks
     * @param lamps
     */
    @Override
    public void onLampsChanged(ArrayList<LampProduct> lamps) {
        Log.d(TAG,"on lamps changed callback received");
        this.lights = lamps;
    }
}