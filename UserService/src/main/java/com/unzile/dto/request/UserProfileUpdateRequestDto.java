package com.unzile.dto.request;

import com.unzile.repository.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileUpdateRequestDto {
     @NotEmpty
     @Size(min=5)
    String token;
    String name;
    String surname;
    String phone;
    String address;
    String avatar;
    Gender gender;
}
