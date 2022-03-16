package by.fedorenko.controller;

import by.fedorenko.exception.ServiceException;
import by.fedorenko.repository.TaskJpaRepository;
import by.fedorenko.service.BotService;
import by.fedorenko.service.PdfService;
import by.fedorenko.util.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/telegram")
public class TelegramController {

    private final PdfService pdfService;
    private final TaskJpaRepository taskJpaRepository;
    private final BotService serviceBot;

    public TelegramController(PdfService pdfService, TaskJpaRepository taskJpaRepository, BotService serviceBot) {
        this.pdfService = pdfService;
        this.taskJpaRepository = taskJpaRepository;
        this.serviceBot = serviceBot;
    }

    @GetMapping("/send")
//    @PreAuthorize("hasAuthority('developers:write')")
    public String send(Model model, HttpServletResponse response, HttpServletRequest request) {
        String basePath = request.getServletContext().getRealPath("/");
        try {
            pdfService.createPdf(basePath);
            serviceBot.sendReports(basePath);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PagePath.TELEGRAM_SUCCESS;
    }

    @GetMapping("/send_task/{id}")
//    @PreAuthorize("hasAuthority('developers:write')")
    public String sendTasks(Model model, @PathVariable("id") Long id) {
        try {
            serviceBot.sendTask(taskJpaRepository.findById(id).get());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PagePath.TELEGRAM_SUCCESS;
    }


    @GetMapping("/success")
//    @PreAuthorize("hasAuthority('developers:write')")
    public String success(Model model){
        model.addAttribute("sendSuccess",true);
        return PagePath.TELEGRAM;
    }

    @GetMapping("")
//    @PreAuthorize("hasAuthority('developers:write')")
    public String index() {
        return PagePath.TELEGRAM;
    }

}
