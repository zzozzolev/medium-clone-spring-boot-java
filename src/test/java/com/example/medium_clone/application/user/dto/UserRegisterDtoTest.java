package com.example.medium_clone.application.user.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void NotAllowUsernameNull() {
        // given
        UserRegisterDto dto = getDto();
        dto.setUsername(null);

        // when
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(dto);

        // then
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void NotAllowUsernameEmpty() {
        // given
        UserRegisterDto dto = getDto();
        dto.setUsername("");

        // when
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(dto);

        // then
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void NotAllowUsernameBlank() {
        // given
        UserRegisterDto dto = getDto();
        dto.setUsername(" ");

        // when
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(dto);

        // then
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void NotAllowPasswordNull() {
        // given
        UserRegisterDto dto = getDto();
        dto.setPassword(null);

        // when
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(dto);

        // then
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void NotAllowPasswordEmpty() {
        // given
        UserRegisterDto dto = getDto();
        dto.setPassword("");

        // when
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(dto);

        // then
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void NotAllowPasswordBlank() {
        // given
        UserRegisterDto dto = getDto();
        dto.setPassword(" ");

        // when
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(dto);

        // then
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void NotAllowInvalidFormEmail() {
        // given
        UserRegisterDto dto = getDto();
        dto.setEmail("test");

        // when
        Set<ConstraintViolation<UserRegisterDto>> violations = validator.validate(dto);

        // then
        Assertions.assertThat(violations.size()).isEqualTo(1);
    }

    /**
     * 정상적인 DTO를 반환한다.
     */
    private UserRegisterDto getDto() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setUsername("test");
        dto.setPassword("password");
        dto.setEmail("test@email.com");
        return dto;
    }

}