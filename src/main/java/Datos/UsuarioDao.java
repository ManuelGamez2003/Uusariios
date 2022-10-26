/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import static Datos.Conexion.close;
import static Datos.Conexion.getConnection;
import Dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alumno Mañana
 */
public class UsuarioDao {
    private static final String SQL_SELECT = "SELECT * from usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (nombre,contraseña)values(?,?)";
    private static final String SQL_UPDATE = "UPDATE usuario set nombre=? , contraseña=? where idusuario=?";
    private static final String SQL_DELETE = "DELETE from usuario where idusuario=?";

    //Lista todas las personas de nuestro sistema
    public List<Usuario> seleccionar() throws SQLException {

        //iNICIALIZO MIS variables
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();

        conn = getConnection();
        ps = conn.prepareStatement(SQL_SELECT);
        rs = ps.executeQuery();

        while (rs.next()) {
            int idPersona = rs.getInt("idusuario");
            String Nombre = rs.getString("nombre");
            String contrasenya = rs.getString("contraseña");
           

            usuarios.add(new Usuario(idPersona, Nombre, contrasenya));
        }
        close(rs);
        close(ps);
        close(conn);

        return usuarios;
    }

    //Metodo que inserta la persona
    public int insertar(Usuario persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            //1. Establecemos la conexion
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getContrasenya());
            

            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;

    }
    public int modificar(Usuario persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            //1. Establecemos la conexion
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
           
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getContrasenya());
             ps.setInt(3, persona.getIdusuario());

            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;

    }
    public int borrar(Usuario persona) {
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;

        try {
            //1. Establecemos la conexion
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
           
            ps.setInt(1, persona.getIdusuario());
           

            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(ps);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;

    }
}
