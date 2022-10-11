package org.example.models;

public class Reimbursement{
    private int id;
    private double amount;
    private String description;

    private int authorId;
    private String status;

    public Reimbursement(){

    }

    public Reimbursement(int id, double amount, String description, String status) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Reimbursement(double amount, String description, String status) {
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
