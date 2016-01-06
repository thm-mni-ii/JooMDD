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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_DATE", "RULE_LANGUAGE_CODE", "RULE_POSITION_TYPES", "RULE_POSITION_TYPES_NAMES", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'eJSLModel'", "'{'", "'datatypes'", "','", "'}'", "'globalparameters'", "'parametergroups'", "'entities'", "'datapackages'", "'pages'", "'extensions'", "'Not Null'", "'Default'", "'='", "'Auto Increment'", "'Datatype'", "'Parameter'", "'type'", "'defaultvalue'", "'label'", "'size'", "'description'", "'ParameterGroup'", "'Parameters'", "'Datapackage'", "'Entity'", "'extends'", "'attributes'", "'references'", "'Attribute'", "'Unique attribute'", "'with'", "'ID'", "'Reference'", "'*Entity'", "'lower'", "'upper'", "'StaticPage'", "'*ParameterGroups'", "'*Globalparameters'", "'localparameters'", "'links'", "'HTMLBody'", "'IndexPage'", "'*Entities'", "'table columns'", "'filters'", "'DetailsPage'", "'edit_fields'", "':'", "'('", "')'", "'ExternalLink'", "'target'", "'linked attribute'", "'InternalLink'", "'InternalcontextLink'", "'linkparameters'", "'*Attribute '", "'Extension package'", "'Manifestation'", "'languages'", "'Component'", "'Global parameter'", "'sections'", "'Backend section'", "'*Pages'", "'*Page'", "'from'", "'Frontend section'", "'Module'", "'Plugin'", "'Plugintype'", "'parameters'", "'Library'", "'classes'", "'packages'", "'Package'", "'Class'", "'*Class references'", "'methods'", "'Method'", "'Returnvalue'", "'methodparameters'", "'MethodParameter'", "'Template'", "'positions'", "'cssblocks'", "'authors'", "'creationdate'", "'copyright'", "'license'", "'link'", "'version'", "'Author'", "'authoremail'", "'authorurl'", "'Language'", "'keyvaluepairs'", "'Key'", "'Templateposition'", "'positionparameters'", "'Position Parameter'", "'divId'", "'Positiontype'", "'CSS keyvaluepairs'", "'CssBlock'", "'-'", "'.'", "'<'", "'>'", "'authenticate'", "'captcha'", "'content'", "'contact'", "'editors'", "'finder'", "'quick_icons'", "'search'", "'system'", "'user'", "'xml_rpc'", "'Integer'", "'Boolean'", "'Textarea'", "'Textfield'", "'Time'", "'Date'", "'Datetime'", "'Link'", "'Image'", "'File'", "'Label'", "'.backend'", "'.frontend'", "'Yes_No_Buttons'", "'Text_Field'", "'Datepicker'", "'Imagepicker'", "'Filepicker'", "'Text_Field_NE'", "'Editor'", "'Multiselect'", "'Checkbox'", "'Radiobutto'"
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
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int RULE_LANGUAGE_CODE=8;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
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
    // InternalEJSL.g:72:1: ruleEJSLModel returns [EObject current=null] : ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}' ) ;
    public final EObject ruleEJSLModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_27=null;
        Token otherlv_29=null;
        Token otherlv_30=null;
        Token otherlv_31=null;
        Token otherlv_33=null;
        Token otherlv_34=null;
        EObject lv_datatypes_6_0 = null;

        EObject lv_datatypes_8_0 = null;

        EObject lv_globalparameters_12_0 = null;

        EObject lv_parametergroups_16_0 = null;

        EObject lv_entities_20_0 = null;

        EObject lv_datapackages_24_0 = null;

        EObject lv_pages_28_0 = null;

        EObject lv_extensions_32_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:78:2: ( ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}' ) )
            // InternalEJSL.g:79:2: ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}' )
            {
            // InternalEJSL.g:79:2: ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}' )
            // InternalEJSL.g:80:3: () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}'
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
            		
            // InternalEJSL.g:113:3: (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==17) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalEJSL.g:114:4: otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}'
                    {
                    otherlv_4=(Token)match(input,17,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getEJSLModelAccess().getDatatypesKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_6); 

                    				newLeafNode(otherlv_5, grammarAccess.getEJSLModelAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:122:4: ( (lv_datatypes_6_0= ruleDatatype ) )
                    // InternalEJSL.g:123:5: (lv_datatypes_6_0= ruleDatatype )
                    {
                    // InternalEJSL.g:123:5: (lv_datatypes_6_0= ruleDatatype )
                    // InternalEJSL.g:124:6: lv_datatypes_6_0= ruleDatatype
                    {

                    						newCompositeNode(grammarAccess.getEJSLModelAccess().getDatatypesDatatypeParserRuleCall_4_2_0());
                    					
                    pushFollow(FOLLOW_7);
                    lv_datatypes_6_0=ruleDatatype();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEJSLModelRule());
                    						}
                    						add(
                    							current,
                    							"datatypes",
                    							lv_datatypes_6_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.Datatype");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:141:4: (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==18) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // InternalEJSL.g:142:5: otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) )
                    	    {
                    	    otherlv_7=(Token)match(input,18,FOLLOW_6); 

                    	    					newLeafNode(otherlv_7, grammarAccess.getEJSLModelAccess().getCommaKeyword_4_3_0());
                    	    				
                    	    // InternalEJSL.g:146:5: ( (lv_datatypes_8_0= ruleDatatype ) )
                    	    // InternalEJSL.g:147:6: (lv_datatypes_8_0= ruleDatatype )
                    	    {
                    	    // InternalEJSL.g:147:6: (lv_datatypes_8_0= ruleDatatype )
                    	    // InternalEJSL.g:148:7: lv_datatypes_8_0= ruleDatatype
                    	    {

                    	    							newCompositeNode(grammarAccess.getEJSLModelAccess().getDatatypesDatatypeParserRuleCall_4_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_7);
                    	    lv_datatypes_8_0=ruleDatatype();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getEJSLModelRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"datatypes",
                    	    								lv_datatypes_8_0,
                    	    								"de.thm.icampus.joomdd.ejsl.EJSL.Datatype");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,19,FOLLOW_8); 

                    				newLeafNode(otherlv_9, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_4_4());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:171:3: (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==20) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalEJSL.g:172:4: otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}'
                    {
                    otherlv_10=(Token)match(input,20,FOLLOW_4); 

                    				newLeafNode(otherlv_10, grammarAccess.getEJSLModelAccess().getGlobalparametersKeyword_5_0());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_9); 

                    				newLeafNode(otherlv_11, grammarAccess.getEJSLModelAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:180:4: ( (lv_globalparameters_12_0= ruleParameter ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==31) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalEJSL.g:181:5: (lv_globalparameters_12_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:181:5: (lv_globalparameters_12_0= ruleParameter )
                    	    // InternalEJSL.g:182:6: lv_globalparameters_12_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getEJSLModelAccess().getGlobalparametersParameterParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_9);
                    	    lv_globalparameters_12_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEJSLModelRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"globalparameters",
                    	    							lv_globalparameters_12_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    otherlv_13=(Token)match(input,19,FOLLOW_10); 

                    				newLeafNode(otherlv_13, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:204:3: (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==21) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalEJSL.g:205:4: otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}'
                    {
                    otherlv_14=(Token)match(input,21,FOLLOW_4); 

                    				newLeafNode(otherlv_14, grammarAccess.getEJSLModelAccess().getParametergroupsKeyword_6_0());
                    			
                    otherlv_15=(Token)match(input,16,FOLLOW_11); 

                    				newLeafNode(otherlv_15, grammarAccess.getEJSLModelAccess().getLeftCurlyBracketKeyword_6_1());
                    			
                    // InternalEJSL.g:213:4: ( (lv_parametergroups_16_0= ruleParameterGroup ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==37) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalEJSL.g:214:5: (lv_parametergroups_16_0= ruleParameterGroup )
                    	    {
                    	    // InternalEJSL.g:214:5: (lv_parametergroups_16_0= ruleParameterGroup )
                    	    // InternalEJSL.g:215:6: lv_parametergroups_16_0= ruleParameterGroup
                    	    {

                    	    						newCompositeNode(grammarAccess.getEJSLModelAccess().getParametergroupsParameterGroupParserRuleCall_6_2_0());
                    	    					
                    	    pushFollow(FOLLOW_11);
                    	    lv_parametergroups_16_0=ruleParameterGroup();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEJSLModelRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"parametergroups",
                    	    							lv_parametergroups_16_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.ParameterGroup");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,19,FOLLOW_12); 

                    				newLeafNode(otherlv_17, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_6_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:237:3: (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==22) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalEJSL.g:238:4: otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}'
                    {
                    otherlv_18=(Token)match(input,22,FOLLOW_4); 

                    				newLeafNode(otherlv_18, grammarAccess.getEJSLModelAccess().getEntitiesKeyword_7_0());
                    			
                    otherlv_19=(Token)match(input,16,FOLLOW_13); 

                    				newLeafNode(otherlv_19, grammarAccess.getEJSLModelAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:246:4: ( (lv_entities_20_0= ruleEntity ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==40) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalEJSL.g:247:5: (lv_entities_20_0= ruleEntity )
                    	    {
                    	    // InternalEJSL.g:247:5: (lv_entities_20_0= ruleEntity )
                    	    // InternalEJSL.g:248:6: lv_entities_20_0= ruleEntity
                    	    {

                    	    						newCompositeNode(grammarAccess.getEJSLModelAccess().getEntitiesEntityParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_13);
                    	    lv_entities_20_0=ruleEntity();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEJSLModelRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"entities",
                    	    							lv_entities_20_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Entity");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    otherlv_21=(Token)match(input,19,FOLLOW_14); 

                    				newLeafNode(otherlv_21, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:270:3: (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalEJSL.g:271:4: otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}'
                    {
                    otherlv_22=(Token)match(input,23,FOLLOW_4); 

                    				newLeafNode(otherlv_22, grammarAccess.getEJSLModelAccess().getDatapackagesKeyword_8_0());
                    			
                    otherlv_23=(Token)match(input,16,FOLLOW_15); 

                    				newLeafNode(otherlv_23, grammarAccess.getEJSLModelAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:279:4: ( (lv_datapackages_24_0= ruleDatapackage ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==39) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // InternalEJSL.g:280:5: (lv_datapackages_24_0= ruleDatapackage )
                    	    {
                    	    // InternalEJSL.g:280:5: (lv_datapackages_24_0= ruleDatapackage )
                    	    // InternalEJSL.g:281:6: lv_datapackages_24_0= ruleDatapackage
                    	    {

                    	    						newCompositeNode(grammarAccess.getEJSLModelAccess().getDatapackagesDatapackageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_datapackages_24_0=ruleDatapackage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEJSLModelRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"datapackages",
                    	    							lv_datapackages_24_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Datapackage");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,19,FOLLOW_16); 

                    				newLeafNode(otherlv_25, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:303:3: (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==24) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalEJSL.g:304:4: otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}'
                    {
                    otherlv_26=(Token)match(input,24,FOLLOW_4); 

                    				newLeafNode(otherlv_26, grammarAccess.getEJSLModelAccess().getPagesKeyword_9_0());
                    			
                    otherlv_27=(Token)match(input,16,FOLLOW_17); 

                    				newLeafNode(otherlv_27, grammarAccess.getEJSLModelAccess().getLeftCurlyBracketKeyword_9_1());
                    			
                    // InternalEJSL.g:312:4: ( (lv_pages_28_0= rulePage ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==52||LA11_0==58||LA11_0==62) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalEJSL.g:313:5: (lv_pages_28_0= rulePage )
                    	    {
                    	    // InternalEJSL.g:313:5: (lv_pages_28_0= rulePage )
                    	    // InternalEJSL.g:314:6: lv_pages_28_0= rulePage
                    	    {

                    	    						newCompositeNode(grammarAccess.getEJSLModelAccess().getPagesPageParserRuleCall_9_2_0());
                    	    					
                    	    pushFollow(FOLLOW_17);
                    	    lv_pages_28_0=rulePage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEJSLModelRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"pages",
                    	    							lv_pages_28_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Page");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    otherlv_29=(Token)match(input,19,FOLLOW_18); 

                    				newLeafNode(otherlv_29, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_9_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:336:3: (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==25) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalEJSL.g:337:4: otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}'
                    {
                    otherlv_30=(Token)match(input,25,FOLLOW_4); 

                    				newLeafNode(otherlv_30, grammarAccess.getEJSLModelAccess().getExtensionsKeyword_10_0());
                    			
                    otherlv_31=(Token)match(input,16,FOLLOW_19); 

                    				newLeafNode(otherlv_31, grammarAccess.getEJSLModelAccess().getLeftCurlyBracketKeyword_10_1());
                    			
                    // InternalEJSL.g:345:4: ( (lv_extensions_32_0= ruleExtension ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==74||LA13_0==77||(LA13_0>=85 && LA13_0<=86)||LA13_0==89||LA13_0==100) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // InternalEJSL.g:346:5: (lv_extensions_32_0= ruleExtension )
                    	    {
                    	    // InternalEJSL.g:346:5: (lv_extensions_32_0= ruleExtension )
                    	    // InternalEJSL.g:347:6: lv_extensions_32_0= ruleExtension
                    	    {

                    	    						newCompositeNode(grammarAccess.getEJSLModelAccess().getExtensionsExtensionParserRuleCall_10_2_0());
                    	    					
                    	    pushFollow(FOLLOW_19);
                    	    lv_extensions_32_0=ruleExtension();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getEJSLModelRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"extensions",
                    	    							lv_extensions_32_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Extension");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    otherlv_33=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_33, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_10_3());
                    			

                    }
                    break;

            }

            otherlv_34=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_34, grammarAccess.getEJSLModelAccess().getRightCurlyBracketKeyword_11());
            		

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


    // $ANTLR start "entryRuleType"
    // InternalEJSL.g:377:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalEJSL.g:377:45: (iv_ruleType= ruleType EOF )
            // InternalEJSL.g:378:2: iv_ruleType= ruleType EOF
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
    // InternalEJSL.g:384:1: ruleType returns [EObject current=null] : (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_DatatypeReference_0 = null;

        EObject this_StandardTypes_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:390:2: ( (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes ) )
            // InternalEJSL.g:391:2: (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes )
            {
            // InternalEJSL.g:391:2: (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_STRING) ) {
                alt15=1;
            }
            else if ( ((LA15_0>=137 && LA15_0<=147)) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // InternalEJSL.g:392:3: this_DatatypeReference_0= ruleDatatypeReference
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
                    // InternalEJSL.g:401:3: this_StandardTypes_1= ruleStandardTypes
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
    // InternalEJSL.g:413:1: entryRuleDatatypeReference returns [EObject current=null] : iv_ruleDatatypeReference= ruleDatatypeReference EOF ;
    public final EObject entryRuleDatatypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatatypeReference = null;


        try {
            // InternalEJSL.g:413:58: (iv_ruleDatatypeReference= ruleDatatypeReference EOF )
            // InternalEJSL.g:414:2: iv_ruleDatatypeReference= ruleDatatypeReference EOF
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
    // InternalEJSL.g:420:1: ruleDatatypeReference returns [EObject current=null] : ( (otherlv_0= RULE_STRING ) ) ;
    public final EObject ruleDatatypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;


        	enterRule();

        try {
            // InternalEJSL.g:426:2: ( ( (otherlv_0= RULE_STRING ) ) )
            // InternalEJSL.g:427:2: ( (otherlv_0= RULE_STRING ) )
            {
            // InternalEJSL.g:427:2: ( (otherlv_0= RULE_STRING ) )
            // InternalEJSL.g:428:3: (otherlv_0= RULE_STRING )
            {
            // InternalEJSL.g:428:3: (otherlv_0= RULE_STRING )
            // InternalEJSL.g:429:4: otherlv_0= RULE_STRING
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
    // InternalEJSL.g:443:1: entryRuleStandardTypes returns [EObject current=null] : iv_ruleStandardTypes= ruleStandardTypes EOF ;
    public final EObject entryRuleStandardTypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStandardTypes = null;


        try {
            // InternalEJSL.g:443:54: (iv_ruleStandardTypes= ruleStandardTypes EOF )
            // InternalEJSL.g:444:2: iv_ruleStandardTypes= ruleStandardTypes EOF
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
    // InternalEJSL.g:450:1: ruleStandardTypes returns [EObject current=null] : ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )? ) ;
    public final EObject ruleStandardTypes() throws RecognitionException {
        EObject current = null;

        Token lv_notnull_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_default_4_0=null;
        Token lv_autoincrement_5_0=null;
        Enumerator lv_type_0_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:456:2: ( ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )? ) )
            // InternalEJSL.g:457:2: ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )? )
            {
            // InternalEJSL.g:457:2: ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )? )
            // InternalEJSL.g:458:3: ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )?
            {
            // InternalEJSL.g:458:3: ( (lv_type_0_0= ruleStandardTypeKinds ) )
            // InternalEJSL.g:459:4: (lv_type_0_0= ruleStandardTypeKinds )
            {
            // InternalEJSL.g:459:4: (lv_type_0_0= ruleStandardTypeKinds )
            // InternalEJSL.g:460:5: lv_type_0_0= ruleStandardTypeKinds
            {

            					newCompositeNode(grammarAccess.getStandardTypesAccess().getTypeStandardTypeKindsEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_21);
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

            // InternalEJSL.g:477:3: ( (lv_notnull_1_0= 'Not Null' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==26) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalEJSL.g:478:4: (lv_notnull_1_0= 'Not Null' )
                    {
                    // InternalEJSL.g:478:4: (lv_notnull_1_0= 'Not Null' )
                    // InternalEJSL.g:479:5: lv_notnull_1_0= 'Not Null'
                    {
                    lv_notnull_1_0=(Token)match(input,26,FOLLOW_22); 

                    					newLeafNode(lv_notnull_1_0, grammarAccess.getStandardTypesAccess().getNotnullNotNullKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getStandardTypesRule());
                    					}
                    					setWithLastConsumed(current, "notnull", true, "Not Null");
                    				

                    }


                    }
                    break;

            }

            // InternalEJSL.g:491:3: (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==27) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalEJSL.g:492:4: otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) )
                    {
                    otherlv_2=(Token)match(input,27,FOLLOW_23); 

                    				newLeafNode(otherlv_2, grammarAccess.getStandardTypesAccess().getDefaultKeyword_2_0());
                    			
                    otherlv_3=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_3, grammarAccess.getStandardTypesAccess().getEqualsSignKeyword_2_1());
                    			
                    // InternalEJSL.g:500:4: ( (lv_default_4_0= RULE_STRING ) )
                    // InternalEJSL.g:501:5: (lv_default_4_0= RULE_STRING )
                    {
                    // InternalEJSL.g:501:5: (lv_default_4_0= RULE_STRING )
                    // InternalEJSL.g:502:6: lv_default_4_0= RULE_STRING
                    {
                    lv_default_4_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

                    						newLeafNode(lv_default_4_0, grammarAccess.getStandardTypesAccess().getDefaultSTRINGTerminalRuleCall_2_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getStandardTypesRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"default",
                    							true,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:519:3: ( (lv_autoincrement_5_0= 'Auto Increment' ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==29) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalEJSL.g:520:4: (lv_autoincrement_5_0= 'Auto Increment' )
                    {
                    // InternalEJSL.g:520:4: (lv_autoincrement_5_0= 'Auto Increment' )
                    // InternalEJSL.g:521:5: lv_autoincrement_5_0= 'Auto Increment'
                    {
                    lv_autoincrement_5_0=(Token)match(input,29,FOLLOW_2); 

                    					newLeafNode(lv_autoincrement_5_0, grammarAccess.getStandardTypesAccess().getAutoincrementAutoIncrementKeyword_3_0());
                    				

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
    // InternalEJSL.g:537:1: entryRuleDatatype returns [EObject current=null] : iv_ruleDatatype= ruleDatatype EOF ;
    public final EObject entryRuleDatatype() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatatype = null;


        try {
            // InternalEJSL.g:537:49: (iv_ruleDatatype= ruleDatatype EOF )
            // InternalEJSL.g:538:2: iv_ruleDatatype= ruleDatatype EOF
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
    // InternalEJSL.g:544:1: ruleDatatype returns [EObject current=null] : ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleDatatype() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;


        	enterRule();

        try {
            // InternalEJSL.g:550:2: ( ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) ) )
            // InternalEJSL.g:551:2: ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) )
            {
            // InternalEJSL.g:551:2: ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) )
            // InternalEJSL.g:552:3: () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) )
            {
            // InternalEJSL.g:552:3: ()
            // InternalEJSL.g:553:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDatatypeAccess().getDatatypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,30,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getDatatypeAccess().getDatatypeKeyword_1());
            		
            // InternalEJSL.g:563:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalEJSL.g:564:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalEJSL.g:564:4: (lv_name_2_0= RULE_STRING )
            // InternalEJSL.g:565:5: lv_name_2_0= RULE_STRING
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
    // InternalEJSL.g:585:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalEJSL.g:585:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalEJSL.g:586:2: iv_ruleParameter= ruleParameter EOF
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
    // InternalEJSL.g:592:1: ruleParameter returns [EObject current=null] : ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}' ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token lv_defaultvalue_9_0=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token lv_label_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_size_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token lv_descripton_18_0=null;
        Token otherlv_19=null;
        EObject lv_dtype_6_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:598:2: ( ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}' ) )
            // InternalEJSL.g:599:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}' )
            {
            // InternalEJSL.g:599:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}' )
            // InternalEJSL.g:600:3: () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}'
            {
            // InternalEJSL.g:600:3: ()
            // InternalEJSL.g:601:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParameterAccess().getParameterAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,31,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getParameterAccess().getParameterKeyword_1());
            		
            // InternalEJSL.g:611:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:612:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:612:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:613:5: lv_name_2_0= RULE_ID
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

            otherlv_3=(Token)match(input,16,FOLLOW_26); 

            			newLeafNode(otherlv_3, grammarAccess.getParameterAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,32,FOLLOW_23); 

            			newLeafNode(otherlv_4, grammarAccess.getParameterAccess().getTypeKeyword_4());
            		
            otherlv_5=(Token)match(input,28,FOLLOW_27); 

            			newLeafNode(otherlv_5, grammarAccess.getParameterAccess().getEqualsSignKeyword_5());
            		
            // InternalEJSL.g:641:3: ( (lv_dtype_6_0= ruleType ) )
            // InternalEJSL.g:642:4: (lv_dtype_6_0= ruleType )
            {
            // InternalEJSL.g:642:4: (lv_dtype_6_0= ruleType )
            // InternalEJSL.g:643:5: lv_dtype_6_0= ruleType
            {

            					newCompositeNode(grammarAccess.getParameterAccess().getDtypeTypeParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_28);
            lv_dtype_6_0=ruleType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getParameterRule());
            					}
            					set(
            						current,
            						"dtype",
            						lv_dtype_6_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Type");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:660:3: (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==33) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalEJSL.g:661:4: otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,33,FOLLOW_23); 

                    				newLeafNode(otherlv_7, grammarAccess.getParameterAccess().getDefaultvalueKeyword_7_0());
                    			
                    otherlv_8=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_8, grammarAccess.getParameterAccess().getEqualsSignKeyword_7_1());
                    			
                    // InternalEJSL.g:669:4: ( (lv_defaultvalue_9_0= RULE_STRING ) )
                    // InternalEJSL.g:670:5: (lv_defaultvalue_9_0= RULE_STRING )
                    {
                    // InternalEJSL.g:670:5: (lv_defaultvalue_9_0= RULE_STRING )
                    // InternalEJSL.g:671:6: lv_defaultvalue_9_0= RULE_STRING
                    {
                    lv_defaultvalue_9_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

                    						newLeafNode(lv_defaultvalue_9_0, grammarAccess.getParameterAccess().getDefaultvalueSTRINGTerminalRuleCall_7_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"defaultvalue",
                    							lv_defaultvalue_9_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:688:3: (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==34) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalEJSL.g:689:4: otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,34,FOLLOW_23); 

                    				newLeafNode(otherlv_10, grammarAccess.getParameterAccess().getLabelKeyword_8_0());
                    			
                    otherlv_11=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_11, grammarAccess.getParameterAccess().getEqualsSignKeyword_8_1());
                    			
                    // InternalEJSL.g:697:4: ( (lv_label_12_0= RULE_STRING ) )
                    // InternalEJSL.g:698:5: (lv_label_12_0= RULE_STRING )
                    {
                    // InternalEJSL.g:698:5: (lv_label_12_0= RULE_STRING )
                    // InternalEJSL.g:699:6: lv_label_12_0= RULE_STRING
                    {
                    lv_label_12_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

                    						newLeafNode(lv_label_12_0, grammarAccess.getParameterAccess().getLabelSTRINGTerminalRuleCall_8_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"label",
                    							lv_label_12_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:716:3: (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==35) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalEJSL.g:717:4: otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) )
                    {
                    otherlv_13=(Token)match(input,35,FOLLOW_23); 

                    				newLeafNode(otherlv_13, grammarAccess.getParameterAccess().getSizeKeyword_9_0());
                    			
                    otherlv_14=(Token)match(input,28,FOLLOW_31); 

                    				newLeafNode(otherlv_14, grammarAccess.getParameterAccess().getEqualsSignKeyword_9_1());
                    			
                    // InternalEJSL.g:725:4: ( (lv_size_15_0= RULE_INT ) )
                    // InternalEJSL.g:726:5: (lv_size_15_0= RULE_INT )
                    {
                    // InternalEJSL.g:726:5: (lv_size_15_0= RULE_INT )
                    // InternalEJSL.g:727:6: lv_size_15_0= RULE_INT
                    {
                    lv_size_15_0=(Token)match(input,RULE_INT,FOLLOW_32); 

                    						newLeafNode(lv_size_15_0, grammarAccess.getParameterAccess().getSizeINTTerminalRuleCall_9_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"size",
                    							lv_size_15_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:744:3: (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==36) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalEJSL.g:745:4: otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) )
                    {
                    otherlv_16=(Token)match(input,36,FOLLOW_23); 

                    				newLeafNode(otherlv_16, grammarAccess.getParameterAccess().getDescriptionKeyword_10_0());
                    			
                    otherlv_17=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_17, grammarAccess.getParameterAccess().getEqualsSignKeyword_10_1());
                    			
                    // InternalEJSL.g:753:4: ( (lv_descripton_18_0= RULE_STRING ) )
                    // InternalEJSL.g:754:5: (lv_descripton_18_0= RULE_STRING )
                    {
                    // InternalEJSL.g:754:5: (lv_descripton_18_0= RULE_STRING )
                    // InternalEJSL.g:755:6: lv_descripton_18_0= RULE_STRING
                    {
                    lv_descripton_18_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

                    						newLeafNode(lv_descripton_18_0, grammarAccess.getParameterAccess().getDescriptonSTRINGTerminalRuleCall_10_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"descripton",
                    							lv_descripton_18_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_19=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_19, grammarAccess.getParameterAccess().getRightCurlyBracketKeyword_11());
            		

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
    // InternalEJSL.g:780:1: entryRuleParameterGroup returns [EObject current=null] : iv_ruleParameterGroup= ruleParameterGroup EOF ;
    public final EObject entryRuleParameterGroup() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterGroup = null;


        try {
            // InternalEJSL.g:780:55: (iv_ruleParameterGroup= ruleParameterGroup EOF )
            // InternalEJSL.g:781:2: iv_ruleParameterGroup= ruleParameterGroup EOF
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
    // InternalEJSL.g:787:1: ruleParameterGroup returns [EObject current=null] : ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}' ) ;
    public final EObject ruleParameterGroup() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_label_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        EObject lv_parameters_10_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:793:2: ( ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}' ) )
            // InternalEJSL.g:794:2: ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}' )
            {
            // InternalEJSL.g:794:2: ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}' )
            // InternalEJSL.g:795:3: () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}'
            {
            // InternalEJSL.g:795:3: ()
            // InternalEJSL.g:796:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParameterGroupAccess().getParameterGroupAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,37,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getParameterGroupAccess().getParameterGroupKeyword_1());
            		
            // InternalEJSL.g:806:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:807:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:807:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:808:5: lv_name_2_0= RULE_ID
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

            otherlv_3=(Token)match(input,16,FOLLOW_33); 

            			newLeafNode(otherlv_3, grammarAccess.getParameterGroupAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:828:3: (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==34) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalEJSL.g:829:4: otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,34,FOLLOW_23); 

                    				newLeafNode(otherlv_4, grammarAccess.getParameterGroupAccess().getLabelKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_5, grammarAccess.getParameterGroupAccess().getEqualsSignKeyword_4_1());
                    			
                    // InternalEJSL.g:837:4: ( (lv_label_6_0= RULE_STRING ) )
                    // InternalEJSL.g:838:5: (lv_label_6_0= RULE_STRING )
                    {
                    // InternalEJSL.g:838:5: (lv_label_6_0= RULE_STRING )
                    // InternalEJSL.g:839:6: lv_label_6_0= RULE_STRING
                    {
                    lv_label_6_0=(Token)match(input,RULE_STRING,FOLLOW_34); 

                    						newLeafNode(lv_label_6_0, grammarAccess.getParameterGroupAccess().getLabelSTRINGTerminalRuleCall_4_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterGroupRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"label",
                    							lv_label_6_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:856:3: (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' )
            // InternalEJSL.g:857:4: otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}'
            {
            otherlv_7=(Token)match(input,38,FOLLOW_4); 

            				newLeafNode(otherlv_7, grammarAccess.getParameterGroupAccess().getParametersKeyword_5_0());
            			
            otherlv_8=(Token)match(input,16,FOLLOW_35); 

            				newLeafNode(otherlv_8, grammarAccess.getParameterGroupAccess().getLeftCurlyBracketKeyword_5_1());
            			
            // InternalEJSL.g:865:4: ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )*
            loop24:
            do {
                int alt24=3;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ID) ) {
                    alt24=1;
                }
                else if ( (LA24_0==31) ) {
                    alt24=2;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalEJSL.g:866:5: ( (otherlv_9= RULE_ID ) )
            	    {
            	    // InternalEJSL.g:866:5: ( (otherlv_9= RULE_ID ) )
            	    // InternalEJSL.g:867:6: (otherlv_9= RULE_ID )
            	    {
            	    // InternalEJSL.g:867:6: (otherlv_9= RULE_ID )
            	    // InternalEJSL.g:868:7: otherlv_9= RULE_ID
            	    {

            	    							if (current==null) {
            	    								current = createModelElement(grammarAccess.getParameterGroupRule());
            	    							}
            	    						
            	    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_35); 

            	    							newLeafNode(otherlv_9, grammarAccess.getParameterGroupAccess().getGlobalparametersParameterCrossReference_5_2_0_0());
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalEJSL.g:880:5: ( (lv_parameters_10_0= ruleParameter ) )
            	    {
            	    // InternalEJSL.g:880:5: ( (lv_parameters_10_0= ruleParameter ) )
            	    // InternalEJSL.g:881:6: (lv_parameters_10_0= ruleParameter )
            	    {
            	    // InternalEJSL.g:881:6: (lv_parameters_10_0= ruleParameter )
            	    // InternalEJSL.g:882:7: lv_parameters_10_0= ruleParameter
            	    {

            	    							newCompositeNode(grammarAccess.getParameterGroupAccess().getParametersParameterParserRuleCall_5_2_1_0());
            	    						
            	    pushFollow(FOLLOW_35);
            	    lv_parameters_10_0=ruleParameter();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getParameterGroupRule());
            	    							}
            	    							add(
            	    								current,
            	    								"parameters",
            	    								lv_parameters_10_0,
            	    								"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            otherlv_11=(Token)match(input,19,FOLLOW_20); 

            				newLeafNode(otherlv_11, grammarAccess.getParameterGroupAccess().getRightCurlyBracketKeyword_5_3());
            			

            }

            otherlv_12=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_12, grammarAccess.getParameterGroupAccess().getRightCurlyBracketKeyword_6());
            		

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


    // $ANTLR start "entryRuleDatapackage"
    // InternalEJSL.g:913:1: entryRuleDatapackage returns [EObject current=null] : iv_ruleDatapackage= ruleDatapackage EOF ;
    public final EObject entryRuleDatapackage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatapackage = null;


        try {
            // InternalEJSL.g:913:52: (iv_ruleDatapackage= ruleDatapackage EOF )
            // InternalEJSL.g:914:2: iv_ruleDatapackage= ruleDatapackage EOF
            {
             newCompositeNode(grammarAccess.getDatapackageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatapackage=ruleDatapackage();

            state._fsp--;

             current =iv_ruleDatapackage; 
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
    // $ANTLR end "entryRuleDatapackage"


    // $ANTLR start "ruleDatapackage"
    // InternalEJSL.g:920:1: ruleDatapackage returns [EObject current=null] : ( () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' ) ;
    public final EObject ruleDatapackage() throws RecognitionException {
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
        EObject lv_datapackages_6_0 = null;

        EObject lv_entities_10_0 = null;

        EObject lv_datatypes_14_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:926:2: ( ( () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' ) )
            // InternalEJSL.g:927:2: ( () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' )
            {
            // InternalEJSL.g:927:2: ( () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' )
            // InternalEJSL.g:928:3: () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}'
            {
            // InternalEJSL.g:928:3: ()
            // InternalEJSL.g:929:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDatapackageAccess().getDatapackageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,39,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getDatapackageAccess().getDatapackageKeyword_1());
            		
            // InternalEJSL.g:939:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:940:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:940:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:941:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_2_0, grammarAccess.getDatapackageAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDatapackageRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_36); 

            			newLeafNode(otherlv_3, grammarAccess.getDatapackageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:961:3: (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==23) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalEJSL.g:962:4: otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}'
                    {
                    otherlv_4=(Token)match(input,23,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getDatapackageAccess().getDatapackagesKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_15); 

                    				newLeafNode(otherlv_5, grammarAccess.getDatapackageAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:970:4: ( (lv_datapackages_6_0= ruleDatapackage ) )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==39) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // InternalEJSL.g:971:5: (lv_datapackages_6_0= ruleDatapackage )
                    	    {
                    	    // InternalEJSL.g:971:5: (lv_datapackages_6_0= ruleDatapackage )
                    	    // InternalEJSL.g:972:6: lv_datapackages_6_0= ruleDatapackage
                    	    {

                    	    						newCompositeNode(grammarAccess.getDatapackageAccess().getDatapackagesDatapackageParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_datapackages_6_0=ruleDatapackage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getDatapackageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"datapackages",
                    	    							lv_datapackages_6_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Datapackage");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,19,FOLLOW_37); 

                    				newLeafNode(otherlv_7, grammarAccess.getDatapackageAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:994:3: (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==22) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalEJSL.g:995:4: otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,22,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getDatapackageAccess().getEntitiesKeyword_5_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_13); 

                    				newLeafNode(otherlv_9, grammarAccess.getDatapackageAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:1003:4: ( (lv_entities_10_0= ruleEntity ) )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==40) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // InternalEJSL.g:1004:5: (lv_entities_10_0= ruleEntity )
                    	    {
                    	    // InternalEJSL.g:1004:5: (lv_entities_10_0= ruleEntity )
                    	    // InternalEJSL.g:1005:6: lv_entities_10_0= ruleEntity
                    	    {

                    	    						newCompositeNode(grammarAccess.getDatapackageAccess().getEntitiesEntityParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_13);
                    	    lv_entities_10_0=ruleEntity();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getDatapackageRule());
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
                    	    break loop27;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_38); 

                    				newLeafNode(otherlv_11, grammarAccess.getDatapackageAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:1027:3: (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==17) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalEJSL.g:1028:4: otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}'
                    {
                    otherlv_12=(Token)match(input,17,FOLLOW_4); 

                    				newLeafNode(otherlv_12, grammarAccess.getDatapackageAccess().getDatatypesKeyword_6_0());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_39); 

                    				newLeafNode(otherlv_13, grammarAccess.getDatapackageAccess().getLeftCurlyBracketKeyword_6_1());
                    			
                    // InternalEJSL.g:1036:4: ( (lv_datatypes_14_0= ruleDatatype ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==30) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // InternalEJSL.g:1037:5: (lv_datatypes_14_0= ruleDatatype )
                    	    {
                    	    // InternalEJSL.g:1037:5: (lv_datatypes_14_0= ruleDatatype )
                    	    // InternalEJSL.g:1038:6: lv_datatypes_14_0= ruleDatatype
                    	    {

                    	    						newCompositeNode(grammarAccess.getDatapackageAccess().getDatatypesDatatypeParserRuleCall_6_2_0());
                    	    					
                    	    pushFollow(FOLLOW_39);
                    	    lv_datatypes_14_0=ruleDatatype();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getDatapackageRule());
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
                    	    break loop29;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_15, grammarAccess.getDatapackageAccess().getRightCurlyBracketKeyword_6_3());
                    			

                    }
                    break;

            }

            otherlv_16=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_16, grammarAccess.getDatapackageAccess().getRightCurlyBracketKeyword_7());
            		

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
    // $ANTLR end "ruleDatapackage"


    // $ANTLR start "entryRuleEntity"
    // InternalEJSL.g:1068:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // InternalEJSL.g:1068:47: (iv_ruleEntity= ruleEntity EOF )
            // InternalEJSL.g:1069:2: iv_ruleEntity= ruleEntity EOF
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
    // InternalEJSL.g:1075:1: ruleEntity returns [EObject current=null] : ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' ) ;
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
            // InternalEJSL.g:1081:2: ( ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' ) )
            // InternalEJSL.g:1082:2: ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' )
            {
            // InternalEJSL.g:1082:2: ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' )
            // InternalEJSL.g:1083:3: () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}'
            {
            // InternalEJSL.g:1083:3: ()
            // InternalEJSL.g:1084:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEntityAccess().getEntityAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,40,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getEntityAccess().getEntityKeyword_1());
            		
            // InternalEJSL.g:1094:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:1095:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:1095:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:1096:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getEntityAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_41);
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

            // InternalEJSL.g:1113:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==41) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalEJSL.g:1114:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {
                    otherlv_3=(Token)match(input,41,FOLLOW_40); 

                    				newLeafNode(otherlv_3, grammarAccess.getEntityAccess().getExtendsKeyword_3_0());
                    			
                    // InternalEJSL.g:1118:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:1119:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:1119:5: ( ruleQualifiedName )
                    // InternalEJSL.g:1120:6: ruleQualifiedName
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

            otherlv_5=(Token)match(input,16,FOLLOW_42); 

            			newLeafNode(otherlv_5, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:1139:3: (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==42) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalEJSL.g:1140:4: otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}'
                    {
                    otherlv_6=(Token)match(input,42,FOLLOW_4); 

                    				newLeafNode(otherlv_6, grammarAccess.getEntityAccess().getAttributesKeyword_5_0());
                    			
                    otherlv_7=(Token)match(input,16,FOLLOW_43); 

                    				newLeafNode(otherlv_7, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:1148:4: ( (lv_attributes_8_0= ruleAttribute ) )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==44) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // InternalEJSL.g:1149:5: (lv_attributes_8_0= ruleAttribute )
                    	    {
                    	    // InternalEJSL.g:1149:5: (lv_attributes_8_0= ruleAttribute )
                    	    // InternalEJSL.g:1150:6: lv_attributes_8_0= ruleAttribute
                    	    {

                    	    						newCompositeNode(grammarAccess.getEntityAccess().getAttributesAttributeParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_43);
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
                    	    break loop32;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,19,FOLLOW_44); 

                    				newLeafNode(otherlv_9, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:1172:3: (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==43) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalEJSL.g:1173:4: otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}'
                    {
                    otherlv_10=(Token)match(input,43,FOLLOW_4); 

                    				newLeafNode(otherlv_10, grammarAccess.getEntityAccess().getReferencesKeyword_6_0());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_45); 

                    				newLeafNode(otherlv_11, grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_6_1());
                    			
                    // InternalEJSL.g:1181:4: ( (lv_references_12_0= ruleReference ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==48) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // InternalEJSL.g:1182:5: (lv_references_12_0= ruleReference )
                    	    {
                    	    // InternalEJSL.g:1182:5: (lv_references_12_0= ruleReference )
                    	    // InternalEJSL.g:1183:6: lv_references_12_0= ruleReference
                    	    {

                    	    						newCompositeNode(grammarAccess.getEntityAccess().getReferencesReferenceParserRuleCall_6_2_0());
                    	    					
                    	    pushFollow(FOLLOW_45);
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
                    	    break loop34;
                        }
                    } while (true);

                    otherlv_13=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_13, grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_6_3());
                    			

                    }
                    break;

            }

            otherlv_14=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:1213:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // InternalEJSL.g:1213:50: (iv_ruleAttribute= ruleAttribute EOF )
            // InternalEJSL.g:1214:2: iv_ruleAttribute= ruleAttribute EOF
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
    // InternalEJSL.g:1220:1: ruleAttribute returns [EObject current=null] : ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}' ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_isunique_7_0=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_type_6_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1226:2: ( ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}' ) )
            // InternalEJSL.g:1227:2: ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}' )
            {
            // InternalEJSL.g:1227:2: ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}' )
            // InternalEJSL.g:1228:3: () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}'
            {
            // InternalEJSL.g:1228:3: ()
            // InternalEJSL.g:1229:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAttributeAccess().getAttributeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,44,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getAttributeAccess().getAttributeKeyword_1());
            		
            // InternalEJSL.g:1239:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:1240:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:1240:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:1241:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_26); 

            			newLeafNode(otherlv_3, grammarAccess.getAttributeAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,32,FOLLOW_23); 

            			newLeafNode(otherlv_4, grammarAccess.getAttributeAccess().getTypeKeyword_4());
            		
            otherlv_5=(Token)match(input,28,FOLLOW_27); 

            			newLeafNode(otherlv_5, grammarAccess.getAttributeAccess().getEqualsSignKeyword_5());
            		
            // InternalEJSL.g:1270:3: ( (lv_type_6_0= ruleType ) )
            // InternalEJSL.g:1271:4: (lv_type_6_0= ruleType )
            {
            // InternalEJSL.g:1271:4: (lv_type_6_0= ruleType )
            // InternalEJSL.g:1272:5: lv_type_6_0= ruleType
            {

            					newCompositeNode(grammarAccess.getAttributeAccess().getTypeTypeParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_46);
            lv_type_6_0=ruleType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAttributeRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_6_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.Type");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:1289:3: ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==45) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalEJSL.g:1290:4: ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )?
                    {
                    // InternalEJSL.g:1290:4: ( (lv_isunique_7_0= 'Unique attribute' ) )
                    // InternalEJSL.g:1291:5: (lv_isunique_7_0= 'Unique attribute' )
                    {
                    // InternalEJSL.g:1291:5: (lv_isunique_7_0= 'Unique attribute' )
                    // InternalEJSL.g:1292:6: lv_isunique_7_0= 'Unique attribute'
                    {
                    lv_isunique_7_0=(Token)match(input,45,FOLLOW_47); 

                    						newLeafNode(lv_isunique_7_0, grammarAccess.getAttributeAccess().getIsuniqueUniqueAttributeKeyword_7_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAttributeRule());
                    						}
                    						setWithLastConsumed(current, "isunique", true, "Unique attribute");
                    					

                    }


                    }

                    // InternalEJSL.g:1304:4: (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==46) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // InternalEJSL.g:1305:5: otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' )
                            {
                            otherlv_8=(Token)match(input,46,FOLLOW_48); 

                            					newLeafNode(otherlv_8, grammarAccess.getAttributeAccess().getWithKeyword_7_1_0());
                            				
                            // InternalEJSL.g:1309:5: ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' )
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( (LA36_0==RULE_ID||LA36_0==124) ) {
                                alt36=1;
                            }
                            else if ( (LA36_0==47) ) {
                                alt36=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 36, 0, input);

                                throw nvae;
                            }
                            switch (alt36) {
                                case 1 :
                                    // InternalEJSL.g:1310:6: ( ( ruleQualifiedName ) )
                                    {
                                    // InternalEJSL.g:1310:6: ( ( ruleQualifiedName ) )
                                    // InternalEJSL.g:1311:7: ( ruleQualifiedName )
                                    {
                                    // InternalEJSL.g:1311:7: ( ruleQualifiedName )
                                    // InternalEJSL.g:1312:8: ruleQualifiedName
                                    {

                                    								if (current==null) {
                                    									current = createModelElement(grammarAccess.getAttributeRule());
                                    								}
                                    							

                                    								newCompositeNode(grammarAccess.getAttributeAccess().getWithattributeCrossReference_7_1_1_0_0());
                                    							
                                    pushFollow(FOLLOW_20);
                                    ruleQualifiedName();

                                    state._fsp--;


                                    								afterParserOrEnumRuleCall();
                                    							

                                    }


                                    }


                                    }
                                    break;
                                case 2 :
                                    // InternalEJSL.g:1327:6: otherlv_10= 'ID'
                                    {
                                    otherlv_10=(Token)match(input,47,FOLLOW_20); 

                                    						newLeafNode(otherlv_10, grammarAccess.getAttributeAccess().getIDKeyword_7_1_1_1());
                                    					

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_11=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getAttributeAccess().getRightCurlyBracketKeyword_8());
            		

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
    // InternalEJSL.g:1342:1: entryRuleReference returns [EObject current=null] : iv_ruleReference= ruleReference EOF ;
    public final EObject entryRuleReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReference = null;


        try {
            // InternalEJSL.g:1342:50: (iv_ruleReference= ruleReference EOF )
            // InternalEJSL.g:1343:2: iv_ruleReference= ruleReference EOF
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
    // InternalEJSL.g:1349:1: ruleReference returns [EObject current=null] : ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}' ) ;
    public final EObject ruleReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        AntlrDatatypeRuleToken lv_lower_14_0 = null;

        AntlrDatatypeRuleToken lv_upper_17_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1355:2: ( ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}' ) )
            // InternalEJSL.g:1356:2: ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}' )
            {
            // InternalEJSL.g:1356:2: ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}' )
            // InternalEJSL.g:1357:3: () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}'
            {
            // InternalEJSL.g:1357:3: ()
            // InternalEJSL.g:1358:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getReferenceAccess().getReferenceAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,48,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getReferenceAccess().getReferenceKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_49); 

            			newLeafNode(otherlv_2, grammarAccess.getReferenceAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,44,FOLLOW_23); 

            			newLeafNode(otherlv_3, grammarAccess.getReferenceAccess().getAttributeKeyword_3());
            		
            otherlv_4=(Token)match(input,28,FOLLOW_40); 

            			newLeafNode(otherlv_4, grammarAccess.getReferenceAccess().getEqualsSignKeyword_4());
            		
            // InternalEJSL.g:1380:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:1381:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:1381:4: ( ruleQualifiedName )
            // InternalEJSL.g:1382:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceRule());
            					}
            				

            					newCompositeNode(grammarAccess.getReferenceAccess().getAttributeAttributeCrossReference_5_0());
            				
            pushFollow(FOLLOW_50);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,49,FOLLOW_23); 

            			newLeafNode(otherlv_6, grammarAccess.getReferenceAccess().getEntityKeyword_6());
            		
            otherlv_7=(Token)match(input,28,FOLLOW_40); 

            			newLeafNode(otherlv_7, grammarAccess.getReferenceAccess().getEqualsSignKeyword_7());
            		
            // InternalEJSL.g:1404:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:1405:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:1405:4: ( ruleQualifiedName )
            // InternalEJSL.g:1406:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceRule());
            					}
            				

            					newCompositeNode(grammarAccess.getReferenceAccess().getEntityEntityCrossReference_8_0());
            				
            pushFollow(FOLLOW_51);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,48,FOLLOW_23); 

            			newLeafNode(otherlv_9, grammarAccess.getReferenceAccess().getReferenceKeyword_9());
            		
            otherlv_10=(Token)match(input,28,FOLLOW_40); 

            			newLeafNode(otherlv_10, grammarAccess.getReferenceAccess().getEqualsSignKeyword_10());
            		
            // InternalEJSL.g:1428:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:1429:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:1429:4: ( ruleQualifiedName )
            // InternalEJSL.g:1430:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getReferenceRule());
            					}
            				

            					newCompositeNode(grammarAccess.getReferenceAccess().getAttributereferecedAttributeCrossReference_11_0());
            				
            pushFollow(FOLLOW_52);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_12=(Token)match(input,50,FOLLOW_23); 

            			newLeafNode(otherlv_12, grammarAccess.getReferenceAccess().getLowerKeyword_12());
            		
            otherlv_13=(Token)match(input,28,FOLLOW_53); 

            			newLeafNode(otherlv_13, grammarAccess.getReferenceAccess().getEqualsSignKeyword_13());
            		
            // InternalEJSL.g:1452:3: ( (lv_lower_14_0= ruleNUMBER ) )
            // InternalEJSL.g:1453:4: (lv_lower_14_0= ruleNUMBER )
            {
            // InternalEJSL.g:1453:4: (lv_lower_14_0= ruleNUMBER )
            // InternalEJSL.g:1454:5: lv_lower_14_0= ruleNUMBER
            {

            					newCompositeNode(grammarAccess.getReferenceAccess().getLowerNUMBERParserRuleCall_14_0());
            				
            pushFollow(FOLLOW_54);
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

            otherlv_15=(Token)match(input,51,FOLLOW_23); 

            			newLeafNode(otherlv_15, grammarAccess.getReferenceAccess().getUpperKeyword_15());
            		
            otherlv_16=(Token)match(input,28,FOLLOW_53); 

            			newLeafNode(otherlv_16, grammarAccess.getReferenceAccess().getEqualsSignKeyword_16());
            		
            // InternalEJSL.g:1479:3: ( (lv_upper_17_0= ruleNUMBER ) )
            // InternalEJSL.g:1480:4: (lv_upper_17_0= ruleNUMBER )
            {
            // InternalEJSL.g:1480:4: (lv_upper_17_0= ruleNUMBER )
            // InternalEJSL.g:1481:5: lv_upper_17_0= ruleNUMBER
            {

            					newCompositeNode(grammarAccess.getReferenceAccess().getUpperNUMBERParserRuleCall_17_0());
            				
            pushFollow(FOLLOW_20);
            lv_upper_17_0=ruleNUMBER();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getReferenceRule());
            					}
            					set(
            						current,
            						"upper",
            						lv_upper_17_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.NUMBER");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_18=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_18, grammarAccess.getReferenceAccess().getRightCurlyBracketKeyword_18());
            		

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
    // InternalEJSL.g:1506:1: entryRulePage returns [EObject current=null] : iv_rulePage= rulePage EOF ;
    public final EObject entryRulePage() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePage = null;


        try {
            // InternalEJSL.g:1506:45: (iv_rulePage= rulePage EOF )
            // InternalEJSL.g:1507:2: iv_rulePage= rulePage EOF
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
    // InternalEJSL.g:1513:1: rulePage returns [EObject current=null] : (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage ) ;
    public final EObject rulePage() throws RecognitionException {
        EObject current = null;

        EObject this_StaticPage_0 = null;

        EObject this_DynamicPage_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1519:2: ( (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage ) )
            // InternalEJSL.g:1520:2: (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage )
            {
            // InternalEJSL.g:1520:2: (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==52) ) {
                alt39=1;
            }
            else if ( (LA39_0==58||LA39_0==62) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // InternalEJSL.g:1521:3: this_StaticPage_0= ruleStaticPage
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
                    // InternalEJSL.g:1530:3: this_DynamicPage_1= ruleDynamicPage
                    {

                    			newCompositeNode(grammarAccess.getPageAccess().getDynamicPageParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_DynamicPage_1=ruleDynamicPage();

                    state._fsp--;


                    			current = this_DynamicPage_1;
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
    // InternalEJSL.g:1542:1: entryRuleStaticPage returns [EObject current=null] : iv_ruleStaticPage= ruleStaticPage EOF ;
    public final EObject entryRuleStaticPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStaticPage = null;


        try {
            // InternalEJSL.g:1542:51: (iv_ruleStaticPage= ruleStaticPage EOF )
            // InternalEJSL.g:1543:2: iv_ruleStaticPage= ruleStaticPage EOF
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
    // InternalEJSL.g:1549:1: ruleStaticPage returns [EObject current=null] : ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}' ) ;
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
        Token lv_HTMLBody_22_0=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_localparameters_14_0 = null;

        EObject lv_links_18_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1555:2: ( ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}' ) )
            // InternalEJSL.g:1556:2: ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}' )
            {
            // InternalEJSL.g:1556:2: ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}' )
            // InternalEJSL.g:1557:3: () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}'
            {
            // InternalEJSL.g:1557:3: ()
            // InternalEJSL.g:1558:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStaticPageAccess().getStaticPageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,52,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getStaticPageAccess().getStaticPageKeyword_1());
            		
            // InternalEJSL.g:1568:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:1569:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:1569:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:1570:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_55); 

            			newLeafNode(otherlv_3, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:1591:3: (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==53) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalEJSL.g:1592:4: otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )*
                    {
                    otherlv_4=(Token)match(input,53,FOLLOW_25); 

                    				newLeafNode(otherlv_4, grammarAccess.getStaticPageAccess().getParameterGroupsKeyword_4_0());
                    			
                    // InternalEJSL.g:1596:4: ( (otherlv_5= RULE_ID ) )
                    // InternalEJSL.g:1597:5: (otherlv_5= RULE_ID )
                    {
                    // InternalEJSL.g:1597:5: (otherlv_5= RULE_ID )
                    // InternalEJSL.g:1598:6: otherlv_5= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getStaticPageRule());
                    						}
                    					
                    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_56); 

                    						newLeafNode(otherlv_5, grammarAccess.getStaticPageAccess().getParametergroupsParameterGroupCrossReference_4_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:1609:4: (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==18) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // InternalEJSL.g:1610:5: otherlv_6= ',' ( (otherlv_7= RULE_ID ) )
                    	    {
                    	    otherlv_6=(Token)match(input,18,FOLLOW_25); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getStaticPageAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalEJSL.g:1614:5: ( (otherlv_7= RULE_ID ) )
                    	    // InternalEJSL.g:1615:6: (otherlv_7= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:1615:6: (otherlv_7= RULE_ID )
                    	    // InternalEJSL.g:1616:7: otherlv_7= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getStaticPageRule());
                    	    							}
                    	    						
                    	    otherlv_7=(Token)match(input,RULE_ID,FOLLOW_56); 

                    	    							newLeafNode(otherlv_7, grammarAccess.getStaticPageAccess().getParametergroupsParameterGroupCrossReference_4_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:1629:3: (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==54) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalEJSL.g:1630:4: otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    {
                    otherlv_8=(Token)match(input,54,FOLLOW_25); 

                    				newLeafNode(otherlv_8, grammarAccess.getStaticPageAccess().getGlobalparametersKeyword_5_0());
                    			
                    // InternalEJSL.g:1634:4: ( (otherlv_9= RULE_ID ) )
                    // InternalEJSL.g:1635:5: (otherlv_9= RULE_ID )
                    {
                    // InternalEJSL.g:1635:5: (otherlv_9= RULE_ID )
                    // InternalEJSL.g:1636:6: otherlv_9= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getStaticPageRule());
                    						}
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_57); 

                    						newLeafNode(otherlv_9, grammarAccess.getStaticPageAccess().getGlobalparametersParameterCrossReference_5_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:1647:4: (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==18) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // InternalEJSL.g:1648:5: otherlv_10= ',' ( (otherlv_11= RULE_ID ) )
                    	    {
                    	    otherlv_10=(Token)match(input,18,FOLLOW_25); 

                    	    					newLeafNode(otherlv_10, grammarAccess.getStaticPageAccess().getCommaKeyword_5_2_0());
                    	    				
                    	    // InternalEJSL.g:1652:5: ( (otherlv_11= RULE_ID ) )
                    	    // InternalEJSL.g:1653:6: (otherlv_11= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:1653:6: (otherlv_11= RULE_ID )
                    	    // InternalEJSL.g:1654:7: otherlv_11= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getStaticPageRule());
                    	    							}
                    	    						
                    	    otherlv_11=(Token)match(input,RULE_ID,FOLLOW_57); 

                    	    							newLeafNode(otherlv_11, grammarAccess.getStaticPageAccess().getGlobalparametersParameterCrossReference_5_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop42;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:1667:3: (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==55) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalEJSL.g:1668:4: otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}'
                    {
                    otherlv_12=(Token)match(input,55,FOLLOW_4); 

                    				newLeafNode(otherlv_12, grammarAccess.getStaticPageAccess().getLocalparametersKeyword_6_0());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_9); 

                    				newLeafNode(otherlv_13, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_6_1());
                    			
                    // InternalEJSL.g:1676:4: ( (lv_localparameters_14_0= ruleParameter ) )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==31) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // InternalEJSL.g:1677:5: (lv_localparameters_14_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:1677:5: (lv_localparameters_14_0= ruleParameter )
                    	    // InternalEJSL.g:1678:6: lv_localparameters_14_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getStaticPageAccess().getLocalparametersParameterParserRuleCall_6_2_0());
                    	    					
                    	    pushFollow(FOLLOW_9);
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
                    	    break loop44;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,19,FOLLOW_58); 

                    				newLeafNode(otherlv_15, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_6_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:1700:3: (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==56) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalEJSL.g:1701:4: otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,56,FOLLOW_4); 

                    				newLeafNode(otherlv_16, grammarAccess.getStaticPageAccess().getLinksKeyword_7_0());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_59); 

                    				newLeafNode(otherlv_17, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:1709:4: ( (lv_links_18_0= ruleLink ) )*
                    loop46:
                    do {
                        int alt46=2;
                        int LA46_0 = input.LA(1);

                        if ( (LA46_0==67||(LA46_0>=70 && LA46_0<=71)) ) {
                            alt46=1;
                        }


                        switch (alt46) {
                    	case 1 :
                    	    // InternalEJSL.g:1710:5: (lv_links_18_0= ruleLink )
                    	    {
                    	    // InternalEJSL.g:1710:5: (lv_links_18_0= ruleLink )
                    	    // InternalEJSL.g:1711:6: lv_links_18_0= ruleLink
                    	    {

                    	    						newCompositeNode(grammarAccess.getStaticPageAccess().getLinksLinkParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_59);
                    	    lv_links_18_0=ruleLink();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getStaticPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"links",
                    	    							lv_links_18_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Link");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop46;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,19,FOLLOW_60); 

                    				newLeafNode(otherlv_19, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            otherlv_20=(Token)match(input,57,FOLLOW_4); 

            			newLeafNode(otherlv_20, grammarAccess.getStaticPageAccess().getHTMLBodyKeyword_8());
            		
            otherlv_21=(Token)match(input,16,FOLLOW_3); 

            			newLeafNode(otherlv_21, grammarAccess.getStaticPageAccess().getLeftCurlyBracketKeyword_9());
            		
            // InternalEJSL.g:1741:3: ( (lv_HTMLBody_22_0= RULE_STRING ) )
            // InternalEJSL.g:1742:4: (lv_HTMLBody_22_0= RULE_STRING )
            {
            // InternalEJSL.g:1742:4: (lv_HTMLBody_22_0= RULE_STRING )
            // InternalEJSL.g:1743:5: lv_HTMLBody_22_0= RULE_STRING
            {
            lv_HTMLBody_22_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					newLeafNode(lv_HTMLBody_22_0, grammarAccess.getStaticPageAccess().getHTMLBodySTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStaticPageRule());
            					}
            					setWithLastConsumed(
            						current,
            						"HTMLBody",
            						lv_HTMLBody_22_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_23=(Token)match(input,19,FOLLOW_20); 

            			newLeafNode(otherlv_23, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_11());
            		
            otherlv_24=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_24, grammarAccess.getStaticPageAccess().getRightCurlyBracketKeyword_12());
            		

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
    // InternalEJSL.g:1771:1: entryRuleDynamicPage returns [EObject current=null] : iv_ruleDynamicPage= ruleDynamicPage EOF ;
    public final EObject entryRuleDynamicPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDynamicPage = null;


        try {
            // InternalEJSL.g:1771:52: (iv_ruleDynamicPage= ruleDynamicPage EOF )
            // InternalEJSL.g:1772:2: iv_ruleDynamicPage= ruleDynamicPage EOF
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
    // InternalEJSL.g:1778:1: ruleDynamicPage returns [EObject current=null] : (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage ) ;
    public final EObject ruleDynamicPage() throws RecognitionException {
        EObject current = null;

        EObject this_IndexPage_0 = null;

        EObject this_DetailsPage_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1784:2: ( (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage ) )
            // InternalEJSL.g:1785:2: (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage )
            {
            // InternalEJSL.g:1785:2: (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==58) ) {
                alt48=1;
            }
            else if ( (LA48_0==62) ) {
                alt48=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // InternalEJSL.g:1786:3: this_IndexPage_0= ruleIndexPage
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
                    // InternalEJSL.g:1795:3: this_DetailsPage_1= ruleDetailsPage
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
    // InternalEJSL.g:1807:1: entryRuleIndexPage returns [EObject current=null] : iv_ruleIndexPage= ruleIndexPage EOF ;
    public final EObject entryRuleIndexPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIndexPage = null;


        try {
            // InternalEJSL.g:1807:50: (iv_ruleIndexPage= ruleIndexPage EOF )
            // InternalEJSL.g:1808:2: iv_ruleIndexPage= ruleIndexPage EOF
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
    // InternalEJSL.g:1814:1: ruleIndexPage returns [EObject current=null] : ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}' ) ;
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
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_30=null;
        Token otherlv_31=null;
        Token otherlv_33=null;
        Token otherlv_34=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_localparameters_18_0 = null;

        EObject lv_links_32_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:1820:2: ( ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}' ) )
            // InternalEJSL.g:1821:2: ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}' )
            {
            // InternalEJSL.g:1821:2: ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}' )
            // InternalEJSL.g:1822:3: () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}'
            {
            // InternalEJSL.g:1822:3: ()
            // InternalEJSL.g:1823:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getIndexPageAccess().getIndexPageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,58,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getIndexPageAccess().getIndexPageKeyword_1());
            		
            // InternalEJSL.g:1833:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:1834:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:1834:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:1835:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_61); 

            			newLeafNode(otherlv_3, grammarAccess.getIndexPageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:1856:3: (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==59) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalEJSL.g:1857:4: otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_4=(Token)match(input,59,FOLLOW_40); 

                    				newLeafNode(otherlv_4, grammarAccess.getIndexPageAccess().getEntitiesKeyword_4_0());
                    			
                    // InternalEJSL.g:1861:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:1862:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:1862:5: ( ruleQualifiedName )
                    // InternalEJSL.g:1863:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getIndexPageAccess().getEntitiesEntityCrossReference_4_1_0());
                    					
                    pushFollow(FOLLOW_62);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:1877:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==18) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // InternalEJSL.g:1878:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,18,FOLLOW_40); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getIndexPageAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalEJSL.g:1882:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:1883:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:1883:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:1884:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getIndexPageAccess().getEntitiesEntityCrossReference_4_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_62);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:1900:3: (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==53) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalEJSL.g:1901:4: otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    {
                    otherlv_8=(Token)match(input,53,FOLLOW_25); 

                    				newLeafNode(otherlv_8, grammarAccess.getIndexPageAccess().getParameterGroupsKeyword_5_0());
                    			
                    // InternalEJSL.g:1905:4: ( (otherlv_9= RULE_ID ) )
                    // InternalEJSL.g:1906:5: (otherlv_9= RULE_ID )
                    {
                    // InternalEJSL.g:1906:5: (otherlv_9= RULE_ID )
                    // InternalEJSL.g:1907:6: otherlv_9= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_63); 

                    						newLeafNode(otherlv_9, grammarAccess.getIndexPageAccess().getParametergroupsParameterGroupCrossReference_5_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:1918:4: (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==18) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // InternalEJSL.g:1919:5: otherlv_10= ',' ( (otherlv_11= RULE_ID ) )
                    	    {
                    	    otherlv_10=(Token)match(input,18,FOLLOW_25); 

                    	    					newLeafNode(otherlv_10, grammarAccess.getIndexPageAccess().getCommaKeyword_5_2_0());
                    	    				
                    	    // InternalEJSL.g:1923:5: ( (otherlv_11= RULE_ID ) )
                    	    // InternalEJSL.g:1924:6: (otherlv_11= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:1924:6: (otherlv_11= RULE_ID )
                    	    // InternalEJSL.g:1925:7: otherlv_11= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						
                    	    otherlv_11=(Token)match(input,RULE_ID,FOLLOW_63); 

                    	    							newLeafNode(otherlv_11, grammarAccess.getIndexPageAccess().getParametergroupsParameterGroupCrossReference_5_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop51;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:1938:3: (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==54) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalEJSL.g:1939:4: otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    {
                    otherlv_12=(Token)match(input,54,FOLLOW_25); 

                    				newLeafNode(otherlv_12, grammarAccess.getIndexPageAccess().getGlobalparametersKeyword_6_0());
                    			
                    // InternalEJSL.g:1943:4: ( (otherlv_13= RULE_ID ) )
                    // InternalEJSL.g:1944:5: (otherlv_13= RULE_ID )
                    {
                    // InternalEJSL.g:1944:5: (otherlv_13= RULE_ID )
                    // InternalEJSL.g:1945:6: otherlv_13= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_64); 

                    						newLeafNode(otherlv_13, grammarAccess.getIndexPageAccess().getGlobalparametersParameterCrossReference_6_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:1956:4: (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==18) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // InternalEJSL.g:1957:5: otherlv_14= ',' ( (otherlv_15= RULE_ID ) )
                    	    {
                    	    otherlv_14=(Token)match(input,18,FOLLOW_25); 

                    	    					newLeafNode(otherlv_14, grammarAccess.getIndexPageAccess().getCommaKeyword_6_2_0());
                    	    				
                    	    // InternalEJSL.g:1961:5: ( (otherlv_15= RULE_ID ) )
                    	    // InternalEJSL.g:1962:6: (otherlv_15= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:1962:6: (otherlv_15= RULE_ID )
                    	    // InternalEJSL.g:1963:7: otherlv_15= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_64); 

                    	    							newLeafNode(otherlv_15, grammarAccess.getIndexPageAccess().getGlobalparametersParameterCrossReference_6_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop53;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:1976:3: (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==55) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalEJSL.g:1977:4: otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,55,FOLLOW_4); 

                    				newLeafNode(otherlv_16, grammarAccess.getIndexPageAccess().getLocalparametersKeyword_7_0());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_9); 

                    				newLeafNode(otherlv_17, grammarAccess.getIndexPageAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:1985:4: ( (lv_localparameters_18_0= ruleParameter ) )*
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==31) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // InternalEJSL.g:1986:5: (lv_localparameters_18_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:1986:5: (lv_localparameters_18_0= ruleParameter )
                    	    // InternalEJSL.g:1987:6: lv_localparameters_18_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getIndexPageAccess().getLocalparametersParameterParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_9);
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
                    	    break loop55;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,19,FOLLOW_65); 

                    				newLeafNode(otherlv_19, grammarAccess.getIndexPageAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2009:3: (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==60) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalEJSL.g:2010:4: otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_20=(Token)match(input,60,FOLLOW_23); 

                    				newLeafNode(otherlv_20, grammarAccess.getIndexPageAccess().getTableColumnsKeyword_8_0());
                    			
                    otherlv_21=(Token)match(input,28,FOLLOW_40); 

                    				newLeafNode(otherlv_21, grammarAccess.getIndexPageAccess().getEqualsSignKeyword_8_1());
                    			
                    // InternalEJSL.g:2018:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2019:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2019:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2020:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getIndexPageAccess().getTablecolumnsAttributeCrossReference_8_2_0());
                    					
                    pushFollow(FOLLOW_66);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2034:4: (otherlv_23= ',' ( ( ruleQualifiedName ) ) )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==18) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // InternalEJSL.g:2035:5: otherlv_23= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_23=(Token)match(input,18,FOLLOW_40); 

                    	    					newLeafNode(otherlv_23, grammarAccess.getIndexPageAccess().getCommaKeyword_8_3_0());
                    	    				
                    	    // InternalEJSL.g:2039:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2040:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2040:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2041:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getIndexPageAccess().getTablecolumnsAttributeCrossReference_8_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_66);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:2057:3: (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==61) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalEJSL.g:2058:4: otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_25=(Token)match(input,61,FOLLOW_23); 

                    				newLeafNode(otherlv_25, grammarAccess.getIndexPageAccess().getFiltersKeyword_9_0());
                    			
                    otherlv_26=(Token)match(input,28,FOLLOW_40); 

                    				newLeafNode(otherlv_26, grammarAccess.getIndexPageAccess().getEqualsSignKeyword_9_1());
                    			
                    // InternalEJSL.g:2066:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2067:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2067:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2068:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getIndexPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getIndexPageAccess().getFiltersAttributeCrossReference_9_2_0());
                    					
                    pushFollow(FOLLOW_67);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2082:4: (otherlv_28= ',' ( ( ruleQualifiedName ) ) )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==18) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // InternalEJSL.g:2083:5: otherlv_28= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_28=(Token)match(input,18,FOLLOW_40); 

                    	    					newLeafNode(otherlv_28, grammarAccess.getIndexPageAccess().getCommaKeyword_9_3_0());
                    	    				
                    	    // InternalEJSL.g:2087:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2088:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2088:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2089:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getIndexPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getIndexPageAccess().getFiltersAttributeCrossReference_9_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_67);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop59;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:2105:3: (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==56) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalEJSL.g:2106:4: otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}'
                    {
                    otherlv_30=(Token)match(input,56,FOLLOW_4); 

                    				newLeafNode(otherlv_30, grammarAccess.getIndexPageAccess().getLinksKeyword_10_0());
                    			
                    otherlv_31=(Token)match(input,16,FOLLOW_59); 

                    				newLeafNode(otherlv_31, grammarAccess.getIndexPageAccess().getLeftCurlyBracketKeyword_10_1());
                    			
                    // InternalEJSL.g:2114:4: ( (lv_links_32_0= ruleLink ) )*
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==67||(LA61_0>=70 && LA61_0<=71)) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // InternalEJSL.g:2115:5: (lv_links_32_0= ruleLink )
                    	    {
                    	    // InternalEJSL.g:2115:5: (lv_links_32_0= ruleLink )
                    	    // InternalEJSL.g:2116:6: lv_links_32_0= ruleLink
                    	    {

                    	    						newCompositeNode(grammarAccess.getIndexPageAccess().getLinksLinkParserRuleCall_10_2_0());
                    	    					
                    	    pushFollow(FOLLOW_59);
                    	    lv_links_32_0=ruleLink();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getIndexPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"links",
                    	    							lv_links_32_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Link");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop61;
                        }
                    } while (true);

                    otherlv_33=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_33, grammarAccess.getIndexPageAccess().getRightCurlyBracketKeyword_10_3());
                    			

                    }
                    break;

            }

            otherlv_34=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_34, grammarAccess.getIndexPageAccess().getRightCurlyBracketKeyword_11());
            		

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
    // InternalEJSL.g:2146:1: entryRuleDetailsPage returns [EObject current=null] : iv_ruleDetailsPage= ruleDetailsPage EOF ;
    public final EObject entryRuleDetailsPage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDetailsPage = null;


        try {
            // InternalEJSL.g:2146:52: (iv_ruleDetailsPage= ruleDetailsPage EOF )
            // InternalEJSL.g:2147:2: iv_ruleDetailsPage= ruleDetailsPage EOF
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
    // InternalEJSL.g:2153:1: ruleDetailsPage returns [EObject current=null] : ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}' ) ;
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
        Token otherlv_29=null;
        Token otherlv_30=null;
        Token otherlv_32=null;
        Token otherlv_34=null;
        Token otherlv_35=null;
        Token otherlv_37=null;
        Token otherlv_38=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_editfields_10_0 = null;

        EObject lv_localparameters_22_0 = null;

        EObject lv_links_36_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2159:2: ( ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}' ) )
            // InternalEJSL.g:2160:2: ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}' )
            {
            // InternalEJSL.g:2160:2: ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}' )
            // InternalEJSL.g:2161:3: () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}'
            {
            // InternalEJSL.g:2161:3: ()
            // InternalEJSL.g:2162:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDetailsPageAccess().getDetailsPageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,62,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getDetailsPageAccess().getDetailsPageKeyword_1());
            		
            // InternalEJSL.g:2172:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:2173:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:2173:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:2174:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_68); 

            			newLeafNode(otherlv_3, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:2195:3: (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==59) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // InternalEJSL.g:2196:4: otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_4=(Token)match(input,59,FOLLOW_40); 

                    				newLeafNode(otherlv_4, grammarAccess.getDetailsPageAccess().getEntitiesKeyword_4_0());
                    			
                    // InternalEJSL.g:2200:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2201:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2201:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2202:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getDetailsPageAccess().getEntitiesEntityCrossReference_4_1_0());
                    					
                    pushFollow(FOLLOW_69);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2216:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==18) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // InternalEJSL.g:2217:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,18,FOLLOW_40); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getDetailsPageAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalEJSL.g:2221:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2222:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2222:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2223:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getDetailsPageAccess().getEntitiesEntityCrossReference_4_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_69);
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

            // InternalEJSL.g:2239:3: (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==63) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // InternalEJSL.g:2240:4: otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,63,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getDetailsPageAccess().getEdit_fieldsKeyword_5_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_70); 

                    				newLeafNode(otherlv_9, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:2248:4: ( (lv_editfields_10_0= ruleDetailPageField ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==RULE_ID||LA65_0==124) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // InternalEJSL.g:2249:5: (lv_editfields_10_0= ruleDetailPageField )
                    	    {
                    	    // InternalEJSL.g:2249:5: (lv_editfields_10_0= ruleDetailPageField )
                    	    // InternalEJSL.g:2250:6: lv_editfields_10_0= ruleDetailPageField
                    	    {

                    	    						newCompositeNode(grammarAccess.getDetailsPageAccess().getEditfieldsDetailPageFieldParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_70);
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
                    	    break loop65;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_71); 

                    				newLeafNode(otherlv_11, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2272:3: (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==53) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalEJSL.g:2273:4: otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    {
                    otherlv_12=(Token)match(input,53,FOLLOW_25); 

                    				newLeafNode(otherlv_12, grammarAccess.getDetailsPageAccess().getParameterGroupsKeyword_6_0());
                    			
                    // InternalEJSL.g:2277:4: ( (otherlv_13= RULE_ID ) )
                    // InternalEJSL.g:2278:5: (otherlv_13= RULE_ID )
                    {
                    // InternalEJSL.g:2278:5: (otherlv_13= RULE_ID )
                    // InternalEJSL.g:2279:6: otherlv_13= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_63); 

                    						newLeafNode(otherlv_13, grammarAccess.getDetailsPageAccess().getParametergroupsParameterGroupCrossReference_6_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:2290:4: (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==18) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // InternalEJSL.g:2291:5: otherlv_14= ',' ( (otherlv_15= RULE_ID ) )
                    	    {
                    	    otherlv_14=(Token)match(input,18,FOLLOW_25); 

                    	    					newLeafNode(otherlv_14, grammarAccess.getDetailsPageAccess().getCommaKeyword_6_2_0());
                    	    				
                    	    // InternalEJSL.g:2295:5: ( (otherlv_15= RULE_ID ) )
                    	    // InternalEJSL.g:2296:6: (otherlv_15= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:2296:6: (otherlv_15= RULE_ID )
                    	    // InternalEJSL.g:2297:7: otherlv_15= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_63); 

                    	    							newLeafNode(otherlv_15, grammarAccess.getDetailsPageAccess().getParametergroupsParameterGroupCrossReference_6_2_1_0());
                    	    						

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

            // InternalEJSL.g:2310:3: (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==54) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalEJSL.g:2311:4: otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )*
                    {
                    otherlv_16=(Token)match(input,54,FOLLOW_25); 

                    				newLeafNode(otherlv_16, grammarAccess.getDetailsPageAccess().getGlobalparametersKeyword_7_0());
                    			
                    // InternalEJSL.g:2315:4: ( (otherlv_17= RULE_ID ) )
                    // InternalEJSL.g:2316:5: (otherlv_17= RULE_ID )
                    {
                    // InternalEJSL.g:2316:5: (otherlv_17= RULE_ID )
                    // InternalEJSL.g:2317:6: otherlv_17= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					
                    otherlv_17=(Token)match(input,RULE_ID,FOLLOW_64); 

                    						newLeafNode(otherlv_17, grammarAccess.getDetailsPageAccess().getGlobalparametersParameterCrossReference_7_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:2328:4: (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==18) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // InternalEJSL.g:2329:5: otherlv_18= ',' ( (otherlv_19= RULE_ID ) )
                    	    {
                    	    otherlv_18=(Token)match(input,18,FOLLOW_25); 

                    	    					newLeafNode(otherlv_18, grammarAccess.getDetailsPageAccess().getCommaKeyword_7_2_0());
                    	    				
                    	    // InternalEJSL.g:2333:5: ( (otherlv_19= RULE_ID ) )
                    	    // InternalEJSL.g:2334:6: (otherlv_19= RULE_ID )
                    	    {
                    	    // InternalEJSL.g:2334:6: (otherlv_19= RULE_ID )
                    	    // InternalEJSL.g:2335:7: otherlv_19= RULE_ID
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						
                    	    otherlv_19=(Token)match(input,RULE_ID,FOLLOW_64); 

                    	    							newLeafNode(otherlv_19, grammarAccess.getDetailsPageAccess().getGlobalparametersParameterCrossReference_7_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop69;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:2348:3: (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==55) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalEJSL.g:2349:4: otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}'
                    {
                    otherlv_20=(Token)match(input,55,FOLLOW_4); 

                    				newLeafNode(otherlv_20, grammarAccess.getDetailsPageAccess().getLocalparametersKeyword_8_0());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_9); 

                    				newLeafNode(otherlv_21, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:2357:4: ( (lv_localparameters_22_0= ruleParameter ) )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==31) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // InternalEJSL.g:2358:5: (lv_localparameters_22_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:2358:5: (lv_localparameters_22_0= ruleParameter )
                    	    // InternalEJSL.g:2359:6: lv_localparameters_22_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getDetailsPageAccess().getLocalparametersParameterParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_9);
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
                    	    break loop71;
                        }
                    } while (true);

                    otherlv_23=(Token)match(input,19,FOLLOW_65); 

                    				newLeafNode(otherlv_23, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:2381:3: (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==60) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalEJSL.g:2382:4: otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_24=(Token)match(input,60,FOLLOW_23); 

                    				newLeafNode(otherlv_24, grammarAccess.getDetailsPageAccess().getTableColumnsKeyword_9_0());
                    			
                    otherlv_25=(Token)match(input,28,FOLLOW_40); 

                    				newLeafNode(otherlv_25, grammarAccess.getDetailsPageAccess().getEqualsSignKeyword_9_1());
                    			
                    // InternalEJSL.g:2390:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2391:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2391:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2392:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getDetailsPageAccess().getTablecolumnsAttributeCrossReference_9_2_0());
                    					
                    pushFollow(FOLLOW_66);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2406:4: (otherlv_27= ',' ( ( ruleQualifiedName ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==18) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // InternalEJSL.g:2407:5: otherlv_27= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_27=(Token)match(input,18,FOLLOW_40); 

                    	    					newLeafNode(otherlv_27, grammarAccess.getDetailsPageAccess().getCommaKeyword_9_3_0());
                    	    				
                    	    // InternalEJSL.g:2411:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2412:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2412:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2413:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getDetailsPageAccess().getTablecolumnsAttributeCrossReference_9_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_66);
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

            // InternalEJSL.g:2429:3: (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==61) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalEJSL.g:2430:4: otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_29=(Token)match(input,61,FOLLOW_23); 

                    				newLeafNode(otherlv_29, grammarAccess.getDetailsPageAccess().getFiltersKeyword_10_0());
                    			
                    otherlv_30=(Token)match(input,28,FOLLOW_40); 

                    				newLeafNode(otherlv_30, grammarAccess.getDetailsPageAccess().getEqualsSignKeyword_10_1());
                    			
                    // InternalEJSL.g:2438:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2439:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2439:5: ( ruleQualifiedName )
                    // InternalEJSL.g:2440:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDetailsPageRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getDetailsPageAccess().getFiltersAttributeCrossReference_10_2_0());
                    					
                    pushFollow(FOLLOW_67);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:2454:4: (otherlv_32= ',' ( ( ruleQualifiedName ) ) )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==18) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // InternalEJSL.g:2455:5: otherlv_32= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_32=(Token)match(input,18,FOLLOW_40); 

                    	    					newLeafNode(otherlv_32, grammarAccess.getDetailsPageAccess().getCommaKeyword_10_3_0());
                    	    				
                    	    // InternalEJSL.g:2459:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:2460:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:2460:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:2461:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getDetailsPageRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getDetailsPageAccess().getFiltersAttributeCrossReference_10_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_67);
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

            // InternalEJSL.g:2477:3: (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==56) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalEJSL.g:2478:4: otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}'
                    {
                    otherlv_34=(Token)match(input,56,FOLLOW_4); 

                    				newLeafNode(otherlv_34, grammarAccess.getDetailsPageAccess().getLinksKeyword_11_0());
                    			
                    otherlv_35=(Token)match(input,16,FOLLOW_59); 

                    				newLeafNode(otherlv_35, grammarAccess.getDetailsPageAccess().getLeftCurlyBracketKeyword_11_1());
                    			
                    // InternalEJSL.g:2486:4: ( (lv_links_36_0= ruleLink ) )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==67||(LA77_0>=70 && LA77_0<=71)) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // InternalEJSL.g:2487:5: (lv_links_36_0= ruleLink )
                    	    {
                    	    // InternalEJSL.g:2487:5: (lv_links_36_0= ruleLink )
                    	    // InternalEJSL.g:2488:6: lv_links_36_0= ruleLink
                    	    {

                    	    						newCompositeNode(grammarAccess.getDetailsPageAccess().getLinksLinkParserRuleCall_11_2_0());
                    	    					
                    	    pushFollow(FOLLOW_59);
                    	    lv_links_36_0=ruleLink();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getDetailsPageRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"links",
                    	    							lv_links_36_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Link");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop77;
                        }
                    } while (true);

                    otherlv_37=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_37, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_11_3());
                    			

                    }
                    break;

            }

            otherlv_38=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_38, grammarAccess.getDetailsPageAccess().getRightCurlyBracketKeyword_12());
            		

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
    // InternalEJSL.g:2518:1: entryRuleDetailPageField returns [EObject current=null] : iv_ruleDetailPageField= ruleDetailPageField EOF ;
    public final EObject entryRuleDetailPageField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDetailPageField = null;


        try {
            // InternalEJSL.g:2518:56: (iv_ruleDetailPageField= ruleDetailPageField EOF )
            // InternalEJSL.g:2519:2: iv_ruleDetailPageField= ruleDetailPageField EOF
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
    // InternalEJSL.g:2525:1: ruleDetailPageField returns [EObject current=null] : ( () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) ) ;
    public final EObject ruleDetailPageField() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_htmltype_3_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2531:2: ( ( () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) ) )
            // InternalEJSL.g:2532:2: ( () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )
            {
            // InternalEJSL.g:2532:2: ( () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )
            // InternalEJSL.g:2533:3: () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) )
            {
            // InternalEJSL.g:2533:3: ()
            // InternalEJSL.g:2534:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getDetailPageFieldAccess().getDetailPageFieldAction_0(),
            					current);
            			

            }

            // InternalEJSL.g:2540:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:2541:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:2541:4: ( ruleQualifiedName )
            // InternalEJSL.g:2542:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDetailPageFieldRule());
            					}
            				

            					newCompositeNode(grammarAccess.getDetailPageFieldAccess().getAttributeAttributeCrossReference_1_0());
            				
            pushFollow(FOLLOW_72);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,64,FOLLOW_73); 

            			newLeafNode(otherlv_2, grammarAccess.getDetailPageFieldAccess().getColonKeyword_2());
            		
            // InternalEJSL.g:2560:3: ( (lv_htmltype_3_0= ruleHTMLTypes ) )
            // InternalEJSL.g:2561:4: (lv_htmltype_3_0= ruleHTMLTypes )
            {
            // InternalEJSL.g:2561:4: (lv_htmltype_3_0= ruleHTMLTypes )
            // InternalEJSL.g:2562:5: lv_htmltype_3_0= ruleHTMLTypes
            {

            					newCompositeNode(grammarAccess.getDetailPageFieldAccess().getHtmltypeHTMLTypesParserRuleCall_3_0());
            				
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


    // $ANTLR start "entryRuleHTMLTypes"
    // InternalEJSL.g:2583:1: entryRuleHTMLTypes returns [EObject current=null] : iv_ruleHTMLTypes= ruleHTMLTypes EOF ;
    public final EObject entryRuleHTMLTypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHTMLTypes = null;


        try {
            // InternalEJSL.g:2583:50: (iv_ruleHTMLTypes= ruleHTMLTypes EOF )
            // InternalEJSL.g:2584:2: iv_ruleHTMLTypes= ruleHTMLTypes EOF
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
    // InternalEJSL.g:2590:1: ruleHTMLTypes returns [EObject current=null] : (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes ) ;
    public final EObject ruleHTMLTypes() throws RecognitionException {
        EObject current = null;

        EObject this_SimpleHTMLTypes_0 = null;

        EObject this_ComplexHTMLTypes_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2596:2: ( (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes ) )
            // InternalEJSL.g:2597:2: (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes )
            {
            // InternalEJSL.g:2597:2: (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==137||LA79_0==139||(LA79_0>=150 && LA79_0<=156)) ) {
                alt79=1;
            }
            else if ( ((LA79_0>=157 && LA79_0<=159)) ) {
                alt79=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // InternalEJSL.g:2598:3: this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes
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
                    // InternalEJSL.g:2607:3: this_ComplexHTMLTypes_1= ruleComplexHTMLTypes
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
    // InternalEJSL.g:2619:1: entryRuleSimpleHTMLTypes returns [EObject current=null] : iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF ;
    public final EObject entryRuleSimpleHTMLTypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleHTMLTypes = null;


        try {
            // InternalEJSL.g:2619:56: (iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF )
            // InternalEJSL.g:2620:2: iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF
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
    // InternalEJSL.g:2626:1: ruleSimpleHTMLTypes returns [EObject current=null] : ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) ) ;
    public final EObject ruleSimpleHTMLTypes() throws RecognitionException {
        EObject current = null;

        Enumerator lv_htmltype_0_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2632:2: ( ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) ) )
            // InternalEJSL.g:2633:2: ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) )
            {
            // InternalEJSL.g:2633:2: ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) )
            // InternalEJSL.g:2634:3: (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds )
            {
            // InternalEJSL.g:2634:3: (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds )
            // InternalEJSL.g:2635:4: lv_htmltype_0_0= ruleSimpleHTMLTypeKinds
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
    // InternalEJSL.g:2655:1: entryRuleComplexHTMLTypes returns [EObject current=null] : iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF ;
    public final EObject entryRuleComplexHTMLTypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComplexHTMLTypes = null;


        try {
            // InternalEJSL.g:2655:57: (iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF )
            // InternalEJSL.g:2656:2: iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF
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
    // InternalEJSL.g:2662:1: ruleComplexHTMLTypes returns [EObject current=null] : ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' ) ;
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
            // InternalEJSL.g:2668:2: ( ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' ) )
            // InternalEJSL.g:2669:2: ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' )
            {
            // InternalEJSL.g:2669:2: ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' )
            // InternalEJSL.g:2670:3: ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')'
            {
            // InternalEJSL.g:2670:3: ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) )
            // InternalEJSL.g:2671:4: (lv_htmltype_0_0= ruleComplexHTMLTypeKinds )
            {
            // InternalEJSL.g:2671:4: (lv_htmltype_0_0= ruleComplexHTMLTypeKinds )
            // InternalEJSL.g:2672:5: lv_htmltype_0_0= ruleComplexHTMLTypeKinds
            {

            					newCompositeNode(grammarAccess.getComplexHTMLTypesAccess().getHtmltypeComplexHTMLTypeKindsEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_74);
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

            otherlv_1=(Token)match(input,65,FOLLOW_75); 

            			newLeafNode(otherlv_1, grammarAccess.getComplexHTMLTypesAccess().getLeftParenthesisKeyword_1());
            		
            // InternalEJSL.g:2693:3: ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) )
            // InternalEJSL.g:2694:4: (lv_keyvaluepairs_2_0= ruleKeyValuePair )
            {
            // InternalEJSL.g:2694:4: (lv_keyvaluepairs_2_0= ruleKeyValuePair )
            // InternalEJSL.g:2695:5: lv_keyvaluepairs_2_0= ruleKeyValuePair
            {

            					newCompositeNode(grammarAccess.getComplexHTMLTypesAccess().getKeyvaluepairsKeyValuePairParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_76);
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

            // InternalEJSL.g:2712:3: (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==18) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // InternalEJSL.g:2713:4: otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) )
            	    {
            	    otherlv_3=(Token)match(input,18,FOLLOW_75); 

            	    				newLeafNode(otherlv_3, grammarAccess.getComplexHTMLTypesAccess().getCommaKeyword_3_0());
            	    			
            	    // InternalEJSL.g:2717:4: ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) )
            	    // InternalEJSL.g:2718:5: (lv_keyvaluepairs_4_0= ruleKeyValuePair )
            	    {
            	    // InternalEJSL.g:2718:5: (lv_keyvaluepairs_4_0= ruleKeyValuePair )
            	    // InternalEJSL.g:2719:6: lv_keyvaluepairs_4_0= ruleKeyValuePair
            	    {

            	    						newCompositeNode(grammarAccess.getComplexHTMLTypesAccess().getKeyvaluepairsKeyValuePairParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_76);
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
            	    break loop80;
                }
            } while (true);

            otherlv_5=(Token)match(input,66,FOLLOW_2); 

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
    // InternalEJSL.g:2745:1: entryRuleLink returns [EObject current=null] : iv_ruleLink= ruleLink EOF ;
    public final EObject entryRuleLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLink = null;


        try {
            // InternalEJSL.g:2745:45: (iv_ruleLink= ruleLink EOF )
            // InternalEJSL.g:2746:2: iv_ruleLink= ruleLink EOF
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
    // InternalEJSL.g:2752:1: ruleLink returns [EObject current=null] : (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink ) ;
    public final EObject ruleLink() throws RecognitionException {
        EObject current = null;

        EObject this_ExternalLink_0 = null;

        EObject this_InternalLink_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2758:2: ( (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink ) )
            // InternalEJSL.g:2759:2: (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink )
            {
            // InternalEJSL.g:2759:2: (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==67) ) {
                alt81=1;
            }
            else if ( ((LA81_0>=70 && LA81_0<=71)) ) {
                alt81=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // InternalEJSL.g:2760:3: this_ExternalLink_0= ruleExternalLink
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
                    // InternalEJSL.g:2769:3: this_InternalLink_1= ruleInternalLink
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
    // InternalEJSL.g:2781:1: entryRuleExternalLink returns [EObject current=null] : iv_ruleExternalLink= ruleExternalLink EOF ;
    public final EObject entryRuleExternalLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExternalLink = null;


        try {
            // InternalEJSL.g:2781:53: (iv_ruleExternalLink= ruleExternalLink EOF )
            // InternalEJSL.g:2782:2: iv_ruleExternalLink= ruleExternalLink EOF
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
    // InternalEJSL.g:2788:1: ruleExternalLink returns [EObject current=null] : ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' ) ;
    public final EObject ruleExternalLink() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_target_5_0=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token lv_label_11_0=null;
        Token otherlv_12=null;


        	enterRule();

        try {
            // InternalEJSL.g:2794:2: ( ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' ) )
            // InternalEJSL.g:2795:2: ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' )
            {
            // InternalEJSL.g:2795:2: ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' )
            // InternalEJSL.g:2796:3: () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}'
            {
            // InternalEJSL.g:2796:3: ()
            // InternalEJSL.g:2797:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExternalLinkAccess().getExternalLinkAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,67,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getExternalLinkAccess().getExternalLinkKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_77); 

            			newLeafNode(otherlv_2, grammarAccess.getExternalLinkAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,68,FOLLOW_23); 

            			newLeafNode(otherlv_3, grammarAccess.getExternalLinkAccess().getTargetKeyword_3());
            		
            otherlv_4=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getExternalLinkAccess().getEqualsSignKeyword_4());
            		
            // InternalEJSL.g:2819:3: ( (lv_target_5_0= RULE_STRING ) )
            // InternalEJSL.g:2820:4: (lv_target_5_0= RULE_STRING )
            {
            // InternalEJSL.g:2820:4: (lv_target_5_0= RULE_STRING )
            // InternalEJSL.g:2821:5: lv_target_5_0= RULE_STRING
            {
            lv_target_5_0=(Token)match(input,RULE_STRING,FOLLOW_78); 

            					newLeafNode(lv_target_5_0, grammarAccess.getExternalLinkAccess().getTargetSTRINGTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExternalLinkRule());
            					}
            					setWithLastConsumed(
            						current,
            						"target",
            						lv_target_5_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_6=(Token)match(input,69,FOLLOW_23); 

            			newLeafNode(otherlv_6, grammarAccess.getExternalLinkAccess().getLinkedAttributeKeyword_6());
            		
            otherlv_7=(Token)match(input,28,FOLLOW_40); 

            			newLeafNode(otherlv_7, grammarAccess.getExternalLinkAccess().getEqualsSignKeyword_7());
            		
            // InternalEJSL.g:2845:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:2846:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:2846:4: ( ruleQualifiedName )
            // InternalEJSL.g:2847:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getExternalLinkRule());
            					}
            				

            					newCompositeNode(grammarAccess.getExternalLinkAccess().getLinkedAttributeAttributeCrossReference_8_0());
            				
            pushFollow(FOLLOW_79);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:2861:3: (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==34) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // InternalEJSL.g:2862:4: otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) )
                    {
                    otherlv_9=(Token)match(input,34,FOLLOW_23); 

                    				newLeafNode(otherlv_9, grammarAccess.getExternalLinkAccess().getLabelKeyword_9_0());
                    			
                    otherlv_10=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_10, grammarAccess.getExternalLinkAccess().getEqualsSignKeyword_9_1());
                    			
                    // InternalEJSL.g:2870:4: ( (lv_label_11_0= RULE_STRING ) )
                    // InternalEJSL.g:2871:5: (lv_label_11_0= RULE_STRING )
                    {
                    // InternalEJSL.g:2871:5: (lv_label_11_0= RULE_STRING )
                    // InternalEJSL.g:2872:6: lv_label_11_0= RULE_STRING
                    {
                    lv_label_11_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

                    						newLeafNode(lv_label_11_0, grammarAccess.getExternalLinkAccess().getLabelSTRINGTerminalRuleCall_9_2_0());
                    					

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

            otherlv_12=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_12, grammarAccess.getExternalLinkAccess().getRightCurlyBracketKeyword_10());
            		

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
    // InternalEJSL.g:2897:1: entryRuleInternalLink returns [EObject current=null] : iv_ruleInternalLink= ruleInternalLink EOF ;
    public final EObject entryRuleInternalLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInternalLink = null;


        try {
            // InternalEJSL.g:2897:53: (iv_ruleInternalLink= ruleInternalLink EOF )
            // InternalEJSL.g:2898:2: iv_ruleInternalLink= ruleInternalLink EOF
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
    // InternalEJSL.g:2904:1: ruleInternalLink returns [EObject current=null] : ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink ) ;
    public final EObject ruleInternalLink() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject this_ContextLink_11 = null;



        	enterRule();

        try {
            // InternalEJSL.g:2910:2: ( ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink ) )
            // InternalEJSL.g:2911:2: ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink )
            {
            // InternalEJSL.g:2911:2: ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==70) ) {
                alt83=1;
            }
            else if ( (LA83_0==71) ) {
                alt83=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }
            switch (alt83) {
                case 1 :
                    // InternalEJSL.g:2912:3: ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) )
                    {
                    // InternalEJSL.g:2912:3: ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) )
                    // InternalEJSL.g:2913:4: () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' )
                    {
                    // InternalEJSL.g:2913:4: ()
                    // InternalEJSL.g:2914:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getInternalLinkAccess().getInternalLinkAction_0_0(),
                    						current);
                    				

                    }

                    // InternalEJSL.g:2920:4: (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' )
                    // InternalEJSL.g:2921:5: otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}'
                    {
                    otherlv_1=(Token)match(input,70,FOLLOW_40); 

                    					newLeafNode(otherlv_1, grammarAccess.getInternalLinkAccess().getInternalLinkKeyword_0_1_0());
                    				
                    // InternalEJSL.g:2925:5: ( (lv_name_2_0= ruleMYID ) )
                    // InternalEJSL.g:2926:6: (lv_name_2_0= ruleMYID )
                    {
                    // InternalEJSL.g:2926:6: (lv_name_2_0= ruleMYID )
                    // InternalEJSL.g:2927:7: lv_name_2_0= ruleMYID
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

                    otherlv_3=(Token)match(input,16,FOLLOW_77); 

                    					newLeafNode(otherlv_3, grammarAccess.getInternalLinkAccess().getLeftCurlyBracketKeyword_0_1_2());
                    				
                    otherlv_4=(Token)match(input,68,FOLLOW_23); 

                    					newLeafNode(otherlv_4, grammarAccess.getInternalLinkAccess().getTargetKeyword_0_1_3());
                    				
                    otherlv_5=(Token)match(input,28,FOLLOW_40); 

                    					newLeafNode(otherlv_5, grammarAccess.getInternalLinkAccess().getEqualsSignKeyword_0_1_4());
                    				
                    // InternalEJSL.g:2956:5: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2957:6: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2957:6: ( ruleQualifiedName )
                    // InternalEJSL.g:2958:7: ruleQualifiedName
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getInternalLinkRule());
                    							}
                    						

                    							newCompositeNode(grammarAccess.getInternalLinkAccess().getTargetPageCrossReference_0_1_5_0());
                    						
                    pushFollow(FOLLOW_78);
                    ruleQualifiedName();

                    state._fsp--;


                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    otherlv_7=(Token)match(input,69,FOLLOW_23); 

                    					newLeafNode(otherlv_7, grammarAccess.getInternalLinkAccess().getLinkedAttributeKeyword_0_1_6());
                    				
                    otherlv_8=(Token)match(input,28,FOLLOW_40); 

                    					newLeafNode(otherlv_8, grammarAccess.getInternalLinkAccess().getEqualsSignKeyword_0_1_7());
                    				
                    // InternalEJSL.g:2980:5: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:2981:6: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:2981:6: ( ruleQualifiedName )
                    // InternalEJSL.g:2982:7: ruleQualifiedName
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getInternalLinkRule());
                    							}
                    						

                    							newCompositeNode(grammarAccess.getInternalLinkAccess().getLinkedAttributeAttributeCrossReference_0_1_8_0());
                    						
                    pushFollow(FOLLOW_20);
                    ruleQualifiedName();

                    state._fsp--;


                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    otherlv_10=(Token)match(input,19,FOLLOW_2); 

                    					newLeafNode(otherlv_10, grammarAccess.getInternalLinkAccess().getRightCurlyBracketKeyword_0_1_9());
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:3003:3: this_ContextLink_11= ruleContextLink
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
    // InternalEJSL.g:3015:1: entryRuleContextLink returns [EObject current=null] : iv_ruleContextLink= ruleContextLink EOF ;
    public final EObject entryRuleContextLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextLink = null;


        try {
            // InternalEJSL.g:3015:52: (iv_ruleContextLink= ruleContextLink EOF )
            // InternalEJSL.g:3016:2: iv_ruleContextLink= ruleContextLink EOF
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
    // InternalEJSL.g:3022:1: ruleContextLink returns [EObject current=null] : ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' ) ;
    public final EObject ruleContextLink() throws RecognitionException {
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
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_linkparameters_12_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3028:2: ( ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' ) )
            // InternalEJSL.g:3029:2: ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' )
            {
            // InternalEJSL.g:3029:2: ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' )
            // InternalEJSL.g:3030:3: () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}'
            {
            // InternalEJSL.g:3030:3: ()
            // InternalEJSL.g:3031:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getContextLinkAccess().getContextLinkAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,71,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getContextLinkAccess().getInternalcontextLinkKeyword_1());
            		
            // InternalEJSL.g:3041:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:3042:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:3042:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:3043:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_77); 

            			newLeafNode(otherlv_3, grammarAccess.getContextLinkAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,68,FOLLOW_23); 

            			newLeafNode(otherlv_4, grammarAccess.getContextLinkAccess().getTargetKeyword_4());
            		
            otherlv_5=(Token)match(input,28,FOLLOW_40); 

            			newLeafNode(otherlv_5, grammarAccess.getContextLinkAccess().getEqualsSignKeyword_5());
            		
            // InternalEJSL.g:3072:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:3073:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:3073:4: ( ruleQualifiedName )
            // InternalEJSL.g:3074:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getContextLinkRule());
            					}
            				

            					newCompositeNode(grammarAccess.getContextLinkAccess().getTargetPageCrossReference_6_0());
            				
            pushFollow(FOLLOW_78);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,69,FOLLOW_23); 

            			newLeafNode(otherlv_7, grammarAccess.getContextLinkAccess().getLinkedAttributeKeyword_7());
            		
            otherlv_8=(Token)match(input,28,FOLLOW_40); 

            			newLeafNode(otherlv_8, grammarAccess.getContextLinkAccess().getEqualsSignKeyword_8());
            		
            // InternalEJSL.g:3096:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:3097:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:3097:4: ( ruleQualifiedName )
            // InternalEJSL.g:3098:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getContextLinkRule());
            					}
            				

            					newCompositeNode(grammarAccess.getContextLinkAccess().getLinkedAttributeAttributeCrossReference_9_0());
            				
            pushFollow(FOLLOW_80);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,72,FOLLOW_4); 

            			newLeafNode(otherlv_10, grammarAccess.getContextLinkAccess().getLinkparametersKeyword_10());
            		
            otherlv_11=(Token)match(input,16,FOLLOW_9); 

            			newLeafNode(otherlv_11, grammarAccess.getContextLinkAccess().getLeftCurlyBracketKeyword_11());
            		
            // InternalEJSL.g:3120:3: ( (lv_linkparameters_12_0= ruleLinkParameter ) )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==31) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // InternalEJSL.g:3121:4: (lv_linkparameters_12_0= ruleLinkParameter )
            	    {
            	    // InternalEJSL.g:3121:4: (lv_linkparameters_12_0= ruleLinkParameter )
            	    // InternalEJSL.g:3122:5: lv_linkparameters_12_0= ruleLinkParameter
            	    {

            	    					newCompositeNode(grammarAccess.getContextLinkAccess().getLinkparametersLinkParameterParserRuleCall_12_0());
            	    				
            	    pushFollow(FOLLOW_9);
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
            	    break loop84;
                }
            } while (true);

            otherlv_13=(Token)match(input,19,FOLLOW_20); 

            			newLeafNode(otherlv_13, grammarAccess.getContextLinkAccess().getRightCurlyBracketKeyword_13());
            		
            otherlv_14=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_14, grammarAccess.getContextLinkAccess().getRightCurlyBracketKeyword_14());
            		

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
    // InternalEJSL.g:3151:1: entryRuleLinkParameter returns [EObject current=null] : iv_ruleLinkParameter= ruleLinkParameter EOF ;
    public final EObject entryRuleLinkParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLinkParameter = null;


        try {
            // InternalEJSL.g:3151:54: (iv_ruleLinkParameter= ruleLinkParameter EOF )
            // InternalEJSL.g:3152:2: iv_ruleLinkParameter= ruleLinkParameter EOF
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
    // InternalEJSL.g:3158:1: ruleLinkParameter returns [EObject current=null] : ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleLinkParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_value_6_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3164:2: ( ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) ) ) )
            // InternalEJSL.g:3165:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) ) )
            {
            // InternalEJSL.g:3165:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) ) )
            // InternalEJSL.g:3166:3: () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) )
            {
            // InternalEJSL.g:3166:3: ()
            // InternalEJSL.g:3167:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getLinkParameterAccess().getLinkParameterAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,31,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getLinkParameterAccess().getParameterKeyword_1());
            		
            // InternalEJSL.g:3177:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:3178:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:3178:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:3179:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getLinkParameterAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_23);
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

            otherlv_3=(Token)match(input,28,FOLLOW_81); 

            			newLeafNode(otherlv_3, grammarAccess.getLinkParameterAccess().getEqualsSignKeyword_3());
            		
            // InternalEJSL.g:3200:3: ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==73) ) {
                alt85=1;
            }
            else if ( (LA85_0==RULE_STRING) ) {
                alt85=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }
            switch (alt85) {
                case 1 :
                    // InternalEJSL.g:3201:4: (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) )
                    {
                    // InternalEJSL.g:3201:4: (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) )
                    // InternalEJSL.g:3202:5: otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,73,FOLLOW_3); 

                    					newLeafNode(otherlv_4, grammarAccess.getLinkParameterAccess().getAttributeKeyword_4_0_0());
                    				
                    // InternalEJSL.g:3206:5: ( (otherlv_5= RULE_STRING ) )
                    // InternalEJSL.g:3207:6: (otherlv_5= RULE_STRING )
                    {
                    // InternalEJSL.g:3207:6: (otherlv_5= RULE_STRING )
                    // InternalEJSL.g:3208:7: otherlv_5= RULE_STRING
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getLinkParameterRule());
                    							}
                    						
                    otherlv_5=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    							newLeafNode(otherlv_5, grammarAccess.getLinkParameterAccess().getAttvalueAttributeCrossReference_4_0_1_0());
                    						

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:3221:4: ( (lv_value_6_0= RULE_STRING ) )
                    {
                    // InternalEJSL.g:3221:4: ( (lv_value_6_0= RULE_STRING ) )
                    // InternalEJSL.g:3222:5: (lv_value_6_0= RULE_STRING )
                    {
                    // InternalEJSL.g:3222:5: (lv_value_6_0= RULE_STRING )
                    // InternalEJSL.g:3223:6: lv_value_6_0= RULE_STRING
                    {
                    lv_value_6_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_value_6_0, grammarAccess.getLinkParameterAccess().getValueSTRINGTerminalRuleCall_4_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getLinkParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_6_0,
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
    // InternalEJSL.g:3244:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // InternalEJSL.g:3244:50: (iv_ruleExtension= ruleExtension EOF )
            // InternalEJSL.g:3245:2: iv_ruleExtension= ruleExtension EOF
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
    // InternalEJSL.g:3251:1: ruleExtension returns [EObject current=null] : (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate ) ;
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
            // InternalEJSL.g:3257:2: ( (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate ) )
            // InternalEJSL.g:3258:2: (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate )
            {
            // InternalEJSL.g:3258:2: (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate )
            int alt86=6;
            switch ( input.LA(1) ) {
            case 74:
                {
                alt86=1;
                }
                break;
            case 77:
                {
                alt86=2;
                }
                break;
            case 85:
                {
                alt86=3;
                }
                break;
            case 86:
                {
                alt86=4;
                }
                break;
            case 89:
                {
                alt86=5;
                }
                break;
            case 100:
                {
                alt86=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }

            switch (alt86) {
                case 1 :
                    // InternalEJSL.g:3259:3: this_ExtensionPackage_0= ruleExtensionPackage
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
                    // InternalEJSL.g:3268:3: this_Component_1= ruleComponent
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
                    // InternalEJSL.g:3277:3: this_Module_2= ruleModule
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
                    // InternalEJSL.g:3286:3: this_Plugin_3= rulePlugin
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
                    // InternalEJSL.g:3295:3: this_Library_4= ruleLibrary
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
                    // InternalEJSL.g:3304:3: this_Template_5= ruleTemplate
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
    // InternalEJSL.g:3316:1: entryRuleExtensionPackage returns [EObject current=null] : iv_ruleExtensionPackage= ruleExtensionPackage EOF ;
    public final EObject entryRuleExtensionPackage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtensionPackage = null;


        try {
            // InternalEJSL.g:3316:57: (iv_ruleExtensionPackage= ruleExtensionPackage EOF )
            // InternalEJSL.g:3317:2: iv_ruleExtensionPackage= ruleExtensionPackage EOF
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
    // InternalEJSL.g:3323:1: ruleExtensionPackage returns [EObject current=null] : ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' ) ;
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
            // InternalEJSL.g:3329:2: ( ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' ) )
            // InternalEJSL.g:3330:2: ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' )
            {
            // InternalEJSL.g:3330:2: ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' )
            // InternalEJSL.g:3331:3: () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}'
            {
            // InternalEJSL.g:3331:3: ()
            // InternalEJSL.g:3332:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExtensionPackageAccess().getExtensionPackageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,74,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getExtensionPackageAccess().getExtensionPackageKeyword_1());
            		
            // InternalEJSL.g:3342:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:3343:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:3343:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:3344:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			newLeafNode(otherlv_3, grammarAccess.getExtensionPackageAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getExtensionPackageAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			newLeafNode(otherlv_5, grammarAccess.getExtensionPackageAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:3373:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:3374:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:3374:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:3375:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getExtensionPackageAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_20);
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

            otherlv_7=(Token)match(input,19,FOLLOW_84); 

            			newLeafNode(otherlv_7, grammarAccess.getExtensionPackageAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:3396:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==76) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalEJSL.g:3397:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,76,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getExtensionPackageAccess().getLanguagesKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_85); 

                    				newLeafNode(otherlv_9, grammarAccess.getExtensionPackageAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:3405:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( (LA87_0==112) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // InternalEJSL.g:3406:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:3406:5: (lv_languages_10_0= ruleLanguage )
                    	    // InternalEJSL.g:3407:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getExtensionPackageAccess().getLanguagesLanguageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop87;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_86); 

                    				newLeafNode(otherlv_11, grammarAccess.getExtensionPackageAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            otherlv_12=(Token)match(input,25,FOLLOW_4); 

            			newLeafNode(otherlv_12, grammarAccess.getExtensionPackageAccess().getExtensionsKeyword_9());
            		
            otherlv_13=(Token)match(input,16,FOLLOW_87); 

            			newLeafNode(otherlv_13, grammarAccess.getExtensionPackageAccess().getLeftCurlyBracketKeyword_10());
            		
            // InternalEJSL.g:3437:3: ( (lv_extensions_14_0= ruleExtension ) )+
            int cnt89=0;
            loop89:
            do {
                int alt89=2;
                int LA89_0 = input.LA(1);

                if ( (LA89_0==74||LA89_0==77||(LA89_0>=85 && LA89_0<=86)||LA89_0==89||LA89_0==100) ) {
                    alt89=1;
                }


                switch (alt89) {
            	case 1 :
            	    // InternalEJSL.g:3438:4: (lv_extensions_14_0= ruleExtension )
            	    {
            	    // InternalEJSL.g:3438:4: (lv_extensions_14_0= ruleExtension )
            	    // InternalEJSL.g:3439:5: lv_extensions_14_0= ruleExtension
            	    {

            	    					newCompositeNode(grammarAccess.getExtensionPackageAccess().getExtensionsExtensionParserRuleCall_11_0());
            	    				
            	    pushFollow(FOLLOW_19);
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
            	    if ( cnt89 >= 1 ) break loop89;
                        EarlyExitException eee =
                            new EarlyExitException(89, input);
                        throw eee;
                }
                cnt89++;
            } while (true);

            otherlv_15=(Token)match(input,19,FOLLOW_20); 

            			newLeafNode(otherlv_15, grammarAccess.getExtensionPackageAccess().getRightCurlyBracketKeyword_12());
            		
            otherlv_16=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:3468:1: entryRuleComponent returns [EObject current=null] : iv_ruleComponent= ruleComponent EOF ;
    public final EObject entryRuleComponent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent = null;


        try {
            // InternalEJSL.g:3468:50: (iv_ruleComponent= ruleComponent EOF )
            // InternalEJSL.g:3469:2: iv_ruleComponent= ruleComponent EOF
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
    // InternalEJSL.g:3475:1: ruleComponent returns [EObject current=null] : ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' ) ;
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
            // InternalEJSL.g:3481:2: ( ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' ) )
            // InternalEJSL.g:3482:2: ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' )
            {
            // InternalEJSL.g:3482:2: ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' )
            // InternalEJSL.g:3483:3: () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}'
            {
            // InternalEJSL.g:3483:3: ()
            // InternalEJSL.g:3484:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getComponentAccess().getComponentAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,77,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getComponentAccess().getComponentKeyword_1());
            		
            // InternalEJSL.g:3494:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:3495:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:3495:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:3496:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			newLeafNode(otherlv_3, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getComponentAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			newLeafNode(otherlv_5, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:3525:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:3526:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:3526:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:3527:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getComponentAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_20);
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

            otherlv_7=(Token)match(input,19,FOLLOW_88); 

            			newLeafNode(otherlv_7, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:3548:3: (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==78) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // InternalEJSL.g:3549:4: otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,78,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getComponentAccess().getGlobalParameterKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_11); 

                    				newLeafNode(otherlv_9, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:3557:4: ( (lv_globalParamter_10_0= ruleParameterGroup ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==37) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // InternalEJSL.g:3558:5: (lv_globalParamter_10_0= ruleParameterGroup )
                    	    {
                    	    // InternalEJSL.g:3558:5: (lv_globalParamter_10_0= ruleParameterGroup )
                    	    // InternalEJSL.g:3559:6: lv_globalParamter_10_0= ruleParameterGroup
                    	    {

                    	    						newCompositeNode(grammarAccess.getComponentAccess().getGlobalParamterParameterGroupParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_11);
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
                    	    break loop90;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_89); 

                    				newLeafNode(otherlv_11, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:3581:3: (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==76) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // InternalEJSL.g:3582:4: otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}'
                    {
                    otherlv_12=(Token)match(input,76,FOLLOW_4); 

                    				newLeafNode(otherlv_12, grammarAccess.getComponentAccess().getLanguagesKeyword_9_0());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_85); 

                    				newLeafNode(otherlv_13, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_9_1());
                    			
                    // InternalEJSL.g:3590:4: ( (lv_languages_14_0= ruleLanguage ) )*
                    loop92:
                    do {
                        int alt92=2;
                        int LA92_0 = input.LA(1);

                        if ( (LA92_0==112) ) {
                            alt92=1;
                        }


                        switch (alt92) {
                    	case 1 :
                    	    // InternalEJSL.g:3591:5: (lv_languages_14_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:3591:5: (lv_languages_14_0= ruleLanguage )
                    	    // InternalEJSL.g:3592:6: lv_languages_14_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getComponentAccess().getLanguagesLanguageParserRuleCall_9_2_0());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop92;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,19,FOLLOW_90); 

                    				newLeafNode(otherlv_15, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_9_3());
                    			

                    }
                    break;

            }

            otherlv_16=(Token)match(input,79,FOLLOW_4); 

            			newLeafNode(otherlv_16, grammarAccess.getComponentAccess().getSectionsKeyword_10());
            		
            otherlv_17=(Token)match(input,16,FOLLOW_91); 

            			newLeafNode(otherlv_17, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_11());
            		
            // InternalEJSL.g:3622:3: ( (lv_sections_18_0= ruleSection ) )+
            int cnt94=0;
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( (LA94_0==80||LA94_0==84) ) {
                    alt94=1;
                }


                switch (alt94) {
            	case 1 :
            	    // InternalEJSL.g:3623:4: (lv_sections_18_0= ruleSection )
            	    {
            	    // InternalEJSL.g:3623:4: (lv_sections_18_0= ruleSection )
            	    // InternalEJSL.g:3624:5: lv_sections_18_0= ruleSection
            	    {

            	    					newCompositeNode(grammarAccess.getComponentAccess().getSectionsSectionParserRuleCall_12_0());
            	    				
            	    pushFollow(FOLLOW_92);
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
            	    if ( cnt94 >= 1 ) break loop94;
                        EarlyExitException eee =
                            new EarlyExitException(94, input);
                        throw eee;
                }
                cnt94++;
            } while (true);

            otherlv_19=(Token)match(input,19,FOLLOW_20); 

            			newLeafNode(otherlv_19, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_13());
            		
            otherlv_20=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:3653:1: entryRuleSection returns [EObject current=null] : iv_ruleSection= ruleSection EOF ;
    public final EObject entryRuleSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSection = null;


        try {
            // InternalEJSL.g:3653:48: (iv_ruleSection= ruleSection EOF )
            // InternalEJSL.g:3654:2: iv_ruleSection= ruleSection EOF
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
    // InternalEJSL.g:3660:1: ruleSection returns [EObject current=null] : (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend ) ;
    public final EObject ruleSection() throws RecognitionException {
        EObject current = null;

        EObject this_Backend_0 = null;

        EObject this_Frontend_1 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3666:2: ( (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend ) )
            // InternalEJSL.g:3667:2: (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend )
            {
            // InternalEJSL.g:3667:2: (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend )
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==80) ) {
                alt95=1;
            }
            else if ( (LA95_0==84) ) {
                alt95=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }
            switch (alt95) {
                case 1 :
                    // InternalEJSL.g:3668:3: this_Backend_0= ruleBackend
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
                    // InternalEJSL.g:3677:3: this_Frontend_1= ruleFrontend
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
    // InternalEJSL.g:3689:1: entryRuleBackend returns [EObject current=null] : iv_ruleBackend= ruleBackend EOF ;
    public final EObject entryRuleBackend() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBackend = null;


        try {
            // InternalEJSL.g:3689:48: (iv_ruleBackend= ruleBackend EOF )
            // InternalEJSL.g:3690:2: iv_ruleBackend= ruleBackend EOF
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
    // InternalEJSL.g:3696:1: ruleBackend returns [EObject current=null] : ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) ;
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
            // InternalEJSL.g:3702:2: ( ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) )
            // InternalEJSL.g:3703:2: ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            {
            // InternalEJSL.g:3703:2: ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            // InternalEJSL.g:3704:3: () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}'
            {
            // InternalEJSL.g:3704:3: ()
            // InternalEJSL.g:3705:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBackendAccess().getBackendSectionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,80,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getBackendAccess().getBackendSectionKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_93); 

            			newLeafNode(otherlv_2, grammarAccess.getBackendAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,81,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getBackendAccess().getPagesKeyword_3());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_94); 

            			newLeafNode(otherlv_4, grammarAccess.getBackendAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:3727:3: ( (lv_pageRef_5_0= rulePageReference ) )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( (LA96_0==82) ) {
                    alt96=1;
                }


                switch (alt96) {
            	case 1 :
            	    // InternalEJSL.g:3728:4: (lv_pageRef_5_0= rulePageReference )
            	    {
            	    // InternalEJSL.g:3728:4: (lv_pageRef_5_0= rulePageReference )
            	    // InternalEJSL.g:3729:5: lv_pageRef_5_0= rulePageReference
            	    {

            	    					newCompositeNode(grammarAccess.getBackendAccess().getPageRefPageReferenceParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_94);
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
            	    break loop96;
                }
            } while (true);

            otherlv_6=(Token)match(input,19,FOLLOW_20); 

            			newLeafNode(otherlv_6, grammarAccess.getBackendAccess().getRightCurlyBracketKeyword_6());
            		
            otherlv_7=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:3758:1: entryRulePageReference returns [EObject current=null] : iv_rulePageReference= rulePageReference EOF ;
    public final EObject entryRulePageReference() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePageReference = null;


        try {
            // InternalEJSL.g:3758:54: (iv_rulePageReference= rulePageReference EOF )
            // InternalEJSL.g:3759:2: iv_rulePageReference= rulePageReference EOF
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
    // InternalEJSL.g:3765:1: rulePageReference returns [EObject current=null] : (otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )? ) ;
    public final EObject rulePageReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Enumerator lv_sect_6_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:3771:2: ( (otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )? ) )
            // InternalEJSL.g:3772:2: (otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )? )
            {
            // InternalEJSL.g:3772:2: (otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )? )
            // InternalEJSL.g:3773:3: otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )?
            {
            otherlv_0=(Token)match(input,82,FOLLOW_72); 

            			newLeafNode(otherlv_0, grammarAccess.getPageReferenceAccess().getPageKeyword_0());
            		
            otherlv_1=(Token)match(input,64,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getPageReferenceAccess().getColonKeyword_1());
            		
            // InternalEJSL.g:3781:3: ( ( ruleQualifiedName ) )
            // InternalEJSL.g:3782:4: ( ruleQualifiedName )
            {
            // InternalEJSL.g:3782:4: ( ruleQualifiedName )
            // InternalEJSL.g:3783:5: ruleQualifiedName
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPageReferenceRule());
            					}
            				

            					newCompositeNode(grammarAccess.getPageReferenceAccess().getPagePageCrossReference_2_0());
            				
            pushFollow(FOLLOW_95);
            ruleQualifiedName();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:3797:3: (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==83) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalEJSL.g:3798:4: otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) )
                    {
                    otherlv_3=(Token)match(input,83,FOLLOW_72); 

                    				newLeafNode(otherlv_3, grammarAccess.getPageReferenceAccess().getFromKeyword_3_0());
                    			
                    otherlv_4=(Token)match(input,64,FOLLOW_40); 

                    				newLeafNode(otherlv_4, grammarAccess.getPageReferenceAccess().getColonKeyword_3_1());
                    			
                    // InternalEJSL.g:3806:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:3807:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:3807:5: ( ruleQualifiedName )
                    // InternalEJSL.g:3808:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPageReferenceRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getPageReferenceAccess().getPagescrComponentCrossReference_3_2_0());
                    					
                    pushFollow(FOLLOW_96);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:3822:4: ( (lv_sect_6_0= ruleSectionReference ) )
                    // InternalEJSL.g:3823:5: (lv_sect_6_0= ruleSectionReference )
                    {
                    // InternalEJSL.g:3823:5: (lv_sect_6_0= ruleSectionReference )
                    // InternalEJSL.g:3824:6: lv_sect_6_0= ruleSectionReference
                    {

                    						newCompositeNode(grammarAccess.getPageReferenceAccess().getSectSectionReferenceEnumRuleCall_3_3_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_sect_6_0=ruleSectionReference();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPageReferenceRule());
                    						}
                    						set(
                    							current,
                    							"sect",
                    							lv_sect_6_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.SectionReference");
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
    // InternalEJSL.g:3846:1: entryRuleFrontend returns [EObject current=null] : iv_ruleFrontend= ruleFrontend EOF ;
    public final EObject entryRuleFrontend() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFrontend = null;


        try {
            // InternalEJSL.g:3846:49: (iv_ruleFrontend= ruleFrontend EOF )
            // InternalEJSL.g:3847:2: iv_ruleFrontend= ruleFrontend EOF
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
    // InternalEJSL.g:3853:1: ruleFrontend returns [EObject current=null] : ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) ;
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
            // InternalEJSL.g:3859:2: ( ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) )
            // InternalEJSL.g:3860:2: ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            {
            // InternalEJSL.g:3860:2: ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            // InternalEJSL.g:3861:3: () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}'
            {
            // InternalEJSL.g:3861:3: ()
            // InternalEJSL.g:3862:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFrontendAccess().getFrontendSectionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,84,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getFrontendAccess().getFrontendSectionKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_93); 

            			newLeafNode(otherlv_2, grammarAccess.getFrontendAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_3=(Token)match(input,81,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getFrontendAccess().getPagesKeyword_3());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_94); 

            			newLeafNode(otherlv_4, grammarAccess.getFrontendAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:3884:3: ( (lv_pageRef_5_0= rulePageReference ) )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==82) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // InternalEJSL.g:3885:4: (lv_pageRef_5_0= rulePageReference )
            	    {
            	    // InternalEJSL.g:3885:4: (lv_pageRef_5_0= rulePageReference )
            	    // InternalEJSL.g:3886:5: lv_pageRef_5_0= rulePageReference
            	    {

            	    					newCompositeNode(grammarAccess.getFrontendAccess().getPageRefPageReferenceParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_94);
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
            	    break loop98;
                }
            } while (true);

            otherlv_6=(Token)match(input,19,FOLLOW_20); 

            			newLeafNode(otherlv_6, grammarAccess.getFrontendAccess().getRightCurlyBracketKeyword_6());
            		
            otherlv_7=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:3915:1: entryRuleModule returns [EObject current=null] : iv_ruleModule= ruleModule EOF ;
    public final EObject entryRuleModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModule = null;


        try {
            // InternalEJSL.g:3915:47: (iv_ruleModule= ruleModule EOF )
            // InternalEJSL.g:3916:2: iv_ruleModule= ruleModule EOF
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
    // InternalEJSL.g:3922:1: ruleModule returns [EObject current=null] : ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' ) ;
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
            // InternalEJSL.g:3928:2: ( ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' ) )
            // InternalEJSL.g:3929:2: ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' )
            {
            // InternalEJSL.g:3929:2: ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' )
            // InternalEJSL.g:3930:3: () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}'
            {
            // InternalEJSL.g:3930:3: ()
            // InternalEJSL.g:3931:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getModuleAccess().getModuleAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,85,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getModuleAccess().getModuleKeyword_1());
            		
            // InternalEJSL.g:3941:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:3942:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:3942:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:3943:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			newLeafNode(otherlv_3, grammarAccess.getModuleAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getModuleAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			newLeafNode(otherlv_5, grammarAccess.getModuleAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:3972:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:3973:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:3973:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:3974:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getModuleAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_20);
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

            otherlv_7=(Token)match(input,19,FOLLOW_97); 

            			newLeafNode(otherlv_7, grammarAccess.getModuleAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:3995:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==76) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // InternalEJSL.g:3996:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,76,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getModuleAccess().getLanguagesKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_85); 

                    				newLeafNode(otherlv_9, grammarAccess.getModuleAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:4004:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop99:
                    do {
                        int alt99=2;
                        int LA99_0 = input.LA(1);

                        if ( (LA99_0==112) ) {
                            alt99=1;
                        }


                        switch (alt99) {
                    	case 1 :
                    	    // InternalEJSL.g:4005:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:4005:5: (lv_languages_10_0= ruleLanguage )
                    	    // InternalEJSL.g:4006:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getModuleAccess().getLanguagesLanguageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop99;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_94); 

                    				newLeafNode(otherlv_11, grammarAccess.getModuleAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:4028:3: ( (lv_pageRef_12_0= rulePageReference ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==82) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // InternalEJSL.g:4029:4: (lv_pageRef_12_0= rulePageReference )
                    {
                    // InternalEJSL.g:4029:4: (lv_pageRef_12_0= rulePageReference )
                    // InternalEJSL.g:4030:5: lv_pageRef_12_0= rulePageReference
                    {

                    					newCompositeNode(grammarAccess.getModuleAccess().getPageRefPageReferenceParserRuleCall_9_0());
                    				
                    pushFollow(FOLLOW_20);
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

            otherlv_13=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:4055:1: entryRulePlugin returns [EObject current=null] : iv_rulePlugin= rulePlugin EOF ;
    public final EObject entryRulePlugin() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlugin = null;


        try {
            // InternalEJSL.g:4055:47: (iv_rulePlugin= rulePlugin EOF )
            // InternalEJSL.g:4056:2: iv_rulePlugin= rulePlugin EOF
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
    // InternalEJSL.g:4062:1: rulePlugin returns [EObject current=null] : ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}' ) ;
    public final EObject rulePlugin() throws RecognitionException {
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
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_manifest_6_0 = null;

        Enumerator lv_type_10_0 = null;

        EObject lv_languages_13_0 = null;

        EObject lv_localparameters_21_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4068:2: ( ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}' ) )
            // InternalEJSL.g:4069:2: ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}' )
            {
            // InternalEJSL.g:4069:2: ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}' )
            // InternalEJSL.g:4070:3: () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}'
            {
            // InternalEJSL.g:4070:3: ()
            // InternalEJSL.g:4071:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPluginAccess().getPluginAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,86,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getPluginAccess().getPluginKeyword_1());
            		
            // InternalEJSL.g:4081:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4082:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4082:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4083:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			newLeafNode(otherlv_3, grammarAccess.getPluginAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getPluginAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			newLeafNode(otherlv_5, grammarAccess.getPluginAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:4112:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:4113:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:4113:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:4114:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getPluginAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_20);
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

            otherlv_7=(Token)match(input,19,FOLLOW_98); 

            			newLeafNode(otherlv_7, grammarAccess.getPluginAccess().getRightCurlyBracketKeyword_7());
            		
            otherlv_8=(Token)match(input,87,FOLLOW_23); 

            			newLeafNode(otherlv_8, grammarAccess.getPluginAccess().getPlugintypeKeyword_8());
            		
            otherlv_9=(Token)match(input,28,FOLLOW_99); 

            			newLeafNode(otherlv_9, grammarAccess.getPluginAccess().getEqualsSignKeyword_9());
            		
            // InternalEJSL.g:4143:3: ( (lv_type_10_0= rulePluginKinds ) )
            // InternalEJSL.g:4144:4: (lv_type_10_0= rulePluginKinds )
            {
            // InternalEJSL.g:4144:4: (lv_type_10_0= rulePluginKinds )
            // InternalEJSL.g:4145:5: lv_type_10_0= rulePluginKinds
            {

            					newCompositeNode(grammarAccess.getPluginAccess().getTypePluginKindsEnumRuleCall_10_0());
            				
            pushFollow(FOLLOW_100);
            lv_type_10_0=rulePluginKinds();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPluginRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_10_0,
            						"de.thm.icampus.joomdd.ejsl.EJSL.PluginKinds");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalEJSL.g:4162:3: (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==76) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // InternalEJSL.g:4163:4: otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}'
                    {
                    otherlv_11=(Token)match(input,76,FOLLOW_4); 

                    				newLeafNode(otherlv_11, grammarAccess.getPluginAccess().getLanguagesKeyword_11_0());
                    			
                    otherlv_12=(Token)match(input,16,FOLLOW_85); 

                    				newLeafNode(otherlv_12, grammarAccess.getPluginAccess().getLeftCurlyBracketKeyword_11_1());
                    			
                    // InternalEJSL.g:4171:4: ( (lv_languages_13_0= ruleLanguage ) )*
                    loop102:
                    do {
                        int alt102=2;
                        int LA102_0 = input.LA(1);

                        if ( (LA102_0==112) ) {
                            alt102=1;
                        }


                        switch (alt102) {
                    	case 1 :
                    	    // InternalEJSL.g:4172:5: (lv_languages_13_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:4172:5: (lv_languages_13_0= ruleLanguage )
                    	    // InternalEJSL.g:4173:6: lv_languages_13_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getPluginAccess().getLanguagesLanguageParserRuleCall_11_2_0());
                    	    					
                    	    pushFollow(FOLLOW_85);
                    	    lv_languages_13_0=ruleLanguage();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPluginRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"languages",
                    	    							lv_languages_13_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Language");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop102;
                        }
                    } while (true);

                    otherlv_14=(Token)match(input,19,FOLLOW_101); 

                    				newLeafNode(otherlv_14, grammarAccess.getPluginAccess().getRightCurlyBracketKeyword_11_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:4195:3: (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==59) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // InternalEJSL.g:4196:4: otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )*
                    {
                    otherlv_15=(Token)match(input,59,FOLLOW_3); 

                    				newLeafNode(otherlv_15, grammarAccess.getPluginAccess().getEntitiesKeyword_12_0());
                    			
                    // InternalEJSL.g:4200:4: ( (otherlv_16= RULE_STRING ) )
                    // InternalEJSL.g:4201:5: (otherlv_16= RULE_STRING )
                    {
                    // InternalEJSL.g:4201:5: (otherlv_16= RULE_STRING )
                    // InternalEJSL.g:4202:6: otherlv_16= RULE_STRING
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPluginRule());
                    						}
                    					
                    otherlv_16=(Token)match(input,RULE_STRING,FOLLOW_102); 

                    						newLeafNode(otherlv_16, grammarAccess.getPluginAccess().getEntitiesEntityCrossReference_12_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:4213:4: (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )*
                    loop104:
                    do {
                        int alt104=2;
                        int LA104_0 = input.LA(1);

                        if ( (LA104_0==18) ) {
                            alt104=1;
                        }


                        switch (alt104) {
                    	case 1 :
                    	    // InternalEJSL.g:4214:5: otherlv_17= ',' ( (otherlv_18= RULE_STRING ) )
                    	    {
                    	    otherlv_17=(Token)match(input,18,FOLLOW_3); 

                    	    					newLeafNode(otherlv_17, grammarAccess.getPluginAccess().getCommaKeyword_12_2_0());
                    	    				
                    	    // InternalEJSL.g:4218:5: ( (otherlv_18= RULE_STRING ) )
                    	    // InternalEJSL.g:4219:6: (otherlv_18= RULE_STRING )
                    	    {
                    	    // InternalEJSL.g:4219:6: (otherlv_18= RULE_STRING )
                    	    // InternalEJSL.g:4220:7: otherlv_18= RULE_STRING
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getPluginRule());
                    	    							}
                    	    						
                    	    otherlv_18=(Token)match(input,RULE_STRING,FOLLOW_102); 

                    	    							newLeafNode(otherlv_18, grammarAccess.getPluginAccess().getEntitiesEntityCrossReference_12_2_1_0());
                    	    						

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

            // InternalEJSL.g:4233:3: (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==88) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // InternalEJSL.g:4234:4: otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}'
                    {
                    otherlv_19=(Token)match(input,88,FOLLOW_4); 

                    				newLeafNode(otherlv_19, grammarAccess.getPluginAccess().getParametersKeyword_13_0());
                    			
                    otherlv_20=(Token)match(input,16,FOLLOW_9); 

                    				newLeafNode(otherlv_20, grammarAccess.getPluginAccess().getLeftCurlyBracketKeyword_13_1());
                    			
                    // InternalEJSL.g:4242:4: ( (lv_localparameters_21_0= ruleParameter ) )*
                    loop106:
                    do {
                        int alt106=2;
                        int LA106_0 = input.LA(1);

                        if ( (LA106_0==31) ) {
                            alt106=1;
                        }


                        switch (alt106) {
                    	case 1 :
                    	    // InternalEJSL.g:4243:5: (lv_localparameters_21_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:4243:5: (lv_localparameters_21_0= ruleParameter )
                    	    // InternalEJSL.g:4244:6: lv_localparameters_21_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getPluginAccess().getLocalparametersParameterParserRuleCall_13_2_0());
                    	    					
                    	    pushFollow(FOLLOW_9);
                    	    lv_localparameters_21_0=ruleParameter();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPluginRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"localparameters",
                    	    							lv_localparameters_21_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.Parameter");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop106;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_22, grammarAccess.getPluginAccess().getRightCurlyBracketKeyword_13_3());
                    			

                    }
                    break;

            }

            otherlv_23=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_23, grammarAccess.getPluginAccess().getRightCurlyBracketKeyword_14());
            		

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
    // InternalEJSL.g:4274:1: entryRuleLibrary returns [EObject current=null] : iv_ruleLibrary= ruleLibrary EOF ;
    public final EObject entryRuleLibrary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLibrary = null;


        try {
            // InternalEJSL.g:4274:48: (iv_ruleLibrary= ruleLibrary EOF )
            // InternalEJSL.g:4275:2: iv_ruleLibrary= ruleLibrary EOF
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
    // InternalEJSL.g:4281:1: ruleLibrary returns [EObject current=null] : ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' ) ;
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
            // InternalEJSL.g:4287:2: ( ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' ) )
            // InternalEJSL.g:4288:2: ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' )
            {
            // InternalEJSL.g:4288:2: ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' )
            // InternalEJSL.g:4289:3: () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}'
            {
            // InternalEJSL.g:4289:3: ()
            // InternalEJSL.g:4290:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getLibraryAccess().getLibraryAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,89,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getLibraryAccess().getLibraryKeyword_1());
            		
            // InternalEJSL.g:4300:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4301:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4301:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4302:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			newLeafNode(otherlv_3, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getLibraryAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			newLeafNode(otherlv_5, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:4331:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:4332:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:4332:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:4333:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getLibraryAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_20);
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

            otherlv_7=(Token)match(input,19,FOLLOW_103); 

            			newLeafNode(otherlv_7, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:4354:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==76) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // InternalEJSL.g:4355:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,76,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getLibraryAccess().getLanguagesKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_85); 

                    				newLeafNode(otherlv_9, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:4363:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop108:
                    do {
                        int alt108=2;
                        int LA108_0 = input.LA(1);

                        if ( (LA108_0==112) ) {
                            alt108=1;
                        }


                        switch (alt108) {
                    	case 1 :
                    	    // InternalEJSL.g:4364:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:4364:5: (lv_languages_10_0= ruleLanguage )
                    	    // InternalEJSL.g:4365:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getLibraryAccess().getLanguagesLanguageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop108;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_104); 

                    				newLeafNode(otherlv_11, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:4387:3: (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==59) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // InternalEJSL.g:4388:4: otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )*
                    {
                    otherlv_12=(Token)match(input,59,FOLLOW_3); 

                    				newLeafNode(otherlv_12, grammarAccess.getLibraryAccess().getEntitiesKeyword_9_0());
                    			
                    // InternalEJSL.g:4392:4: ( (otherlv_13= RULE_STRING ) )
                    // InternalEJSL.g:4393:5: (otherlv_13= RULE_STRING )
                    {
                    // InternalEJSL.g:4393:5: (otherlv_13= RULE_STRING )
                    // InternalEJSL.g:4394:6: otherlv_13= RULE_STRING
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getLibraryRule());
                    						}
                    					
                    otherlv_13=(Token)match(input,RULE_STRING,FOLLOW_105); 

                    						newLeafNode(otherlv_13, grammarAccess.getLibraryAccess().getEntitiesEntityCrossReference_9_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:4405:4: (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )*
                    loop110:
                    do {
                        int alt110=2;
                        int LA110_0 = input.LA(1);

                        if ( (LA110_0==18) ) {
                            alt110=1;
                        }


                        switch (alt110) {
                    	case 1 :
                    	    // InternalEJSL.g:4406:5: otherlv_14= ',' ( (otherlv_15= RULE_STRING ) )
                    	    {
                    	    otherlv_14=(Token)match(input,18,FOLLOW_3); 

                    	    					newLeafNode(otherlv_14, grammarAccess.getLibraryAccess().getCommaKeyword_9_2_0());
                    	    				
                    	    // InternalEJSL.g:4410:5: ( (otherlv_15= RULE_STRING ) )
                    	    // InternalEJSL.g:4411:6: (otherlv_15= RULE_STRING )
                    	    {
                    	    // InternalEJSL.g:4411:6: (otherlv_15= RULE_STRING )
                    	    // InternalEJSL.g:4412:7: otherlv_15= RULE_STRING
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getLibraryRule());
                    	    							}
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_STRING,FOLLOW_105); 

                    	    							newLeafNode(otherlv_15, grammarAccess.getLibraryAccess().getEntitiesEntityCrossReference_9_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop110;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:4425:3: (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==90) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // InternalEJSL.g:4426:4: otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,90,FOLLOW_4); 

                    				newLeafNode(otherlv_16, grammarAccess.getLibraryAccess().getClassesKeyword_10_0());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_106); 

                    				newLeafNode(otherlv_17, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_10_1());
                    			
                    // InternalEJSL.g:4434:4: ( (lv_classes_18_0= ruleClass ) )*
                    loop112:
                    do {
                        int alt112=2;
                        int LA112_0 = input.LA(1);

                        if ( (LA112_0==93) ) {
                            alt112=1;
                        }


                        switch (alt112) {
                    	case 1 :
                    	    // InternalEJSL.g:4435:5: (lv_classes_18_0= ruleClass )
                    	    {
                    	    // InternalEJSL.g:4435:5: (lv_classes_18_0= ruleClass )
                    	    // InternalEJSL.g:4436:6: lv_classes_18_0= ruleClass
                    	    {

                    	    						newCompositeNode(grammarAccess.getLibraryAccess().getClassesClassParserRuleCall_10_2_0());
                    	    					
                    	    pushFollow(FOLLOW_106);
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
                    	    break loop112;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,19,FOLLOW_107); 

                    				newLeafNode(otherlv_19, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_10_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:4458:3: (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==91) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // InternalEJSL.g:4459:4: otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}'
                    {
                    otherlv_20=(Token)match(input,91,FOLLOW_4); 

                    				newLeafNode(otherlv_20, grammarAccess.getLibraryAccess().getPackagesKeyword_11_0());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_108); 

                    				newLeafNode(otherlv_21, grammarAccess.getLibraryAccess().getLeftCurlyBracketKeyword_11_1());
                    			
                    // InternalEJSL.g:4467:4: ( (lv_packages_22_0= rulePackage ) )*
                    loop114:
                    do {
                        int alt114=2;
                        int LA114_0 = input.LA(1);

                        if ( (LA114_0==92) ) {
                            alt114=1;
                        }


                        switch (alt114) {
                    	case 1 :
                    	    // InternalEJSL.g:4468:5: (lv_packages_22_0= rulePackage )
                    	    {
                    	    // InternalEJSL.g:4468:5: (lv_packages_22_0= rulePackage )
                    	    // InternalEJSL.g:4469:6: lv_packages_22_0= rulePackage
                    	    {

                    	    						newCompositeNode(grammarAccess.getLibraryAccess().getPackagesPackageParserRuleCall_11_2_0());
                    	    					
                    	    pushFollow(FOLLOW_108);
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
                    	    break loop114;
                        }
                    } while (true);

                    otherlv_23=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_23, grammarAccess.getLibraryAccess().getRightCurlyBracketKeyword_11_3());
                    			

                    }
                    break;

            }

            otherlv_24=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:4499:1: entryRulePackage returns [EObject current=null] : iv_rulePackage= rulePackage EOF ;
    public final EObject entryRulePackage() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackage = null;


        try {
            // InternalEJSL.g:4499:48: (iv_rulePackage= rulePackage EOF )
            // InternalEJSL.g:4500:2: iv_rulePackage= rulePackage EOF
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
    // InternalEJSL.g:4506:1: rulePackage returns [EObject current=null] : ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' ) ;
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
            // InternalEJSL.g:4512:2: ( ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' ) )
            // InternalEJSL.g:4513:2: ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' )
            {
            // InternalEJSL.g:4513:2: ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' )
            // InternalEJSL.g:4514:3: () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}'
            {
            // InternalEJSL.g:4514:3: ()
            // InternalEJSL.g:4515:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPackageAccess().getPackageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,92,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getPackageAccess().getPackageKeyword_1());
            		
            // InternalEJSL.g:4525:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4526:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4526:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4527:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_109); 

            			newLeafNode(otherlv_3, grammarAccess.getPackageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:4548:3: (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==91) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // InternalEJSL.g:4549:4: otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}'
                    {
                    otherlv_4=(Token)match(input,91,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getPackageAccess().getPackagesKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_108); 

                    				newLeafNode(otherlv_5, grammarAccess.getPackageAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:4557:4: ( (lv_packages_6_0= rulePackage ) )*
                    loop116:
                    do {
                        int alt116=2;
                        int LA116_0 = input.LA(1);

                        if ( (LA116_0==92) ) {
                            alt116=1;
                        }


                        switch (alt116) {
                    	case 1 :
                    	    // InternalEJSL.g:4558:5: (lv_packages_6_0= rulePackage )
                    	    {
                    	    // InternalEJSL.g:4558:5: (lv_packages_6_0= rulePackage )
                    	    // InternalEJSL.g:4559:6: lv_packages_6_0= rulePackage
                    	    {

                    	    						newCompositeNode(grammarAccess.getPackageAccess().getPackagesPackageParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_108);
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
                    	    break loop116;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,19,FOLLOW_110); 

                    				newLeafNode(otherlv_7, grammarAccess.getPackageAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:4581:3: (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==90) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // InternalEJSL.g:4582:4: otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,90,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getPackageAccess().getClassesKeyword_5_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_106); 

                    				newLeafNode(otherlv_9, grammarAccess.getPackageAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:4590:4: ( (lv_classes_10_0= ruleClass ) )*
                    loop118:
                    do {
                        int alt118=2;
                        int LA118_0 = input.LA(1);

                        if ( (LA118_0==93) ) {
                            alt118=1;
                        }


                        switch (alt118) {
                    	case 1 :
                    	    // InternalEJSL.g:4591:5: (lv_classes_10_0= ruleClass )
                    	    {
                    	    // InternalEJSL.g:4591:5: (lv_classes_10_0= ruleClass )
                    	    // InternalEJSL.g:4592:6: lv_classes_10_0= ruleClass
                    	    {

                    	    						newCompositeNode(grammarAccess.getPackageAccess().getClassesClassParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_106);
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
                    	    break loop118;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_11, grammarAccess.getPackageAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            otherlv_12=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:4622:1: entryRuleClass returns [EObject current=null] : iv_ruleClass= ruleClass EOF ;
    public final EObject entryRuleClass() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClass = null;


        try {
            // InternalEJSL.g:4622:46: (iv_ruleClass= ruleClass EOF )
            // InternalEJSL.g:4623:2: iv_ruleClass= ruleClass EOF
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
    // InternalEJSL.g:4629:1: ruleClass returns [EObject current=null] : ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
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
            // InternalEJSL.g:4635:2: ( ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // InternalEJSL.g:4636:2: ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // InternalEJSL.g:4636:2: ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // InternalEJSL.g:4637:3: () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            // InternalEJSL.g:4637:3: ()
            // InternalEJSL.g:4638:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getClassAccess().getClassAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,93,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getClassAccess().getClassKeyword_1());
            		
            // InternalEJSL.g:4648:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4649:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4649:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4650:5: lv_name_2_0= ruleMYID
            {

            					newCompositeNode(grammarAccess.getClassAccess().getNameMYIDParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_41);
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

            // InternalEJSL.g:4667:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==41) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // InternalEJSL.g:4668:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {
                    otherlv_3=(Token)match(input,41,FOLLOW_40); 

                    				newLeafNode(otherlv_3, grammarAccess.getClassAccess().getExtendsKeyword_3_0());
                    			
                    // InternalEJSL.g:4672:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:4673:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:4673:5: ( ruleQualifiedName )
                    // InternalEJSL.g:4674:6: ruleQualifiedName
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

            otherlv_5=(Token)match(input,16,FOLLOW_111); 

            			newLeafNode(otherlv_5, grammarAccess.getClassAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:4693:3: (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==94) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // InternalEJSL.g:4694:4: otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    {
                    otherlv_6=(Token)match(input,94,FOLLOW_40); 

                    				newLeafNode(otherlv_6, grammarAccess.getClassAccess().getClassReferencesKeyword_5_0());
                    			
                    // InternalEJSL.g:4698:4: ( ( ruleQualifiedName ) )
                    // InternalEJSL.g:4699:5: ( ruleQualifiedName )
                    {
                    // InternalEJSL.g:4699:5: ( ruleQualifiedName )
                    // InternalEJSL.g:4700:6: ruleQualifiedName
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassRule());
                    						}
                    					

                    						newCompositeNode(grammarAccess.getClassAccess().getReferencesClassCrossReference_5_1_0());
                    					
                    pushFollow(FOLLOW_112);
                    ruleQualifiedName();

                    state._fsp--;


                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalEJSL.g:4714:4: (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    loop121:
                    do {
                        int alt121=2;
                        int LA121_0 = input.LA(1);

                        if ( (LA121_0==18) ) {
                            alt121=1;
                        }


                        switch (alt121) {
                    	case 1 :
                    	    // InternalEJSL.g:4715:5: otherlv_8= ',' ( ( ruleQualifiedName ) )
                    	    {
                    	    otherlv_8=(Token)match(input,18,FOLLOW_40); 

                    	    					newLeafNode(otherlv_8, grammarAccess.getClassAccess().getCommaKeyword_5_2_0());
                    	    				
                    	    // InternalEJSL.g:4719:5: ( ( ruleQualifiedName ) )
                    	    // InternalEJSL.g:4720:6: ( ruleQualifiedName )
                    	    {
                    	    // InternalEJSL.g:4720:6: ( ruleQualifiedName )
                    	    // InternalEJSL.g:4721:7: ruleQualifiedName
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getClassRule());
                    	    							}
                    	    						

                    	    							newCompositeNode(grammarAccess.getClassAccess().getReferencesClassCrossReference_5_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_112);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop121;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:4737:3: (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==59) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // InternalEJSL.g:4738:4: otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )*
                    {
                    otherlv_10=(Token)match(input,59,FOLLOW_3); 

                    				newLeafNode(otherlv_10, grammarAccess.getClassAccess().getEntitiesKeyword_6_0());
                    			
                    // InternalEJSL.g:4742:4: ( (otherlv_11= RULE_STRING ) )
                    // InternalEJSL.g:4743:5: (otherlv_11= RULE_STRING )
                    {
                    // InternalEJSL.g:4743:5: (otherlv_11= RULE_STRING )
                    // InternalEJSL.g:4744:6: otherlv_11= RULE_STRING
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getClassRule());
                    						}
                    					
                    otherlv_11=(Token)match(input,RULE_STRING,FOLLOW_113); 

                    						newLeafNode(otherlv_11, grammarAccess.getClassAccess().getEntitiesEntityCrossReference_6_1_0());
                    					

                    }


                    }

                    // InternalEJSL.g:4755:4: (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )*
                    loop123:
                    do {
                        int alt123=2;
                        int LA123_0 = input.LA(1);

                        if ( (LA123_0==18) ) {
                            alt123=1;
                        }


                        switch (alt123) {
                    	case 1 :
                    	    // InternalEJSL.g:4756:5: otherlv_12= ',' ( (otherlv_13= RULE_STRING ) )
                    	    {
                    	    otherlv_12=(Token)match(input,18,FOLLOW_3); 

                    	    					newLeafNode(otherlv_12, grammarAccess.getClassAccess().getCommaKeyword_6_2_0());
                    	    				
                    	    // InternalEJSL.g:4760:5: ( (otherlv_13= RULE_STRING ) )
                    	    // InternalEJSL.g:4761:6: (otherlv_13= RULE_STRING )
                    	    {
                    	    // InternalEJSL.g:4761:6: (otherlv_13= RULE_STRING )
                    	    // InternalEJSL.g:4762:7: otherlv_13= RULE_STRING
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getClassRule());
                    	    							}
                    	    						
                    	    otherlv_13=(Token)match(input,RULE_STRING,FOLLOW_113); 

                    	    							newLeafNode(otherlv_13, grammarAccess.getClassAccess().getEntitiesEntityCrossReference_6_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop123;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalEJSL.g:4775:3: (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==95) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // InternalEJSL.g:4776:4: otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}'
                    {
                    otherlv_14=(Token)match(input,95,FOLLOW_4); 

                    				newLeafNode(otherlv_14, grammarAccess.getClassAccess().getMethodsKeyword_7_0());
                    			
                    otherlv_15=(Token)match(input,16,FOLLOW_114); 

                    				newLeafNode(otherlv_15, grammarAccess.getClassAccess().getLeftCurlyBracketKeyword_7_1());
                    			
                    // InternalEJSL.g:4784:4: ( (lv_methods_16_0= ruleMethod ) )*
                    loop125:
                    do {
                        int alt125=2;
                        int LA125_0 = input.LA(1);

                        if ( (LA125_0==96) ) {
                            alt125=1;
                        }


                        switch (alt125) {
                    	case 1 :
                    	    // InternalEJSL.g:4785:5: (lv_methods_16_0= ruleMethod )
                    	    {
                    	    // InternalEJSL.g:4785:5: (lv_methods_16_0= ruleMethod )
                    	    // InternalEJSL.g:4786:6: lv_methods_16_0= ruleMethod
                    	    {

                    	    						newCompositeNode(grammarAccess.getClassAccess().getMethodsMethodParserRuleCall_7_2_0());
                    	    					
                    	    pushFollow(FOLLOW_114);
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
                    	    break loop125;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_17, grammarAccess.getClassAccess().getRightCurlyBracketKeyword_7_3());
                    			

                    }
                    break;

            }

            otherlv_18=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:4816:1: entryRuleMethod returns [EObject current=null] : iv_ruleMethod= ruleMethod EOF ;
    public final EObject entryRuleMethod() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethod = null;


        try {
            // InternalEJSL.g:4816:47: (iv_ruleMethod= ruleMethod EOF )
            // InternalEJSL.g:4817:2: iv_ruleMethod= ruleMethod EOF
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
    // InternalEJSL.g:4823:1: ruleMethod returns [EObject current=null] : ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' ) ;
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
            // InternalEJSL.g:4829:2: ( ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' ) )
            // InternalEJSL.g:4830:2: ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' )
            {
            // InternalEJSL.g:4830:2: ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' )
            // InternalEJSL.g:4831:3: () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}'
            {
            // InternalEJSL.g:4831:3: ()
            // InternalEJSL.g:4832:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getMethodAccess().getMethodAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,96,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getMethodAccess().getMethodKeyword_1());
            		
            // InternalEJSL.g:4842:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:4843:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:4843:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:4844:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_115); 

            			newLeafNode(otherlv_3, grammarAccess.getMethodAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:4865:3: (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==97) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // InternalEJSL.g:4866:4: otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) )
                    {
                    otherlv_4=(Token)match(input,97,FOLLOW_25); 

                    				newLeafNode(otherlv_4, grammarAccess.getMethodAccess().getReturnvalueKeyword_4_0());
                    			
                    // InternalEJSL.g:4870:4: ( (lv_returnvalue_5_0= RULE_ID ) )
                    // InternalEJSL.g:4871:5: (lv_returnvalue_5_0= RULE_ID )
                    {
                    // InternalEJSL.g:4871:5: (lv_returnvalue_5_0= RULE_ID )
                    // InternalEJSL.g:4872:6: lv_returnvalue_5_0= RULE_ID
                    {
                    lv_returnvalue_5_0=(Token)match(input,RULE_ID,FOLLOW_72); 

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

                    otherlv_6=(Token)match(input,64,FOLLOW_27); 

                    				newLeafNode(otherlv_6, grammarAccess.getMethodAccess().getColonKeyword_4_2());
                    			
                    // InternalEJSL.g:4892:4: ( (lv_type_7_0= ruleType ) )
                    // InternalEJSL.g:4893:5: (lv_type_7_0= ruleType )
                    {
                    // InternalEJSL.g:4893:5: (lv_type_7_0= ruleType )
                    // InternalEJSL.g:4894:6: lv_type_7_0= ruleType
                    {

                    						newCompositeNode(grammarAccess.getMethodAccess().getTypeTypeParserRuleCall_4_3_0());
                    					
                    pushFollow(FOLLOW_116);
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

            // InternalEJSL.g:4912:3: (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==98) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // InternalEJSL.g:4913:4: otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,98,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getMethodAccess().getMethodparametersKeyword_5_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_117); 

                    				newLeafNode(otherlv_9, grammarAccess.getMethodAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:4921:4: ( (lv_methodparameters_10_0= ruleMethodParameter ) )*
                    loop128:
                    do {
                        int alt128=2;
                        int LA128_0 = input.LA(1);

                        if ( (LA128_0==99) ) {
                            alt128=1;
                        }


                        switch (alt128) {
                    	case 1 :
                    	    // InternalEJSL.g:4922:5: (lv_methodparameters_10_0= ruleMethodParameter )
                    	    {
                    	    // InternalEJSL.g:4922:5: (lv_methodparameters_10_0= ruleMethodParameter )
                    	    // InternalEJSL.g:4923:6: lv_methodparameters_10_0= ruleMethodParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getMethodAccess().getMethodparametersMethodParameterParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_117);
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
                    	    break loop128;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_11, grammarAccess.getMethodAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            otherlv_12=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:4953:1: entryRuleMethodParameter returns [EObject current=null] : iv_ruleMethodParameter= ruleMethodParameter EOF ;
    public final EObject entryRuleMethodParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMethodParameter = null;


        try {
            // InternalEJSL.g:4953:56: (iv_ruleMethodParameter= ruleMethodParameter EOF )
            // InternalEJSL.g:4954:2: iv_ruleMethodParameter= ruleMethodParameter EOF
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
    // InternalEJSL.g:4960:1: ruleMethodParameter returns [EObject current=null] : ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) ;
    public final EObject ruleMethodParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        EObject lv_type_4_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:4966:2: ( ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) )
            // InternalEJSL.g:4967:2: ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            {
            // InternalEJSL.g:4967:2: ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            // InternalEJSL.g:4968:3: () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) )
            {
            // InternalEJSL.g:4968:3: ()
            // InternalEJSL.g:4969:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getMethodParameterAccess().getMethodParameterAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,99,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getMethodParameterAccess().getMethodParameterKeyword_1());
            		
            // InternalEJSL.g:4979:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:4980:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:4980:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:4981:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_72); 

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

            otherlv_3=(Token)match(input,64,FOLLOW_27); 

            			newLeafNode(otherlv_3, grammarAccess.getMethodParameterAccess().getColonKeyword_3());
            		
            // InternalEJSL.g:5001:3: ( (lv_type_4_0= ruleType ) )
            // InternalEJSL.g:5002:4: (lv_type_4_0= ruleType )
            {
            // InternalEJSL.g:5002:4: (lv_type_4_0= ruleType )
            // InternalEJSL.g:5003:5: lv_type_4_0= ruleType
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
    // InternalEJSL.g:5024:1: entryRuleTemplate returns [EObject current=null] : iv_ruleTemplate= ruleTemplate EOF ;
    public final EObject entryRuleTemplate() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplate = null;


        try {
            // InternalEJSL.g:5024:49: (iv_ruleTemplate= ruleTemplate EOF )
            // InternalEJSL.g:5025:2: iv_ruleTemplate= ruleTemplate EOF
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
    // InternalEJSL.g:5031:1: ruleTemplate returns [EObject current=null] : ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' ) ;
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
            // InternalEJSL.g:5037:2: ( ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' ) )
            // InternalEJSL.g:5038:2: ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' )
            {
            // InternalEJSL.g:5038:2: ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' )
            // InternalEJSL.g:5039:3: () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}'
            {
            // InternalEJSL.g:5039:3: ()
            // InternalEJSL.g:5040:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTemplateAccess().getTemplateAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,100,FOLLOW_40); 

            			newLeafNode(otherlv_1, grammarAccess.getTemplateAccess().getTemplateKeyword_1());
            		
            // InternalEJSL.g:5050:3: ( (lv_name_2_0= ruleMYID ) )
            // InternalEJSL.g:5051:4: (lv_name_2_0= ruleMYID )
            {
            // InternalEJSL.g:5051:4: (lv_name_2_0= ruleMYID )
            // InternalEJSL.g:5052:5: lv_name_2_0= ruleMYID
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

            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			newLeafNode(otherlv_3, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_3());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getTemplateAccess().getManifestationKeyword_4());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			newLeafNode(otherlv_5, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalEJSL.g:5081:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // InternalEJSL.g:5082:4: (lv_manifest_6_0= ruleManifestation )
            {
            // InternalEJSL.g:5082:4: (lv_manifest_6_0= ruleManifestation )
            // InternalEJSL.g:5083:5: lv_manifest_6_0= ruleManifestation
            {

            					newCompositeNode(grammarAccess.getTemplateAccess().getManifestManifestationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_20);
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

            otherlv_7=(Token)match(input,19,FOLLOW_118); 

            			newLeafNode(otherlv_7, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalEJSL.g:5104:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==76) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // InternalEJSL.g:5105:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {
                    otherlv_8=(Token)match(input,76,FOLLOW_4); 

                    				newLeafNode(otherlv_8, grammarAccess.getTemplateAccess().getLanguagesKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_85); 

                    				newLeafNode(otherlv_9, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_8_1());
                    			
                    // InternalEJSL.g:5113:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop130:
                    do {
                        int alt130=2;
                        int LA130_0 = input.LA(1);

                        if ( (LA130_0==112) ) {
                            alt130=1;
                        }


                        switch (alt130) {
                    	case 1 :
                    	    // InternalEJSL.g:5114:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // InternalEJSL.g:5114:5: (lv_languages_10_0= ruleLanguage )
                    	    // InternalEJSL.g:5115:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						newCompositeNode(grammarAccess.getTemplateAccess().getLanguagesLanguageParserRuleCall_8_2_0());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop130;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FOLLOW_119); 

                    				newLeafNode(otherlv_11, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_8_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:5137:3: (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==88) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // InternalEJSL.g:5138:4: otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}'
                    {
                    otherlv_12=(Token)match(input,88,FOLLOW_4); 

                    				newLeafNode(otherlv_12, grammarAccess.getTemplateAccess().getParametersKeyword_9_0());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_9); 

                    				newLeafNode(otherlv_13, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_9_1());
                    			
                    // InternalEJSL.g:5146:4: ( (lv_localparameters_14_0= ruleParameter ) )*
                    loop132:
                    do {
                        int alt132=2;
                        int LA132_0 = input.LA(1);

                        if ( (LA132_0==31) ) {
                            alt132=1;
                        }


                        switch (alt132) {
                    	case 1 :
                    	    // InternalEJSL.g:5147:5: (lv_localparameters_14_0= ruleParameter )
                    	    {
                    	    // InternalEJSL.g:5147:5: (lv_localparameters_14_0= ruleParameter )
                    	    // InternalEJSL.g:5148:6: lv_localparameters_14_0= ruleParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getTemplateAccess().getLocalparametersParameterParserRuleCall_9_2_0());
                    	    					
                    	    pushFollow(FOLLOW_9);
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
                    	    break loop132;
                        }
                    } while (true);

                    otherlv_15=(Token)match(input,19,FOLLOW_120); 

                    				newLeafNode(otherlv_15, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_9_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:5170:3: (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==101) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // InternalEJSL.g:5171:4: otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,101,FOLLOW_4); 

                    				newLeafNode(otherlv_16, grammarAccess.getTemplateAccess().getPositionsKeyword_10_0());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_121); 

                    				newLeafNode(otherlv_17, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_10_1());
                    			
                    // InternalEJSL.g:5179:4: ( (lv_positions_18_0= rulePosition ) )*
                    loop134:
                    do {
                        int alt134=2;
                        int LA134_0 = input.LA(1);

                        if ( (LA134_0==115) ) {
                            alt134=1;
                        }


                        switch (alt134) {
                    	case 1 :
                    	    // InternalEJSL.g:5180:5: (lv_positions_18_0= rulePosition )
                    	    {
                    	    // InternalEJSL.g:5180:5: (lv_positions_18_0= rulePosition )
                    	    // InternalEJSL.g:5181:6: lv_positions_18_0= rulePosition
                    	    {

                    	    						newCompositeNode(grammarAccess.getTemplateAccess().getPositionsPositionParserRuleCall_10_2_0());
                    	    					
                    	    pushFollow(FOLLOW_121);
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
                    	    break loop134;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,19,FOLLOW_122); 

                    				newLeafNode(otherlv_19, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_10_3());
                    			

                    }
                    break;

            }

            // InternalEJSL.g:5203:3: (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==102) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // InternalEJSL.g:5204:4: otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}'
                    {
                    otherlv_20=(Token)match(input,102,FOLLOW_4); 

                    				newLeafNode(otherlv_20, grammarAccess.getTemplateAccess().getCssblocksKeyword_11_0());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_123); 

                    				newLeafNode(otherlv_21, grammarAccess.getTemplateAccess().getLeftCurlyBracketKeyword_11_1());
                    			
                    // InternalEJSL.g:5212:4: ( (lv_cssblocks_22_0= ruleCssBlock ) )*
                    loop136:
                    do {
                        int alt136=2;
                        int LA136_0 = input.LA(1);

                        if ( (LA136_0==121) ) {
                            alt136=1;
                        }


                        switch (alt136) {
                    	case 1 :
                    	    // InternalEJSL.g:5213:5: (lv_cssblocks_22_0= ruleCssBlock )
                    	    {
                    	    // InternalEJSL.g:5213:5: (lv_cssblocks_22_0= ruleCssBlock )
                    	    // InternalEJSL.g:5214:6: lv_cssblocks_22_0= ruleCssBlock
                    	    {

                    	    						newCompositeNode(grammarAccess.getTemplateAccess().getCssblocksCssBlockParserRuleCall_11_2_0());
                    	    					
                    	    pushFollow(FOLLOW_123);
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
                    	    break loop136;
                        }
                    } while (true);

                    otherlv_23=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_23, grammarAccess.getTemplateAccess().getRightCurlyBracketKeyword_11_3());
                    			

                    }
                    break;

            }

            otherlv_24=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:5244:1: entryRuleManifestation returns [EObject current=null] : iv_ruleManifestation= ruleManifestation EOF ;
    public final EObject entryRuleManifestation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleManifestation = null;


        try {
            // InternalEJSL.g:5244:54: (iv_ruleManifestation= ruleManifestation EOF )
            // InternalEJSL.g:5245:2: iv_ruleManifestation= ruleManifestation EOF
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
    // InternalEJSL.g:5251:1: ruleManifestation returns [EObject current=null] : ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )? ) ;
    public final EObject ruleManifestation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_creationdate_7_0=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token lv_copyright_10_0=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token lv_license_13_0=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token lv_link_16_0=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token lv_version_19_0=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token lv_description_22_0=null;
        EObject lv_authors_3_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:5257:2: ( ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )? ) )
            // InternalEJSL.g:5258:2: ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )? )
            {
            // InternalEJSL.g:5258:2: ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )? )
            // InternalEJSL.g:5259:3: () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )?
            {
            // InternalEJSL.g:5259:3: ()
            // InternalEJSL.g:5260:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getManifestationAccess().getManifestationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,103,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getManifestationAccess().getAuthorsKeyword_1());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_124); 

            			newLeafNode(otherlv_2, grammarAccess.getManifestationAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalEJSL.g:5274:3: ( (lv_authors_3_0= ruleAuthor ) )+
            int cnt138=0;
            loop138:
            do {
                int alt138=2;
                int LA138_0 = input.LA(1);

                if ( (LA138_0==109) ) {
                    alt138=1;
                }


                switch (alt138) {
            	case 1 :
            	    // InternalEJSL.g:5275:4: (lv_authors_3_0= ruleAuthor )
            	    {
            	    // InternalEJSL.g:5275:4: (lv_authors_3_0= ruleAuthor )
            	    // InternalEJSL.g:5276:5: lv_authors_3_0= ruleAuthor
            	    {

            	    					newCompositeNode(grammarAccess.getManifestationAccess().getAuthorsAuthorParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_125);
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
            	    if ( cnt138 >= 1 ) break loop138;
                        EarlyExitException eee =
                            new EarlyExitException(138, input);
                        throw eee;
                }
                cnt138++;
            } while (true);

            otherlv_4=(Token)match(input,19,FOLLOW_126); 

            			newLeafNode(otherlv_4, grammarAccess.getManifestationAccess().getRightCurlyBracketKeyword_4());
            		
            // InternalEJSL.g:5297:3: (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==104) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // InternalEJSL.g:5298:4: otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) )
                    {
                    otherlv_5=(Token)match(input,104,FOLLOW_23); 

                    				newLeafNode(otherlv_5, grammarAccess.getManifestationAccess().getCreationdateKeyword_5_0());
                    			
                    otherlv_6=(Token)match(input,28,FOLLOW_127); 

                    				newLeafNode(otherlv_6, grammarAccess.getManifestationAccess().getEqualsSignKeyword_5_1());
                    			
                    // InternalEJSL.g:5306:4: ( (lv_creationdate_7_0= RULE_DATE ) )
                    // InternalEJSL.g:5307:5: (lv_creationdate_7_0= RULE_DATE )
                    {
                    // InternalEJSL.g:5307:5: (lv_creationdate_7_0= RULE_DATE )
                    // InternalEJSL.g:5308:6: lv_creationdate_7_0= RULE_DATE
                    {
                    lv_creationdate_7_0=(Token)match(input,RULE_DATE,FOLLOW_128); 

                    						newLeafNode(lv_creationdate_7_0, grammarAccess.getManifestationAccess().getCreationdateDATETerminalRuleCall_5_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"creationdate",
                    							lv_creationdate_7_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.DATE");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5325:3: (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==105) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // InternalEJSL.g:5326:4: otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,105,FOLLOW_23); 

                    				newLeafNode(otherlv_8, grammarAccess.getManifestationAccess().getCopyrightKeyword_6_0());
                    			
                    otherlv_9=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_9, grammarAccess.getManifestationAccess().getEqualsSignKeyword_6_1());
                    			
                    // InternalEJSL.g:5334:4: ( (lv_copyright_10_0= RULE_STRING ) )
                    // InternalEJSL.g:5335:5: (lv_copyright_10_0= RULE_STRING )
                    {
                    // InternalEJSL.g:5335:5: (lv_copyright_10_0= RULE_STRING )
                    // InternalEJSL.g:5336:6: lv_copyright_10_0= RULE_STRING
                    {
                    lv_copyright_10_0=(Token)match(input,RULE_STRING,FOLLOW_129); 

                    						newLeafNode(lv_copyright_10_0, grammarAccess.getManifestationAccess().getCopyrightSTRINGTerminalRuleCall_6_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"copyright",
                    							lv_copyright_10_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5353:3: (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==106) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // InternalEJSL.g:5354:4: otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) )
                    {
                    otherlv_11=(Token)match(input,106,FOLLOW_23); 

                    				newLeafNode(otherlv_11, grammarAccess.getManifestationAccess().getLicenseKeyword_7_0());
                    			
                    otherlv_12=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_12, grammarAccess.getManifestationAccess().getEqualsSignKeyword_7_1());
                    			
                    // InternalEJSL.g:5362:4: ( (lv_license_13_0= RULE_STRING ) )
                    // InternalEJSL.g:5363:5: (lv_license_13_0= RULE_STRING )
                    {
                    // InternalEJSL.g:5363:5: (lv_license_13_0= RULE_STRING )
                    // InternalEJSL.g:5364:6: lv_license_13_0= RULE_STRING
                    {
                    lv_license_13_0=(Token)match(input,RULE_STRING,FOLLOW_130); 

                    						newLeafNode(lv_license_13_0, grammarAccess.getManifestationAccess().getLicenseSTRINGTerminalRuleCall_7_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"license",
                    							lv_license_13_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5381:3: (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==107) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // InternalEJSL.g:5382:4: otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) )
                    {
                    otherlv_14=(Token)match(input,107,FOLLOW_23); 

                    				newLeafNode(otherlv_14, grammarAccess.getManifestationAccess().getLinkKeyword_8_0());
                    			
                    otherlv_15=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_15, grammarAccess.getManifestationAccess().getEqualsSignKeyword_8_1());
                    			
                    // InternalEJSL.g:5390:4: ( (lv_link_16_0= RULE_STRING ) )
                    // InternalEJSL.g:5391:5: (lv_link_16_0= RULE_STRING )
                    {
                    // InternalEJSL.g:5391:5: (lv_link_16_0= RULE_STRING )
                    // InternalEJSL.g:5392:6: lv_link_16_0= RULE_STRING
                    {
                    lv_link_16_0=(Token)match(input,RULE_STRING,FOLLOW_131); 

                    						newLeafNode(lv_link_16_0, grammarAccess.getManifestationAccess().getLinkSTRINGTerminalRuleCall_8_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"link",
                    							lv_link_16_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5409:3: (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==108) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // InternalEJSL.g:5410:4: otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) )
                    {
                    otherlv_17=(Token)match(input,108,FOLLOW_23); 

                    				newLeafNode(otherlv_17, grammarAccess.getManifestationAccess().getVersionKeyword_9_0());
                    			
                    otherlv_18=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_18, grammarAccess.getManifestationAccess().getEqualsSignKeyword_9_1());
                    			
                    // InternalEJSL.g:5418:4: ( (lv_version_19_0= RULE_STRING ) )
                    // InternalEJSL.g:5419:5: (lv_version_19_0= RULE_STRING )
                    {
                    // InternalEJSL.g:5419:5: (lv_version_19_0= RULE_STRING )
                    // InternalEJSL.g:5420:6: lv_version_19_0= RULE_STRING
                    {
                    lv_version_19_0=(Token)match(input,RULE_STRING,FOLLOW_132); 

                    						newLeafNode(lv_version_19_0, grammarAccess.getManifestationAccess().getVersionSTRINGTerminalRuleCall_9_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"version",
                    							lv_version_19_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5437:3: (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==36) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // InternalEJSL.g:5438:4: otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) )
                    {
                    otherlv_20=(Token)match(input,36,FOLLOW_23); 

                    				newLeafNode(otherlv_20, grammarAccess.getManifestationAccess().getDescriptionKeyword_10_0());
                    			
                    otherlv_21=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_21, grammarAccess.getManifestationAccess().getEqualsSignKeyword_10_1());
                    			
                    // InternalEJSL.g:5446:4: ( (lv_description_22_0= RULE_STRING ) )
                    // InternalEJSL.g:5447:5: (lv_description_22_0= RULE_STRING )
                    {
                    // InternalEJSL.g:5447:5: (lv_description_22_0= RULE_STRING )
                    // InternalEJSL.g:5448:6: lv_description_22_0= RULE_STRING
                    {
                    lv_description_22_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_description_22_0, grammarAccess.getManifestationAccess().getDescriptionSTRINGTerminalRuleCall_10_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getManifestationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"description",
                    							lv_description_22_0,
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
    // InternalEJSL.g:5469:1: entryRuleAuthor returns [EObject current=null] : iv_ruleAuthor= ruleAuthor EOF ;
    public final EObject entryRuleAuthor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAuthor = null;


        try {
            // InternalEJSL.g:5469:47: (iv_ruleAuthor= ruleAuthor EOF )
            // InternalEJSL.g:5470:2: iv_ruleAuthor= ruleAuthor EOF
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
    // InternalEJSL.g:5476:1: ruleAuthor returns [EObject current=null] : ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}' ) ;
    public final EObject ruleAuthor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_authoremail_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token lv_authorurl_9_0=null;
        Token otherlv_10=null;


        	enterRule();

        try {
            // InternalEJSL.g:5482:2: ( ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}' ) )
            // InternalEJSL.g:5483:2: ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}' )
            {
            // InternalEJSL.g:5483:2: ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}' )
            // InternalEJSL.g:5484:3: () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}'
            {
            // InternalEJSL.g:5484:3: ()
            // InternalEJSL.g:5485:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAuthorAccess().getAuthorAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,109,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getAuthorAccess().getAuthorKeyword_1());
            		
            // InternalEJSL.g:5495:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalEJSL.g:5496:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalEJSL.g:5496:4: (lv_name_2_0= RULE_STRING )
            // InternalEJSL.g:5497:5: lv_name_2_0= RULE_STRING
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

            otherlv_3=(Token)match(input,16,FOLLOW_133); 

            			newLeafNode(otherlv_3, grammarAccess.getAuthorAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:5517:3: (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==110) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // InternalEJSL.g:5518:4: otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,110,FOLLOW_23); 

                    				newLeafNode(otherlv_4, grammarAccess.getAuthorAccess().getAuthoremailKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_5, grammarAccess.getAuthorAccess().getEqualsSignKeyword_4_1());
                    			
                    // InternalEJSL.g:5526:4: ( (lv_authoremail_6_0= RULE_STRING ) )
                    // InternalEJSL.g:5527:5: (lv_authoremail_6_0= RULE_STRING )
                    {
                    // InternalEJSL.g:5527:5: (lv_authoremail_6_0= RULE_STRING )
                    // InternalEJSL.g:5528:6: lv_authoremail_6_0= RULE_STRING
                    {
                    lv_authoremail_6_0=(Token)match(input,RULE_STRING,FOLLOW_134); 

                    						newLeafNode(lv_authoremail_6_0, grammarAccess.getAuthorAccess().getAuthoremailSTRINGTerminalRuleCall_4_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAuthorRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"authoremail",
                    							lv_authoremail_6_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5545:3: (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==111) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // InternalEJSL.g:5546:4: otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,111,FOLLOW_23); 

                    				newLeafNode(otherlv_7, grammarAccess.getAuthorAccess().getAuthorurlKeyword_5_0());
                    			
                    otherlv_8=(Token)match(input,28,FOLLOW_3); 

                    				newLeafNode(otherlv_8, grammarAccess.getAuthorAccess().getEqualsSignKeyword_5_1());
                    			
                    // InternalEJSL.g:5554:4: ( (lv_authorurl_9_0= RULE_STRING ) )
                    // InternalEJSL.g:5555:5: (lv_authorurl_9_0= RULE_STRING )
                    {
                    // InternalEJSL.g:5555:5: (lv_authorurl_9_0= RULE_STRING )
                    // InternalEJSL.g:5556:6: lv_authorurl_9_0= RULE_STRING
                    {
                    lv_authorurl_9_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

                    						newLeafNode(lv_authorurl_9_0, grammarAccess.getAuthorAccess().getAuthorurlSTRINGTerminalRuleCall_5_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAuthorRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"authorurl",
                    							lv_authorurl_9_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getAuthorAccess().getRightCurlyBracketKeyword_6());
            		

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
    // InternalEJSL.g:5581:1: entryRuleLanguage returns [EObject current=null] : iv_ruleLanguage= ruleLanguage EOF ;
    public final EObject entryRuleLanguage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLanguage = null;


        try {
            // InternalEJSL.g:5581:49: (iv_ruleLanguage= ruleLanguage EOF )
            // InternalEJSL.g:5582:2: iv_ruleLanguage= ruleLanguage EOF
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
    // InternalEJSL.g:5588:1: ruleLanguage returns [EObject current=null] : ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' ) ;
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
            // InternalEJSL.g:5594:2: ( ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' ) )
            // InternalEJSL.g:5595:2: ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' )
            {
            // InternalEJSL.g:5595:2: ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' )
            // InternalEJSL.g:5596:3: () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}'
            {
            // InternalEJSL.g:5596:3: ()
            // InternalEJSL.g:5597:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getLanguageAccess().getLanguageAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,112,FOLLOW_135); 

            			newLeafNode(otherlv_1, grammarAccess.getLanguageAccess().getLanguageKeyword_1());
            		
            // InternalEJSL.g:5607:3: ( (lv_name_2_0= RULE_LANGUAGE_CODE ) )
            // InternalEJSL.g:5608:4: (lv_name_2_0= RULE_LANGUAGE_CODE )
            {
            // InternalEJSL.g:5608:4: (lv_name_2_0= RULE_LANGUAGE_CODE )
            // InternalEJSL.g:5609:5: lv_name_2_0= RULE_LANGUAGE_CODE
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

            otherlv_3=(Token)match(input,16,FOLLOW_136); 

            			newLeafNode(otherlv_3, grammarAccess.getLanguageAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:5629:3: (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==113) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // InternalEJSL.g:5630:4: otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}'
                    {
                    otherlv_4=(Token)match(input,113,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getLanguageAccess().getKeyvaluepairsKeyword_4_0());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_137); 

                    				newLeafNode(otherlv_5, grammarAccess.getLanguageAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:5638:4: ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==114) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // InternalEJSL.g:5639:5: (lv_keyvaluepairs_6_0= ruleKeyValuePair )
                    	    {
                    	    // InternalEJSL.g:5639:5: (lv_keyvaluepairs_6_0= ruleKeyValuePair )
                    	    // InternalEJSL.g:5640:6: lv_keyvaluepairs_6_0= ruleKeyValuePair
                    	    {

                    	    						newCompositeNode(grammarAccess.getLanguageAccess().getKeyvaluepairsKeyValuePairParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_137);
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
                    	    break loop147;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_7, grammarAccess.getLanguageAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            otherlv_8=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:5670:1: entryRuleKeyValuePair returns [EObject current=null] : iv_ruleKeyValuePair= ruleKeyValuePair EOF ;
    public final EObject entryRuleKeyValuePair() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeyValuePair = null;


        try {
            // InternalEJSL.g:5670:53: (iv_ruleKeyValuePair= ruleKeyValuePair EOF )
            // InternalEJSL.g:5671:2: iv_ruleKeyValuePair= ruleKeyValuePair EOF
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
    // InternalEJSL.g:5677:1: ruleKeyValuePair returns [EObject current=null] : ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) ) ;
    public final EObject ruleKeyValuePair() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_value_4_0=null;


        	enterRule();

        try {
            // InternalEJSL.g:5683:2: ( ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) ) )
            // InternalEJSL.g:5684:2: ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) )
            {
            // InternalEJSL.g:5684:2: ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) )
            // InternalEJSL.g:5685:3: () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) )
            {
            // InternalEJSL.g:5685:3: ()
            // InternalEJSL.g:5686:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getKeyValuePairAccess().getKeyValuePairAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,114,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getKeyValuePairAccess().getKeyKeyword_1());
            		
            // InternalEJSL.g:5696:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalEJSL.g:5697:4: (lv_name_2_0= RULE_ID )
            {
            // InternalEJSL.g:5697:4: (lv_name_2_0= RULE_ID )
            // InternalEJSL.g:5698:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_23); 

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

            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getKeyValuePairAccess().getEqualsSignKeyword_3());
            		
            // InternalEJSL.g:5718:3: ( (lv_value_4_0= RULE_STRING ) )
            // InternalEJSL.g:5719:4: (lv_value_4_0= RULE_STRING )
            {
            // InternalEJSL.g:5719:4: (lv_value_4_0= RULE_STRING )
            // InternalEJSL.g:5720:5: lv_value_4_0= RULE_STRING
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
    // InternalEJSL.g:5740:1: entryRulePosition returns [EObject current=null] : iv_rulePosition= rulePosition EOF ;
    public final EObject entryRulePosition() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePosition = null;


        try {
            // InternalEJSL.g:5740:49: (iv_rulePosition= rulePosition EOF )
            // InternalEJSL.g:5741:2: iv_rulePosition= rulePosition EOF
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
    // InternalEJSL.g:5747:1: rulePosition returns [EObject current=null] : ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' ) ;
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
            // InternalEJSL.g:5753:2: ( ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' ) )
            // InternalEJSL.g:5754:2: ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' )
            {
            // InternalEJSL.g:5754:2: ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' )
            // InternalEJSL.g:5755:3: () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}'
            {
            // InternalEJSL.g:5755:3: ()
            // InternalEJSL.g:5756:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPositionAccess().getPositionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,115,FOLLOW_138); 

            			newLeafNode(otherlv_1, grammarAccess.getPositionAccess().getTemplatepositionKeyword_1());
            		
            // InternalEJSL.g:5766:3: ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) )
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==RULE_POSITION_TYPES) ) {
                alt149=1;
            }
            else if ( (LA149_0==RULE_ID) ) {
                alt149=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 149, 0, input);

                throw nvae;
            }
            switch (alt149) {
                case 1 :
                    // InternalEJSL.g:5767:4: ( (lv_name_2_0= RULE_POSITION_TYPES ) )
                    {
                    // InternalEJSL.g:5767:4: ( (lv_name_2_0= RULE_POSITION_TYPES ) )
                    // InternalEJSL.g:5768:5: (lv_name_2_0= RULE_POSITION_TYPES )
                    {
                    // InternalEJSL.g:5768:5: (lv_name_2_0= RULE_POSITION_TYPES )
                    // InternalEJSL.g:5769:6: lv_name_2_0= RULE_POSITION_TYPES
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
                    // InternalEJSL.g:5786:4: ( (lv_name_3_0= RULE_ID ) )
                    {
                    // InternalEJSL.g:5786:4: ( (lv_name_3_0= RULE_ID ) )
                    // InternalEJSL.g:5787:5: (lv_name_3_0= RULE_ID )
                    {
                    // InternalEJSL.g:5787:5: (lv_name_3_0= RULE_ID )
                    // InternalEJSL.g:5788:6: lv_name_3_0= RULE_ID
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

            otherlv_4=(Token)match(input,16,FOLLOW_139); 

            			newLeafNode(otherlv_4, grammarAccess.getPositionAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalEJSL.g:5809:3: (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==116) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // InternalEJSL.g:5810:4: otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}'
                    {
                    otherlv_5=(Token)match(input,116,FOLLOW_4); 

                    				newLeafNode(otherlv_5, grammarAccess.getPositionAccess().getPositionparametersKeyword_4_0());
                    			
                    otherlv_6=(Token)match(input,16,FOLLOW_140); 

                    				newLeafNode(otherlv_6, grammarAccess.getPositionAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalEJSL.g:5818:4: ( (lv_positionparameters_7_0= rulePositionParameter ) )*
                    loop150:
                    do {
                        int alt150=2;
                        int LA150_0 = input.LA(1);

                        if ( (LA150_0==117) ) {
                            alt150=1;
                        }


                        switch (alt150) {
                    	case 1 :
                    	    // InternalEJSL.g:5819:5: (lv_positionparameters_7_0= rulePositionParameter )
                    	    {
                    	    // InternalEJSL.g:5819:5: (lv_positionparameters_7_0= rulePositionParameter )
                    	    // InternalEJSL.g:5820:6: lv_positionparameters_7_0= rulePositionParameter
                    	    {

                    	    						newCompositeNode(grammarAccess.getPositionAccess().getPositionparametersPositionParameterParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_140);
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
                    	    break loop150;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_8, grammarAccess.getPositionAccess().getRightCurlyBracketKeyword_4_3());
                    			

                    }
                    break;

            }

            otherlv_9=(Token)match(input,19,FOLLOW_2); 

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
    // InternalEJSL.g:5850:1: entryRulePositionParameter returns [EObject current=null] : iv_rulePositionParameter= rulePositionParameter EOF ;
    public final EObject entryRulePositionParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePositionParameter = null;


        try {
            // InternalEJSL.g:5850:58: (iv_rulePositionParameter= rulePositionParameter EOF )
            // InternalEJSL.g:5851:2: iv_rulePositionParameter= rulePositionParameter EOF
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
    // InternalEJSL.g:5857:1: rulePositionParameter returns [EObject current=null] : (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}' ) ;
    public final EObject rulePositionParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_divid_5_0=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token lv_type_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        EObject lv_keyvaluepairs_11_0 = null;



        	enterRule();

        try {
            // InternalEJSL.g:5863:2: ( (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}' ) )
            // InternalEJSL.g:5864:2: (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}' )
            {
            // InternalEJSL.g:5864:2: (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}' )
            // InternalEJSL.g:5865:3: otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}'
            {
            otherlv_0=(Token)match(input,117,FOLLOW_25); 

            			newLeafNode(otherlv_0, grammarAccess.getPositionParameterAccess().getPositionParameterKeyword_0());
            		
            // InternalEJSL.g:5869:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalEJSL.g:5870:4: (lv_name_1_0= RULE_ID )
            {
            // InternalEJSL.g:5870:4: (lv_name_1_0= RULE_ID )
            // InternalEJSL.g:5871:5: lv_name_1_0= RULE_ID
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

            otherlv_2=(Token)match(input,16,FOLLOW_141); 

            			newLeafNode(otherlv_2, grammarAccess.getPositionParameterAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalEJSL.g:5891:3: (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==118) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // InternalEJSL.g:5892:4: otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) )
                    {
                    otherlv_3=(Token)match(input,118,FOLLOW_23); 

                    				newLeafNode(otherlv_3, grammarAccess.getPositionParameterAccess().getDivIdKeyword_3_0());
                    			
                    otherlv_4=(Token)match(input,28,FOLLOW_25); 

                    				newLeafNode(otherlv_4, grammarAccess.getPositionParameterAccess().getEqualsSignKeyword_3_1());
                    			
                    // InternalEJSL.g:5900:4: ( (lv_divid_5_0= RULE_ID ) )
                    // InternalEJSL.g:5901:5: (lv_divid_5_0= RULE_ID )
                    {
                    // InternalEJSL.g:5901:5: (lv_divid_5_0= RULE_ID )
                    // InternalEJSL.g:5902:6: lv_divid_5_0= RULE_ID
                    {
                    lv_divid_5_0=(Token)match(input,RULE_ID,FOLLOW_142); 

                    						newLeafNode(lv_divid_5_0, grammarAccess.getPositionParameterAccess().getDividIDTerminalRuleCall_3_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPositionParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"divid",
                    							lv_divid_5_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5919:3: (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==119) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // InternalEJSL.g:5920:4: otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) )
                    {
                    otherlv_6=(Token)match(input,119,FOLLOW_23); 

                    				newLeafNode(otherlv_6, grammarAccess.getPositionParameterAccess().getPositiontypeKeyword_4_0());
                    			
                    otherlv_7=(Token)match(input,28,FOLLOW_143); 

                    				newLeafNode(otherlv_7, grammarAccess.getPositionParameterAccess().getEqualsSignKeyword_4_1());
                    			
                    // InternalEJSL.g:5928:4: ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) )
                    // InternalEJSL.g:5929:5: (lv_type_8_0= RULE_POSITION_TYPES_NAMES )
                    {
                    // InternalEJSL.g:5929:5: (lv_type_8_0= RULE_POSITION_TYPES_NAMES )
                    // InternalEJSL.g:5930:6: lv_type_8_0= RULE_POSITION_TYPES_NAMES
                    {
                    lv_type_8_0=(Token)match(input,RULE_POSITION_TYPES_NAMES,FOLLOW_144); 

                    						newLeafNode(lv_type_8_0, grammarAccess.getPositionParameterAccess().getTypePOSITION_TYPES_NAMESTerminalRuleCall_4_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPositionParameterRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"type",
                    							lv_type_8_0,
                    							"de.thm.icampus.joomdd.ejsl.EJSL.POSITION_TYPES_NAMES");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalEJSL.g:5947:3: (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==120) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // InternalEJSL.g:5948:4: otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}'
                    {
                    otherlv_9=(Token)match(input,120,FOLLOW_4); 

                    				newLeafNode(otherlv_9, grammarAccess.getPositionParameterAccess().getCSSKeyvaluepairsKeyword_5_0());
                    			
                    otherlv_10=(Token)match(input,16,FOLLOW_137); 

                    				newLeafNode(otherlv_10, grammarAccess.getPositionParameterAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalEJSL.g:5956:4: ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )*
                    loop154:
                    do {
                        int alt154=2;
                        int LA154_0 = input.LA(1);

                        if ( (LA154_0==114) ) {
                            alt154=1;
                        }


                        switch (alt154) {
                    	case 1 :
                    	    // InternalEJSL.g:5957:5: (lv_keyvaluepairs_11_0= ruleKeyValuePair )
                    	    {
                    	    // InternalEJSL.g:5957:5: (lv_keyvaluepairs_11_0= ruleKeyValuePair )
                    	    // InternalEJSL.g:5958:6: lv_keyvaluepairs_11_0= ruleKeyValuePair
                    	    {

                    	    						newCompositeNode(grammarAccess.getPositionParameterAccess().getKeyvaluepairsKeyValuePairParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_137);
                    	    lv_keyvaluepairs_11_0=ruleKeyValuePair();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPositionParameterRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"keyvaluepairs",
                    	    							lv_keyvaluepairs_11_0,
                    	    							"de.thm.icampus.joomdd.ejsl.EJSL.KeyValuePair");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop154;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,19,FOLLOW_20); 

                    				newLeafNode(otherlv_12, grammarAccess.getPositionParameterAccess().getRightCurlyBracketKeyword_5_3());
                    			

                    }
                    break;

            }

            otherlv_13=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_13, grammarAccess.getPositionParameterAccess().getRightCurlyBracketKeyword_6());
            		

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
    // InternalEJSL.g:5988:1: entryRuleCssBlock returns [EObject current=null] : iv_ruleCssBlock= ruleCssBlock EOF ;
    public final EObject entryRuleCssBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCssBlock = null;


        try {
            // InternalEJSL.g:5988:49: (iv_ruleCssBlock= ruleCssBlock EOF )
            // InternalEJSL.g:5989:2: iv_ruleCssBlock= ruleCssBlock EOF
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
    // InternalEJSL.g:5995:1: ruleCssBlock returns [EObject current=null] : (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' ) ;
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
            // InternalEJSL.g:6001:2: ( (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' ) )
            // InternalEJSL.g:6002:2: (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' )
            {
            // InternalEJSL.g:6002:2: (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' )
            // InternalEJSL.g:6003:3: otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')'
            {
            otherlv_0=(Token)match(input,121,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getCssBlockAccess().getCssBlockKeyword_0());
            		
            // InternalEJSL.g:6007:3: ( (lv_selector_1_0= RULE_STRING ) )
            // InternalEJSL.g:6008:4: (lv_selector_1_0= RULE_STRING )
            {
            // InternalEJSL.g:6008:4: (lv_selector_1_0= RULE_STRING )
            // InternalEJSL.g:6009:5: lv_selector_1_0= RULE_STRING
            {
            lv_selector_1_0=(Token)match(input,RULE_STRING,FOLLOW_74); 

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

            otherlv_2=(Token)match(input,65,FOLLOW_145); 

            			newLeafNode(otherlv_2, grammarAccess.getCssBlockAccess().getLeftParenthesisKeyword_2());
            		
            // InternalEJSL.g:6029:3: (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==113) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // InternalEJSL.g:6030:4: otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}'
                    {
                    otherlv_3=(Token)match(input,113,FOLLOW_4); 

                    				newLeafNode(otherlv_3, grammarAccess.getCssBlockAccess().getKeyvaluepairsKeyword_3_0());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_137); 

                    				newLeafNode(otherlv_4, grammarAccess.getCssBlockAccess().getLeftCurlyBracketKeyword_3_1());
                    			
                    // InternalEJSL.g:6038:4: ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )*
                    loop156:
                    do {
                        int alt156=2;
                        int LA156_0 = input.LA(1);

                        if ( (LA156_0==114) ) {
                            alt156=1;
                        }


                        switch (alt156) {
                    	case 1 :
                    	    // InternalEJSL.g:6039:5: (lv_keyvaluepairs_5_0= ruleKeyValuePair )
                    	    {
                    	    // InternalEJSL.g:6039:5: (lv_keyvaluepairs_5_0= ruleKeyValuePair )
                    	    // InternalEJSL.g:6040:6: lv_keyvaluepairs_5_0= ruleKeyValuePair
                    	    {

                    	    						newCompositeNode(grammarAccess.getCssBlockAccess().getKeyvaluepairsKeyValuePairParserRuleCall_3_2_0());
                    	    					
                    	    pushFollow(FOLLOW_137);
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
                    	    break loop156;
                        }
                    } while (true);

                    otherlv_6=(Token)match(input,19,FOLLOW_146); 

                    				newLeafNode(otherlv_6, grammarAccess.getCssBlockAccess().getRightCurlyBracketKeyword_3_3());
                    			

                    }
                    break;

            }

            otherlv_7=(Token)match(input,66,FOLLOW_2); 

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
    // InternalEJSL.g:6070:1: entryRuleNUMBER returns [String current=null] : iv_ruleNUMBER= ruleNUMBER EOF ;
    public final String entryRuleNUMBER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER = null;


        try {
            // InternalEJSL.g:6070:46: (iv_ruleNUMBER= ruleNUMBER EOF )
            // InternalEJSL.g:6071:2: iv_ruleNUMBER= ruleNUMBER EOF
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
    // InternalEJSL.g:6077:1: ruleNUMBER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleNUMBER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;


        	enterRule();

        try {
            // InternalEJSL.g:6083:2: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // InternalEJSL.g:6084:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // InternalEJSL.g:6084:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            // InternalEJSL.g:6085:3: (kw= '-' )? this_INT_1= RULE_INT
            {
            // InternalEJSL.g:6085:3: (kw= '-' )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==122) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // InternalEJSL.g:6086:4: kw= '-'
                    {
                    kw=(Token)match(input,122,FOLLOW_31); 

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
    // InternalEJSL.g:6103:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalEJSL.g:6103:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalEJSL.g:6104:2: iv_ruleQualifiedName= ruleQualifiedName EOF
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
    // InternalEJSL.g:6110:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_MYID_0 = null;

        AntlrDatatypeRuleToken this_MYID_2 = null;



        	enterRule();

        try {
            // InternalEJSL.g:6116:2: ( (this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )* ) )
            // InternalEJSL.g:6117:2: (this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )* )
            {
            // InternalEJSL.g:6117:2: (this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )* )
            // InternalEJSL.g:6118:3: this_MYID_0= ruleMYID (kw= '.' this_MYID_2= ruleMYID )*
            {

            			newCompositeNode(grammarAccess.getQualifiedNameAccess().getMYIDParserRuleCall_0());
            		
            pushFollow(FOLLOW_147);
            this_MYID_0=ruleMYID();

            state._fsp--;


            			current.merge(this_MYID_0);
            		

            			afterParserOrEnumRuleCall();
            		
            // InternalEJSL.g:6128:3: (kw= '.' this_MYID_2= ruleMYID )*
            loop159:
            do {
                int alt159=2;
                int LA159_0 = input.LA(1);

                if ( (LA159_0==123) ) {
                    alt159=1;
                }


                switch (alt159) {
            	case 1 :
            	    // InternalEJSL.g:6129:4: kw= '.' this_MYID_2= ruleMYID
            	    {
            	    kw=(Token)match(input,123,FOLLOW_40); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	    			

            	    				newCompositeNode(grammarAccess.getQualifiedNameAccess().getMYIDParserRuleCall_1_1());
            	    			
            	    pushFollow(FOLLOW_147);
            	    this_MYID_2=ruleMYID();

            	    state._fsp--;


            	    				current.merge(this_MYID_2);
            	    			

            	    				afterParserOrEnumRuleCall();
            	    			

            	    }
            	    break;

            	default :
            	    break loop159;
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
    // InternalEJSL.g:6149:1: entryRuleMYID returns [String current=null] : iv_ruleMYID= ruleMYID EOF ;
    public final String entryRuleMYID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleMYID = null;


        try {
            // InternalEJSL.g:6149:44: (iv_ruleMYID= ruleMYID EOF )
            // InternalEJSL.g:6150:2: iv_ruleMYID= ruleMYID EOF
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
    // InternalEJSL.g:6156:1: ruleMYID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? ) ;
    public final AntlrDatatypeRuleToken ruleMYID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_ID_1=null;


        	enterRule();

        try {
            // InternalEJSL.g:6162:2: ( ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? ) )
            // InternalEJSL.g:6163:2: ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? )
            {
            // InternalEJSL.g:6163:2: ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? )
            // InternalEJSL.g:6164:3: (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )?
            {
            // InternalEJSL.g:6164:3: (kw= '<' )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==124) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // InternalEJSL.g:6165:4: kw= '<'
                    {
                    kw=(Token)match(input,124,FOLLOW_25); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getMYIDAccess().getLessThanSignKeyword_0());
                    			

                    }
                    break;

            }

            this_ID_1=(Token)match(input,RULE_ID,FOLLOW_148); 

            			current.merge(this_ID_1);
            		

            			newLeafNode(this_ID_1, grammarAccess.getMYIDAccess().getIDTerminalRuleCall_1());
            		
            // InternalEJSL.g:6178:3: (kw= '>' )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==125) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // InternalEJSL.g:6179:4: kw= '>'
                    {
                    kw=(Token)match(input,125,FOLLOW_2); 

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
    // InternalEJSL.g:6189:1: rulePluginKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) ) ;
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
            // InternalEJSL.g:6195:2: ( ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) ) )
            // InternalEJSL.g:6196:2: ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) )
            {
            // InternalEJSL.g:6196:2: ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) )
            int alt162=12;
            switch ( input.LA(1) ) {
            case 126:
                {
                alt162=1;
                }
                break;
            case 127:
                {
                alt162=2;
                }
                break;
            case 128:
                {
                alt162=3;
                }
                break;
            case 129:
                {
                alt162=4;
                }
                break;
            case 130:
                {
                alt162=5;
                }
                break;
            case 25:
                {
                alt162=6;
                }
                break;
            case 131:
                {
                alt162=7;
                }
                break;
            case 132:
                {
                alt162=8;
                }
                break;
            case 133:
                {
                alt162=9;
                }
                break;
            case 134:
                {
                alt162=10;
                }
                break;
            case 135:
                {
                alt162=11;
                }
                break;
            case 136:
                {
                alt162=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 162, 0, input);

                throw nvae;
            }

            switch (alt162) {
                case 1 :
                    // InternalEJSL.g:6197:3: (enumLiteral_0= 'authenticate' )
                    {
                    // InternalEJSL.g:6197:3: (enumLiteral_0= 'authenticate' )
                    // InternalEJSL.g:6198:4: enumLiteral_0= 'authenticate'
                    {
                    enumLiteral_0=(Token)match(input,126,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getAuthenticateEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getPluginKindsAccess().getAuthenticateEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:6205:3: (enumLiteral_1= 'captcha' )
                    {
                    // InternalEJSL.g:6205:3: (enumLiteral_1= 'captcha' )
                    // InternalEJSL.g:6206:4: enumLiteral_1= 'captcha'
                    {
                    enumLiteral_1=(Token)match(input,127,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getCaptchaEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getPluginKindsAccess().getCaptchaEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:6213:3: (enumLiteral_2= 'content' )
                    {
                    // InternalEJSL.g:6213:3: (enumLiteral_2= 'content' )
                    // InternalEJSL.g:6214:4: enumLiteral_2= 'content'
                    {
                    enumLiteral_2=(Token)match(input,128,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getContentEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getPluginKindsAccess().getContentEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalEJSL.g:6221:3: (enumLiteral_3= 'contact' )
                    {
                    // InternalEJSL.g:6221:3: (enumLiteral_3= 'contact' )
                    // InternalEJSL.g:6222:4: enumLiteral_3= 'contact'
                    {
                    enumLiteral_3=(Token)match(input,129,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getContactEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getPluginKindsAccess().getContactEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalEJSL.g:6229:3: (enumLiteral_4= 'editors' )
                    {
                    // InternalEJSL.g:6229:3: (enumLiteral_4= 'editors' )
                    // InternalEJSL.g:6230:4: enumLiteral_4= 'editors'
                    {
                    enumLiteral_4=(Token)match(input,130,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getEditorsEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getPluginKindsAccess().getEditorsEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalEJSL.g:6237:3: (enumLiteral_5= 'extensions' )
                    {
                    // InternalEJSL.g:6237:3: (enumLiteral_5= 'extensions' )
                    // InternalEJSL.g:6238:4: enumLiteral_5= 'extensions'
                    {
                    enumLiteral_5=(Token)match(input,25,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getExtensionsEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getPluginKindsAccess().getExtensionsEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalEJSL.g:6245:3: (enumLiteral_6= 'finder' )
                    {
                    // InternalEJSL.g:6245:3: (enumLiteral_6= 'finder' )
                    // InternalEJSL.g:6246:4: enumLiteral_6= 'finder'
                    {
                    enumLiteral_6=(Token)match(input,131,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getFinderEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getPluginKindsAccess().getFinderEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalEJSL.g:6253:3: (enumLiteral_7= 'quick_icons' )
                    {
                    // InternalEJSL.g:6253:3: (enumLiteral_7= 'quick_icons' )
                    // InternalEJSL.g:6254:4: enumLiteral_7= 'quick_icons'
                    {
                    enumLiteral_7=(Token)match(input,132,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getQuick_iconsEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getPluginKindsAccess().getQuick_iconsEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalEJSL.g:6261:3: (enumLiteral_8= 'search' )
                    {
                    // InternalEJSL.g:6261:3: (enumLiteral_8= 'search' )
                    // InternalEJSL.g:6262:4: enumLiteral_8= 'search'
                    {
                    enumLiteral_8=(Token)match(input,133,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getSearchEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getPluginKindsAccess().getSearchEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalEJSL.g:6269:3: (enumLiteral_9= 'system' )
                    {
                    // InternalEJSL.g:6269:3: (enumLiteral_9= 'system' )
                    // InternalEJSL.g:6270:4: enumLiteral_9= 'system'
                    {
                    enumLiteral_9=(Token)match(input,134,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getSystemEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getPluginKindsAccess().getSystemEnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalEJSL.g:6277:3: (enumLiteral_10= 'user' )
                    {
                    // InternalEJSL.g:6277:3: (enumLiteral_10= 'user' )
                    // InternalEJSL.g:6278:4: enumLiteral_10= 'user'
                    {
                    enumLiteral_10=(Token)match(input,135,FOLLOW_2); 

                    				current = grammarAccess.getPluginKindsAccess().getUserEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_10, grammarAccess.getPluginKindsAccess().getUserEnumLiteralDeclaration_10());
                    			

                    }


                    }
                    break;
                case 12 :
                    // InternalEJSL.g:6285:3: (enumLiteral_11= 'xml_rpc' )
                    {
                    // InternalEJSL.g:6285:3: (enumLiteral_11= 'xml_rpc' )
                    // InternalEJSL.g:6286:4: enumLiteral_11= 'xml_rpc'
                    {
                    enumLiteral_11=(Token)match(input,136,FOLLOW_2); 

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


    // $ANTLR start "ruleStandardTypeKinds"
    // InternalEJSL.g:6296:1: ruleStandardTypeKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) ) ;
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
            // InternalEJSL.g:6302:2: ( ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) ) )
            // InternalEJSL.g:6303:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) )
            {
            // InternalEJSL.g:6303:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) )
            int alt163=11;
            switch ( input.LA(1) ) {
            case 137:
                {
                alt163=1;
                }
                break;
            case 138:
                {
                alt163=2;
                }
                break;
            case 139:
                {
                alt163=3;
                }
                break;
            case 140:
                {
                alt163=4;
                }
                break;
            case 141:
                {
                alt163=5;
                }
                break;
            case 142:
                {
                alt163=6;
                }
                break;
            case 143:
                {
                alt163=7;
                }
                break;
            case 144:
                {
                alt163=8;
                }
                break;
            case 145:
                {
                alt163=9;
                }
                break;
            case 146:
                {
                alt163=10;
                }
                break;
            case 147:
                {
                alt163=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 163, 0, input);

                throw nvae;
            }

            switch (alt163) {
                case 1 :
                    // InternalEJSL.g:6304:3: (enumLiteral_0= 'Integer' )
                    {
                    // InternalEJSL.g:6304:3: (enumLiteral_0= 'Integer' )
                    // InternalEJSL.g:6305:4: enumLiteral_0= 'Integer'
                    {
                    enumLiteral_0=(Token)match(input,137,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getIntegerEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getStandardTypeKindsAccess().getIntegerEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:6312:3: (enumLiteral_1= 'Boolean' )
                    {
                    // InternalEJSL.g:6312:3: (enumLiteral_1= 'Boolean' )
                    // InternalEJSL.g:6313:4: enumLiteral_1= 'Boolean'
                    {
                    enumLiteral_1=(Token)match(input,138,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getBooleanEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getStandardTypeKindsAccess().getBooleanEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:6320:3: (enumLiteral_2= 'Textarea' )
                    {
                    // InternalEJSL.g:6320:3: (enumLiteral_2= 'Textarea' )
                    // InternalEJSL.g:6321:4: enumLiteral_2= 'Textarea'
                    {
                    enumLiteral_2=(Token)match(input,139,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getTextareaEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getStandardTypeKindsAccess().getTextareaEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalEJSL.g:6328:3: (enumLiteral_3= 'Textfield' )
                    {
                    // InternalEJSL.g:6328:3: (enumLiteral_3= 'Textfield' )
                    // InternalEJSL.g:6329:4: enumLiteral_3= 'Textfield'
                    {
                    enumLiteral_3=(Token)match(input,140,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getTextfieldEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getStandardTypeKindsAccess().getTextfieldEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalEJSL.g:6336:3: (enumLiteral_4= 'Time' )
                    {
                    // InternalEJSL.g:6336:3: (enumLiteral_4= 'Time' )
                    // InternalEJSL.g:6337:4: enumLiteral_4= 'Time'
                    {
                    enumLiteral_4=(Token)match(input,141,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getTimeEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getStandardTypeKindsAccess().getTimeEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalEJSL.g:6344:3: (enumLiteral_5= 'Date' )
                    {
                    // InternalEJSL.g:6344:3: (enumLiteral_5= 'Date' )
                    // InternalEJSL.g:6345:4: enumLiteral_5= 'Date'
                    {
                    enumLiteral_5=(Token)match(input,142,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getDateEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getStandardTypeKindsAccess().getDateEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalEJSL.g:6352:3: (enumLiteral_6= 'Datetime' )
                    {
                    // InternalEJSL.g:6352:3: (enumLiteral_6= 'Datetime' )
                    // InternalEJSL.g:6353:4: enumLiteral_6= 'Datetime'
                    {
                    enumLiteral_6=(Token)match(input,143,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getDatetimeEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getStandardTypeKindsAccess().getDatetimeEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalEJSL.g:6360:3: (enumLiteral_7= 'Link' )
                    {
                    // InternalEJSL.g:6360:3: (enumLiteral_7= 'Link' )
                    // InternalEJSL.g:6361:4: enumLiteral_7= 'Link'
                    {
                    enumLiteral_7=(Token)match(input,144,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getLinkEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getStandardTypeKindsAccess().getLinkEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalEJSL.g:6368:3: (enumLiteral_8= 'Image' )
                    {
                    // InternalEJSL.g:6368:3: (enumLiteral_8= 'Image' )
                    // InternalEJSL.g:6369:4: enumLiteral_8= 'Image'
                    {
                    enumLiteral_8=(Token)match(input,145,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getImageEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getStandardTypeKindsAccess().getImageEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalEJSL.g:6376:3: (enumLiteral_9= 'File' )
                    {
                    // InternalEJSL.g:6376:3: (enumLiteral_9= 'File' )
                    // InternalEJSL.g:6377:4: enumLiteral_9= 'File'
                    {
                    enumLiteral_9=(Token)match(input,146,FOLLOW_2); 

                    				current = grammarAccess.getStandardTypeKindsAccess().getFileEnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getStandardTypeKindsAccess().getFileEnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalEJSL.g:6384:3: (enumLiteral_10= 'Label' )
                    {
                    // InternalEJSL.g:6384:3: (enumLiteral_10= 'Label' )
                    // InternalEJSL.g:6385:4: enumLiteral_10= 'Label'
                    {
                    enumLiteral_10=(Token)match(input,147,FOLLOW_2); 

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


    // $ANTLR start "ruleSectionReference"
    // InternalEJSL.g:6395:1: ruleSectionReference returns [Enumerator current=null] : ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) ) ;
    public final Enumerator ruleSectionReference() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalEJSL.g:6401:2: ( ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) ) )
            // InternalEJSL.g:6402:2: ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) )
            {
            // InternalEJSL.g:6402:2: ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) )
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==148) ) {
                alt164=1;
            }
            else if ( (LA164_0==149) ) {
                alt164=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 164, 0, input);

                throw nvae;
            }
            switch (alt164) {
                case 1 :
                    // InternalEJSL.g:6403:3: (enumLiteral_0= '.backend' )
                    {
                    // InternalEJSL.g:6403:3: (enumLiteral_0= '.backend' )
                    // InternalEJSL.g:6404:4: enumLiteral_0= '.backend'
                    {
                    enumLiteral_0=(Token)match(input,148,FOLLOW_2); 

                    				current = grammarAccess.getSectionReferenceAccess().getBackendEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSectionReferenceAccess().getBackendEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:6411:3: (enumLiteral_1= '.frontend' )
                    {
                    // InternalEJSL.g:6411:3: (enumLiteral_1= '.frontend' )
                    // InternalEJSL.g:6412:4: enumLiteral_1= '.frontend'
                    {
                    enumLiteral_1=(Token)match(input,149,FOLLOW_2); 

                    				current = grammarAccess.getSectionReferenceAccess().getFrontendEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSectionReferenceAccess().getFrontendEnumLiteralDeclaration_1());
                    			

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
    // $ANTLR end "ruleSectionReference"


    // $ANTLR start "ruleSimpleHTMLTypeKinds"
    // InternalEJSL.g:6422:1: ruleSimpleHTMLTypeKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) ) ;
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
            // InternalEJSL.g:6428:2: ( ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) ) )
            // InternalEJSL.g:6429:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) )
            {
            // InternalEJSL.g:6429:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) )
            int alt165=9;
            switch ( input.LA(1) ) {
            case 137:
                {
                alt165=1;
                }
                break;
            case 150:
                {
                alt165=2;
                }
                break;
            case 139:
                {
                alt165=3;
                }
                break;
            case 151:
                {
                alt165=4;
                }
                break;
            case 152:
                {
                alt165=5;
                }
                break;
            case 153:
                {
                alt165=6;
                }
                break;
            case 154:
                {
                alt165=7;
                }
                break;
            case 155:
                {
                alt165=8;
                }
                break;
            case 156:
                {
                alt165=9;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 165, 0, input);

                throw nvae;
            }

            switch (alt165) {
                case 1 :
                    // InternalEJSL.g:6430:3: (enumLiteral_0= 'Integer' )
                    {
                    // InternalEJSL.g:6430:3: (enumLiteral_0= 'Integer' )
                    // InternalEJSL.g:6431:4: enumLiteral_0= 'Integer'
                    {
                    enumLiteral_0=(Token)match(input,137,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getIntegerEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSimpleHTMLTypeKindsAccess().getIntegerEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:6438:3: (enumLiteral_1= 'Yes_No_Buttons' )
                    {
                    // InternalEJSL.g:6438:3: (enumLiteral_1= 'Yes_No_Buttons' )
                    // InternalEJSL.g:6439:4: enumLiteral_1= 'Yes_No_Buttons'
                    {
                    enumLiteral_1=(Token)match(input,150,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getYes_No_ButtonsEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSimpleHTMLTypeKindsAccess().getYes_No_ButtonsEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:6446:3: (enumLiteral_2= 'Textarea' )
                    {
                    // InternalEJSL.g:6446:3: (enumLiteral_2= 'Textarea' )
                    // InternalEJSL.g:6447:4: enumLiteral_2= 'Textarea'
                    {
                    enumLiteral_2=(Token)match(input,139,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getTextareaEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getSimpleHTMLTypeKindsAccess().getTextareaEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalEJSL.g:6454:3: (enumLiteral_3= 'Text_Field' )
                    {
                    // InternalEJSL.g:6454:3: (enumLiteral_3= 'Text_Field' )
                    // InternalEJSL.g:6455:4: enumLiteral_3= 'Text_Field'
                    {
                    enumLiteral_3=(Token)match(input,151,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getText_FieldEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getSimpleHTMLTypeKindsAccess().getText_FieldEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalEJSL.g:6462:3: (enumLiteral_4= 'Datepicker' )
                    {
                    // InternalEJSL.g:6462:3: (enumLiteral_4= 'Datepicker' )
                    // InternalEJSL.g:6463:4: enumLiteral_4= 'Datepicker'
                    {
                    enumLiteral_4=(Token)match(input,152,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getDatepickerEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getSimpleHTMLTypeKindsAccess().getDatepickerEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalEJSL.g:6470:3: (enumLiteral_5= 'Imagepicker' )
                    {
                    // InternalEJSL.g:6470:3: (enumLiteral_5= 'Imagepicker' )
                    // InternalEJSL.g:6471:4: enumLiteral_5= 'Imagepicker'
                    {
                    enumLiteral_5=(Token)match(input,153,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getImagepickerEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getSimpleHTMLTypeKindsAccess().getImagepickerEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalEJSL.g:6478:3: (enumLiteral_6= 'Filepicker' )
                    {
                    // InternalEJSL.g:6478:3: (enumLiteral_6= 'Filepicker' )
                    // InternalEJSL.g:6479:4: enumLiteral_6= 'Filepicker'
                    {
                    enumLiteral_6=(Token)match(input,154,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getFilepickerEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getSimpleHTMLTypeKindsAccess().getFilepickerEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalEJSL.g:6486:3: (enumLiteral_7= 'Text_Field_NE' )
                    {
                    // InternalEJSL.g:6486:3: (enumLiteral_7= 'Text_Field_NE' )
                    // InternalEJSL.g:6487:4: enumLiteral_7= 'Text_Field_NE'
                    {
                    enumLiteral_7=(Token)match(input,155,FOLLOW_2); 

                    				current = grammarAccess.getSimpleHTMLTypeKindsAccess().getText_Field_NEEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getSimpleHTMLTypeKindsAccess().getText_Field_NEEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalEJSL.g:6494:3: (enumLiteral_8= 'Editor' )
                    {
                    // InternalEJSL.g:6494:3: (enumLiteral_8= 'Editor' )
                    // InternalEJSL.g:6495:4: enumLiteral_8= 'Editor'
                    {
                    enumLiteral_8=(Token)match(input,156,FOLLOW_2); 

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
    // InternalEJSL.g:6505:1: ruleComplexHTMLTypeKinds returns [Enumerator current=null] : ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) ) ;
    public final Enumerator ruleComplexHTMLTypeKinds() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalEJSL.g:6511:2: ( ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) ) )
            // InternalEJSL.g:6512:2: ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) )
            {
            // InternalEJSL.g:6512:2: ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) )
            int alt166=3;
            switch ( input.LA(1) ) {
            case 157:
                {
                alt166=1;
                }
                break;
            case 158:
                {
                alt166=2;
                }
                break;
            case 159:
                {
                alt166=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 166, 0, input);

                throw nvae;
            }

            switch (alt166) {
                case 1 :
                    // InternalEJSL.g:6513:3: (enumLiteral_0= 'Multiselect' )
                    {
                    // InternalEJSL.g:6513:3: (enumLiteral_0= 'Multiselect' )
                    // InternalEJSL.g:6514:4: enumLiteral_0= 'Multiselect'
                    {
                    enumLiteral_0=(Token)match(input,157,FOLLOW_2); 

                    				current = grammarAccess.getComplexHTMLTypeKindsAccess().getMultiselectEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getComplexHTMLTypeKindsAccess().getMultiselectEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalEJSL.g:6521:3: (enumLiteral_1= 'Checkbox' )
                    {
                    // InternalEJSL.g:6521:3: (enumLiteral_1= 'Checkbox' )
                    // InternalEJSL.g:6522:4: enumLiteral_1= 'Checkbox'
                    {
                    enumLiteral_1=(Token)match(input,158,FOLLOW_2); 

                    				current = grammarAccess.getComplexHTMLTypeKindsAccess().getCheckboxEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getComplexHTMLTypeKindsAccess().getCheckboxEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalEJSL.g:6529:3: (enumLiteral_2= 'Radiobutto' )
                    {
                    // InternalEJSL.g:6529:3: (enumLiteral_2= 'Radiobutto' )
                    // InternalEJSL.g:6530:4: enumLiteral_2= 'Radiobutto'
                    {
                    enumLiteral_2=(Token)match(input,159,FOLLOW_2); 

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
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000003FA0000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000003F80000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000080080000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000003E80000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000002000080000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000003C80000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000010000080000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000003880000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000008000080000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000003080000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x4410000000080000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000002080000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000080000L,0x0000001002602400L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000000002C000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000028000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000000L,0x00000000000FFE00L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000001E00080000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000001C00080000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000001800080000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000001000080000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000004400000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000080080020L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000CA0000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x00000000004A0000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000040080000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000020L,0x1000000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000020000010000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x00000C0000080000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000100000080000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000080000080000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0001000000080000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000200000080000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000400000080000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000800000000020L,0x1000000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000040L,0x0400000000000000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x03E0000000000000L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x03C0000000040000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0380000000040000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0300000000000000L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000080000L,0x00000000000000C8L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x39E0000000080000L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x31E00000000C0000L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x31C00000000C0000L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x31800000000C0000L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x3100000000080000L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x21000000000C0000L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x01000000000C0000L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0xB9E0000000080000L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0xB1E00000000C0000L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000000080020L,0x1000000000000000L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x31E0000000080000L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x00000000FFC00A00L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000000000000L,0x0004000000000000L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000040000L,0x0000000000000004L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000400080000L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0000000000000010L,0x0000000000000200L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000002000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000000080000L,0x0001000000000000L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000000L,0x0000001002602400L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000000000L,0x000000000000D000L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x0000000000000000L,0x0000000000009000L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0x0000000000000000L,0x0000000000110000L});
    public static final BitSet FOLLOW_92 = new BitSet(new long[]{0x0000000000080000L,0x0000000000110000L});
    public static final BitSet FOLLOW_93 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_94 = new BitSet(new long[]{0x0000000000080000L,0x0000000000040000L});
    public static final BitSet FOLLOW_95 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_96 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000300000L});
    public static final BitSet FOLLOW_97 = new BitSet(new long[]{0x0000000000080000L,0x0000000000041000L});
    public static final BitSet FOLLOW_98 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_99 = new BitSet(new long[]{0x0000000002000000L,0xC000000000000000L,0x00000000000001FFL});
    public static final BitSet FOLLOW_100 = new BitSet(new long[]{0x0800000000080000L,0x0000000001001000L});
    public static final BitSet FOLLOW_101 = new BitSet(new long[]{0x0800000000080000L,0x0000000001000000L});
    public static final BitSet FOLLOW_102 = new BitSet(new long[]{0x00000000000C0000L,0x0000000001000000L});
    public static final BitSet FOLLOW_103 = new BitSet(new long[]{0x0800000000080000L,0x000000000C001000L});
    public static final BitSet FOLLOW_104 = new BitSet(new long[]{0x0800000000080000L,0x000000000C000000L});
    public static final BitSet FOLLOW_105 = new BitSet(new long[]{0x00000000000C0000L,0x000000000C000000L});
    public static final BitSet FOLLOW_106 = new BitSet(new long[]{0x0000000000080000L,0x0000000020000000L});
    public static final BitSet FOLLOW_107 = new BitSet(new long[]{0x0000000000080000L,0x0000000008000000L});
    public static final BitSet FOLLOW_108 = new BitSet(new long[]{0x0000000000080000L,0x0000000010000000L});
    public static final BitSet FOLLOW_109 = new BitSet(new long[]{0x0000000000080000L,0x000000000C000000L});
    public static final BitSet FOLLOW_110 = new BitSet(new long[]{0x0000000000080000L,0x0000000004000000L});
    public static final BitSet FOLLOW_111 = new BitSet(new long[]{0x0800000000080000L,0x00000000C0000000L});
    public static final BitSet FOLLOW_112 = new BitSet(new long[]{0x08000000000C0000L,0x0000000080000000L});
    public static final BitSet FOLLOW_113 = new BitSet(new long[]{0x00000000000C0000L,0x0000000080000000L});
    public static final BitSet FOLLOW_114 = new BitSet(new long[]{0x0000000000080000L,0x0000000100000000L});
    public static final BitSet FOLLOW_115 = new BitSet(new long[]{0x0000000000080000L,0x0000000600000000L});
    public static final BitSet FOLLOW_116 = new BitSet(new long[]{0x0000000000080000L,0x0000000400000000L});
    public static final BitSet FOLLOW_117 = new BitSet(new long[]{0x0000000000080000L,0x0000000800000000L});
    public static final BitSet FOLLOW_118 = new BitSet(new long[]{0x0000000000080000L,0x0000006001001000L});
    public static final BitSet FOLLOW_119 = new BitSet(new long[]{0x0000000000080000L,0x0000006001000000L});
    public static final BitSet FOLLOW_120 = new BitSet(new long[]{0x0000000000080000L,0x0000006000000000L});
    public static final BitSet FOLLOW_121 = new BitSet(new long[]{0x0000000000080000L,0x0008000000000000L});
    public static final BitSet FOLLOW_122 = new BitSet(new long[]{0x0000000000080000L,0x0000004000000000L});
    public static final BitSet FOLLOW_123 = new BitSet(new long[]{0x0000000000080000L,0x0200000000000000L});
    public static final BitSet FOLLOW_124 = new BitSet(new long[]{0x0000000000000000L,0x0000200000000000L});
    public static final BitSet FOLLOW_125 = new BitSet(new long[]{0x0000000000080000L,0x0000200000000000L});
    public static final BitSet FOLLOW_126 = new BitSet(new long[]{0x0000001000000002L,0x00001F0000000000L});
    public static final BitSet FOLLOW_127 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_128 = new BitSet(new long[]{0x0000001000000002L,0x00001E0000000000L});
    public static final BitSet FOLLOW_129 = new BitSet(new long[]{0x0000001000000002L,0x00001C0000000000L});
    public static final BitSet FOLLOW_130 = new BitSet(new long[]{0x0000001000000002L,0x0000180000000000L});
    public static final BitSet FOLLOW_131 = new BitSet(new long[]{0x0000001000000002L,0x0000100000000000L});
    public static final BitSet FOLLOW_132 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_133 = new BitSet(new long[]{0x0000000000080000L,0x0000C00000000000L});
    public static final BitSet FOLLOW_134 = new BitSet(new long[]{0x0000000000080000L,0x0000800000000000L});
    public static final BitSet FOLLOW_135 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_136 = new BitSet(new long[]{0x0000000000080000L,0x0002000000000000L});
    public static final BitSet FOLLOW_137 = new BitSet(new long[]{0x0000000000080000L,0x0004000000000000L});
    public static final BitSet FOLLOW_138 = new BitSet(new long[]{0x0000000000000220L});
    public static final BitSet FOLLOW_139 = new BitSet(new long[]{0x0000000000080000L,0x0010000000000000L});
    public static final BitSet FOLLOW_140 = new BitSet(new long[]{0x0000000000080000L,0x0020000000000000L});
    public static final BitSet FOLLOW_141 = new BitSet(new long[]{0x0000000000080000L,0x01C0000000000000L});
    public static final BitSet FOLLOW_142 = new BitSet(new long[]{0x0000000000080000L,0x0180000000000000L});
    public static final BitSet FOLLOW_143 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_144 = new BitSet(new long[]{0x0000000000080000L,0x0100000000000000L});
    public static final BitSet FOLLOW_145 = new BitSet(new long[]{0x0000000000000000L,0x0002000000000004L});
    public static final BitSet FOLLOW_146 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_147 = new BitSet(new long[]{0x0000000000000002L,0x0800000000000000L});
    public static final BitSet FOLLOW_148 = new BitSet(new long[]{0x0000000000000002L,0x2000000000000000L});

}