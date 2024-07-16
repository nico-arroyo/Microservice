import com.masmovil.developers.core.domain.model.developer.Developer;
import com.masmovil.developers.core.domain.model.developer.DeveloperId;
import com.masmovil.developers.core.domain.model.developer.DeveloperName;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeveloperTest {

    @Test
    public void testDeveloperCreation() {
        DeveloperName name = DeveloperName.of("John", "Doe");
        List<String> skills = List.of("Java", "Kotlin");
        DeveloperId id = DeveloperId.of("11a8d6a3-0470-4e2a-b8e9-85c469c4a220");

        Developer developer = Developer.builder()
                .withName(name)
                .withSkills(skills)
                .withId(id)
                .build();

        assertEquals("John", developer.getName().getFirstName());
        assertEquals("Doe", developer.getName().getLastName());
        assertEquals(skills, developer.getSkills());
        assertEquals(id, developer.getId());
    }

    @Test
    public void testDevelopertoJson() {
        DeveloperName name = DeveloperName.of("John", "Doe");
        List<String> skills = List.of("Java", "Kotlin");
        DeveloperId id = DeveloperId.of("11a8d6a3-0470-4e2a-b8e9-85c469c4a220");

        Developer developer = Developer.builder()
                .withName(name)
                .withSkills(skills)
                .withId(id)
                .build();

        JsonObject json = developer.toJson();
        assertEquals("John", json.getJsonObject("name").getString("first_name"));
        assertEquals("Doe", json.getJsonObject("name").getString("last_name"));
        assertEquals(skills, json.getJsonArray("skills").getList());
        assertEquals("11a8d6a3-0470-4e2a-b8e9-85c469c4a220", json.getString("id"));
    }

    @Test
    public void testCreateDeveloperFromJson() {
        JsonObject json = new JsonObject()
                .put("name", new JsonObject().put("first_name", "John").put("last_name", "Doe"))
                .put("skills", new JsonArray().add("Java").add("Kotlin"))
                .put("id", "11a8d6a3-0470-4e2a-b8e9-85c469c4a220");

        Developer developer = Developer.create(json);
        assertEquals("John", developer.getName().getFirstName());
        assertEquals("Doe", developer.getName().getLastName());
        assertEquals(List.of("Java", "Kotlin"), developer.getSkills());
        assertEquals("11a8d6a3-0470-4e2a-b8e9-85c469c4a220", developer.getId().toString());
    }

    @Test
    public void testDeveloperEquality() {
        DeveloperName name1 = DeveloperName.of("John", "Doe");
        List<String> skills1 = List.of("Java", "Kotlin");
        DeveloperId id1 = DeveloperId.of("11a8d6a3-0470-4e2a-b8e9-85c469c4a220");

        Developer developer1 = Developer.builder()
                .withName(name1)
                .withSkills(skills1)
                .withId(id1)
                .build();

        DeveloperName name2 = DeveloperName.of("D", "F");
        List<String> skills2 = List.of("d", "Python");
        DeveloperId id2 = DeveloperId.of("11a8d6a3-0470-4e2a-b8e9-85c469c4a220");

        Developer developer2 = Developer.builder()
                .withName(name2)
                .withSkills(skills2)
                .withId(id2)
                .build();

        assertEquals(developer1, developer2);
    }
}
