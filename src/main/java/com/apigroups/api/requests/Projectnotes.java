package com.apigroups.api.requests;

import java.util.List;

import com.apigroups.api.requests.projectnotes.Comments;
import com.apigroups.api.requests.projectnotes.Editedcode;

public class Projectnotes {
    private String note;
    private String model;
    private List<Comments> comments;
    private String displaydate;
    private String creator;
    private String noteid;
    private List<Editedcode> editsmade;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public String getDisplaydate() {
        return displaydate;
    }

    public void setDisplaydate(String displaydate) {
        this.displaydate = displaydate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getNoteid() {
        return noteid;
    }

    public void setNoteid(String noteid) {
        this.noteid = noteid;
    }

    public List<Editedcode> getEditsmade() {
        return editsmade;
    }

    public void setEditsmade(List<Editedcode> editsmade) {
        this.editsmade = editsmade;
    }
}