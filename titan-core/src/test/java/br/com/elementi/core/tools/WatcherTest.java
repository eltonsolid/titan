package br.com.elementi.core.tools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.exception.NotAllowException;
import br.com.elementi.core.exception.NotExpectedException;
import br.com.elementi.core.exception.WatcherException;
import br.com.elementi.test.DummyWatcher;

@Ignore
public class WatcherTest extends Unit {

	private DummyWatcher dummyWatcher;

	@Before
	public void before() throws Exception {
		dummyWatcher = DummyWatcher.create();
	}

	@Test
	public void testThat() throws Exception {
		Watcher.that(dummyWatcher, WatcherException.class).count();
		assertEquals(ONE.intValue(), dummyWatcher.getCount());
	}

	@Test(expected = NotAllowException.class)
	public void testThatOnObject() throws Exception {
		Watcher.that(new Object(), WatcherException.class);
	}

	@Test(expected = WatcherException.class)
	public void testThatOnException() throws Exception {
		Watcher.that(dummyWatcher, WatcherException.class).domainException();
	}

	@Test(expected = NotExpectedException.class)
	public void testThatOnNotExpectedException() throws Exception {
		DummyWatcher that = Watcher.that(dummyWatcher, WatcherException.class);
		that.exception();
	}

}
