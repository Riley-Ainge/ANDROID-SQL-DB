package com.example.midsemmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckoutModel checkoutModel = new CheckoutModel();
        checkoutModel.load(getApplicationContext());
        checkoutModel.deleteTable();

        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.load(getApplicationContext());
        restaurantModel.deleteTable();
        restaurantModel.addRestaurant(new Restaurant("KFC", "@drawable/kfc"));
        restaurantModel.addRestaurant(new Restaurant("Mcdonalds", "@drawable/mc"));
        restaurantModel.addRestaurant(new Restaurant("Subway", "@drawable/subway"));
        restaurantModel.addRestaurant(new Restaurant("Jacks", "@drawable/jacks"));
        restaurantModel.addRestaurant(new Restaurant("Chip Place", "@drawable/chipplace"));

        MenuModel menuModel = new MenuModel();
        menuModel.load(getApplicationContext());
        menuModel.deleteTable();
        menuModel.addMenu(new MenuItem("0", "chip", "thick chips", "8.50", "@drawable/chip", "KFC"));
        menuModel.addMenu(new MenuItem("1", "big chip", "more chips", "10.50", "@drawable/chip", "KFC"));
        menuModel.addMenu(new MenuItem("2", "bigger chip", "even more chips", "13.50", "@drawable/chip", "KFC"));
        menuModel.addMenu(new MenuItem("3", "burbor", "yummy", "10.99", "@drawable/burger", "KFC"));
        menuModel.addMenu(new MenuItem("4", "bigger burbor", "tasty", "15.99", "@drawable/burger", "KFC"));

        menuModel.addMenu(new MenuItem("5", "sandwich", "mc sandwich", "8.50", "@drawable/sandwich", "Mcdonalds"));
        menuModel.addMenu(new MenuItem("6", "chip", "thin chips", "10.50", "@drawable/chip", "Mcdonalds"));
        menuModel.addMenu(new MenuItem("7", "burger", "mc burger", "13.50", "@drawable/burger", "Mcdonalds"));
        menuModel.addMenu(new MenuItem("8", "mcrib", "mc rib burger", "15.50", "@drawable/burger", "Mcdonalds"));

        menuModel.addMenu(new MenuItem("9", "sandwich", "yummy", "8.50", "@drawable/sandwich", "Subway"));
        menuModel.addMenu(new MenuItem("10", "big sandwich", "more yumm", "10.50", "@drawable/sandwich", "Subway"));
        menuModel.addMenu(new MenuItem("11", "bigger sandwich", "lots of yum", "13.50", "@drawable/sandwich", "Subway"));
        menuModel.addMenu(new MenuItem("12", "burger", "subway burger", "15.50", "@drawable/burger", "Subway"));

        menuModel.addMenu(new MenuItem("13", "chippies", "big chipps", "8.50", "@drawable/chip", "Jacks"));
        menuModel.addMenu(new MenuItem("14", "whopper", "(favourite)", "10.50", "@drawable/burger", "Jacks"));

        menuModel.addMenu(new MenuItem("15", "loads of chippies", "the only thing we service", "300.0", "@drawable/chip", "Chip Place"));

        /*AccountModel accountModel = new AccountModel();
        accountModel.load(getApplicationContext());
        accountModel.addAccount(new Account("0", "EEE", "EEE"));*/

        menu = findViewById(R.id.Menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                startActivity(intent);

            }
        });
        RecyclerView rv = findViewById(R.id.SpecialRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        LandingAdapter landingAdapter = new LandingAdapter();
        rv.setAdapter(landingAdapter);
    }
    public class LandingViewHolder extends RecyclerView.ViewHolder
    {

        ImageView image;
        TextView name;
        TextView desc;
        TextView price;
        public LandingViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.MenuImage);
            name = itemView.findViewById(R.id.MenuName);
            desc = itemView.findViewById(R.id.MenuDescription);
            price = itemView.findViewById(R.id.MenuPrice);
        }
    }
    private class LandingAdapter extends RecyclerView.Adapter<LandingViewHolder>
    {
        MenuModel menuModel;
        @Override
        public LandingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.menuitemlayout,parent,false);
            LandingViewHolder menuViewHolder = new LandingViewHolder(view);
            menuModel = new MenuModel();
            menuModel.load(getApplicationContext());
            return menuViewHolder;
        }

        @Override
        public void onBindViewHolder(LandingViewHolder holder, int position) {
            ArrayList<MenuItem> menuList = menuModel.getAllMenu();
            Random rand = new Random();
            position = rand.nextInt(menuList.size());
            holder.name.setText(menuList.get(position).name);
            holder.desc.setText(menuList.get(position).description);
            holder.price.setText(menuList.get(position).price + " (" + new Random().nextInt(80) + "% off)   ");
            int imageResource = getResources().getIdentifier(menuList.get(position).pictureLocation, null, getPackageName());
            holder.image.setImageResource(imageResource);
        }
        public int getItemCount() {
            return 5;
        }
    }
}