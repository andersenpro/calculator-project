package local.system.common.core.enumerator;

public enum PropertiesEnum {

    REQUEST_ID("requestId"),
    CALC_DIRECT_EXCHANGE("calc-direct-exchange");

    private final String property;

    PropertiesEnum(String property) {
        this.property = property;
    }

    public String getMessage() {
        return this.property;
    }
}
