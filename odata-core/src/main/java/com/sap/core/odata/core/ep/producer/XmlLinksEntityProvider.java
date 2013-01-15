package com.sap.core.odata.core.ep.producer;

import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sap.core.odata.api.edm.Edm;
import com.sap.core.odata.api.ep.EntityProviderException;
import com.sap.core.odata.api.ep.EntityProviderProperties;
import com.sap.core.odata.core.ep.FormatXml;
import com.sap.core.odata.core.ep.aggregator.EntityInfoAggregator;

/**
 * Provider for writing a collection of links
 * @author SAP AG
 */
public class XmlLinksEntityProvider {

  private final EntityProviderProperties properties;

  public XmlLinksEntityProvider(final EntityProviderProperties properties) throws EntityProviderException {
    this.properties = properties;
  }

  public void append(XMLStreamWriter writer, final EntityInfoAggregator entityInfo, final List<Map<String, Object>> data) throws EntityProviderException {
    try {
      writer.writeStartElement(FormatXml.D_LINKS);
      writer.writeDefaultNamespace(Edm.NAMESPACE_D_2007_08);
      if (properties.getInlineCount() != null) {
        writer.writeStartElement(Edm.PREFIX_M, FormatXml.M_COUNT, Edm.NAMESPACE_M_2007_08);
        writer.writeNamespace(Edm.PREFIX_M, Edm.NAMESPACE_M_2007_08);
        writer.writeCharacters(properties.getInlineCount().toString());
        writer.writeEndElement();
      }
      XmlLinkEntityProvider provider = new XmlLinkEntityProvider(properties);
      for (final Map<String, Object> entityData : data)
        provider.append(writer, entityInfo, entityData, false);
      writer.writeEndElement();
      writer.flush();
    } catch (XMLStreamException e) {
      throw new EntityProviderException(EntityProviderException.COMMON, e);
    }
  }
}