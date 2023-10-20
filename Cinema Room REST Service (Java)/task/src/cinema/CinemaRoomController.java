package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

class Token {
    UUID token;

    public Token() {
    }

    public Token(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
@RestController
public class CinemaRoomController {
    public final CinemaRoom cinemaRoom = new CinemaRoom(9, 9);
    public static ArrayList<BoughtTickets> boughtTicketsList = new ArrayList<>();



    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    @GetMapping("/stats")
    public Object getStats(@RequestParam Optional<String> password) {
        Optional<String> truePassword = Optional.of("super_secret");
        if (password.isPresent() && password.equals(truePassword)) {
            int income = 0;
            int available = cinemaRoom.getSeats().size();
            int purchased = 0;
            for (BoughtTickets boughtTicket : boughtTicketsList) {
                income += boughtTicket.getTicket().getPrice();
                purchased++;
            }

            return new ResponseEntity<>(Map.of(
                    "income", income,
                    "available", available,
                    "purchased", purchased ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/return")
    public Object returnTicket(@RequestBody Token token) {
        for (BoughtTickets boughtTicket : boughtTicketsList) {
            if (boughtTicket.getToken().equals(token.getToken())) {
                cinemaRoom.getSeats().add(boughtTicket.getTicket());
                boughtTicketsList.remove(boughtTicket);
                return new ResponseEntity<>(Map.of("ticket", boughtTicket.getTicket()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/purchase")
    public Object purchaseSeat(@RequestBody Seats inputSeat) {
        if (inputSeat.getRow() > cinemaRoom.getRows() || inputSeat.getRow() < 1
                || inputSeat.getColumn() > cinemaRoom.getColumns() || inputSeat.getColumn() < 1) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else {
            boolean isInList = true;
            Seats tempSeat = new Seats();
            BoughtTickets boughtTicket = new BoughtTickets();
            for (Seats seat : cinemaRoom.getSeats()) {
                if (seat.getRow() == inputSeat.getRow() && seat.getColumn() == inputSeat.getColumn()) {
                    boughtTicket = new BoughtTickets(seat);
                    boughtTicketsList.add(boughtTicket);
                    cinemaRoom.getSeats().remove(seat);
                    isInList = true;
                    break;
                } else {
                    isInList = false;
                }
            }

            if (isInList) {
                return boughtTicket;
            } else {
                return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
            }
        }
    }
}
