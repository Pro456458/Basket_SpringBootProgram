/**
 * 
 */
package com.rbc.dbos.program.model;

import java.util.Objects;

import com.rbc.dbos.program.service.Item;

/**
 * @author Pro-PC
 *
 */
public class BasketItem implements Item{

	private Double price;

	public BasketItem(Double price) {
		this.price = price;
	}


	@Override
	public Double getItemPrice() {		
		return price;
	}


	@Override
	public int hashCode() {
		return Objects.hash(price);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BasketItem other = (BasketItem) obj;
		return Objects.equals(price, other.price);
	}




}







