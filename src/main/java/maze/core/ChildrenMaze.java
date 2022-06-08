package maze.core;

public class ChildrenMaze extends Maze {



    /**
     * Constructor for ChildrenMaze object that generates a default tile set for the maze
     * @param size where size[0] is the width(x) and size[1] is the height(y)
     * @author Jack
     */
    public ChildrenMaze(int[] size) {
        super(size);

    }
    @Override
    public byte[] getEndImage() {
        return this.mazeTile(this.getEnd()[0],this.getEnd()[1]).getImage();
    }

}
