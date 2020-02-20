package com.mistershorr.databases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.List;

public class FriendListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        Backendless.Data.of(Friend.class).find(new AsyncCallback<List<Friend>>() {
            @Override
            public void handleResponse(List<Friend> foundFriend) {
                Log.d("Loaded Friends","handleResponse" + foundFriend.toString());
                //TODO make a custom adapter to display the friend and load the list

                //TODO make Friend parcelable

                //TODO when a friend is clicked, open detail activity

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(FriendListActivity.this,fault.getDetail(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
