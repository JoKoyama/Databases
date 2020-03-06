package com.mistershorr.databases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class FriendListActivity extends AppCompatActivity {
    private Friend friend;
    public static final String EXTRA_FRIEND = "";
    private List<Friend> friendStringList;
    private FriendAdapter friendAdapter;
    private ListView listView;
    private FloatingActionButton newButton;
    private BackendlessUser user = Backendless.UserService.CurrentUser();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_friend_list);
        listView = findViewById(R.id.listView_list_listOfFriends);
        newButton = findViewById(R.id.floatingActionButton2);
        //search for friends that have ownerIds that match the user's objectId
        String userId = Backendless.UserService.CurrentUser().getObjectId();
        String whereClause = "ownerId = "+"'"+userId+"'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        Backendless.Data.of(Friend.class).find(queryBuilder,new AsyncCallback<List<Friend>>() {
            @Override
            public void handleResponse(List<Friend> foundFriend) {
                Log.d("Loaded Friends","handleResponse" + foundFriend.toString());
                //TODO make a custom adapter to display the friend and load the list
                friendStringList = foundFriend;
                friendAdapter = new FriendAdapter(friendStringList);
                listView.setAdapter(friendAdapter);

                //TODO make Friend parcelable

                //TODO when a friend is clicked, open detail activity
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent targetIntent = new Intent(FriendListActivity.this,FriendDetailActivity.class);
                        targetIntent.putExtra(EXTRA_FRIEND,friendStringList.get(position));
                        startActivity(targetIntent);


                    }
                });
                newButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveNewContact();
                    }
                });
            }


            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(FriendListActivity.this,fault.getDetail(),Toast.LENGTH_LONG).show();


            }
        });
    }

    public class FriendAdapter extends ArrayAdapter<Friend>{
        private List<Friend> friendList;
        public FriendAdapter(List<Friend> list) {
            super(FriendListActivity.this,-1,list);
            this.friendList = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = getLayoutInflater();

            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_friend_detail,parent, false);

            }
            TextView textViewFriendName = convertView.findViewById(R.id.textView_list_friendName);
            TextView textViewMoneyOwed = convertView.findViewById(R.id.textView_list_moneyOwed);
            TextView textViewTrustworthiness = convertView.findViewById(R.id.textView_list_trustworthiness);
            textViewFriendName.setText(friendList.get(position).getName());
            textViewMoneyOwed.setText(String.format("$%,03.2f",friend.getMoneyOwed()));
            textViewTrustworthiness.setText(String.valueOf(friendList.get(position).getTrustWorthiness()-1));
            return convertView;
        }
    }
    public void saveNewContact()
    {
        Friend friend = new Friend();
        friend.setOwnerId(user.getUserId());
        friend.setAwesome(false);
        friend.setTrustWorthiness(1);
        friend.setMoneyOwed(1);
        friend.setGymFrequency(1);
        friend.setClumsiness(1);
        friend.setName("Enter Name Here");
        // save object asynchronously
        Backendless.Persistence.save( friend, new AsyncCallback<Friend>() {
            public void handleResponse( Friend response )
            {
                Toast.makeText(FriendListActivity.this,"this worked",Toast.LENGTH_LONG).show();
                Intent targetIntent = new Intent(FriendListActivity.this,FriendDetailActivity.class);
                targetIntent.putExtra(EXTRA_FRIEND,response);
                startActivity(targetIntent);
            }

            public void handleFault( BackendlessFault fault )
            {
               Toast.makeText(FriendListActivity.this,fault.getDetail(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
