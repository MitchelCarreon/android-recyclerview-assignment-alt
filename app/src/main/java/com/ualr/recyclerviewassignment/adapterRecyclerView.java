package com.ualr.recyclerviewassignment;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;


public class adapterRecyclerView extends RecyclerView.Adapter {
    private List<Inbox> messages;
    private Context context;
//    private OnItemClickListener onItemClickListener;


//    public interface OnItemClickListener {
//        void onItemClick(View view, Inbox message, int pos);
//    }
//
//    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

    public void addItem(int pos, Inbox message) {
        this.messages.add(pos, message);
        notifyItemInserted(pos);
    }

    public class messageViewHolder extends RecyclerView.ViewHolder {
        public TextView name_txtView;
        public TextView email_txtView;
        public TextView message_txtView;
        public TextView date_txtView;
        public TextView sender_initials;

        public View lyt_parent;

        public messageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name_txtView = itemView.findViewById(R.id.sender_name);
            this.email_txtView = itemView.findViewById(R.id.sender_email);
            this.message_txtView = itemView.findViewById(R.id.sender_message);
            this.date_txtView = itemView.findViewById(R.id.message_date);
            this.sender_initials = itemView.findViewById(R.id.sender_initials);

            this.lyt_parent = itemView.findViewById(R.id.lyt_parent);

//            this.lyt_parent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onItemClick(v, messages.get(getLayoutPosition()), getLayoutPosition());
//                }
//            });
        }
    }

    public adapterRecyclerView(Context context, List<Inbox> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.message_row, parent, false);

        RecyclerView.ViewHolder vh = new messageViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        messageViewHolder viewHolder = (messageViewHolder) holder;
        Inbox message = this.messages.get(position);

        viewHolder.message_txtView.setText(message.getMessage());
        viewHolder.date_txtView.setText(message.getDate());
        viewHolder.email_txtView.setText(message.getEmail());
        viewHolder.name_txtView.setText(message.getFrom());

        viewHolder.sender_initials.setText(message.getFrom().substring(0, 1));
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }


}
