import javax.inject.Inject;

import com.acme.SantaClausService;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SantaClausTest {
    @Inject
    SantaClausService service;

    @Test
    public void testPersistGift() {

        //SantaClausService service = new SantaClausService();
        service.createGift("My gift!");
    }
}