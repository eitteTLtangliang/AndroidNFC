package com.sisensing.androidnfclirary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sisensing.nfclibrary.BaseNfcActivity;
import com.sisensing.nfclibrary.NfcUtils;
import com.sisensing.nfclibrary.listener.NFCListener;

public class WriteNFCInfoActivity extends BaseNfcActivity {

    private EditText etWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_nfcinfo);
        etWrite = findViewById(R.id.etWrite);
    }

    @Override
    protected void readNFCInfo(String info) {

    }

    @Override
    protected void nfcNoSupport() {
        Toast.makeText(this, "该设备不支持NFC", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void openNfc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage("请开启NFC");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NfcUtils.openNfc(WriteNFCInfoActivity.this);
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
            case R.id.btWrite:
                writeNFCInfo(etWrite.getText().toString(), new NFCListener() {
                    public void onProcess(boolean state) {
                        Toast.makeText(WriteNFCInfoActivity.this, state?"写入成功":"写入失败", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                break;
        }
    }
}
