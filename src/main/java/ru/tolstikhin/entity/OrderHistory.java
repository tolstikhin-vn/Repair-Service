package ru.tolstikhin.entity;

import java.time.LocalDateTime;

public class OrderHistory {

    private int id;
    private int repairOrderId;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private RepairStatus repairStatus;

    public OrderHistory() {
    }

    public OrderHistory(int id, int repairOrderId, LocalDateTime startDatetime, LocalDateTime endDatetime, RepairStatus repairStatus) {
        this.id = id;
        this.repairOrderId = repairOrderId;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.repairStatus = repairStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepairOrderId() {
        return repairOrderId;
    }

    public void setRepairOrderId(int repairOrderId) {
        this.repairOrderId = repairOrderId;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public LocalDateTime getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(LocalDateTime endDatetime) {
        this.endDatetime = endDatetime;
    }

    public RepairStatus getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(RepairStatus repairStatus) {
        this.repairStatus = repairStatus;
    }
}
