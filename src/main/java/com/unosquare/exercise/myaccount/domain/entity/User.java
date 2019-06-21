package com.unosquare.exercise.myaccount.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

/**
 * @author frils
 *
 */
@Getter
@Setter
@Embeddable
public class User {

    @NotBlank
    @Column(name = "id_number")
    @Length(min = 4)
    private String idNumber;
    @NotBlank
    @Column(name = "first_name")
    @Length(min = 2)
    private String firstName;
    @NotBlank
    @Length(min = 2)
    @Column(name = "last_name")
    private String lastName;

}
