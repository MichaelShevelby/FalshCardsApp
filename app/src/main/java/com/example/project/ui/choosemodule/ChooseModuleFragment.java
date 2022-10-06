package com.example.project.ui.choosemodule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.project.Manager;
import com.example.project.R;
import com.example.project.adapters.ChooseModuleAdapter;
import com.example.project.ui.gallery.GalleryFragment;
import com.example.project.ui.modules.ModulesFragment;


public class ChooseModuleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choose_module, container, false);

        ListView choseModulesList = (ListView)root.findViewById(R.id.choosemodules);
        ChooseModuleAdapter chooseModuleAdapter = new ChooseModuleAdapter(Manager.getAllModules());
        choseModulesList.setAdapter(chooseModuleAdapter);

        choseModulesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //КЛОНИРУЕМ ЧТОБЫ КАРТОЧКИ НЕ ПРОПАДАЛИ ИЗ НАСТОЯЩЕГО МОДУЛЯ
                Manager.setSpacedModule(Manager.getAllModules().get(position).clone1());
//                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
//                ftrans.replace(R.id.nav_host_fragment_content_main, new GalleryFragment());
//                ftrans.addToBackStack( "tag" );
//                ftrans.commit();
                getFragmentManager().popBackStack();

            }
        });

        return root;
    }
}