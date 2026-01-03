package otelrezervasyon.test;

import static org.junit.Assert.*;
import org.junit.Test;

import otelrezervasyon.StandardRoom;

public class StandardRoomTest {

    @Test
    public void testCalculatePrice() {
        StandardRoom room = new StandardRoom(101, 100.0);
        double price = room.calculatePrice(3);

        assertEquals(300.0, price, 0.01);
    }
}
