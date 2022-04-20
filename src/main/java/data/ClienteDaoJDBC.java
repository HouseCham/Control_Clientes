package data;

import domain.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteDaoJDBC {

    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + "FROM cliente";
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo "
            + "FROM 'cliente' WHERE id_cliente=?";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre,apellido,email,telefono,saldo) "
            + "VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE cliente "
            + "SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente=?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";

    public List<Cliente> listar() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            connection = Conexion.getConnection();
            statement = connection.prepareStatement(SQL_SELECT);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idCliente = resultSet.getInt("id_cliente");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                double saldo = resultSet.getDouble("saldo");

                cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultSet);
            Conexion.close(statement);
            Conexion.close(connection);
        }
        return clientes;
    }

    public Cliente encontrar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();
            rs.absolute(1); //nos posicionamos en el primer registro devuelto

            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellido(rs.getString("apellido"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setSaldo(rs.getDouble("saldo"));

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
    
    public int insertar(Cliente cliente){
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;
        try {
            connection = Conexion.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefono());
            statement.setDouble(5, cliente.getSaldo());
            
            rows = statement.executeUpdate();   // regresa cuantos registros se han hecho
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(statement);
            Conexion.close(connection);
        }
        return rows;
    }
    
    public int actualizar(Cliente cliente){
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;

        try {
            connection = Conexion.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefono());
            statement.setDouble(5, cliente.getSaldo());
            statement.setInt(6, cliente.getIdCliente());
            
            rows = statement.executeUpdate();   // regresa cuantos registros se han hecho
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(statement);
            Conexion.close(connection);
        }
        return rows;
    }
    
    public int eliminar(Cliente cliente){
        Connection connection = null;
        PreparedStatement statement = null;
        int rows = 0;

        try {
            connection = Conexion.getConnection();
            statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, cliente.getIdCliente());
            
            rows = statement.executeUpdate();   // regresa cuantos registros se han hecho
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(statement);
            Conexion.close(connection);
        }
        return rows;
    }
}
