package com.groupdocs.search.examples.advanced_usage.creating_an_index;

import com.groupdocs.search.*;
import com.groupdocs.search.events.*;
import com.groupdocs.search.results.*;
import com.groupdocs.search.examples.Utils;

public class UsingIndexRepository {
    public static void run() {
        String indexFolder1 = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingIndexRepository\\Index1";
        String indexFolder2 = ".\\output\\AdvancedUsage\\CreatingAnIndex\\UsingIndexRepository\\Index2";
        String documentFolder1 = Utils.DocumentsPath;
        String documentFolder2 = Utils.DocumentsPath2;

        // Creating an index repository instance
        IndexRepository indexRepository = new IndexRepository();

        // Subscribing to an event
        indexRepository.getEvents().OperationProgressChanged.add(new EventHandler<OperationProgressEventArgs>() {
            @Override
            public void invoke(Object sender, OperationProgressEventArgs args) {
                System.out.println("Indexed document:\n\t" + args.getLastDocumentPath());
            }
        });

        // Creating or loading an index and adding to the index repository
        Index index1 = new Index(indexFolder1);
        indexRepository.addToRepository(index1);

        // Creating or loading an index and adding to the index repository
        Index index2 = new Index(indexFolder2);
        indexRepository.addToRepository(index2);

        // Adding documents to the index 1
        index1.add(documentFolder1);

        // Adding documents to the index 2
        index2.add(documentFolder2);

        // Changing, deleting, adding documents to document folders
        // ...

        // Updating all indexes in the repository
        indexRepository.update();

        // Searching in the repository
        String query = "decisively";
        SearchResult result = indexRepository.search(query);

        Utils.traceResult(query, result);
    }
}
