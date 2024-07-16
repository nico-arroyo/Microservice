import com.masmovil.developers.core.domain.model.developer.DeveloperName;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeveloperNameTest {

    @Test
    public void testDeveloperNameCreation() {
        DeveloperName name = DeveloperName.of("John", "Doe");

        assertEquals("John", name.getFirstName());
        assertEquals("Doe", name.getLastName());
    }

    @Test
    public void testDeveloperNameToJson() {
        DeveloperName name = DeveloperName.of("John", "Doe");

        JsonObject json = name.toJson();
        assertEquals("John", json.getString("first_name"));
        assertEquals("Doe", json.getString("last_name"));
    }

    @Test
    public void testCreateDeveloperNameFromJson() {
        JsonObject json = new JsonObject()
                .put("first_name", "John")
                .put("last_name", "Doe");

        DeveloperName name = DeveloperName.fromJson(json);
        assertEquals("John", name.getFirstName());
        assertEquals("Doe", name.getLastName());
    }

    @Test
    public void testCreateDeveloperNameWithNullFirstName() {
        IllegalArgumentException thrown = assertThrows(java.lang.IllegalArgumentException.class, () -> {
            DeveloperName.of(null, "Doe");
        });
        assertEquals("Name cannot be empty", thrown.getMessage());
    }

    @Test
    public void testDeveloperNameCreationWithEmptyString() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            DeveloperName.of("", "Doe");
        });
        assertEquals("Name cannot be empty", thrown.getMessage());
    }

    @Test
    public void testDeveloperNameEquality() {
        DeveloperName name = DeveloperName.of("John", "Doe");
        DeveloperName name2 = DeveloperName.of("John", "Doe");
        assertEquals(name, name2);
    }
}
