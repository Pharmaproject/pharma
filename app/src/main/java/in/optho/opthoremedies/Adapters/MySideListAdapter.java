package in.optho.opthoremedies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.optho.opthoremedies.Activities.ProductActivity;
import in.optho.opthoremedies.Models.Product;
import in.optho.opthoremedies.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by krishna on 3/10/17.
 */

public class MySideListAdapter extends RecyclerView.Adapter<MySideListAdapter.MyViewHolder> {

    public ArrayList<Product> productList=new ArrayList<>();
    Context context;

    public MySideListAdapter(Context context, ArrayList<Product> productList) {
        this.productList = productList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_sidepane,null);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked "+viewType, Toast.LENGTH_SHORT).show();
            }
        });

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemTV.setText(productList.get(position).getName());
        holder.snoTV.setText(Integer.toString(productList.get(position).getId()));

  /*      if(productList.get(position).getCategory()==1) {
        holder.card.setCardBackgroundColor(Color.BLUE);
    }
*/

    }

    @Override
    public int getItemCount() {

        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTV;
        TextView snoTV;
        CardView card;

        public MyViewHolder(final View itemView) {
            super(itemView);

            itemTV = (TextView) itemView.findViewById(R.id.itemode);
            snoTV = (TextView) itemView.findViewById(R.id.itemno);
            card = (CardView) itemView.findViewById(R.id.card_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Product product = productList.get(getAdapterPosition());

                    Intent intent = new Intent(itemView.getContext(), ProductActivity.class);
                    intent.putExtra("list",productList);
                    intent.putExtra("PRODUCT", product);
                    itemView.getContext().startActivity(intent);

                    //increase the counter by 1 on each click
                    SharedPreferences storeddata;
                    SharedPreferences.Editor edit;

                    storeddata = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
                    edit = storeddata.edit();

                    int temp = storeddata.getInt(String.valueOf(product.getId()), 0);
                    edit.putInt(String.valueOf(product.getId()),++temp);



                }
            });

        }
    }





    public void setFilter(ArrayList<Product> newList){

        productList = new ArrayList<>();
        productList.addAll(newList);
        notifyDataSetChanged();
    }


}
