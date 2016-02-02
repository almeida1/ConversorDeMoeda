package com.example.edson.conversordemoeda;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by edson on 01/02/2016.
 */
public class TesteDaActivityConversorDeMoeda extends ActivityInstrumentationTestCase2<ConversorDeMoeda> {
    private ConversorDeMoeda mActivity;
    private String mKey;
    public TesteDaActivityConversorDeMoeda(){
        super(ConversorDeMoeda.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        //obtem a activity sob teste
        //mActivity = getActivity();
        mActivity = new ConversorDeMoeda();
        mKey = mActivity.getKey("open_key"); //getKey foi modificado para public para permitir o teste

    }
    public void testInteger() throws Exception, Throwable {
        assertEquals("teste", mKey);
    }
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
