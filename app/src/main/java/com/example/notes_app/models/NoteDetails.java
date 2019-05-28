package com.example.notes_app.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteDetails implements Parcelable {
    private String title, content;

    public NoteDetails(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteDetails() {
    }

    protected NoteDetails(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<NoteDetails> CREATOR = new Creator<NoteDetails>() {
        @Override
        public NoteDetails createFromParcel(Parcel in) {
            return new NoteDetails(in);
        }

        @Override
        public NoteDetails[] newArray(int size) {
            return new NoteDetails[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NoteDetails{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
    }
}
