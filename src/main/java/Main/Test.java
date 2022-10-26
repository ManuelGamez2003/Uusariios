/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import Datos.UsuarioDao;
import Dominio.Usuario;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Alumno Mañana
 */
public class Test {
    
    public static void main(String[] args) {
        UsuarioDao personaDao=new UsuarioDao();
        Usuario usuario1 = new Usuario(2,"aaa", "Lama");
        
       // personaDao.insertar(usuario1);
          //personaDao.borrar(usuario1);
            personaDao.modificar(usuario1);

            try{
            List<Usuario> usuarios=personaDao.seleccionar();
            usuarios.forEach(usuario-> {
                System.out.println("usuario = "+usuario);
            
            });
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
}

