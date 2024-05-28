package my.app.momschoice.customerFoodPanel;

import static my.app.momschoice.customerFoodPanel.CustomerCartAdapter.total;

import androidx.appcompat.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import my.app.momschoice.chefFoodPanel.ChefPendingOrders1;

import my.app.momschoice.R;
import my.app.momschoice.ReusableCode.ReusableCodeForAll;
import my.app.momschoice.SendNotification.APIService;
import my.app.momschoice.SendNotification.Client;
import my.app.momschoice.SendNotification.Data;
import my.app.momschoice.SendNotification.MyResponse;
import my.app.momschoice.SendNotification.NotificationSender;

import my.app.momschoice.SendNotification.Client;
import my.app.momschoice.SendNotification.Data;
import my.app.momschoice.SendNotification.MyResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import my.app.momschoice.customerFoodPanel.Customer;
import my.app.momschoice.chefFoodPanel.ChefPendingOrders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//public class CustomerCartFragment extends Fragment {
//
//    RecyclerView recyclecart;
//    private List<Cart> cartModelList;
//    private CustomerCartAdapter adapter;
//    private LinearLayout TotalBtns;
//    DatabaseReference databaseReference, data, reference, ref, getRef, dataa;
//    public static TextView grandt;
//    Button remove, placeorder;
//    String address, Addnote;
//    String DishId, RandomUId, ChefId;
//    private ProgressDialog progressDialog;
//    private APIService apiService;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        getActivity().setTitle("Cart");
//        View v = inflater.inflate(R.layout.fragment_customercart, null);
//        recyclecart = v.findViewById(R.id.recyclecart);
//        recyclecart.setHasFixedSize(true);
//        recyclecart.setLayoutManager(new LinearLayoutManager(getContext()));
//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setCancelable(false);
//        cartModelList = new ArrayList<>();
//        grandt = v.findViewById(R.id.GT);
//        remove = v.findViewById(R.id.RM);
//        placeorder = v.findViewById(R.id.PO);
//        TotalBtns = v.findViewById(R.id.TotalBtns);
//        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
//        customercart();
//        return v;
//    }
//
//    private void customercart() {
//        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        databaseReference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(userID);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                cartModelList.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Cart cart = snapshot.getValue(Cart.class);
//                    cartModelList.add(cart);
//                }
//                if (cartModelList.size() == 0) {
//                    TotalBtns.setVisibility(View.INVISIBLE);
//                } else {
//                    TotalBtns.setVisibility(View.VISIBLE);
//                    remove.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                            builder.setMessage("Are you sure you want to remove Dish");
//                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
//                                    FirebaseDatabase.getInstance().getReference("Cart").child("GrandTotal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
//                                }
//                            });
//                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//                            AlertDialog alert = builder.create();
//                            alert.show();
//                        }
//                    });
//
//                    String UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                    data = FirebaseDatabase.getInstance().getReference("Customer").child(UserID);
//                    data.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            final Customer customer = dataSnapshot.getValue(Customer.class);
//                            placeorder.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    FirebaseDatabase.getInstance().getReference("AlreadyOrdered").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("isOrdered").addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                            String ss = "";
//                                            if (dataSnapshot.exists()) {
//                                                ss = dataSnapshot.getValue(String.class);
//                                            }
//
//                                            if (ss.trim().equalsIgnoreCase("false") || ss.trim().equalsIgnoreCase("")) {
//                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                                builder.setTitle("Enter Address");
//                                                LayoutInflater inflater = getActivity().getLayoutInflater();
//                                                View view = inflater.inflate(R.layout.enter_address, null);
//                                                final EditText localaddress = view.findViewById(R.id.LA);
//                                                final EditText addnote = view.findViewById(R.id.addnote);
//                                                RadioGroup group = view.findViewById(R.id.grp);
//                                                final RadioButton home = view.findViewById(R.id.HA);
//                                                final RadioButton other = view.findViewById(R.id.OA);
//                                                builder.setView(view);
//                                                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                                                    @Override
//                                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//                                                        if (home.isChecked()) {
//                                                            localaddress.setText(customer.getLocaladdress());
//                                                        } else if (other.isChecked()) {
//                                                            localaddress.getText().clear();
//                                                            Toast.makeText(getContext(), "check", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                    }
//                                                });
//
//                                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        progressDialog.setMessage("Please wait...");
//                                                        progressDialog.show();
//
//                                                        reference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                                                            @Override
//                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                                                RandomUId = UUID.randomUUID().toString();
//                                                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                                                                    final Cart cart1 = dataSnapshot1.getValue(Cart.class);
//                                                                    DishId = cart1.getDishID();
//                                                                    address = localaddress.getText().toString().trim();
//                                                                    Addnote = addnote.getText().toString().trim();
//                                                                    final HashMap<String, String> hashMap = new HashMap<>();
//                                                                    hashMap.put("ChefId", cart1.getChefId());
//                                                                    hashMap.put("DishID", cart1.getDishID());
//                                                                    hashMap.put("DishName", cart1.getDishName());
//                                                                    hashMap.put("DishQuantity", cart1.getDishQuantity());
//                                                                    hashMap.put("Price", cart1.getPrice());
//                                                                    hashMap.put("TotalPrice", cart1.getTotalprice());
//                                                                    FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId).child("Dishes").child(DishId).setValue(hashMap);
//
//                                                                    ChefPendingOrders chefPendingOrders = new ChefPendingOrders();
//                                                                    chefPendingOrders.setChefId(cart1.getChefId());
//                                                                    chefPendingOrders.setDishId(cart1.getDishID());
//                                                                    chefPendingOrders.setDishName(cart1.getDishName());
//                                                                    chefPendingOrders.setDishQuantity(cart1.getDishQuantity());
//                                                                    chefPendingOrders.setPrice(cart1.getPrice());
//                                                                    chefPendingOrders.setTotalPrice(cart1.getTotalprice());
//                                                                    chefPendingOrders.setUserId(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                                                                    chefPendingOrders.setRandomUID(RandomUId);
//                                                                    FirebaseDatabase.getInstance().getReference("ChefPendingOrders").child(cart1.getChefId()).child(RandomUId).child("Dishes").child(DishId).setValue(chefPendingOrders);
//                                                                }
//
//                                                                final HashMap<String, String> hashMap1 = new HashMap<>();
//                                                                hashMap1.put("Address", address);
//                                                                hashMap1.put("GrandTotalPrice", String.valueOf(total));
//                                                                hashMap1.put("MobileNumber", customer.getMobileNo());
//                                                                hashMap1.put("Name", customer.getFirstName() + " " + customer.getLastName());
//                                                                hashMap1.put("Note", Addnote);
//                                                                FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId).child("OtherInformation").setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                                    @Override
//                                                                    public void onComplete(@NonNull Task<Void> task) {
//                                                                        HashMap<String, String> hashMap2 = new HashMap<>();
//                                                                        hashMap2.put("isOrdered", "true");
//                                                                        FirebaseDatabase.getInstance().getReference("AlreadyOrdered").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(hashMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                                            @Override
//                                                                            public void onComplete(@NonNull Task<Void> task) {
//                                                                                FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                                                    @Override
//                                                                                    public void onComplete(@NonNull Task<Void> task) {
//                                                                                        FirebaseDatabase.getInstance().getReference("Cart").child("GrandTotal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                                                            @Override
//                                                                                            public void onComplete(@NonNull Task<Void> task) {
//                                                                                                progressDialog.dismiss();
//                                                                                                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
//                                                                                                builder1.setMessage("Order Placed Successfully!");
//                                                                                                builder1.setCancelable(false);
//                                                                                                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                                                                    @Override
//                                                                                                    public void onClick(DialogInterface dialog, int which) {
//                                                                                                        dialog.dismiss();
//                                                                                                    }
//                                                                                                });
//                                                                                                AlertDialog alert = builder1.create();
//                                                                                                alert.show();
//                                                                                            }
//                                                                                        });
//                                                                                    }
//                                                                                });
//                                                                            }
//                                                                        });
//                                                                    }
//                                                                });
//                                                            }
//
//                                                            @Override
//                                                            public void onCancelled(@NonNull DatabaseError databaseError) {
//                                                            }
//                                                        });
//                                                    }
//                                                });
//                                                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        dialog.dismiss();
//                                                    }
//                                                });
//                                                AlertDialog alert = builder.create();
//                                                alert.show();
//                                            } else {
//                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                                builder.setMessage("You have already placed an order, please wait until the current order is delivered.");
//                                                builder.setCancelable(false);
//                                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        dialog.dismiss();
//                                                    }
//                                                });
//                                                AlertDialog alert = builder.create();
//                                                alert.show();
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                                        }
//                                    });
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                        }
//                    });
//                }
//                adapter = new CustomerCartAdapter(getContext(), cartModelList);
//                recyclecart.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });
//    }
//}



public class CustomerCartFragment extends Fragment {

    RecyclerView recyclecart;
    private List<Cart> cartModelList;
    private CustomerCartAdapter adapter;
    private LinearLayout TotalBtns;
    DatabaseReference databaseReference, data, reference, ref, getRef, dataa;
    public static TextView grandt;
    Button remove, placeorder;
    String address, Addnote;
    String DishId, RandomUId, ChefId;
    private ProgressDialog progressDialog;
    private APIService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Cart");
        View v = inflater.inflate(R.layout.fragment_customercart, null);
        recyclecart = v.findViewById(R.id.recyclecart);
        recyclecart.setHasFixedSize(true);
        recyclecart.setLayoutManager(new LinearLayoutManager(getContext()));
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        cartModelList = new ArrayList<>();
        grandt = v.findViewById(R.id.GT);
        remove = v.findViewById(R.id.RM);
        placeorder = v.findViewById(R.id.PO);
        TotalBtns = v.findViewById(R.id.TotalBtns);
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        customercart();
        return v;
    }

    private void customercart() {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Cart cart = snapshot.getValue(Cart.class);
                    cartModelList.add(cart);
                }
                if (cartModelList.size() == 0) {
                    TotalBtns.setVisibility(View.INVISIBLE);
                } else {
                    TotalBtns.setVisibility(View.VISIBLE);
                    remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("Are you sure you want to remove Dish");
                            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                    FirebaseDatabase.getInstance().getReference("Cart").child("GrandTotal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                                }
                            });
                            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    });

                    String UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    data = FirebaseDatabase.getInstance().getReference("Customer").child(UserID);
                    data.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Customer customer = dataSnapshot.getValue(Customer.class);
                            placeorder.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    FirebaseDatabase.getInstance().getReference("AlreadyOrdered").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("isOrdered").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String ss = "";
                                            if (dataSnapshot.exists()) {
                                                ss = dataSnapshot.getValue(String.class);
                                            }

                                            if (ss.trim().equalsIgnoreCase("false") || ss.trim().equalsIgnoreCase("")) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                                builder.setTitle("Enter Address");
                                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                                View view = inflater.inflate(R.layout.enter_address, null);
                                                final EditText localaddress = view.findViewById(R.id.LA);
                                                final EditText addnote = view.findViewById(R.id.addnote);
                                                RadioGroup group = view.findViewById(R.id.grp);
                                                final RadioButton home = view.findViewById(R.id.HA);
                                                final RadioButton other = view.findViewById(R.id.OA);
                                                builder.setView(view);
                                                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                    @Override
                                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                        if (home.isChecked()) {
                                                            localaddress.setText(customer.getLocaladdress());
                                                        } else if (other.isChecked()) {
                                                            localaddress.getText().clear();
                                                            Toast.makeText(getContext(), "check", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        progressDialog.setMessage("Please wait...");
                                                        progressDialog.show();

                                                        reference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                RandomUId = UUID.randomUUID().toString();
                                                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                                    final Cart cart1 = dataSnapshot1.getValue(Cart.class);
                                                                    DishId = cart1.getDishID();
                                                                    address = localaddress.getText().toString().trim();
                                                                    Addnote = addnote.getText().toString().trim();
                                                                    final HashMap<String, String> hashMap = new HashMap<>();
                                                                    hashMap.put("ChefId", cart1.getChefId());
                                                                    hashMap.put("DishID", cart1.getDishID());
                                                                    hashMap.put("DishName", cart1.getDishName());
                                                                    hashMap.put("DishQuantity", cart1.getDishQuantity());
                                                                    hashMap.put("Price", cart1.getPrice());
//                                                                    hashMap.put("RandomUID", RandomUId);
                                                                    hashMap.put("TotalPrice", cart1.getTotalprice());


                                                                    ChefId = cart1.getChefId();
                                                                    FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId).child("Dishes").child(DishId).setValue(hashMap);
                                                                }

                                                                ref = FirebaseDatabase.getInstance().getReference("Cart").child("GrandTotal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GrandTotal");
                                                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        String grandtotal = dataSnapshot.getValue(String.class);
                                                                        HashMap<String, String> hashMap1 = new HashMap<>();
                                                                        hashMap1.put("Address", address);
                                                                        hashMap1.put("GrandTotalPrice", grandtotal);
                                                                        hashMap1.put("MobileNumber", customer.getMobileNo());
                                                                        hashMap1.put("Name", customer.getFirstName() + " " + customer.getLastName());
                                                                        hashMap1.put("Note", Addnote);
                                                                        hashMap1.put("RandomUID", RandomUId);

                                                                        FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId).child("OtherInformation").setValue(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        FirebaseDatabase.getInstance().getReference("Cart").child("GrandTotal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                getRef = FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId).child("Dishes");
                                                                                                getRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                                                                            final CustomerPendingOrders customerPendingOrders = dataSnapshot1.getValue(CustomerPendingOrders.class);
                                                                                                            String d = customerPendingOrders.getDishID();
                                                                                                            HashMap<String, String> hashMap2 = new HashMap<>();
                                                                                                            hashMap2.put("ChefId", ChefId);
                                                                                                            hashMap2.put("DishId", customerPendingOrders.getDishID());
                                                                                                            hashMap2.put("DishName", customerPendingOrders.getDishName());
                                                                                                            hashMap2.put("DishQuantity", customerPendingOrders.getDishQuantity());
                                                                                                            hashMap2.put("Price", customerPendingOrders.getPrice());
                                                                                                            hashMap2.put("RandomUID", RandomUId);
                                                                                                            hashMap2.put("TotalPrice", customerPendingOrders.getTotalPrice());
                                                                                                            hashMap2.put("UserId", FirebaseAuth.getInstance().getCurrentUser().getUid());


                                                                                                            FirebaseDatabase.getInstance().getReference("ChefPendingOrders").child(ChefId).child(RandomUId).child("Dishes").child(d).setValue(hashMap2);
                                                                                                        }
                                                                                                        dataa = FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(RandomUId).child("OtherInformation");
                                                                                                        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                            @Override
                                                                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                                ChefPendingOrders1 chefPendingOrders1 = dataSnapshot.getValue(ChefPendingOrders1.class);
                                                                                                                HashMap<String, String> hashMap3 = new HashMap<>();
                                                                                                                hashMap3.put("Address", chefPendingOrders1.getAddress());
                                                                                                                hashMap3.put("GrandTotalPrice", chefPendingOrders1.getGrandTotalPrice());
                                                                                                                hashMap3.put("MobileNumber", chefPendingOrders1.getMobileNumber());
                                                                                                                hashMap3.put("Name", chefPendingOrders1.getName());
                                                                                                                hashMap3.put("Note", chefPendingOrders1.getNote());
                                                                                                                hashMap3.put("RandomUID", RandomUId);

                                                                                                                FirebaseDatabase.getInstance().getReference("ChefPendingOrders").child(ChefId).child(RandomUId).child("OtherInformation").setValue(hashMap3).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                                    @Override
                                                                                                                    public void onSuccess(Void aVoid) {

                                                                                                                        FirebaseDatabase.getInstance().getReference("AlreadyOrdered").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("isOrdered").setValue("true").addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                                                            @Override
                                                                                                                            public void onSuccess(Void aVoid) {

                                                                                                                                FirebaseDatabase.getInstance().getReference().child("Tokens").child(ChefId).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                                                    @Override
                                                                                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                                                        String usertoken = dataSnapshot.getValue(String.class);
                                                                                                                                        sendNotifications(usertoken, "New Order", "You have a new Order", "Order");
                                                                                                                                        progressDialog.dismiss();
                                                                                                                                        ReusableCodeForAll.ShowAlert(getContext(), "", "Your Order has been shifted to Pending state, please wait until the Chef accept your order.");
                                                                                                                                    }

                                                                                                                                    @Override
                                                                                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                                                                    }
                                                                                                                                });

                                                                                                                            }
                                                                                                                        });


                                                                                                                    }


                                                                                                                });
                                                                                                            }

                                                                                                            @Override
                                                                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                                            }
                                                                                                        });
                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                                    }
                                                                                                });
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });

                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                                AlertDialog aler = builder.create();
                                                aler.show();

                                            } else {
                                                ReusableCodeForAll.ShowAlert(getContext(), "Error", "It seems you have already placed the order, So you cannot place another order until the delivery of first order");
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
                adapter = new CustomerCartAdapter(getContext(), cartModelList);
                recyclecart.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void sendNotifications(String usertoken, String title, String message, String order) {

        Data data = new Data(title, message, order);
        NotificationSender sender = new NotificationSender(data, usertoken);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });
    }
}





