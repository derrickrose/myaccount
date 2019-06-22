package com.unosquare.exercise.myaccount.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

/**
 * Used to save user information.
 * @author frils
 */
@ApiModel(value = "Entity user information.")
@Getter
@Setter
@Embeddable
public class User {

    @ApiModelProperty("Identification number for the user.")
    @NotBlank
    @Column(name = "id_number", unique = true)
    @Length(min = 4)
    private String idNumber;

    @ApiModelProperty("First name.")
    @NotBlank
    @Column(name = "first_name")
    @Length(min = 2)
    private String firstName;

    @ApiModelProperty("User last name.")
    @NotBlank
    @Length(min = 2)
    @Column(name = "last_name")
    private String lastName;

}
