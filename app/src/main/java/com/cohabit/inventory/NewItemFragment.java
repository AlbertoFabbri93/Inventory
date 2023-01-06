package com.cohabit.inventory;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class NewItemFragment extends Fragment {

    private FragmentNewItemBinding binding;
    private DatabaseReference itemsDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        itemsDatabase = FirebaseDatabase.getInstance("https://cohabit-inventory-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        Query latestItemNumberQuery = itemsDatabase.child("items").orderByChild("id").limitToLast(1);

        latestItemNumberQuery.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                Log.d("firebase", String.valueOf(task.getResult().getValue()));
            }
        });

        binding = FragmentNewItemBinding.inflate(inflater, container, false);

        // Get string Array from strings.xml
        String [] productCategoryArray = getContext().getResources().getStringArray(R.array.product_category_array);
        String [] materialCategoryArray = getContext().getResources().getStringArray(R.array.material_category_array);
        String [] functionalityArray = getContext().getResources().getStringArray(R.array.functionality_array);
        String [] aestheticsArray = getContext().getResources().getStringArray(R.array.aesthetics_array);

        EditText editTextColor = binding.color;
        EditText editTextDimension = binding.editText1;
        String userInputString = editTextColor.getText().toString();
        CharSequence userInput = editTextColor.getText();
        binding.imageView3.setImageResource(R.drawable.cohabit_logo);
        Spinner spinnerProductCategory = binding.spinnerProductCategory;
        Spinner spinnerMaterialCategory = binding.spinnerMaterialCategory;
        Spinner spinnerFunctionality = binding.spinnerFunctionality;
        Spinner spinnerAesthetics = binding.spinnerAesthetic;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, productCategoryArray);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, materialCategoryArray);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, functionalityArray);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, aestheticsArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProductCategory.setAdapter(adapter);
        spinnerMaterialCategory.setAdapter(adapter1);
        spinnerFunctionality.setAdapter(adapter2);
        spinnerAesthetics.setAdapter(adapter3);

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
