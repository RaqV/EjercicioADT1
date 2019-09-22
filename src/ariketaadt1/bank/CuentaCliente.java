/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ariketaadt1.bank;

/**
 *
 * @author rvalv
 */
public class CuentaCliente {
    
    private long idCuenta;
    
    private long idCliente;

    public CuentaCliente(long idCuenta, long idCliente) {
        this.idCuenta = idCuenta;
        this.idCliente = idCliente;
    }

    public long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setICliente(long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.idCuenta ^ (this.idCuenta >>> 32));
        hash = 89 * hash + (int) (this.idCliente ^ (this.idCliente >>> 32));
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
        final CuentaCliente other = (CuentaCliente) obj;
        if (this.idCuenta != other.idCuenta) {
            return false;
        }
        if (this.idCliente != other.idCliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CuentaCliente{" + "idCuenta=" + idCuenta + ", idCliente=" + idCliente + '}';
    }
    
    
}
