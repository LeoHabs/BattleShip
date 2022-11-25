public class Player {
    private String name;
    private Grid playerGrid = new Grid();

    private Grid matchGrid = new Grid();

    private int counterHits = 0;


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grid getPlayerGrid() {
        return playerGrid;
    }

    public void setPlayerGrid(Grid playerGrid) {
        this.playerGrid = playerGrid;
    }

    public int getCounterHits() {
        return counterHits;
    }

    public void setCounterHits(int counterHits) {
        this.counterHits = counterHits;
    }
}
