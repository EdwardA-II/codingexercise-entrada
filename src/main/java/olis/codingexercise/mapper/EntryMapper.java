package olis.codingexercise.mapper;

import org.springframework.stereotype.Component;

import olis.codingexercise.dto.EntryResponse;
import olis.codingexercise.dto.EntryUpdateRequest;
import olis.codingexercise.model.Entry;

@Component
public class EntryMapper {

    public static void updateEntryFromRequest(EntryUpdateRequest updateRequest, Entry entryToUpdate) {
        if ( !isBlank(updateRequest.getFirstName()) ) {
            entryToUpdate.setFirstName(updateRequest.getFirstName().trim());
        }
        if ( !isBlank(updateRequest.getLastName()) ) {
            entryToUpdate.setLastName(updateRequest.getLastName().trim());
        }
        if (updateRequest.getAge() != null) {
            entryToUpdate.setAge(updateRequest.getAge());
        }
        if (!isBlank(updateRequest.getTitle())) {
            entryToUpdate.setTitle(updateRequest.getTitle().trim());
        }
        if ( !isBlank(updateRequest.getHometown()) ) {
            entryToUpdate.setHometown(updateRequest.getHometown().trim());
        }
    }

    public static EntryResponse mapResponse(Entry entry) {
        EntryResponse entryResponse = new EntryResponse();
        // entryResponse.setEntryId(entry.getEntryId()); // Not returning Id!

        entryResponse.setFirstName(entry.getFirstName());
        entryResponse.setLastName(entry.getLastName());

        entryResponse.setAge(entry.getAge());
        entryResponse.setTitle(entry.getTitle());
        entryResponse.setHometown(entry.getHometown());

        return entryResponse;
    }

    public static boolean isBlank(String str) {
        // Utility class to check if the incoming string is empty or null!
        // Learning note:
            // Trims it first so empty spaces aren't treated as characters
            // which would cause isEmpty() to fail!
        return str == null || str.trim().isEmpty();
    }

}