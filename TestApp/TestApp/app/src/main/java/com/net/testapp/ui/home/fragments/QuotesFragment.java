package com.net.testapp.ui.home.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.net.testapp.R;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuotesFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;

    public QuotesFragment() {
    }


    public static QuotesFragment newInstance() {
        QuotesFragment fragment = new QuotesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        recyclerView = view.findViewById(R.id.recycler_quotes);
        QuoteAdapter adapter=new QuoteAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    public class QuoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public QuoteAdapter() {
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ViewHolder(@NonNull @NotNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.txtQuote);
            }
        }

        @NonNull
        @NotNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_quote, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

            ViewHolder holder1 = (ViewHolder) holder;
            holder1.textView.setText("Quote " + (position + 1));
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
}