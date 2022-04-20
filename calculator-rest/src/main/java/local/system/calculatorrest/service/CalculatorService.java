package local.system.calculatorrest.service;

import java.math.BigDecimal;

public interface CalculatorService {

    public void setRequestId();
    public BigDecimal executeCalculator(BigDecimal a, BigDecimal b, String operation) throws NumberFormatException;
}
