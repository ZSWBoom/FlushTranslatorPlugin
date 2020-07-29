package io.github.laomao1997.flushtranslator;

/**
 * 常量类
 */
public class Constant {
    /** 内网环境 */
    private static final String BASE_URL = "http://10.0.29.2:8100/dictionary/dictionary/";
    /** 外网测试环境 */
    private static final String TEST_URL = "http://47.93.58.173:8080/dictionary/dictionary/";
    /** 查询接口地址 */
    public static final String URL_QUERY = BASE_URL + "query";
    /** 新建词条接口地址 */
    public static final String URL_CREATE = BASE_URL + "add";
    /** 接口类型 */
    public static final String MEDIA_TYPE_JSON_UTF8 = "application/json; charset=utf-8";
    /** 请求异常字符串 */
    public static final String REQUEST_EXCEPTION = "请求异常";
    /** 空字符串 */
    public static final String EMPTY_STRING = "";
    /** 新建词条对话框标题 */
    public static final String CREATE_DICTIONARY_TITLE = "新建词条";

    /** 请求成功状态码 */
    public static final String STATUS_SUCCESS = "0";

    public static final String JSON_STATUS = "status";
    public static final String JSON_MESSAGE = "message";
    public static final String JSON_DATA = "data";
    public static final String JSON_DATA_MODELS = "dataModels";
    public static final String JSON_NAME_CN = "name_cn";
    public static final String JSON_NAME_EN = "name_en";
    public static final String JSON_EXPLANATION = "explanation";

    /** 气泡消失的时间 */
    public static final long TIME_BALLOON_FADE_OUT = 10000;
}
