/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.phone.vvm.omtp.protocol;

import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.phone.vvm.omtp.OmtpVvmCarrierConfigHelper;
import com.android.phone.vvm.omtp.sms.OmtpMessageSender;

public class ProtocolHelper {

    private static final String TAG = "ProtocolHelper";

    public static OmtpMessageSender getMessageSender(VisualVoicemailProtocol protocol,
            OmtpVvmCarrierConfigHelper config) {

        int applicationPort = config.getApplicationPort();
        String destinationNumber = config.getDestinationNumber();
        if (TextUtils.isEmpty(destinationNumber)) {
            Log.w(TAG, "No destination number for this carrier.");
            return null;
        }

        SmsManager smsManager = SmsManager.getSmsManagerForSubscriptionId(config.getSubId());
        return protocol.createMessageSender(smsManager, (short) applicationPort, destinationNumber);
    }
}
