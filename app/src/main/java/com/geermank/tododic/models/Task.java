package com.geermank.tododic.models;

public class Task {

    private long id;
    private String title;
    private String assignedTo;
    private boolean finished;

    public Task(String title, String assignedTo, boolean finished) {
        this.title = title;
        this.assignedTo = assignedTo;
        this.finished = finished;
    }

    public Task(long id, String title, String assignedTo, boolean finished) {
        this.id = id;
        this.title = title;
        this.assignedTo = assignedTo;
        this.finished = finished;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
