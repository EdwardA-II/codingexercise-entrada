package olis.codingexercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryResponse {

    // Don't want to return the ID bc might be sensitive data/info
    // private Long entryId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String title;
    private String hometown;
}
