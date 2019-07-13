package com.fyle.assignment.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fyle.assignment.dao.BankRepository;
import com.fyle.assignment.model.Bank;
import com.fyle.assignment.util.ReflectionUtil;

@RestController
@CrossOrigin
@RequestMapping(path = "/object/bank")
public class BankController {

    @Autowired
    private BankRepository bankRepository;

    ReflectionUtil refUtil = ReflectionUtil.getInstance();

    @PostMapping(path = "/create")
    public ResponseEntity<?> createBankDetails(@Valid @RequestBody Bank bank, BindingResult result) {
	if (result != null && result.hasErrors()) {
	    Map<String, String> error = new LinkedHashMap<String, String>();
	    for (FieldError fieldError : result.getFieldErrors()) {
		error.put(fieldError.getField(), fieldError.getDefaultMessage());
	    }
	    return new ResponseEntity<Map<String, String>>(error, HttpStatus.BAD_REQUEST);
	}
	Bank bankResult = bankRepository.save(bank);
	return new ResponseEntity<Bank>(bankResult, HttpStatus.OK);
    }

    @GetMapping(path = "/getBankByIFSC") // Pageable pagable no need because returns single element...!!!!
    public ResponseEntity<?> getBankDetailsByIFSC(@Valid @RequestParam(value = "ifsc", required = true) String ifsc) {
	Bank bank = bankRepository.getFetchBankDetailsByIFSC(ifsc);
	System.out.println(bank);
	if (bank == null) {
	    return new ResponseEntity<String>("IFSC:- " + ifsc + " Does't Exist", HttpStatus.BAD_REQUEST);
	}
	return new ResponseEntity<Bank>(bank, HttpStatus.OK);
    }

    @GetMapping(path = "/getBankByTwoParam")
    public ResponseEntity<?> getBankDetailsByTwoParam(@RequestParam(value = "bank_name") String bank_name,
	    @RequestParam(value = "city") String city, @RequestParam(value="limit") Long limit, @RequestParam(value="offset") Long offset) {
	List<Bank> bankFromDB = bankRepository.getBankDetailsByTwoParam(bank_name, city, limit, offset);
	if (bankFromDB == null) {
	    return new ResponseEntity<String>("No such BankDetails Exist", HttpStatus.BAD_REQUEST);
	}
	return new ResponseEntity<List<Bank>>(bankFromDB, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteBankDetails(@PathVariable(value = "id") Long id) {
	Bank bankFromDB = bankRepository.getBankByBankId(id);
	if (bankFromDB == null) {
	    return new ResponseEntity<String>("No BankDetails for id:- " + id + " Found", HttpStatus.BAD_REQUEST);
	}
	bankRepository.softDeleteBankDetails(id);
	return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
    }

    @PatchMapping(path = "/udate/{id}")
    public ResponseEntity<?> updateBankDetails(@Valid @RequestBody String bank, @PathVariable(value = "id") Long id)
	    throws ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	Bank bankFromDB = bankRepository.getBankByBankId(id);
	if (bankFromDB == null) {
	    return new ResponseEntity<String>("No sub bankDetails with id :- " + id + " Found", HttpStatus.BAD_REQUEST);
	}

	JSONParser parser = new JSONParser();
	JSONObject obj = (JSONObject) parser.parse(bank);
	for (Iterator iterator = ((Map<String, String>) obj).keySet().iterator(); iterator.hasNext();) {
	    String props = (String) iterator.next();
	    refUtil.getSetterMethod("Bank", props).invoke(obj.get(props));

	}
	bankRepository.save(bankFromDB);
	return new ResponseEntity<Bank>(bankFromDB, HttpStatus.OK);
    }
}
