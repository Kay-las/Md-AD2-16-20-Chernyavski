package com.demo.homework4;

import android.os.Parcel;
import android.os.Parcelable;

public class DataContact implements Parcelable {

    private int imageId;
    private String name;
    private String contact;

    public DataContact(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public DataContact(int imageId, String name, String contact) {
        this.imageId = imageId;
        this.name = name;
        this.contact = contact;
    }


    protected DataContact(Parcel in) {
        imageId = in.readInt();
        name = in.readString();
        contact = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(name);
        dest.writeString(contact);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataContact> CREATOR = new Creator<DataContact>() {
        @Override
        public DataContact createFromParcel(Parcel in) {
            return new DataContact(in);
        }

        @Override
        public DataContact[] newArray(int size) {
            return new DataContact[size];
        }
    };

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
