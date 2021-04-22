/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
 */

package logic;

import data.UsuarioDAO;

public class Service {
    private static Service instancia;
    private UsuarioDAO usuarios;
    
    private Service(){
        this.usuarios = new UsuarioDAO();
    }
    
    public static Service instancia(){
        if (instancia == null){
            instancia = new Service();
        }
        return instancia;
    }
    
    public Usuario buscarUsuario(String id) throws Exception{
        Usuario usuario = usuarios.readUsuario(id);
        return usuario;
    }
    
    public void insertarUsuario(Usuario usuario) throws Exception{
        usuarios.signin(usuario);
    }
}
