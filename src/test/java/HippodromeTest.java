import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
//import org.mockito.Mock;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void nullHippodromeConstuctor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void nullHippodromeConstuctorMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void emptyHippodromeConstuctor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void emptyHippodromeConstuctorMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesList(){
        List<Horse> horses = new ArrayList<Horse>(30);
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horse" + i, 1.0, 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void isReallyAllHorsesMoving(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses){
            verify(horse).move();
        }
    }

    @Test
    void getWinner(){
        List<Horse> horses = new ArrayList<Horse>(30);
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("horse" + i, 1.0 + 0.25 * i, 1 + i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        // Ищем победителя другим способом, результат которого должен совпасть с результатом Hippodrome.getWinner()
        double maxDistance = 0;
        Horse winnerHorse = new Horse("0", 0.0, 0);
        for (Horse horse:horses){
            if (horse.getDistance() > maxDistance){
                maxDistance = horse.getDistance();
                winnerHorse = horse;
            }
        }

        assertEquals(winnerHorse, hippodrome.getWinner());
    }
}