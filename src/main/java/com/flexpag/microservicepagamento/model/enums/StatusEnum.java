package com.flexpag.microservicepagamento.model.enums;

public enum StatusEnum {

    PAID,
    PENDING,
    NOT_AUTHORIZED,
    CANCELED;

    private Integer pos;

    // StatusEnum(Integer pos){
    //     this.pos = pos;
    // }


    // public static StatusEnum getPos(Integer pos) {
    //     switch(pos){
    //         case 0:
    //             return StatusEnum.PAID;
    //             break;
    //         case 1:
    //             return StatusEnum.PENDING;
    //             break;
    //         case 2:
    //             return StatusEnum.NOT_AUTHORIZED;
    //             break;
    //         case 3:
    //             return StatusEnum.CANCELED;
    //             break;
    //         default:
    //             return "erro";
    //         }
    // }
}
