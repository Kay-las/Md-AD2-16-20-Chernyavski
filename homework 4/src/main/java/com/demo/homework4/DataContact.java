package com.demo.homework4;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;

public class DataContact implements Parcelable {

    enum InfoType{Mail, Phone}

   private String contactId;
   private String name;
   private String contact;
   private InfoType infoType;


    protected DataContact(Parcel in) {
        contactId = in.readString();
        name = in.readString();
        contact = in.readString();
        infoType =InfoType.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contactId);
        dest.writeString(name);
        dest.writeString(contact);
        dest.writeString(infoType.name());
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

    public String getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public InfoType getInfoType() {
        return infoType;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setContact(String contact) {
        this.contact = contact;
    }

    public DataContact(String name, String contact, InfoType infoType) {
        this.contactId = UUID.randomUUID().toString();
        this.name = name;
        this.contact = contact;
        this.infoType = infoType;
    }
}



