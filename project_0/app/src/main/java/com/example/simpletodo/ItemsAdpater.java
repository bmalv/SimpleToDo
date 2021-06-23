package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//displays data from the model into the row in the recycler view
public class ItemsAdpater extends RecyclerView.Adapter<ItemsAdpater.viewHolder>{

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdpater(List<String> items, OnLongClickListener longClickListener){
        this.items = items;
        this.longClickListener = longClickListener;
    }


    @NonNull
    //@org.jetbrains.annotations.NotNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflator to inflate view
        View toolView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap it inside a view holder and return it
        return new viewHolder(toolView);
    }

    @Override
    //responsible for binding data to a particular view holder
    public void onBindViewHolder(@NonNull ItemsAdpater.viewHolder holder, int position) {
        //grab the item at the position
        String item = items.get(position);
        //bind the item at the specified view holder
        holder.bind(item);
    }

    @Override
    //tells the recycler view how many items are in the list
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy acces to views that represent each row and list
    class viewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //updates the view inside the view holder with the data of string item
        public void bind(String item){
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notify the listner which item was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }

    }


}
