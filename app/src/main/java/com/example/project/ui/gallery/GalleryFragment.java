package com.example.project.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.Manager;
import com.example.project.R;
import com.example.project.databinding.FragmentGalleryBinding;
import com.example.project.ui.choosemodule.ChooseModuleFragment;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (Manager.getSpacedModule() != null) {
            ((TextView)root.findViewById(R.id.choseModule)).setText(Manager.getSpacedModule().getName());
        }

        ((Button)root.findViewById(R.id.buttonChoose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                ftrans.replace(R.id.nav_host_fragment_content_main, new ChooseModuleFragment());
                ftrans.addToBackStack( "tag" );
                ftrans.commit();
            }
        });

        ((Switch)root.findViewById(R.id.switch1)).setChecked(Manager.isSpacedRepetition);

        ((Switch)root.findViewById(R.id.switch1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Manager.isSpacedRepetition = b;
            }
        });


        return root;
    }

}