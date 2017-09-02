package com.example.swapnilgupta.sharechat.homescreen;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swapnilgupta.sharechat.R;
import com.example.swapnilgupta.sharechat.models.FeedItem;

import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_POSITION;
import static com.example.swapnilgupta.sharechat.models.FeedItem.TYPE_IMAGE;
import static com.example.swapnilgupta.sharechat.models.FeedItem.TYPE_IMAGE_INT;
import static com.example.swapnilgupta.sharechat.models.FeedItem.TYPE_LOAD_MORE;
import static com.example.swapnilgupta.sharechat.models.FeedItem.TYPE_LOAD_MORE_INT;
import static com.example.swapnilgupta.sharechat.models.FeedItem.TYPE_PROFILE;
import static com.example.swapnilgupta.sharechat.models.FeedItem.TYPE_PROFILE_INT;

/**
 * Created by swapnilgupta on 01/09/2017.
 */

public class HomeScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "HomeScreenAdapter";

    private List<FeedItem> items;
    private HomeScreenActivity.FeedsItemListener itemListener;


    public HomeScreenAdapter(List<FeedItem> items,
                             HomeScreenActivity.FeedsItemListener itemListener) {
        this.items = items;
        this.itemListener = itemListener;
    }

    public void setItems(List<FeedItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    private class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView tempText;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            tempText = (TextView) itemView.findViewById(R.id.tvTemp);
        }
    }

    private class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView tempText;

        public ImageViewHolder(View itemView) {
            super(itemView);
            tempText = (TextView) itemView.findViewById(R.id.tvTemp);
        }
    }

    private class LoadMoreViewHolder extends RecyclerView.ViewHolder {

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case TYPE_PROFILE_INT:
                return new ProfileViewHolder(inflater.inflate(R.layout.card_view_type_profile,
                        parent, false));
            case TYPE_IMAGE_INT:
                return new ImageViewHolder(inflater.inflate(R.layout.card_view_type_image,
                        parent, false));
            case TYPE_LOAD_MORE_INT:
                return new LoadMoreViewHolder(inflater.inflate(R.layout.card_view_load_more,
                        parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = holder.getAdapterPosition();
                if(pos == NO_POSITION) {
                    return;
                }
                if(itemListener != null) {
                    itemListener.onFeedClicked(items.get(pos));
                }
            }
        });

        switch (items.get(position).getType()) {
            case TYPE_PROFILE:
                bindProfile((ProfileViewHolder) holder, position);
                break;
            case TYPE_IMAGE:
                bindImage((ImageViewHolder) holder, position);
                break;
        }
    }

    private void bindProfile(ProfileViewHolder holder, int position) {
        holder.tempText.setText(items.get(position).toString());
    }

    private void bindImage(ImageViewHolder holder, int position) {
        holder.tempText.setText(items.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (items.get(position).getType()) {
            case TYPE_IMAGE:
                return TYPE_IMAGE_INT;
            case TYPE_PROFILE:
                return TYPE_PROFILE_INT;
            case TYPE_LOAD_MORE:
                return TYPE_LOAD_MORE_INT;
        }

        return TYPE_IMAGE_INT;
    }
}
