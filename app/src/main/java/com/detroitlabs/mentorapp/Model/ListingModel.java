package com.detroitlabs.mentorapp.Model;

/**
 * Created by aditishetty on 11/18/14.
 */
public class ListingModel {
    private String title;
    private String author;
    private String selfText;
    private String ListingUrl;
    private boolean isSelfText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSelfText() {
        return selfText;
    }

    public void setSelfText(String selfText) {
        this.selfText = selfText;
    }

    public String getListingUrl() {
        return ListingUrl;
    }

    public void setListingUrl(String listingUrl) {
        ListingUrl = listingUrl;
    }

    public boolean isSelfText() {
        return isSelfText;
    }

    public void setSelfText(boolean isSelfText) {
        this.isSelfText = isSelfText;
    }
}
