package com.bikkadIT.electronicStore.payloads;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {


    private String userId;

    @Size(min = 4, max = 15, message = "Please Enter User Valid Name!")
    @NotBlank
    private String name;

    @Email(message = "Email id not according to standard!")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[a-z1-9]{6}")
    private String password;

    @NotBlank(message = "Please specify valid gender")
    @Size(min = 4, max = 8, message = "Min length for the gender is 4 and maximun is 8!")
    private String gender;

    @Size(min = 18, max = 30, message = "Please tell us something about User!")
    private String about;

    private String imageName;
}
