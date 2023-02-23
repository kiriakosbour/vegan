package com.vegan.test.service.intr;

import java.util.ArrayList;

import com.vegan.test.domain.Condition;

public interface VeganServiceInterface {
	public Boolean checkConditions(Condition condition);
	public ArrayList<Integer> printknapSack(int W, int wt[], int val[], int n);
}
