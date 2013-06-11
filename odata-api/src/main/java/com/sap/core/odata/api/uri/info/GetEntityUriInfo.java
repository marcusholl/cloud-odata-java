package com.sap.core.odata.api.uri.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sap.core.odata.api.edm.EdmEntityContainer;
import com.sap.core.odata.api.edm.EdmEntitySet;
import com.sap.core.odata.api.edm.EdmFunctionImport;
import com.sap.core.odata.api.edm.EdmLiteral;
import com.sap.core.odata.api.edm.EdmType;
import com.sap.core.odata.api.uri.KeyPredicate;
import com.sap.core.odata.api.uri.NavigationPropertySegment;
import com.sap.core.odata.api.uri.NavigationSegment;
import com.sap.core.odata.api.uri.SelectItem;
import com.sap.core.odata.api.uri.expression.FilterExpression;

/**
 * Access to the parts of the request URI that are relevant for GET requests
 * of a single entity.
 * @com.sap.core.odata.DoNotImplement
 * @author SAP AG
 */
public interface GetEntityUriInfo {
  /**
   * Gets the target entity container.
   * @return {@link EdmEntityContainer} the target entity container
   */
  public EdmEntityContainer getEntityContainer();

  /**
   * Gets the start entity set - identical to the target entity set if no navigation
   * has been used.
   * @return {@link EdmEntitySet}
   */
  public EdmEntitySet getStartEntitySet();

  /**
   * Gets the target entity set after navigation.
   * @return {@link EdmEntitySet} target entity set
   */
  public EdmEntitySet getTargetEntitySet();

  /**
   * Gets the function import.
   * @return {@link EdmFunctionImport} the function import
   */
  public EdmFunctionImport getFunctionImport();

  /**
   * Gets the target entity type of the request.
   * @return {@link EdmType} the target type
   */
  public EdmType getTargetType();

  /**
   * Gets the key predicates used to select a single entity out of the start entity set,
   * or an empty list if not used.
   * @return List of {@link KeyPredicate}
   * @see #getStartEntitySet()
   */
  public List<KeyPredicate> getKeyPredicates();

  /**
   * Gets the key predicates used to select a single entity out of the target entity set,
   * or an empty list if not used - identical to the key predicates from the last entry
   * retrieved from {@link #getNavigationSegments()} or, if no navigation has been used,
   * to the result of {@link #getKeyPredicates()}.
   * @return List of {@link KeyPredicate}
   * @see #getTargetEntitySet()
   */
  public List<KeyPredicate> getTargetKeyPredicates();

  /**
   * Gets the navigation segments, or an empty list if no navigation has been used.
   * @return List of {@link NavigationSegment}
   */
  public List<NavigationSegment> getNavigationSegments();

  /**
   * Gets the value of the $format system query option.
   * @return the format (as set as <code>$format</code> query parameter) or null
   */
  public String getFormat();

  /**
   * Gets the value of the $filter system query option as root object of the
   * expression tree built during URI parsing.
   * @return the filter expression or null
   */
  public FilterExpression getFilter();

  /**
   * Gets the value of the $expand system query option as a list of
   * lists of navigation-property segments, or an empty list if not used.
   * @return List of a list of {@link NavigationPropertySegment} to be expanded
   */
  public List<ArrayList<NavigationPropertySegment>> getExpand();

  /**
   * Gets the value of the $select system query option as a list of select items,
   * or an empty list if not used.
   * @return List of {@link SelectItem} to be selected
   */
  public List<SelectItem> getSelect();

  /**
   * Gets the parameters of a function import as Map from parameter names to
   * their corresponding typed values, or an empty list if no function import
   * is used or no parameters are given in the URI.
   * @return Map of {@literal <String,} {@link EdmLiteral}{@literal >} function import parameters
   */
  public Map<String, EdmLiteral> getFunctionImportParameters();

  /**
   * Gets the custom query options as Map from option names to their
   * corresponding String values, or an empty list if no custom query options
   * are given in the URI.
   * @return Map of {@literal <String, String>} custom query options
   */
  public Map<String, String> getCustomQueryOptions();
}
