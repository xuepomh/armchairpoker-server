package com.armchairfun.poker.common;

import com.armchairfun.testutils.ArmchairFunTestCase;


public class TestErrorIds extends ArmchairFunTestCase {

	public static final int UNKNOWN = 0;
	public static final int NO_USER_FOUND = 1;
	public static final int UNRECOGNIZED_ACTION = 2;
	public static final int NO_TABLES_FOUND = 3;
	public static final int NOT_ENOUGH_PLAYERS_FOR_ROUND = 4;
	public static final int ROUND_PLAYERS_IS_NULL = 5;
	public static final int PLAYER_ALREADY_IN_ROUND = 6;
	public static final int CANNOT_ASSIGN_DEALER = 7;
	public static final int CANNOT_PARSE_XML = 8;
	public static final int INVALID_SEARCH_OPERATION = 9;
	public static final int TABLE_FULL = 10;
	
	public void testErrorIds() {
		assertEquals(ErrorIds.UNKNOWN, UNKNOWN);
		assertEquals(ErrorIds.NO_USER_FOUND, NO_USER_FOUND);
		assertEquals(ErrorIds.UNRECOGNIZED_ACTION, UNRECOGNIZED_ACTION);
		assertEquals(ErrorIds.NO_TABLES_FOUND, NO_TABLES_FOUND);
		assertEquals(ErrorIds.NOT_ENOUGH_PLAYERS_FOR_ROUND, NOT_ENOUGH_PLAYERS_FOR_ROUND);
		assertEquals(ErrorIds.ROUND_PLAYERS_IS_NULL, ROUND_PLAYERS_IS_NULL);
		assertEquals(ErrorIds.CANNOT_ASSIGN_DEALER, CANNOT_ASSIGN_DEALER);
		assertEquals(ErrorIds.CANNOT_PARSE_XML, CANNOT_PARSE_XML);
		assertEquals(ErrorIds.INVALID_SEARCH_OPERATION, INVALID_SEARCH_OPERATION);
		assertEquals(ErrorIds.TABLE_FULL, TABLE_FULL);
	}
	
}
