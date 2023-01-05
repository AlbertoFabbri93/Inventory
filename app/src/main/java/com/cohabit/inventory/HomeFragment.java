package com.cohabit.inventory;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cohabit.inventory.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.newItemButton.setOnClickListener(view1 -> {
            Log.i(TAG, "New Item Button Clicked");
            NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_Home_to_New_Item);
        });

       binding.Returnbtn.setOnClickListener(viewReturn -> {
           Log.i(TAG, "Return Item Button Clicked");
           NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_Home_to_Return);
       });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}