package com.iqbalfahrul.kelompok10.carijodoh;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iqbalfahrul.kelompok10.carijodoh.models.DataUser;

import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.ViewHolder> {
    private List<DataUser> dataUsers;

    public MyDataAdapter(List<DataUser> dataUsers){
        this.dataUsers = dataUsers;
    }

    @NonNull
    @Override
    public MyDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,
                viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDataAdapter.ViewHolder viewHolder, int i) {
        DataUser dataUser = dataUsers.get(i);
        viewHolder.nama.setText(dataUser.getNama());
        viewHolder.view.setBackgroundResource(dataUser.getFoto());
    }

    @Override
    public int getItemCount() {
        return dataUsers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nama;
        RelativeLayout view;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.viewNama);
            view = itemView.findViewById(R.id.parentView);
        }
    }

}
