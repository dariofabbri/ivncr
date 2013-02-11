package it.ivncr.erp.jsf;

import org.primefaces.model.LazyDataModel;

public abstract class RobustLazyDataModel<T> extends LazyDataModel<T>{
    
	private static final long serialVersionUID = 1L;

	@Override
    public void setRowIndex(int rowIndex) {
		
        /*
         * The following is in ancestor (LazyDataModel):
         * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
         */
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        }
        else {
            super.setRowIndex(rowIndex % getPageSize());
        }      
    }
}