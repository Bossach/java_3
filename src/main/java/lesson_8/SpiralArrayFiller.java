package lesson_8;

public class SpiralArrayFiller {
    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int DIRECTIONS_COUNT = 4;

    private int[][] array;
    private Pos pos;
    private int direction;
    private int startDirection;
    private boolean isClockwise;

    static class Pos{
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Pos clone() {
            return new Pos(this.x, this.y);
        }
    }

    public static void main(String[] args) {
        SpiralArrayFiller filler = new SpiralArrayFiller();

        int[][] array = new int[5][5];

        //any not zero size rectangle int arrays
        filler.fillIncrement(array);
        printArray(array);

        System.out.println("------------------------");

        SpiralArrayFiller contrClockWisefiller = new SpiralArrayFiller(false);
        array = new int[4][10];
        contrClockWisefiller.fillIncrement(array);
        printArray(array);
    }

    public void fillIncrement(int[][] arr) {

        if(!checkArrayValid(arr)) throw new RuntimeException("Invalid Array");
        array = arr;
        int value = 1;
        direction = startDirection;
        pos = new Pos(0, 0);

        while (true) {
            array[pos.x][pos.y] = value++;
            if (goNextPos()) continue;
            break;
        }
    }

    public SpiralArrayFiller(boolean isClockwise) {
        this.isClockwise = isClockwise;
        if (isClockwise) startDirection = RIGHT; else startDirection = DOWN;
    }

    public SpiralArrayFiller(){
        this(true);
    }

    private boolean goNextPos() {
        Pos nextPos = getNextPos(pos);
        if (checkCell(nextPos)) {
            pos = nextPos;
            return true;
        } else {
            direction = nextDir();
            nextPos = getNextPos(pos);
            if (checkCell(nextPos)) {
                pos = nextPos;
                return true;
            }
        }
        return false;
    }

    private boolean checkCell(Pos pos) {
        if (pos.x >= 0 && pos.x < array.length && pos.y >=0 && pos.y < array[0].length) {
            if (array[pos.x][pos.y] == 0) return true;
        }
        return false;
    }

    private Pos getNextPos(Pos pos) {
        Pos nextPos = pos.clone();
        switch (direction) {
            case RIGHT:
                nextPos.y++;
                break;
            case DOWN:
                nextPos.x++;
                break;
            case LEFT:
                nextPos.y--;
                break;
            case UP:
                nextPos.x--;
        }

        return nextPos;
    }

    public int nextDir() {
        if (isClockwise) {
            direction = (direction + 1) % DIRECTIONS_COUNT;
        } else {
            direction = (direction - 1 + DIRECTIONS_COUNT) % DIRECTIONS_COUNT;
        }
        return direction;
    }
    public static void printArray(int[][] array) {
        for (int[] line : array) {
            for (int i : line) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
    }

    public boolean checkArrayValid(int[][] array){
        if (array.length == 0) return false;
        if (array[0].length == 0) return false;
        int lineLength = array[0].length;
        for (int[] line : array) {
            if (line.length != lineLength) return false;
        }
        return true;
    }
}
