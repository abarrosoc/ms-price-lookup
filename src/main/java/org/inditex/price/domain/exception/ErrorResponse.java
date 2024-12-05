package org.inditex.price.domain.exception;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorResponse
{
    private static final String PATTERN_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                                                                        .withZone(ZoneId.systemDefault());

    public ErrorResponse(HttpStatus status,
                         String message)
    {
        this.status = status;
        this.message = message;
        this.timestamp = formatter.format(Instant.now());
    }

    private HttpStatus status;

    private String message;

    private String timestamp;




}
