package com.r4sh33d.tourister;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.r4sh33d.tourister.network.CostStateLovModel;
import com.r4sh33d.tourister.network.RecommendationListModel;
import com.r4sh33d.tourister.utils.TextUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {


    private List<Location> locations;

    public ResultAdapter(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return (new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.individual_location_view, parent, false)));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(locations.get(position));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cost)
        TextView amountView;
        @BindView(R.id.name)
        TextView nameView;
        @BindView(R.id.state)
        TextView stateView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Location location) {

            int cost = Integer.parseInt(location.cost);
            cost = cost * 3;
            cost = round(cost, 100);

            nameView.setText("Location : " + capitalize(location.name));
            amountView.setText("Cost of Trip : "+ TextUtils.formatTextToNaira((double) cost));
            stateView.setText("State : "+capitalize(location.state));
        }
    }

    public String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private int round(int number, int multiple){

        int result = multiple;

        //If not already multiple of given number

        if (number % multiple != 0){

            int division = (number / multiple)+1;

            result = division * multiple;

        }

        return result;

    }
}
