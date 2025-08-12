package olis.codingexercise.service;

import olis.codingexercise.dto.EntryResponse;
import olis.codingexercise.dto.EntryUpdateRequest;
import olis.codingexercise.exception.EntryNotFoundException;
import olis.codingexercise.mapper.EntryMapper;
import olis.codingexercise.model.Entry;
import olis.codingexercise.repository.EntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// Learning Point: A transaction keeps all DB work for all methods in this class together.
    // If anything fails, all changes are rolled back so the DB stays consistent.
    // "readOnly = true" means this method only reads data and won’t make updates.
@Transactional(readOnly = true)
public class EntryService {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public Entry findById(Long entryId) {
        return entryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found!"));
    }

    @Transactional
    public Entry addEntry(Entry newEntry) {
        newEntry.setEntryId(null);
        Entry savedEntry = entryRepository.save(newEntry);
        return savedEntry;
    }

    @Transactional
    public Entry save(Entry entry) {
        return entryRepository.save(entry);
    }

    @Transactional
    public void deleteEntry(Long entryId) {
        if (!entryRepository.existsById(entryId)) {
            throw new EntryNotFoundException("Entry not found with entryId: " + entryId);
        }
        entryRepository.deleteById(entryId);
    }

    @Transactional
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
