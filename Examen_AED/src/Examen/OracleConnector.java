package Examen;

import java.sql.*;

public class OracleConnector {

    private final String DDBB_DRIVER = "oracle.jdbc.driver.OracleDriver";

    private final String connectionLocation;
    private final String username;
    private final String password;
    private String[] COLUMN_DATA_TYPE;

    private Connection connection;

    public OracleConnector(String location, String user, String password){
        this.username = user;
        this.password = password;
        //localhost or ip (192.168.192.75:1521)
        this.connectionLocation = "jdbc:oracle:thin:@"+location+"/XE";

        initConnector();
    }

    private void initConnector() {
        try {
            //Carga el driver
            Class.forName(DDBB_DRIVER);

            //Establece la conexión con la base de datos indicada
            connection = DriverManager.getConnection(connectionLocation,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Para ejecutar consultas como select...
    public void executeQuery(String query){

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            COLUMN_DATA_TYPE = new String[resultSetMetaData.getColumnCount()];

            printCols(resultSetMetaData);
            printDataContent(resultSet);

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Para sentencias como update, delete, update...
    public void executeUpdate(String query){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Ejecuta un procedimiento con el nombre y los valores su parámetro que le pasemos.
    public void executeProcedure(String procedureName,String[] paramData){

        int paramAmount = paramData.length;

        try {
            //Preparamos el procedimiento.
            CallableStatement callableStatement = connection.prepareCall(getProcedureQuery(procedureName, paramAmount));

            //Le pasamos los parámetros del procedimiento. (Empieza en 1)
            for (int i = 1; i <= paramAmount; i++) {
                callableStatement.setString(i, paramData[i - 1]);
            }

            //Ejecuta el procedimiento.
            callableStatement.execute();
        }catch (SQLException ignored){}
    }

    //Obtenemos la sentencia del procedimiento en base a su nombre y el número de parámetros.
    private String getProcedureQuery(String procedureName, int paramAmount) {
        //La estructura es: {call NOMBRE_PROCEDIMIENTO(?,?)}, el número de '?'
        //dependerá del número de parámetros.
        StringBuilder query = new StringBuilder("{call "+ procedureName +"()}");

        //Nos situamos justo en la apertura del paréntesis.
        int pos = query.indexOf("(")+1;

        //Añadimos un '?' por cada parámetro.
        for (int i = 0; i< paramAmount; i++){
            if (!(i== paramAmount -1)) {
                query.insert(pos,"?,");
                pos+=2;
            }else {
                query.insert(pos,"?");
            }
        }

        return query.toString();
    }

    //Printea el nombre de las columnas de la tabla.
    private void printCols(ResultSetMetaData resultSetMetaData) throws SQLException {
        for (int i = 1;i<=COLUMN_DATA_TYPE.length;i++) {
            //.getColumnName() -> devuelve el nombre de la columna
            System.out.printf("%-20s", resultSetMetaData.getColumnName(i));
            //.getColumnTypeName() -> devuelve el nombre del tipo de dato de la columna
            COLUMN_DATA_TYPE[i-1] = resultSetMetaData.getColumnTypeName(i);
        }
        System.out.println();
    }

    //Printea todos los datos de una tabla.
    private void printDataContent(ResultSet resultSet) throws SQLException {
        //Mientras sigan existiendo registros/filas...
        while (resultSet.next()) {
            for (int i = 1;i<=COLUMN_DATA_TYPE.length;i++) {
                //readData() método personalizado
                System.out.printf("%-20s",readData(resultSet,COLUMN_DATA_TYPE[i-1],i));
            }
            System.out.println();
        }
    }

    //Dependiendo del tipo de dato, lee con su correspondiente método y devuelve el contenido leído.
    private String readData(ResultSet resultSet,String dataType,int columPos) throws SQLException {
        String dataValue="";
        switch (dataType) {
            case "NUMBER" ->    dataValue = String.valueOf(resultSet.getInt(columPos));
            case "VARCHAR2", "DATE" ->    dataValue = resultSet.getString(columPos);
            default -> System.out.println(dataType);
        }

        return dataValue;
    }

    //Cierra la conexion.
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
