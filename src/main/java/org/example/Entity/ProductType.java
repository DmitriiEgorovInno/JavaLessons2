package org.example.Entity;

import jakarta.persistence.Convert;

@Convert
public enum ProductType {

    CARD("0","Карта"),
    ACCOUNT("1","Счет");

    private final String code;
    private final String desciption;

    ProductType(String code, String desciption) {
        this.code = code;
        this.desciption = desciption;
    }

    public String getDesciption() {
        return desciption;
    }

    public String getCode() {
        return code;
    }

    public static ProductType getByCode(String code){
        for (ProductType type: values()){
            if (type.getCode().equals(code)){
                return type;
            }
        }
        throw new IllegalArgumentException("Неверное значение поля product_type "+code);
    }
}
