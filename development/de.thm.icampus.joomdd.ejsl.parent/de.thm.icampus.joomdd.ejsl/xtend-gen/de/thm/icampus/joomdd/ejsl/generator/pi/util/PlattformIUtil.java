package de.thm.icampus.joomdd.ejsl.generator.pi.util;

import de.thm.icampus.joomdd.ejsl.eJSL.Attribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute;
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.impl.ExtendedAttributeImpl;

@SuppressWarnings("all")
public class PlattformIUtil {
  public static String slugify(final String str) {
    String _xblockexpression = null;
    {
      String res = str;
      String _replaceAll = res.replaceAll("[^\\pL\\d]+", "_");
      res = _replaceAll;
      String _replaceAll_1 = res.replaceAll("ä", "ae");
      res = _replaceAll_1;
      String _replaceAll_2 = res.replaceAll("ö", "ou");
      res = _replaceAll_2;
      String _replaceAll_3 = res.replaceAll("ü", "ue");
      res = _replaceAll_3;
      String _replaceAll_4 = res.replaceAll("ß", "ss");
      res = _replaceAll_4;
      String _replaceAll_5 = res.replaceAll("[^-\\w]+", "");
      res = _replaceAll_5;
      String _lowerCase = res.toLowerCase();
      res = _lowerCase;
      char _charAt = "_".charAt(0);
      _xblockexpression = PlattformIUtil.trim(res, _charAt);
    }
    return _xblockexpression;
  }
  
  public static String trim(final String str, final char c) {
    String _xblockexpression = null;
    {
      int a = 0;
      int z = str.length();
      while (((a < z) && (str.charAt(a) == c))) {
        a = (a + 1);
      }
      do {
        z = (z - 1);
      } while(((z > a) && (str.charAt(z) == c)));
      _xblockexpression = str.substring(a, (z + 1));
    }
    return _xblockexpression;
  }
  
  public static ExtendedAttribute transformAttribute(final Attribute ejslAttribute) {
    return new ExtendedAttributeImpl(ejslAttribute);
  }
}
