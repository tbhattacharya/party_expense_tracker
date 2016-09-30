package com.expense.tamal.expenseshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.expense.tamal.expenseshare.R;
import com.expense.tamal.expenseshare.pojo.Event;

import java.util.List;

/**
 * Created by tamal on 29/09/2016.
 */
public class HomeEventListAdapter extends ArrayAdapter<Event>{

    private List<Event> allEvents;
    private LayoutInflater inflater;

    public HomeEventListAdapter(Context context, List<Event> objects) {
        super(context, R.layout.events_row, objects);
        this.allEvents = objects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.events_row,
                    parent, false);
        }

        TextView eventName = (TextView)convertView.findViewById(R.id.event_name);
        eventName.setText(allEvents.get(position).getEventName());
        eventName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
}
