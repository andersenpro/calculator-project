package local.system.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OperandsDTO implements Serializable {
    private BigDecimal a;
    private BigDecimal b;
}
