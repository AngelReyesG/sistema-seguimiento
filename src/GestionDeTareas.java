import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GestionDeTareas {
    private List<Tarea> todasLasTareas;


    public GestionDeTareas() {
        this.todasLasTareas = new ArrayList<>();
    }


    public void registrarTarea(Tarea nueva) {
        todasLasTareas.add(nueva);
        System.out.println("Sistema: Tarea guardada exitosamente.");
    }


    //Filtrado
    public List<Tarea> obtenerTareasPorPrioridad(String prioridad) {
        return todasLasTareas.stream()
                .filter(t -> t.getPrioridad().equalsIgnoreCase(prioridad))
                .collect(Collectors.toList());
    }
}
