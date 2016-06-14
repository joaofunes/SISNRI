package com.dimesa.managedbean.lazymodel.generic;

import com.dimesa.service.generic.GenericService;
import com.dimesa.utils.PagedResult;
import java.io.Serializable;
import java.util.ArrayList;
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
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<T> ret = new ArrayList<T>();
        List<Map<String, String>> criterions = new ArrayList<Map<String, String>>();
        criterions.add(filters);
        if (service != null) {
            PagedResult page = service.getPage((first/pageSize)+1, pageSize, sortField, sortOrder.toString().substring(0, 3), criterions);
            ret = page.getList();
            setRowCount(page.getCounter());
        }
        return ret;
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
