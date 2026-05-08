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

            ps.setString(1, tarea.getModulo().name());
            ps.setString(2, tarea.getTitulo());
            ps.setString(3, tarea.getDescripcion());
            ps.setString(4, tarea.getPrioridad());

            ps.executeUpdate();
            System.out.printf("¡Tarea guardada con éxito!\n");

        } catch (SQLException e){
            System.out.printf("Error al guardar:" + e.getMessage());
        }
    }

    //Metodo para actualizar progreso de tareas
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
        String sql = "SELECT * FROM tareas" + " ORDER BY FIELD(prioridad, 'Alta', 'Media', 'Baja'), fecha_creacion DESC";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombreModulo = rs.getString("modulo");
                Modulo moduloEnum = Modulo.valueOf(nombreModulo.toUpperCase());
                //Creacion de tarea con datos de la tabla
                Tarea t = new Tarea(
                        rs.getInt("id"),
                        moduloEnum,
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("prioridad"),
                        rs.getBigDecimal("progreso"),
                        rs.getString("estado"),
                        rs.getString("fecha_creacion")
                );

                //setear el progreso y estado
                lista.add(t);

            }
        }catch (SQLException e) {
            System.out.println("Error al consultar: " + e.getMessage());
        }
        return lista;
    }

    //Metodo para consultar tareas por módulo
    public List<Tarea> obtenerTareasPorModulo(String moduloBusqueda) {
        List<Tarea> filtradas = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE modulo = ?" + " ORDER BY FIELD(prioridad 'Alta', 'Media', 'Baja'), fecha_creacion DESC";

        try (Connection con = ConexionDB.conectar();
            PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery ();

            while (rs.next()){
                String nombreModulo = rs.getString("modulo");
                Modulo moduloEnum = Modulo.valueOf(nombreModulo.toUpperCase());

                Tarea t = new Tarea(
                        rs.getInt("id"),
                        moduloEnum,
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("prioridad"),
                        rs.getBigDecimal("progreso"),
                        rs.getString("estado"),
                        rs.getString("fecha_Creacion")
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
                String nombreModulo = rs.getString("modulo");
                Modulo moduloenum = Modulo.valueOf(nombreModulo.toUpperCase());

                Tarea t = new Tarea(
                        rs.getInt("id"),
                        moduloenum,
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("prioridad"),
                        rs.getBigDecimal("progreso"),
                        rs.getString("estado"),
                        rs.getString("fecha_Creacion")
                );
                filtradaPorId.add(t);
                }
            } catch (SQLException e) {
            System.out.println("Error al filtrar: " + e.getMessage());
        }
        return filtradaPorId;
    }
}

