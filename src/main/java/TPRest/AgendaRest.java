package TPRest;

import TPControlador.GestorAgenda;
import TPModelo.Agenda;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("agenda")
public class AgendaRest {

    @GET
    @Path("/resultados")
    @Produces(MediaType.TEXT_HTML)
    public String getHTMLAgenda() {
        //http://localhost:8080/TP8Programacion3/rest/resultados
        GestorAgenda gestor = new GestorAgenda();
        List<Agenda> lista = new ArrayList<Agenda>();
        lista = gestor.consultarHQL();

        String resultado = "";
        for (Agenda agenda : lista) {
            resultado
                    += "<tr>"
                    + "<td>" + agenda.getId() + "</td>"
                    + "<td>" + agenda.getNombre() + "</td>"
                    + "<td>" + agenda.getApellido() + "</td>"
                    + "<td>" + agenda.getTelefono() + "</td>"
                    + "<td>" + agenda.getEmail() + "</td>"
                    + "<td>" + agenda.getDomicilio() + "</td>"
                    + "</tr>";
        }

        return "<table border=\"1\">"
                + "<tr>"
                + "<td>ID</td>"
                + "<td>Nombre</td>"
                + "<td>Apellido</td>"
                + "<td>Telefono</td>"
                + "<td>Email</td>"
                + "<td>Domicilio</td>"
                + "</tr>"
                + resultado
                + "</table>"
                + "<form action=\"http://localhost:8080/TP8Programacion3/rest/agenda/eliminar\" method=\"POST\">"
                + "<br><br><br>"
                + "<label for=\"id\">ID: </label><input type=\"text\" name=\"id\" id=\"id\"/><br>"
                + "<button type=\"submit\">Eliminar</button>"
                + "</form>";
    }

    @POST
    @Path("/agregar")
    @Produces(MediaType.TEXT_HTML)
    public String crearRegistro(@FormParam("id") long id, @FormParam("nombre") String nombre,
            @FormParam("apellido") String apellido, @FormParam("telefono") int telefono, 
            @FormParam("email") String email, @FormParam("domicilio") String domicilio) {
        
        GestorAgenda gestor = new GestorAgenda();
        
        Agenda registro = new Agenda();
        registro.setId(id);
        registro.setNombre(nombre);
        registro.setApellido(apellido);
        registro.setTelefono(telefono);
        registro.setEmail(email);
        registro.setDomicilio(domicilio);

        gestor.insertarHQL(registro);
        
        return getHTMLAgenda();
    }
    
    @POST
    @Path("/eliminar")
    @Produces(MediaType.TEXT_HTML)
    public String eliminarRegistro(@FormParam("id") long id) {
        
        GestorAgenda gestor = new GestorAgenda();
        List<Agenda> lista = new ArrayList<Agenda>();
        lista = gestor.consultarHQL();
        Agenda registroABorrar = null;
        for (Agenda registro : lista) {
            if (registro.getId() == id) {
                registroABorrar = registro;
            }
        }

        gestor.eliminarHQL(registroABorrar);
        
        return getHTMLAgenda();
    }
}
