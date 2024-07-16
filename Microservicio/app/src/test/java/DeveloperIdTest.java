import com.masmovil.developers.core.domain.model.developer.DeveloperId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeveloperIdTest {

    @Test
    public void testDeveloperIdCreation() {
        DeveloperId id = new DeveloperId(DeveloperId.of("11a8d6a3-0470-4e2a-b8e9-85c469c4a220").getId());

        assertEquals("11a8d6a3-0470-4e2a-b8e9-85c469c4a220", id.getId().toString());
    }

    @Test
    public void testDeveloperIdEquality() {
        DeveloperId id1 = new DeveloperId(DeveloperId.of("11a8d6a3-0470-4e2a-b8e9-85c469c4a220").getId());
        DeveloperId id2 = new DeveloperId(DeveloperId.of("11a8d6a3-0470-4e2a-b8e9-85c469c4a220").getId());
        DeveloperId id3 = new DeveloperId(DeveloperId.of("dc614046-8941-40f9-a8a8-3992aeee300b").getId());

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
    }
}
