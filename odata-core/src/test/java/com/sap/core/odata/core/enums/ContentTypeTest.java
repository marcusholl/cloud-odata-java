package com.sap.core.odata.core.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sap.core.odata.api.enums.ContentType;

// 14.1 Accept
//
// The Accept request-header field can be used to specify certain media types which are acceptable for the response.
// Accept headers can be used to indicate that the request is specifically limited to a small set of desired types, as
// in the case of a request for an in-line image.
//
// Accept = "Accept" ":"
// #( media-range [ accept-params ] )
// media-range = ( "*/*"
// | ( type "/" "*" )
// | ( type "/" subtype )
// ) *( ";" parameter )
// accept-params = ";" "q" "=" qvalue *( accept-extension )
// accept-extension = ";" token [ "=" ( token | quoted-string ) ]

public class ContentTypeTest {

  @Test
  public void testContentTypeCreation() {
    ContentType mt = ContentType.create("type", "subtype");
    assertEquals("type", mt.getType());
    assertEquals("subtype", mt.getSubtype());
    assertEquals("type/subtype", mt.toString());
  }

  @Test
  public void testContentTypeWithParameterCreation() {
    ContentType mt = ContentType.create("type", "subtype").addParameter("key", "value");
    assertEquals("type", mt.getType());
    assertEquals("subtype", mt.getSubtype());
    assertEquals(1, mt.getParameters().size());
    assertEquals("value", mt.getParameters().get("key"));
    assertEquals("type/subtype;key=value", mt.toString());
  }

  @Test
  public void testContentTypeWithParametersCreation() {
    ContentType mt = ContentType.create("type", "subtype").addParameter("key1", "value1").addParameter("key2", "value2");
    assertEquals("type", mt.getType());
    assertEquals("subtype", mt.getSubtype());
    assertEquals(2, mt.getParameters().size());
    assertEquals("value1", mt.getParameters().get("key1"));
    assertEquals("value2", mt.getParameters().get("key2"));
    assertEquals("type/subtype;key1=value1;key2=value2", mt.toString());
  }

  @Test
  public void testFormatParserValidInputType() {
    ContentType t = ContentType.create("aaa");

    assertEquals("aaa", t.getType());
    assertEquals("*", t.getSubtype());
    assertEquals(0, t.getParameters().size());
  }

  @Test
  public void testFormatParserValidInputTypeSubtype() {
    ContentType t = ContentType.create("aaa/bbb");
    assertEquals("aaa", t.getType());
    assertEquals("bbb", t.getSubtype());
    assertEquals(0, t.getParameters().size());
  }

  @Test
  public void testFormatParserValidInputTypeSybtypePara() {
    ContentType t = ContentType.create("aaa/bbb;x=y");
    assertEquals("aaa", t.getType());
    assertEquals("bbb", t.getSubtype());
    assertEquals(1, t.getParameters().size());
  }

  @Test
  public void testFormatParserValidInputTypeSubtypeParas() {
    ContentType t = ContentType.create("aaa/bbb;x=y;a=b");
    assertEquals("aaa", t.getType());
    assertEquals("bbb", t.getSubtype());
    assertEquals(2, t.getParameters().size());
  }

  @Test
  public void testFormatParserValidInputTypeSubtypeNullPara() {
    ContentType t = ContentType.create("aaa/bbb;x=y;a");

    assertEquals("aaa", t.getType());
    assertEquals("bbb", t.getSubtype());
    assertEquals(2, t.getParameters().size());
  }

  @Test
  public void testFormatParserValidInputTypeNullPara() {
    ContentType t = ContentType.create("aaa;x=y;a");

    assertEquals("aaa", t.getType());
    assertEquals("*", t.getSubtype());
    assertEquals(2, t.getParameters().size());
  }
  
  @Test
  public void testSimpleEqual() {
    ContentType t1 = ContentType.create("aaa/bbb");
    ContentType t2 = ContentType.create("aaa/bbb");
    
    assertEquals(t1, t2);
  }

  @Test
  public void testEqualWithParameters() {
    ContentType t1 = ContentType.create("aaa/bbb;x=y;a");
    ContentType t2 = ContentType.create("aaa/bbb;x=y;a");
    
    assertEquals(t1, t2);
    assertTrue(t1.equals(t2));
    assertTrue(t2.equals(t1));
  }

  @Test
  public void testEqualWithUnsortedParameters() {
    ContentType t1 = ContentType.create("aaa/bbb;x=y;a=b");
    ContentType t2 = ContentType.create("aaa/bbb;a=b;x=y");
    
    assertEquals(t1, t2);
    assertTrue(t1.equals(t2));
    assertTrue(t2.equals(t1));
  }

  @Test
  public void testEqualWithWildcard() {
    ContentType t1 = ContentType.create("aaa/bbb");
    ContentType t2 = ContentType.create("*");
    
    assertTrue(t1.equals(t2));
    assertTrue(t2.equals(t1));
    assertEquals(t1, t2);
  }

  @Test
  public void testEqualWithWildcardSubtype() {
    ContentType t1 = ContentType.create("aaa/bbb");
    ContentType t2 = ContentType.create("aaa/*");
    
    assertEquals(t1, t2);
    assertTrue(t1.equals(t2));
    assertTrue(t2.equals(t1));
  }

  @Test
  public void testEqualWithWildcardAndParameters() {
    ContentType t1 = ContentType.create("aaa/bbb;x=y;a");
    ContentType t2 = ContentType.create("*");
    
    assertEquals(t1, t2);
    assertTrue(t1.equals(t2));
    assertTrue(t2.equals(t1));
  }

  @Test
  public void testEqualWithWildcardSubtypeAndParameters() {
    ContentType t1 = ContentType.create("aaa/bbb;x=y;a");
    ContentType t2 = ContentType.create("aaa/*");
    
    assertEquals(t1, t2);
    assertTrue(t1.equals(t2));
    assertTrue(t2.equals(t1));
  }
  
  @Test
  public void testEqualWithWildcardSubtypeAndParametersBoth() {
    ContentType t1 = ContentType.create("aaa/bbb;x=y");
    ContentType t2 = ContentType.create("aaa/*;x=y");
    
    assertEquals(t1, t2);
    assertTrue(t1.equals(t2));
    assertTrue(t2.equals(t1));
  }

  @Test
  public void testUnEqualWithWildcardSubtypeAndDiffParameters() {
    ContentType t1 = ContentType.create("aaa/bbb;x=z");
    ContentType t2 = ContentType.create("aaa/*;x=y");
    
    assertFalse(t1.equals(t2));
    assertFalse(t2.equals(t1));
  }
  
  
  @Test
  public void testUnSimpleEqual() {
    ContentType t1 = ContentType.create("aaa/ccc");
    ContentType t2 = ContentType.create("aaa/bbb");
    
    assertFalse(t1.equals(t2));
    assertFalse(t2.equals(t1));
  }

  @Test
  public void testUnEqualTypesWithParameters() {
    ContentType t1 = ContentType.create("aaa/bbb;x=y;a");
    ContentType t2 = ContentType.create("ccc/bbb;x=y;a");
    
    assertFalse(t1.equals(t2));
    assertFalse(t2.equals(t1));
  }


  @Test
  public void testUnEqualParameters() {
    ContentType t1 = ContentType.create("aaa/bbb;x=y;a");
    ContentType t2 = ContentType.create("aaa/bbb;x=y;a=b");
    
    assertFalse(t1.equals(t2));
    assertFalse(t2.equals(t1));
  }

  @Test
  public void testUnEqualWithUnsortedParameters() {
    ContentType t1 = ContentType.create("aaa/bbb;x=z;a=b");
    ContentType t2 = ContentType.create("aaa/bbb;a=b;x=y");
    
    assertFalse(t1.equals(t2));
    assertFalse(t2.equals(t1));
  }
}
