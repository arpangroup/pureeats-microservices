package com.arpangroup.exception;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected String errorCode;
    protected String errorMessage;

    public BaseException(){

    }

    public BaseException(String errorCode, String errorMessage) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BaseException(ExceptionReason exceptionReason) {
        super();
        this.errorCode = exceptionReason.value;
        this.errorMessage = exceptionReason.getReasonPhrase();
    }


    public  @NonNull FieldError getErrorMessage(){
        return new FieldError(this.errorCode, this.errorMessage);
    }


    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    public enum ExceptionReason {
        ID_NOT_FOUND("E700", Series.NOT_FOUND, "id not found"),
        REQUEST_NOT_FOUND("600", Series.BAD_REQUEST, "Request not found"),
        ILLEGAL_OPERATION("400", Series.BAD_REQUEST, "This operation is not permitted at this time"),
        TIMEOUT("400", Series.BAD_REQUEST, "Transaction timed out");


        private final String value;
        private final Series series;
        private final String reasonPhrase;

        ExceptionReason(String value, Series series, String reasonPhrase) {
            this.value = value;
            this.series = series;
            this.reasonPhrase = reasonPhrase;
        }


        /**
         * Return the integer value of this status code.
         */
        public String value() {
            return value;
        }

        /**
         * Return the HTTP status series of this status code.
         * @see HttpStatus.Series
         */
        public Series series() {
            return this.series;
        }

        /**
         * Return the reason phrase of this status code.
         */
        public String getReasonPhrase() {
            return this.reasonPhrase;
        }

    }

    public enum Series {
        BAD_REQUEST(6),
        NOT_FOUND(7),
        REDIRECTION(8),
        SERVER_ERROR(9);
        private final int value;

        Series(int value) {
            this.value = value;
        }

        /**
         * Return the integer value of this status series. Ranges from 1 to 5.
         */
        public int value() {
            return this.value;
        }


        /**
         * Resolve the given status code to an {@code HttpStatus.Series}, if possible.
         * @param statusCode the HTTP status code (potentially non-standard)
         * @return the corresponding {@code Series}, or {@code null} if not found
         * @since 5.1.3
         */
        @Nullable
        public static Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            for (Series series : values()) {
                if (series.value == seriesCode) {
                    return series;
                }
            }
            return null;
        }

    }
}
