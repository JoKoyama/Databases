package com.mistershorr.databases;


import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_detail_wrap);

        Intent lastIntent = getIntent();
        friend = lastIntent.getParcelableExtra(FriendListActivity.EXTRA_FRIEND);

        wireWidgets();
        setString();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    updateContact();
            }
        });

    }


    private void setString() {
        if(friend != null) {
            name.setText(friend.getName());
            moneyOwed.setText(String.format("$%,03.2f",friend.getMoneyOwed()));
            clumsiness.setProgress(friend.getClumsiness()-1);
            gymFrequency.setProgress((int) ((friend.getGymFrequency()-1) * 2));
            trustworthiness.setRating((float)((friend.getTrustWorthiness()-1)/2.0));
            isAwesome.setChecked(friend.isAwesome());

        } else {
            friend = new Friend();
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
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
    public void updateContact()
    {
        friend.setName(name.getText().toString());
        friend.setClumsiness(clumsiness.getProgress()+1);
        friend.setGymFrequency((double)(gymFrequency.getProgress()/2+1));
        friend.setMoneyOwed(Double.parseDouble(moneyOwed.getText().toString()
                .replace("$","")
                .replace(",","")
                .trim()));
        friend.setTrustWorthiness((int)(trustworthiness.getRating()*2)+1);
        friend.setAwesome(isAwesome.isChecked());

        Backendless.Persistence.save( friend, new AsyncCallback<Friend>() {
            public void handleResponse( Friend savedContact )
            {
               // what you do when it's saved
                Toast.makeText(FriendDetailActivity.this, "" + savedContact.isAwesome(), Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText(FriendDetailActivity.this,fault.getDetail(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
