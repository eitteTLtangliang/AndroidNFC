package com.algorithm.androidnfcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sisensing.nfclibrary.BaseNfcActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.btRead:
                startActivity(new Intent(this, ReadNFCInfoActivity.class));
                break;
            case R.id.btWrite:
                startActivity(new Intent(this, WriteNFCInfoActivity.class));
                break;
        }
    }
}
