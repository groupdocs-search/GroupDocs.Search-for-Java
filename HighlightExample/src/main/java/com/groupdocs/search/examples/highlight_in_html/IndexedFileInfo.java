package com.groupdocs.search.examples.highlight_in_html;

import java.io.File;
import java.nio.file.Paths;

class IndexedFileInfo
{
    private final String _viewerCacheFolderPath;
    private final String _filePath;
    private final String _fileFolderName;
    private final String _fileCacheFolderPath;

    public IndexedFileInfo(String viewerCacheFolderPath, String filePath) {
        _viewerCacheFolderPath = viewerCacheFolderPath;
        _filePath = filePath;
        String fileName = new File(_filePath).getName();
        _fileFolderName = fileName.replace('.', '_');
        _fileCacheFolderPath = Paths.get(viewerCacheFolderPath, _fileFolderName).toString();
    }

    public String getViewerCacheFolderPath() {
        return _viewerCacheFolderPath;
    }

    public String getFilePath() {
        return _filePath;
    }

    public String getFileFolderName() {
        return _fileFolderName;
    }

    public String getFileCacheFolderPath() {
        return _fileCacheFolderPath;
    }

    public final String getHtmlPageFilePath(int pageNumber) {
        String pageFileName = "p" + pageNumber + ".html";
        String pageFilePath = Paths.get(_fileCacheFolderPath, pageFileName).toString();
        return pageFilePath;
    }

    public final String getHtmlPageResourceFilePath(int pageNumber, String resourceFileName) {
        String resFileName = getResourceFileName(pageNumber, resourceFileName);
        String resFilePath = Paths.get(_fileCacheFolderPath, resFileName).toString();
        return resFilePath;
    }

    public final String getHtmlPageResourceUrl(int pageNumber, String resourceFileName) {
        return getResourceFileName(pageNumber, resourceFileName);
    }

    private static String getResourceFileName(int pageNumber, String resourceFileName) {
        String resFileName = "p" + pageNumber + "_" + resourceFileName;
        return resFileName;
    }
}
