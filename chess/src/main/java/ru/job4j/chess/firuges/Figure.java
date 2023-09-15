package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;

/**
 * Описывает поведение фигур.
 */
public interface Figure {
    /**
     * Для получения позиции фигуры на поле.
     *
     * @return позицию на поле.
     */
    Cell position();

    /**
     * Возвращает массив с клетками, которые должна пройти фигура при своем движении из текущей позиции до клетки dest
     *
     * @param dest конечная точка.
     * @return массив с клетками из текущей позиции до клетки dest.
     * @throws ImpossibleMoveException если ход сделать нельзя - ловим исключение.
     */
    Cell[] way(Cell dest) throws ImpossibleMoveException;

    /**
     * Для реализации имени картинки фигуры.
     *
     * @return имя картинки фигуры.
     */
    default String icon() {
        return String.format("%s.png", getClass().getSimpleName());
    }

    /**
     * Для создания одноразового объекта класса с позицией dest
     *
     * @param dest где будет располагаться фигура.
     * @return объект.
     */
    Figure copy(Cell dest);
}
