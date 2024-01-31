package com.springboot.consumer.Controller;

import com.springboot.consumer.Service.ContractService;
import com.springboot.consumer.domain.Contract;
import com.springboot.consumer.domain.Enum.ContractStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    public ContractService contractService;


    @PostMapping("/create")
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract){

        if(contractService.existsValidContract()){
            Contract oldContact= contractService.getValidContract();
            oldContact.setContractStatus(ContractStatus.INVALID);
            contractService.save(oldContact);

            Contract newContract= contractService.save(contract);
            return new ResponseEntity<>(newContract, HttpStatus.OK);
        }else{
            Contract newContract= contractService.save(contract);

            return new ResponseEntity<>(newContract, HttpStatus.OK);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable("id") Long id,@RequestBody Contract contract){
        if(contractService.existsValidContract()){
            Contract oldContact= contractService.getValidContract();
            oldContact.setContractStatus(ContractStatus.INVALID);
            contractService.save(oldContact);

            Contract contractForUpdate= contractService.getById(id);
            contractForUpdate.setContractStatus(ContractStatus.INVALID);
            contractService.save(contractForUpdate);

            Contract newContract= new Contract();
            newContract.setCompanyId(contract.getCompanyId());
            newContract.setLatitude(contract.getLatitude());
            newContract.setLongitude(contract.getLongitude());
            newContract.setContractStatus(ContractStatus.VALID);
            newContract.setDate(contract.getDate());
            newContract.setAmount(contract.getAmount());
            newContract.setEquipmentType(contract.getEquipmentType());

            contractService.save(newContract);
            return new ResponseEntity<>(newContract, HttpStatus.OK);
        }else{

            Contract newContract= new Contract();
            newContract.setLatitude(contract.getLatitude());
            newContract.setLongitude(contract.getLongitude());
            newContract.setContractStatus(ContractStatus.VALID);
            newContract.setDate(contract.getDate());
            newContract.setAmount(contract.getAmount());
            newContract.setCompanyId(contract.getCompanyId());
            newContract.setEquipmentType(contract.getEquipmentType());

            contractService.save(newContract);
            return new ResponseEntity<>(newContract,HttpStatus.NO_CONTENT);
        }
    }
}
