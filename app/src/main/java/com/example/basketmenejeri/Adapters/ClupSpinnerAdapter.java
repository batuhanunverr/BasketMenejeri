package com.example.basketmenejeri.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.basketmenejeri.R;
import java.util.List;

public class ClupSpinnerAdapter extends ArrayAdapter<Clupp> {
    private List<Clupp> data;


    public ClupSpinnerAdapter(Context context, List<Clupp> data) {
        super(context, 0, data);
        this.data = data;
    }
    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getView(position, convertView, parent);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Clupp clup = data.get(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_clup, parent, false);
            convertView.setTag(ViewHolder.createViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        holder.textClup.setText(clup.getName());
        holder.imgClupFlag.setImageResource(clup.getFlag());
        return convertView;
    }
    @Override
    public int getCount( ) {
        return data.size();
    }

    private static class ViewHolder {
        public ImageView imgClupFlag;
        public TextView textClup;

        public static ViewHolder createViewHolder(View view) {
            ViewHolder holder = new ViewHolder();
            holder.imgClupFlag = (ImageView) view.findViewById(R.id.imgClupFlag);
            holder.textClup = (TextView)view.findViewById(R.id.textClup);
            return holder;
        }
    }
}