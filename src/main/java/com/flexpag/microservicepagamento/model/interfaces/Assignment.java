package com.flexpag.microservicepagamento.model.interfaces;

import com.flexpag.microservicepagamento.model.enums.ContractTypeEnum;

import java.util.List;

public interface Assignment{

   public List<Double> getFees(ContractTypeEnum contractType);
}
