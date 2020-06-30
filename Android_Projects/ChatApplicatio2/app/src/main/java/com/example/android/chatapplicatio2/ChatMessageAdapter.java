package com.example.android.chatapplicatio2;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage>
{

    int Color;
    public ChatMessageAdapter(Activity context, ArrayList<ChatMessage> messages, int color)
    {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, messages);
        Color=color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            Log.d("yahan","hoon yahan");
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        ChatMessage currentChatMessage= getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        nameTextView.setText(currentChatMessage.getName());

        TextView discussionTextView = (TextView) listItemView.findViewById(R.id.discussion);
        discussionTextView.setText(currentChatMessage.getMessage());

        TextView timeStampTextView = (TextView) listItemView.findViewById(R.id.timeStamp);
        timeStampTextView.setText(currentChatMessage.getTimeStamp());

        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.text);
        int bgColor = ContextCompat.getColor(getContext(),Color);
        linearLayout.setBackgroundColor(bgColor);

        return listItemView;
    }
}
