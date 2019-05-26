package com.example.notes_app.models;

public class NoteDetails {
    private String title, content;

    public NoteDetails(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteDetails() {
    }

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
}
