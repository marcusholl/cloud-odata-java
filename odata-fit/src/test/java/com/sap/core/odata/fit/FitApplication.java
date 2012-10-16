package com.sap.core.odata.fit;

import javax.ws.rs.ext.ContextResolver;

import com.sap.core.odata.core.producer.ODataProducer;
import com.sap.core.odata.core.rest.ODataApplication;




public class FitApplication extends ODataApplication {
  @Override
  protected Class<? extends ContextResolver<ODataProducer>> getContextResolver() {
    return FitContextResolver.class;
  }

  public static void setProducerInstance(ODataProducer producer) {
    FitContextResolver.setProducerInstance(producer);
  }
}