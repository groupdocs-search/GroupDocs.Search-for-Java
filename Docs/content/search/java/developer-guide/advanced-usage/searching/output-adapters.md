---
id: output-adapters
url: search/java/output-adapters
title: Output adapters
weight: 13
description: ""
keywords: 
productName: GroupDocs.Search for Java
hideChildren: False
---
Output adapters are used to output generated HTML text to various output objects. The following output adapters are currently supported:

*   [FileOutputAdapter](https://apireference.groupdocs.com/search/java/com.groupdocs.search.common/FileOutputAdapter) is an output adapter that is used to output text to a file. The path to a file is specified in the constructor of the adapter.
*   [StreamOutputAdapter](https://apireference.groupdocs.com/search/java/com.groupdocs.search.common/StreamOutputAdapter) is an output adapter that is used to output text to a stream. The stream is specified in the constructor of the adapter.
*   [StringOutputAdapter](https://apireference.groupdocs.com/search/java/com.groupdocs.search.common/StringOutputAdapter) is an output adapter that is used to output text to a string. The resulting string can be accessed through the [getResult](https://apireference.groupdocs.com/search/java/com.groupdocs.search.common/StringOutputAdapter#getResult()) method of the adapter class.

The example below demonstates how to use adapters of different types.



```java
String indexFolder = "c:\\MyIndex\\";
String documentsFolder = "c:\\MyDocuments\\";
 
// Creating an index in the specified folder
Index index = new Index(indexFolder);
 
// Indexing documents from the specified folder
index.add(documentsFolder);
 
// Getting list of indexed documents
DocumentInfo[] documents = index.getIndexedDocuments();
 
// Getting a document text
if (documents.length > 0) {
    DocumentInfo document = documents[0];
 
    // Output to a file
    FileOutputAdapter fileOutputAdapter = new FileOutputAdapter("C:\\Text.html");
    index.getDocumentText(document, fileOutputAdapter);
 
    // Output to a stream
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    StreamOutputAdapter streamOutputAdapter = new StreamOutputAdapter(stream);
    index.getDocumentText(document, streamOutputAdapter);
 
    // Output to a string
    StringOutputAdapter stringOutputAdapter = new StringOutputAdapter();
    index.getDocumentText(document, stringOutputAdapter);
    String result = stringOutputAdapter.getResult();
}
```

## More resources

### GitHub examples

You may easily run the code from documentation articles and see the features in action in our GitHub examples:

*   [GroupDocs.Search for .NET examples](https://github.com/groupdocs-search/GroupDocs.Search-for-.NET)
    
*   [GroupDocs.Search for Java examples](https://github.com/groupdocs-search/GroupDocs.Search-for-Java)
    

### Free online document search App

Along with full featured .NET library we provide simple, but powerful free Apps.

You are welcome to search over your PDF, DOC, DOCX, PPT, PPTX, XLS, XLSX and more with our free online [Free Online Document Search App](https://products.groupdocs.app/search).