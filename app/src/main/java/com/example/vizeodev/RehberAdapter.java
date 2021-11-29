package com.example.vizeodev;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RehberAdapter extends RecyclerView.Adapter<RehberAdapter.PersonHolder> {

    private ArrayList<Kisiler> kisiler;
    public interface OnItemLongClickListener{
        public boolean onItemLongClick(int position);
    }
    private OnItemLongClickListener clickListener;
    public void setOnItemClickListener(OnItemLongClickListener longClickListener)
    {
        clickListener = longClickListener;
    }

    public RehberAdapter(ArrayList<Kisiler> kisiler)
    {
        this.kisiler=kisiler;
    }

    public void setKisiler(ArrayList<Kisiler> kisiler) {
        this.kisiler = kisiler;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType)    //Yapmış olduğumuz layout'u tanımlıyoruz.
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.kisi_layout,parent,false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RehberAdapter.PersonHolder holder, int position) {
        holder.tvname.setText(kisiler.get(position).get_isim());
        holder.tvnumber.setText(kisiler.get(position).get_numara());
        holder.IvPhoto.setImageResource(kisiler.get(position).get_foto());
    }

    @Override
    public int getItemCount() {
        return kisiler.size();
    }
    public class PersonHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView tvname;
        TextView tvnumber;
        ImageView IvPhoto;

        public PersonHolder(@NonNull View personLayout)
        {
            super(personLayout);
            tvname=personLayout.findViewById(R.id.textView3);
            tvnumber=personLayout.findViewById(R.id.textView4);
            IvPhoto=personLayout.findViewById(R.id.imageView);
            personLayout.setOnLongClickListener(this);

        }
        @Override
        public boolean onLongClick(View v)
        {
            clickListener.onItemLongClick(getAdapterPosition());
            return false;
        }



    }

}
