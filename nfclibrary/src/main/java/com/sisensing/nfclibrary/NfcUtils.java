package com.sisensing.nfclibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

public class NfcUtils {

    public static void openNfc(Activity activity) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
        } else {
            activity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }
    }

}
