
# Java Library to Search & Index Documents

GroupDocs.Search for Java is a [Search & Indexing Library](https://products.groupdocs.com/search/java) that can perform basic and advanced search operations against various formats to gain data insights. It can also be used for indexing [various document types](https://docs.groupdocs.com/search/java/supported-document-formats/) to perform search on the basis of different search query types. Advance searching techniques such as fuzzy, synonyms, boolean and full-text search are supported.

<p align="center">
  <a title="Download complete GroupDocs.Search for Java source code" href="https://github.com/groupdocs-search/GroupDocs.Search-for-Java/archive/master.zip"> 
    <img src="https://camo.githubusercontent.com/11839cd752a2d367f3149c7bee1742b68e4a4d37/68747470733a2f2f7261772e6769746875622e636f6d2f4173706f73654578616d706c65732f6a6176612d6578616d706c65732d64617368626f6172642f6d61737465722f696d616765732f646f776e6c6f61645a69702d427574746f6e2d4c617267652e706e67" data-canonical-src="https://raw.github.com/AsposeExamples/java-examples-dashboard/master/images/downloadZip-Button-Large.png" style="max-width:100%;">
  </a>
</p>

Directory | Description
--------- | -----------
[Examples](https://github.com/groupdocs-search/GroupDocs.Search-for-Java/tree/master/Examples)  | Java examples and sample documents for you to get started quickly. 

## Document Indexing Features

- Create index in memory or on disk.
- Update index to take into account changed, deleted and added documents.
- Merge several indexes into one.
- Optimize index to improve search performance.
- Indexing password protected documents.
- Indexing with stop words.
- Support for indexing additional fields.
- Support for blended characters.
- Support for characters indexed as a whole word.
- Support for character replacement during indexing.
- Support for custom text extractors.
- Option for compact and metadata index.
- Ability to save extracted text in index with different level of compression.
- Document filtering during indexing.

## Document Search Features

- Simple word search.
- Boolean search.
- Regular expression search.
- Faceted search.
- Case sensitive search.
- Flexible fuzzy search.
- Synonym search.
- Homophone search.
- Wildcard search.
- Phrase search with wildcards.
- Search for different word forms.
- Date range search.
- Numeric range search.
- Search by chunks (pages).
- Document filtering in search result.
- Search for different object types: text, numbers, dates, file names, document types, metadata fields, document creation/modification dates.
- Combine different types of search into one search query.
- Alias substitution in search queries.
- Perform spell check during search.
- Perform keyboard layout correction during search.
- Search queries in text or flexible object form.
- Highlight search results in the text of the entire document or in text segments.
- Multiple simultaneous thread safe search.
- Thread safe search during indexing, updating or merging operation.
- Search over several indexes simultaneously.


## Get Started with GroupDocs.Search for Java

GroupDocs.Search for Java requires J2SE 7.0 (1.7), J2SE 8.0 (1.8) or above. Please install Java first if you do not have it already. 

GroupDocs hosts all Java APIs on [GroupDocs Artifact Repository](https://artifact.groupdocs.com/webapp/#/artifacts/browse/tree/General/repo/com/groupdocs/groupdocs-search), so simply [configure](https://docs.groupdocs.com/search/java/installation/) your Maven project to fetch the dependencies automatically.

## Perform Case-Sensitive Search with a Query in Object Form

```java
// creating an index in the specified folder
Index index = new Index(indexFolder);
 
// indexing documents from the specified folder
index.add(documentsFolder);
 
SearchOptions options = new SearchOptions();
options.setUseCaseSensitiveSearch(true); // Enabling case sensitive search
 
// creating search query in object form
SearchQuery query = SearchQuery.createWordQuery("Windows");
 
// searching in the index
SearchResult result = index.search(query, options);
```

[Home](https://www.groupdocs.com/) | [Product Page](https://products.groupdocs.com/search/java) | [Documentation](https://docs.groupdocs.com/search/java/) | [Demos](https://products.groupdocs.app/search/family) | [API Reference](https://apireference.groupdocs.com/java/search) | [Examples](https://github.com/groupdocs-search/GroupDocs.search-for-Java/tree/master/Examples) | [Blog](https://blog.groupdocs.com/category/search/) | [Free Support](https://forum.groupdocs.com/c/search) | [Temporary License](https://purchase.groupdocs.com/temporary-license)
