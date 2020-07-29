package io.github.laomao1997.flushtranslator.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.Messages;
import io.github.laomao1997.flushtranslator.Constant;
import io.github.laomao1997.flushtranslator.client.CreateClient;
import io.github.laomao1997.flushtranslator.gui.CreateDictionaryDialog;
import io.github.laomao1997.flushtranslator.model.DataModel;
import io.github.laomao1997.flushtranslator.util.TextUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

public class CreateDictionaryAction extends AnAction implements CreateDictionaryDialog.DialogInterface {

    CreateDictionaryDialog mDialog;

    CreateClient mCreateClient;
    CreateClientCallbackImpl mCreateClientCallbackImpl;


    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println("CreateDictionaryAction: actionPerformed()");
        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = TextUtil.getCurrentSelectedText(editor);
        initClient();
        showDialog(selectedText);
    }

    private void initClient() {
        mCreateClient = new CreateClient();
        mCreateClientCallbackImpl = new CreateClientCallbackImpl();
    }

    private void showDialog(String pattern) {
        mDialog = new CreateDictionaryDialog();
        // 设置对话框窗体大小
        mDialog.setPreferredSize(new Dimension(800, 600));
        // 设置对话框标题
        mDialog.setTitle(Constant.CREATE_DICTIONARY_TITLE);
        // 设置选中词句
        mDialog.setPattern(pattern);

        mDialog.setInterface(this);
        mDialog.pack();
        // 设置对话框居于屏幕正中
        mDialog.setLocationRelativeTo(null);

        mDialog.setVisible(true);
    }

    @Override
    public void onOK(DataModel dataModel) {
        if (TextUtil.isEmpty(dataModel.getNameCn())
                || TextUtil.isEmpty(dataModel.getNameEn())
                || TextUtil.isEmpty(dataModel.getExplanation())) {
            Messages.showMessageDialog("请提供完整的中英文词句及解释", "错误", Messages.getWarningIcon());
            return;
        }
        if (!TextUtil.isContainChinese(dataModel.getNameCn()) || TextUtil.isContainChinese(dataModel.getNameEn())) {
            Messages.showMessageDialog("中文词句未包含中文或英文词语内有中文字符", "错误", Messages.getWarningIcon());
            return;
        }
        if (mCreateClient == null || mCreateClientCallbackImpl == null) {
            initClient();
        }
        mCreateClient.request(dataModel, mCreateClientCallbackImpl);
    }

    @Override
    public void onCancel() {
        if (mDialog != null) {
            mDialog.dispose();
        }
    }

    private class CreateClientCallbackImpl implements CreateClient.CreateClientCallback {
        @Override
        public void onSuccess() {
            ApplicationManager.getApplication().invokeLater(() -> {
                Messages.showMessageDialog("添加成功", "成功", Messages.getInformationIcon());
                mDialog.dispose();
            });
        }

        @Override
        public void onFailure(@Nullable String message) {
            ApplicationManager.getApplication().invokeLater(() -> Messages.showMessageDialog(
                    "未能成功添加, " + message,
                    "网络错误",
                    Messages.getErrorIcon()
            ));
        }
    }
}
