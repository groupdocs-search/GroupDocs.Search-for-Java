package com.groupdocs.search.examples.highlight_in_html;

import com.groupdocs.viewer.interfaces.ResourceStreamFactory;
import com.groupdocs.viewer.results.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class CustomResourceStreamFactory implements ResourceStreamFactory {
    private final IndexedFileInfo _fileInfo;

    public CustomResourceStreamFactory(IndexedFileInfo fileInfo) {
        _fileInfo = fileInfo;
    }

    @Override
    public OutputStream createResourceStream(int pageNumber, Resource resource) {
        String cacheFilePath = _fileInfo.getHtmlPageResourceFilePath(pageNumber, resource.getFileName());
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
    public String createResourceUrl(int pageNumber, Resource resource) {
        return _fileInfo.getHtmlPageResourceUrl(pageNumber, resource.getFileName());
    }

    @Override
    public void closeResourceStream(int i, Resource rsrc, OutputStream out) {
        try {
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
