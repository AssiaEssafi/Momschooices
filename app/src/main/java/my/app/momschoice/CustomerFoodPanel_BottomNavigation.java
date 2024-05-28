package my.app.momschoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.annotations.NonNull;
import my.app.momschoice.customerFoodPanel.CustomerCartFragment;
import my.app.momschoice.customerFoodPanel.CustomerHomeFragment;
import my.app.momschoice.customerFoodPanel.CustomerOrderFragment;
import my.app.momschoice.customerFoodPanel.CustomerProfileFragment;
import my.app.momschoice.customerFoodPanel.CustomerTrackFragment;

public class CustomerFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name=getIntent().getStringExtra("PAGE");
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        if(name!=null){
            if(name.equalsIgnoreCase("HomePage")){
                loadcustomerfragment(new CustomerHomeFragment());
            }else if (name.equalsIgnoreCase("PreparingPage")){
                loadcustomerfragment(new CustomerTrackFragment());
            }else if (name.equalsIgnoreCase("DeliveryOrderpage")){
                loadcustomerfragment(new CustomerTrackFragment());
            }else if (name.equalsIgnoreCase("Thankyoupage")) {
                loadcustomerfragment(new CustomerHomeFragment());
            }

        }else{
            loadcustomerfragment(new CustomerHomeFragment());
        }

    }
    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.cust_Home){
            fragment=new CustomerHomeFragment();
        }
        else if (itemId == R.id.cust_profile){
            fragment=new CustomerProfileFragment();
        }
        else if (itemId == R.id.Cust_order){
            fragment=new CustomerOrderFragment();
        }
        else if (itemId == R.id.track){
            fragment=new CustomerTrackFragment();
        }
        else if (itemId == R.id.cart){
            fragment=new CustomerCartFragment();
        }
        return loadcustomerfragment(fragment);

    }

    private boolean loadcustomerfragment(Fragment fragment) {

        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }
}