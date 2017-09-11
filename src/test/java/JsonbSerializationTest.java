import org.junit.Assert;
import org.junit.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonbSerializationTest {

    @Test
    public void test() {
        Ghost ghost = new Ghost();
        ghost.setDateTime(LocalDateTime.now());

        Map<String, Object> properties = new HashMap<>();
        ghost.setProperties(properties);
        properties.put("testtest", LocalDateTime.now());

        JsonbConfig config = new JsonbConfig()
                .withDateFormat("yyyy", Locale.ENGLISH);

        Jsonb jsonb = JsonbBuilder.create(config);

        String json = jsonb.toJson(ghost);

        System.out.println(json);
        Pattern pattern = Pattern.compile("\\d+:\\d+:\\d+");
        Matcher hoursMatcher = pattern.matcher(json);
        Assert.assertFalse("Should not contain hours", hoursMatcher.find());
    }
}
