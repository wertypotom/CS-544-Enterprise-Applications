package bank.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "tracerecord")
public class Trace {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private String string;

    public Trace() {
    }

    public Trace(Date date, String string) {
        this.date = date;
        this.string = string;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
