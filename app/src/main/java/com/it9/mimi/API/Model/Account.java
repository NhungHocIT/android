package com.it9.mimi.API.Model;

import android.os.Parcelable;
import android.os.Parcel;

public class Account implements Parcelable {
    private int mataikhoan;
    private String sodienthoai;
    private String hovaten;
    private String diachi;
    private String matkhau;
    private int level;

    // Getter và Setter cho mataikhoan
    public int getMataikhoan() {
        return mataikhoan;
    }
    public int getLevel() {
        return level;
    }
    public String getSodienthoai() {
        return sodienthoai;
    }
    public String getHovaten() {
        return hovaten;
    }
    public String getDiachi() {
        return diachi;
    }

    // Parcelable implementation
    protected Account(Parcel in) {
        mataikhoan = in.readInt();
        sodienthoai = in.readString();
        hovaten = in.readString();
        diachi = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mataikhoan);
        dest.writeString(sodienthoai);
        dest.writeString(hovaten);
        dest.writeString(diachi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getMatkhau() {
        return matkhau;
    }
}