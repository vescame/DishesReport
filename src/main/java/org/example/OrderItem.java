package org.example;

public class OrderItem {

    private String dishName;
    private Integer amount;
    private Double price;

    public OrderItem(){}

    public OrderItem(String dishName, Double price) {
        this.dishName = dishName;
        this.price = price;
        this.amount = 1;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return price * amount;
    }

    public String toString()
    {
        return String.format("%-10s | %2dx | %.2f", dishName, amount, getTotalPrice());
    }
}