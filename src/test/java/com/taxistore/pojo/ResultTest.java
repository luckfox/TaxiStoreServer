package com.taxistore.pojo;

import com.utils.Language2Utils;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    void setErrorMessage() {
//        Result.setLocale(Locale.forLanguageTag("zh-TW")); // 設置為台灣繁體
//        Result<String> result1 = new Result<>("error.not_found");
//        System.out.println(result1);

        Language2Utils.setLocale("zh_TW");
        Result<String> result2 = new Result<>("invalid.input", "hello");
        System.out.println(result2);
    }
}