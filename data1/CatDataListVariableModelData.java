package com.example.foldergallery.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatDataListVariableModelData {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Application_Id")
    @Expose
    private String applicationId;
    @SerializedName("Cat_Name")
    @Expose
    private String catName;
    @SerializedName("CreatedAt")
    @Expose
    private String createdAt;


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

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
