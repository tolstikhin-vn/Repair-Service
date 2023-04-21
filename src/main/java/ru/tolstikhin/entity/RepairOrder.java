package ru.tolstikhin.entity;

public class RepairOrder {

    private int id;
    private String orderNumber;
    private int serviceCenterId;
    private int deviceId;
    private String descriptionProblem;
    private String clientPhoneNumber;
    private boolean completed;

    public RepairOrder() {
    }

    public RepairOrder(int id, String orderNumber, int serviceCenterId, int deviceId, String descriptionProblem, String clientPhoneNumber, boolean completed) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.serviceCenterId = serviceCenterId;
        this.deviceId = deviceId;
        this.descriptionProblem = descriptionProblem;
        this.clientPhoneNumber = clientPhoneNumber;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(int serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescriptionProblem() {
        return descriptionProblem;
    }

    public void setDescriptionProblem(String descriptionProblem) {
        this.descriptionProblem = descriptionProblem;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
