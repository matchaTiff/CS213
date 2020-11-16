import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void makeOrder() {
        Order order1 = new Order();
        Sandwich sandwich1 = new Chicken();
        Sandwich sandwich2 = new Beef();
        Sandwich sandwich3 = new Fish();
        order1.add(sandwich1);
        order1.add(sandwich2);
        order1.add(sandwich3);

        ArrayList<OrderLine> orderLine1 = new ArrayList<OrderLine>();
        orderLine1.add( new OrderLine(1, sandwich1, sandwich1.price() ) );
        orderLine1.add( new OrderLine(2, sandwich2, sandwich2.price() ) );
        orderLine1.add( new OrderLine(3, sandwich3, sandwich3.price() ) );

        Order order2 = new Order();
        Sandwich sandwich4 = new Beef();
        Sandwich sandwich5 = new Beef();
        Sandwich sandwich6 = new Chicken();
        Sandwich sandwich7 = new Chicken();
        order2.add(sandwich4);
        order2.add(sandwich5);
        order2.add(sandwich6);
        order2.add(sandwich7);

        ArrayList<OrderLine> orderLine2 = new ArrayList<OrderLine>();
        orderLine2.add( new OrderLine(1, sandwich4, sandwich4.price() ) );
        orderLine2.add( new OrderLine(2, sandwich5, sandwich5.price() ) );
        orderLine2.add( new OrderLine(3, sandwich6, sandwich6.price() ) );
        orderLine2.add( new OrderLine(4, sandwich7, sandwich7.price() ) );

        ArrayList<OrderLine> order1OrderLine = order1.getOrderLines();
        ArrayList<OrderLine> order2OrderLine = order2.getOrderLines();

        System.out.println( orderLine1.equals(order1OrderLine) );

       // assertTrue( Arrays.equals(order1OrderLine, orderLine1) );
        assertEquals( orderLine1, order1OrderLine );
        assertFalse( orderLine1.equals(order2.getOrderLines()) );
        assertFalse( orderLine2.equals(order1.getOrderLines()) );

    }

    @Test
    void testAdd() {
        Order order = new Order();
        Sandwich sandwich1 = new Chicken();
        Sandwich sandwich2 = new Beef();
        Sandwich sandwich3 = new Fish();
        Extra extra1 = new Extra("Onions");
        Extra extra2 = new Extra("Pickles");
        Extra extra3 = new Extra("Relish");
        sandwich1.add(extra1);
        sandwich2.add(extra2);
        sandwich3.add(extra3);

        assertTrue(order.add(sandwich1));
        assertTrue(order.add(sandwich2));
        assertTrue(order.add(sandwich3));

        assertFalse(order.add(extra1));
        assertFalse(order.add(4));
        assertFalse(order.add(new ArrayList<OrderLine>()));
    }

    @Test
    void testRemove() {
        Order order = new Order();
        Sandwich sandwich1 = new Chicken();
        Sandwich sandwich2 = new Beef();
        Sandwich sandwich3 = new Fish();
        Extra extra1 = new Extra("Onions");
        Extra extra2 = new Extra("Pickles");
        Extra extra3 = new Extra("Relish");
        sandwich1.add(extra1);
        sandwich2.add(extra2);
        sandwich3.add(extra3);
        order.add(sandwich1);
        order.add(sandwich2);
        order.add(sandwich3);


        //Removal tests of objects that are and are not on the Order
        assertTrue( order.remove(0) );
        assertTrue( order.remove(1) );

        assertFalse( order.remove(4) );
        assertFalse( order.remove(new Chicken()) );
    }
}