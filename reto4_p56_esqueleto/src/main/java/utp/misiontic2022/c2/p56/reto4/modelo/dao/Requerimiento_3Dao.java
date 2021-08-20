package utp.misiontic2022.c2.p56.reto4.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utp.misiontic2022.c2.p56.reto4.modelo.vo.Requerimiento_3;
import utp.misiontic2022.c2.p56.reto4.util.JDBCUtilities;

public class Requerimiento_3Dao {
    public ArrayList<Requerimiento_3> requerimiento3() throws SQLException {
        ArrayList<Requerimiento_3> resultados = new ArrayList<>();
        
        String queryConstructora = "SELECT p.Constructora, SUM(l.salario) AS \"Costo de líderes\" " +
                                   "FROM Proyecto p JOIN Lider l ON p.ID_Lider = l.ID_Lider " +
                                   "GROUP BY p.Constructora HAVING \"Costo de líderes\" > 40000000 LIMIT 100";
        
        JDBCUtilities conexion = new JDBCUtilities();
        ResultSet resultadoConstructoras = conexion.ejecutarConsulta(queryConstructora);
        
        while (resultadoConstructoras.next()) {
            String constructora = resultadoConstructoras.getString("Constructora");
            int costoLideres = resultadoConstructoras.getInt("Costo de líderes");
            Requerimiento_3 filaResultado = new Requerimiento_3(constructora, costoLideres);
            resultados.add(filaResultado);
        }

        return resultados;
    }
}