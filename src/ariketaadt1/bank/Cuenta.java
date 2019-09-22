/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ariketaadt1.bank;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author rvalv
 */
public class Cuenta {
     private Long id=null;
     private double balance;
     private double beginBalance;
     private Date beginBalanceTimestamp;
     private double creditLine;
     private String description;
     private int    type;

    public Cuenta(double balance, double beginBalance, Date beginBalanceTimestamp, double creditLine, String description, int type) {
        this.balance = balance;
        this.beginBalance = beginBalance;
        this.beginBalanceTimestamp = beginBalanceTimestamp;
        this.creditLine = creditLine;
        this.description = description;
        this.type = type;
    }
    
    //Constructor de cuenta que pide los datos de la cuenta por pantalla y sin el id
    public Cuenta() throws IOException {
        System.out.println("Introduce el balance actual de la cta");
        this.balance = Utilidades.leerDouble();
        System.out.println("Introduce el balance inicial de la cta");
        this.beginBalance = Utilidades.leerDouble();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.beginBalanceTimestamp = timestamp;
        System.out.println("Introduce el crédito de la cta");
        this.creditLine = Utilidades.leerDouble();
        System.out.println("Introduce la descripción de la cta");
        this.description = Utilidades.introducirCadena();
        System.out.println("Introduce el tipo de la cta, 0 o 1");
        this.type = Utilidades.leerInt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBeginBalance() {
        return beginBalance;
    }

    public void setBeginBalance(double beginBalance) {
        this.beginBalance = beginBalance;
    }

    public Date getBeginBalanceTimestamp() {
        return beginBalanceTimestamp;
    }

    public void setBeginBalanceTimestamp(Date beginBalanceTimestamp) {
        this.beginBalanceTimestamp = beginBalanceTimestamp;
    }

    public double getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(double creditLine) {
        this.creditLine = creditLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "id=" + id +  ", balance=" + balance + ", beginBalance=" + beginBalance+ ", beginBalanceTimestamp=" + beginBalanceTimestamp + ", creditLine=" + creditLine + ", description=" + description + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.balance) ^ (Double.doubleToLongBits(this.balance) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.beginBalance) ^ (Double.doubleToLongBits(this.beginBalance) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.beginBalanceTimestamp);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.creditLine) ^ (Double.doubleToLongBits(this.creditLine) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + this.type;
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
        final Cuenta other = (Cuenta) obj;
        if (Double.doubleToLongBits(this.balance) != Double.doubleToLongBits(other.balance)) {
            return false;
        }
        if (Double.doubleToLongBits(this.beginBalance) != Double.doubleToLongBits(other.beginBalance)) {
            return false;
        }
        if (Double.doubleToLongBits(this.creditLine) != Double.doubleToLongBits(other.creditLine)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
       
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.beginBalanceTimestamp, other.beginBalanceTimestamp)) {
            return false;
        }
        return true;
    }
    
     
}
