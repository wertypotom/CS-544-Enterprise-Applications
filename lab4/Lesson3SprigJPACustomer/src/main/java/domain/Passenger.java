package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id")
    List<Flight> flights = new ArrayList<>();

    public Passenger() {}

    public Passenger(String name, Flight flight) {
        this.name = name;
        this.flights.add(flight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlight(Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", flights=" + flights +
                '}';
    }
}
