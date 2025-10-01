package com.phellipe.workoutplanner.service.handler;

import java.time.LocalDateTime;

public record ErrorResponse(
   String message,
   int status,
   LocalDateTime timestamp,
   String path
) {}
