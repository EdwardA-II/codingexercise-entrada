package olis.codingexercise.controller;

import jakarta.validation.Valid;
import olis.codingexercise.model.Entry;
import olis.codingexercise.service.EntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/entries")
public class EntryController {

    private final EntryService entryService;
    private List<Entry> entryList = new ArrayList<>();

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    // Show the form + table of previous entries
    @GetMapping
    public String showForm(Model model) {
        // Create an empty Form object to hold all of the user's info.
        model.addAttribute("entry", new Entry());
        model.addAttribute("entries", entryService.findAll());

        // Return the Thymeleaf template name!
        return "entry-form";
    }

    // Handle the Form submission
    @PostMapping
    public String submit(@Valid @ModelAttribute Entry entry,
                         // BindingResult - Stores validation errors so we can
                         // tell the user that they entered something wrong.
                         BindingResult binding,
                         Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("entries", entryService.findAll());
            return "entry-form";
        }
        Entry saved = entryService.addEntry(entry);
        // Redirect and pass the new ID so the confirm page can show the just-submitted record
        return "redirect:/entries/confirm?id=" + saved.getEntryId();
    }

    // Confirmation page
    @GetMapping("/confirm")
    public String showConfirmation(Model model) {
        model.addAttribute("entries", entryService.findAll());
        return "entry-confirmation";
    }

}