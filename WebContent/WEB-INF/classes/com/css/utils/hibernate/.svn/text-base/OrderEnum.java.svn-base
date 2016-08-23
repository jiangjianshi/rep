package com.css.utils.hibernate;

import org.hibernate.criterion.Order;
 
public enum OrderEnum {
    /**
     * ID倒序
     */
    IdDesc(Order.desc("id")),
    /**
     * ID正序
     */
    IdAsc(Order.asc("id"));
 
    private Order value;
 
    private OrderEnum(Order value) {
        this.value = value;
    }
 
    public Order getValue() {
        return value;
    }
 
}