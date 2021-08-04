/**
 * 
 */
package com.rbc.dbos.program.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;

import com.rbc.dbos.program.service.BasketService;
import com.rbc.dbos.program.service.Item;

/**
 * @author Pro-PC
 *
 */
public class BasketServiceImpl implements BasketService {

	Double totalCost;



	/** 
	 * method that takes a basket of items and return its total cost in 2 digit of decimal
	 * @return
	 * sum of total price in in maximum 2 digit after decimal
	 **/
	@Override
	public Double getTotalCost(List<Item> basketList, HashSet<Class> containedItems) {

		totalCost=new Double(0l);

		if(basketList!=null && !basketList.isEmpty()) {

			basketList.forEach( item -> {						
				if(containedItems!=null && containedItems.contains(item.getClass() )) { 
					totalCost+= Double.valueOf(item.getItemPrice());
				}else throw new IllegalArgumentException(" Illegal Item "+item.getClass().getSimpleName()+". Not Belong to basket.");
			});
		}else throw new IllegalStateException("Basket must not be empity..!! Please Add atlest One item in basket");

		return new BigDecimal(totalCost).setScale(2, RoundingMode.HALF_EVEN).doubleValue();	// return price in maximum 2 digit after decimal

	}

}
