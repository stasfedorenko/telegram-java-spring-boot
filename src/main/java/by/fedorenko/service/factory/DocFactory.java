package by.fedorenko.service.factory;

import by.fedorenko.service.PdfService;
import by.fedorenko.service.impl.PdfServiceImpl;

public final class DocFactory {
    private static final DocFactory instance = new DocFactory();
    private final PdfService servicePdf = new PdfServiceImpl();

    private DocFactory() {
    }

    public static DocFactory getInstance() {
        return instance;
    }

    public PdfService getServicePdf() {
        return servicePdf;
    }
}
