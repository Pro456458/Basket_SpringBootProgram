/**
 * 
 */
package com.rbc.dbos.program.service;

import java.util.HashSet;
import java.util.List;

/**
 * @author Pro-PC
 *
 */
public interface BasketService {
	
	Double getTotalCost(List<Item> basketList, HashSet<Class> containedItems);

}
