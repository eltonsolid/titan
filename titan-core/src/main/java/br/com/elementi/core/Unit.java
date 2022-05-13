package br.com.elementi.core;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import br.com.elementi.core.junit.UnitRunner;

@RunWith(UnitRunner.class)
public abstract class Unit {

	protected static final Integer ZERO = 0;
	protected static final Integer ONE = 1;
	protected static final Integer TWO = 2;
	protected static final Integer TREE = 3;
	protected static final Integer FOUR = 4;
	protected static final Integer FIVE = 5;

	protected static final String NAME = "NAME";
	protected static final String NAME_1 = "NAME_1";
	protected static final String NAME_2 = "NAME_2";
	protected static final String DESCRIPTION = "DESCRIPTION";
	protected static final String DESCRIPTION_1 = "DESCRIPTION_1";
	protected static final String DESCRIPTION_2 = "DESCRIPTION_2";

	protected static final List<?> EMPTY_LIST = Collections.EMPTY_LIST;
	protected static final Set<?> EMPTY_SET = Collections.EMPTY_SET;
	protected static final Map<?, ?> EMPTY_MAP = Collections.EMPTY_MAP;

}
