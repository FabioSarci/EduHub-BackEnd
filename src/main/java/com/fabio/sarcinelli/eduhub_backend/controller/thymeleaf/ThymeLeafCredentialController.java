package com.fabio.sarcinelli.eduhub_backend.controller.thymeleaf;

import com.fabio.sarcinelli.eduhub_backend.model.Credential;
import com.fabio.sarcinelli.eduhub_backend.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/credential")
public class ThymeLeafCredentialController {

    @Autowired
    private CredentialService credentialService;

    @GetMapping({"/", "/index"})
    public String newCredentialForm(Model model) {
        model.addAttribute("credential", new Credential());
        model.addAttribute("method", "post");
        model.addAttribute("action", "/credential");
        return "credential-form";
    }

    @GetMapping("/edit/{id}")
    public String editCredential(@PathVariable Long id, Model model) {
        Credential credential = credentialService.findById(id).orElse(new Credential());
        model.addAttribute("credential", credential);
        model.addAttribute("method", "put");
        model.addAttribute("action", "/credential/update/" + id);
        return "credential-form";
    }

    @GetMapping("/list")
    public String listCredentials(Model model) {
        List<Credential> credentials = credentialService.findAll();
        model.addAttribute("credentials", credentials);
        return "credential-list";
    }


    @GetMapping("/find/{id}")
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
        return "redirect:/credential/find/" + credentialSaved.getId();
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Credential credential, Model model) {
        credential.setId(id);
        Credential credentialSaved = credentialService.update(credential);
        model.addAttribute("credential", credentialSaved);
        return "redirect:/credential/find/" + credentialSaved.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        credentialService.deleteById(id);
        return "redirect:/credential/list";
    }
}
