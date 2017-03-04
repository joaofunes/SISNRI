package com.sisrni.managedbean.lazymodel.generic;

import com.sisrni.service.generic.GenericService;
import com.sisrni.utils.PagedResult;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public abstract class GenericLazyModel<T, ID extends Serializable> extends LazyDataModel<T> {

    private List<T> datasource;
    private GenericService<T, ID> service;

    public GenericLazyModel(GenericService<T, ID> service) {
        this.service = service;
    }

    public GenericLazyModel(List<T> datasource, GenericService<T, ID> service) {
        this.datasource = datasource;
        this.service = service;
    }

    @Override
    public abstract T getRowData(String rowKey);

    @Override
    public abstract ID getRowKey(T element);
    
    
    
    
    
    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<T> data = new ArrayList<T>();
 
        //filter
        for(T car : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(car);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }

    
    

   

    public List<T> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<T> datasource) {
        this.datasource = datasource;
    }

    public GenericService<T, ID> getService() {
        return service;
    }

    public void setService(GenericService<T, ID> service) {
        this.service = service;
    }

}
