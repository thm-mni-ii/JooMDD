package de.thm.icampus.joomdd.ejsl.idea.parser.antlr.internal;

import org.eclipse.xtext.idea.parser.AbstractPsiAntlrParser;
import de.thm.icampus.joomdd.ejsl.idea.lang.EJSLElementTypeProvider;
import org.eclipse.xtext.idea.parser.TokenTypeProvider;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import de.thm.icampus.joomdd.ejsl.services.EJSLGrammarAccess;

import com.intellij.lang.PsiBuilder;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class PsiInternalEJSLParser extends AbstractPsiAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_DATE", "RULE_LANGUAGE_CODE", "RULE_POSITION_TYPES", "RULE_POSITION_TYPES_NAMES", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'eJSLModel'", "'{'", "'eJSL part:'", "'}'", "'CMS Core'", "'datatypes'", "','", "'globalparameters'", "'parametergroups'", "'CMS Extension'", "'extensions'", "'entitypackages'", "'entities'", "'pages'", "'sections'", "'Not Null'", "'Default ='", "'Auto Increment'", "'Datatype'", "'Parameter'", "'type ='", "'defaultvalue ='", "'label ='", "'size ='", "'description ='", "'ParameterGroup'", "'Parameters'", "'PageAction'", "'position ='", "'Entitypackage'", "'Entity'", "'extends'", "'attributes'", "'references'", "'Attribute'", "'Unique attribute'", "'with'", "'ID'", "'Reference'", "'EntityAttribute ='", "'*Entity ='", "'*EntityReference ='", "'lower ='", "'upper ='", "'StaticPage'", "'*ParameterGroups'", "'*Globalparameters'", "'localparameters'", "'pageactions'", "'links'", "'HTMLBody'", "'IndexPage'", "'*Entities'", "'table columns ='", "'filters ='", "'DetailsPage'", "'editFields'", "':'", "'CustomPage'", "'Page type:'", "'('", "')'", "'ExternalLink'", "'target ='", "'linked attribute ='", "'linked action ='", "'label'", "'='", "'InternalLink'", "'InternalcontextLink'", "'linkparameters'", "'*Attribute '", "'Extension package'", "'Manifestation'", "'languages'", "'Component'", "'Global parameter'", "'Backend section'", "'*Pages'", "'*Page :'", "'from :'", "'Frontend section'", "'Module'", "'Plugin'", "'Plugintype ='", "'parameters'", "'Library'", "'classes'", "'packages'", "'Package'", "'Class'", "'*Class references'", "'methods'", "'Method'", "'Returnvalue'", "'methodparameters'", "'MethodParameter'", "'Template'", "'positions'", "'cssblocks'", "'authors'", "'creationdate ='", "'copyright ='", "'license ='", "'link ='", "'version ='", "'Author'", "'authoremail ='", "'authorurl ='", "'Language'", "'keyvaluepairs'", "'Key'", "'Templateposition'", "'positionparameters'", "'Position Parameter'", "'divId ='", "'Positiontype ='", "'CSS keyvaluepairs'", "'CssBlock'", "'-'", "'.'", "'<'", "'>'", "'authenticate'", "'captcha'", "'content'", "'contact'", "'editors'", "'finder'", "'quick_icons'", "'search'", "'system'", "'user'", "'xml_rpc'", "'Text'", "'Button'", "'top'", "'center'", "'bottom'", "'Integer'", "'Boolean'", "'Textarea'", "'Textfield'", "'Time'", "'Date'", "'Datetime'", "'Link'", "'Image'", "'File'", "'Label'", "'.backend'", "'.frontend'", "'list'", "'details'", "'custom'", "'Yes_No_Buttons'", "'Text_Field'", "'Datepicker'", "'Imagepicker'", "'Filepicker'", "'Text_Field_NE'", "'Editor'", "'Multiselect'", "'Checkbox'", "'Radiobutto'"
    };
    public static final int T__144=144;
    public static final int T__143=143;
    public static final int T__146=146;
    public static final int T__50=50;
    public static final int T__145=145;
    public static final int T__140=140;
    public static final int T__142=142;
    public static final int T__141=141;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__137=137;
    public static final int T__52=52;
    public static final int T__136=136;
    public static final int T__53=53;
    public static final int T__139=139;
    public static final int T__54=54;
    public static final int T__138=138;
    public static final int T__133=133;
    public static final int T__132=132;
    public static final int T__60=60;
    public static final int T__135=135;
    public static final int T__61=61;
    public static final int T__134=134;
    public static final int RULE_ID=5;
    public static final int T__131=131;
    public static final int T__130=130;
    public static final int RULE_DATE=7;
    public static final int RULE_INT=6;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=11;
    public static final int T__67=67;
    public static final int T__129=129;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__126=126;
    public static final int T__63=63;
    public static final int T__125=125;
    public static final int T__64=64;
    public static final int T__128=128;
    public static final int T__65=65;
    public static final int T__127=127;
    public static final int T__166=166;
    public static final int T__165=165;
    public static final int T__168=168;
    public static final int T__167=167;
    public static final int T__162=162;
    public static final int T__161=161;
    public static final int T__164=164;
    public static final int T__163=163;
    public static final int T__160=160;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__159=159;
    public static final int T__30=30;
    public static final int T__158=158;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__155=155;
    public static final int T__154=154;
    public static final int T__157=157;
    public static final int T__156=156;
    public static final int T__151=151;
    public static final int T__150=150;
    public static final int T__153=153;
    public static final int T__152=152;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__148=148;
    public static final int T__41=41;
    public static final int T__147=147;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__149=149;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int T__90=90;
    public static final int RULE_POSITION_TYPES=9;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__99=99;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__177=177;
    public static final int T__176=176;
    public static final int T__179=179;
    public static final int T__178=178;
    public static final int T__173=173;
    public static final int T__172=172;
    public static final int T__175=175;
    public static final int T__174=174;
    public static final int T__171=171;
    public static final int T__170=170;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int RULE_LANGUAGE_CODE=8;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__169=169;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__122=122;
    public static final int T__70=70;
    public static final int T__121=121;
    public static final int T__71=71;
    public static final int T__124=124;
    public static final int T__72=72;
    public static final int T__123=123;
    public static final int T__120=120;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=12;
    public static final int T__77=77;
    public static final int T__119=119;
    public static final int T__78=78;
    public static final int T__118=118;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int T__115=115;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__114=114;
    public static final int T__75=75;
    public static final int T__117=117;
    public static final int T__76=76;
    public static final int T__116=116;
    public static final int T__80=80;
    public static final int T__111=111;
    public static final int T__81=81;
    public static final int T__110=110;
    public static final int T__82=82;
    public static final int T__113=113;
    public static final int T__83=83;
    public static final int T__112=112;
    public static final int RULE_WS=13;
    public static final int RULE_ANY_OTHER=14;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
    public static final int T__109=109;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int RULE_POSITION_TYPES_NAMES=10;
    public static final int T__86=86;
    public static final int T__106=106;
    public static final int T__87=87;
    public static final int T__105=105;

    // delegates
    // delegators


        public PsiInternalEJSLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public PsiInternalEJSLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return PsiInternalEJSLParser.tokenNames; }
    public String getGrammarFileName() { return "PsiInternalEJSL.g"; }



    	protected EJSLGrammarAccess grammarAccess;

    	protected EJSLElementTypeProvider elementTypeProvider;

    	public PsiInternalEJSLParser(PsiBuilder builder, TokenStream input, EJSLElementTypeProvider elementTypeProvider, EJSLGrammarAccess grammarAccess) {
    		this(input);
    		setPsiBuilder(builder);
        	this.grammarAccess = grammarAccess;
    		this.elementTypeProvider = elementTypeProvider;
    	}

    	@Override
    	protected String getFirstRuleName() {
    		return "EJSLModel";
    	}




    // $ANTLR start "entryRuleEJSLModel"
    // PsiInternalEJSL.g:52:1: entryRuleEJSLModel returns [Boolean current=false] : iv_ruleEJSLModel= ruleEJSLModel EOF ;
    public final Boolean entryRuleEJSLModel() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEJSLModel = null;


        try {
            // PsiInternalEJSL.g:52:51: (iv_ruleEJSLModel= ruleEJSLModel EOF )
            // PsiInternalEJSL.g:53:2: iv_ruleEJSLModel= ruleEJSLModel EOF
            {
             markComposite(elementTypeProvider.getEJSLModelElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleEJSLModel=ruleEJSLModel();

            state._fsp--;

             current =iv_ruleEJSLModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEJSLModel"


    // $ANTLR start "ruleEJSLModel"
    // PsiInternalEJSL.g:59:1: ruleEJSLModel returns [Boolean current=false] : ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}' ) ;
    public final Boolean ruleEJSLModel() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Boolean lv_ejslPart_5_0 = null;


        try {
            // PsiInternalEJSL.g:60:1: ( ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}' ) )
            // PsiInternalEJSL.g:61:2: ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}' )
            {
            // PsiInternalEJSL.g:61:2: ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}' )
            // PsiInternalEJSL.g:62:3: () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}'
            {
            // PsiInternalEJSL.g:62:3: ()
            // PsiInternalEJSL.g:63:4: 
            {

            				precedeComposite(elementTypeProvider.getEJSLModel_EJSLModelAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getEJSLModel_EJSLModelKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,15,FOLLOW_3); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:76:3: ( (lv_name_2_0= RULE_STRING ) )
            // PsiInternalEJSL.g:77:4: (lv_name_2_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:77:4: (lv_name_2_0= RULE_STRING )
            // PsiInternalEJSL.g:78:5: lv_name_2_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getEJSLModel_NameSTRINGTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getEJSLModel_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_5); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:100:3: (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) )
            // PsiInternalEJSL.g:101:4: otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) )
            {

            				markLeaf(elementTypeProvider.getEJSLModel_EJSLPartKeyword_4_0ElementType());
            			
            otherlv_4=(Token)match(input,17,FOLLOW_6); 

            				doneLeaf(otherlv_4);
            			
            // PsiInternalEJSL.g:108:4: ( (lv_ejslPart_5_0= ruleEJSLPart ) )
            // PsiInternalEJSL.g:109:5: (lv_ejslPart_5_0= ruleEJSLPart )
            {
            // PsiInternalEJSL.g:109:5: (lv_ejslPart_5_0= ruleEJSLPart )
            // PsiInternalEJSL.g:110:6: lv_ejslPart_5_0= ruleEJSLPart
            {

            						markComposite(elementTypeProvider.getEJSLModel_EjslPartEJSLPartParserRuleCall_4_1_0ElementType());
            					
            pushFollow(FOLLOW_7);
            lv_ejslPart_5_0=ruleEJSLPart();

            state._fsp--;


            						doneComposite();
            						if(!current) {
            							associateWithSemanticElement();
            							current = true;
            						}
            					

            }


            }


            }


            			markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_5ElementType());
            		
            otherlv_6=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_6);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEJSLModel"


    // $ANTLR start "entryRuleEJSLPart"
    // PsiInternalEJSL.g:135:1: entryRuleEJSLPart returns [Boolean current=false] : iv_ruleEJSLPart= ruleEJSLPart EOF ;
    public final Boolean entryRuleEJSLPart() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEJSLPart = null;


        try {
            // PsiInternalEJSL.g:135:50: (iv_ruleEJSLPart= ruleEJSLPart EOF )
            // PsiInternalEJSL.g:136:2: iv_ruleEJSLPart= ruleEJSLPart EOF
            {
             markComposite(elementTypeProvider.getEJSLPartElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleEJSLPart=ruleEJSLPart();

            state._fsp--;

             current =iv_ruleEJSLPart; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEJSLPart"


    // $ANTLR start "ruleEJSLPart"
    // PsiInternalEJSL.g:142:1: ruleEJSLPart returns [Boolean current=false] : (this_CMSCore_0= ruleCMSCore | this_CMSExtension_1= ruleCMSExtension ) ;
    public final Boolean ruleEJSLPart() throws RecognitionException {
        Boolean current = false;

        Boolean this_CMSCore_0 = null;

        Boolean this_CMSExtension_1 = null;


        try {
            // PsiInternalEJSL.g:143:1: ( (this_CMSCore_0= ruleCMSCore | this_CMSExtension_1= ruleCMSExtension ) )
            // PsiInternalEJSL.g:144:2: (this_CMSCore_0= ruleCMSCore | this_CMSExtension_1= ruleCMSExtension )
            {
            // PsiInternalEJSL.g:144:2: (this_CMSCore_0= ruleCMSCore | this_CMSExtension_1= ruleCMSExtension )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==19) ) {
                alt1=1;
            }
            else if ( (LA1_0==24) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // PsiInternalEJSL.g:145:3: this_CMSCore_0= ruleCMSCore
                    {

                    			markComposite(elementTypeProvider.getEJSLPart_CMSCoreParserRuleCall_0ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_CMSCore_0=ruleCMSCore();

                    state._fsp--;


                    			current = this_CMSCore_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:154:3: this_CMSExtension_1= ruleCMSExtension
                    {

                    			markComposite(elementTypeProvider.getEJSLPart_CMSExtensionParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_CMSExtension_1=ruleCMSExtension();

                    state._fsp--;


                    			current = this_CMSExtension_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEJSLPart"


    // $ANTLR start "entryRuleCMSCore"
    // PsiInternalEJSL.g:166:1: entryRuleCMSCore returns [Boolean current=false] : iv_ruleCMSCore= ruleCMSCore EOF ;
    public final Boolean entryRuleCMSCore() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleCMSCore = null;


        try {
            // PsiInternalEJSL.g:166:49: (iv_ruleCMSCore= ruleCMSCore EOF )
            // PsiInternalEJSL.g:167:2: iv_ruleCMSCore= ruleCMSCore EOF
            {
             markComposite(elementTypeProvider.getCMSCoreElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleCMSCore=ruleCMSCore();

            state._fsp--;

             current =iv_ruleCMSCore; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCMSCore"


    // $ANTLR start "ruleCMSCore"
    // PsiInternalEJSL.g:173:1: ruleCMSCore returns [Boolean current=false] : ( () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}' ) ;
    public final Boolean ruleCMSCore() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Boolean lv_datatypes_5_0 = null;

        Boolean lv_datatypes_7_0 = null;

        Boolean lv_globalparameters_11_0 = null;

        Boolean lv_parametergroups_15_0 = null;

        Boolean lv_feature_17_0 = null;


        try {
            // PsiInternalEJSL.g:174:1: ( ( () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}' ) )
            // PsiInternalEJSL.g:175:2: ( () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}' )
            {
            // PsiInternalEJSL.g:175:2: ( () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}' )
            // PsiInternalEJSL.g:176:3: () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}'
            {
            // PsiInternalEJSL.g:176:3: ()
            // PsiInternalEJSL.g:177:4: 
            {

            				precedeComposite(elementTypeProvider.getCMSCore_CMSCoreAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getCMSCore_CMSCoreKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,19,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getCMSCore_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_8); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:197:3: (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==20) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // PsiInternalEJSL.g:198:4: otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}'
                    {

                    				markLeaf(elementTypeProvider.getCMSCore_DatatypesKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,20,FOLLOW_4); 

                    				doneLeaf(otherlv_3);
                    			

                    				markLeaf(elementTypeProvider.getCMSCore_LeftCurlyBracketKeyword_3_1ElementType());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_9); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:212:4: ( (lv_datatypes_5_0= ruleDatatype ) )
                    // PsiInternalEJSL.g:213:5: (lv_datatypes_5_0= ruleDatatype )
                    {
                    // PsiInternalEJSL.g:213:5: (lv_datatypes_5_0= ruleDatatype )
                    // PsiInternalEJSL.g:214:6: lv_datatypes_5_0= ruleDatatype
                    {

                    						markComposite(elementTypeProvider.getCMSCore_DatatypesDatatypeParserRuleCall_3_2_0ElementType());
                    					
                    pushFollow(FOLLOW_10);
                    lv_datatypes_5_0=ruleDatatype();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }

                    // PsiInternalEJSL.g:227:4: (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==21) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:228:5: otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getCMSCore_CommaKeyword_3_3_0ElementType());
                    	    				
                    	    otherlv_6=(Token)match(input,21,FOLLOW_9); 

                    	    					doneLeaf(otherlv_6);
                    	    				
                    	    // PsiInternalEJSL.g:235:5: ( (lv_datatypes_7_0= ruleDatatype ) )
                    	    // PsiInternalEJSL.g:236:6: (lv_datatypes_7_0= ruleDatatype )
                    	    {
                    	    // PsiInternalEJSL.g:236:6: (lv_datatypes_7_0= ruleDatatype )
                    	    // PsiInternalEJSL.g:237:7: lv_datatypes_7_0= ruleDatatype
                    	    {

                    	    							markComposite(elementTypeProvider.getCMSCore_DatatypesDatatypeParserRuleCall_3_3_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_10);
                    	    lv_datatypes_7_0=ruleDatatype();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCMSCore_RightCurlyBracketKeyword_3_4ElementType());
                    			
                    otherlv_8=(Token)match(input,18,FOLLOW_11); 

                    				doneLeaf(otherlv_8);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:259:3: (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==22) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // PsiInternalEJSL.g:260:4: otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}'
                    {

                    				markLeaf(elementTypeProvider.getCMSCore_GlobalparametersKeyword_4_0ElementType());
                    			
                    otherlv_9=(Token)match(input,22,FOLLOW_4); 

                    				doneLeaf(otherlv_9);
                    			

                    				markLeaf(elementTypeProvider.getCMSCore_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_10=(Token)match(input,16,FOLLOW_12); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:274:4: ( (lv_globalparameters_11_0= ruleParameter ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==34) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:275:5: (lv_globalparameters_11_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:275:5: (lv_globalparameters_11_0= ruleParameter )
                    	    // PsiInternalEJSL.g:276:6: lv_globalparameters_11_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getCMSCore_GlobalparametersParameterParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_globalparameters_11_0=ruleParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCMSCore_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_12=(Token)match(input,18,FOLLOW_13); 

                    				doneLeaf(otherlv_12);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:297:3: (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==23) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // PsiInternalEJSL.g:298:4: otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}'
                    {

                    				markLeaf(elementTypeProvider.getCMSCore_ParametergroupsKeyword_5_0ElementType());
                    			
                    otherlv_13=(Token)match(input,23,FOLLOW_4); 

                    				doneLeaf(otherlv_13);
                    			

                    				markLeaf(elementTypeProvider.getCMSCore_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_14=(Token)match(input,16,FOLLOW_14); 

                    				doneLeaf(otherlv_14);
                    			
                    // PsiInternalEJSL.g:312:4: ( (lv_parametergroups_15_0= ruleParameterGroup ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==40) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:313:5: (lv_parametergroups_15_0= ruleParameterGroup )
                    	    {
                    	    // PsiInternalEJSL.g:313:5: (lv_parametergroups_15_0= ruleParameterGroup )
                    	    // PsiInternalEJSL.g:314:6: lv_parametergroups_15_0= ruleParameterGroup
                    	    {

                    	    						markComposite(elementTypeProvider.getCMSCore_ParametergroupsParameterGroupParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_14);
                    	    lv_parametergroups_15_0=ruleParameterGroup();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCMSCore_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_16=(Token)match(input,18,FOLLOW_15); 

                    				doneLeaf(otherlv_16);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:335:3: ( (lv_feature_17_0= ruleFeature ) )
            // PsiInternalEJSL.g:336:4: (lv_feature_17_0= ruleFeature )
            {
            // PsiInternalEJSL.g:336:4: (lv_feature_17_0= ruleFeature )
            // PsiInternalEJSL.g:337:5: lv_feature_17_0= ruleFeature
            {

            					markComposite(elementTypeProvider.getCMSCore_FeatureFeatureParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_feature_17_0=ruleFeature();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getCMSCore_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_18=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_18);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCMSCore"


    // $ANTLR start "entryRuleCMSExtension"
    // PsiInternalEJSL.g:361:1: entryRuleCMSExtension returns [Boolean current=false] : iv_ruleCMSExtension= ruleCMSExtension EOF ;
    public final Boolean entryRuleCMSExtension() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleCMSExtension = null;


        try {
            // PsiInternalEJSL.g:361:54: (iv_ruleCMSExtension= ruleCMSExtension EOF )
            // PsiInternalEJSL.g:362:2: iv_ruleCMSExtension= ruleCMSExtension EOF
            {
             markComposite(elementTypeProvider.getCMSExtensionElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleCMSExtension=ruleCMSExtension();

            state._fsp--;

             current =iv_ruleCMSExtension; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCMSExtension"


    // $ANTLR start "ruleCMSExtension"
    // PsiInternalEJSL.g:368:1: ruleCMSExtension returns [Boolean current=false] : ( () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}' ) ;
    public final Boolean ruleCMSExtension() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Boolean lv_datatypes_5_0 = null;

        Boolean lv_datatypes_7_0 = null;

        Boolean lv_globalparameters_11_0 = null;

        Boolean lv_parametergroups_15_0 = null;

        Boolean lv_feature_17_0 = null;

        Boolean lv_extensions_20_0 = null;


        try {
            // PsiInternalEJSL.g:369:1: ( ( () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}' ) )
            // PsiInternalEJSL.g:370:2: ( () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}' )
            {
            // PsiInternalEJSL.g:370:2: ( () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}' )
            // PsiInternalEJSL.g:371:3: () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}'
            {
            // PsiInternalEJSL.g:371:3: ()
            // PsiInternalEJSL.g:372:4: 
            {

            				precedeComposite(elementTypeProvider.getCMSExtension_CMSExtensionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getCMSExtension_CMSExtensionKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,24,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getCMSExtension_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_16); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:392:3: (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==20) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // PsiInternalEJSL.g:393:4: otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}'
                    {

                    				markLeaf(elementTypeProvider.getCMSExtension_DatatypesKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,20,FOLLOW_4); 

                    				doneLeaf(otherlv_3);
                    			

                    				markLeaf(elementTypeProvider.getCMSExtension_LeftCurlyBracketKeyword_3_1ElementType());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_9); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:407:4: ( (lv_datatypes_5_0= ruleDatatype ) )
                    // PsiInternalEJSL.g:408:5: (lv_datatypes_5_0= ruleDatatype )
                    {
                    // PsiInternalEJSL.g:408:5: (lv_datatypes_5_0= ruleDatatype )
                    // PsiInternalEJSL.g:409:6: lv_datatypes_5_0= ruleDatatype
                    {

                    						markComposite(elementTypeProvider.getCMSExtension_DatatypesDatatypeParserRuleCall_3_2_0ElementType());
                    					
                    pushFollow(FOLLOW_10);
                    lv_datatypes_5_0=ruleDatatype();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }

                    // PsiInternalEJSL.g:422:4: (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==21) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:423:5: otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getCMSExtension_CommaKeyword_3_3_0ElementType());
                    	    				
                    	    otherlv_6=(Token)match(input,21,FOLLOW_9); 

                    	    					doneLeaf(otherlv_6);
                    	    				
                    	    // PsiInternalEJSL.g:430:5: ( (lv_datatypes_7_0= ruleDatatype ) )
                    	    // PsiInternalEJSL.g:431:6: (lv_datatypes_7_0= ruleDatatype )
                    	    {
                    	    // PsiInternalEJSL.g:431:6: (lv_datatypes_7_0= ruleDatatype )
                    	    // PsiInternalEJSL.g:432:7: lv_datatypes_7_0= ruleDatatype
                    	    {

                    	    							markComposite(elementTypeProvider.getCMSExtension_DatatypesDatatypeParserRuleCall_3_3_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_10);
                    	    lv_datatypes_7_0=ruleDatatype();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    							if(!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCMSExtension_RightCurlyBracketKeyword_3_4ElementType());
                    			
                    otherlv_8=(Token)match(input,18,FOLLOW_17); 

                    				doneLeaf(otherlv_8);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:454:3: (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==22) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // PsiInternalEJSL.g:455:4: otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}'
                    {

                    				markLeaf(elementTypeProvider.getCMSExtension_GlobalparametersKeyword_4_0ElementType());
                    			
                    otherlv_9=(Token)match(input,22,FOLLOW_4); 

                    				doneLeaf(otherlv_9);
                    			

                    				markLeaf(elementTypeProvider.getCMSExtension_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_10=(Token)match(input,16,FOLLOW_12); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:469:4: ( (lv_globalparameters_11_0= ruleParameter ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==34) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:470:5: (lv_globalparameters_11_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:470:5: (lv_globalparameters_11_0= ruleParameter )
                    	    // PsiInternalEJSL.g:471:6: lv_globalparameters_11_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getCMSExtension_GlobalparametersParameterParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_globalparameters_11_0=ruleParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCMSExtension_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_12=(Token)match(input,18,FOLLOW_18); 

                    				doneLeaf(otherlv_12);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:492:3: (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==23) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // PsiInternalEJSL.g:493:4: otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}'
                    {

                    				markLeaf(elementTypeProvider.getCMSExtension_ParametergroupsKeyword_5_0ElementType());
                    			
                    otherlv_13=(Token)match(input,23,FOLLOW_4); 

                    				doneLeaf(otherlv_13);
                    			

                    				markLeaf(elementTypeProvider.getCMSExtension_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_14=(Token)match(input,16,FOLLOW_14); 

                    				doneLeaf(otherlv_14);
                    			
                    // PsiInternalEJSL.g:507:4: ( (lv_parametergroups_15_0= ruleParameterGroup ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==40) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:508:5: (lv_parametergroups_15_0= ruleParameterGroup )
                    	    {
                    	    // PsiInternalEJSL.g:508:5: (lv_parametergroups_15_0= ruleParameterGroup )
                    	    // PsiInternalEJSL.g:509:6: lv_parametergroups_15_0= ruleParameterGroup
                    	    {

                    	    						markComposite(elementTypeProvider.getCMSExtension_ParametergroupsParameterGroupParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_14);
                    	    lv_parametergroups_15_0=ruleParameterGroup();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCMSExtension_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_16=(Token)match(input,18,FOLLOW_19); 

                    				doneLeaf(otherlv_16);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:530:3: ( (lv_feature_17_0= ruleFeature ) )
            // PsiInternalEJSL.g:531:4: (lv_feature_17_0= ruleFeature )
            {
            // PsiInternalEJSL.g:531:4: (lv_feature_17_0= ruleFeature )
            // PsiInternalEJSL.g:532:5: lv_feature_17_0= ruleFeature
            {

            					markComposite(elementTypeProvider.getCMSExtension_FeatureFeatureParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_20);
            lv_feature_17_0=ruleFeature();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:545:3: (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==25) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // PsiInternalEJSL.g:546:4: otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}'
                    {

                    				markLeaf(elementTypeProvider.getCMSExtension_ExtensionsKeyword_7_0ElementType());
                    			
                    otherlv_18=(Token)match(input,25,FOLLOW_4); 

                    				doneLeaf(otherlv_18);
                    			

                    				markLeaf(elementTypeProvider.getCMSExtension_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_19=(Token)match(input,16,FOLLOW_21); 

                    				doneLeaf(otherlv_19);
                    			
                    // PsiInternalEJSL.g:560:4: ( (lv_extensions_20_0= ruleExtension ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==87||LA14_0==90||(LA14_0>=97 && LA14_0<=98)||LA14_0==101||LA14_0==112) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:561:5: (lv_extensions_20_0= ruleExtension )
                    	    {
                    	    // PsiInternalEJSL.g:561:5: (lv_extensions_20_0= ruleExtension )
                    	    // PsiInternalEJSL.g:562:6: lv_extensions_20_0= ruleExtension
                    	    {

                    	    						markComposite(elementTypeProvider.getCMSExtension_ExtensionsExtensionParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_21);
                    	    lv_extensions_20_0=ruleExtension();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCMSExtension_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_21=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_21);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getCMSExtension_RightCurlyBracketKeyword_8ElementType());
            		
            otherlv_22=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_22);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCMSExtension"


    // $ANTLR start "entryRuleFeature"
    // PsiInternalEJSL.g:594:1: entryRuleFeature returns [Boolean current=false] : iv_ruleFeature= ruleFeature EOF ;
    public final Boolean entryRuleFeature() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleFeature = null;


        try {
            // PsiInternalEJSL.g:594:49: (iv_ruleFeature= ruleFeature EOF )
            // PsiInternalEJSL.g:595:2: iv_ruleFeature= ruleFeature EOF
            {
             markComposite(elementTypeProvider.getFeatureElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeature=ruleFeature();

            state._fsp--;

             current =iv_ruleFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // PsiInternalEJSL.g:601:1: ruleFeature returns [Boolean current=false] : ( () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )? ) ;
    public final Boolean ruleFeature() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Boolean lv_entitypackages_3_0 = null;

        Boolean lv_entities_7_0 = null;

        Boolean lv_pages_11_0 = null;

        Boolean lv_sections_15_0 = null;


        try {
            // PsiInternalEJSL.g:602:1: ( ( () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )? ) )
            // PsiInternalEJSL.g:603:2: ( () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )? )
            {
            // PsiInternalEJSL.g:603:2: ( () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )? )
            // PsiInternalEJSL.g:604:3: () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )?
            {
            // PsiInternalEJSL.g:604:3: ()
            // PsiInternalEJSL.g:605:4: 
            {

            				precedeComposite(elementTypeProvider.getFeature_FeatureAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }

            // PsiInternalEJSL.g:611:3: (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==26) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // PsiInternalEJSL.g:612:4: otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}'
                    {

                    				markLeaf(elementTypeProvider.getFeature_EntitypackagesKeyword_1_0ElementType());
                    			
                    otherlv_1=(Token)match(input,26,FOLLOW_4); 

                    				doneLeaf(otherlv_1);
                    			

                    				markLeaf(elementTypeProvider.getFeature_LeftCurlyBracketKeyword_1_1ElementType());
                    			
                    otherlv_2=(Token)match(input,16,FOLLOW_22); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalEJSL.g:626:4: ( (lv_entitypackages_3_0= ruleEntitypackage ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==44) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:627:5: (lv_entitypackages_3_0= ruleEntitypackage )
                    	    {
                    	    // PsiInternalEJSL.g:627:5: (lv_entitypackages_3_0= ruleEntitypackage )
                    	    // PsiInternalEJSL.g:628:6: lv_entitypackages_3_0= ruleEntitypackage
                    	    {

                    	    						markComposite(elementTypeProvider.getFeature_EntitypackagesEntitypackageParserRuleCall_1_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_22);
                    	    lv_entitypackages_3_0=ruleEntitypackage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getFeature_RightCurlyBracketKeyword_1_3ElementType());
                    			
                    otherlv_4=(Token)match(input,18,FOLLOW_23); 

                    				doneLeaf(otherlv_4);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:649:3: (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==27) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // PsiInternalEJSL.g:650:4: otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}'
                    {

                    				markLeaf(elementTypeProvider.getFeature_EntitiesKeyword_2_0ElementType());
                    			
                    otherlv_5=(Token)match(input,27,FOLLOW_4); 

                    				doneLeaf(otherlv_5);
                    			

                    				markLeaf(elementTypeProvider.getFeature_LeftCurlyBracketKeyword_2_1ElementType());
                    			
                    otherlv_6=(Token)match(input,16,FOLLOW_24); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:664:4: ( (lv_entities_7_0= ruleEntity ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==45) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:665:5: (lv_entities_7_0= ruleEntity )
                    	    {
                    	    // PsiInternalEJSL.g:665:5: (lv_entities_7_0= ruleEntity )
                    	    // PsiInternalEJSL.g:666:6: lv_entities_7_0= ruleEntity
                    	    {

                    	    						markComposite(elementTypeProvider.getFeature_EntitiesEntityParserRuleCall_2_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_24);
                    	    lv_entities_7_0=ruleEntity();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getFeature_RightCurlyBracketKeyword_2_3ElementType());
                    			
                    otherlv_8=(Token)match(input,18,FOLLOW_25); 

                    				doneLeaf(otherlv_8);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:687:3: (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==28) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // PsiInternalEJSL.g:688:4: otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}'
                    {

                    				markLeaf(elementTypeProvider.getFeature_PagesKeyword_3_0ElementType());
                    			
                    otherlv_9=(Token)match(input,28,FOLLOW_4); 

                    				doneLeaf(otherlv_9);
                    			

                    				markLeaf(elementTypeProvider.getFeature_LeftCurlyBracketKeyword_3_1ElementType());
                    			
                    otherlv_10=(Token)match(input,16,FOLLOW_26); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:702:4: ( (lv_pages_11_0= rulePage ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==59||LA20_0==66||LA20_0==70||LA20_0==73) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:703:5: (lv_pages_11_0= rulePage )
                    	    {
                    	    // PsiInternalEJSL.g:703:5: (lv_pages_11_0= rulePage )
                    	    // PsiInternalEJSL.g:704:6: lv_pages_11_0= rulePage
                    	    {

                    	    						markComposite(elementTypeProvider.getFeature_PagesPageParserRuleCall_3_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_26);
                    	    lv_pages_11_0=rulePage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getFeature_RightCurlyBracketKeyword_3_3ElementType());
                    			
                    otherlv_12=(Token)match(input,18,FOLLOW_27); 

                    				doneLeaf(otherlv_12);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:725:3: (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==29) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // PsiInternalEJSL.g:726:4: otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}'
                    {

                    				markLeaf(elementTypeProvider.getFeature_SectionsKeyword_4_0ElementType());
                    			
                    otherlv_13=(Token)match(input,29,FOLLOW_4); 

                    				doneLeaf(otherlv_13);
                    			

                    				markLeaf(elementTypeProvider.getFeature_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_14=(Token)match(input,16,FOLLOW_28); 

                    				doneLeaf(otherlv_14);
                    			
                    // PsiInternalEJSL.g:740:4: ( (lv_sections_15_0= ruleSection ) )+
                    int cnt22=0;
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==92||LA22_0==96) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:741:5: (lv_sections_15_0= ruleSection )
                    	    {
                    	    // PsiInternalEJSL.g:741:5: (lv_sections_15_0= ruleSection )
                    	    // PsiInternalEJSL.g:742:6: lv_sections_15_0= ruleSection
                    	    {

                    	    						markComposite(elementTypeProvider.getFeature_SectionsSectionParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_29);
                    	    lv_sections_15_0=ruleSection();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt22 >= 1 ) break loop22;
                                EarlyExitException eee =
                                    new EarlyExitException(22, input);
                                throw eee;
                        }
                        cnt22++;
                    } while (true);


                    				markLeaf(elementTypeProvider.getFeature_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_16=(Token)match(input,18,FOLLOW_2); 

                    				doneLeaf(otherlv_16);
                    			

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleType"
    // PsiInternalEJSL.g:767:1: entryRuleType returns [Boolean current=false] : iv_ruleType= ruleType EOF ;
    public final Boolean entryRuleType() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleType = null;


        try {
            // PsiInternalEJSL.g:767:46: (iv_ruleType= ruleType EOF )
            // PsiInternalEJSL.g:768:2: iv_ruleType= ruleType EOF
            {
             markComposite(elementTypeProvider.getTypeElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;

             current =iv_ruleType; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // PsiInternalEJSL.g:774:1: ruleType returns [Boolean current=false] : (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes ) ;
    public final Boolean ruleType() throws RecognitionException {
        Boolean current = false;

        Boolean this_DatatypeReference_0 = null;

        Boolean this_StandardTypes_1 = null;


        try {
            // PsiInternalEJSL.g:775:1: ( (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes ) )
            // PsiInternalEJSL.g:776:2: (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes )
            {
            // PsiInternalEJSL.g:776:2: (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_STRING) ) {
                alt24=1;
            }
            else if ( ((LA24_0>=154 && LA24_0<=164)) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // PsiInternalEJSL.g:777:3: this_DatatypeReference_0= ruleDatatypeReference
                    {

                    			markComposite(elementTypeProvider.getType_DatatypeReferenceParserRuleCall_0ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_DatatypeReference_0=ruleDatatypeReference();

                    state._fsp--;


                    			current = this_DatatypeReference_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:786:3: this_StandardTypes_1= ruleStandardTypes
                    {

                    			markComposite(elementTypeProvider.getType_StandardTypesParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_StandardTypes_1=ruleStandardTypes();

                    state._fsp--;


                    			current = this_StandardTypes_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleDatatypeReference"
    // PsiInternalEJSL.g:798:1: entryRuleDatatypeReference returns [Boolean current=false] : iv_ruleDatatypeReference= ruleDatatypeReference EOF ;
    public final Boolean entryRuleDatatypeReference() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDatatypeReference = null;


        try {
            // PsiInternalEJSL.g:798:59: (iv_ruleDatatypeReference= ruleDatatypeReference EOF )
            // PsiInternalEJSL.g:799:2: iv_ruleDatatypeReference= ruleDatatypeReference EOF
            {
             markComposite(elementTypeProvider.getDatatypeReferenceElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatatypeReference=ruleDatatypeReference();

            state._fsp--;

             current =iv_ruleDatatypeReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatatypeReference"


    // $ANTLR start "ruleDatatypeReference"
    // PsiInternalEJSL.g:805:1: ruleDatatypeReference returns [Boolean current=false] : ( (otherlv_0= RULE_STRING ) ) ;
    public final Boolean ruleDatatypeReference() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;

        try {
            // PsiInternalEJSL.g:806:1: ( ( (otherlv_0= RULE_STRING ) ) )
            // PsiInternalEJSL.g:807:2: ( (otherlv_0= RULE_STRING ) )
            {
            // PsiInternalEJSL.g:807:2: ( (otherlv_0= RULE_STRING ) )
            // PsiInternalEJSL.g:808:3: (otherlv_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:808:3: (otherlv_0= RULE_STRING )
            // PsiInternalEJSL.g:809:4: otherlv_0= RULE_STRING
            {

            				if (!current) {
            					associateWithSemanticElement();
            					current = true;
            				}
            			

            				markLeaf(elementTypeProvider.getDatatypeReference_TypeDatatypeCrossReference_0ElementType());
            			
            otherlv_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            				doneLeaf(otherlv_0);
            			

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDatatypeReference"


    // $ANTLR start "entryRuleStandardTypes"
    // PsiInternalEJSL.g:827:1: entryRuleStandardTypes returns [Boolean current=false] : iv_ruleStandardTypes= ruleStandardTypes EOF ;
    public final Boolean entryRuleStandardTypes() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleStandardTypes = null;


        try {
            // PsiInternalEJSL.g:827:55: (iv_ruleStandardTypes= ruleStandardTypes EOF )
            // PsiInternalEJSL.g:828:2: iv_ruleStandardTypes= ruleStandardTypes EOF
            {
             markComposite(elementTypeProvider.getStandardTypesElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleStandardTypes=ruleStandardTypes();

            state._fsp--;

             current =iv_ruleStandardTypes; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStandardTypes"


    // $ANTLR start "ruleStandardTypes"
    // PsiInternalEJSL.g:834:1: ruleStandardTypes returns [Boolean current=false] : ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )? ) ;
    public final Boolean ruleStandardTypes() throws RecognitionException {
        Boolean current = false;

        Token lv_notnull_1_0=null;
        Token otherlv_2=null;
        Token lv_default_3_0=null;
        Token lv_autoincrement_4_0=null;
        Boolean lv_type_0_0 = null;


        try {
            // PsiInternalEJSL.g:835:1: ( ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )? ) )
            // PsiInternalEJSL.g:836:2: ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )? )
            {
            // PsiInternalEJSL.g:836:2: ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )? )
            // PsiInternalEJSL.g:837:3: ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )?
            {
            // PsiInternalEJSL.g:837:3: ( (lv_type_0_0= ruleStandardTypeKinds ) )
            // PsiInternalEJSL.g:838:4: (lv_type_0_0= ruleStandardTypeKinds )
            {
            // PsiInternalEJSL.g:838:4: (lv_type_0_0= ruleStandardTypeKinds )
            // PsiInternalEJSL.g:839:5: lv_type_0_0= ruleStandardTypeKinds
            {

            					markComposite(elementTypeProvider.getStandardTypes_TypeStandardTypeKindsEnumRuleCall_0_0ElementType());
            				
            pushFollow(FOLLOW_30);
            lv_type_0_0=ruleStandardTypeKinds();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:852:3: ( (lv_notnull_1_0= 'Not Null' ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==30) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // PsiInternalEJSL.g:853:4: (lv_notnull_1_0= 'Not Null' )
                    {
                    // PsiInternalEJSL.g:853:4: (lv_notnull_1_0= 'Not Null' )
                    // PsiInternalEJSL.g:854:5: lv_notnull_1_0= 'Not Null'
                    {

                    					markLeaf(elementTypeProvider.getStandardTypes_NotnullNotNullKeyword_1_0ElementType());
                    				
                    lv_notnull_1_0=(Token)match(input,30,FOLLOW_31); 

                    					doneLeaf(lv_notnull_1_0);
                    				

                    					if (!current) {
                    						associateWithSemanticElement();
                    						current = true;
                    					}
                    				

                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:869:3: (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==31) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // PsiInternalEJSL.g:870:4: otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getStandardTypes_DefaultKeyword_2_0ElementType());
                    			
                    otherlv_2=(Token)match(input,31,FOLLOW_3); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalEJSL.g:877:4: ( (lv_default_3_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:878:5: (lv_default_3_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:878:5: (lv_default_3_0= RULE_STRING )
                    // PsiInternalEJSL.g:879:6: lv_default_3_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getStandardTypes_DefaultSTRINGTerminalRuleCall_2_1_0ElementType());
                    					
                    lv_default_3_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_default_3_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:895:3: ( (lv_autoincrement_4_0= 'Auto Increment' ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==32) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // PsiInternalEJSL.g:896:4: (lv_autoincrement_4_0= 'Auto Increment' )
                    {
                    // PsiInternalEJSL.g:896:4: (lv_autoincrement_4_0= 'Auto Increment' )
                    // PsiInternalEJSL.g:897:5: lv_autoincrement_4_0= 'Auto Increment'
                    {

                    					markLeaf(elementTypeProvider.getStandardTypes_AutoincrementAutoIncrementKeyword_3_0ElementType());
                    				
                    lv_autoincrement_4_0=(Token)match(input,32,FOLLOW_2); 

                    					doneLeaf(lv_autoincrement_4_0);
                    				

                    					if (!current) {
                    						associateWithSemanticElement();
                    						current = true;
                    					}
                    				

                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStandardTypes"


    // $ANTLR start "entryRuleDatatype"
    // PsiInternalEJSL.g:916:1: entryRuleDatatype returns [Boolean current=false] : iv_ruleDatatype= ruleDatatype EOF ;
    public final Boolean entryRuleDatatype() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDatatype = null;


        try {
            // PsiInternalEJSL.g:916:50: (iv_ruleDatatype= ruleDatatype EOF )
            // PsiInternalEJSL.g:917:2: iv_ruleDatatype= ruleDatatype EOF
            {
             markComposite(elementTypeProvider.getDatatypeElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatatype=ruleDatatype();

            state._fsp--;

             current =iv_ruleDatatype; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatatype"


    // $ANTLR start "ruleDatatype"
    // PsiInternalEJSL.g:923:1: ruleDatatype returns [Boolean current=false] : ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) ) ;
    public final Boolean ruleDatatype() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;

        try {
            // PsiInternalEJSL.g:924:1: ( ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) ) )
            // PsiInternalEJSL.g:925:2: ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) )
            {
            // PsiInternalEJSL.g:925:2: ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) )
            // PsiInternalEJSL.g:926:3: () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) )
            {
            // PsiInternalEJSL.g:926:3: ()
            // PsiInternalEJSL.g:927:4: 
            {

            				precedeComposite(elementTypeProvider.getDatatype_DatatypeAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getDatatype_DatatypeKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,33,FOLLOW_3); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:940:3: ( (lv_name_2_0= RULE_STRING ) )
            // PsiInternalEJSL.g:941:4: (lv_name_2_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:941:4: (lv_name_2_0= RULE_STRING )
            // PsiInternalEJSL.g:942:5: lv_name_2_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getDatatype_NameSTRINGTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDatatype"


    // $ANTLR start "entryRuleParameter"
    // PsiInternalEJSL.g:961:1: entryRuleParameter returns [Boolean current=false] : iv_ruleParameter= ruleParameter EOF ;
    public final Boolean entryRuleParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParameter = null;


        try {
            // PsiInternalEJSL.g:961:51: (iv_ruleParameter= ruleParameter EOF )
            // PsiInternalEJSL.g:962:2: iv_ruleParameter= ruleParameter EOF
            {
             markComposite(elementTypeProvider.getParameterElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // PsiInternalEJSL.g:968:1: ruleParameter returns [Boolean current=false] : ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}' ) ;
    public final Boolean ruleParameter() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_defaultvalue_7_0=null;
        Token otherlv_8=null;
        Token lv_label_9_0=null;
        Token otherlv_10=null;
        Token lv_size_11_0=null;
        Token otherlv_12=null;
        Token lv_descripton_13_0=null;
        Token otherlv_14=null;
        Boolean lv_dtype_5_0 = null;


        try {
            // PsiInternalEJSL.g:969:1: ( ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}' ) )
            // PsiInternalEJSL.g:970:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}' )
            {
            // PsiInternalEJSL.g:970:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}' )
            // PsiInternalEJSL.g:971:3: () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}'
            {
            // PsiInternalEJSL.g:971:3: ()
            // PsiInternalEJSL.g:972:4: 
            {

            				precedeComposite(elementTypeProvider.getParameter_ParameterAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getParameter_ParameterKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,34,FOLLOW_33); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:985:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:986:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:986:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:987:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getParameter_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getParameter_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_34); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getParameter_TypeKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,35,FOLLOW_35); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:1016:3: ( (lv_dtype_5_0= ruleType ) )
            // PsiInternalEJSL.g:1017:4: (lv_dtype_5_0= ruleType )
            {
            // PsiInternalEJSL.g:1017:4: (lv_dtype_5_0= ruleType )
            // PsiInternalEJSL.g:1018:5: lv_dtype_5_0= ruleType
            {

            					markComposite(elementTypeProvider.getParameter_DtypeTypeParserRuleCall_5_0ElementType());
            				
            pushFollow(FOLLOW_36);
            lv_dtype_5_0=ruleType();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:1031:3: (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==36) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // PsiInternalEJSL.g:1032:4: otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getParameter_DefaultvalueKeyword_6_0ElementType());
                    			
                    otherlv_6=(Token)match(input,36,FOLLOW_3); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:1039:4: ( (lv_defaultvalue_7_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:1040:5: (lv_defaultvalue_7_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:1040:5: (lv_defaultvalue_7_0= RULE_STRING )
                    // PsiInternalEJSL.g:1041:6: lv_defaultvalue_7_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getParameter_DefaultvalueSTRINGTerminalRuleCall_6_1_0ElementType());
                    					
                    lv_defaultvalue_7_0=(Token)match(input,RULE_STRING,FOLLOW_37); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_defaultvalue_7_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:1057:3: (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==37) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // PsiInternalEJSL.g:1058:4: otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getParameter_LabelKeyword_7_0ElementType());
                    			
                    otherlv_8=(Token)match(input,37,FOLLOW_3); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:1065:4: ( (lv_label_9_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:1066:5: (lv_label_9_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:1066:5: (lv_label_9_0= RULE_STRING )
                    // PsiInternalEJSL.g:1067:6: lv_label_9_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getParameter_LabelSTRINGTerminalRuleCall_7_1_0ElementType());
                    					
                    lv_label_9_0=(Token)match(input,RULE_STRING,FOLLOW_38); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_label_9_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:1083:3: (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==38) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // PsiInternalEJSL.g:1084:4: otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) )
                    {

                    				markLeaf(elementTypeProvider.getParameter_SizeKeyword_8_0ElementType());
                    			
                    otherlv_10=(Token)match(input,38,FOLLOW_39); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:1091:4: ( (lv_size_11_0= RULE_INT ) )
                    // PsiInternalEJSL.g:1092:5: (lv_size_11_0= RULE_INT )
                    {
                    // PsiInternalEJSL.g:1092:5: (lv_size_11_0= RULE_INT )
                    // PsiInternalEJSL.g:1093:6: lv_size_11_0= RULE_INT
                    {

                    						markLeaf(elementTypeProvider.getParameter_SizeINTTerminalRuleCall_8_1_0ElementType());
                    					
                    lv_size_11_0=(Token)match(input,RULE_INT,FOLLOW_40); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_size_11_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:1109:3: (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==39) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // PsiInternalEJSL.g:1110:4: otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getParameter_DescriptionKeyword_9_0ElementType());
                    			
                    otherlv_12=(Token)match(input,39,FOLLOW_3); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:1117:4: ( (lv_descripton_13_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:1118:5: (lv_descripton_13_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:1118:5: (lv_descripton_13_0= RULE_STRING )
                    // PsiInternalEJSL.g:1119:6: lv_descripton_13_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getParameter_DescriptonSTRINGTerminalRuleCall_9_1_0ElementType());
                    					
                    lv_descripton_13_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_descripton_13_0);
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getParameter_RightCurlyBracketKeyword_10ElementType());
            		
            otherlv_14=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_14);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleParameterGroup"
    // PsiInternalEJSL.g:1146:1: entryRuleParameterGroup returns [Boolean current=false] : iv_ruleParameterGroup= ruleParameterGroup EOF ;
    public final Boolean entryRuleParameterGroup() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParameterGroup = null;


        try {
            // PsiInternalEJSL.g:1146:56: (iv_ruleParameterGroup= ruleParameterGroup EOF )
            // PsiInternalEJSL.g:1147:2: iv_ruleParameterGroup= ruleParameterGroup EOF
            {
             markComposite(elementTypeProvider.getParameterGroupElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameterGroup=ruleParameterGroup();

            state._fsp--;

             current =iv_ruleParameterGroup; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterGroup"


    // $ANTLR start "ruleParameterGroup"
    // PsiInternalEJSL.g:1153:1: ruleParameterGroup returns [Boolean current=false] : ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}' ) ;
    public final Boolean ruleParameterGroup() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_label_5_0=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Boolean lv_parameters_9_0 = null;


        try {
            // PsiInternalEJSL.g:1154:1: ( ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}' ) )
            // PsiInternalEJSL.g:1155:2: ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}' )
            {
            // PsiInternalEJSL.g:1155:2: ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}' )
            // PsiInternalEJSL.g:1156:3: () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}'
            {
            // PsiInternalEJSL.g:1156:3: ()
            // PsiInternalEJSL.g:1157:4: 
            {

            				precedeComposite(elementTypeProvider.getParameterGroup_ParameterGroupAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getParameterGroup_ParameterGroupKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,40,FOLLOW_33); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1170:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:1171:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:1171:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:1172:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getParameterGroup_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getParameterGroup_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_41); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:1194:3: (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==37) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // PsiInternalEJSL.g:1195:4: otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getParameterGroup_LabelKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,37,FOLLOW_3); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:1202:4: ( (lv_label_5_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:1203:5: (lv_label_5_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:1203:5: (lv_label_5_0= RULE_STRING )
                    // PsiInternalEJSL.g:1204:6: lv_label_5_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getParameterGroup_LabelSTRINGTerminalRuleCall_4_1_0ElementType());
                    					
                    lv_label_5_0=(Token)match(input,RULE_STRING,FOLLOW_42); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_label_5_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:1220:3: (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' )
            // PsiInternalEJSL.g:1221:4: otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}'
            {

            				markLeaf(elementTypeProvider.getParameterGroup_ParametersKeyword_5_0ElementType());
            			
            otherlv_6=(Token)match(input,41,FOLLOW_4); 

            				doneLeaf(otherlv_6);
            			

            				markLeaf(elementTypeProvider.getParameterGroup_LeftCurlyBracketKeyword_5_1ElementType());
            			
            otherlv_7=(Token)match(input,16,FOLLOW_43); 

            				doneLeaf(otherlv_7);
            			
            // PsiInternalEJSL.g:1235:4: ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )*
            loop33:
            do {
                int alt33=3;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID) ) {
                    alt33=1;
                }
                else if ( (LA33_0==34) ) {
                    alt33=2;
                }


                switch (alt33) {
            	case 1 :
            	    // PsiInternalEJSL.g:1236:5: ( (otherlv_8= RULE_ID ) )
            	    {
            	    // PsiInternalEJSL.g:1236:5: ( (otherlv_8= RULE_ID ) )
            	    // PsiInternalEJSL.g:1237:6: (otherlv_8= RULE_ID )
            	    {
            	    // PsiInternalEJSL.g:1237:6: (otherlv_8= RULE_ID )
            	    // PsiInternalEJSL.g:1238:7: otherlv_8= RULE_ID
            	    {

            	    							if (!current) {
            	    								associateWithSemanticElement();
            	    								current = true;
            	    							}
            	    						

            	    							markLeaf(elementTypeProvider.getParameterGroup_GlobalparametersParameterCrossReference_5_2_0_0ElementType());
            	    						
            	    otherlv_8=(Token)match(input,RULE_ID,FOLLOW_43); 

            	    							doneLeaf(otherlv_8);
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // PsiInternalEJSL.g:1254:5: ( (lv_parameters_9_0= ruleParameter ) )
            	    {
            	    // PsiInternalEJSL.g:1254:5: ( (lv_parameters_9_0= ruleParameter ) )
            	    // PsiInternalEJSL.g:1255:6: (lv_parameters_9_0= ruleParameter )
            	    {
            	    // PsiInternalEJSL.g:1255:6: (lv_parameters_9_0= ruleParameter )
            	    // PsiInternalEJSL.g:1256:7: lv_parameters_9_0= ruleParameter
            	    {

            	    							markComposite(elementTypeProvider.getParameterGroup_ParametersParameterParserRuleCall_5_2_1_0ElementType());
            	    						
            	    pushFollow(FOLLOW_43);
            	    lv_parameters_9_0=ruleParameter();

            	    state._fsp--;


            	    							doneComposite();
            	    							if(!current) {
            	    								associateWithSemanticElement();
            	    								current = true;
            	    							}
            	    						

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            				markLeaf(elementTypeProvider.getParameterGroup_RightCurlyBracketKeyword_5_3ElementType());
            			
            otherlv_10=(Token)match(input,18,FOLLOW_7); 

            				doneLeaf(otherlv_10);
            			

            }


            			markLeaf(elementTypeProvider.getParameterGroup_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_11=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_11);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterGroup"


    // $ANTLR start "entryRulePageAction"
    // PsiInternalEJSL.g:1289:1: entryRulePageAction returns [Boolean current=false] : iv_rulePageAction= rulePageAction EOF ;
    public final Boolean entryRulePageAction() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePageAction = null;


        try {
            // PsiInternalEJSL.g:1289:52: (iv_rulePageAction= rulePageAction EOF )
            // PsiInternalEJSL.g:1290:2: iv_rulePageAction= rulePageAction EOF
            {
             markComposite(elementTypeProvider.getPageActionElementType()); 
            pushFollow(FOLLOW_1);
            iv_rulePageAction=rulePageAction();

            state._fsp--;

             current =iv_rulePageAction; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePageAction"


    // $ANTLR start "rulePageAction"
    // PsiInternalEJSL.g:1296:1: rulePageAction returns [Boolean current=false] : ( () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}' ) ;
    public final Boolean rulePageAction() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Boolean lv_pageActionType_5_0 = null;

        Boolean lv_pageActionPosition_7_0 = null;


        try {
            // PsiInternalEJSL.g:1297:1: ( ( () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}' ) )
            // PsiInternalEJSL.g:1298:2: ( () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}' )
            {
            // PsiInternalEJSL.g:1298:2: ( () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}' )
            // PsiInternalEJSL.g:1299:3: () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}'
            {
            // PsiInternalEJSL.g:1299:3: ()
            // PsiInternalEJSL.g:1300:4: 
            {

            				precedeComposite(elementTypeProvider.getPageAction_PageActionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getPageAction_PageActionKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,42,FOLLOW_33); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1313:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:1314:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:1314:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:1315:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getPageAction_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getPageAction_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_34); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getPageAction_TypeKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,35,FOLLOW_44); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:1344:3: ( (lv_pageActionType_5_0= rulePageActionKind ) )
            // PsiInternalEJSL.g:1345:4: (lv_pageActionType_5_0= rulePageActionKind )
            {
            // PsiInternalEJSL.g:1345:4: (lv_pageActionType_5_0= rulePageActionKind )
            // PsiInternalEJSL.g:1346:5: lv_pageActionType_5_0= rulePageActionKind
            {

            					markComposite(elementTypeProvider.getPageAction_PageActionTypePageActionKindEnumRuleCall_5_0ElementType());
            				
            pushFollow(FOLLOW_45);
            lv_pageActionType_5_0=rulePageActionKind();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getPageAction_PositionKeyword_6ElementType());
            		
            otherlv_6=(Token)match(input,43,FOLLOW_46); 

            			doneLeaf(otherlv_6);
            		
            // PsiInternalEJSL.g:1366:3: ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) )
            // PsiInternalEJSL.g:1367:4: (lv_pageActionPosition_7_0= rulePageActionPositionKind )
            {
            // PsiInternalEJSL.g:1367:4: (lv_pageActionPosition_7_0= rulePageActionPositionKind )
            // PsiInternalEJSL.g:1368:5: lv_pageActionPosition_7_0= rulePageActionPositionKind
            {

            					markComposite(elementTypeProvider.getPageAction_PageActionPositionPageActionPositionKindEnumRuleCall_7_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_pageActionPosition_7_0=rulePageActionPositionKind();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getPageAction_RightCurlyBracketKeyword_8ElementType());
            		
            otherlv_8=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_8);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageAction"


    // $ANTLR start "entryRuleEntitypackage"
    // PsiInternalEJSL.g:1392:1: entryRuleEntitypackage returns [Boolean current=false] : iv_ruleEntitypackage= ruleEntitypackage EOF ;
    public final Boolean entryRuleEntitypackage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEntitypackage = null;


        try {
            // PsiInternalEJSL.g:1392:55: (iv_ruleEntitypackage= ruleEntitypackage EOF )
            // PsiInternalEJSL.g:1393:2: iv_ruleEntitypackage= ruleEntitypackage EOF
            {
             markComposite(elementTypeProvider.getEntitypackageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntitypackage=ruleEntitypackage();

            state._fsp--;

             current =iv_ruleEntitypackage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEntitypackage"


    // $ANTLR start "ruleEntitypackage"
    // PsiInternalEJSL.g:1399:1: ruleEntitypackage returns [Boolean current=false] : ( () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' ) ;
    public final Boolean ruleEntitypackage() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Boolean lv_entitypackages_6_0 = null;

        Boolean lv_entities_10_0 = null;

        Boolean lv_datatypes_14_0 = null;


        try {
            // PsiInternalEJSL.g:1400:1: ( ( () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' ) )
            // PsiInternalEJSL.g:1401:2: ( () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' )
            {
            // PsiInternalEJSL.g:1401:2: ( () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' )
            // PsiInternalEJSL.g:1402:3: () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}'
            {
            // PsiInternalEJSL.g:1402:3: ()
            // PsiInternalEJSL.g:1403:4: 
            {

            				precedeComposite(elementTypeProvider.getEntitypackage_EntitypackageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getEntitypackage_EntitypackageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,44,FOLLOW_33); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1416:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:1417:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:1417:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:1418:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getEntitypackage_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getEntitypackage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_47); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:1440:3: (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==26) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // PsiInternalEJSL.g:1441:4: otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}'
                    {

                    				markLeaf(elementTypeProvider.getEntitypackage_EntitypackagesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,26,FOLLOW_4); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getEntitypackage_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_22); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:1455:4: ( (lv_entitypackages_6_0= ruleEntitypackage ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==44) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1456:5: (lv_entitypackages_6_0= ruleEntitypackage )
                    	    {
                    	    // PsiInternalEJSL.g:1456:5: (lv_entitypackages_6_0= ruleEntitypackage )
                    	    // PsiInternalEJSL.g:1457:6: lv_entitypackages_6_0= ruleEntitypackage
                    	    {

                    	    						markComposite(elementTypeProvider.getEntitypackage_EntitypackagesEntitypackageParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_22);
                    	    lv_entitypackages_6_0=ruleEntitypackage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEntitypackage_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_7=(Token)match(input,18,FOLLOW_48); 

                    				doneLeaf(otherlv_7);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:1478:3: (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==27) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // PsiInternalEJSL.g:1479:4: otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getEntitypackage_EntitiesKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,27,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getEntitypackage_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_24); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:1493:4: ( (lv_entities_10_0= ruleEntity ) )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==45) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1494:5: (lv_entities_10_0= ruleEntity )
                    	    {
                    	    // PsiInternalEJSL.g:1494:5: (lv_entities_10_0= ruleEntity )
                    	    // PsiInternalEJSL.g:1495:6: lv_entities_10_0= ruleEntity
                    	    {

                    	    						markComposite(elementTypeProvider.getEntitypackage_EntitiesEntityParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_24);
                    	    lv_entities_10_0=ruleEntity();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEntitypackage_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_49); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:1516:3: (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==20) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // PsiInternalEJSL.g:1517:4: otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}'
                    {

                    				markLeaf(elementTypeProvider.getEntitypackage_DatatypesKeyword_6_0ElementType());
                    			
                    otherlv_12=(Token)match(input,20,FOLLOW_4); 

                    				doneLeaf(otherlv_12);
                    			

                    				markLeaf(elementTypeProvider.getEntitypackage_LeftCurlyBracketKeyword_6_1ElementType());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_50); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:1531:4: ( (lv_datatypes_14_0= ruleDatatype ) )*
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==33) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1532:5: (lv_datatypes_14_0= ruleDatatype )
                    	    {
                    	    // PsiInternalEJSL.g:1532:5: (lv_datatypes_14_0= ruleDatatype )
                    	    // PsiInternalEJSL.g:1533:6: lv_datatypes_14_0= ruleDatatype
                    	    {

                    	    						markComposite(elementTypeProvider.getEntitypackage_DatatypesDatatypeParserRuleCall_6_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_50);
                    	    lv_datatypes_14_0=ruleDatatype();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop38;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEntitypackage_RightCurlyBracketKeyword_6_3ElementType());
                    			
                    otherlv_15=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_15);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getEntitypackage_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_16=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_16);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEntitypackage"


    // $ANTLR start "entryRuleEntity"
    // PsiInternalEJSL.g:1565:1: entryRuleEntity returns [Boolean current=false] : iv_ruleEntity= ruleEntity EOF ;
    public final Boolean entryRuleEntity() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEntity = null;


        try {
            // PsiInternalEJSL.g:1565:48: (iv_ruleEntity= ruleEntity EOF )
            // PsiInternalEJSL.g:1566:2: iv_ruleEntity= ruleEntity EOF
            {
             markComposite(elementTypeProvider.getEntityElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntity=ruleEntity();

            state._fsp--;

             current =iv_ruleEntity; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // PsiInternalEJSL.g:1572:1: ruleEntity returns [Boolean current=false] : ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' ) ;
    public final Boolean ruleEntity() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_attributes_8_0 = null;

        Boolean lv_references_12_0 = null;


        try {
            // PsiInternalEJSL.g:1573:1: ( ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' ) )
            // PsiInternalEJSL.g:1574:2: ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' )
            {
            // PsiInternalEJSL.g:1574:2: ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' )
            // PsiInternalEJSL.g:1575:3: () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}'
            {
            // PsiInternalEJSL.g:1575:3: ()
            // PsiInternalEJSL.g:1576:4: 
            {

            				precedeComposite(elementTypeProvider.getEntity_EntityAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getEntity_EntityKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,45,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1589:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:1590:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:1590:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:1591:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getEntity_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_52);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:1604:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==46) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // PsiInternalEJSL.g:1605:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {

                    				markLeaf(elementTypeProvider.getEntity_ExtendsKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,46,FOLLOW_51); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalEJSL.g:1612:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:1613:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:1613:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:1614:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getEntity_SupertypeEntityCrossReference_3_1_0ElementType());
                    					
                    pushFollow(FOLLOW_4);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getEntity_LeftCurlyBracketKeyword_4ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_53); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:1637:3: (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==47) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // PsiInternalEJSL.g:1638:4: otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}'
                    {

                    				markLeaf(elementTypeProvider.getEntity_AttributesKeyword_5_0ElementType());
                    			
                    otherlv_6=(Token)match(input,47,FOLLOW_4); 

                    				doneLeaf(otherlv_6);
                    			

                    				markLeaf(elementTypeProvider.getEntity_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_7=(Token)match(input,16,FOLLOW_54); 

                    				doneLeaf(otherlv_7);
                    			
                    // PsiInternalEJSL.g:1652:4: ( (lv_attributes_8_0= ruleAttribute ) )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==49) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1653:5: (lv_attributes_8_0= ruleAttribute )
                    	    {
                    	    // PsiInternalEJSL.g:1653:5: (lv_attributes_8_0= ruleAttribute )
                    	    // PsiInternalEJSL.g:1654:6: lv_attributes_8_0= ruleAttribute
                    	    {

                    	    						markComposite(elementTypeProvider.getEntity_AttributesAttributeParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_54);
                    	    lv_attributes_8_0=ruleAttribute();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEntity_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_9=(Token)match(input,18,FOLLOW_55); 

                    				doneLeaf(otherlv_9);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:1675:3: (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==48) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // PsiInternalEJSL.g:1676:4: otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}'
                    {

                    				markLeaf(elementTypeProvider.getEntity_ReferencesKeyword_6_0ElementType());
                    			
                    otherlv_10=(Token)match(input,48,FOLLOW_4); 

                    				doneLeaf(otherlv_10);
                    			

                    				markLeaf(elementTypeProvider.getEntity_LeftCurlyBracketKeyword_6_1ElementType());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_56); 

                    				doneLeaf(otherlv_11);
                    			
                    // PsiInternalEJSL.g:1690:4: ( (lv_references_12_0= ruleReference ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==53) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1691:5: (lv_references_12_0= ruleReference )
                    	    {
                    	    // PsiInternalEJSL.g:1691:5: (lv_references_12_0= ruleReference )
                    	    // PsiInternalEJSL.g:1692:6: lv_references_12_0= ruleReference
                    	    {

                    	    						markComposite(elementTypeProvider.getEntity_ReferencesReferenceParserRuleCall_6_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_56);
                    	    lv_references_12_0=ruleReference();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEntity_RightCurlyBracketKeyword_6_3ElementType());
                    			
                    otherlv_13=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_13);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getEntity_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_14=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_14);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleAttribute"
    // PsiInternalEJSL.g:1724:1: entryRuleAttribute returns [Boolean current=false] : iv_ruleAttribute= ruleAttribute EOF ;
    public final Boolean entryRuleAttribute() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAttribute = null;


        try {
            // PsiInternalEJSL.g:1724:51: (iv_ruleAttribute= ruleAttribute EOF )
            // PsiInternalEJSL.g:1725:2: iv_ruleAttribute= ruleAttribute EOF
            {
             markComposite(elementTypeProvider.getAttributeElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // PsiInternalEJSL.g:1731:1: ruleAttribute returns [Boolean current=false] : ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}' ) ;
    public final Boolean ruleAttribute() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_isunique_6_0=null;
        Token otherlv_7=null;
        Token lv_id_9_0=null;
        Token otherlv_10=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_type_5_0 = null;


        try {
            // PsiInternalEJSL.g:1732:1: ( ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}' ) )
            // PsiInternalEJSL.g:1733:2: ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}' )
            {
            // PsiInternalEJSL.g:1733:2: ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}' )
            // PsiInternalEJSL.g:1734:3: () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}'
            {
            // PsiInternalEJSL.g:1734:3: ()
            // PsiInternalEJSL.g:1735:4: 
            {

            				precedeComposite(elementTypeProvider.getAttribute_AttributeAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getAttribute_AttributeKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,49,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1748:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:1749:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:1749:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:1750:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getAttribute_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getAttribute_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_34); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getAttribute_TypeKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,35,FOLLOW_35); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:1777:3: ( (lv_type_5_0= ruleType ) )
            // PsiInternalEJSL.g:1778:4: (lv_type_5_0= ruleType )
            {
            // PsiInternalEJSL.g:1778:4: (lv_type_5_0= ruleType )
            // PsiInternalEJSL.g:1779:5: lv_type_5_0= ruleType
            {

            					markComposite(elementTypeProvider.getAttribute_TypeTypeParserRuleCall_5_0ElementType());
            				
            pushFollow(FOLLOW_57);
            lv_type_5_0=ruleType();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:1792:3: ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==50) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // PsiInternalEJSL.g:1793:4: ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )?
                    {
                    // PsiInternalEJSL.g:1793:4: ( (lv_isunique_6_0= 'Unique attribute' ) )
                    // PsiInternalEJSL.g:1794:5: (lv_isunique_6_0= 'Unique attribute' )
                    {
                    // PsiInternalEJSL.g:1794:5: (lv_isunique_6_0= 'Unique attribute' )
                    // PsiInternalEJSL.g:1795:6: lv_isunique_6_0= 'Unique attribute'
                    {

                    						markLeaf(elementTypeProvider.getAttribute_IsuniqueUniqueAttributeKeyword_6_0_0ElementType());
                    					
                    lv_isunique_6_0=(Token)match(input,50,FOLLOW_58); 

                    						doneLeaf(lv_isunique_6_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }

                    // PsiInternalEJSL.g:1810:4: (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==51) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // PsiInternalEJSL.g:1811:5: otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) )
                            {

                            					markLeaf(elementTypeProvider.getAttribute_WithKeyword_6_1_0ElementType());
                            				
                            otherlv_7=(Token)match(input,51,FOLLOW_59); 

                            					doneLeaf(otherlv_7);
                            				
                            // PsiInternalEJSL.g:1818:5: ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) )
                            int alt45=2;
                            int LA45_0 = input.LA(1);

                            if ( (LA45_0==RULE_ID||LA45_0==136) ) {
                                alt45=1;
                            }
                            else if ( (LA45_0==52) ) {
                                alt45=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 45, 0, input);

                                throw nvae;
                            }
                            switch (alt45) {
                                case 1 :
                                    // PsiInternalEJSL.g:1819:6: ( ( ruleQualifiedName ) )
                                    {
                                    // PsiInternalEJSL.g:1819:6: ( ( ruleQualifiedName ) )
                                    // PsiInternalEJSL.g:1820:7: ( ruleQualifiedName )
                                    {
                                    // PsiInternalEJSL.g:1820:7: ( ruleQualifiedName )
                                    // PsiInternalEJSL.g:1821:8: ruleQualifiedName
                                    {

                                    								if (!current) {
                                    									associateWithSemanticElement();
                                    									current = true;
                                    								}
                                    							

                                    								markComposite(elementTypeProvider.getAttribute_WithattributeAttributeCrossReference_6_1_1_0_0ElementType());
                                    							
                                    pushFollow(FOLLOW_7);
                                    ruleQualifiedName();

                                    state._fsp--;


                                    								doneComposite();
                                    							

                                    }


                                    }


                                    }
                                    break;
                                case 2 :
                                    // PsiInternalEJSL.g:1837:6: ( (lv_id_9_0= 'ID' ) )
                                    {
                                    // PsiInternalEJSL.g:1837:6: ( (lv_id_9_0= 'ID' ) )
                                    // PsiInternalEJSL.g:1838:7: (lv_id_9_0= 'ID' )
                                    {
                                    // PsiInternalEJSL.g:1838:7: (lv_id_9_0= 'ID' )
                                    // PsiInternalEJSL.g:1839:8: lv_id_9_0= 'ID'
                                    {

                                    								markLeaf(elementTypeProvider.getAttribute_IdIDKeyword_6_1_1_1_0ElementType());
                                    							
                                    lv_id_9_0=(Token)match(input,52,FOLLOW_7); 

                                    								doneLeaf(lv_id_9_0);
                                    							

                                    								if (!current) {
                                    									associateWithSemanticElement();
                                    									current = true;
                                    								}
                                    							

                                    }


                                    }


                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getAttribute_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_10=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_10);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleReference"
    // PsiInternalEJSL.g:1868:1: entryRuleReference returns [Boolean current=false] : iv_ruleReference= ruleReference EOF ;
    public final Boolean entryRuleReference() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleReference = null;


        try {
            // PsiInternalEJSL.g:1868:51: (iv_ruleReference= ruleReference EOF )
            // PsiInternalEJSL.g:1869:2: iv_ruleReference= ruleReference EOF
            {
             markComposite(elementTypeProvider.getReferenceElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleReference=ruleReference();

            state._fsp--;

             current =iv_ruleReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReference"


    // $ANTLR start "ruleReference"
    // PsiInternalEJSL.g:1875:1: ruleReference returns [Boolean current=false] : ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}' ) ;
    public final Boolean ruleReference() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token lv_id_11_0=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Boolean lv_lower_14_0 = null;

        Boolean lv_upper_16_0 = null;


        try {
            // PsiInternalEJSL.g:1876:1: ( ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}' ) )
            // PsiInternalEJSL.g:1877:2: ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}' )
            {
            // PsiInternalEJSL.g:1877:2: ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}' )
            // PsiInternalEJSL.g:1878:3: () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}'
            {
            // PsiInternalEJSL.g:1878:3: ()
            // PsiInternalEJSL.g:1879:4: 
            {

            				precedeComposite(elementTypeProvider.getReference_ReferenceAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getReference_ReferenceKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,53,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getReference_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_60); 

            			doneLeaf(otherlv_2);
            		

            			markLeaf(elementTypeProvider.getReference_EntityAttributeKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,54,FOLLOW_51); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:1906:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:1907:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:1907:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:1908:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getReference_AttributeAttributeCrossReference_4_0ElementType());
            				
            pushFollow(FOLLOW_61);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }

            // PsiInternalEJSL.g:1923:3: (otherlv_5= ',' ( ( ruleQualifiedName ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==21) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // PsiInternalEJSL.g:1924:4: otherlv_5= ',' ( ( ruleQualifiedName ) )
            	    {

            	    				markLeaf(elementTypeProvider.getReference_CommaKeyword_5_0ElementType());
            	    			
            	    otherlv_5=(Token)match(input,21,FOLLOW_51); 

            	    				doneLeaf(otherlv_5);
            	    			
            	    // PsiInternalEJSL.g:1931:4: ( ( ruleQualifiedName ) )
            	    // PsiInternalEJSL.g:1932:5: ( ruleQualifiedName )
            	    {
            	    // PsiInternalEJSL.g:1932:5: ( ruleQualifiedName )
            	    // PsiInternalEJSL.g:1933:6: ruleQualifiedName
            	    {

            	    						if (!current) {
            	    							associateWithSemanticElement();
            	    							current = true;
            	    						}
            	    					

            	    						markComposite(elementTypeProvider.getReference_AttributeAttributeCrossReference_5_1_0ElementType());
            	    					
            	    pushFollow(FOLLOW_61);
            	    ruleQualifiedName();

            	    state._fsp--;


            	    						doneComposite();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getReference_EntityKeyword_6ElementType());
            		
            otherlv_7=(Token)match(input,55,FOLLOW_51); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:1956:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:1957:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:1957:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:1958:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getReference_EntityEntityCrossReference_7_0ElementType());
            				
            pushFollow(FOLLOW_62);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }


            			markLeaf(elementTypeProvider.getReference_EntityReferenceKeyword_8ElementType());
            		
            otherlv_9=(Token)match(input,56,FOLLOW_59); 

            			doneLeaf(otherlv_9);
            		
            // PsiInternalEJSL.g:1980:3: ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==RULE_ID||LA49_0==136) ) {
                alt49=1;
            }
            else if ( (LA49_0==52) ) {
                alt49=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // PsiInternalEJSL.g:1981:4: ( ( ruleQualifiedName ) )
                    {
                    // PsiInternalEJSL.g:1981:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:1982:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:1982:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:1983:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getReference_AttributereferecedAttributeCrossReference_9_0_0ElementType());
                    					
                    pushFollow(FOLLOW_63);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:1999:4: ( (lv_id_11_0= 'ID' ) )
                    {
                    // PsiInternalEJSL.g:1999:4: ( (lv_id_11_0= 'ID' ) )
                    // PsiInternalEJSL.g:2000:5: (lv_id_11_0= 'ID' )
                    {
                    // PsiInternalEJSL.g:2000:5: (lv_id_11_0= 'ID' )
                    // PsiInternalEJSL.g:2001:6: lv_id_11_0= 'ID'
                    {

                    						markLeaf(elementTypeProvider.getReference_IdIDKeyword_9_1_0ElementType());
                    					
                    lv_id_11_0=(Token)match(input,52,FOLLOW_63); 

                    						doneLeaf(lv_id_11_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:2017:3: ( ( ruleQualifiedName ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==RULE_ID||LA50_0==136) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // PsiInternalEJSL.g:2018:4: ( ruleQualifiedName )
            	    {
            	    // PsiInternalEJSL.g:2018:4: ( ruleQualifiedName )
            	    // PsiInternalEJSL.g:2019:5: ruleQualifiedName
            	    {

            	    					if (!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    					markComposite(elementTypeProvider.getReference_AttributereferecedAttributeCrossReference_10_0ElementType());
            	    				
            	    pushFollow(FOLLOW_63);
            	    ruleQualifiedName();

            	    state._fsp--;


            	    					doneComposite();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getReference_LowerKeyword_11ElementType());
            		
            otherlv_13=(Token)match(input,57,FOLLOW_64); 

            			doneLeaf(otherlv_13);
            		
            // PsiInternalEJSL.g:2041:3: ( (lv_lower_14_0= ruleNUMBER ) )
            // PsiInternalEJSL.g:2042:4: (lv_lower_14_0= ruleNUMBER )
            {
            // PsiInternalEJSL.g:2042:4: (lv_lower_14_0= ruleNUMBER )
            // PsiInternalEJSL.g:2043:5: lv_lower_14_0= ruleNUMBER
            {

            					markComposite(elementTypeProvider.getReference_LowerNUMBERParserRuleCall_12_0ElementType());
            				
            pushFollow(FOLLOW_65);
            lv_lower_14_0=ruleNUMBER();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getReference_UpperKeyword_13ElementType());
            		
            otherlv_15=(Token)match(input,58,FOLLOW_64); 

            			doneLeaf(otherlv_15);
            		
            // PsiInternalEJSL.g:2063:3: ( (lv_upper_16_0= ruleNUMBER ) )
            // PsiInternalEJSL.g:2064:4: (lv_upper_16_0= ruleNUMBER )
            {
            // PsiInternalEJSL.g:2064:4: (lv_upper_16_0= ruleNUMBER )
            // PsiInternalEJSL.g:2065:5: lv_upper_16_0= ruleNUMBER
            {

            					markComposite(elementTypeProvider.getReference_UpperNUMBERParserRuleCall_14_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_upper_16_0=ruleNUMBER();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getReference_RightCurlyBracketKeyword_15ElementType());
            		
            otherlv_17=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_17);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReference"


    // $ANTLR start "entryRulePage"
    // PsiInternalEJSL.g:2089:1: entryRulePage returns [Boolean current=false] : iv_rulePage= rulePage EOF ;
    public final Boolean entryRulePage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePage = null;


        try {
            // PsiInternalEJSL.g:2089:46: (iv_rulePage= rulePage EOF )
            // PsiInternalEJSL.g:2090:2: iv_rulePage= rulePage EOF
            {
             markComposite(elementTypeProvider.getPageElementType()); 
            pushFollow(FOLLOW_1);
            iv_rulePage=rulePage();

            state._fsp--;

             current =iv_rulePage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePage"


    // $ANTLR start "rulePage"
    // PsiInternalEJSL.g:2096:1: rulePage returns [Boolean current=false] : (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage | this_CustomPage_2= ruleCustomPage ) ;
    public final Boolean rulePage() throws RecognitionException {
        Boolean current = false;

        Boolean this_StaticPage_0 = null;

        Boolean this_DynamicPage_1 = null;

        Boolean this_CustomPage_2 = null;


        try {
            // PsiInternalEJSL.g:2097:1: ( (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage | this_CustomPage_2= ruleCustomPage ) )
            // PsiInternalEJSL.g:2098:2: (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage | this_CustomPage_2= ruleCustomPage )
            {
            // PsiInternalEJSL.g:2098:2: (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage | this_CustomPage_2= ruleCustomPage )
            int alt51=3;
            switch ( input.LA(1) ) {
            case 59:
                {
                alt51=1;
                }
                break;
            case 66:
            case 70:
                {
                alt51=2;
                }
                break;
            case 73:
                {
                alt51=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // PsiInternalEJSL.g:2099:3: this_StaticPage_0= ruleStaticPage
                    {

                    			markComposite(elementTypeProvider.getPage_StaticPageParserRuleCall_0ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_StaticPage_0=ruleStaticPage();

                    state._fsp--;


                    			current = this_StaticPage_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:2108:3: this_DynamicPage_1= ruleDynamicPage
                    {

                    			markComposite(elementTypeProvider.getPage_DynamicPageParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_DynamicPage_1=ruleDynamicPage();

                    state._fsp--;


                    			current = this_DynamicPage_1;
                    			doneComposite();
                    		

                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:2117:3: this_CustomPage_2= ruleCustomPage
                    {

                    			markComposite(elementTypeProvider.getPage_CustomPageParserRuleCall_2ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_CustomPage_2=ruleCustomPage();

                    state._fsp--;


                    			current = this_CustomPage_2;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePage"


    // $ANTLR start "entryRuleStaticPage"
    // PsiInternalEJSL.g:2129:1: entryRuleStaticPage returns [Boolean current=false] : iv_ruleStaticPage= ruleStaticPage EOF ;
    public final Boolean entryRuleStaticPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleStaticPage = null;


        try {
            // PsiInternalEJSL.g:2129:52: (iv_ruleStaticPage= ruleStaticPage EOF )
            // PsiInternalEJSL.g:2130:2: iv_ruleStaticPage= ruleStaticPage EOF
            {
             markComposite(elementTypeProvider.getStaticPageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleStaticPage=ruleStaticPage();

            state._fsp--;

             current =iv_ruleStaticPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStaticPage"


    // $ANTLR start "ruleStaticPage"
    // PsiInternalEJSL.g:2136:1: ruleStaticPage returns [Boolean current=false] : ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}' ) ;
    public final Boolean ruleStaticPage() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        Token lv_HTMLBody_26_0=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_localparameters_14_0 = null;

        Boolean lv_pageactions_18_0 = null;

        Boolean lv_links_22_0 = null;


        try {
            // PsiInternalEJSL.g:2137:1: ( ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}' ) )
            // PsiInternalEJSL.g:2138:2: ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}' )
            {
            // PsiInternalEJSL.g:2138:2: ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}' )
            // PsiInternalEJSL.g:2139:3: () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}'
            {
            // PsiInternalEJSL.g:2139:3: ()
            // PsiInternalEJSL.g:2140:4: 
            {

            				precedeComposite(elementTypeProvider.getStaticPage_StaticPageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getStaticPage_StaticPageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,59,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:2153:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:2154:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:2154:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:2155:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getStaticPage_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getStaticPage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_66); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:2175:3: (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==60) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // PsiInternalEJSL.g:2176:4: otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_ParameterGroupsKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,60,FOLLOW_33); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:2183:4: ( (otherlv_5= RULE_ID ) )
                    // PsiInternalEJSL.g:2184:5: (otherlv_5= RULE_ID )
                    {
                    // PsiInternalEJSL.g:2184:5: (otherlv_5= RULE_ID )
                    // PsiInternalEJSL.g:2185:6: otherlv_5= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getStaticPage_ParametergroupsParameterGroupCrossReference_4_1_0ElementType());
                    					
                    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_67); 

                    						doneLeaf(otherlv_5);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2200:4: (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==21) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2201:5: otherlv_6= ',' ( (otherlv_7= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getStaticPage_CommaKeyword_4_2_0ElementType());
                    	    				
                    	    otherlv_6=(Token)match(input,21,FOLLOW_33); 

                    	    					doneLeaf(otherlv_6);
                    	    				
                    	    // PsiInternalEJSL.g:2208:5: ( (otherlv_7= RULE_ID ) )
                    	    // PsiInternalEJSL.g:2209:6: (otherlv_7= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:2209:6: (otherlv_7= RULE_ID )
                    	    // PsiInternalEJSL.g:2210:7: otherlv_7= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getStaticPage_ParametergroupsParameterGroupCrossReference_4_2_1_0ElementType());
                    	    						
                    	    otherlv_7=(Token)match(input,RULE_ID,FOLLOW_67); 

                    	    							doneLeaf(otherlv_7);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop52;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:2227:3: (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==61) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // PsiInternalEJSL.g:2228:4: otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_GlobalparametersKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,61,FOLLOW_33); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:2235:4: ( (otherlv_9= RULE_ID ) )
                    // PsiInternalEJSL.g:2236:5: (otherlv_9= RULE_ID )
                    {
                    // PsiInternalEJSL.g:2236:5: (otherlv_9= RULE_ID )
                    // PsiInternalEJSL.g:2237:6: otherlv_9= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getStaticPage_GlobalparametersParameterCrossReference_5_1_0ElementType());
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_68); 

                    						doneLeaf(otherlv_9);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2252:4: (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==21) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2253:5: otherlv_10= ',' ( (otherlv_11= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getStaticPage_CommaKeyword_5_2_0ElementType());
                    	    				
                    	    otherlv_10=(Token)match(input,21,FOLLOW_33); 

                    	    					doneLeaf(otherlv_10);
                    	    				
                    	    // PsiInternalEJSL.g:2260:5: ( (otherlv_11= RULE_ID ) )
                    	    // PsiInternalEJSL.g:2261:6: (otherlv_11= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:2261:6: (otherlv_11= RULE_ID )
                    	    // PsiInternalEJSL.g:2262:7: otherlv_11= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getStaticPage_GlobalparametersParameterCrossReference_5_2_1_0ElementType());
                    	    						
                    	    otherlv_11=(Token)match(input,RULE_ID,FOLLOW_68); 

                    	    							doneLeaf(otherlv_11);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop54;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:2279:3: (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==62) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // PsiInternalEJSL.g:2280:4: otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}'
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_LocalparametersKeyword_6_0ElementType());
                    			
                    otherlv_12=(Token)match(input,62,FOLLOW_4); 

                    				doneLeaf(otherlv_12);
                    			

                    				markLeaf(elementTypeProvider.getStaticPage_LeftCurlyBracketKeyword_6_1ElementType());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_12); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:2294:4: ( (lv_localparameters_14_0= ruleParameter ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==34) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2295:5: (lv_localparameters_14_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:2295:5: (lv_localparameters_14_0= ruleParameter )
                    	    // PsiInternalEJSL.g:2296:6: lv_localparameters_14_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getStaticPage_LocalparametersParameterParserRuleCall_6_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_14_0=ruleParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_6_3ElementType());
                    			
                    otherlv_15=(Token)match(input,18,FOLLOW_69); 

                    				doneLeaf(otherlv_15);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:2317:3: (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==63) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // PsiInternalEJSL.g:2318:4: otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}'
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_PageactionsKeyword_7_0ElementType());
                    			
                    otherlv_16=(Token)match(input,63,FOLLOW_4); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getStaticPage_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_70); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:2332:4: ( (lv_pageactions_18_0= rulePageAction ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==42) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2333:5: (lv_pageactions_18_0= rulePageAction )
                    	    {
                    	    // PsiInternalEJSL.g:2333:5: (lv_pageactions_18_0= rulePageAction )
                    	    // PsiInternalEJSL.g:2334:6: lv_pageactions_18_0= rulePageAction
                    	    {

                    	    						markComposite(elementTypeProvider.getStaticPage_PageactionsPageActionParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_70);
                    	    lv_pageactions_18_0=rulePageAction();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_19=(Token)match(input,18,FOLLOW_71); 

                    				doneLeaf(otherlv_19);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:2355:3: (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==64) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // PsiInternalEJSL.g:2356:4: otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}'
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_LinksKeyword_8_0ElementType());
                    			
                    otherlv_20=(Token)match(input,64,FOLLOW_4); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getStaticPage_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_72); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:2370:4: ( (lv_links_22_0= ruleLink ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==77||(LA60_0>=83 && LA60_0<=84)) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2371:5: (lv_links_22_0= ruleLink )
                    	    {
                    	    // PsiInternalEJSL.g:2371:5: (lv_links_22_0= ruleLink )
                    	    // PsiInternalEJSL.g:2372:6: lv_links_22_0= ruleLink
                    	    {

                    	    						markComposite(elementTypeProvider.getStaticPage_LinksLinkParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_72);
                    	    lv_links_22_0=ruleLink();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_23=(Token)match(input,18,FOLLOW_73); 

                    				doneLeaf(otherlv_23);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getStaticPage_HTMLBodyKeyword_9ElementType());
            		
            otherlv_24=(Token)match(input,65,FOLLOW_4); 

            			doneLeaf(otherlv_24);
            		

            			markLeaf(elementTypeProvider.getStaticPage_LeftCurlyBracketKeyword_10ElementType());
            		
            otherlv_25=(Token)match(input,16,FOLLOW_3); 

            			doneLeaf(otherlv_25);
            		
            // PsiInternalEJSL.g:2407:3: ( (lv_HTMLBody_26_0= RULE_STRING ) )
            // PsiInternalEJSL.g:2408:4: (lv_HTMLBody_26_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:2408:4: (lv_HTMLBody_26_0= RULE_STRING )
            // PsiInternalEJSL.g:2409:5: lv_HTMLBody_26_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getStaticPage_HTMLBodySTRINGTerminalRuleCall_11_0ElementType());
            				
            lv_HTMLBody_26_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_HTMLBody_26_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_27=(Token)match(input,18,FOLLOW_7); 

            			doneLeaf(otherlv_27);
            		

            			markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_13ElementType());
            		
            otherlv_28=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_28);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStaticPage"


    // $ANTLR start "entryRuleDynamicPage"
    // PsiInternalEJSL.g:2442:1: entryRuleDynamicPage returns [Boolean current=false] : iv_ruleDynamicPage= ruleDynamicPage EOF ;
    public final Boolean entryRuleDynamicPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDynamicPage = null;


        try {
            // PsiInternalEJSL.g:2442:53: (iv_ruleDynamicPage= ruleDynamicPage EOF )
            // PsiInternalEJSL.g:2443:2: iv_ruleDynamicPage= ruleDynamicPage EOF
            {
             markComposite(elementTypeProvider.getDynamicPageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleDynamicPage=ruleDynamicPage();

            state._fsp--;

             current =iv_ruleDynamicPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDynamicPage"


    // $ANTLR start "ruleDynamicPage"
    // PsiInternalEJSL.g:2449:1: ruleDynamicPage returns [Boolean current=false] : (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage ) ;
    public final Boolean ruleDynamicPage() throws RecognitionException {
        Boolean current = false;

        Boolean this_IndexPage_0 = null;

        Boolean this_DetailsPage_1 = null;


        try {
            // PsiInternalEJSL.g:2450:1: ( (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage ) )
            // PsiInternalEJSL.g:2451:2: (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage )
            {
            // PsiInternalEJSL.g:2451:2: (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==66) ) {
                alt62=1;
            }
            else if ( (LA62_0==70) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // PsiInternalEJSL.g:2452:3: this_IndexPage_0= ruleIndexPage
                    {

                    			markComposite(elementTypeProvider.getDynamicPage_IndexPageParserRuleCall_0ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_IndexPage_0=ruleIndexPage();

                    state._fsp--;


                    			current = this_IndexPage_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:2461:3: this_DetailsPage_1= ruleDetailsPage
                    {

                    			markComposite(elementTypeProvider.getDynamicPage_DetailsPageParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_DetailsPage_1=ruleDetailsPage();

                    state._fsp--;


                    			current = this_DetailsPage_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDynamicPage"


    // $ANTLR start "entryRuleIndexPage"
    // PsiInternalEJSL.g:2473:1: entryRuleIndexPage returns [Boolean current=false] : iv_ruleIndexPage= ruleIndexPage EOF ;
    public final Boolean entryRuleIndexPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleIndexPage = null;


        try {
            // PsiInternalEJSL.g:2473:51: (iv_ruleIndexPage= ruleIndexPage EOF )
            // PsiInternalEJSL.g:2474:2: iv_ruleIndexPage= ruleIndexPage EOF
            {
             markComposite(elementTypeProvider.getIndexPageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleIndexPage=ruleIndexPage();

            state._fsp--;

             current =iv_ruleIndexPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIndexPage"


    // $ANTLR start "ruleIndexPage"
    // PsiInternalEJSL.g:2480:1: ruleIndexPage returns [Boolean current=false] : ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}' ) ;
    public final Boolean ruleIndexPage() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_30=null;
        Token otherlv_32=null;
        Token otherlv_33=null;
        Token otherlv_35=null;
        Token otherlv_36=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_localparameters_18_0 = null;

        Boolean lv_pageactions_22_0 = null;

        Boolean lv_links_34_0 = null;


        try {
            // PsiInternalEJSL.g:2481:1: ( ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}' ) )
            // PsiInternalEJSL.g:2482:2: ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}' )
            {
            // PsiInternalEJSL.g:2482:2: ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}' )
            // PsiInternalEJSL.g:2483:3: () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}'
            {
            // PsiInternalEJSL.g:2483:3: ()
            // PsiInternalEJSL.g:2484:4: 
            {

            				precedeComposite(elementTypeProvider.getIndexPage_IndexPageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getIndexPage_IndexPageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,66,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:2497:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:2498:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:2498:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:2499:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getIndexPage_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getIndexPage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_74); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:2519:3: (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==67) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // PsiInternalEJSL.g:2520:4: otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_EntitiesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,67,FOLLOW_51); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:2527:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2528:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2528:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2529:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getIndexPage_EntitiesEntityCrossReference_4_1_0ElementType());
                    					
                    pushFollow(FOLLOW_75);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2544:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==21) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2545:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_4_2_0ElementType());
                    	    				
                    	    otherlv_6=(Token)match(input,21,FOLLOW_51); 

                    	    					doneLeaf(otherlv_6);
                    	    				
                    	    // PsiInternalEJSL.g:2552:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2553:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2553:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2554:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getIndexPage_EntitiesEntityCrossReference_4_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_75);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop63;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:2571:3: (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==60) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // PsiInternalEJSL.g:2572:4: otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_ParameterGroupsKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,60,FOLLOW_33); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:2579:4: ( (otherlv_9= RULE_ID ) )
                    // PsiInternalEJSL.g:2580:5: (otherlv_9= RULE_ID )
                    {
                    // PsiInternalEJSL.g:2580:5: (otherlv_9= RULE_ID )
                    // PsiInternalEJSL.g:2581:6: otherlv_9= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getIndexPage_ParametergroupsParameterGroupCrossReference_5_1_0ElementType());
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_76); 

                    						doneLeaf(otherlv_9);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2596:4: (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==21) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2597:5: otherlv_10= ',' ( (otherlv_11= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_5_2_0ElementType());
                    	    				
                    	    otherlv_10=(Token)match(input,21,FOLLOW_33); 

                    	    					doneLeaf(otherlv_10);
                    	    				
                    	    // PsiInternalEJSL.g:2604:5: ( (otherlv_11= RULE_ID ) )
                    	    // PsiInternalEJSL.g:2605:6: (otherlv_11= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:2605:6: (otherlv_11= RULE_ID )
                    	    // PsiInternalEJSL.g:2606:7: otherlv_11= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getIndexPage_ParametergroupsParameterGroupCrossReference_5_2_1_0ElementType());
                    	    						
                    	    otherlv_11=(Token)match(input,RULE_ID,FOLLOW_76); 

                    	    							doneLeaf(otherlv_11);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop65;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:2623:3: (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==61) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // PsiInternalEJSL.g:2624:4: otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_GlobalparametersKeyword_6_0ElementType());
                    			
                    otherlv_12=(Token)match(input,61,FOLLOW_33); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:2631:4: ( (otherlv_13= RULE_ID ) )
                    // PsiInternalEJSL.g:2632:5: (otherlv_13= RULE_ID )
                    {
                    // PsiInternalEJSL.g:2632:5: (otherlv_13= RULE_ID )
                    // PsiInternalEJSL.g:2633:6: otherlv_13= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getIndexPage_GlobalparametersParameterCrossReference_6_1_0ElementType());
                    					
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_77); 

                    						doneLeaf(otherlv_13);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2648:4: (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==21) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2649:5: otherlv_14= ',' ( (otherlv_15= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_6_2_0ElementType());
                    	    				
                    	    otherlv_14=(Token)match(input,21,FOLLOW_33); 

                    	    					doneLeaf(otherlv_14);
                    	    				
                    	    // PsiInternalEJSL.g:2656:5: ( (otherlv_15= RULE_ID ) )
                    	    // PsiInternalEJSL.g:2657:6: (otherlv_15= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:2657:6: (otherlv_15= RULE_ID )
                    	    // PsiInternalEJSL.g:2658:7: otherlv_15= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getIndexPage_GlobalparametersParameterCrossReference_6_2_1_0ElementType());
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_77); 

                    	    							doneLeaf(otherlv_15);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop67;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:2675:3: (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==62) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // PsiInternalEJSL.g:2676:4: otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}'
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_LocalparametersKeyword_7_0ElementType());
                    			
                    otherlv_16=(Token)match(input,62,FOLLOW_4); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getIndexPage_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_12); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:2690:4: ( (lv_localparameters_18_0= ruleParameter ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==34) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2691:5: (lv_localparameters_18_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:2691:5: (lv_localparameters_18_0= ruleParameter )
                    	    // PsiInternalEJSL.g:2692:6: lv_localparameters_18_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getIndexPage_LocalparametersParameterParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_18_0=ruleParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop69;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getIndexPage_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_19=(Token)match(input,18,FOLLOW_78); 

                    				doneLeaf(otherlv_19);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:2713:3: (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==63) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // PsiInternalEJSL.g:2714:4: otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}'
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_PageactionsKeyword_8_0ElementType());
                    			
                    otherlv_20=(Token)match(input,63,FOLLOW_4); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getIndexPage_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_70); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:2728:4: ( (lv_pageactions_22_0= rulePageAction ) )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==42) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2729:5: (lv_pageactions_22_0= rulePageAction )
                    	    {
                    	    // PsiInternalEJSL.g:2729:5: (lv_pageactions_22_0= rulePageAction )
                    	    // PsiInternalEJSL.g:2730:6: lv_pageactions_22_0= rulePageAction
                    	    {

                    	    						markComposite(elementTypeProvider.getIndexPage_PageactionsPageActionParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_70);
                    	    lv_pageactions_22_0=rulePageAction();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop71;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getIndexPage_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_23=(Token)match(input,18,FOLLOW_79); 

                    				doneLeaf(otherlv_23);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:2751:3: (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==68) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // PsiInternalEJSL.g:2752:4: otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_TableColumnsKeyword_9_0ElementType());
                    			
                    otherlv_24=(Token)match(input,68,FOLLOW_51); 

                    				doneLeaf(otherlv_24);
                    			
                    // PsiInternalEJSL.g:2759:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2760:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2760:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2761:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getIndexPage_TablecolumnsAttributeCrossReference_9_1_0ElementType());
                    					
                    pushFollow(FOLLOW_80);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2776:4: (otherlv_26= ',' ( ( ruleQualifiedName ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==21) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2777:5: otherlv_26= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_9_2_0ElementType());
                    	    				
                    	    otherlv_26=(Token)match(input,21,FOLLOW_51); 

                    	    					doneLeaf(otherlv_26);
                    	    				
                    	    // PsiInternalEJSL.g:2784:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2785:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2785:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2786:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getIndexPage_TablecolumnsAttributeCrossReference_9_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_80);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:2803:3: (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==69) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // PsiInternalEJSL.g:2804:4: otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_FiltersKeyword_10_0ElementType());
                    			
                    otherlv_28=(Token)match(input,69,FOLLOW_51); 

                    				doneLeaf(otherlv_28);
                    			
                    // PsiInternalEJSL.g:2811:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2812:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2812:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2813:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getIndexPage_FiltersAttributeCrossReference_10_1_0ElementType());
                    					
                    pushFollow(FOLLOW_81);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2828:4: (otherlv_30= ',' ( ( ruleQualifiedName ) ) )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==21) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2829:5: otherlv_30= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_10_2_0ElementType());
                    	    				
                    	    otherlv_30=(Token)match(input,21,FOLLOW_51); 

                    	    					doneLeaf(otherlv_30);
                    	    				
                    	    // PsiInternalEJSL.g:2836:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2837:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2837:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2838:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getIndexPage_FiltersAttributeCrossReference_10_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_81);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop75;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:2855:3: (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==64) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // PsiInternalEJSL.g:2856:4: otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}'
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_LinksKeyword_11_0ElementType());
                    			
                    otherlv_32=(Token)match(input,64,FOLLOW_4); 

                    				doneLeaf(otherlv_32);
                    			

                    				markLeaf(elementTypeProvider.getIndexPage_LeftCurlyBracketKeyword_11_1ElementType());
                    			
                    otherlv_33=(Token)match(input,16,FOLLOW_72); 

                    				doneLeaf(otherlv_33);
                    			
                    // PsiInternalEJSL.g:2870:4: ( (lv_links_34_0= ruleLink ) )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==77||(LA77_0>=83 && LA77_0<=84)) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2871:5: (lv_links_34_0= ruleLink )
                    	    {
                    	    // PsiInternalEJSL.g:2871:5: (lv_links_34_0= ruleLink )
                    	    // PsiInternalEJSL.g:2872:6: lv_links_34_0= ruleLink
                    	    {

                    	    						markComposite(elementTypeProvider.getIndexPage_LinksLinkParserRuleCall_11_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_72);
                    	    lv_links_34_0=ruleLink();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop77;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getIndexPage_RightCurlyBracketKeyword_11_3ElementType());
                    			
                    otherlv_35=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_35);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getIndexPage_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_36=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_36);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIndexPage"


    // $ANTLR start "entryRuleDetailsPage"
    // PsiInternalEJSL.g:2904:1: entryRuleDetailsPage returns [Boolean current=false] : iv_ruleDetailsPage= ruleDetailsPage EOF ;
    public final Boolean entryRuleDetailsPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDetailsPage = null;


        try {
            // PsiInternalEJSL.g:2904:53: (iv_ruleDetailsPage= ruleDetailsPage EOF )
            // PsiInternalEJSL.g:2905:2: iv_ruleDetailsPage= ruleDetailsPage EOF
            {
             markComposite(elementTypeProvider.getDetailsPageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleDetailsPage=ruleDetailsPage();

            state._fsp--;

             current =iv_ruleDetailsPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDetailsPage"


    // $ANTLR start "ruleDetailsPage"
    // PsiInternalEJSL.g:2911:1: ruleDetailsPage returns [Boolean current=false] : ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}' ) ;
    public final Boolean ruleDetailsPage() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        Token otherlv_30=null;
        Token otherlv_32=null;
        Token otherlv_34=null;
        Token otherlv_36=null;
        Token otherlv_37=null;
        Token otherlv_39=null;
        Token otherlv_40=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_editfields_10_0 = null;

        Boolean lv_localparameters_22_0 = null;

        Boolean lv_pageactions_26_0 = null;

        Boolean lv_links_38_0 = null;


        try {
            // PsiInternalEJSL.g:2912:1: ( ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}' ) )
            // PsiInternalEJSL.g:2913:2: ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}' )
            {
            // PsiInternalEJSL.g:2913:2: ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}' )
            // PsiInternalEJSL.g:2914:3: () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}'
            {
            // PsiInternalEJSL.g:2914:3: ()
            // PsiInternalEJSL.g:2915:4: 
            {

            				precedeComposite(elementTypeProvider.getDetailsPage_DetailsPageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getDetailsPage_DetailsPageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,70,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:2928:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:2929:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:2929:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:2930:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getDetailsPage_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getDetailsPage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:2950:3: (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==67) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // PsiInternalEJSL.g:2951:4: otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_EntitiesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,67,FOLLOW_51); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:2958:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2959:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2959:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2960:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getDetailsPage_EntitiesEntityCrossReference_4_1_0ElementType());
                    					
                    pushFollow(FOLLOW_83);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2975:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop79:
                    do {
                        int alt79=2;
                        int LA79_0 = input.LA(1);

                        if ( (LA79_0==21) ) {
                            alt79=1;
                        }


                        switch (alt79) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2976:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_4_2_0ElementType());
                    	    				
                    	    otherlv_6=(Token)match(input,21,FOLLOW_51); 

                    	    					doneLeaf(otherlv_6);
                    	    				
                    	    // PsiInternalEJSL.g:2983:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2984:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2984:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2985:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getDetailsPage_EntitiesEntityCrossReference_4_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_83);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop79;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:3002:3: (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==71) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // PsiInternalEJSL.g:3003:4: otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_EditFieldsKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,71,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_84); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:3017:4: ( (lv_editfields_10_0= ruleDetailPageField ) )*
                    loop81:
                    do {
                        int alt81=2;
                        int LA81_0 = input.LA(1);

                        if ( (LA81_0==RULE_ID||LA81_0==136) ) {
                            alt81=1;
                        }


                        switch (alt81) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3018:5: (lv_editfields_10_0= ruleDetailPageField )
                    	    {
                    	    // PsiInternalEJSL.g:3018:5: (lv_editfields_10_0= ruleDetailPageField )
                    	    // PsiInternalEJSL.g:3019:6: lv_editfields_10_0= ruleDetailPageField
                    	    {

                    	    						markComposite(elementTypeProvider.getDetailsPage_EditfieldsDetailPageFieldParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_84);
                    	    lv_editfields_10_0=ruleDetailPageField();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop81;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_85); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:3040:3: (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==60) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // PsiInternalEJSL.g:3041:4: otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_ParameterGroupsKeyword_6_0ElementType());
                    			
                    otherlv_12=(Token)match(input,60,FOLLOW_33); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:3048:4: ( (otherlv_13= RULE_ID ) )
                    // PsiInternalEJSL.g:3049:5: (otherlv_13= RULE_ID )
                    {
                    // PsiInternalEJSL.g:3049:5: (otherlv_13= RULE_ID )
                    // PsiInternalEJSL.g:3050:6: otherlv_13= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getDetailsPage_ParametergroupsParameterGroupCrossReference_6_1_0ElementType());
                    					
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_76); 

                    						doneLeaf(otherlv_13);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:3065:4: (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    loop83:
                    do {
                        int alt83=2;
                        int LA83_0 = input.LA(1);

                        if ( (LA83_0==21) ) {
                            alt83=1;
                        }


                        switch (alt83) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3066:5: otherlv_14= ',' ( (otherlv_15= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_6_2_0ElementType());
                    	    				
                    	    otherlv_14=(Token)match(input,21,FOLLOW_33); 

                    	    					doneLeaf(otherlv_14);
                    	    				
                    	    // PsiInternalEJSL.g:3073:5: ( (otherlv_15= RULE_ID ) )
                    	    // PsiInternalEJSL.g:3074:6: (otherlv_15= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:3074:6: (otherlv_15= RULE_ID )
                    	    // PsiInternalEJSL.g:3075:7: otherlv_15= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getDetailsPage_ParametergroupsParameterGroupCrossReference_6_2_1_0ElementType());
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_76); 

                    	    							doneLeaf(otherlv_15);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop83;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:3092:3: (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==61) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // PsiInternalEJSL.g:3093:4: otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_GlobalparametersKeyword_7_0ElementType());
                    			
                    otherlv_16=(Token)match(input,61,FOLLOW_33); 

                    				doneLeaf(otherlv_16);
                    			
                    // PsiInternalEJSL.g:3100:4: ( (otherlv_17= RULE_ID ) )
                    // PsiInternalEJSL.g:3101:5: (otherlv_17= RULE_ID )
                    {
                    // PsiInternalEJSL.g:3101:5: (otherlv_17= RULE_ID )
                    // PsiInternalEJSL.g:3102:6: otherlv_17= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getDetailsPage_GlobalparametersParameterCrossReference_7_1_0ElementType());
                    					
                    otherlv_17=(Token)match(input,RULE_ID,FOLLOW_77); 

                    						doneLeaf(otherlv_17);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:3117:4: (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==21) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3118:5: otherlv_18= ',' ( (otherlv_19= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_7_2_0ElementType());
                    	    				
                    	    otherlv_18=(Token)match(input,21,FOLLOW_33); 

                    	    					doneLeaf(otherlv_18);
                    	    				
                    	    // PsiInternalEJSL.g:3125:5: ( (otherlv_19= RULE_ID ) )
                    	    // PsiInternalEJSL.g:3126:6: (otherlv_19= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:3126:6: (otherlv_19= RULE_ID )
                    	    // PsiInternalEJSL.g:3127:7: otherlv_19= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getDetailsPage_GlobalparametersParameterCrossReference_7_2_1_0ElementType());
                    	    						
                    	    otherlv_19=(Token)match(input,RULE_ID,FOLLOW_77); 

                    	    							doneLeaf(otherlv_19);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop85;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:3144:3: (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==62) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // PsiInternalEJSL.g:3145:4: otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}'
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_LocalparametersKeyword_8_0ElementType());
                    			
                    otherlv_20=(Token)match(input,62,FOLLOW_4); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_12); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:3159:4: ( (lv_localparameters_22_0= ruleParameter ) )*
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( (LA87_0==34) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3160:5: (lv_localparameters_22_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:3160:5: (lv_localparameters_22_0= ruleParameter )
                    	    // PsiInternalEJSL.g:3161:6: lv_localparameters_22_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getDetailsPage_LocalparametersParameterParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_22_0=ruleParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop87;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_23=(Token)match(input,18,FOLLOW_78); 

                    				doneLeaf(otherlv_23);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:3182:3: (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==63) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // PsiInternalEJSL.g:3183:4: otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}'
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_PageactionsKeyword_9_0ElementType());
                    			
                    otherlv_24=(Token)match(input,63,FOLLOW_4); 

                    				doneLeaf(otherlv_24);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_LeftCurlyBracketKeyword_9_1ElementType());
                    			
                    otherlv_25=(Token)match(input,16,FOLLOW_70); 

                    				doneLeaf(otherlv_25);
                    			
                    // PsiInternalEJSL.g:3197:4: ( (lv_pageactions_26_0= rulePageAction ) )*
                    loop89:
                    do {
                        int alt89=2;
                        int LA89_0 = input.LA(1);

                        if ( (LA89_0==42) ) {
                            alt89=1;
                        }


                        switch (alt89) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3198:5: (lv_pageactions_26_0= rulePageAction )
                    	    {
                    	    // PsiInternalEJSL.g:3198:5: (lv_pageactions_26_0= rulePageAction )
                    	    // PsiInternalEJSL.g:3199:6: lv_pageactions_26_0= rulePageAction
                    	    {

                    	    						markComposite(elementTypeProvider.getDetailsPage_PageactionsPageActionParserRuleCall_9_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_70);
                    	    lv_pageactions_26_0=rulePageAction();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop89;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_9_3ElementType());
                    			
                    otherlv_27=(Token)match(input,18,FOLLOW_79); 

                    				doneLeaf(otherlv_27);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:3220:3: (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==68) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // PsiInternalEJSL.g:3221:4: otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_TableColumnsKeyword_10_0ElementType());
                    			
                    otherlv_28=(Token)match(input,68,FOLLOW_51); 

                    				doneLeaf(otherlv_28);
                    			
                    // PsiInternalEJSL.g:3228:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:3229:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:3229:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:3230:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getDetailsPage_TablecolumnsAttributeCrossReference_10_1_0ElementType());
                    					
                    pushFollow(FOLLOW_80);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:3245:4: (otherlv_30= ',' ( ( ruleQualifiedName ) ) )*
                    loop91:
                    do {
                        int alt91=2;
                        int LA91_0 = input.LA(1);

                        if ( (LA91_0==21) ) {
                            alt91=1;
                        }


                        switch (alt91) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3246:5: otherlv_30= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_10_2_0ElementType());
                    	    				
                    	    otherlv_30=(Token)match(input,21,FOLLOW_51); 

                    	    					doneLeaf(otherlv_30);
                    	    				
                    	    // PsiInternalEJSL.g:3253:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:3254:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:3254:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:3255:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getDetailsPage_TablecolumnsAttributeCrossReference_10_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_80);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop91;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:3272:3: (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==69) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // PsiInternalEJSL.g:3273:4: otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_FiltersKeyword_11_0ElementType());
                    			
                    otherlv_32=(Token)match(input,69,FOLLOW_51); 

                    				doneLeaf(otherlv_32);
                    			
                    // PsiInternalEJSL.g:3280:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:3281:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:3281:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:3282:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getDetailsPage_FiltersAttributeCrossReference_11_1_0ElementType());
                    					
                    pushFollow(FOLLOW_81);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:3297:4: (otherlv_34= ',' ( ( ruleQualifiedName ) ) )*
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==21) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3298:5: otherlv_34= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_11_2_0ElementType());
                    	    				
                    	    otherlv_34=(Token)match(input,21,FOLLOW_51); 

                    	    					doneLeaf(otherlv_34);
                    	    				
                    	    // PsiInternalEJSL.g:3305:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:3306:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:3306:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:3307:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getDetailsPage_FiltersAttributeCrossReference_11_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_81);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop93;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:3324:3: (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==64) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // PsiInternalEJSL.g:3325:4: otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}'
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_LinksKeyword_12_0ElementType());
                    			
                    otherlv_36=(Token)match(input,64,FOLLOW_4); 

                    				doneLeaf(otherlv_36);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_LeftCurlyBracketKeyword_12_1ElementType());
                    			
                    otherlv_37=(Token)match(input,16,FOLLOW_72); 

                    				doneLeaf(otherlv_37);
                    			
                    // PsiInternalEJSL.g:3339:4: ( (lv_links_38_0= ruleLink ) )*
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==77||(LA95_0>=83 && LA95_0<=84)) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3340:5: (lv_links_38_0= ruleLink )
                    	    {
                    	    // PsiInternalEJSL.g:3340:5: (lv_links_38_0= ruleLink )
                    	    // PsiInternalEJSL.g:3341:6: lv_links_38_0= ruleLink
                    	    {

                    	    						markComposite(elementTypeProvider.getDetailsPage_LinksLinkParserRuleCall_12_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_72);
                    	    lv_links_38_0=ruleLink();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop95;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_12_3ElementType());
                    			
                    otherlv_39=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_39);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_13ElementType());
            		
            otherlv_40=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_40);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDetailsPage"


    // $ANTLR start "entryRuleDetailPageField"
    // PsiInternalEJSL.g:3373:1: entryRuleDetailPageField returns [Boolean current=false] : iv_ruleDetailPageField= ruleDetailPageField EOF ;
    public final Boolean entryRuleDetailPageField() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDetailPageField = null;


        try {
            // PsiInternalEJSL.g:3373:57: (iv_ruleDetailPageField= ruleDetailPageField EOF )
            // PsiInternalEJSL.g:3374:2: iv_ruleDetailPageField= ruleDetailPageField EOF
            {
             markComposite(elementTypeProvider.getDetailPageFieldElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleDetailPageField=ruleDetailPageField();

            state._fsp--;

             current =iv_ruleDetailPageField; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDetailPageField"


    // $ANTLR start "ruleDetailPageField"
    // PsiInternalEJSL.g:3380:1: ruleDetailPageField returns [Boolean current=false] : ( () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )? ) ;
    public final Boolean ruleDetailPageField() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean lv_htmltype_3_0 = null;


        try {
            // PsiInternalEJSL.g:3381:1: ( ( () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )? ) )
            // PsiInternalEJSL.g:3382:2: ( () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )? )
            {
            // PsiInternalEJSL.g:3382:2: ( () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )? )
            // PsiInternalEJSL.g:3383:3: () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )?
            {
            // PsiInternalEJSL.g:3383:3: ()
            // PsiInternalEJSL.g:3384:4: 
            {

            				precedeComposite(elementTypeProvider.getDetailPageField_DetailPageFieldAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }

            // PsiInternalEJSL.g:3390:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:3391:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:3391:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:3392:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getDetailPageField_AttributeAttributeCrossReference_1_0ElementType());
            				
            pushFollow(FOLLOW_86);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }

            // PsiInternalEJSL.g:3407:3: (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==72) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // PsiInternalEJSL.g:3408:4: otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) )
                    {

                    				markLeaf(elementTypeProvider.getDetailPageField_ColonKeyword_2_0ElementType());
                    			
                    otherlv_2=(Token)match(input,72,FOLLOW_87); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalEJSL.g:3415:4: ( (lv_htmltype_3_0= ruleHTMLTypes ) )
                    // PsiInternalEJSL.g:3416:5: (lv_htmltype_3_0= ruleHTMLTypes )
                    {
                    // PsiInternalEJSL.g:3416:5: (lv_htmltype_3_0= ruleHTMLTypes )
                    // PsiInternalEJSL.g:3417:6: lv_htmltype_3_0= ruleHTMLTypes
                    {

                    						markComposite(elementTypeProvider.getDetailPageField_HtmltypeHTMLTypesParserRuleCall_2_1_0ElementType());
                    					
                    pushFollow(FOLLOW_2);
                    lv_htmltype_3_0=ruleHTMLTypes();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDetailPageField"


    // $ANTLR start "entryRuleCustomPage"
    // PsiInternalEJSL.g:3435:1: entryRuleCustomPage returns [Boolean current=false] : iv_ruleCustomPage= ruleCustomPage EOF ;
    public final Boolean entryRuleCustomPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleCustomPage = null;


        try {
            // PsiInternalEJSL.g:3435:52: (iv_ruleCustomPage= ruleCustomPage EOF )
            // PsiInternalEJSL.g:3436:2: iv_ruleCustomPage= ruleCustomPage EOF
            {
             markComposite(elementTypeProvider.getCustomPageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleCustomPage=ruleCustomPage();

            state._fsp--;

             current =iv_ruleCustomPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCustomPage"


    // $ANTLR start "ruleCustomPage"
    // PsiInternalEJSL.g:3442:1: ruleCustomPage returns [Boolean current=false] : ( () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}' ) ;
    public final Boolean ruleCustomPage() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        Token otherlv_29=null;
        Token otherlv_30=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_pageType_5_0 = null;

        Boolean lv_pageactions_12_0 = null;

        Boolean lv_localparameters_24_0 = null;

        Boolean lv_links_28_0 = null;


        try {
            // PsiInternalEJSL.g:3443:1: ( ( () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}' ) )
            // PsiInternalEJSL.g:3444:2: ( () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}' )
            {
            // PsiInternalEJSL.g:3444:2: ( () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}' )
            // PsiInternalEJSL.g:3445:3: () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}'
            {
            // PsiInternalEJSL.g:3445:3: ()
            // PsiInternalEJSL.g:3446:4: 
            {

            				precedeComposite(elementTypeProvider.getCustomPage_CustomPageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getCustomPage_CustomPageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,73,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:3459:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:3460:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:3460:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:3461:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getCustomPage_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getCustomPage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_88); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getCustomPage_PageTypeKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,74,FOLLOW_89); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:3488:3: ( (lv_pageType_5_0= rulePageKinds ) )
            // PsiInternalEJSL.g:3489:4: (lv_pageType_5_0= rulePageKinds )
            {
            // PsiInternalEJSL.g:3489:4: (lv_pageType_5_0= rulePageKinds )
            // PsiInternalEJSL.g:3490:5: lv_pageType_5_0= rulePageKinds
            {

            					markComposite(elementTypeProvider.getCustomPage_PageTypePageKindsEnumRuleCall_5_0ElementType());
            				
            pushFollow(FOLLOW_90);
            lv_pageType_5_0=rulePageKinds();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:3503:3: (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==67) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // PsiInternalEJSL.g:3504:4: otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getCustomPage_EntitiesKeyword_6_0ElementType());
                    			
                    otherlv_6=(Token)match(input,67,FOLLOW_51); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:3511:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:3512:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:3512:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:3513:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getCustomPage_EntitiesEntityCrossReference_6_1_0ElementType());
                    					
                    pushFollow(FOLLOW_91);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:3528:4: (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    loop98:
                    do {
                        int alt98=2;
                        int LA98_0 = input.LA(1);

                        if ( (LA98_0==21) ) {
                            alt98=1;
                        }


                        switch (alt98) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3529:5: otherlv_8= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getCustomPage_CommaKeyword_6_2_0ElementType());
                    	    				
                    	    otherlv_8=(Token)match(input,21,FOLLOW_51); 

                    	    					doneLeaf(otherlv_8);
                    	    				
                    	    // PsiInternalEJSL.g:3536:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:3537:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:3537:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:3538:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getCustomPage_EntitiesEntityCrossReference_6_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_91);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop98;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:3555:3: (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==63) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // PsiInternalEJSL.g:3556:4: otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}'
                    {

                    				markLeaf(elementTypeProvider.getCustomPage_PageactionsKeyword_7_0ElementType());
                    			
                    otherlv_10=(Token)match(input,63,FOLLOW_4); 

                    				doneLeaf(otherlv_10);
                    			

                    				markLeaf(elementTypeProvider.getCustomPage_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_70); 

                    				doneLeaf(otherlv_11);
                    			
                    // PsiInternalEJSL.g:3570:4: ( (lv_pageactions_12_0= rulePageAction ) )*
                    loop100:
                    do {
                        int alt100=2;
                        int LA100_0 = input.LA(1);

                        if ( (LA100_0==42) ) {
                            alt100=1;
                        }


                        switch (alt100) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3571:5: (lv_pageactions_12_0= rulePageAction )
                    	    {
                    	    // PsiInternalEJSL.g:3571:5: (lv_pageactions_12_0= rulePageAction )
                    	    // PsiInternalEJSL.g:3572:6: lv_pageactions_12_0= rulePageAction
                    	    {

                    	    						markComposite(elementTypeProvider.getCustomPage_PageactionsPageActionParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_70);
                    	    lv_pageactions_12_0=rulePageAction();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop100;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCustomPage_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_13=(Token)match(input,18,FOLLOW_92); 

                    				doneLeaf(otherlv_13);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:3593:3: (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==60) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // PsiInternalEJSL.g:3594:4: otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getCustomPage_ParameterGroupsKeyword_8_0ElementType());
                    			
                    otherlv_14=(Token)match(input,60,FOLLOW_33); 

                    				doneLeaf(otherlv_14);
                    			
                    // PsiInternalEJSL.g:3601:4: ( (otherlv_15= RULE_ID ) )
                    // PsiInternalEJSL.g:3602:5: (otherlv_15= RULE_ID )
                    {
                    // PsiInternalEJSL.g:3602:5: (otherlv_15= RULE_ID )
                    // PsiInternalEJSL.g:3603:6: otherlv_15= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getCustomPage_ParametergroupsParameterGroupCrossReference_8_1_0ElementType());
                    					
                    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_93); 

                    						doneLeaf(otherlv_15);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:3618:4: (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )*
                    loop102:
                    do {
                        int alt102=2;
                        int LA102_0 = input.LA(1);

                        if ( (LA102_0==21) ) {
                            alt102=1;
                        }


                        switch (alt102) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3619:5: otherlv_16= ',' ( (otherlv_17= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getCustomPage_CommaKeyword_8_2_0ElementType());
                    	    				
                    	    otherlv_16=(Token)match(input,21,FOLLOW_33); 

                    	    					doneLeaf(otherlv_16);
                    	    				
                    	    // PsiInternalEJSL.g:3626:5: ( (otherlv_17= RULE_ID ) )
                    	    // PsiInternalEJSL.g:3627:6: (otherlv_17= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:3627:6: (otherlv_17= RULE_ID )
                    	    // PsiInternalEJSL.g:3628:7: otherlv_17= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getCustomPage_ParametergroupsParameterGroupCrossReference_8_2_1_0ElementType());
                    	    						
                    	    otherlv_17=(Token)match(input,RULE_ID,FOLLOW_93); 

                    	    							doneLeaf(otherlv_17);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop102;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:3645:3: (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==61) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // PsiInternalEJSL.g:3646:4: otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getCustomPage_GlobalparametersKeyword_9_0ElementType());
                    			
                    otherlv_18=(Token)match(input,61,FOLLOW_33); 

                    				doneLeaf(otherlv_18);
                    			
                    // PsiInternalEJSL.g:3653:4: ( (otherlv_19= RULE_ID ) )
                    // PsiInternalEJSL.g:3654:5: (otherlv_19= RULE_ID )
                    {
                    // PsiInternalEJSL.g:3654:5: (otherlv_19= RULE_ID )
                    // PsiInternalEJSL.g:3655:6: otherlv_19= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getCustomPage_GlobalparametersParameterCrossReference_9_1_0ElementType());
                    					
                    otherlv_19=(Token)match(input,RULE_ID,FOLLOW_94); 

                    						doneLeaf(otherlv_19);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:3670:4: (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )*
                    loop104:
                    do {
                        int alt104=2;
                        int LA104_0 = input.LA(1);

                        if ( (LA104_0==21) ) {
                            alt104=1;
                        }


                        switch (alt104) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3671:5: otherlv_20= ',' ( (otherlv_21= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getCustomPage_CommaKeyword_9_2_0ElementType());
                    	    				
                    	    otherlv_20=(Token)match(input,21,FOLLOW_33); 

                    	    					doneLeaf(otherlv_20);
                    	    				
                    	    // PsiInternalEJSL.g:3678:5: ( (otherlv_21= RULE_ID ) )
                    	    // PsiInternalEJSL.g:3679:6: (otherlv_21= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:3679:6: (otherlv_21= RULE_ID )
                    	    // PsiInternalEJSL.g:3680:7: otherlv_21= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getCustomPage_GlobalparametersParameterCrossReference_9_2_1_0ElementType());
                    	    						
                    	    otherlv_21=(Token)match(input,RULE_ID,FOLLOW_94); 

                    	    							doneLeaf(otherlv_21);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop104;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:3697:3: (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==62) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // PsiInternalEJSL.g:3698:4: otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}'
                    {

                    				markLeaf(elementTypeProvider.getCustomPage_LocalparametersKeyword_10_0ElementType());
                    			
                    otherlv_22=(Token)match(input,62,FOLLOW_4); 

                    				doneLeaf(otherlv_22);
                    			

                    				markLeaf(elementTypeProvider.getCustomPage_LeftCurlyBracketKeyword_10_1ElementType());
                    			
                    otherlv_23=(Token)match(input,16,FOLLOW_12); 

                    				doneLeaf(otherlv_23);
                    			
                    // PsiInternalEJSL.g:3712:4: ( (lv_localparameters_24_0= ruleParameter ) )*
                    loop106:
                    do {
                        int alt106=2;
                        int LA106_0 = input.LA(1);

                        if ( (LA106_0==34) ) {
                            alt106=1;
                        }


                        switch (alt106) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3713:5: (lv_localparameters_24_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:3713:5: (lv_localparameters_24_0= ruleParameter )
                    	    // PsiInternalEJSL.g:3714:6: lv_localparameters_24_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getCustomPage_LocalparametersParameterParserRuleCall_10_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_24_0=ruleParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop106;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCustomPage_RightCurlyBracketKeyword_10_3ElementType());
                    			
                    otherlv_25=(Token)match(input,18,FOLLOW_95); 

                    				doneLeaf(otherlv_25);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:3735:3: (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==64) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // PsiInternalEJSL.g:3736:4: otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}'
                    {

                    				markLeaf(elementTypeProvider.getCustomPage_LinksKeyword_11_0ElementType());
                    			
                    otherlv_26=(Token)match(input,64,FOLLOW_4); 

                    				doneLeaf(otherlv_26);
                    			

                    				markLeaf(elementTypeProvider.getCustomPage_LeftCurlyBracketKeyword_11_1ElementType());
                    			
                    otherlv_27=(Token)match(input,16,FOLLOW_72); 

                    				doneLeaf(otherlv_27);
                    			
                    // PsiInternalEJSL.g:3750:4: ( (lv_links_28_0= ruleLink ) )*
                    loop108:
                    do {
                        int alt108=2;
                        int LA108_0 = input.LA(1);

                        if ( (LA108_0==77||(LA108_0>=83 && LA108_0<=84)) ) {
                            alt108=1;
                        }


                        switch (alt108) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3751:5: (lv_links_28_0= ruleLink )
                    	    {
                    	    // PsiInternalEJSL.g:3751:5: (lv_links_28_0= ruleLink )
                    	    // PsiInternalEJSL.g:3752:6: lv_links_28_0= ruleLink
                    	    {

                    	    						markComposite(elementTypeProvider.getCustomPage_LinksLinkParserRuleCall_11_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_72);
                    	    lv_links_28_0=ruleLink();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop108;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCustomPage_RightCurlyBracketKeyword_11_3ElementType());
                    			
                    otherlv_29=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_29);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getCustomPage_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_30=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_30);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCustomPage"


    // $ANTLR start "entryRuleHTMLTypes"
    // PsiInternalEJSL.g:3784:1: entryRuleHTMLTypes returns [Boolean current=false] : iv_ruleHTMLTypes= ruleHTMLTypes EOF ;
    public final Boolean entryRuleHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleHTMLTypes = null;


        try {
            // PsiInternalEJSL.g:3784:51: (iv_ruleHTMLTypes= ruleHTMLTypes EOF )
            // PsiInternalEJSL.g:3785:2: iv_ruleHTMLTypes= ruleHTMLTypes EOF
            {
             markComposite(elementTypeProvider.getHTMLTypesElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleHTMLTypes=ruleHTMLTypes();

            state._fsp--;

             current =iv_ruleHTMLTypes; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHTMLTypes"


    // $ANTLR start "ruleHTMLTypes"
    // PsiInternalEJSL.g:3791:1: ruleHTMLTypes returns [Boolean current=false] : (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes ) ;
    public final Boolean ruleHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean this_SimpleHTMLTypes_0 = null;

        Boolean this_ComplexHTMLTypes_1 = null;


        try {
            // PsiInternalEJSL.g:3792:1: ( (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes ) )
            // PsiInternalEJSL.g:3793:2: (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes )
            {
            // PsiInternalEJSL.g:3793:2: (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes )
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==154||LA110_0==156||(LA110_0>=170 && LA110_0<=176)) ) {
                alt110=1;
            }
            else if ( ((LA110_0>=177 && LA110_0<=179)) ) {
                alt110=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 110, 0, input);

                throw nvae;
            }
            switch (alt110) {
                case 1 :
                    // PsiInternalEJSL.g:3794:3: this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes
                    {

                    			markComposite(elementTypeProvider.getHTMLTypes_SimpleHTMLTypesParserRuleCall_0ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_SimpleHTMLTypes_0=ruleSimpleHTMLTypes();

                    state._fsp--;


                    			current = this_SimpleHTMLTypes_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:3803:3: this_ComplexHTMLTypes_1= ruleComplexHTMLTypes
                    {

                    			markComposite(elementTypeProvider.getHTMLTypes_ComplexHTMLTypesParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_ComplexHTMLTypes_1=ruleComplexHTMLTypes();

                    state._fsp--;


                    			current = this_ComplexHTMLTypes_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHTMLTypes"


    // $ANTLR start "entryRuleSimpleHTMLTypes"
    // PsiInternalEJSL.g:3815:1: entryRuleSimpleHTMLTypes returns [Boolean current=false] : iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF ;
    public final Boolean entryRuleSimpleHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleSimpleHTMLTypes = null;


        try {
            // PsiInternalEJSL.g:3815:57: (iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF )
            // PsiInternalEJSL.g:3816:2: iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF
            {
             markComposite(elementTypeProvider.getSimpleHTMLTypesElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleHTMLTypes=ruleSimpleHTMLTypes();

            state._fsp--;

             current =iv_ruleSimpleHTMLTypes; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleHTMLTypes"


    // $ANTLR start "ruleSimpleHTMLTypes"
    // PsiInternalEJSL.g:3822:1: ruleSimpleHTMLTypes returns [Boolean current=false] : ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) ) ;
    public final Boolean ruleSimpleHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean lv_htmltype_0_0 = null;


        try {
            // PsiInternalEJSL.g:3823:1: ( ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) ) )
            // PsiInternalEJSL.g:3824:2: ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) )
            {
            // PsiInternalEJSL.g:3824:2: ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) )
            // PsiInternalEJSL.g:3825:3: (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds )
            {
            // PsiInternalEJSL.g:3825:3: (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds )
            // PsiInternalEJSL.g:3826:4: lv_htmltype_0_0= ruleSimpleHTMLTypeKinds
            {

            				markComposite(elementTypeProvider.getSimpleHTMLTypes_HtmltypeSimpleHTMLTypeKindsEnumRuleCall_0ElementType());
            			
            pushFollow(FOLLOW_2);
            lv_htmltype_0_0=ruleSimpleHTMLTypeKinds();

            state._fsp--;


            				doneComposite();
            				if(!current) {
            					associateWithSemanticElement();
            					current = true;
            				}
            			

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleHTMLTypes"


    // $ANTLR start "entryRuleComplexHTMLTypes"
    // PsiInternalEJSL.g:3842:1: entryRuleComplexHTMLTypes returns [Boolean current=false] : iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF ;
    public final Boolean entryRuleComplexHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleComplexHTMLTypes = null;


        try {
            // PsiInternalEJSL.g:3842:58: (iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF )
            // PsiInternalEJSL.g:3843:2: iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF
            {
             markComposite(elementTypeProvider.getComplexHTMLTypesElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleComplexHTMLTypes=ruleComplexHTMLTypes();

            state._fsp--;

             current =iv_ruleComplexHTMLTypes; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComplexHTMLTypes"


    // $ANTLR start "ruleComplexHTMLTypes"
    // PsiInternalEJSL.g:3849:1: ruleComplexHTMLTypes returns [Boolean current=false] : ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' ) ;
    public final Boolean ruleComplexHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Boolean lv_htmltype_0_0 = null;

        Boolean lv_keyvaluepairs_2_0 = null;

        Boolean lv_keyvaluepairs_4_0 = null;


        try {
            // PsiInternalEJSL.g:3850:1: ( ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' ) )
            // PsiInternalEJSL.g:3851:2: ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' )
            {
            // PsiInternalEJSL.g:3851:2: ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' )
            // PsiInternalEJSL.g:3852:3: ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')'
            {
            // PsiInternalEJSL.g:3852:3: ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) )
            // PsiInternalEJSL.g:3853:4: (lv_htmltype_0_0= ruleComplexHTMLTypeKinds )
            {
            // PsiInternalEJSL.g:3853:4: (lv_htmltype_0_0= ruleComplexHTMLTypeKinds )
            // PsiInternalEJSL.g:3854:5: lv_htmltype_0_0= ruleComplexHTMLTypeKinds
            {

            					markComposite(elementTypeProvider.getComplexHTMLTypes_HtmltypeComplexHTMLTypeKindsEnumRuleCall_0_0ElementType());
            				
            pushFollow(FOLLOW_96);
            lv_htmltype_0_0=ruleComplexHTMLTypeKinds();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getComplexHTMLTypes_LeftParenthesisKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,75,FOLLOW_97); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:3874:3: ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) )
            // PsiInternalEJSL.g:3875:4: (lv_keyvaluepairs_2_0= ruleKeyValuePair )
            {
            // PsiInternalEJSL.g:3875:4: (lv_keyvaluepairs_2_0= ruleKeyValuePair )
            // PsiInternalEJSL.g:3876:5: lv_keyvaluepairs_2_0= ruleKeyValuePair
            {

            					markComposite(elementTypeProvider.getComplexHTMLTypes_KeyvaluepairsKeyValuePairParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_98);
            lv_keyvaluepairs_2_0=ruleKeyValuePair();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:3889:3: (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )*
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( (LA111_0==21) ) {
                    alt111=1;
                }


                switch (alt111) {
            	case 1 :
            	    // PsiInternalEJSL.g:3890:4: otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) )
            	    {

            	    				markLeaf(elementTypeProvider.getComplexHTMLTypes_CommaKeyword_3_0ElementType());
            	    			
            	    otherlv_3=(Token)match(input,21,FOLLOW_97); 

            	    				doneLeaf(otherlv_3);
            	    			
            	    // PsiInternalEJSL.g:3897:4: ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) )
            	    // PsiInternalEJSL.g:3898:5: (lv_keyvaluepairs_4_0= ruleKeyValuePair )
            	    {
            	    // PsiInternalEJSL.g:3898:5: (lv_keyvaluepairs_4_0= ruleKeyValuePair )
            	    // PsiInternalEJSL.g:3899:6: lv_keyvaluepairs_4_0= ruleKeyValuePair
            	    {

            	    						markComposite(elementTypeProvider.getComplexHTMLTypes_KeyvaluepairsKeyValuePairParserRuleCall_3_1_0ElementType());
            	    					
            	    pushFollow(FOLLOW_98);
            	    lv_keyvaluepairs_4_0=ruleKeyValuePair();

            	    state._fsp--;


            	    						doneComposite();
            	    						if(!current) {
            	    							associateWithSemanticElement();
            	    							current = true;
            	    						}
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop111;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getComplexHTMLTypes_RightParenthesisKeyword_4ElementType());
            		
            otherlv_5=(Token)match(input,76,FOLLOW_2); 

            			doneLeaf(otherlv_5);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComplexHTMLTypes"


    // $ANTLR start "entryRuleLink"
    // PsiInternalEJSL.g:3924:1: entryRuleLink returns [Boolean current=false] : iv_ruleLink= ruleLink EOF ;
    public final Boolean entryRuleLink() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLink = null;


        try {
            // PsiInternalEJSL.g:3924:46: (iv_ruleLink= ruleLink EOF )
            // PsiInternalEJSL.g:3925:2: iv_ruleLink= ruleLink EOF
            {
             markComposite(elementTypeProvider.getLinkElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleLink=ruleLink();

            state._fsp--;

             current =iv_ruleLink; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLink"


    // $ANTLR start "ruleLink"
    // PsiInternalEJSL.g:3931:1: ruleLink returns [Boolean current=false] : (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink ) ;
    public final Boolean ruleLink() throws RecognitionException {
        Boolean current = false;

        Boolean this_ExternalLink_0 = null;

        Boolean this_InternalLink_1 = null;


        try {
            // PsiInternalEJSL.g:3932:1: ( (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink ) )
            // PsiInternalEJSL.g:3933:2: (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink )
            {
            // PsiInternalEJSL.g:3933:2: (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink )
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==77) ) {
                alt112=1;
            }
            else if ( ((LA112_0>=83 && LA112_0<=84)) ) {
                alt112=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 112, 0, input);

                throw nvae;
            }
            switch (alt112) {
                case 1 :
                    // PsiInternalEJSL.g:3934:3: this_ExternalLink_0= ruleExternalLink
                    {

                    			markComposite(elementTypeProvider.getLink_ExternalLinkParserRuleCall_0ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_ExternalLink_0=ruleExternalLink();

                    state._fsp--;


                    			current = this_ExternalLink_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:3943:3: this_InternalLink_1= ruleInternalLink
                    {

                    			markComposite(elementTypeProvider.getLink_InternalLinkParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_InternalLink_1=ruleInternalLink();

                    state._fsp--;


                    			current = this_InternalLink_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLink"


    // $ANTLR start "entryRuleExternalLink"
    // PsiInternalEJSL.g:3955:1: entryRuleExternalLink returns [Boolean current=false] : iv_ruleExternalLink= ruleExternalLink EOF ;
    public final Boolean entryRuleExternalLink() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleExternalLink = null;


        try {
            // PsiInternalEJSL.g:3955:54: (iv_ruleExternalLink= ruleExternalLink EOF )
            // PsiInternalEJSL.g:3956:2: iv_ruleExternalLink= ruleExternalLink EOF
            {
             markComposite(elementTypeProvider.getExternalLinkElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleExternalLink=ruleExternalLink();

            state._fsp--;

             current =iv_ruleExternalLink; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExternalLink"


    // $ANTLR start "ruleExternalLink"
    // PsiInternalEJSL.g:3962:1: ruleExternalLink returns [Boolean current=false] : ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' ) ;
    public final Boolean ruleExternalLink() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_target_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token lv_label_11_0=null;
        Token otherlv_12=null;

        try {
            // PsiInternalEJSL.g:3963:1: ( ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' ) )
            // PsiInternalEJSL.g:3964:2: ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' )
            {
            // PsiInternalEJSL.g:3964:2: ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' )
            // PsiInternalEJSL.g:3965:3: () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}'
            {
            // PsiInternalEJSL.g:3965:3: ()
            // PsiInternalEJSL.g:3966:4: 
            {

            				precedeComposite(elementTypeProvider.getExternalLink_ExternalLinkAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getExternalLink_ExternalLinkKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,77,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getExternalLink_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_99); 

            			doneLeaf(otherlv_2);
            		

            			markLeaf(elementTypeProvider.getExternalLink_TargetKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,78,FOLLOW_3); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:3993:3: ( (lv_target_4_0= RULE_STRING ) )
            // PsiInternalEJSL.g:3994:4: (lv_target_4_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:3994:4: (lv_target_4_0= RULE_STRING )
            // PsiInternalEJSL.g:3995:5: lv_target_4_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getExternalLink_TargetSTRINGTerminalRuleCall_4_0ElementType());
            				
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_100); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_target_4_0);
            				

            }


            }

            // PsiInternalEJSL.g:4010:3: (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==79) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // PsiInternalEJSL.g:4011:4: otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) )
                    {

                    				markLeaf(elementTypeProvider.getExternalLink_LinkedAttributeKeyword_5_0ElementType());
                    			
                    otherlv_5=(Token)match(input,79,FOLLOW_51); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:4018:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:4019:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:4019:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:4020:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getExternalLink_LinkedAttributeAttributeCrossReference_5_1_0ElementType());
                    					
                    pushFollow(FOLLOW_101);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:4036:3: (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==80) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // PsiInternalEJSL.g:4037:4: otherlv_7= 'linked action =' ( ( ruleQualifiedName ) )
                    {

                    				markLeaf(elementTypeProvider.getExternalLink_LinkedActionKeyword_6_0ElementType());
                    			
                    otherlv_7=(Token)match(input,80,FOLLOW_51); 

                    				doneLeaf(otherlv_7);
                    			
                    // PsiInternalEJSL.g:4044:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:4045:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:4045:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:4046:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getExternalLink_LinkedActionPageActionCrossReference_6_1_0ElementType());
                    					
                    pushFollow(FOLLOW_102);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:4062:3: (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==81) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // PsiInternalEJSL.g:4063:4: otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getExternalLink_LabelKeyword_7_0ElementType());
                    			
                    otherlv_9=(Token)match(input,81,FOLLOW_103); 

                    				doneLeaf(otherlv_9);
                    			

                    				markLeaf(elementTypeProvider.getExternalLink_EqualsSignKeyword_7_1ElementType());
                    			
                    otherlv_10=(Token)match(input,82,FOLLOW_3); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:4077:4: ( (lv_label_11_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:4078:5: (lv_label_11_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:4078:5: (lv_label_11_0= RULE_STRING )
                    // PsiInternalEJSL.g:4079:6: lv_label_11_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getExternalLink_LabelSTRINGTerminalRuleCall_7_2_0ElementType());
                    					
                    lv_label_11_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_label_11_0);
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getExternalLink_RightCurlyBracketKeyword_8ElementType());
            		
            otherlv_12=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_12);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExternalLink"


    // $ANTLR start "entryRuleInternalLink"
    // PsiInternalEJSL.g:4106:1: entryRuleInternalLink returns [Boolean current=false] : iv_ruleInternalLink= ruleInternalLink EOF ;
    public final Boolean entryRuleInternalLink() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleInternalLink = null;


        try {
            // PsiInternalEJSL.g:4106:54: (iv_ruleInternalLink= ruleInternalLink EOF )
            // PsiInternalEJSL.g:4107:2: iv_ruleInternalLink= ruleInternalLink EOF
            {
             markComposite(elementTypeProvider.getInternalLinkElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleInternalLink=ruleInternalLink();

            state._fsp--;

             current =iv_ruleInternalLink; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInternalLink"


    // $ANTLR start "ruleInternalLink"
    // PsiInternalEJSL.g:4113:1: ruleInternalLink returns [Boolean current=false] : ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink ) ;
    public final Boolean ruleInternalLink() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Boolean lv_name_2_0 = null;

        Boolean this_ContextLink_11 = null;


        try {
            // PsiInternalEJSL.g:4114:1: ( ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink ) )
            // PsiInternalEJSL.g:4115:2: ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink )
            {
            // PsiInternalEJSL.g:4115:2: ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink )
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==83) ) {
                alt118=1;
            }
            else if ( (LA118_0==84) ) {
                alt118=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;
            }
            switch (alt118) {
                case 1 :
                    // PsiInternalEJSL.g:4116:3: ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) )
                    {
                    // PsiInternalEJSL.g:4116:3: ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) )
                    // PsiInternalEJSL.g:4117:4: () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' )
                    {
                    // PsiInternalEJSL.g:4117:4: ()
                    // PsiInternalEJSL.g:4118:5: 
                    {

                    					precedeComposite(elementTypeProvider.getInternalLink_InternalLinkAction_0_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalEJSL.g:4124:4: (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' )
                    // PsiInternalEJSL.g:4125:5: otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}'
                    {

                    					markLeaf(elementTypeProvider.getInternalLink_InternalLinkKeyword_0_1_0ElementType());
                    				
                    otherlv_1=(Token)match(input,83,FOLLOW_51); 

                    					doneLeaf(otherlv_1);
                    				
                    // PsiInternalEJSL.g:4132:5: ( (lv_name_2_0= ruleMYID ) )
                    // PsiInternalEJSL.g:4133:6: (lv_name_2_0= ruleMYID )
                    {
                    // PsiInternalEJSL.g:4133:6: (lv_name_2_0= ruleMYID )
                    // PsiInternalEJSL.g:4134:7: lv_name_2_0= ruleMYID
                    {

                    							markComposite(elementTypeProvider.getInternalLink_NameMYIDParserRuleCall_0_1_1_0ElementType());
                    						
                    pushFollow(FOLLOW_4);
                    lv_name_2_0=ruleMYID();

                    state._fsp--;


                    							doneComposite();
                    							if(!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    }


                    }


                    					markLeaf(elementTypeProvider.getInternalLink_LeftCurlyBracketKeyword_0_1_2ElementType());
                    				
                    otherlv_3=(Token)match(input,16,FOLLOW_99); 

                    					doneLeaf(otherlv_3);
                    				

                    					markLeaf(elementTypeProvider.getInternalLink_TargetKeyword_0_1_3ElementType());
                    				
                    otherlv_4=(Token)match(input,78,FOLLOW_51); 

                    					doneLeaf(otherlv_4);
                    				
                    // PsiInternalEJSL.g:4161:5: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:4162:6: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:4162:6: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:4163:7: ruleQualifiedName
                    {

                    							if (!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    							markComposite(elementTypeProvider.getInternalLink_TargetPageCrossReference_0_1_4_0ElementType());
                    						
                    pushFollow(FOLLOW_104);
                    ruleQualifiedName();

                    state._fsp--;


                    							doneComposite();
                    						

                    }


                    }

                    // PsiInternalEJSL.g:4178:5: (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )?
                    int alt116=2;
                    int LA116_0 = input.LA(1);

                    if ( (LA116_0==79) ) {
                        alt116=1;
                    }
                    switch (alt116) {
                        case 1 :
                            // PsiInternalEJSL.g:4179:6: otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) )
                            {

                            						markLeaf(elementTypeProvider.getInternalLink_LinkedAttributeKeyword_0_1_5_0ElementType());
                            					
                            otherlv_6=(Token)match(input,79,FOLLOW_51); 

                            						doneLeaf(otherlv_6);
                            					
                            // PsiInternalEJSL.g:4186:6: ( ( ruleQualifiedName ) )
                            // PsiInternalEJSL.g:4187:7: ( ruleQualifiedName )
                            {
                            // PsiInternalEJSL.g:4187:7: ( ruleQualifiedName )
                            // PsiInternalEJSL.g:4188:8: ruleQualifiedName
                            {

                            								if (!current) {
                            									associateWithSemanticElement();
                            									current = true;
                            								}
                            							

                            								markComposite(elementTypeProvider.getInternalLink_LinkedAttributeAttributeCrossReference_0_1_5_1_0ElementType());
                            							
                            pushFollow(FOLLOW_105);
                            ruleQualifiedName();

                            state._fsp--;


                            								doneComposite();
                            							

                            }


                            }


                            }
                            break;

                    }

                    // PsiInternalEJSL.g:4204:5: (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )?
                    int alt117=2;
                    int LA117_0 = input.LA(1);

                    if ( (LA117_0==80) ) {
                        alt117=1;
                    }
                    switch (alt117) {
                        case 1 :
                            // PsiInternalEJSL.g:4205:6: otherlv_8= 'linked action =' ( ( ruleQualifiedName ) )
                            {

                            						markLeaf(elementTypeProvider.getInternalLink_LinkedActionKeyword_0_1_6_0ElementType());
                            					
                            otherlv_8=(Token)match(input,80,FOLLOW_51); 

                            						doneLeaf(otherlv_8);
                            					
                            // PsiInternalEJSL.g:4212:6: ( ( ruleQualifiedName ) )
                            // PsiInternalEJSL.g:4213:7: ( ruleQualifiedName )
                            {
                            // PsiInternalEJSL.g:4213:7: ( ruleQualifiedName )
                            // PsiInternalEJSL.g:4214:8: ruleQualifiedName
                            {

                            								if (!current) {
                            									associateWithSemanticElement();
                            									current = true;
                            								}
                            							

                            								markComposite(elementTypeProvider.getInternalLink_LinkedActionPageActionCrossReference_0_1_6_1_0ElementType());
                            							
                            pushFollow(FOLLOW_7);
                            ruleQualifiedName();

                            state._fsp--;


                            								doneComposite();
                            							

                            }


                            }


                            }
                            break;

                    }


                    					markLeaf(elementTypeProvider.getInternalLink_RightCurlyBracketKeyword_0_1_7ElementType());
                    				
                    otherlv_10=(Token)match(input,18,FOLLOW_2); 

                    					doneLeaf(otherlv_10);
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:4240:3: this_ContextLink_11= ruleContextLink
                    {

                    			markComposite(elementTypeProvider.getInternalLink_ContextLinkParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_ContextLink_11=ruleContextLink();

                    state._fsp--;


                    			current = this_ContextLink_11;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInternalLink"


    // $ANTLR start "entryRuleContextLink"
    // PsiInternalEJSL.g:4252:1: entryRuleContextLink returns [Boolean current=false] : iv_ruleContextLink= ruleContextLink EOF ;
    public final Boolean entryRuleContextLink() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleContextLink = null;


        try {
            // PsiInternalEJSL.g:4252:53: (iv_ruleContextLink= ruleContextLink EOF )
            // PsiInternalEJSL.g:4253:2: iv_ruleContextLink= ruleContextLink EOF
            {
             markComposite(elementTypeProvider.getContextLinkElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleContextLink=ruleContextLink();

            state._fsp--;

             current =iv_ruleContextLink; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleContextLink"


    // $ANTLR start "ruleContextLink"
    // PsiInternalEJSL.g:4259:1: ruleContextLink returns [Boolean current=false] : ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' ) ;
    public final Boolean ruleContextLink() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_linkparameters_12_0 = null;


        try {
            // PsiInternalEJSL.g:4260:1: ( ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' ) )
            // PsiInternalEJSL.g:4261:2: ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' )
            {
            // PsiInternalEJSL.g:4261:2: ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' )
            // PsiInternalEJSL.g:4262:3: () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}'
            {
            // PsiInternalEJSL.g:4262:3: ()
            // PsiInternalEJSL.g:4263:4: 
            {

            				precedeComposite(elementTypeProvider.getContextLink_ContextLinkAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getContextLink_InternalcontextLinkKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,84,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4276:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:4277:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:4277:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:4278:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getContextLink_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getContextLink_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_99); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getContextLink_TargetKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,78,FOLLOW_51); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:4305:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:4306:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:4306:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:4307:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getContextLink_TargetPageCrossReference_5_0ElementType());
            				
            pushFollow(FOLLOW_106);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }

            // PsiInternalEJSL.g:4322:3: (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==79) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // PsiInternalEJSL.g:4323:4: otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) )
                    {

                    				markLeaf(elementTypeProvider.getContextLink_LinkedAttributeKeyword_6_0ElementType());
                    			
                    otherlv_6=(Token)match(input,79,FOLLOW_51); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:4330:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:4331:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:4331:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:4332:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getContextLink_LinkedAttributeAttributeCrossReference_6_1_0ElementType());
                    					
                    pushFollow(FOLLOW_107);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:4348:3: (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==80) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // PsiInternalEJSL.g:4349:4: otherlv_8= 'linked action =' ( ( ruleQualifiedName ) )
                    {

                    				markLeaf(elementTypeProvider.getContextLink_LinkedActionKeyword_7_0ElementType());
                    			
                    otherlv_8=(Token)match(input,80,FOLLOW_51); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:4356:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:4357:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:4357:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:4358:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getContextLink_LinkedActionPageActionCrossReference_7_1_0ElementType());
                    					
                    pushFollow(FOLLOW_108);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getContextLink_LinkparametersKeyword_8ElementType());
            		
            otherlv_10=(Token)match(input,85,FOLLOW_4); 

            			doneLeaf(otherlv_10);
            		

            			markLeaf(elementTypeProvider.getContextLink_LeftCurlyBracketKeyword_9ElementType());
            		
            otherlv_11=(Token)match(input,16,FOLLOW_12); 

            			doneLeaf(otherlv_11);
            		
            // PsiInternalEJSL.g:4388:3: ( (lv_linkparameters_12_0= ruleLinkParameter ) )*
            loop121:
            do {
                int alt121=2;
                int LA121_0 = input.LA(1);

                if ( (LA121_0==34) ) {
                    alt121=1;
                }


                switch (alt121) {
            	case 1 :
            	    // PsiInternalEJSL.g:4389:4: (lv_linkparameters_12_0= ruleLinkParameter )
            	    {
            	    // PsiInternalEJSL.g:4389:4: (lv_linkparameters_12_0= ruleLinkParameter )
            	    // PsiInternalEJSL.g:4390:5: lv_linkparameters_12_0= ruleLinkParameter
            	    {

            	    					markComposite(elementTypeProvider.getContextLink_LinkparametersLinkParameterParserRuleCall_10_0ElementType());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_linkparameters_12_0=ruleLinkParameter();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop121;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getContextLink_RightCurlyBracketKeyword_11ElementType());
            		
            otherlv_13=(Token)match(input,18,FOLLOW_7); 

            			doneLeaf(otherlv_13);
            		

            			markLeaf(elementTypeProvider.getContextLink_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_14=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_14);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleContextLink"


    // $ANTLR start "entryRuleLinkParameter"
    // PsiInternalEJSL.g:4421:1: entryRuleLinkParameter returns [Boolean current=false] : iv_ruleLinkParameter= ruleLinkParameter EOF ;
    public final Boolean entryRuleLinkParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLinkParameter = null;


        try {
            // PsiInternalEJSL.g:4421:55: (iv_ruleLinkParameter= ruleLinkParameter EOF )
            // PsiInternalEJSL.g:4422:2: iv_ruleLinkParameter= ruleLinkParameter EOF
            {
             markComposite(elementTypeProvider.getLinkParameterElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleLinkParameter=ruleLinkParameter();

            state._fsp--;

             current =iv_ruleLinkParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLinkParameter"


    // $ANTLR start "ruleLinkParameter"
    // PsiInternalEJSL.g:4428:1: ruleLinkParameter returns [Boolean current=false] : ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) ) ) ;
    public final Boolean ruleLinkParameter() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_id_6_0=null;
        Token lv_value_7_0=null;
        Boolean lv_name_2_0 = null;


        try {
            // PsiInternalEJSL.g:4429:1: ( ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) ) ) )
            // PsiInternalEJSL.g:4430:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) ) )
            {
            // PsiInternalEJSL.g:4430:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) ) )
            // PsiInternalEJSL.g:4431:3: () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) )
            {
            // PsiInternalEJSL.g:4431:3: ()
            // PsiInternalEJSL.g:4432:4: 
            {

            				precedeComposite(elementTypeProvider.getLinkParameter_LinkParameterAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getLinkParameter_ParameterKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,34,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4445:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:4446:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:4446:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:4447:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getLinkParameter_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_103);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getLinkParameter_EqualsSignKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,82,FOLLOW_109); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:4467:3: ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) )
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==86) ) {
                alt123=1;
            }
            else if ( (LA123_0==RULE_STRING) ) {
                alt123=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 123, 0, input);

                throw nvae;
            }
            switch (alt123) {
                case 1 :
                    // PsiInternalEJSL.g:4468:4: (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) )
                    {
                    // PsiInternalEJSL.g:4468:4: (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) )
                    // PsiInternalEJSL.g:4469:5: otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) )
                    {

                    					markLeaf(elementTypeProvider.getLinkParameter_AttributeKeyword_4_0_0ElementType());
                    				
                    otherlv_4=(Token)match(input,86,FOLLOW_110); 

                    					doneLeaf(otherlv_4);
                    				
                    // PsiInternalEJSL.g:4476:5: ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) )
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( (LA122_0==RULE_STRING) ) {
                        alt122=1;
                    }
                    else if ( (LA122_0==52) ) {
                        alt122=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 122, 0, input);

                        throw nvae;
                    }
                    switch (alt122) {
                        case 1 :
                            // PsiInternalEJSL.g:4477:6: ( (otherlv_5= RULE_STRING ) )
                            {
                            // PsiInternalEJSL.g:4477:6: ( (otherlv_5= RULE_STRING ) )
                            // PsiInternalEJSL.g:4478:7: (otherlv_5= RULE_STRING )
                            {
                            // PsiInternalEJSL.g:4478:7: (otherlv_5= RULE_STRING )
                            // PsiInternalEJSL.g:4479:8: otherlv_5= RULE_STRING
                            {

                            								if (!current) {
                            									associateWithSemanticElement();
                            									current = true;
                            								}
                            							

                            								markLeaf(elementTypeProvider.getLinkParameter_AttvalueAttributeCrossReference_4_0_1_0_0ElementType());
                            							
                            otherlv_5=(Token)match(input,RULE_STRING,FOLLOW_2); 

                            								doneLeaf(otherlv_5);
                            							

                            }


                            }


                            }
                            break;
                        case 2 :
                            // PsiInternalEJSL.g:4495:6: ( (lv_id_6_0= 'ID' ) )
                            {
                            // PsiInternalEJSL.g:4495:6: ( (lv_id_6_0= 'ID' ) )
                            // PsiInternalEJSL.g:4496:7: (lv_id_6_0= 'ID' )
                            {
                            // PsiInternalEJSL.g:4496:7: (lv_id_6_0= 'ID' )
                            // PsiInternalEJSL.g:4497:8: lv_id_6_0= 'ID'
                            {

                            								markLeaf(elementTypeProvider.getLinkParameter_IdIDKeyword_4_0_1_1_0ElementType());
                            							
                            lv_id_6_0=(Token)match(input,52,FOLLOW_2); 

                            								doneLeaf(lv_id_6_0);
                            							

                            								if (!current) {
                            									associateWithSemanticElement();
                            									current = true;
                            								}
                            							

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:4515:4: ( (lv_value_7_0= RULE_STRING ) )
                    {
                    // PsiInternalEJSL.g:4515:4: ( (lv_value_7_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:4516:5: (lv_value_7_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:4516:5: (lv_value_7_0= RULE_STRING )
                    // PsiInternalEJSL.g:4517:6: lv_value_7_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getLinkParameter_ValueSTRINGTerminalRuleCall_4_1_0ElementType());
                    					
                    lv_value_7_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_value_7_0);
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLinkParameter"


    // $ANTLR start "entryRuleExtension"
    // PsiInternalEJSL.g:4537:1: entryRuleExtension returns [Boolean current=false] : iv_ruleExtension= ruleExtension EOF ;
    public final Boolean entryRuleExtension() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleExtension = null;


        try {
            // PsiInternalEJSL.g:4537:51: (iv_ruleExtension= ruleExtension EOF )
            // PsiInternalEJSL.g:4538:2: iv_ruleExtension= ruleExtension EOF
            {
             markComposite(elementTypeProvider.getExtensionElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleExtension=ruleExtension();

            state._fsp--;

             current =iv_ruleExtension; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExtension"


    // $ANTLR start "ruleExtension"
    // PsiInternalEJSL.g:4544:1: ruleExtension returns [Boolean current=false] : (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate ) ;
    public final Boolean ruleExtension() throws RecognitionException {
        Boolean current = false;

        Boolean this_ExtensionPackage_0 = null;

        Boolean this_Component_1 = null;

        Boolean this_Module_2 = null;

        Boolean this_Plugin_3 = null;

        Boolean this_Library_4 = null;

        Boolean this_Template_5 = null;


        try {
            // PsiInternalEJSL.g:4545:1: ( (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate ) )
            // PsiInternalEJSL.g:4546:2: (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate )
            {
            // PsiInternalEJSL.g:4546:2: (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate )
            int alt124=6;
            switch ( input.LA(1) ) {
            case 87:
                {
                alt124=1;
                }
                break;
            case 90:
                {
                alt124=2;
                }
                break;
            case 97:
                {
                alt124=3;
                }
                break;
            case 98:
                {
                alt124=4;
                }
                break;
            case 101:
                {
                alt124=5;
                }
                break;
            case 112:
                {
                alt124=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 124, 0, input);

                throw nvae;
            }

            switch (alt124) {
                case 1 :
                    // PsiInternalEJSL.g:4547:3: this_ExtensionPackage_0= ruleExtensionPackage
                    {

                    			markComposite(elementTypeProvider.getExtension_ExtensionPackageParserRuleCall_0ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_ExtensionPackage_0=ruleExtensionPackage();

                    state._fsp--;


                    			current = this_ExtensionPackage_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:4556:3: this_Component_1= ruleComponent
                    {

                    			markComposite(elementTypeProvider.getExtension_ComponentParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_Component_1=ruleComponent();

                    state._fsp--;


                    			current = this_Component_1;
                    			doneComposite();
                    		

                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:4565:3: this_Module_2= ruleModule
                    {

                    			markComposite(elementTypeProvider.getExtension_ModuleParserRuleCall_2ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_Module_2=ruleModule();

                    state._fsp--;


                    			current = this_Module_2;
                    			doneComposite();
                    		

                    }
                    break;
                case 4 :
                    // PsiInternalEJSL.g:4574:3: this_Plugin_3= rulePlugin
                    {

                    			markComposite(elementTypeProvider.getExtension_PluginParserRuleCall_3ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_Plugin_3=rulePlugin();

                    state._fsp--;


                    			current = this_Plugin_3;
                    			doneComposite();
                    		

                    }
                    break;
                case 5 :
                    // PsiInternalEJSL.g:4583:3: this_Library_4= ruleLibrary
                    {

                    			markComposite(elementTypeProvider.getExtension_LibraryParserRuleCall_4ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_Library_4=ruleLibrary();

                    state._fsp--;


                    			current = this_Library_4;
                    			doneComposite();
                    		

                    }
                    break;
                case 6 :
                    // PsiInternalEJSL.g:4592:3: this_Template_5= ruleTemplate
                    {

                    			markComposite(elementTypeProvider.getExtension_TemplateParserRuleCall_5ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_Template_5=ruleTemplate();

                    state._fsp--;


                    			current = this_Template_5;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExtension"


    // $ANTLR start "entryRuleExtensionPackage"
    // PsiInternalEJSL.g:4604:1: entryRuleExtensionPackage returns [Boolean current=false] : iv_ruleExtensionPackage= ruleExtensionPackage EOF ;
    public final Boolean entryRuleExtensionPackage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleExtensionPackage = null;


        try {
            // PsiInternalEJSL.g:4604:58: (iv_ruleExtensionPackage= ruleExtensionPackage EOF )
            // PsiInternalEJSL.g:4605:2: iv_ruleExtensionPackage= ruleExtensionPackage EOF
            {
             markComposite(elementTypeProvider.getExtensionPackageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleExtensionPackage=ruleExtensionPackage();

            state._fsp--;

             current =iv_ruleExtensionPackage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExtensionPackage"


    // $ANTLR start "ruleExtensionPackage"
    // PsiInternalEJSL.g:4611:1: ruleExtensionPackage returns [Boolean current=false] : ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' ) ;
    public final Boolean ruleExtensionPackage() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_manifest_6_0 = null;

        Boolean lv_languages_10_0 = null;

        Boolean lv_extensions_14_0 = null;


        try {
            // PsiInternalEJSL.g:4612:1: ( ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' ) )
            // PsiInternalEJSL.g:4613:2: ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' )
            {
            // PsiInternalEJSL.g:4613:2: ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' )
            // PsiInternalEJSL.g:4614:3: () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}'
            {
            // PsiInternalEJSL.g:4614:3: ()
            // PsiInternalEJSL.g:4615:4: 
            {

            				precedeComposite(elementTypeProvider.getExtensionPackage_ExtensionPackageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getExtensionPackage_ExtensionPackageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,87,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4628:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:4629:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:4629:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:4630:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getExtensionPackage_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getExtensionPackage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getExtensionPackage_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getExtensionPackage_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:4664:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:4665:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:4665:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:4666:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getExtensionPackage_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getExtensionPackage_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_113); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:4686:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==89) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // PsiInternalEJSL.g:4687:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getExtensionPackage_LanguagesKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,89,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getExtensionPackage_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_114); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:4701:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop125:
                    do {
                        int alt125=2;
                        int LA125_0 = input.LA(1);

                        if ( (LA125_0==124) ) {
                            alt125=1;
                        }


                        switch (alt125) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4702:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:4702:5: (lv_languages_10_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:4703:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getExtensionPackage_LanguagesLanguageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_10_0=ruleLanguage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop125;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getExtensionPackage_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_115); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getExtensionPackage_ExtensionsKeyword_9ElementType());
            		
            otherlv_12=(Token)match(input,25,FOLLOW_4); 

            			doneLeaf(otherlv_12);
            		

            			markLeaf(elementTypeProvider.getExtensionPackage_LeftCurlyBracketKeyword_10ElementType());
            		
            otherlv_13=(Token)match(input,16,FOLLOW_116); 

            			doneLeaf(otherlv_13);
            		
            // PsiInternalEJSL.g:4738:3: ( (lv_extensions_14_0= ruleExtension ) )+
            int cnt127=0;
            loop127:
            do {
                int alt127=2;
                int LA127_0 = input.LA(1);

                if ( (LA127_0==87||LA127_0==90||(LA127_0>=97 && LA127_0<=98)||LA127_0==101||LA127_0==112) ) {
                    alt127=1;
                }


                switch (alt127) {
            	case 1 :
            	    // PsiInternalEJSL.g:4739:4: (lv_extensions_14_0= ruleExtension )
            	    {
            	    // PsiInternalEJSL.g:4739:4: (lv_extensions_14_0= ruleExtension )
            	    // PsiInternalEJSL.g:4740:5: lv_extensions_14_0= ruleExtension
            	    {

            	    					markComposite(elementTypeProvider.getExtensionPackage_ExtensionsExtensionParserRuleCall_11_0ElementType());
            	    				
            	    pushFollow(FOLLOW_21);
            	    lv_extensions_14_0=ruleExtension();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt127 >= 1 ) break loop127;
                        EarlyExitException eee =
                            new EarlyExitException(127, input);
                        throw eee;
                }
                cnt127++;
            } while (true);


            			markLeaf(elementTypeProvider.getExtensionPackage_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_15=(Token)match(input,18,FOLLOW_7); 

            			doneLeaf(otherlv_15);
            		

            			markLeaf(elementTypeProvider.getExtensionPackage_RightCurlyBracketKeyword_13ElementType());
            		
            otherlv_16=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_16);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExtensionPackage"


    // $ANTLR start "entryRuleComponent"
    // PsiInternalEJSL.g:4771:1: entryRuleComponent returns [Boolean current=false] : iv_ruleComponent= ruleComponent EOF ;
    public final Boolean entryRuleComponent() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleComponent = null;


        try {
            // PsiInternalEJSL.g:4771:51: (iv_ruleComponent= ruleComponent EOF )
            // PsiInternalEJSL.g:4772:2: iv_ruleComponent= ruleComponent EOF
            {
             markComposite(elementTypeProvider.getComponentElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent=ruleComponent();

            state._fsp--;

             current =iv_ruleComponent; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComponent"


    // $ANTLR start "ruleComponent"
    // PsiInternalEJSL.g:4778:1: ruleComponent returns [Boolean current=false] : ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' ) ;
    public final Boolean ruleComponent() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_manifest_6_0 = null;

        Boolean lv_globalParamter_10_0 = null;

        Boolean lv_languages_14_0 = null;

        Boolean lv_sections_18_0 = null;


        try {
            // PsiInternalEJSL.g:4779:1: ( ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' ) )
            // PsiInternalEJSL.g:4780:2: ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' )
            {
            // PsiInternalEJSL.g:4780:2: ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' )
            // PsiInternalEJSL.g:4781:3: () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}'
            {
            // PsiInternalEJSL.g:4781:3: ()
            // PsiInternalEJSL.g:4782:4: 
            {

            				precedeComposite(elementTypeProvider.getComponent_ComponentAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getComponent_ComponentKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,90,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4795:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:4796:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:4796:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:4797:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getComponent_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getComponent_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:4831:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:4832:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:4832:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:4833:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getComponent_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_117); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:4853:3: (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==91) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // PsiInternalEJSL.g:4854:4: otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getComponent_GlobalParameterKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,91,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_14); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:4868:4: ( (lv_globalParamter_10_0= ruleParameterGroup ) )*
                    loop128:
                    do {
                        int alt128=2;
                        int LA128_0 = input.LA(1);

                        if ( (LA128_0==40) ) {
                            alt128=1;
                        }


                        switch (alt128) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4869:5: (lv_globalParamter_10_0= ruleParameterGroup )
                    	    {
                    	    // PsiInternalEJSL.g:4869:5: (lv_globalParamter_10_0= ruleParameterGroup )
                    	    // PsiInternalEJSL.g:4870:6: lv_globalParamter_10_0= ruleParameterGroup
                    	    {

                    	    						markComposite(elementTypeProvider.getComponent_GlobalParamterParameterGroupParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_14);
                    	    lv_globalParamter_10_0=ruleParameterGroup();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop128;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_118); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:4891:3: (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==89) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // PsiInternalEJSL.g:4892:4: otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}'
                    {

                    				markLeaf(elementTypeProvider.getComponent_LanguagesKeyword_9_0ElementType());
                    			
                    otherlv_12=(Token)match(input,89,FOLLOW_4); 

                    				doneLeaf(otherlv_12);
                    			

                    				markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_9_1ElementType());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_114); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:4906:4: ( (lv_languages_14_0= ruleLanguage ) )*
                    loop130:
                    do {
                        int alt130=2;
                        int LA130_0 = input.LA(1);

                        if ( (LA130_0==124) ) {
                            alt130=1;
                        }


                        switch (alt130) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4907:5: (lv_languages_14_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:4907:5: (lv_languages_14_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:4908:6: lv_languages_14_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getComponent_LanguagesLanguageParserRuleCall_9_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_14_0=ruleLanguage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop130;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_9_3ElementType());
                    			
                    otherlv_15=(Token)match(input,18,FOLLOW_119); 

                    				doneLeaf(otherlv_15);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getComponent_SectionsKeyword_10ElementType());
            		
            otherlv_16=(Token)match(input,29,FOLLOW_4); 

            			doneLeaf(otherlv_16);
            		

            			markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_11ElementType());
            		
            otherlv_17=(Token)match(input,16,FOLLOW_28); 

            			doneLeaf(otherlv_17);
            		
            // PsiInternalEJSL.g:4943:3: ( (lv_sections_18_0= ruleSection ) )+
            int cnt132=0;
            loop132:
            do {
                int alt132=2;
                int LA132_0 = input.LA(1);

                if ( (LA132_0==92||LA132_0==96) ) {
                    alt132=1;
                }


                switch (alt132) {
            	case 1 :
            	    // PsiInternalEJSL.g:4944:4: (lv_sections_18_0= ruleSection )
            	    {
            	    // PsiInternalEJSL.g:4944:4: (lv_sections_18_0= ruleSection )
            	    // PsiInternalEJSL.g:4945:5: lv_sections_18_0= ruleSection
            	    {

            	    					markComposite(elementTypeProvider.getComponent_SectionsSectionParserRuleCall_12_0ElementType());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_sections_18_0=ruleSection();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt132 >= 1 ) break loop132;
                        EarlyExitException eee =
                            new EarlyExitException(132, input);
                        throw eee;
                }
                cnt132++;
            } while (true);


            			markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_13ElementType());
            		
            otherlv_19=(Token)match(input,18,FOLLOW_7); 

            			doneLeaf(otherlv_19);
            		

            			markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_14ElementType());
            		
            otherlv_20=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_20);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComponent"


    // $ANTLR start "entryRuleSection"
    // PsiInternalEJSL.g:4976:1: entryRuleSection returns [Boolean current=false] : iv_ruleSection= ruleSection EOF ;
    public final Boolean entryRuleSection() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleSection = null;


        try {
            // PsiInternalEJSL.g:4976:49: (iv_ruleSection= ruleSection EOF )
            // PsiInternalEJSL.g:4977:2: iv_ruleSection= ruleSection EOF
            {
             markComposite(elementTypeProvider.getSectionElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleSection=ruleSection();

            state._fsp--;

             current =iv_ruleSection; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSection"


    // $ANTLR start "ruleSection"
    // PsiInternalEJSL.g:4983:1: ruleSection returns [Boolean current=false] : (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend ) ;
    public final Boolean ruleSection() throws RecognitionException {
        Boolean current = false;

        Boolean this_Backend_0 = null;

        Boolean this_Frontend_1 = null;


        try {
            // PsiInternalEJSL.g:4984:1: ( (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend ) )
            // PsiInternalEJSL.g:4985:2: (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend )
            {
            // PsiInternalEJSL.g:4985:2: (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend )
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==92) ) {
                alt133=1;
            }
            else if ( (LA133_0==96) ) {
                alt133=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 133, 0, input);

                throw nvae;
            }
            switch (alt133) {
                case 1 :
                    // PsiInternalEJSL.g:4986:3: this_Backend_0= ruleBackend
                    {

                    			markComposite(elementTypeProvider.getSection_BackendParserRuleCall_0ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_Backend_0=ruleBackend();

                    state._fsp--;


                    			current = this_Backend_0;
                    			doneComposite();
                    		

                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:4995:3: this_Frontend_1= ruleFrontend
                    {

                    			markComposite(elementTypeProvider.getSection_FrontendParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_Frontend_1=ruleFrontend();

                    state._fsp--;


                    			current = this_Frontend_1;
                    			doneComposite();
                    		

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSection"


    // $ANTLR start "entryRuleBackend"
    // PsiInternalEJSL.g:5007:1: entryRuleBackend returns [Boolean current=false] : iv_ruleBackend= ruleBackend EOF ;
    public final Boolean entryRuleBackend() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleBackend = null;


        try {
            // PsiInternalEJSL.g:5007:49: (iv_ruleBackend= ruleBackend EOF )
            // PsiInternalEJSL.g:5008:2: iv_ruleBackend= ruleBackend EOF
            {
             markComposite(elementTypeProvider.getBackendElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleBackend=ruleBackend();

            state._fsp--;

             current =iv_ruleBackend; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBackend"


    // $ANTLR start "ruleBackend"
    // PsiInternalEJSL.g:5014:1: ruleBackend returns [Boolean current=false] : ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) ;
    public final Boolean ruleBackend() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Boolean lv_pageRef_5_0 = null;


        try {
            // PsiInternalEJSL.g:5015:1: ( ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) )
            // PsiInternalEJSL.g:5016:2: ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            {
            // PsiInternalEJSL.g:5016:2: ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            // PsiInternalEJSL.g:5017:3: () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}'
            {
            // PsiInternalEJSL.g:5017:3: ()
            // PsiInternalEJSL.g:5018:4: 
            {

            				precedeComposite(elementTypeProvider.getBackend_BackendSectionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getBackend_BackendSectionKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,92,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getBackend_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_120); 

            			doneLeaf(otherlv_2);
            		

            			markLeaf(elementTypeProvider.getBackend_PagesKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,93,FOLLOW_4); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getBackend_LeftCurlyBracketKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_121); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:5052:3: ( (lv_pageRef_5_0= rulePageReference ) )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==94) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // PsiInternalEJSL.g:5053:4: (lv_pageRef_5_0= rulePageReference )
            	    {
            	    // PsiInternalEJSL.g:5053:4: (lv_pageRef_5_0= rulePageReference )
            	    // PsiInternalEJSL.g:5054:5: lv_pageRef_5_0= rulePageReference
            	    {

            	    					markComposite(elementTypeProvider.getBackend_PageRefPageReferenceParserRuleCall_5_0ElementType());
            	    				
            	    pushFollow(FOLLOW_121);
            	    lv_pageRef_5_0=rulePageReference();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getBackend_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_6=(Token)match(input,18,FOLLOW_7); 

            			doneLeaf(otherlv_6);
            		

            			markLeaf(elementTypeProvider.getBackend_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_7);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBackend"


    // $ANTLR start "entryRulePageReference"
    // PsiInternalEJSL.g:5085:1: entryRulePageReference returns [Boolean current=false] : iv_rulePageReference= rulePageReference EOF ;
    public final Boolean entryRulePageReference() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePageReference = null;


        try {
            // PsiInternalEJSL.g:5085:55: (iv_rulePageReference= rulePageReference EOF )
            // PsiInternalEJSL.g:5086:2: iv_rulePageReference= rulePageReference EOF
            {
             markComposite(elementTypeProvider.getPageReferenceElementType()); 
            pushFollow(FOLLOW_1);
            iv_rulePageReference=rulePageReference();

            state._fsp--;

             current =iv_rulePageReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePageReference"


    // $ANTLR start "rulePageReference"
    // PsiInternalEJSL.g:5092:1: rulePageReference returns [Boolean current=false] : (otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )? ) ;
    public final Boolean rulePageReference() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Boolean lv_sect_4_0 = null;


        try {
            // PsiInternalEJSL.g:5093:1: ( (otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )? ) )
            // PsiInternalEJSL.g:5094:2: (otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )? )
            {
            // PsiInternalEJSL.g:5094:2: (otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )? )
            // PsiInternalEJSL.g:5095:3: otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )?
            {

            			markLeaf(elementTypeProvider.getPageReference_PageKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,94,FOLLOW_51); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalEJSL.g:5102:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:5103:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:5103:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:5104:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getPageReference_PagePageCrossReference_1_0ElementType());
            				
            pushFollow(FOLLOW_122);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }

            // PsiInternalEJSL.g:5119:3: (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==95) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // PsiInternalEJSL.g:5120:4: otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) )
                    {

                    				markLeaf(elementTypeProvider.getPageReference_FromKeyword_2_0ElementType());
                    			
                    otherlv_2=(Token)match(input,95,FOLLOW_51); 

                    				doneLeaf(otherlv_2);
                    			
                    // PsiInternalEJSL.g:5127:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:5128:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:5128:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:5129:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getPageReference_PagescrComponentCrossReference_2_1_0ElementType());
                    					
                    pushFollow(FOLLOW_123);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:5144:4: ( (lv_sect_4_0= ruleSectionKinds ) )
                    // PsiInternalEJSL.g:5145:5: (lv_sect_4_0= ruleSectionKinds )
                    {
                    // PsiInternalEJSL.g:5145:5: (lv_sect_4_0= ruleSectionKinds )
                    // PsiInternalEJSL.g:5146:6: lv_sect_4_0= ruleSectionKinds
                    {

                    						markComposite(elementTypeProvider.getPageReference_SectSectionKindsEnumRuleCall_2_2_0ElementType());
                    					
                    pushFollow(FOLLOW_2);
                    lv_sect_4_0=ruleSectionKinds();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageReference"


    // $ANTLR start "entryRuleFrontend"
    // PsiInternalEJSL.g:5164:1: entryRuleFrontend returns [Boolean current=false] : iv_ruleFrontend= ruleFrontend EOF ;
    public final Boolean entryRuleFrontend() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleFrontend = null;


        try {
            // PsiInternalEJSL.g:5164:50: (iv_ruleFrontend= ruleFrontend EOF )
            // PsiInternalEJSL.g:5165:2: iv_ruleFrontend= ruleFrontend EOF
            {
             markComposite(elementTypeProvider.getFrontendElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleFrontend=ruleFrontend();

            state._fsp--;

             current =iv_ruleFrontend; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFrontend"


    // $ANTLR start "ruleFrontend"
    // PsiInternalEJSL.g:5171:1: ruleFrontend returns [Boolean current=false] : ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) ;
    public final Boolean ruleFrontend() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Boolean lv_pageRef_5_0 = null;


        try {
            // PsiInternalEJSL.g:5172:1: ( ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) )
            // PsiInternalEJSL.g:5173:2: ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            {
            // PsiInternalEJSL.g:5173:2: ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            // PsiInternalEJSL.g:5174:3: () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}'
            {
            // PsiInternalEJSL.g:5174:3: ()
            // PsiInternalEJSL.g:5175:4: 
            {

            				precedeComposite(elementTypeProvider.getFrontend_FrontendSectionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getFrontend_FrontendSectionKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,96,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getFrontend_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_120); 

            			doneLeaf(otherlv_2);
            		

            			markLeaf(elementTypeProvider.getFrontend_PagesKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,93,FOLLOW_4); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getFrontend_LeftCurlyBracketKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_121); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:5209:3: ( (lv_pageRef_5_0= rulePageReference ) )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==94) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // PsiInternalEJSL.g:5210:4: (lv_pageRef_5_0= rulePageReference )
            	    {
            	    // PsiInternalEJSL.g:5210:4: (lv_pageRef_5_0= rulePageReference )
            	    // PsiInternalEJSL.g:5211:5: lv_pageRef_5_0= rulePageReference
            	    {

            	    					markComposite(elementTypeProvider.getFrontend_PageRefPageReferenceParserRuleCall_5_0ElementType());
            	    				
            	    pushFollow(FOLLOW_121);
            	    lv_pageRef_5_0=rulePageReference();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop136;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getFrontend_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_6=(Token)match(input,18,FOLLOW_7); 

            			doneLeaf(otherlv_6);
            		

            			markLeaf(elementTypeProvider.getFrontend_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_7);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFrontend"


    // $ANTLR start "entryRuleModule"
    // PsiInternalEJSL.g:5242:1: entryRuleModule returns [Boolean current=false] : iv_ruleModule= ruleModule EOF ;
    public final Boolean entryRuleModule() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleModule = null;


        try {
            // PsiInternalEJSL.g:5242:48: (iv_ruleModule= ruleModule EOF )
            // PsiInternalEJSL.g:5243:2: iv_ruleModule= ruleModule EOF
            {
             markComposite(elementTypeProvider.getModuleElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleModule=ruleModule();

            state._fsp--;

             current =iv_ruleModule; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModule"


    // $ANTLR start "ruleModule"
    // PsiInternalEJSL.g:5249:1: ruleModule returns [Boolean current=false] : ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' ) ;
    public final Boolean ruleModule() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_manifest_6_0 = null;

        Boolean lv_languages_10_0 = null;

        Boolean lv_pageRef_12_0 = null;


        try {
            // PsiInternalEJSL.g:5250:1: ( ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' ) )
            // PsiInternalEJSL.g:5251:2: ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' )
            {
            // PsiInternalEJSL.g:5251:2: ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' )
            // PsiInternalEJSL.g:5252:3: () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}'
            {
            // PsiInternalEJSL.g:5252:3: ()
            // PsiInternalEJSL.g:5253:4: 
            {

            				precedeComposite(elementTypeProvider.getModule_ModuleAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getModule_ModuleKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,97,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:5266:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:5267:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:5267:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:5268:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getModule_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getModule_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getModule_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getModule_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:5302:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:5303:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:5303:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:5304:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getModule_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getModule_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_124); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:5324:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==89) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // PsiInternalEJSL.g:5325:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getModule_LanguagesKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,89,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getModule_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_114); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:5339:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop137:
                    do {
                        int alt137=2;
                        int LA137_0 = input.LA(1);

                        if ( (LA137_0==124) ) {
                            alt137=1;
                        }


                        switch (alt137) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5340:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:5340:5: (lv_languages_10_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:5341:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getModule_LanguagesLanguageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_10_0=ruleLanguage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop137;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getModule_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_121); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5362:3: ( (lv_pageRef_12_0= rulePageReference ) )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==94) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // PsiInternalEJSL.g:5363:4: (lv_pageRef_12_0= rulePageReference )
                    {
                    // PsiInternalEJSL.g:5363:4: (lv_pageRef_12_0= rulePageReference )
                    // PsiInternalEJSL.g:5364:5: lv_pageRef_12_0= rulePageReference
                    {

                    					markComposite(elementTypeProvider.getModule_PageRefPageReferenceParserRuleCall_9_0ElementType());
                    				
                    pushFollow(FOLLOW_7);
                    lv_pageRef_12_0=rulePageReference();

                    state._fsp--;


                    					doneComposite();
                    					if(!current) {
                    						associateWithSemanticElement();
                    						current = true;
                    					}
                    				

                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getModule_RightCurlyBracketKeyword_10ElementType());
            		
            otherlv_13=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_13);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModule"


    // $ANTLR start "entryRulePlugin"
    // PsiInternalEJSL.g:5388:1: entryRulePlugin returns [Boolean current=false] : iv_rulePlugin= rulePlugin EOF ;
    public final Boolean entryRulePlugin() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePlugin = null;


        try {
            // PsiInternalEJSL.g:5388:48: (iv_rulePlugin= rulePlugin EOF )
            // PsiInternalEJSL.g:5389:2: iv_rulePlugin= rulePlugin EOF
            {
             markComposite(elementTypeProvider.getPluginElementType()); 
            pushFollow(FOLLOW_1);
            iv_rulePlugin=rulePlugin();

            state._fsp--;

             current =iv_rulePlugin; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePlugin"


    // $ANTLR start "rulePlugin"
    // PsiInternalEJSL.g:5395:1: rulePlugin returns [Boolean current=false] : ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}' ) ;
    public final Boolean rulePlugin() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_manifest_6_0 = null;

        Boolean lv_type_9_0 = null;

        Boolean lv_languages_12_0 = null;

        Boolean lv_localparameters_20_0 = null;


        try {
            // PsiInternalEJSL.g:5396:1: ( ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}' ) )
            // PsiInternalEJSL.g:5397:2: ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}' )
            {
            // PsiInternalEJSL.g:5397:2: ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}' )
            // PsiInternalEJSL.g:5398:3: () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}'
            {
            // PsiInternalEJSL.g:5398:3: ()
            // PsiInternalEJSL.g:5399:4: 
            {

            				precedeComposite(elementTypeProvider.getPlugin_PluginAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getPlugin_PluginKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,98,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:5412:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:5413:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:5413:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:5414:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getPlugin_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getPlugin_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getPlugin_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getPlugin_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:5448:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:5449:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:5449:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:5450:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getPlugin_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getPlugin_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_125); 

            			doneLeaf(otherlv_7);
            		

            			markLeaf(elementTypeProvider.getPlugin_PlugintypeKeyword_8ElementType());
            		
            otherlv_8=(Token)match(input,99,FOLLOW_126); 

            			doneLeaf(otherlv_8);
            		
            // PsiInternalEJSL.g:5477:3: ( (lv_type_9_0= rulePluginKinds ) )
            // PsiInternalEJSL.g:5478:4: (lv_type_9_0= rulePluginKinds )
            {
            // PsiInternalEJSL.g:5478:4: (lv_type_9_0= rulePluginKinds )
            // PsiInternalEJSL.g:5479:5: lv_type_9_0= rulePluginKinds
            {

            					markComposite(elementTypeProvider.getPlugin_TypePluginKindsEnumRuleCall_9_0ElementType());
            				
            pushFollow(FOLLOW_127);
            lv_type_9_0=rulePluginKinds();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:5492:3: (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==89) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // PsiInternalEJSL.g:5493:4: otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}'
                    {

                    				markLeaf(elementTypeProvider.getPlugin_LanguagesKeyword_10_0ElementType());
                    			
                    otherlv_10=(Token)match(input,89,FOLLOW_4); 

                    				doneLeaf(otherlv_10);
                    			

                    				markLeaf(elementTypeProvider.getPlugin_LeftCurlyBracketKeyword_10_1ElementType());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_114); 

                    				doneLeaf(otherlv_11);
                    			
                    // PsiInternalEJSL.g:5507:4: ( (lv_languages_12_0= ruleLanguage ) )*
                    loop140:
                    do {
                        int alt140=2;
                        int LA140_0 = input.LA(1);

                        if ( (LA140_0==124) ) {
                            alt140=1;
                        }


                        switch (alt140) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5508:5: (lv_languages_12_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:5508:5: (lv_languages_12_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:5509:6: lv_languages_12_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getPlugin_LanguagesLanguageParserRuleCall_10_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_12_0=ruleLanguage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop140;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPlugin_RightCurlyBracketKeyword_10_3ElementType());
                    			
                    otherlv_13=(Token)match(input,18,FOLLOW_128); 

                    				doneLeaf(otherlv_13);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5530:3: (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==67) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // PsiInternalEJSL.g:5531:4: otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getPlugin_EntitiesKeyword_11_0ElementType());
                    			
                    otherlv_14=(Token)match(input,67,FOLLOW_3); 

                    				doneLeaf(otherlv_14);
                    			
                    // PsiInternalEJSL.g:5538:4: ( (otherlv_15= RULE_STRING ) )
                    // PsiInternalEJSL.g:5539:5: (otherlv_15= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:5539:5: (otherlv_15= RULE_STRING )
                    // PsiInternalEJSL.g:5540:6: otherlv_15= RULE_STRING
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getPlugin_EntitiesEntityCrossReference_11_1_0ElementType());
                    					
                    otherlv_15=(Token)match(input,RULE_STRING,FOLLOW_129); 

                    						doneLeaf(otherlv_15);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:5555:4: (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )*
                    loop142:
                    do {
                        int alt142=2;
                        int LA142_0 = input.LA(1);

                        if ( (LA142_0==21) ) {
                            alt142=1;
                        }


                        switch (alt142) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5556:5: otherlv_16= ',' ( (otherlv_17= RULE_STRING ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getPlugin_CommaKeyword_11_2_0ElementType());
                    	    				
                    	    otherlv_16=(Token)match(input,21,FOLLOW_3); 

                    	    					doneLeaf(otherlv_16);
                    	    				
                    	    // PsiInternalEJSL.g:5563:5: ( (otherlv_17= RULE_STRING ) )
                    	    // PsiInternalEJSL.g:5564:6: (otherlv_17= RULE_STRING )
                    	    {
                    	    // PsiInternalEJSL.g:5564:6: (otherlv_17= RULE_STRING )
                    	    // PsiInternalEJSL.g:5565:7: otherlv_17= RULE_STRING
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getPlugin_EntitiesEntityCrossReference_11_2_1_0ElementType());
                    	    						
                    	    otherlv_17=(Token)match(input,RULE_STRING,FOLLOW_129); 

                    	    							doneLeaf(otherlv_17);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop142;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:5582:3: (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==100) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // PsiInternalEJSL.g:5583:4: otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}'
                    {

                    				markLeaf(elementTypeProvider.getPlugin_ParametersKeyword_12_0ElementType());
                    			
                    otherlv_18=(Token)match(input,100,FOLLOW_4); 

                    				doneLeaf(otherlv_18);
                    			

                    				markLeaf(elementTypeProvider.getPlugin_LeftCurlyBracketKeyword_12_1ElementType());
                    			
                    otherlv_19=(Token)match(input,16,FOLLOW_12); 

                    				doneLeaf(otherlv_19);
                    			
                    // PsiInternalEJSL.g:5597:4: ( (lv_localparameters_20_0= ruleParameter ) )*
                    loop144:
                    do {
                        int alt144=2;
                        int LA144_0 = input.LA(1);

                        if ( (LA144_0==34) ) {
                            alt144=1;
                        }


                        switch (alt144) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5598:5: (lv_localparameters_20_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:5598:5: (lv_localparameters_20_0= ruleParameter )
                    	    // PsiInternalEJSL.g:5599:6: lv_localparameters_20_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getPlugin_LocalparametersParameterParserRuleCall_12_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_20_0=ruleParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop144;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPlugin_RightCurlyBracketKeyword_12_3ElementType());
                    			
                    otherlv_21=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_21);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPlugin_RightCurlyBracketKeyword_13ElementType());
            		
            otherlv_22=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_22);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePlugin"


    // $ANTLR start "entryRuleLibrary"
    // PsiInternalEJSL.g:5631:1: entryRuleLibrary returns [Boolean current=false] : iv_ruleLibrary= ruleLibrary EOF ;
    public final Boolean entryRuleLibrary() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLibrary = null;


        try {
            // PsiInternalEJSL.g:5631:49: (iv_ruleLibrary= ruleLibrary EOF )
            // PsiInternalEJSL.g:5632:2: iv_ruleLibrary= ruleLibrary EOF
            {
             markComposite(elementTypeProvider.getLibraryElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleLibrary=ruleLibrary();

            state._fsp--;

             current =iv_ruleLibrary; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLibrary"


    // $ANTLR start "ruleLibrary"
    // PsiInternalEJSL.g:5638:1: ruleLibrary returns [Boolean current=false] : ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' ) ;
    public final Boolean ruleLibrary() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_manifest_6_0 = null;

        Boolean lv_languages_10_0 = null;

        Boolean lv_classes_18_0 = null;

        Boolean lv_packages_22_0 = null;


        try {
            // PsiInternalEJSL.g:5639:1: ( ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' ) )
            // PsiInternalEJSL.g:5640:2: ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' )
            {
            // PsiInternalEJSL.g:5640:2: ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' )
            // PsiInternalEJSL.g:5641:3: () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}'
            {
            // PsiInternalEJSL.g:5641:3: ()
            // PsiInternalEJSL.g:5642:4: 
            {

            				precedeComposite(elementTypeProvider.getLibrary_LibraryAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getLibrary_LibraryKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,101,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:5655:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:5656:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:5656:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:5657:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getLibrary_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getLibrary_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:5691:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:5692:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:5692:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:5693:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getLibrary_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_130); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:5713:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==89) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // PsiInternalEJSL.g:5714:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getLibrary_LanguagesKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,89,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_114); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:5728:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop146:
                    do {
                        int alt146=2;
                        int LA146_0 = input.LA(1);

                        if ( (LA146_0==124) ) {
                            alt146=1;
                        }


                        switch (alt146) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5729:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:5729:5: (lv_languages_10_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:5730:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getLibrary_LanguagesLanguageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_10_0=ruleLanguage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop146;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_131); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5751:3: (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==67) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // PsiInternalEJSL.g:5752:4: otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getLibrary_EntitiesKeyword_9_0ElementType());
                    			
                    otherlv_12=(Token)match(input,67,FOLLOW_3); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:5759:4: ( (otherlv_13= RULE_STRING ) )
                    // PsiInternalEJSL.g:5760:5: (otherlv_13= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:5760:5: (otherlv_13= RULE_STRING )
                    // PsiInternalEJSL.g:5761:6: otherlv_13= RULE_STRING
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getLibrary_EntitiesEntityCrossReference_9_1_0ElementType());
                    					
                    otherlv_13=(Token)match(input,RULE_STRING,FOLLOW_132); 

                    						doneLeaf(otherlv_13);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:5776:4: (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )*
                    loop148:
                    do {
                        int alt148=2;
                        int LA148_0 = input.LA(1);

                        if ( (LA148_0==21) ) {
                            alt148=1;
                        }


                        switch (alt148) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5777:5: otherlv_14= ',' ( (otherlv_15= RULE_STRING ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getLibrary_CommaKeyword_9_2_0ElementType());
                    	    				
                    	    otherlv_14=(Token)match(input,21,FOLLOW_3); 

                    	    					doneLeaf(otherlv_14);
                    	    				
                    	    // PsiInternalEJSL.g:5784:5: ( (otherlv_15= RULE_STRING ) )
                    	    // PsiInternalEJSL.g:5785:6: (otherlv_15= RULE_STRING )
                    	    {
                    	    // PsiInternalEJSL.g:5785:6: (otherlv_15= RULE_STRING )
                    	    // PsiInternalEJSL.g:5786:7: otherlv_15= RULE_STRING
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getLibrary_EntitiesEntityCrossReference_9_2_1_0ElementType());
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_STRING,FOLLOW_132); 

                    	    							doneLeaf(otherlv_15);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop148;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:5803:3: (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==102) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // PsiInternalEJSL.g:5804:4: otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}'
                    {

                    				markLeaf(elementTypeProvider.getLibrary_ClassesKeyword_10_0ElementType());
                    			
                    otherlv_16=(Token)match(input,102,FOLLOW_4); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_10_1ElementType());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_133); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:5818:4: ( (lv_classes_18_0= ruleClass ) )*
                    loop150:
                    do {
                        int alt150=2;
                        int LA150_0 = input.LA(1);

                        if ( (LA150_0==105) ) {
                            alt150=1;
                        }


                        switch (alt150) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5819:5: (lv_classes_18_0= ruleClass )
                    	    {
                    	    // PsiInternalEJSL.g:5819:5: (lv_classes_18_0= ruleClass )
                    	    // PsiInternalEJSL.g:5820:6: lv_classes_18_0= ruleClass
                    	    {

                    	    						markComposite(elementTypeProvider.getLibrary_ClassesClassParserRuleCall_10_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_133);
                    	    lv_classes_18_0=ruleClass();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop150;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_10_3ElementType());
                    			
                    otherlv_19=(Token)match(input,18,FOLLOW_134); 

                    				doneLeaf(otherlv_19);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5841:3: (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==103) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // PsiInternalEJSL.g:5842:4: otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}'
                    {

                    				markLeaf(elementTypeProvider.getLibrary_PackagesKeyword_11_0ElementType());
                    			
                    otherlv_20=(Token)match(input,103,FOLLOW_4); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_11_1ElementType());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_135); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:5856:4: ( (lv_packages_22_0= rulePackage ) )*
                    loop152:
                    do {
                        int alt152=2;
                        int LA152_0 = input.LA(1);

                        if ( (LA152_0==104) ) {
                            alt152=1;
                        }


                        switch (alt152) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5857:5: (lv_packages_22_0= rulePackage )
                    	    {
                    	    // PsiInternalEJSL.g:5857:5: (lv_packages_22_0= rulePackage )
                    	    // PsiInternalEJSL.g:5858:6: lv_packages_22_0= rulePackage
                    	    {

                    	    						markComposite(elementTypeProvider.getLibrary_PackagesPackageParserRuleCall_11_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_135);
                    	    lv_packages_22_0=rulePackage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop152;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_11_3ElementType());
                    			
                    otherlv_23=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_23);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_24=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_24);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLibrary"


    // $ANTLR start "entryRulePackage"
    // PsiInternalEJSL.g:5890:1: entryRulePackage returns [Boolean current=false] : iv_rulePackage= rulePackage EOF ;
    public final Boolean entryRulePackage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePackage = null;


        try {
            // PsiInternalEJSL.g:5890:49: (iv_rulePackage= rulePackage EOF )
            // PsiInternalEJSL.g:5891:2: iv_rulePackage= rulePackage EOF
            {
             markComposite(elementTypeProvider.getPackageElementType()); 
            pushFollow(FOLLOW_1);
            iv_rulePackage=rulePackage();

            state._fsp--;

             current =iv_rulePackage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePackage"


    // $ANTLR start "rulePackage"
    // PsiInternalEJSL.g:5897:1: rulePackage returns [Boolean current=false] : ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' ) ;
    public final Boolean rulePackage() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_packages_6_0 = null;

        Boolean lv_classes_10_0 = null;


        try {
            // PsiInternalEJSL.g:5898:1: ( ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' ) )
            // PsiInternalEJSL.g:5899:2: ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' )
            {
            // PsiInternalEJSL.g:5899:2: ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' )
            // PsiInternalEJSL.g:5900:3: () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}'
            {
            // PsiInternalEJSL.g:5900:3: ()
            // PsiInternalEJSL.g:5901:4: 
            {

            				precedeComposite(elementTypeProvider.getPackage_PackageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getPackage_PackageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,104,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:5914:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:5915:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:5915:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:5916:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getPackage_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getPackage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_136); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:5936:3: (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==103) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // PsiInternalEJSL.g:5937:4: otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}'
                    {

                    				markLeaf(elementTypeProvider.getPackage_PackagesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,103,FOLLOW_4); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getPackage_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_135); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:5951:4: ( (lv_packages_6_0= rulePackage ) )*
                    loop154:
                    do {
                        int alt154=2;
                        int LA154_0 = input.LA(1);

                        if ( (LA154_0==104) ) {
                            alt154=1;
                        }


                        switch (alt154) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5952:5: (lv_packages_6_0= rulePackage )
                    	    {
                    	    // PsiInternalEJSL.g:5952:5: (lv_packages_6_0= rulePackage )
                    	    // PsiInternalEJSL.g:5953:6: lv_packages_6_0= rulePackage
                    	    {

                    	    						markComposite(elementTypeProvider.getPackage_PackagesPackageParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_135);
                    	    lv_packages_6_0=rulePackage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop154;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPackage_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_7=(Token)match(input,18,FOLLOW_137); 

                    				doneLeaf(otherlv_7);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5974:3: (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==102) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // PsiInternalEJSL.g:5975:4: otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getPackage_ClassesKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,102,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getPackage_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_133); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:5989:4: ( (lv_classes_10_0= ruleClass ) )*
                    loop156:
                    do {
                        int alt156=2;
                        int LA156_0 = input.LA(1);

                        if ( (LA156_0==105) ) {
                            alt156=1;
                        }


                        switch (alt156) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5990:5: (lv_classes_10_0= ruleClass )
                    	    {
                    	    // PsiInternalEJSL.g:5990:5: (lv_classes_10_0= ruleClass )
                    	    // PsiInternalEJSL.g:5991:6: lv_classes_10_0= ruleClass
                    	    {

                    	    						markComposite(elementTypeProvider.getPackage_ClassesClassParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_133);
                    	    lv_classes_10_0=ruleClass();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop156;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPackage_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPackage_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_12=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_12);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePackage"


    // $ANTLR start "entryRuleClass"
    // PsiInternalEJSL.g:6023:1: entryRuleClass returns [Boolean current=false] : iv_ruleClass= ruleClass EOF ;
    public final Boolean entryRuleClass() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleClass = null;


        try {
            // PsiInternalEJSL.g:6023:47: (iv_ruleClass= ruleClass EOF )
            // PsiInternalEJSL.g:6024:2: iv_ruleClass= ruleClass EOF
            {
             markComposite(elementTypeProvider.getClassElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleClass=ruleClass();

            state._fsp--;

             current =iv_ruleClass; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClass"


    // $ANTLR start "ruleClass"
    // PsiInternalEJSL.g:6030:1: ruleClass returns [Boolean current=false] : ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
    public final Boolean ruleClass() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_methods_16_0 = null;


        try {
            // PsiInternalEJSL.g:6031:1: ( ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // PsiInternalEJSL.g:6032:2: ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // PsiInternalEJSL.g:6032:2: ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // PsiInternalEJSL.g:6033:3: () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            // PsiInternalEJSL.g:6033:3: ()
            // PsiInternalEJSL.g:6034:4: 
            {

            				precedeComposite(elementTypeProvider.getClass_ClassAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getClass_ClassKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,105,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6047:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:6048:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:6048:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:6049:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getClass_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_52);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:6062:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==46) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // PsiInternalEJSL.g:6063:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {

                    				markLeaf(elementTypeProvider.getClass_ExtendsKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,46,FOLLOW_51); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalEJSL.g:6070:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:6071:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:6071:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:6072:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getClass_SupertypeClassCrossReference_3_1_0ElementType());
                    					
                    pushFollow(FOLLOW_4);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getClass_LeftCurlyBracketKeyword_4ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_138); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:6095:3: (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==106) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // PsiInternalEJSL.g:6096:4: otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getClass_ClassReferencesKeyword_5_0ElementType());
                    			
                    otherlv_6=(Token)match(input,106,FOLLOW_51); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:6103:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:6104:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:6104:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:6105:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getClass_ReferencesClassCrossReference_5_1_0ElementType());
                    					
                    pushFollow(FOLLOW_139);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:6120:4: (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    loop159:
                    do {
                        int alt159=2;
                        int LA159_0 = input.LA(1);

                        if ( (LA159_0==21) ) {
                            alt159=1;
                        }


                        switch (alt159) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6121:5: otherlv_8= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getClass_CommaKeyword_5_2_0ElementType());
                    	    				
                    	    otherlv_8=(Token)match(input,21,FOLLOW_51); 

                    	    					doneLeaf(otherlv_8);
                    	    				
                    	    // PsiInternalEJSL.g:6128:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:6129:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:6129:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:6130:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getClass_ReferencesClassCrossReference_5_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_139);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop159;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:6147:3: (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==67) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // PsiInternalEJSL.g:6148:4: otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getClass_EntitiesKeyword_6_0ElementType());
                    			
                    otherlv_10=(Token)match(input,67,FOLLOW_3); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:6155:4: ( (otherlv_11= RULE_STRING ) )
                    // PsiInternalEJSL.g:6156:5: (otherlv_11= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6156:5: (otherlv_11= RULE_STRING )
                    // PsiInternalEJSL.g:6157:6: otherlv_11= RULE_STRING
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getClass_EntitiesEntityCrossReference_6_1_0ElementType());
                    					
                    otherlv_11=(Token)match(input,RULE_STRING,FOLLOW_140); 

                    						doneLeaf(otherlv_11);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:6172:4: (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )*
                    loop161:
                    do {
                        int alt161=2;
                        int LA161_0 = input.LA(1);

                        if ( (LA161_0==21) ) {
                            alt161=1;
                        }


                        switch (alt161) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6173:5: otherlv_12= ',' ( (otherlv_13= RULE_STRING ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getClass_CommaKeyword_6_2_0ElementType());
                    	    				
                    	    otherlv_12=(Token)match(input,21,FOLLOW_3); 

                    	    					doneLeaf(otherlv_12);
                    	    				
                    	    // PsiInternalEJSL.g:6180:5: ( (otherlv_13= RULE_STRING ) )
                    	    // PsiInternalEJSL.g:6181:6: (otherlv_13= RULE_STRING )
                    	    {
                    	    // PsiInternalEJSL.g:6181:6: (otherlv_13= RULE_STRING )
                    	    // PsiInternalEJSL.g:6182:7: otherlv_13= RULE_STRING
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getClass_EntitiesEntityCrossReference_6_2_1_0ElementType());
                    	    						
                    	    otherlv_13=(Token)match(input,RULE_STRING,FOLLOW_140); 

                    	    							doneLeaf(otherlv_13);
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop161;
                        }
                    } while (true);


                    }
                    break;

            }

            // PsiInternalEJSL.g:6199:3: (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )?
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==107) ) {
                alt164=1;
            }
            switch (alt164) {
                case 1 :
                    // PsiInternalEJSL.g:6200:4: otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}'
                    {

                    				markLeaf(elementTypeProvider.getClass_MethodsKeyword_7_0ElementType());
                    			
                    otherlv_14=(Token)match(input,107,FOLLOW_4); 

                    				doneLeaf(otherlv_14);
                    			

                    				markLeaf(elementTypeProvider.getClass_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_15=(Token)match(input,16,FOLLOW_141); 

                    				doneLeaf(otherlv_15);
                    			
                    // PsiInternalEJSL.g:6214:4: ( (lv_methods_16_0= ruleMethod ) )*
                    loop163:
                    do {
                        int alt163=2;
                        int LA163_0 = input.LA(1);

                        if ( (LA163_0==108) ) {
                            alt163=1;
                        }


                        switch (alt163) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6215:5: (lv_methods_16_0= ruleMethod )
                    	    {
                    	    // PsiInternalEJSL.g:6215:5: (lv_methods_16_0= ruleMethod )
                    	    // PsiInternalEJSL.g:6216:6: lv_methods_16_0= ruleMethod
                    	    {

                    	    						markComposite(elementTypeProvider.getClass_MethodsMethodParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_141);
                    	    lv_methods_16_0=ruleMethod();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop163;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getClass_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_17=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_17);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getClass_RightCurlyBracketKeyword_8ElementType());
            		
            otherlv_18=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_18);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClass"


    // $ANTLR start "entryRuleMethod"
    // PsiInternalEJSL.g:6248:1: entryRuleMethod returns [Boolean current=false] : iv_ruleMethod= ruleMethod EOF ;
    public final Boolean entryRuleMethod() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleMethod = null;


        try {
            // PsiInternalEJSL.g:6248:48: (iv_ruleMethod= ruleMethod EOF )
            // PsiInternalEJSL.g:6249:2: iv_ruleMethod= ruleMethod EOF
            {
             markComposite(elementTypeProvider.getMethodElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleMethod=ruleMethod();

            state._fsp--;

             current =iv_ruleMethod; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethod"


    // $ANTLR start "ruleMethod"
    // PsiInternalEJSL.g:6255:1: ruleMethod returns [Boolean current=false] : ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' ) ;
    public final Boolean ruleMethod() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_returnvalue_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_type_7_0 = null;

        Boolean lv_methodparameters_10_0 = null;


        try {
            // PsiInternalEJSL.g:6256:1: ( ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' ) )
            // PsiInternalEJSL.g:6257:2: ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' )
            {
            // PsiInternalEJSL.g:6257:2: ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' )
            // PsiInternalEJSL.g:6258:3: () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}'
            {
            // PsiInternalEJSL.g:6258:3: ()
            // PsiInternalEJSL.g:6259:4: 
            {

            				precedeComposite(elementTypeProvider.getMethod_MethodAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getMethod_MethodKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,108,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6272:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:6273:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:6273:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:6274:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getMethod_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getMethod_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_142); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:6294:3: (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )?
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==109) ) {
                alt165=1;
            }
            switch (alt165) {
                case 1 :
                    // PsiInternalEJSL.g:6295:4: otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) )
                    {

                    				markLeaf(elementTypeProvider.getMethod_ReturnvalueKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,109,FOLLOW_33); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:6302:4: ( (lv_returnvalue_5_0= RULE_ID ) )
                    // PsiInternalEJSL.g:6303:5: (lv_returnvalue_5_0= RULE_ID )
                    {
                    // PsiInternalEJSL.g:6303:5: (lv_returnvalue_5_0= RULE_ID )
                    // PsiInternalEJSL.g:6304:6: lv_returnvalue_5_0= RULE_ID
                    {

                    						markLeaf(elementTypeProvider.getMethod_ReturnvalueIDTerminalRuleCall_4_1_0ElementType());
                    					
                    lv_returnvalue_5_0=(Token)match(input,RULE_ID,FOLLOW_143); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_returnvalue_5_0);
                    					

                    }


                    }


                    				markLeaf(elementTypeProvider.getMethod_ColonKeyword_4_2ElementType());
                    			
                    otherlv_6=(Token)match(input,72,FOLLOW_35); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:6326:4: ( (lv_type_7_0= ruleType ) )
                    // PsiInternalEJSL.g:6327:5: (lv_type_7_0= ruleType )
                    {
                    // PsiInternalEJSL.g:6327:5: (lv_type_7_0= ruleType )
                    // PsiInternalEJSL.g:6328:6: lv_type_7_0= ruleType
                    {

                    						markComposite(elementTypeProvider.getMethod_TypeTypeParserRuleCall_4_3_0ElementType());
                    					
                    pushFollow(FOLLOW_144);
                    lv_type_7_0=ruleType();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6342:3: (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )?
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==110) ) {
                alt167=1;
            }
            switch (alt167) {
                case 1 :
                    // PsiInternalEJSL.g:6343:4: otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getMethod_MethodparametersKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,110,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getMethod_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_145); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:6357:4: ( (lv_methodparameters_10_0= ruleMethodParameter ) )*
                    loop166:
                    do {
                        int alt166=2;
                        int LA166_0 = input.LA(1);

                        if ( (LA166_0==111) ) {
                            alt166=1;
                        }


                        switch (alt166) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6358:5: (lv_methodparameters_10_0= ruleMethodParameter )
                    	    {
                    	    // PsiInternalEJSL.g:6358:5: (lv_methodparameters_10_0= ruleMethodParameter )
                    	    // PsiInternalEJSL.g:6359:6: lv_methodparameters_10_0= ruleMethodParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getMethod_MethodparametersMethodParameterParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_145);
                    	    lv_methodparameters_10_0=ruleMethodParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop166;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getMethod_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getMethod_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_12=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_12);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethod"


    // $ANTLR start "entryRuleMethodParameter"
    // PsiInternalEJSL.g:6391:1: entryRuleMethodParameter returns [Boolean current=false] : iv_ruleMethodParameter= ruleMethodParameter EOF ;
    public final Boolean entryRuleMethodParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleMethodParameter = null;


        try {
            // PsiInternalEJSL.g:6391:57: (iv_ruleMethodParameter= ruleMethodParameter EOF )
            // PsiInternalEJSL.g:6392:2: iv_ruleMethodParameter= ruleMethodParameter EOF
            {
             markComposite(elementTypeProvider.getMethodParameterElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleMethodParameter=ruleMethodParameter();

            state._fsp--;

             current =iv_ruleMethodParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethodParameter"


    // $ANTLR start "ruleMethodParameter"
    // PsiInternalEJSL.g:6398:1: ruleMethodParameter returns [Boolean current=false] : ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) ;
    public final Boolean ruleMethodParameter() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Boolean lv_type_4_0 = null;


        try {
            // PsiInternalEJSL.g:6399:1: ( ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) )
            // PsiInternalEJSL.g:6400:2: ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            {
            // PsiInternalEJSL.g:6400:2: ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            // PsiInternalEJSL.g:6401:3: () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) )
            {
            // PsiInternalEJSL.g:6401:3: ()
            // PsiInternalEJSL.g:6402:4: 
            {

            				precedeComposite(elementTypeProvider.getMethodParameter_MethodParameterAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getMethodParameter_MethodParameterKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,111,FOLLOW_33); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6415:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:6416:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:6416:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:6417:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getMethodParameter_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_143); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getMethodParameter_ColonKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,72,FOLLOW_35); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:6439:3: ( (lv_type_4_0= ruleType ) )
            // PsiInternalEJSL.g:6440:4: (lv_type_4_0= ruleType )
            {
            // PsiInternalEJSL.g:6440:4: (lv_type_4_0= ruleType )
            // PsiInternalEJSL.g:6441:5: lv_type_4_0= ruleType
            {

            					markComposite(elementTypeProvider.getMethodParameter_TypeTypeParserRuleCall_4_0ElementType());
            				
            pushFollow(FOLLOW_2);
            lv_type_4_0=ruleType();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethodParameter"


    // $ANTLR start "entryRuleTemplate"
    // PsiInternalEJSL.g:6458:1: entryRuleTemplate returns [Boolean current=false] : iv_ruleTemplate= ruleTemplate EOF ;
    public final Boolean entryRuleTemplate() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTemplate = null;


        try {
            // PsiInternalEJSL.g:6458:50: (iv_ruleTemplate= ruleTemplate EOF )
            // PsiInternalEJSL.g:6459:2: iv_ruleTemplate= ruleTemplate EOF
            {
             markComposite(elementTypeProvider.getTemplateElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleTemplate=ruleTemplate();

            state._fsp--;

             current =iv_ruleTemplate; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTemplate"


    // $ANTLR start "ruleTemplate"
    // PsiInternalEJSL.g:6465:1: ruleTemplate returns [Boolean current=false] : ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' ) ;
    public final Boolean ruleTemplate() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_manifest_6_0 = null;

        Boolean lv_languages_10_0 = null;

        Boolean lv_localparameters_14_0 = null;

        Boolean lv_positions_18_0 = null;

        Boolean lv_cssblocks_22_0 = null;


        try {
            // PsiInternalEJSL.g:6466:1: ( ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' ) )
            // PsiInternalEJSL.g:6467:2: ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' )
            {
            // PsiInternalEJSL.g:6467:2: ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' )
            // PsiInternalEJSL.g:6468:3: () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}'
            {
            // PsiInternalEJSL.g:6468:3: ()
            // PsiInternalEJSL.g:6469:4: 
            {

            				precedeComposite(elementTypeProvider.getTemplate_TemplateAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getTemplate_TemplateKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,112,FOLLOW_51); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6482:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:6483:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:6483:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:6484:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getTemplate_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getTemplate_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:6518:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:6519:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:6519:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:6520:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getTemplate_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_146); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:6540:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==89) ) {
                alt169=1;
            }
            switch (alt169) {
                case 1 :
                    // PsiInternalEJSL.g:6541:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getTemplate_LanguagesKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,89,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_114); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:6555:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop168:
                    do {
                        int alt168=2;
                        int LA168_0 = input.LA(1);

                        if ( (LA168_0==124) ) {
                            alt168=1;
                        }


                        switch (alt168) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6556:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:6556:5: (lv_languages_10_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:6557:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getTemplate_LanguagesLanguageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_10_0=ruleLanguage();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop168;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,18,FOLLOW_147); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:6578:3: (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )?
            int alt171=2;
            int LA171_0 = input.LA(1);

            if ( (LA171_0==100) ) {
                alt171=1;
            }
            switch (alt171) {
                case 1 :
                    // PsiInternalEJSL.g:6579:4: otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}'
                    {

                    				markLeaf(elementTypeProvider.getTemplate_ParametersKeyword_9_0ElementType());
                    			
                    otherlv_12=(Token)match(input,100,FOLLOW_4); 

                    				doneLeaf(otherlv_12);
                    			

                    				markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_9_1ElementType());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_12); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:6593:4: ( (lv_localparameters_14_0= ruleParameter ) )*
                    loop170:
                    do {
                        int alt170=2;
                        int LA170_0 = input.LA(1);

                        if ( (LA170_0==34) ) {
                            alt170=1;
                        }


                        switch (alt170) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6594:5: (lv_localparameters_14_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:6594:5: (lv_localparameters_14_0= ruleParameter )
                    	    // PsiInternalEJSL.g:6595:6: lv_localparameters_14_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getTemplate_LocalparametersParameterParserRuleCall_9_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_14_0=ruleParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop170;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_9_3ElementType());
                    			
                    otherlv_15=(Token)match(input,18,FOLLOW_148); 

                    				doneLeaf(otherlv_15);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:6616:3: (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )?
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==113) ) {
                alt173=1;
            }
            switch (alt173) {
                case 1 :
                    // PsiInternalEJSL.g:6617:4: otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}'
                    {

                    				markLeaf(elementTypeProvider.getTemplate_PositionsKeyword_10_0ElementType());
                    			
                    otherlv_16=(Token)match(input,113,FOLLOW_4); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_10_1ElementType());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_149); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:6631:4: ( (lv_positions_18_0= rulePosition ) )*
                    loop172:
                    do {
                        int alt172=2;
                        int LA172_0 = input.LA(1);

                        if ( (LA172_0==127) ) {
                            alt172=1;
                        }


                        switch (alt172) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6632:5: (lv_positions_18_0= rulePosition )
                    	    {
                    	    // PsiInternalEJSL.g:6632:5: (lv_positions_18_0= rulePosition )
                    	    // PsiInternalEJSL.g:6633:6: lv_positions_18_0= rulePosition
                    	    {

                    	    						markComposite(elementTypeProvider.getTemplate_PositionsPositionParserRuleCall_10_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_149);
                    	    lv_positions_18_0=rulePosition();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop172;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_10_3ElementType());
                    			
                    otherlv_19=(Token)match(input,18,FOLLOW_150); 

                    				doneLeaf(otherlv_19);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:6654:3: (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )?
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==114) ) {
                alt175=1;
            }
            switch (alt175) {
                case 1 :
                    // PsiInternalEJSL.g:6655:4: otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}'
                    {

                    				markLeaf(elementTypeProvider.getTemplate_CssblocksKeyword_11_0ElementType());
                    			
                    otherlv_20=(Token)match(input,114,FOLLOW_4); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_11_1ElementType());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_151); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:6669:4: ( (lv_cssblocks_22_0= ruleCssBlock ) )*
                    loop174:
                    do {
                        int alt174=2;
                        int LA174_0 = input.LA(1);

                        if ( (LA174_0==133) ) {
                            alt174=1;
                        }


                        switch (alt174) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6670:5: (lv_cssblocks_22_0= ruleCssBlock )
                    	    {
                    	    // PsiInternalEJSL.g:6670:5: (lv_cssblocks_22_0= ruleCssBlock )
                    	    // PsiInternalEJSL.g:6671:6: lv_cssblocks_22_0= ruleCssBlock
                    	    {

                    	    						markComposite(elementTypeProvider.getTemplate_CssblocksCssBlockParserRuleCall_11_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_151);
                    	    lv_cssblocks_22_0=ruleCssBlock();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop174;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_11_3ElementType());
                    			
                    otherlv_23=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_23);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_24=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_24);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTemplate"


    // $ANTLR start "entryRuleManifestation"
    // PsiInternalEJSL.g:6703:1: entryRuleManifestation returns [Boolean current=false] : iv_ruleManifestation= ruleManifestation EOF ;
    public final Boolean entryRuleManifestation() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleManifestation = null;


        try {
            // PsiInternalEJSL.g:6703:55: (iv_ruleManifestation= ruleManifestation EOF )
            // PsiInternalEJSL.g:6704:2: iv_ruleManifestation= ruleManifestation EOF
            {
             markComposite(elementTypeProvider.getManifestationElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleManifestation=ruleManifestation();

            state._fsp--;

             current =iv_ruleManifestation; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleManifestation"


    // $ANTLR start "ruleManifestation"
    // PsiInternalEJSL.g:6710:1: ruleManifestation returns [Boolean current=false] : ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )? ) ;
    public final Boolean ruleManifestation() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_creationdate_6_0=null;
        Token otherlv_7=null;
        Token lv_copyright_8_0=null;
        Token otherlv_9=null;
        Token lv_license_10_0=null;
        Token otherlv_11=null;
        Token lv_link_12_0=null;
        Token otherlv_13=null;
        Token lv_version_14_0=null;
        Token otherlv_15=null;
        Token lv_description_16_0=null;
        Boolean lv_authors_3_0 = null;


        try {
            // PsiInternalEJSL.g:6711:1: ( ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )? ) )
            // PsiInternalEJSL.g:6712:2: ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )? )
            {
            // PsiInternalEJSL.g:6712:2: ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )? )
            // PsiInternalEJSL.g:6713:3: () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )?
            {
            // PsiInternalEJSL.g:6713:3: ()
            // PsiInternalEJSL.g:6714:4: 
            {

            				precedeComposite(elementTypeProvider.getManifestation_ManifestationAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getManifestation_AuthorsKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,115,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getManifestation_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_152); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:6734:3: ( (lv_authors_3_0= ruleAuthor ) )+
            int cnt176=0;
            loop176:
            do {
                int alt176=2;
                int LA176_0 = input.LA(1);

                if ( (LA176_0==121) ) {
                    alt176=1;
                }


                switch (alt176) {
            	case 1 :
            	    // PsiInternalEJSL.g:6735:4: (lv_authors_3_0= ruleAuthor )
            	    {
            	    // PsiInternalEJSL.g:6735:4: (lv_authors_3_0= ruleAuthor )
            	    // PsiInternalEJSL.g:6736:5: lv_authors_3_0= ruleAuthor
            	    {

            	    					markComposite(elementTypeProvider.getManifestation_AuthorsAuthorParserRuleCall_3_0ElementType());
            	    				
            	    pushFollow(FOLLOW_153);
            	    lv_authors_3_0=ruleAuthor();

            	    state._fsp--;


            	    					doneComposite();
            	    					if(!current) {
            	    						associateWithSemanticElement();
            	    						current = true;
            	    					}
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt176 >= 1 ) break loop176;
                        EarlyExitException eee =
                            new EarlyExitException(176, input);
                        throw eee;
                }
                cnt176++;
            } while (true);


            			markLeaf(elementTypeProvider.getManifestation_RightCurlyBracketKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,18,FOLLOW_154); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:6756:3: (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )?
            int alt177=2;
            int LA177_0 = input.LA(1);

            if ( (LA177_0==116) ) {
                alt177=1;
            }
            switch (alt177) {
                case 1 :
                    // PsiInternalEJSL.g:6757:4: otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_CreationdateKeyword_5_0ElementType());
                    			
                    otherlv_5=(Token)match(input,116,FOLLOW_155); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:6764:4: ( (lv_creationdate_6_0= RULE_DATE ) )
                    // PsiInternalEJSL.g:6765:5: (lv_creationdate_6_0= RULE_DATE )
                    {
                    // PsiInternalEJSL.g:6765:5: (lv_creationdate_6_0= RULE_DATE )
                    // PsiInternalEJSL.g:6766:6: lv_creationdate_6_0= RULE_DATE
                    {

                    						markLeaf(elementTypeProvider.getManifestation_CreationdateDATETerminalRuleCall_5_1_0ElementType());
                    					
                    lv_creationdate_6_0=(Token)match(input,RULE_DATE,FOLLOW_156); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_creationdate_6_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6782:3: (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )?
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==117) ) {
                alt178=1;
            }
            switch (alt178) {
                case 1 :
                    // PsiInternalEJSL.g:6783:4: otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_CopyrightKeyword_6_0ElementType());
                    			
                    otherlv_7=(Token)match(input,117,FOLLOW_3); 

                    				doneLeaf(otherlv_7);
                    			
                    // PsiInternalEJSL.g:6790:4: ( (lv_copyright_8_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6791:5: (lv_copyright_8_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6791:5: (lv_copyright_8_0= RULE_STRING )
                    // PsiInternalEJSL.g:6792:6: lv_copyright_8_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_CopyrightSTRINGTerminalRuleCall_6_1_0ElementType());
                    					
                    lv_copyright_8_0=(Token)match(input,RULE_STRING,FOLLOW_157); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_copyright_8_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6808:3: (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )?
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==118) ) {
                alt179=1;
            }
            switch (alt179) {
                case 1 :
                    // PsiInternalEJSL.g:6809:4: otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_LicenseKeyword_7_0ElementType());
                    			
                    otherlv_9=(Token)match(input,118,FOLLOW_3); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:6816:4: ( (lv_license_10_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6817:5: (lv_license_10_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6817:5: (lv_license_10_0= RULE_STRING )
                    // PsiInternalEJSL.g:6818:6: lv_license_10_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_LicenseSTRINGTerminalRuleCall_7_1_0ElementType());
                    					
                    lv_license_10_0=(Token)match(input,RULE_STRING,FOLLOW_158); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_license_10_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6834:3: (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )?
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==119) ) {
                alt180=1;
            }
            switch (alt180) {
                case 1 :
                    // PsiInternalEJSL.g:6835:4: otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_LinkKeyword_8_0ElementType());
                    			
                    otherlv_11=(Token)match(input,119,FOLLOW_3); 

                    				doneLeaf(otherlv_11);
                    			
                    // PsiInternalEJSL.g:6842:4: ( (lv_link_12_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6843:5: (lv_link_12_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6843:5: (lv_link_12_0= RULE_STRING )
                    // PsiInternalEJSL.g:6844:6: lv_link_12_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_LinkSTRINGTerminalRuleCall_8_1_0ElementType());
                    					
                    lv_link_12_0=(Token)match(input,RULE_STRING,FOLLOW_159); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_link_12_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6860:3: (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )?
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==120) ) {
                alt181=1;
            }
            switch (alt181) {
                case 1 :
                    // PsiInternalEJSL.g:6861:4: otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_VersionKeyword_9_0ElementType());
                    			
                    otherlv_13=(Token)match(input,120,FOLLOW_3); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:6868:4: ( (lv_version_14_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6869:5: (lv_version_14_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6869:5: (lv_version_14_0= RULE_STRING )
                    // PsiInternalEJSL.g:6870:6: lv_version_14_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_VersionSTRINGTerminalRuleCall_9_1_0ElementType());
                    					
                    lv_version_14_0=(Token)match(input,RULE_STRING,FOLLOW_160); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_version_14_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6886:3: (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==39) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // PsiInternalEJSL.g:6887:4: otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_DescriptionKeyword_10_0ElementType());
                    			
                    otherlv_15=(Token)match(input,39,FOLLOW_3); 

                    				doneLeaf(otherlv_15);
                    			
                    // PsiInternalEJSL.g:6894:4: ( (lv_description_16_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6895:5: (lv_description_16_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6895:5: (lv_description_16_0= RULE_STRING )
                    // PsiInternalEJSL.g:6896:6: lv_description_16_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_DescriptionSTRINGTerminalRuleCall_10_1_0ElementType());
                    					
                    lv_description_16_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_description_16_0);
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleManifestation"


    // $ANTLR start "entryRuleAuthor"
    // PsiInternalEJSL.g:6916:1: entryRuleAuthor returns [Boolean current=false] : iv_ruleAuthor= ruleAuthor EOF ;
    public final Boolean entryRuleAuthor() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAuthor = null;


        try {
            // PsiInternalEJSL.g:6916:48: (iv_ruleAuthor= ruleAuthor EOF )
            // PsiInternalEJSL.g:6917:2: iv_ruleAuthor= ruleAuthor EOF
            {
             markComposite(elementTypeProvider.getAuthorElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleAuthor=ruleAuthor();

            state._fsp--;

             current =iv_ruleAuthor; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAuthor"


    // $ANTLR start "ruleAuthor"
    // PsiInternalEJSL.g:6923:1: ruleAuthor returns [Boolean current=false] : ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}' ) ;
    public final Boolean ruleAuthor() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_authoremail_5_0=null;
        Token otherlv_6=null;
        Token lv_authorurl_7_0=null;
        Token otherlv_8=null;

        try {
            // PsiInternalEJSL.g:6924:1: ( ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}' ) )
            // PsiInternalEJSL.g:6925:2: ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}' )
            {
            // PsiInternalEJSL.g:6925:2: ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}' )
            // PsiInternalEJSL.g:6926:3: () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}'
            {
            // PsiInternalEJSL.g:6926:3: ()
            // PsiInternalEJSL.g:6927:4: 
            {

            				precedeComposite(elementTypeProvider.getAuthor_AuthorAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getAuthor_AuthorKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,121,FOLLOW_3); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6940:3: ( (lv_name_2_0= RULE_STRING ) )
            // PsiInternalEJSL.g:6941:4: (lv_name_2_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:6941:4: (lv_name_2_0= RULE_STRING )
            // PsiInternalEJSL.g:6942:5: lv_name_2_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getAuthor_NameSTRINGTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getAuthor_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_161); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:6964:3: (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )?
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==122) ) {
                alt183=1;
            }
            switch (alt183) {
                case 1 :
                    // PsiInternalEJSL.g:6965:4: otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getAuthor_AuthoremailKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,122,FOLLOW_3); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:6972:4: ( (lv_authoremail_5_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6973:5: (lv_authoremail_5_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6973:5: (lv_authoremail_5_0= RULE_STRING )
                    // PsiInternalEJSL.g:6974:6: lv_authoremail_5_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getAuthor_AuthoremailSTRINGTerminalRuleCall_4_1_0ElementType());
                    					
                    lv_authoremail_5_0=(Token)match(input,RULE_STRING,FOLLOW_162); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_authoremail_5_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6990:3: (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==123) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // PsiInternalEJSL.g:6991:4: otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getAuthor_AuthorurlKeyword_5_0ElementType());
                    			
                    otherlv_6=(Token)match(input,123,FOLLOW_3); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:6998:4: ( (lv_authorurl_7_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6999:5: (lv_authorurl_7_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6999:5: (lv_authorurl_7_0= RULE_STRING )
                    // PsiInternalEJSL.g:7000:6: lv_authorurl_7_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getAuthor_AuthorurlSTRINGTerminalRuleCall_5_1_0ElementType());
                    					
                    lv_authorurl_7_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_authorurl_7_0);
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getAuthor_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_8=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_8);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAuthor"


    // $ANTLR start "entryRuleLanguage"
    // PsiInternalEJSL.g:7027:1: entryRuleLanguage returns [Boolean current=false] : iv_ruleLanguage= ruleLanguage EOF ;
    public final Boolean entryRuleLanguage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLanguage = null;


        try {
            // PsiInternalEJSL.g:7027:50: (iv_ruleLanguage= ruleLanguage EOF )
            // PsiInternalEJSL.g:7028:2: iv_ruleLanguage= ruleLanguage EOF
            {
             markComposite(elementTypeProvider.getLanguageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleLanguage=ruleLanguage();

            state._fsp--;

             current =iv_ruleLanguage; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLanguage"


    // $ANTLR start "ruleLanguage"
    // PsiInternalEJSL.g:7034:1: ruleLanguage returns [Boolean current=false] : ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' ) ;
    public final Boolean ruleLanguage() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Boolean lv_keyvaluepairs_6_0 = null;


        try {
            // PsiInternalEJSL.g:7035:1: ( ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' ) )
            // PsiInternalEJSL.g:7036:2: ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' )
            {
            // PsiInternalEJSL.g:7036:2: ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' )
            // PsiInternalEJSL.g:7037:3: () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}'
            {
            // PsiInternalEJSL.g:7037:3: ()
            // PsiInternalEJSL.g:7038:4: 
            {

            				precedeComposite(elementTypeProvider.getLanguage_LanguageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getLanguage_LanguageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,124,FOLLOW_163); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:7051:3: ( (lv_name_2_0= RULE_LANGUAGE_CODE ) )
            // PsiInternalEJSL.g:7052:4: (lv_name_2_0= RULE_LANGUAGE_CODE )
            {
            // PsiInternalEJSL.g:7052:4: (lv_name_2_0= RULE_LANGUAGE_CODE )
            // PsiInternalEJSL.g:7053:5: lv_name_2_0= RULE_LANGUAGE_CODE
            {

            					markLeaf(elementTypeProvider.getLanguage_NameLANGUAGE_CODETerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_LANGUAGE_CODE,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getLanguage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_164); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:7075:3: (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )?
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==125) ) {
                alt186=1;
            }
            switch (alt186) {
                case 1 :
                    // PsiInternalEJSL.g:7076:4: otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}'
                    {

                    				markLeaf(elementTypeProvider.getLanguage_KeyvaluepairsKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,125,FOLLOW_4); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getLanguage_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_165); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:7090:4: ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )*
                    loop185:
                    do {
                        int alt185=2;
                        int LA185_0 = input.LA(1);

                        if ( (LA185_0==126) ) {
                            alt185=1;
                        }


                        switch (alt185) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:7091:5: (lv_keyvaluepairs_6_0= ruleKeyValuePair )
                    	    {
                    	    // PsiInternalEJSL.g:7091:5: (lv_keyvaluepairs_6_0= ruleKeyValuePair )
                    	    // PsiInternalEJSL.g:7092:6: lv_keyvaluepairs_6_0= ruleKeyValuePair
                    	    {

                    	    						markComposite(elementTypeProvider.getLanguage_KeyvaluepairsKeyValuePairParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_165);
                    	    lv_keyvaluepairs_6_0=ruleKeyValuePair();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop185;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getLanguage_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_7=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_7);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getLanguage_RightCurlyBracketKeyword_5ElementType());
            		
            otherlv_8=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_8);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLanguage"


    // $ANTLR start "entryRuleKeyValuePair"
    // PsiInternalEJSL.g:7124:1: entryRuleKeyValuePair returns [Boolean current=false] : iv_ruleKeyValuePair= ruleKeyValuePair EOF ;
    public final Boolean entryRuleKeyValuePair() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleKeyValuePair = null;


        try {
            // PsiInternalEJSL.g:7124:54: (iv_ruleKeyValuePair= ruleKeyValuePair EOF )
            // PsiInternalEJSL.g:7125:2: iv_ruleKeyValuePair= ruleKeyValuePair EOF
            {
             markComposite(elementTypeProvider.getKeyValuePairElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleKeyValuePair=ruleKeyValuePair();

            state._fsp--;

             current =iv_ruleKeyValuePair; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKeyValuePair"


    // $ANTLR start "ruleKeyValuePair"
    // PsiInternalEJSL.g:7131:1: ruleKeyValuePair returns [Boolean current=false] : ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) ) ;
    public final Boolean ruleKeyValuePair() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_value_4_0=null;

        try {
            // PsiInternalEJSL.g:7132:1: ( ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) ) )
            // PsiInternalEJSL.g:7133:2: ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) )
            {
            // PsiInternalEJSL.g:7133:2: ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) )
            // PsiInternalEJSL.g:7134:3: () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) )
            {
            // PsiInternalEJSL.g:7134:3: ()
            // PsiInternalEJSL.g:7135:4: 
            {

            				precedeComposite(elementTypeProvider.getKeyValuePair_KeyValuePairAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getKeyValuePair_KeyKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,126,FOLLOW_33); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:7148:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:7149:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:7149:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:7150:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getKeyValuePair_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_103); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getKeyValuePair_EqualsSignKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,82,FOLLOW_3); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:7172:3: ( (lv_value_4_0= RULE_STRING ) )
            // PsiInternalEJSL.g:7173:4: (lv_value_4_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:7173:4: (lv_value_4_0= RULE_STRING )
            // PsiInternalEJSL.g:7174:5: lv_value_4_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getKeyValuePair_ValueSTRINGTerminalRuleCall_4_0ElementType());
            				
            lv_value_4_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_value_4_0);
            				

            }


            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKeyValuePair"


    // $ANTLR start "entryRulePosition"
    // PsiInternalEJSL.g:7193:1: entryRulePosition returns [Boolean current=false] : iv_rulePosition= rulePosition EOF ;
    public final Boolean entryRulePosition() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePosition = null;


        try {
            // PsiInternalEJSL.g:7193:50: (iv_rulePosition= rulePosition EOF )
            // PsiInternalEJSL.g:7194:2: iv_rulePosition= rulePosition EOF
            {
             markComposite(elementTypeProvider.getPositionElementType()); 
            pushFollow(FOLLOW_1);
            iv_rulePosition=rulePosition();

            state._fsp--;

             current =iv_rulePosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePosition"


    // $ANTLR start "rulePosition"
    // PsiInternalEJSL.g:7200:1: rulePosition returns [Boolean current=false] : ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' ) ;
    public final Boolean rulePosition() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Boolean lv_positionparameters_7_0 = null;


        try {
            // PsiInternalEJSL.g:7201:1: ( ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' ) )
            // PsiInternalEJSL.g:7202:2: ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' )
            {
            // PsiInternalEJSL.g:7202:2: ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' )
            // PsiInternalEJSL.g:7203:3: () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}'
            {
            // PsiInternalEJSL.g:7203:3: ()
            // PsiInternalEJSL.g:7204:4: 
            {

            				precedeComposite(elementTypeProvider.getPosition_PositionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getPosition_TemplatepositionKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,127,FOLLOW_166); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:7217:3: ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) )
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==RULE_POSITION_TYPES) ) {
                alt187=1;
            }
            else if ( (LA187_0==RULE_ID) ) {
                alt187=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 187, 0, input);

                throw nvae;
            }
            switch (alt187) {
                case 1 :
                    // PsiInternalEJSL.g:7218:4: ( (lv_name_2_0= RULE_POSITION_TYPES ) )
                    {
                    // PsiInternalEJSL.g:7218:4: ( (lv_name_2_0= RULE_POSITION_TYPES ) )
                    // PsiInternalEJSL.g:7219:5: (lv_name_2_0= RULE_POSITION_TYPES )
                    {
                    // PsiInternalEJSL.g:7219:5: (lv_name_2_0= RULE_POSITION_TYPES )
                    // PsiInternalEJSL.g:7220:6: lv_name_2_0= RULE_POSITION_TYPES
                    {

                    						markLeaf(elementTypeProvider.getPosition_NamePOSITION_TYPESTerminalRuleCall_2_0_0ElementType());
                    					
                    lv_name_2_0=(Token)match(input,RULE_POSITION_TYPES,FOLLOW_4); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_name_2_0);
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7236:4: ( (lv_name_3_0= RULE_ID ) )
                    {
                    // PsiInternalEJSL.g:7236:4: ( (lv_name_3_0= RULE_ID ) )
                    // PsiInternalEJSL.g:7237:5: (lv_name_3_0= RULE_ID )
                    {
                    // PsiInternalEJSL.g:7237:5: (lv_name_3_0= RULE_ID )
                    // PsiInternalEJSL.g:7238:6: lv_name_3_0= RULE_ID
                    {

                    						markLeaf(elementTypeProvider.getPosition_NameIDTerminalRuleCall_2_1_0ElementType());
                    					
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_4); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_name_3_0);
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPosition_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_167); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:7261:3: (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )?
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==128) ) {
                alt189=1;
            }
            switch (alt189) {
                case 1 :
                    // PsiInternalEJSL.g:7262:4: otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}'
                    {

                    				markLeaf(elementTypeProvider.getPosition_PositionparametersKeyword_4_0ElementType());
                    			
                    otherlv_5=(Token)match(input,128,FOLLOW_4); 

                    				doneLeaf(otherlv_5);
                    			

                    				markLeaf(elementTypeProvider.getPosition_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_6=(Token)match(input,16,FOLLOW_168); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:7276:4: ( (lv_positionparameters_7_0= rulePositionParameter ) )*
                    loop188:
                    do {
                        int alt188=2;
                        int LA188_0 = input.LA(1);

                        if ( (LA188_0==129) ) {
                            alt188=1;
                        }


                        switch (alt188) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:7277:5: (lv_positionparameters_7_0= rulePositionParameter )
                    	    {
                    	    // PsiInternalEJSL.g:7277:5: (lv_positionparameters_7_0= rulePositionParameter )
                    	    // PsiInternalEJSL.g:7278:6: lv_positionparameters_7_0= rulePositionParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getPosition_PositionparametersPositionParameterParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_168);
                    	    lv_positionparameters_7_0=rulePositionParameter();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop188;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPosition_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_8=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_8);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPosition_RightCurlyBracketKeyword_5ElementType());
            		
            otherlv_9=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_9);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePosition"


    // $ANTLR start "entryRulePositionParameter"
    // PsiInternalEJSL.g:7310:1: entryRulePositionParameter returns [Boolean current=false] : iv_rulePositionParameter= rulePositionParameter EOF ;
    public final Boolean entryRulePositionParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePositionParameter = null;


        try {
            // PsiInternalEJSL.g:7310:59: (iv_rulePositionParameter= rulePositionParameter EOF )
            // PsiInternalEJSL.g:7311:2: iv_rulePositionParameter= rulePositionParameter EOF
            {
             markComposite(elementTypeProvider.getPositionParameterElementType()); 
            pushFollow(FOLLOW_1);
            iv_rulePositionParameter=rulePositionParameter();

            state._fsp--;

             current =iv_rulePositionParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePositionParameter"


    // $ANTLR start "rulePositionParameter"
    // PsiInternalEJSL.g:7317:1: rulePositionParameter returns [Boolean current=false] : (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}' ) ;
    public final Boolean rulePositionParameter() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_divid_4_0=null;
        Token otherlv_5=null;
        Token lv_type_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Boolean lv_keyvaluepairs_9_0 = null;


        try {
            // PsiInternalEJSL.g:7318:1: ( (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}' ) )
            // PsiInternalEJSL.g:7319:2: (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}' )
            {
            // PsiInternalEJSL.g:7319:2: (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}' )
            // PsiInternalEJSL.g:7320:3: otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}'
            {

            			markLeaf(elementTypeProvider.getPositionParameter_PositionParameterKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,129,FOLLOW_33); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalEJSL.g:7327:3: ( (lv_name_1_0= RULE_ID ) )
            // PsiInternalEJSL.g:7328:4: (lv_name_1_0= RULE_ID )
            {
            // PsiInternalEJSL.g:7328:4: (lv_name_1_0= RULE_ID )
            // PsiInternalEJSL.g:7329:5: lv_name_1_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getPositionParameter_NameIDTerminalRuleCall_1_0ElementType());
            				
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_1_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getPositionParameter_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_169); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:7351:3: (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==130) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // PsiInternalEJSL.g:7352:4: otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) )
                    {

                    				markLeaf(elementTypeProvider.getPositionParameter_DivIdKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,130,FOLLOW_33); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalEJSL.g:7359:4: ( (lv_divid_4_0= RULE_ID ) )
                    // PsiInternalEJSL.g:7360:5: (lv_divid_4_0= RULE_ID )
                    {
                    // PsiInternalEJSL.g:7360:5: (lv_divid_4_0= RULE_ID )
                    // PsiInternalEJSL.g:7361:6: lv_divid_4_0= RULE_ID
                    {

                    						markLeaf(elementTypeProvider.getPositionParameter_DividIDTerminalRuleCall_3_1_0ElementType());
                    					
                    lv_divid_4_0=(Token)match(input,RULE_ID,FOLLOW_170); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_divid_4_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:7377:3: (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==131) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // PsiInternalEJSL.g:7378:4: otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) )
                    {

                    				markLeaf(elementTypeProvider.getPositionParameter_PositiontypeKeyword_4_0ElementType());
                    			
                    otherlv_5=(Token)match(input,131,FOLLOW_171); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:7385:4: ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) )
                    // PsiInternalEJSL.g:7386:5: (lv_type_6_0= RULE_POSITION_TYPES_NAMES )
                    {
                    // PsiInternalEJSL.g:7386:5: (lv_type_6_0= RULE_POSITION_TYPES_NAMES )
                    // PsiInternalEJSL.g:7387:6: lv_type_6_0= RULE_POSITION_TYPES_NAMES
                    {

                    						markLeaf(elementTypeProvider.getPositionParameter_TypePOSITION_TYPES_NAMESTerminalRuleCall_4_1_0ElementType());
                    					
                    lv_type_6_0=(Token)match(input,RULE_POSITION_TYPES_NAMES,FOLLOW_172); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_type_6_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:7403:3: (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )?
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==132) ) {
                alt193=1;
            }
            switch (alt193) {
                case 1 :
                    // PsiInternalEJSL.g:7404:4: otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}'
                    {

                    				markLeaf(elementTypeProvider.getPositionParameter_CSSKeyvaluepairsKeyword_5_0ElementType());
                    			
                    otherlv_7=(Token)match(input,132,FOLLOW_4); 

                    				doneLeaf(otherlv_7);
                    			

                    				markLeaf(elementTypeProvider.getPositionParameter_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_8=(Token)match(input,16,FOLLOW_165); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:7418:4: ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )*
                    loop192:
                    do {
                        int alt192=2;
                        int LA192_0 = input.LA(1);

                        if ( (LA192_0==126) ) {
                            alt192=1;
                        }


                        switch (alt192) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:7419:5: (lv_keyvaluepairs_9_0= ruleKeyValuePair )
                    	    {
                    	    // PsiInternalEJSL.g:7419:5: (lv_keyvaluepairs_9_0= ruleKeyValuePair )
                    	    // PsiInternalEJSL.g:7420:6: lv_keyvaluepairs_9_0= ruleKeyValuePair
                    	    {

                    	    						markComposite(elementTypeProvider.getPositionParameter_KeyvaluepairsKeyValuePairParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_165);
                    	    lv_keyvaluepairs_9_0=ruleKeyValuePair();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop192;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPositionParameter_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_10=(Token)match(input,18,FOLLOW_7); 

                    				doneLeaf(otherlv_10);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPositionParameter_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_11=(Token)match(input,18,FOLLOW_2); 

            			doneLeaf(otherlv_11);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePositionParameter"


    // $ANTLR start "entryRuleCssBlock"
    // PsiInternalEJSL.g:7452:1: entryRuleCssBlock returns [Boolean current=false] : iv_ruleCssBlock= ruleCssBlock EOF ;
    public final Boolean entryRuleCssBlock() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleCssBlock = null;


        try {
            // PsiInternalEJSL.g:7452:50: (iv_ruleCssBlock= ruleCssBlock EOF )
            // PsiInternalEJSL.g:7453:2: iv_ruleCssBlock= ruleCssBlock EOF
            {
             markComposite(elementTypeProvider.getCssBlockElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleCssBlock=ruleCssBlock();

            state._fsp--;

             current =iv_ruleCssBlock; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCssBlock"


    // $ANTLR start "ruleCssBlock"
    // PsiInternalEJSL.g:7459:1: ruleCssBlock returns [Boolean current=false] : (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' ) ;
    public final Boolean ruleCssBlock() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token lv_selector_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Boolean lv_keyvaluepairs_5_0 = null;


        try {
            // PsiInternalEJSL.g:7460:1: ( (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' ) )
            // PsiInternalEJSL.g:7461:2: (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' )
            {
            // PsiInternalEJSL.g:7461:2: (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' )
            // PsiInternalEJSL.g:7462:3: otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')'
            {

            			markLeaf(elementTypeProvider.getCssBlock_CssBlockKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,133,FOLLOW_3); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalEJSL.g:7469:3: ( (lv_selector_1_0= RULE_STRING ) )
            // PsiInternalEJSL.g:7470:4: (lv_selector_1_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:7470:4: (lv_selector_1_0= RULE_STRING )
            // PsiInternalEJSL.g:7471:5: lv_selector_1_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getCssBlock_SelectorSTRINGTerminalRuleCall_1_0ElementType());
            				
            lv_selector_1_0=(Token)match(input,RULE_STRING,FOLLOW_96); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_selector_1_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getCssBlock_LeftParenthesisKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,75,FOLLOW_173); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:7493:3: (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )?
            int alt195=2;
            int LA195_0 = input.LA(1);

            if ( (LA195_0==125) ) {
                alt195=1;
            }
            switch (alt195) {
                case 1 :
                    // PsiInternalEJSL.g:7494:4: otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}'
                    {

                    				markLeaf(elementTypeProvider.getCssBlock_KeyvaluepairsKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,125,FOLLOW_4); 

                    				doneLeaf(otherlv_3);
                    			

                    				markLeaf(elementTypeProvider.getCssBlock_LeftCurlyBracketKeyword_3_1ElementType());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_165); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:7508:4: ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )*
                    loop194:
                    do {
                        int alt194=2;
                        int LA194_0 = input.LA(1);

                        if ( (LA194_0==126) ) {
                            alt194=1;
                        }


                        switch (alt194) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:7509:5: (lv_keyvaluepairs_5_0= ruleKeyValuePair )
                    	    {
                    	    // PsiInternalEJSL.g:7509:5: (lv_keyvaluepairs_5_0= ruleKeyValuePair )
                    	    // PsiInternalEJSL.g:7510:6: lv_keyvaluepairs_5_0= ruleKeyValuePair
                    	    {

                    	    						markComposite(elementTypeProvider.getCssBlock_KeyvaluepairsKeyValuePairParserRuleCall_3_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_165);
                    	    lv_keyvaluepairs_5_0=ruleKeyValuePair();

                    	    state._fsp--;


                    	    						doneComposite();
                    	    						if(!current) {
                    	    							associateWithSemanticElement();
                    	    							current = true;
                    	    						}
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop194;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCssBlock_RightCurlyBracketKeyword_3_3ElementType());
                    			
                    otherlv_6=(Token)match(input,18,FOLLOW_174); 

                    				doneLeaf(otherlv_6);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getCssBlock_RightParenthesisKeyword_4ElementType());
            		
            otherlv_7=(Token)match(input,76,FOLLOW_2); 

            			doneLeaf(otherlv_7);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCssBlock"


    // $ANTLR start "entryRuleNUMBER"
    // PsiInternalEJSL.g:7542:1: entryRuleNUMBER returns [Boolean current=false] : iv_ruleNUMBER= ruleNUMBER EOF ;
    public final Boolean entryRuleNUMBER() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleNUMBER = null;


        try {
            // PsiInternalEJSL.g:7542:48: (iv_ruleNUMBER= ruleNUMBER EOF )
            // PsiInternalEJSL.g:7543:2: iv_ruleNUMBER= ruleNUMBER EOF
            {
             markComposite(elementTypeProvider.getNUMBERElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleNUMBER=ruleNUMBER();

            state._fsp--;

             current =iv_ruleNUMBER; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNUMBER"


    // $ANTLR start "ruleNUMBER"
    // PsiInternalEJSL.g:7549:1: ruleNUMBER returns [Boolean current=false] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final Boolean ruleNUMBER() throws RecognitionException {
        Boolean current = false;

        Token kw=null;
        Token this_INT_1=null;

        try {
            // PsiInternalEJSL.g:7550:1: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // PsiInternalEJSL.g:7551:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // PsiInternalEJSL.g:7551:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            // PsiInternalEJSL.g:7552:3: (kw= '-' )? this_INT_1= RULE_INT
            {
            // PsiInternalEJSL.g:7552:3: (kw= '-' )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==134) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // PsiInternalEJSL.g:7553:4: kw= '-'
                    {

                    				markLeaf(elementTypeProvider.getNUMBER_HyphenMinusKeyword_0ElementType());
                    			
                    kw=(Token)match(input,134,FOLLOW_39); 

                    				doneLeaf(kw);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getNUMBER_INTTerminalRuleCall_1ElementType());
            		
            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_2); 

            			doneLeaf(this_INT_1);
            		

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNUMBER"


    // $ANTLR start "entryRuleQualifiedName"
    // PsiInternalEJSL.g:7572:1: entryRuleQualifiedName returns [Boolean current=false] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final Boolean entryRuleQualifiedName() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleQualifiedName = null;


        try {
            // PsiInternalEJSL.g:7572:55: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // PsiInternalEJSL.g:7573:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             markComposite(elementTypeProvider.getQualifiedNameElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // PsiInternalEJSL.g:7579:1: ruleQualifiedName returns [Boolean current=false] : ( ruleMYID (kw= '.' ruleMYID )* ) ;
    public final Boolean ruleQualifiedName() throws RecognitionException {
        Boolean current = false;

        Token kw=null;

        try {
            // PsiInternalEJSL.g:7580:1: ( ( ruleMYID (kw= '.' ruleMYID )* ) )
            // PsiInternalEJSL.g:7581:2: ( ruleMYID (kw= '.' ruleMYID )* )
            {
            // PsiInternalEJSL.g:7581:2: ( ruleMYID (kw= '.' ruleMYID )* )
            // PsiInternalEJSL.g:7582:3: ruleMYID (kw= '.' ruleMYID )*
            {

            			markComposite(elementTypeProvider.getQualifiedName_MYIDParserRuleCall_0ElementType());
            		
            pushFollow(FOLLOW_175);
            ruleMYID();

            state._fsp--;


            			doneComposite();
            		
            // PsiInternalEJSL.g:7589:3: (kw= '.' ruleMYID )*
            loop197:
            do {
                int alt197=2;
                int LA197_0 = input.LA(1);

                if ( (LA197_0==135) ) {
                    alt197=1;
                }


                switch (alt197) {
            	case 1 :
            	    // PsiInternalEJSL.g:7590:4: kw= '.' ruleMYID
            	    {

            	    				markLeaf(elementTypeProvider.getQualifiedName_FullStopKeyword_1_0ElementType());
            	    			
            	    kw=(Token)match(input,135,FOLLOW_51); 

            	    				doneLeaf(kw);
            	    			

            	    				markComposite(elementTypeProvider.getQualifiedName_MYIDParserRuleCall_1_1ElementType());
            	    			
            	    pushFollow(FOLLOW_175);
            	    ruleMYID();

            	    state._fsp--;


            	    				doneComposite();
            	    			

            	    }
            	    break;

            	default :
            	    break loop197;
                }
            } while (true);


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleMYID"
    // PsiInternalEJSL.g:7609:1: entryRuleMYID returns [Boolean current=false] : iv_ruleMYID= ruleMYID EOF ;
    public final Boolean entryRuleMYID() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleMYID = null;


        try {
            // PsiInternalEJSL.g:7609:46: (iv_ruleMYID= ruleMYID EOF )
            // PsiInternalEJSL.g:7610:2: iv_ruleMYID= ruleMYID EOF
            {
             markComposite(elementTypeProvider.getMYIDElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleMYID=ruleMYID();

            state._fsp--;

             current =iv_ruleMYID; 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMYID"


    // $ANTLR start "ruleMYID"
    // PsiInternalEJSL.g:7616:1: ruleMYID returns [Boolean current=false] : ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? ) ;
    public final Boolean ruleMYID() throws RecognitionException {
        Boolean current = false;

        Token kw=null;
        Token this_ID_1=null;

        try {
            // PsiInternalEJSL.g:7617:1: ( ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? ) )
            // PsiInternalEJSL.g:7618:2: ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? )
            {
            // PsiInternalEJSL.g:7618:2: ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? )
            // PsiInternalEJSL.g:7619:3: (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )?
            {
            // PsiInternalEJSL.g:7619:3: (kw= '<' )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==136) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // PsiInternalEJSL.g:7620:4: kw= '<'
                    {

                    				markLeaf(elementTypeProvider.getMYID_LessThanSignKeyword_0ElementType());
                    			
                    kw=(Token)match(input,136,FOLLOW_33); 

                    				doneLeaf(kw);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getMYID_IDTerminalRuleCall_1ElementType());
            		
            this_ID_1=(Token)match(input,RULE_ID,FOLLOW_176); 

            			doneLeaf(this_ID_1);
            		
            // PsiInternalEJSL.g:7635:3: (kw= '>' )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==137) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // PsiInternalEJSL.g:7636:4: kw= '>'
                    {

                    				markLeaf(elementTypeProvider.getMYID_GreaterThanSignKeyword_2ElementType());
                    			
                    kw=(Token)match(input,137,FOLLOW_2); 

                    				doneLeaf(kw);
                    			

                    }
                    break;

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMYID"


    // $ANTLR start "rulePluginKinds"
    // PsiInternalEJSL.g:7648:1: rulePluginKinds returns [Boolean current=false] : ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) ) ;
    public final Boolean rulePluginKinds() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;
        Token enumLiteral_11=null;

        try {
            // PsiInternalEJSL.g:7649:1: ( ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) ) )
            // PsiInternalEJSL.g:7650:2: ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) )
            {
            // PsiInternalEJSL.g:7650:2: ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) )
            int alt200=12;
            switch ( input.LA(1) ) {
            case 138:
                {
                alt200=1;
                }
                break;
            case 139:
                {
                alt200=2;
                }
                break;
            case 140:
                {
                alt200=3;
                }
                break;
            case 141:
                {
                alt200=4;
                }
                break;
            case 142:
                {
                alt200=5;
                }
                break;
            case 25:
                {
                alt200=6;
                }
                break;
            case 143:
                {
                alt200=7;
                }
                break;
            case 144:
                {
                alt200=8;
                }
                break;
            case 145:
                {
                alt200=9;
                }
                break;
            case 146:
                {
                alt200=10;
                }
                break;
            case 147:
                {
                alt200=11;
                }
                break;
            case 148:
                {
                alt200=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 200, 0, input);

                throw nvae;
            }

            switch (alt200) {
                case 1 :
                    // PsiInternalEJSL.g:7651:3: (enumLiteral_0= 'authenticate' )
                    {
                    // PsiInternalEJSL.g:7651:3: (enumLiteral_0= 'authenticate' )
                    // PsiInternalEJSL.g:7652:4: enumLiteral_0= 'authenticate'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_AuthenticateEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,138,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7661:3: (enumLiteral_1= 'captcha' )
                    {
                    // PsiInternalEJSL.g:7661:3: (enumLiteral_1= 'captcha' )
                    // PsiInternalEJSL.g:7662:4: enumLiteral_1= 'captcha'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_CaptchaEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,139,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:7671:3: (enumLiteral_2= 'content' )
                    {
                    // PsiInternalEJSL.g:7671:3: (enumLiteral_2= 'content' )
                    // PsiInternalEJSL.g:7672:4: enumLiteral_2= 'content'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_ContentEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,140,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;
                case 4 :
                    // PsiInternalEJSL.g:7681:3: (enumLiteral_3= 'contact' )
                    {
                    // PsiInternalEJSL.g:7681:3: (enumLiteral_3= 'contact' )
                    // PsiInternalEJSL.g:7682:4: enumLiteral_3= 'contact'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_ContactEnumLiteralDeclaration_3ElementType());
                    			
                    enumLiteral_3=(Token)match(input,141,FOLLOW_2); 

                    				doneLeaf(enumLiteral_3);
                    			

                    }


                    }
                    break;
                case 5 :
                    // PsiInternalEJSL.g:7691:3: (enumLiteral_4= 'editors' )
                    {
                    // PsiInternalEJSL.g:7691:3: (enumLiteral_4= 'editors' )
                    // PsiInternalEJSL.g:7692:4: enumLiteral_4= 'editors'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_EditorsEnumLiteralDeclaration_4ElementType());
                    			
                    enumLiteral_4=(Token)match(input,142,FOLLOW_2); 

                    				doneLeaf(enumLiteral_4);
                    			

                    }


                    }
                    break;
                case 6 :
                    // PsiInternalEJSL.g:7701:3: (enumLiteral_5= 'extensions' )
                    {
                    // PsiInternalEJSL.g:7701:3: (enumLiteral_5= 'extensions' )
                    // PsiInternalEJSL.g:7702:4: enumLiteral_5= 'extensions'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_ExtensionsEnumLiteralDeclaration_5ElementType());
                    			
                    enumLiteral_5=(Token)match(input,25,FOLLOW_2); 

                    				doneLeaf(enumLiteral_5);
                    			

                    }


                    }
                    break;
                case 7 :
                    // PsiInternalEJSL.g:7711:3: (enumLiteral_6= 'finder' )
                    {
                    // PsiInternalEJSL.g:7711:3: (enumLiteral_6= 'finder' )
                    // PsiInternalEJSL.g:7712:4: enumLiteral_6= 'finder'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_FinderEnumLiteralDeclaration_6ElementType());
                    			
                    enumLiteral_6=(Token)match(input,143,FOLLOW_2); 

                    				doneLeaf(enumLiteral_6);
                    			

                    }


                    }
                    break;
                case 8 :
                    // PsiInternalEJSL.g:7721:3: (enumLiteral_7= 'quick_icons' )
                    {
                    // PsiInternalEJSL.g:7721:3: (enumLiteral_7= 'quick_icons' )
                    // PsiInternalEJSL.g:7722:4: enumLiteral_7= 'quick_icons'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_Quick_iconsEnumLiteralDeclaration_7ElementType());
                    			
                    enumLiteral_7=(Token)match(input,144,FOLLOW_2); 

                    				doneLeaf(enumLiteral_7);
                    			

                    }


                    }
                    break;
                case 9 :
                    // PsiInternalEJSL.g:7731:3: (enumLiteral_8= 'search' )
                    {
                    // PsiInternalEJSL.g:7731:3: (enumLiteral_8= 'search' )
                    // PsiInternalEJSL.g:7732:4: enumLiteral_8= 'search'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_SearchEnumLiteralDeclaration_8ElementType());
                    			
                    enumLiteral_8=(Token)match(input,145,FOLLOW_2); 

                    				doneLeaf(enumLiteral_8);
                    			

                    }


                    }
                    break;
                case 10 :
                    // PsiInternalEJSL.g:7741:3: (enumLiteral_9= 'system' )
                    {
                    // PsiInternalEJSL.g:7741:3: (enumLiteral_9= 'system' )
                    // PsiInternalEJSL.g:7742:4: enumLiteral_9= 'system'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_SystemEnumLiteralDeclaration_9ElementType());
                    			
                    enumLiteral_9=(Token)match(input,146,FOLLOW_2); 

                    				doneLeaf(enumLiteral_9);
                    			

                    }


                    }
                    break;
                case 11 :
                    // PsiInternalEJSL.g:7751:3: (enumLiteral_10= 'user' )
                    {
                    // PsiInternalEJSL.g:7751:3: (enumLiteral_10= 'user' )
                    // PsiInternalEJSL.g:7752:4: enumLiteral_10= 'user'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_UserEnumLiteralDeclaration_10ElementType());
                    			
                    enumLiteral_10=(Token)match(input,147,FOLLOW_2); 

                    				doneLeaf(enumLiteral_10);
                    			

                    }


                    }
                    break;
                case 12 :
                    // PsiInternalEJSL.g:7761:3: (enumLiteral_11= 'xml_rpc' )
                    {
                    // PsiInternalEJSL.g:7761:3: (enumLiteral_11= 'xml_rpc' )
                    // PsiInternalEJSL.g:7762:4: enumLiteral_11= 'xml_rpc'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_Xml_rpcEnumLiteralDeclaration_11ElementType());
                    			
                    enumLiteral_11=(Token)match(input,148,FOLLOW_2); 

                    				doneLeaf(enumLiteral_11);
                    			

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePluginKinds"


    // $ANTLR start "rulePageActionKind"
    // PsiInternalEJSL.g:7774:1: rulePageActionKind returns [Boolean current=false] : ( (enumLiteral_0= 'Text' ) | (enumLiteral_1= 'Button' ) ) ;
    public final Boolean rulePageActionKind() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

        try {
            // PsiInternalEJSL.g:7775:1: ( ( (enumLiteral_0= 'Text' ) | (enumLiteral_1= 'Button' ) ) )
            // PsiInternalEJSL.g:7776:2: ( (enumLiteral_0= 'Text' ) | (enumLiteral_1= 'Button' ) )
            {
            // PsiInternalEJSL.g:7776:2: ( (enumLiteral_0= 'Text' ) | (enumLiteral_1= 'Button' ) )
            int alt201=2;
            int LA201_0 = input.LA(1);

            if ( (LA201_0==149) ) {
                alt201=1;
            }
            else if ( (LA201_0==150) ) {
                alt201=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 201, 0, input);

                throw nvae;
            }
            switch (alt201) {
                case 1 :
                    // PsiInternalEJSL.g:7777:3: (enumLiteral_0= 'Text' )
                    {
                    // PsiInternalEJSL.g:7777:3: (enumLiteral_0= 'Text' )
                    // PsiInternalEJSL.g:7778:4: enumLiteral_0= 'Text'
                    {

                    				markLeaf(elementTypeProvider.getPageActionKind_TextEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,149,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7787:3: (enumLiteral_1= 'Button' )
                    {
                    // PsiInternalEJSL.g:7787:3: (enumLiteral_1= 'Button' )
                    // PsiInternalEJSL.g:7788:4: enumLiteral_1= 'Button'
                    {

                    				markLeaf(elementTypeProvider.getPageActionKind_ButtonEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,150,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageActionKind"


    // $ANTLR start "rulePageActionPositionKind"
    // PsiInternalEJSL.g:7800:1: rulePageActionPositionKind returns [Boolean current=false] : ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) ) ;
    public final Boolean rulePageActionPositionKind() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

        try {
            // PsiInternalEJSL.g:7801:1: ( ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) ) )
            // PsiInternalEJSL.g:7802:2: ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) )
            {
            // PsiInternalEJSL.g:7802:2: ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) )
            int alt202=3;
            switch ( input.LA(1) ) {
            case 151:
                {
                alt202=1;
                }
                break;
            case 152:
                {
                alt202=2;
                }
                break;
            case 153:
                {
                alt202=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 202, 0, input);

                throw nvae;
            }

            switch (alt202) {
                case 1 :
                    // PsiInternalEJSL.g:7803:3: (enumLiteral_0= 'top' )
                    {
                    // PsiInternalEJSL.g:7803:3: (enumLiteral_0= 'top' )
                    // PsiInternalEJSL.g:7804:4: enumLiteral_0= 'top'
                    {

                    				markLeaf(elementTypeProvider.getPageActionPositionKind_TopEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,151,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7813:3: (enumLiteral_1= 'center' )
                    {
                    // PsiInternalEJSL.g:7813:3: (enumLiteral_1= 'center' )
                    // PsiInternalEJSL.g:7814:4: enumLiteral_1= 'center'
                    {

                    				markLeaf(elementTypeProvider.getPageActionPositionKind_CenterEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,152,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:7823:3: (enumLiteral_2= 'bottom' )
                    {
                    // PsiInternalEJSL.g:7823:3: (enumLiteral_2= 'bottom' )
                    // PsiInternalEJSL.g:7824:4: enumLiteral_2= 'bottom'
                    {

                    				markLeaf(elementTypeProvider.getPageActionPositionKind_BottomEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,153,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageActionPositionKind"


    // $ANTLR start "ruleStandardTypeKinds"
    // PsiInternalEJSL.g:7836:1: ruleStandardTypeKinds returns [Boolean current=false] : ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) ) ;
    public final Boolean ruleStandardTypeKinds() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;

        try {
            // PsiInternalEJSL.g:7837:1: ( ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) ) )
            // PsiInternalEJSL.g:7838:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) )
            {
            // PsiInternalEJSL.g:7838:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) )
            int alt203=11;
            switch ( input.LA(1) ) {
            case 154:
                {
                alt203=1;
                }
                break;
            case 155:
                {
                alt203=2;
                }
                break;
            case 156:
                {
                alt203=3;
                }
                break;
            case 157:
                {
                alt203=4;
                }
                break;
            case 158:
                {
                alt203=5;
                }
                break;
            case 159:
                {
                alt203=6;
                }
                break;
            case 160:
                {
                alt203=7;
                }
                break;
            case 161:
                {
                alt203=8;
                }
                break;
            case 162:
                {
                alt203=9;
                }
                break;
            case 163:
                {
                alt203=10;
                }
                break;
            case 164:
                {
                alt203=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 203, 0, input);

                throw nvae;
            }

            switch (alt203) {
                case 1 :
                    // PsiInternalEJSL.g:7839:3: (enumLiteral_0= 'Integer' )
                    {
                    // PsiInternalEJSL.g:7839:3: (enumLiteral_0= 'Integer' )
                    // PsiInternalEJSL.g:7840:4: enumLiteral_0= 'Integer'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_IntegerEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,154,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7849:3: (enumLiteral_1= 'Boolean' )
                    {
                    // PsiInternalEJSL.g:7849:3: (enumLiteral_1= 'Boolean' )
                    // PsiInternalEJSL.g:7850:4: enumLiteral_1= 'Boolean'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_BooleanEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,155,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:7859:3: (enumLiteral_2= 'Textarea' )
                    {
                    // PsiInternalEJSL.g:7859:3: (enumLiteral_2= 'Textarea' )
                    // PsiInternalEJSL.g:7860:4: enumLiteral_2= 'Textarea'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_TextareaEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,156,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;
                case 4 :
                    // PsiInternalEJSL.g:7869:3: (enumLiteral_3= 'Textfield' )
                    {
                    // PsiInternalEJSL.g:7869:3: (enumLiteral_3= 'Textfield' )
                    // PsiInternalEJSL.g:7870:4: enumLiteral_3= 'Textfield'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_TextfieldEnumLiteralDeclaration_3ElementType());
                    			
                    enumLiteral_3=(Token)match(input,157,FOLLOW_2); 

                    				doneLeaf(enumLiteral_3);
                    			

                    }


                    }
                    break;
                case 5 :
                    // PsiInternalEJSL.g:7879:3: (enumLiteral_4= 'Time' )
                    {
                    // PsiInternalEJSL.g:7879:3: (enumLiteral_4= 'Time' )
                    // PsiInternalEJSL.g:7880:4: enumLiteral_4= 'Time'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_TimeEnumLiteralDeclaration_4ElementType());
                    			
                    enumLiteral_4=(Token)match(input,158,FOLLOW_2); 

                    				doneLeaf(enumLiteral_4);
                    			

                    }


                    }
                    break;
                case 6 :
                    // PsiInternalEJSL.g:7889:3: (enumLiteral_5= 'Date' )
                    {
                    // PsiInternalEJSL.g:7889:3: (enumLiteral_5= 'Date' )
                    // PsiInternalEJSL.g:7890:4: enumLiteral_5= 'Date'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_DateEnumLiteralDeclaration_5ElementType());
                    			
                    enumLiteral_5=(Token)match(input,159,FOLLOW_2); 

                    				doneLeaf(enumLiteral_5);
                    			

                    }


                    }
                    break;
                case 7 :
                    // PsiInternalEJSL.g:7899:3: (enumLiteral_6= 'Datetime' )
                    {
                    // PsiInternalEJSL.g:7899:3: (enumLiteral_6= 'Datetime' )
                    // PsiInternalEJSL.g:7900:4: enumLiteral_6= 'Datetime'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_DatetimeEnumLiteralDeclaration_6ElementType());
                    			
                    enumLiteral_6=(Token)match(input,160,FOLLOW_2); 

                    				doneLeaf(enumLiteral_6);
                    			

                    }


                    }
                    break;
                case 8 :
                    // PsiInternalEJSL.g:7909:3: (enumLiteral_7= 'Link' )
                    {
                    // PsiInternalEJSL.g:7909:3: (enumLiteral_7= 'Link' )
                    // PsiInternalEJSL.g:7910:4: enumLiteral_7= 'Link'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_LinkEnumLiteralDeclaration_7ElementType());
                    			
                    enumLiteral_7=(Token)match(input,161,FOLLOW_2); 

                    				doneLeaf(enumLiteral_7);
                    			

                    }


                    }
                    break;
                case 9 :
                    // PsiInternalEJSL.g:7919:3: (enumLiteral_8= 'Image' )
                    {
                    // PsiInternalEJSL.g:7919:3: (enumLiteral_8= 'Image' )
                    // PsiInternalEJSL.g:7920:4: enumLiteral_8= 'Image'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_ImageEnumLiteralDeclaration_8ElementType());
                    			
                    enumLiteral_8=(Token)match(input,162,FOLLOW_2); 

                    				doneLeaf(enumLiteral_8);
                    			

                    }


                    }
                    break;
                case 10 :
                    // PsiInternalEJSL.g:7929:3: (enumLiteral_9= 'File' )
                    {
                    // PsiInternalEJSL.g:7929:3: (enumLiteral_9= 'File' )
                    // PsiInternalEJSL.g:7930:4: enumLiteral_9= 'File'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_FileEnumLiteralDeclaration_9ElementType());
                    			
                    enumLiteral_9=(Token)match(input,163,FOLLOW_2); 

                    				doneLeaf(enumLiteral_9);
                    			

                    }


                    }
                    break;
                case 11 :
                    // PsiInternalEJSL.g:7939:3: (enumLiteral_10= 'Label' )
                    {
                    // PsiInternalEJSL.g:7939:3: (enumLiteral_10= 'Label' )
                    // PsiInternalEJSL.g:7940:4: enumLiteral_10= 'Label'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_LabelEnumLiteralDeclaration_10ElementType());
                    			
                    enumLiteral_10=(Token)match(input,164,FOLLOW_2); 

                    				doneLeaf(enumLiteral_10);
                    			

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStandardTypeKinds"


    // $ANTLR start "ruleSectionKinds"
    // PsiInternalEJSL.g:7952:1: ruleSectionKinds returns [Boolean current=false] : ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) ) ;
    public final Boolean ruleSectionKinds() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

        try {
            // PsiInternalEJSL.g:7953:1: ( ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) ) )
            // PsiInternalEJSL.g:7954:2: ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) )
            {
            // PsiInternalEJSL.g:7954:2: ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) )
            int alt204=2;
            int LA204_0 = input.LA(1);

            if ( (LA204_0==165) ) {
                alt204=1;
            }
            else if ( (LA204_0==166) ) {
                alt204=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 204, 0, input);

                throw nvae;
            }
            switch (alt204) {
                case 1 :
                    // PsiInternalEJSL.g:7955:3: (enumLiteral_0= '.backend' )
                    {
                    // PsiInternalEJSL.g:7955:3: (enumLiteral_0= '.backend' )
                    // PsiInternalEJSL.g:7956:4: enumLiteral_0= '.backend'
                    {

                    				markLeaf(elementTypeProvider.getSectionKinds_BackendEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,165,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7965:3: (enumLiteral_1= '.frontend' )
                    {
                    // PsiInternalEJSL.g:7965:3: (enumLiteral_1= '.frontend' )
                    // PsiInternalEJSL.g:7966:4: enumLiteral_1= '.frontend'
                    {

                    				markLeaf(elementTypeProvider.getSectionKinds_FrontendEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,166,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSectionKinds"


    // $ANTLR start "rulePageKinds"
    // PsiInternalEJSL.g:7978:1: rulePageKinds returns [Boolean current=false] : ( (enumLiteral_0= 'list' ) | (enumLiteral_1= 'details' ) | (enumLiteral_2= 'custom' ) ) ;
    public final Boolean rulePageKinds() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

        try {
            // PsiInternalEJSL.g:7979:1: ( ( (enumLiteral_0= 'list' ) | (enumLiteral_1= 'details' ) | (enumLiteral_2= 'custom' ) ) )
            // PsiInternalEJSL.g:7980:2: ( (enumLiteral_0= 'list' ) | (enumLiteral_1= 'details' ) | (enumLiteral_2= 'custom' ) )
            {
            // PsiInternalEJSL.g:7980:2: ( (enumLiteral_0= 'list' ) | (enumLiteral_1= 'details' ) | (enumLiteral_2= 'custom' ) )
            int alt205=3;
            switch ( input.LA(1) ) {
            case 167:
                {
                alt205=1;
                }
                break;
            case 168:
                {
                alt205=2;
                }
                break;
            case 169:
                {
                alt205=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 205, 0, input);

                throw nvae;
            }

            switch (alt205) {
                case 1 :
                    // PsiInternalEJSL.g:7981:3: (enumLiteral_0= 'list' )
                    {
                    // PsiInternalEJSL.g:7981:3: (enumLiteral_0= 'list' )
                    // PsiInternalEJSL.g:7982:4: enumLiteral_0= 'list'
                    {

                    				markLeaf(elementTypeProvider.getPageKinds_ListEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,167,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7991:3: (enumLiteral_1= 'details' )
                    {
                    // PsiInternalEJSL.g:7991:3: (enumLiteral_1= 'details' )
                    // PsiInternalEJSL.g:7992:4: enumLiteral_1= 'details'
                    {

                    				markLeaf(elementTypeProvider.getPageKinds_DetailsEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,168,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:8001:3: (enumLiteral_2= 'custom' )
                    {
                    // PsiInternalEJSL.g:8001:3: (enumLiteral_2= 'custom' )
                    // PsiInternalEJSL.g:8002:4: enumLiteral_2= 'custom'
                    {

                    				markLeaf(elementTypeProvider.getPageKinds_CustomEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,169,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageKinds"


    // $ANTLR start "ruleSimpleHTMLTypeKinds"
    // PsiInternalEJSL.g:8014:1: ruleSimpleHTMLTypeKinds returns [Boolean current=false] : ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) ) ;
    public final Boolean ruleSimpleHTMLTypeKinds() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;

        try {
            // PsiInternalEJSL.g:8015:1: ( ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) ) )
            // PsiInternalEJSL.g:8016:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) )
            {
            // PsiInternalEJSL.g:8016:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) )
            int alt206=9;
            switch ( input.LA(1) ) {
            case 154:
                {
                alt206=1;
                }
                break;
            case 170:
                {
                alt206=2;
                }
                break;
            case 156:
                {
                alt206=3;
                }
                break;
            case 171:
                {
                alt206=4;
                }
                break;
            case 172:
                {
                alt206=5;
                }
                break;
            case 173:
                {
                alt206=6;
                }
                break;
            case 174:
                {
                alt206=7;
                }
                break;
            case 175:
                {
                alt206=8;
                }
                break;
            case 176:
                {
                alt206=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 206, 0, input);

                throw nvae;
            }

            switch (alt206) {
                case 1 :
                    // PsiInternalEJSL.g:8017:3: (enumLiteral_0= 'Integer' )
                    {
                    // PsiInternalEJSL.g:8017:3: (enumLiteral_0= 'Integer' )
                    // PsiInternalEJSL.g:8018:4: enumLiteral_0= 'Integer'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_IntegerEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,154,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:8027:3: (enumLiteral_1= 'Yes_No_Buttons' )
                    {
                    // PsiInternalEJSL.g:8027:3: (enumLiteral_1= 'Yes_No_Buttons' )
                    // PsiInternalEJSL.g:8028:4: enumLiteral_1= 'Yes_No_Buttons'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_Yes_No_ButtonsEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,170,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:8037:3: (enumLiteral_2= 'Textarea' )
                    {
                    // PsiInternalEJSL.g:8037:3: (enumLiteral_2= 'Textarea' )
                    // PsiInternalEJSL.g:8038:4: enumLiteral_2= 'Textarea'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_TextareaEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,156,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;
                case 4 :
                    // PsiInternalEJSL.g:8047:3: (enumLiteral_3= 'Text_Field' )
                    {
                    // PsiInternalEJSL.g:8047:3: (enumLiteral_3= 'Text_Field' )
                    // PsiInternalEJSL.g:8048:4: enumLiteral_3= 'Text_Field'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_Text_FieldEnumLiteralDeclaration_3ElementType());
                    			
                    enumLiteral_3=(Token)match(input,171,FOLLOW_2); 

                    				doneLeaf(enumLiteral_3);
                    			

                    }


                    }
                    break;
                case 5 :
                    // PsiInternalEJSL.g:8057:3: (enumLiteral_4= 'Datepicker' )
                    {
                    // PsiInternalEJSL.g:8057:3: (enumLiteral_4= 'Datepicker' )
                    // PsiInternalEJSL.g:8058:4: enumLiteral_4= 'Datepicker'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_DatepickerEnumLiteralDeclaration_4ElementType());
                    			
                    enumLiteral_4=(Token)match(input,172,FOLLOW_2); 

                    				doneLeaf(enumLiteral_4);
                    			

                    }


                    }
                    break;
                case 6 :
                    // PsiInternalEJSL.g:8067:3: (enumLiteral_5= 'Imagepicker' )
                    {
                    // PsiInternalEJSL.g:8067:3: (enumLiteral_5= 'Imagepicker' )
                    // PsiInternalEJSL.g:8068:4: enumLiteral_5= 'Imagepicker'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_ImagepickerEnumLiteralDeclaration_5ElementType());
                    			
                    enumLiteral_5=(Token)match(input,173,FOLLOW_2); 

                    				doneLeaf(enumLiteral_5);
                    			

                    }


                    }
                    break;
                case 7 :
                    // PsiInternalEJSL.g:8077:3: (enumLiteral_6= 'Filepicker' )
                    {
                    // PsiInternalEJSL.g:8077:3: (enumLiteral_6= 'Filepicker' )
                    // PsiInternalEJSL.g:8078:4: enumLiteral_6= 'Filepicker'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_FilepickerEnumLiteralDeclaration_6ElementType());
                    			
                    enumLiteral_6=(Token)match(input,174,FOLLOW_2); 

                    				doneLeaf(enumLiteral_6);
                    			

                    }


                    }
                    break;
                case 8 :
                    // PsiInternalEJSL.g:8087:3: (enumLiteral_7= 'Text_Field_NE' )
                    {
                    // PsiInternalEJSL.g:8087:3: (enumLiteral_7= 'Text_Field_NE' )
                    // PsiInternalEJSL.g:8088:4: enumLiteral_7= 'Text_Field_NE'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_Text_Field_NEEnumLiteralDeclaration_7ElementType());
                    			
                    enumLiteral_7=(Token)match(input,175,FOLLOW_2); 

                    				doneLeaf(enumLiteral_7);
                    			

                    }


                    }
                    break;
                case 9 :
                    // PsiInternalEJSL.g:8097:3: (enumLiteral_8= 'Editor' )
                    {
                    // PsiInternalEJSL.g:8097:3: (enumLiteral_8= 'Editor' )
                    // PsiInternalEJSL.g:8098:4: enumLiteral_8= 'Editor'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_EditorEnumLiteralDeclaration_8ElementType());
                    			
                    enumLiteral_8=(Token)match(input,176,FOLLOW_2); 

                    				doneLeaf(enumLiteral_8);
                    			

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleHTMLTypeKinds"


    // $ANTLR start "ruleComplexHTMLTypeKinds"
    // PsiInternalEJSL.g:8110:1: ruleComplexHTMLTypeKinds returns [Boolean current=false] : ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) ) ;
    public final Boolean ruleComplexHTMLTypeKinds() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

        try {
            // PsiInternalEJSL.g:8111:1: ( ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) ) )
            // PsiInternalEJSL.g:8112:2: ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) )
            {
            // PsiInternalEJSL.g:8112:2: ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) )
            int alt207=3;
            switch ( input.LA(1) ) {
            case 177:
                {
                alt207=1;
                }
                break;
            case 178:
                {
                alt207=2;
                }
                break;
            case 179:
                {
                alt207=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 207, 0, input);

                throw nvae;
            }

            switch (alt207) {
                case 1 :
                    // PsiInternalEJSL.g:8113:3: (enumLiteral_0= 'Multiselect' )
                    {
                    // PsiInternalEJSL.g:8113:3: (enumLiteral_0= 'Multiselect' )
                    // PsiInternalEJSL.g:8114:4: enumLiteral_0= 'Multiselect'
                    {

                    				markLeaf(elementTypeProvider.getComplexHTMLTypeKinds_MultiselectEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,177,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:8123:3: (enumLiteral_1= 'Checkbox' )
                    {
                    // PsiInternalEJSL.g:8123:3: (enumLiteral_1= 'Checkbox' )
                    // PsiInternalEJSL.g:8124:4: enumLiteral_1= 'Checkbox'
                    {

                    				markLeaf(elementTypeProvider.getComplexHTMLTypeKinds_CheckboxEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,178,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:8133:3: (enumLiteral_2= 'Radiobutto' )
                    {
                    // PsiInternalEJSL.g:8133:3: (enumLiteral_2= 'Radiobutto' )
                    // PsiInternalEJSL.g:8134:4: enumLiteral_2= 'Radiobutto'
                    {

                    				markLeaf(elementTypeProvider.getComplexHTMLTypeKinds_RadiobuttoEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,179,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComplexHTMLTypeKinds"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000001080000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000000003CD40000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000240000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x000000003CC40000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000400040000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x000000003C840000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000010000040000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x000000003C040000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x000000003ED40000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000000003EC40000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x000000003E840000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x000000003E040000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000002040000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000040000L,0x0001002604800000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000100000040000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000038000002L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000200000040000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0800000000040000L,0x0000000000000244L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000000000L,0x0000000110000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000040000L,0x0000000110000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000180000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000000L,0x0000001FFC000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x000000F000040000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x000000E000040000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x000000C000040000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000008000040000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000022000000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000400040020L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000003800000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x000000000C140000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000008140000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000200040000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000000020L,0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000400000010000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0001800000040000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0002000000040000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0001000000040000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0020000000040000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0004000000040000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0008000000040000L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0010000000000020L,0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0080000000200000L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0200000000000020L,0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0xF000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0xE000000000200000L,0x0000000000000003L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0xC000000000200000L,0x0000000000000003L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x8000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000040000040000L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000003L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000000000040000L,0x0000000000182000L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0xF000000000040000L,0x0000000000000039L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0xF000000000240000L,0x0000000000000031L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0xE000000000240000L,0x0000000000000031L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0xC000000000240000L,0x0000000000000031L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x8000000000040000L,0x0000000000000031L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000031L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000240000L,0x0000000000000021L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000240000L,0x0000000000000001L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0xF000000000040000L,0x00000000000000B9L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0xF000000000240000L,0x00000000000000B1L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000000040020L,0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0xF000000000040000L,0x0000000000000031L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000FFC0014000000L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000038000000000L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0xF000000000040000L,0x0000000000000009L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0xF000000000240000L,0x0000000000000001L});
    public static final BitSet FOLLOW_92 = new BitSet(new long[]{0x7000000000040000L,0x0000000000000001L});
    public static final BitSet FOLLOW_93 = new BitSet(new long[]{0x6000000000240000L,0x0000000000000001L});
    public static final BitSet FOLLOW_94 = new BitSet(new long[]{0x4000000000240000L,0x0000000000000001L});
    public static final BitSet FOLLOW_95 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000001L});
    public static final BitSet FOLLOW_96 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_97 = new BitSet(new long[]{0x0000000000000000L,0x4000000000000000L});
    public static final BitSet FOLLOW_98 = new BitSet(new long[]{0x0000000000200000L,0x0000000000001000L});
    public static final BitSet FOLLOW_99 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_100 = new BitSet(new long[]{0x0000000000040000L,0x0000000000038000L});
    public static final BitSet FOLLOW_101 = new BitSet(new long[]{0x0000000000040000L,0x0000000000030000L});
    public static final BitSet FOLLOW_102 = new BitSet(new long[]{0x0000000000040000L,0x0000000000020000L});
    public static final BitSet FOLLOW_103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_104 = new BitSet(new long[]{0x0000000000040000L,0x0000000000018000L});
    public static final BitSet FOLLOW_105 = new BitSet(new long[]{0x0000000000040000L,0x0000000000010000L});
    public static final BitSet FOLLOW_106 = new BitSet(new long[]{0x0000000000000000L,0x0000000000218000L});
    public static final BitSet FOLLOW_107 = new BitSet(new long[]{0x0000000000000000L,0x0000000000210000L});
    public static final BitSet FOLLOW_108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_109 = new BitSet(new long[]{0x0000000000000010L,0x0000000000400000L});
    public static final BitSet FOLLOW_110 = new BitSet(new long[]{0x0010000000000010L});
    public static final BitSet FOLLOW_111 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_112 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000000L});
    public static final BitSet FOLLOW_113 = new BitSet(new long[]{0x0000000002000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_114 = new BitSet(new long[]{0x0000000000040000L,0x1000000000000000L});
    public static final BitSet FOLLOW_115 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_116 = new BitSet(new long[]{0x0000000000000000L,0x0001002604800000L});
    public static final BitSet FOLLOW_117 = new BitSet(new long[]{0x0000000020000000L,0x000000000A000000L});
    public static final BitSet FOLLOW_118 = new BitSet(new long[]{0x0000000020000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_119 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_120 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_121 = new BitSet(new long[]{0x0000000000040000L,0x0000000040000000L});
    public static final BitSet FOLLOW_122 = new BitSet(new long[]{0x0000000000000002L,0x0000000080000000L});
    public static final BitSet FOLLOW_123 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000006000000000L});
    public static final BitSet FOLLOW_124 = new BitSet(new long[]{0x0000000000040000L,0x0000000042000000L});
    public static final BitSet FOLLOW_125 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
    public static final BitSet FOLLOW_126 = new BitSet(new long[]{0x0000000002000000L,0x0000000000000000L,0x00000000001FFC00L});
    public static final BitSet FOLLOW_127 = new BitSet(new long[]{0x0000000000040000L,0x0000001002000008L});
    public static final BitSet FOLLOW_128 = new BitSet(new long[]{0x0000000000040000L,0x0000001000000008L});
    public static final BitSet FOLLOW_129 = new BitSet(new long[]{0x0000000000240000L,0x0000001000000000L});
    public static final BitSet FOLLOW_130 = new BitSet(new long[]{0x0000000000040000L,0x000000C002000008L});
    public static final BitSet FOLLOW_131 = new BitSet(new long[]{0x0000000000040000L,0x000000C000000008L});
    public static final BitSet FOLLOW_132 = new BitSet(new long[]{0x0000000000240000L,0x000000C000000000L});
    public static final BitSet FOLLOW_133 = new BitSet(new long[]{0x0000000000040000L,0x0000020000000000L});
    public static final BitSet FOLLOW_134 = new BitSet(new long[]{0x0000000000040000L,0x0000008000000000L});
    public static final BitSet FOLLOW_135 = new BitSet(new long[]{0x0000000000040000L,0x0000010000000000L});
    public static final BitSet FOLLOW_136 = new BitSet(new long[]{0x0000000000040000L,0x000000C000000000L});
    public static final BitSet FOLLOW_137 = new BitSet(new long[]{0x0000000000040000L,0x0000004000000000L});
    public static final BitSet FOLLOW_138 = new BitSet(new long[]{0x0000000000040000L,0x00000C0000000008L});
    public static final BitSet FOLLOW_139 = new BitSet(new long[]{0x0000000000240000L,0x0000080000000008L});
    public static final BitSet FOLLOW_140 = new BitSet(new long[]{0x0000000000240000L,0x0000080000000000L});
    public static final BitSet FOLLOW_141 = new BitSet(new long[]{0x0000000000040000L,0x0000100000000000L});
    public static final BitSet FOLLOW_142 = new BitSet(new long[]{0x0000000000040000L,0x0000600000000000L});
    public static final BitSet FOLLOW_143 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_144 = new BitSet(new long[]{0x0000000000040000L,0x0000400000000000L});
    public static final BitSet FOLLOW_145 = new BitSet(new long[]{0x0000000000040000L,0x0000800000000000L});
    public static final BitSet FOLLOW_146 = new BitSet(new long[]{0x0000000000040000L,0x0006001002000000L});
    public static final BitSet FOLLOW_147 = new BitSet(new long[]{0x0000000000040000L,0x0006001000000000L});
    public static final BitSet FOLLOW_148 = new BitSet(new long[]{0x0000000000040000L,0x0006000000000000L});
    public static final BitSet FOLLOW_149 = new BitSet(new long[]{0x0000000000040000L,0x8000000000000000L});
    public static final BitSet FOLLOW_150 = new BitSet(new long[]{0x0000000000040000L,0x0004000000000000L});
    public static final BitSet FOLLOW_151 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_152 = new BitSet(new long[]{0x0000000000000000L,0x0200000000000000L});
    public static final BitSet FOLLOW_153 = new BitSet(new long[]{0x0000000000040000L,0x0200000000000000L});
    public static final BitSet FOLLOW_154 = new BitSet(new long[]{0x0000008000000002L,0x01F0000000000000L});
    public static final BitSet FOLLOW_155 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_156 = new BitSet(new long[]{0x0000008000000002L,0x01E0000000000000L});
    public static final BitSet FOLLOW_157 = new BitSet(new long[]{0x0000008000000002L,0x01C0000000000000L});
    public static final BitSet FOLLOW_158 = new BitSet(new long[]{0x0000008000000002L,0x0180000000000000L});
    public static final BitSet FOLLOW_159 = new BitSet(new long[]{0x0000008000000002L,0x0100000000000000L});
    public static final BitSet FOLLOW_160 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_161 = new BitSet(new long[]{0x0000000000040000L,0x0C00000000000000L});
    public static final BitSet FOLLOW_162 = new BitSet(new long[]{0x0000000000040000L,0x0800000000000000L});
    public static final BitSet FOLLOW_163 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_164 = new BitSet(new long[]{0x0000000000040000L,0x2000000000000000L});
    public static final BitSet FOLLOW_165 = new BitSet(new long[]{0x0000000000040000L,0x4000000000000000L});
    public static final BitSet FOLLOW_166 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_167 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_168 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_169 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x000000000000001CL});
    public static final BitSet FOLLOW_170 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000018L});
    public static final BitSet FOLLOW_171 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_172 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_173 = new BitSet(new long[]{0x0000000000000000L,0x2000000000001000L});
    public static final BitSet FOLLOW_174 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_175 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_176 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000000L,0x0000000000000200L});

}