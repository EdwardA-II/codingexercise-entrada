package olis.codingexercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryUpdateRequest {

    private String firstName;
    private String lastName;
    private Integer age;
    private String title;
    private String hometown;

}
