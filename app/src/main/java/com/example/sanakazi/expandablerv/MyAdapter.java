package com.example.sanakazi.expandablerv;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

/**
 * Created by SanaKazi on 9/21/2016.
 */
public class MyAdapter extends ExpandableRecyclerAdapter<MyAdapter.MyParentViewHolder,MyAdapter.MyChildViewHolder> {

    private LayoutInflater mInflator;
    private static final String TAG=MyAdapter.class.getSimpleName();

    public MyAdapter(Context context,@NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public MyParentViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {

        View recipeView = mInflator.inflate(R.layout.parent_view, parentViewGroup, false);
        return new MyParentViewHolder(recipeView);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {

        View ingredientView = mInflator.inflate(R.layout.child_view, childViewGroup, false);
        return new MyChildViewHolder(ingredientView);
    }

    @Override
    public void onBindParentViewHolder(MyParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
      //  DataModel recipe = (DataModel) parentListItem;
        DataModel.ParentModel parentItem =(DataModel.ParentModel)parentListItem;
        Log.w(TAG,parentItem.getFullName());
        parentViewHolder.txt_1.setText(parentItem.getFullName().toString());
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder childViewHolder, int position, Object childListItem) {
        DataModel.ParentModel.ChildModel childItem = (DataModel.ParentModel.ChildModel) childListItem;
        Log.w(TAG,childItem.getFullName().toString());
        childViewHolder.txt_2.setText(childItem.getFullName().toString());

    }

    public static class MyParentViewHolder extends ParentViewHolder {
        private static final float INITIAL_POSITION = 0.0f;
        private static final float ROTATED_POSITION = 180f;
        TextView txt_1;
        ImageView arrow_expand_imageview;
        public MyParentViewHolder(View itemView) {
            super(itemView);
            txt_1 = (TextView)itemView.findViewById(R.id.txt_1);
            arrow_expand_imageview = (ImageView)itemView.findViewById(R.id.arrow_expand_imageview);
        }

        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                if (expanded) {
                    arrow_expand_imageview.setRotation(ROTATED_POSITION);
                } else {
                    arrow_expand_imageview.setRotation(INITIAL_POSITION);
                }
            }
        }
    }
    public static class MyChildViewHolder extends ChildViewHolder{
        TextView txt_2;
        public MyChildViewHolder(View itemView) {
            super(itemView);
            txt_2 = (TextView)itemView.findViewById(R.id.txt_2);
        }
    }

}
