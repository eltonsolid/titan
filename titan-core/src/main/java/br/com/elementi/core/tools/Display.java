package br.com.elementi.core.tools;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public final class Display {

	private static final String DATE = "dd/MM/yyyy";
	private static final String DATE_TIME = "dd/MM/yyyy HH:mm";
	private static final String DATE_TIME_SEG = "dd/MM/yyyy HH:mm:ss";
	private static final String TIME = "HH:mm";
	private static final String TIME_SEG = "HH:mm:ss";
	private static final DecimalFormat DECIMAL_US = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
	private static final PeriodFormatter FORMATTER = new PeriodFormatterBuilder()//
			.printZeroAlways().appendHours()//
			.appendPrefix(":")//
			.printZeroAlways().minimumPrintedDigits(2).appendMinutes()//
			.toFormatter();

	public static String date(DateTime dateTime) {
		return dateTime.toString(DATE);
	}

	public static String dateTime(DateTime dateTime) {
		return dateTime.toString(DATE_TIME);
	}

	public static String dateTimeSecond(DateTime dateTime) {
		return dateTime.toString(DATE_TIME_SEG);
	}

	public static String time(DateTime dateTime) {
		return dateTime.toString(TIME);
	}

	public static String timeSecond(DateTime dateTime) {
		return dateTime.toString(TIME_SEG);
	}

	public static String duration(Duration duration) {
		return duration.toPeriod().toString(FORMATTER);
	}

	public static String doubles(Double value) {
		return DECIMAL_US.format(value);
	}

}
