package com.example.medium_clone.application.common.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RandomStringGeneratorTest {

    @Test
    public void test() {
        // given
        RandomStringGenerator generator = new RandomStringGenerator();

        // when
        String randomString1 = generator.getRandomString(10);
        String randomString2 = generator.getRandomString(10);

        // then
        assertThat(randomString1).isNotEqualTo(randomString2);
    }

}