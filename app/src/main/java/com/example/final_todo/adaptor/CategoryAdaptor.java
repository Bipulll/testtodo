package com.example.final_todo.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_todo.R;
import com.example.final_todo.model.Category;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.CategoryView> {

    private List<Category> categoryList;

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoryitemlayout, parent, false);
        CategoryView categoryView = new CategoryView(view);
        return categoryView;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryView holder, int position) {
        holder.tvCategoryName.setText(categoryList.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public class CategoryView extends  RecyclerView.ViewHolder {

        TextView tvCategoryName;
        public CategoryView(@NonNull View itemView) {
            super(itemView);
            tvCategoryName = (TextView) itemView.findViewById(R.id.category_item_layout_txt_category);
        }
    }
}
