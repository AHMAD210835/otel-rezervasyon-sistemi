package otelrezervasyon.test;

import static org.junit.Assert.*;
import org.junit.Test;

import otelrezervasyon.DeluxeRoom;

public class DeluxeRoomTest {

    @Test
    public void testCalculatePrice() {
        DeluxeRoom room = new DeluxeRoom(201, 200.0);
        double price = room.calculatePrice(2);

        assertEquals(600.0, price, 0.01);
    }
}
