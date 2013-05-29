/*******************************************************************************
 * Copyright 2013 SAP AG
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.sap.core.odata.core.servicedocument;

import java.util.ArrayList;
import java.util.List;

import com.sap.core.odata.api.edm.EdmEntitySetInfo;
import com.sap.core.odata.api.ep.EntityProviderException;
import com.sap.core.odata.api.servicedocument.AtomInfo;
import com.sap.core.odata.api.servicedocument.ServiceDocument;

public class ServiceDocumentImpl implements ServiceDocument {
  private AtomInfo atomInfo;
  private List<EdmEntitySetInfo> entitySets = new ArrayList<EdmEntitySetInfo>();

  public ServiceDocumentImpl setEntitySetsInfo(final List<EdmEntitySetInfo> entitySets) {
    this.entitySets = entitySets;
    return this;
  }

  @Override
  public List<EdmEntitySetInfo> getEntitySetsInfo() throws EntityProviderException {
    return entitySets;
  }

  @Override
  public AtomInfo getAtomInfo() {
    return atomInfo;
  }

  public void setAtomInfo(final AtomInfo atomInfo) {
    this.atomInfo = atomInfo;
  }

}
