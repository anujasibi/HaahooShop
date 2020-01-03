package com.example.haahooshop;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class choosepdtcategoryadapter extends
        RecyclerView.Adapter<choosepdtcategoryadapter.ViewHolder> {

    private List<OffersModel> offersList;
    private Context context;

    private int lastSelectedPosition = -1;

    public choosepdtcategoryadapter(List<OffersModel> offersListIn
            , Context ctx) {
        offersList = offersListIn;
        context = ctx;
    }

    @Override
    public choosepdtcategoryadapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.radio, parent, false);

        choosepdtcategoryadapter.ViewHolder viewHolder =
                new choosepdtcategoryadapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(choosepdtcategoryadapter.ViewHolder holder,
                                 int position) {
        OffersModel offersModel = offersList.get(position);

        holder.offerAmount.setText("" + offersModel.getName());

        //since only one radio button is allowed to be selected,
        // this condition un-checks previous selections
        holder.selectionState.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView offerAmount;
        public RadioButton selectionState;

        public ViewHolder(View view) {
            super(view);
            offerAmount = (TextView) view.findViewById(R.id.offer_amount_txt);
            selectionState = (RadioButton) view.findViewById(R.id.offer_select);

            selectionState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                    Toast.makeText(choosepdtcategoryadapter.this.context,
                            "selected offer is " + offerAmount.getText(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}