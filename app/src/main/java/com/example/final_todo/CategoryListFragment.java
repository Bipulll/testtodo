package com.example.final_todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_todo.adaptor.CategoryAdaptor;
import com.example.final_todo.model.Category;
import com.example.final_todo.viewmodel.CategoryViewModel;

import java.util.List;

public class CategoryListFragment extends Fragment {

    CategoryViewModel categoryViewModel;
    RecyclerView categoryRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        categoryViewModel  = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        CategoryAdaptor categoryAdaptor = new CategoryAdaptor();
        categoryViewModel.getCategoryList().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdaptor.setCategoryList(categories);
            }
        });

        categoryRecyclerView = view.findViewById(R.id.category_recycler_view);
        categoryRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        categoryRecyclerView.setAdapter(categoryAdaptor);

        return view;
    }
}