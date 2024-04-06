package com.example.beyondcurrency.models;

public class SavedTalentModel {
    private int savedTalentId;
    private int userId;
    private int talentId;

    public SavedTalentModel(int savedTalentId, int userId, int talentId) {
        this.savedTalentId = savedTalentId;
        this.userId = userId;
        this.talentId = talentId;
    }

    public SavedTalentModel() {
    }

    @Override
    public String toString() {
        return "SavedTalentModel{" +
                "savedTalentId=" + savedTalentId +
                ", userId=" + userId +
                ", talentId=" + talentId +
                '}';
    }

    public int getSavedTalentId() {
        return savedTalentId;
    }

    public void setSavedTalentId(int savedTalentId) {
        this.savedTalentId = savedTalentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTalentId() {
        return talentId;
    }

    public void setTalentId(int talentId) {
        this.talentId = talentId;
    }
}
