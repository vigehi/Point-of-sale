

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Json {
	
	public static ResultSet RetrieveData() throws Exception {
        DriverManager.registerDriver(new org.postgresql.Driver());
        String mysqlUrl = "jdbc:postgresql://localhost/sales";
        Connection con = DriverManager.getConnection(mysqlUrl, "postgres", "100ee20gg");
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("Select * from items");
        return rs;
    }

    public static void main(String args[]) throws Exception {
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        ResultSet rs = RetrieveData();

        while(rs.next()) {
            JSONObject record = new JSONObject();
            record.put("Item", rs.getString("items"));
            record.put("Qty", rs.getString("qty"));
            record.put("Amount", rs.getString("amount"));
            array.add(record);
        }
        
        jsonObject.put("Sales_data", array);
        try {
            FileWriter file = new FileWriter("output.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created!");
    }

}
