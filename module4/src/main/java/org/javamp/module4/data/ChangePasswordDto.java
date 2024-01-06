package org.javamp.module4.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordDto {
    private String userName;
    private String oldPassword;
    private String newPassword;
}
