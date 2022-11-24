public class Main {
    public static void main(String[] args) {
    Player newPlayer = new Player();
    Game.gameTitle();
    newPlayer.getPlayerGrid().printGrid();
    newPlayer.getPlayerGrid().buildGrid(newPlayer);
    }
}