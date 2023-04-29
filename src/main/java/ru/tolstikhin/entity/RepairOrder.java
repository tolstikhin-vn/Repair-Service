package ru.tolstikhin.entity;

import java.time.LocalDateTime;

public class RepairOrder {

    private int id;
    private String orderNumber;
    private int serviceCenterId;
    private String deviceType;

    private String deviceName;
    private String descriptionProblem;
    private String clientPhoneNumber;
    private boolean completed;

    private int calculationId;

    private int warranty;

    private boolean rated;

    private LocalDateTime startDatetime;

    public RepairOrder() {
    }

    public RepairOrder(int id, String orderNumber, int serviceCenterId, int deviceId, String descriptionProblem, String clientPhoneNumber, boolean completed, int calculationId, int warranty, boolean rated) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.serviceCenterId = serviceCenterId;
        this.descriptionProblem = descriptionProblem;
        this.clientPhoneNumber = clientPhoneNumber;
        this.completed = completed;
        this.calculationId = calculationId;
        this.warranty = warranty;
        this.rated = rated;
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

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public int getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(int calculationId) {
        this.calculationId = calculationId;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
