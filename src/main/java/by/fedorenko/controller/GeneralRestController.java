package by.fedorenko.controller;


import by.fedorenko.exception.ServiceException;
import by.fedorenko.service.BotService;
import by.fedorenko.service.PdfService;
import by.fedorenko.service.impl.BotServiceImpl;

import by.fedorenko.service.impl.PdfServiceImpl;
import by.fedorenko.util.PagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/")
public class GeneralRestController {

    @RequestMapping("/rest")
    public String index(Model model, HttpServletResponse response, HttpServletRequest request) {
        String basePath = request.getServletContext().getRealPath("/");
        System.out.println();
        return "Hello spring boot index in RESTCONTROLLER";
    }
}
