/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ariketaadt1.bank;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author rvalv
 */
public class Movimiento {
    private Long id=null;
    private Long idCuenta;
    private double amount;
    private double balance;
    private String description;
    private Date timestamp;
    private long account_id;

    public Movimiento(double amount, double balance, String description, Date timestamp, long account_id) {
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.timestamp = timestamp;
        this.account_id = account_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    @Override
    public String toString() {
        return "Movimientos{" + "id=" + id + ", amount=" + amount + ", balance=" + balance + ", description=" + description + ", account_id=" + account_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.amount) ^ (Double.doubleToLongBits(this.amount) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.balance) ^ (Double.doubleToLongBits(this.balance) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.timestamp);
        hash = 89 * hash + (int) (this.account_id ^ (this.account_id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movimiento other = (Movimiento) obj;
        if (Double.doubleToLongBits(this.amount) != Double.doubleToLongBits(other.amount)) {
            return false;
        }
        if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }
    
    
}
