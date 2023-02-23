package com.vegan.test.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.vegan.test.domain.Condition;
import com.vegan.test.domain.Item;
import com.vegan.test.service.intr.VeganServiceInterface;

@Service
public class VeganService implements VeganServiceInterface {
	static final Logger LOGGER = Logger.getLogger(VeganService.class.getName());

	@Override
	public Boolean checkConditions(Condition condition) {
		List<Item> listOfItems = condition.getItems();

		if (listOfItems.size() > 15 || condition.getWeight() > 100) {
			return false;
		}
		for (Item item : listOfItems) {
			int itemPrice = item.getPrice();
			int itemWeight = item.getWeight();
			if (itemPrice > 100 || itemWeight > 100.0) {
				return false;
			}
		}
		return true;

	}

	@Override
	public ArrayList<Integer> printknapSack(int W, int wt[], int val[], int n) {
		int i, w;
		int K[][] = new int[n + 1][W + 1];
		ArrayList<Integer> resArray = new ArrayList<Integer>();
// Build table K[][] in bottom up manner
		for (i = 0; i <= n; i++) {
			for (w = 0; w <= W; w++) {
				if (i == 0 || w == 0)
					K[i][w] = 0;
				else if (wt[i - 1] <= w)
					K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
				else
					K[i][w] = K[i - 1][w];
			}
		}

// stores the result of Knapsack
		int res = K[n][W];
		w = W;

		for (i = n; i > 0 && res > 0; i--) {

// either the result comes from the top
// (K[i-1][w]) or from (val[i-1] + K[i-1]
// [w-wt[i-1]]) as in Knapsack table. If
// it comes from the latter one/ it means
// the item is included.
			if (res == K[i - 1][w])
				continue;
			else {

// This item is included.
				resArray.add(wt[i - 1]);

// Since this weight is included its
// value is deducted
				res = res - val[i - 1];
				w = w - wt[i - 1];
			}
		}
		return resArray;
	}

}
