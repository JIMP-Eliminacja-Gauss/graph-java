package com.company.graphjava;

public class MyExceptions{
    public static class FileFormatException extends Exception {
        public FileFormatException(String msg) {
            super(msg);
        }
    }

}
