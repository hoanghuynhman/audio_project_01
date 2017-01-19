package com.framgia.mixrecorder.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.mixrecorder.R;

public class MixActivity extends AppCompatActivity {
    public static Intent getMixIntent(Context context) {
        Intent intent = new Intent(context, MixActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix);
    }
}
