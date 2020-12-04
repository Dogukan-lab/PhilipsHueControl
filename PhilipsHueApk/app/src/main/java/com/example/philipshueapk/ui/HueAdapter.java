package com.example.philipshueapk.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.philipshueapk.R;
import com.example.philipshueapk.lamp.LampProduct;

import java.util.ArrayList;

/**
 * Adapter for the main fragment. This adapter adds a new light to the view based on the HTTP GET request.
 */
public class HueAdapter extends RecyclerView.Adapter<HueAdapter.HueViewHolder> {
    private ArrayList<LampProduct> lights;
    private Context context;
    private OnItemClickListener onItemClickListener;

    /**
     * Interface to transfer the data of the clicked item inside of a recyclerview correctly.
     */
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public HueAdapter(Context context, ArrayList<LampProduct> lights, OnItemClickListener onItemClickListener){
        this.context = context;
        this.lights = lights;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HueViewHolder(LayoutInflater.from(this.context).inflate(R.layout.lamp_card, parent, false), this.onItemClickListener);
    }

    /**
     * This method creates the card for a light.
     * @param holder the viewholder that will be modified
     * @param position current position of the list of lights.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull HueViewHolder holder, int position) {
        holder.textView.setText(this.lights.get(position).getName());

        LampProduct lamp = lights.get(position);
        if (lamp.getState().getOn()) {
            holder.statusTextView.setText(R.string.lamp_status_on);
        } else {
            holder.statusTextView.setText(R.string.lamp_status_off);
        }
        holder.itemView.setBackgroundColor(lamp.getState().calculateRGBColor());
    }

    @Override
    public int getItemCount() {
        return this.lights.size();
    }

    /**
     * The viewholder for a light. In this case in form of a card.
     */
    public static class HueViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView textView;
        public TextView statusTextView;
        private OnItemClickListener onItemClickListener;

        public HueViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.lamp_image);
            this.textView = itemView.findViewById(R.id.lamp_name);
            this.statusTextView = itemView.findViewById(R.id.lamp_state);
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.onItemClickListener.OnItemClick(getAdapterPosition());
        }
    }

}
