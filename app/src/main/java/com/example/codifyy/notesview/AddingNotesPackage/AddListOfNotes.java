package com.example.codifyy.notesview.AddingNotesPackage;

public class AddListOfNotes {
    String Notes;
    String url;
    String title;


    public AddListOfNotes(String notes, String title,String url) {
        this.url = url;
        this.Notes = notes;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AddListOfNotes(String notes) {
        Notes = notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getNotes() {
        return Notes;
    }
}
