package com.spertus.jacquard.exceptions;

/**
 * Signals that an internal error occurred within the autograder.
 */
public class InternalException extends AutograderException {
    /**
     * Constructs an {@code InternalException} with the specified
     * message.
     *
     * @param message an explanation
     */
    public InternalException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code InternalException} with the specified
     * message and cause.
     *
     * @param message an explanation
     * @param cause the underlying cause
     */
    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an {@code InternalException} with the specified cause.
     *
     * @param cause the underlying cause
     */
    public InternalException(Throwable cause) {
        super("An internal autograder error occurred.", cause);
    }
}