package com.cohabit.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cohabit.inventory.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    ImageView im;
    ImageView im2;
    private FragmentHomeBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView im = (ImageView) view.findViewById(R.id.imageView);
        im.setImageResource(R.drawable.cohabit_bg);
        ImageView im2 = (ImageView) view.findViewById(R.id.imageView2);
        im2.setImageResource(R.drawable.cohabit_logo);
        return view;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.Itembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView im = (ImageView) getView().findViewById(R.id.imageView);
                im.setImageResource(R.drawable.cohabit_bg);
                ImageView im2 = (ImageView) getView().findViewById(R.id.imageView2);
                im2.setImageResource(R.drawable.cohabit_logo);
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}