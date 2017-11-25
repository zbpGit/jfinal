package com.wx.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Administrator on 2017/8/7.
 */
public class Pay extends Model<Pay>{

    private String merNo;

    private String subAppId;

    private String appsecret;

    private String key;

    private String url;

    private String notifyUrl;

    private String payUrl;

    private String times;

    private String present;

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    public String getSubAppId() {
        return subAppId;
    }

    public void setSubAppId(String subAppId) {
        this.subAppId = subAppId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public Pay() {
    }

    public Pay(String merNo, String subAppId, String appsecret, String key, String url, String notifyUrl, String payUrl, String times, String present) {
        this.merNo = merNo;
        this.subAppId = subAppId;
        this.appsecret = appsecret;
        this.key = key;
        this.url = url;
        this.notifyUrl = notifyUrl;
        this.payUrl = payUrl;
        this.times = times;
        this.present = present;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "merNo='" + merNo + '\'' +
                ", subAppId='" + subAppId + '\'' +
                ", appsecret='" + appsecret + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", payUrl='" + payUrl + '\'' +
                ", times='" + times + '\'' +
                ", present=" + present +
                '}';
    }
}
