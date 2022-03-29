package com.joantolos.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class JsonMarshallerTest {

    private SimpleDateFormat simpleDateFormat;
    private String simpleDateFormatJson;
    private JsonMarshaller<SimpleDateFormat> jsonMarshaller;

    @Before
    public void setUp() {
        this.jsonMarshaller = new JsonMarshaller<>(SimpleDateFormat.class);
        this.simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");
        this.simpleDateFormatJson = "{\"2DigitYearStart\":-982316389310,\"calendar\":-982316389310,\"dateFormatSymbols\":" +
                "{\"amPmStrings\":[\"AM\",\"PM\"],\"eras\":[\"BC\",\"AD\"],\"localPatternChars\":\"GyMdkHmsSEDFwWahKzZ\"," +
                "\"months\":[\"January\",\"February\"],\"shortMonths\":[\"Jan\",\"Feb\"],\"shortWeekdays\":[\"\",\"Sun\",\"Mon\"]," +
                "\"weekdays\":[\"\",\"Sunday\",\"Monday\"],\"zoneStrings\":[[\"America/Los_Angeles\",\"Pacific Standard Time\"," +
                "\"PST\",\"Pacific Daylight Time\",\"PDT\",\"Pacific Time\",\"PT\"]]},\"lenient\":true,\"timeZone\":\"Europe/Madrid\"}";
    }

    @Test
    public void objectToJsonTest() throws JsonProcessingException {
        String json = this.jsonMarshaller.marshall(simpleDateFormat);
        Assert.assertNotNull(json);
    }

    @Test
    public void jsonToObjectTest() throws IOException {
        SimpleDateFormat simpleDateFormat = this.jsonMarshaller.unmarshall(simpleDateFormatJson);
        Assert.assertNotNull(simpleDateFormat);
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldReturnErrorWhenMalformedJSON() throws IOException {
        this.jsonMarshaller.unmarshall("{\"name\":\"Some ");
    }

    @Test(expected = JsonProcessingException.class)
    public void shouldReturnErrorWhenMalformedObjectJSON() throws JsonProcessingException {
        JsonMarshaller<ClassThatJacksonCannotSerialize> jsonMarshaller = new JsonMarshaller<>(ClassThatJacksonCannotSerialize.class);
        jsonMarshaller.marshall(new ClassThatJacksonCannotSerialize());
    }

    @Test
    public void shouldMarshallToMap() throws JsonProcessingException {
        JsonMarshaller<Object> jsonMarshaller = new JsonMarshaller<>(Object.class);
        Map<String, Object> eventsJson = jsonMarshaller.unmarshallToMap("{\"hello\":\"world\"}");
        Assert.assertEquals("world", eventsJson.get("hello"));
    }

    @Test
    public void shouldMarshallList() throws JsonProcessingException {
        JsonMarshaller<Event> jsonMarshaller = new JsonMarshaller<>(Event.class);
        String eventsJson = jsonMarshaller.marshallList(List.of(new Event(123, "2022-01-31 00:00:00.0")));
        Assert.assertEquals("[{\"id\":123,\"date\":\"2022-01-31 00:00:00.0\"}]", eventsJson);
    }

    @Test
    public void shouldUnMarshallList() throws JsonProcessingException {
        JsonMarshaller<Event> jsonMarshaller = new JsonMarshaller<>(Event.class);
        List<Event> events = jsonMarshaller.unmarshallList("[{\"id\":123,\"date\":\"2022-01-31 00:00:00.0\"}]");
        Assert.assertEquals(Integer.valueOf(123), events.get(0).getId());
        Assert.assertEquals("2022-01-31 00:00:00.0", events.get(0).getDate());
    }

    private static class ClassThatJacksonCannotSerialize {
        private final ClassThatJacksonCannotSerialize self = this;

        @Override
        public String toString() {
            return self.getClass().getName();
        }
    }

    private static class Event {

        private Integer id;
        private String date;

        public Event(Integer id, String date) {
            this.id = id;
            this.date = date;
        }

        public Event() {
        }

        public Integer getId() {
            return id;
        }

        public String getDate() {
            return date;
        }

    }

}
