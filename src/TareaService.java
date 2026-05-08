import java.util.ArrayList;
import java.util.List;

public class TareaService {
    private TareaDAO dao = new TareaDAO();

    public boolean validarTarea(Tarea t) {
        //Titulo not null
        if (t.getTitulo() == null || t.getTitulo().trim().isEmpty()) {
            System.out.println("SS ERROR: FAVOR DE INGRESAR UN TÍTULO");
            return false;
        }

        //Descripción mínimo 10 caracteres
        if (t.getDescripcion().length() < 10) {
            System.out.println("SS ERROR: LA DESCRIPCIÓN ES DEMASIADO BREVE.");
            return false;
        }

        //Prioridad sea permitida
        String p = t.getPrioridad().toLowerCase();
        if (!p.equals("alta") && !p.equals("media") && !p.equals("baja")) {
            System.out.println("SS ERROR: PRIORIDAD NO VÁLIDA.");
            return false;
        }
        dao.guardarTarea(t);
        return true;
    }

    public List<Tarea> obtenerTareasPorModulo(String modulo) {
        if (modulo == null || modulo.isEmpty()) {
            System.out.println("SS Error: Debe especificar un módulo.");
            return new ArrayList<>();
        }
        return dao.obtenerTareasPorModulo(modulo);
    }

}
