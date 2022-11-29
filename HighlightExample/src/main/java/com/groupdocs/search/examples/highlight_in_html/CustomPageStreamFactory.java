package com.groupdocs.search.examples.highlight_in_html;

import com.groupdocs.viewer.interfaces.PageStreamFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class CustomPageStreamFactory implements PageStreamFactory {
    private final IndexedFileInfo _fileInfo;
    
    public CustomPageStreamFactory(IndexedFileInfo fileInfo) {
        _fileInfo = fileInfo;
    }

    @Override
    public OutputStream createPageStream(int pageNumber) {
        String cacheFilePath = _fileInfo.getHtmlPageFilePath(pageNumber);
        File file = new File(cacheFilePath);
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file, false);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return stream;
    }

    @Override
    public void closePageStream(int i, OutputStream out) {
        try {
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
