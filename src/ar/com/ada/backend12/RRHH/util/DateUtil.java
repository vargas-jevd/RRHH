package ar.com.ada.backend12.RRHH.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Convierte un String en el formato YYYY-MM-DD en un objeto java.util.Date.
	 */
	public static Date parse(String date) throws Exception {
		Date output = null;
		try {
			output = SDF.parse(date);
		} catch (ParseException e) {
			throw new Exception("Formato de fecha inválido. El formato esperado es YYYY-MM-DD");
		}
		return output;
	}
	
	/**
	 * Convierte un objeto java.util.Date en un String con el formato YYYY-MM-DD.
	 */
	public static String format(Date date) {
		return SDF.format(date);
	}
}
