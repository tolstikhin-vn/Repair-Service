package ru.tolstikhin.entity;
public class CenterMetroLink {
    private int id;
    private int centerId;
    private int metroId;
    private int distance;
    private Metro metro;

    public CenterMetroLink(int id, int centerId, int metroId, int distance) {
        this.id = id;
        this.centerId = centerId;
        this.metroId = metroId;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public int getCenterId() {
        return centerId;
    }

    public int getMetroId() {
        return metroId;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Metro getMetro() {
        return metro;
    }

    public void setMetro(Metro metro) {
        this.metro = metro;
    }
}