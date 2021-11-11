package com.ualr.recyclerviewassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.databinding.ActivityListMultiSelectionBinding;
import com.ualr.recyclerviewassignment.model.Inbox;

import java.util.List;
import java.util.Objects;


// TODO 05. Create a new Adapter class and the corresponding ViewHolder class in a different file. The adapter will be used to populate
//  the recyclerView and manage the interaction with the items in the list
// TODO 06. Detect click events on the list items. Implement a new method to toggle items' selection in response to click events
// TODO 07. Detect click events on the thumbnail located on the left of every list row when the corresponding item is selected.
//  Implement a new method to delete the corresponding item in the list
// TODO 08. Create a new method to add a new item on the top of the list. Use the DataGenerator class to create the new item to be added.

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton mFAB;
    private ActivityListMultiSelectionBinding binding;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMultiSelectionBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initComponent();
    }

    private void initComponent() {

        List<Inbox> inbox = DataGenerator.getInboxData(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        this.recyclerView = (RecyclerView) binding.inboxRecycle;

        adapterRecyclerView adapter = new adapterRecyclerView(this, inbox);
        adapter.setOnItemClickListener(new adapterRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Inbox inbox, int pos) {

                view.setActivated(true);
                view.findViewById(R.id.delete_img).setVisibility(View.VISIBLE);
                view.findViewById(R.id.sender_initials).setVisibility(View.GONE);

                for (int i = 0; i < adapter.getItemCount(); ++i) {
                    RecyclerView.ViewHolder otherViewHolder =
                            recyclerView.findViewHolderForLayoutPosition(i);

                    if (i == pos) continue;
                    if (otherViewHolder != null) {
                        otherViewHolder.itemView.setActivated(false);
                        otherViewHolder.itemView.findViewById(R.id.delete_img).setVisibility(View.GONE);
                        otherViewHolder.itemView.findViewById(R.id.sender_initials).setVisibility(View.VISIBLE);
                    }

                }
                FrameLayout initials_button = view.findViewById(R.id.initial_button);
                initials_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.isActivated()) {
                            adapter.removeItem(adapter.getMessages().indexOf(inbox));
                            initials_button.setOnClickListener(null);
                        }
                    }
                });
            }
        });

        this.recyclerView.setAdapter(adapter);
        this.recyclerView.setLayoutManager(layoutManager);

        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(0, DataGenerator.getRandomInboxItem(view.getContext()));
                recyclerView.scrollToPosition(0);
            }
        });
    }


}