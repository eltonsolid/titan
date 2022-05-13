package br.com.elementi.core.tools;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import br.com.elementi.core.Unit;
import br.com.elementi.core.tools.Display;

public class DisplayTest extends Unit {

	private DateTime dateTime = new DateTime(1900, 1, 1, 0, 0);

	@Test
	public void testDate() throws Exception {
		assertEquals("01/01/1900", Display.date(dateTime));
	}

	@Test
	public void testDatePlusDays() throws Exception {
		assertEquals("25/01/1900", Display.date(dateTime.plusDays(24)));
	}

	@Test
	public void testDateTime() throws Exception {
		assertEquals("01/01/1900 00:00", Display.dateTime(dateTime));
	}

	@Test
	public void testDateTimeSecond() throws Exception {
		assertEquals("01/01/1900 00:00:00", Display.dateTimeSecond(dateTime));
	}

	@Test
	public void testDateTimePlusHour() throws Exception {
		assertEquals("01/01/1900 12:00", Display.dateTime(dateTime.plusHours(12)));
	}

	@Test
	public void testDateTimePlusHourMinute() throws Exception {
		assertEquals("01/01/1900 12:15", Display.dateTime(dateTime.plusHours(12).plusMinutes(15)));
	}

	@Test
	public void testTime() throws Exception {
		assertEquals("16:20", Display.time(dateTime.plusHours(16).plusMinutes(20)));
	}

	@Test
	public void testTimeSecond() throws Exception {
		assertEquals("16:20:10", Display.timeSecond(dateTime.plusHours(16).plusMinutes(20).plusSeconds(10)));
	}

	@Test
	public void testDuration() throws Exception {
		Duration duration = new Duration(dateTime.getMillis(), dateTime.plusHours(72).getMillis());
		assertEquals("72:00", Display.duration(duration));
	}

	@Test
	public void testDurationMinutes() throws Exception {
		Duration duration = new Duration(dateTime.getMillis(), dateTime.plusHours(72).plusMinutes(10).getMillis());
		assertEquals("72:10", Display.duration(duration));
	}

	@Test
	public void testDurationMonth() throws Exception {
		Duration duration = new Duration(dateTime.getMillis(), dateTime.plusDays(20).getMillis());
		assertEquals("480:00", Display.duration(duration));
	}

	@Test
	public void testDoubles() throws Exception {
		Double value = 0.23;
		assertEquals("0.23", Display.doubles(value));
	}

}
