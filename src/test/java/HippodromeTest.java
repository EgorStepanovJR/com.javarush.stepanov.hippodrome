import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.text.DecimalFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    public void constructor_nullParam_thrownIllegalArgumentException() {
        Throwable exeption = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exeption.getMessage());
    }

    @Test
    public void constructor_emptyList_thrownIllegalArgumentException() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(horses);
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }



    @Test
    public void getHorses_addedToObjectNewList_ShouldReturnEqualList() {

        List<Horse> horses = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("horse #" + i, randomSpeed()));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());
    }

    @Test
    void move_ListOfmockHorsesAdded_moveForEachHorseRuns() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
            //System.out.println(horse.toString());
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4, 10),
                new Horse("Ace of Spades", 2.5, 11),
                new Horse("Zephyr", 2.6, 12)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals("Zephyr", hippodrome.getWinner().getName());
    }

    static double randomSpeed() {
        int max = 3;
        int min = 2;
        int range = max - min + 1;
        DecimalFormat df = new DecimalFormat("#.#");

        double rand = (Math.random() * range) + min;
        return Double.valueOf(df.format(rand));
    }
}
