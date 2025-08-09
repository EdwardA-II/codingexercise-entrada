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

    private final EntryRepository userRepo;

    public EntryService(EntryRepository entryRepository) {
        this.userRepo = entryRepository;
    }

    public List<Entry> findAll() {
        return userRepo.findAll();
    }

    public Entry findById(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    public Entry createUser(Entry newEntry) {
        newEntry.setUserId(null);
        userRepo.save(newEntry);
        return newEntry;
    }

    public Entry save(Entry entry) {
        return userRepo.save(entry);
    }

    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }

    public EntryResponse updateUser(Long userId, EntryUpdateRequest updateRequest) {
        // Find the user to be updated.
        Entry entryToUpdate = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Entry not found!"));

        // Update the user using the requested changes.
        EntryMapper.updateEntryFromRequest(updateRequest, entryToUpdate);

        // After updating the user, map it to the Response DTO.
        EntryResponse entryResponse = EntryMapper.mapResponse(entryToUpdate);

        // Save the updates to the DB.
        userRepo.save(entryToUpdate);

        return entryResponse;
    }
}
