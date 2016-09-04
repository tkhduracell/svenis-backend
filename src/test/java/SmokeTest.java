
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;

import com.svenis.db.model.svenis.tables.Sessions;
import com.svenis.db.model.svenis.tables.records.SessionsRecord;
import org.jooq.impl.DSL;
import org.junit.Test;

public class SmokeTest {

  private static final String H2_DATABASE = "jdbc:h2:./db/svenis.data.db";

  @Test
  public void testQueryingAfterMigration() throws Exception {
    try (Connection c = DriverManager.getConnection(H2_DATABASE, "sa", "")) {
      SessionsRecord[] result =
          DSL.using(c)
              .selectFrom(Sessions.SESSIONS)
              .fetchArray();
      assertEquals(1, result.length);
      assertEquals("my-app", result[0].getValue(Sessions.SESSIONS.APP_NAME));
    }
  }
}
