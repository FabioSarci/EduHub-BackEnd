package com.fabio.sarcinelli.eduhub_backend.controller.thymeleaf;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/credential")
public class ThymeLeafCredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping("/index")
    public String showCredentialForm(Model model) {
        model.addAttribute("credential",  new Credential());
        return "credential-form";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Credential> credential = credentialService.findById(id);
        model.addAttribute("credential", credential.orElse(null));
        return "credential-detail";
    }

    @GetMapping("/email/{email}")
    public String findByEmail(@PathVariable String email, Model model) {
        Credential credential = credentialService.findByEmail(email);
        model.addAttribute("credential", credential);
        return "credential-detail";
    }
    

    @PostMapping
    public String save(@ModelAttribute Credential credential, Model model) {
        Credential credentialSaved = credentialService.save(credential);
        model.addAttribute("credential", credentialSaved);
        return "redirect:/credential/" + credentialSaved.getId();
    }
}
