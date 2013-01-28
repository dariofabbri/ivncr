package it.ivncr.erp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	private static final String ITALIA = "(IT){0,1}(\\d{11})";

	private static int odd[] = { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3,
		6, 8, 12, 14, 16, 10, 22, 25, 24, 23 };


	public static boolean isPartitaIva(String s) {

		// Must be numeric, 11 digits.
		//
		Pattern p = Pattern.compile(ITALIA);
		Matcher m = p.matcher(s);
		if (!m.matches())
			return false;

		if (m.group(1) != null) {
			int index = m.end(1);
			s = s.substring(index);
		}

		// Verify check digit.
		//

		// Sum odd digits (including check digit).
		//
		int odd = 0;
		for (int i = 0; i < 11; i += 2) {
			int digit = s.getBytes()[i] - '0';
			odd += digit;
		}

		// Sum doubled even digits counting digits >= 5.
		//
		int upcnt = 0;
		int even = 0;
		for (int i = 1; i < 11; i += 2) {
			int digit = s.getBytes()[i] - '0';
			upcnt += digit >= 5 ? 1 : 0;
			even += digit * 2;
		}

		// Sum up everything.
		//
		int t = odd + even + upcnt;

		// Mod 10 must be zero.
		//
		return (t % 10) == 0;
	}


	public static boolean isCodiceFiscale(String s) {

		if (s.length() == 0)
			return false;

		if (s.length() != 16)
			return false;

		s = s.toUpperCase();

		for (int i = 0; i < 16; i++) {
			int c = s.charAt(i);
			if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'Z'))
				return false;
		}

		int sum = 0;

		for (int i = 1; i <= 13; i += 2) {
			int c = s.charAt(i);
			if (c >= '0' && c <= '9')
				sum = sum + c - '0';
			else
				sum = sum + c - 'A';
		}

		for (int i = 0; i <= 14; i += 2) {
			int c = s.charAt(i);
			if (c >= '0' && c <= '9')
				c = c - '0' + 'A';
			sum = sum + odd[c - 'A'];
		}

		return (sum % 26 + 'A' == s.charAt(15));
	}
}
