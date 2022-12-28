package com.cohabit.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cohabit.inventory.databinding.FragmentReturnBinding;

public class Return extends Fragment {

    private FragmentReturnBinding binding;

    // Get string Array from strings.xml
    String [] functionalities = getResources().getStringArray(R.array.functionalities_array);
    String [] aesthetics = getResources().getStringArray(R.array.aesthetics_array);
    String [] location = getResources().getStringArray(R.array.location_array);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReturnBinding.inflate(inflater, container, false);

        // Binding spinner.
        Spinner spinnerFunctionality = binding.spinnerFunctionality;
        Spinner spinnerAesthetics = binding.spinnerAesthetics;
        Spinner spinnerLocation = binding.spinnerLocation;

        // Adding Array to spinner.
        ArrayAdapter<String> adapterFunctionality = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, functionalities);
        ArrayAdapter<String> adapterAesthetics = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, aesthetics);
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, location);

        // setting adapter for spinners.
        adapterFunctionality.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFunctionality.setAdapter(adapterFunctionality);
        spinnerAesthetics.setAdapter(adapterAesthetics);
        spinnerLocation.setAdapter(adapterLocation);

        return binding.getRoot();
    }

    //
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSubmit.setOnClickListener(view1 -> NavHostFragment.findNavController(Return.this)
                .navigate(R.id.action_New_Item_to_Home));
    }
    //
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}