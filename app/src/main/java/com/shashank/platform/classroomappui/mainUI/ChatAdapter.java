package com.shashank.platform.classroomappui.mainUI;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shashank.platform.classroomappui.R;
import com.shashank.platform.classroomappui.businessLogic.DiscussionData;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.Viewholder> {

    private Context mContext;
    private ArrayList<DiscussionData> discussionList;

    public ChatAdapter(Context context, ArrayList<DiscussionData> discussionList) {
        this.mContext = context;
        this.discussionList = discussionList;
    }

    @NonNull
    @Override
    public ChatAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_discussion, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.Viewholder holder, int position) {
        // to set data to textviews and imageviews of each card layout
        // change the text color for the primary user
        if(discussionList.get(position).getUserName().equals("Sharan")) {
            holder.nameTxt.setBackgroundTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.lightBlue)));
        }
        holder.nameTxt.setText(discussionList.get(position).getUserName());
        holder.contentTxt.setText(discussionList.get(position).getContent());
        holder.timeStampTxt.setText(discussionList.get(position).getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return discussionList.size();
    }

    /**
     * View holder class for initializing of your views such as textview and image view
     */

    public static class Viewholder extends RecyclerView.ViewHolder {

        private TextView nameTxt;
        private TextView contentTxt;
        private TextView timeStampTxt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.discussionNameTxt);
            contentTxt = itemView.findViewById(R.id.discussionContentTxt);
            timeStampTxt = itemView.findViewById(R.id.discussionTimeTxt);
        }
    }
}
