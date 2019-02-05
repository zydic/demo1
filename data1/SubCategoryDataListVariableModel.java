package com.example.foldergallery.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryDataListVariableModel {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Cat_Id")
    @Expose
    private String catId;
    @SerializedName("Application_Id")
    @Expose
    private String applicationId;
    @SerializedName("App_Version")
    @Expose
    private String appVersion;
    @SerializedName("Theme_Name")
    @Expose
    private String themeName;
    @SerializedName("Theme_Info")
    @Expose
    private String themeInfo;
    @SerializedName("Theme_Bundle")
    @Expose
    private String themeBundle;
    @SerializedName("bundle_size")
    @Expose
    private String bundleSize;
    @SerializedName("Thumnail_Big")
    @Expose
    private String thumnailBig;
    @SerializedName("Thumnail_Small")
    @Expose
    private String thumnailSmall;
    @SerializedName("SoundName")
    @Expose
    private String soundName;
    @SerializedName("SoundFile")
    @Expose
    private String soundFile;
    @SerializedName("sound_size")
    @Expose
    private String soundSize;
    @SerializedName("NoOfImages")
    @Expose
    private String noOfImages;
    @SerializedName("Height")
    @Expose
    private String height;
    @SerializedName("Width")
    @Expose
    private String width;
    @SerializedName("VideoOrCard")
    @Expose
    private String videoOrCard;
    @SerializedName("GameobjectName")
    @Expose
    private String gameobjectName;
    @SerializedName("Is_Preimum")
    @Expose
    private String isPreimum;
    @SerializedName("Theme_Counter")
    @Expose
    private String themeCounter;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("isNewRealise")
    @Expose
    private String isNewRealise;
    @SerializedName("CreatedAt")
    @Expose
    private String createdAt;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("UpdatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("UpdatedBy")
    @Expose
    private Object updatedBy;

    public int getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(int isStatic) {
        this.isStatic = isStatic;
    }

    public int getIsdownloaded() {
        return isdownloaded;
    }

    public void setIsdownloaded(int isdownloaded) {
        this.isdownloaded = isdownloaded;
    }

    private int isStatic;

    private int isdownloaded;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeInfo() {
        return themeInfo;
    }

    public void setThemeInfo(String themeInfo) {
        this.themeInfo = themeInfo;
    }

    public String getThemeBundle() {
        return themeBundle;
    }

    public void setThemeBundle(String themeBundle) {
        this.themeBundle = themeBundle;
    }

    public String getBundleSize() {
        return bundleSize;
    }

    public void setBundleSize(String bundleSize) {
        this.bundleSize = bundleSize;
    }

    public String getThumnailBig() {
        return thumnailBig;
    }

    public void setThumnailBig(String thumnailBig) {
        this.thumnailBig = thumnailBig;
    }

    public String getThumnailSmall() {
        return thumnailSmall;
    }

    public void setThumnailSmall(String thumnailSmall) {
        this.thumnailSmall = thumnailSmall;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }

    public String getSoundFile() {
        return soundFile;
    }

    public void setSoundFile(String soundFile) {
        this.soundFile = soundFile;
    }

    public String getSoundSize() {
        return soundSize;
    }

    public void setSoundSize(String soundSize) {
        this.soundSize = soundSize;
    }

    public String getNoOfImages() {
        return noOfImages;
    }

    public void setNoOfImages(String noOfImages) {
        this.noOfImages = noOfImages;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getVideoOrCard() {
        return videoOrCard;
    }

    public void setVideoOrCard(String videoOrCard) {
        this.videoOrCard = videoOrCard;
    }

    public String getGameobjectName() {
        return gameobjectName;
    }

    public void setGameobjectName(String gameobjectName) {
        this.gameobjectName = gameobjectName;
    }

    public String getIsPreimum() {
        return isPreimum;
    }

    public void setIsPreimum(String isPreimum) {
        this.isPreimum = isPreimum;
    }

    public String getThemeCounter() {
        return themeCounter;
    }

    public void setThemeCounter(String themeCounter) {
        this.themeCounter = themeCounter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsNewRealise() {
        return isNewRealise;
    }

    public void setIsNewRealise(String isNewRealise) {
        this.isNewRealise = isNewRealise;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }


    @Override
    public String toString() {
        return "SubCategoryDataListVariableModel{" +
                "id=" + id +
                ", catId='" + catId + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", themeName='" + themeName + '\'' +
                ", themeInfo='" + themeInfo + '\'' +
                ", themeBundle='" + themeBundle + '\'' +
                ", bundleSize='" + bundleSize + '\'' +
                ", thumnailBig='" + thumnailBig + '\'' +
                ", thumnailSmall='" + thumnailSmall + '\'' +
                ", soundName='" + soundName + '\'' +
                ", soundFile='" + soundFile + '\'' +
                ", soundSize='" + soundSize + '\'' +
                ", noOfImages='" + noOfImages + '\'' +
                ", height='" + height + '\'' +
                ", width='" + width + '\'' +
                ", videoOrCard='" + videoOrCard + '\'' +
                ", gameobjectName='" + gameobjectName + '\'' +
                ", isPreimum='" + isPreimum + '\'' +
                ", themeCounter='" + themeCounter + '\'' +
                ", status='" + status + '\'' +
                ", isNewRealise='" + isNewRealise + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", updatedBy=" + updatedBy +
                ", isStatic=" + isStatic +
                ", isdownloaded=" + isdownloaded +
                '}';
    }
}