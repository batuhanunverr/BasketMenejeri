package com.example.basketmenejeri.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.basketmenejeri.R;

import java.util.ArrayList;

public class ClupListaAdapter extends ArrayAdapter<Clup> implements View.OnClickListener{
    private static final String TAG = "ClupListaAdapter";

    private Context mContext;

    ArrayList<Clup> list_urunler;
    private int mResource;
    private int lastPosition = -1;

    public ClupListaAdapter(Context context, ArrayList<Clup> players) {
        super(context, 0, players);
        this.mContext = context;
        this.list_urunler = players;
    }
    public void onClick(View v) {
        ClupListaAdapter.ViewHolder viewHolder = new ClupListaAdapter.ViewHolder();
    }

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        Button teklif;
        TextView name;
        TextView win;
        TextView lose;
        TextView point;

    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * Default constructor for the PersonListAdapter
     *
     * @param context
     * @param resource
     * @param objects
     */
    public ClupListaAdapter(Context context, int resource, ArrayList<Clup> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        String name = getItem(position).getClupname();

        String win = getItem(position).getClupwin();
        String lose = getItem(position).getCluplose();
        String point = getItem(position).getCluppoint();


        //Create the person object with the information
        Clup person = new Clup(name, win, lose, point);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ClupListaAdapter.ViewHolder holder;


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.leaguetableadaptor, parent, false);
            holder = new ClupListaAdapter.ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.adaptorteamnamee);
            holder.win = (TextView) convertView.findViewById(R.id.adaptorwinn);
            holder.lose = (TextView) convertView.findViewById(R.id.adaptorlosee);
            holder.point = (TextView) convertView.findViewById(R.id.adaptorpointt);



            convertView.setTag(holder);
        } else {
            holder = (ClupListaAdapter.ViewHolder) convertView.getTag();

        }


        holder.name.setText(person.getClupname());

        holder.win.setText(person.getClupwin());
        holder.lose.setText(person.getCluplose());
        holder.point.setText(person.getCluppoint());


        return convertView ;

    }


}
