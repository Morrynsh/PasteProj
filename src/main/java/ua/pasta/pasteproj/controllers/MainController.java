package ua.pasta.pasteproj.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.pasta.pasteproj.api.response.PasteboxResponse;
import ua.pasta.pasteproj.service.PasteboxService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PasteboxService pasteboxService;

    @GetMapping("/")
    public String homePage(Model model){

        List<PasteboxResponse> pasteboxResponses = pasteboxService.getLastPublic();
        model.addAttribute("responses", pasteboxResponses);
        return "index";
    }

}
