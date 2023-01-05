package com.cohabit.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.cohabit.inventory.databinding.FragmentNewItemBinding;
import com.google.firebase.database.FirebaseDatabase;

public class NewItemFragment extends Fragment {

    private FragmentNewItemBinding binding;
    private String[] options = {"Product Category", "Table", "Sofa", "Chair", "Bed"};
    private String[] options1 = {"Material Category", "Wood", "Steel", "Plastic"};
    private String[] options2 = {"Functionality", "Needs repair", "Does not need repair"};
    private String[] options3 = {"Aesthetics", "Damaged", "Brand New"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNewItemBinding.inflate(inflater, container, false);

        EditText editTextColor = binding.color;
        EditText editTextDimension = binding.editText1;
        String userInputString = editTextColor.getText().toString();
        CharSequence userInput = editTextColor.getText();
        binding.imageView3.setImageResource(R.drawable.cohabit_logo);
        Spinner spinnerProductCategory = binding.spinner;
        Spinner spinnerMaterialCategory = binding.spinner1;
        Spinner spinnerFunctionality = binding.spinner2;
        Spinner spinnerAesthetics = binding.spinner3;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, options3);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProductCategory.setAdapter(adapter);
        spinnerMaterialCategory.setAdapter(adapter1);
        spinnerFunctionality.setAdapter(adapter2);
        spinnerAesthetics.setAdapter(adapter3);

        spinnerMaterialCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        spinnerProductCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        binding.Save.setOnClickListener(view1 -> {

            Toast.makeText(getActivity(), "Item saved", Toast.LENGTH_SHORT).show();
            String productCategory = spinnerProductCategory.getSelectedItem().toString();
            String materialCategory = spinnerMaterialCategory.getSelectedItem().toString();
            String functionality = spinnerFunctionality.getSelectedItem().toString();
            String aesthetics = spinnerAesthetics.getSelectedItem().toString();
            String color = editTextColor.getText().toString();
            String dimensions = editTextDimension.getText().toString();
            Item item = new Item(productCategory, materialCategory, functionality, aesthetics, color, dimensions);
            FirebaseDatabase.getInstance("https://cohabit-inventory-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("items").push().setValue(item);
            NavHostFragment.findNavController(NewItemFragment.this).navigate(R.id.action_New_Item_to_Home);
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }
}
