
package ariketaadt1.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class DAO {
    
    //*************************************************************************************
    //CLIENTE
  
    final String INSERTcte = "INSERT INTO customer(city, email, firstName, lastName, middleInitial, phone, state, street, zip) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String GETONEcte = "SELECT id, city, email, firstName, lastName, middleInitial, phone, state, street, zip FROM customer WHERE id = ?";
    final String CTASCLT = "SELECT id, balance, beginBalance, beginBalanceTimestamp, creditLine, description, type FROM `account` INNER JOIN `customer_account` ON id = accounts_id WHERE customers_id = ?";
    final String INSERTctaclt = "INSERT INTO customer_account(customers_id, accounts_id) VALUES( ?, ?)";
    final String INSERTcta = "INSERT INTO account(balance, beginBalance, beginBalanceTimestamp, creditLine, description, type) VALUES( ?, ?, ?, ?, ?, ?)";
    final String GETONE = "SELECT id, balance, beginBalance, beginBalanceTimestamp, creditLine, description, type FROM account WHERE id = ?";
    final String MOVSCTA = "SELECT movement.id, movement.amount, movement.balance, movement.description, movement.timestamp FROM `movement` INNER JOIN `account` ON account.id = account_id WHERE account_id = ?";
    final String INSERTmov = "INSERT INTO movement(amount, balance, description, timestamp, account_id) VALUES( ?, ?, ?, ?, ?)";
    
    private Connection conn;
    private Properties connectionProps;
    
        
    public DAO() throws SQLException{
        connectionProps = new Properties();
    	connectionProps.put("user", "root");
    	connectionProps.put("password", "raquel");
        conn = DriverManager.getConnection ("jdbc:mysql://localhost/bankdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", connectionProps);
    }

    public void desconectar()throws SQLException{
        if (conn != null) {
        conn.close();
        }
    }

    void insertar(Cliente c) throws DAOException, SQLException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            //He añadido el stat.RETURN_GENERATED_KEYS para hacer posteriormente el getGeneratedKeys
            stat =conn.prepareStatement(INSERTcte, stat.RETURN_GENERATED_KEYS);
           
            stat.setString(1, c.getCity());
            stat.setString(2, c.getEmail());
            stat.setString(3, c.getFirstName());
            stat.setString(4, c.getLastName());
            stat.setString(5, c.getMiddleInitial());
            stat.setLong(6, c.getPhone());
            stat.setString(7, c.getState());
            stat.setString(8, c.getStreet());
            stat.setInt(9, c.getZip());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardato");
            }
            //Queremos obtener con que clave se ha guardado el cliente
            rs =stat.getGeneratedKeys();
            if(rs.next()){
                c.setId(rs.getLong(1));
            }else {
                throw new DAOException("No puedo asignar ID a este cliente");
            }
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
        
    }
    
    private Cliente convertir(ResultSet rs) throws SQLException {
        String city = rs.getString("city");
        String email = rs.getString("email");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String middleInitial = rs.getString("middleInitial");
        Long phone = rs.getLong("phone");
        String state = rs.getString("state");
        String street = rs.getString("street");
        int zip = rs.getInt("zip");
        Cliente c = new Cliente(city, email, firstName, lastName, middleInitial, phone, state, street, zip);
        c.setId(rs.getLong("id"));
        return c;    
    }
    
    Cliente obtener (Long id) throws DAOException, SQLException {
         PreparedStatement stat = null;
         ResultSet rs= null;
         Cliente c = null;
        try{
            stat =conn.prepareStatement(GETONEcte);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()){
                c = convertir(rs);
            }else{
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
     return c;   
    }
    
    //Metodo que nos confirma que un clte existe en la tabla de clientes
    boolean existeClt (Long id) throws DAOException, SQLException {
        PreparedStatement stat = null;
        ResultSet rs= null;
        Cliente c = null;
        boolean encontrado = false;
        try{
            stat =conn.prepareStatement(GETONEcte);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            encontrado = rs.next();
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
     return encontrado;   
    }
    
    //*************************************************************************************
    //*************************************************************************************
    //CUENTA-CLIENTE
    
    void insertar(CuentaCliente ctaClt) throws DAOException, SQLException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            stat =conn.prepareStatement(INSERTctaclt);
           
            stat.setLong(1, ctaClt.getIdCuenta());
            stat.setLong(2, ctaClt.getIdCliente());
            
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
        
    }
       
    //ASOCIA UNA CUENTA Y UN CLIENTE
    void crear(Long cte, Long cta) throws DAOException, SQLException {
        
            CuentaCliente nueva = new CuentaCliente(cte, cta);
 
            insertar(nueva);
          
        
    }
  //*************************************************************************************
  //CUENTAS
    
    void insertar(Cuenta cta) throws DAOException, SQLException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            //He añadido el stat.RETURN_GENERATED_KEYS para hacer posteriormente el getGeneratedKeys
            stat =conn.prepareStatement(INSERTcta, stat.RETURN_GENERATED_KEYS);
           
            stat.setDouble(1, cta.getBalance());
            stat.setDouble(2, cta.getBeginBalance());
            stat.setTimestamp(3, (Timestamp) cta.getBeginBalanceTimestamp());
            stat.setDouble(4, cta.getCreditLine());
            stat.setString(5, cta.getDescription());
            stat.setInt(6, cta.getType());
            
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
            //Queremos obtener con que clave se ha guardado el cliente
            rs =stat.getGeneratedKeys();
            if(rs.next()){
                cta.setId(rs.getLong(1));
            }else {
                throw new DAOException("No puedo asignar ID a esta Cuenta");
            }
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
        
    }
     
    private Cuenta convertirCta(ResultSet rs) throws SQLException {
        Double balance = rs.getDouble("balance");
        Double beginBalance = rs.getDouble("beginBalance");
        Date fecha = rs.getDate("beginBalanceTimestamp");
        Double creditLine = rs.getDouble("creditLine");
        String description = rs.getString("description");
        int type = rs.getInt("type");
        Cuenta cta = new Cuenta(balance, beginBalance, fecha, creditLine, description, type);
        cta.setId(rs.getLong("id"));
        return cta;
        
    }
    
    Cuenta obtenerCta (Long id) throws DAOException, SQLException {
         PreparedStatement stat = null;
         ResultSet rs= null;
         Cuenta cta = null;
        try{
            stat =conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()){
                cta = convertirCta(rs);
            }else{
                throw new DAOException("No se ha encontrado ese registro");
            }
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
     return cta;   
    }
    
     List<Cuenta> obtenerCtasClt (Long id) throws DAOException, SQLException {
         PreparedStatement stat = null;
         ResultSet rs= null;
         List<Cuenta> cuentas = new ArrayList<>();
        try{
            stat =conn.prepareStatement(CTASCLT);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            while (rs.next()){
                cuentas.add(convertirCta(rs));
            }
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
     return cuentas;   
    }
     
    void insertarCtaClte(Long c) throws DAOException, SQLException {
        //CREAR UNA CTA PARA UN CLTE: Primero creamos la cta y luego se la asociamos al clte
        //Para recojer el timestamp del sistema
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Cuenta nueva = new Cuenta(540.45, 45.45, timestamp, 200.34, "Cta de RV", 0);
           
        insertar(nueva);
        System.out.println("La Cuenta se ha creado con el idenficador " + nueva.getId());
            
        //Ahora creamos la relacción CTA-CLTE en la tabla correspondiente
        crear(c, nueva.getId());      
    }
    
    //Metodo que nos confirma que un clte existe en la tabla de clientes
    boolean existeCta (Long id) throws DAOException, SQLException {
        PreparedStatement stat = null;
        ResultSet rs= null;
        Cuenta c = null;
        boolean encontrado = false;
        try{
            stat =conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            encontrado = rs.next();
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
     return encontrado;   
    }
    
    //Le pasamos el num de cuenta para construir los objetos movs
    private Movimiento convertirMov(ResultSet rs, Long cta) throws SQLException {
        Double amount = rs.getDouble("amount");
        Double balance = rs.getDouble("balance");
        String description = rs.getString("description");
        Date fecha = rs.getDate("timestamp");
        Movimiento mov = new Movimiento(amount, balance, description, fecha, cta);
        mov.setId(rs.getLong("id"));
        return mov;  
    }
    
    //Obtener los movimientos para una cuenta
    List<Movimiento> obtenerMovsCta (Long id) throws DAOException, SQLException {
        PreparedStatement stat = null;
        ResultSet rs= null;
        List<Movimiento> movimientos = new ArrayList<>();
        try{
            stat =conn.prepareStatement(MOVSCTA);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            while (rs.next()){
                movimientos.add(convertirMov(rs, id));
            }
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
     return movimientos;   
    }
//*************************************************************************************
//*************************************************************************************
//MOVIMIENTOS
    
    public void insertar(Movimiento mov) throws DAOException, SQLException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try{
            //He añadido el stat.RETURN_GENERATED_KEYS para hacer posteriormente el getGeneratedKeys
            stat =conn.prepareStatement(INSERTmov, stat.RETURN_GENERATED_KEYS);
           
            stat.setDouble(1, mov.getAmount());
            stat.setDouble(2, mov.getBalance());
            stat.setString(3, mov.getDescription());
            stat.setTimestamp(4, (Timestamp) mov.getTimestamp());
            stat.setLong(5, mov.getAccount_id());
            
            if (stat.executeUpdate() == 0){
                throw new DAOException("Puede que no se haya guardado");
            }
             //Queremos obtener con que clave se ha guardado el movimiento
            rs =stat.getGeneratedKeys();
            if(rs.next()){
                mov.setId(rs.getLong(1));
            }else {
                throw new DAOException("No puedo asignar ID a esta Cuenta");
            }
        } catch (SQLException ex){
            throw new DAOException ("Error en SQL", ex);
        } finally{
            if (rs != null) {
                try {
                    rs.close();
                }catch (SQLException ex) {
                    new DAOException ("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex){
                    throw new DAOException("Error en SQL", ex);
                }
            }
                
        }
        
    }
       
    void insertarMovCta(Long cta) throws DAOException, SQLException {
        //CREAR UN MOVIMIENTO PARA UNA CUENTA DETERMINADA
        
        //Para recojer el timestamp del sistema
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Movimiento nuevo = new Movimiento(540.45, 45.45, "cta de la abuela", timestamp, cta);
           
        insertar(nuevo);
        System.out.println("El movimiento se ha creado con el identificador:  " + nuevo.getId());
    }
      
//*************************************************************************************    
} 



    