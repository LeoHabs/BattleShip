import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class Grid {
    private String[][] grid = {{"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
            {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}, {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-"}};
    private String[] letters = {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};

    public void printGrid() {
        System.out.print("  ");
        for (int i = 0; i < letters.length; i++) {
            System.out.print(Color.BLUE_BOLD);
            System.out.print(i + 1);
            System.out.print(Color.RESET);
            System.out.print(" ");
        }
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Color.BLUE_BOLD);
            System.out.print(letters[i]);
            System.out.print(Color.RESET);
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public void buildGrid(Player player){
        boolean CarrierStat = false;
        boolean BattleShipStat = false;
        boolean CruiserStat = false;
        boolean SubmarineStat = false;
        boolean DestroyerStat = false;
        System.out.println(Color.GREEN_BOLD + "Let's place a Carrier (length 5)!"+ Color.RESET);
        while (!CarrierStat){
            CarrierStat = choosePosition(new Carrier5(), player.getPlayerGrid());
        }
        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
        System.out.println(Color.GREEN_BOLD + "Let's place a Battleship (length 4)!"+ Color.RESET);
        while (!BattleShipStat){
            BattleShipStat = choosePosition(new Battleship4(), player.getPlayerGrid());
        }
        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
        System.out.println(Color.GREEN_BOLD + "Let's place a Cruiser (length 3)!"+ Color.RESET);
        while (!CruiserStat){
            CruiserStat = choosePosition(new Cruiser3(), player.getPlayerGrid());
        }
        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
        System.out.println(Color.GREEN_BOLD + "Let's place a Submarine (length 3)!"+ Color.RESET);
        while (!SubmarineStat){
            SubmarineStat = choosePosition(new Submarine3(), player.getPlayerGrid());
        }
        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
        System.out.println(Color.GREEN_BOLD + "Finally, let's place a Destroyer (length 2)!"+ Color.RESET);
        while (!DestroyerStat){
            DestroyerStat = choosePosition(new Destroyer2(), player.getPlayerGrid());
        }
        System.out.println(Color.GREEN_BOLD + "Current grid:" + Color.RESET);
        player.getPlayerGrid().printGrid();
    }

    public boolean choosePosition(Ship ship, Grid playerGrid) {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println(Color.GREEN_BOLD + "Choose a row (A-J)" + Color.RESET);
        int vertical = letterToIndex(scanner.next());
        System.out.println(Color.GREEN_BOLD + "Choose a column (1-10)" + Color.RESET);
        int horizontal = scanner.nextInt() - 1;
        System.out.println(Color.GREEN_BOLD + "Horizontal or Vertical Captain?" + Color.RESET);
        String orientation = scanner.next().toUpperCase();

        switch (orientation){
            case "HORIZONTAL":
            case "H":
                try {
                    placeShipHorizontal(vertical, horizontal, ship.size, playerGrid);
                }catch(Exception e){
                    System.out.println(Color.RED_BOLD + "Sir that ship won't fit there. Please focus the enemy is upon us!" + Color.RESET);
                    return false;
                }
                return true;
            case "VERTICAL":
            case "V":
                try{
                placeShipVertical(vertical,horizontal,ship.size, playerGrid);
                }catch(Exception e){
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

    public void placeShipHorizontal(int vertical, int horizontal, int shipSize, Grid playerGrid) {
        //Place horizontal
        int j = horizontal;
        while (shipSize > 0) {
            playerGrid.grid[vertical][j] = "X";
            shipSize--;
            j++;
        }
    }

    public void placeShipVertical(int vertical, int horizontal, int shipSize, Grid playerGrid){
        int j= vertical;
        while (shipSize>0){
            playerGrid.grid[j][horizontal]="X";
            shipSize--;
            j++;
        }
    }

    public int letterToIndex(String option) {
        switch (option) {
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


}
