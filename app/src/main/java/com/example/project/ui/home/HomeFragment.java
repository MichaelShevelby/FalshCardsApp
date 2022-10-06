package com.example.project.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.Folder;
import com.example.project.Manager;
import com.example.project.R;
import com.example.project.adapters.FoldersAdapter;
import com.example.project.databinding.FragmentHomeBinding;
import com.example.project.ui.modules.ModulesFragment;

import kotlin.text.MatchNamedGroupCollection;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        //Список папок
        ListView foldersList = (ListView)root.findViewById(R.id.foldersList);

        FoldersAdapter foldersAdapter = new FoldersAdapter(Manager.getFolders());

        foldersList.setAdapter(foldersAdapter);

        foldersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.nav_host_fragment_content_main, new ModulesFragment(Manager.getFolders().get(position)));
                ftrans.addToBackStack( "tag" );
                ftrans.commit();

            }
        });

        Button button = root.findViewById(R.id.add_folder);

        //Нажата кнопка добавить папку
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View createFolderView = inflater.inflate(R.layout.fragment_addfolder, null);

                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getContext());
                mDialogBuilder.setView(createFolderView);


                //Строим диалог с кнопкой создания книги
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Создать",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        //получаем содержимое поля книга
                                        final String nameFolder = ((EditText)createFolderView.findViewById(R.id.foldername)).getText().toString();

                                        Folder folder = new Folder(nameFolder);

                                        Manager.getFolders().add(folder);

                                        //Перезапускаем фрагмент, чтобы визуально список папок обновился
                                        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                                        ftrans.replace(R.id.nav_host_fragment_content_main, new HomeFragment());
                                        ftrans.commit();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}