package com.example.blockit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String savedNumber = "";
        //We listen to two intents.  The new outgoing call only tells us of an outgoing call.  We use it to get the number.
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        } else {
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int state = 0;
            if (stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }
            if (number != null && !number.isEmpty() && !number.equals("null")) {
                /*onCallStateChanged(context, state, number);*/
                showToast(context, "NUMBER =>" + number);
                return;
            }

        }

//        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);


    }

    void onCallStateChanged(Context context, String state, String number) {
        if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
            showToast(context, "Call started...");
        } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            showToast(context, "Call ended...");
        } else if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            showToast(context, "Incoming call from: " + number);
        }
    }

    void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
