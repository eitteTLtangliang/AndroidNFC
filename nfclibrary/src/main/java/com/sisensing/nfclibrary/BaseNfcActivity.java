package com.sisensing.nfclibrary;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sisensing.nfclibrary.listener.NFCListener;
import com.sisensing.nfclibrary.utils.ReadDataUtils;
import com.sisensing.nfclibrary.utils.Utils;
import com.sisensing.nfclibrary.utils.WriteDataUtils;

/**
 * Author:Created by Focus on 2017/8/25.
 * Email:tl594336505@163.com
 * Description:
 * 子类在onNewIntent方法中进行NFC标签相关操作。
 * launchMode设置为singleTop或singleTask，保证Activity的重用唯一
 * 在onNewIntent方法中执行intent传递过来的Tag数据
 * 将NFC标签卡靠近手机后部（NFC标签卡可网上自行购买）
 */
public abstract class BaseNfcActivity extends AppCompatActivity {

    private String nfcText;
    private NFCListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.getInstance().init(this);
        if (Utils.getInstance().isSupport()) {
            if (!Utils.getInstance().isEnable()) {
                openNfc();
            }
        } else {
            nfcNoSupport();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utils.getInstance().onStart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.getInstance().onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.getInstance().onPause(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String nfcTag = ReadDataUtils.readNfcTag(intent);
        readNFCInfo(nfcTag);
        if (listener != null) {
            listener.onProcess(WriteDataUtils.dealNFCInfo(intent, this.nfcText));
        }
    }

    protected abstract void readNFCInfo(String info);

    protected abstract void nfcNoSupport();

    protected void openNfc() {}

    protected void writeNFCInfo(String nfcText, NFCListener listener) {
        this.nfcText = nfcText;
        this.listener = listener;
    }

}

