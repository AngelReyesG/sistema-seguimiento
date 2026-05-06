import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
    private static final String URL = "jdbc:mysql://be7t4qycb0fkeycnyyfj-mysql.services.clever-cloud.com:3306/be7t4qycb0fkeycnyyfj";
    private static final String USER = "ubzpwhy4pywwnmmh";
    private static final String PASS = "cKtozYe13hMiZVJJIdhN";

    private static Connection instancia = null;

    public static Connection conectar() throws SQLException {
        if (instancia == null || instancia.isClosed()) {
            instancia = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("SS: Conexión establecida con la nube");
        }
        return instancia;
    }
}
