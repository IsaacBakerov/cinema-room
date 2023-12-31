package cinema;

public class Seats {
    private int row;
    private int column;

    private int price;

    public Seats() {}

    public Seats(int row, int column) {
        this.row = row;
        this.column = column;

        if (row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    public void setRow(int row){
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() { return price; };
}
