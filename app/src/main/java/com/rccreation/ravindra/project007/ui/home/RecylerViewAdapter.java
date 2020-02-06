package com.rccreation.ravindra.project007.ui.home;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.rccreation.ravindra.project007.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by root on 7/3/18.
 */

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder>

{
    Context context;
    URI urI;
    Intent intent;
    List<javafunctionslist> listofcards;
    CardView cardView;
    int y;
    Intent intentw;
    RecyclerView recyclerView;




    public RecylerViewAdapter(Context context, List<javafunctionslist> TempList) {

        this.listofcards = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardsonview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        y = position;

        final javafunctionslist functionslistmodel = listofcards.get(position);

        holder.functionname.setText(functionslistmodel.getName());

        holder.functionaddress.setText(functionslistmodel.getAddress());

holder.contact.setText(functionslistmodel.getContact());

holder.type.setText(functionslistmodel.getDescription());

        holder.setImage(context, functionslistmodel.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // final Dialog dialog = new Dialog(context);
                // dialog.setContentView(R.layout.custom_date_picker);
                // dialog.setTitle("select date and time");
                // dialog.show();

                Intent intentw;
              //  intentw = new Intent(context, Extended.class);
             //   intentw.putExtra("value1", carsAdminPacarrentClass.getCarName());

               // context.startActivity(intentw);

            }


        });
    }

    @Override
    public int getItemCount() {

        return listofcards.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView functionname;
        public TextView functionaddress;
        public TextView contact;
        public TextView type;
        ImageView post_image;
        public ImageView overflow;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            functionname = (TextView) itemView.findViewById(R.id.hallname);
            functionaddress = (TextView) itemView.findViewById(R.id.functionalhalllocation);
            contact = (TextView) itemView.findViewById(R.id.halltype);
            type = (TextView) itemView.findViewById(R.id.hallcontact);


            cardView = (CardView) itemView.findViewById(R.id.cardview1);
        }


        void setImage(final Context ctx, final String image) {



           post_image = (ImageView) itemView.findViewById(R.id.imageview123);

            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                    Picasso.with(ctx).load(image).into(post_image);
                    Toast.makeText(ctx, " loading images", Toast.LENGTH_SHORT).show();

                }
            });

        }





    }


    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

           listofcards.remove(viewHolder.getAdapterPosition());

        }

        @Override
        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        }
    };

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

}