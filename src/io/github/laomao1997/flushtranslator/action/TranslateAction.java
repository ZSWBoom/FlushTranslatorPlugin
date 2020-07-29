package io.github.laomao1997.flushtranslator.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import io.github.laomao1997.flushtranslator.Constant;
import io.github.laomao1997.flushtranslator.client.QueryClient;
import io.github.laomao1997.flushtranslator.model.DataModel;
import io.github.laomao1997.flushtranslator.util.TextUtil;
import okhttp3.Call;
import okhttp3.MediaType;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TranslateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println("TranslateAction: actionPerformed()");
        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = TextUtil.getCurrentSelectedText(editor);
        QueryClient queryClient = new QueryClient();
        queryClient.request(selectedText, new QueryClient.QueryCallback() {
            @Override
            public void onSuccess(@Nonnull List<DataModel> dataModelList) {
                final String htmlStr = buildHtmlString(selectedText, dataModelList);
                showPopup(editor, htmlStr);
                queryClient.removeCallback();
            }

            @Override
            public void onFailure(@Nullable String message) {
                showPopup(editor, Constant.REQUEST_EXCEPTION);
                queryClient.removeCallback();
            }
        });
    }

    @Nonnull
    private String buildHtmlString(@Nonnull String pattern, @Nonnull List<DataModel> dataModelList) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("<h1>").append(pattern).append("</h1>").append("\n");
        resultBuilder.append("<ul>");
        for (DataModel dataModel: dataModelList) {
            System.out.println(dataModel.toString());
            resultBuilder.append(dataModel.toBeautifiedString());
        }
        resultBuilder.append("</ul>");
        return resultBuilder.toString();
    }

    /**
     * 展示结果
     *
     * @param editor 编辑框实例
     * @param selectedText 选中的文本
     */
    private void showPopup(Editor editor, String selectedText) {
        ApplicationManager.getApplication().invokeLater(() -> {
            JBPopupFactory factory = JBPopupFactory.getInstance();
            BalloonBuilder builder = factory.createHtmlTextBalloonBuilder(selectedText, null,
                    new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null);

            builder.setFadeoutTime(Constant.TIME_BALLOON_FADE_OUT)
                    .createBalloon()
                    .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
        });
    }
}
