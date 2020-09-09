package com.totalcross;

import totalcross.TotalCrossApplication;

public class RunMedicalAppApplication {
    public static void main(String [] args) {
        TotalCrossApplication.run(MedicalApp.class, "/r", "5443444B5AAEEB90306B00E4", "/scr", "960x640");
    }
}
