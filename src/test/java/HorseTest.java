import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    @Test
    void nullNameHorseConstuctor() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5.0));
    }

    @Test
    void nullNameHorseConstuctorMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 5.0);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "\t", "\n" })
    void EmptyNameHorseConstuctor(String argument) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 5.0));
    }


    @ParameterizedTest
    @ValueSource(strings = { "", " ", "\t", "\n" })
    void EmptyNameHorseConstuctorMessage(String argument) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(argument, 5.0);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void negariveSpeedHorseConstuctor() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Alex", -1.0));
    }

    @Test
    void negariveSpeedHorseConstuctorMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Alex", -1.0);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void negariveDistanceHorseConstuctor() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Alex", 1.0, -1));
    }

    @Test
    void negariveDistanceHorseConstuctorMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Alex", 1.0, -1);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName(){
        assertEquals("Alex", new Horse("Alex", 1.0).getName());
    }

    @Test
    void getSpeed(){
        assertEquals(5.0, new Horse("Alex", 5.0).getSpeed());
    }

    @Test
    void getDistance(){
        Horse horse1 = new Horse("Alex", 1.0, 10);
        Horse horse2 = new Horse("Alex", 1.0);
        assertAll("Несколько вариантов передаваемой дистанции",
                () -> assertEquals(10, horse1.getDistance()),
                () -> assertEquals(0, horse2.getDistance())
        );
    }


    @Test
    public void moveUsesGetRandomDouble () {
        try (MockedStatic<Horse> mockedStatic =  Mockito.mockStatic(Horse.class)) {
            new Horse("Alex", 1.0, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 3.0, 10.0, 100.0})
    public void  moveUsesFormulatoGetDistance(Double argument){
        try(MockedStatic<Horse> mockedStatic =  Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("Alex", 5.0, 15);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(argument);

            horse.move();

            assertEquals(15 + 5 * argument, horse.getDistance());
        }

    }

}