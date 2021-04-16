package com.example.city_screen;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

    // List with String type
    private List<String> list;
    private ItemClickListenear onItemClickListenear;

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView extends RecyclerView.ViewHolder {

        // Text View
        TextView textView;

        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textView = (TextView) view
                    .findViewById(R.id.textview);
        }
    }

    // Constructor for adapter class
    // which takes a list of String type
    public Adapter(List<String> horizontalList, ItemClickListenear onItemClickListenear) {
        this.list = horizontalList;
        this.onItemClickListenear = onItemClickListenear;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate item.xml using LayoutInflator
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,
                false);

        // return itemView
        return new MyView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(final MyView holder,
                                 final int position) {

        // Set the text of each item of
        // Recycler view with the list items
        holder.textView.setText(list.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenear.onItem(list.get(position), position);
            }
        });
    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount() {
        return list.size();
    }

    interface ItemClickListenear {
        void onItem(String date, int position);
    }
}