import java.util.Scanner;

public class Game {

    private static Player[] players = new Player[2];


    public static void game(){
        Scanner scanner = new Scanner(System.in);
        gameStartProcedure();
        Player winner = null;
        while (winner == null) {
            boolean hitComboPlayer = true;
            boolean hitComboCPU = true;
            while (hitComboPlayer) {
                players[0].getMatchGrid().printGrid();
                System.out.println(Color.GREEN_BOLD + "Where do we shoot?" + Color.RESET);
                int vertical;
                while(true){
                    System.out.println(Color.GREEN_BOLD + "Choose a row (A-J)" + Color.RESET);
                    vertical = players[0].getMatchGrid().letterToIndex(scanner.next());
                    if(vertical == 10){
                        continue;
                    }
                    break;
                }
                int horizontal;
                while(true){
                    System.out.println(Color.GREEN_BOLD + "Choose a column (1-10)" + Color.RESET);
                    horizontal = scanner.nextInt() - 1;
                    if(vertical > 9 || vertical< 0){
                        continue;
                    }
                    break;
                }

                hitComboPlayer = checkHit(vertical, horizontal, players[0], players[1]);
            }
            while (hitComboCPU){
                while (hitComboCPU){
                    hitComboCPU = cpuAttack(players[1],players[0]);
                }
            }
            winner = checkWin(players[0]);
            winner = checkWin(players[1]);
        }
        System.out.println(Color.GREEN_BOLD + "The winner is " + winner.getName() + Color.RESET);
    }

    public static boolean cpuAttack(Player cpu, Player player) {
        boolean cpuAttacked = true;
        while (cpuAttacked) {
            int vertical = (int) Math.floor(Math.random() * 9);
            int horizontal = (int) Math.floor(Math.random() * 9);
            if (cpu.getPlayerGrid().getGrid()[vertical][horizontal].equals(Color.HIT_SYMBOL.toString()) && cpu.getPlayerGrid().getGrid()[vertical][horizontal].equals(Color.MISS_SYMBOL.toString())) {
                continue;
            }
            cpuAttacked = checkHit(vertical,horizontal,cpu,player);
        }
        return false;
    }

    public static void gameStartProcedure() {
        Scanner scanner = new Scanner(System.in);
        gameTitle();
        System.out.println(Color.SOLDIER);
        System.out.println(Color.GREEN_BOLD + "Captain please remember me of your name, sir!" + Color.RESET);
        System.out.print("Enter name: ");
        Player player = new Player(scanner.next());
        Player cpu = new Player("CPU");
        players[0] = player;
        players[1] = cpu;
        players[0].getPlayerGrid().buildGrid(players[0]);
        generateCpuGrid(cpu.getPlayerGrid());
    }

    public static Player checkWin(Player player) {
        if (player.getCounterHits() == 17) {
            return player;
        }
        return null;
    }

    public static void gameTitle() {
        System.out.println(Color.BLUE_BOLD);
        System.out.println("▀█████████▄     ▄████████     ███         ███      ▄█          ▄████████    ▄████████    ▄█    █▄     ▄█     ▄███████▄ \n" +
                "  ███    ███   ███    ███ ▀█████████▄ ▀█████████▄ ███         ███    ███   ███    ███   ███    ███   ███    ███    ███ \n" +
                "  ███    ███   ███    ███    ▀███▀▀██    ▀███▀▀██ ███         ███    █▀    ███    █▀    ███    ███   ███▌   ███    ███ \n" +
                " ▄███▄▄▄██▀    ███    ███     ███   ▀     ███   ▀ ███        ▄███▄▄▄       ███         ▄███▄▄▄▄███▄▄ ███▌   ███    ███ \n" +
                "▀▀███▀▀▀██▄  ▀███████████     ███         ███     ███       ▀▀███▀▀▀     ▀███████████ ▀▀███▀▀▀▀███▀  ███▌ ▀█████████▀  \n" +
                "  ███    ██▄   ███    ███     ███         ███     ███         ███    █▄           ███   ███    ███   ███    ███        \n" +
                "  ███    ███   ███    ███     ███         ███     ███▌    ▄   ███    ███    ▄█    ███   ███    ███   ███    ███        \n" +
                "▄█████████▀    ███    █▀     ▄████▀      ▄████▀   █████▄▄██   ██████████  ▄████████▀    ███    █▀    █▀    ▄████▀      \n" +
                "                                                  ▀                                                                    ");
        System.out.println(
                "                                              __/___            \n" +
                        "                                        _____/______|           \n" +
                        "                                _______/_____\\_______\\_____     \n" +
                        "                                \\              < < <       |    \n" +
                        "                              ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(Color.RESET);
    }

    public static boolean checkHit(int vertical, int horizontal, Player attacker, Player target) {
        if(target.getPlayerGrid().getGrid()[vertical][horizontal].equals("-")){
            System.out.println(Color.GREEN_BOLD + "We already attacked that position sir!" + Color.RESET);
            return true;
        }
        if (!target.getPlayerGrid().getGrid()[vertical][horizontal].equals("-")) {
            attacker.getMatchGrid().getGrid()[vertical][horizontal] = Color.HIT_SYMBOL.toString();
            attacker.setCounterHits(attacker.getCounterHits() + 1);
            if(attacker.equals(players[1])){
                System.out.println("ENEMY HIT");
                return true;
            }
            System.out.println(Color.GREEN_BOLD + "HIT!!" + Color.RESET);
            return true;
        }
        if(attacker.equals(players[1])){
            System.out.println("ENEMY MISS");
            return false;
        }
        System.out.println("MISSED");
        attacker.getMatchGrid().getGrid()[vertical][horizontal] = Color.MISS_SYMBOL.toString();
        return false;
    }


    public static void generateCpuGrid(Grid cpuGrid) {
        Player cpu = new Player("CPU");
        boolean generatedCarrier = false;
        while (!generatedCarrier) {
            int vertical = (int) Math.floor(Math.random() * 10);
            int horizontal = (int) Math.floor(Math.random() * 10);
            int orientation = (int) Math.floor(Math.random() * 2);
            try {
                generatedCarrier = placeCPUCarrier(vertical, horizontal, orientation, cpuGrid);
            } catch (Exception e) {
                System.out.println("OUT OF BOUNDS");
            }
        }
        boolean generatedBattleShip = false;
        while (!generatedBattleShip) {
            int vertical = (int) Math.floor(Math.random() * 10);
            int horizontal = (int) Math.floor(Math.random() * 10);
            int orientation = (int) Math.floor(Math.random() * 2);
            try {
                generatedBattleShip = placeCPUBattleShip(vertical, horizontal, orientation, cpuGrid);
            } catch (Exception e) {
                System.out.println("OUT OF BOUNDS");
            }
        }
        boolean generatedCruiser = false;
        while (!generatedCruiser) {
            int vertical = (int) Math.floor(Math.random() * 10);
            int horizontal = (int) Math.floor(Math.random() * 10);
            int orientation = (int) Math.floor(Math.random() * 2);
            try {
                generatedCruiser = placeCPUSize3(vertical, horizontal, orientation, cpuGrid);
            } catch (Exception e) {
                System.out.println("OUT OF BOUNDS");
            }
        }
        boolean generatedSubmarine = false;
        while (!generatedSubmarine) {
            int vertical = (int) Math.floor(Math.random() * 10);
            int horizontal = (int) Math.floor(Math.random() * 10);
            int orientation = (int) Math.floor(Math.random() * 2);
            try {
                generatedSubmarine = placeCPUSize3(vertical, horizontal, orientation, cpuGrid);
            } catch (Exception e) {
                System.out.println("OUT OF BOUNDS");
            }
        }
        boolean generatedDestroyer = false;
        while (!generatedDestroyer) {
            int vertical = (int) Math.floor(Math.random() * 10);
            int horizontal = (int) Math.floor(Math.random() * 10);
            int orientation = (int) Math.floor(Math.random() * 2);
            try {
                generatedDestroyer = placeCPUDestroyer(vertical, horizontal, orientation, cpuGrid);
            } catch (Exception e) {
                System.out.println("OUT OF BOUNDS");
            }
        }
    }

    public static boolean placeCPUCarrier(int vertical, int horizontal, int orientation, Grid cpuGrid) throws Exception {
        Carrier5 carrier5 = new Carrier5();
        int shipSize = carrier5.size;

        if (cpuGrid.getGrid().length - shipSize < vertical) {
            throw new Exception();
        }
        if (orientation == 0) {
            // vertical carrier
            if (cpuGrid.getGrid()[vertical][horizontal].equals("-") && cpuGrid.getGrid()[vertical + 1][horizontal].equals("-") && cpuGrid.getGrid()[vertical + 2][horizontal].equals("-") &&
                    cpuGrid.getGrid()[vertical + 3][horizontal].equals("-") && cpuGrid.getGrid()[vertical + 4][horizontal].equals("-")) {
                int j = vertical;

                for (int i = 0; i < 5; i++) {
                    cpuGrid.getGrid()[j][horizontal] = carrier5.symbol;
                    shipSize--;
                    j++;

                }
                cpuGrid.printGrid();
                return true;
            }
        }
        if (orientation == 1) {
            // horizontal carrier
            if (cpuGrid.getGrid()[vertical][horizontal].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 1].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 2].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 3].equals("-") &&
                    cpuGrid.getGrid()[vertical][horizontal + 4].equals("-")) {
                int j = vertical;
                if (cpuGrid.getGrid()[0].length < horizontal + shipSize) {
                    throw new Exception();
                }
                for (int i = 0; i < 5; i++) {
                    cpuGrid.getGrid()[vertical][j] = carrier5.symbol;
                    shipSize--;
                    j++;
                }
                cpuGrid.printGrid();
                return true;
            }
        }
        return false;
    }

    public static boolean placeCPUBattleShip(int vertical, int horizontal, int orientation, Grid cpuGrid) throws Exception {
        Battleship4 battleship4 = new Battleship4();
        int shipSize = battleship4.size;
        if (cpuGrid.getGrid().length - shipSize < vertical) {
            throw new Exception();
        }

        if (orientation == 0) {
            // vertical battleship
            if (cpuGrid.getGrid()[vertical][horizontal].equals("-") && cpuGrid.getGrid()[vertical + 1][horizontal].equals("-") && cpuGrid.getGrid()[vertical + 2][horizontal].equals("-") &&
                    cpuGrid.getGrid()[vertical + 3][horizontal].equals("-")) {
                int j = vertical;

                for (int i = 0; i < 4; i++) {
                    cpuGrid.getGrid()[j][horizontal] = battleship4.symbol;
                    shipSize--;
                    j++;
                }
                cpuGrid.printGrid();
                return true;
            }
        }

        if (orientation == 1) {
            // horizontal battleship
            if (cpuGrid.getGrid()[vertical][horizontal].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 1].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 2].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 3].equals("-")) {
                int j = vertical;
                if (cpuGrid.getGrid().length < horizontal + shipSize) {
                    throw new Exception();
                }
                for (int i = 0; i < 4; i++) {
                    cpuGrid.getGrid()[vertical][j] = battleship4.symbol;
                    shipSize--;
                    j++;
                }
                cpuGrid.printGrid();
                return true;
            }
        }
        return false;
    }

    public static boolean placeCPUSize3(int vertical, int horizontal, int orientation, Grid cpuGrid) throws Exception {
        Cruiser3 cruiser3 = new Cruiser3();
        int shipSize = cruiser3.size;
        if (cpuGrid.getGrid().length - shipSize < vertical) {
            throw new Exception();
        }

        if (orientation == 0) {
            // vertical cruiser
            if (cpuGrid.getGrid()[vertical][horizontal].equals("-") && cpuGrid.getGrid()[vertical + 1][horizontal].equals("-") && cpuGrid.getGrid()[vertical + 2][horizontal].equals("-")) {
                int j = vertical;

                for (int i = 0; i < 3; i++) {
                    cpuGrid.getGrid()[j][horizontal] = cruiser3.symbol;
                    shipSize--;
                    j++;
                }
                cpuGrid.printGrid();
                return true;
            }
        }

        if (orientation == 1) {
            // horizontal cruiser
            if (cpuGrid.getGrid()[vertical][horizontal].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 1].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 2].equals("-")) {
                int j = vertical;
                if (cpuGrid.getGrid().length < horizontal + shipSize) {
                    throw new Exception();
                }
                for (int i = 0; i < 3; i++) {
                    cpuGrid.getGrid()[vertical][j] = cruiser3.symbol;
                    shipSize--;
                    j++;
                }
                cpuGrid.printGrid();
                return true;
            }
        }
        return false;
    }

    public static boolean placeCPUDestroyer(int vertical, int horizontal, int orientation, Grid cpuGrid) throws Exception {
        Destroyer2 destroyer2 = new Destroyer2();
        int shipSize = destroyer2.size;
        if (cpuGrid.getGrid().length - shipSize < vertical) {
            throw new Exception();
        }

        if (orientation == 0) {
            // vertical cruiser
            if (cpuGrid.getGrid()[vertical][horizontal].equals("-") && cpuGrid.getGrid()[vertical + 1][horizontal].equals("-")) {
                int j = vertical;
                for (int i = 0; i < 2; i++) {
                    cpuGrid.getGrid()[j][horizontal] = destroyer2.symbol;
                    j++;
                }
                cpuGrid.printGrid();
                return true;
            }
        }

        if (orientation == 1) {
            // horizontal cruiser
            if (cpuGrid.getGrid()[vertical][horizontal].equals("-") && cpuGrid.getGrid()[vertical][horizontal + 1].equals("-")) {
                int j = vertical;
                if (cpuGrid.getGrid().length < horizontal + shipSize) {
                    throw new Exception();
                }
                for (int i = 0; i < 2; i++) {
                    cpuGrid.getGrid()[vertical][j] = destroyer2.symbol;
                    j++;
                }
                cpuGrid.printGrid();
                return true;
            }
        }
        return false;
    }


}






