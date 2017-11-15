package com.example.batch.repository.domain;

public class DatabaseInfo {
    private Long seq;
    private String description;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DatabaseInfo{" +
                "seq=" + seq +
                ", description='" + description + '\'' +
                '}';
    }
}
