package com.apigroups.api.requests.projectnotes;

import java.util.List;

public class Edits {
    private String model;
    private String editid;
    private String post;
    private List<Comments> comments;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEditid() {
        return editid;
    }

    public void setEditid(String editid) {
        this.editid = editid;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}