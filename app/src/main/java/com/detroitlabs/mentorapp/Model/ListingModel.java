package com.detroitlabs.mentorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aditishetty on 11/18/14.
 */
public class ListingModel implements Parcelable {
    private String title;
    private String author;
    private String selfText;
    private String ListingUrl;
    private boolean isSelfText;

    public ListingModel() {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeString(this.selfText);
        dest.writeString(this.ListingUrl);
        dest.writeByte(isSelfText ? (byte) 1 : (byte) 0);
    }

    private ListingModel(Parcel in) {
        this.title = in.readString();
        this.author = in.readString();
        this.selfText = in.readString();
        this.ListingUrl = in.readString();
        this.isSelfText = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ListingModel> CREATOR = new Parcelable.Creator<ListingModel>() {
        public ListingModel createFromParcel(Parcel source) {
            return new ListingModel(source);
        }

        public ListingModel[] newArray(int size) {
            return new ListingModel[size];
        }
    };
}
