package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {
    @Test
    public void whenCheckPosition() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell expected = bishopBlack.position();
        assertThat(expected).isEqualTo(Cell.C8);
    }

    @Test
    public void whenCheckCopy() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Figure expected = bishopBlack.copy(Cell.C7);
        assertThat(expected.position()).isEqualTo(Cell.C7);
    }

    @Test
    public void whenCheckWay() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] steps = bishopBlack.way(Cell.G5);
        assertThat(steps).isEqualTo(new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5});
    }

    @Test
    public void whenCheckExceptionWay() throws ImpossibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    bishopBlack.way(Cell.C3);
                });
        assertThat(exception.getMessage()).isEqualTo(String.format(
                "Could not move by diagonal from %s to %s",
                bishopBlack.position(),
                Cell.C3
        ));
    }

    @Test
    void whenCorrectWay() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] steps = bishopBlack.way(Cell.G5);
        assertThat(steps).isEqualTo(new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5});
    }

    @Test
    void whenCheckDiagonalTrue() {
        BishopBlack cellC1 = new BishopBlack(Cell.F8);
        boolean rsl = cellC1.isDiagonal(cellC1.position(), Cell.H6);
        assertThat(rsl).isTrue();
    }

    @Test
    void whenCheckDiagonalFalse() {
        BishopBlack cellC1 = new BishopBlack(Cell.C1);
        boolean rsl = cellC1.isDiagonal(cellC1.position(), Cell.C3);
        assertThat(rsl).isFalse();
    }
}