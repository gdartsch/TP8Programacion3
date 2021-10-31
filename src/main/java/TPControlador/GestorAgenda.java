package TPControlador;

import TPModelo.Agenda;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GestorAgenda extends Gestor {
    BaseDeDatos bd = new BaseDeDatos();
    Connection conexion = bd.estableceConexion();

    public GestorAgenda() {
        sesion = ConfigHibernate.openSession();
    }
    
    public void cerrarConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarHQL(Object o) {
        try {
            this.guardar(o);
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void eliminarHQL(Object o){
        try{
            this.eliminar(o);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Agenda> consultarHQL(){
        List<Agenda> lista = new ArrayList<Agenda>();
        try{
            lista = this.listar(Agenda.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
