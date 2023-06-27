package org.example;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Order{
    private List<OrderItem> orderItems;
    private Integer NumPeople;

    public Order(Integer numPeople){
        this.orderItems = new ArrayList<>();
        this.NumPeople = numPeople;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getNumPeople() {
        return NumPeople;
    }

    public void setNumPeople(int numPeople) {
        this.NumPeople = numPeople;
    }

    public double getOrderTotal(){
        return orderItems
                .stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }

    public double getTotalPerPerson(){
        return getOrderTotal()/this.NumPeople;
    }

    private Optional<OrderItem> getOrderItemByName(String dishName) {
        return orderItems
                .stream()
                .filter(item -> item.getDishName().equals(dishName))
                .findFirst();
    }

    public void addNewOrderItem(OrderItem orderItem){
        final Optional<OrderItem> existingOrderItem = getOrderItemByName(orderItem.getDishName());
        if (existingOrderItem.isEmpty()) {
            orderItems.add(orderItem);
        } else {
            incrementOrderItem(existingOrderItem.get());
        }
    }

    private void incrementOrderItem(OrderItem orderItem){
        orderItem.setAmount(orderItem.getAmount() + 1);
    }

    public void printOrder(){
        orderItems.sort((o1, o2) -> Double.compare(o2.getTotalPrice(), o1.getTotalPrice()));
        for (OrderItem oi : this.orderItems){
            System.out.println(oi);
        }
        System.out.println("=========================");
        System.out.printf("Total: $%.2f\n", getOrderTotal());
        System.out.printf("Num of people: %d\n", NumPeople);
        System.out.printf("Total per person: $%.2f\n", getTotalPerPerson());
    }
}