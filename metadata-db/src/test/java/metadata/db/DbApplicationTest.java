package metadata.db.application;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import metadata.db.DbApplication;

public class DbApplicationTest {

    @Test
    public void bootstrapsApplication() {
        AnnotationConfigApplicationContext annotation = new AnnotationConfigApplicationContext(DbApplication.class);
        annotation.close();
    }
}