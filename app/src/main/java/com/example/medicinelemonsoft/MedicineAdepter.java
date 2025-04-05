package com.example.medicinelemonsoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MedicineAdepter extends RecyclerView.Adapter<MedicineAdepter.ViewHolder> {

    ArrayList<MedicineModel> arrayList = new ArrayList<>();
    Context context;


    public MedicineAdepter(ArrayList<MedicineModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MedicineAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_item,parent,false));


    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdepter.ViewHolder holder, int position) {


        holder.tvBrand.setText(arrayList.get(position).getBrand_name());
        holder.tvFrom.setText(arrayList.get(position).getFrom());
        holder.tvStrength.setText(arrayList.get(position).getStrength());
        holder.tvPrice.setText(arrayList.get(position).getPrice());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvBrand,tvFrom,tvStrength,tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvBrand = itemView.findViewById(R.id.tvBrand);
            tvFrom = itemView.findViewById(R.id.tvFrom);
            tvStrength = itemView.findViewById(R.id.tvStrength);
            tvPrice = itemView.findViewById(R.id.tvPrice);








        }
    }
}
