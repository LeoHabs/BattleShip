import java.util.Scanner;

public class Grid {
    private String[][] grid = {{"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}};
    private String[] letters = {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};

    public void printGrid() {
        System.out.print("  ");
        for (int i = 0; i < letters.length; i++) {
            System.out.print(Color.BLUE_BOLD);
            System.out.print("|");
            System.out.print(i + 1);
            System.out.print("|");
            System.out.print(" ");
            System.out.print(Color.RESET);
        }
        for (int i = 0; i < grid.length; i++) {
            System.out.print(Color.BLUE_BOLD);
            System.out.println();
            System.out.println("_");
            System.out.print(letters[i]);
            System.out.print(Color.RESET);
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(" ");
                System.out.print(grid[i][j]);
                System.out.print(" ");
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println();
        ;
    }

    public void buildGrid(Player player) {
        player.getPlayerGrid().printGrid();
        boolean CarrierStat = false;
        boolean BattleShipStat = false;
        boolean CruiserStat = false;
        boolean SubmarineStat = false;
        boolean DestroyerStat = false;

        while (!CarrierStat) {
            try {
                System.out.println();
                System.out.println(Color.GREEN_BOLD + "Let's place a Carrier (length 5)!" + Color.RESET);
                CarrierStat = choosePosition(new Carrier5(), player.getPlayerGrid());
            } catch (Exception e) {
                System.out.println(Color.RED_BOLD + "Sir please focus" + Color.RESET);
            }
        }


        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
        try {
            System.out.println();
            System.out.println(Color.GREEN_BOLD + "Let's place a Battleship (length 4)!" + Color.RESET);
            while (!BattleShipStat) {
                BattleShipStat = choosePosition(new Battleship4(), player.getPlayerGrid());
            }
        } catch (Exception e) {
            System.out.println(Color.RED_BOLD + "Sir please focus" + Color.RESET);
        }
        System.out.println(Color.GREEN_BOLD + "Let's place a Carrier (length 5)!" + Color.RESET);
        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
        try {
            System.out.println();
            System.out.println(Color.GREEN_BOLD + "Let's place a Cruiser (length 3)!" + Color.RESET);
            while (!CruiserStat) {
                CruiserStat = choosePosition(new Cruiser3(), player.getPlayerGrid());
            }
        } catch (Exception e) {
            System.out.println(Color.RED_BOLD + "Sir please focus" + Color.RESET);
        }

        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
        try {
            System.out.println();
            System.out.println(Color.GREEN_BOLD + "Let's place a Submarine (length 3)!" + Color.RESET);
            while (!SubmarineStat) {
                SubmarineStat = choosePosition(new Submarine3(), player.getPlayerGrid());
            }
        } catch (Exception e) {
            System.out.println(Color.RED_BOLD + "Sir please focus" + Color.RESET);
        }
        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
        try {
            System.out.println();
            System.out.println(Color.GREEN_BOLD + "Finally, let's place a Destroyer (length 2)!" + Color.RESET);
            while (!DestroyerStat) {
                DestroyerStat = choosePosition(new Destroyer2(), player.getPlayerGrid());
            }
        } catch (Exception e) {
            System.out.println(Color.RED_BOLD + "Sir please focus" + Color.RESET);
        }

        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
    }

    public boolean choosePosition(Ship ship, Grid playerGrid) throws Exception {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int vertical;
        int horizontal;
        while (true) {
            System.out.println(Color.GREEN_BOLD + "Choose a row (A-J)" + Color.RESET);
            vertical = letterToIndex(scanner.next());
            if (vertical == 10) {
                continue;
            }
            break;
        }
        while (true) {
            System.out.println(Color.GREEN_BOLD + "Choose a column (1-10)" + Color.RESET);
            horizontal = scanner.nextInt() - 1;
            if (horizontal > 9 || horizontal < 0) {
                System.out.println(Color.GREEN_BOLD + "That number is not on the grid sir" + Color.RESET);
                continue;
            }
            break;
        }
        System.out.println(Color.GREEN_BOLD + "Horizontal or Vertical Captain?" + Color.RESET);
        String orientation = scanner.next().toUpperCase();

        switch (orientation) {
            case "HORIZONTAL":
            case "H":
                try {
                    placeShipHorizontal(vertical, horizontal, ship, playerGrid);
                } catch (Exception e) {
                    System.out.println(Color.RED_BOLD + "Sir that ship won't fit there. Please focus the enemy is upon us!" + Color.RESET);
                    return false;
                }
                return true;
            case "VERTICAL":
            case "V":
                try {
                    placeShipVertical(vertical, horizontal, ship, playerGrid);
                } catch (Exception e) {
                    System.out.println(Color.RED_BOLD + "Sir that ship won't fit there. Please focus the enemy is upon us!" + Color.RESET);
                    return false;
                }
                return true;
            default:
                System.out.println(Color.SOLDIER);
                System.out.println(Color.GREEN_BOLD + "Captain i couldn't understand your orders! Say again!" + Color.RESET);
                System.out.println();
                System.out.println();
                playerGrid.printGrid();
                return false;
        }
    }

    public void placeShipHorizontal(int vertical, int horizontal, Ship ship, Grid playerGrid) throws Exception {
        //Place horizontal
        int safecheckerHor = horizontal;
        int j = horizontal;
        int shipSizeCheck = ship.size;

        while (shipSizeCheck > 0) {
            if (playerGrid.grid[vertical][safecheckerHor].equals("-")) {
                safecheckerHor++;
                shipSizeCheck--;
            } else {
                System.out.println(Color.GREEN_BOLD + "We can't stack boats on top of each other sir" + Color.RESET);
                return;
            }

        }
        int shipSizeCheck2 = ship.size;
        if (playerGrid.grid[vertical].length < horizontal + shipSizeCheck) {
            throw new Exception();
        }
        while (shipSizeCheck2 > 0) {
            playerGrid.grid[vertical][j] = ship.symbol;
            shipSizeCheck2--;
            j++;
        }
    }

    public void placeShipVertical(int vertical, int horizontal, Ship ship, Grid playerGrid) throws Exception {
        int j = vertical;
        int shipSizeChecker = ship.size;
        if (playerGrid.grid.length < vertical + ship.size) {
            throw new Exception();
        }
        while (shipSizeChecker > 0) {
            playerGrid.grid[j][horizontal] = "X";
            shipSizeChecker--;
            j++;
        }
    }

    public int letterToIndex(String option) {
        switch (option.toUpperCase()) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            case "J":
                return 9;
            default:
                System.out.print(Color.RED_UNDERLINED);
                System.out.println("That letter doesn't exist Captain!!");
                System.out.println(Color.RESET);
                return 10;
        }
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }
}
