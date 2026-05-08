import java.util.Scanner;
import java.util.List;


public class PruebaSS {
    public static void main(String[] args){
       /*Creación de tarea
       Tarea pendienteCostos = new Tarea("Costos", "Actualizar costo promedio de artículos", "Mostrar el ccosto promedio de los materiales registrados en el Almacén.", "Alta");
       Tarea pendienteJuridico = new Tarea("Jurídico", "Actualizar interfaz de registro de Contratos", "Permitir agregar comentarios a cada contrato y/o convenio", "Media");*/
        //Creación de objeto para guardar (DAO)

        Scanner lector = new Scanner(System.in);
        TareaDAO dao = new TareaDAO();
        TareaService service = new TareaService();
        int opcion = 0;


        do {
            System.out.println("\n--- SISTEMA DE SEGUIMIENTO ---");
            System.out.println("1. Registrar nueva tarea");
            System.out.println("2. Ver todas las tareas");
            System.out.println("3. Ver tareas por módulo");
            System.out.println("4. Actualizar progreso de una tarea");
            System.out.println("5. Consultar una tarea");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = lector.nextInt();
            lector.nextLine();


            switch (opcion) {
                case 1:
                    System.out.println("\n--- NUEVA TAREA ---");
                    System.out.println("Módulo de actividad: ");
                    for (int i = 0; i < Modulo.values().length; i++) {
                        System.out.println((i + 1) + ". " + Modulo.values()[i]);
                    }
                    int opcion = lector.nextInt();
                    Modulo seleccionado = Modulo.values()[opcion - 1];
                    dao.obtenerTareasPorModulo(seleccionado.name());

                    System.out.println("Título: ");
                    String titulo = lector.nextLine();

                    System.out.println("Descripción: ");
                    String descripcion = lector.nextLine();

                    System.out.println("Prioridad (Alta/Media/Baja): ");
                    String prioridad = lector.nextLine();

                    Tarea nueva = new Tarea(modulo, titulo, descripcion, prioridad);
                    service.registrarNuevaTarea(nueva);
                    break;


                case 2:
                    System.out.println("\n--- LISTADO GENERAL ---");
                    List<Tarea> todas = dao.listarTareas();
                    for (Tarea t : todas) {
                        System.out.println(t);
                    }
                    break;


                case 3:
                    System.out.println("\n¿Qué módulo desea consultar?: ");
                    String modBusqueda = lector.nextLine();
                    List<Tarea> filtradas = dao.obtenerTareasPorModulo(modBusqueda);


                    if(filtradas.isEmpty()) {
                        System.out.println("No hay tareas en el módulo seleccionado");
                    } else {
                        for (Tarea t : filtradas) {
                            System.out.println(t);
                        }
                    }
                    break;


                case 4:
                    System.out.println("Introduce el ID de la tarea a actualizar: ");
                    int id = lector.nextInt();
                    List<Tarea> filtradasPorId = dao.obtenerTareaPorId(id);
                    if(filtradasPorId.isEmpty()) {
                        System.out.println("No existe el registro, favor de ingresar uno válido.");
                    } else {
                        for (Tarea t : filtradasPorId) {
                            System.out.println(t);
                        }
                    }
                    System.out.println("Nuevo porcentaje de progreso (0-100): ");
                    double avance = lector.nextDouble();

                    dao.actualizarProgreso(id, avance);
                    break;
                case 5:
                    System.out.print("¿Que tarea deseas consultar: ");
                    id = lector.nextInt();
                    filtradasPorId = dao.obtenerTareaPorId(id);
                    if(filtradasPorId.isEmpty()) {
                        System.out.println("No existe el registro, favor de ingresar uno válido");
                    } else {
                        for (Tarea t : filtradasPorId) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 6);


        lector.close();

    }


}
