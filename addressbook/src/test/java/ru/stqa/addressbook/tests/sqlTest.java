package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.sql.*;

public class sqlTest {
    @Test
    public void test() throws SQLException {
        Connection conn = null;
        conn =
                DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                        "user=root&password=&serverTimezone=UTC");
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
        while (result.next()) {
            System.out.println(new GroupData().withGroupId(result.getInt("group_id"))
                    .withGroupName(result.getString("group_name"))
                    .withGroupFooter(result.getString("group_footer"))
                    .withGroupHeader(result.getString("group_footer")));
        }
        // Do something with the Connection
    }
}
