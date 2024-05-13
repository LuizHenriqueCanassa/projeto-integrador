package br.com.luizcanassa.projetointegrador.domain.enums;

public enum OrderStatus {
    AWAITING(0, "Aguardando"),
    IN_PROCESSING(1, "Em processo"),
    DELIVERING(2, "Saiu para Entrega"),
    DELIVERED(3, "Entregue"),
    CANCELED(4, "Cancelado");

    private final int statusValue;
    private final String description;

    OrderStatus(int statusValue, String description) {
        this.statusValue = statusValue;
        this.description = description;
    }

    public int getStatusValue() {
        return statusValue;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus statusValueToEnum(int statusValue) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getStatusValue() == statusValue) {
                return orderStatus;
            }
        }

        return null;
    }
}
