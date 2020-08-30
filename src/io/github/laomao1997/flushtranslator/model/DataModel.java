package io.github.laomao1997.flushtranslator.model;

import io.github.laomao1997.flushtranslator.Constant;
import io.github.laomao1997.flushtranslator.util.TextUtil;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import javax.swing.text.html.HTML;

public class DataModel {
    private String id;
    private String gmtCreate;
    private String gmtModified;
    private boolean isActive;
    private String nameCn;
    private String nameEn;
    private String explanation;
    private String creator;
    private String creatorBranch;

    public DataModel(String nameCn, String nameEn, String explanation) {
        this.nameCn = nameCn;
        this.nameEn = nameEn;
        this.explanation = explanation;
    }

    public DataModel(String id, String gmtCreate, String gmtModified, boolean isActive, String nameCn, String nameEn, String explanation) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.isActive = isActive;
        this.nameCn = nameCn;
        this.nameEn = nameEn;
        this.explanation = explanation;
    }

    public DataModel(String id, String gmtCreate, String gmtModified, boolean isActive, String nameCn, String nameEn, String explanation, String creator, String creatorBranch) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.isActive = isActive;
        this.nameCn = nameCn;
        this.nameEn = nameEn;
        this.explanation = explanation;
        this.creator = creator;
        this.creatorBranch = creatorBranch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorBranch() {
        return creatorBranch;
    }

    public void setCreatorBranch(String creatorBranch) {
        this.creatorBranch = creatorBranch;
    }

    public String toBeautifiedString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constant.HTML_TAG_UL)
                .append(Constant.HTML_TAG_LI).append("中: ").append(nameCn).append(Constant.HTML_TAG_LI_END)
                .append(Constant.HTML_TAG_LI).append("英: ").append(nameEn).append(Constant.HTML_TAG_LI_END)
                .append(Constant.HTML_TAG_LI).append(explanation).append(Constant.HTML_TAG_LI_END);
        if (!TextUtil.isEmpty(creator)) {
            stringBuilder.append(Constant.HTML_TAG_LI).append("词条创建者: ").append(creator).append(Constant.HTML_TAG_LI_END);
        }
        if (!TextUtil.isEmpty(creatorBranch)) {
            stringBuilder.append(Constant.HTML_TAG_LI).append("词条分支: ").append(creatorBranch).append(Constant.HTML_TAG_LI_END);
        }
        stringBuilder.append(Constant.HTML_TAG_UL_END);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "id='" + id + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified='" + gmtModified + '\'' +
                ", isActive=" + isActive +
                ", nameCn='" + nameCn + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }

    @Nullable
    public static DataModel fromJson(@Nonnull JSONObject jsonObject) {
        String id = jsonObject.optString("id");
        String gmtCreate = jsonObject.optString("gmt_create");
        String gmtModified = jsonObject.optString("gmt_modified");
        int activeNum = jsonObject.optInt("is_active");
        boolean isActive = activeNum == 0;
        String nameCn = jsonObject.optString("name_cn");
        String nameEn = jsonObject.optString("name_en");
        String explanation = jsonObject.optString("explanation");
        String creator = jsonObject.optString("creator");
        String creatorBranch = jsonObject.optString("creator_branch");
        if (id == null || gmtCreate == null || gmtModified == null || nameCn == null || nameEn == null || explanation == null) {
            return null;
        }
        if (creator == null || creatorBranch == null) {
            return DataModelBuilder.aDataModel()
                    .withId(id)
                    .withGmtCreate(gmtCreate)
                    .withGmtModified(gmtModified)
                    .withIsActive(isActive)
                    .withNameCn(nameCn)
                    .withNameEn(nameEn)
                    .withExplanation(explanation)
                    .build();
        } else {
            return DataModelBuilder.aDataModel()
                    .withId(id)
                    .withGmtCreate(gmtCreate)
                    .withGmtModified(gmtModified)
                    .withIsActive(isActive)
                    .withNameCn(nameCn)
                    .withNameEn(nameEn)
                    .withExplanation(explanation)
                    .withCreator(creator)
                    .withCreatorBranch(creatorBranch)
                    .build();
        }
    }
}
