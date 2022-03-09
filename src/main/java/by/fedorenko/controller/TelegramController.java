package by.fedorenko.controller;


import by.fedorenko.exception.ServiceException;
import by.fedorenko.service.BotService;
import by.fedorenko.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/telegram")
public class TelegramController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private BotService serviceBot;

    @GetMapping("/send")
    @PreAuthorize("hasAuthority('developers:write')")
    public String send(Model model, HttpServletResponse response, HttpServletRequest request) {
        String basePath = request.getServletContext().getRealPath("/");
        try {
            pdfService.createPdf(basePath);
            serviceBot.runBot(basePath);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "telegram-page";
    }

    @RequestMapping("/index")
    public String index(Model model, HttpServletResponse response, HttpServletRequest request) {
        String basePath = request.getServletContext().getRealPath("/");

        System.out.println();
        return "Hello spring boot index";
    }

}
