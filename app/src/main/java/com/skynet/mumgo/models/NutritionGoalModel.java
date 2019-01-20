package com.skynet.mumgo.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NutritionGoalModel {
    @SerializedName("target")
    Target target;
    @SerializedName("food")
    List<Food> food;
    @SerializedName("food_favourite")
    List<Food> food_favourite;
    @SerializedName("target_week")
    List<TargetWeek> target_week;
    @SerializedName("level_action")
    List<ActionLevel> level_action;

    public List<TargetWeek> getTarget_week() {
        return target_week;
    }

    public void setTarget_week(List<TargetWeek> target_week) {
        this.target_week = target_week;
    }

    public List<ActionLevel> getLevel_action() {
        return level_action;
    }

    public void setLevel_action(List<ActionLevel> level_action) {
        this.level_action = level_action;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public List<Food> getFood_favourite() {
        return food_favourite;
    }

    public void setFood_favourite(List<Food> food_favourite) {
        this.food_favourite = food_favourite;
    }
}
