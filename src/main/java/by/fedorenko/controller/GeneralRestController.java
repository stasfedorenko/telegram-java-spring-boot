package by.fedorenko.controller;



import by.fedorenko.exception.ServiceException;
import by.fedorenko.service.BotService;
import by.fedorenko.service.PdfService;
import by.fedorenko.service.factory.DocFactory;
import by.fedorenko.service.impl.BotServiceImpl;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
public class GeneralRestController {

    @GetMapping("/run")
    public void run(){
        DocFactory docFactory = DocFactory.getInstance();
        PdfService pdfService = docFactory.getServicePdf();
        BotService serviceBot = new BotServiceImpl();

        try {
            pdfService.createPdf();
            serviceBot.runBot();
        } catch (ServiceException e) {
            e.printStackTrace();
        }







    }

}
