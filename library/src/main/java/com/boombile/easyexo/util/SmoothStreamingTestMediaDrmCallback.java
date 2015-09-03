package com.boombile.easyexo.util;

import android.annotation.TargetApi;
import android.media.MediaDrm;
import android.os.Build;
import android.text.TextUtils;

import com.google.android.exoplayer.drm.MediaDrmCallback;
import com.google.android.exoplayer.util.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by maohieng on 9/1/15.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class SmoothStreamingTestMediaDrmCallback implements MediaDrmCallback {

    private static final String PLAYREADY_TEST_DEFAULT_URI =
            "http://playready.directtaps.net/pr/svc/rightsmanager.asmx";
    private static final Map<String, String> KEY_REQUEST_PROPERTIES;

    static {
        HashMap<String, String> keyRequestProperties = new HashMap<>();
        keyRequestProperties.put("Content-Type", "text/xml");
        keyRequestProperties.put("SOAPAction",
                "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        KEY_REQUEST_PROPERTIES = keyRequestProperties;
    }

    @Override
    public byte[] executeProvisionRequest(UUID uuid, MediaDrm.ProvisionRequest request) throws IOException {
        String url = request.getDefaultUrl() + "&signedRequest=" + new String(request.getData());
        return Util.executePost(url, null, null);
    }

    @Override
    public byte[] executeKeyRequest(UUID uuid, MediaDrm.KeyRequest request) throws Exception {
        String url = request.getDefaultUrl();
        if (TextUtils.isEmpty(url)) {
            url = PLAYREADY_TEST_DEFAULT_URI;
        }
        return Util.executePost(url, request.getData(), KEY_REQUEST_PROPERTIES);
    }
}
