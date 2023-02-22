/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author FPTshop
 */
public class Todo {

    private int id_TODO;
    private String id_USER;
    private String title;
    private String note;
    private int status_TODO;
    private Date createAt;
    private Date updateAt;

    public Todo() {
    }

    public Todo(int id_TODO, String id_USER, String title, String note, int status_TODO, Date createAt, Date updateAt) {
        this.id_TODO = id_TODO;
        this.id_USER = id_USER;
        this.title = title;
        this.note = note;
        this.status_TODO = status_TODO;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId_TODO() {
        return id_TODO;
    }

    public void setId_TODO(int id_TODO) {
        this.id_TODO = id_TODO;
    }

    public String getId_USER() {
        return id_USER;
    }

    public void setId_USER(String id_USER) {
        this.id_USER = id_USER;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus_TODO() {
        return status_TODO;
    }

    public void setStatus_TODO(int status_TODO) {
        this.status_TODO = status_TODO;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Todo{" + "id_TODO=" + id_TODO + ", id_USER=" + id_USER + ", title=" + title + ", note=" + note + ", status_TODO=" + status_TODO + ", createAt=" + createAt + ", updateAt=" + updateAt + '}';
    }

}
