package PL;

import BL.Action;
import BL.MessageCallback;

import java.util.Scanner;

public class CLI implements MessageCallback {

        @Override
        public void send(String message) {
            System.out.println(message);
        }
    }

