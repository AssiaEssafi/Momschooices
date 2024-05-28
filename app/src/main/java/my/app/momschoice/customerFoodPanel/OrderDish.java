package my.app.momschoice.customerFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
//import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import my.app.momschoice.chefFoodPanel.Chef;
import my.app.momschoice.chefFoodPanel.UpdateDishModel;
//import my.app.momschoice.Customer;
import my.app.momschoice.CustomerFoodPanel_BottomNavigation;
import my.app.momschoice.customerFoodPanel.Customer;
import my.app.momschoice.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

//public class OrderDish extends AppCompatActivity {
//
//    String RandomId, ChefID;
//    ImageView imageView;
//    NumberPicker additem;
//    TextView Foodname, ChefName, ChefLoaction, FoodQuantity, FoodPrice, FoodDescription;
//    DatabaseReference databaseReference, dataaa, chefdata, reference, data, dataref;
//    String State, Area, dishname;
//    int dishprice;
//    String custID;
//    FirebaseDatabase firebaseDatabase;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_order_dish);
//
//        Foodname = findViewById(R.id.food_name);
//        ChefName = findViewById(R.id.chef_name);
//        ChefLoaction = findViewById(R.id.chef_location);
//        FoodQuantity = findViewById(R.id.food_quantity);
//        FoodPrice = findViewById(R.id.food_price);
//        FoodDescription = findViewById(R.id.food_description);
//        imageView = findViewById(R.id.image);
//        additem = findViewById(R.id.numberbtn);
//
//        final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        dataaa = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
//
//        RandomId = getIntent().getStringExtra("RandomUID");
//        ChefID = getIntent().getStringExtra("Chefid");
//
//        if (RandomId == null) {
//            Log.e("OrderDish", "RandomUID  is null");
//            finish();
//            return;
//        }else if (ChefID == null) {
//            Log.e("OrderDish", "RandomUID  is null");
//            finish();
//            return;
//        }
//
//
//
//        dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Customer cust = dataSnapshot.getValue(Customer.class);
//                if (cust != null) {
//                    State = cust.getState();
//                    Area = cust.getArea();
//
//                    databaseReference = FirebaseDatabase.getInstance()
//                            .getReference("FoodDetails")
//                            .child(State)
//                            .child(Area)
//                            .child(ChefID)
//                            .child(RandomId);
//
//                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            UpdateDishModel updateDishModel = dataSnapshot.getValue(UpdateDishModel.class);
//                            if (updateDishModel != null) {
//                                Foodname.setText(updateDishModel.getDishes());
//                                String qua = "<b>Quantity: </b>" + updateDishModel.getQuantity();
//                                FoodQuantity.setText(Html.fromHtml(qua));
//                                String ss = "<b>Description: </b>" + updateDishModel.getDescription();
//                                FoodDescription.setText(Html.fromHtml(ss));
//                                String pri = "<b>Price: Dhs </b>" + updateDishModel.getPrice();
//                                FoodPrice.setText(Html.fromHtml(pri));
//                                Glide.with(OrderDish.this).load(updateDishModel.getImageURL()).into(imageView);
//
//                                chefdata = FirebaseDatabase.getInstance().getReference("Chef").child(ChefID);
//                                chefdata.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        Chef chef = dataSnapshot.getValue(Chef.class);
//                                        if (chef != null) {
//                                            String name = "<b>Chef Name: </b>" + chef.getFname() + " " + chef.getLname();
//                                            ChefName.setText(Html.fromHtml(name));
//                                            String loc = "<b>Location: </b>" + chef.getArea();
//                                            ChefLoaction.setText(Html.fromHtml(loc));
//
//                                            custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                                            DatabaseReference cartReference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(custID).child(RandomId);
//                                            cartReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                                    if (dataSnapshot.exists()) {
//                                                        Cart cart = dataSnapshot.getValue(Cart.class);
//                                                        if (cart != null) {
//                                                            additem.setValue(Integer.parseInt(cart.getDishQuantity()));
//                                                        }
//                                                    }
//                                                }
//
//                                                @Override
//                                                public void onCancelled(@NonNull DatabaseError databaseError) {
//                                                    // Handle error
//                                                }
//                                            });
//                                        } else {
//                                            Log.e("OrderDish", "Chef data is null");
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                                        // Handle error
//                                    }
//                                });
//                            } else {
//                                Log.e("OrderDish", "UpdateDishModel data is null");
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                            // Handle error
//                        }
//                    });
//                } else {
//                    Log.e("OrderDish", "Customer data is null");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//            }
//        });
//
//        additem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handleAddItemClick();
//            }
//        });
//    }
//
//    private void handleAddItemClick() {
//        dataref = FirebaseDatabase.getInstance().getReference("Cart").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//        dataref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Cart cart1 = null;
//                if (dataSnapshot.exists()) {
//                    int totalcount = 0;
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        totalcount++;
//                    }
//                    int i = 0;
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        i++;
//                        if (i == totalcount) {
//                            cart1 = snapshot.getValue(Cart.class);
//                        }
//                    }
//
//                    if (cart1 != null && ChefID.equals(cart1.getChefId())) {
//                        data = FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(Area).child(ChefID).child(RandomId);
//                        data.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                UpdateDishModel update = dataSnapshot.getValue(UpdateDishModel.class);
//                                if (update != null) {
//                                    dishname = update.getDishes();
//                                    dishprice = Integer.parseInt(update.getPrice());
//                                    int num = additem.getValue();
//                                    int totalprice = num * dishprice;
//                                    if (num != 0) {
//                                        HashMap<String, String> hashMap = new HashMap<>();
//                                        hashMap.put("DishName", dishname);
//                                        hashMap.put("DishID", RandomId);
//                                        hashMap.put("DishQuantity", String.valueOf(num));
//                                        hashMap.put("Price", String.valueOf(dishprice));
//                                        hashMap.put("Totalprice", String.valueOf(totalprice));
//                                        hashMap.put("ChefId", ChefID);
//                                        custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                                        reference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(custID).child(RandomId);
//                                        reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Toast.makeText(OrderDish.this, "Added to cart", Toast.LENGTH_SHORT).show();
//                                            }
//                                        });
//                                    } else {
//                                        FirebaseDatabase.getInstance().getReference("Cart").child(custID).child(RandomId).removeValue();
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//                                // Handle error
//                            }
//                        });
//                    } else {
//                        showMultipleChefAlert();
//                    }
//                } else {
//                    data = FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(Area).child(ChefID).child(RandomId);
//                    data.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            UpdateDishModel update = dataSnapshot.getValue(UpdateDishModel.class);
//                            if (update != null) {
//                                dishname = update.getDishes();
//                                dishprice = Integer.parseInt(update.getPrice());
//                                int num = additem.getValue();
//                                int totalprice = num * dishprice;
//                                if (num != 0) {
//                                    HashMap<String, String> hashMap = new HashMap<>();
//                                    hashMap.put("DishName", dishname);
//                                    hashMap.put("DishID", RandomId);
//                                    hashMap.put("DishQuantity", String.valueOf(num));
//                                    hashMap.put("Price", String.valueOf(dishprice));
//                                    hashMap.put("Totalprice", String.valueOf(totalprice));
//                                    hashMap.put("ChefId", ChefID);
//                                    custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                                    reference = FirebaseDatabase.getInstance().getReference("Cart").child(custID).child(RandomId);
//                                    reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void aVoid) {
//                                            Toast.makeText(OrderDish.this, "Added to cart", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                } else {
//                                    FirebaseDatabase.getInstance().getReference("Cart").child(custID).child(RandomId).removeValue();
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                            // Handle error
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle error
//            }
//        });
//    }
//
//    private void showMultipleChefAlert() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDish.this);
//        builder.setMessage("You can't add food items of multiple chefs at a time. Try to add items of the same chef.");
//        builder.setCancelable(false);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                Intent intent = new Intent(OrderDish.this, CustomerFoodPanel_BottomNavigation.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
//}

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import my.app.momschoice.R;
import my.app.momschoice.customerFoodPanel.Customer;

public class OrderDish extends AppCompatActivity {

    String RandomId, ChefID;
    ImageView imageView;
    NumberPicker additem;
    TextView Foodname, ChefName, ChefLocation, FoodQuantity, FoodPrice, FoodDescription;
    DatabaseReference databaseReference, dataaa, chefdata, reference, data, dataref;
    String State, Area, dishname;
    int dishprice;
    String custID;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dish);

        Foodname = findViewById(R.id.food_name);
        ChefName = findViewById(R.id.chef_name);
        ChefLocation = findViewById(R.id.chef_location);
        FoodQuantity = findViewById(R.id.food_quantity);
        FoodPrice = findViewById(R.id.food_price);
        FoodDescription = findViewById(R.id.food_description);
        imageView = findViewById(R.id.image);
        additem = findViewById(R.id.numberbtn);

        // Configurer la plage de valeurs et la valeur initiale du NumberPicker
        additem.setMinValue(0);
        additem.setMaxValue(10);  // Supposons que la quantité maximale autorisée est 10
        additem.setValue(1);

        final String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataaa = FirebaseDatabase.getInstance().getReference("Customer").child(userid);

        RandomId = getIntent().getStringExtra("RandomUID");
        ChefID = getIntent().getStringExtra("Chefid");

        if (RandomId == null) {
            Log.e("OrderDish", "RandomUID est null");
            finish();
            return;
        } else if (ChefID == null) {
            Log.e("OrderDish", "ChefID est null");
            finish();
            return;
        }

        dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customer cust = dataSnapshot.getValue(Customer.class);
                if (cust != null) {
                    State = cust.getState();
                    Area = cust.getArea();

                    databaseReference = firebaseDatabase.getInstance()
                            .getReference("FoodDetails")
                            .child(State)
                            .child(Area)
                            .child(ChefID)
                            .child(RandomId);

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            UpdateDishModel updateDishModel = dataSnapshot.getValue(UpdateDishModel.class);
                            if (updateDishModel != null) {
                                Foodname.setText(updateDishModel.getDishes());
                                String qua = "<b>Quantity: </b>" + updateDishModel.getQuantity();
                                FoodQuantity.setText(Html.fromHtml(qua));
                                String ss = "<b>Price: $ </b>" + updateDishModel.getPrice();
                                FoodPrice.setText(Html.fromHtml(ss));
                                FoodDescription.setText(updateDishModel.getDescription());
                                Glide.with(OrderDish.this).load(updateDishModel.getImageURL()).into(imageView);
                                chefdata = firebaseDatabase.getInstance().getReference("Chef").child(ChefID);
                                chefdata.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Chef cheff = dataSnapshot.getValue(Chef.class);
                                        if (cheff != null) {
                                            ChefName.setText(cheff.getFname() + " " + cheff.getLname());
                                            String loc = "<b>Location: </b>" + cheff.getArea();
                                            ChefLocation.setText(Html.fromHtml(loc));
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        // Gérer l'erreur
                                    }
                                });
                                custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                dataref = FirebaseDatabase.getInstance().getReference("Cart")
                                        .child("CartItems")
                                        .child(custID)
                                        .child(RandomId);

                                dataref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            Cart cart = dataSnapshot.getValue(Cart.class);
                                            if (cart != null) {
                                                additem.setValue(Integer.parseInt(cart.getDishQuantity()));
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        // Gérer l'erreur
                                    }
                                });

                                additem.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                                    @Override
                                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                                        handleAddItemClick(newVal);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Gérer l'erreur
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer l'erreur
            }
        });
    }

    private void handleAddItemClick(int quantity) {
        custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dataref = FirebaseDatabase.getInstance().getReference("Cart")
                .child("CartItems")
                .child(custID);

        dataref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Cart cart = snapshot.getValue(Cart.class);
                        if (cart != null && !ChefID.equals(cart.getChefId())) {
                            showMultipleChefAlert();
                            return;
                        }
                    }

                    updateCart();
                } else {
                    updateCart();
                }
            }

            private void updateCart() {
                if (quantity > 0) {
                    data = FirebaseDatabase.getInstance()
                            .getReference("FoodDetails")
                            .child(State)
                            .child(Area)
                            .child(ChefID)
                            .child(RandomId);

                    data.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            UpdateDishModel update = dataSnapshot.getValue(UpdateDishModel.class);
                            if (update != null) {
                                dishname = update.getDishes();
                                dishprice = Integer.parseInt(update.getPrice());
                                int totalprice = quantity * dishprice;
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("DishName", dishname);
                                hashMap.put("DishID", RandomId);
                                hashMap.put("DishQuantity", String.valueOf(quantity));
                                hashMap.put("Price", String.valueOf(dishprice));
                                hashMap.put("Totalprice", String.valueOf(totalprice));
                                hashMap.put("ChefId", ChefID);
                                reference = FirebaseDatabase.getInstance()
                                        .getReference("Cart")
                                        .child("CartItems")
                                        .child(custID)
                                        .child(RandomId);

                                reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(OrderDish.this, "Ajouté au panier", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Gérer l'erreur
                        }
                    });
                } else {
                    FirebaseDatabase.getInstance()
                            .getReference("Cart")
                            .child(custID)
                            .child(RandomId)
                            .removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer l'erreur
            }
        });
    }

    private void showMultipleChefAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDish.this);
        builder.setMessage("Vous ne pouvez pas ajouter des plats de plusieurs chefs en même temps. Essayez d'ajouter des éléments du même chef.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            Intent intent = new Intent(OrderDish.this, CustomerFoodPanel_BottomNavigation.class);
            startActivity(intent);
            finish();
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}



