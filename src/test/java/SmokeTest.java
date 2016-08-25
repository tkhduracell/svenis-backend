import static com.svenis.model.svenis.tables.Questions.QUESTIONS;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.junit.Test;

public class SmokeTest {

  public static final String H2_DATABASE = "jdbc:h2:./target/svenisdb";

  @Test
  public void testQueryingAfterMigration() throws Exception {
    try (Connection c = DriverManager.getConnection(H2_DATABASE, "sa", "")) {
      Result<?> result =
          DSL.using(c)
              .select(QUESTIONS.APP_NAME)
              .from(QUESTIONS)
              .fetch()
          ;

      assertEquals(1, result.size());
      assertEquals(asList("my-app"), result.getValues(QUESTIONS.APP_NAME));
    }
  }
}
