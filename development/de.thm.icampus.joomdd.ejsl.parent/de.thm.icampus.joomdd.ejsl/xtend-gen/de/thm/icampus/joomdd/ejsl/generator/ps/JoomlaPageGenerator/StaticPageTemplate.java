package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator;

import de.thm.icampus.joomdd.ejsl.eJSL.Page;
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator.AbstractPageGenerator;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Static Page</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see eJSLGenerator.GeneratorTemplatePackage#getStaticPage()
 * @model
 * @generated
 */
@SuppressWarnings("all")
public class StaticPageTemplate extends AbstractPageGenerator {
  private Page mypage;
  
  public StaticPageTemplate(final Page page) {
    this.mypage = page;
  }
  
  public CharSequence generateStaticPage() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("INSERT INTO `#__content`");
    _builder.newLine();
    _builder.append("(title, alias, introtext, state, created, created_by, attribs, metadata, version)");
    _builder.newLine();
    _builder.append("VALUES");
    _builder.newLine();
    _builder.append("(`");
    String _name = this.mypage.getName();
    _builder.append(_name, "");
    _builder.append("`, `");
    String _name_1 = this.mypage.getName();
    String _lowerCase = _name_1.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("`");
    _builder.newLineIfNotEmpty();
    _builder.append(", `");
    int _hashCode = this.mypage.hashCode();
    _builder.append(_hashCode, "");
    _builder.append("`");
    _builder.newLineIfNotEmpty();
    _builder.append(", 1");
    _builder.newLine();
    _builder.append(", GETDATE()");
    _builder.newLine();
    _builder.append(", 1");
    _builder.newLine();
    _builder.append(", `{\"show_title\":\"\",\"link_titles\":\"\",\"show_intro\":\"\",\"show_category\":\"\",\"link_category\":\"\",\"show_parent_category\":\"\",\"link_parent_category\":\"\",\"show_author\":\"\",\"link_author\":\"\",\"show_create_date\":\"\",\"show_modify_date\":\"\",\"show_publish_date\":\"\",\"show_item_navigation\":\"\",\"show_icons\":\"\",\"show_print_icon\":\"\",\"show_email_icon\":\"\",\"show_vote\":\"\",\"show_hits\":\"\",\"show_noauth\":\"\",\"alternative_readmore\":\"\",\"article_layout\":\"\"}`");
    _builder.newLine();
    _builder.append(", `{\"robots\":\"\",\"author\":\"\",\"rights\":\"\",\"xreference\":\"\"}`");
    _builder.newLine();
    _builder.append(", 1");
    _builder.newLine();
    _builder.append(")");
    _builder.newLine();
    return _builder;
  }
}
