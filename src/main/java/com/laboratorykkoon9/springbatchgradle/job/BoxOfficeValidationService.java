package com.laboratorykkoon9.springbatchgradle.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoxOfficeValidationService {
    public static final String KEY_IS_REQUIRED = "key 파라미터는 필수값입니다.";
    public static final String DATE_IS_REQUIRED = "targetDt 파라미터는 필수값입니다.";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public void validateKey(String key) {
        if (ObjectUtils.isEmpty(key)) {
            throw new IllegalArgumentException(KEY_IS_REQUIRED);
        }
    }

    public void validateDate(String date) {
        if (ObjectUtils.isEmpty(date)) {
            throw new IllegalArgumentException(DATE_IS_REQUIRED);
        }
    }
}
