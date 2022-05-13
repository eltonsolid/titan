package br.com.elementi.core.xml;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        new Main().main();
    }

    private void main() {
		/*Parameter parameter = Parameter.load();
        DIN din = Soap.create(DIN.class, parameter.dinwebservice());
		FPB fpb = Soap.create(FPB.class, parameter.fpbwebservice());
		//ServerFPB serverFPB = Soap.server(new ServerFPB(fpb), parameter.fpbWebServicePublisher());

		Consumer consumer = new Consumer();
		Timer timer = new Timer();
		timer.schedule();

		consumer.process();
*/

		Consumer consumer = new Consumer(new SincadService());



    }


}
