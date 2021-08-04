package com.rbc.dbos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rbc.dbos.program.model.Banana;
import com.rbc.dbos.program.model.BasketItem;
import com.rbc.dbos.program.model.Lemon;
import com.rbc.dbos.program.model.Orange;
import com.rbc.dbos.program.model.Peache;
import com.rbc.dbos.program.model.Apple;
import com.rbc.dbos.program.service.BasketService;
import com.rbc.dbos.program.service.Item;
import com.rbc.dbos.program.service.impl.BasketServiceImpl;

@SpringBootTest
class BasketApplicationTests {

	static BasketService basketService;
	static HashSet<Class> containedItems;
	static Double expetedTotalCost;
	static List<Item> basketList;


	@BeforeEach
	public void preProcessingBeforeExecuteTestCases() {

		basketService=new BasketServiceImpl();

		containedItems=new HashSet<Class>();


		//set of item allowed in basket 
		containedItems.add(Banana.class);
		containedItems.add(Orange.class);
		containedItems.add(Apple.class);
		containedItems.add(Lemon.class);
		containedItems.add(Peache.class);



		//adding allowed items in basket 
		basketList= new ArrayList<Item>();

		basketList.add(new Apple(50.50));
		basketList.add(new Banana(20.00));
		basketList.add(new Orange(10.50));
		basketList.add(new Peache(15.05));
		basketList.add(new Lemon(5.75));

		expetedTotalCost=new Double(101.80);

	}

	/**
	 *  Test Case For Checking Cost Of Single Basket
	 **/
	@Test	
	public void testCase_For_Checking_CostOfSingleBasket() {
		assertEquals(expetedTotalCost, basketService.getTotalCost(basketList,containedItems),"getTotalCost method should calculate all item price");        
	}


	/**
	 *  Test Case For Checking  Invalid  OR Empty Basket 
	 *  @return
	 *  IllegalStateException
	 **/
	@Test
	public void testCase_For_Checking_Invalid_OR_EmptyBasket() {

		basketList = null;						//create an invalid List
		assertThrows(IllegalStateException.class, ()-> basketService.getTotalCost(basketList,containedItems),"getTotalCost method should return IllegalStateException");

		basketList = new ArrayList<Item>();		//create an blank basket List
		assertThrows(IllegalStateException.class, ()-> basketService.getTotalCost(basketList,containedItems),"getTotalCost method should return IllegalStateException");

	}


	/**
	 *  Test Case For Check Empty List Of Allowed Item 
	 *  @return
	 *  IllegalArgumentException
	 **/
	@Test
	public void testCase_For_Checking_EmptyList_Of_AllowedItem() {
		containedItems = null;        
		assertThrows(IllegalArgumentException.class, ()-> basketService.getTotalCost(basketList,containedItems),"getTotalCost method should return IllegalArgumentException");
	}



	/**
	 *  Test Case For Checking Illegal Item Which Is Not Allow In Basket
	 *  @return
	 *  IllegalArgumentException 
	 **/
	@Test
	public void testCase_For_Checking_IllegalItem_In_Basket() {

		basketList.add(new Papaya(10.25));		//adding a new item which is not allow in basket
		assertThrows(IllegalArgumentException.class, ()-> basketService.getTotalCost(basketList,containedItems),"getTotalCost method should return IllegalArgumentException");
	}



}


/**
 *  Papaya i.e Item which is not allow in Basket List  
 **/
class Papaya extends BasketItem{

	public Papaya(Double price) {
		super(price);
		// TODO Auto-generated constructor stub
	}

}

