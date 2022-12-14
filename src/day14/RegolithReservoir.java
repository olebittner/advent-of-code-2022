package day14;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegolithReservoir {

    public static void main(String[] args) {
        List<String> input = Util.readInput("day14/RegolithReservoir.txt");
        List<List<int[]>> scans = new ArrayList<>();
        for (String line : input) {
            scans.add(Arrays.stream(line.split(" -> ")).map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::valueOf).toArray()).toList());
        }
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMax = Integer.MIN_VALUE;
        for (List<int[]> cords : scans) {
            for (int[] c : cords) {
                xMin = Math.min(xMin, c[0]);
                xMax = Math.max(xMax, c[0]);
                yMax = Math.max(yMax, c[1]);
            }
        }

        GridMaterial[][] grid = createGrid(scans, xMin, xMax, yMax);
        visualize(grid, xMin);

        int amountOfSand = simulateSandFall(xMin, yMax, grid);
        visualize(grid, xMin);
        System.out.println(amountOfSand-1);
    }

    private static int simulateSandFall(int xMin, int yMax, GridMaterial[][] grid) {
        int amountOfSand = 0;
        boolean intoTheVoid = false;
        while (!intoTheVoid) {
            amountOfSand++;
            int x = 500- xMin;
            int y = 0;
            boolean moved;
            do {
                moved = false;
                if (y >= yMax) {
                    intoTheVoid = true;
                    break;
                }
                if (grid[x][y+1] == GridMaterial.AIR) {
                    y++;
                    moved = true;
                } else if (grid[x-1][y+1] == GridMaterial.AIR) {
                    y++;
                    x--;
                    moved = true;
                } else if (grid[x + 1][y + 1] == GridMaterial.AIR) {
                    y++;
                    x++;
                    moved = true;
                }
            } while (moved);
            grid[x][y] = GridMaterial.SAND;
        }
        return amountOfSand;
    }

    private static GridMaterial[][] createGrid(List<List<int[]>> scans, int xMin, int xMax, int yMax) {
        int xOffset = xMax - xMin;
        GridMaterial[][] grid = new GridMaterial[xOffset+1][yMax +1];
        for (GridMaterial[] gridMaterials : grid) {
            Arrays.fill(gridMaterials, GridMaterial.AIR);
        }

        for (List<int[]> cords : scans) {
            int[] startCord = cords.get(0);
            for (int cordI = 1; cordI < cords.size(); cordI++) {
                int[] endCord = cords.get(cordI);
                int lowerX = Math.min(startCord[0], endCord[0]);
                int upperX = Math.max(startCord[0], endCord[0]);
                int lowerY = Math.min(startCord[1], endCord[1]);
                int upperY = Math.max(startCord[1], endCord[1]);
                for (int x = lowerX; x <= upperX; x++) {
                    for (int y = lowerY; y <= upperY; y++) {
                        grid[x- xMin][y] = GridMaterial.ROCK;
                    }
                }
                startCord = endCord;
            }
        }
        return grid;
    }

    public static void visualize(GridMaterial[][] grid, int offset) {
        visualize(grid, -1, -1, offset);
    }

    public static void visualize(GridMaterial[][] grid, int cx, int cy, int offset) {
        StringBuilder builder = new StringBuilder();
        builder.append("—".repeat(500 - offset));
        builder.append("↓");
        builder.append("—".repeat(grid.length - (500 - offset) - 1));
        builder.append("\r\n");
        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (x == cx && y == cy) {
                    builder.append("☼");
                } else {
                    switch (grid[x][y]) {
                        case ROCK -> builder.append('◼');
                        case AIR -> builder.append('◻');
                        case SAND -> builder.append('⊡');
                    }
                }
            }
            builder.append("\r\n");
        }
        builder.append("\r\n");
        System.out.println(builder);
    }

}
