package io.github.laomao1997.flushtranslator.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import io.github.laomao1997.flushtranslator.Constant;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    public static void main(String[] args) {
        String str = "public static void ma";
        System.out.println(str + " " + isContainChinese(str));
    }

    /**
     * 判断字符串是否为NULL或空字符串
     *
     * @param str 字符串实例
     * @return true 当字符串为NULL或空字符串, 否则 false
     */
    public static boolean isEmpty(@Nullable String str) {
        return null == str || str.isEmpty();
    }

    /**
     * 判断字符串中是否包含中文, 中文标点不算作中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     */
    public static boolean isContainChinese(String str) {
        final Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        final Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 获取编辑器中选中文本
     *
     * @param editor 编辑器实例
     * @return 当前选中文本，若没有正选中文本则返回空字符串
     */
    @Nonnull
    public static String getCurrentSelectedText(@Nullable Editor editor) {
        if (editor == null) {
            return Constant.EMPTY_STRING;
        }
        // 通过编辑器得到用户选择文本的对象
        final SelectionModel model = editor.getSelectionModel();
        // 获取模型中的文本
        final String selectedText = model.getSelectedText();
        if (TextUtil.isEmpty(selectedText)) {
            return Constant.EMPTY_STRING;
        } else {
            return selectedText;
        }
    }

    private TextUtil() {
    }

    private static TextUtil INSTANCE() {
        return new TextUtil();
    }

    public static TextUtil getInstance() {
        return INSTANCE();
    }
}
