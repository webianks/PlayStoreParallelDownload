package com.webianks.task.playstoreparalleldownload;

/**
 * Created by R Ankit on 30-03-2017.
 */

class App {

    private String appName;
    private String developer;
    private String downloadLink;
    private String appIcon;
    private String stars;
    boolean downloading;

    void setAppIcon(String  appIcon) {
        this.appIcon = appIcon;
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

    String getAppIcon() {
        return appIcon;
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

    public void setDownloading(boolean downloading) {
        this.downloading = downloading;
    }

    public boolean isDownloading() {
        return downloading;
    }
}
