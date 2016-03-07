package de.thm.icampus.joomdd.ejsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.thm.icampus.joomdd.ejsl.services.EJSLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalEJSLParser extends AbstractInternalAntlrParser {
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


        public InternalEJSLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalEJSLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalEJSLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalEJSL.g"; }



     	private EJSLGrammarAccess grammarAccess;

        public InternalEJSLParser(TokenStream input, EJSLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "EJSLModel";
       	}

       	@Override
       	protected EJSLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleEJSLModel"
    // InternalEJSL.g:65:1: entryRuleEJSLModel returns [EObject current=null] : iv_ruleEJSLModel= ruleEJSLModel EOF ;
    public final EObject entryRuleEJSLModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEJSLModel = null;


        try {
            // InternalEJSL.g:65:50: (iv_ruleEJSLModel= ruleEJSLModel EOF )
            // InternalEJSL.g:66:2: iv_ruleEJSLModel= ruleEJSLModel EOF
            {
             newCompositeNode(grammarAccess.getEJSLModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEJSLModel=ruleEJSLModel();

            state._fsp--;

             current =iv_ruleEJSLModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEJSLModel"


    // $ANTLR start "ruleEJSLModel"
    // InternalEJSL.g:72:1: ruleEJSLModel returns [EObject current=null] : ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}' ) ;
    public final EObject ruleEJSLModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_ejslPart_5_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:78:2: ( ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}' ) )
            // InternalEJSL.g:79:2: ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}' )
            {
            // InternalEJSL.g:79:2: ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}' )
            // InternalEJSL.g:80:3: () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) ) otherlv_6= '}'
            {
            // InternalEJSL.g:80:3: ()
            // InternalEJSL.g:81:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEJSLModelAccess().getEJSLModelAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getEJSLModelAccess().getEJSLModelKeyword_1());
            		
            // InternalEJSL.g:91:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalEJSL.g:92:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalEJSL.g:92:4: (lv_name_2_0= RULE_STRING )
            // InternalEJSL.g:93:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getEJSLModelAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEJSLModelRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_5); 

            			newLeafNode(otherlv_3, grammarAccess.getEJSLModelAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:113:3: (otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) ) )
            // InternalEJSL.g:114:4: otherlv_4= 'eJSL part:' ( (lv_ejslPart_5_0= ruleEJSLPart ) )
            {
            otherlv_4=(Token)match(input,17,FOLLOW_6); 

            				newLeafNode(otherlv_4, grammarAccess.getEJSLModelAccess().getEJSLPartKeyword_4_0());
            			
            // InternalEJSL.g:118:4: ( (lv_ejslPart_5_0= ruleEJSLPart ) )
            // InternalEJSL.g:119:5: (lv_ejslPart_5_0= ruleEJSLPart )
            {
            // InternalEJSL.g:119:5: (lv_ejslPart_5_0= ruleEJSLPart )
            // InternalEJSL.g:120:6: lv_ejslPart_5_0= ruleEJSLPart
            {

            						newCompositeNode(grammarAccess.getEJSLModelAccess().getEjslPartEJSLPartParserRuleCall_4_1_0());
            					
            pushFollow(FOLLOW_7);
            lv_ejslPart_5_0=ruleEJSLPart();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getEJSLModelRule());
            						}
            						set(
            							current,
            							"ejslPart",
            							lv_ejslPart_5_0,
            							"de.thm.icampus.joomdd.ejsl.EJSL.EJSLPart");
            						afterParserOrEnumRuleCall();
            					

            }


            }


            }

            otherlv_6=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEJSLModel"


    // $ANTLR start "entryRuleEJSLPart"
    // InternalEJSL.g:146:1: entryRuleEJSLPart returns [EObject current=null] : iv_ruleEJSLPart= ruleEJSLPart EOF ;
    public final EObject entryRuleEJSLPart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEJSLPart = null;


        try {
            // InternalEJSL.g:146:49: (iv_ruleEJSLPart= ruleEJSLPart EOF )
            // InternalEJSL.g:147:2: iv_ruleEJSLPart= ruleEJSLPart EOF
            {
             newCompositeNode(grammarAccess.getEJSLPartRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEJSLPart=ruleEJSLPart();

            state._fsp--;

             current =iv_ruleEJSLPart; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEJSLPart"


    // $ANTLR start "ruleEJSLPart"
    // InternalEJSL.g:153:1: ruleEJSLPart returns [EObject current=null] : (this_CMSCore_0= ruleCMSCore | this_CMSExtension_1= ruleCMSExtension ) ;
    public final EObject ruleEJSLPart() throws RecognitionException {
        EObject current = null;

        EObject this_CMSCore_0 = null;

        EObject this_CMSExtension_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:159:2: ( (this_CMSCore_0= ruleCMSCore | this_CMSExtension_1= ruleCMSExtension ) )
            // InternalEJSL.g:160:2: (this_CMSCore_0= ruleCMSCore | this_CMSExtension_1= ruleCMSExtension )
            {
            // InternalEJSL.g:160:2: (this_CMSCore_0= ruleCMSCore | this_CMSExtension_1= ruleCMSExtension )
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
                    // InternalEJSL.g:161:3: this_CMSCore_0= ruleCMSCore
                    {

                    			newCompositeNode(grammarAccess.getEJSLPartAccess().getCMSCoreParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_CMSCore_0=ruleCMSCore();

                    state._fsp--;


                    			current = this_CMSCore_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEJSL.g:170:3: this_CMSExtension_1= ruleCMSExtension
                    {

                    			newCompositeNode(grammarAccess.getEJSLPartAccess().getCMSExtensionParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_CMSExtension_1=ruleCMSExtension();

                    state._fsp--;


                    			current = this_CMSExtension_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEJSLPart"


    // $ANTLR start "entryRuleCMSCore"
    // InternalEJSL.g:182:1: entryRuleCMSCore returns [EObject current=null] : iv_ruleCMSCore= ruleCMSCore EOF ;
    public final EObject entryRuleCMSCore() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCMSCore = null;


        try {
            // InternalEJSL.g:182:48: (iv_ruleCMSCore= ruleCMSCore EOF )
            // InternalEJSL.g:183:2: iv_ruleCMSCore= ruleCMSCore EOF
            {
             newCompositeNode(grammarAccess.getCMSCoreRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCMSCore=ruleCMSCore();

            state._fsp--;

             current =iv_ruleCMSCore; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCMSCore"


    // $ANTLR start "ruleCMSCore"
    // InternalEJSL.g:189:1: ruleCMSCore returns [EObject current=null] : ( () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}' ) ;
    public final EObject ruleCMSCore() throws RecognitionException {
        EObject current = null;

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
        EObject lv_datatypes_5_0 = null;

        EObject lv_datatypes_7_0 = null;

        EObject lv_globalparameters_11_0 = null;

        EObject lv_parametergroups_15_0 = null;

        EObject lv_feature_17_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:195:2: ( ( () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}' ) )
            // InternalEJSL.g:196:2: ( () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}' )
            {
            // InternalEJSL.g:196:2: ( () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}' )
            // InternalEJSL.g:197:3: () otherlv_1= 'CMS Core' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) otherlv_18= '}'
            {
            // InternalEJSL.g:197:3: ()
            // InternalEJSL.g:198:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getCMSCoreAccess().getCMSCoreAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,19,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getCMSCoreAccess().getCMSCoreKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_8); 

            			newLeafNode(otherlv_2, grammarAccess.getCMSCoreAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalEJSL.g:212:3: (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==20) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalEJSL.g:213:4: otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}'
                    {
                    otherlv_3=(Token)match(input,20,FOLLOW_4); 

                    				newLeafNode(otherlv_3, grammarAccess.getCMSCoreAccess().getDatatypesKeyword_3_0());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_9); 

                    				newLeafNode(otherlv_4, grammarAccess.getCMSCoreAccess().getLeftCurlyBracketKeyword_3_1());
                    			
                    // InternalEJSL.g:221:4: ( (lv_datatypes_5_0= ruleDatatype ) )
                    // InternalEJSL.g:222:5: (lv_datatypes_5_0= ruleDatatype )
                    {
                    // InternalEJSL.g:222:5: (lv_datatypes_5_0= ruleDatatype )
                    // InternalEJSL.g:223:6: lv_datatypes_5_0= ruleDatatype
                    {

                    						newCompositeNode(grammarAccess.getCMSCoreAccess().getDatatypesDatatypeParserRuleCall_3_2_0());
                    					
                    pushFollow(FOLLOW_10);
                    lv_datatypes_5_0=ruleDatatype();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getCMSCoreRule());
                    						}
                    						add(
                    							current,
                    							"datatypes",
                    							lv_datatypes_5_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.Datatype");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:240:4: (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==21) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // InternalEJSL.g:241:5: otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) )
                    	    {
                    	    otherlv_6=(Token)match(input,21,FOLLOW_9); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getCMSCoreAccess().getCommaKeyword_3_3_0());
                    	    				
                    	    // InternalEJSL.g:245:5: ( (lv_datatypes_7_0= ruleDatatype ) )
                    	    // InternalEJSL.g:246:6: (lv_datatypes_7_0= ruleDatatype )
                    	    {
                    	    // InternalEJSL.g:246:6: (lv_datatypes_7_0= ruleDatatype )
                    	    // InternalEJSL.g:247:7: lv_datatypes_7_0= ruleDatatype
                    	    {

                    	    							newCompositeNode(grammarAccess.getCMSCoreAccess().getDatatypesDatatypeParserRuleCall_3_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_10);
                    	    lv_datatypes_7_0=ruleDatatype();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getCMSCoreRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"datatypes",
                    	    								lv_datatypes_7_0,
                    	    								"de.thm.icampus.joomdd.ejsl.EJSL.Datatype");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,18,FOLLOW_11); 

                    				newLeafNode(otherlv_8, grammarAccess.getCMSCoreAccess().getRightCurlyBracketKeyword_3_4());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:270:3: (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==22) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalEJSL.g:271:4: otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}'
                    {
                    otherlv_9=(Token)match(input,22,FOLLOW_4); 

                    				newLeafNode(otherlv_9, grammarAccess.getCMSCoreAccess().getGlobalparametersKeyword_4_0());
                    			
                    otherlv_10=(Token)match(input,16,FOLLOW_12); 

                    				newLeafNode(otherlv_10, grammarAccess.getCMSCoreAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:279:4: ( (lv_globalparameters_11_0= ruleParameter ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==34) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // InternalEJSL.g:280:5: (lv_globalparameters_11_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:280:5: (lv_globalparameters_11_0= ruleParameter )
                    	    // InternalEJSL.g:281:6: lv_globalparameters_11_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getCMSCoreAccess().getGlobalparametersParameterParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_globalparameters_11_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCMSCoreRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"globalparameters",
                    	    							lv_globalparameters_11_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,18,FOLLOW_13); 

                    				newLeafNode(otherlv_12, grammarAccess.getCMSCoreAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:303:3: (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==23) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalEJSL.g:304:4: otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}'
                    {
                    otherlv_13=(Token)match(input,23,FOLLOW_4); 

                    				newLeafNode(otherlv_13, grammarAccess.getCMSCoreAccess().getParametergroupsKeyword_5_0());
                    			
                    otherlv_14=(Token)match(input,16,FOLLOW_14); 

                    				newLeafNode(otherlv_14, grammarAccess.getCMSCoreAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:312:4: ( (lv_parametergroups_15_0= ruleParameterGroup ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==40) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalEJSL.g:313:5: (lv_parametergroups_15_0= ruleParameterGroup )
                    	    {
                    	    // InternalEJSL.g:313:5: (lv_parametergroups_15_0= ruleParameterGroup )
                    	    // InternalEJSL.g:314:6: lv_parametergroups_15_0= ruleParameterGroup
                    	    {

                    	    						newCompositeNode(grammarAccess.getCMSCoreAccess().getParametergroupsParameterGroupParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_14);
                    	    lv_parametergroups_15_0=ruleParameterGroup();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCMSCoreRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"parametergroups",
                    	    							lv_parametergroups_15_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.ParameterGroup");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FOLLOW_15); 

                    				newLeafNode(otherlv_16, grammarAccess.getCMSCoreAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:336:3: ( (lv_feature_17_0= ruleFeature ) )
            // InternalEJSL.g:337:4: (lv_feature_17_0= ruleFeature )
            {
            // InternalEJSL.g:337:4: (lv_feature_17_0= ruleFeature )
            // InternalEJSL.g:338:5: lv_feature_17_0= ruleFeature
            {

            					newCompositeNode(grammarAccess.getCMSCoreAccess().getFeatureFeatureParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_7);
            lv_feature_17_0=ruleFeature();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCMSCoreRule());
            					}
            					set(
            						current,
            						"feature",
            						lv_feature_17_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Feature");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_18=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_18, grammarAccess.getCMSCoreAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCMSCore"


    // $ANTLR start "entryRuleCMSExtension"
    // InternalEJSL.g:363:1: entryRuleCMSExtension returns [EObject current=null] : iv_ruleCMSExtension= ruleCMSExtension EOF ;
    public final EObject entryRuleCMSExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCMSExtension = null;


        try {
            // InternalEJSL.g:363:53: (iv_ruleCMSExtension= ruleCMSExtension EOF )
            // InternalEJSL.g:364:2: iv_ruleCMSExtension= ruleCMSExtension EOF
            {
             newCompositeNode(grammarAccess.getCMSExtensionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCMSExtension=ruleCMSExtension();

            state._fsp--;

             current =iv_ruleCMSExtension; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCMSExtension"


    // $ANTLR start "ruleCMSExtension"
    // InternalEJSL.g:370:1: ruleCMSExtension returns [EObject current=null] : ( () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}' ) ;
    public final EObject ruleCMSExtension() throws RecognitionException {
        EObject current = null;

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
        EObject lv_datatypes_5_0 = null;

        EObject lv_datatypes_7_0 = null;

        EObject lv_globalparameters_11_0 = null;

        EObject lv_parametergroups_15_0 = null;

        EObject lv_feature_17_0 = null;

        EObject lv_extensions_20_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:376:2: ( ( () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}' ) )
            // InternalEJSL.g:377:2: ( () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}' )
            {
            // InternalEJSL.g:377:2: ( () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}' )
            // InternalEJSL.g:378:3: () otherlv_1= 'CMS Extension' otherlv_2= '{' (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )? (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )? (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )? ( (lv_feature_17_0= ruleFeature ) ) (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )? otherlv_22= '}'
            {
            // InternalEJSL.g:378:3: ()
            // InternalEJSL.g:379:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getCMSExtensionAccess().getCMSExtensionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,24,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getCMSExtensionAccess().getCMSExtensionKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_16); 

            			newLeafNode(otherlv_2, grammarAccess.getCMSExtensionAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalEJSL.g:393:3: (otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==20) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalEJSL.g:394:4: otherlv_3= 'datatypes' otherlv_4= '{' ( (lv_datatypes_5_0= ruleDatatype ) ) (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )* otherlv_8= '}'
                    {
                    otherlv_3=(Token)match(input,20,FOLLOW_4); 

                    				newLeafNode(otherlv_3, grammarAccess.getCMSExtensionAccess().getDatatypesKeyword_3_0());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_9); 

                    				newLeafNode(otherlv_4, grammarAccess.getCMSExtensionAccess().getLeftCurlyBracketKeyword_3_1());
                    			
                    // InternalEJSL.g:402:4: ( (lv_datatypes_5_0= ruleDatatype ) )
                    // InternalEJSL.g:403:5: (lv_datatypes_5_0= ruleDatatype )
                    {
                    // InternalEJSL.g:403:5: (lv_datatypes_5_0= ruleDatatype )
                    // InternalEJSL.g:404:6: lv_datatypes_5_0= ruleDatatype
                    {

                    						newCompositeNode(grammarAccess.getCMSExtensionAccess().getDatatypesDatatypeParserRuleCall_3_2_0());
                    					
                    pushFollow(FOLLOW_10);
                    lv_datatypes_5_0=ruleDatatype();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getCMSExtensionRule());
                    						}
                    						add(
                    							current,
                    							"datatypes",
                    							lv_datatypes_5_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.Datatype");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:421:4: (otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) ) )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==21) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // InternalEJSL.g:422:5: otherlv_6= ',' ( (lv_datatypes_7_0= ruleDatatype ) )
                    	    {
                    	    otherlv_6=(Token)match(input,21,FOLLOW_9); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getCMSExtensionAccess().getCommaKeyword_3_3_0());
                    	    				
                    	    // InternalEJSL.g:426:5: ( (lv_datatypes_7_0= ruleDatatype ) )
                    	    // InternalEJSL.g:427:6: (lv_datatypes_7_0= ruleDatatype )
                    	    {
                    	    // InternalEJSL.g:427:6: (lv_datatypes_7_0= ruleDatatype )
                    	    // InternalEJSL.g:428:7: lv_datatypes_7_0= ruleDatatype
                    	    {

                    	    							newCompositeNode(grammarAccess.getCMSExtensionAccess().getDatatypesDatatypeParserRuleCall_3_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_10);
                    	    lv_datatypes_7_0=ruleDatatype();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getCMSExtensionRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"datatypes",
                    	    								lv_datatypes_7_0,
                    	    								"de.thm.icampus.joomdd.ejsl.EJSL.Datatype");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,18,FOLLOW_17); 

                    				newLeafNode(otherlv_8, grammarAccess.getCMSExtensionAccess().getRightCurlyBracketKeyword_3_4());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:451:3: (otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==22) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalEJSL.g:452:4: otherlv_9= 'globalparameters' otherlv_10= '{' ( (lv_globalparameters_11_0= ruleParameter ) )* otherlv_12= '}'
                    {
                    otherlv_9=(Token)match(input,22,FOLLOW_4); 

                    				newLeafNode(otherlv_9, grammarAccess.getCMSExtensionAccess().getGlobalparametersKeyword_4_0());
                    			
                    otherlv_10=(Token)match(input,16,FOLLOW_12); 

                    				newLeafNode(otherlv_10, grammarAccess.getCMSExtensionAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:460:4: ( (lv_globalparameters_11_0= ruleParameter ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==34) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalEJSL.g:461:5: (lv_globalparameters_11_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:461:5: (lv_globalparameters_11_0= ruleParameter )
                    	    // InternalEJSL.g:462:6: lv_globalparameters_11_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getCMSExtensionAccess().getGlobalparametersParameterParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_globalparameters_11_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCMSExtensionRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"globalparameters",
                    	    							lv_globalparameters_11_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,18,FOLLOW_18); 

                    				newLeafNode(otherlv_12, grammarAccess.getCMSExtensionAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:484:3: (otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==23) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalEJSL.g:485:4: otherlv_13= 'parametergroups' otherlv_14= '{' ( (lv_parametergroups_15_0= ruleParameterGroup ) )* otherlv_16= '}'
                    {
                    otherlv_13=(Token)match(input,23,FOLLOW_4); 

                    				newLeafNode(otherlv_13, grammarAccess.getCMSExtensionAccess().getParametergroupsKeyword_5_0());
                    			
                    otherlv_14=(Token)match(input,16,FOLLOW_14); 

                    				newLeafNode(otherlv_14, grammarAccess.getCMSExtensionAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:493:4: ( (lv_parametergroups_15_0= ruleParameterGroup ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==40) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // InternalEJSL.g:494:5: (lv_parametergroups_15_0= ruleParameterGroup )
                    	    {
                    	    // InternalEJSL.g:494:5: (lv_parametergroups_15_0= ruleParameterGroup )
                    	    // InternalEJSL.g:495:6: lv_parametergroups_15_0= ruleParameterGroup
                    	    {

                    	    						newCompositeNode(grammarAccess.getCMSExtensionAccess().getParametergroupsParameterGroupParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_14);
                    	    lv_parametergroups_15_0=ruleParameterGroup();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCMSExtensionRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"parametergroups",
                    	    							lv_parametergroups_15_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.ParameterGroup");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,18,FOLLOW_19); 

                    				newLeafNode(otherlv_16, grammarAccess.getCMSExtensionAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:517:3: ( (lv_feature_17_0= ruleFeature ) )
            // InternalEJSL.g:518:4: (lv_feature_17_0= ruleFeature )
            {
            // InternalEJSL.g:518:4: (lv_feature_17_0= ruleFeature )
            // InternalEJSL.g:519:5: lv_feature_17_0= ruleFeature
            {

            					newCompositeNode(grammarAccess.getCMSExtensionAccess().getFeatureFeatureParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_20);
            lv_feature_17_0=ruleFeature();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCMSExtensionRule());
            					}
            					set(
            						current,
            						"feature",
            						lv_feature_17_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Feature");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:536:3: (otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==25) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalEJSL.g:537:4: otherlv_18= 'extensions' otherlv_19= '{' ( (lv_extensions_20_0= ruleExtension ) )* otherlv_21= '}'
                    {
                    otherlv_18=(Token)match(input,25,FOLLOW_4); 

                    				newLeafNode(otherlv_18, grammarAccess.getCMSExtensionAccess().getExtensionsKeyword_7_0());
                    			
                    otherlv_19=(Token)match(input,16,FOLLOW_21); 

                    				newLeafNode(otherlv_19, grammarAccess.getCMSExtensionAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:545:4: ( (lv_extensions_20_0= ruleExtension ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==87||LA14_0==90||(LA14_0>=97 && LA14_0<=98)||LA14_0==101||LA14_0==112) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalEJSL.g:546:5: (lv_extensions_20_0= ruleExtension )
                    	    {
                    	    // InternalEJSL.g:546:5: (lv_extensions_20_0= ruleExtension )
                    	    // InternalEJSL.g:547:6: lv_extensions_20_0= ruleExtension
                    	    {

                    	    						newCompositeNode(grammarAccess.getCMSExtensionAccess().getExtensionsExtensionParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_21);
                    	    lv_extensions_20_0=ruleExtension();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCMSExtensionRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"extensions",
                    	    							lv_extensions_20_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Extension");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    otherlv_21=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_21, grammarAccess.getCMSExtensionAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            otherlv_22=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_22, grammarAccess.getCMSExtensionAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCMSExtension"


    // $ANTLR start "entryRuleFeature"
    // InternalEJSL.g:577:1: entryRuleFeature returns [EObject current=null] : iv_ruleFeature= ruleFeature EOF ;
    public final EObject entryRuleFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeature = null;


        try {
            // InternalEJSL.g:577:48: (iv_ruleFeature= ruleFeature EOF )
            // InternalEJSL.g:578:2: iv_ruleFeature= ruleFeature EOF
            {
             newCompositeNode(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeature=ruleFeature();

            state._fsp--;

             current =iv_ruleFeature; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalEJSL.g:584:1: ruleFeature returns [EObject current=null] : ( () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )? ) ;
    public final EObject ruleFeature() throws RecognitionException {
        EObject current = null;

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
        EObject lv_entitypackages_3_0 = null;

        EObject lv_entities_7_0 = null;

        EObject lv_pages_11_0 = null;

        EObject lv_sections_15_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:590:2: ( ( () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )? ) )
            // InternalEJSL.g:591:2: ( () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )? )
            {
            // InternalEJSL.g:591:2: ( () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )? )
            // InternalEJSL.g:592:3: () (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )? (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )? (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )? (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )?
            {
            // InternalEJSL.g:592:3: ()
            // InternalEJSL.g:593:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFeatureAccess().getFeatureAction_0(),
            					current);
            			

            }

            // InternalEJSL.g:599:3: (otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==26) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalEJSL.g:600:4: otherlv_1= 'entitypackages' otherlv_2= '{' ( (lv_entitypackages_3_0= ruleEntitypackage ) )* otherlv_4= '}'
                    {
                    otherlv_1=(Token)match(input,26,FOLLOW_4); 

                    				newLeafNode(otherlv_1, grammarAccess.getFeatureAccess().getEntitypackagesKeyword_1_0());
                    			
                    otherlv_2=(Token)match(input,16,FOLLOW_22); 

                    				newLeafNode(otherlv_2, grammarAccess.getFeatureAccess().getLeftCurlyBracketKeyword_1_1());
                    			
                    // InternalEJSL.g:608:4: ( (lv_entitypackages_3_0= ruleEntitypackage ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==44) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // InternalEJSL.g:609:5: (lv_entitypackages_3_0= ruleEntitypackage )
                    	    {
                    	    // InternalEJSL.g:609:5: (lv_entitypackages_3_0= ruleEntitypackage )
                    	    // InternalEJSL.g:610:6: lv_entitypackages_3_0= ruleEntitypackage
                    	    {

                    	    						newCompositeNode(grammarAccess.getFeatureAccess().getEntitypackagesEntitypackageParserRuleCall_1_2_0());
                    	    					
                    	    pushFollow(FOLLOW_22);
                    	    lv_entitypackages_3_0=ruleEntitypackage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getFeatureRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"entitypackages",
                    	    							lv_entitypackages_3_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Entitypackage");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    otherlv_4=(Token)match(input,18,FOLLOW_23); 

                    				newLeafNode(otherlv_4, grammarAccess.getFeatureAccess().getRightCurlyBracketKeyword_1_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:632:3: (otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==27) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalEJSL.g:633:4: otherlv_5= 'entities' otherlv_6= '{' ( (lv_entities_7_0= ruleEntity ) )* otherlv_8= '}'
                    {
                    otherlv_5=(Token)match(input,27,FOLLOW_4); 

                    				newLeafNode(otherlv_5, grammarAccess.getFeatureAccess().getEntitiesKeyword_2_0());
                    			
                    otherlv_6=(Token)match(input,16,FOLLOW_24); 

                    				newLeafNode(otherlv_6, grammarAccess.getFeatureAccess().getLeftCurlyBracketKeyword_2_1());
                    			
                    // InternalEJSL.g:641:4: ( (lv_entities_7_0= ruleEntity ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==45) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalEJSL.g:642:5: (lv_entities_7_0= ruleEntity )
                    	    {
                    	    // InternalEJSL.g:642:5: (lv_entities_7_0= ruleEntity )
                    	    // InternalEJSL.g:643:6: lv_entities_7_0= ruleEntity
                    	    {

                    	    						newCompositeNode(grammarAccess.getFeatureAccess().getEntitiesEntityParserRuleCall_2_2_0());
                    	    					
                    	    pushFollow(FOLLOW_24);
                    	    lv_entities_7_0=ruleEntity();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getFeatureRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"entities",
                    	    							lv_entities_7_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Entity");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,18,FOLLOW_25); 

                    				newLeafNode(otherlv_8, grammarAccess.getFeatureAccess().getRightCurlyBracketKeyword_2_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:665:3: (otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==28) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalEJSL.g:666:4: otherlv_9= 'pages' otherlv_10= '{' ( (lv_pages_11_0= rulePage ) )* otherlv_12= '}'
                    {
                    otherlv_9=(Token)match(input,28,FOLLOW_4); 

                    				newLeafNode(otherlv_9, grammarAccess.getFeatureAccess().getPagesKeyword_3_0());
                    			
                    otherlv_10=(Token)match(input,16,FOLLOW_26); 

                    				newLeafNode(otherlv_10, grammarAccess.getFeatureAccess().getLeftCurlyBracketKeyword_3_1());
                    			
                    // InternalEJSL.g:674:4: ( (lv_pages_11_0= rulePage ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==59||LA20_0==66||LA20_0==70||LA20_0==73) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // InternalEJSL.g:675:5: (lv_pages_11_0= rulePage )
                    	    {
                    	    // InternalEJSL.g:675:5: (lv_pages_11_0= rulePage )
                    	    // InternalEJSL.g:676:6: lv_pages_11_0= rulePage
                    	    {

                    	    						newCompositeNode(grammarAccess.getFeatureAccess().getPagesPageParserRuleCall_3_2_0());
                    	    					
                    	    pushFollow(FOLLOW_26);
                    	    lv_pages_11_0=rulePage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getFeatureRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"pages",
                    	    							lv_pages_11_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Page");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,18,FOLLOW_27); 

                    				newLeafNode(otherlv_12, grammarAccess.getFeatureAccess().getRightCurlyBracketKeyword_3_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:698:3: (otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==29) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalEJSL.g:699:4: otherlv_13= 'sections' otherlv_14= '{' ( (lv_sections_15_0= ruleSection ) )+ otherlv_16= '}'
                    {
                    otherlv_13=(Token)match(input,29,FOLLOW_4); 

                    				newLeafNode(otherlv_13, grammarAccess.getFeatureAccess().getSectionsKeyword_4_0());
                    			
                    otherlv_14=(Token)match(input,16,FOLLOW_28); 

                    				newLeafNode(otherlv_14, grammarAccess.getFeatureAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:707:4: ( (lv_sections_15_0= ruleSection ) )+
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
                    	    // InternalEJSL.g:708:5: (lv_sections_15_0= ruleSection )
                    	    {
                    	    // InternalEJSL.g:708:5: (lv_sections_15_0= ruleSection )
                    	    // InternalEJSL.g:709:6: lv_sections_15_0= ruleSection
                    	    {

                    	    						newCompositeNode(grammarAccess.getFeatureAccess().getSectionsSectionParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_29);
                    	    lv_sections_15_0=ruleSection();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getFeatureRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"sections",
                    	    							lv_sections_15_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Section");
                    	    						afterParserOrEnumRuleCall();
                    	    					

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

                    otherlv_16=(Token)match(input,18,FOLLOW_2); 

                    				newLeafNode(otherlv_16, grammarAccess.getFeatureAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleType"
    // InternalEJSL.g:735:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalEJSL.g:735:45: (iv_ruleType= ruleType EOF )
            // InternalEJSL.g:736:2: iv_ruleType= ruleType EOF
            {
             newCompositeNode(grammarAccess.getTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;

             current =iv_ruleType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalEJSL.g:742:1: ruleType returns [EObject current=null] : (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_DatatypeReference_0 = null;

        EObject this_StandardTypes_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:748:2: ( (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes ) )
            // InternalEJSL.g:749:2: (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes )
            {
            // InternalEJSL.g:749:2: (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes )
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
                    // InternalEJSL.g:750:3: this_DatatypeReference_0= ruleDatatypeReference
                    {

                    			newCompositeNode(grammarAccess.getTypeAccess().getDatatypeReferenceParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_DatatypeReference_0=ruleDatatypeReference();

                    state._fsp--;


                    			current = this_DatatypeReference_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEJSL.g:759:3: this_StandardTypes_1= ruleStandardTypes
                    {

                    			newCompositeNode(grammarAccess.getTypeAccess().getStandardTypesParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_StandardTypes_1=ruleStandardTypes();

                    state._fsp--;


                    			current = this_StandardTypes_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleDatatypeReference"
    // InternalEJSL.g:771:1: entryRuleDatatypeReference returns [EObject current=null] : iv_ruleDatatypeReference= ruleDatatypeReference EOF ;
    public final EObject entryRuleDatatypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatatypeReference = null;


        try {
            // InternalEJSL.g:771:58: (iv_ruleDatatypeReference= ruleDatatypeReference EOF )
            // InternalEJSL.g:772:2: iv_ruleDatatypeReference= ruleDatatypeReference EOF
            {
             newCompositeNode(grammarAccess.getDatatypeReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatatypeReference=ruleDatatypeReference();

            state._fsp--;

             current =iv_ruleDatatypeReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatatypeReference"


    // $ANTLR start "ruleDatatypeReference"
    // InternalEJSL.g:778:1: ruleDatatypeReference returns [EObject current=null] : ( (otherlv_0= RULE_STRING ) ) ;
    public final EObject ruleDatatypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;


        	enterRule();

        try {
            // InternalEJSL.g:784:2: ( ( (otherlv_0= RULE_STRING ) ) )
            // InternalEJSL.g:785:2: ( (otherlv_0= RULE_STRING ) )
            {
            // InternalEJSL.g:785:2: ( (otherlv_0= RULE_STRING ) )
            // InternalEJSL.g:786:3: (otherlv_0= RULE_STRING )
            {
            // InternalEJSL.g:786:3: (otherlv_0= RULE_STRING )
            // InternalEJSL.g:787:4: otherlv_0= RULE_STRING
            {

            				if (current==null) {
            					current = createModelElement(grammarAccess.getDatatypeReferenceRule());
            				}
            			
            otherlv_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            				newLeafNode(otherlv_0, grammarAccess.getDatatypeReferenceAccess().getTypeDatatypeCrossReference_0());
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDatatypeReference"


    // $ANTLR start "entryRuleStandardTypes"
    // InternalEJSL.g:801:1: entryRuleStandardTypes returns [EObject current=null] : iv_ruleStandardTypes= ruleStandardTypes EOF ;
    public final EObject entryRuleStandardTypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStandardTypes = null;


        try {
            // InternalEJSL.g:801:54: (iv_ruleStandardTypes= ruleStandardTypes EOF )
            // InternalEJSL.g:802:2: iv_ruleStandardTypes= ruleStandardTypes EOF
            {
             newCompositeNode(grammarAccess.getStandardTypesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStandardTypes=ruleStandardTypes();

            state._fsp--;

             current =iv_ruleStandardTypes; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStandardTypes"


    // $ANTLR start "ruleStandardTypes"
    // InternalEJSL.g:808:1: ruleStandardTypes returns [EObject current=null] : ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )? ) ;
    public final EObject ruleStandardTypes() throws RecognitionException {
        EObject current = null;

        Token lv_notnull_1_0=null;
        Token otherlv_2=null;
        Token lv_default_3_0=null;
        Token lv_autoincrement_4_0=null;
        Enumerator lv_type_0_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:814:2: ( ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )? ) )
            // InternalEJSL.g:815:2: ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )? )
            {
            // InternalEJSL.g:815:2: ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )? )
            // InternalEJSL.g:816:3: ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )? ( (lv_autoincrement_4_0= 'Auto Increment' ) )?
            {
            // InternalEJSL.g:816:3: ( (lv_type_0_0= ruleStandardTypeKinds ) )
            // InternalEJSL.g:817:4: (lv_type_0_0= ruleStandardTypeKinds )
            {
            // InternalEJSL.g:817:4: (lv_type_0_0= ruleStandardTypeKinds )
            // InternalEJSL.g:818:5: lv_type_0_0= ruleStandardTypeKinds
            {

            					newCompositeNode(grammarAccess.getStandardTypesAccess().getTypeStandardTypeKindsEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_30);
            lv_type_0_0=ruleStandardTypeKinds();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStandardTypesRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_0_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.StandardTypeKinds");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:835:3: ( (lv_notnull_1_0= 'Not Null' ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==30) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalEJSL.g:836:4: (lv_notnull_1_0= 'Not Null' )
                    {
                    // InternalEJSL.g:836:4: (lv_notnull_1_0= 'Not Null' )
                    // InternalEJSL.g:837:5: lv_notnull_1_0= 'Not Null'
                    {
                    lv_notnull_1_0=(Token)match(input,30,FOLLOW_31); 

                    					newLeafNode(lv_notnull_1_0, grammarAccess.getStandardTypesAccess().getNotnullNotNullKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getStandardTypesRule());
                    					}
                    					setWithLastConsumed(current, "notnull", true, "Not Null");
                    				

                    }


                    }
                    break;

            }

            // InternalEJSL.g:849:3: (otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==31) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalEJSL.g:850:4: otherlv_2= 'Default =' ( (lv_default_3_0= RULE_STRING ) )
                    {
                    otherlv_2=(Token)match(input,31,FOLLOW_3); 

                    				newLeafNode(otherlv_2, grammarAccess.getStandardTypesAccess().getDefaultKeyword_2_0());
                    			
                    // InternalEJSL.g:854:4: ( (lv_default_3_0= RULE_STRING ) )
                    // InternalEJSL.g:855:5: (lv_default_3_0= RULE_STRING )
                    {
                    // InternalEJSL.g:855:5: (lv_default_3_0= RULE_STRING )
                    // InternalEJSL.g:856:6: lv_default_3_0= RULE_STRING
                    {
                    lv_default_3_0=(Token)match(input,RULE_STRING,FOLLOW_32); 

                    						newLeafNode(lv_default_3_0, grammarAccess.getStandardTypesAccess().getDefaultSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getStandardTypesRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"default",
                    							lv_default_3_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:873:3: ( (lv_autoincrement_4_0= 'Auto Increment' ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==32) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalEJSL.g:874:4: (lv_autoincrement_4_0= 'Auto Increment' )
                    {
                    // InternalEJSL.g:874:4: (lv_autoincrement_4_0= 'Auto Increment' )
                    // InternalEJSL.g:875:5: lv_autoincrement_4_0= 'Auto Increment'
                    {
                    lv_autoincrement_4_0=(Token)match(input,32,FOLLOW_2); 

                    					newLeafNode(lv_autoincrement_4_0, grammarAccess.getStandardTypesAccess().getAutoincrementAutoIncrementKeyword_3_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getStandardTypesRule());
                    					}
                    					setWithLastConsumed(current, "autoincrement", true, "Auto Increment");
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStandardTypes"


    // $ANTLR start "entryRuleDatatype"
    // InternalEJSL.g:891:1: entryRuleDatatype returns [EObject current=null] : iv_ruleDatatype= ruleDatatype EOF ;
    public final EObject entryRuleDatatype() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatatype = null;


        try {
            // InternalEJSL.g:891:49: (iv_ruleDatatype= ruleDatatype EOF )
            // InternalEJSL.g:892:2: iv_ruleDatatype= ruleDatatype EOF
            {
             newCompositeNode(grammarAccess.getDatatypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatatype=ruleDatatype();

            state._fsp--;

             current =iv_ruleDatatype; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatatype"


    // $ANTLR start "ruleDatatype"
    // InternalEJSL.g:898:1: ruleDatatype returns [EObject current=null] : ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleDatatype() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;


        	enterRule();

        try {
            // InternalEJSL.g:904:2: ( ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) ) )
            // InternalEJSL.g:905:2: ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) )
            {
            // InternalEJSL.g:905:2: ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) )
            // InternalEJSL.g:906:3: () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) )
            {
            // InternalEJSL.g:906:3: ()
            // InternalEJSL.g:907:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDatatypeAccess().getDatatypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getDatatypeAccess().getDatatypeKeyword_1());
            		
            // InternalEJSL.g:917:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalEJSL.g:918:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalEJSL.g:918:4: (lv_name_2_0= RULE_STRING )
            // InternalEJSL.g:919:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_name_2_0, grammarAccess.getDatatypeAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDatatypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDatatype"


    // $ANTLR start "entryRuleParameter"
    // InternalEJSL.g:939:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalEJSL.g:939:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalEJSL.g:940:2: iv_ruleParameter= ruleParameter EOF
            {
             newCompositeNode(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalEJSL.g:946:1: ruleParameter returns [EObject current=null] : ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}' ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

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
        EObject lv_dtype_5_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:952:2: ( ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}' ) )
            // InternalEJSL.g:953:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}' )
            {
            // InternalEJSL.g:953:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}' )
            // InternalEJSL.g:954:3: () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_dtype_5_0= ruleType ) ) (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )? (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )? (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )? (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )? otherlv_14= '}'
            {
            // InternalEJSL.g:954:3: ()
            // InternalEJSL.g:955:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParameterAccess().getParameterAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,34,FOLLOW_33); 

            			newLeafNode(otherlv_1, grammarAccess.getParameterAccess().getParameterKeyword_1());
            		
            // InternalEJSL.g:965:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:966:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:966:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:967:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getParameterAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_34); 

            			newLeafNode(otherlv_3, grammarAccess.getParameterAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,35,FOLLOW_35); 

            			newLeafNode(otherlv_4, grammarAccess.getParameterAccess().getTypeKeyword_4());
            		
            // InternalEJSL.g:991:3: ( (lv_dtype_5_0= ruleType ) )
            // InternalEJSL.g:992:4: (lv_dtype_5_0= ruleType )
            {
            // InternalEJSL.g:992:4: (lv_dtype_5_0= ruleType )
            // InternalEJSL.g:993:5: lv_dtype_5_0= ruleType
            {

            					newCompositeNode(grammarAccess.getParameterAccess().getDtypeTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_36);
            lv_dtype_5_0=ruleType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParameterRule());
            					}
            					set(
            						current,
            						"dtype",
            						lv_dtype_5_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Type");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:1010:3: (otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==36) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalEJSL.g:1011:4: otherlv_6= 'defaultvalue =' ( (lv_defaultvalue_7_0= RULE_STRING ) )
                    {
                    otherlv_6=(Token)match(input,36,FOLLOW_3); 

                    				newLeafNode(otherlv_6, grammarAccess.getParameterAccess().getDefaultvalueKeyword_6_0());
                    			
                    // InternalEJSL.g:1015:4: ( (lv_defaultvalue_7_0= RULE_STRING ) )
                    // InternalEJSL.g:1016:5: (lv_defaultvalue_7_0= RULE_STRING )
                    {
                    // InternalEJSL.g:1016:5: (lv_defaultvalue_7_0= RULE_STRING )
                    // InternalEJSL.g:1017:6: lv_defaultvalue_7_0= RULE_STRING
                    {
                    lv_defaultvalue_7_0=(Token)match(input,RULE_STRING,FOLLOW_37); 

                    						newLeafNode(lv_defaultvalue_7_0, grammarAccess.getParameterAccess().getDefaultvalueSTRINGTerminalRuleCall_6_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"defaultvalue",
                    							lv_defaultvalue_7_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:1034:3: (otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==37) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalEJSL.g:1035:4: otherlv_8= 'label =' ( (lv_label_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,37,FOLLOW_3); 

                    				newLeafNode(otherlv_8, grammarAccess.getParameterAccess().getLabelKeyword_7_0());
                    			
                    // InternalEJSL.g:1039:4: ( (lv_label_9_0= RULE_STRING ) )
                    // InternalEJSL.g:1040:5: (lv_label_9_0= RULE_STRING )
                    {
                    // InternalEJSL.g:1040:5: (lv_label_9_0= RULE_STRING )
                    // InternalEJSL.g:1041:6: lv_label_9_0= RULE_STRING
                    {
                    lv_label_9_0=(Token)match(input,RULE_STRING,FOLLOW_38); 

                    						newLeafNode(lv_label_9_0, grammarAccess.getParameterAccess().getLabelSTRINGTerminalRuleCall_7_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"label",
                    							lv_label_9_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:1058:3: (otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==38) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalEJSL.g:1059:4: otherlv_10= 'size =' ( (lv_size_11_0= RULE_INT ) )
                    {
                    otherlv_10=(Token)match(input,38,FOLLOW_39); 

                    				newLeafNode(otherlv_10, grammarAccess.getParameterAccess().getSizeKeyword_8_0());
                    			
                    // InternalEJSL.g:1063:4: ( (lv_size_11_0= RULE_INT ) )
                    // InternalEJSL.g:1064:5: (lv_size_11_0= RULE_INT )
                    {
                    // InternalEJSL.g:1064:5: (lv_size_11_0= RULE_INT )
                    // InternalEJSL.g:1065:6: lv_size_11_0= RULE_INT
                    {
                    lv_size_11_0=(Token)match(input,RULE_INT,FOLLOW_40); 

                    						newLeafNode(lv_size_11_0, grammarAccess.getParameterAccess().getSizeINTTerminalRuleCall_8_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"size",
                    							lv_size_11_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:1082:3: (otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==39) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalEJSL.g:1083:4: otherlv_12= 'description =' ( (lv_descripton_13_0= RULE_STRING ) )
                    {
                    otherlv_12=(Token)match(input,39,FOLLOW_3); 

                    				newLeafNode(otherlv_12, grammarAccess.getParameterAccess().getDescriptionKeyword_9_0());
                    			
                    // InternalEJSL.g:1087:4: ( (lv_descripton_13_0= RULE_STRING ) )
                    // InternalEJSL.g:1088:5: (lv_descripton_13_0= RULE_STRING )
                    {
                    // InternalEJSL.g:1088:5: (lv_descripton_13_0= RULE_STRING )
                    // InternalEJSL.g:1089:6: lv_descripton_13_0= RULE_STRING
                    {
                    lv_descripton_13_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

                    						newLeafNode(lv_descripton_13_0, grammarAccess.getParameterAccess().getDescriptonSTRINGTerminalRuleCall_9_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"descripton",
                    							lv_descripton_13_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_14=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_14, grammarAccess.getParameterAccess().getRightCurlyBracketKeyword_10());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleParameterGroup"
    // InternalEJSL.g:1114:1: entryRuleParameterGroup returns [EObject current=null] : iv_ruleParameterGroup= ruleParameterGroup EOF ;
    public final EObject entryRuleParameterGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterGroup = null;


        try {
            // InternalEJSL.g:1114:55: (iv_ruleParameterGroup= ruleParameterGroup EOF )
            // InternalEJSL.g:1115:2: iv_ruleParameterGroup= ruleParameterGroup EOF
            {
             newCompositeNode(grammarAccess.getParameterGroupRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameterGroup=ruleParameterGroup();

            state._fsp--;

             current =iv_ruleParameterGroup; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterGroup"


    // $ANTLR start "ruleParameterGroup"
    // InternalEJSL.g:1121:1: ruleParameterGroup returns [EObject current=null] : ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}' ) ;
    public final EObject ruleParameterGroup() throws RecognitionException {
        EObject current = null;

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
        EObject lv_parameters_9_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1127:2: ( ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}' ) )
            // InternalEJSL.g:1128:2: ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}' )
            {
            // InternalEJSL.g:1128:2: ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}' )
            // InternalEJSL.g:1129:3: () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )? (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' ) otherlv_11= '}'
            {
            // InternalEJSL.g:1129:3: ()
            // InternalEJSL.g:1130:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParameterGroupAccess().getParameterGroupAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,40,FOLLOW_33); 

            			newLeafNode(otherlv_1, grammarAccess.getParameterGroupAccess().getParameterGroupKeyword_1());
            		
            // InternalEJSL.g:1140:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:1141:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:1141:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:1142:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getParameterGroupAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParameterGroupRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_41); 

            			newLeafNode(otherlv_3, grammarAccess.getParameterGroupAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:1162:3: (otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==37) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalEJSL.g:1163:4: otherlv_4= 'label =' ( (lv_label_5_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,37,FOLLOW_3); 

                    				newLeafNode(otherlv_4, grammarAccess.getParameterGroupAccess().getLabelKeyword_4_0());
                    			
                    // InternalEJSL.g:1167:4: ( (lv_label_5_0= RULE_STRING ) )
                    // InternalEJSL.g:1168:5: (lv_label_5_0= RULE_STRING )
                    {
                    // InternalEJSL.g:1168:5: (lv_label_5_0= RULE_STRING )
                    // InternalEJSL.g:1169:6: lv_label_5_0= RULE_STRING
                    {
                    lv_label_5_0=(Token)match(input,RULE_STRING,FOLLOW_42); 

                    						newLeafNode(lv_label_5_0, grammarAccess.getParameterGroupAccess().getLabelSTRINGTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterGroupRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"label",
                    							lv_label_5_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:1186:3: (otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}' )
            // InternalEJSL.g:1187:4: otherlv_6= 'Parameters' otherlv_7= '{' ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )* otherlv_10= '}'
            {
            otherlv_6=(Token)match(input,41,FOLLOW_4); 

            				newLeafNode(otherlv_6, grammarAccess.getParameterGroupAccess().getParametersKeyword_5_0());
            			
            otherlv_7=(Token)match(input,16,FOLLOW_43); 

            				newLeafNode(otherlv_7, grammarAccess.getParameterGroupAccess().getLeftCurlyBracketKeyword_5_1());
            			
            // InternalEJSL.g:1195:4: ( ( (otherlv_8= RULE_ID ) ) | ( (lv_parameters_9_0= ruleParameter ) ) )*
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
            	    // InternalEJSL.g:1196:5: ( (otherlv_8= RULE_ID ) )
            	    {
            	    // InternalEJSL.g:1196:5: ( (otherlv_8= RULE_ID ) )
            	    // InternalEJSL.g:1197:6: (otherlv_8= RULE_ID )
            	    {
            	    // InternalEJSL.g:1197:6: (otherlv_8= RULE_ID )
            	    // InternalEJSL.g:1198:7: otherlv_8= RULE_ID
            	    {

            	    							if (current==null) {
            	    								current = createModelElement(grammarAccess.getParameterGroupRule());
            	    							}
            	    						
            	    otherlv_8=(Token)match(input,RULE_ID,FOLLOW_43); 

            	    							newLeafNode(otherlv_8, grammarAccess.getParameterGroupAccess().getGlobalparametersParameterCrossReference_5_2_0_0());
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalEJSL.g:1210:5: ( (lv_parameters_9_0= ruleParameter ) )
            	    {
            	    // InternalEJSL.g:1210:5: ( (lv_parameters_9_0= ruleParameter ) )
            	    // InternalEJSL.g:1211:6: (lv_parameters_9_0= ruleParameter )
            	    {
            	    // InternalEJSL.g:1211:6: (lv_parameters_9_0= ruleParameter )
            	    // InternalEJSL.g:1212:7: lv_parameters_9_0= ruleParameter
            	    {

            	    							newCompositeNode(grammarAccess.getParameterGroupAccess().getParametersParameterParserRuleCall_5_2_1_0());
            	    						
            	    pushFollow(FOLLOW_43);
            	    lv_parameters_9_0=ruleParameter();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getParameterGroupRule());
            	    							}
            	    							add(
            	    								current,
            	    								"parameters",
            	    								lv_parameters_9_0,
            	    								"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            otherlv_10=(Token)match(input,18,FOLLOW_7); 

            				newLeafNode(otherlv_10, grammarAccess.getParameterGroupAccess().getRightCurlyBracketKeyword_5_3());
            			

            }

            otherlv_11=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getParameterGroupAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterGroup"


    // $ANTLR start "entryRulePageAction"
    // InternalEJSL.g:1243:1: entryRulePageAction returns [EObject current=null] : iv_rulePageAction= rulePageAction EOF ;
    public final EObject entryRulePageAction() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePageAction = null;


        try {
            // InternalEJSL.g:1243:51: (iv_rulePageAction= rulePageAction EOF )
            // InternalEJSL.g:1244:2: iv_rulePageAction= rulePageAction EOF
            {
             newCompositeNode(grammarAccess.getPageActionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePageAction=rulePageAction();

            state._fsp--;

             current =iv_rulePageAction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePageAction"


    // $ANTLR start "rulePageAction"
    // InternalEJSL.g:1250:1: rulePageAction returns [EObject current=null] : ( () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}' ) ;
    public final EObject rulePageAction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_pageActionType_5_0 = null;

        Enumerator lv_pageActionPosition_7_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1256:2: ( ( () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}' ) )
            // InternalEJSL.g:1257:2: ( () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}' )
            {
            // InternalEJSL.g:1257:2: ( () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}' )
            // InternalEJSL.g:1258:3: () otherlv_1= 'PageAction' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_pageActionType_5_0= rulePageActionKind ) ) otherlv_6= 'position =' ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) ) otherlv_8= '}'
            {
            // InternalEJSL.g:1258:3: ()
            // InternalEJSL.g:1259:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPageActionAccess().getPageActionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,42,FOLLOW_33); 

            			newLeafNode(otherlv_1, grammarAccess.getPageActionAccess().getPageActionKeyword_1());
            		
            // InternalEJSL.g:1269:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:1270:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:1270:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:1271:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getPageActionAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPageActionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_34); 

            			newLeafNode(otherlv_3, grammarAccess.getPageActionAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,35,FOLLOW_44); 

            			newLeafNode(otherlv_4, grammarAccess.getPageActionAccess().getTypeKeyword_4());
            		
            // InternalEJSL.g:1295:3: ( (lv_pageActionType_5_0= rulePageActionKind ) )
            // InternalEJSL.g:1296:4: (lv_pageActionType_5_0= rulePageActionKind )
            {
            // InternalEJSL.g:1296:4: (lv_pageActionType_5_0= rulePageActionKind )
            // InternalEJSL.g:1297:5: lv_pageActionType_5_0= rulePageActionKind
            {

            					newCompositeNode(grammarAccess.getPageActionAccess().getPageActionTypePageActionKindEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_45);
            lv_pageActionType_5_0=rulePageActionKind();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPageActionRule());
            					}
            					set(
            						current,
            						"pageActionType",
            						lv_pageActionType_5_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.PageActionKind");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,43,FOLLOW_46); 

            			newLeafNode(otherlv_6, grammarAccess.getPageActionAccess().getPositionKeyword_6());
            		
            // InternalEJSL.g:1318:3: ( (lv_pageActionPosition_7_0= rulePageActionPositionKind ) )
            // InternalEJSL.g:1319:4: (lv_pageActionPosition_7_0= rulePageActionPositionKind )
            {
            // InternalEJSL.g:1319:4: (lv_pageActionPosition_7_0= rulePageActionPositionKind )
            // InternalEJSL.g:1320:5: lv_pageActionPosition_7_0= rulePageActionPositionKind
            {

            					newCompositeNode(grammarAccess.getPageActionAccess().getPageActionPositionPageActionPositionKindEnumRuleCall_7_0());
            				
            pushFollow(FOLLOW_7);
            lv_pageActionPosition_7_0=rulePageActionPositionKind();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPageActionRule());
            					}
            					set(
            						current,
            						"pageActionPosition",
            						lv_pageActionPosition_7_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.PageActionPositionKind");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getPageActionAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageAction"


    // $ANTLR start "entryRuleEntitypackage"
    // InternalEJSL.g:1345:1: entryRuleEntitypackage returns [EObject current=null] : iv_ruleEntitypackage= ruleEntitypackage EOF ;
    public final EObject entryRuleEntitypackage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntitypackage = null;


        try {
            // InternalEJSL.g:1345:54: (iv_ruleEntitypackage= ruleEntitypackage EOF )
            // InternalEJSL.g:1346:2: iv_ruleEntitypackage= ruleEntitypackage EOF
            {
             newCompositeNode(grammarAccess.getEntitypackageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntitypackage=ruleEntitypackage();

            state._fsp--;

             current =iv_ruleEntitypackage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEntitypackage"


    // $ANTLR start "ruleEntitypackage"
    // InternalEJSL.g:1352:1: ruleEntitypackage returns [EObject current=null] : ( () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' ) ;
    public final EObject ruleEntitypackage() throws RecognitionException {
        EObject current = null;

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
        EObject lv_entitypackages_6_0 = null;

        EObject lv_entities_10_0 = null;

        EObject lv_datatypes_14_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1358:2: ( ( () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' ) )
            // InternalEJSL.g:1359:2: ( () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' )
            {
            // InternalEJSL.g:1359:2: ( () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' )
            // InternalEJSL.g:1360:3: () otherlv_1= 'Entitypackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}'
            {
            // InternalEJSL.g:1360:3: ()
            // InternalEJSL.g:1361:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEntitypackageAccess().getEntitypackageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,44,FOLLOW_33); 

            			newLeafNode(otherlv_1, grammarAccess.getEntitypackageAccess().getEntitypackageKeyword_1());
            		
            // InternalEJSL.g:1371:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:1372:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:1372:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:1373:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getEntitypackageAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEntitypackageRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_47); 

            			newLeafNode(otherlv_3, grammarAccess.getEntitypackageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:1393:3: (otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==26) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalEJSL.g:1394:4: otherlv_4= 'entitypackages' otherlv_5= '{' ( (lv_entitypackages_6_0= ruleEntitypackage ) )* otherlv_7= '}'
                    {
                    otherlv_4=(Token)match(input,26,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getEntitypackageAccess().getEntitypackagesKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_22); 

                    				newLeafNode(otherlv_5, grammarAccess.getEntitypackageAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:1402:4: ( (lv_entitypackages_6_0= ruleEntitypackage ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==44) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // InternalEJSL.g:1403:5: (lv_entitypackages_6_0= ruleEntitypackage )
                    	    {
                    	    // InternalEJSL.g:1403:5: (lv_entitypackages_6_0= ruleEntitypackage )
                    	    // InternalEJSL.g:1404:6: lv_entitypackages_6_0= ruleEntitypackage
                    	    {

                    	    						newCompositeNode(grammarAccess.getEntitypackageAccess().getEntitypackagesEntitypackageParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_22);
                    	    lv_entitypackages_6_0=ruleEntitypackage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEntitypackageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"entitypackages",
                    	    							lv_entitypackages_6_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Entitypackage");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop34;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,18,FOLLOW_48); 

                    				newLeafNode(otherlv_7, grammarAccess.getEntitypackageAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:1426:3: (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==27) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalEJSL.g:1427:4: otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,27,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getEntitypackageAccess().getEntitiesKeyword_5_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_24); 

                    				newLeafNode(otherlv_9, grammarAccess.getEntitypackageAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:1435:4: ( (lv_entities_10_0= ruleEntity ) )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==45) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // InternalEJSL.g:1436:5: (lv_entities_10_0= ruleEntity )
                    	    {
                    	    // InternalEJSL.g:1436:5: (lv_entities_10_0= ruleEntity )
                    	    // InternalEJSL.g:1437:6: lv_entities_10_0= ruleEntity
                    	    {

                    	    						newCompositeNode(grammarAccess.getEntitypackageAccess().getEntitiesEntityParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_24);
                    	    lv_entities_10_0=ruleEntity();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEntitypackageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"entities",
                    	    							lv_entities_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Entity");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_49); 

                    				newLeafNode(otherlv_11, grammarAccess.getEntitypackageAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:1459:3: (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==20) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalEJSL.g:1460:4: otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}'
                    {
                    otherlv_12=(Token)match(input,20,FOLLOW_4); 

                    				newLeafNode(otherlv_12, grammarAccess.getEntitypackageAccess().getDatatypesKeyword_6_0());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_50); 

                    				newLeafNode(otherlv_13, grammarAccess.getEntitypackageAccess().getLeftCurlyBracketKeyword_6_1());
                    			
                    // InternalEJSL.g:1468:4: ( (lv_datatypes_14_0= ruleDatatype ) )*
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==33) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // InternalEJSL.g:1469:5: (lv_datatypes_14_0= ruleDatatype )
                    	    {
                    	    // InternalEJSL.g:1469:5: (lv_datatypes_14_0= ruleDatatype )
                    	    // InternalEJSL.g:1470:6: lv_datatypes_14_0= ruleDatatype
                    	    {

                    	    						newCompositeNode(grammarAccess.getEntitypackageAccess().getDatatypesDatatypeParserRuleCall_6_2_0());
                    	    					
                    	    pushFollow(FOLLOW_50);
                    	    lv_datatypes_14_0=ruleDatatype();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEntitypackageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"datatypes",
                    	    							lv_datatypes_14_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Datatype");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop38;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_15, grammarAccess.getEntitypackageAccess().getRightCurlyBracketKeyword_6_3());
                    			

                    }
                    break;

            }

            otherlv_16=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_16, grammarAccess.getEntitypackageAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEntitypackage"


    // $ANTLR start "entryRuleEntity"
    // InternalEJSL.g:1500:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // InternalEJSL.g:1500:47: (iv_ruleEntity= ruleEntity EOF )
            // InternalEJSL.g:1501:2: iv_ruleEntity= ruleEntity EOF
            {
             newCompositeNode(grammarAccess.getEntityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntity=ruleEntity();

            state._fsp--;

             current =iv_ruleEntity; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEntity"


    // $ANTLR start "ruleEntity"
    // InternalEJSL.g:1507:1: ruleEntity returns [EObject current=null] : ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_attributes_8_0 = null;

        EObject lv_references_12_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1513:2: ( ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' ) )
            // InternalEJSL.g:1514:2: ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' )
            {
            // InternalEJSL.g:1514:2: ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' )
            // InternalEJSL.g:1515:3: () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}'
            {
            // InternalEJSL.g:1515:3: ()
            // InternalEJSL.g:1516:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEntityAccess().getEntityAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,45,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getEntityAccess().getEntityKeyword_1());
            		
            // InternalEJSL.g:1526:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:1527:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:1527:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:1528:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getEntityAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_52);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEntityRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:1545:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==46) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalEJSL.g:1546:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {
                    otherlv_3=(Token)match(input,46,FOLLOW_51); 

                    				newLeafNode(otherlv_3, grammarAccess.getEntityAccess().getExtendsKeyword_3_0());
                    			
                    // InternalEJSL.g:1550:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:1551:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:1551:5: ( ruleQualifiedName )
                    // InternalEJSL.g:1552:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getEntityRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getEntityAccess().getSupertypeEntityCrossReference_3_1_0());
                    					
                    pushFollow(FOLLOW_4);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,16,FOLLOW_53); 

            			newLeafNode(otherlv_5, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:1571:3: (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==47) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // InternalEJSL.g:1572:4: otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}'
                    {
                    otherlv_6=(Token)match(input,47,FOLLOW_4); 

                    				newLeafNode(otherlv_6, grammarAccess.getEntityAccess().getAttributesKeyword_5_0());
                    			
                    otherlv_7=(Token)match(input,16,FOLLOW_54); 

                    				newLeafNode(otherlv_7, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:1580:4: ( (lv_attributes_8_0= ruleAttribute ) )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==49) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // InternalEJSL.g:1581:5: (lv_attributes_8_0= ruleAttribute )
                    	    {
                    	    // InternalEJSL.g:1581:5: (lv_attributes_8_0= ruleAttribute )
                    	    // InternalEJSL.g:1582:6: lv_attributes_8_0= ruleAttribute
                    	    {

                    	    						newCompositeNode(grammarAccess.getEntityAccess().getAttributesAttributeParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_54);
                    	    lv_attributes_8_0=ruleAttribute();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEntityRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"attributes",
                    	    							lv_attributes_8_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Attribute");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,18,FOLLOW_55); 

                    				newLeafNode(otherlv_9, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:1604:3: (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==48) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalEJSL.g:1605:4: otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}'
                    {
                    otherlv_10=(Token)match(input,48,FOLLOW_4); 

                    				newLeafNode(otherlv_10, grammarAccess.getEntityAccess().getReferencesKeyword_6_0());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_56); 

                    				newLeafNode(otherlv_11, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_6_1());
                    			
                    // InternalEJSL.g:1613:4: ( (lv_references_12_0= ruleReference ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==53) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // InternalEJSL.g:1614:5: (lv_references_12_0= ruleReference )
                    	    {
                    	    // InternalEJSL.g:1614:5: (lv_references_12_0= ruleReference )
                    	    // InternalEJSL.g:1615:6: lv_references_12_0= ruleReference
                    	    {

                    	    						newCompositeNode(grammarAccess.getEntityAccess().getReferencesReferenceParserRuleCall_6_2_0());
                    	    					
                    	    pushFollow(FOLLOW_56);
                    	    lv_references_12_0=ruleReference();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEntityRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"references",
                    	    							lv_references_12_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Reference");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop43;
                        }
                    } while (true);

                    otherlv_13=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_13, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_6_3());
                    			

                    }
                    break;

            }

            otherlv_14=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_14, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEntity"


    // $ANTLR start "entryRuleAttribute"
    // InternalEJSL.g:1645:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // InternalEJSL.g:1645:50: (iv_ruleAttribute= ruleAttribute EOF )
            // InternalEJSL.g:1646:2: iv_ruleAttribute= ruleAttribute EOF
            {
             newCompositeNode(grammarAccess.getAttributeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;

             current =iv_ruleAttribute; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // InternalEJSL.g:1652:1: ruleAttribute returns [EObject current=null] : ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}' ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_isunique_6_0=null;
        Token otherlv_7=null;
        Token lv_id_9_0=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_type_5_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1658:2: ( ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}' ) )
            // InternalEJSL.g:1659:2: ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}' )
            {
            // InternalEJSL.g:1659:2: ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}' )
            // InternalEJSL.g:1660:3: () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type =' ( (lv_type_5_0= ruleType ) ) ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )? otherlv_10= '}'
            {
            // InternalEJSL.g:1660:3: ()
            // InternalEJSL.g:1661:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAttributeAccess().getAttributeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,49,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getAttributeAccess().getAttributeKeyword_1());
            		
            // InternalEJSL.g:1671:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:1672:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:1672:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:1673:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getAttributeAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAttributeRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_34); 

            			newLeafNode(otherlv_3, grammarAccess.getAttributeAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,35,FOLLOW_35); 

            			newLeafNode(otherlv_4, grammarAccess.getAttributeAccess().getTypeKeyword_4());
            		
            // InternalEJSL.g:1698:3: ( (lv_type_5_0= ruleType ) )
            // InternalEJSL.g:1699:4: (lv_type_5_0= ruleType )
            {
            // InternalEJSL.g:1699:4: (lv_type_5_0= ruleType )
            // InternalEJSL.g:1700:5: lv_type_5_0= ruleType
            {

            					newCompositeNode(grammarAccess.getAttributeAccess().getTypeTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_57);
            lv_type_5_0=ruleType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAttributeRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_5_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Type");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:1717:3: ( ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )? )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==50) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalEJSL.g:1718:4: ( (lv_isunique_6_0= 'Unique attribute' ) ) (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )?
                    {
                    // InternalEJSL.g:1718:4: ( (lv_isunique_6_0= 'Unique attribute' ) )
                    // InternalEJSL.g:1719:5: (lv_isunique_6_0= 'Unique attribute' )
                    {
                    // InternalEJSL.g:1719:5: (lv_isunique_6_0= 'Unique attribute' )
                    // InternalEJSL.g:1720:6: lv_isunique_6_0= 'Unique attribute'
                    {
                    lv_isunique_6_0=(Token)match(input,50,FOLLOW_58); 

                    						newLeafNode(lv_isunique_6_0, grammarAccess.getAttributeAccess().getIsuniqueUniqueAttributeKeyword_6_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(current, "isunique", true, "Unique attribute");
                    					

                    }


                    }

                    // InternalEJSL.g:1732:4: (otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) ) )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==51) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // InternalEJSL.g:1733:5: otherlv_7= 'with' ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) )
                            {
                            otherlv_7=(Token)match(input,51,FOLLOW_59); 

                            					newLeafNode(otherlv_7, grammarAccess.getAttributeAccess().getWithKeyword_6_1_0());
                            				
                            // InternalEJSL.g:1737:5: ( ( ( ruleQualifiedName ) ) | ( (lv_id_9_0= 'ID' ) ) )
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
                                    // InternalEJSL.g:1738:6: ( ( ruleQualifiedName ) )
                                    {
                                    // InternalEJSL.g:1738:6: ( ( ruleQualifiedName ) )
                                    // InternalEJSL.g:1739:7: ( ruleQualifiedName )
                                    {
                                    // InternalEJSL.g:1739:7: ( ruleQualifiedName )
                                    // InternalEJSL.g:1740:8: ruleQualifiedName
                                    {

                                    								if (current==null) {
                                    									current = createModelElement(grammarAccess.getAttributeRule());
                                    								}
                                    							

                                    								newCompositeNode(grammarAccess.getAttributeAccess().getWithattributeAttributeCrossReference_6_1_1_0_0());
                                    							
                                    pushFollow(FOLLOW_7);
                                    ruleQualifiedName();

                                    state._fsp--;


                                    								afterParserOrEnumRuleCall();
                                    							

                                    }


                                    }


                                    }
                                    break;
                                case 2 :
                                    // InternalEJSL.g:1755:6: ( (lv_id_9_0= 'ID' ) )
                                    {
                                    // InternalEJSL.g:1755:6: ( (lv_id_9_0= 'ID' ) )
                                    // InternalEJSL.g:1756:7: (lv_id_9_0= 'ID' )
                                    {
                                    // InternalEJSL.g:1756:7: (lv_id_9_0= 'ID' )
                                    // InternalEJSL.g:1757:8: lv_id_9_0= 'ID'
                                    {
                                    lv_id_9_0=(Token)match(input,52,FOLLOW_7); 

                                    								newLeafNode(lv_id_9_0, grammarAccess.getAttributeAccess().getIdIDKeyword_6_1_1_1_0());
                                    							

                                    								if (current==null) {
                                    									current = createModelElement(grammarAccess.getAttributeRule());
                                    								}
                                    								setWithLastConsumed(current, "id", true, "ID");
                                    							

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

            otherlv_10=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getAttributeAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleReference"
    // InternalEJSL.g:1780:1: entryRuleReference returns [EObject current=null] : iv_ruleReference= ruleReference EOF ;
    public final EObject entryRuleReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReference = null;


        try {
            // InternalEJSL.g:1780:50: (iv_ruleReference= ruleReference EOF )
            // InternalEJSL.g:1781:2: iv_ruleReference= ruleReference EOF
            {
             newCompositeNode(grammarAccess.getReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleReference=ruleReference();

            state._fsp--;

             current =iv_ruleReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReference"


    // $ANTLR start "ruleReference"
    // InternalEJSL.g:1787:1: ruleReference returns [EObject current=null] : ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}' ) ;
    public final EObject ruleReference() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_lower_14_0 = null;

        AntlrDatatypeRuleToken lv_upper_16_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1793:2: ( ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}' ) )
            // InternalEJSL.g:1794:2: ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}' )
            {
            // InternalEJSL.g:1794:2: ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}' )
            // InternalEJSL.g:1795:3: () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'EntityAttribute =' ( ( ruleQualifiedName ) ) (otherlv_5= ',' ( ( ruleQualifiedName ) ) )* otherlv_7= '*Entity =' ( ( ruleQualifiedName ) ) otherlv_9= '*EntityReference =' ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) ) ( ( ruleQualifiedName ) )* otherlv_13= 'lower =' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper =' ( (lv_upper_16_0= ruleNUMBER ) ) otherlv_17= '}'
            {
            // InternalEJSL.g:1795:3: ()
            // InternalEJSL.g:1796:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getReferenceAccess().getReferenceAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,53,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getReferenceAccess().getReferenceKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_60); 

            			newLeafNode(otherlv_2, grammarAccess.getReferenceAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,54,FOLLOW_51); 

            			newLeafNode(otherlv_3, grammarAccess.getReferenceAccess().getEntityAttributeKeyword_3());
            		
            // InternalEJSL.g:1814:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:1815:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:1815:4: ( ruleQualifiedName )
            // InternalEJSL.g:1816:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceRule());
            					}
            				

            					newCompositeNode(grammarAccess.getReferenceAccess().getAttributeAttributeCrossReference_4_0());
            				
            pushFollow(FOLLOW_61);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:1830:3: (otherlv_5= ',' ( ( ruleQualifiedName ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( (LA48_0==21) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalEJSL.g:1831:4: otherlv_5= ',' ( ( ruleQualifiedName ) )
            	    {
            	    otherlv_5=(Token)match(input,21,FOLLOW_51); 

            	    				newLeafNode(otherlv_5, grammarAccess.getReferenceAccess().getCommaKeyword_5_0());
            	    			
            	    // InternalEJSL.g:1835:4: ( ( ruleQualifiedName ) )
            	    // InternalEJSL.g:1836:5: ( ruleQualifiedName )
            	    {
            	    // InternalEJSL.g:1836:5: ( ruleQualifiedName )
            	    // InternalEJSL.g:1837:6: ruleQualifiedName
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getReferenceRule());
            	    						}
            	    					

            	    						newCompositeNode(grammarAccess.getReferenceAccess().getAttributeAttributeCrossReference_5_1_0());
            	    					
            	    pushFollow(FOLLOW_61);
            	    ruleQualifiedName();

            	    state._fsp--;


            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);

            otherlv_7=(Token)match(input,55,FOLLOW_51); 

            			newLeafNode(otherlv_7, grammarAccess.getReferenceAccess().getEntityKeyword_6());
            		
            // InternalEJSL.g:1856:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:1857:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:1857:4: ( ruleQualifiedName )
            // InternalEJSL.g:1858:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceRule());
            					}
            				

            					newCompositeNode(grammarAccess.getReferenceAccess().getEntityEntityCrossReference_7_0());
            				
            pushFollow(FOLLOW_62);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,56,FOLLOW_59); 

            			newLeafNode(otherlv_9, grammarAccess.getReferenceAccess().getEntityReferenceKeyword_8());
            		
            // InternalEJSL.g:1876:3: ( ( ( ruleQualifiedName ) ) | ( (lv_id_11_0= 'ID' ) ) )
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
                    // InternalEJSL.g:1877:4: ( ( ruleQualifiedName ) )
                    {
                    // InternalEJSL.g:1877:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:1878:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:1878:5: ( ruleQualifiedName )
                    // InternalEJSL.g:1879:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getReferenceRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getReferenceAccess().getAttributereferecedAttributeCrossReference_9_0_0());
                    					
                    pushFollow(FOLLOW_63);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:1894:4: ( (lv_id_11_0= 'ID' ) )
                    {
                    // InternalEJSL.g:1894:4: ( (lv_id_11_0= 'ID' ) )
                    // InternalEJSL.g:1895:5: (lv_id_11_0= 'ID' )
                    {
                    // InternalEJSL.g:1895:5: (lv_id_11_0= 'ID' )
                    // InternalEJSL.g:1896:6: lv_id_11_0= 'ID'
                    {
                    lv_id_11_0=(Token)match(input,52,FOLLOW_63); 

                    						newLeafNode(lv_id_11_0, grammarAccess.getReferenceAccess().getIdIDKeyword_9_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getReferenceRule());
                    						}
                    						setWithLastConsumed(current, "id", true, "ID");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:1909:3: ( ( ruleQualifiedName ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==RULE_ID||LA50_0==136) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // InternalEJSL.g:1910:4: ( ruleQualifiedName )
            	    {
            	    // InternalEJSL.g:1910:4: ( ruleQualifiedName )
            	    // InternalEJSL.g:1911:5: ruleQualifiedName
            	    {

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getReferenceRule());
            	    					}
            	    				

            	    					newCompositeNode(grammarAccess.getReferenceAccess().getAttributereferecedAttributeCrossReference_10_0());
            	    				
            	    pushFollow(FOLLOW_63);
            	    ruleQualifiedName();

            	    state._fsp--;


            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);

            otherlv_13=(Token)match(input,57,FOLLOW_64); 

            			newLeafNode(otherlv_13, grammarAccess.getReferenceAccess().getLowerKeyword_11());
            		
            // InternalEJSL.g:1929:3: ( (lv_lower_14_0= ruleNUMBER ) )
            // InternalEJSL.g:1930:4: (lv_lower_14_0= ruleNUMBER )
            {
            // InternalEJSL.g:1930:4: (lv_lower_14_0= ruleNUMBER )
            // InternalEJSL.g:1931:5: lv_lower_14_0= ruleNUMBER
            {

            					newCompositeNode(grammarAccess.getReferenceAccess().getLowerNUMBERParserRuleCall_12_0());
            				
            pushFollow(FOLLOW_65);
            lv_lower_14_0=ruleNUMBER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getReferenceRule());
            					}
            					set(
            						current,
            						"lower",
            						lv_lower_14_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.NUMBER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_15=(Token)match(input,58,FOLLOW_64); 

            			newLeafNode(otherlv_15, grammarAccess.getReferenceAccess().getUpperKeyword_13());
            		
            // InternalEJSL.g:1952:3: ( (lv_upper_16_0= ruleNUMBER ) )
            // InternalEJSL.g:1953:4: (lv_upper_16_0= ruleNUMBER )
            {
            // InternalEJSL.g:1953:4: (lv_upper_16_0= ruleNUMBER )
            // InternalEJSL.g:1954:5: lv_upper_16_0= ruleNUMBER
            {

            					newCompositeNode(grammarAccess.getReferenceAccess().getUpperNUMBERParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_7);
            lv_upper_16_0=ruleNUMBER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getReferenceRule());
            					}
            					set(
            						current,
            						"upper",
            						lv_upper_16_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.NUMBER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_17=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_17, grammarAccess.getReferenceAccess().getRightCurlyBracketKeyword_15());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReference"


    // $ANTLR start "entryRulePage"
    // InternalEJSL.g:1979:1: entryRulePage returns [EObject current=null] : iv_rulePage= rulePage EOF ;
    public final EObject entryRulePage() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePage = null;


        try {
            // InternalEJSL.g:1979:45: (iv_rulePage= rulePage EOF )
            // InternalEJSL.g:1980:2: iv_rulePage= rulePage EOF
            {
             newCompositeNode(grammarAccess.getPageRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePage=rulePage();

            state._fsp--;

             current =iv_rulePage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePage"


    // $ANTLR start "rulePage"
    // InternalEJSL.g:1986:1: rulePage returns [EObject current=null] : (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage | this_CustomPage_2= ruleCustomPage ) ;
    public final EObject rulePage() throws RecognitionException {
        EObject current = null;

        EObject this_StaticPage_0 = null;

        EObject this_DynamicPage_1 = null;

        EObject this_CustomPage_2 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1992:2: ( (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage | this_CustomPage_2= ruleCustomPage ) )
            // InternalEJSL.g:1993:2: (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage | this_CustomPage_2= ruleCustomPage )
            {
            // InternalEJSL.g:1993:2: (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage | this_CustomPage_2= ruleCustomPage )
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
                    // InternalEJSL.g:1994:3: this_StaticPage_0= ruleStaticPage
                    {

                    			newCompositeNode(grammarAccess.getPageAccess().getStaticPageParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_StaticPage_0=ruleStaticPage();

                    state._fsp--;


                    			current = this_StaticPage_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEJSL.g:2003:3: this_DynamicPage_1= ruleDynamicPage
                    {

                    			newCompositeNode(grammarAccess.getPageAccess().getDynamicPageParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_DynamicPage_1=ruleDynamicPage();

                    state._fsp--;


                    			current = this_DynamicPage_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalEJSL.g:2012:3: this_CustomPage_2= ruleCustomPage
                    {

                    			newCompositeNode(grammarAccess.getPageAccess().getCustomPageParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_CustomPage_2=ruleCustomPage();

                    state._fsp--;


                    			current = this_CustomPage_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePage"


    // $ANTLR start "entryRuleStaticPage"
    // InternalEJSL.g:2024:1: entryRuleStaticPage returns [EObject current=null] : iv_ruleStaticPage= ruleStaticPage EOF ;
    public final EObject entryRuleStaticPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStaticPage = null;


        try {
            // InternalEJSL.g:2024:51: (iv_ruleStaticPage= ruleStaticPage EOF )
            // InternalEJSL.g:2025:2: iv_ruleStaticPage= ruleStaticPage EOF
            {
             newCompositeNode(grammarAccess.getStaticPageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStaticPage=ruleStaticPage();

            state._fsp--;

             current =iv_ruleStaticPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStaticPage"


    // $ANTLR start "ruleStaticPage"
    // InternalEJSL.g:2031:1: ruleStaticPage returns [EObject current=null] : ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}' ) ;
    public final EObject ruleStaticPage() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_localparameters_14_0 = null;

        EObject lv_pageactions_18_0 = null;

        EObject lv_links_22_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2037:2: ( ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}' ) )
            // InternalEJSL.g:2038:2: ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}' )
            {
            // InternalEJSL.g:2038:2: ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}' )
            // InternalEJSL.g:2039:3: () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )? (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )? otherlv_24= 'HTMLBody' otherlv_25= '{' ( (lv_HTMLBody_26_0= RULE_STRING ) ) otherlv_27= '}' otherlv_28= '}'
            {
            // InternalEJSL.g:2039:3: ()
            // InternalEJSL.g:2040:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStaticPageAccess().getStaticPageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,59,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getStaticPageAccess().getStaticPageKeyword_1());
            		
            // InternalEJSL.g:2050:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:2051:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:2051:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:2052:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getStaticPageAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getStaticPageRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_66); 

            			newLeafNode(otherlv_3, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:2073:3: (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==60) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // InternalEJSL.g:2074:4: otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )*
                    {
                    otherlv_4=(Token)match(input,60,FOLLOW_33); 

                    				newLeafNode(otherlv_4, grammarAccess.getStaticPageAccess().getParameterGroupsKeyword_4_0());
                    			
                    // InternalEJSL.g:2078:4: ( (otherlv_5= RULE_ID ) )
                    // InternalEJSL.g:2079:5: (otherlv_5= RULE_ID )
                    {
                    // InternalEJSL.g:2079:5: (otherlv_5= RULE_ID )
                    // InternalEJSL.g:2080:6: otherlv_5= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getStaticPageRule());
                    						}
                    					
                    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_67); 

                    						newLeafNode(otherlv_5, grammarAccess.getStaticPageAccess().getParametergroupsParameterGroupCrossReference_4_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:2091:4: (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==21) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // InternalEJSL.g:2092:5: otherlv_6= ',' ( (otherlv_7= RULE_ID ) )
                    	    {
                    	    otherlv_6=(Token)match(input,21,FOLLOW_33); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getStaticPageAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalEJSL.g:2096:5: ( (otherlv_7= RULE_ID ) )
                    	    // InternalEJSL.g:2097:6: (otherlv_7= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:2097:6: (otherlv_7= RULE_ID )
                    	    // InternalEJSL.g:2098:7: otherlv_7= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getStaticPageRule());
                    	    							}
                    	    						
                    	    otherlv_7=(Token)match(input,RULE_ID,FOLLOW_67); 

                    	    							newLeafNode(otherlv_7, grammarAccess.getStaticPageAccess().getParametergroupsParameterGroupCrossReference_4_2_1_0());
                    	    						

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

            // InternalEJSL.g:2111:3: (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==61) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // InternalEJSL.g:2112:4: otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    {
                    otherlv_8=(Token)match(input,61,FOLLOW_33); 

                    				newLeafNode(otherlv_8, grammarAccess.getStaticPageAccess().getGlobalparametersKeyword_5_0());
                    			
                    // InternalEJSL.g:2116:4: ( (otherlv_9= RULE_ID ) )
                    // InternalEJSL.g:2117:5: (otherlv_9= RULE_ID )
                    {
                    // InternalEJSL.g:2117:5: (otherlv_9= RULE_ID )
                    // InternalEJSL.g:2118:6: otherlv_9= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getStaticPageRule());
                    						}
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_68); 

                    						newLeafNode(otherlv_9, grammarAccess.getStaticPageAccess().getGlobalparametersParameterCrossReference_5_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:2129:4: (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==21) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // InternalEJSL.g:2130:5: otherlv_10= ',' ( (otherlv_11= RULE_ID ) )
                    	    {
                    	    otherlv_10=(Token)match(input,21,FOLLOW_33); 

                    	    					newLeafNode(otherlv_10, grammarAccess.getStaticPageAccess().getCommaKeyword_5_2_0());
                    	    				
                    	    // InternalEJSL.g:2134:5: ( (otherlv_11= RULE_ID ) )
                    	    // InternalEJSL.g:2135:6: (otherlv_11= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:2135:6: (otherlv_11= RULE_ID )
                    	    // InternalEJSL.g:2136:7: otherlv_11= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getStaticPageRule());
                    	    							}
                    	    						
                    	    otherlv_11=(Token)match(input,RULE_ID,FOLLOW_68); 

                    	    							newLeafNode(otherlv_11, grammarAccess.getStaticPageAccess().getGlobalparametersParameterCrossReference_5_2_1_0());
                    	    						

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

            // InternalEJSL.g:2149:3: (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==62) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // InternalEJSL.g:2150:4: otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}'
                    {
                    otherlv_12=(Token)match(input,62,FOLLOW_4); 

                    				newLeafNode(otherlv_12, grammarAccess.getStaticPageAccess().getLocalparametersKeyword_6_0());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_12); 

                    				newLeafNode(otherlv_13, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_6_1());
                    			
                    // InternalEJSL.g:2158:4: ( (lv_localparameters_14_0= ruleParameter ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==34) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // InternalEJSL.g:2159:5: (lv_localparameters_14_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:2159:5: (lv_localparameters_14_0= ruleParameter )
                    	    // InternalEJSL.g:2160:6: lv_localparameters_14_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getStaticPageAccess().getLocalparametersParameterParserRuleCall_6_2_0());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_14_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getStaticPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"localparameters",
                    	    							lv_localparameters_14_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,18,FOLLOW_69); 

                    				newLeafNode(otherlv_15, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_6_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2182:3: (otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}' )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==63) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // InternalEJSL.g:2183:4: otherlv_16= 'pageactions' otherlv_17= '{' ( (lv_pageactions_18_0= rulePageAction ) )* otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,63,FOLLOW_4); 

                    				newLeafNode(otherlv_16, grammarAccess.getStaticPageAccess().getPageactionsKeyword_7_0());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_70); 

                    				newLeafNode(otherlv_17, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:2191:4: ( (lv_pageactions_18_0= rulePageAction ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==42) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // InternalEJSL.g:2192:5: (lv_pageactions_18_0= rulePageAction )
                    	    {
                    	    // InternalEJSL.g:2192:5: (lv_pageactions_18_0= rulePageAction )
                    	    // InternalEJSL.g:2193:6: lv_pageactions_18_0= rulePageAction
                    	    {

                    	    						newCompositeNode(grammarAccess.getStaticPageAccess().getPageactionsPageActionParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_70);
                    	    lv_pageactions_18_0=rulePageAction();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getStaticPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"pageactions",
                    	    							lv_pageactions_18_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.PageAction");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FOLLOW_71); 

                    				newLeafNode(otherlv_19, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2215:3: (otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==64) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalEJSL.g:2216:4: otherlv_20= 'links' otherlv_21= '{' ( (lv_links_22_0= ruleLink ) )* otherlv_23= '}'
                    {
                    otherlv_20=(Token)match(input,64,FOLLOW_4); 

                    				newLeafNode(otherlv_20, grammarAccess.getStaticPageAccess().getLinksKeyword_8_0());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_72); 

                    				newLeafNode(otherlv_21, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:2224:4: ( (lv_links_22_0= ruleLink ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==77||(LA60_0>=83 && LA60_0<=84)) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // InternalEJSL.g:2225:5: (lv_links_22_0= ruleLink )
                    	    {
                    	    // InternalEJSL.g:2225:5: (lv_links_22_0= ruleLink )
                    	    // InternalEJSL.g:2226:6: lv_links_22_0= ruleLink
                    	    {

                    	    						newCompositeNode(grammarAccess.getStaticPageAccess().getLinksLinkParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_72);
                    	    lv_links_22_0=ruleLink();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getStaticPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"links",
                    	    							lv_links_22_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Link");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);

                    otherlv_23=(Token)match(input,18,FOLLOW_73); 

                    				newLeafNode(otherlv_23, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            otherlv_24=(Token)match(input,65,FOLLOW_4); 

            			newLeafNode(otherlv_24, grammarAccess.getStaticPageAccess().getHTMLBodyKeyword_9());
            		
            otherlv_25=(Token)match(input,16,FOLLOW_3); 

            			newLeafNode(otherlv_25, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalEJSL.g:2256:3: ( (lv_HTMLBody_26_0= RULE_STRING ) )
            // InternalEJSL.g:2257:4: (lv_HTMLBody_26_0= RULE_STRING )
            {
            // InternalEJSL.g:2257:4: (lv_HTMLBody_26_0= RULE_STRING )
            // InternalEJSL.g:2258:5: lv_HTMLBody_26_0= RULE_STRING
            {
            lv_HTMLBody_26_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

            					newLeafNode(lv_HTMLBody_26_0, grammarAccess.getStaticPageAccess().getHTMLBodySTRINGTerminalRuleCall_11_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStaticPageRule());
            					}
            					setWithLastConsumed(
            						current,
            						"HTMLBody",
            						lv_HTMLBody_26_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_27=(Token)match(input,18,FOLLOW_7); 

            			newLeafNode(otherlv_27, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_28=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_28, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_13());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStaticPage"


    // $ANTLR start "entryRuleDynamicPage"
    // InternalEJSL.g:2286:1: entryRuleDynamicPage returns [EObject current=null] : iv_ruleDynamicPage= ruleDynamicPage EOF ;
    public final EObject entryRuleDynamicPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDynamicPage = null;


        try {
            // InternalEJSL.g:2286:52: (iv_ruleDynamicPage= ruleDynamicPage EOF )
            // InternalEJSL.g:2287:2: iv_ruleDynamicPage= ruleDynamicPage EOF
            {
             newCompositeNode(grammarAccess.getDynamicPageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDynamicPage=ruleDynamicPage();

            state._fsp--;

             current =iv_ruleDynamicPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDynamicPage"


    // $ANTLR start "ruleDynamicPage"
    // InternalEJSL.g:2293:1: ruleDynamicPage returns [EObject current=null] : (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage ) ;
    public final EObject ruleDynamicPage() throws RecognitionException {
        EObject current = null;

        EObject this_IndexPage_0 = null;

        EObject this_DetailsPage_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2299:2: ( (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage ) )
            // InternalEJSL.g:2300:2: (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage )
            {
            // InternalEJSL.g:2300:2: (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage )
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
                    // InternalEJSL.g:2301:3: this_IndexPage_0= ruleIndexPage
                    {

                    			newCompositeNode(grammarAccess.getDynamicPageAccess().getIndexPageParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IndexPage_0=ruleIndexPage();

                    state._fsp--;


                    			current = this_IndexPage_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEJSL.g:2310:3: this_DetailsPage_1= ruleDetailsPage
                    {

                    			newCompositeNode(grammarAccess.getDynamicPageAccess().getDetailsPageParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_DetailsPage_1=ruleDetailsPage();

                    state._fsp--;


                    			current = this_DetailsPage_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDynamicPage"


    // $ANTLR start "entryRuleIndexPage"
    // InternalEJSL.g:2322:1: entryRuleIndexPage returns [EObject current=null] : iv_ruleIndexPage= ruleIndexPage EOF ;
    public final EObject entryRuleIndexPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIndexPage = null;


        try {
            // InternalEJSL.g:2322:50: (iv_ruleIndexPage= ruleIndexPage EOF )
            // InternalEJSL.g:2323:2: iv_ruleIndexPage= ruleIndexPage EOF
            {
             newCompositeNode(grammarAccess.getIndexPageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIndexPage=ruleIndexPage();

            state._fsp--;

             current =iv_ruleIndexPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIndexPage"


    // $ANTLR start "ruleIndexPage"
    // InternalEJSL.g:2329:1: ruleIndexPage returns [EObject current=null] : ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}' ) ;
    public final EObject ruleIndexPage() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_localparameters_18_0 = null;

        EObject lv_pageactions_22_0 = null;

        EObject lv_links_34_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2335:2: ( ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}' ) )
            // InternalEJSL.g:2336:2: ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}' )
            {
            // InternalEJSL.g:2336:2: ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}' )
            // InternalEJSL.g:2337:3: () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )? (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )? otherlv_36= '}'
            {
            // InternalEJSL.g:2337:3: ()
            // InternalEJSL.g:2338:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getIndexPageAccess().getIndexPageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,66,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getIndexPageAccess().getIndexPageKeyword_1());
            		
            // InternalEJSL.g:2348:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:2349:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:2349:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:2350:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getIndexPageAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIndexPageRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_74); 

            			newLeafNode(otherlv_3, grammarAccess.getIndexPageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:2371:3: (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==67) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // InternalEJSL.g:2372:4: otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_4=(Token)match(input,67,FOLLOW_51); 

                    				newLeafNode(otherlv_4, grammarAccess.getIndexPageAccess().getEntitiesKeyword_4_0());
                    			
                    // InternalEJSL.g:2376:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2377:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2377:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2378:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getIndexPageAccess().getEntitiesEntityCrossReference_4_1_0());
                    					
                    pushFollow(FOLLOW_75);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2392:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==21) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // InternalEJSL.g:2393:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,21,FOLLOW_51); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getIndexPageAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalEJSL.g:2397:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2398:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2398:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2399:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getIndexPageAccess().getEntitiesEntityCrossReference_4_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_75);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

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

            // InternalEJSL.g:2415:3: (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==60) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // InternalEJSL.g:2416:4: otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    {
                    otherlv_8=(Token)match(input,60,FOLLOW_33); 

                    				newLeafNode(otherlv_8, grammarAccess.getIndexPageAccess().getParameterGroupsKeyword_5_0());
                    			
                    // InternalEJSL.g:2420:4: ( (otherlv_9= RULE_ID ) )
                    // InternalEJSL.g:2421:5: (otherlv_9= RULE_ID )
                    {
                    // InternalEJSL.g:2421:5: (otherlv_9= RULE_ID )
                    // InternalEJSL.g:2422:6: otherlv_9= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_76); 

                    						newLeafNode(otherlv_9, grammarAccess.getIndexPageAccess().getParametergroupsParameterGroupCrossReference_5_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:2433:4: (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==21) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // InternalEJSL.g:2434:5: otherlv_10= ',' ( (otherlv_11= RULE_ID ) )
                    	    {
                    	    otherlv_10=(Token)match(input,21,FOLLOW_33); 

                    	    					newLeafNode(otherlv_10, grammarAccess.getIndexPageAccess().getCommaKeyword_5_2_0());
                    	    				
                    	    // InternalEJSL.g:2438:5: ( (otherlv_11= RULE_ID ) )
                    	    // InternalEJSL.g:2439:6: (otherlv_11= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:2439:6: (otherlv_11= RULE_ID )
                    	    // InternalEJSL.g:2440:7: otherlv_11= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						
                    	    otherlv_11=(Token)match(input,RULE_ID,FOLLOW_76); 

                    	    							newLeafNode(otherlv_11, grammarAccess.getIndexPageAccess().getParametergroupsParameterGroupCrossReference_5_2_1_0());
                    	    						

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

            // InternalEJSL.g:2453:3: (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==61) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalEJSL.g:2454:4: otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    {
                    otherlv_12=(Token)match(input,61,FOLLOW_33); 

                    				newLeafNode(otherlv_12, grammarAccess.getIndexPageAccess().getGlobalparametersKeyword_6_0());
                    			
                    // InternalEJSL.g:2458:4: ( (otherlv_13= RULE_ID ) )
                    // InternalEJSL.g:2459:5: (otherlv_13= RULE_ID )
                    {
                    // InternalEJSL.g:2459:5: (otherlv_13= RULE_ID )
                    // InternalEJSL.g:2460:6: otherlv_13= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_77); 

                    						newLeafNode(otherlv_13, grammarAccess.getIndexPageAccess().getGlobalparametersParameterCrossReference_6_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:2471:4: (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==21) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // InternalEJSL.g:2472:5: otherlv_14= ',' ( (otherlv_15= RULE_ID ) )
                    	    {
                    	    otherlv_14=(Token)match(input,21,FOLLOW_33); 

                    	    					newLeafNode(otherlv_14, grammarAccess.getIndexPageAccess().getCommaKeyword_6_2_0());
                    	    				
                    	    // InternalEJSL.g:2476:5: ( (otherlv_15= RULE_ID ) )
                    	    // InternalEJSL.g:2477:6: (otherlv_15= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:2477:6: (otherlv_15= RULE_ID )
                    	    // InternalEJSL.g:2478:7: otherlv_15= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_77); 

                    	    							newLeafNode(otherlv_15, grammarAccess.getIndexPageAccess().getGlobalparametersParameterCrossReference_6_2_1_0());
                    	    						

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

            // InternalEJSL.g:2491:3: (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==62) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalEJSL.g:2492:4: otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,62,FOLLOW_4); 

                    				newLeafNode(otherlv_16, grammarAccess.getIndexPageAccess().getLocalparametersKeyword_7_0());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_12); 

                    				newLeafNode(otherlv_17, grammarAccess.getIndexPageAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:2500:4: ( (lv_localparameters_18_0= ruleParameter ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==34) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // InternalEJSL.g:2501:5: (lv_localparameters_18_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:2501:5: (lv_localparameters_18_0= ruleParameter )
                    	    // InternalEJSL.g:2502:6: lv_localparameters_18_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getIndexPageAccess().getLocalparametersParameterParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_18_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getIndexPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"localparameters",
                    	    							lv_localparameters_18_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop69;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FOLLOW_78); 

                    				newLeafNode(otherlv_19, grammarAccess.getIndexPageAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2524:3: (otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}' )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==63) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalEJSL.g:2525:4: otherlv_20= 'pageactions' otherlv_21= '{' ( (lv_pageactions_22_0= rulePageAction ) )* otherlv_23= '}'
                    {
                    otherlv_20=(Token)match(input,63,FOLLOW_4); 

                    				newLeafNode(otherlv_20, grammarAccess.getIndexPageAccess().getPageactionsKeyword_8_0());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_70); 

                    				newLeafNode(otherlv_21, grammarAccess.getIndexPageAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:2533:4: ( (lv_pageactions_22_0= rulePageAction ) )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==42) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // InternalEJSL.g:2534:5: (lv_pageactions_22_0= rulePageAction )
                    	    {
                    	    // InternalEJSL.g:2534:5: (lv_pageactions_22_0= rulePageAction )
                    	    // InternalEJSL.g:2535:6: lv_pageactions_22_0= rulePageAction
                    	    {

                    	    						newCompositeNode(grammarAccess.getIndexPageAccess().getPageactionsPageActionParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_70);
                    	    lv_pageactions_22_0=rulePageAction();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getIndexPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"pageactions",
                    	    							lv_pageactions_22_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.PageAction");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop71;
                        }
                    } while (true);

                    otherlv_23=(Token)match(input,18,FOLLOW_79); 

                    				newLeafNode(otherlv_23, grammarAccess.getIndexPageAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2557:3: (otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==68) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalEJSL.g:2558:4: otherlv_24= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_26= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_24=(Token)match(input,68,FOLLOW_51); 

                    				newLeafNode(otherlv_24, grammarAccess.getIndexPageAccess().getTableColumnsKeyword_9_0());
                    			
                    // InternalEJSL.g:2562:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2563:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2563:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2564:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getIndexPageAccess().getTablecolumnsAttributeCrossReference_9_1_0());
                    					
                    pushFollow(FOLLOW_80);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2578:4: (otherlv_26= ',' ( ( ruleQualifiedName ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==21) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // InternalEJSL.g:2579:5: otherlv_26= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_26=(Token)match(input,21,FOLLOW_51); 

                    	    					newLeafNode(otherlv_26, grammarAccess.getIndexPageAccess().getCommaKeyword_9_2_0());
                    	    				
                    	    // InternalEJSL.g:2583:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2584:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2584:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2585:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getIndexPageAccess().getTablecolumnsAttributeCrossReference_9_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_80);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

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

            // InternalEJSL.g:2601:3: (otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==69) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalEJSL.g:2602:4: otherlv_28= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_28=(Token)match(input,69,FOLLOW_51); 

                    				newLeafNode(otherlv_28, grammarAccess.getIndexPageAccess().getFiltersKeyword_10_0());
                    			
                    // InternalEJSL.g:2606:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2607:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2607:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2608:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getIndexPageAccess().getFiltersAttributeCrossReference_10_1_0());
                    					
                    pushFollow(FOLLOW_81);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2622:4: (otherlv_30= ',' ( ( ruleQualifiedName ) ) )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==21) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // InternalEJSL.g:2623:5: otherlv_30= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_30=(Token)match(input,21,FOLLOW_51); 

                    	    					newLeafNode(otherlv_30, grammarAccess.getIndexPageAccess().getCommaKeyword_10_2_0());
                    	    				
                    	    // InternalEJSL.g:2627:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2628:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2628:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2629:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getIndexPageAccess().getFiltersAttributeCrossReference_10_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_81);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

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

            // InternalEJSL.g:2645:3: (otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==64) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalEJSL.g:2646:4: otherlv_32= 'links' otherlv_33= '{' ( (lv_links_34_0= ruleLink ) )* otherlv_35= '}'
                    {
                    otherlv_32=(Token)match(input,64,FOLLOW_4); 

                    				newLeafNode(otherlv_32, grammarAccess.getIndexPageAccess().getLinksKeyword_11_0());
                    			
                    otherlv_33=(Token)match(input,16,FOLLOW_72); 

                    				newLeafNode(otherlv_33, grammarAccess.getIndexPageAccess().getLeftCurlyBracketKeyword_11_1());
                    			
                    // InternalEJSL.g:2654:4: ( (lv_links_34_0= ruleLink ) )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==77||(LA77_0>=83 && LA77_0<=84)) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // InternalEJSL.g:2655:5: (lv_links_34_0= ruleLink )
                    	    {
                    	    // InternalEJSL.g:2655:5: (lv_links_34_0= ruleLink )
                    	    // InternalEJSL.g:2656:6: lv_links_34_0= ruleLink
                    	    {

                    	    						newCompositeNode(grammarAccess.getIndexPageAccess().getLinksLinkParserRuleCall_11_2_0());
                    	    					
                    	    pushFollow(FOLLOW_72);
                    	    lv_links_34_0=ruleLink();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getIndexPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"links",
                    	    							lv_links_34_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Link");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop77;
                        }
                    } while (true);

                    otherlv_35=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_35, grammarAccess.getIndexPageAccess().getRightCurlyBracketKeyword_11_3());
                    			

                    }
                    break;

            }

            otherlv_36=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_36, grammarAccess.getIndexPageAccess().getRightCurlyBracketKeyword_12());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIndexPage"


    // $ANTLR start "entryRuleDetailsPage"
    // InternalEJSL.g:2686:1: entryRuleDetailsPage returns [EObject current=null] : iv_ruleDetailsPage= ruleDetailsPage EOF ;
    public final EObject entryRuleDetailsPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDetailsPage = null;


        try {
            // InternalEJSL.g:2686:52: (iv_ruleDetailsPage= ruleDetailsPage EOF )
            // InternalEJSL.g:2687:2: iv_ruleDetailsPage= ruleDetailsPage EOF
            {
             newCompositeNode(grammarAccess.getDetailsPageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDetailsPage=ruleDetailsPage();

            state._fsp--;

             current =iv_ruleDetailsPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDetailsPage"


    // $ANTLR start "ruleDetailsPage"
    // InternalEJSL.g:2693:1: ruleDetailsPage returns [EObject current=null] : ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}' ) ;
    public final EObject ruleDetailsPage() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_editfields_10_0 = null;

        EObject lv_localparameters_22_0 = null;

        EObject lv_pageactions_26_0 = null;

        EObject lv_links_38_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2699:2: ( ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}' ) )
            // InternalEJSL.g:2700:2: ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}' )
            {
            // InternalEJSL.g:2700:2: ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}' )
            // InternalEJSL.g:2701:3: () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )? (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )? otherlv_40= '}'
            {
            // InternalEJSL.g:2701:3: ()
            // InternalEJSL.g:2702:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDetailsPageAccess().getDetailsPageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,70,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getDetailsPageAccess().getDetailsPageKeyword_1());
            		
            // InternalEJSL.g:2712:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:2713:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:2713:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:2714:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getDetailsPageAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDetailsPageRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			newLeafNode(otherlv_3, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:2735:3: (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==67) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // InternalEJSL.g:2736:4: otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_4=(Token)match(input,67,FOLLOW_51); 

                    				newLeafNode(otherlv_4, grammarAccess.getDetailsPageAccess().getEntitiesKeyword_4_0());
                    			
                    // InternalEJSL.g:2740:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2741:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2741:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2742:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getDetailsPageAccess().getEntitiesEntityCrossReference_4_1_0());
                    					
                    pushFollow(FOLLOW_83);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2756:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop79:
                    do {
                        int alt79=2;
                        int LA79_0 = input.LA(1);

                        if ( (LA79_0==21) ) {
                            alt79=1;
                        }


                        switch (alt79) {
                    	case 1 :
                    	    // InternalEJSL.g:2757:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,21,FOLLOW_51); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getDetailsPageAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalEJSL.g:2761:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2762:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2762:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2763:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getDetailsPageAccess().getEntitiesEntityCrossReference_4_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_83);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

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

            // InternalEJSL.g:2779:3: (otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==71) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // InternalEJSL.g:2780:4: otherlv_8= 'editFields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,71,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getDetailsPageAccess().getEditFieldsKeyword_5_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_84); 

                    				newLeafNode(otherlv_9, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:2788:4: ( (lv_editfields_10_0= ruleDetailPageField ) )*
                    loop81:
                    do {
                        int alt81=2;
                        int LA81_0 = input.LA(1);

                        if ( (LA81_0==RULE_ID||LA81_0==136) ) {
                            alt81=1;
                        }


                        switch (alt81) {
                    	case 1 :
                    	    // InternalEJSL.g:2789:5: (lv_editfields_10_0= ruleDetailPageField )
                    	    {
                    	    // InternalEJSL.g:2789:5: (lv_editfields_10_0= ruleDetailPageField )
                    	    // InternalEJSL.g:2790:6: lv_editfields_10_0= ruleDetailPageField
                    	    {

                    	    						newCompositeNode(grammarAccess.getDetailsPageAccess().getEditfieldsDetailPageFieldParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_84);
                    	    lv_editfields_10_0=ruleDetailPageField();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getDetailsPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"editfields",
                    	    							lv_editfields_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.DetailPageField");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop81;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_85); 

                    				newLeafNode(otherlv_11, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2812:3: (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==60) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // InternalEJSL.g:2813:4: otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    {
                    otherlv_12=(Token)match(input,60,FOLLOW_33); 

                    				newLeafNode(otherlv_12, grammarAccess.getDetailsPageAccess().getParameterGroupsKeyword_6_0());
                    			
                    // InternalEJSL.g:2817:4: ( (otherlv_13= RULE_ID ) )
                    // InternalEJSL.g:2818:5: (otherlv_13= RULE_ID )
                    {
                    // InternalEJSL.g:2818:5: (otherlv_13= RULE_ID )
                    // InternalEJSL.g:2819:6: otherlv_13= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_76); 

                    						newLeafNode(otherlv_13, grammarAccess.getDetailsPageAccess().getParametergroupsParameterGroupCrossReference_6_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:2830:4: (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    loop83:
                    do {
                        int alt83=2;
                        int LA83_0 = input.LA(1);

                        if ( (LA83_0==21) ) {
                            alt83=1;
                        }


                        switch (alt83) {
                    	case 1 :
                    	    // InternalEJSL.g:2831:5: otherlv_14= ',' ( (otherlv_15= RULE_ID ) )
                    	    {
                    	    otherlv_14=(Token)match(input,21,FOLLOW_33); 

                    	    					newLeafNode(otherlv_14, grammarAccess.getDetailsPageAccess().getCommaKeyword_6_2_0());
                    	    				
                    	    // InternalEJSL.g:2835:5: ( (otherlv_15= RULE_ID ) )
                    	    // InternalEJSL.g:2836:6: (otherlv_15= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:2836:6: (otherlv_15= RULE_ID )
                    	    // InternalEJSL.g:2837:7: otherlv_15= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_76); 

                    	    							newLeafNode(otherlv_15, grammarAccess.getDetailsPageAccess().getParametergroupsParameterGroupCrossReference_6_2_1_0());
                    	    						

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

            // InternalEJSL.g:2850:3: (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==61) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalEJSL.g:2851:4: otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )*
                    {
                    otherlv_16=(Token)match(input,61,FOLLOW_33); 

                    				newLeafNode(otherlv_16, grammarAccess.getDetailsPageAccess().getGlobalparametersKeyword_7_0());
                    			
                    // InternalEJSL.g:2855:4: ( (otherlv_17= RULE_ID ) )
                    // InternalEJSL.g:2856:5: (otherlv_17= RULE_ID )
                    {
                    // InternalEJSL.g:2856:5: (otherlv_17= RULE_ID )
                    // InternalEJSL.g:2857:6: otherlv_17= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					
                    otherlv_17=(Token)match(input,RULE_ID,FOLLOW_77); 

                    						newLeafNode(otherlv_17, grammarAccess.getDetailsPageAccess().getGlobalparametersParameterCrossReference_7_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:2868:4: (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==21) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // InternalEJSL.g:2869:5: otherlv_18= ',' ( (otherlv_19= RULE_ID ) )
                    	    {
                    	    otherlv_18=(Token)match(input,21,FOLLOW_33); 

                    	    					newLeafNode(otherlv_18, grammarAccess.getDetailsPageAccess().getCommaKeyword_7_2_0());
                    	    				
                    	    // InternalEJSL.g:2873:5: ( (otherlv_19= RULE_ID ) )
                    	    // InternalEJSL.g:2874:6: (otherlv_19= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:2874:6: (otherlv_19= RULE_ID )
                    	    // InternalEJSL.g:2875:7: otherlv_19= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						
                    	    otherlv_19=(Token)match(input,RULE_ID,FOLLOW_77); 

                    	    							newLeafNode(otherlv_19, grammarAccess.getDetailsPageAccess().getGlobalparametersParameterCrossReference_7_2_1_0());
                    	    						

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

            // InternalEJSL.g:2888:3: (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==62) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalEJSL.g:2889:4: otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}'
                    {
                    otherlv_20=(Token)match(input,62,FOLLOW_4); 

                    				newLeafNode(otherlv_20, grammarAccess.getDetailsPageAccess().getLocalparametersKeyword_8_0());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_12); 

                    				newLeafNode(otherlv_21, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:2897:4: ( (lv_localparameters_22_0= ruleParameter ) )*
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( (LA87_0==34) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // InternalEJSL.g:2898:5: (lv_localparameters_22_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:2898:5: (lv_localparameters_22_0= ruleParameter )
                    	    // InternalEJSL.g:2899:6: lv_localparameters_22_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getDetailsPageAccess().getLocalparametersParameterParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_22_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getDetailsPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"localparameters",
                    	    							lv_localparameters_22_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop87;
                        }
                    } while (true);

                    otherlv_23=(Token)match(input,18,FOLLOW_78); 

                    				newLeafNode(otherlv_23, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2921:3: (otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}' )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==63) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // InternalEJSL.g:2922:4: otherlv_24= 'pageactions' otherlv_25= '{' ( (lv_pageactions_26_0= rulePageAction ) )* otherlv_27= '}'
                    {
                    otherlv_24=(Token)match(input,63,FOLLOW_4); 

                    				newLeafNode(otherlv_24, grammarAccess.getDetailsPageAccess().getPageactionsKeyword_9_0());
                    			
                    otherlv_25=(Token)match(input,16,FOLLOW_70); 

                    				newLeafNode(otherlv_25, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_9_1());
                    			
                    // InternalEJSL.g:2930:4: ( (lv_pageactions_26_0= rulePageAction ) )*
                    loop89:
                    do {
                        int alt89=2;
                        int LA89_0 = input.LA(1);

                        if ( (LA89_0==42) ) {
                            alt89=1;
                        }


                        switch (alt89) {
                    	case 1 :
                    	    // InternalEJSL.g:2931:5: (lv_pageactions_26_0= rulePageAction )
                    	    {
                    	    // InternalEJSL.g:2931:5: (lv_pageactions_26_0= rulePageAction )
                    	    // InternalEJSL.g:2932:6: lv_pageactions_26_0= rulePageAction
                    	    {

                    	    						newCompositeNode(grammarAccess.getDetailsPageAccess().getPageactionsPageActionParserRuleCall_9_2_0());
                    	    					
                    	    pushFollow(FOLLOW_70);
                    	    lv_pageactions_26_0=rulePageAction();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getDetailsPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"pageactions",
                    	    							lv_pageactions_26_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.PageAction");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop89;
                        }
                    } while (true);

                    otherlv_27=(Token)match(input,18,FOLLOW_79); 

                    				newLeafNode(otherlv_27, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_9_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2954:3: (otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==68) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // InternalEJSL.g:2955:4: otherlv_28= 'table columns =' ( ( ruleQualifiedName ) ) (otherlv_30= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_28=(Token)match(input,68,FOLLOW_51); 

                    				newLeafNode(otherlv_28, grammarAccess.getDetailsPageAccess().getTableColumnsKeyword_10_0());
                    			
                    // InternalEJSL.g:2959:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2960:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2960:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2961:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getDetailsPageAccess().getTablecolumnsAttributeCrossReference_10_1_0());
                    					
                    pushFollow(FOLLOW_80);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2975:4: (otherlv_30= ',' ( ( ruleQualifiedName ) ) )*
                    loop91:
                    do {
                        int alt91=2;
                        int LA91_0 = input.LA(1);

                        if ( (LA91_0==21) ) {
                            alt91=1;
                        }


                        switch (alt91) {
                    	case 1 :
                    	    // InternalEJSL.g:2976:5: otherlv_30= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_30=(Token)match(input,21,FOLLOW_51); 

                    	    					newLeafNode(otherlv_30, grammarAccess.getDetailsPageAccess().getCommaKeyword_10_2_0());
                    	    				
                    	    // InternalEJSL.g:2980:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2981:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2981:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2982:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getDetailsPageAccess().getTablecolumnsAttributeCrossReference_10_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_80);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

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

            // InternalEJSL.g:2998:3: (otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==69) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalEJSL.g:2999:4: otherlv_32= 'filters =' ( ( ruleQualifiedName ) ) (otherlv_34= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_32=(Token)match(input,69,FOLLOW_51); 

                    				newLeafNode(otherlv_32, grammarAccess.getDetailsPageAccess().getFiltersKeyword_11_0());
                    			
                    // InternalEJSL.g:3003:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:3004:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:3004:5: ( ruleQualifiedName )
                    // InternalEJSL.g:3005:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getDetailsPageAccess().getFiltersAttributeCrossReference_11_1_0());
                    					
                    pushFollow(FOLLOW_81);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:3019:4: (otherlv_34= ',' ( ( ruleQualifiedName ) ) )*
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==21) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // InternalEJSL.g:3020:5: otherlv_34= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_34=(Token)match(input,21,FOLLOW_51); 

                    	    					newLeafNode(otherlv_34, grammarAccess.getDetailsPageAccess().getCommaKeyword_11_2_0());
                    	    				
                    	    // InternalEJSL.g:3024:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:3025:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:3025:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:3026:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getDetailsPageAccess().getFiltersAttributeCrossReference_11_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_81);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

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

            // InternalEJSL.g:3042:3: (otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==64) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // InternalEJSL.g:3043:4: otherlv_36= 'links' otherlv_37= '{' ( (lv_links_38_0= ruleLink ) )* otherlv_39= '}'
                    {
                    otherlv_36=(Token)match(input,64,FOLLOW_4); 

                    				newLeafNode(otherlv_36, grammarAccess.getDetailsPageAccess().getLinksKeyword_12_0());
                    			
                    otherlv_37=(Token)match(input,16,FOLLOW_72); 

                    				newLeafNode(otherlv_37, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_12_1());
                    			
                    // InternalEJSL.g:3051:4: ( (lv_links_38_0= ruleLink ) )*
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==77||(LA95_0>=83 && LA95_0<=84)) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // InternalEJSL.g:3052:5: (lv_links_38_0= ruleLink )
                    	    {
                    	    // InternalEJSL.g:3052:5: (lv_links_38_0= ruleLink )
                    	    // InternalEJSL.g:3053:6: lv_links_38_0= ruleLink
                    	    {

                    	    						newCompositeNode(grammarAccess.getDetailsPageAccess().getLinksLinkParserRuleCall_12_2_0());
                    	    					
                    	    pushFollow(FOLLOW_72);
                    	    lv_links_38_0=ruleLink();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getDetailsPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"links",
                    	    							lv_links_38_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Link");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop95;
                        }
                    } while (true);

                    otherlv_39=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_39, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_12_3());
                    			

                    }
                    break;

            }

            otherlv_40=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_40, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_13());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDetailsPage"


    // $ANTLR start "entryRuleDetailPageField"
    // InternalEJSL.g:3083:1: entryRuleDetailPageField returns [EObject current=null] : iv_ruleDetailPageField= ruleDetailPageField EOF ;
    public final EObject entryRuleDetailPageField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDetailPageField = null;


        try {
            // InternalEJSL.g:3083:56: (iv_ruleDetailPageField= ruleDetailPageField EOF )
            // InternalEJSL.g:3084:2: iv_ruleDetailPageField= ruleDetailPageField EOF
            {
             newCompositeNode(grammarAccess.getDetailPageFieldRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDetailPageField=ruleDetailPageField();

            state._fsp--;

             current =iv_ruleDetailPageField; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDetailPageField"


    // $ANTLR start "ruleDetailPageField"
    // InternalEJSL.g:3090:1: ruleDetailPageField returns [EObject current=null] : ( () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )? ) ;
    public final EObject ruleDetailPageField() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_htmltype_3_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3096:2: ( ( () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )? ) )
            // InternalEJSL.g:3097:2: ( () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )? )
            {
            // InternalEJSL.g:3097:2: ( () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )? )
            // InternalEJSL.g:3098:3: () ( ( ruleQualifiedName ) ) (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )?
            {
            // InternalEJSL.g:3098:3: ()
            // InternalEJSL.g:3099:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDetailPageFieldAccess().getDetailPageFieldAction_0(),
            					current);
            			

            }

            // InternalEJSL.g:3105:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:3106:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:3106:4: ( ruleQualifiedName )
            // InternalEJSL.g:3107:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDetailPageFieldRule());
            					}
            				

            					newCompositeNode(grammarAccess.getDetailPageFieldAccess().getAttributeAttributeCrossReference_1_0());
            				
            pushFollow(FOLLOW_86);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:3121:3: (otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==72) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalEJSL.g:3122:4: otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) )
                    {
                    otherlv_2=(Token)match(input,72,FOLLOW_87); 

                    				newLeafNode(otherlv_2, grammarAccess.getDetailPageFieldAccess().getColonKeyword_2_0());
                    			
                    // InternalEJSL.g:3126:4: ( (lv_htmltype_3_0= ruleHTMLTypes ) )
                    // InternalEJSL.g:3127:5: (lv_htmltype_3_0= ruleHTMLTypes )
                    {
                    // InternalEJSL.g:3127:5: (lv_htmltype_3_0= ruleHTMLTypes )
                    // InternalEJSL.g:3128:6: lv_htmltype_3_0= ruleHTMLTypes
                    {

                    						newCompositeNode(grammarAccess.getDetailPageFieldAccess().getHtmltypeHTMLTypesParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_htmltype_3_0=ruleHTMLTypes();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getDetailPageFieldRule());
                    						}
                    						set(
                    							current,
                    							"htmltype",
                    							lv_htmltype_3_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.HTMLTypes");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDetailPageField"


    // $ANTLR start "entryRuleCustomPage"
    // InternalEJSL.g:3150:1: entryRuleCustomPage returns [EObject current=null] : iv_ruleCustomPage= ruleCustomPage EOF ;
    public final EObject entryRuleCustomPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCustomPage = null;


        try {
            // InternalEJSL.g:3150:51: (iv_ruleCustomPage= ruleCustomPage EOF )
            // InternalEJSL.g:3151:2: iv_ruleCustomPage= ruleCustomPage EOF
            {
             newCompositeNode(grammarAccess.getCustomPageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCustomPage=ruleCustomPage();

            state._fsp--;

             current =iv_ruleCustomPage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCustomPage"


    // $ANTLR start "ruleCustomPage"
    // InternalEJSL.g:3157:1: ruleCustomPage returns [EObject current=null] : ( () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}' ) ;
    public final EObject ruleCustomPage() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        Enumerator lv_pageType_5_0 = null;

        EObject lv_pageactions_12_0 = null;

        EObject lv_localparameters_24_0 = null;

        EObject lv_links_28_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3163:2: ( ( () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}' ) )
            // InternalEJSL.g:3164:2: ( () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}' )
            {
            // InternalEJSL.g:3164:2: ( () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}' )
            // InternalEJSL.g:3165:3: () otherlv_1= 'CustomPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Page type:' ( (lv_pageType_5_0= rulePageKinds ) ) (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )? (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )? (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )? (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )? (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )? otherlv_30= '}'
            {
            // InternalEJSL.g:3165:3: ()
            // InternalEJSL.g:3166:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getCustomPageAccess().getCustomPageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,73,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getCustomPageAccess().getCustomPageKeyword_1());
            		
            // InternalEJSL.g:3176:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:3177:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:3177:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:3178:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getCustomPageAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCustomPageRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_88); 

            			newLeafNode(otherlv_3, grammarAccess.getCustomPageAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,74,FOLLOW_89); 

            			newLeafNode(otherlv_4, grammarAccess.getCustomPageAccess().getPageTypeKeyword_4());
            		
            // InternalEJSL.g:3203:3: ( (lv_pageType_5_0= rulePageKinds ) )
            // InternalEJSL.g:3204:4: (lv_pageType_5_0= rulePageKinds )
            {
            // InternalEJSL.g:3204:4: (lv_pageType_5_0= rulePageKinds )
            // InternalEJSL.g:3205:5: lv_pageType_5_0= rulePageKinds
            {

            					newCompositeNode(grammarAccess.getCustomPageAccess().getPageTypePageKindsEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_90);
            lv_pageType_5_0=rulePageKinds();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCustomPageRule());
            					}
            					set(
            						current,
            						"pageType",
            						lv_pageType_5_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.PageKinds");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:3222:3: (otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==67) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // InternalEJSL.g:3223:4: otherlv_6= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_6=(Token)match(input,67,FOLLOW_51); 

                    				newLeafNode(otherlv_6, grammarAccess.getCustomPageAccess().getEntitiesKeyword_6_0());
                    			
                    // InternalEJSL.g:3227:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:3228:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:3228:5: ( ruleQualifiedName )
                    // InternalEJSL.g:3229:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getCustomPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getCustomPageAccess().getEntitiesEntityCrossReference_6_1_0());
                    					
                    pushFollow(FOLLOW_91);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:3243:4: (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    loop98:
                    do {
                        int alt98=2;
                        int LA98_0 = input.LA(1);

                        if ( (LA98_0==21) ) {
                            alt98=1;
                        }


                        switch (alt98) {
                    	case 1 :
                    	    // InternalEJSL.g:3244:5: otherlv_8= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_8=(Token)match(input,21,FOLLOW_51); 

                    	    					newLeafNode(otherlv_8, grammarAccess.getCustomPageAccess().getCommaKeyword_6_2_0());
                    	    				
                    	    // InternalEJSL.g:3248:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:3249:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:3249:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:3250:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getCustomPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getCustomPageAccess().getEntitiesEntityCrossReference_6_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_91);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

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

            // InternalEJSL.g:3266:3: (otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}' )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==63) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // InternalEJSL.g:3267:4: otherlv_10= 'pageactions' otherlv_11= '{' ( (lv_pageactions_12_0= rulePageAction ) )* otherlv_13= '}'
                    {
                    otherlv_10=(Token)match(input,63,FOLLOW_4); 

                    				newLeafNode(otherlv_10, grammarAccess.getCustomPageAccess().getPageactionsKeyword_7_0());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_70); 

                    				newLeafNode(otherlv_11, grammarAccess.getCustomPageAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:3275:4: ( (lv_pageactions_12_0= rulePageAction ) )*
                    loop100:
                    do {
                        int alt100=2;
                        int LA100_0 = input.LA(1);

                        if ( (LA100_0==42) ) {
                            alt100=1;
                        }


                        switch (alt100) {
                    	case 1 :
                    	    // InternalEJSL.g:3276:5: (lv_pageactions_12_0= rulePageAction )
                    	    {
                    	    // InternalEJSL.g:3276:5: (lv_pageactions_12_0= rulePageAction )
                    	    // InternalEJSL.g:3277:6: lv_pageactions_12_0= rulePageAction
                    	    {

                    	    						newCompositeNode(grammarAccess.getCustomPageAccess().getPageactionsPageActionParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_70);
                    	    lv_pageactions_12_0=rulePageAction();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCustomPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"pageactions",
                    	    							lv_pageactions_12_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.PageAction");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop100;
                        }
                    } while (true);

                    otherlv_13=(Token)match(input,18,FOLLOW_92); 

                    				newLeafNode(otherlv_13, grammarAccess.getCustomPageAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:3299:3: (otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )* )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==60) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // InternalEJSL.g:3300:4: otherlv_14= '*ParameterGroups' ( (otherlv_15= RULE_ID ) ) (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )*
                    {
                    otherlv_14=(Token)match(input,60,FOLLOW_33); 

                    				newLeafNode(otherlv_14, grammarAccess.getCustomPageAccess().getParameterGroupsKeyword_8_0());
                    			
                    // InternalEJSL.g:3304:4: ( (otherlv_15= RULE_ID ) )
                    // InternalEJSL.g:3305:5: (otherlv_15= RULE_ID )
                    {
                    // InternalEJSL.g:3305:5: (otherlv_15= RULE_ID )
                    // InternalEJSL.g:3306:6: otherlv_15= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getCustomPageRule());
                    						}
                    					
                    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_93); 

                    						newLeafNode(otherlv_15, grammarAccess.getCustomPageAccess().getParametergroupsParameterGroupCrossReference_8_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:3317:4: (otherlv_16= ',' ( (otherlv_17= RULE_ID ) ) )*
                    loop102:
                    do {
                        int alt102=2;
                        int LA102_0 = input.LA(1);

                        if ( (LA102_0==21) ) {
                            alt102=1;
                        }


                        switch (alt102) {
                    	case 1 :
                    	    // InternalEJSL.g:3318:5: otherlv_16= ',' ( (otherlv_17= RULE_ID ) )
                    	    {
                    	    otherlv_16=(Token)match(input,21,FOLLOW_33); 

                    	    					newLeafNode(otherlv_16, grammarAccess.getCustomPageAccess().getCommaKeyword_8_2_0());
                    	    				
                    	    // InternalEJSL.g:3322:5: ( (otherlv_17= RULE_ID ) )
                    	    // InternalEJSL.g:3323:6: (otherlv_17= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:3323:6: (otherlv_17= RULE_ID )
                    	    // InternalEJSL.g:3324:7: otherlv_17= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getCustomPageRule());
                    	    							}
                    	    						
                    	    otherlv_17=(Token)match(input,RULE_ID,FOLLOW_93); 

                    	    							newLeafNode(otherlv_17, grammarAccess.getCustomPageAccess().getParametergroupsParameterGroupCrossReference_8_2_1_0());
                    	    						

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

            // InternalEJSL.g:3337:3: (otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )* )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==61) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // InternalEJSL.g:3338:4: otherlv_18= '*Globalparameters' ( (otherlv_19= RULE_ID ) ) (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )*
                    {
                    otherlv_18=(Token)match(input,61,FOLLOW_33); 

                    				newLeafNode(otherlv_18, grammarAccess.getCustomPageAccess().getGlobalparametersKeyword_9_0());
                    			
                    // InternalEJSL.g:3342:4: ( (otherlv_19= RULE_ID ) )
                    // InternalEJSL.g:3343:5: (otherlv_19= RULE_ID )
                    {
                    // InternalEJSL.g:3343:5: (otherlv_19= RULE_ID )
                    // InternalEJSL.g:3344:6: otherlv_19= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getCustomPageRule());
                    						}
                    					
                    otherlv_19=(Token)match(input,RULE_ID,FOLLOW_94); 

                    						newLeafNode(otherlv_19, grammarAccess.getCustomPageAccess().getGlobalparametersParameterCrossReference_9_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:3355:4: (otherlv_20= ',' ( (otherlv_21= RULE_ID ) ) )*
                    loop104:
                    do {
                        int alt104=2;
                        int LA104_0 = input.LA(1);

                        if ( (LA104_0==21) ) {
                            alt104=1;
                        }


                        switch (alt104) {
                    	case 1 :
                    	    // InternalEJSL.g:3356:5: otherlv_20= ',' ( (otherlv_21= RULE_ID ) )
                    	    {
                    	    otherlv_20=(Token)match(input,21,FOLLOW_33); 

                    	    					newLeafNode(otherlv_20, grammarAccess.getCustomPageAccess().getCommaKeyword_9_2_0());
                    	    				
                    	    // InternalEJSL.g:3360:5: ( (otherlv_21= RULE_ID ) )
                    	    // InternalEJSL.g:3361:6: (otherlv_21= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:3361:6: (otherlv_21= RULE_ID )
                    	    // InternalEJSL.g:3362:7: otherlv_21= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getCustomPageRule());
                    	    							}
                    	    						
                    	    otherlv_21=(Token)match(input,RULE_ID,FOLLOW_94); 

                    	    							newLeafNode(otherlv_21, grammarAccess.getCustomPageAccess().getGlobalparametersParameterCrossReference_9_2_1_0());
                    	    						

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

            // InternalEJSL.g:3375:3: (otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}' )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==62) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // InternalEJSL.g:3376:4: otherlv_22= 'localparameters' otherlv_23= '{' ( (lv_localparameters_24_0= ruleParameter ) )* otherlv_25= '}'
                    {
                    otherlv_22=(Token)match(input,62,FOLLOW_4); 

                    				newLeafNode(otherlv_22, grammarAccess.getCustomPageAccess().getLocalparametersKeyword_10_0());
                    			
                    otherlv_23=(Token)match(input,16,FOLLOW_12); 

                    				newLeafNode(otherlv_23, grammarAccess.getCustomPageAccess().getLeftCurlyBracketKeyword_10_1());
                    			
                    // InternalEJSL.g:3384:4: ( (lv_localparameters_24_0= ruleParameter ) )*
                    loop106:
                    do {
                        int alt106=2;
                        int LA106_0 = input.LA(1);

                        if ( (LA106_0==34) ) {
                            alt106=1;
                        }


                        switch (alt106) {
                    	case 1 :
                    	    // InternalEJSL.g:3385:5: (lv_localparameters_24_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:3385:5: (lv_localparameters_24_0= ruleParameter )
                    	    // InternalEJSL.g:3386:6: lv_localparameters_24_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getCustomPageAccess().getLocalparametersParameterParserRuleCall_10_2_0());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_24_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCustomPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"localparameters",
                    	    							lv_localparameters_24_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop106;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,18,FOLLOW_95); 

                    				newLeafNode(otherlv_25, grammarAccess.getCustomPageAccess().getRightCurlyBracketKeyword_10_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:3408:3: (otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}' )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==64) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // InternalEJSL.g:3409:4: otherlv_26= 'links' otherlv_27= '{' ( (lv_links_28_0= ruleLink ) )* otherlv_29= '}'
                    {
                    otherlv_26=(Token)match(input,64,FOLLOW_4); 

                    				newLeafNode(otherlv_26, grammarAccess.getCustomPageAccess().getLinksKeyword_11_0());
                    			
                    otherlv_27=(Token)match(input,16,FOLLOW_72); 

                    				newLeafNode(otherlv_27, grammarAccess.getCustomPageAccess().getLeftCurlyBracketKeyword_11_1());
                    			
                    // InternalEJSL.g:3417:4: ( (lv_links_28_0= ruleLink ) )*
                    loop108:
                    do {
                        int alt108=2;
                        int LA108_0 = input.LA(1);

                        if ( (LA108_0==77||(LA108_0>=83 && LA108_0<=84)) ) {
                            alt108=1;
                        }


                        switch (alt108) {
                    	case 1 :
                    	    // InternalEJSL.g:3418:5: (lv_links_28_0= ruleLink )
                    	    {
                    	    // InternalEJSL.g:3418:5: (lv_links_28_0= ruleLink )
                    	    // InternalEJSL.g:3419:6: lv_links_28_0= ruleLink
                    	    {

                    	    						newCompositeNode(grammarAccess.getCustomPageAccess().getLinksLinkParserRuleCall_11_2_0());
                    	    					
                    	    pushFollow(FOLLOW_72);
                    	    lv_links_28_0=ruleLink();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCustomPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"links",
                    	    							lv_links_28_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Link");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop108;
                        }
                    } while (true);

                    otherlv_29=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_29, grammarAccess.getCustomPageAccess().getRightCurlyBracketKeyword_11_3());
                    			

                    }
                    break;

            }

            otherlv_30=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_30, grammarAccess.getCustomPageAccess().getRightCurlyBracketKeyword_12());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCustomPage"


    // $ANTLR start "entryRuleHTMLTypes"
    // InternalEJSL.g:3449:1: entryRuleHTMLTypes returns [EObject current=null] : iv_ruleHTMLTypes= ruleHTMLTypes EOF ;
    public final EObject entryRuleHTMLTypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHTMLTypes = null;


        try {
            // InternalEJSL.g:3449:50: (iv_ruleHTMLTypes= ruleHTMLTypes EOF )
            // InternalEJSL.g:3450:2: iv_ruleHTMLTypes= ruleHTMLTypes EOF
            {
             newCompositeNode(grammarAccess.getHTMLTypesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHTMLTypes=ruleHTMLTypes();

            state._fsp--;

             current =iv_ruleHTMLTypes; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHTMLTypes"


    // $ANTLR start "ruleHTMLTypes"
    // InternalEJSL.g:3456:1: ruleHTMLTypes returns [EObject current=null] : (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes ) ;
    public final EObject ruleHTMLTypes() throws RecognitionException {
        EObject current = null;

        EObject this_SimpleHTMLTypes_0 = null;

        EObject this_ComplexHTMLTypes_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3462:2: ( (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes ) )
            // InternalEJSL.g:3463:2: (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes )
            {
            // InternalEJSL.g:3463:2: (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes )
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
                    // InternalEJSL.g:3464:3: this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes
                    {

                    			newCompositeNode(grammarAccess.getHTMLTypesAccess().getSimpleHTMLTypesParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SimpleHTMLTypes_0=ruleSimpleHTMLTypes();

                    state._fsp--;


                    			current = this_SimpleHTMLTypes_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEJSL.g:3473:3: this_ComplexHTMLTypes_1= ruleComplexHTMLTypes
                    {

                    			newCompositeNode(grammarAccess.getHTMLTypesAccess().getComplexHTMLTypesParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ComplexHTMLTypes_1=ruleComplexHTMLTypes();

                    state._fsp--;


                    			current = this_ComplexHTMLTypes_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHTMLTypes"


    // $ANTLR start "entryRuleSimpleHTMLTypes"
    // InternalEJSL.g:3485:1: entryRuleSimpleHTMLTypes returns [EObject current=null] : iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF ;
    public final EObject entryRuleSimpleHTMLTypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleHTMLTypes = null;


        try {
            // InternalEJSL.g:3485:56: (iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF )
            // InternalEJSL.g:3486:2: iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF
            {
             newCompositeNode(grammarAccess.getSimpleHTMLTypesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleHTMLTypes=ruleSimpleHTMLTypes();

            state._fsp--;

             current =iv_ruleSimpleHTMLTypes; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleHTMLTypes"


    // $ANTLR start "ruleSimpleHTMLTypes"
    // InternalEJSL.g:3492:1: ruleSimpleHTMLTypes returns [EObject current=null] : ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) ) ;
    public final EObject ruleSimpleHTMLTypes() throws RecognitionException {
        EObject current = null;

        Enumerator lv_htmltype_0_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3498:2: ( ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) ) )
            // InternalEJSL.g:3499:2: ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) )
            {
            // InternalEJSL.g:3499:2: ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) )
            // InternalEJSL.g:3500:3: (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds )
            {
            // InternalEJSL.g:3500:3: (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds )
            // InternalEJSL.g:3501:4: lv_htmltype_0_0= ruleSimpleHTMLTypeKinds
            {

            				newCompositeNode(grammarAccess.getSimpleHTMLTypesAccess().getHtmltypeSimpleHTMLTypeKindsEnumRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_htmltype_0_0=ruleSimpleHTMLTypeKinds();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getSimpleHTMLTypesRule());
            				}
            				set(
            					current,
            					"htmltype",
            					lv_htmltype_0_0,
            					"de.thm.icampus.joomdd.ejsl.EJSL.SimpleHTMLTypeKinds");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleHTMLTypes"


    // $ANTLR start "entryRuleComplexHTMLTypes"
    // InternalEJSL.g:3521:1: entryRuleComplexHTMLTypes returns [EObject current=null] : iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF ;
    public final EObject entryRuleComplexHTMLTypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComplexHTMLTypes = null;


        try {
            // InternalEJSL.g:3521:57: (iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF )
            // InternalEJSL.g:3522:2: iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF
            {
             newCompositeNode(grammarAccess.getComplexHTMLTypesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComplexHTMLTypes=ruleComplexHTMLTypes();

            state._fsp--;

             current =iv_ruleComplexHTMLTypes; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComplexHTMLTypes"


    // $ANTLR start "ruleComplexHTMLTypes"
    // InternalEJSL.g:3528:1: ruleComplexHTMLTypes returns [EObject current=null] : ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' ) ;
    public final EObject ruleComplexHTMLTypes() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Enumerator lv_htmltype_0_0 = null;

        EObject lv_keyvaluepairs_2_0 = null;

        EObject lv_keyvaluepairs_4_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3534:2: ( ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' ) )
            // InternalEJSL.g:3535:2: ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' )
            {
            // InternalEJSL.g:3535:2: ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' )
            // InternalEJSL.g:3536:3: ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')'
            {
            // InternalEJSL.g:3536:3: ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) )
            // InternalEJSL.g:3537:4: (lv_htmltype_0_0= ruleComplexHTMLTypeKinds )
            {
            // InternalEJSL.g:3537:4: (lv_htmltype_0_0= ruleComplexHTMLTypeKinds )
            // InternalEJSL.g:3538:5: lv_htmltype_0_0= ruleComplexHTMLTypeKinds
            {

            					newCompositeNode(grammarAccess.getComplexHTMLTypesAccess().getHtmltypeComplexHTMLTypeKindsEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_96);
            lv_htmltype_0_0=ruleComplexHTMLTypeKinds();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComplexHTMLTypesRule());
            					}
            					set(
            						current,
            						"htmltype",
            						lv_htmltype_0_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.ComplexHTMLTypeKinds");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,75,FOLLOW_97); 

            			newLeafNode(otherlv_1, grammarAccess.getComplexHTMLTypesAccess().getLeftParenthesisKeyword_1());
            		
            // InternalEJSL.g:3559:3: ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) )
            // InternalEJSL.g:3560:4: (lv_keyvaluepairs_2_0= ruleKeyValuePair )
            {
            // InternalEJSL.g:3560:4: (lv_keyvaluepairs_2_0= ruleKeyValuePair )
            // InternalEJSL.g:3561:5: lv_keyvaluepairs_2_0= ruleKeyValuePair
            {

            					newCompositeNode(grammarAccess.getComplexHTMLTypesAccess().getKeyvaluepairsKeyValuePairParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_98);
            lv_keyvaluepairs_2_0=ruleKeyValuePair();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComplexHTMLTypesRule());
            					}
            					add(
            						current,
            						"keyvaluepairs",
            						lv_keyvaluepairs_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.KeyValuePair");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:3578:3: (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )*
            loop111:
            do {
                int alt111=2;
                int LA111_0 = input.LA(1);

                if ( (LA111_0==21) ) {
                    alt111=1;
                }


                switch (alt111) {
            	case 1 :
            	    // InternalEJSL.g:3579:4: otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) )
            	    {
            	    otherlv_3=(Token)match(input,21,FOLLOW_97); 

            	    				newLeafNode(otherlv_3, grammarAccess.getComplexHTMLTypesAccess().getCommaKeyword_3_0());
            	    			
            	    // InternalEJSL.g:3583:4: ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) )
            	    // InternalEJSL.g:3584:5: (lv_keyvaluepairs_4_0= ruleKeyValuePair )
            	    {
            	    // InternalEJSL.g:3584:5: (lv_keyvaluepairs_4_0= ruleKeyValuePair )
            	    // InternalEJSL.g:3585:6: lv_keyvaluepairs_4_0= ruleKeyValuePair
            	    {

            	    						newCompositeNode(grammarAccess.getComplexHTMLTypesAccess().getKeyvaluepairsKeyValuePairParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_98);
            	    lv_keyvaluepairs_4_0=ruleKeyValuePair();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getComplexHTMLTypesRule());
            	    						}
            	    						add(
            	    							current,
            	    							"keyvaluepairs",
            	    							lv_keyvaluepairs_4_0,
            	    							"de.thm.icampus.joomdd.ejsl.EJSL.KeyValuePair");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop111;
                }
            } while (true);

            otherlv_5=(Token)match(input,76,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getComplexHTMLTypesAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComplexHTMLTypes"


    // $ANTLR start "entryRuleLink"
    // InternalEJSL.g:3611:1: entryRuleLink returns [EObject current=null] : iv_ruleLink= ruleLink EOF ;
    public final EObject entryRuleLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLink = null;


        try {
            // InternalEJSL.g:3611:45: (iv_ruleLink= ruleLink EOF )
            // InternalEJSL.g:3612:2: iv_ruleLink= ruleLink EOF
            {
             newCompositeNode(grammarAccess.getLinkRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLink=ruleLink();

            state._fsp--;

             current =iv_ruleLink; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLink"


    // $ANTLR start "ruleLink"
    // InternalEJSL.g:3618:1: ruleLink returns [EObject current=null] : (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink ) ;
    public final EObject ruleLink() throws RecognitionException {
        EObject current = null;

        EObject this_ExternalLink_0 = null;

        EObject this_InternalLink_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3624:2: ( (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink ) )
            // InternalEJSL.g:3625:2: (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink )
            {
            // InternalEJSL.g:3625:2: (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink )
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
                    // InternalEJSL.g:3626:3: this_ExternalLink_0= ruleExternalLink
                    {

                    			newCompositeNode(grammarAccess.getLinkAccess().getExternalLinkParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ExternalLink_0=ruleExternalLink();

                    state._fsp--;


                    			current = this_ExternalLink_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEJSL.g:3635:3: this_InternalLink_1= ruleInternalLink
                    {

                    			newCompositeNode(grammarAccess.getLinkAccess().getInternalLinkParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_InternalLink_1=ruleInternalLink();

                    state._fsp--;


                    			current = this_InternalLink_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLink"


    // $ANTLR start "entryRuleExternalLink"
    // InternalEJSL.g:3647:1: entryRuleExternalLink returns [EObject current=null] : iv_ruleExternalLink= ruleExternalLink EOF ;
    public final EObject entryRuleExternalLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExternalLink = null;


        try {
            // InternalEJSL.g:3647:53: (iv_ruleExternalLink= ruleExternalLink EOF )
            // InternalEJSL.g:3648:2: iv_ruleExternalLink= ruleExternalLink EOF
            {
             newCompositeNode(grammarAccess.getExternalLinkRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExternalLink=ruleExternalLink();

            state._fsp--;

             current =iv_ruleExternalLink; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExternalLink"


    // $ANTLR start "ruleExternalLink"
    // InternalEJSL.g:3654:1: ruleExternalLink returns [EObject current=null] : ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' ) ;
    public final EObject ruleExternalLink() throws RecognitionException {
        EObject current = null;

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


        	enterRule();

        try {
            // InternalEJSL.g:3660:2: ( ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' ) )
            // InternalEJSL.g:3661:2: ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' )
            {
            // InternalEJSL.g:3661:2: ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' )
            // InternalEJSL.g:3662:3: () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target =' ( (lv_target_4_0= RULE_STRING ) ) (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )? (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}'
            {
            // InternalEJSL.g:3662:3: ()
            // InternalEJSL.g:3663:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExternalLinkAccess().getExternalLinkAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,77,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getExternalLinkAccess().getExternalLinkKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_99); 

            			newLeafNode(otherlv_2, grammarAccess.getExternalLinkAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,78,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getExternalLinkAccess().getTargetKeyword_3());
            		
            // InternalEJSL.g:3681:3: ( (lv_target_4_0= RULE_STRING ) )
            // InternalEJSL.g:3682:4: (lv_target_4_0= RULE_STRING )
            {
            // InternalEJSL.g:3682:4: (lv_target_4_0= RULE_STRING )
            // InternalEJSL.g:3683:5: lv_target_4_0= RULE_STRING
            {
            lv_target_4_0=(Token)match(input,RULE_STRING,FOLLOW_100); 

            					newLeafNode(lv_target_4_0, grammarAccess.getExternalLinkAccess().getTargetSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExternalLinkRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalEJSL.g:3699:3: (otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) ) )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==79) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // InternalEJSL.g:3700:4: otherlv_5= 'linked attribute =' ( ( ruleQualifiedName ) )
                    {
                    otherlv_5=(Token)match(input,79,FOLLOW_51); 

                    				newLeafNode(otherlv_5, grammarAccess.getExternalLinkAccess().getLinkedAttributeKeyword_5_0());
                    			
                    // InternalEJSL.g:3704:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:3705:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:3705:5: ( ruleQualifiedName )
                    // InternalEJSL.g:3706:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getExternalLinkRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getExternalLinkAccess().getLinkedAttributeAttributeCrossReference_5_1_0());
                    					
                    pushFollow(FOLLOW_101);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:3721:3: (otherlv_7= 'linked action =' ( ( ruleQualifiedName ) ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==80) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // InternalEJSL.g:3722:4: otherlv_7= 'linked action =' ( ( ruleQualifiedName ) )
                    {
                    otherlv_7=(Token)match(input,80,FOLLOW_51); 

                    				newLeafNode(otherlv_7, grammarAccess.getExternalLinkAccess().getLinkedActionKeyword_6_0());
                    			
                    // InternalEJSL.g:3726:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:3727:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:3727:5: ( ruleQualifiedName )
                    // InternalEJSL.g:3728:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getExternalLinkRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getExternalLinkAccess().getLinkedActionPageActionCrossReference_6_1_0());
                    					
                    pushFollow(FOLLOW_102);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:3743:3: (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==81) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // InternalEJSL.g:3744:4: otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) )
                    {
                    otherlv_9=(Token)match(input,81,FOLLOW_103); 

                    				newLeafNode(otherlv_9, grammarAccess.getExternalLinkAccess().getLabelKeyword_7_0());
                    			
                    otherlv_10=(Token)match(input,82,FOLLOW_3); 

                    				newLeafNode(otherlv_10, grammarAccess.getExternalLinkAccess().getEqualsSignKeyword_7_1());
                    			
                    // InternalEJSL.g:3752:4: ( (lv_label_11_0= RULE_STRING ) )
                    // InternalEJSL.g:3753:5: (lv_label_11_0= RULE_STRING )
                    {
                    // InternalEJSL.g:3753:5: (lv_label_11_0= RULE_STRING )
                    // InternalEJSL.g:3754:6: lv_label_11_0= RULE_STRING
                    {
                    lv_label_11_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

                    						newLeafNode(lv_label_11_0, grammarAccess.getExternalLinkAccess().getLabelSTRINGTerminalRuleCall_7_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getExternalLinkRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"label",
                    							lv_label_11_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_12, grammarAccess.getExternalLinkAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExternalLink"


    // $ANTLR start "entryRuleInternalLink"
    // InternalEJSL.g:3779:1: entryRuleInternalLink returns [EObject current=null] : iv_ruleInternalLink= ruleInternalLink EOF ;
    public final EObject entryRuleInternalLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInternalLink = null;


        try {
            // InternalEJSL.g:3779:53: (iv_ruleInternalLink= ruleInternalLink EOF )
            // InternalEJSL.g:3780:2: iv_ruleInternalLink= ruleInternalLink EOF
            {
             newCompositeNode(grammarAccess.getInternalLinkRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInternalLink=ruleInternalLink();

            state._fsp--;

             current =iv_ruleInternalLink; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInternalLink"


    // $ANTLR start "ruleInternalLink"
    // InternalEJSL.g:3786:1: ruleInternalLink returns [EObject current=null] : ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink ) ;
    public final EObject ruleInternalLink() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject this_ContextLink_11 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3792:2: ( ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink ) )
            // InternalEJSL.g:3793:2: ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink )
            {
            // InternalEJSL.g:3793:2: ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink )
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
                    // InternalEJSL.g:3794:3: ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) )
                    {
                    // InternalEJSL.g:3794:3: ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' ) )
                    // InternalEJSL.g:3795:4: () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' )
                    {
                    // InternalEJSL.g:3795:4: ()
                    // InternalEJSL.g:3796:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getInternalLinkAccess().getInternalLinkAction_0_0(),
                    						current);
                    				

                    }

                    // InternalEJSL.g:3802:4: (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}' )
                    // InternalEJSL.g:3803:5: otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= '}'
                    {
                    otherlv_1=(Token)match(input,83,FOLLOW_51); 

                    					newLeafNode(otherlv_1, grammarAccess.getInternalLinkAccess().getInternalLinkKeyword_0_1_0());
                    				
                    // InternalEJSL.g:3807:5: ( (lv_name_2_0= ruleMYID ) )
                    // InternalEJSL.g:3808:6: (lv_name_2_0= ruleMYID )
                    {
                    // InternalEJSL.g:3808:6: (lv_name_2_0= ruleMYID )
                    // InternalEJSL.g:3809:7: lv_name_2_0= ruleMYID
                    {

                    							newCompositeNode(grammarAccess.getInternalLinkAccess().getNameMYIDParserRuleCall_0_1_1_0());
                    						
                    pushFollow(FOLLOW_4);
                    lv_name_2_0=ruleMYID();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getInternalLinkRule());
                    							}
                    							set(
                    								current,
                    								"name",
                    								lv_name_2_0,
                    								"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    otherlv_3=(Token)match(input,16,FOLLOW_99); 

                    					newLeafNode(otherlv_3, grammarAccess.getInternalLinkAccess().getLeftCurlyBracketKeyword_0_1_2());
                    				
                    otherlv_4=(Token)match(input,78,FOLLOW_51); 

                    					newLeafNode(otherlv_4, grammarAccess.getInternalLinkAccess().getTargetKeyword_0_1_3());
                    				
                    // InternalEJSL.g:3834:5: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:3835:6: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:3835:6: ( ruleQualifiedName )
                    // InternalEJSL.g:3836:7: ruleQualifiedName
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getInternalLinkRule());
                    							}
                    						

                    							newCompositeNode(grammarAccess.getInternalLinkAccess().getTargetPageCrossReference_0_1_4_0());
                    						
                    pushFollow(FOLLOW_104);
                    ruleQualifiedName();

                    state._fsp--;


                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalEJSL.g:3850:5: (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )?
                    int alt116=2;
                    int LA116_0 = input.LA(1);

                    if ( (LA116_0==79) ) {
                        alt116=1;
                    }
                    switch (alt116) {
                        case 1 :
                            // InternalEJSL.g:3851:6: otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) )
                            {
                            otherlv_6=(Token)match(input,79,FOLLOW_51); 

                            						newLeafNode(otherlv_6, grammarAccess.getInternalLinkAccess().getLinkedAttributeKeyword_0_1_5_0());
                            					
                            // InternalEJSL.g:3855:6: ( ( ruleQualifiedName ) )
                            // InternalEJSL.g:3856:7: ( ruleQualifiedName )
                            {
                            // InternalEJSL.g:3856:7: ( ruleQualifiedName )
                            // InternalEJSL.g:3857:8: ruleQualifiedName
                            {

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getInternalLinkRule());
                            								}
                            							

                            								newCompositeNode(grammarAccess.getInternalLinkAccess().getLinkedAttributeAttributeCrossReference_0_1_5_1_0());
                            							
                            pushFollow(FOLLOW_105);
                            ruleQualifiedName();

                            state._fsp--;


                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }


                            }
                            break;

                    }

                    // InternalEJSL.g:3872:5: (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )?
                    int alt117=2;
                    int LA117_0 = input.LA(1);

                    if ( (LA117_0==80) ) {
                        alt117=1;
                    }
                    switch (alt117) {
                        case 1 :
                            // InternalEJSL.g:3873:6: otherlv_8= 'linked action =' ( ( ruleQualifiedName ) )
                            {
                            otherlv_8=(Token)match(input,80,FOLLOW_51); 

                            						newLeafNode(otherlv_8, grammarAccess.getInternalLinkAccess().getLinkedActionKeyword_0_1_6_0());
                            					
                            // InternalEJSL.g:3877:6: ( ( ruleQualifiedName ) )
                            // InternalEJSL.g:3878:7: ( ruleQualifiedName )
                            {
                            // InternalEJSL.g:3878:7: ( ruleQualifiedName )
                            // InternalEJSL.g:3879:8: ruleQualifiedName
                            {

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getInternalLinkRule());
                            								}
                            							

                            								newCompositeNode(grammarAccess.getInternalLinkAccess().getLinkedActionPageActionCrossReference_0_1_6_1_0());
                            							
                            pushFollow(FOLLOW_7);
                            ruleQualifiedName();

                            state._fsp--;


                            								afterParserOrEnumRuleCall();
                            							

                            }


                            }


                            }
                            break;

                    }

                    otherlv_10=(Token)match(input,18,FOLLOW_2); 

                    					newLeafNode(otherlv_10, grammarAccess.getInternalLinkAccess().getRightCurlyBracketKeyword_0_1_7());
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:3901:3: this_ContextLink_11= ruleContextLink
                    {

                    			newCompositeNode(grammarAccess.getInternalLinkAccess().getContextLinkParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ContextLink_11=ruleContextLink();

                    state._fsp--;


                    			current = this_ContextLink_11;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInternalLink"


    // $ANTLR start "entryRuleContextLink"
    // InternalEJSL.g:3913:1: entryRuleContextLink returns [EObject current=null] : iv_ruleContextLink= ruleContextLink EOF ;
    public final EObject entryRuleContextLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextLink = null;


        try {
            // InternalEJSL.g:3913:52: (iv_ruleContextLink= ruleContextLink EOF )
            // InternalEJSL.g:3914:2: iv_ruleContextLink= ruleContextLink EOF
            {
             newCompositeNode(grammarAccess.getContextLinkRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleContextLink=ruleContextLink();

            state._fsp--;

             current =iv_ruleContextLink; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleContextLink"


    // $ANTLR start "ruleContextLink"
    // InternalEJSL.g:3920:1: ruleContextLink returns [EObject current=null] : ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' ) ;
    public final EObject ruleContextLink() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_linkparameters_12_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3926:2: ( ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' ) )
            // InternalEJSL.g:3927:2: ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' )
            {
            // InternalEJSL.g:3927:2: ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' )
            // InternalEJSL.g:3928:3: () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target =' ( ( ruleQualifiedName ) ) (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )? (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )? otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}'
            {
            // InternalEJSL.g:3928:3: ()
            // InternalEJSL.g:3929:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getContextLinkAccess().getContextLinkAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,84,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getContextLinkAccess().getInternalcontextLinkKeyword_1());
            		
            // InternalEJSL.g:3939:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:3940:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:3940:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:3941:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getContextLinkAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getContextLinkRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_99); 

            			newLeafNode(otherlv_3, grammarAccess.getContextLinkAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,78,FOLLOW_51); 

            			newLeafNode(otherlv_4, grammarAccess.getContextLinkAccess().getTargetKeyword_4());
            		
            // InternalEJSL.g:3966:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:3967:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:3967:4: ( ruleQualifiedName )
            // InternalEJSL.g:3968:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getContextLinkRule());
            					}
            				

            					newCompositeNode(grammarAccess.getContextLinkAccess().getTargetPageCrossReference_5_0());
            				
            pushFollow(FOLLOW_106);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:3982:3: (otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) ) )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==79) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // InternalEJSL.g:3983:4: otherlv_6= 'linked attribute =' ( ( ruleQualifiedName ) )
                    {
                    otherlv_6=(Token)match(input,79,FOLLOW_51); 

                    				newLeafNode(otherlv_6, grammarAccess.getContextLinkAccess().getLinkedAttributeKeyword_6_0());
                    			
                    // InternalEJSL.g:3987:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:3988:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:3988:5: ( ruleQualifiedName )
                    // InternalEJSL.g:3989:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getContextLinkRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getContextLinkAccess().getLinkedAttributeAttributeCrossReference_6_1_0());
                    					
                    pushFollow(FOLLOW_107);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:4004:3: (otherlv_8= 'linked action =' ( ( ruleQualifiedName ) ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==80) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // InternalEJSL.g:4005:4: otherlv_8= 'linked action =' ( ( ruleQualifiedName ) )
                    {
                    otherlv_8=(Token)match(input,80,FOLLOW_51); 

                    				newLeafNode(otherlv_8, grammarAccess.getContextLinkAccess().getLinkedActionKeyword_7_0());
                    			
                    // InternalEJSL.g:4009:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:4010:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:4010:5: ( ruleQualifiedName )
                    // InternalEJSL.g:4011:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getContextLinkRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getContextLinkAccess().getLinkedActionPageActionCrossReference_7_1_0());
                    					
                    pushFollow(FOLLOW_108);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,85,FOLLOW_4); 

            			newLeafNode(otherlv_10, grammarAccess.getContextLinkAccess().getLinkparametersKeyword_8());
            		
            otherlv_11=(Token)match(input,16,FOLLOW_12); 

            			newLeafNode(otherlv_11, grammarAccess.getContextLinkAccess().getLeftCurlyBracketKeyword_9());
            		
            // InternalEJSL.g:4034:3: ( (lv_linkparameters_12_0= ruleLinkParameter ) )*
            loop121:
            do {
                int alt121=2;
                int LA121_0 = input.LA(1);

                if ( (LA121_0==34) ) {
                    alt121=1;
                }


                switch (alt121) {
            	case 1 :
            	    // InternalEJSL.g:4035:4: (lv_linkparameters_12_0= ruleLinkParameter )
            	    {
            	    // InternalEJSL.g:4035:4: (lv_linkparameters_12_0= ruleLinkParameter )
            	    // InternalEJSL.g:4036:5: lv_linkparameters_12_0= ruleLinkParameter
            	    {

            	    					newCompositeNode(grammarAccess.getContextLinkAccess().getLinkparametersLinkParameterParserRuleCall_10_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_linkparameters_12_0=ruleLinkParameter();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getContextLinkRule());
            	    					}
            	    					add(
            	    						current,
            	    						"linkparameters",
            	    						lv_linkparameters_12_0,
            	    						"de.thm.icampus.joomdd.ejsl.EJSL.LinkParameter");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop121;
                }
            } while (true);

            otherlv_13=(Token)match(input,18,FOLLOW_7); 

            			newLeafNode(otherlv_13, grammarAccess.getContextLinkAccess().getRightCurlyBracketKeyword_11());
            		
            otherlv_14=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_14, grammarAccess.getContextLinkAccess().getRightCurlyBracketKeyword_12());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleContextLink"


    // $ANTLR start "entryRuleLinkParameter"
    // InternalEJSL.g:4065:1: entryRuleLinkParameter returns [EObject current=null] : iv_ruleLinkParameter= ruleLinkParameter EOF ;
    public final EObject entryRuleLinkParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLinkParameter = null;


        try {
            // InternalEJSL.g:4065:54: (iv_ruleLinkParameter= ruleLinkParameter EOF )
            // InternalEJSL.g:4066:2: iv_ruleLinkParameter= ruleLinkParameter EOF
            {
             newCompositeNode(grammarAccess.getLinkParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLinkParameter=ruleLinkParameter();

            state._fsp--;

             current =iv_ruleLinkParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLinkParameter"


    // $ANTLR start "ruleLinkParameter"
    // InternalEJSL.g:4072:1: ruleLinkParameter returns [EObject current=null] : ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleLinkParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_id_6_0=null;
        Token lv_value_7_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4078:2: ( ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) ) ) )
            // InternalEJSL.g:4079:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) ) )
            {
            // InternalEJSL.g:4079:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) ) )
            // InternalEJSL.g:4080:3: () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) )
            {
            // InternalEJSL.g:4080:3: ()
            // InternalEJSL.g:4081:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getLinkParameterAccess().getLinkParameterAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,34,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getLinkParameterAccess().getParameterKeyword_1());
            		
            // InternalEJSL.g:4091:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4092:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4092:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4093:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getLinkParameterAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_103);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLinkParameterRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,82,FOLLOW_109); 

            			newLeafNode(otherlv_3, grammarAccess.getLinkParameterAccess().getEqualsSignKeyword_3());
            		
            // InternalEJSL.g:4114:3: ( (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) ) | ( (lv_value_7_0= RULE_STRING ) ) )
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
                    // InternalEJSL.g:4115:4: (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) )
                    {
                    // InternalEJSL.g:4115:4: (otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) ) )
                    // InternalEJSL.g:4116:5: otherlv_4= '*Attribute ' ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) )
                    {
                    otherlv_4=(Token)match(input,86,FOLLOW_110); 

                    					newLeafNode(otherlv_4, grammarAccess.getLinkParameterAccess().getAttributeKeyword_4_0_0());
                    				
                    // InternalEJSL.g:4120:5: ( ( (otherlv_5= RULE_STRING ) ) | ( (lv_id_6_0= 'ID' ) ) )
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
                            // InternalEJSL.g:4121:6: ( (otherlv_5= RULE_STRING ) )
                            {
                            // InternalEJSL.g:4121:6: ( (otherlv_5= RULE_STRING ) )
                            // InternalEJSL.g:4122:7: (otherlv_5= RULE_STRING )
                            {
                            // InternalEJSL.g:4122:7: (otherlv_5= RULE_STRING )
                            // InternalEJSL.g:4123:8: otherlv_5= RULE_STRING
                            {

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getLinkParameterRule());
                            								}
                            							
                            otherlv_5=(Token)match(input,RULE_STRING,FOLLOW_2); 

                            								newLeafNode(otherlv_5, grammarAccess.getLinkParameterAccess().getAttvalueAttributeCrossReference_4_0_1_0_0());
                            							

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalEJSL.g:4135:6: ( (lv_id_6_0= 'ID' ) )
                            {
                            // InternalEJSL.g:4135:6: ( (lv_id_6_0= 'ID' ) )
                            // InternalEJSL.g:4136:7: (lv_id_6_0= 'ID' )
                            {
                            // InternalEJSL.g:4136:7: (lv_id_6_0= 'ID' )
                            // InternalEJSL.g:4137:8: lv_id_6_0= 'ID'
                            {
                            lv_id_6_0=(Token)match(input,52,FOLLOW_2); 

                            								newLeafNode(lv_id_6_0, grammarAccess.getLinkParameterAccess().getIdIDKeyword_4_0_1_1_0());
                            							

                            								if (current==null) {
                            									current = createModelElement(grammarAccess.getLinkParameterRule());
                            								}
                            								setWithLastConsumed(current, "id", true, "ID");
                            							

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:4152:4: ( (lv_value_7_0= RULE_STRING ) )
                    {
                    // InternalEJSL.g:4152:4: ( (lv_value_7_0= RULE_STRING ) )
                    // InternalEJSL.g:4153:5: (lv_value_7_0= RULE_STRING )
                    {
                    // InternalEJSL.g:4153:5: (lv_value_7_0= RULE_STRING )
                    // InternalEJSL.g:4154:6: lv_value_7_0= RULE_STRING
                    {
                    lv_value_7_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_value_7_0, grammarAccess.getLinkParameterAccess().getValueSTRINGTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getLinkParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_7_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLinkParameter"


    // $ANTLR start "entryRuleExtension"
    // InternalEJSL.g:4175:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // InternalEJSL.g:4175:50: (iv_ruleExtension= ruleExtension EOF )
            // InternalEJSL.g:4176:2: iv_ruleExtension= ruleExtension EOF
            {
             newCompositeNode(grammarAccess.getExtensionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExtension=ruleExtension();

            state._fsp--;

             current =iv_ruleExtension; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExtension"


    // $ANTLR start "ruleExtension"
    // InternalEJSL.g:4182:1: ruleExtension returns [EObject current=null] : (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate ) ;
    public final EObject ruleExtension() throws RecognitionException {
        EObject current = null;

        EObject this_ExtensionPackage_0 = null;

        EObject this_Component_1 = null;

        EObject this_Module_2 = null;

        EObject this_Plugin_3 = null;

        EObject this_Library_4 = null;

        EObject this_Template_5 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4188:2: ( (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate ) )
            // InternalEJSL.g:4189:2: (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate )
            {
            // InternalEJSL.g:4189:2: (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate )
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
                    // InternalEJSL.g:4190:3: this_ExtensionPackage_0= ruleExtensionPackage
                    {

                    			newCompositeNode(grammarAccess.getExtensionAccess().getExtensionPackageParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ExtensionPackage_0=ruleExtensionPackage();

                    state._fsp--;


                    			current = this_ExtensionPackage_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEJSL.g:4199:3: this_Component_1= ruleComponent
                    {

                    			newCompositeNode(grammarAccess.getExtensionAccess().getComponentParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Component_1=ruleComponent();

                    state._fsp--;


                    			current = this_Component_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalEJSL.g:4208:3: this_Module_2= ruleModule
                    {

                    			newCompositeNode(grammarAccess.getExtensionAccess().getModuleParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Module_2=ruleModule();

                    state._fsp--;


                    			current = this_Module_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalEJSL.g:4217:3: this_Plugin_3= rulePlugin
                    {

                    			newCompositeNode(grammarAccess.getExtensionAccess().getPluginParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Plugin_3=rulePlugin();

                    state._fsp--;


                    			current = this_Plugin_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalEJSL.g:4226:3: this_Library_4= ruleLibrary
                    {

                    			newCompositeNode(grammarAccess.getExtensionAccess().getLibraryParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_Library_4=ruleLibrary();

                    state._fsp--;


                    			current = this_Library_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalEJSL.g:4235:3: this_Template_5= ruleTemplate
                    {

                    			newCompositeNode(grammarAccess.getExtensionAccess().getTemplateParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_Template_5=ruleTemplate();

                    state._fsp--;


                    			current = this_Template_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExtension"


    // $ANTLR start "entryRuleExtensionPackage"
    // InternalEJSL.g:4247:1: entryRuleExtensionPackage returns [EObject current=null] : iv_ruleExtensionPackage= ruleExtensionPackage EOF ;
    public final EObject entryRuleExtensionPackage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtensionPackage = null;


        try {
            // InternalEJSL.g:4247:57: (iv_ruleExtensionPackage= ruleExtensionPackage EOF )
            // InternalEJSL.g:4248:2: iv_ruleExtensionPackage= ruleExtensionPackage EOF
            {
             newCompositeNode(grammarAccess.getExtensionPackageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExtensionPackage=ruleExtensionPackage();

            state._fsp--;

             current =iv_ruleExtensionPackage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExtensionPackage"


    // $ANTLR start "ruleExtensionPackage"
    // InternalEJSL.g:4254:1: ruleExtensionPackage returns [EObject current=null] : ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' ) ;
    public final EObject ruleExtensionPackage() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_manifest_6_0 = null;

        EObject lv_languages_10_0 = null;

        EObject lv_extensions_14_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4260:2: ( ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' ) )
            // InternalEJSL.g:4261:2: ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' )
            {
            // InternalEJSL.g:4261:2: ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' )
            // InternalEJSL.g:4262:3: () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}'
            {
            // InternalEJSL.g:4262:3: ()
            // InternalEJSL.g:4263:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExtensionPackageAccess().getExtensionPackageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,87,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getExtensionPackageAccess().getExtensionPackageKeyword_1());
            		
            // InternalEJSL.g:4273:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4274:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4274:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4275:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getExtensionPackageAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExtensionPackageRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			newLeafNode(otherlv_3, grammarAccess.getExtensionPackageAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getExtensionPackageAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			newLeafNode(otherlv_5, grammarAccess.getExtensionPackageAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:4304:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:4305:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:4305:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:4306:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getExtensionPackageAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExtensionPackageRule());
            					}
            					set(
            						current,
            						"manifest",
            						lv_manifest_6_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Manifestation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,18,FOLLOW_113); 

            			newLeafNode(otherlv_7, grammarAccess.getExtensionPackageAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:4327:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==89) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // InternalEJSL.g:4328:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,89,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getExtensionPackageAccess().getLanguagesKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_114); 

                    				newLeafNode(otherlv_9, grammarAccess.getExtensionPackageAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:4336:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop125:
                    do {
                        int alt125=2;
                        int LA125_0 = input.LA(1);

                        if ( (LA125_0==124) ) {
                            alt125=1;
                        }


                        switch (alt125) {
                    	case 1 :
                    	    // InternalEJSL.g:4337:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:4337:5: (lv_languages_10_0= ruleLanguage )
                    	    // InternalEJSL.g:4338:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getExtensionPackageAccess().getLanguagesLanguageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_10_0=ruleLanguage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getExtensionPackageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"languages",
                    	    							lv_languages_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Language");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop125;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_115); 

                    				newLeafNode(otherlv_11, grammarAccess.getExtensionPackageAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            otherlv_12=(Token)match(input,25,FOLLOW_4); 

            			newLeafNode(otherlv_12, grammarAccess.getExtensionPackageAccess().getExtensionsKeyword_9());
            		
            otherlv_13=(Token)match(input,16,FOLLOW_116); 

            			newLeafNode(otherlv_13, grammarAccess.getExtensionPackageAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalEJSL.g:4368:3: ( (lv_extensions_14_0= ruleExtension ) )+
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
            	    // InternalEJSL.g:4369:4: (lv_extensions_14_0= ruleExtension )
            	    {
            	    // InternalEJSL.g:4369:4: (lv_extensions_14_0= ruleExtension )
            	    // InternalEJSL.g:4370:5: lv_extensions_14_0= ruleExtension
            	    {

            	    					newCompositeNode(grammarAccess.getExtensionPackageAccess().getExtensionsExtensionParserRuleCall_11_0());
            	    				
            	    pushFollow(FOLLOW_21);
            	    lv_extensions_14_0=ruleExtension();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getExtensionPackageRule());
            	    					}
            	    					add(
            	    						current,
            	    						"extensions",
            	    						lv_extensions_14_0,
            	    						"de.thm.icampus.joomdd.ejsl.EJSL.Extension");
            	    					afterParserOrEnumRuleCall();
            	    				

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

            otherlv_15=(Token)match(input,18,FOLLOW_7); 

            			newLeafNode(otherlv_15, grammarAccess.getExtensionPackageAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_16=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_16, grammarAccess.getExtensionPackageAccess().getRightCurlyBracketKeyword_13());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExtensionPackage"


    // $ANTLR start "entryRuleComponent"
    // InternalEJSL.g:4399:1: entryRuleComponent returns [EObject current=null] : iv_ruleComponent= ruleComponent EOF ;
    public final EObject entryRuleComponent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent = null;


        try {
            // InternalEJSL.g:4399:50: (iv_ruleComponent= ruleComponent EOF )
            // InternalEJSL.g:4400:2: iv_ruleComponent= ruleComponent EOF
            {
             newCompositeNode(grammarAccess.getComponentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent=ruleComponent();

            state._fsp--;

             current =iv_ruleComponent; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComponent"


    // $ANTLR start "ruleComponent"
    // InternalEJSL.g:4406:1: ruleComponent returns [EObject current=null] : ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' ) ;
    public final EObject ruleComponent() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_manifest_6_0 = null;

        EObject lv_globalParamter_10_0 = null;

        EObject lv_languages_14_0 = null;

        EObject lv_sections_18_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4412:2: ( ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' ) )
            // InternalEJSL.g:4413:2: ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' )
            {
            // InternalEJSL.g:4413:2: ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' )
            // InternalEJSL.g:4414:3: () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}'
            {
            // InternalEJSL.g:4414:3: ()
            // InternalEJSL.g:4415:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getComponentAccess().getComponentAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,90,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getComponentAccess().getComponentKeyword_1());
            		
            // InternalEJSL.g:4425:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4426:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4426:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4427:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getComponentAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComponentRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			newLeafNode(otherlv_3, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getComponentAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			newLeafNode(otherlv_5, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:4456:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:4457:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:4457:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:4458:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getComponentAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComponentRule());
            					}
            					set(
            						current,
            						"manifest",
            						lv_manifest_6_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Manifestation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,18,FOLLOW_117); 

            			newLeafNode(otherlv_7, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:4479:3: (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==91) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // InternalEJSL.g:4480:4: otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,91,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getComponentAccess().getGlobalParameterKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_14); 

                    				newLeafNode(otherlv_9, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:4488:4: ( (lv_globalParamter_10_0= ruleParameterGroup ) )*
                    loop128:
                    do {
                        int alt128=2;
                        int LA128_0 = input.LA(1);

                        if ( (LA128_0==40) ) {
                            alt128=1;
                        }


                        switch (alt128) {
                    	case 1 :
                    	    // InternalEJSL.g:4489:5: (lv_globalParamter_10_0= ruleParameterGroup )
                    	    {
                    	    // InternalEJSL.g:4489:5: (lv_globalParamter_10_0= ruleParameterGroup )
                    	    // InternalEJSL.g:4490:6: lv_globalParamter_10_0= ruleParameterGroup
                    	    {

                    	    						newCompositeNode(grammarAccess.getComponentAccess().getGlobalParamterParameterGroupParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_14);
                    	    lv_globalParamter_10_0=ruleParameterGroup();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getComponentRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"globalParamter",
                    	    							lv_globalParamter_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.ParameterGroup");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop128;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_118); 

                    				newLeafNode(otherlv_11, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:4512:3: (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==89) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // InternalEJSL.g:4513:4: otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}'
                    {
                    otherlv_12=(Token)match(input,89,FOLLOW_4); 

                    				newLeafNode(otherlv_12, grammarAccess.getComponentAccess().getLanguagesKeyword_9_0());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_114); 

                    				newLeafNode(otherlv_13, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_9_1());
                    			
                    // InternalEJSL.g:4521:4: ( (lv_languages_14_0= ruleLanguage ) )*
                    loop130:
                    do {
                        int alt130=2;
                        int LA130_0 = input.LA(1);

                        if ( (LA130_0==124) ) {
                            alt130=1;
                        }


                        switch (alt130) {
                    	case 1 :
                    	    // InternalEJSL.g:4522:5: (lv_languages_14_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:4522:5: (lv_languages_14_0= ruleLanguage )
                    	    // InternalEJSL.g:4523:6: lv_languages_14_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getComponentAccess().getLanguagesLanguageParserRuleCall_9_2_0());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_14_0=ruleLanguage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getComponentRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"languages",
                    	    							lv_languages_14_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Language");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop130;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,18,FOLLOW_119); 

                    				newLeafNode(otherlv_15, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_9_3());
                    			

                    }
                    break;

            }

            otherlv_16=(Token)match(input,29,FOLLOW_4); 

            			newLeafNode(otherlv_16, grammarAccess.getComponentAccess().getSectionsKeyword_10());
            		
            otherlv_17=(Token)match(input,16,FOLLOW_28); 

            			newLeafNode(otherlv_17, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_11());
            		
            // InternalEJSL.g:4553:3: ( (lv_sections_18_0= ruleSection ) )+
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
            	    // InternalEJSL.g:4554:4: (lv_sections_18_0= ruleSection )
            	    {
            	    // InternalEJSL.g:4554:4: (lv_sections_18_0= ruleSection )
            	    // InternalEJSL.g:4555:5: lv_sections_18_0= ruleSection
            	    {

            	    					newCompositeNode(grammarAccess.getComponentAccess().getSectionsSectionParserRuleCall_12_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_sections_18_0=ruleSection();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getComponentRule());
            	    					}
            	    					add(
            	    						current,
            	    						"sections",
            	    						lv_sections_18_0,
            	    						"de.thm.icampus.joomdd.ejsl.EJSL.Section");
            	    					afterParserOrEnumRuleCall();
            	    				

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

            otherlv_19=(Token)match(input,18,FOLLOW_7); 

            			newLeafNode(otherlv_19, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_13());
            		
            otherlv_20=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_20, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_14());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComponent"


    // $ANTLR start "entryRuleSection"
    // InternalEJSL.g:4584:1: entryRuleSection returns [EObject current=null] : iv_ruleSection= ruleSection EOF ;
    public final EObject entryRuleSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSection = null;


        try {
            // InternalEJSL.g:4584:48: (iv_ruleSection= ruleSection EOF )
            // InternalEJSL.g:4585:2: iv_ruleSection= ruleSection EOF
            {
             newCompositeNode(grammarAccess.getSectionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSection=ruleSection();

            state._fsp--;

             current =iv_ruleSection; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSection"


    // $ANTLR start "ruleSection"
    // InternalEJSL.g:4591:1: ruleSection returns [EObject current=null] : (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend ) ;
    public final EObject ruleSection() throws RecognitionException {
        EObject current = null;

        EObject this_Backend_0 = null;

        EObject this_Frontend_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4597:2: ( (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend ) )
            // InternalEJSL.g:4598:2: (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend )
            {
            // InternalEJSL.g:4598:2: (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend )
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
                    // InternalEJSL.g:4599:3: this_Backend_0= ruleBackend
                    {

                    			newCompositeNode(grammarAccess.getSectionAccess().getBackendParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Backend_0=ruleBackend();

                    state._fsp--;


                    			current = this_Backend_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalEJSL.g:4608:3: this_Frontend_1= ruleFrontend
                    {

                    			newCompositeNode(grammarAccess.getSectionAccess().getFrontendParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Frontend_1=ruleFrontend();

                    state._fsp--;


                    			current = this_Frontend_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSection"


    // $ANTLR start "entryRuleBackend"
    // InternalEJSL.g:4620:1: entryRuleBackend returns [EObject current=null] : iv_ruleBackend= ruleBackend EOF ;
    public final EObject entryRuleBackend() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBackend = null;


        try {
            // InternalEJSL.g:4620:48: (iv_ruleBackend= ruleBackend EOF )
            // InternalEJSL.g:4621:2: iv_ruleBackend= ruleBackend EOF
            {
             newCompositeNode(grammarAccess.getBackendRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBackend=ruleBackend();

            state._fsp--;

             current =iv_ruleBackend; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBackend"


    // $ANTLR start "ruleBackend"
    // InternalEJSL.g:4627:1: ruleBackend returns [EObject current=null] : ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) ;
    public final EObject ruleBackend() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_pageRef_5_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4633:2: ( ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) )
            // InternalEJSL.g:4634:2: ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            {
            // InternalEJSL.g:4634:2: ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            // InternalEJSL.g:4635:3: () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}'
            {
            // InternalEJSL.g:4635:3: ()
            // InternalEJSL.g:4636:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBackendAccess().getBackendSectionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,92,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getBackendAccess().getBackendSectionKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_120); 

            			newLeafNode(otherlv_2, grammarAccess.getBackendAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,93,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getBackendAccess().getPagesKeyword_3());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_121); 

            			newLeafNode(otherlv_4, grammarAccess.getBackendAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:4658:3: ( (lv_pageRef_5_0= rulePageReference ) )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==94) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // InternalEJSL.g:4659:4: (lv_pageRef_5_0= rulePageReference )
            	    {
            	    // InternalEJSL.g:4659:4: (lv_pageRef_5_0= rulePageReference )
            	    // InternalEJSL.g:4660:5: lv_pageRef_5_0= rulePageReference
            	    {

            	    					newCompositeNode(grammarAccess.getBackendAccess().getPageRefPageReferenceParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_121);
            	    lv_pageRef_5_0=rulePageReference();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getBackendRule());
            	    					}
            	    					add(
            	    						current,
            	    						"pageRef",
            	    						lv_pageRef_5_0,
            	    						"de.thm.icampus.joomdd.ejsl.EJSL.PageReference");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);

            otherlv_6=(Token)match(input,18,FOLLOW_7); 

            			newLeafNode(otherlv_6, grammarAccess.getBackendAccess().getRightCurlyBracketKeyword_6());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getBackendAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBackend"


    // $ANTLR start "entryRulePageReference"
    // InternalEJSL.g:4689:1: entryRulePageReference returns [EObject current=null] : iv_rulePageReference= rulePageReference EOF ;
    public final EObject entryRulePageReference() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePageReference = null;


        try {
            // InternalEJSL.g:4689:54: (iv_rulePageReference= rulePageReference EOF )
            // InternalEJSL.g:4690:2: iv_rulePageReference= rulePageReference EOF
            {
             newCompositeNode(grammarAccess.getPageReferenceRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePageReference=rulePageReference();

            state._fsp--;

             current =iv_rulePageReference; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePageReference"


    // $ANTLR start "rulePageReference"
    // InternalEJSL.g:4696:1: rulePageReference returns [EObject current=null] : (otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )? ) ;
    public final EObject rulePageReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Enumerator lv_sect_4_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4702:2: ( (otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )? ) )
            // InternalEJSL.g:4703:2: (otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )? )
            {
            // InternalEJSL.g:4703:2: (otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )? )
            // InternalEJSL.g:4704:3: otherlv_0= '*Page :' ( ( ruleQualifiedName ) ) (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )?
            {
            otherlv_0=(Token)match(input,94,FOLLOW_51); 

            			newLeafNode(otherlv_0, grammarAccess.getPageReferenceAccess().getPageKeyword_0());
            		
            // InternalEJSL.g:4708:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:4709:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:4709:4: ( ruleQualifiedName )
            // InternalEJSL.g:4710:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPageReferenceRule());
            					}
            				

            					newCompositeNode(grammarAccess.getPageReferenceAccess().getPagePageCrossReference_1_0());
            				
            pushFollow(FOLLOW_122);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:4724:3: (otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) ) )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==95) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // InternalEJSL.g:4725:4: otherlv_2= 'from :' ( ( ruleQualifiedName ) ) ( (lv_sect_4_0= ruleSectionKinds ) )
                    {
                    otherlv_2=(Token)match(input,95,FOLLOW_51); 

                    				newLeafNode(otherlv_2, grammarAccess.getPageReferenceAccess().getFromKeyword_2_0());
                    			
                    // InternalEJSL.g:4729:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:4730:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:4730:5: ( ruleQualifiedName )
                    // InternalEJSL.g:4731:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPageReferenceRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getPageReferenceAccess().getPagescrComponentCrossReference_2_1_0());
                    					
                    pushFollow(FOLLOW_123);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:4745:4: ( (lv_sect_4_0= ruleSectionKinds ) )
                    // InternalEJSL.g:4746:5: (lv_sect_4_0= ruleSectionKinds )
                    {
                    // InternalEJSL.g:4746:5: (lv_sect_4_0= ruleSectionKinds )
                    // InternalEJSL.g:4747:6: lv_sect_4_0= ruleSectionKinds
                    {

                    						newCompositeNode(grammarAccess.getPageReferenceAccess().getSectSectionKindsEnumRuleCall_2_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_sect_4_0=ruleSectionKinds();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPageReferenceRule());
                    						}
                    						set(
                    							current,
                    							"sect",
                    							lv_sect_4_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.SectionKinds");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageReference"


    // $ANTLR start "entryRuleFrontend"
    // InternalEJSL.g:4769:1: entryRuleFrontend returns [EObject current=null] : iv_ruleFrontend= ruleFrontend EOF ;
    public final EObject entryRuleFrontend() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFrontend = null;


        try {
            // InternalEJSL.g:4769:49: (iv_ruleFrontend= ruleFrontend EOF )
            // InternalEJSL.g:4770:2: iv_ruleFrontend= ruleFrontend EOF
            {
             newCompositeNode(grammarAccess.getFrontendRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFrontend=ruleFrontend();

            state._fsp--;

             current =iv_ruleFrontend; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFrontend"


    // $ANTLR start "ruleFrontend"
    // InternalEJSL.g:4776:1: ruleFrontend returns [EObject current=null] : ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) ;
    public final EObject ruleFrontend() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_pageRef_5_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4782:2: ( ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) )
            // InternalEJSL.g:4783:2: ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            {
            // InternalEJSL.g:4783:2: ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            // InternalEJSL.g:4784:3: () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}'
            {
            // InternalEJSL.g:4784:3: ()
            // InternalEJSL.g:4785:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFrontendAccess().getFrontendSectionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,96,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getFrontendAccess().getFrontendSectionKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_120); 

            			newLeafNode(otherlv_2, grammarAccess.getFrontendAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,93,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getFrontendAccess().getPagesKeyword_3());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_121); 

            			newLeafNode(otherlv_4, grammarAccess.getFrontendAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:4807:3: ( (lv_pageRef_5_0= rulePageReference ) )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==94) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // InternalEJSL.g:4808:4: (lv_pageRef_5_0= rulePageReference )
            	    {
            	    // InternalEJSL.g:4808:4: (lv_pageRef_5_0= rulePageReference )
            	    // InternalEJSL.g:4809:5: lv_pageRef_5_0= rulePageReference
            	    {

            	    					newCompositeNode(grammarAccess.getFrontendAccess().getPageRefPageReferenceParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_121);
            	    lv_pageRef_5_0=rulePageReference();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFrontendRule());
            	    					}
            	    					add(
            	    						current,
            	    						"pageRef",
            	    						lv_pageRef_5_0,
            	    						"de.thm.icampus.joomdd.ejsl.EJSL.PageReference");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop136;
                }
            } while (true);

            otherlv_6=(Token)match(input,18,FOLLOW_7); 

            			newLeafNode(otherlv_6, grammarAccess.getFrontendAccess().getRightCurlyBracketKeyword_6());
            		
            otherlv_7=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getFrontendAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFrontend"


    // $ANTLR start "entryRuleModule"
    // InternalEJSL.g:4838:1: entryRuleModule returns [EObject current=null] : iv_ruleModule= ruleModule EOF ;
    public final EObject entryRuleModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModule = null;


        try {
            // InternalEJSL.g:4838:47: (iv_ruleModule= ruleModule EOF )
            // InternalEJSL.g:4839:2: iv_ruleModule= ruleModule EOF
            {
             newCompositeNode(grammarAccess.getModuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModule=ruleModule();

            state._fsp--;

             current =iv_ruleModule; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModule"


    // $ANTLR start "ruleModule"
    // InternalEJSL.g:4845:1: ruleModule returns [EObject current=null] : ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' ) ;
    public final EObject ruleModule() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_manifest_6_0 = null;

        EObject lv_languages_10_0 = null;

        EObject lv_pageRef_12_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4851:2: ( ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' ) )
            // InternalEJSL.g:4852:2: ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' )
            {
            // InternalEJSL.g:4852:2: ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' )
            // InternalEJSL.g:4853:3: () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}'
            {
            // InternalEJSL.g:4853:3: ()
            // InternalEJSL.g:4854:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getModuleAccess().getModuleAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,97,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getModuleAccess().getModuleKeyword_1());
            		
            // InternalEJSL.g:4864:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4865:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4865:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4866:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getModuleAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModuleRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			newLeafNode(otherlv_3, grammarAccess.getModuleAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getModuleAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			newLeafNode(otherlv_5, grammarAccess.getModuleAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:4895:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:4896:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:4896:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:4897:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getModuleAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModuleRule());
            					}
            					set(
            						current,
            						"manifest",
            						lv_manifest_6_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Manifestation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,18,FOLLOW_124); 

            			newLeafNode(otherlv_7, grammarAccess.getModuleAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:4918:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==89) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // InternalEJSL.g:4919:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,89,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getModuleAccess().getLanguagesKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_114); 

                    				newLeafNode(otherlv_9, grammarAccess.getModuleAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:4927:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop137:
                    do {
                        int alt137=2;
                        int LA137_0 = input.LA(1);

                        if ( (LA137_0==124) ) {
                            alt137=1;
                        }


                        switch (alt137) {
                    	case 1 :
                    	    // InternalEJSL.g:4928:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:4928:5: (lv_languages_10_0= ruleLanguage )
                    	    // InternalEJSL.g:4929:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getModuleAccess().getLanguagesLanguageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_10_0=ruleLanguage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getModuleRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"languages",
                    	    							lv_languages_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Language");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop137;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_121); 

                    				newLeafNode(otherlv_11, grammarAccess.getModuleAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:4951:3: ( (lv_pageRef_12_0= rulePageReference ) )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==94) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // InternalEJSL.g:4952:4: (lv_pageRef_12_0= rulePageReference )
                    {
                    // InternalEJSL.g:4952:4: (lv_pageRef_12_0= rulePageReference )
                    // InternalEJSL.g:4953:5: lv_pageRef_12_0= rulePageReference
                    {

                    					newCompositeNode(grammarAccess.getModuleAccess().getPageRefPageReferenceParserRuleCall_9_0());
                    				
                    pushFollow(FOLLOW_7);
                    lv_pageRef_12_0=rulePageReference();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModuleRule());
                    					}
                    					set(
                    						current,
                    						"pageRef",
                    						lv_pageRef_12_0,
                    						"de.thm.icampus.joomdd.ejsl.EJSL.PageReference");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_13=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_13, grammarAccess.getModuleAccess().getRightCurlyBracketKeyword_10());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModule"


    // $ANTLR start "entryRulePlugin"
    // InternalEJSL.g:4978:1: entryRulePlugin returns [EObject current=null] : iv_rulePlugin= rulePlugin EOF ;
    public final EObject entryRulePlugin() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlugin = null;


        try {
            // InternalEJSL.g:4978:47: (iv_rulePlugin= rulePlugin EOF )
            // InternalEJSL.g:4979:2: iv_rulePlugin= rulePlugin EOF
            {
             newCompositeNode(grammarAccess.getPluginRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePlugin=rulePlugin();

            state._fsp--;

             current =iv_rulePlugin; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePlugin"


    // $ANTLR start "rulePlugin"
    // InternalEJSL.g:4985:1: rulePlugin returns [EObject current=null] : ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}' ) ;
    public final EObject rulePlugin() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_manifest_6_0 = null;

        Enumerator lv_type_9_0 = null;

        EObject lv_languages_12_0 = null;

        EObject lv_localparameters_20_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4991:2: ( ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}' ) )
            // InternalEJSL.g:4992:2: ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}' )
            {
            // InternalEJSL.g:4992:2: ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}' )
            // InternalEJSL.g:4993:3: () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype =' ( (lv_type_9_0= rulePluginKinds ) ) (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )? (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )? (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )? otherlv_22= '}'
            {
            // InternalEJSL.g:4993:3: ()
            // InternalEJSL.g:4994:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPluginAccess().getPluginAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,98,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getPluginAccess().getPluginKeyword_1());
            		
            // InternalEJSL.g:5004:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:5005:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:5005:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:5006:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getPluginAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPluginRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			newLeafNode(otherlv_3, grammarAccess.getPluginAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getPluginAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			newLeafNode(otherlv_5, grammarAccess.getPluginAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:5035:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:5036:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:5036:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:5037:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getPluginAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPluginRule());
            					}
            					set(
            						current,
            						"manifest",
            						lv_manifest_6_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Manifestation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,18,FOLLOW_125); 

            			newLeafNode(otherlv_7, grammarAccess.getPluginAccess().getRightCurlyBracketKeyword_7());
            		
            otherlv_8=(Token)match(input,99,FOLLOW_126); 

            			newLeafNode(otherlv_8, grammarAccess.getPluginAccess().getPlugintypeKeyword_8());
            		
            // InternalEJSL.g:5062:3: ( (lv_type_9_0= rulePluginKinds ) )
            // InternalEJSL.g:5063:4: (lv_type_9_0= rulePluginKinds )
            {
            // InternalEJSL.g:5063:4: (lv_type_9_0= rulePluginKinds )
            // InternalEJSL.g:5064:5: lv_type_9_0= rulePluginKinds
            {

            					newCompositeNode(grammarAccess.getPluginAccess().getTypePluginKindsEnumRuleCall_9_0());
            				
            pushFollow(FOLLOW_127);
            lv_type_9_0=rulePluginKinds();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPluginRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_9_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.PluginKinds");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:5081:3: (otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}' )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==89) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // InternalEJSL.g:5082:4: otherlv_10= 'languages' otherlv_11= '{' ( (lv_languages_12_0= ruleLanguage ) )* otherlv_13= '}'
                    {
                    otherlv_10=(Token)match(input,89,FOLLOW_4); 

                    				newLeafNode(otherlv_10, grammarAccess.getPluginAccess().getLanguagesKeyword_10_0());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_114); 

                    				newLeafNode(otherlv_11, grammarAccess.getPluginAccess().getLeftCurlyBracketKeyword_10_1());
                    			
                    // InternalEJSL.g:5090:4: ( (lv_languages_12_0= ruleLanguage ) )*
                    loop140:
                    do {
                        int alt140=2;
                        int LA140_0 = input.LA(1);

                        if ( (LA140_0==124) ) {
                            alt140=1;
                        }


                        switch (alt140) {
                    	case 1 :
                    	    // InternalEJSL.g:5091:5: (lv_languages_12_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:5091:5: (lv_languages_12_0= ruleLanguage )
                    	    // InternalEJSL.g:5092:6: lv_languages_12_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getPluginAccess().getLanguagesLanguageParserRuleCall_10_2_0());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_12_0=ruleLanguage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPluginRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"languages",
                    	    							lv_languages_12_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Language");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop140;
                        }
                    } while (true);

                    otherlv_13=(Token)match(input,18,FOLLOW_128); 

                    				newLeafNode(otherlv_13, grammarAccess.getPluginAccess().getRightCurlyBracketKeyword_10_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:5114:3: (otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )* )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==67) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // InternalEJSL.g:5115:4: otherlv_14= '*Entities' ( (otherlv_15= RULE_STRING ) ) (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )*
                    {
                    otherlv_14=(Token)match(input,67,FOLLOW_3); 

                    				newLeafNode(otherlv_14, grammarAccess.getPluginAccess().getEntitiesKeyword_11_0());
                    			
                    // InternalEJSL.g:5119:4: ( (otherlv_15= RULE_STRING ) )
                    // InternalEJSL.g:5120:5: (otherlv_15= RULE_STRING )
                    {
                    // InternalEJSL.g:5120:5: (otherlv_15= RULE_STRING )
                    // InternalEJSL.g:5121:6: otherlv_15= RULE_STRING
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPluginRule());
                    						}
                    					
                    otherlv_15=(Token)match(input,RULE_STRING,FOLLOW_129); 

                    						newLeafNode(otherlv_15, grammarAccess.getPluginAccess().getEntitiesEntityCrossReference_11_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:5132:4: (otherlv_16= ',' ( (otherlv_17= RULE_STRING ) ) )*
                    loop142:
                    do {
                        int alt142=2;
                        int LA142_0 = input.LA(1);

                        if ( (LA142_0==21) ) {
                            alt142=1;
                        }


                        switch (alt142) {
                    	case 1 :
                    	    // InternalEJSL.g:5133:5: otherlv_16= ',' ( (otherlv_17= RULE_STRING ) )
                    	    {
                    	    otherlv_16=(Token)match(input,21,FOLLOW_3); 

                    	    					newLeafNode(otherlv_16, grammarAccess.getPluginAccess().getCommaKeyword_11_2_0());
                    	    				
                    	    // InternalEJSL.g:5137:5: ( (otherlv_17= RULE_STRING ) )
                    	    // InternalEJSL.g:5138:6: (otherlv_17= RULE_STRING )
                    	    {
                    	    // InternalEJSL.g:5138:6: (otherlv_17= RULE_STRING )
                    	    // InternalEJSL.g:5139:7: otherlv_17= RULE_STRING
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getPluginRule());
                    	    							}
                    	    						
                    	    otherlv_17=(Token)match(input,RULE_STRING,FOLLOW_129); 

                    	    							newLeafNode(otherlv_17, grammarAccess.getPluginAccess().getEntitiesEntityCrossReference_11_2_1_0());
                    	    						

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

            // InternalEJSL.g:5152:3: (otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}' )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==100) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // InternalEJSL.g:5153:4: otherlv_18= 'parameters' otherlv_19= '{' ( (lv_localparameters_20_0= ruleParameter ) )* otherlv_21= '}'
                    {
                    otherlv_18=(Token)match(input,100,FOLLOW_4); 

                    				newLeafNode(otherlv_18, grammarAccess.getPluginAccess().getParametersKeyword_12_0());
                    			
                    otherlv_19=(Token)match(input,16,FOLLOW_12); 

                    				newLeafNode(otherlv_19, grammarAccess.getPluginAccess().getLeftCurlyBracketKeyword_12_1());
                    			
                    // InternalEJSL.g:5161:4: ( (lv_localparameters_20_0= ruleParameter ) )*
                    loop144:
                    do {
                        int alt144=2;
                        int LA144_0 = input.LA(1);

                        if ( (LA144_0==34) ) {
                            alt144=1;
                        }


                        switch (alt144) {
                    	case 1 :
                    	    // InternalEJSL.g:5162:5: (lv_localparameters_20_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:5162:5: (lv_localparameters_20_0= ruleParameter )
                    	    // InternalEJSL.g:5163:6: lv_localparameters_20_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getPluginAccess().getLocalparametersParameterParserRuleCall_12_2_0());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_20_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPluginRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"localparameters",
                    	    							lv_localparameters_20_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop144;
                        }
                    } while (true);

                    otherlv_21=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_21, grammarAccess.getPluginAccess().getRightCurlyBracketKeyword_12_3());
                    			

                    }
                    break;

            }

            otherlv_22=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_22, grammarAccess.getPluginAccess().getRightCurlyBracketKeyword_13());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePlugin"


    // $ANTLR start "entryRuleLibrary"
    // InternalEJSL.g:5193:1: entryRuleLibrary returns [EObject current=null] : iv_ruleLibrary= ruleLibrary EOF ;
    public final EObject entryRuleLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLibrary = null;


        try {
            // InternalEJSL.g:5193:48: (iv_ruleLibrary= ruleLibrary EOF )
            // InternalEJSL.g:5194:2: iv_ruleLibrary= ruleLibrary EOF
            {
             newCompositeNode(grammarAccess.getLibraryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLibrary=ruleLibrary();

            state._fsp--;

             current =iv_ruleLibrary; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLibrary"


    // $ANTLR start "ruleLibrary"
    // InternalEJSL.g:5200:1: ruleLibrary returns [EObject current=null] : ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' ) ;
    public final EObject ruleLibrary() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_manifest_6_0 = null;

        EObject lv_languages_10_0 = null;

        EObject lv_classes_18_0 = null;

        EObject lv_packages_22_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:5206:2: ( ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' ) )
            // InternalEJSL.g:5207:2: ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' )
            {
            // InternalEJSL.g:5207:2: ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' )
            // InternalEJSL.g:5208:3: () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}'
            {
            // InternalEJSL.g:5208:3: ()
            // InternalEJSL.g:5209:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getLibraryAccess().getLibraryAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,101,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getLibraryAccess().getLibraryKeyword_1());
            		
            // InternalEJSL.g:5219:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:5220:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:5220:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:5221:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getLibraryAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLibraryRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			newLeafNode(otherlv_3, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getLibraryAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			newLeafNode(otherlv_5, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:5250:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:5251:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:5251:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:5252:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getLibraryAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getLibraryRule());
            					}
            					set(
            						current,
            						"manifest",
            						lv_manifest_6_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Manifestation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,18,FOLLOW_130); 

            			newLeafNode(otherlv_7, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:5273:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==89) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // InternalEJSL.g:5274:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,89,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getLibraryAccess().getLanguagesKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_114); 

                    				newLeafNode(otherlv_9, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:5282:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop146:
                    do {
                        int alt146=2;
                        int LA146_0 = input.LA(1);

                        if ( (LA146_0==124) ) {
                            alt146=1;
                        }


                        switch (alt146) {
                    	case 1 :
                    	    // InternalEJSL.g:5283:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:5283:5: (lv_languages_10_0= ruleLanguage )
                    	    // InternalEJSL.g:5284:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getLibraryAccess().getLanguagesLanguageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_10_0=ruleLanguage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getLibraryRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"languages",
                    	    							lv_languages_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Language");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop146;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_131); 

                    				newLeafNode(otherlv_11, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:5306:3: (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==67) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // InternalEJSL.g:5307:4: otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )*
                    {
                    otherlv_12=(Token)match(input,67,FOLLOW_3); 

                    				newLeafNode(otherlv_12, grammarAccess.getLibraryAccess().getEntitiesKeyword_9_0());
                    			
                    // InternalEJSL.g:5311:4: ( (otherlv_13= RULE_STRING ) )
                    // InternalEJSL.g:5312:5: (otherlv_13= RULE_STRING )
                    {
                    // InternalEJSL.g:5312:5: (otherlv_13= RULE_STRING )
                    // InternalEJSL.g:5313:6: otherlv_13= RULE_STRING
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getLibraryRule());
                    						}
                    					
                    otherlv_13=(Token)match(input,RULE_STRING,FOLLOW_132); 

                    						newLeafNode(otherlv_13, grammarAccess.getLibraryAccess().getEntitiesEntityCrossReference_9_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:5324:4: (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )*
                    loop148:
                    do {
                        int alt148=2;
                        int LA148_0 = input.LA(1);

                        if ( (LA148_0==21) ) {
                            alt148=1;
                        }


                        switch (alt148) {
                    	case 1 :
                    	    // InternalEJSL.g:5325:5: otherlv_14= ',' ( (otherlv_15= RULE_STRING ) )
                    	    {
                    	    otherlv_14=(Token)match(input,21,FOLLOW_3); 

                    	    					newLeafNode(otherlv_14, grammarAccess.getLibraryAccess().getCommaKeyword_9_2_0());
                    	    				
                    	    // InternalEJSL.g:5329:5: ( (otherlv_15= RULE_STRING ) )
                    	    // InternalEJSL.g:5330:6: (otherlv_15= RULE_STRING )
                    	    {
                    	    // InternalEJSL.g:5330:6: (otherlv_15= RULE_STRING )
                    	    // InternalEJSL.g:5331:7: otherlv_15= RULE_STRING
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getLibraryRule());
                    	    							}
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_STRING,FOLLOW_132); 

                    	    							newLeafNode(otherlv_15, grammarAccess.getLibraryAccess().getEntitiesEntityCrossReference_9_2_1_0());
                    	    						

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

            // InternalEJSL.g:5344:3: (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==102) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // InternalEJSL.g:5345:4: otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,102,FOLLOW_4); 

                    				newLeafNode(otherlv_16, grammarAccess.getLibraryAccess().getClassesKeyword_10_0());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_133); 

                    				newLeafNode(otherlv_17, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_10_1());
                    			
                    // InternalEJSL.g:5353:4: ( (lv_classes_18_0= ruleClass ) )*
                    loop150:
                    do {
                        int alt150=2;
                        int LA150_0 = input.LA(1);

                        if ( (LA150_0==105) ) {
                            alt150=1;
                        }


                        switch (alt150) {
                    	case 1 :
                    	    // InternalEJSL.g:5354:5: (lv_classes_18_0= ruleClass )
                    	    {
                    	    // InternalEJSL.g:5354:5: (lv_classes_18_0= ruleClass )
                    	    // InternalEJSL.g:5355:6: lv_classes_18_0= ruleClass
                    	    {

                    	    						newCompositeNode(grammarAccess.getLibraryAccess().getClassesClassParserRuleCall_10_2_0());
                    	    					
                    	    pushFollow(FOLLOW_133);
                    	    lv_classes_18_0=ruleClass();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getLibraryRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"classes",
                    	    							lv_classes_18_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Class");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop150;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FOLLOW_134); 

                    				newLeafNode(otherlv_19, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_10_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:5377:3: (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==103) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // InternalEJSL.g:5378:4: otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}'
                    {
                    otherlv_20=(Token)match(input,103,FOLLOW_4); 

                    				newLeafNode(otherlv_20, grammarAccess.getLibraryAccess().getPackagesKeyword_11_0());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_135); 

                    				newLeafNode(otherlv_21, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_11_1());
                    			
                    // InternalEJSL.g:5386:4: ( (lv_packages_22_0= rulePackage ) )*
                    loop152:
                    do {
                        int alt152=2;
                        int LA152_0 = input.LA(1);

                        if ( (LA152_0==104) ) {
                            alt152=1;
                        }


                        switch (alt152) {
                    	case 1 :
                    	    // InternalEJSL.g:5387:5: (lv_packages_22_0= rulePackage )
                    	    {
                    	    // InternalEJSL.g:5387:5: (lv_packages_22_0= rulePackage )
                    	    // InternalEJSL.g:5388:6: lv_packages_22_0= rulePackage
                    	    {

                    	    						newCompositeNode(grammarAccess.getLibraryAccess().getPackagesPackageParserRuleCall_11_2_0());
                    	    					
                    	    pushFollow(FOLLOW_135);
                    	    lv_packages_22_0=rulePackage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getLibraryRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"packages",
                    	    							lv_packages_22_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Package");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop152;
                        }
                    } while (true);

                    otherlv_23=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_23, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_11_3());
                    			

                    }
                    break;

            }

            otherlv_24=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_24, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_12());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLibrary"


    // $ANTLR start "entryRulePackage"
    // InternalEJSL.g:5418:1: entryRulePackage returns [EObject current=null] : iv_rulePackage= rulePackage EOF ;
    public final EObject entryRulePackage() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackage = null;


        try {
            // InternalEJSL.g:5418:48: (iv_rulePackage= rulePackage EOF )
            // InternalEJSL.g:5419:2: iv_rulePackage= rulePackage EOF
            {
             newCompositeNode(grammarAccess.getPackageRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePackage=rulePackage();

            state._fsp--;

             current =iv_rulePackage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePackage"


    // $ANTLR start "rulePackage"
    // InternalEJSL.g:5425:1: rulePackage returns [EObject current=null] : ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' ) ;
    public final EObject rulePackage() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_packages_6_0 = null;

        EObject lv_classes_10_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:5431:2: ( ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' ) )
            // InternalEJSL.g:5432:2: ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' )
            {
            // InternalEJSL.g:5432:2: ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' )
            // InternalEJSL.g:5433:3: () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}'
            {
            // InternalEJSL.g:5433:3: ()
            // InternalEJSL.g:5434:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPackageAccess().getPackageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,104,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getPackageAccess().getPackageKeyword_1());
            		
            // InternalEJSL.g:5444:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:5445:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:5445:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:5446:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getPackageAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPackageRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_136); 

            			newLeafNode(otherlv_3, grammarAccess.getPackageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:5467:3: (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==103) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // InternalEJSL.g:5468:4: otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}'
                    {
                    otherlv_4=(Token)match(input,103,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getPackageAccess().getPackagesKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_135); 

                    				newLeafNode(otherlv_5, grammarAccess.getPackageAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:5476:4: ( (lv_packages_6_0= rulePackage ) )*
                    loop154:
                    do {
                        int alt154=2;
                        int LA154_0 = input.LA(1);

                        if ( (LA154_0==104) ) {
                            alt154=1;
                        }


                        switch (alt154) {
                    	case 1 :
                    	    // InternalEJSL.g:5477:5: (lv_packages_6_0= rulePackage )
                    	    {
                    	    // InternalEJSL.g:5477:5: (lv_packages_6_0= rulePackage )
                    	    // InternalEJSL.g:5478:6: lv_packages_6_0= rulePackage
                    	    {

                    	    						newCompositeNode(grammarAccess.getPackageAccess().getPackagesPackageParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_135);
                    	    lv_packages_6_0=rulePackage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPackageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"packages",
                    	    							lv_packages_6_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Package");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop154;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,18,FOLLOW_137); 

                    				newLeafNode(otherlv_7, grammarAccess.getPackageAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:5500:3: (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==102) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // InternalEJSL.g:5501:4: otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,102,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getPackageAccess().getClassesKeyword_5_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_133); 

                    				newLeafNode(otherlv_9, grammarAccess.getPackageAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:5509:4: ( (lv_classes_10_0= ruleClass ) )*
                    loop156:
                    do {
                        int alt156=2;
                        int LA156_0 = input.LA(1);

                        if ( (LA156_0==105) ) {
                            alt156=1;
                        }


                        switch (alt156) {
                    	case 1 :
                    	    // InternalEJSL.g:5510:5: (lv_classes_10_0= ruleClass )
                    	    {
                    	    // InternalEJSL.g:5510:5: (lv_classes_10_0= ruleClass )
                    	    // InternalEJSL.g:5511:6: lv_classes_10_0= ruleClass
                    	    {

                    	    						newCompositeNode(grammarAccess.getPackageAccess().getClassesClassParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_133);
                    	    lv_classes_10_0=ruleClass();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPackageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"classes",
                    	    							lv_classes_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Class");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop156;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_11, grammarAccess.getPackageAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            otherlv_12=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_12, grammarAccess.getPackageAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePackage"


    // $ANTLR start "entryRuleClass"
    // InternalEJSL.g:5541:1: entryRuleClass returns [EObject current=null] : iv_ruleClass= ruleClass EOF ;
    public final EObject entryRuleClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClass = null;


        try {
            // InternalEJSL.g:5541:46: (iv_ruleClass= ruleClass EOF )
            // InternalEJSL.g:5542:2: iv_ruleClass= ruleClass EOF
            {
             newCompositeNode(grammarAccess.getClassRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleClass=ruleClass();

            state._fsp--;

             current =iv_ruleClass; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClass"


    // $ANTLR start "ruleClass"
    // InternalEJSL.g:5548:1: ruleClass returns [EObject current=null] : ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
    public final EObject ruleClass() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_methods_16_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:5554:2: ( ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // InternalEJSL.g:5555:2: ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // InternalEJSL.g:5555:2: ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // InternalEJSL.g:5556:3: () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            // InternalEJSL.g:5556:3: ()
            // InternalEJSL.g:5557:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getClassAccess().getClassAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,105,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getClassAccess().getClassKeyword_1());
            		
            // InternalEJSL.g:5567:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:5568:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:5568:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:5569:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getClassAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_52);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getClassRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:5586:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==46) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // InternalEJSL.g:5587:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {
                    otherlv_3=(Token)match(input,46,FOLLOW_51); 

                    				newLeafNode(otherlv_3, grammarAccess.getClassAccess().getExtendsKeyword_3_0());
                    			
                    // InternalEJSL.g:5591:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:5592:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:5592:5: ( ruleQualifiedName )
                    // InternalEJSL.g:5593:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getClassAccess().getSupertypeClassCrossReference_3_1_0());
                    					
                    pushFollow(FOLLOW_4);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,16,FOLLOW_138); 

            			newLeafNode(otherlv_5, grammarAccess.getClassAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:5612:3: (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==106) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // InternalEJSL.g:5613:4: otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_6=(Token)match(input,106,FOLLOW_51); 

                    				newLeafNode(otherlv_6, grammarAccess.getClassAccess().getClassReferencesKeyword_5_0());
                    			
                    // InternalEJSL.g:5617:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:5618:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:5618:5: ( ruleQualifiedName )
                    // InternalEJSL.g:5619:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getClassAccess().getReferencesClassCrossReference_5_1_0());
                    					
                    pushFollow(FOLLOW_139);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:5633:4: (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    loop159:
                    do {
                        int alt159=2;
                        int LA159_0 = input.LA(1);

                        if ( (LA159_0==21) ) {
                            alt159=1;
                        }


                        switch (alt159) {
                    	case 1 :
                    	    // InternalEJSL.g:5634:5: otherlv_8= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_8=(Token)match(input,21,FOLLOW_51); 

                    	    					newLeafNode(otherlv_8, grammarAccess.getClassAccess().getCommaKeyword_5_2_0());
                    	    				
                    	    // InternalEJSL.g:5638:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:5639:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:5639:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:5640:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getClassRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getClassAccess().getReferencesClassCrossReference_5_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_139);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

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

            // InternalEJSL.g:5656:3: (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==67) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // InternalEJSL.g:5657:4: otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )*
                    {
                    otherlv_10=(Token)match(input,67,FOLLOW_3); 

                    				newLeafNode(otherlv_10, grammarAccess.getClassAccess().getEntitiesKeyword_6_0());
                    			
                    // InternalEJSL.g:5661:4: ( (otherlv_11= RULE_STRING ) )
                    // InternalEJSL.g:5662:5: (otherlv_11= RULE_STRING )
                    {
                    // InternalEJSL.g:5662:5: (otherlv_11= RULE_STRING )
                    // InternalEJSL.g:5663:6: otherlv_11= RULE_STRING
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassRule());
                    						}
                    					
                    otherlv_11=(Token)match(input,RULE_STRING,FOLLOW_140); 

                    						newLeafNode(otherlv_11, grammarAccess.getClassAccess().getEntitiesEntityCrossReference_6_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:5674:4: (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )*
                    loop161:
                    do {
                        int alt161=2;
                        int LA161_0 = input.LA(1);

                        if ( (LA161_0==21) ) {
                            alt161=1;
                        }


                        switch (alt161) {
                    	case 1 :
                    	    // InternalEJSL.g:5675:5: otherlv_12= ',' ( (otherlv_13= RULE_STRING ) )
                    	    {
                    	    otherlv_12=(Token)match(input,21,FOLLOW_3); 

                    	    					newLeafNode(otherlv_12, grammarAccess.getClassAccess().getCommaKeyword_6_2_0());
                    	    				
                    	    // InternalEJSL.g:5679:5: ( (otherlv_13= RULE_STRING ) )
                    	    // InternalEJSL.g:5680:6: (otherlv_13= RULE_STRING )
                    	    {
                    	    // InternalEJSL.g:5680:6: (otherlv_13= RULE_STRING )
                    	    // InternalEJSL.g:5681:7: otherlv_13= RULE_STRING
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getClassRule());
                    	    							}
                    	    						
                    	    otherlv_13=(Token)match(input,RULE_STRING,FOLLOW_140); 

                    	    							newLeafNode(otherlv_13, grammarAccess.getClassAccess().getEntitiesEntityCrossReference_6_2_1_0());
                    	    						

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

            // InternalEJSL.g:5694:3: (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )?
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==107) ) {
                alt164=1;
            }
            switch (alt164) {
                case 1 :
                    // InternalEJSL.g:5695:4: otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}'
                    {
                    otherlv_14=(Token)match(input,107,FOLLOW_4); 

                    				newLeafNode(otherlv_14, grammarAccess.getClassAccess().getMethodsKeyword_7_0());
                    			
                    otherlv_15=(Token)match(input,16,FOLLOW_141); 

                    				newLeafNode(otherlv_15, grammarAccess.getClassAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:5703:4: ( (lv_methods_16_0= ruleMethod ) )*
                    loop163:
                    do {
                        int alt163=2;
                        int LA163_0 = input.LA(1);

                        if ( (LA163_0==108) ) {
                            alt163=1;
                        }


                        switch (alt163) {
                    	case 1 :
                    	    // InternalEJSL.g:5704:5: (lv_methods_16_0= ruleMethod )
                    	    {
                    	    // InternalEJSL.g:5704:5: (lv_methods_16_0= ruleMethod )
                    	    // InternalEJSL.g:5705:6: lv_methods_16_0= ruleMethod
                    	    {

                    	    						newCompositeNode(grammarAccess.getClassAccess().getMethodsMethodParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_141);
                    	    lv_methods_16_0=ruleMethod();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getClassRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"methods",
                    	    							lv_methods_16_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Method");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop163;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_17, grammarAccess.getClassAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            otherlv_18=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_18, grammarAccess.getClassAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClass"


    // $ANTLR start "entryRuleMethod"
    // InternalEJSL.g:5735:1: entryRuleMethod returns [EObject current=null] : iv_ruleMethod= ruleMethod EOF ;
    public final EObject entryRuleMethod() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethod = null;


        try {
            // InternalEJSL.g:5735:47: (iv_ruleMethod= ruleMethod EOF )
            // InternalEJSL.g:5736:2: iv_ruleMethod= ruleMethod EOF
            {
             newCompositeNode(grammarAccess.getMethodRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMethod=ruleMethod();

            state._fsp--;

             current =iv_ruleMethod; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethod"


    // $ANTLR start "ruleMethod"
    // InternalEJSL.g:5742:1: ruleMethod returns [EObject current=null] : ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' ) ;
    public final EObject ruleMethod() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_returnvalue_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_type_7_0 = null;

        EObject lv_methodparameters_10_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:5748:2: ( ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' ) )
            // InternalEJSL.g:5749:2: ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' )
            {
            // InternalEJSL.g:5749:2: ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' )
            // InternalEJSL.g:5750:3: () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}'
            {
            // InternalEJSL.g:5750:3: ()
            // InternalEJSL.g:5751:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getMethodAccess().getMethodAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,108,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getMethodAccess().getMethodKeyword_1());
            		
            // InternalEJSL.g:5761:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:5762:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:5762:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:5763:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getMethodAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMethodRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_142); 

            			newLeafNode(otherlv_3, grammarAccess.getMethodAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:5784:3: (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )?
            int alt165=2;
            int LA165_0 = input.LA(1);

            if ( (LA165_0==109) ) {
                alt165=1;
            }
            switch (alt165) {
                case 1 :
                    // InternalEJSL.g:5785:4: otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) )
                    {
                    otherlv_4=(Token)match(input,109,FOLLOW_33); 

                    				newLeafNode(otherlv_4, grammarAccess.getMethodAccess().getReturnvalueKeyword_4_0());
                    			
                    // InternalEJSL.g:5789:4: ( (lv_returnvalue_5_0= RULE_ID ) )
                    // InternalEJSL.g:5790:5: (lv_returnvalue_5_0= RULE_ID )
                    {
                    // InternalEJSL.g:5790:5: (lv_returnvalue_5_0= RULE_ID )
                    // InternalEJSL.g:5791:6: lv_returnvalue_5_0= RULE_ID
                    {
                    lv_returnvalue_5_0=(Token)match(input,RULE_ID,FOLLOW_143); 

                    						newLeafNode(lv_returnvalue_5_0, grammarAccess.getMethodAccess().getReturnvalueIDTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getMethodRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"returnvalue",
                    							lv_returnvalue_5_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }

                    otherlv_6=(Token)match(input,72,FOLLOW_35); 

                    				newLeafNode(otherlv_6, grammarAccess.getMethodAccess().getColonKeyword_4_2());
                    			
                    // InternalEJSL.g:5811:4: ( (lv_type_7_0= ruleType ) )
                    // InternalEJSL.g:5812:5: (lv_type_7_0= ruleType )
                    {
                    // InternalEJSL.g:5812:5: (lv_type_7_0= ruleType )
                    // InternalEJSL.g:5813:6: lv_type_7_0= ruleType
                    {

                    						newCompositeNode(grammarAccess.getMethodAccess().getTypeTypeParserRuleCall_4_3_0());
                    					
                    pushFollow(FOLLOW_144);
                    lv_type_7_0=ruleType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getMethodRule());
                    						}
                    						set(
                    							current,
                    							"type",
                    							lv_type_7_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.Type");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5831:3: (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )?
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==110) ) {
                alt167=1;
            }
            switch (alt167) {
                case 1 :
                    // InternalEJSL.g:5832:4: otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,110,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getMethodAccess().getMethodparametersKeyword_5_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_145); 

                    				newLeafNode(otherlv_9, grammarAccess.getMethodAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:5840:4: ( (lv_methodparameters_10_0= ruleMethodParameter ) )*
                    loop166:
                    do {
                        int alt166=2;
                        int LA166_0 = input.LA(1);

                        if ( (LA166_0==111) ) {
                            alt166=1;
                        }


                        switch (alt166) {
                    	case 1 :
                    	    // InternalEJSL.g:5841:5: (lv_methodparameters_10_0= ruleMethodParameter )
                    	    {
                    	    // InternalEJSL.g:5841:5: (lv_methodparameters_10_0= ruleMethodParameter )
                    	    // InternalEJSL.g:5842:6: lv_methodparameters_10_0= ruleMethodParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getMethodAccess().getMethodparametersMethodParameterParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_145);
                    	    lv_methodparameters_10_0=ruleMethodParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getMethodRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"methodparameters",
                    	    							lv_methodparameters_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.MethodParameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop166;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_11, grammarAccess.getMethodAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            otherlv_12=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_12, grammarAccess.getMethodAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethod"


    // $ANTLR start "entryRuleMethodParameter"
    // InternalEJSL.g:5872:1: entryRuleMethodParameter returns [EObject current=null] : iv_ruleMethodParameter= ruleMethodParameter EOF ;
    public final EObject entryRuleMethodParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethodParameter = null;


        try {
            // InternalEJSL.g:5872:56: (iv_ruleMethodParameter= ruleMethodParameter EOF )
            // InternalEJSL.g:5873:2: iv_ruleMethodParameter= ruleMethodParameter EOF
            {
             newCompositeNode(grammarAccess.getMethodParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMethodParameter=ruleMethodParameter();

            state._fsp--;

             current =iv_ruleMethodParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMethodParameter"


    // $ANTLR start "ruleMethodParameter"
    // InternalEJSL.g:5879:1: ruleMethodParameter returns [EObject current=null] : ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) ;
    public final EObject ruleMethodParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        EObject lv_type_4_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:5885:2: ( ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) )
            // InternalEJSL.g:5886:2: ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            {
            // InternalEJSL.g:5886:2: ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            // InternalEJSL.g:5887:3: () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) )
            {
            // InternalEJSL.g:5887:3: ()
            // InternalEJSL.g:5888:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getMethodParameterAccess().getMethodParameterAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,111,FOLLOW_33); 

            			newLeafNode(otherlv_1, grammarAccess.getMethodParameterAccess().getMethodParameterKeyword_1());
            		
            // InternalEJSL.g:5898:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:5899:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:5899:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:5900:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_143); 

            					newLeafNode(lv_name_2_0, grammarAccess.getMethodParameterAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMethodParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,72,FOLLOW_35); 

            			newLeafNode(otherlv_3, grammarAccess.getMethodParameterAccess().getColonKeyword_3());
            		
            // InternalEJSL.g:5920:3: ( (lv_type_4_0= ruleType ) )
            // InternalEJSL.g:5921:4: (lv_type_4_0= ruleType )
            {
            // InternalEJSL.g:5921:4: (lv_type_4_0= ruleType )
            // InternalEJSL.g:5922:5: lv_type_4_0= ruleType
            {

            					newCompositeNode(grammarAccess.getMethodParameterAccess().getTypeTypeParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_type_4_0=ruleType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMethodParameterRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_4_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Type");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMethodParameter"


    // $ANTLR start "entryRuleTemplate"
    // InternalEJSL.g:5943:1: entryRuleTemplate returns [EObject current=null] : iv_ruleTemplate= ruleTemplate EOF ;
    public final EObject entryRuleTemplate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplate = null;


        try {
            // InternalEJSL.g:5943:49: (iv_ruleTemplate= ruleTemplate EOF )
            // InternalEJSL.g:5944:2: iv_ruleTemplate= ruleTemplate EOF
            {
             newCompositeNode(grammarAccess.getTemplateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTemplate=ruleTemplate();

            state._fsp--;

             current =iv_ruleTemplate; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTemplate"


    // $ANTLR start "ruleTemplate"
    // InternalEJSL.g:5950:1: ruleTemplate returns [EObject current=null] : ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' ) ;
    public final EObject ruleTemplate() throws RecognitionException {
        EObject current = null;

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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_manifest_6_0 = null;

        EObject lv_languages_10_0 = null;

        EObject lv_localparameters_14_0 = null;

        EObject lv_positions_18_0 = null;

        EObject lv_cssblocks_22_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:5956:2: ( ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' ) )
            // InternalEJSL.g:5957:2: ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' )
            {
            // InternalEJSL.g:5957:2: ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' )
            // InternalEJSL.g:5958:3: () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}'
            {
            // InternalEJSL.g:5958:3: ()
            // InternalEJSL.g:5959:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTemplateAccess().getTemplateAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,112,FOLLOW_51); 

            			newLeafNode(otherlv_1, grammarAccess.getTemplateAccess().getTemplateKeyword_1());
            		
            // InternalEJSL.g:5969:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:5970:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:5970:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:5971:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getTemplateAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_4);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTemplateRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.MYID");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_111); 

            			newLeafNode(otherlv_3, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,88,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getTemplateAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_112); 

            			newLeafNode(otherlv_5, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:6000:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:6001:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:6001:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:6002:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getTemplateAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_7);
            lv_manifest_6_0=ruleManifestation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTemplateRule());
            					}
            					set(
            						current,
            						"manifest",
            						lv_manifest_6_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Manifestation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,18,FOLLOW_146); 

            			newLeafNode(otherlv_7, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:6023:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==89) ) {
                alt169=1;
            }
            switch (alt169) {
                case 1 :
                    // InternalEJSL.g:6024:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,89,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getTemplateAccess().getLanguagesKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_114); 

                    				newLeafNode(otherlv_9, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:6032:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop168:
                    do {
                        int alt168=2;
                        int LA168_0 = input.LA(1);

                        if ( (LA168_0==124) ) {
                            alt168=1;
                        }


                        switch (alt168) {
                    	case 1 :
                    	    // InternalEJSL.g:6033:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:6033:5: (lv_languages_10_0= ruleLanguage )
                    	    // InternalEJSL.g:6034:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getTemplateAccess().getLanguagesLanguageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_114);
                    	    lv_languages_10_0=ruleLanguage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getTemplateRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"languages",
                    	    							lv_languages_10_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Language");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop168;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,18,FOLLOW_147); 

                    				newLeafNode(otherlv_11, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:6056:3: (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )?
            int alt171=2;
            int LA171_0 = input.LA(1);

            if ( (LA171_0==100) ) {
                alt171=1;
            }
            switch (alt171) {
                case 1 :
                    // InternalEJSL.g:6057:4: otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}'
                    {
                    otherlv_12=(Token)match(input,100,FOLLOW_4); 

                    				newLeafNode(otherlv_12, grammarAccess.getTemplateAccess().getParametersKeyword_9_0());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_12); 

                    				newLeafNode(otherlv_13, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_9_1());
                    			
                    // InternalEJSL.g:6065:4: ( (lv_localparameters_14_0= ruleParameter ) )*
                    loop170:
                    do {
                        int alt170=2;
                        int LA170_0 = input.LA(1);

                        if ( (LA170_0==34) ) {
                            alt170=1;
                        }


                        switch (alt170) {
                    	case 1 :
                    	    // InternalEJSL.g:6066:5: (lv_localparameters_14_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:6066:5: (lv_localparameters_14_0= ruleParameter )
                    	    // InternalEJSL.g:6067:6: lv_localparameters_14_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getTemplateAccess().getLocalparametersParameterParserRuleCall_9_2_0());
                    	    					
                    	    pushFollow(FOLLOW_12);
                    	    lv_localparameters_14_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getTemplateRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"localparameters",
                    	    							lv_localparameters_14_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop170;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,18,FOLLOW_148); 

                    				newLeafNode(otherlv_15, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_9_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:6089:3: (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )?
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==113) ) {
                alt173=1;
            }
            switch (alt173) {
                case 1 :
                    // InternalEJSL.g:6090:4: otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,113,FOLLOW_4); 

                    				newLeafNode(otherlv_16, grammarAccess.getTemplateAccess().getPositionsKeyword_10_0());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_149); 

                    				newLeafNode(otherlv_17, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_10_1());
                    			
                    // InternalEJSL.g:6098:4: ( (lv_positions_18_0= rulePosition ) )*
                    loop172:
                    do {
                        int alt172=2;
                        int LA172_0 = input.LA(1);

                        if ( (LA172_0==127) ) {
                            alt172=1;
                        }


                        switch (alt172) {
                    	case 1 :
                    	    // InternalEJSL.g:6099:5: (lv_positions_18_0= rulePosition )
                    	    {
                    	    // InternalEJSL.g:6099:5: (lv_positions_18_0= rulePosition )
                    	    // InternalEJSL.g:6100:6: lv_positions_18_0= rulePosition
                    	    {

                    	    						newCompositeNode(grammarAccess.getTemplateAccess().getPositionsPositionParserRuleCall_10_2_0());
                    	    					
                    	    pushFollow(FOLLOW_149);
                    	    lv_positions_18_0=rulePosition();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getTemplateRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"positions",
                    	    							lv_positions_18_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Position");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop172;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,18,FOLLOW_150); 

                    				newLeafNode(otherlv_19, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_10_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:6122:3: (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )?
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==114) ) {
                alt175=1;
            }
            switch (alt175) {
                case 1 :
                    // InternalEJSL.g:6123:4: otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}'
                    {
                    otherlv_20=(Token)match(input,114,FOLLOW_4); 

                    				newLeafNode(otherlv_20, grammarAccess.getTemplateAccess().getCssblocksKeyword_11_0());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_151); 

                    				newLeafNode(otherlv_21, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_11_1());
                    			
                    // InternalEJSL.g:6131:4: ( (lv_cssblocks_22_0= ruleCssBlock ) )*
                    loop174:
                    do {
                        int alt174=2;
                        int LA174_0 = input.LA(1);

                        if ( (LA174_0==133) ) {
                            alt174=1;
                        }


                        switch (alt174) {
                    	case 1 :
                    	    // InternalEJSL.g:6132:5: (lv_cssblocks_22_0= ruleCssBlock )
                    	    {
                    	    // InternalEJSL.g:6132:5: (lv_cssblocks_22_0= ruleCssBlock )
                    	    // InternalEJSL.g:6133:6: lv_cssblocks_22_0= ruleCssBlock
                    	    {

                    	    						newCompositeNode(grammarAccess.getTemplateAccess().getCssblocksCssBlockParserRuleCall_11_2_0());
                    	    					
                    	    pushFollow(FOLLOW_151);
                    	    lv_cssblocks_22_0=ruleCssBlock();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getTemplateRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"cssblocks",
                    	    							lv_cssblocks_22_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.CssBlock");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop174;
                        }
                    } while (true);

                    otherlv_23=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_23, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_11_3());
                    			

                    }
                    break;

            }

            otherlv_24=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_24, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_12());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTemplate"


    // $ANTLR start "entryRuleManifestation"
    // InternalEJSL.g:6163:1: entryRuleManifestation returns [EObject current=null] : iv_ruleManifestation= ruleManifestation EOF ;
    public final EObject entryRuleManifestation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleManifestation = null;


        try {
            // InternalEJSL.g:6163:54: (iv_ruleManifestation= ruleManifestation EOF )
            // InternalEJSL.g:6164:2: iv_ruleManifestation= ruleManifestation EOF
            {
             newCompositeNode(grammarAccess.getManifestationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleManifestation=ruleManifestation();

            state._fsp--;

             current =iv_ruleManifestation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleManifestation"


    // $ANTLR start "ruleManifestation"
    // InternalEJSL.g:6170:1: ruleManifestation returns [EObject current=null] : ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleManifestation() throws RecognitionException {
        EObject current = null;

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
        EObject lv_authors_3_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:6176:2: ( ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )? ) )
            // InternalEJSL.g:6177:2: ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )? )
            {
            // InternalEJSL.g:6177:2: ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )? )
            // InternalEJSL.g:6178:3: () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )? (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )? (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )? (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )? (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )? (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )?
            {
            // InternalEJSL.g:6178:3: ()
            // InternalEJSL.g:6179:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getManifestationAccess().getManifestationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,115,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getManifestationAccess().getAuthorsKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_152); 

            			newLeafNode(otherlv_2, grammarAccess.getManifestationAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalEJSL.g:6193:3: ( (lv_authors_3_0= ruleAuthor ) )+
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
            	    // InternalEJSL.g:6194:4: (lv_authors_3_0= ruleAuthor )
            	    {
            	    // InternalEJSL.g:6194:4: (lv_authors_3_0= ruleAuthor )
            	    // InternalEJSL.g:6195:5: lv_authors_3_0= ruleAuthor
            	    {

            	    					newCompositeNode(grammarAccess.getManifestationAccess().getAuthorsAuthorParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_153);
            	    lv_authors_3_0=ruleAuthor();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getManifestationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"authors",
            	    						lv_authors_3_0,
            	    						"de.thm.icampus.joomdd.ejsl.EJSL.Author");
            	    					afterParserOrEnumRuleCall();
            	    				

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

            otherlv_4=(Token)match(input,18,FOLLOW_154); 

            			newLeafNode(otherlv_4, grammarAccess.getManifestationAccess().getRightCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:6216:3: (otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) ) )?
            int alt177=2;
            int LA177_0 = input.LA(1);

            if ( (LA177_0==116) ) {
                alt177=1;
            }
            switch (alt177) {
                case 1 :
                    // InternalEJSL.g:6217:4: otherlv_5= 'creationdate =' ( (lv_creationdate_6_0= RULE_DATE ) )
                    {
                    otherlv_5=(Token)match(input,116,FOLLOW_155); 

                    				newLeafNode(otherlv_5, grammarAccess.getManifestationAccess().getCreationdateKeyword_5_0());
                    			
                    // InternalEJSL.g:6221:4: ( (lv_creationdate_6_0= RULE_DATE ) )
                    // InternalEJSL.g:6222:5: (lv_creationdate_6_0= RULE_DATE )
                    {
                    // InternalEJSL.g:6222:5: (lv_creationdate_6_0= RULE_DATE )
                    // InternalEJSL.g:6223:6: lv_creationdate_6_0= RULE_DATE
                    {
                    lv_creationdate_6_0=(Token)match(input,RULE_DATE,FOLLOW_156); 

                    						newLeafNode(lv_creationdate_6_0, grammarAccess.getManifestationAccess().getCreationdateDATETerminalRuleCall_5_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"creationdate",
                    							lv_creationdate_6_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.DATE");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:6240:3: (otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) ) )?
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==117) ) {
                alt178=1;
            }
            switch (alt178) {
                case 1 :
                    // InternalEJSL.g:6241:4: otherlv_7= 'copyright =' ( (lv_copyright_8_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,117,FOLLOW_3); 

                    				newLeafNode(otherlv_7, grammarAccess.getManifestationAccess().getCopyrightKeyword_6_0());
                    			
                    // InternalEJSL.g:6245:4: ( (lv_copyright_8_0= RULE_STRING ) )
                    // InternalEJSL.g:6246:5: (lv_copyright_8_0= RULE_STRING )
                    {
                    // InternalEJSL.g:6246:5: (lv_copyright_8_0= RULE_STRING )
                    // InternalEJSL.g:6247:6: lv_copyright_8_0= RULE_STRING
                    {
                    lv_copyright_8_0=(Token)match(input,RULE_STRING,FOLLOW_157); 

                    						newLeafNode(lv_copyright_8_0, grammarAccess.getManifestationAccess().getCopyrightSTRINGTerminalRuleCall_6_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"copyright",
                    							lv_copyright_8_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:6264:3: (otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) ) )?
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==118) ) {
                alt179=1;
            }
            switch (alt179) {
                case 1 :
                    // InternalEJSL.g:6265:4: otherlv_9= 'license =' ( (lv_license_10_0= RULE_STRING ) )
                    {
                    otherlv_9=(Token)match(input,118,FOLLOW_3); 

                    				newLeafNode(otherlv_9, grammarAccess.getManifestationAccess().getLicenseKeyword_7_0());
                    			
                    // InternalEJSL.g:6269:4: ( (lv_license_10_0= RULE_STRING ) )
                    // InternalEJSL.g:6270:5: (lv_license_10_0= RULE_STRING )
                    {
                    // InternalEJSL.g:6270:5: (lv_license_10_0= RULE_STRING )
                    // InternalEJSL.g:6271:6: lv_license_10_0= RULE_STRING
                    {
                    lv_license_10_0=(Token)match(input,RULE_STRING,FOLLOW_158); 

                    						newLeafNode(lv_license_10_0, grammarAccess.getManifestationAccess().getLicenseSTRINGTerminalRuleCall_7_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"license",
                    							lv_license_10_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:6288:3: (otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) ) )?
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==119) ) {
                alt180=1;
            }
            switch (alt180) {
                case 1 :
                    // InternalEJSL.g:6289:4: otherlv_11= 'link =' ( (lv_link_12_0= RULE_STRING ) )
                    {
                    otherlv_11=(Token)match(input,119,FOLLOW_3); 

                    				newLeafNode(otherlv_11, grammarAccess.getManifestationAccess().getLinkKeyword_8_0());
                    			
                    // InternalEJSL.g:6293:4: ( (lv_link_12_0= RULE_STRING ) )
                    // InternalEJSL.g:6294:5: (lv_link_12_0= RULE_STRING )
                    {
                    // InternalEJSL.g:6294:5: (lv_link_12_0= RULE_STRING )
                    // InternalEJSL.g:6295:6: lv_link_12_0= RULE_STRING
                    {
                    lv_link_12_0=(Token)match(input,RULE_STRING,FOLLOW_159); 

                    						newLeafNode(lv_link_12_0, grammarAccess.getManifestationAccess().getLinkSTRINGTerminalRuleCall_8_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"link",
                    							lv_link_12_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:6312:3: (otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) ) )?
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==120) ) {
                alt181=1;
            }
            switch (alt181) {
                case 1 :
                    // InternalEJSL.g:6313:4: otherlv_13= 'version =' ( (lv_version_14_0= RULE_STRING ) )
                    {
                    otherlv_13=(Token)match(input,120,FOLLOW_3); 

                    				newLeafNode(otherlv_13, grammarAccess.getManifestationAccess().getVersionKeyword_9_0());
                    			
                    // InternalEJSL.g:6317:4: ( (lv_version_14_0= RULE_STRING ) )
                    // InternalEJSL.g:6318:5: (lv_version_14_0= RULE_STRING )
                    {
                    // InternalEJSL.g:6318:5: (lv_version_14_0= RULE_STRING )
                    // InternalEJSL.g:6319:6: lv_version_14_0= RULE_STRING
                    {
                    lv_version_14_0=(Token)match(input,RULE_STRING,FOLLOW_160); 

                    						newLeafNode(lv_version_14_0, grammarAccess.getManifestationAccess().getVersionSTRINGTerminalRuleCall_9_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"version",
                    							lv_version_14_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:6336:3: (otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==39) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // InternalEJSL.g:6337:4: otherlv_15= 'description =' ( (lv_description_16_0= RULE_STRING ) )
                    {
                    otherlv_15=(Token)match(input,39,FOLLOW_3); 

                    				newLeafNode(otherlv_15, grammarAccess.getManifestationAccess().getDescriptionKeyword_10_0());
                    			
                    // InternalEJSL.g:6341:4: ( (lv_description_16_0= RULE_STRING ) )
                    // InternalEJSL.g:6342:5: (lv_description_16_0= RULE_STRING )
                    {
                    // InternalEJSL.g:6342:5: (lv_description_16_0= RULE_STRING )
                    // InternalEJSL.g:6343:6: lv_description_16_0= RULE_STRING
                    {
                    lv_description_16_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_description_16_0, grammarAccess.getManifestationAccess().getDescriptionSTRINGTerminalRuleCall_10_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"description",
                    							lv_description_16_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleManifestation"


    // $ANTLR start "entryRuleAuthor"
    // InternalEJSL.g:6364:1: entryRuleAuthor returns [EObject current=null] : iv_ruleAuthor= ruleAuthor EOF ;
    public final EObject entryRuleAuthor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAuthor = null;


        try {
            // InternalEJSL.g:6364:47: (iv_ruleAuthor= ruleAuthor EOF )
            // InternalEJSL.g:6365:2: iv_ruleAuthor= ruleAuthor EOF
            {
             newCompositeNode(grammarAccess.getAuthorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAuthor=ruleAuthor();

            state._fsp--;

             current =iv_ruleAuthor; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAuthor"


    // $ANTLR start "ruleAuthor"
    // InternalEJSL.g:6371:1: ruleAuthor returns [EObject current=null] : ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}' ) ;
    public final EObject ruleAuthor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_authoremail_5_0=null;
        Token otherlv_6=null;
        Token lv_authorurl_7_0=null;
        Token otherlv_8=null;


        	enterRule();

        try {
            // InternalEJSL.g:6377:2: ( ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}' ) )
            // InternalEJSL.g:6378:2: ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}' )
            {
            // InternalEJSL.g:6378:2: ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}' )
            // InternalEJSL.g:6379:3: () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )? (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )? otherlv_8= '}'
            {
            // InternalEJSL.g:6379:3: ()
            // InternalEJSL.g:6380:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAuthorAccess().getAuthorAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,121,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getAuthorAccess().getAuthorKeyword_1());
            		
            // InternalEJSL.g:6390:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalEJSL.g:6391:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalEJSL.g:6391:4: (lv_name_2_0= RULE_STRING )
            // InternalEJSL.g:6392:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getAuthorAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAuthorRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_161); 

            			newLeafNode(otherlv_3, grammarAccess.getAuthorAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:6412:3: (otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) ) )?
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==122) ) {
                alt183=1;
            }
            switch (alt183) {
                case 1 :
                    // InternalEJSL.g:6413:4: otherlv_4= 'authoremail =' ( (lv_authoremail_5_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,122,FOLLOW_3); 

                    				newLeafNode(otherlv_4, grammarAccess.getAuthorAccess().getAuthoremailKeyword_4_0());
                    			
                    // InternalEJSL.g:6417:4: ( (lv_authoremail_5_0= RULE_STRING ) )
                    // InternalEJSL.g:6418:5: (lv_authoremail_5_0= RULE_STRING )
                    {
                    // InternalEJSL.g:6418:5: (lv_authoremail_5_0= RULE_STRING )
                    // InternalEJSL.g:6419:6: lv_authoremail_5_0= RULE_STRING
                    {
                    lv_authoremail_5_0=(Token)match(input,RULE_STRING,FOLLOW_162); 

                    						newLeafNode(lv_authoremail_5_0, grammarAccess.getAuthorAccess().getAuthoremailSTRINGTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAuthorRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"authoremail",
                    							lv_authoremail_5_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:6436:3: (otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) ) )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==123) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // InternalEJSL.g:6437:4: otherlv_6= 'authorurl =' ( (lv_authorurl_7_0= RULE_STRING ) )
                    {
                    otherlv_6=(Token)match(input,123,FOLLOW_3); 

                    				newLeafNode(otherlv_6, grammarAccess.getAuthorAccess().getAuthorurlKeyword_5_0());
                    			
                    // InternalEJSL.g:6441:4: ( (lv_authorurl_7_0= RULE_STRING ) )
                    // InternalEJSL.g:6442:5: (lv_authorurl_7_0= RULE_STRING )
                    {
                    // InternalEJSL.g:6442:5: (lv_authorurl_7_0= RULE_STRING )
                    // InternalEJSL.g:6443:6: lv_authorurl_7_0= RULE_STRING
                    {
                    lv_authorurl_7_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

                    						newLeafNode(lv_authorurl_7_0, grammarAccess.getAuthorAccess().getAuthorurlSTRINGTerminalRuleCall_5_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAuthorRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"authorurl",
                    							lv_authorurl_7_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getAuthorAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAuthor"


    // $ANTLR start "entryRuleLanguage"
    // InternalEJSL.g:6468:1: entryRuleLanguage returns [EObject current=null] : iv_ruleLanguage= ruleLanguage EOF ;
    public final EObject entryRuleLanguage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLanguage = null;


        try {
            // InternalEJSL.g:6468:49: (iv_ruleLanguage= ruleLanguage EOF )
            // InternalEJSL.g:6469:2: iv_ruleLanguage= ruleLanguage EOF
            {
             newCompositeNode(grammarAccess.getLanguageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLanguage=ruleLanguage();

            state._fsp--;

             current =iv_ruleLanguage; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLanguage"


    // $ANTLR start "ruleLanguage"
    // InternalEJSL.g:6475:1: ruleLanguage returns [EObject current=null] : ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' ) ;
    public final EObject ruleLanguage() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_keyvaluepairs_6_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:6481:2: ( ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' ) )
            // InternalEJSL.g:6482:2: ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' )
            {
            // InternalEJSL.g:6482:2: ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' )
            // InternalEJSL.g:6483:3: () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}'
            {
            // InternalEJSL.g:6483:3: ()
            // InternalEJSL.g:6484:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getLanguageAccess().getLanguageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,124,FOLLOW_163); 

            			newLeafNode(otherlv_1, grammarAccess.getLanguageAccess().getLanguageKeyword_1());
            		
            // InternalEJSL.g:6494:3: ( (lv_name_2_0= RULE_LANGUAGE_CODE ) )
            // InternalEJSL.g:6495:4: (lv_name_2_0= RULE_LANGUAGE_CODE )
            {
            // InternalEJSL.g:6495:4: (lv_name_2_0= RULE_LANGUAGE_CODE )
            // InternalEJSL.g:6496:5: lv_name_2_0= RULE_LANGUAGE_CODE
            {
            lv_name_2_0=(Token)match(input,RULE_LANGUAGE_CODE,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getLanguageAccess().getNameLANGUAGE_CODETerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getLanguageRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.LANGUAGE_CODE");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_164); 

            			newLeafNode(otherlv_3, grammarAccess.getLanguageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:6516:3: (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )?
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==125) ) {
                alt186=1;
            }
            switch (alt186) {
                case 1 :
                    // InternalEJSL.g:6517:4: otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}'
                    {
                    otherlv_4=(Token)match(input,125,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getLanguageAccess().getKeyvaluepairsKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_165); 

                    				newLeafNode(otherlv_5, grammarAccess.getLanguageAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:6525:4: ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )*
                    loop185:
                    do {
                        int alt185=2;
                        int LA185_0 = input.LA(1);

                        if ( (LA185_0==126) ) {
                            alt185=1;
                        }


                        switch (alt185) {
                    	case 1 :
                    	    // InternalEJSL.g:6526:5: (lv_keyvaluepairs_6_0= ruleKeyValuePair )
                    	    {
                    	    // InternalEJSL.g:6526:5: (lv_keyvaluepairs_6_0= ruleKeyValuePair )
                    	    // InternalEJSL.g:6527:6: lv_keyvaluepairs_6_0= ruleKeyValuePair
                    	    {

                    	    						newCompositeNode(grammarAccess.getLanguageAccess().getKeyvaluepairsKeyValuePairParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_165);
                    	    lv_keyvaluepairs_6_0=ruleKeyValuePair();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getLanguageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"keyvaluepairs",
                    	    							lv_keyvaluepairs_6_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.KeyValuePair");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop185;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_7, grammarAccess.getLanguageAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            otherlv_8=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getLanguageAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLanguage"


    // $ANTLR start "entryRuleKeyValuePair"
    // InternalEJSL.g:6557:1: entryRuleKeyValuePair returns [EObject current=null] : iv_ruleKeyValuePair= ruleKeyValuePair EOF ;
    public final EObject entryRuleKeyValuePair() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyValuePair = null;


        try {
            // InternalEJSL.g:6557:53: (iv_ruleKeyValuePair= ruleKeyValuePair EOF )
            // InternalEJSL.g:6558:2: iv_ruleKeyValuePair= ruleKeyValuePair EOF
            {
             newCompositeNode(grammarAccess.getKeyValuePairRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKeyValuePair=ruleKeyValuePair();

            state._fsp--;

             current =iv_ruleKeyValuePair; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKeyValuePair"


    // $ANTLR start "ruleKeyValuePair"
    // InternalEJSL.g:6564:1: ruleKeyValuePair returns [EObject current=null] : ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) ) ;
    public final EObject ruleKeyValuePair() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_value_4_0=null;


        	enterRule();

        try {
            // InternalEJSL.g:6570:2: ( ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) ) )
            // InternalEJSL.g:6571:2: ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) )
            {
            // InternalEJSL.g:6571:2: ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) )
            // InternalEJSL.g:6572:3: () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) )
            {
            // InternalEJSL.g:6572:3: ()
            // InternalEJSL.g:6573:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKeyValuePairAccess().getKeyValuePairAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,126,FOLLOW_33); 

            			newLeafNode(otherlv_1, grammarAccess.getKeyValuePairAccess().getKeyKeyword_1());
            		
            // InternalEJSL.g:6583:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:6584:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:6584:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:6585:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_103); 

            					newLeafNode(lv_name_2_0, grammarAccess.getKeyValuePairAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKeyValuePairRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,82,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getKeyValuePairAccess().getEqualsSignKeyword_3());
            		
            // InternalEJSL.g:6605:3: ( (lv_value_4_0= RULE_STRING ) )
            // InternalEJSL.g:6606:4: (lv_value_4_0= RULE_STRING )
            {
            // InternalEJSL.g:6606:4: (lv_value_4_0= RULE_STRING )
            // InternalEJSL.g:6607:5: lv_value_4_0= RULE_STRING
            {
            lv_value_4_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_value_4_0, grammarAccess.getKeyValuePairAccess().getValueSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getKeyValuePairRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKeyValuePair"


    // $ANTLR start "entryRulePosition"
    // InternalEJSL.g:6627:1: entryRulePosition returns [EObject current=null] : iv_rulePosition= rulePosition EOF ;
    public final EObject entryRulePosition() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePosition = null;


        try {
            // InternalEJSL.g:6627:49: (iv_rulePosition= rulePosition EOF )
            // InternalEJSL.g:6628:2: iv_rulePosition= rulePosition EOF
            {
             newCompositeNode(grammarAccess.getPositionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePosition=rulePosition();

            state._fsp--;

             current =iv_rulePosition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePosition"


    // $ANTLR start "rulePosition"
    // InternalEJSL.g:6634:1: rulePosition returns [EObject current=null] : ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' ) ;
    public final EObject rulePosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        EObject lv_positionparameters_7_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:6640:2: ( ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' ) )
            // InternalEJSL.g:6641:2: ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' )
            {
            // InternalEJSL.g:6641:2: ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' )
            // InternalEJSL.g:6642:3: () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}'
            {
            // InternalEJSL.g:6642:3: ()
            // InternalEJSL.g:6643:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPositionAccess().getPositionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,127,FOLLOW_166); 

            			newLeafNode(otherlv_1, grammarAccess.getPositionAccess().getTemplatepositionKeyword_1());
            		
            // InternalEJSL.g:6653:3: ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) )
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
                    // InternalEJSL.g:6654:4: ( (lv_name_2_0= RULE_POSITION_TYPES ) )
                    {
                    // InternalEJSL.g:6654:4: ( (lv_name_2_0= RULE_POSITION_TYPES ) )
                    // InternalEJSL.g:6655:5: (lv_name_2_0= RULE_POSITION_TYPES )
                    {
                    // InternalEJSL.g:6655:5: (lv_name_2_0= RULE_POSITION_TYPES )
                    // InternalEJSL.g:6656:6: lv_name_2_0= RULE_POSITION_TYPES
                    {
                    lv_name_2_0=(Token)match(input,RULE_POSITION_TYPES,FOLLOW_4); 

                    						newLeafNode(lv_name_2_0, grammarAccess.getPositionAccess().getNamePOSITION_TYPESTerminalRuleCall_2_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPositionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"name",
                    							lv_name_2_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.POSITION_TYPES");
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:6673:4: ( (lv_name_3_0= RULE_ID ) )
                    {
                    // InternalEJSL.g:6673:4: ( (lv_name_3_0= RULE_ID ) )
                    // InternalEJSL.g:6674:5: (lv_name_3_0= RULE_ID )
                    {
                    // InternalEJSL.g:6674:5: (lv_name_3_0= RULE_ID )
                    // InternalEJSL.g:6675:6: lv_name_3_0= RULE_ID
                    {
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_4); 

                    						newLeafNode(lv_name_3_0, grammarAccess.getPositionAccess().getNameIDTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPositionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"name",
                    							lv_name_3_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,16,FOLLOW_167); 

            			newLeafNode(otherlv_4, grammarAccess.getPositionAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:6696:3: (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )?
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==128) ) {
                alt189=1;
            }
            switch (alt189) {
                case 1 :
                    // InternalEJSL.g:6697:4: otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}'
                    {
                    otherlv_5=(Token)match(input,128,FOLLOW_4); 

                    				newLeafNode(otherlv_5, grammarAccess.getPositionAccess().getPositionparametersKeyword_4_0());
                    			
                    otherlv_6=(Token)match(input,16,FOLLOW_168); 

                    				newLeafNode(otherlv_6, grammarAccess.getPositionAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:6705:4: ( (lv_positionparameters_7_0= rulePositionParameter ) )*
                    loop188:
                    do {
                        int alt188=2;
                        int LA188_0 = input.LA(1);

                        if ( (LA188_0==129) ) {
                            alt188=1;
                        }


                        switch (alt188) {
                    	case 1 :
                    	    // InternalEJSL.g:6706:5: (lv_positionparameters_7_0= rulePositionParameter )
                    	    {
                    	    // InternalEJSL.g:6706:5: (lv_positionparameters_7_0= rulePositionParameter )
                    	    // InternalEJSL.g:6707:6: lv_positionparameters_7_0= rulePositionParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getPositionAccess().getPositionparametersPositionParameterParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_168);
                    	    lv_positionparameters_7_0=rulePositionParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPositionRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"positionparameters",
                    	    							lv_positionparameters_7_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.PositionParameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop188;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_8, grammarAccess.getPositionAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            otherlv_9=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getPositionAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePosition"


    // $ANTLR start "entryRulePositionParameter"
    // InternalEJSL.g:6737:1: entryRulePositionParameter returns [EObject current=null] : iv_rulePositionParameter= rulePositionParameter EOF ;
    public final EObject entryRulePositionParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePositionParameter = null;


        try {
            // InternalEJSL.g:6737:58: (iv_rulePositionParameter= rulePositionParameter EOF )
            // InternalEJSL.g:6738:2: iv_rulePositionParameter= rulePositionParameter EOF
            {
             newCompositeNode(grammarAccess.getPositionParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePositionParameter=rulePositionParameter();

            state._fsp--;

             current =iv_rulePositionParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePositionParameter"


    // $ANTLR start "rulePositionParameter"
    // InternalEJSL.g:6744:1: rulePositionParameter returns [EObject current=null] : (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}' ) ;
    public final EObject rulePositionParameter() throws RecognitionException {
        EObject current = null;

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
        EObject lv_keyvaluepairs_9_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:6750:2: ( (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}' ) )
            // InternalEJSL.g:6751:2: (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}' )
            {
            // InternalEJSL.g:6751:2: (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}' )
            // InternalEJSL.g:6752:3: otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )? (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )? otherlv_11= '}'
            {
            otherlv_0=(Token)match(input,129,FOLLOW_33); 

            			newLeafNode(otherlv_0, grammarAccess.getPositionParameterAccess().getPositionParameterKeyword_0());
            		
            // InternalEJSL.g:6756:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalEJSL.g:6757:4: (lv_name_1_0= RULE_ID )
            {
            // InternalEJSL.g:6757:4: (lv_name_1_0= RULE_ID )
            // InternalEJSL.g:6758:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getPositionParameterAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPositionParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_169); 

            			newLeafNode(otherlv_2, grammarAccess.getPositionParameterAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalEJSL.g:6778:3: (otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) ) )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==130) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // InternalEJSL.g:6779:4: otherlv_3= 'divId =' ( (lv_divid_4_0= RULE_ID ) )
                    {
                    otherlv_3=(Token)match(input,130,FOLLOW_33); 

                    				newLeafNode(otherlv_3, grammarAccess.getPositionParameterAccess().getDivIdKeyword_3_0());
                    			
                    // InternalEJSL.g:6783:4: ( (lv_divid_4_0= RULE_ID ) )
                    // InternalEJSL.g:6784:5: (lv_divid_4_0= RULE_ID )
                    {
                    // InternalEJSL.g:6784:5: (lv_divid_4_0= RULE_ID )
                    // InternalEJSL.g:6785:6: lv_divid_4_0= RULE_ID
                    {
                    lv_divid_4_0=(Token)match(input,RULE_ID,FOLLOW_170); 

                    						newLeafNode(lv_divid_4_0, grammarAccess.getPositionParameterAccess().getDividIDTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPositionParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"divid",
                    							lv_divid_4_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:6802:3: (otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) ) )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==131) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // InternalEJSL.g:6803:4: otherlv_5= 'Positiontype =' ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) )
                    {
                    otherlv_5=(Token)match(input,131,FOLLOW_171); 

                    				newLeafNode(otherlv_5, grammarAccess.getPositionParameterAccess().getPositiontypeKeyword_4_0());
                    			
                    // InternalEJSL.g:6807:4: ( (lv_type_6_0= RULE_POSITION_TYPES_NAMES ) )
                    // InternalEJSL.g:6808:5: (lv_type_6_0= RULE_POSITION_TYPES_NAMES )
                    {
                    // InternalEJSL.g:6808:5: (lv_type_6_0= RULE_POSITION_TYPES_NAMES )
                    // InternalEJSL.g:6809:6: lv_type_6_0= RULE_POSITION_TYPES_NAMES
                    {
                    lv_type_6_0=(Token)match(input,RULE_POSITION_TYPES_NAMES,FOLLOW_172); 

                    						newLeafNode(lv_type_6_0, grammarAccess.getPositionParameterAccess().getTypePOSITION_TYPES_NAMESTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPositionParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"type",
                    							lv_type_6_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.POSITION_TYPES_NAMES");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:6826:3: (otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}' )?
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==132) ) {
                alt193=1;
            }
            switch (alt193) {
                case 1 :
                    // InternalEJSL.g:6827:4: otherlv_7= 'CSS keyvaluepairs' otherlv_8= '{' ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )* otherlv_10= '}'
                    {
                    otherlv_7=(Token)match(input,132,FOLLOW_4); 

                    				newLeafNode(otherlv_7, grammarAccess.getPositionParameterAccess().getCSSKeyvaluepairsKeyword_5_0());
                    			
                    otherlv_8=(Token)match(input,16,FOLLOW_165); 

                    				newLeafNode(otherlv_8, grammarAccess.getPositionParameterAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:6835:4: ( (lv_keyvaluepairs_9_0= ruleKeyValuePair ) )*
                    loop192:
                    do {
                        int alt192=2;
                        int LA192_0 = input.LA(1);

                        if ( (LA192_0==126) ) {
                            alt192=1;
                        }


                        switch (alt192) {
                    	case 1 :
                    	    // InternalEJSL.g:6836:5: (lv_keyvaluepairs_9_0= ruleKeyValuePair )
                    	    {
                    	    // InternalEJSL.g:6836:5: (lv_keyvaluepairs_9_0= ruleKeyValuePair )
                    	    // InternalEJSL.g:6837:6: lv_keyvaluepairs_9_0= ruleKeyValuePair
                    	    {

                    	    						newCompositeNode(grammarAccess.getPositionParameterAccess().getKeyvaluepairsKeyValuePairParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_165);
                    	    lv_keyvaluepairs_9_0=ruleKeyValuePair();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPositionParameterRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"keyvaluepairs",
                    	    							lv_keyvaluepairs_9_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.KeyValuePair");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop192;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,18,FOLLOW_7); 

                    				newLeafNode(otherlv_10, grammarAccess.getPositionParameterAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            otherlv_11=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getPositionParameterAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePositionParameter"


    // $ANTLR start "entryRuleCssBlock"
    // InternalEJSL.g:6867:1: entryRuleCssBlock returns [EObject current=null] : iv_ruleCssBlock= ruleCssBlock EOF ;
    public final EObject entryRuleCssBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCssBlock = null;


        try {
            // InternalEJSL.g:6867:49: (iv_ruleCssBlock= ruleCssBlock EOF )
            // InternalEJSL.g:6868:2: iv_ruleCssBlock= ruleCssBlock EOF
            {
             newCompositeNode(grammarAccess.getCssBlockRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCssBlock=ruleCssBlock();

            state._fsp--;

             current =iv_ruleCssBlock; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCssBlock"


    // $ANTLR start "ruleCssBlock"
    // InternalEJSL.g:6874:1: ruleCssBlock returns [EObject current=null] : (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' ) ;
    public final EObject ruleCssBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_selector_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_keyvaluepairs_5_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:6880:2: ( (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' ) )
            // InternalEJSL.g:6881:2: (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' )
            {
            // InternalEJSL.g:6881:2: (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' )
            // InternalEJSL.g:6882:3: otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')'
            {
            otherlv_0=(Token)match(input,133,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getCssBlockAccess().getCssBlockKeyword_0());
            		
            // InternalEJSL.g:6886:3: ( (lv_selector_1_0= RULE_STRING ) )
            // InternalEJSL.g:6887:4: (lv_selector_1_0= RULE_STRING )
            {
            // InternalEJSL.g:6887:4: (lv_selector_1_0= RULE_STRING )
            // InternalEJSL.g:6888:5: lv_selector_1_0= RULE_STRING
            {
            lv_selector_1_0=(Token)match(input,RULE_STRING,FOLLOW_96); 

            					newLeafNode(lv_selector_1_0, grammarAccess.getCssBlockAccess().getSelectorSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCssBlockRule());
            					}
            					setWithLastConsumed(
            						current,
            						"selector",
            						lv_selector_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_2=(Token)match(input,75,FOLLOW_173); 

            			newLeafNode(otherlv_2, grammarAccess.getCssBlockAccess().getLeftParenthesisKeyword_2());
            		
            // InternalEJSL.g:6908:3: (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )?
            int alt195=2;
            int LA195_0 = input.LA(1);

            if ( (LA195_0==125) ) {
                alt195=1;
            }
            switch (alt195) {
                case 1 :
                    // InternalEJSL.g:6909:4: otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}'
                    {
                    otherlv_3=(Token)match(input,125,FOLLOW_4); 

                    				newLeafNode(otherlv_3, grammarAccess.getCssBlockAccess().getKeyvaluepairsKeyword_3_0());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_165); 

                    				newLeafNode(otherlv_4, grammarAccess.getCssBlockAccess().getLeftCurlyBracketKeyword_3_1());
                    			
                    // InternalEJSL.g:6917:4: ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )*
                    loop194:
                    do {
                        int alt194=2;
                        int LA194_0 = input.LA(1);

                        if ( (LA194_0==126) ) {
                            alt194=1;
                        }


                        switch (alt194) {
                    	case 1 :
                    	    // InternalEJSL.g:6918:5: (lv_keyvaluepairs_5_0= ruleKeyValuePair )
                    	    {
                    	    // InternalEJSL.g:6918:5: (lv_keyvaluepairs_5_0= ruleKeyValuePair )
                    	    // InternalEJSL.g:6919:6: lv_keyvaluepairs_5_0= ruleKeyValuePair
                    	    {

                    	    						newCompositeNode(grammarAccess.getCssBlockAccess().getKeyvaluepairsKeyValuePairParserRuleCall_3_2_0());
                    	    					
                    	    pushFollow(FOLLOW_165);
                    	    lv_keyvaluepairs_5_0=ruleKeyValuePair();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getCssBlockRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"keyvaluepairs",
                    	    							lv_keyvaluepairs_5_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.KeyValuePair");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop194;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,18,FOLLOW_174); 

                    				newLeafNode(otherlv_6, grammarAccess.getCssBlockAccess().getRightCurlyBracketKeyword_3_3());
                    			

                    }
                    break;

            }

            otherlv_7=(Token)match(input,76,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getCssBlockAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCssBlock"


    // $ANTLR start "entryRuleNUMBER"
    // InternalEJSL.g:6949:1: entryRuleNUMBER returns [String current=null] : iv_ruleNUMBER= ruleNUMBER EOF ;
    public final String entryRuleNUMBER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER = null;


        try {
            // InternalEJSL.g:6949:46: (iv_ruleNUMBER= ruleNUMBER EOF )
            // InternalEJSL.g:6950:2: iv_ruleNUMBER= ruleNUMBER EOF
            {
             newCompositeNode(grammarAccess.getNUMBERRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNUMBER=ruleNUMBER();

            state._fsp--;

             current =iv_ruleNUMBER.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNUMBER"


    // $ANTLR start "ruleNUMBER"
    // InternalEJSL.g:6956:1: ruleNUMBER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleNUMBER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;


        	enterRule();

        try {
            // InternalEJSL.g:6962:2: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // InternalEJSL.g:6963:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // InternalEJSL.g:6963:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            // InternalEJSL.g:6964:3: (kw= '-' )? this_INT_1= RULE_INT
            {
            // InternalEJSL.g:6964:3: (kw= '-' )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==134) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // InternalEJSL.g:6965:4: kw= '-'
                    {
                    kw=(Token)match(input,134,FOLLOW_39); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getNUMBERAccess().getHyphenMinusKeyword_0());
                    			

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_2); 

            			current.merge(this_INT_1);
            		

            			newLeafNode(this_INT_1, grammarAccess.getNUMBERAccess().getINTTerminalRuleCall_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNUMBER"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalEJSL.g:6982:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalEJSL.g:6982:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalEJSL.g:6983:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
             newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;

             current =iv_ruleQualifiedName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalEJSL.g:6989:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_MYID_0 = null;

        AntlrDatatypeRuleToken this_MYID_2 = null;



        	enterRule();

        try {
            // InternalEJSL.g:6995:2: ( (this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )* ) )
            // InternalEJSL.g:6996:2: (this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )* )
            {
            // InternalEJSL.g:6996:2: (this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )* )
            // InternalEJSL.g:6997:3: this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )*
            {

            			newCompositeNode(grammarAccess.getQualifiedNameAccess().getMYIDParserRuleCall_0());
            		
            pushFollow(FOLLOW_175);
            this_MYID_0=ruleMYID();

            state._fsp--;


            			current.merge(this_MYID_0);
            		

            			afterParserOrEnumRuleCall();
            		
            // InternalEJSL.g:7007:3: (kw= '.' this_MYID_2= ruleMYID )*
            loop197:
            do {
                int alt197=2;
                int LA197_0 = input.LA(1);

                if ( (LA197_0==135) ) {
                    alt197=1;
                }


                switch (alt197) {
            	case 1 :
            	    // InternalEJSL.g:7008:4: kw= '.' this_MYID_2= ruleMYID
            	    {
            	    kw=(Token)match(input,135,FOLLOW_51); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	    			

            	    				newCompositeNode(grammarAccess.getQualifiedNameAccess().getMYIDParserRuleCall_1_1());
            	    			
            	    pushFollow(FOLLOW_175);
            	    this_MYID_2=ruleMYID();

            	    state._fsp--;


            	    				current.merge(this_MYID_2);
            	    			

            	    				afterParserOrEnumRuleCall();
            	    			

            	    }
            	    break;

            	default :
            	    break loop197;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleMYID"
    // InternalEJSL.g:7028:1: entryRuleMYID returns [String current=null] : iv_ruleMYID= ruleMYID EOF ;
    public final String entryRuleMYID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleMYID = null;


        try {
            // InternalEJSL.g:7028:44: (iv_ruleMYID= ruleMYID EOF )
            // InternalEJSL.g:7029:2: iv_ruleMYID= ruleMYID EOF
            {
             newCompositeNode(grammarAccess.getMYIDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMYID=ruleMYID();

            state._fsp--;

             current =iv_ruleMYID.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMYID"


    // $ANTLR start "ruleMYID"
    // InternalEJSL.g:7035:1: ruleMYID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? ) ;
    public final AntlrDatatypeRuleToken ruleMYID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_ID_1=null;


        	enterRule();

        try {
            // InternalEJSL.g:7041:2: ( ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? ) )
            // InternalEJSL.g:7042:2: ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? )
            {
            // InternalEJSL.g:7042:2: ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? )
            // InternalEJSL.g:7043:3: (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )?
            {
            // InternalEJSL.g:7043:3: (kw= '<' )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==136) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // InternalEJSL.g:7044:4: kw= '<'
                    {
                    kw=(Token)match(input,136,FOLLOW_33); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getMYIDAccess().getLessThanSignKeyword_0());
                    			

                    }
                    break;

            }

            this_ID_1=(Token)match(input,RULE_ID,FOLLOW_176); 

            			current.merge(this_ID_1);
            		

            			newLeafNode(this_ID_1, grammarAccess.getMYIDAccess().getIDTerminalRuleCall_1());
            		
            // InternalEJSL.g:7057:3: (kw= '>' )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==137) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // InternalEJSL.g:7058:4: kw= '>'
                    {
                    kw=(Token)match(input,137,FOLLOW_2); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getMYIDAccess().getGreaterThanSignKeyword_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMYID"


    // $ANTLR start "rulePluginKinds"
    // InternalEJSL.g:7068:1: rulePluginKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) ) ;
    public final Enumerator rulePluginKinds() throws RecognitionException {
        Enumerator current = null;

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


        	enterRule();

        try {
            // InternalEJSL.g:7074:2: ( ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) ) )
            // InternalEJSL.g:7075:2: ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) )
            {
            // InternalEJSL.g:7075:2: ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) )
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
                    // InternalEJSL.g:7076:3: (enumLiteral_0= 'authenticate' )
                    {
                    // InternalEJSL.g:7076:3: (enumLiteral_0= 'authenticate' )
                    // InternalEJSL.g:7077:4: enumLiteral_0= 'authenticate'
                    {
                    enumLiteral_0=(Token)match(input,138,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getAuthenticateEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getPluginKindsAccess().getAuthenticateEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:7084:3: (enumLiteral_1= 'captcha' )
                    {
                    // InternalEJSL.g:7084:3: (enumLiteral_1= 'captcha' )
                    // InternalEJSL.g:7085:4: enumLiteral_1= 'captcha'
                    {
                    enumLiteral_1=(Token)match(input,139,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getCaptchaEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getPluginKindsAccess().getCaptchaEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:7092:3: (enumLiteral_2= 'content' )
                    {
                    // InternalEJSL.g:7092:3: (enumLiteral_2= 'content' )
                    // InternalEJSL.g:7093:4: enumLiteral_2= 'content'
                    {
                    enumLiteral_2=(Token)match(input,140,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getContentEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getPluginKindsAccess().getContentEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalEJSL.g:7100:3: (enumLiteral_3= 'contact' )
                    {
                    // InternalEJSL.g:7100:3: (enumLiteral_3= 'contact' )
                    // InternalEJSL.g:7101:4: enumLiteral_3= 'contact'
                    {
                    enumLiteral_3=(Token)match(input,141,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getContactEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getPluginKindsAccess().getContactEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalEJSL.g:7108:3: (enumLiteral_4= 'editors' )
                    {
                    // InternalEJSL.g:7108:3: (enumLiteral_4= 'editors' )
                    // InternalEJSL.g:7109:4: enumLiteral_4= 'editors'
                    {
                    enumLiteral_4=(Token)match(input,142,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getEditorsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getPluginKindsAccess().getEditorsEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalEJSL.g:7116:3: (enumLiteral_5= 'extensions' )
                    {
                    // InternalEJSL.g:7116:3: (enumLiteral_5= 'extensions' )
                    // InternalEJSL.g:7117:4: enumLiteral_5= 'extensions'
                    {
                    enumLiteral_5=(Token)match(input,25,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getExtensionsEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getPluginKindsAccess().getExtensionsEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalEJSL.g:7124:3: (enumLiteral_6= 'finder' )
                    {
                    // InternalEJSL.g:7124:3: (enumLiteral_6= 'finder' )
                    // InternalEJSL.g:7125:4: enumLiteral_6= 'finder'
                    {
                    enumLiteral_6=(Token)match(input,143,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getFinderEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getPluginKindsAccess().getFinderEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalEJSL.g:7132:3: (enumLiteral_7= 'quick_icons' )
                    {
                    // InternalEJSL.g:7132:3: (enumLiteral_7= 'quick_icons' )
                    // InternalEJSL.g:7133:4: enumLiteral_7= 'quick_icons'
                    {
                    enumLiteral_7=(Token)match(input,144,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getQuick_iconsEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getPluginKindsAccess().getQuick_iconsEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalEJSL.g:7140:3: (enumLiteral_8= 'search' )
                    {
                    // InternalEJSL.g:7140:3: (enumLiteral_8= 'search' )
                    // InternalEJSL.g:7141:4: enumLiteral_8= 'search'
                    {
                    enumLiteral_8=(Token)match(input,145,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getSearchEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getPluginKindsAccess().getSearchEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalEJSL.g:7148:3: (enumLiteral_9= 'system' )
                    {
                    // InternalEJSL.g:7148:3: (enumLiteral_9= 'system' )
                    // InternalEJSL.g:7149:4: enumLiteral_9= 'system'
                    {
                    enumLiteral_9=(Token)match(input,146,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getSystemEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getPluginKindsAccess().getSystemEnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalEJSL.g:7156:3: (enumLiteral_10= 'user' )
                    {
                    // InternalEJSL.g:7156:3: (enumLiteral_10= 'user' )
                    // InternalEJSL.g:7157:4: enumLiteral_10= 'user'
                    {
                    enumLiteral_10=(Token)match(input,147,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getUserEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_10, grammarAccess.getPluginKindsAccess().getUserEnumLiteralDeclaration_10());
                    			

                    }


                    }
                    break;
                case 12 :
                    // InternalEJSL.g:7164:3: (enumLiteral_11= 'xml_rpc' )
                    {
                    // InternalEJSL.g:7164:3: (enumLiteral_11= 'xml_rpc' )
                    // InternalEJSL.g:7165:4: enumLiteral_11= 'xml_rpc'
                    {
                    enumLiteral_11=(Token)match(input,148,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getXml_rpcEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_11, grammarAccess.getPluginKindsAccess().getXml_rpcEnumLiteralDeclaration_11());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePluginKinds"


    // $ANTLR start "rulePageActionKind"
    // InternalEJSL.g:7175:1: rulePageActionKind returns [Enumerator current=null] : ( (enumLiteral_0= 'Text' ) | (enumLiteral_1= 'Button' ) ) ;
    public final Enumerator rulePageActionKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalEJSL.g:7181:2: ( ( (enumLiteral_0= 'Text' ) | (enumLiteral_1= 'Button' ) ) )
            // InternalEJSL.g:7182:2: ( (enumLiteral_0= 'Text' ) | (enumLiteral_1= 'Button' ) )
            {
            // InternalEJSL.g:7182:2: ( (enumLiteral_0= 'Text' ) | (enumLiteral_1= 'Button' ) )
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
                    // InternalEJSL.g:7183:3: (enumLiteral_0= 'Text' )
                    {
                    // InternalEJSL.g:7183:3: (enumLiteral_0= 'Text' )
                    // InternalEJSL.g:7184:4: enumLiteral_0= 'Text'
                    {
                    enumLiteral_0=(Token)match(input,149,FOLLOW_2); 

                    				current = grammarAccess.getPageActionKindAccess().getTextEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getPageActionKindAccess().getTextEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:7191:3: (enumLiteral_1= 'Button' )
                    {
                    // InternalEJSL.g:7191:3: (enumLiteral_1= 'Button' )
                    // InternalEJSL.g:7192:4: enumLiteral_1= 'Button'
                    {
                    enumLiteral_1=(Token)match(input,150,FOLLOW_2); 

                    				current = grammarAccess.getPageActionKindAccess().getButtonEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getPageActionKindAccess().getButtonEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageActionKind"


    // $ANTLR start "rulePageActionPositionKind"
    // InternalEJSL.g:7202:1: rulePageActionPositionKind returns [Enumerator current=null] : ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) ) ;
    public final Enumerator rulePageActionPositionKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalEJSL.g:7208:2: ( ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) ) )
            // InternalEJSL.g:7209:2: ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) )
            {
            // InternalEJSL.g:7209:2: ( (enumLiteral_0= 'top' ) | (enumLiteral_1= 'center' ) | (enumLiteral_2= 'bottom' ) )
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
                    // InternalEJSL.g:7210:3: (enumLiteral_0= 'top' )
                    {
                    // InternalEJSL.g:7210:3: (enumLiteral_0= 'top' )
                    // InternalEJSL.g:7211:4: enumLiteral_0= 'top'
                    {
                    enumLiteral_0=(Token)match(input,151,FOLLOW_2); 

                    				current = grammarAccess.getPageActionPositionKindAccess().getTopEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getPageActionPositionKindAccess().getTopEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:7218:3: (enumLiteral_1= 'center' )
                    {
                    // InternalEJSL.g:7218:3: (enumLiteral_1= 'center' )
                    // InternalEJSL.g:7219:4: enumLiteral_1= 'center'
                    {
                    enumLiteral_1=(Token)match(input,152,FOLLOW_2); 

                    				current = grammarAccess.getPageActionPositionKindAccess().getCenterEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getPageActionPositionKindAccess().getCenterEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:7226:3: (enumLiteral_2= 'bottom' )
                    {
                    // InternalEJSL.g:7226:3: (enumLiteral_2= 'bottom' )
                    // InternalEJSL.g:7227:4: enumLiteral_2= 'bottom'
                    {
                    enumLiteral_2=(Token)match(input,153,FOLLOW_2); 

                    				current = grammarAccess.getPageActionPositionKindAccess().getBottomEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getPageActionPositionKindAccess().getBottomEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageActionPositionKind"


    // $ANTLR start "ruleStandardTypeKinds"
    // InternalEJSL.g:7237:1: ruleStandardTypeKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) ) ;
    public final Enumerator ruleStandardTypeKinds() throws RecognitionException {
        Enumerator current = null;

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


        	enterRule();

        try {
            // InternalEJSL.g:7243:2: ( ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) ) )
            // InternalEJSL.g:7244:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) )
            {
            // InternalEJSL.g:7244:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) )
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
                    // InternalEJSL.g:7245:3: (enumLiteral_0= 'Integer' )
                    {
                    // InternalEJSL.g:7245:3: (enumLiteral_0= 'Integer' )
                    // InternalEJSL.g:7246:4: enumLiteral_0= 'Integer'
                    {
                    enumLiteral_0=(Token)match(input,154,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getIntegerEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getStandardTypeKindsAccess().getIntegerEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:7253:3: (enumLiteral_1= 'Boolean' )
                    {
                    // InternalEJSL.g:7253:3: (enumLiteral_1= 'Boolean' )
                    // InternalEJSL.g:7254:4: enumLiteral_1= 'Boolean'
                    {
                    enumLiteral_1=(Token)match(input,155,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getBooleanEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getStandardTypeKindsAccess().getBooleanEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:7261:3: (enumLiteral_2= 'Textarea' )
                    {
                    // InternalEJSL.g:7261:3: (enumLiteral_2= 'Textarea' )
                    // InternalEJSL.g:7262:4: enumLiteral_2= 'Textarea'
                    {
                    enumLiteral_2=(Token)match(input,156,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getTextareaEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getStandardTypeKindsAccess().getTextareaEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalEJSL.g:7269:3: (enumLiteral_3= 'Textfield' )
                    {
                    // InternalEJSL.g:7269:3: (enumLiteral_3= 'Textfield' )
                    // InternalEJSL.g:7270:4: enumLiteral_3= 'Textfield'
                    {
                    enumLiteral_3=(Token)match(input,157,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getTextfieldEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getStandardTypeKindsAccess().getTextfieldEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalEJSL.g:7277:3: (enumLiteral_4= 'Time' )
                    {
                    // InternalEJSL.g:7277:3: (enumLiteral_4= 'Time' )
                    // InternalEJSL.g:7278:4: enumLiteral_4= 'Time'
                    {
                    enumLiteral_4=(Token)match(input,158,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getTimeEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getStandardTypeKindsAccess().getTimeEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalEJSL.g:7285:3: (enumLiteral_5= 'Date' )
                    {
                    // InternalEJSL.g:7285:3: (enumLiteral_5= 'Date' )
                    // InternalEJSL.g:7286:4: enumLiteral_5= 'Date'
                    {
                    enumLiteral_5=(Token)match(input,159,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getDateEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getStandardTypeKindsAccess().getDateEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalEJSL.g:7293:3: (enumLiteral_6= 'Datetime' )
                    {
                    // InternalEJSL.g:7293:3: (enumLiteral_6= 'Datetime' )
                    // InternalEJSL.g:7294:4: enumLiteral_6= 'Datetime'
                    {
                    enumLiteral_6=(Token)match(input,160,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getDatetimeEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getStandardTypeKindsAccess().getDatetimeEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalEJSL.g:7301:3: (enumLiteral_7= 'Link' )
                    {
                    // InternalEJSL.g:7301:3: (enumLiteral_7= 'Link' )
                    // InternalEJSL.g:7302:4: enumLiteral_7= 'Link'
                    {
                    enumLiteral_7=(Token)match(input,161,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getLinkEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getStandardTypeKindsAccess().getLinkEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalEJSL.g:7309:3: (enumLiteral_8= 'Image' )
                    {
                    // InternalEJSL.g:7309:3: (enumLiteral_8= 'Image' )
                    // InternalEJSL.g:7310:4: enumLiteral_8= 'Image'
                    {
                    enumLiteral_8=(Token)match(input,162,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getImageEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getStandardTypeKindsAccess().getImageEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalEJSL.g:7317:3: (enumLiteral_9= 'File' )
                    {
                    // InternalEJSL.g:7317:3: (enumLiteral_9= 'File' )
                    // InternalEJSL.g:7318:4: enumLiteral_9= 'File'
                    {
                    enumLiteral_9=(Token)match(input,163,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getFileEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getStandardTypeKindsAccess().getFileEnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalEJSL.g:7325:3: (enumLiteral_10= 'Label' )
                    {
                    // InternalEJSL.g:7325:3: (enumLiteral_10= 'Label' )
                    // InternalEJSL.g:7326:4: enumLiteral_10= 'Label'
                    {
                    enumLiteral_10=(Token)match(input,164,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getLabelEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_10, grammarAccess.getStandardTypeKindsAccess().getLabelEnumLiteralDeclaration_10());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStandardTypeKinds"


    // $ANTLR start "ruleSectionKinds"
    // InternalEJSL.g:7336:1: ruleSectionKinds returns [Enumerator current=null] : ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) ) ;
    public final Enumerator ruleSectionKinds() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalEJSL.g:7342:2: ( ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) ) )
            // InternalEJSL.g:7343:2: ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) )
            {
            // InternalEJSL.g:7343:2: ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) )
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
                    // InternalEJSL.g:7344:3: (enumLiteral_0= '.backend' )
                    {
                    // InternalEJSL.g:7344:3: (enumLiteral_0= '.backend' )
                    // InternalEJSL.g:7345:4: enumLiteral_0= '.backend'
                    {
                    enumLiteral_0=(Token)match(input,165,FOLLOW_2); 

                    				current = grammarAccess.getSectionKindsAccess().getBackendEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSectionKindsAccess().getBackendEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:7352:3: (enumLiteral_1= '.frontend' )
                    {
                    // InternalEJSL.g:7352:3: (enumLiteral_1= '.frontend' )
                    // InternalEJSL.g:7353:4: enumLiteral_1= '.frontend'
                    {
                    enumLiteral_1=(Token)match(input,166,FOLLOW_2); 

                    				current = grammarAccess.getSectionKindsAccess().getFrontendEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSectionKindsAccess().getFrontendEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSectionKinds"


    // $ANTLR start "rulePageKinds"
    // InternalEJSL.g:7363:1: rulePageKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'list' ) | (enumLiteral_1= 'details' ) | (enumLiteral_2= 'custom' ) ) ;
    public final Enumerator rulePageKinds() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalEJSL.g:7369:2: ( ( (enumLiteral_0= 'list' ) | (enumLiteral_1= 'details' ) | (enumLiteral_2= 'custom' ) ) )
            // InternalEJSL.g:7370:2: ( (enumLiteral_0= 'list' ) | (enumLiteral_1= 'details' ) | (enumLiteral_2= 'custom' ) )
            {
            // InternalEJSL.g:7370:2: ( (enumLiteral_0= 'list' ) | (enumLiteral_1= 'details' ) | (enumLiteral_2= 'custom' ) )
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
                    // InternalEJSL.g:7371:3: (enumLiteral_0= 'list' )
                    {
                    // InternalEJSL.g:7371:3: (enumLiteral_0= 'list' )
                    // InternalEJSL.g:7372:4: enumLiteral_0= 'list'
                    {
                    enumLiteral_0=(Token)match(input,167,FOLLOW_2); 

                    				current = grammarAccess.getPageKindsAccess().getListEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getPageKindsAccess().getListEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:7379:3: (enumLiteral_1= 'details' )
                    {
                    // InternalEJSL.g:7379:3: (enumLiteral_1= 'details' )
                    // InternalEJSL.g:7380:4: enumLiteral_1= 'details'
                    {
                    enumLiteral_1=(Token)match(input,168,FOLLOW_2); 

                    				current = grammarAccess.getPageKindsAccess().getDetailsEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getPageKindsAccess().getDetailsEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:7387:3: (enumLiteral_2= 'custom' )
                    {
                    // InternalEJSL.g:7387:3: (enumLiteral_2= 'custom' )
                    // InternalEJSL.g:7388:4: enumLiteral_2= 'custom'
                    {
                    enumLiteral_2=(Token)match(input,169,FOLLOW_2); 

                    				current = grammarAccess.getPageKindsAccess().getCustomEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getPageKindsAccess().getCustomEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePageKinds"


    // $ANTLR start "ruleSimpleHTMLTypeKinds"
    // InternalEJSL.g:7398:1: ruleSimpleHTMLTypeKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) ) ;
    public final Enumerator ruleSimpleHTMLTypeKinds() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;


        	enterRule();

        try {
            // InternalEJSL.g:7404:2: ( ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) ) )
            // InternalEJSL.g:7405:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) )
            {
            // InternalEJSL.g:7405:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) )
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
                    // InternalEJSL.g:7406:3: (enumLiteral_0= 'Integer' )
                    {
                    // InternalEJSL.g:7406:3: (enumLiteral_0= 'Integer' )
                    // InternalEJSL.g:7407:4: enumLiteral_0= 'Integer'
                    {
                    enumLiteral_0=(Token)match(input,154,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getIntegerEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSimpleHTMLTypeKindsAccess().getIntegerEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:7414:3: (enumLiteral_1= 'Yes_No_Buttons' )
                    {
                    // InternalEJSL.g:7414:3: (enumLiteral_1= 'Yes_No_Buttons' )
                    // InternalEJSL.g:7415:4: enumLiteral_1= 'Yes_No_Buttons'
                    {
                    enumLiteral_1=(Token)match(input,170,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getYes_No_ButtonsEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSimpleHTMLTypeKindsAccess().getYes_No_ButtonsEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:7422:3: (enumLiteral_2= 'Textarea' )
                    {
                    // InternalEJSL.g:7422:3: (enumLiteral_2= 'Textarea' )
                    // InternalEJSL.g:7423:4: enumLiteral_2= 'Textarea'
                    {
                    enumLiteral_2=(Token)match(input,156,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getTextareaEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getSimpleHTMLTypeKindsAccess().getTextareaEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalEJSL.g:7430:3: (enumLiteral_3= 'Text_Field' )
                    {
                    // InternalEJSL.g:7430:3: (enumLiteral_3= 'Text_Field' )
                    // InternalEJSL.g:7431:4: enumLiteral_3= 'Text_Field'
                    {
                    enumLiteral_3=(Token)match(input,171,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getText_FieldEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getSimpleHTMLTypeKindsAccess().getText_FieldEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalEJSL.g:7438:3: (enumLiteral_4= 'Datepicker' )
                    {
                    // InternalEJSL.g:7438:3: (enumLiteral_4= 'Datepicker' )
                    // InternalEJSL.g:7439:4: enumLiteral_4= 'Datepicker'
                    {
                    enumLiteral_4=(Token)match(input,172,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getDatepickerEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getSimpleHTMLTypeKindsAccess().getDatepickerEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalEJSL.g:7446:3: (enumLiteral_5= 'Imagepicker' )
                    {
                    // InternalEJSL.g:7446:3: (enumLiteral_5= 'Imagepicker' )
                    // InternalEJSL.g:7447:4: enumLiteral_5= 'Imagepicker'
                    {
                    enumLiteral_5=(Token)match(input,173,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getImagepickerEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getSimpleHTMLTypeKindsAccess().getImagepickerEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalEJSL.g:7454:3: (enumLiteral_6= 'Filepicker' )
                    {
                    // InternalEJSL.g:7454:3: (enumLiteral_6= 'Filepicker' )
                    // InternalEJSL.g:7455:4: enumLiteral_6= 'Filepicker'
                    {
                    enumLiteral_6=(Token)match(input,174,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getFilepickerEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getSimpleHTMLTypeKindsAccess().getFilepickerEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalEJSL.g:7462:3: (enumLiteral_7= 'Text_Field_NE' )
                    {
                    // InternalEJSL.g:7462:3: (enumLiteral_7= 'Text_Field_NE' )
                    // InternalEJSL.g:7463:4: enumLiteral_7= 'Text_Field_NE'
                    {
                    enumLiteral_7=(Token)match(input,175,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getText_Field_NEEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getSimpleHTMLTypeKindsAccess().getText_Field_NEEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalEJSL.g:7470:3: (enumLiteral_8= 'Editor' )
                    {
                    // InternalEJSL.g:7470:3: (enumLiteral_8= 'Editor' )
                    // InternalEJSL.g:7471:4: enumLiteral_8= 'Editor'
                    {
                    enumLiteral_8=(Token)match(input,176,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getEditorEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getSimpleHTMLTypeKindsAccess().getEditorEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleHTMLTypeKinds"


    // $ANTLR start "ruleComplexHTMLTypeKinds"
    // InternalEJSL.g:7481:1: ruleComplexHTMLTypeKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) ) ;
    public final Enumerator ruleComplexHTMLTypeKinds() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalEJSL.g:7487:2: ( ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) ) )
            // InternalEJSL.g:7488:2: ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) )
            {
            // InternalEJSL.g:7488:2: ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) )
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
                    // InternalEJSL.g:7489:3: (enumLiteral_0= 'Multiselect' )
                    {
                    // InternalEJSL.g:7489:3: (enumLiteral_0= 'Multiselect' )
                    // InternalEJSL.g:7490:4: enumLiteral_0= 'Multiselect'
                    {
                    enumLiteral_0=(Token)match(input,177,FOLLOW_2); 

                    				current = grammarAccess.getComplexHTMLTypeKindsAccess().getMultiselectEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getComplexHTMLTypeKindsAccess().getMultiselectEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:7497:3: (enumLiteral_1= 'Checkbox' )
                    {
                    // InternalEJSL.g:7497:3: (enumLiteral_1= 'Checkbox' )
                    // InternalEJSL.g:7498:4: enumLiteral_1= 'Checkbox'
                    {
                    enumLiteral_1=(Token)match(input,178,FOLLOW_2); 

                    				current = grammarAccess.getComplexHTMLTypeKindsAccess().getCheckboxEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getComplexHTMLTypeKindsAccess().getCheckboxEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:7505:3: (enumLiteral_2= 'Radiobutto' )
                    {
                    // InternalEJSL.g:7505:3: (enumLiteral_2= 'Radiobutto' )
                    // InternalEJSL.g:7506:4: enumLiteral_2= 'Radiobutto'
                    {
                    enumLiteral_2=(Token)match(input,179,FOLLOW_2); 

                    				current = grammarAccess.getComplexHTMLTypeKindsAccess().getRadiobuttoEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getComplexHTMLTypeKindsAccess().getRadiobuttoEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
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