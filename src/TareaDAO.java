import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TareaDAO {


    //Metodo para guardar las tareas
    public void guardarTarea(Tarea tarea){
        String sql = "INSERT INTO tareas (modulo, titulo, descripcion, prioridad) VALUES (?, ?, ?, ?)";


        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setString(1, tarea.getModulo());
            ps.setString(2, tarea.getTitulo());
            ps.setString(3, tarea.getDescripcion());
            ps.setString(4, tarea.getPrioridad());


            ps.executeUpdate();
            System.out.printf("¡Tarea guardada con éxito!\n");


        } catch (SQLException e){
            System.out.printf("Error al guardar:" + e.getMessage());
        }
    }
    public void actualizarProgreso (int idTarea, double nuevoProgreso) {
        String sql = "UPDATE tareas SET progreso = ?, estado = ? WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setDouble(1, nuevoProgreso);

            //Si llega a 100 se marcará como finalizada
            String nuevoEstado = (nuevoProgreso >= 100) ? "Finalizada" : "En Proceso";
            ps.setString(2, nuevoEstado);

            ps.setInt(3, idTarea);

            ps.executeUpdate();
            System.out.println("SS: Progeso actualizado correctamente");
        } catch (SQLException e){
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    //Metodo para consultar las tareas
    public List<Tarea> listarTareas(){
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM tareas";


        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {
                //Creacion de tarea con datos de la tabla
                Tarea t = new Tarea(
                        rs.getInt("id"),
                        rs.getString("modulo"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("prioridad"),
                        rs.getBigDecimal("progreso"),
                        rs.getString("estado")
                );


                //setear el progreso y estado
                lista.add(t);


            }
        }catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
        return lista;
    }


    public List<Tarea> obtenerTareasPorModulo(String nombreModulo) {
        List<Tarea> filtradas = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE modulo = ?";


        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)){


            ps.setString(1, nombreModulo);
            ResultSet rs = ps.executeQuery ();


            while (rs.next()){
                Tarea t = new Tarea(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("modulo"),
                        rs.getString("prioridad"),
                        rs.getBigDecimal("progreso"),
                        rs.getString("estado")
                );
                filtradas.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar: " + e.getMessage());
        }
        return filtradas;
    }

    public List<Tarea> obtenerTareaPorId(int id) {
        List<Tarea> filtradaPorId = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Tarea t = new Tarea(
                        rs.getInt("id"),
                        rs.getString("modulo"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("prioridad"),
                        rs.getBigDecimal("progreso"),
                        rs.getString("estado")
                );
                filtradaPorId.add(t);
                }
            } catch (SQLException e) {
            System.out.println("Error al filtrar: " + e.getMessage());
        }
        return filtradaPorId;
    }
}

