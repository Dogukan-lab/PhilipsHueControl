package com.example.philipshueapk.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.philipshueapk.DunnyData;
import com.example.philipshueapk.R;
import com.example.philipshueapk.lamp.LampProduct;

import java.util.ArrayList;

//TODO: Change dunnydata back to LampProduct
public class HueAdapter extends RecyclerView.Adapter<HueAdapter.HueViewHolder> {
    private ArrayList<DunnyData> lights;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public HueAdapter(Context context, ArrayList<DunnyData> lights, OnItemClickListener onItemClickListener){
        this.context = context;
        this.lights = lights;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HueViewHolder(LayoutInflater.from(this.context).inflate(R.layout.lamp_card, parent, false), this.onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HueViewHolder holder, int position) {
        holder.textView.setText(this.lights.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return this.lights.size();
    }

    public static class HueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView textView;
        private OnItemClickListener onItemClickListener;

        public HueViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.lamp_image);
            this.textView = itemView.findViewById(R.id.lamp_name);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onItemClickListener.OnItemClick(getAdapterPosition());
        }
    }
}
