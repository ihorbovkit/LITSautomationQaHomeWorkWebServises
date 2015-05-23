package timezone.data.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import dataBase.connector.JDBCConnector;
import parser.dto.timezone.TimeZone;

public class TimeZoneServiceData {

	public TimeZone prepareTimeZoneObjects() {
		JDBCConnector db = new JDBCConnector();
		ResultSet rs = db.executeQuery("SELECT * from timezone");
		TimeZone timeZone = null;
		try {
			while (rs.next()) {
				timeZone = new TimeZone(rs.getDouble("dstOffset"),
						rs.getDouble("rawOffset"), rs.getString("status"),
						rs.getString("timeZoneId"),
						rs.getString("timeZoneName"));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeDBConnection();
		return timeZone;
	}
}
