package olis.codingexercise.service;

import olis.codingexercise.dto.EntryResponse;
import olis.codingexercise.dto.EntryUpdateRequest;
import olis.codingexercise.mapper.EntryMapper;
import olis.codingexercise.model.Entry;
import olis.codingexercise.repository.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public Entry findById(Long entryId) {
        return entryRepository.findById(entryId).orElse(null);
    }

    public Entry addEntry(Entry newEntry) {
        newEntry.setEntryId(null);
        entryRepository.save(newEntry);
        return newEntry;
    }

    public Entry save(Entry entry) {
        return entryRepository.save(entry);
    }

    public void delete(Long entryId) {
        entryRepository.deleteById(entryId);
    }

    public EntryResponse updateEntry(Long entryId, EntryUpdateRequest updateRequest) {
        // Find the entry to be updated.
        Entry entryToUpdate = entryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found!"));

        // Update the entry using the requested changes.
        EntryMapper.updateEntryFromRequest(updateRequest, entryToUpdate);

        // After updating the entry, map it to the Response DTO.
        EntryResponse entryResponse = EntryMapper.mapResponse(entryToUpdate);

        // Save the updates to the DB.
        entryRepository.save(entryToUpdate);

        return entryResponse;
    }
}
