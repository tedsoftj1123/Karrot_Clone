package com.example.karrotmarket.domain.controller.dto.req;

import com.example.karrotmarket.domain.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SignupRequest {
    @NotBlank(message = "id엔 공백과 뛰어쓰기를 포함 할 수 없습니다.")
    @Length(max = 20, message = "아이디는 20글자보다 짧아야합니다.")
    private String memberId;

    @NotBlank(message = "name은 공백과 띄어쓰기를 포함 할 수 없습니다.")
    @Length(max = 5, message = "회원입름은 5글자보다 짧아야합니다.")
    private String memberName;

    @NotBlank(message = "passowrd는 공백과 띄어쓰기를 포함 할 수 없습니다.")
    @Length(min = 5, max = 20, message = "비밀번호는 5글자보다 길고 20글자보다 짧아야합니다.")
    private String memberPassword;



    private Address address;
}
