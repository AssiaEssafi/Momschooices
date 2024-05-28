package my.app.momschoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.app.momschoice.chefFoodPanel.ChefHomeFragment;
import my.app.momschoice.chefFoodPanel.ChefOrderFragment;
import my.app.momschoice.chefFoodPanel.ChefPendingOrdersFragment;
import my.app.momschoice.chefFoodPanel.ChefProfileFragment;


public class ChefFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.chef_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name=getIntent().getStringExtra("PAGE");
        if(name!=null){
            if(name.equalsIgnoreCase("OrderPage")){
                loadcheffragment(new ChefPendingOrdersFragment());
            }else if (name.equalsIgnoreCase("ConfirmPage")){
                loadcheffragment(new ChefOrderFragment());
            }else if (name.equalsIgnoreCase("AcceptOrderpage")){
                loadcheffragment(new ChefOrderFragment());
            }else if (name.equalsIgnoreCase("DeliveredOrderpage")) {
                loadcheffragment(new ChefOrderFragment());
            }
        }else{
            loadcheffragment(new ChefHomeFragment());
        }

    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Fragment fragment = null;
//        int itemId = item.getItemId();
//
//        if (itemId == R.id.chefHome) {
//            fragment = new ChefHomeFragment();
//        } else if (itemId == R.id.PendingOrders) {
//            fragment = new ChefPendingOrderFragment();
//        } else if (itemId == R.id.Orders) {
//            fragment = new ChefOrderFragment();
//        } else if (itemId == R.id.chefProfile) {
//            fragment = new ChefProfileFragment();
//        }
//
//        return loadcheffragment(fragment);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.chefHome:
                fragment=new ChefHomeFragment();
                break;
            case R.id.PendingOrders:
                fragment=new ChefPendingOrdersFragment();
                break;
            case R.id.Orders:
                fragment=new ChefOrderFragment();
                break;
            case R.id.chefProfile:
                fragment=new ChefProfileFragment();
                break;
        }
        return loadcheffragment(fragment);
    }





    private boolean loadcheffragment(Fragment fragment) {

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
        return false;
    }


}