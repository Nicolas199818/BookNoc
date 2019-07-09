package com.example.booknoc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder>{
    private List<Book> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // C'est ici que l'on lie les données des cellules avec le java
        public TextView bookTitle;
        public MyViewHolder(View v) {
            super(v);
            bookTitle = v.findViewById(R.id.row_cell_id);
        }
    }

    public BookAdapter(List<Book> mDataset) {
        this.mDataset = mDataset;
    }

    @Override
    public BookAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bookTitle.setText(mDataset.get(position).getTitle());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
