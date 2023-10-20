package cinema;

import java.util.ArrayList;
import java.util.List;

public class CinemaRoom {
    private int rows;
    private int columns;
    private List<Seats> seats;

    public CinemaRoom() {}

    public CinemaRoom(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        List<Seats> newSeats = new ArrayList<>();

        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= 9; j++) {
                newSeats.add(new Seats(i, j));
            }
        }

        this.seats = newSeats;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    public void setSeats(List<Seats> seats) {
        this.seats = seats;
    }

    public List<Seats> getSeats() {
        return seats;
    }
}
