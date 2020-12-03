package com.example.philipshueapk.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
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

//TODO: Change dunnydata back to LampProduct
public class HueAdapter extends RecyclerView.Adapter<HueAdapter.HueViewHolder> {
    private ArrayList<LampProduct> lights;
    private Context context;
    private OnItemClickListener onItemClickListener;


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

    //TODO: make the first color.parsecolor the value of the hue light
    @Override
    public void onBindViewHolder(@NonNull HueViewHolder holder, int position) {
        holder.textView.setText(this.lights.get(position).getName());

        int height = holder.itemView.getHeight();
        ShapeDrawable drawable = new ShapeDrawable(new RectShape());
        drawable.getPaint().setShader(new LinearGradient(0, 0, 0, height,
                Color.parseColor("#330000FF"),
                Color.parseColor("#89BBFE"),
                Shader.TileMode.REPEAT));

        holder.itemView.setBackground(drawable);
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
