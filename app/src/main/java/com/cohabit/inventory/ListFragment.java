package com.cohabit.inventory;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ListFragment extends Fragment implements MenuProvider {

    private ItemsAdapter itemsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(this, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        // Database
        DatabaseReference itemsDatabase = FirebaseDatabase.getInstance("https://cohabit-inventory-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        Query itemsQuery = itemsDatabase.child("items");

        // Debug
        itemsDatabase.child("items").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long count= dataSnapshot.getChildrenCount();
                Log.d("ListFragment", "Database elements: " + count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.items_list, container, false);

        // Add the following lines to create RecyclerView
        RecyclerView recyclerViewItems = rootView.findViewById(R.id.recyclerview_items);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewItems.getContext(), DividerItemDecoration.VERTICAL);
        recyclerViewItems.addItemDecoration(dividerItemDecoration);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<Item> options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(itemsQuery, Item.class).build();
        itemsAdapter = new ItemsAdapter(options);
        recyclerViewItems.setAdapter(itemsAdapter);

        return rootView;
    }

    @Override public void onStart()
    {
        super.onStart();
        itemsAdapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override public void onStop()
    {
        super.onStop();
        itemsAdapter.stopListening();
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        Log.d("ListFragment", "Create List Fragment Menu");
        menuInflater.inflate(R.menu.menu_list, menu);
        final MenuItem search = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Search Project");
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.d("ListFragment", "Search submit query: " + query);
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.d("ListFragment", "Search query: " + newText);
                    return false;
                }
            });
        }
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
