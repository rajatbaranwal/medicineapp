package com.example.medicinelemonsoft;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MedicineAdepter extends RecyclerView.Adapter<MedicineAdepter.ViewHolder> {

    private ArrayList<MedicineModel> medicineList;
    private Context context;

    public MedicineAdepter(ArrayList<MedicineModel> medicineList, Context context) {
        this.medicineList = medicineList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicineModel medicine = medicineList.get(position);

        holder.tvBrand.setText(medicine.getBrand_name());
        holder.tvFrom.setText(medicine.getFrom());
        holder.tvStrength.setText(medicine.getStrength());
        holder.tvPrice.setText(medicine.getPrice());

        // Set click listener for the entire item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MedicineDetailsActivity.class);
            intent.putExtra("medicine", medicine);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBrand, tvFrom, tvStrength, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBrand = itemView.findViewById(R.id.tvBrand);
            tvFrom = itemView.findViewById(R.id.tvFrom);
            tvStrength = itemView.findViewById(R.id.tvStrength);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }

    // Update data method
    public void updateData(ArrayList<MedicineModel> newList) {
        medicineList = newList;
        notifyDataSetChanged();
    }
}