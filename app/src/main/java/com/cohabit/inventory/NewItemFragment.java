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

import com.cohabit.inventory.databinding.FragmentNewItemBinding;

public class NewItemFragment extends Fragment {

    private FragmentNewItemBinding binding;
    private String[] options = {"Product Category", "Table", "Sofa", "Chair", "Bed"};
    private String[] options1 = {"Material Category", "Wood", "Steel","Plastic"};
    private String[] options2 = {"Functionality", "Needs repair", "Does not need repair"};
    private String[] options3 = {"Aesthetics", "Damaged", "Brand New"};
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentNewItemBinding.inflate(inflater, container, false);

        EditText editText = binding.editText;
        EditText editText1 =binding.editText1;
        String userInputString = editText.getText().toString();
        CharSequence userInput = editText.getText();
        binding.imageView3.setImageResource(R.drawable.cohabit_logo);
        Spinner spinner = binding.spinner;
        Spinner spinner1 = binding.spinner1;
        Spinner spinner2 = binding.spinner2;
        Spinner spinner3 = binding.spinner3;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, options);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, options1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, options2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, options3);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = options1[position];
                if (position == 1) {
                    //do something
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = options[position];
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

        binding.Secondbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(NewItemFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }
}
