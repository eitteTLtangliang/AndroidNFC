package com.algorithm.androidnfcdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sisensing.nfclibrary.BaseNfcActivity;
import com.sisensing.nfclibrary.NfcUtils;

public class ReadNFCInfoActivity extends BaseNfcActivity {

    private TextView tvReader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_nfcinfo);
        tvReader = findViewById(R.id.tvReader);
    }

    @Override
    public void readNFCInfo(String info) {
        tvReader.setText(info);
    }

    @Override
    public void nfcNoSupport() {
        Toast.makeText(this, "该设备不支持NFC", Toast.LENGTH_LONG).show();
    }

    @Override
    public void openNfc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("请开启NFC");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NfcUtils.openNfc(ReadNFCInfoActivity.this);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                finish();
                break;
        }
    }
}
