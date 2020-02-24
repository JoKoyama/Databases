package com.mistershorr.databases;


import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
}
