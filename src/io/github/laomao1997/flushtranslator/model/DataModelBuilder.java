package io.github.laomao1997.flushtranslator.model;

public final class DataModelBuilder {
    private String creator;
    private String creatorBranch;
    private String id;
    private String gmtCreate;
    private String gmtModified;
    private boolean isActive;
    private String nameCn;
    private String nameEn;
    private String explanation;

    private DataModelBuilder() {
    }

    public static DataModelBuilder aDataModel() {
        return new DataModelBuilder();
    }

    public DataModelBuilder withCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public DataModelBuilder withCreatorBranch(String creatorBranch) {
        this.creatorBranch = creatorBranch;
        return this;
    }

    public DataModelBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public DataModelBuilder withGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public DataModelBuilder withGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    public DataModelBuilder withIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public DataModelBuilder withNameCn(String nameCn) {
        this.nameCn = nameCn;
        return this;
    }

    public DataModelBuilder withNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public DataModelBuilder withExplanation(String explanation) {
        this.explanation = explanation;
        return this;
    }

    public DataModel build() {
        DataModel dataModel = new DataModel(id, gmtCreate, gmtModified, isActive, nameCn, nameEn, explanation);
        dataModel.setCreator(creator);
        dataModel.setCreatorBranch(creatorBranch);
        return dataModel;
    }
}
