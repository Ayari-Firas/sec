package org.sid.Elearning.Exceptions;

public class NoFormationsFoundException extends RuntimeException { // Unchecked exception

    public NoFormationsFoundException(String message) {
        super(message);
    }
}
