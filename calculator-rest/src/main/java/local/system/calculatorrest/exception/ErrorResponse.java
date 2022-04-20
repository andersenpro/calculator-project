package local.system.calculatorrest.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private boolean error;
    private String message;
    private int statusCode;
}
