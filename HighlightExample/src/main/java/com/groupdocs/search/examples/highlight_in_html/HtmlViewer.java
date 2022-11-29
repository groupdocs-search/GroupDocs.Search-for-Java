package com.groupdocs.search.examples.highlight_in_html;

import com.groupdocs.viewer.Viewer;
import com.groupdocs.viewer.interfaces.PageStreamFactory;
import com.groupdocs.viewer.interfaces.ResourceStreamFactory;
import com.groupdocs.viewer.options.HtmlViewOptions;
import com.groupdocs.viewer.options.LoadOptions;
import com.groupdocs.viewer.options.SpreadsheetOptions;
import com.groupdocs.viewer.options.TextOverflowMode;
import com.groupdocs.viewer.options.ViewInfoOptions;
import com.groupdocs.viewer.results.Page;
import com.groupdocs.viewer.results.ViewInfo;
import java.io.Closeable;
import java.io.File;
import java.util.List;

class HtmlViewer implements Closeable
{
    private final IndexedFileInfo _fileInfo;
    private final Viewer _viewer;
    private final HtmlViewOptions _viewOptions;

    public HtmlViewer(
        IndexedFileInfo fileInfo,
        String password) {
        _fileInfo = fileInfo;
        new File(_fileInfo.getFileCacheFolderPath()).mkdirs();

        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setPassword(password);
        _viewer = new Viewer(_fileInfo.getFilePath(), loadOptions);
        _viewOptions = createHtmlViewOptions();
    }

    @Override
    public final void close() {
        if (_viewer != null) {
            _viewer.close();
        }
    }

    public final List<Page> getPages() {
        ViewInfoOptions viewInfoOptions = ViewInfoOptions.fromHtmlViewOptions(_viewOptions);
        ViewInfo viewInfo = _viewer.getViewInfo(viewInfoOptions);
        return viewInfo.getPages();
    }

    public final void createCacheForPage(int pageNumber) {
        _viewer.view(_viewOptions, pageNumber);
    }

    private HtmlViewOptions createHtmlViewOptions() {
        PageStreamFactory psf = new CustomPageStreamFactory(_fileInfo);
        ResourceStreamFactory rsf = new CustomResourceStreamFactory(_fileInfo);
        HtmlViewOptions htmlViewOptions = HtmlViewOptions.forExternalResources(psf, rsf);

        htmlViewOptions.setSpreadsheetOptions(SpreadsheetOptions.forOnePagePerSheet());
        htmlViewOptions.getSpreadsheetOptions().setTextOverflowMode(TextOverflowMode.HIDE_TEXT);
        htmlViewOptions.getSpreadsheetOptions().setRenderGridLines(true);

        return htmlViewOptions;
    }
}
