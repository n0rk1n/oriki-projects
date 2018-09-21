package cn.oriki.commons.loader;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesConfigLoaderTest {

    private PropertiesConfigLoader configLoader;

    @Before
    public void before() {
        this.configLoader = new PropertiesConfigLoader("test.properties");
    }

    @Test
    public void getProperty() {
        Object s = this.configLoader.getProperty("string-key");
        assertEquals("value", s);

        Object s1 = this.configLoader.getProperty("unknown-key");
        assertNull(s1);
    }

    @Test
    public void getProperty1() {
        Object s = this.configLoader.getProperty("string-key", "other-value");
        assertEquals("value", s);

        Object s1 = this.configLoader.getProperty("unknown-key", "other-value");
        assertEquals("other-value", s1);
    }

    @Test
    public void getBoolean() {
        Boolean b = this.configLoader.getBoolean("boolean-key");
        assertTrue(b);

        Boolean b1 = this.configLoader.getBoolean("unknown-key");
        assertNull(b1);
    }

    @Test
    public void getBoolean1() {
        boolean b = this.configLoader.getBoolean("boolean-key", false);
        assertTrue(b);

        boolean b1 = this.configLoader.getBoolean("unknown-key", false);
        assertFalse(b1);
    }

}