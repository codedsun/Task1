package com.example.suneetsrivastava.task1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by suneetsrivastava on 04/01/18.
 */

public  class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Country> countries;
    RecyclerViewAdapter(Context c, ArrayList<Country> countries){
        this.context = c;
        this.countries = countries;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recylerview_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
          Picasso.with(context).load(countries.get(position).getFlag()).into(holder.imageView);
          holder.imageView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent i = new Intent(context,ImageViewFull.class);
                  i.putExtra("uri",countries.get(position).getFlag());
                  context.startActivity(i);
                  Log.e("TAG", "onClick: "+position );
              }
          });

    }


    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}