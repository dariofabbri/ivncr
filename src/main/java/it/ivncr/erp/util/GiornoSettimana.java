package it.ivncr.erp.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum GiornoSettimana {

	Lunedi,
	Martedi,
	Mercoledi,
	Giovedi,
	Venerdi,
	Sabato,
	Domenica;


	public static GiornoSettimana fromDate(Date date) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);

		switch(gc.get(Calendar.DAY_OF_WEEK)) {

			case Calendar.MONDAY:
				return GiornoSettimana.Lunedi;

			case Calendar.TUESDAY:
				return GiornoSettimana.Martedi;

			case Calendar.WEDNESDAY:
				return GiornoSettimana.Mercoledi;

			case Calendar.THURSDAY:
				return GiornoSettimana.Giovedi;

			case Calendar.FRIDAY:
				return GiornoSettimana.Venerdi;

			case Calendar.SATURDAY:
				return GiornoSettimana.Sabato;

			case Calendar.SUNDAY:
				return GiornoSettimana.Domenica;

			default:
				return null;
		}
	}


	@Override
	public String toString() {

		switch(this) {

			case Lunedi:
				return "Lunedì";

			case Martedi:
				return "Martedì";

			case Mercoledi:
				return "Mercoledì";

			case Giovedi:
				return "Giovedì";

			case Venerdi:
				return "Venerdì";

			case Sabato:
				return "Sabato";

			case Domenica:
				return "Domenica";

			default:
				return null;
		}
	}
}
