package my.app.momschoice.customerFoodPanel;

import static androidx.core.content.ContextCompat.startActivity;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import my.app.momschoice.R;
import my.app.momschoice.chefFoodPanel.UpdateDishModel;

//public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder> {
//    private Context mcontext;
//    private ArrayList<UpdateDishModel> updateDishModellist;
//    private OnItemClickListener mListener;
//
//    public CustomerHomeAdapter(Context context, ArrayList<my.app.momschoice.UpdateDishModel> updateDishModelList, CustomerHomeFragment listener) {
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(UpdateDishModel updateDishModel);
//    }
//
//    public CustomerHomeAdapter(Context context, ArrayList<UpdateDishModel> updateDishModelslist, OnItemClickListener listener) {
//        this.updateDishModellist = updateDishModelslist;
//        this.mcontext = context;
//        this.mListener = listener;
//    }
//
//    @NonNull
//    @Override
//    public CustomerHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mcontext).inflate(R.layout.customer_menudish, parent, false);
//        return new CustomerHomeAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomerHomeAdapter.ViewHolder holder, int position) {
//        final UpdateDishModel updateDishModel = updateDishModellist.get(position);
//        Glide.with(mcontext).load(updateDishModel.getImageURL()).into(holder.imageView);
//        holder.Dishname.setText(updateDishModel.getDishes());
//        holder.Price.setText("Price: " + updateDishModel.getPrice() + "DHs");
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener.onItemClick(updateDishModel);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return updateDishModellist.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView Dishname, Price;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.menu_image);
//            Dishname = itemView.findViewById(R.id.dishname);
//            Price = itemView.findViewById(R.id.dishprice);
//        }
//    }
//
//
//}



public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<UpdateDishModel> updateDishModellist;
    private OnItemClickListener mListener;

    public CustomerHomeAdapter(Context context, ArrayList<UpdateDishModel> updateDishModelslist, OnItemClickListener listener) {
        this.updateDishModellist = updateDishModelslist;
        this.mcontext = context;
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(UpdateDishModel updateDishModel);
    }

    @NonNull
    @Override
    public CustomerHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.customer_menudish, parent, false);
        return new CustomerHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerHomeAdapter.ViewHolder holder, int position) {
        final UpdateDishModel updateDishModel = updateDishModellist.get(position);
        Glide.with(mcontext).load(updateDishModel.getImageURL()).into(holder.imageView);
        holder.Dishname.setText(updateDishModel.getDishes());
        holder.Price.setText("Price: " + updateDishModel.getPrice() + "DHs");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CustomerHomeAdapter", "Item clicked: " + updateDishModel.getDishes());
                Log.d("CustomerHomeAdapter", "RandomUID: " + updateDishModel.getRandomUID() + ", ChefID: " + updateDishModel.getChefid());
                mListener.onItemClick(updateDishModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return updateDishModellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView Dishname, Price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.menu_image);
            Dishname = itemView.findViewById(R.id.dishname);
            Price = itemView.findViewById(R.id.dishprice);
        }
    }
}

