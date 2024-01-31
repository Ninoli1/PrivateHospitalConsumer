package com.springboot.consumer.Service.impl;

import com.springboot.consumer.Repository.ContractRepository;
import com.springboot.consumer.Service.ContractService;
import com.springboot.consumer.domain.Contract;
import com.springboot.consumer.domain.Enum.ContractStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    public ContractRepository contractRepo ;
    public Contract save(Contract contract){
       return  contractRepo.save(contract);
    }

    public Contract getById(Long id){
       return  contractRepo.getById(id);
    }
    public Boolean existsValidContract(){
        return contractRepo.existsValidContract(ContractStatus.VALID);
    }

    public Contract getValidContract(){
        return contractRepo.getValidContract(ContractStatus.VALID);
    }

}
