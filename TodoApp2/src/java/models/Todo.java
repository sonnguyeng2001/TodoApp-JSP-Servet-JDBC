/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
/**
 *
 * @author FPTshop
 */
public class Todo {

    SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
    private String id_TODO;
    private String id_USER;
    private String title;
    private String note;
    private int status_TODO;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Todo() {
    }

    public Todo(String id_TODO, String id_USER, String title, String note, int status_TODO, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id_TODO = id_TODO;
        this.id_USER = id_USER;
        this.title = title;
        this.note = note;
        this.status_TODO = status_TODO;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public String getId_TODO() {
        return id_TODO;
    }

    public void setId_TODO(String id_TODO) {
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Todo{" + "sm=" + sm + ", id_TODO=" + id_TODO + ", id_USER=" + id_USER + ", title=" + title + ", note=" + note + ", status_TODO=" + status_TODO + ", createAt=" + createAt + ", updateAt=" + updateAt + '}';
    }

}
