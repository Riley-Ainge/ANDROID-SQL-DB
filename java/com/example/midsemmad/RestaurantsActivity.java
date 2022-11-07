package com.example.midsemmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    public static String selectedRestaurant = "";
    public static String selectedItem = "";

    private Button plus;
    private Button minus;
    private Button addToCart;
    private Button checkout;
    private Button back;

    private int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        RecyclerView rv = findViewById(R.id.RestaurantRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter();
        rv.setAdapter(restaurantAdapter);

        RecyclerView rv2 = findViewById(R.id.MenuRecycler);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        MenuAdapter menuAdapter = new MenuAdapter();
        rv2.setAdapter(menuAdapter);

        plus = findViewById(R.id.Plus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity += 1;
                TextView text = (TextView)findViewById(R.id.Quantity);
                text.setText(String.valueOf(quantity));
            }
        });
        minus = findViewById(R.id.Minus);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity-1 >= 1) {
                    quantity -= 1;
                    TextView text = (TextView) findViewById(R.id.Quantity);
                    text.setText(String.valueOf(quantity));
                }
            }
        });
        addToCart = findViewById(R.id.AddToCart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!selectedItem.equals("")) {
                    CheckoutModel checkoutModel = new CheckoutModel();
                    checkoutModel.load(getApplicationContext());
                    checkoutModel.addCheckout(new Checkout(String.valueOf(checkoutModel.getAllCheckout().size()), String.valueOf(quantity), selectedItem));
                    Log.d("CHECKEDOUT", String.valueOf(checkoutModel.getAllCheckout().size()));
                }
            }
        });
        checkout = findViewById(R.id.Checkout);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantsActivity.this, CheckoutActivity.class);
                startActivity(intent);

            }
        });
        back = findViewById(R.id.RestaurantBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public class RestaurantViewHolder extends RecyclerView.ViewHolder
    {

        ImageView image;
        TextView name;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.RestaurantImage);
            name = itemView.findViewById(R.id.RestaurantName);
        }
    }
    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder>
    {
        RestaurantModel restaurantModel;
        @Override
        public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.restaurantlayout,parent,false);
            RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(view);
            restaurantModel = new RestaurantModel();
            restaurantModel.load(getApplicationContext());
            return restaurantViewHolder;
        }

        @Override
        public void onBindViewHolder(RestaurantViewHolder holder, int position) {
            ArrayList<Restaurant> restaurantList = restaurantModel.getAllRestaurant();
            String tempName = restaurantList.get(position).name;
            holder.name.setText(restaurantList.get(position).name);
            int imageResource = getResources().getIdentifier(restaurantList.get(position).pictureLocation, null, getPackageName());
            holder.image.setImageResource(imageResource);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedRestaurant = tempName;
                    RecyclerView rv2 = findViewById(R.id.MenuRecycler);
                    rv2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    MenuAdapter menuAdapter = new MenuAdapter();
                    rv2.setAdapter(menuAdapter);
                }
            });

        }
        public int getItemCount() {
            RestaurantModel model = new RestaurantModel();
            model.load(getApplicationContext());
            return model.getAllRestaurant().size();
        }
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder
    {

        ImageView image;
        TextView name;
        TextView desc;
        TextView price;
        public MenuViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.MenuImage);
            name = itemView.findViewById(R.id.MenuName);
            desc = itemView.findViewById(R.id.MenuDescription);
            price = itemView.findViewById(R.id.MenuPrice);
        }
    }
    private class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder>
    {
        MenuModel menuModel;
        @Override
        public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.menuitemlayout,parent,false);
            MenuViewHolder menuViewHolder = new MenuViewHolder(view);
            menuModel = new MenuModel();
            menuModel.load(getApplicationContext());
            return menuViewHolder;
        }

        @Override
        public void onBindViewHolder(MenuViewHolder holder, int position) {

            if(!selectedRestaurant.equals("") ) {

                ArrayList<MenuItem> menuList = menuModel.getMenuForRestaurant(selectedRestaurant);
                if (menuList.size() > 0) {
                    String tempName = menuList.get(position).id;
                    holder.name.setText(menuList.get(position).name);
                    holder.desc.setText(menuList.get(position).description);
                    holder.price.setText(menuList.get(position).price);
                    int imageResource = getResources().getIdentifier(menuList.get(position).pictureLocation, null, getPackageName());
                    holder.image.setImageResource(imageResource);
                    holder.image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            selectedItem = tempName;
                            Log.d("EEEA", selectedItem);
                        }
                    });
                }
                else
                {
                    holder.name.setText("");
                    holder.desc.setText("");
                    holder.price.setText("");
                    holder.image.setImageResource(0);
                }
            } else
            {
                holder.name.setText("");
                holder.desc.setText("");
                holder.price.setText("");
                holder.image.setImageResource(0);
            }
        }
        public int getItemCount() {
            if(selectedRestaurant.equals(""))
                return 0;
            MenuModel model = new MenuModel();
            model.load(getApplicationContext());
            return model.getMenuForRestaurant(selectedRestaurant).size();
        }
    }
}