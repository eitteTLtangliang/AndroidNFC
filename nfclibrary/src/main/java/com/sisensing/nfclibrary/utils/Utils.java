package com.sisensing.nfclibrary.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;

public class Utils {

    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private Context mContext;
    private static Utils instance;

    private Utils(){}

    public static Utils getInstance() {
        if (instance == null) {
            synchronized (Utils.class) {
                if (instance == null) instance = new Utils();
            }
        }
        return instance;
    }

    public void init(Context context) {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
        this.mContext = context;
    }

    public boolean isSupport() {
        checkSelf();
        PackageManager packageManager = this.mContext.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_NFC);
    }

    public boolean isEnable() {
        if (mNfcAdapter != null) {
            return mNfcAdapter.isEnabled();
        }
        return false;
    }

    public void onStart(Activity activity) {
       if (mNfcAdapter != null) {
           mPendingIntent = PendingIntent.getActivity(activity, 0, new Intent(activity, activity.getClass()), 0);
       }
    }

    public void onResume(Activity activity) {
        if (mNfcAdapter != null && mPendingIntent != null) {
            mNfcAdapter.enableForegroundDispatch(activity, mPendingIntent, null, null);
        }
    }

    public void onPause(Activity activity) {
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch(activity);
        }
    }

    private void checkSelf() {
        if (this.mContext == null) {
            throw new RuntimeException("Please initialize and set the context first!");
        }
    }
}
