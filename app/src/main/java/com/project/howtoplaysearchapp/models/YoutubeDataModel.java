package com.project.howtoplaysearchapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class YoutubeDataModel implements Parcelable {


    private String title = "";
    private String desciption = "";
    private String date = "";
    private String thumbnail = "";
    private String video_id = "";


    public String getVideo_id() { return video_id; }
    public String getTitle() {
        return title;
    }
    public String getDesciption() {
        return desciption;
    }
    public String getDate() {
        return date;
    }
    public String getThumbnail() {
        return thumbnail;
    }

    public void setVideo_id(String video_id) { this.video_id = video_id;}
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(desciption);
        parcel.writeString(date);
        parcel.writeString(thumbnail);
        parcel.writeString(video_id);

    }

    public YoutubeDataModel() {
        super();
    }


    protected YoutubeDataModel(Parcel in) {
        this();
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in){
        this.title = in.readString();
        this.desciption = in.readString();
        this.date = in.readString();
        this.thumbnail = in.readString();
        this.video_id = in.readString();
    }

    public static final Creator<YoutubeDataModel> CREATOR = new Creator<YoutubeDataModel>() {
        @Override
        public YoutubeDataModel createFromParcel(Parcel in) {
            return new YoutubeDataModel(in);
        }

        @Override
        public YoutubeDataModel[] newArray(int size) {
            return new YoutubeDataModel[size];
        }
    };
}
