package com.example.nestedrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class parent_adapter extends RecyclerView.Adapter<ViewHolder> {

    private List<parent_model> mParentModel;
    Context mContext;

    public parent_adapter(List<parent_model> parentModel,Context context) {
        mParentModel = parentModel;
        mContext=context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mParentModel.get(position).getType()) {
            case 0:
                return parent_model.MEDIA_TYPE;
            case 1:
            return parent_model.TEXT_TYPE;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case parent_model.MEDIA_TYPE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_rv, parent, false);
                return new MediaHolder(v);
            case parent_model.TEXT_TYPE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_rv1, parent, false);
                return new TextHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType()==parent_model.MEDIA_TYPE) {
            MediaHolder vh = (MediaHolder) holder;
            List<child_model> lm = mParentModel.get(position).getChildren();
            vh.setData(lm);
        } else if(holder.getItemViewType()==parent_model.TEXT_TYPE){
            TextHolder vh1 = (TextHolder) holder;
            String text = mParentModel.get(position).getText();
            vh1.setData(text);
        }
    }

    @Override
    public int getItemCount() {
        if(mParentModel==null)
            return 0;
        return mParentModel.size();
    }

    class MediaHolder extends RecyclerView.ViewHolder {
        private RecyclerView child_rv;

        public MediaHolder(@NonNull View itemView) {
            super(itemView);
            child_rv = itemView.findViewById(R.id.rv_child);
        }

        private void setData(List<child_model> childList) {
            LinearLayoutManager childLM = new LinearLayoutManager(child_rv.getContext(), RecyclerView.HORIZONTAL, false);
            childLM.setInitialPrefetchItemCount(4);
            RecyclerView.Adapter adapter = new child_adapter(childList,mContext);
            child_rv.setLayoutManager(childLM);
            child_rv.setAdapter(adapter);
        }
    }

    class TextHolder extends RecyclerView.ViewHolder {
        private TextView tV;

        public TextHolder(@NonNull View itemView) {
            super(itemView);
            tV = itemView.findViewById(R.id.rv_tV);
        }

        private void setData(String text) {
            tV.setText(text);
        }
    }
}
