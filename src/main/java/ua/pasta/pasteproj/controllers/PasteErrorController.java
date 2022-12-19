package ua.pasta.pasteproj.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PasteErrorController implements ErrorController{

    private static final Logger LOGGER = LoggerFactory.getLogger(PasteErrorController.class);

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model){

        String errorPage = "error";
        String pageTitle = "Error page";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                errorPage = "/error/404";
                pageTitle = "Not found page";
                LOGGER.error("Error 404");
            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                errorPage = "/error/500";
                pageTitle = "Server error page";
                LOGGER.error("Error 500");
            } else if(statusCode == HttpStatus.FORBIDDEN.value()){
                errorPage = "error/403";
                pageTitle = "Forbidden";
                LOGGER.error("Error 403");
            }
        }

        model.addAttribute("pageTitle", pageTitle);

        return errorPage;

    }

}
