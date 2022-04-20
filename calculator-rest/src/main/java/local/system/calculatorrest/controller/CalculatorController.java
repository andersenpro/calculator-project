package local.system.calculatorrest.controller;

import local.system.calculatorrest.config.RabbitConfig;
import local.system.calculatorrest.dto.ResponseDTO;
import local.system.calculatorrest.exception.DivisionByZeroException;
import local.system.calculatorrest.service.CalculatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CalculatorController extends RabbitConfig {

    private final CalculatorService calculatorService;

    @GetMapping(value = "/sum")
    public ResponseEntity<ResponseDTO> sum(@RequestParam("a") BigDecimal a,
                                           @RequestParam("b") BigDecimal b)
            throws NumberFormatException {
        BigDecimal response = calculatorService.executeCalculator(a, b, sumOperatorProp);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(response), HttpStatus.OK);
    }

    @GetMapping(value = "/subtract")
    public ResponseEntity<ResponseDTO> subtract(@RequestParam("a") BigDecimal a,
                                                @RequestParam("b") BigDecimal b)
            throws NumberFormatException {
        BigDecimal response = calculatorService.executeCalculator(a, b, subtractOperatorProp);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(response), HttpStatus.OK);
    }

    @GetMapping(value = "/divide")
    public ResponseEntity<ResponseDTO> divide(@RequestParam("a") BigDecimal a,
                                              @RequestParam("b") BigDecimal b)
            throws DivisionByZeroException, NumberFormatException {
        if ((b.equals(BigDecimal.valueOf(0))) || (b == null)) {
            throw new DivisionByZeroException("Division by zero forbidden");
        }
        BigDecimal response = calculatorService.executeCalculator(a, b, divideOperatorProp);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(response), HttpStatus.OK);
    }

    @GetMapping(value = "/multiply")
    public ResponseEntity<ResponseDTO> multiply(@RequestParam("a") BigDecimal a,
                                                @RequestParam("b") BigDecimal b)
            throws NumberFormatException {
        BigDecimal response = calculatorService.executeCalculator(a, b, multiplyOperatorProp);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(response), HttpStatus.OK);
    }
}
