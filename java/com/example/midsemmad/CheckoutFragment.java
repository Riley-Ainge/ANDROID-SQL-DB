package com.example.midsemmad;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckoutFragment extends Fragment {

    public int quantity;
    public String selectedCheckout = "";

    public class CheckoutViewHolder extends RecyclerView.ViewHolder
    {

        ImageView image;
        TextView id;
        TextView name;
        TextView quantity;
        TextView price;
        public CheckoutViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.CheckoutSelector);
            id = itemView.findViewById(R.id.CheckoutID);
            name = itemView.findViewById(R.id.CheckoutName);
            quantity = itemView.findViewById(R.id.CheckoutQuant);
            price = itemView.findViewById(R.id.CheckoutPrice);
        }
    }
    private class CheckoutAdapter extends RecyclerView.Adapter<CheckoutViewHolder>
    {
        CheckoutModel checkoutModel;
        MenuModel menuModel;
        @Override
        public CheckoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.checkout,parent,false);
            CheckoutViewHolder checkoutViewHolder = new CheckoutViewHolder(view);
            checkoutModel = new CheckoutModel();
            checkoutModel.load(parent.getContext());
            menuModel = new MenuModel();
            menuModel.load(parent.getContext());
            return checkoutViewHolder;
        }

        @Override
        public void onBindViewHolder(CheckoutViewHolder holder, int position) {
            ArrayList<Checkout> checkoutList = checkoutModel.getAllCheckout();
            String tempName = checkoutList.get(position).id;
            holder.id.setText(checkoutList.get(position).id);
            holder.name.setText(menuModel.getAllMenu().get(Integer.parseInt(checkoutList.get(position).menuItemID)).name);
            holder.quantity.setText(checkoutList.get(position).quantity);
            holder.price.setText(menuModel.getAllMenu().get(Integer.parseInt(checkoutList.get(position).menuItemID)).price);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCheckout = tempName;
                    quantity = Integer.parseInt(checkoutModel.getCheckoutForID(tempName).get(0).quantity);
                    TextView text = (TextView)getView().findViewById(R.id.CheckoutQuantity);
                    text.setText(String.valueOf(quantity));
                }
            });

        }
        public int getItemCount() {
            CheckoutModel model = new CheckoutModel();
            model.load(getContext());
            return model.getAllCheckout().size();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_checkout, container, false);
        RecyclerView rv = v.findViewById(R.id.CheckoutRecylcer);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        CheckoutAdapter myAdapter = new CheckoutAdapter();
        rv.setAdapter(myAdapter);

        CheckoutModel checkout = new CheckoutModel();
        checkout.load(getContext());
        Button plus = v.findViewById(R.id.CheckoutPlus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selectedCheckout.equals("")) {
                    quantity += 1;
                    TextView text = (TextView) v.findViewById(R.id.CheckoutQuantity);
                    text.setText(String.valueOf(quantity));
                    checkout.updateCheckout(checkout.getCheckoutForID(selectedCheckout).get(0), String.valueOf(quantity));
                    myAdapter.notifyDataSetChanged();
                }
                }
        });
        Button minus = v.findViewById(R.id.CheckoutMinus);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selectedCheckout.equals("")) {
                    if(quantity-1 >= 1) {
                        quantity -= 1;
                        TextView text = (TextView) v.findViewById(R.id.CheckoutQuantity);
                        text.setText(String.valueOf(quantity));
                        checkout.updateCheckout(checkout.getCheckoutForID(selectedCheckout).get(0), String.valueOf(quantity));
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        Button delete = v.findViewById(R.id.Delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!selectedCheckout.equals("")) {
                    checkout.removeCheckout(checkout.getCheckoutForID(selectedCheckout).get(0));
                    myAdapter.notifyDataSetChanged();
                    selectedCheckout = "";
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}