package it.ivncr.erp.service;

import java.util.Map;

public interface EntityService<T> extends Service {
	
	QueryResult<T> list(
			int first,
			int pageSize,
			String sortCriteria,
			SortDirection sortDirection,
			Map<String, String> filters);

}
