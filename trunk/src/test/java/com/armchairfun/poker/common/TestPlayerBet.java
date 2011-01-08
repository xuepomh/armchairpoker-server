package com.armchairfun.poker.common;

import java.math.BigDecimal;

import com.armchairfun.testutils.ArmchairFunTestCase;

public class TestPlayerBet extends ArmchairFunTestCase {


	public void testConstructor() {
		User u1 = new User();
		u1.setId(1);
		PlayerBet bet = new PlayerBet(u1, new BigDecimal(400));
		assertNotNull(bet);
		assertEquals(u1, bet.getPlayer());
		assertEquals(400, bet.getAmount().intValue());
		User u2 = new User();
		u2.setId(2);
		bet.setPlayer(u2);
		assertEquals(u2, bet.getPlayer());
		bet.setAmount(new BigDecimal(800));
		assertEquals(800, bet.getAmount().intValue());		
		
	}

}
