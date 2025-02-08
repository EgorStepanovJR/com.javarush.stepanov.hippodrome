import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HorseTest {
    @Test
    public void constructor_NullFirstParam_ThrownIllegalArgumentException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0, 0);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\s", "\t"})
    public void constructor_EmptyFirstParam_ThrownIllegalArgumentException(String strings) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(strings, 0, 0);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructor_NegativeSecondParam_ThrownIllegalArgumentException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", -1, 0);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_NegativeThirdParam_ThrownIllegalArgumentException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", 0, -1);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName_checkFirstParam_returnObjectName() {
        Horse horse = new Horse("noname", 0);
        assertEquals("noname", horse.getName());
    }

    @Test
    public void getSpeed_checkSecondParam_returnObjectSpeed() {
        Horse horse = new Horse("noname", 10.55555);
        assertEquals(10.55555, horse.getSpeed());
    }

    @Test
    public void getDistance_checkThirdParam_returnObjectDistance() {
        Horse horse = new Horse("noname", 0, 1000.333);
        assertEquals(1000.333, horse.getDistance());
    }

    @Test
    public void move_shouldInvokeGetRandomDoubleMethod_correctParams() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("noname", 2.4);
            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.04, 0.08, 0.2, 0.3, 0.6, 0.8, 0.9})
    public void getRandomDouble_CalculatingMethod_returnCorrectValue(double randomDoubles) {
        double speed = 2.9;
        double expectedDistance = speed * randomDoubles;

        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("noname", speed);

            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDoubles);
            horse.move();

            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}
