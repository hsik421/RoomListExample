package com.example.admin.roomtest;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<User> users;
    private AppDababase dababase;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDb(this);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDialog dialog = new AddDialog(MainActivity.this);
                dialog.setClickListener(new AddDialog.DialogBtnClickListener() {
                    @Override
                    public void positiveClick(User user) {
                        dialog.dismiss();
                        new AsyncTask<Void,Void,Void>(){
                            @Override
                            protected Void doInBackground(Void... params) {
                                dababase.userDao().insertUser(user);
                                users = dababase.userDao().getAll();
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                adapter.insertUsers(users,users.size());
                            }
                        }.execute();
                    }
                    @Override
                    public void negativeClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
        adapter.setClickListener(new MainAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(final int position, final User user) {
                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected Void doInBackground(Void... params) {
                        dababase.userDao().delete(user);
                        users.remove(position);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        adapter.removeUser(users,position);
                    }
                }.execute();

            }
        });

    }
    private void initializeDb(final Context context){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
//                getApplicationContext().deleteDatabase("dabasename");
                dababase = Room.databaseBuilder(context.getApplicationContext(),AppDababase.class,"databasename").build();
                users = dababase.userDao().getAll();
                adapter.setUsers(users);

                return null;
            }
        }.execute();

    }

}
