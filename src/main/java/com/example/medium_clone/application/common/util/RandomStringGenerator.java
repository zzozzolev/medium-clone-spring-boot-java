package com.example.medium_clone.application.common.util;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomStringGenerator {

    // a ~ z
    private static final List<Integer> asciiCharRange = IntStream.rangeClosed(97, 122)
            .boxed()
            .collect(Collectors.toList());
    // 0 ~ 9
    private static final List<Integer> asciiIntRange = IntStream.rangeClosed(48, 57)
            .boxed()
            .collect(Collectors.toList());
    private static final List<Integer> ascii = Stream.of(asciiCharRange, asciiIntRange)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

    public String getRandomString(int size) {
        Random random = new Random();
        String s = "";
        for (int i = 0; i < size; ++i) {
            s += (char) ascii.get(random.nextInt(ascii.size())).intValue();
        }
        return s;
    }

}
