package org.acme.getting.started;

import java.util.StringJoiner;

import javax.ws.rs.core.MultivaluedMap;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class FilterHelper {

    static String getHeadersAsString(MultivaluedMap<String, ?> headers) {
        StringJoiner headerJoiner = new StringJoiner(", ");
        headers.forEach((key, value) -> headerJoiner.add(key + "=" + value));
        return headerJoiner.toString();
    }
}
