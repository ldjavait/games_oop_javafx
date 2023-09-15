package ru.job4j.chess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {
    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenOccupiedCellException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.F8);
        logic.add(bishopBlack);
        PawnBlack pawnBlack1 = new PawnBlack(Cell.G7);
        logic.add(pawnBlack1);
        OccupiedCellException exception = assertThrows(
                OccupiedCellException.class,
                () -> {
                    logic.move(bishopBlack.position(), Cell.G7);
                });
        assertThat(exception.getMessage()).isEqualTo("This cell is already occupied.");
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.F8);
        logic.add(bishopBlack);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    logic.move(bishopBlack.position(), Cell.F4);
                });
        assertThat(exception.getMessage()).isEqualTo(
                String.format(
                        "Could not move by diagonal from %s to %s",
                        bishopBlack.position(),
                        Cell.F4
                )
        );
    }
}