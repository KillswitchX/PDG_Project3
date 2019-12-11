package com.icesi.pdg_project.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icesi.pdg_project.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapter_Planification extends RecyclerView.Adapter<Adapter_Planification.ViewHolder> implements Serializable {

    private ArrayList<Item_Plan> items;

    private Context context;

    public Adapter_Planification(ArrayList<Item_Plan> items, Context context){
        this.items = items;
        this.context = context;
    }

    public ArrayList<Item_Plan> getItems(){
        return items;
    }

    public void setItems(ArrayList<Item_Plan> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planification,parent,false);
        ViewHolder viewHolderPlayList = new ViewHolder(view);
        return viewHolderPlayList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((TextView) holder.root.findViewById(R.id.item_planification_var)).setText(items.get(position).getVariable());
        ((TextView) holder.root.findViewById(R.id.item_planification_symbol)).setText(items.get(position).getSymbol());
        ((TextView) holder.root.findViewById(R.id.item_planification_value)).setText(items.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;

        public ViewHolder(LinearLayout v){
            super(v);
            root=v;
        }
    }
}
