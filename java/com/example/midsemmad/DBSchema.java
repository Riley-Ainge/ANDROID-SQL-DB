package com.example.midsemmad;

public class DBSchema {
    public static class MenuTable{
        public static final String NAME = "menuItem";
        public static class Cols{
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String DESC = "description";
            public static final String PRICE = "price";
            public static final String LOCATION = "location";
            public static final String RESTAURANT = "restaurant";
        }
    }
    public static class RestaurantTable{
        public static final String NAME = "restaurant";
        public static class Cols{
            public static final String NAME = "name";
            public static final String LOCATION = "location";
        }
    }
    public static class CheckoutTable{
        public static final String NAME = "checkout";
        public static class Cols{
            public static final String ID = "id";
            public static final String QUANTITY = "quantity";
            public static final String MENUID = "menuItemID";
        }
    }
    public static class AccountTable{
        public static final String NAME = "account";
        public static class Cols{
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String PASSWORD = "password";
        }
    }
    public static class LogTable{
        public static final String NAME = "log";
        public static class Cols{
            public static final String ID = "id";
            public static final String RESTAURANTS = "restaurants";
            public static final String PRICE = "priceBreakdown";
            public static final String TOTALPRICE = "totalPrice";
            public static final String ACCOUNTID = "AccountID";
        }
    }
}
