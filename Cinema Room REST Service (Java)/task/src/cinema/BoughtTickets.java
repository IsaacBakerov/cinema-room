package cinema;

import java.util.UUID;

public class BoughtTickets {
    private UUID token;
    private Seats ticket;

    public BoughtTickets() {}

    public BoughtTickets(Seats ticket) {
        this.token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public void setTicket(Seats ticket) {
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public Seats getTicket() {
        return ticket;
    }
}
