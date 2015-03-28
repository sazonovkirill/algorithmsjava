package ru.sazonovkirill.algorithms;

import java.util.*;

public class Labyrinth {
    public static final int BLACK = 0;
    public static final int WHITE = 1;

    private final Array2D arr;
    private boolean exitFound = false;

    public Labyrinth(Array2D arr) {
        this.arr = arr;
    }

    public void generate(Point start) {
        Stack<Point> waysToExpand = new Stack<>();
        exitFound = false;

        final List<Point> nextOptions = new ArrayList<>(4);

        waysToExpand.add(start);
        while (!waysToExpand.isEmpty()) {
            Point p = waysToExpand.pop();

            if (canBecomeWhite(p)) {
                arr.setWhite(p);
                if (onTheEdge(p) && p.differsFrom(start)) {
                    exitFound = true;
                }

                if (isBlack(p.up()) && canBecomeWhite(p.up())) nextOptions.add(p.up());
                if (isBlack(p.down()) && canBecomeWhite(p.down())) nextOptions.add(p.down());
                if (isBlack(p.left()) && canBecomeWhite(p.left())) nextOptions.add(p.left());
                if (isBlack(p.right()) && canBecomeWhite(p.right())) nextOptions.add(p.right());
                Collections.shuffle(nextOptions);
                int c = 0;
                for (Point t : nextOptions) {
                    waysToExpand.push(t);
                    c++;
                    if (c == 2) break;
                }
                nextOptions.clear();
            }
        }
    }

    public boolean canBecomeWhite(Point p) {
        if (outOfBounds(p)) return false;
        else if (inTheCorner(p)) return false;
        else if (onTheEdge(p)) {
            if (exitFound) return false;
            else if (outOfBounds(p.left()) || outOfBounds(p.right())) return false; // isBlack(p.up()) && isBlack(p.down());
            else return isBlack(p.left()) && isBlack(p.right());
        } else {
            int cnt = 0;
            if (isWhite(p.up())) cnt++;
            if (isWhite(p.down())) cnt++;
            if (isWhite(p.left())) cnt++;
            if (isWhite(p.right())) cnt++;
            return cnt == 1;
        }
    }

    public boolean inTheCorner(Point p) {
        return (p.x == 0 && p.y == 0) || (p.x == 0 && p.y == arr.sizeY - 1) || (p.x == arr.sizeX - 1 && p.y == 0) || (p.x == arr.sizeX - 1 && p.y == arr.sizeY - 1);
    }

    public boolean onTheEdge(Point p) {
        return p.x == 0 || p.x == arr.sizeX - 1 || p.y == 0 || p.y == arr.sizeY - 1;
    }

    public boolean isWhite(Point p) {
        if (outOfBounds(p)) return true;
        else return arr.get(p.x, p.y) == WHITE;
    }

    public boolean isBlack(Point p) {
        if (outOfBounds(p)) return false;
        return arr.get(p.x, p.y) == BLACK;
    }

    public boolean outOfBounds(Point p) {
        return p.x < 0 || p.x >= arr.sizeX || p.y < 0 || p.y >= arr.sizeY;
    }

    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point left() {
            return new Point(x - 1, y);
        }

        public Point right() {
            return new Point(x + 1, y);
        }

        public Point up() {
            return new Point(x, y + 1);
        }

        public Point down() {
            return new Point(x, y - 1);
        }

        public boolean differsFrom(Point p) {
            return x != p.x || y != p.y;
        }
    }

    public static class Array2D {
        private final int sizeX;
        private final int sizeY;
        private final int[][] arr;

        public Array2D(int x, int y, int defaultValue) {
            this.sizeX = x;
            this.sizeY = y;

            arr = new int[x][y];
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    arr[i][j] = defaultValue;
                }
            }
        }

        public int get(int x, int y) {
            return arr[x][y];
        }

        public void set(int x, int y, int v) {
            arr[x][y] = v;
        }

        public void set(Point p, int v) {
            arr[p.x][p.y] = v;
        }

        public void setWhite(Point p) {
            set(p, WHITE);
        }

        public void setBlack(Point p) {
            set(p, BLACK);
        }
    }

    public static void main(String[] args) {
        Array2D arr = new Array2D(100, 100, BLACK);

        Labyrinth l = new Labyrinth(arr);
        l.generate(new Point(1, 0));
        print(l.arr);
    }

    public static void print(Array2D arr) {

        for (int i = 0; i < arr.sizeY; i++) {
            System.out.print("<tr>");
            for (int j = 0; j < arr.sizeX; j++) {
                System.out.print(arr.get(j, i) == BLACK ? "<td class='x'>x</td>" : "<td>x</td>");
            }
            System.out.print("</tr>");
            System.out.println();
        }
    }
}
