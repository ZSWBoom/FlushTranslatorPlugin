package io.github.laomao1997.flushtranslator.client;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import io.github.laomao1997.flushtranslator.Constant;
import io.github.laomao1997.flushtranslator.model.DataModel;
import okhttp3.Call;
import okhttp3.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryClient {

    private QueryCallback mQueryCallback;

    public void request(String pattern, @Nonnull QueryCallback callback) {
        System.out.println("选中文本：" + pattern + " 开始查询...");
        // 查询
        if (pattern.isEmpty()) {
            return;
        }
        mQueryCallback = callback;
        String requestJson;
        try {
            requestJson = buildRequestJson(pattern).toString();
        } catch (JSONException e1) {
            System.out.println(Arrays.toString(e1.getStackTrace()));
            return;
        }
        OkHttpUtils.postString()
                .url(Constant.URL_QUERY)
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
        if (mQueryCallback != null) {
            mQueryCallback = null;
        }
    }

    private JSONObject buildRequestJson(String pattern) throws JSONException {
        return new JSONObject().put("pattern", pattern);
    }

    /**
     * 解析查询返回的结果
     *
     * @param responseStr 查询返回的结果
     */
    private void handleReceive(String responseStr) {
        System.out.println("Result: " + responseStr);
        final JSONObject resultJson;
        try {
            resultJson = new JSONObject(responseStr);
        } catch (JSONException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            mQueryCallback.onFailure(Constant.EMPTY_STRING);
            return;
        }
        final String statusString = resultJson.optString(Constant.JSON_STATUS);
        final String messageString = resultJson.optString(Constant.JSON_MESSAGE);
        if (statusString == null || !statusString.equals(Constant.STATUS_SUCCESS)) {
            mQueryCallback.onFailure(messageString);
            return;
        }
        final JSONObject dataString = resultJson.optJSONObject(Constant.JSON_DATA);
        if (dataString == null) {
            mQueryCallback.onFailure(Constant.EMPTY_STRING);
            return;
        }
        List<DataModel> dataModelList = new ArrayList<>();
        final JSONArray dataModels = dataString.optJSONArray(Constant.JSON_DATA_MODELS);
        for (int i = 0; i < dataModels.length(); i++) {
            JSONObject jsonObject = dataModels.optJSONObject(i);
            if (jsonObject != null) {
                DataModel dataModel = DataModel.fromJson(jsonObject);
                if (dataModel != null) {
                    dataModelList.add(dataModel);
                }
            }
        }
        if (dataModelList.isEmpty()) {
            mQueryCallback.onFailure(Constant.EMPTY_STRING);
        } else {
            mQueryCallback.onSuccess(dataModelList);
        }
    }

    /**
     * 查询请求回调
     */
    public interface QueryCallback {
        /**
         * 成功回调
         *
         * @param dataModelList 查询结果列表
         */
        void onSuccess(@Nonnull List<DataModel> dataModelList);

        /**
         * 失败回调
         *
         * @param message 失败信息
         */
        void onFailure(@Nullable String message);
    }
}
