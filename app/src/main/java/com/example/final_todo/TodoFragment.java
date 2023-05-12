package com.example.final_todo;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.final_todo.model.Category;
import com.example.final_todo.viewmodel.CategoryViewModel;

import java.util.Calendar;
import java.util.List;

public class TodoFragment extends Fragment {

    Spinner categoryDropDownList;
    EditText txtTodoDate, txtTitle, txtDescription;
    RadioGroup rgPriority;
    CheckBox chkComlete;
    Button btnSave;
    CategoryViewModel categoryViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        categoryViewModel = new ViewModelProvider(getActivity()).get(CategoryViewModel.class);
        categoryDropDownList = view.findViewById(R.id.fragment_todo_cbo_category);
        txtTodoDate = view.findViewById(R.id.fragment_todo_txt_date);
        txtTitle = view.findViewById(R.id.fragment_todo_txt_title);
        txtDescription = view.findViewById(R.id.fragment_todo_txtDescription);
        rgPriority = view.findViewById(R.id.fragment_todo_rg_priority);
        chkComlete = view.findViewById(R.id.fragment_todo_chk_complete);
        btnSave = view.findViewById(R.id.fragment_todo_btn_Save);

        categoryViewModel.getCategoryList().observe(getViewLifecycleOwner(), categories -> {
            setCategorySpinner(categories);
        });

        txtTodoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDateDialog();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        return  view;
    }
    private void setCategorySpinner(List<Category> categories) {
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, categories) ;
        categoryDropDownList.setAdapter(adapter);
    }

    private void showDateDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtTodoDate.setText(dayOfMonth + "/"+ (month+1)+"/" +year);
            }
        }, year, month, day
        );
        datePickerDialog.show();
    }

    private void saveData(){
        String title = txtTitle.getText().toString();
        String descripton = txtDescription.getText().toString();
        String todoDate = txtTodoDate.getText().toString();
        Category category = (Category) categoryDropDownList.getSelectedItem();
        int checkedRadio = rgPriority.getCheckedRadioButtonId();
        int prority =0;
        switch (checkedRadio){
            case R.id.fragment_todo_rb_high:
                prority =0;
                break;
            case R.id.fragment_todo_rb_mid:
                prority = 1;
                break;
            case R.id.fragment_todo_rb_low:
                prority = 2;
                break;
        }
    }
}