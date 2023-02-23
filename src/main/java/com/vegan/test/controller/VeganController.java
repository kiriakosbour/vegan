package com.vegan.test.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vegan.test.domain.Item;
import com.vegan.test.domain.Root;
import com.vegan.test.service.intr.VeganServiceInterface;

@RestController
public class VeganController {
	@Autowired
	private VeganServiceInterface service;

	@PostMapping(value = "/conditions")
	public ResponseEntity<String> getValues(@RequestBody Root con) {
		Boolean resp = service.checkConditions(con.getCondition());
		if (!resp) {
			return new ResponseEntity<String>("The conditions are not acceptable", HttpStatus.NOT_ACCEPTABLE);
		} else {
			int index = 0;
			String resString = "";
			int[] profit = new int[con.getCondition().getItems().size() + 1];
			int[] weight = new int[con.getCondition().getItems().size() + 1];
			for (Item item : con.getCondition().getItems()) {
				index++;
				profit[index] = item.getPrice();
				weight[index] = item.getWeight();
			}
			// Integer size = service.maxProfit(profit, weight,
			// con.getCondition().getItems().size(), con.getCondition().getWeight());
			ArrayList<Integer> res = service.printknapSack(con.getCondition().getWeight(), weight, profit,
					con.getCondition().getItems().size());

			for (Item item : con.getCondition().getItems()) {
				for (Integer resInt : res) {
					if (item.getWeight() == resInt) {
						resString = Strings.concat(item.getId().toString(), " " + resString);
					}
				}

			}

			return new ResponseEntity<String>("The result is  " + resString, HttpStatus.OK);
		}

	}
}
