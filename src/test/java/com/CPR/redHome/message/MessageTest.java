package com.CPR.redHome.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootTest
public class MessageTest {

    @Autowired
    MessageSource messageSource;

    @Test
    @DisplayName("메세지 테스트")
    public void test() {

    String result = messageSource.getMessage("hello",null, null);
        Assertions.assertThat(result).isEqualTo("안녕");
        System.out.println("result = " + result);

    }
}
