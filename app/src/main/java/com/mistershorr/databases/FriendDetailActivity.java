package com.mistershorr.databases;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class FriendDetailActivity extends AppCompatActivity {
    private EditText name;
    private EditText moneyOwed;
    private TextView clumsinessString;
    private TextView gymFrequencyString;
    private TextView trustworthinessString;
    private TextView moneyOwedString;
    private TextView clumsinessMin;
    private TextView clumsinessMax;
    private TextView gymFrequencyMin;
    private TextView gymFrequencyMax;
    private SeekBar clumsiness;
    private SeekBar gymFrequency;
    private RatingBar trustworthiness;
    private Switch isAwesome;
    private RadioButton save;
    private Friend friend;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_detail);
        Intent lastIntent = getIntent();
        friend = lastIntent.getParcelableExtra(FriendListActivity.EXTRA_FRIEND);
        makeContact();
        wireWidgets();
        setString();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backendless.Persistence.save( friend, new AsyncCallback<Friend>() {
                    public void handleResponse( Friend friend )
                    {
//                        friend
//                        friend

                        Backendless.Persistence.save( friend, new AsyncCallback<Friend>() {
                            @Override
                            public void handleResponse( Friend response )
                            {
                                // Contact instance has been updated
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                // an error has occurred, the error code can be retrieved with fault.getCode()
                            }
                        } );
                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                    }
                });
            }
        });

    }

    private void makeContact() {
        Friend updateFriend = friend;
    }

    private void setString() {
        name.setText(friend.getName());
        moneyOwed.setText(friend.getMoneyOwed());
        clumsiness.setProgress(friend.getCumsiness());
        gymFrequency.setProgress((int)(friend.getGymFrequency()*2));
        trustworthiness.setRating(friend.getTrustWorthiness());
        if (friend.isAwesome()==true){
            isAwesome.isActivated();
        }
    }

    private void wireWidgets() {
        name = findViewById(R.id.editText_detail_name);
        moneyOwed = findViewById(R.id.editText_detail_moneyOwedAmount);
        clumsinessString = findViewById(R.id.textView_detail_clumsinessText);
        gymFrequencyString = findViewById(R.id.textView_detail_gymFrequencyText);
        trustworthinessString = findViewById(R.id.textView_detail_trustText);
        moneyOwedString = findViewById(R.id.textView_detail_moneyOwed);
        clumsinessMin = findViewById(R.id.textView_detail_clumsinessMin);
        clumsinessMax = findViewById(R.id.textView_detail_clumsinessMax);
        gymFrequencyMax = findViewById(R.id.textView_detail_gymFrequencyMax);
        gymFrequencyMin = findViewById(R.id.textView_detail_gymFrequncyMin);
        clumsiness = findViewById(R.id.seekBar_detail_clumsinessSlider);
        gymFrequency = findViewById(R.id.seekBar_detail_gymFrequencySlider);
        trustworthiness = findViewById(R.id.ratingBar_detail_trustRate);
        isAwesome = findViewById(R.id.switch_detail_isAwesomeSwitch);
        save = findViewById(R.id.radioButton);
    }
}
