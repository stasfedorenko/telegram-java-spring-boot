package by.fedorenko.service;

import by.fedorenko.exception.ServiceException;

public interface PdfService {
    void createPdf(String path) throws ServiceException;
}
