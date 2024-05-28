package my.app.momschoice.DeliveryFoodPanel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import my.app.momschoice.MainMenu;
import my.app.momschoice.R;

public class DeliveryPendingOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<DeliveryShipOrders1> deliveryShipOrders1List;
    private DeliveryPendingOrderFragmentAdapter adapter;
    private DatabaseReference databaseReference;
    private SwipeRefreshLayout swipeRefreshLayout;
    String deliveryId = "oCpc4SwLVFbKO0fPdtp4R6bmDmI3";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_deliverypendingorders, null);
        getActivity().setTitle("Pending Orders");
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.delipendingorder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deliveryShipOrders1List = new ArrayList<>();
        swipeRefreshLayout = view.findViewById(R.id.Swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.Black, R.color.Black);
        adapter = new DeliveryPendingOrderFragmentAdapter(getContext(), deliveryShipOrders1List);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                deliveryShipOrders1List.clear();
                recyclerView = view.findViewById(R.id.delipendingorder);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                deliveryShipOrders1List = new ArrayList<>();
                DeliveryPendingOrders();
            }
        });
        DeliveryPendingOrders();

        return view;
    }

    private void DeliveryPendingOrders() {
        // Assurez-vous que swipeRefreshLayout et recyclerView sont correctement initialisés.
        swipeRefreshLayout.setRefreshing(true);

        // Initialiser l'adaptateur avec une liste vide
        deliveryShipOrders1List = new ArrayList<>();
        adapter = new DeliveryPendingOrderFragmentAdapter(getContext(), deliveryShipOrders1List);
        recyclerView.setAdapter(adapter);

        // Référence de la base de données
        databaseReference = FirebaseDatabase.getInstance().getReference("DeliveryShipOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        // Écouter les changements dans la base de données
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    deliveryShipOrders1List.clear(); // Vider la liste avant de l'ajouter à nouveau

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key = snapshot.getKey();
                        DatabaseReference data = FirebaseDatabase.getInstance().getReference("DeliveryShipOrders")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child(key)
                                .child("OtherInformation");

                        data.addListenerForSingleValueEvent(new ValueEventListener() {
                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    DeliveryShipOrders1 deliveryShipOrders1 = dataSnapshot.getValue(DeliveryShipOrders1.class);
                                    if (deliveryShipOrders1 != null) {
                                        // Ajouter le numéro de téléphone mobile à l'objet DeliveryShipOrders1
                                        deliveryShipOrders1.setMobileNumber(dataSnapshot.child("MobileNumber").getValue(String.class));
                                        deliveryShipOrders1List.add(deliveryShipOrders1);
                                        adapter.notifyDataSetChanged(); // Notifier l'adaptateur des changements
                                    }
                                } else {
                                    Log.d("FirebaseData", "No data exists at " + data.getPath());
                                }
                                swipeRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e("FirebaseError", databaseError.getMessage());
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                } else {
                    Log.d("FirebaseData", "No delivery orders found for user " + FirebaseAuth.getInstance().getCurrentUser().getUid());
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseError", databaseError.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idd = item.getItemId();
        if (idd == R.id.LOGOUT) {
            Logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
