package com.portalsoup;

import com.portalsoup.ai.AbstractEntity;
import com.portalsoup.ai.entity.Hal;

import static voce.SpeechInterface.*;

/**
 * Created by julian on 4/18/2017.
 */
public class Main {

    public Main() {
        init("lib", true, true, "grammar", "grammar");
    }

    public static void main(String[] args) {
        Main program = new Main();

        program.run(Hal.init());
    }

    public void run(AbstractEntity entity) {

        boolean quit = false;
        String halResponse;
        while (!quit) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            while (getRecognizerQueueSize() > 0) {

                String recognizedString = popRecognizedString();
                if (recognizedString.toLowerCase().equals("exit")) {
                    quit = true;
                }

                halResponse = entity.ask(recognizedString);


                System.out.println("Hal says: " + halResponse);
                synthesize(halResponse);
            }
        }

        destroy();
        System.exit(0);
    }
}
