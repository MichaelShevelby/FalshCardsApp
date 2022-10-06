package com.example.project.ui.repeat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.Card;
import com.example.project.R;
import com.example.project.adapters.RepeatCardAdapter;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RepeatFragment extends Fragment {
    private ArrayList<Card> cards;

    public RepeatFragment(ArrayList<Card> cards) {
       this.cards = (ArrayList<Card>)cards.clone();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_repeat, container, false);

        Button b1 = root.findViewById(R.id.returnModule);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        TextView numberCards = (TextView)root.findViewById(R.id.numberCards);
        numberCards.setText("Осталось карточек: " + cards.size());

        RepeatCardAdapter recyclerViewAdapter = new RepeatCardAdapter(root.getContext(), cards);

        ItemTouchHelper.Callback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


                //Убираем карточку и добавляем ее в конец
                Card card = cards.get(0);
                cards.remove(0);

                if (direction == ItemTouchHelper.LEFT)
                    cards.add(card);


                numberCards.setText("Осталось карточек: " + cards.size());

                recyclerViewAdapter.notifyDataSetChanged();

                if (cards.size() == 0) {
                    b1.setVisibility(View.VISIBLE);
                    ((TextView)root.findViewById(R.id.success)).setVisibility(View.VISIBLE);
                }
            }
        };

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);

        return root;
    }
}