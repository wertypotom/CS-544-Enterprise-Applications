package domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue
    private Long id;

    private String flightNumber;

    @Column(name = "from_city")
    private String from;

    @Column(name = "to_city")
    private String to;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    public Flight() {
    }

    public Flight(String flightNumber, String from, String to, LocalDate date) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }
}
