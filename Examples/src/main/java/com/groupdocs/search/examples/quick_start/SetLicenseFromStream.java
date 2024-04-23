package com.groupdocs.search.examples.quick_start;

import com.groupdocs.search.licenses.License;
import com.groupdocs.search.examples.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SetLicenseFromStream {
    public static void run() throws FileNotFoundException, Exception {
        if (Files.exists(Paths.get(Utils.LicensePath))) {
            InputStream stream = new FileInputStream(Utils.LicensePath);
            License license = new License();
            license.setLicense(stream);
            System.out.println("License set successfully.");
        } else {
            System.out.println("\nWe do not ship any license with this example. " +
                    "\nVisit the GroupDocs site to obtain either a temporary or permanent license. " +
                    "\nLearn more about licensing at https://purchase.groupdocs.com/faqs/licensing. " +
                    "\nLear how to request temporary license at https://purchase.groupdocs.com/temporary-license.");
        }
    }
}
