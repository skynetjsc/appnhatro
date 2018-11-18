package com.skynet.thuenha.models;

public class Filter {
    int idService;
    float min,max;
    String listIdUtility;

    public Filter(int idService, float min, float max, String listIdUtility) {
        this.idService = idService;
        this.min = min;
        this.max = max;
        this.listIdUtility = listIdUtility;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public String getListIdUtility() {
        return listIdUtility;
    }

    public void setListIdUtility(String listIdUtility) {
        this.listIdUtility = listIdUtility;
    }
}
