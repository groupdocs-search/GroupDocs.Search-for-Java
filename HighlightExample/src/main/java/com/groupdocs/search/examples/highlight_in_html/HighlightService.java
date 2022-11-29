package com.groupdocs.search.examples.highlight_in_html;

import com.groupdocs.search.dictionaries.Alphabet;
import com.groupdocs.search.examples.Utils;
import com.groupdocs.search.results.FoundDocument;
import com.groupdocs.viewer.results.Page;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.apache.commons.io.FileUtils;

class HighlightService {
    private static final String Key = "<style>";
    private static final String HighlightStyle = ".highlighted-term { background-color:#ADFF2F; } ";

    private final IndexedFileInfo _fileInfo;
    private final String _password;
    private final Path _backupPath;

    private List<Page> _pages;

    public HighlightService(
        IndexedFileInfo fileInfo,
        String password) {
        _fileInfo = fileInfo;
        _password = password;
        _backupPath = Paths.get(_fileInfo.getViewerCacheFolderPath(), _fileInfo.getFileFolderName() + "_backup");

        try (HtmlViewer htmlViewer = new HtmlViewer(_fileInfo, _password)) {
            _pages = htmlViewer.getPages();
            for (int i = 0; i < _pages.size(); i++) {
                Page page = _pages.get(i);
                htmlViewer.createCacheForPage(page.getNumber());
            }
        }

        try {
            Files.move(
                    new File(_fileInfo.getFileCacheFolderPath()).toPath(),
                    _backupPath,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public final void highlight(FoundDocument foundDocument, Alphabet alphabet) {
        Utils.copyFiles(_backupPath.toString(), _fileInfo.getFileCacheFolderPath());
        
        for (int i = 0; i < _pages.size(); i++) {
            Page page = _pages.get(i);
            String pageFilePath = _fileInfo.getHtmlPageFilePath(page.getNumber());

            String text;
            try {
                text = FileUtils.readFileToString(new File(pageFilePath), "utf-8");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            String result = HtmlHighlighter.handle(
                text,
                false,
                alphabet,
                foundDocument.getTerms(),
                foundDocument.getTermSequences());

            // Inserting the highlighting style
            int index = result.indexOf(Key);
            if (index > 0 && index + Key.length() < result.length()) {
                int position = index + Key.length();
                result = result.substring(0, position) + HighlightStyle + result.substring(position);
            }

            try {
                FileUtils.writeStringToFile(new File(pageFilePath), result, "utf-8");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
