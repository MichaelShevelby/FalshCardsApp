package com.example.project.ui.modules;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.Folder;
import com.example.project.Manager;
import com.example.project.Module;
import com.example.project.R;
import com.example.project.adapters.FoldersAdapter;
import com.example.project.adapters.ModulesAdapter;
import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.module.ModuleFragment;


public class ModulesFragment extends Fragment {
    private Folder folder;

    public ModulesFragment(Folder folder) {
        this.folder = folder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modules, container, false);

        //Список модулей
        ListView modulesList = (ListView)root.findViewById(R.id.modulesList);

        ModulesAdapter modulesAdapter = new ModulesAdapter(folder.getModules());

        modulesList.setAdapter(modulesAdapter);

        modulesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.nav_host_fragment_content_main, new ModuleFragment(folder.getModules().get(position)));
                ftrans.addToBackStack( "tag" );
                ftrans.commit();

            }
        });

        TextView nameFolder = root.findViewById(R.id.nameFolder);
        nameFolder.setText("Папка: " + folder.getName());

        Button addModuleButton = (Button)root.findViewById(R.id.add_module);

        addModuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Клик по кнопке "Добавить модуль"
                final View createModuleView = inflater.inflate(R.layout.fragment_addmodule, null);

                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getContext());
                mDialogBuilder.setView(createModuleView);


                //Строим диалог с кнопкой создания книги
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Создать",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //получаем содержимое поля книга
                                        final String nameModule = ((EditText)createModuleView.findViewById(R.id.modulename)).getText().toString();

                                        Module module = new Module(nameModule);
                                        folder.getModules().add(module);
                                        modulesAdapter.notifyDataSetChanged();
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