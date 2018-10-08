package corey.hue.notifications.trigger;

import java.sql.SQLException;

public class DerbyHelper {

  public static boolean tableAlreadyExists(SQLException e) {
    if(e.getSQLState().equals("X0Y32")){
      return true;
    }
    return false;
  }
}
