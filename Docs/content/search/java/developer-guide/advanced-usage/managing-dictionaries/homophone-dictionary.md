---
id: homophone-dictionary
url: search/java/homophone-dictionary
title: Homophone dictionary
weight: 5
description: ""
keywords: 
productName: GroupDocs.Search for Java
hideChildren: False
---
The [HomophoneDictionary](https://apireference.groupdocs.com/search/java/com.groupdocs.search.dictionaries/HomophoneDictionary) class is designed to store homophones in an index. For information on searching using homophones, see the [Homophone search]({{< ref "search/java/developer-guide/advanced-usage/searching/homophone-search.md" >}}) page.

To get the number of homophones in the dictionary, use the [getCount](https://apireference.groupdocs.com/search/java/com.groupdocs.search.dictionaries/HomophoneDictionary#getCount()) method.

To add groups of homophones to the dictionary, use the [addRange](https://apireference.groupdocs.com/search/java/com.groupdocs.search.dictionaries/HomophoneDictionary#addRange(java.lang.Iterable)) method.

The [clear](https://apireference.groupdocs.com/search/java/com.groupdocs.search.dictionaries/HomophoneDictionary#clear()) method is used to remove all homophones from the dictionary.

To export homophones to a file, use the [exportDictionary](https://apireference.groupdocs.com/search/java/com.groupdocs.search.dictionaries/DictionaryBase#exportDictionary(java.lang.String)) method.

To import homophones from a file, use the [importDictionary](https://apireference.groupdocs.com/search/java/com.groupdocs.search.dictionaries/DictionaryBase#importDictionary(java.lang.String)) method.

The following example demonstrates the use of methods of the homophone dictionary.



```java
String indexFolder = "c:\\MyIndex\\";
 
// Creating an index from in specified folder
Index index = new Index(indexFolder);
 
if (index.getDictionaries().getHomophoneDictionary().getCount() > 0) {
    // Removing all homophones from the dictionary
    index.getDictionaries().getHomophoneDictionary().clear();
}
 
// Adding homophones to the dictionary
String[][] homophoneGroups = new String[][] {
    new String[] { "awe", "oar", "or", "ore" },
    new String[] { "aye", "eye", "i" },
};
index.getDictionaries().getHomophoneDictionary().addRange(homophoneGroups);
 
// Export homophones to a file
index.getDictionaries().getHomophoneDictionary().exportDictionary("C:\\Homophones.dat");
 
// Import homophones from a file
index.getDictionaries().getHomophoneDictionary().importDictionary("C:\\Homophones.dat");
```

## More resources

### GitHub examples

You may easily run the code from documentation articles and see the features in action in our GitHub examples:

*   [GroupDocs.Search for .NET examples](https://github.com/groupdocs-search/GroupDocs.Search-for-.NET)
    
*   [GroupDocs.Search for Java examples](https://github.com/groupdocs-search/GroupDocs.Search-for-Java)
    

### Free online document search App

Along with full featured .NET library we provide simple, but powerful free Apps.

You are welcome to search over your PDF, DOC, DOCX, PPT, PPTX, XLS, XLSX and more with our free online [Free Online Document Search App](https://products.groupdocs.app/search).
