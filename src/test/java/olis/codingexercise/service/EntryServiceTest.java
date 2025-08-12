package olis.codingexercise.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import olis.codingexercise.exception.EntryNotFoundException;
import olis.codingexercise.model.Entry;
import olis.codingexercise.repository.EntryRepository;

@ExtendWith(MockitoExtension.class)
class EntryServiceTest {

    @Mock
    private EntryRepository entryRepository;

    @InjectMocks
    private EntryService entryService;

    @Test
    void shouldReturnEntryWhenIdExists() {
        Long entryId = 1L;
        Entry expectedEntry = new Entry();
        expectedEntry.setEntryId(entryId);
        expectedEntry.setEntryId(entryId);


        when(entryRepository.findById(entryId)).thenReturn(Optional.of(expectedEntry));

        Entry result = entryService.findById(entryId);

        assertNotNull(result);
        assertEquals(expectedEntry, result);
        verify(entryRepository).findById(entryId);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        Long entryId = 1L;
        when(entryRepository.findById(entryId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> entryService.findById(entryId));
        verify(entryRepository).findById(entryId);
    }

    @Test
    void shouldSaveEntrySuccessfully() {
        Entry entryToSave = new Entry();
        Entry savedEntry = new Entry();
        when(entryRepository.save(entryToSave)).thenReturn(savedEntry);

        Entry result = entryService.save(entryToSave);

        assertNotNull(result);
        assertEquals(savedEntry, result);
        verify(entryRepository).save(entryToSave);
    }

    @Test
    void shouldDeleteEntryWhenIdExists() {
        Long entryId = 1L;
        when(entryRepository.existsById(entryId)).thenReturn(true);

        entryService.deleteEntry(entryId);

        verify(entryRepository).existsById(entryId);
        verify(entryRepository).deleteById(entryId);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentEntry() {
        Long entryId = 1L;
        when(entryRepository.existsById(entryId)).thenReturn(false);

        EntryNotFoundException deleteException = assertThrows(EntryNotFoundException.class, () -> entryService.deleteEntry(entryId));

        // First, check that existsById was called
        verify(entryRepository).existsById(entryId);

        // Then also, check that deleteById was never called
        verify(entryRepository, never()).deleteById(entryId);
    }
}