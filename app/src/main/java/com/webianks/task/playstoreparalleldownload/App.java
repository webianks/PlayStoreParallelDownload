package com.webianks.task.playstoreparalleldownload;

/**
 * Created by R Ankit on 30-03-2017.
 */

class App {

    private String appName;
    private String developer;
    private String downloadLink;
    private int appLogo;
    private String stars;

    void setAppLogo(int appLogo) {
        this.appLogo = appLogo;
    }

    void setAppName(String appName) {
        this.appName = appName;
    }

    void setStars(String stars) {
        this.stars = stars;
    }

    String getStars() {
        return stars;
    }

    int getAppLogo() {
        return appLogo;
    }

    String getAppName() {
        return appName;
    }

    void setDeveloper(String developer) {
        this.developer = developer;
    }

    String getDeveloper() {
        return developer;
    }

    void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    String getDownloadLink() {
        return downloadLink;
    }
}
