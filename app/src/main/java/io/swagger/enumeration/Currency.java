package io.swagger.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Currency
 */
public enum Currency {
    TRY("TRY"), EUR("EUR"), USD("USD");

    private String value;

    Currency(final String value) {
        this.value = value;
    }

    @Override @JsonValue public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator public static Currency fromValue(final String text) {
        for (Currency b : Currency.values()) {
            if (String.valueOf(b.value)
                      .equals(text)) {
                return b;
            }
        }
        return null;
    }
}
