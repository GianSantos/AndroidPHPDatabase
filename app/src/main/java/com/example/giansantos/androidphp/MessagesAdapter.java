package com.example.giansantos.androidphp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MessagesAdapter extends ArrayAdapter<Messages> {
    public MessagesAdapter(Context context, ArrayList<Messages> messages){
        super(context, 0, messages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemview = convertView;
        if(listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Messages currentMessage = getItem(position);
        TextView sender = (TextView) listitemview.findViewById(R.id.sender);
        sender.setText(currentMessage.getMusername());

        TextView body = (TextView) listitemview.findViewById(R.id.body);
        body.setText(currentMessage.getMbody());

        TextView date = (TextView) listitemview.findViewById(R.id.date);
        date.setText(currentMessage.getMdate());

        return listitemview;
    }
}