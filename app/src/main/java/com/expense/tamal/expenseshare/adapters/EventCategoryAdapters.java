package com.expense.tamal.expenseshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.expense.tamal.expenseshare.enums.EventCategoryEnum;

import java.util.List;

/**
 * Created by tamal on 13/10/2016.
 */
public class EventCategoryAdapters extends ArrayAdapter<EventCategoryEnum>{

    private List<EventCategoryEnum> eventCategories;
    private LayoutInflater inflater;

    public EventCategoryAdapters(Context context, List<EventCategoryEnum> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.eventCategories = objects;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView tv = (TextView) view.findViewById(android.R.id.text1);
        tv.setText(eventCategories.get(position).categoryName);
        return view;
    }
}
