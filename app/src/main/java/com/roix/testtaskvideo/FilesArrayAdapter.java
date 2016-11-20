package com.roix.testtaskvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by roix on 20.11.2016.
 */

public class FilesArrayAdapter extends RecyclerView.Adapter<FilesArrayAdapter.FilesViewHolder> {
    private List<Item> items;
    private int downloadPosition;
    private Context context;

    public FilesArrayAdapter(Context context){
        this.context=context;
    }
    public void setData(List<Item> items, int downloadPosition){
        this.downloadPosition=downloadPosition;
        this.items=items;
        notifyDataSetChanged();
    }

    @Override
    public FilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        FilesViewHolder holder=new FilesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FilesViewHolder holder, int position) {
        String status="";
        if(position<downloadPosition) status="downloaded";
        else if(position==downloadPosition) status="downloading";
        else status="not downloaded";
        holder.name.setText(items.get(position).getName());
        holder.status.setText(status);
    }

    @Override
    public int getItemCount() {
        if(items==null)return 0;
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class FilesViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView status;


        public FilesViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.name);
            status=(TextView) itemView.findViewById(R.id.status);

        }
    }
}
