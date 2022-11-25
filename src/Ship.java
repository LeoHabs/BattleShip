public abstract class Ship {
    protected String name;
    protected int size;

    protected String symbol;

    public Ship() {
    }

    public Ship(String name, int size, String symbol) {
        this.name = name;
        this.size = size;
        this.symbol = symbol;
    }
}
