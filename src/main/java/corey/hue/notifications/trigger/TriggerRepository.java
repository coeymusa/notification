package corey.hue.notifications.trigger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Service;
import corey.hue.notifications.model.Trigger;

@Service
public class TriggerRepository {

  private static String dbURL = "jdbc:derby://localhost:1527/HueDB;create=true";
  private static String tableName = "triggers";
  // jdbc Connection
  private static Connection conn = null;
  private static Statement statement = null;


  private static void createConnection() {
      try{
          Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
          //Get a connection
          conn = DriverManager.getConnection(dbURL); 
         // statement = conn.createStatement();
         //statement.execute("CREATE TABLE " + tableName + " (id INTEGER not NULL, name VARCHAR(255) , effect VARCHAR(255),colour VARCHAR(255))");
         /// statement.close();
      }
      catch (Exception except){
          except.printStackTrace();
      }
  }

  public void addTrigger(Trigger trigger){
    try{
      createConnection();
      statement = conn.createStatement();
      String sql = "insert into " + tableName + " values (" +
          "1, '" + trigger.getName() + "','" + trigger.getEffect() + "','" + trigger.getColour() +"')";
      statement.execute(sql);
      statement.close();
    }
    catch (SQLException sqlExcept){
      sqlExcept.printStackTrace();
    }
  }

  public void getTriggers(){
    Trigger trigger = new Trigger();
    try {
      statement = conn.createStatement();
      ResultSet results = statement.executeQuery("select * from " + tableName);
      ResultSetMetaData rsmd = results.getMetaData();
      int numberCols = rsmd.getColumnCount();
      for (int i=1; i<=numberCols; i++){
        System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
      }

      System.out.println("\n-------------------------------------------------");

      while(results.next()) {

        String name = results.getString(1);
        String effect = results.getString(2);
        String colour = results.getString(3);
//        trigger.setColor(colour);
//        trigger.setEffect(effect);
//        trigger.get
        System.out.println(name + "\t\t" + effect + "\t\t" + colour);
      }
      results.close();
      statement.close();
    }
    catch (SQLException sqlExcept){
      sqlExcept.printStackTrace();
    }
  }

  private static void shutdown()
  {
    try{
      if (statement != null){
        statement.close();
      }
      if (conn != null){
        DriverManager.getConnection(dbURL + ";shutdown=true");
        conn.close();
      }           
    }
    catch (SQLException sqlExcept) {
    }
  }
  
}


