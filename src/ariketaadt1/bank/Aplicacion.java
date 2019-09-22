/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ariketaadt1.bank;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author rvalv
 */
public class Aplicacion {
    
     public static void main (String[] args) throws SQLException, DAOException, Exception{
         
        //Creamos los clientes
        Cliente clte1 = new Cliente("Bilbao", "rvalverde@gmail.com", "Raquel", "Valverde", "RV", 946005934, "Euskadi", "Gaiarrez", 3);
        Cliente clte2 = new Cliente("Barakaldo", "mara@gmail.com", "Mara", "Gomez", "MG", 946003334, "Euskadi", "Gabriel Aresti", 32231);
        Cliente clte3 = new Cliente("Etxebarri", "unax@gmail.com", "Unax", "Ibañez", "UI", 946004434, "Euskadi", "Ondarroa", 11223);
        
        //Creamos las cuentas
        //Para recojer el timestamp del sistema
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Cuenta cta1 = new Cuenta();
        timestamp = new Timestamp(System.currentTimeMillis());
        Cuenta cta2 = new Cuenta();
        timestamp = new Timestamp(System.currentTimeMillis());
        Cuenta cta3 = new Cuenta();
        
         
        //Creamos el DAO
        DAO dao = new DAO();
        
        //Ejecutamos acciones
        //CLIENTES
        dao.insertar(clte1);
        System.out.println("El cliente se ha creado con el idenficador " + clte1.getId());
        dao.insertar(clte2);
        System.out.println("El cliente se ha creado con el idenficador " + clte2.getId());
        dao.insertar(clte3);
        System.out.println("El cliente se ha creado con el idenficador " + clte3.getId());
        //CUENTAS
        dao.insertar(cta1);
        System.out.println("La Cuenta se ha creado con el idenficador " + cta1.getId());
        dao.insertar(cta2);
        System.out.println("La Cuenta se ha creado con el idenficador " + cta2.getId());
        dao.insertar(cta3);
        System.out.println("La Cuenta se ha creado con el idenficador " + cta3.getId());
        //MOVIMIENTOS
        //CREAR UN MOVIMIENTO PARA UNA CTA
        //Vamos a solicitar la identificación de la Cta para el que queremos introducir un movimiento
        System.out.println("Introduce el Id de la cta para la que quieres crear un movimiento");
        Long idCta = Utilidades.leerLong();
        //Primero comprobamos la cta existe
        if (dao.existeCta(idCta)){
            dao.insertarMovCta(idCta);
        } else {
            System.out.println("La cuenta que has introducido no existe en la BD");
        }
        //CUENTAS-CLIENTES
        //CREAR UNA CTA PARA UN CLIENTE
        //Vamos a solicitar la identificación del Clte para el que queremos introducir una cta
        System.out.println("Introduce el Id del cliente para el que quieres crear una cuenta");
        Long idClt = Utilidades.leerLong();
        //Primero comprobamos que el cliente existe 
        if (dao.existeClt(idClt)){
                dao.insertarCtaClte(idClt);
        } else {
            System.out.println("El cliente que has introducido no existe en la BD");
        }
       
        //DESCONECXION
        dao.desconectar();
       
     }
    
}
