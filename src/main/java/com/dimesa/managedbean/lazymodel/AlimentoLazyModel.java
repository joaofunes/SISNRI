package com.dimesa.managedbean.lazymodel;

//package com.siapa.managedbean.lazymodel;
//
//import com.siapa.managedbean.lazymodel.generic.GenericLazyModel;
//import com.dimesa.model.Alimento;
//import com.siapa.service.generic.GenericService;
//
///**
// *
// * @author Joao
// */
//public class AlimentoLazyModel extends GenericLazyModel<Alimento, Integer> {
//
//    public AlimentoLazyModel(GenericService<Alimento, Integer> service) {
//        super(service);
//    }
//
//    @Override
//    public Alimento getRowData(String rowKey) {
//        for (Alimento alimento : getDatasource()) {
//            if (alimento.getIdAlimento().equals(Integer.valueOf(rowKey)));
//            return alimento;
//        }
//        return null;
//    }
//
//    @Override
//    public Integer getRowKey(Alimento element) {
//        return element.getIdAlimento();
//    }
//
//}
