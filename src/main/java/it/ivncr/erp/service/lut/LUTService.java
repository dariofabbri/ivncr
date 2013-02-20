package it.ivncr.erp.service.lut;

import it.ivncr.erp.service.Service;

import java.util.List;

public interface LUTService extends Service {

	<T> T retrieveItem(String lut, Object id);

	<T> T retrieveItemByDescrizione(String lut, String descrizione);

	<T> T retrieveItemByDescrizione(String lut, String column, String descrizione);

	<T> List<T> listItems(String lut);

	<T> List<T> listItems(String lut, String orderColumn);

	<T> List<T> listItems(String lut, String orderColumn, String filterColumn, Object filterValue);

	<T> List<T> listItemsSingleColumn(String lut, String column);
}