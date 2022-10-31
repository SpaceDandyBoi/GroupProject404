package com.example.groupproject404.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class path {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int Xcoor;
    private int Ycoor;
    private int Zcoor;
    private int time;

    public path() {
    }

    public path(UUID id, int xcoor, int ycoor, int zcoor, int time) {
        this.id = id;
        Xcoor = xcoor;
        Ycoor = ycoor;
        Zcoor = zcoor;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getXcoor() {
        return Xcoor;
    }

    public void setXcoor(int xcoor) {
        Xcoor = xcoor;
    }

    public int getYcoor() {
        return Ycoor;
    }

    public void setYcoor(int ycoor) {
        Ycoor = ycoor;
    }

    public int getZcoor() {
        return Zcoor;
    }

    public void setZcoor(int zcoor) {
        Zcoor = zcoor;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
