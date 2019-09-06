package model.entities;

import java.util.Date;

public class Installment {
    private Date date;
    private Double value;

    public Installment(Date date, Double value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

}
