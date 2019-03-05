package com.example.pointer.mypointer.utils;

/*import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sample.multitrack106.R;

import java.util.List;


/**
 * Created by sensetime on 17-6-7.
 */

import android.content.Context;
import android.view.View;

import java.util.List;

public class HandActionAdapter{
    List<HandActionItem> mHandActionList;
    private View.OnClickListener mOnClickHandActionListener;
    private int mSelectedPosition = 0;
    Context mContext;

    public HandActionAdapter(List<HandActionItem> list, Context context) {
        mHandActionList = list;
        mContext = context;
    }

  /*  @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hand_action_item, null);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ObjectViewHolder viewHolder = (ObjectViewHolder) holder;
        viewHolder.imageView.setImageBitmap(mHandActionList.get(position).icon);
//        holder.itemView.setSelected(mSelectedPosition == position);
//        if(mOnClickHandActionListener != null) {
//            holder.itemView.setTag(position);
//            holder.itemView.setOnClickListener(mOnClickHandActionListener);
//
//            holder.itemView.setSelected(mSelectedPosition == position);
//        }
    }

    public void setClickHandActionListener(View.OnClickListener listener) {
        mOnClickHandActionListener = listener;
    }

    @Override
    public int getItemCount() {
        return mHandActionList.size();
    }

    static class ObjectViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView imageView;

        public ObjectViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.icon);
        }
    }

    public void setSelectedPosition(int position){
        mSelectedPosition = position;
    }*/
}
