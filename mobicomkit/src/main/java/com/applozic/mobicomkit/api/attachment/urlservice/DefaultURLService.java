package com.applozic.mobicomkit.api.attachment.urlservice;

import android.content.Context;

import com.applozic.mobicomkit.api.HttpRequestUtils;
import com.applozic.mobicomkit.api.MobiComKitClientService;
import com.applozic.mobicomkit.api.attachment.FileClientService;
import com.applozic.mobicomkit.api.conversation.Message;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;

public class DefaultURLService implements URLService {

    private MobiComKitClientService mobiComKitClientService;
    private Context context;
    private HttpRequestUtils httpRequestUtils;


    public DefaultURLService(Context context) {

        this.httpRequestUtils = new HttpRequestUtils(context);
        mobiComKitClientService = new MobiComKitClientService(context);
        this.context = context;
    }

    @Override
    public HttpURLConnection getAttachmentConnection(Message message) throws IOException {
        return mobiComKitClientService.openHttpConnection(new MobiComKitClientService(context).getFileUrl() + message.getFileMetas().getBlobKeyString());
    }

    @Override
    public String getThumbnailURL(Message message) throws IOException {
        return message.getFileMetas().getThumbnailUrl();
    }

    @Override
    public String getFileUploadUrl() {
        return httpRequestUtils.getResponse(mobiComKitClientService.getFileBaseUrl() + FileClientService.FILE_UPLOAD_URL
                + "?data=" + new Date().getTime(), "text/plain", "text/plain", true);
    }

    @Override
    public String getImageUrl(Message message) {
        return new MobiComKitClientService(context).getFileUrl() + message.getFileMetas().getBlobKeyString();
    }

}
