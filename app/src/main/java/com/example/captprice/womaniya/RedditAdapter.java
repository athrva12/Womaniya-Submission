package com.example.captprice.womaniya;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.captprice.womaniya.data.Child;
import com.example.captprice.womaniya.data.Data_;
import com.example.captprice.womaniya.data.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedditAdapter extends RecyclerView.Adapter<RedditAdapter.ViewHolder>{
    private List<Child> childrenList=new ArrayList<>();

    public RedditAdapter(List<ClipData.Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    private List<ClipData.Item> items;
    Context context=null;

    public RedditAdapter(List<Child> childrenList) {

        this.childrenList = childrenList;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View itemView= LayoutInflater.from(context).inflate(R.layout.post_single_item,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Data_ data_ = childrenList.get(position).getData();

        final Image image= childrenList.get(position).getImage();


        //Pattern p=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");


        Glide.with(context).load(data_.getUrl()).into(holder.imageView);
        holder.titleTV.setText(data_.getTitle());

    }

    @Override
    public int getItemCount() {

        return childrenList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTV=itemView.findViewById(R.id.postname);
            imageView=itemView.findViewById(R.id.post_item_image);
            cardView=itemView.findViewById(R.id.card_view_gallery_image);
        }
    }
}
