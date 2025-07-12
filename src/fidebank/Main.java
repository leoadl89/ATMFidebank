package fidebank;

import fidebank.controller.ATMThread;

public class Main {
    public static void main(String[] args) {
        ATMThread atmThread = new ATMThread();
        atmThread.start();
    }
}
