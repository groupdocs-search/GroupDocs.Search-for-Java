package com.groupdocs.search.examples.quick_start;

import com.groupdocs.search.licenses.Metered;

public class SetMeteredLicense {
    public static void run() throws Exception {
        String publicKey = "*****";
        String privateKey = "*****";

        Metered metered = new Metered();
        metered.setMeteredKey(publicKey, privateKey);

        System.out.println("License set successfully.");
    }
}
