package com.pemrogramanmobile.firestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pemrogramanmobile.firestore.R;
import com.pemrogramanmobile.firestore.model.Pakaian;

import java.util.List;

public class PakaianAdapter extends  RecyclerView.Adapter<PakaianAdapter.MyViewHolder>{
    private Context context;
    private List<Pakaian> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick (int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public PakaianAdapter(Context context, List<Pakaian> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pakaian, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.namaPakaian.setText(list.get(position).getNamaPakaian());
        holder.cuciBasah.setText(list.get(position).getCuciBasah());
        holder.cuciKering.setText(list.get(position).getCuciKering());
        holder.setrika.setText(list.get(position).getSetrika());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView namaPakaian, cuciBasah, cuciKering, setrika;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namaPakaian = itemView.findViewById(R.id.tv_namaPakaian);
            cuciBasah = itemView.findViewById(R.id.tv_cuciBasah);
            cuciKering = itemView.findViewById(R.id.tv_cuciKering);
            setrika = itemView.findViewById(R.id.tv_setrika);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
