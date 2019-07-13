package com.fyle.assignment.dataimport.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fyle.assignment.controller.BankController;
import com.fyle.assignment.dao.BankRepository;
import com.fyle.assignment.model.Address;
import com.fyle.assignment.model.Bank;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/import/bank")
@CrossOrigin
@Api(description = "Import data from BankCSV file")
public class BankDataImportController {
    private static final Logger logger = LogManager.getLogger(BankDataImportController.class);

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BankController bankController;
    @Autowired
    private ObjectMapper objectMapper; // Added

    @PostConstruct
    public void setUp() {
	objectMapper.registerModule(new JavaTimeModule());
    }

    @PostMapping("/csv")
    public String getCSV(@RequestBody String request)
	    throws ParseException, JsonParseException, JsonMappingException, ValidationException, IOException {
	JSONObject obj = new JSONObject(request);
	String FileName = obj.getString("FileName");
	String path = FileName;
	String csvFile = FileName;
	String line = "";
	String cvsSplitBy = ",";
	List<String[]> CSV_DATA = new ArrayList<String[]>();
	try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	    while ((line = br.readLine()) != null) {
		String strLine = "";
		String[] str = new String[8];
		int start = 0;
		int end = 0;
		int count = 0;
		int startIndex = 0;

		for (int iter = 0; iter < line.length(); iter++) {
		    if (line.charAt(iter) != ',') {
			strLine = strLine + line.charAt(iter);
		    } else {
			str[startIndex] = strLine;
			startIndex++;
			strLine = "";
			count++;
			if (count == 3) {
			    start = iter + 2;
			    break;
			}
		    }
		}
		startIndex = 7;
		count = 0;
		for (int jter = line.length() - 1; jter >= 0; jter--) {
		    if (line.charAt(jter) != ',') {
			strLine = strLine + line.charAt(jter);
		    } else {

			StringBuffer sbr = new StringBuffer(strLine);
			sbr.reverse();
			str[startIndex] = sbr.toString();
			strLine = "";
			startIndex--;
			count++;
			if (count == 4) {
			    end = jter - 1;
			    break;
			}
		    }
		}
		str[3] = line.substring(start, end);
		CSV_DATA.add(str);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

	for (int i = 0; i < CSV_DATA.size(); i++) {

	    Bank bank = new Bank();
	    String[] data = {};
	    data = CSV_DATA.get(i);
	    String ifscCode = "";
	    if (!data[0].isEmpty()) {
		ifscCode = data[0];
		bank.setIfsc(data[0]);

	    }
	    if (!data[1].isEmpty()) {
		bank.setBankId(Long.parseLong(data[1]));
	    }
	    if (!data[2].isEmpty()) {
		bank.setBranch(data[2]);
	    }
	    Address address = new Address();

	    if (!data[3].isEmpty()) {
		address.setAddressLine1(data[3]);
	    }
	    if (!data[4].isEmpty()) {
		address.setCity(data[4]);
	    }
	    if (!data[5].isEmpty()) {
		address.setDistrict(data[5]);
	    }
	    if (!data[6].isEmpty()) {
		address.setState(data[6]);
	    }
	    bank.setAddress(address);
	    if (!data[7].isEmpty()) {
		bank.setBankName(data[7]);
	    }

	    if (bankRepository.getFetchBankDetailsByIFSC(ifscCode) == null) {
		bankController.createBankDetails(bank, null);
	    } else {
		System.out.println("Sorry It Already Exist in the DataBase...");
	    }

	}
	return "Successfull Imported Bank Data";

    }

}
