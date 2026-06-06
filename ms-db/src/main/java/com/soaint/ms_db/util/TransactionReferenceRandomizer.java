package com.soaint.ms_db.util;

import java.util.concurrent.ThreadLocalRandom;

public class TransactionReferenceRandomizer {

    /**
     * Genera numero aleatorios de 100000 a 999999
     * @return numero aleatorio
     */
    public static int getInt() {
        return ThreadLocalRandom.current().nextInt(900000) + 100000;
    }
}
