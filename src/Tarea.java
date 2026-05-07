import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Tarea{

    //Atributos de la tarea
    private String modulo;
    private String titulo;
    private String descripcion;
    private String prioridad;
    private BigDecimal progreso;
    private String estado;
    private List<String> actividades;
    private int id;
    private String fechaCreacion;

    //Constructor para registros
    public Tarea (String modulo, String titulo, String descripcion, String prioridad) {
        this.id = id;
        this.modulo = modulo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.progreso = BigDecimal.ZERO;
        this.estado = "En proceso";
        this.actividades = new ArrayList<>();
    }

    //Constructor para consultas
    public Tarea(int id, String modulo, String titulo, String descripcion, String prioridad, BigDecimal progreso, String estado, String fecha_Creacion){
        this.id = id;
        this.modulo = modulo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.progreso = progreso;
        this.estado = estado;
        this.fechaCreacion = fecha_Creacion;

    }
    //Metódos de lógica
    public void agregarActividad(String actividad, BigDecimal incrementoProgreso) {
        this.actividades.add(actividad);
        this.progreso = this.progreso.add(incrementoProgreso);
        if (this.progreso.compareTo(new BigDecimal("100"))>=0) {
            this.progreso = new BigDecimal("100");
            this.estado = "Concluida";
        }
    }




    //Getters y Setters
    public String getModulo () { return modulo; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getEstado() { return estado; }
    public BigDecimal getProgreso() { return progreso; }
    public String getDescripcion () { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getPrioridad() { return prioridad; }
    public List<String> getActividades() { return actividades; }
    public String fechaCreacion() { return fechaCreacion; }

@Override
public String toString(){
    return String.format (
            "-------------------------------------\n" +
                    "ID: %d | Módulo: [%s]\n" +
                    "Título: %s\n" +
                    "Prioridad: %s | Progreso: %.2f%%\n" +
                    "Estado: %s\n" +
                    "Tarea creada el: %s",
            id, modulo, titulo,prioridad, progreso, estado, fechaCreacion
    );
}
}


