package com.groupdocs.search.examples;

import com.groupdocs.search.licenses.License;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SetLicenseFromFile {
    public static void run() {
        if (Files.exists(Paths.get(Utils.LicensePath))) {
            License license = new License();
            license.setLicense(Utils.LicensePath);

            System.out.println("License set successfully.");
        }
        else {
            System.out.println("\nWe do not ship any license with this example. " +
                              "\nVisit the GroupDocs site to obtain either a temporary or permanent license. " +
                              "\nLearn more about licensing at https://purchase.groupdocs.com/faqs/licensing. " +
                              "\nLear how to request temporary license at https://purchase.groupdocs.com/temporary-license.");
        }
        System.out.println();
    }
}
