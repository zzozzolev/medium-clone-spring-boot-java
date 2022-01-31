package com.example.medium_clone.application.common.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RandomStringGeneratorTest {

    /*
    연속으로 두 번 만든 랜던 스트링이 서로 다른지 확인한다.
     */
    @Test
    public void testRandomness() {
        // given
        RandomStringGenerator generator = new RandomStringGenerator();

        // when
        String randomString1 = generator.getRandomString(10);
        String randomString2 = generator.getRandomString(10);

        // then
        assertThat(randomString1).isNotEqualTo(randomString2);
    }

}