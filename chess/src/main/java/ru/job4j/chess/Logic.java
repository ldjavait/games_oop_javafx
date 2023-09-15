package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 * Класс, в котором размещена логика игры.
 */
public final class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        figures[index++] = figure;
    }

    public void move(Cell source, Cell dest)
            throws FigureNotFoundException, ImpossibleMoveException, OccupiedCellException {
        int index = findBy(source);
        Cell[] steps = figures[index].way(dest);
        free(steps);
        figures[index] = figures[index].copy(dest);
    }

    /**
     * Проверяет, что фигуры не занимают элементы из массива steps.
     *
     * @param steps массив элементов.
     * @return true - если фигуры не занимают элементы.
     * @throws OccupiedCellException если фигуры занимают элементы выбрасывает исключение.
     */
    private boolean free(Cell[] steps) throws OccupiedCellException {
        for (Figure figure : figures) {
            for (Cell step : steps) {
                if (step.equals(figure.position())) {
                    throw new OccupiedCellException("This cell is already occupied.");
                }
            }
        }
        return true;
    }

    public void clean() {
        Arrays.fill(figures, null);
        index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        for (int index = 0; index != figures.length; index++) {
            Figure figure = figures[index];
            if (figure != null && figure.position().equals(cell)) {
                return index;
            }
        }
        throw new FigureNotFoundException("Figure not found on the board.");
    }
}
