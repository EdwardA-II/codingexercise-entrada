package olis.codingexercise.controller;

import olis.codingexercise.dto.EntryResponse;
import olis.codingexercise.dto.EntryUpdateRequest;
import olis.codingexercise.model.Entry;
import olis.codingexercise.service.EntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {

    private final EntryService entryService;
    private List<Entry> entryList = new ArrayList<>();

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping(produces = "application/json")
    public List<Entry> getAllEntrys() {
        return entryService.findAll();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public Entry addEntry(@RequestBody Entry newEntry) {
        return entryService.addEntry(newEntry);
    }

    @GetMapping(path = "/{entryId}", produces = "application/json")
    public Entry getEntry(@PathVariable Long entryId) {
        return entryService.findById(entryId);
    }

    @PatchMapping("/{entryId}")
    public ResponseEntity<EntryResponse> editEntry(
            @PathVariable Long entryId,
            @RequestBody EntryUpdateRequest updateRequest
    ) {
        EntryResponse response = entryService.updateEntry(entryId, updateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{entryId}", produces = "application/json")
    public void deleteEntry(@PathVariable Long entryId) {
        entryService.delete(entryId);
    }
}