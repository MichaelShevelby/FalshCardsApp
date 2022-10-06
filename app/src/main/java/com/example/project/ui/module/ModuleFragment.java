package com.example.project.ui.module;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.Card;
import com.example.project.Manager;
import com.example.project.Module;
import com.example.project.R;
import com.example.project.adapters.CardsAdapter;
import com.example.project.adapters.ModulesAdapter;
import com.example.project.ui.repeat.RepeatFragment;

public class ModuleFragment extends Fragment {
    Module module;

    public ModuleFragment(Module module) {
        this.module = module;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_module, container, false);

        ListView cardsList = (ListView)root.findViewById(R.id.cardsList);

        CardsAdapter cardsAdapter = new CardsAdapter(module.getCards());

        cardsList.setAdapter(cardsAdapter);

        ((Button)root.findViewById(R.id.repeatbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.nav_host_fragment_content_main, new RepeatFragment(module.getCards()));
                ftrans.addToBackStack( "tag" );
                ftrans.commit();
            }
        });

        TextView nameFolder = root.findViewById(R.id.nameModule);
        nameFolder.setText("Модуль: " + module.getName());

        Button addCardButton = (Button)root.findViewById(R.id.add_card);

        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Клик по кнопке "Добавить модуль"
                final View createCardView = inflater.inflate(R.layout.fragment_addcard, null);

                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getContext());
                mDialogBuilder.setView(createCardView);


                //Строим диалог с кнопкой создания книги
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Создать",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //получаем содержимое поля книга
                                        final String term = ((EditText)createCardView.findViewById(R.id.term)).getText().toString();
                                        final String def = ((EditText)createCardView.findViewById(R.id.def)).getText().toString();

                                        Card card = new Card(term, def);
                                        module.getCards().add(card);
                                        cardsAdapter.notifyDataSetChanged();
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = mDialogBuilder.create();

                //Отображаем диалог
                alertDialog.show();

            }
        });

        return root;
    }
}