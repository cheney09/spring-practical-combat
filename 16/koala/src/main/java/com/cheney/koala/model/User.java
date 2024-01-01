package com.cheney.koala.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotBlank(message = "姓名不能为空")
    private String name;

    private String gender;

    @NotNull(message = "年龄不能为空")
    @Min(value = 18, message = "年龄不能小于18岁")
    private Integer age;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "手机号必须是11位数字")
    private String phoneNumber;

    @NotBlank(message = "邮箱号不能为空")
    @Email(message = "邮箱号格式不正确")
    private String email;
}
