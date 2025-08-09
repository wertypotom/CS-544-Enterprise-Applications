package ToolCalling.profit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProfitEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String month;
    private Double profit;

    public ProfitEntity() {}

    public ProfitEntity(String month, Double profit) {
        this.month = month;
        this.profit = profit;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "ProfitEntity{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", profit=" + profit +
                '}';
    }
}
