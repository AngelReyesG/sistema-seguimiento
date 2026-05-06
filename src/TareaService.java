public class TareaService {
    private TareaDAO dao = new TareaDAO();

    public boolean registrarNuevaTarea(Tarea t) {
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

        dao.guardarTarea(t);
        return true;
    }

}
