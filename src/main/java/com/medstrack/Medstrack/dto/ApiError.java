package com.medstrack.Medstrack.dto;

import java.util.Map;

public record ApiError(
        boolean success,
        String error,
        String message,
        Map<String, String> fields
) {}
