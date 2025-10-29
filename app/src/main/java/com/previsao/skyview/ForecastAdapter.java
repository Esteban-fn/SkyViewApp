package com.previsao.skyview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.previsao.skyview.model.Forecast;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<Forecast> forecastList;

    public ForecastAdapter(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        Forecast forecast = forecastList.get(position);
        holder.textDate.setText(forecast.getDate() + " - " + forecast.getWeekday());
        holder.textDescription.setText(forecast.getDescription());
        holder.textTemp.setText(forecast.getMin() + "° / " + forecast.getMax() + "°");
    }

    @Override
    public int getItemCount() {
        return forecastList != null ? forecastList.size() : 0;
    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder {
        TextView textDate, textDescription, textTemp;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.text_date);
            textDescription = itemView.findViewById(R.id.text_description);
            textTemp = itemView.findViewById(R.id.text_temp);
        }
    }

    public void updateData(List<Forecast> newForecastList) {
        this.forecastList.clear();
        this.forecastList.addAll(newForecastList);
        notifyDataSetChanged();
    }
}
