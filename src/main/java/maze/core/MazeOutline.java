package maze.core;

import src.main.java.exceptions.MazeException;

public interface MazeOutline {

    public int[] mazeSize();

    void setStart(int[] start) throws MazeException;

    void setStart(int[] start, byte[] StartImage) throws MazeException;

    void setEnd(int[] end) throws MazeException;

    void setEnd(int[] end, byte[] EndImage) throws MazeException;

}
