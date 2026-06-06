package com.soaint.ms_main.constants;

public interface Regex {
    String ONLY_CHARS = "^[a-zA-Z]+$";
    String ONLY_CHARS_WITH_SPACE = "^[a-zA-Z ]+$";
    String ONLY_CURRENCY = "^\\d{1,6}\\.\\d{2}$";
    String PATTERN_SECRET = "^[^\\s]+$";
}
