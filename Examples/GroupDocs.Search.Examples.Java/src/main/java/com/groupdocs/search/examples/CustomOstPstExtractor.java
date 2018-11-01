package com.groupdocs.search.examples.java;

import com.groupdocs.search.DocumentType;
import com.groupdocs.search.ExtractedItemInfo;
import com.groupdocs.search.FieldInfo;
import com.groupdocs.search.IFieldExtractor;

public class CustomOstPstExtractor implements IFieldExtractor {
    public String[] getExtensions() {
        return new String[]{".ost", ".pst"};
    }

    public FieldInfo[] getFields(String fileName) {
        FieldInfo[] result = new FieldInfo[3];
        result[1] = new FieldInfo("Author", "Hardcoded author");
        result[2] = new FieldInfo("CreationDate", "21.05.2004");
        return result;
    }

    public ExtractedItemInfo[] getContaianerItems(String fileName) {
        ExtractedItemInfo[] result = new ExtractedItemInfo[1];
        FieldInfo[] fields;

        fields = new FieldInfo[9];
        fields[0] = new FieldInfo("MailMessageBody", "Text of email message");
        fields[1] = new FieldInfo("MailSenderName", "sender@email.com");
        fields[2] = new FieldInfo("MailDisplayName", "John Smith");
        fields[3] = new FieldInfo("MailDisplayToS", "All");
        fields[4] = new FieldInfo("MailSubject", "Email subject");
        fields[5] = new FieldInfo("MailDeliveryTime", "11:30");
        fields[6] = new FieldInfo("Author", "Email Author");
        fields[7] = new FieldInfo("MailArrivalTime", "11:30");
        fields[8] = new FieldInfo("MailMessageFlags", "Message flags");

        result[0] = new ExtractedItemInfo(DocumentType.OutlookEmailMessage, "EntryIdString", fileName, fields);

        return result;
    }

    public int getDocumentType() {
        {
            return DocumentType.OutlookStorage;
        }
    }

}
