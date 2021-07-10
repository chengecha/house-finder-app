package com.example.draw_nav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {
    //private ArrayList<String>headerr=new ArrayList<>();
    private List<Houses>homes;
    private Context mctx;
    private String Images;

    public myadapter(List<Houses> homes, Context mctx, String images) {
        this.homes = homes;
        this.mctx = mctx;
        Images = images;
    }

    public myadapter(Context context, List<Houses> homes) {
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mctx)
                .inflate(R.layout.recyc_items,null);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull myadapter.ViewHolder holder, int position) {
        Houses houses=homes.get(position);
        holder.nametxt.setText(houses.getName());
        holder.emailtxt.setText(houses.getEmail());
        holder.phonetxt.setText(houses.getPhone());
        holder.img.setImageDrawable(mctx.getResources().getDrawable(houses.getImage(),null));

    }
    @Override
    public int getItemCount() {

       // return homes .size();
        return 0;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public ImageView img;
        public TextView nametxt,emailtxt,phonetxt;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            img =itemView.findViewById(R.id.imgg);
            nametxt=itemView.findViewById(R.id.text);
            emailtxt=itemView.findViewById(R.id.email);
            phonetxt=itemView.findViewById(R.id.phone);
        }
    }
}
