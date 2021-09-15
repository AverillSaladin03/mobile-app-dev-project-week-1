package com.example.mobileappdevweek1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Mahasiswa;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.mahasiswaViewHolder> {

    Intent intent;
    Context context;

    //ArrayList
    public ArrayList<Mahasiswa> mahasiswaList;

    public RVAdapter(ArrayList<Mahasiswa> mahasiswaList){
        this.mahasiswaList = mahasiswaList;
    }

    @NonNull
    @Override
    public mahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_userinfo, parent, false);
        context = parent.getContext();
        return new mahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mahasiswaViewHolder holder, int position) {
        holder.cardView_nama.setText(mahasiswaList.get(position).getNama());
        holder.cardView_age.setText(mahasiswaList.get(position).getAge() + " y.o");
        holder.cardView_address.setText(mahasiswaList.get(position).getAddress());
        holder.cardView_id.setText(String.valueOf(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = holder.getAdapterPosition();
                intent = new Intent(context.getApplicationContext(), ProfileActivity.class);
                intent.putExtra("indexMahasiswa", index);
                intent.putParcelableArrayListExtra("listMahasiswa", mahasiswaList);
                context.startActivity(intent);
            }
        });
    }


    //ItemCounter
    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    //View Holder
    public class mahasiswaViewHolder extends RecyclerView.ViewHolder {

        private TextView cardView_nama, cardView_age, cardView_address, cardView_id;

        public mahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView_nama = itemView.findViewById(R.id.cardView_nama);
            cardView_age = itemView.findViewById(R.id.cardView_age);
            cardView_address = itemView.findViewById(R.id.cardView_address);
            cardView_id = itemView.findViewById(R.id.cardView_id);
        }
    }
}
