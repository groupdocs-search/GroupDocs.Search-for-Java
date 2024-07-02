package com.groupdocs.search.examples;

import com.groupdocs.search.examples.advanced_usage.creating_an_index.*;
import com.groupdocs.search.examples.advanced_usage.indexing.*;
import com.groupdocs.search.examples.advanced_usage.managing_dictionaries.*;
import com.groupdocs.search.examples.advanced_usage.searching.*;
import com.groupdocs.search.examples.advanced_usage.scaling.*;
import com.groupdocs.search.examples.basic_usage.*;
import com.groupdocs.search.examples.quick_start.*;

public class MainClass {
    public static void main(String[] args) throws Throwable {
        System.out.println("Open MainClass.java. \nIn the main() method, uncomment the example that you want to run.");
        System.out.println("=====================================================");

        //NOTE: Please uncomment the example you want to try out

        // Getting Started ----------------------------------------------------------------------------------------------------

        SetLicenseFromFile.run();
        //SetLicenseFromStream.run();
        //SetMeteredLicense.run();

        HelloWorld.run();

        // Basic Usage ----------------------------------------------------------------------------------------------------

//        BuildYourFirstSearchSolution.runSynchronousIndexing();
//        BuildYourFirstSearchSolution.runAsynchronousIndexing();
//
//        BuildSearchQuery.run();
//
//        WorkWithSearchResults.obtainSearchResultInformation();
//        WorkWithSearchResults.highlightSearchResults();
//
//        GetSupportedFileFormats.run();

        // Advanced Usage ----------------------------------------------------------------------------------------------------

        // Creating an Index ----------------------------------------------------------------------------------------------------

//        UsingEvents.operationFinishedEvent();
//        UsingEvents.errorOccurredEvent();
//        UsingEvents.operationProgressChangedEvent();
//        UsingEvents.optimizationProgressChangedEvent();
//        UsingEvents.passwordRequiredEvent();
//        UsingEvents.fileIndexingEvent();
//        UsingEvents.statusChangedEvent();
//        UsingEvents.searchPhaseCompletedEvent();
//        UsingEvents.ImagePreparingEvent();

//        UsingIndexRepository.run();

        // Indexing ----------------------------------------------------------------------------------------------------

//        CharacterReplacementDuringIndexing.run();
//
//        CharacterTypes.regularCharacters();
//        CharacterTypes.blendedCharacters();
//
//        CustomTextExtractors.run();
//
//        DeleteIndexedDocuments.run();
//
//        DeleteIndexedPaths.run();
//
//        DocumentAttributes.changeAttributes();
//        DocumentAttributes.addAttributesDuringIndexing();
//
//        DocumentFilteringDuringIndexing.settingAFilter();
//        DocumentFilteringDuringIndexing.creationTimeFilters();
//        DocumentFilteringDuringIndexing.modificationTimeFilters();
//        DocumentFilteringDuringIndexing.filePathFilters();
//        DocumentFilteringDuringIndexing.fileLengthFilters();
//        DocumentFilteringDuringIndexing.fileExtensionFilter();
//        DocumentFilteringDuringIndexing.logicalNotFilter();
//        DocumentFilteringDuringIndexing.logicalAndFilter();
//        DocumentFilteringDuringIndexing.logicalOrFilter();
//
//        DocumentRenaming.run();
//
//        IndexingAdditionalFields.run();
//
//        IndexingFromDifferentSources.indexingFromFile();
//        IndexingFromDifferentSources.indexingFromStream();
//        IndexingFromDifferentSources.indexingFromStructure();
//        IndexingFromDifferentSources.indexingFromUrl();
//        IndexingFromDifferentSources.indexingFromFtp();
//
//        IndexingMetadataOfDocuments.run();
//
//        IndexingOptionsProperties.cancellationProperty();
//        IndexingOptionsProperties.isAsyncProperty();
//        IndexingOptionsProperties.threadsProperty();
//        IndexingOptionsProperties.metadataIndexingOptionsProperty();
//
//        IndexingPasswordProtectedDocuments.indexingUsingThePasswordDictionary();
//        IndexingPasswordProtectedDocuments.indexingUsingThePasswordRequiredEvent();
//
//        IndexingReports.run();
//
//        IndexingWithStopWords.run();
//
//        Logging.useOfStandardFileLogger();
//        Logging.implementingCustomLogger();
//
//        MergeIndexes.run();
//
//        OcrSupport.run();
//
//        OptimizeIndex.run();
//
//        SeparateDataExtraction.run();
//
//        StoringTextOfIndexedDocuments.run();
//
//        TextFileEncodingDetection.run();
//
//        UpdateIndex.updateIndexedDocuments();
//        UpdateIndex.updateIndexVersion();

        // Searching ----------------------------------------------------------------------------------------------------

//        BooleanSearch.operatorAnd();
//        BooleanSearch.operatorOr();
//        BooleanSearch.operatorNot();
//        BooleanSearch.complexQueries();
//
//        CaseSensitiveSearch.queryInTextForm();
//        CaseSensitiveSearch.queryInObjectForm();
//
//        DateRangeSearch.creatingDateRangeSearchQueries();
//        DateRangeSearch.specifyingDateRangeSearchFormats();
//
//        DocumentFilteringInSearchResult.settingAFilter();
//        DocumentFilteringInSearchResult.filePathFilters();
//        DocumentFilteringInSearchResult.fileExtensionFilter();
//        DocumentFilteringInSearchResult.attributeFilter();
//        DocumentFilteringInSearchResult.combiningFilters();
//
//        FacetedSearch.simpleFacetedSearch();
//        FacetedSearch.complexQuery();
//
//        FuzzySearch.settingFuzzySearchAlgorithm();
//        FuzzySearch.settingStepFunction();
//
//        GettingIndexedDocuments.gettingDocuments();
//        GettingIndexedDocuments.gettingTextOfIndexedDocuments();
//
//        HighlightingSearchResults.highlightingInEntireDocument();
//        HighlightingSearchResults.highlightingInFragments();
//
//        HomophoneSearch.run();
//
//        KeyboardLayoutCorrection.run();
//
//        NumericRangeSearch.run();
//
//        OutputAdapters.run();
//
//        PhraseSearch.simplePhraseSearch();
//        PhraseSearch.phraseSearchWithWildcards();
//        PhraseSearch.phraseSearchWithWildcards2();
//
//        QueriesInTextAndObjectForm.run();
//
//        RegularExpressionSearch.run();
//
//        ReverseImageSearch.run();
//
//        SearchByChunks.run();
//
//        SearchForDifferentWordForms.run();
//
//        SearchForSpecialCharacters.run();
//
//        SearchReports.run();
//
//        SearchResults.run();
//
//        SpellChecking.run();
//
//        SynonymSearch.run();
//
//        UsingAliases.run();
//
//        WildcardSearch.queryInTextForm();
//        WildcardSearch.queryInObjectForm();

        // Managing Dictionaries ----------------------------------------------------------------------------------------------------

//        AliasDictionary.run();
//
//        Alphabet.run();
//
//        CharacterReplacements.run();
//
//        DocumentPasswords.run();
//
//        HomophoneDictionary.run();
//
//        SpellingCorrector.run();
//
//        StopWordDictionary.run();
//
//        SynonymDictionary.run();
//
//        WordFormsProvider.run();

        // Scaling ----------------------------------------------------------------------------------------------------

//        ConfiguringSearchNetwork.run();
//
//        SearchNetworkDeployment.run();
//
//        SearchNetworkNodeEvents.run();
//
//        IndexingDocuments.run();
//
//        DeletingDocuments.run();
//
//        OptimizingShards.run();
//
//        SynchronizingShards.run();
//
//        WorkingWithAttributes.run();
//
//        TextSearchInNetwork.run();
//
//        ImageSearchInNetwork.run();
//
//        HighlightingResultsInNetwork.run();
//
//        GettingDocumentsInNetwork.run();
//
//        GettingDocumentTextInNetwork.run();
//
//        ManagingDictionariesInNetwork.run();

        // Highlight Search Results in HTML ----------------------------------------------------------------------------------------------------

//        HighlightExample.run();

        System.out.println();
        System.out.println("All done.");
    }
}
