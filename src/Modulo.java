public enum Modulo {
    ABASTECIMIENTOS, ALMACEN, COMERCIAL, CONTABILIDAD,
    COSTOS, JURIDICO, NOMINA, PERSONAL, PRESUPUESTOS;

    public String getNombre() {
        return name().substring(0, 1) + name().substring(1).toLowerCase();
    }
}
