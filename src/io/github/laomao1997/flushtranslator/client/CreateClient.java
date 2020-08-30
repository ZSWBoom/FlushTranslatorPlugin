package io.github.laomao1997.flushtranslator.client;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import io.github.laomao1997.flushtranslator.Constant;
import io.github.laomao1997.flushtranslator.model.DataModel;
import io.github.laomao1997.flushtranslator.util.TextUtil;
import okhttp3.Call;
import okhttp3.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public class CreateClient {

    private CreateClientCallback mCreateClientCallback;

    public void request(DataModel dataModel, @Nonnull CreateClientCallback callback) {
        mCreateClientCallback = callback;
        String requestJson;
        try {
            requestJson = buildRequestJson(dataModel).toString();
        } catch (JSONException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return;
        }
        OkHttpUtils.postString()
                .url(Constant.URL_CREATE)
                .content(requestJson)
                .mediaType(MediaType.parse(Constant.MEDIA_TYPE_JSON_UTF8))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        callback.onFailure(Constant.REQUEST_EXCEPTION);
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        handleReceive(s);
                    }
                });
    }

    /**
     * 移除监听
     */
    public void removeCallback() {
        if (mCreateClientCallback != null) {
            mCreateClientCallback = null;
        }
    }

    private JSONObject buildRequestJson(DataModel dataModel) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constant.JSON_NAME_CN, dataModel.getNameCn());
        jsonObject.put(Constant.JSON_NAME_EN, dataModel.getNameEn());
        jsonObject.put(Constant.JSON_EXPLANATION, dataModel.getExplanation());
        String creator = dataModel.getCreator();
        String creatorBranch = dataModel.getCreatorBranch();
        if (!TextUtil.isEmpty(creator)) {
            jsonObject.put(Constant.JSON_CREATOR, creator);
        }
        if (!TextUtil.isEmpty(creatorBranch)) {
            jsonObject.put(Constant.JSON_CREATOR_BRANCH, creatorBranch);
        }
        return jsonObject;
    }

    private void handleReceive(String responseStr) {
        System.out.println("Result: " + responseStr);
        if (mCreateClientCallback == null) {
            return;
        }
        final JSONObject resultJson;
        try {
            resultJson = new JSONObject(responseStr);
        } catch (JSONException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            mCreateClientCallback.onFailure(Constant.EMPTY_STRING);
            return;
        }
        final String statusString = resultJson.optString(Constant.JSON_STATUS);
        final String messageString = resultJson.optString(Constant.JSON_MESSAGE);
        if (statusString == null || !statusString.equals(Constant.STATUS_SUCCESS)) {
            mCreateClientCallback.onFailure(messageString);
        } else {
            mCreateClientCallback.onSuccess();
        }
    }

    public interface CreateClientCallback {
        /**
         * 成功回调
         */
        void onSuccess();

        /**
         * 失败回调
         *
         * @param message 失败信息
         */
        void onFailure(@Nullable String message);
    }
}
