package com.pawlukowicz.empik.user.errorhandling;

import lombok.NonNull;
import lombok.Value;

@Value
public class ErrorResponse {
    int errorCode;
    @NonNull String message;
}
