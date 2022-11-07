package com.example.midsemmad;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LogFragment extends Fragment {

    String loggedAccount = "";

    public class LogViewHolder extends RecyclerView.ViewHolder
    {

        TextView id;
        TextView restaurants;
        TextView prices;
        TextView totalPrice;
        TextView quantity;
        public LogViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.LogID);
            restaurants = itemView.findViewById(R.id.LogRestaurant);
            prices = itemView.findViewById(R.id.LogPrice);
            totalPrice = itemView.findViewById(R.id.LogTotalPrice);

        }
    }
    private class LogAdapter extends RecyclerView.Adapter<LogViewHolder>
    {
        LogModel logModel;
        @Override
        public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.logitem,parent,false);
            LogViewHolder logViewHolder = new LogViewHolder(view);
            logModel = new LogModel();
            logModel.load(parent.getContext());
            return logViewHolder;
        }

        @Override
        public void onBindViewHolder(LogViewHolder holder, int position) {
            if(!loggedAccount.equals("")) {
                ArrayList<Log> logList = logModel.getLogForAccount(loggedAccount);
                android.util.Log.d("AAAAAAAA", logList.get(position).id);
                holder.id.setText(logList.get(position).id);
                holder.restaurants.setText(logList.get(position).restaurants);
                holder.prices.setText(logList.get(position).price);
                holder.totalPrice.setText(logList.get(position).totalPrice);
            }
        }
        public int getItemCount() {
            LogModel model = new LogModel();
            model.load(getContext());
            if(!loggedAccount.equals("")) {
                return model.getLogForAccount(loggedAccount).size();
            }
            else {
                return 0;
            }

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);
        RecyclerView rv = v.findViewById(R.id.OrderLog);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        LogAdapter myAdapter = new LogAdapter();
        rv.setAdapter(myAdapter);

        AccountModel accounts = new AccountModel();
        accounts.load(getContext());
        Button login = v.findViewById(R.id.Login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Account account : accounts.getAllAccount())
                {
                    TextView name = (TextView)v.findViewById(R.id.AccountName);
                    TextView password = (TextView)v.findViewById(R.id.AccountPassword);
                    if(account.name.equals(name.getText().toString()) && account.password.equals(password.getText().toString())) {
                        LogModel log = new LogModel();
                        log.load(getContext());
                        loggedAccount = account.id;
                        v.findViewById(R.id.Successful).setVisibility(View.VISIBLE);
                        ((TextView)v.findViewById(R.id.Successful)).setText("Account Logged In With ID: " + loggedAccount);
                        v.findViewById(R.id.Login).setVisibility(View.INVISIBLE);
                        v.findViewById(R.id.Register).setVisibility(View.INVISIBLE);
                        v.findViewById(R.id.AccountName).setVisibility(View.INVISIBLE);
                        v.findViewById(R.id.AccountPassword).setVisibility(View.INVISIBLE);
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        Button register = v.findViewById(R.id.Register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView name = (TextView)v.findViewById(R.id.AccountName);
                TextView password = (TextView)v.findViewById(R.id.AccountPassword);
                accounts.addAccount(new Account(String.valueOf(accounts.getAllAccount().size()), name.getText().toString(), password.getText().toString()));
            }
        });

        Button checkout = v.findViewById(R.id.FinalizePayment);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!loggedAccount.equals("")) {
                    CheckoutModel checkout = new CheckoutModel();
                    checkout.load(getContext());
                    MenuModel menu = new MenuModel();
                    menu.load(getContext());
                    LogModel log = new LogModel();
                    log.load(getContext());
                    ArrayList<Checkout> checkoutList = checkout.getAllCheckout();
                    String restaurantString = "";
                    String priceString = "";
                    double totalPrice = 0;
                    for(Checkout check : checkoutList)
                    {
                        restaurantString = restaurantString + menu.getAllMenu().get(Integer.parseInt(check.menuItemID)).restaurantName + " " + menu.getAllMenu().get(Integer.parseInt(check.menuItemID)).name + "\n";
                        priceString = priceString + Double.parseDouble(menu.getAllMenu().get(Integer.parseInt(check.menuItemID)).price)*Double.parseDouble(check.quantity) + "\n";
                        totalPrice = totalPrice + Double.parseDouble(menu.getAllMenu().get(Integer.parseInt(check.menuItemID)).price)*Double.parseDouble(check.quantity);
                    }
                    log.addLog(new Log(String.valueOf(log.getAllLog().size()), restaurantString, priceString, String.valueOf(totalPrice), loggedAccount));
                    checkout.deleteTable();
                    android.util.Log.d("EEEE", String.valueOf(log.getLogForAccount(loggedAccount).size()));
                    myAdapter.notifyDataSetChanged();
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}