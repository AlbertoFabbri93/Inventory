package com.cohabit.inventory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.info_SKnumber);
        }

        public TextView getView(){
            return textView;
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_card;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("ItemsAdapter", "onBindViewHolder: " + position);
        holder.getView().setText(String.valueOf(12));
    }

    @Override
    public int getItemCount() {
        Log.d("ItemsAdapter", "getItemCount: " + 12);
        return 100;
    }
}
