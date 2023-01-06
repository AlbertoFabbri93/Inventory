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

import com.cohabit.inventory.databinding.FragmentReturnBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Return extends Fragment {

    private FragmentReturnBinding binding;
    private DatabaseReference itemsDatabase;

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

        itemsDatabase = FirebaseDatabase.getInstance("https://cohabit-inventory-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSubmit.setOnClickListener(view1 -> {

            int itemDesired = Integer.parseInt(binding.SKNumberEditText.getText().toString());
            itemsDatabase.child("items").orderByChild("id").equalTo(itemDesired).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if (task.getResult().getChildrenCount() == 0) {
                        Toast.makeText(getContext(), "Item not found", Toast.LENGTH_SHORT).show();
                    } else {
                        for (DataSnapshot ds : task.getResult().getChildren()) {
                            Item itemToUpdate = ds.getValue(Item.class);
                            itemToUpdate.setFunctionality(binding.spinnerFunctionality.getSelectedItem().toString());
                            itemToUpdate.setAesthetics(binding.spinnerAesthetics.getSelectedItem().toString());
                            // itemToUpdate.setLocation(binding.spinnerLocation.getSelectedItem().toString());
                            itemsDatabase.child("items").child(ds.getKey()).setValue(itemToUpdate);
                            NavHostFragment.findNavController(Return.this).navigate(R.id.action_Return_to_Home);
                        }

                        Toast.makeText(getContext(), "Item saved", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}