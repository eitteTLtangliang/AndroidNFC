package com.sisensing.nfclibrary.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;

public class NFCUtils {

    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private static NFCUtils instance;

    private NFCUtils(){}

    public static NFCUtils getInstance() {
        if (instance == null) {
            synchronized (NFCUtils.class) {
                if (instance == null) instance = new NFCUtils();
            }
        }
        return instance;
    }

    public void onStart(Activity activity) {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        mPendingIntent = PendingIntent.getActivity(activity, 0, new Intent(activity, activity.getClass()), 0);
    }

    public void onResume(Activity activity) {
        if (mNfcAdapter != null) mNfcAdapter.enableForegroundDispatch(activity, mPendingIntent, null, null);
    }

    public void onPause(Activity activity) {
        if (mNfcAdapter != null) mNfcAdapter.disableForegroundDispatch(activity);
    }
}
