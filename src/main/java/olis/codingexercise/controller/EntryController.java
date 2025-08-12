package olis.codingexercise.controller;

import jakarta.servlet.http.HttpServletRequest;
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
    @GetMapping("/entry-form")
    public String showForm(Model model) {
        // Create an empty Form object to hold all of the user's info.
        model.addAttribute("entry", new Entry());

        // Return the Thymeleaf template name!
        return "entry-form";
    }

    // Handle the Form submission, take them to a confirmation page.
    @PostMapping("/confirm")
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
        return "redirect:/entries/confirm?newEntryId=" + saved.getEntryId();
    }

    // Method to handle the GET request after the confirmation redirect
    @GetMapping("/confirm")
    public String showConfirmation(@RequestParam("newEntryId") Long entryId, Model model) {
        Entry newEntry = entryService.findById(entryId);
        model.addAttribute("entry", newEntry);
        return "entry-confirmation";
    }

    // An independent page that shows all of the entries
    @GetMapping("/all-entries")
    public String showAllEntries(Model model) {
        List<Entry> allEntries = entryService.findAll();
        model.addAttribute("entries", allEntries);
        return "entries-list";
    }

    // Serves an "Are you sure?" page to the user just in case.
    @GetMapping("/delete/{entryId}")
    public String showDeleteConfirmation(@PathVariable("entryId") Long entryId, Model model) {
        Entry entryToDelete = entryService.findById(entryId);

        // Check if the entry was found.
        if (entryToDelete == null) {
            // Maybe make an "entry not found" page later?
            return "redirect:/entries/all-entries";
        }

        model.addAttribute("entry", entryToDelete);
        return "entry-delete-confirmation";
    }

    // Learning Moment:
        // Here, we are "tricking" Spring Boot into overriding the POST request into a DELETE request
        // because HTML requests only allow for POST and GET requests!
    @RequestMapping(value = "/delete/{entryId}", method = RequestMethod.POST)
    public String deleteEntry(@PathVariable("entryId") Long entryId,
                              @RequestParam("_method") String method) {
        if ("delete".equalsIgnoreCase(method)) {
            entryService.deleteEntry(entryId);
        }

        // Take us back to the entries list once the deletion is complete.
        return "redirect:/entries/all-entries";
    }



}