package com.cohabit.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cohabit.inventory.databinding.FragmentReturnBinding;

public class Return extends Fragment {

    private FragmentReturnBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get string Array from strings.xml
        String [] functionalities = getContext().getResources().getStringArray(R.array.functionality_array);
        String [] aesthetics = getContext().getResources().getStringArray(R.array.aesthetics_array);
        String [] location = getContext().getResources().getStringArray(R.array.location_array);

        // Binding spinners and editText.
        binding = FragmentReturnBinding.inflate(inflater, container, false);
        Spinner spinnerFunctionality = binding.spinnerFunctionality;
        Spinner spinnerAesthetics = binding.spinnerAesthetics;
        Spinner spinnerLocation = binding.spinnerLocation;

        EditText editText = binding.SKNumberEditText;

        // Adding Array to spinners.
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

        // saves item
        spinnerFunctionality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ItemSelected = functionalities[position];
                if (position == 1) {
                    //do something
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        spinnerAesthetics.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ItemSelected = aesthetics[position];
                if (position == 1) {
                    //do something
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }

        });
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ItemSelected = location[position];
                if (position == 1) {
                    //do something
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Return.this)
                        .navigate(R.id.action_Return_to_Home);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}