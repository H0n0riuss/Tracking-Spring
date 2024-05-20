package io.github.honoriuss.tracking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrackingAspectHelperTest {
    private final String standardColName = "colName";
    private final TrackingProperties trackingProperties = new TrackingProperties();
    private final TrackingAspectHelper trackingAspectHelper = new TrackingAspectHelper(trackingProperties);

    public TrackingAspectHelperTest() {
        trackingProperties.setColumnName(standardColName);
    }

    private class TestClass {
        public String test;
    }

    @Test
    public void createMessageStringTest(){
        var expected = "{\"test\":\"test\"}";
        var args = new Object[]{"test"};
        var parameterNames = new String[]{"test"};
        var res = trackingAspectHelper.createMessageString(args, parameterNames);

        Assertions.assertNotNull(res);
        Assertions.assertEquals(expected.length(), res.length());
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void createMessageStringTest2(){
        var expected = "{\"colName\":\"test2\",\"test\":\"test\"}";
        var args = new Object[]{"test", "test2"};
        var parameterNames = new String[]{"test", standardColName};
        var res = trackingAspectHelper.createMessageString(args, parameterNames);

        Assertions.assertNotNull(res);
        Assertions.assertEquals(expected.length(), res.length());
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void createMessageStringTest3(){
        var expected = "{\"colName\":\"test2\",\"test\":\"test\",\"colName0\":\"test0\"}";
        var args = new Object[]{"test", "test2", "test0"};
        var parameterNames = new String[]{"test", standardColName, standardColName + 0};
        var res = trackingAspectHelper.createMessageString(args, parameterNames);

        Assertions.assertNotNull(res);
        Assertions.assertEquals(expected.length(), res.length());
        Assertions.assertEquals(expected, res);
    }

    @Test
    public void createMessageStringTest4(){
        var expected = new TestClass();
        expected.test = "gurr";
        var res = trackingAspectHelper.createMessageString(expected);

        Assertions.assertNotNull(res);
        Assertions.assertEquals("{\"test\":\"gurr\"}", res);
    }
}
