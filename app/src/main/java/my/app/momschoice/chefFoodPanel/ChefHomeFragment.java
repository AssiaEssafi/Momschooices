package my.app.momschoice.chefFoodPanel;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import my.app.momschoice.MainMenu;
import my.app.momschoice.R;
import my.app.momschoice.UpdateDishModel;

public class ChefHomeFragment extends Fragment {
    RecyclerView recyclerView;
    private List<UpdateDishModel> updateDishModelList;
    private ChefHomeAdapter adapter;
    DatabaseReference dataa;
    private String State,Area;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chef_home,null);
        getActivity().setTitle("Home");
        setHasOptionsMenu(true);
        recyclerView = v.findViewById(R.id.Recycle_menu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateDishModelList = new ArrayList<>();
        String userid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        dataa = FirebaseDatabase.getInstance().getReference("Chef").child(userid);
        dataa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Chef cheff = snapshot.getValue(Chef.class);
//                assert cheff != null;
                State = cheff.getState();
                Area = cheff.getArea();
                chefDishes();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return v;
    }


    private void chefDishes() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            if (State != null && Area != null && userId != null) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference("FoodDetails")
                        .child(State)
                        .child(Area)
                        .child(userId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        updateDishModelList.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            UpdateDishModel updateDishModel = snapshot1.getValue(UpdateDishModel.class);
                            if (updateDishModel != null) {
                                updateDishModelList.add(updateDishModel);
                            }
                        }
                        adapter = new ChefHomeAdapter(getContext(), updateDishModelList);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Gérer l'annulation de la récupération des données de la base de données
                    }
                });
            } else {
                // Gérer le cas où State, Area, ou userId sont null
            }
        } else {
            // Gérer le cas où aucun utilisateur n'est actuellement authentifié
        }
    }
//private void chefDishes() {
//
//    String useridd = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("FoodDetails").child(State).child(Area).child(useridd);
//    databaseReference.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            updateDishModelList.clear();
//            for(DataSnapshot snapshot1:snapshot.getChildren()){
//                UpdateDishModel updateDishModel = snapshot1.getValue(UpdateDishModel.class);
//                updateDishModelList.add(updateDishModel);
//            }
//            adapter = new ChefHomeAdapter(getContext(),updateDishModelList);
//            recyclerView.setAdapter(adapter);
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    });







    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int idd = item.getItemId();
        if(idd == R.id.LOGOUT){
            Logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
