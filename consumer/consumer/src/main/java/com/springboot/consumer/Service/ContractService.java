package com.springboot.consumer.Service;

import com.springboot.consumer.domain.Contract;

public interface ContractService {

    public Contract save(Contract contract);

    public Contract getById(Long id);

    public Boolean existsValidContract();

    public Contract getValidContract();
}
