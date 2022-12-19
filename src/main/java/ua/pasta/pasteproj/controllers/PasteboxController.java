package ua.pasta.pasteproj.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.pasta.pasteproj.api.request.HashRequest;
import ua.pasta.pasteproj.api.request.PasteboxRequest;
import ua.pasta.pasteproj.api.response.PasteboxResponse;
import ua.pasta.pasteproj.api.response.PasteboxUrlResponse;
import ua.pasta.pasteproj.service.PasteboxService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PasteboxController {

    private final PasteboxService pasteboxService;

    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("pastebox", new PasteboxRequest());
        return "paste-creation-page";

    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("pastebox") PasteboxRequest request, BindingResult result){

        if(result.hasErrors())
            return "paste-creation-page";

        PasteboxUrlResponse urlResponse = pasteboxService.create(request);
        return "redirect:/getPasteInfo/" + urlResponse.getUrl();

    }

    // We have 2 options where the variable comes from: path, requestparam
    @GetMapping({"/getPasteInfo/{hash}", "/getPasteInfo/"})
    public String getByHash(@RequestParam(required = false, value = "hash") String byRequestParamHash,
                            @PathVariable(required = false, value = "hash") String byUrlHash, Model model){

        // If byRequestParamHash = null, then we use byUrlHash variable, and vice-versa
        PasteboxResponse response;
        if(byRequestParamHash == null)
            response = pasteboxService.getByHash(byUrlHash);
        else
            response = pasteboxService.getByHash(byRequestParamHash);

        // If there's no any paste, then send notfound page 'show-one-null'
        if(response.getId() == null) {
            model.addAttribute("message",
                    "We haven't found any paste with hash: " + response.getHash());
            return "show-null-paste";
        }

        model.addAttribute("response", response);
        return "show-one-paste";

    }

    @GetMapping("/get")
    public String getByInput(Model model){
        model.addAttribute("hash", new HashRequest());
        return "get-paste-by-hash";
    }

    @GetMapping("paste/remove/{hash}")
    public String remove(@PathVariable String hash, Model model){

        String message;
        if(pasteboxService.deleteById(hash)) {
            message = "Paste with hash " + hash + " has been deleted successfully!";
        } else {
            message = "We haven't found any paste with hash: " + hash;
        }
        model.addAttribute("message", message);

        return "show-null-paste";
    }

}
