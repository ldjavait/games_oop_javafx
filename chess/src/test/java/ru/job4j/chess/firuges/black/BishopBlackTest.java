package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;

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
}