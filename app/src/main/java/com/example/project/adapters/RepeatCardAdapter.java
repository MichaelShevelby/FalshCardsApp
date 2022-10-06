package com.example.project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Card;
import com.example.project.R;

import java.util.ArrayList;

public class RepeatCardAdapter extends RecyclerView.Adapter<RepeatCardAdapter.ViewHolder> {
    private ArrayList<Card> adapterCards;
    private Context mContext;

    public RepeatCardAdapter(Context mContext, ArrayList<Card> adapterCards) {
        this.mContext = mContext;
        this.adapterCards = adapterCards;
    }


    //Этот метод одинаков для любого адаптера
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repeatcard, parent, false);
        ViewHolder holder= new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Установка значений всем блокам
        holder.textView.setText(adapterCards.get(holder.getAdapterPosition()).getTerm());

        //Клик на карточку
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textView.setText(holder.textView.getText() == adapterCards.get(holder.getAdapterPosition()).getTerm() ? adapterCards.get(holder.getAdapterPosition()).getDef() : adapterCards.get(holder.getAdapterPosition()).getTerm());
            }
        });
    }

    @Override
    public int getItemCount() {
        return adapterCards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardTerm);
            relativeLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
