package my.app.momschoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.annotations.NonNull;
import my.app.momschoice.DeliveryFoodPanel.DeliveryPendingOrderFragment;
import my.app.momschoice.DeliveryFoodPanel.DeliveryShipOrderFragment;

public class DeliveryFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.delivery_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        String name=getIntent().getStringExtra("PAGE");
        if(name!=null){
            if(name.equalsIgnoreCase("DeliveryOrderPage")) {
                loaddeliveryfragment(new DeliveryPendingOrderFragment());
            }
        }else{
            loaddeliveryfragment(new DeliveryPendingOrderFragment());
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.shiporders) {
            fragment = new DeliveryShipOrderFragment();
        } else if (itemId == R.id.PendingOrders) {
            fragment = new DeliveryPendingOrderFragment();
        }
        return loaddeliveryfragment(fragment);
    }

    private boolean loaddeliveryfragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }
}