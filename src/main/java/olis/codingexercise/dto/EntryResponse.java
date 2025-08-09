package olis.codingexercise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntryResponse {

    private Long userId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String title;
    private String hometown;
}
