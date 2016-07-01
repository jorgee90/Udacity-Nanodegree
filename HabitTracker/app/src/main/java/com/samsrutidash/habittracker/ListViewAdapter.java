package com.samsrutidash.habittracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {

        private static final String TAG = ListViewAdapter.class.getSimpleName();
        ArrayList<Inventory> listArray;

        public ListViewAdapter(ArrayList<Inventory> listArray) {
            this.listArray = new ArrayList<Inventory>(listArray);
        }

        @Override
        public int getCount() {
            return listArray.size();    // total number of elements in the list
        }

        @Override
        public Object getItem(int i) {
            return listArray.get(i);    // single item in the list
        }

        @Override
        public long getItemId(int i) {
            return i;                   // index number
        }

        @Override
        public View getView(int index, View view, final ViewGroup parent) {

            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(R.layout.item_list_view, parent, false);
            }

            final Inventory dataModel = listArray.get(index);

            TextView textView = (TextView) view.findViewById(R.id.productName);
            textView.setText(dataModel.getProductName());

            Button button = (Button) view.findViewById(R.id.listItemButton);
            button.setText("" + dataModel.getQuantity());



            // button click listener
            // this chunk of code will run, if user click the button
            // because, we set the click listener on the button only

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(parent.getContext(), "PRice: " + dataModel.getPrice(), Toast.LENGTH_SHORT).show();
                }
            });


            // if you commented out the above chunk of code and
            // activate this chunk of code
            // then if user click on any view inside the list view (except button)
            // this chunk of code will execute
            // because we set the click listener on the whole view


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(parent.getContext(), "view clicked: " + dataModel.getProductName(), Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }