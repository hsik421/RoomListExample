package com.example.admin.roomtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<User> users;
    private OnLongClickListener clickListener;
    MainAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setOnLongClickListener(v -> {
            clickListener.onLongClick(position,users.get(position));
            return false;
        });
        holder.id.setText(users.get(position).getId());
        holder.pw.setText(users.get(position).getPw());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    void setUsers(List<User> users){
        this.users = users;
    }
    void insertUsers(List<User> users, int position) {
        this.users = users;
        notifyItemInserted(position);
    }
    void removeUser(List<User> users,int position){
        this.users = users;
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,getItemCount());
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView pw;
        ViewHolder(View itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.item_main_id_text);
            pw = (TextView)itemView.findViewById(R.id.item_main_pw_text);
        }

    }

    void setClickListener(OnLongClickListener clickListener) {
        this.clickListener = clickListener;
    }

    interface OnLongClickListener {
        void onLongClick(int position,User user);
    }
}
