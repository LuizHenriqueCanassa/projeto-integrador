package br.com.luizcanassa.projetointegrador.domain.enums;

public enum OrderStatus {
    AWAITING(0),
    IN_PROCESSING(1),
    DELIVERING(2),
    DELIVERED(3);

    private final int statusValue;

    OrderStatus(int statusValue) {
        this.statusValue = statusValue;
    }

    public int getStatusValue() {
        return statusValue;
    }
}
