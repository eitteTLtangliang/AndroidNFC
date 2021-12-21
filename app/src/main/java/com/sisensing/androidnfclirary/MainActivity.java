package com.sisensing.androidnfclirary;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sisensing.nfclibrary.BaseNfcActivity;
import com.sisensing.nfclibrary.listener.NFCListener;

public class MainActivity extends BaseNfcActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private TextView tvReader;
    private EditText etWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvReader = findViewById(R.id.tvReader);
        etWrite = findViewById(R.id.etWrite);
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.btRead:

                break;
            case R.id.btWrite:
                writeNFCInfo(etWrite.getText().toString(), new NFCListener() {
                    public void onProcess(boolean state) {
                        Log.e(TAG, "数据写入："+state);
                    }
                });
                break;
        }
    }

    @Override
    protected void readNFCInfo(String info) {
        tvReader.setText(info);
    }
}
