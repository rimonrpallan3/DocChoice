package com.glen.filetest.activity.Landing.presenter;

public class TestSingleton {
    private static final TestSingleton ourInstance = new TestSingleton();

    public static TestSingleton getInstance() {
        return ourInstance;
    }

    private TestSingleton() {
    }
}
