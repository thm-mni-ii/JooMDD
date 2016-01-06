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
    // PsiInternalEJSL.g:59:1: ruleEJSLModel returns [Boolean current=false] : ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}' ) ;
    public final Boolean ruleEJSLModel() throws RecognitionException {
        Boolean current = false;

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
        Boolean lv_datatypes_6_0 = null;

        Boolean lv_datatypes_8_0 = null;

        Boolean lv_globalparameters_12_0 = null;

        Boolean lv_parametergroups_16_0 = null;

        Boolean lv_entities_20_0 = null;

        Boolean lv_datapackages_24_0 = null;

        Boolean lv_pages_28_0 = null;

        Boolean lv_extensions_32_0 = null;


        try {
            // PsiInternalEJSL.g:60:1: ( ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}' ) )
            // PsiInternalEJSL.g:61:2: ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}' )
            {
            // PsiInternalEJSL.g:61:2: ( () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}' )
            // PsiInternalEJSL.g:62:3: () otherlv_1= 'eJSLModel' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )? (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )? (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )? (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )? (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )? (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )? (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )? otherlv_34= '}'
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
            		
            // PsiInternalEJSL.g:100:3: (otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==17) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // PsiInternalEJSL.g:101:4: otherlv_4= 'datatypes' otherlv_5= '{' ( (lv_datatypes_6_0= ruleDatatype ) ) (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )* otherlv_9= '}'
                    {

                    				markLeaf(elementTypeProvider.getEJSLModel_DatatypesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,17,FOLLOW_4); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getEJSLModel_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_6); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:115:4: ( (lv_datatypes_6_0= ruleDatatype ) )
                    // PsiInternalEJSL.g:116:5: (lv_datatypes_6_0= ruleDatatype )
                    {
                    // PsiInternalEJSL.g:116:5: (lv_datatypes_6_0= ruleDatatype )
                    // PsiInternalEJSL.g:117:6: lv_datatypes_6_0= ruleDatatype
                    {

                    						markComposite(elementTypeProvider.getEJSLModel_DatatypesDatatypeParserRuleCall_4_2_0ElementType());
                    					
                    pushFollow(FOLLOW_7);
                    lv_datatypes_6_0=ruleDatatype();

                    state._fsp--;


                    						doneComposite();
                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }

                    // PsiInternalEJSL.g:130:4: (otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==18) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:131:5: otherlv_7= ',' ( (lv_datatypes_8_0= ruleDatatype ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getEJSLModel_CommaKeyword_4_3_0ElementType());
                    	    				
                    	    otherlv_7=(Token)match(input,18,FOLLOW_6); 

                    	    					doneLeaf(otherlv_7);
                    	    				
                    	    // PsiInternalEJSL.g:138:5: ( (lv_datatypes_8_0= ruleDatatype ) )
                    	    // PsiInternalEJSL.g:139:6: (lv_datatypes_8_0= ruleDatatype )
                    	    {
                    	    // PsiInternalEJSL.g:139:6: (lv_datatypes_8_0= ruleDatatype )
                    	    // PsiInternalEJSL.g:140:7: lv_datatypes_8_0= ruleDatatype
                    	    {

                    	    							markComposite(elementTypeProvider.getEJSLModel_DatatypesDatatypeParserRuleCall_4_3_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_7);
                    	    lv_datatypes_8_0=ruleDatatype();

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
                    	    break loop1;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_4_4ElementType());
                    			
                    otherlv_9=(Token)match(input,19,FOLLOW_8); 

                    				doneLeaf(otherlv_9);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:162:3: (otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==20) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // PsiInternalEJSL.g:163:4: otherlv_10= 'globalparameters' otherlv_11= '{' ( (lv_globalparameters_12_0= ruleParameter ) )* otherlv_13= '}'
                    {

                    				markLeaf(elementTypeProvider.getEJSLModel_GlobalparametersKeyword_5_0ElementType());
                    			
                    otherlv_10=(Token)match(input,20,FOLLOW_4); 

                    				doneLeaf(otherlv_10);
                    			

                    				markLeaf(elementTypeProvider.getEJSLModel_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_9); 

                    				doneLeaf(otherlv_11);
                    			
                    // PsiInternalEJSL.g:177:4: ( (lv_globalparameters_12_0= ruleParameter ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==31) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:178:5: (lv_globalparameters_12_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:178:5: (lv_globalparameters_12_0= ruleParameter )
                    	    // PsiInternalEJSL.g:179:6: lv_globalparameters_12_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getEJSLModel_GlobalparametersParameterParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_9);
                    	    lv_globalparameters_12_0=ruleParameter();

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
                    	    break loop3;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_13=(Token)match(input,19,FOLLOW_10); 

                    				doneLeaf(otherlv_13);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:200:3: (otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==21) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // PsiInternalEJSL.g:201:4: otherlv_14= 'parametergroups' otherlv_15= '{' ( (lv_parametergroups_16_0= ruleParameterGroup ) )* otherlv_17= '}'
                    {

                    				markLeaf(elementTypeProvider.getEJSLModel_ParametergroupsKeyword_6_0ElementType());
                    			
                    otherlv_14=(Token)match(input,21,FOLLOW_4); 

                    				doneLeaf(otherlv_14);
                    			

                    				markLeaf(elementTypeProvider.getEJSLModel_LeftCurlyBracketKeyword_6_1ElementType());
                    			
                    otherlv_15=(Token)match(input,16,FOLLOW_11); 

                    				doneLeaf(otherlv_15);
                    			
                    // PsiInternalEJSL.g:215:4: ( (lv_parametergroups_16_0= ruleParameterGroup ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==37) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:216:5: (lv_parametergroups_16_0= ruleParameterGroup )
                    	    {
                    	    // PsiInternalEJSL.g:216:5: (lv_parametergroups_16_0= ruleParameterGroup )
                    	    // PsiInternalEJSL.g:217:6: lv_parametergroups_16_0= ruleParameterGroup
                    	    {

                    	    						markComposite(elementTypeProvider.getEJSLModel_ParametergroupsParameterGroupParserRuleCall_6_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_11);
                    	    lv_parametergroups_16_0=ruleParameterGroup();

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
                    	    break loop5;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_6_3ElementType());
                    			
                    otherlv_17=(Token)match(input,19,FOLLOW_12); 

                    				doneLeaf(otherlv_17);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:238:3: (otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==22) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // PsiInternalEJSL.g:239:4: otherlv_18= 'entities' otherlv_19= '{' ( (lv_entities_20_0= ruleEntity ) )* otherlv_21= '}'
                    {

                    				markLeaf(elementTypeProvider.getEJSLModel_EntitiesKeyword_7_0ElementType());
                    			
                    otherlv_18=(Token)match(input,22,FOLLOW_4); 

                    				doneLeaf(otherlv_18);
                    			

                    				markLeaf(elementTypeProvider.getEJSLModel_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_19=(Token)match(input,16,FOLLOW_13); 

                    				doneLeaf(otherlv_19);
                    			
                    // PsiInternalEJSL.g:253:4: ( (lv_entities_20_0= ruleEntity ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==40) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:254:5: (lv_entities_20_0= ruleEntity )
                    	    {
                    	    // PsiInternalEJSL.g:254:5: (lv_entities_20_0= ruleEntity )
                    	    // PsiInternalEJSL.g:255:6: lv_entities_20_0= ruleEntity
                    	    {

                    	    						markComposite(elementTypeProvider.getEJSLModel_EntitiesEntityParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_13);
                    	    lv_entities_20_0=ruleEntity();

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
                    	    break loop7;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_21=(Token)match(input,19,FOLLOW_14); 

                    				doneLeaf(otherlv_21);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:276:3: (otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==23) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // PsiInternalEJSL.g:277:4: otherlv_22= 'datapackages' otherlv_23= '{' ( (lv_datapackages_24_0= ruleDatapackage ) )* otherlv_25= '}'
                    {

                    				markLeaf(elementTypeProvider.getEJSLModel_DatapackagesKeyword_8_0ElementType());
                    			
                    otherlv_22=(Token)match(input,23,FOLLOW_4); 

                    				doneLeaf(otherlv_22);
                    			

                    				markLeaf(elementTypeProvider.getEJSLModel_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_23=(Token)match(input,16,FOLLOW_15); 

                    				doneLeaf(otherlv_23);
                    			
                    // PsiInternalEJSL.g:291:4: ( (lv_datapackages_24_0= ruleDatapackage ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==39) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:292:5: (lv_datapackages_24_0= ruleDatapackage )
                    	    {
                    	    // PsiInternalEJSL.g:292:5: (lv_datapackages_24_0= ruleDatapackage )
                    	    // PsiInternalEJSL.g:293:6: lv_datapackages_24_0= ruleDatapackage
                    	    {

                    	    						markComposite(elementTypeProvider.getEJSLModel_DatapackagesDatapackageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_datapackages_24_0=ruleDatapackage();

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
                    	    break loop9;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_25=(Token)match(input,19,FOLLOW_16); 

                    				doneLeaf(otherlv_25);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:314:3: (otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==24) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // PsiInternalEJSL.g:315:4: otherlv_26= 'pages' otherlv_27= '{' ( (lv_pages_28_0= rulePage ) )* otherlv_29= '}'
                    {

                    				markLeaf(elementTypeProvider.getEJSLModel_PagesKeyword_9_0ElementType());
                    			
                    otherlv_26=(Token)match(input,24,FOLLOW_4); 

                    				doneLeaf(otherlv_26);
                    			

                    				markLeaf(elementTypeProvider.getEJSLModel_LeftCurlyBracketKeyword_9_1ElementType());
                    			
                    otherlv_27=(Token)match(input,16,FOLLOW_17); 

                    				doneLeaf(otherlv_27);
                    			
                    // PsiInternalEJSL.g:329:4: ( (lv_pages_28_0= rulePage ) )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==52||LA11_0==58||LA11_0==62) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:330:5: (lv_pages_28_0= rulePage )
                    	    {
                    	    // PsiInternalEJSL.g:330:5: (lv_pages_28_0= rulePage )
                    	    // PsiInternalEJSL.g:331:6: lv_pages_28_0= rulePage
                    	    {

                    	    						markComposite(elementTypeProvider.getEJSLModel_PagesPageParserRuleCall_9_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_17);
                    	    lv_pages_28_0=rulePage();

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
                    	    break loop11;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_9_3ElementType());
                    			
                    otherlv_29=(Token)match(input,19,FOLLOW_18); 

                    				doneLeaf(otherlv_29);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:352:3: (otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==25) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // PsiInternalEJSL.g:353:4: otherlv_30= 'extensions' otherlv_31= '{' ( (lv_extensions_32_0= ruleExtension ) )* otherlv_33= '}'
                    {

                    				markLeaf(elementTypeProvider.getEJSLModel_ExtensionsKeyword_10_0ElementType());
                    			
                    otherlv_30=(Token)match(input,25,FOLLOW_4); 

                    				doneLeaf(otherlv_30);
                    			

                    				markLeaf(elementTypeProvider.getEJSLModel_LeftCurlyBracketKeyword_10_1ElementType());
                    			
                    otherlv_31=(Token)match(input,16,FOLLOW_19); 

                    				doneLeaf(otherlv_31);
                    			
                    // PsiInternalEJSL.g:367:4: ( (lv_extensions_32_0= ruleExtension ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==74||LA13_0==77||(LA13_0>=85 && LA13_0<=86)||LA13_0==89||LA13_0==100) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:368:5: (lv_extensions_32_0= ruleExtension )
                    	    {
                    	    // PsiInternalEJSL.g:368:5: (lv_extensions_32_0= ruleExtension )
                    	    // PsiInternalEJSL.g:369:6: lv_extensions_32_0= ruleExtension
                    	    {

                    	    						markComposite(elementTypeProvider.getEJSLModel_ExtensionsExtensionParserRuleCall_10_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_19);
                    	    lv_extensions_32_0=ruleExtension();

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
                    	    break loop13;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_10_3ElementType());
                    			
                    otherlv_33=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_33);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getEJSLModel_RightCurlyBracketKeyword_11ElementType());
            		
            otherlv_34=(Token)match(input,19,FOLLOW_2); 

            			doneLeaf(otherlv_34);
            		

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


    // $ANTLR start "entryRuleType"
    // PsiInternalEJSL.g:401:1: entryRuleType returns [Boolean current=false] : iv_ruleType= ruleType EOF ;
    public final Boolean entryRuleType() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleType = null;


        try {
            // PsiInternalEJSL.g:401:46: (iv_ruleType= ruleType EOF )
            // PsiInternalEJSL.g:402:2: iv_ruleType= ruleType EOF
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
    // PsiInternalEJSL.g:408:1: ruleType returns [Boolean current=false] : (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes ) ;
    public final Boolean ruleType() throws RecognitionException {
        Boolean current = false;

        Boolean this_DatatypeReference_0 = null;

        Boolean this_StandardTypes_1 = null;


        try {
            // PsiInternalEJSL.g:409:1: ( (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes ) )
            // PsiInternalEJSL.g:410:2: (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes )
            {
            // PsiInternalEJSL.g:410:2: (this_DatatypeReference_0= ruleDatatypeReference | this_StandardTypes_1= ruleStandardTypes )
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
                    // PsiInternalEJSL.g:411:3: this_DatatypeReference_0= ruleDatatypeReference
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
                    // PsiInternalEJSL.g:420:3: this_StandardTypes_1= ruleStandardTypes
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
    // PsiInternalEJSL.g:432:1: entryRuleDatatypeReference returns [Boolean current=false] : iv_ruleDatatypeReference= ruleDatatypeReference EOF ;
    public final Boolean entryRuleDatatypeReference() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDatatypeReference = null;


        try {
            // PsiInternalEJSL.g:432:59: (iv_ruleDatatypeReference= ruleDatatypeReference EOF )
            // PsiInternalEJSL.g:433:2: iv_ruleDatatypeReference= ruleDatatypeReference EOF
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
    // PsiInternalEJSL.g:439:1: ruleDatatypeReference returns [Boolean current=false] : ( (otherlv_0= RULE_STRING ) ) ;
    public final Boolean ruleDatatypeReference() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;

        try {
            // PsiInternalEJSL.g:440:1: ( ( (otherlv_0= RULE_STRING ) ) )
            // PsiInternalEJSL.g:441:2: ( (otherlv_0= RULE_STRING ) )
            {
            // PsiInternalEJSL.g:441:2: ( (otherlv_0= RULE_STRING ) )
            // PsiInternalEJSL.g:442:3: (otherlv_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:442:3: (otherlv_0= RULE_STRING )
            // PsiInternalEJSL.g:443:4: otherlv_0= RULE_STRING
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
    // PsiInternalEJSL.g:461:1: entryRuleStandardTypes returns [Boolean current=false] : iv_ruleStandardTypes= ruleStandardTypes EOF ;
    public final Boolean entryRuleStandardTypes() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleStandardTypes = null;


        try {
            // PsiInternalEJSL.g:461:55: (iv_ruleStandardTypes= ruleStandardTypes EOF )
            // PsiInternalEJSL.g:462:2: iv_ruleStandardTypes= ruleStandardTypes EOF
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
    // PsiInternalEJSL.g:468:1: ruleStandardTypes returns [Boolean current=false] : ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )? ) ;
    public final Boolean ruleStandardTypes() throws RecognitionException {
        Boolean current = false;

        Token lv_notnull_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_default_4_0=null;
        Token lv_autoincrement_5_0=null;
        Boolean lv_type_0_0 = null;


        try {
            // PsiInternalEJSL.g:469:1: ( ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )? ) )
            // PsiInternalEJSL.g:470:2: ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )? )
            {
            // PsiInternalEJSL.g:470:2: ( ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )? )
            // PsiInternalEJSL.g:471:3: ( (lv_type_0_0= ruleStandardTypeKinds ) ) ( (lv_notnull_1_0= 'Not Null' ) )? (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )? ( (lv_autoincrement_5_0= 'Auto Increment' ) )?
            {
            // PsiInternalEJSL.g:471:3: ( (lv_type_0_0= ruleStandardTypeKinds ) )
            // PsiInternalEJSL.g:472:4: (lv_type_0_0= ruleStandardTypeKinds )
            {
            // PsiInternalEJSL.g:472:4: (lv_type_0_0= ruleStandardTypeKinds )
            // PsiInternalEJSL.g:473:5: lv_type_0_0= ruleStandardTypeKinds
            {

            					markComposite(elementTypeProvider.getStandardTypes_TypeStandardTypeKindsEnumRuleCall_0_0ElementType());
            				
            pushFollow(FOLLOW_21);
            lv_type_0_0=ruleStandardTypeKinds();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:486:3: ( (lv_notnull_1_0= 'Not Null' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==26) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // PsiInternalEJSL.g:487:4: (lv_notnull_1_0= 'Not Null' )
                    {
                    // PsiInternalEJSL.g:487:4: (lv_notnull_1_0= 'Not Null' )
                    // PsiInternalEJSL.g:488:5: lv_notnull_1_0= 'Not Null'
                    {

                    					markLeaf(elementTypeProvider.getStandardTypes_NotnullNotNullKeyword_1_0ElementType());
                    				
                    lv_notnull_1_0=(Token)match(input,26,FOLLOW_22); 

                    					doneLeaf(lv_notnull_1_0);
                    				

                    					if (!current) {
                    						associateWithSemanticElement();
                    						current = true;
                    					}
                    				

                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:503:3: (otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==27) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // PsiInternalEJSL.g:504:4: otherlv_2= 'Default' otherlv_3= '=' ( (lv_default_4_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getStandardTypes_DefaultKeyword_2_0ElementType());
                    			
                    otherlv_2=(Token)match(input,27,FOLLOW_23); 

                    				doneLeaf(otherlv_2);
                    			

                    				markLeaf(elementTypeProvider.getStandardTypes_EqualsSignKeyword_2_1ElementType());
                    			
                    otherlv_3=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalEJSL.g:518:4: ( (lv_default_4_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:519:5: (lv_default_4_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:519:5: (lv_default_4_0= RULE_STRING )
                    // PsiInternalEJSL.g:520:6: lv_default_4_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getStandardTypes_DefaultSTRINGTerminalRuleCall_2_2_0ElementType());
                    					
                    lv_default_4_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_default_4_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:536:3: ( (lv_autoincrement_5_0= 'Auto Increment' ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==29) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // PsiInternalEJSL.g:537:4: (lv_autoincrement_5_0= 'Auto Increment' )
                    {
                    // PsiInternalEJSL.g:537:4: (lv_autoincrement_5_0= 'Auto Increment' )
                    // PsiInternalEJSL.g:538:5: lv_autoincrement_5_0= 'Auto Increment'
                    {

                    					markLeaf(elementTypeProvider.getStandardTypes_AutoincrementAutoIncrementKeyword_3_0ElementType());
                    				
                    lv_autoincrement_5_0=(Token)match(input,29,FOLLOW_2); 

                    					doneLeaf(lv_autoincrement_5_0);
                    				

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
    // PsiInternalEJSL.g:557:1: entryRuleDatatype returns [Boolean current=false] : iv_ruleDatatype= ruleDatatype EOF ;
    public final Boolean entryRuleDatatype() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDatatype = null;


        try {
            // PsiInternalEJSL.g:557:50: (iv_ruleDatatype= ruleDatatype EOF )
            // PsiInternalEJSL.g:558:2: iv_ruleDatatype= ruleDatatype EOF
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
    // PsiInternalEJSL.g:564:1: ruleDatatype returns [Boolean current=false] : ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) ) ;
    public final Boolean ruleDatatype() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;

        try {
            // PsiInternalEJSL.g:565:1: ( ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) ) )
            // PsiInternalEJSL.g:566:2: ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) )
            {
            // PsiInternalEJSL.g:566:2: ( () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) ) )
            // PsiInternalEJSL.g:567:3: () otherlv_1= 'Datatype' ( (lv_name_2_0= RULE_STRING ) )
            {
            // PsiInternalEJSL.g:567:3: ()
            // PsiInternalEJSL.g:568:4: 
            {

            				precedeComposite(elementTypeProvider.getDatatype_DatatypeAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getDatatype_DatatypeKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,30,FOLLOW_3); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:581:3: ( (lv_name_2_0= RULE_STRING ) )
            // PsiInternalEJSL.g:582:4: (lv_name_2_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:582:4: (lv_name_2_0= RULE_STRING )
            // PsiInternalEJSL.g:583:5: lv_name_2_0= RULE_STRING
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
    // PsiInternalEJSL.g:602:1: entryRuleParameter returns [Boolean current=false] : iv_ruleParameter= ruleParameter EOF ;
    public final Boolean entryRuleParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParameter = null;


        try {
            // PsiInternalEJSL.g:602:51: (iv_ruleParameter= ruleParameter EOF )
            // PsiInternalEJSL.g:603:2: iv_ruleParameter= ruleParameter EOF
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
    // PsiInternalEJSL.g:609:1: ruleParameter returns [Boolean current=false] : ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}' ) ;
    public final Boolean ruleParameter() throws RecognitionException {
        Boolean current = false;

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
        Boolean lv_dtype_6_0 = null;


        try {
            // PsiInternalEJSL.g:610:1: ( ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}' ) )
            // PsiInternalEJSL.g:611:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}' )
            {
            // PsiInternalEJSL.g:611:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}' )
            // PsiInternalEJSL.g:612:3: () otherlv_1= 'Parameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_dtype_6_0= ruleType ) ) (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )? (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )? (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )? (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )? otherlv_19= '}'
            {
            // PsiInternalEJSL.g:612:3: ()
            // PsiInternalEJSL.g:613:4: 
            {

            				precedeComposite(elementTypeProvider.getParameter_ParameterAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getParameter_ParameterKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,31,FOLLOW_25); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:626:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:627:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:627:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:628:5: lv_name_2_0= RULE_ID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_26); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getParameter_TypeKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,32,FOLLOW_23); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getParameter_EqualsSignKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,28,FOLLOW_27); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:664:3: ( (lv_dtype_6_0= ruleType ) )
            // PsiInternalEJSL.g:665:4: (lv_dtype_6_0= ruleType )
            {
            // PsiInternalEJSL.g:665:4: (lv_dtype_6_0= ruleType )
            // PsiInternalEJSL.g:666:5: lv_dtype_6_0= ruleType
            {

            					markComposite(elementTypeProvider.getParameter_DtypeTypeParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_28);
            lv_dtype_6_0=ruleType();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:679:3: (otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==33) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // PsiInternalEJSL.g:680:4: otherlv_7= 'defaultvalue' otherlv_8= '=' ( (lv_defaultvalue_9_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getParameter_DefaultvalueKeyword_7_0ElementType());
                    			
                    otherlv_7=(Token)match(input,33,FOLLOW_23); 

                    				doneLeaf(otherlv_7);
                    			

                    				markLeaf(elementTypeProvider.getParameter_EqualsSignKeyword_7_1ElementType());
                    			
                    otherlv_8=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:694:4: ( (lv_defaultvalue_9_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:695:5: (lv_defaultvalue_9_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:695:5: (lv_defaultvalue_9_0= RULE_STRING )
                    // PsiInternalEJSL.g:696:6: lv_defaultvalue_9_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getParameter_DefaultvalueSTRINGTerminalRuleCall_7_2_0ElementType());
                    					
                    lv_defaultvalue_9_0=(Token)match(input,RULE_STRING,FOLLOW_29); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_defaultvalue_9_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:712:3: (otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==34) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // PsiInternalEJSL.g:713:4: otherlv_10= 'label' otherlv_11= '=' ( (lv_label_12_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getParameter_LabelKeyword_8_0ElementType());
                    			
                    otherlv_10=(Token)match(input,34,FOLLOW_23); 

                    				doneLeaf(otherlv_10);
                    			

                    				markLeaf(elementTypeProvider.getParameter_EqualsSignKeyword_8_1ElementType());
                    			
                    otherlv_11=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_11);
                    			
                    // PsiInternalEJSL.g:727:4: ( (lv_label_12_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:728:5: (lv_label_12_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:728:5: (lv_label_12_0= RULE_STRING )
                    // PsiInternalEJSL.g:729:6: lv_label_12_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getParameter_LabelSTRINGTerminalRuleCall_8_2_0ElementType());
                    					
                    lv_label_12_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_label_12_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:745:3: (otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==35) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // PsiInternalEJSL.g:746:4: otherlv_13= 'size' otherlv_14= '=' ( (lv_size_15_0= RULE_INT ) )
                    {

                    				markLeaf(elementTypeProvider.getParameter_SizeKeyword_9_0ElementType());
                    			
                    otherlv_13=(Token)match(input,35,FOLLOW_23); 

                    				doneLeaf(otherlv_13);
                    			

                    				markLeaf(elementTypeProvider.getParameter_EqualsSignKeyword_9_1ElementType());
                    			
                    otherlv_14=(Token)match(input,28,FOLLOW_31); 

                    				doneLeaf(otherlv_14);
                    			
                    // PsiInternalEJSL.g:760:4: ( (lv_size_15_0= RULE_INT ) )
                    // PsiInternalEJSL.g:761:5: (lv_size_15_0= RULE_INT )
                    {
                    // PsiInternalEJSL.g:761:5: (lv_size_15_0= RULE_INT )
                    // PsiInternalEJSL.g:762:6: lv_size_15_0= RULE_INT
                    {

                    						markLeaf(elementTypeProvider.getParameter_SizeINTTerminalRuleCall_9_2_0ElementType());
                    					
                    lv_size_15_0=(Token)match(input,RULE_INT,FOLLOW_32); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_size_15_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:778:3: (otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==36) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // PsiInternalEJSL.g:779:4: otherlv_16= 'description' otherlv_17= '=' ( (lv_descripton_18_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getParameter_DescriptionKeyword_10_0ElementType());
                    			
                    otherlv_16=(Token)match(input,36,FOLLOW_23); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getParameter_EqualsSignKeyword_10_1ElementType());
                    			
                    otherlv_17=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:793:4: ( (lv_descripton_18_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:794:5: (lv_descripton_18_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:794:5: (lv_descripton_18_0= RULE_STRING )
                    // PsiInternalEJSL.g:795:6: lv_descripton_18_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getParameter_DescriptonSTRINGTerminalRuleCall_10_2_0ElementType());
                    					
                    lv_descripton_18_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_descripton_18_0);
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getParameter_RightCurlyBracketKeyword_11ElementType());
            		
            otherlv_19=(Token)match(input,19,FOLLOW_2); 

            			doneLeaf(otherlv_19);
            		

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
    // PsiInternalEJSL.g:822:1: entryRuleParameterGroup returns [Boolean current=false] : iv_ruleParameterGroup= ruleParameterGroup EOF ;
    public final Boolean entryRuleParameterGroup() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleParameterGroup = null;


        try {
            // PsiInternalEJSL.g:822:56: (iv_ruleParameterGroup= ruleParameterGroup EOF )
            // PsiInternalEJSL.g:823:2: iv_ruleParameterGroup= ruleParameterGroup EOF
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
    // PsiInternalEJSL.g:829:1: ruleParameterGroup returns [Boolean current=false] : ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}' ) ;
    public final Boolean ruleParameterGroup() throws RecognitionException {
        Boolean current = false;

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
        Boolean lv_parameters_10_0 = null;


        try {
            // PsiInternalEJSL.g:830:1: ( ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}' ) )
            // PsiInternalEJSL.g:831:2: ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}' )
            {
            // PsiInternalEJSL.g:831:2: ( () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}' )
            // PsiInternalEJSL.g:832:3: () otherlv_1= 'ParameterGroup' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )? (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' ) otherlv_12= '}'
            {
            // PsiInternalEJSL.g:832:3: ()
            // PsiInternalEJSL.g:833:4: 
            {

            				precedeComposite(elementTypeProvider.getParameterGroup_ParameterGroupAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getParameterGroup_ParameterGroupKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,37,FOLLOW_25); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:846:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:847:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:847:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:848:5: lv_name_2_0= RULE_ID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_33); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:870:3: (otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==34) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // PsiInternalEJSL.g:871:4: otherlv_4= 'label' otherlv_5= '=' ( (lv_label_6_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getParameterGroup_LabelKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,34,FOLLOW_23); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getParameterGroup_EqualsSignKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:885:4: ( (lv_label_6_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:886:5: (lv_label_6_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:886:5: (lv_label_6_0= RULE_STRING )
                    // PsiInternalEJSL.g:887:6: lv_label_6_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getParameterGroup_LabelSTRINGTerminalRuleCall_4_2_0ElementType());
                    					
                    lv_label_6_0=(Token)match(input,RULE_STRING,FOLLOW_34); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_label_6_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:903:3: (otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}' )
            // PsiInternalEJSL.g:904:4: otherlv_7= 'Parameters' otherlv_8= '{' ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )* otherlv_11= '}'
            {

            				markLeaf(elementTypeProvider.getParameterGroup_ParametersKeyword_5_0ElementType());
            			
            otherlv_7=(Token)match(input,38,FOLLOW_4); 

            				doneLeaf(otherlv_7);
            			

            				markLeaf(elementTypeProvider.getParameterGroup_LeftCurlyBracketKeyword_5_1ElementType());
            			
            otherlv_8=(Token)match(input,16,FOLLOW_35); 

            				doneLeaf(otherlv_8);
            			
            // PsiInternalEJSL.g:918:4: ( ( (otherlv_9= RULE_ID ) ) | ( (lv_parameters_10_0= ruleParameter ) ) )*
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
            	    // PsiInternalEJSL.g:919:5: ( (otherlv_9= RULE_ID ) )
            	    {
            	    // PsiInternalEJSL.g:919:5: ( (otherlv_9= RULE_ID ) )
            	    // PsiInternalEJSL.g:920:6: (otherlv_9= RULE_ID )
            	    {
            	    // PsiInternalEJSL.g:920:6: (otherlv_9= RULE_ID )
            	    // PsiInternalEJSL.g:921:7: otherlv_9= RULE_ID
            	    {

            	    							if (!current) {
            	    								associateWithSemanticElement();
            	    								current = true;
            	    							}
            	    						

            	    							markLeaf(elementTypeProvider.getParameterGroup_GlobalparametersParameterCrossReference_5_2_0_0ElementType());
            	    						
            	    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_35); 

            	    							doneLeaf(otherlv_9);
            	    						

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // PsiInternalEJSL.g:937:5: ( (lv_parameters_10_0= ruleParameter ) )
            	    {
            	    // PsiInternalEJSL.g:937:5: ( (lv_parameters_10_0= ruleParameter ) )
            	    // PsiInternalEJSL.g:938:6: (lv_parameters_10_0= ruleParameter )
            	    {
            	    // PsiInternalEJSL.g:938:6: (lv_parameters_10_0= ruleParameter )
            	    // PsiInternalEJSL.g:939:7: lv_parameters_10_0= ruleParameter
            	    {

            	    							markComposite(elementTypeProvider.getParameterGroup_ParametersParameterParserRuleCall_5_2_1_0ElementType());
            	    						
            	    pushFollow(FOLLOW_35);
            	    lv_parameters_10_0=ruleParameter();

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
            	    break loop24;
                }
            } while (true);


            				markLeaf(elementTypeProvider.getParameterGroup_RightCurlyBracketKeyword_5_3ElementType());
            			
            otherlv_11=(Token)match(input,19,FOLLOW_20); 

            				doneLeaf(otherlv_11);
            			

            }


            			markLeaf(elementTypeProvider.getParameterGroup_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_12=(Token)match(input,19,FOLLOW_2); 

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
    // $ANTLR end "ruleParameterGroup"


    // $ANTLR start "entryRuleDatapackage"
    // PsiInternalEJSL.g:972:1: entryRuleDatapackage returns [Boolean current=false] : iv_ruleDatapackage= ruleDatapackage EOF ;
    public final Boolean entryRuleDatapackage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDatapackage = null;


        try {
            // PsiInternalEJSL.g:972:53: (iv_ruleDatapackage= ruleDatapackage EOF )
            // PsiInternalEJSL.g:973:2: iv_ruleDatapackage= ruleDatapackage EOF
            {
             markComposite(elementTypeProvider.getDatapackageElementType()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatapackage=ruleDatapackage();

            state._fsp--;

             current =iv_ruleDatapackage; 
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
    // $ANTLR end "entryRuleDatapackage"


    // $ANTLR start "ruleDatapackage"
    // PsiInternalEJSL.g:979:1: ruleDatapackage returns [Boolean current=false] : ( () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' ) ;
    public final Boolean ruleDatapackage() throws RecognitionException {
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
        Boolean lv_datapackages_6_0 = null;

        Boolean lv_entities_10_0 = null;

        Boolean lv_datatypes_14_0 = null;


        try {
            // PsiInternalEJSL.g:980:1: ( ( () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' ) )
            // PsiInternalEJSL.g:981:2: ( () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' )
            {
            // PsiInternalEJSL.g:981:2: ( () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}' )
            // PsiInternalEJSL.g:982:3: () otherlv_1= 'Datapackage' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )? (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )? (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )? otherlv_16= '}'
            {
            // PsiInternalEJSL.g:982:3: ()
            // PsiInternalEJSL.g:983:4: 
            {

            				precedeComposite(elementTypeProvider.getDatapackage_DatapackageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getDatapackage_DatapackageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,39,FOLLOW_25); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:996:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:997:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:997:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:998:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getDatapackage_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getDatapackage_LeftCurlyBracketKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,16,FOLLOW_36); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:1020:3: (otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==23) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // PsiInternalEJSL.g:1021:4: otherlv_4= 'datapackages' otherlv_5= '{' ( (lv_datapackages_6_0= ruleDatapackage ) )* otherlv_7= '}'
                    {

                    				markLeaf(elementTypeProvider.getDatapackage_DatapackagesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,23,FOLLOW_4); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getDatapackage_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_15); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:1035:4: ( (lv_datapackages_6_0= ruleDatapackage ) )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==39) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1036:5: (lv_datapackages_6_0= ruleDatapackage )
                    	    {
                    	    // PsiInternalEJSL.g:1036:5: (lv_datapackages_6_0= ruleDatapackage )
                    	    // PsiInternalEJSL.g:1037:6: lv_datapackages_6_0= ruleDatapackage
                    	    {

                    	    						markComposite(elementTypeProvider.getDatapackage_DatapackagesDatapackageParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_datapackages_6_0=ruleDatapackage();

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
                    	    break loop25;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDatapackage_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_7=(Token)match(input,19,FOLLOW_37); 

                    				doneLeaf(otherlv_7);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:1058:3: (otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==22) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // PsiInternalEJSL.g:1059:4: otherlv_8= 'entities' otherlv_9= '{' ( (lv_entities_10_0= ruleEntity ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getDatapackage_EntitiesKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,22,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getDatapackage_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_13); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:1073:4: ( (lv_entities_10_0= ruleEntity ) )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==40) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1074:5: (lv_entities_10_0= ruleEntity )
                    	    {
                    	    // PsiInternalEJSL.g:1074:5: (lv_entities_10_0= ruleEntity )
                    	    // PsiInternalEJSL.g:1075:6: lv_entities_10_0= ruleEntity
                    	    {

                    	    						markComposite(elementTypeProvider.getDatapackage_EntitiesEntityParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_13);
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
                    	    break loop27;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDatapackage_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_38); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:1096:3: (otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==17) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // PsiInternalEJSL.g:1097:4: otherlv_12= 'datatypes' otherlv_13= '{' ( (lv_datatypes_14_0= ruleDatatype ) )* otherlv_15= '}'
                    {

                    				markLeaf(elementTypeProvider.getDatapackage_DatatypesKeyword_6_0ElementType());
                    			
                    otherlv_12=(Token)match(input,17,FOLLOW_4); 

                    				doneLeaf(otherlv_12);
                    			

                    				markLeaf(elementTypeProvider.getDatapackage_LeftCurlyBracketKeyword_6_1ElementType());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_39); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:1111:4: ( (lv_datatypes_14_0= ruleDatatype ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==30) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1112:5: (lv_datatypes_14_0= ruleDatatype )
                    	    {
                    	    // PsiInternalEJSL.g:1112:5: (lv_datatypes_14_0= ruleDatatype )
                    	    // PsiInternalEJSL.g:1113:6: lv_datatypes_14_0= ruleDatatype
                    	    {

                    	    						markComposite(elementTypeProvider.getDatapackage_DatatypesDatatypeParserRuleCall_6_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_39);
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
                    	    break loop29;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDatapackage_RightCurlyBracketKeyword_6_3ElementType());
                    			
                    otherlv_15=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_15);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getDatapackage_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_16=(Token)match(input,19,FOLLOW_2); 

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
    // $ANTLR end "ruleDatapackage"


    // $ANTLR start "entryRuleEntity"
    // PsiInternalEJSL.g:1145:1: entryRuleEntity returns [Boolean current=false] : iv_ruleEntity= ruleEntity EOF ;
    public final Boolean entryRuleEntity() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleEntity = null;


        try {
            // PsiInternalEJSL.g:1145:48: (iv_ruleEntity= ruleEntity EOF )
            // PsiInternalEJSL.g:1146:2: iv_ruleEntity= ruleEntity EOF
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
    // PsiInternalEJSL.g:1152:1: ruleEntity returns [Boolean current=false] : ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' ) ;
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
            // PsiInternalEJSL.g:1153:1: ( ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' ) )
            // PsiInternalEJSL.g:1154:2: ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' )
            {
            // PsiInternalEJSL.g:1154:2: ( () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}' )
            // PsiInternalEJSL.g:1155:3: () otherlv_1= 'Entity' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )? (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )? otherlv_14= '}'
            {
            // PsiInternalEJSL.g:1155:3: ()
            // PsiInternalEJSL.g:1156:4: 
            {

            				precedeComposite(elementTypeProvider.getEntity_EntityAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getEntity_EntityKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,40,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1169:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:1170:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:1170:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:1171:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getEntity_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_41);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:1184:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==41) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // PsiInternalEJSL.g:1185:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {

                    				markLeaf(elementTypeProvider.getEntity_ExtendsKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,41,FOLLOW_40); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalEJSL.g:1192:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:1193:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:1193:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:1194:6: ruleQualifiedName
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
            		
            otherlv_5=(Token)match(input,16,FOLLOW_42); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:1217:3: (otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}' )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==42) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // PsiInternalEJSL.g:1218:4: otherlv_6= 'attributes' otherlv_7= '{' ( (lv_attributes_8_0= ruleAttribute ) )* otherlv_9= '}'
                    {

                    				markLeaf(elementTypeProvider.getEntity_AttributesKeyword_5_0ElementType());
                    			
                    otherlv_6=(Token)match(input,42,FOLLOW_4); 

                    				doneLeaf(otherlv_6);
                    			

                    				markLeaf(elementTypeProvider.getEntity_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_7=(Token)match(input,16,FOLLOW_43); 

                    				doneLeaf(otherlv_7);
                    			
                    // PsiInternalEJSL.g:1232:4: ( (lv_attributes_8_0= ruleAttribute ) )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==44) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1233:5: (lv_attributes_8_0= ruleAttribute )
                    	    {
                    	    // PsiInternalEJSL.g:1233:5: (lv_attributes_8_0= ruleAttribute )
                    	    // PsiInternalEJSL.g:1234:6: lv_attributes_8_0= ruleAttribute
                    	    {

                    	    						markComposite(elementTypeProvider.getEntity_AttributesAttributeParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_43);
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
                    	    break loop32;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEntity_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_9=(Token)match(input,19,FOLLOW_44); 

                    				doneLeaf(otherlv_9);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:1255:3: (otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==43) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // PsiInternalEJSL.g:1256:4: otherlv_10= 'references' otherlv_11= '{' ( (lv_references_12_0= ruleReference ) )* otherlv_13= '}'
                    {

                    				markLeaf(elementTypeProvider.getEntity_ReferencesKeyword_6_0ElementType());
                    			
                    otherlv_10=(Token)match(input,43,FOLLOW_4); 

                    				doneLeaf(otherlv_10);
                    			

                    				markLeaf(elementTypeProvider.getEntity_LeftCurlyBracketKeyword_6_1ElementType());
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_45); 

                    				doneLeaf(otherlv_11);
                    			
                    // PsiInternalEJSL.g:1270:4: ( (lv_references_12_0= ruleReference ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==48) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1271:5: (lv_references_12_0= ruleReference )
                    	    {
                    	    // PsiInternalEJSL.g:1271:5: (lv_references_12_0= ruleReference )
                    	    // PsiInternalEJSL.g:1272:6: lv_references_12_0= ruleReference
                    	    {

                    	    						markComposite(elementTypeProvider.getEntity_ReferencesReferenceParserRuleCall_6_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_45);
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
                    	    break loop34;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getEntity_RightCurlyBracketKeyword_6_3ElementType());
                    			
                    otherlv_13=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_13);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getEntity_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_14=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:1304:1: entryRuleAttribute returns [Boolean current=false] : iv_ruleAttribute= ruleAttribute EOF ;
    public final Boolean entryRuleAttribute() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAttribute = null;


        try {
            // PsiInternalEJSL.g:1304:51: (iv_ruleAttribute= ruleAttribute EOF )
            // PsiInternalEJSL.g:1305:2: iv_ruleAttribute= ruleAttribute EOF
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
    // PsiInternalEJSL.g:1311:1: ruleAttribute returns [Boolean current=false] : ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}' ) ;
    public final Boolean ruleAttribute() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_isunique_7_0=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_type_6_0 = null;


        try {
            // PsiInternalEJSL.g:1312:1: ( ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}' ) )
            // PsiInternalEJSL.g:1313:2: ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}' )
            {
            // PsiInternalEJSL.g:1313:2: ( () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}' )
            // PsiInternalEJSL.g:1314:3: () otherlv_1= 'Attribute' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'type' otherlv_5= '=' ( (lv_type_6_0= ruleType ) ) ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )? otherlv_11= '}'
            {
            // PsiInternalEJSL.g:1314:3: ()
            // PsiInternalEJSL.g:1315:4: 
            {

            				precedeComposite(elementTypeProvider.getAttribute_AttributeAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getAttribute_AttributeKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,44,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1328:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:1329:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:1329:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:1330:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_26); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getAttribute_TypeKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,32,FOLLOW_23); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getAttribute_EqualsSignKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,28,FOLLOW_27); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:1364:3: ( (lv_type_6_0= ruleType ) )
            // PsiInternalEJSL.g:1365:4: (lv_type_6_0= ruleType )
            {
            // PsiInternalEJSL.g:1365:4: (lv_type_6_0= ruleType )
            // PsiInternalEJSL.g:1366:5: lv_type_6_0= ruleType
            {

            					markComposite(elementTypeProvider.getAttribute_TypeTypeParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_46);
            lv_type_6_0=ruleType();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:1379:3: ( ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )? )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==45) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // PsiInternalEJSL.g:1380:4: ( (lv_isunique_7_0= 'Unique attribute' ) ) (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )?
                    {
                    // PsiInternalEJSL.g:1380:4: ( (lv_isunique_7_0= 'Unique attribute' ) )
                    // PsiInternalEJSL.g:1381:5: (lv_isunique_7_0= 'Unique attribute' )
                    {
                    // PsiInternalEJSL.g:1381:5: (lv_isunique_7_0= 'Unique attribute' )
                    // PsiInternalEJSL.g:1382:6: lv_isunique_7_0= 'Unique attribute'
                    {

                    						markLeaf(elementTypeProvider.getAttribute_IsuniqueUniqueAttributeKeyword_7_0_0ElementType());
                    					
                    lv_isunique_7_0=(Token)match(input,45,FOLLOW_47); 

                    						doneLeaf(lv_isunique_7_0);
                    					

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    }


                    }

                    // PsiInternalEJSL.g:1397:4: (otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' ) )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==46) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // PsiInternalEJSL.g:1398:5: otherlv_8= 'with' ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' )
                            {

                            					markLeaf(elementTypeProvider.getAttribute_WithKeyword_7_1_0ElementType());
                            				
                            otherlv_8=(Token)match(input,46,FOLLOW_48); 

                            					doneLeaf(otherlv_8);
                            				
                            // PsiInternalEJSL.g:1405:5: ( ( ( ruleQualifiedName ) ) | otherlv_10= 'ID' )
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
                                    // PsiInternalEJSL.g:1406:6: ( ( ruleQualifiedName ) )
                                    {
                                    // PsiInternalEJSL.g:1406:6: ( ( ruleQualifiedName ) )
                                    // PsiInternalEJSL.g:1407:7: ( ruleQualifiedName )
                                    {
                                    // PsiInternalEJSL.g:1407:7: ( ruleQualifiedName )
                                    // PsiInternalEJSL.g:1408:8: ruleQualifiedName
                                    {

                                    								if (!current) {
                                    									associateWithSemanticElement();
                                    									current = true;
                                    								}
                                    							

                                    								markComposite(elementTypeProvider.getAttribute_WithattributeCrossReference_7_1_1_0_0ElementType());
                                    							
                                    pushFollow(FOLLOW_20);
                                    ruleQualifiedName();

                                    state._fsp--;


                                    								doneComposite();
                                    							

                                    }


                                    }


                                    }
                                    break;
                                case 2 :
                                    // PsiInternalEJSL.g:1424:6: otherlv_10= 'ID'
                                    {

                                    						markLeaf(elementTypeProvider.getAttribute_IDKeyword_7_1_1_1ElementType());
                                    					
                                    otherlv_10=(Token)match(input,47,FOLLOW_20); 

                                    						doneLeaf(otherlv_10);
                                    					

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getAttribute_RightCurlyBracketKeyword_8ElementType());
            		
            otherlv_11=(Token)match(input,19,FOLLOW_2); 

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
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleReference"
    // PsiInternalEJSL.g:1445:1: entryRuleReference returns [Boolean current=false] : iv_ruleReference= ruleReference EOF ;
    public final Boolean entryRuleReference() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleReference = null;


        try {
            // PsiInternalEJSL.g:1445:51: (iv_ruleReference= ruleReference EOF )
            // PsiInternalEJSL.g:1446:2: iv_ruleReference= ruleReference EOF
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
    // PsiInternalEJSL.g:1452:1: ruleReference returns [Boolean current=false] : ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}' ) ;
    public final Boolean ruleReference() throws RecognitionException {
        Boolean current = false;

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
        Boolean lv_lower_14_0 = null;

        Boolean lv_upper_17_0 = null;


        try {
            // PsiInternalEJSL.g:1453:1: ( ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}' ) )
            // PsiInternalEJSL.g:1454:2: ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}' )
            {
            // PsiInternalEJSL.g:1454:2: ( () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}' )
            // PsiInternalEJSL.g:1455:3: () otherlv_1= 'Reference' otherlv_2= '{' otherlv_3= 'Attribute' otherlv_4= '=' ( ( ruleQualifiedName ) ) otherlv_6= '*Entity' otherlv_7= '=' ( ( ruleQualifiedName ) ) otherlv_9= 'Reference' otherlv_10= '=' ( ( ruleQualifiedName ) ) otherlv_12= 'lower' otherlv_13= '=' ( (lv_lower_14_0= ruleNUMBER ) ) otherlv_15= 'upper' otherlv_16= '=' ( (lv_upper_17_0= ruleNUMBER ) ) otherlv_18= '}'
            {
            // PsiInternalEJSL.g:1455:3: ()
            // PsiInternalEJSL.g:1456:4: 
            {

            				precedeComposite(elementTypeProvider.getReference_ReferenceAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getReference_ReferenceKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,48,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getReference_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_49); 

            			doneLeaf(otherlv_2);
            		

            			markLeaf(elementTypeProvider.getReference_AttributeKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,44,FOLLOW_23); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getReference_EqualsSignKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,28,FOLLOW_40); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:1490:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:1491:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:1491:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:1492:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getReference_AttributeAttributeCrossReference_5_0ElementType());
            				
            pushFollow(FOLLOW_50);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }


            			markLeaf(elementTypeProvider.getReference_EntityKeyword_6ElementType());
            		
            otherlv_6=(Token)match(input,49,FOLLOW_23); 

            			doneLeaf(otherlv_6);
            		

            			markLeaf(elementTypeProvider.getReference_EqualsSignKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,28,FOLLOW_40); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:1521:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:1522:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:1522:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:1523:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getReference_EntityEntityCrossReference_8_0ElementType());
            				
            pushFollow(FOLLOW_51);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }


            			markLeaf(elementTypeProvider.getReference_ReferenceKeyword_9ElementType());
            		
            otherlv_9=(Token)match(input,48,FOLLOW_23); 

            			doneLeaf(otherlv_9);
            		

            			markLeaf(elementTypeProvider.getReference_EqualsSignKeyword_10ElementType());
            		
            otherlv_10=(Token)match(input,28,FOLLOW_40); 

            			doneLeaf(otherlv_10);
            		
            // PsiInternalEJSL.g:1552:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:1553:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:1553:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:1554:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getReference_AttributereferecedAttributeCrossReference_11_0ElementType());
            				
            pushFollow(FOLLOW_52);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }


            			markLeaf(elementTypeProvider.getReference_LowerKeyword_12ElementType());
            		
            otherlv_12=(Token)match(input,50,FOLLOW_23); 

            			doneLeaf(otherlv_12);
            		

            			markLeaf(elementTypeProvider.getReference_EqualsSignKeyword_13ElementType());
            		
            otherlv_13=(Token)match(input,28,FOLLOW_53); 

            			doneLeaf(otherlv_13);
            		
            // PsiInternalEJSL.g:1583:3: ( (lv_lower_14_0= ruleNUMBER ) )
            // PsiInternalEJSL.g:1584:4: (lv_lower_14_0= ruleNUMBER )
            {
            // PsiInternalEJSL.g:1584:4: (lv_lower_14_0= ruleNUMBER )
            // PsiInternalEJSL.g:1585:5: lv_lower_14_0= ruleNUMBER
            {

            					markComposite(elementTypeProvider.getReference_LowerNUMBERParserRuleCall_14_0ElementType());
            				
            pushFollow(FOLLOW_54);
            lv_lower_14_0=ruleNUMBER();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getReference_UpperKeyword_15ElementType());
            		
            otherlv_15=(Token)match(input,51,FOLLOW_23); 

            			doneLeaf(otherlv_15);
            		

            			markLeaf(elementTypeProvider.getReference_EqualsSignKeyword_16ElementType());
            		
            otherlv_16=(Token)match(input,28,FOLLOW_53); 

            			doneLeaf(otherlv_16);
            		
            // PsiInternalEJSL.g:1612:3: ( (lv_upper_17_0= ruleNUMBER ) )
            // PsiInternalEJSL.g:1613:4: (lv_upper_17_0= ruleNUMBER )
            {
            // PsiInternalEJSL.g:1613:4: (lv_upper_17_0= ruleNUMBER )
            // PsiInternalEJSL.g:1614:5: lv_upper_17_0= ruleNUMBER
            {

            					markComposite(elementTypeProvider.getReference_UpperNUMBERParserRuleCall_17_0ElementType());
            				
            pushFollow(FOLLOW_20);
            lv_upper_17_0=ruleNUMBER();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }


            			markLeaf(elementTypeProvider.getReference_RightCurlyBracketKeyword_18ElementType());
            		
            otherlv_18=(Token)match(input,19,FOLLOW_2); 

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
    // $ANTLR end "ruleReference"


    // $ANTLR start "entryRulePage"
    // PsiInternalEJSL.g:1638:1: entryRulePage returns [Boolean current=false] : iv_rulePage= rulePage EOF ;
    public final Boolean entryRulePage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePage = null;


        try {
            // PsiInternalEJSL.g:1638:46: (iv_rulePage= rulePage EOF )
            // PsiInternalEJSL.g:1639:2: iv_rulePage= rulePage EOF
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
    // PsiInternalEJSL.g:1645:1: rulePage returns [Boolean current=false] : (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage ) ;
    public final Boolean rulePage() throws RecognitionException {
        Boolean current = false;

        Boolean this_StaticPage_0 = null;

        Boolean this_DynamicPage_1 = null;


        try {
            // PsiInternalEJSL.g:1646:1: ( (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage ) )
            // PsiInternalEJSL.g:1647:2: (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage )
            {
            // PsiInternalEJSL.g:1647:2: (this_StaticPage_0= ruleStaticPage | this_DynamicPage_1= ruleDynamicPage )
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
                    // PsiInternalEJSL.g:1648:3: this_StaticPage_0= ruleStaticPage
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
                    // PsiInternalEJSL.g:1657:3: this_DynamicPage_1= ruleDynamicPage
                    {

                    			markComposite(elementTypeProvider.getPage_DynamicPageParserRuleCall_1ElementType());
                    		
                    pushFollow(FOLLOW_2);
                    this_DynamicPage_1=ruleDynamicPage();

                    state._fsp--;


                    			current = this_DynamicPage_1;
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
    // PsiInternalEJSL.g:1669:1: entryRuleStaticPage returns [Boolean current=false] : iv_ruleStaticPage= ruleStaticPage EOF ;
    public final Boolean entryRuleStaticPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleStaticPage = null;


        try {
            // PsiInternalEJSL.g:1669:52: (iv_ruleStaticPage= ruleStaticPage EOF )
            // PsiInternalEJSL.g:1670:2: iv_ruleStaticPage= ruleStaticPage EOF
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
    // PsiInternalEJSL.g:1676:1: ruleStaticPage returns [Boolean current=false] : ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}' ) ;
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
        Token lv_HTMLBody_22_0=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_localparameters_14_0 = null;

        Boolean lv_links_18_0 = null;


        try {
            // PsiInternalEJSL.g:1677:1: ( ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}' ) )
            // PsiInternalEJSL.g:1678:2: ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}' )
            {
            // PsiInternalEJSL.g:1678:2: ( () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}' )
            // PsiInternalEJSL.g:1679:3: () otherlv_1= 'StaticPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )? (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )? otherlv_20= 'HTMLBody' otherlv_21= '{' ( (lv_HTMLBody_22_0= RULE_STRING ) ) otherlv_23= '}' otherlv_24= '}'
            {
            // PsiInternalEJSL.g:1679:3: ()
            // PsiInternalEJSL.g:1680:4: 
            {

            				precedeComposite(elementTypeProvider.getStaticPage_StaticPageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getStaticPage_StaticPageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,52,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1693:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:1694:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:1694:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:1695:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_55); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:1715:3: (otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )* )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==53) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // PsiInternalEJSL.g:1716:4: otherlv_4= '*ParameterGroups' ( (otherlv_5= RULE_ID ) ) (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_ParameterGroupsKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,53,FOLLOW_25); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:1723:4: ( (otherlv_5= RULE_ID ) )
                    // PsiInternalEJSL.g:1724:5: (otherlv_5= RULE_ID )
                    {
                    // PsiInternalEJSL.g:1724:5: (otherlv_5= RULE_ID )
                    // PsiInternalEJSL.g:1725:6: otherlv_5= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getStaticPage_ParametergroupsParameterGroupCrossReference_4_1_0ElementType());
                    					
                    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_56); 

                    						doneLeaf(otherlv_5);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:1740:4: (otherlv_6= ',' ( (otherlv_7= RULE_ID ) ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==18) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1741:5: otherlv_6= ',' ( (otherlv_7= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getStaticPage_CommaKeyword_4_2_0ElementType());
                    	    				
                    	    otherlv_6=(Token)match(input,18,FOLLOW_25); 

                    	    					doneLeaf(otherlv_6);
                    	    				
                    	    // PsiInternalEJSL.g:1748:5: ( (otherlv_7= RULE_ID ) )
                    	    // PsiInternalEJSL.g:1749:6: (otherlv_7= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:1749:6: (otherlv_7= RULE_ID )
                    	    // PsiInternalEJSL.g:1750:7: otherlv_7= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getStaticPage_ParametergroupsParameterGroupCrossReference_4_2_1_0ElementType());
                    	    						
                    	    otherlv_7=(Token)match(input,RULE_ID,FOLLOW_56); 

                    	    							doneLeaf(otherlv_7);
                    	    						

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

            // PsiInternalEJSL.g:1767:3: (otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==54) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // PsiInternalEJSL.g:1768:4: otherlv_8= '*Globalparameters' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_GlobalparametersKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,54,FOLLOW_25); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:1775:4: ( (otherlv_9= RULE_ID ) )
                    // PsiInternalEJSL.g:1776:5: (otherlv_9= RULE_ID )
                    {
                    // PsiInternalEJSL.g:1776:5: (otherlv_9= RULE_ID )
                    // PsiInternalEJSL.g:1777:6: otherlv_9= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getStaticPage_GlobalparametersParameterCrossReference_5_1_0ElementType());
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_57); 

                    						doneLeaf(otherlv_9);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:1792:4: (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==18) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1793:5: otherlv_10= ',' ( (otherlv_11= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getStaticPage_CommaKeyword_5_2_0ElementType());
                    	    				
                    	    otherlv_10=(Token)match(input,18,FOLLOW_25); 

                    	    					doneLeaf(otherlv_10);
                    	    				
                    	    // PsiInternalEJSL.g:1800:5: ( (otherlv_11= RULE_ID ) )
                    	    // PsiInternalEJSL.g:1801:6: (otherlv_11= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:1801:6: (otherlv_11= RULE_ID )
                    	    // PsiInternalEJSL.g:1802:7: otherlv_11= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getStaticPage_GlobalparametersParameterCrossReference_5_2_1_0ElementType());
                    	    						
                    	    otherlv_11=(Token)match(input,RULE_ID,FOLLOW_57); 

                    	    							doneLeaf(otherlv_11);
                    	    						

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

            // PsiInternalEJSL.g:1819:3: (otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==55) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // PsiInternalEJSL.g:1820:4: otherlv_12= 'localparameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}'
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_LocalparametersKeyword_6_0ElementType());
                    			
                    otherlv_12=(Token)match(input,55,FOLLOW_4); 

                    				doneLeaf(otherlv_12);
                    			

                    				markLeaf(elementTypeProvider.getStaticPage_LeftCurlyBracketKeyword_6_1ElementType());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_9); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:1834:4: ( (lv_localparameters_14_0= ruleParameter ) )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==31) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1835:5: (lv_localparameters_14_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:1835:5: (lv_localparameters_14_0= ruleParameter )
                    	    // PsiInternalEJSL.g:1836:6: lv_localparameters_14_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getStaticPage_LocalparametersParameterParserRuleCall_6_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_9);
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
                    	    break loop44;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_6_3ElementType());
                    			
                    otherlv_15=(Token)match(input,19,FOLLOW_58); 

                    				doneLeaf(otherlv_15);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:1857:3: (otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}' )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==56) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // PsiInternalEJSL.g:1858:4: otherlv_16= 'links' otherlv_17= '{' ( (lv_links_18_0= ruleLink ) )* otherlv_19= '}'
                    {

                    				markLeaf(elementTypeProvider.getStaticPage_LinksKeyword_7_0ElementType());
                    			
                    otherlv_16=(Token)match(input,56,FOLLOW_4); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getStaticPage_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_59); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:1872:4: ( (lv_links_18_0= ruleLink ) )*
                    loop46:
                    do {
                        int alt46=2;
                        int LA46_0 = input.LA(1);

                        if ( (LA46_0==67||(LA46_0>=70 && LA46_0<=71)) ) {
                            alt46=1;
                        }


                        switch (alt46) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:1873:5: (lv_links_18_0= ruleLink )
                    	    {
                    	    // PsiInternalEJSL.g:1873:5: (lv_links_18_0= ruleLink )
                    	    // PsiInternalEJSL.g:1874:6: lv_links_18_0= ruleLink
                    	    {

                    	    						markComposite(elementTypeProvider.getStaticPage_LinksLinkParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_59);
                    	    lv_links_18_0=ruleLink();

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
                    	    break loop46;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_19=(Token)match(input,19,FOLLOW_60); 

                    				doneLeaf(otherlv_19);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getStaticPage_HTMLBodyKeyword_8ElementType());
            		
            otherlv_20=(Token)match(input,57,FOLLOW_4); 

            			doneLeaf(otherlv_20);
            		

            			markLeaf(elementTypeProvider.getStaticPage_LeftCurlyBracketKeyword_9ElementType());
            		
            otherlv_21=(Token)match(input,16,FOLLOW_3); 

            			doneLeaf(otherlv_21);
            		
            // PsiInternalEJSL.g:1909:3: ( (lv_HTMLBody_22_0= RULE_STRING ) )
            // PsiInternalEJSL.g:1910:4: (lv_HTMLBody_22_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:1910:4: (lv_HTMLBody_22_0= RULE_STRING )
            // PsiInternalEJSL.g:1911:5: lv_HTMLBody_22_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getStaticPage_HTMLBodySTRINGTerminalRuleCall_10_0ElementType());
            				
            lv_HTMLBody_22_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_HTMLBody_22_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_11ElementType());
            		
            otherlv_23=(Token)match(input,19,FOLLOW_20); 

            			doneLeaf(otherlv_23);
            		

            			markLeaf(elementTypeProvider.getStaticPage_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_24=(Token)match(input,19,FOLLOW_2); 

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
    // $ANTLR end "ruleStaticPage"


    // $ANTLR start "entryRuleDynamicPage"
    // PsiInternalEJSL.g:1944:1: entryRuleDynamicPage returns [Boolean current=false] : iv_ruleDynamicPage= ruleDynamicPage EOF ;
    public final Boolean entryRuleDynamicPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDynamicPage = null;


        try {
            // PsiInternalEJSL.g:1944:53: (iv_ruleDynamicPage= ruleDynamicPage EOF )
            // PsiInternalEJSL.g:1945:2: iv_ruleDynamicPage= ruleDynamicPage EOF
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
    // PsiInternalEJSL.g:1951:1: ruleDynamicPage returns [Boolean current=false] : (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage ) ;
    public final Boolean ruleDynamicPage() throws RecognitionException {
        Boolean current = false;

        Boolean this_IndexPage_0 = null;

        Boolean this_DetailsPage_1 = null;


        try {
            // PsiInternalEJSL.g:1952:1: ( (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage ) )
            // PsiInternalEJSL.g:1953:2: (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage )
            {
            // PsiInternalEJSL.g:1953:2: (this_IndexPage_0= ruleIndexPage | this_DetailsPage_1= ruleDetailsPage )
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
                    // PsiInternalEJSL.g:1954:3: this_IndexPage_0= ruleIndexPage
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
                    // PsiInternalEJSL.g:1963:3: this_DetailsPage_1= ruleDetailsPage
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
    // PsiInternalEJSL.g:1975:1: entryRuleIndexPage returns [Boolean current=false] : iv_ruleIndexPage= ruleIndexPage EOF ;
    public final Boolean entryRuleIndexPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleIndexPage = null;


        try {
            // PsiInternalEJSL.g:1975:51: (iv_ruleIndexPage= ruleIndexPage EOF )
            // PsiInternalEJSL.g:1976:2: iv_ruleIndexPage= ruleIndexPage EOF
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
    // PsiInternalEJSL.g:1982:1: ruleIndexPage returns [Boolean current=false] : ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}' ) ;
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
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_30=null;
        Token otherlv_31=null;
        Token otherlv_33=null;
        Token otherlv_34=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_localparameters_18_0 = null;

        Boolean lv_links_32_0 = null;


        try {
            // PsiInternalEJSL.g:1983:1: ( ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}' ) )
            // PsiInternalEJSL.g:1984:2: ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}' )
            {
            // PsiInternalEJSL.g:1984:2: ( () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}' )
            // PsiInternalEJSL.g:1985:3: () otherlv_1= 'IndexPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )? (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )? (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )? otherlv_34= '}'
            {
            // PsiInternalEJSL.g:1985:3: ()
            // PsiInternalEJSL.g:1986:4: 
            {

            				precedeComposite(elementTypeProvider.getIndexPage_IndexPageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getIndexPage_IndexPageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,58,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:1999:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:2000:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:2000:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:2001:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_61); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:2021:3: (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==59) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // PsiInternalEJSL.g:2022:4: otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_EntitiesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,59,FOLLOW_40); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:2029:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2030:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2030:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2031:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getIndexPage_EntitiesEntityCrossReference_4_1_0ElementType());
                    					
                    pushFollow(FOLLOW_62);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2046:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==18) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2047:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_4_2_0ElementType());
                    	    				
                    	    otherlv_6=(Token)match(input,18,FOLLOW_40); 

                    	    					doneLeaf(otherlv_6);
                    	    				
                    	    // PsiInternalEJSL.g:2054:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2055:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2055:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2056:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getIndexPage_EntitiesEntityCrossReference_4_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_62);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

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

            // PsiInternalEJSL.g:2073:3: (otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )* )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==53) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // PsiInternalEJSL.g:2074:4: otherlv_8= '*ParameterGroups' ( (otherlv_9= RULE_ID ) ) (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_ParameterGroupsKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,53,FOLLOW_25); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:2081:4: ( (otherlv_9= RULE_ID ) )
                    // PsiInternalEJSL.g:2082:5: (otherlv_9= RULE_ID )
                    {
                    // PsiInternalEJSL.g:2082:5: (otherlv_9= RULE_ID )
                    // PsiInternalEJSL.g:2083:6: otherlv_9= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getIndexPage_ParametergroupsParameterGroupCrossReference_5_1_0ElementType());
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_63); 

                    						doneLeaf(otherlv_9);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2098:4: (otherlv_10= ',' ( (otherlv_11= RULE_ID ) ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==18) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2099:5: otherlv_10= ',' ( (otherlv_11= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_5_2_0ElementType());
                    	    				
                    	    otherlv_10=(Token)match(input,18,FOLLOW_25); 

                    	    					doneLeaf(otherlv_10);
                    	    				
                    	    // PsiInternalEJSL.g:2106:5: ( (otherlv_11= RULE_ID ) )
                    	    // PsiInternalEJSL.g:2107:6: (otherlv_11= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:2107:6: (otherlv_11= RULE_ID )
                    	    // PsiInternalEJSL.g:2108:7: otherlv_11= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getIndexPage_ParametergroupsParameterGroupCrossReference_5_2_1_0ElementType());
                    	    						
                    	    otherlv_11=(Token)match(input,RULE_ID,FOLLOW_63); 

                    	    							doneLeaf(otherlv_11);
                    	    						

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

            // PsiInternalEJSL.g:2125:3: (otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==54) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // PsiInternalEJSL.g:2126:4: otherlv_12= '*Globalparameters' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_GlobalparametersKeyword_6_0ElementType());
                    			
                    otherlv_12=(Token)match(input,54,FOLLOW_25); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:2133:4: ( (otherlv_13= RULE_ID ) )
                    // PsiInternalEJSL.g:2134:5: (otherlv_13= RULE_ID )
                    {
                    // PsiInternalEJSL.g:2134:5: (otherlv_13= RULE_ID )
                    // PsiInternalEJSL.g:2135:6: otherlv_13= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getIndexPage_GlobalparametersParameterCrossReference_6_1_0ElementType());
                    					
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_64); 

                    						doneLeaf(otherlv_13);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2150:4: (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==18) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2151:5: otherlv_14= ',' ( (otherlv_15= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_6_2_0ElementType());
                    	    				
                    	    otherlv_14=(Token)match(input,18,FOLLOW_25); 

                    	    					doneLeaf(otherlv_14);
                    	    				
                    	    // PsiInternalEJSL.g:2158:5: ( (otherlv_15= RULE_ID ) )
                    	    // PsiInternalEJSL.g:2159:6: (otherlv_15= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:2159:6: (otherlv_15= RULE_ID )
                    	    // PsiInternalEJSL.g:2160:7: otherlv_15= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getIndexPage_GlobalparametersParameterCrossReference_6_2_1_0ElementType());
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_64); 

                    	    							doneLeaf(otherlv_15);
                    	    						

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

            // PsiInternalEJSL.g:2177:3: (otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}' )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==55) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // PsiInternalEJSL.g:2178:4: otherlv_16= 'localparameters' otherlv_17= '{' ( (lv_localparameters_18_0= ruleParameter ) )* otherlv_19= '}'
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_LocalparametersKeyword_7_0ElementType());
                    			
                    otherlv_16=(Token)match(input,55,FOLLOW_4); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getIndexPage_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_9); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:2192:4: ( (lv_localparameters_18_0= ruleParameter ) )*
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==31) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2193:5: (lv_localparameters_18_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:2193:5: (lv_localparameters_18_0= ruleParameter )
                    	    // PsiInternalEJSL.g:2194:6: lv_localparameters_18_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getIndexPage_LocalparametersParameterParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_9);
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
                    	    break loop55;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getIndexPage_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_19=(Token)match(input,19,FOLLOW_65); 

                    				doneLeaf(otherlv_19);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:2215:3: (otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==60) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // PsiInternalEJSL.g:2216:4: otherlv_20= 'table columns' otherlv_21= '=' ( ( ruleQualifiedName ) ) (otherlv_23= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_TableColumnsKeyword_8_0ElementType());
                    			
                    otherlv_20=(Token)match(input,60,FOLLOW_23); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getIndexPage_EqualsSignKeyword_8_1ElementType());
                    			
                    otherlv_21=(Token)match(input,28,FOLLOW_40); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:2230:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2231:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2231:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2232:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getIndexPage_TablecolumnsAttributeCrossReference_8_2_0ElementType());
                    					
                    pushFollow(FOLLOW_66);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2247:4: (otherlv_23= ',' ( ( ruleQualifiedName ) ) )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==18) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2248:5: otherlv_23= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_8_3_0ElementType());
                    	    				
                    	    otherlv_23=(Token)match(input,18,FOLLOW_40); 

                    	    					doneLeaf(otherlv_23);
                    	    				
                    	    // PsiInternalEJSL.g:2255:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2256:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2256:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2257:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getIndexPage_TablecolumnsAttributeCrossReference_8_3_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_66);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

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

            // PsiInternalEJSL.g:2274:3: (otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==61) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // PsiInternalEJSL.g:2275:4: otherlv_25= 'filters' otherlv_26= '=' ( ( ruleQualifiedName ) ) (otherlv_28= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_FiltersKeyword_9_0ElementType());
                    			
                    otherlv_25=(Token)match(input,61,FOLLOW_23); 

                    				doneLeaf(otherlv_25);
                    			

                    				markLeaf(elementTypeProvider.getIndexPage_EqualsSignKeyword_9_1ElementType());
                    			
                    otherlv_26=(Token)match(input,28,FOLLOW_40); 

                    				doneLeaf(otherlv_26);
                    			
                    // PsiInternalEJSL.g:2289:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2290:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2290:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2291:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getIndexPage_FiltersAttributeCrossReference_9_2_0ElementType());
                    					
                    pushFollow(FOLLOW_67);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2306:4: (otherlv_28= ',' ( ( ruleQualifiedName ) ) )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==18) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2307:5: otherlv_28= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getIndexPage_CommaKeyword_9_3_0ElementType());
                    	    				
                    	    otherlv_28=(Token)match(input,18,FOLLOW_40); 

                    	    					doneLeaf(otherlv_28);
                    	    				
                    	    // PsiInternalEJSL.g:2314:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2315:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2315:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2316:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getIndexPage_FiltersAttributeCrossReference_9_3_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_67);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

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

            // PsiInternalEJSL.g:2333:3: (otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}' )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==56) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // PsiInternalEJSL.g:2334:4: otherlv_30= 'links' otherlv_31= '{' ( (lv_links_32_0= ruleLink ) )* otherlv_33= '}'
                    {

                    				markLeaf(elementTypeProvider.getIndexPage_LinksKeyword_10_0ElementType());
                    			
                    otherlv_30=(Token)match(input,56,FOLLOW_4); 

                    				doneLeaf(otherlv_30);
                    			

                    				markLeaf(elementTypeProvider.getIndexPage_LeftCurlyBracketKeyword_10_1ElementType());
                    			
                    otherlv_31=(Token)match(input,16,FOLLOW_59); 

                    				doneLeaf(otherlv_31);
                    			
                    // PsiInternalEJSL.g:2348:4: ( (lv_links_32_0= ruleLink ) )*
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==67||(LA61_0>=70 && LA61_0<=71)) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2349:5: (lv_links_32_0= ruleLink )
                    	    {
                    	    // PsiInternalEJSL.g:2349:5: (lv_links_32_0= ruleLink )
                    	    // PsiInternalEJSL.g:2350:6: lv_links_32_0= ruleLink
                    	    {

                    	    						markComposite(elementTypeProvider.getIndexPage_LinksLinkParserRuleCall_10_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_59);
                    	    lv_links_32_0=ruleLink();

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
                    	    break loop61;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getIndexPage_RightCurlyBracketKeyword_10_3ElementType());
                    			
                    otherlv_33=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_33);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getIndexPage_RightCurlyBracketKeyword_11ElementType());
            		
            otherlv_34=(Token)match(input,19,FOLLOW_2); 

            			doneLeaf(otherlv_34);
            		

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
    // PsiInternalEJSL.g:2382:1: entryRuleDetailsPage returns [Boolean current=false] : iv_ruleDetailsPage= ruleDetailsPage EOF ;
    public final Boolean entryRuleDetailsPage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDetailsPage = null;


        try {
            // PsiInternalEJSL.g:2382:53: (iv_ruleDetailsPage= ruleDetailsPage EOF )
            // PsiInternalEJSL.g:2383:2: iv_ruleDetailsPage= ruleDetailsPage EOF
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
    // PsiInternalEJSL.g:2389:1: ruleDetailsPage returns [Boolean current=false] : ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}' ) ;
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
        Token otherlv_29=null;
        Token otherlv_30=null;
        Token otherlv_32=null;
        Token otherlv_34=null;
        Token otherlv_35=null;
        Token otherlv_37=null;
        Token otherlv_38=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_editfields_10_0 = null;

        Boolean lv_localparameters_22_0 = null;

        Boolean lv_links_36_0 = null;


        try {
            // PsiInternalEJSL.g:2390:1: ( ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}' ) )
            // PsiInternalEJSL.g:2391:2: ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}' )
            {
            // PsiInternalEJSL.g:2391:2: ( () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}' )
            // PsiInternalEJSL.g:2392:3: () otherlv_1= 'DetailsPage' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )? (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )? (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )? (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )? (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )? otherlv_38= '}'
            {
            // PsiInternalEJSL.g:2392:3: ()
            // PsiInternalEJSL.g:2393:4: 
            {

            				precedeComposite(elementTypeProvider.getDetailsPage_DetailsPageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getDetailsPage_DetailsPageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,62,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:2406:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:2407:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:2407:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:2408:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_68); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:2428:3: (otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==59) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // PsiInternalEJSL.g:2429:4: otherlv_4= '*Entities' ( ( ruleQualifiedName ) ) (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_EntitiesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,59,FOLLOW_40); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:2436:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2437:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2437:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2438:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getDetailsPage_EntitiesEntityCrossReference_4_1_0ElementType());
                    					
                    pushFollow(FOLLOW_69);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2453:4: (otherlv_6= ',' ( ( ruleQualifiedName ) ) )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==18) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2454:5: otherlv_6= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_4_2_0ElementType());
                    	    				
                    	    otherlv_6=(Token)match(input,18,FOLLOW_40); 

                    	    					doneLeaf(otherlv_6);
                    	    				
                    	    // PsiInternalEJSL.g:2461:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2462:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2462:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2463:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getDetailsPage_EntitiesEntityCrossReference_4_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_69);
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

            // PsiInternalEJSL.g:2480:3: (otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}' )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==63) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // PsiInternalEJSL.g:2481:4: otherlv_8= 'edit_fields' otherlv_9= '{' ( (lv_editfields_10_0= ruleDetailPageField ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_Edit_fieldsKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,63,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_70); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:2495:4: ( (lv_editfields_10_0= ruleDetailPageField ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==RULE_ID||LA65_0==124) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2496:5: (lv_editfields_10_0= ruleDetailPageField )
                    	    {
                    	    // PsiInternalEJSL.g:2496:5: (lv_editfields_10_0= ruleDetailPageField )
                    	    // PsiInternalEJSL.g:2497:6: lv_editfields_10_0= ruleDetailPageField
                    	    {

                    	    						markComposite(elementTypeProvider.getDetailsPage_EditfieldsDetailPageFieldParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_70);
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
                    	    break loop65;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_71); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:2518:3: (otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )* )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==53) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // PsiInternalEJSL.g:2519:4: otherlv_12= '*ParameterGroups' ( (otherlv_13= RULE_ID ) ) (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_ParameterGroupsKeyword_6_0ElementType());
                    			
                    otherlv_12=(Token)match(input,53,FOLLOW_25); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:2526:4: ( (otherlv_13= RULE_ID ) )
                    // PsiInternalEJSL.g:2527:5: (otherlv_13= RULE_ID )
                    {
                    // PsiInternalEJSL.g:2527:5: (otherlv_13= RULE_ID )
                    // PsiInternalEJSL.g:2528:6: otherlv_13= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getDetailsPage_ParametergroupsParameterGroupCrossReference_6_1_0ElementType());
                    					
                    otherlv_13=(Token)match(input,RULE_ID,FOLLOW_63); 

                    						doneLeaf(otherlv_13);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2543:4: (otherlv_14= ',' ( (otherlv_15= RULE_ID ) ) )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==18) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2544:5: otherlv_14= ',' ( (otherlv_15= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_6_2_0ElementType());
                    	    				
                    	    otherlv_14=(Token)match(input,18,FOLLOW_25); 

                    	    					doneLeaf(otherlv_14);
                    	    				
                    	    // PsiInternalEJSL.g:2551:5: ( (otherlv_15= RULE_ID ) )
                    	    // PsiInternalEJSL.g:2552:6: (otherlv_15= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:2552:6: (otherlv_15= RULE_ID )
                    	    // PsiInternalEJSL.g:2553:7: otherlv_15= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getDetailsPage_ParametergroupsParameterGroupCrossReference_6_2_1_0ElementType());
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_63); 

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

            // PsiInternalEJSL.g:2570:3: (otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )* )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==54) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // PsiInternalEJSL.g:2571:4: otherlv_16= '*Globalparameters' ( (otherlv_17= RULE_ID ) ) (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_GlobalparametersKeyword_7_0ElementType());
                    			
                    otherlv_16=(Token)match(input,54,FOLLOW_25); 

                    				doneLeaf(otherlv_16);
                    			
                    // PsiInternalEJSL.g:2578:4: ( (otherlv_17= RULE_ID ) )
                    // PsiInternalEJSL.g:2579:5: (otherlv_17= RULE_ID )
                    {
                    // PsiInternalEJSL.g:2579:5: (otherlv_17= RULE_ID )
                    // PsiInternalEJSL.g:2580:6: otherlv_17= RULE_ID
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getDetailsPage_GlobalparametersParameterCrossReference_7_1_0ElementType());
                    					
                    otherlv_17=(Token)match(input,RULE_ID,FOLLOW_64); 

                    						doneLeaf(otherlv_17);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2595:4: (otherlv_18= ',' ( (otherlv_19= RULE_ID ) ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==18) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2596:5: otherlv_18= ',' ( (otherlv_19= RULE_ID ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_7_2_0ElementType());
                    	    				
                    	    otherlv_18=(Token)match(input,18,FOLLOW_25); 

                    	    					doneLeaf(otherlv_18);
                    	    				
                    	    // PsiInternalEJSL.g:2603:5: ( (otherlv_19= RULE_ID ) )
                    	    // PsiInternalEJSL.g:2604:6: (otherlv_19= RULE_ID )
                    	    {
                    	    // PsiInternalEJSL.g:2604:6: (otherlv_19= RULE_ID )
                    	    // PsiInternalEJSL.g:2605:7: otherlv_19= RULE_ID
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getDetailsPage_GlobalparametersParameterCrossReference_7_2_1_0ElementType());
                    	    						
                    	    otherlv_19=(Token)match(input,RULE_ID,FOLLOW_64); 

                    	    							doneLeaf(otherlv_19);
                    	    						

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

            // PsiInternalEJSL.g:2622:3: (otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}' )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==55) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // PsiInternalEJSL.g:2623:4: otherlv_20= 'localparameters' otherlv_21= '{' ( (lv_localparameters_22_0= ruleParameter ) )* otherlv_23= '}'
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_LocalparametersKeyword_8_0ElementType());
                    			
                    otherlv_20=(Token)match(input,55,FOLLOW_4); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_9); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:2637:4: ( (lv_localparameters_22_0= ruleParameter ) )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==31) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2638:5: (lv_localparameters_22_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:2638:5: (lv_localparameters_22_0= ruleParameter )
                    	    // PsiInternalEJSL.g:2639:6: lv_localparameters_22_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getDetailsPage_LocalparametersParameterParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_9);
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
                    	    break loop71;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_23=(Token)match(input,19,FOLLOW_65); 

                    				doneLeaf(otherlv_23);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:2660:3: (otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==60) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // PsiInternalEJSL.g:2661:4: otherlv_24= 'table columns' otherlv_25= '=' ( ( ruleQualifiedName ) ) (otherlv_27= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_TableColumnsKeyword_9_0ElementType());
                    			
                    otherlv_24=(Token)match(input,60,FOLLOW_23); 

                    				doneLeaf(otherlv_24);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_EqualsSignKeyword_9_1ElementType());
                    			
                    otherlv_25=(Token)match(input,28,FOLLOW_40); 

                    				doneLeaf(otherlv_25);
                    			
                    // PsiInternalEJSL.g:2675:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2676:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2676:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2677:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getDetailsPage_TablecolumnsAttributeCrossReference_9_2_0ElementType());
                    					
                    pushFollow(FOLLOW_66);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2692:4: (otherlv_27= ',' ( ( ruleQualifiedName ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==18) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2693:5: otherlv_27= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_9_3_0ElementType());
                    	    				
                    	    otherlv_27=(Token)match(input,18,FOLLOW_40); 

                    	    					doneLeaf(otherlv_27);
                    	    				
                    	    // PsiInternalEJSL.g:2700:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2701:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2701:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2702:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getDetailsPage_TablecolumnsAttributeCrossReference_9_3_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_66);
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

            // PsiInternalEJSL.g:2719:3: (otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==61) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // PsiInternalEJSL.g:2720:4: otherlv_29= 'filters' otherlv_30= '=' ( ( ruleQualifiedName ) ) (otherlv_32= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_FiltersKeyword_10_0ElementType());
                    			
                    otherlv_29=(Token)match(input,61,FOLLOW_23); 

                    				doneLeaf(otherlv_29);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_EqualsSignKeyword_10_1ElementType());
                    			
                    otherlv_30=(Token)match(input,28,FOLLOW_40); 

                    				doneLeaf(otherlv_30);
                    			
                    // PsiInternalEJSL.g:2734:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:2735:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:2735:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:2736:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getDetailsPage_FiltersAttributeCrossReference_10_2_0ElementType());
                    					
                    pushFollow(FOLLOW_67);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:2751:4: (otherlv_32= ',' ( ( ruleQualifiedName ) ) )*
                    loop75:
                    do {
                        int alt75=2;
                        int LA75_0 = input.LA(1);

                        if ( (LA75_0==18) ) {
                            alt75=1;
                        }


                        switch (alt75) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2752:5: otherlv_32= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getDetailsPage_CommaKeyword_10_3_0ElementType());
                    	    				
                    	    otherlv_32=(Token)match(input,18,FOLLOW_40); 

                    	    					doneLeaf(otherlv_32);
                    	    				
                    	    // PsiInternalEJSL.g:2759:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:2760:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:2760:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:2761:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getDetailsPage_FiltersAttributeCrossReference_10_3_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_67);
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

            // PsiInternalEJSL.g:2778:3: (otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}' )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==56) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // PsiInternalEJSL.g:2779:4: otherlv_34= 'links' otherlv_35= '{' ( (lv_links_36_0= ruleLink ) )* otherlv_37= '}'
                    {

                    				markLeaf(elementTypeProvider.getDetailsPage_LinksKeyword_11_0ElementType());
                    			
                    otherlv_34=(Token)match(input,56,FOLLOW_4); 

                    				doneLeaf(otherlv_34);
                    			

                    				markLeaf(elementTypeProvider.getDetailsPage_LeftCurlyBracketKeyword_11_1ElementType());
                    			
                    otherlv_35=(Token)match(input,16,FOLLOW_59); 

                    				doneLeaf(otherlv_35);
                    			
                    // PsiInternalEJSL.g:2793:4: ( (lv_links_36_0= ruleLink ) )*
                    loop77:
                    do {
                        int alt77=2;
                        int LA77_0 = input.LA(1);

                        if ( (LA77_0==67||(LA77_0>=70 && LA77_0<=71)) ) {
                            alt77=1;
                        }


                        switch (alt77) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:2794:5: (lv_links_36_0= ruleLink )
                    	    {
                    	    // PsiInternalEJSL.g:2794:5: (lv_links_36_0= ruleLink )
                    	    // PsiInternalEJSL.g:2795:6: lv_links_36_0= ruleLink
                    	    {

                    	    						markComposite(elementTypeProvider.getDetailsPage_LinksLinkParserRuleCall_11_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_59);
                    	    lv_links_36_0=ruleLink();

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


                    				markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_11_3ElementType());
                    			
                    otherlv_37=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_37);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getDetailsPage_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_38=(Token)match(input,19,FOLLOW_2); 

            			doneLeaf(otherlv_38);
            		

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
    // PsiInternalEJSL.g:2827:1: entryRuleDetailPageField returns [Boolean current=false] : iv_ruleDetailPageField= ruleDetailPageField EOF ;
    public final Boolean entryRuleDetailPageField() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleDetailPageField = null;


        try {
            // PsiInternalEJSL.g:2827:57: (iv_ruleDetailPageField= ruleDetailPageField EOF )
            // PsiInternalEJSL.g:2828:2: iv_ruleDetailPageField= ruleDetailPageField EOF
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
    // PsiInternalEJSL.g:2834:1: ruleDetailPageField returns [Boolean current=false] : ( () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) ) ;
    public final Boolean ruleDetailPageField() throws RecognitionException {
        Boolean current = false;

        Token otherlv_2=null;
        Boolean lv_htmltype_3_0 = null;


        try {
            // PsiInternalEJSL.g:2835:1: ( ( () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) ) )
            // PsiInternalEJSL.g:2836:2: ( () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )
            {
            // PsiInternalEJSL.g:2836:2: ( () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) ) )
            // PsiInternalEJSL.g:2837:3: () ( ( ruleQualifiedName ) ) otherlv_2= ':' ( (lv_htmltype_3_0= ruleHTMLTypes ) )
            {
            // PsiInternalEJSL.g:2837:3: ()
            // PsiInternalEJSL.g:2838:4: 
            {

            				precedeComposite(elementTypeProvider.getDetailPageField_DetailPageFieldAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }

            // PsiInternalEJSL.g:2844:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:2845:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:2845:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:2846:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getDetailPageField_AttributeAttributeCrossReference_1_0ElementType());
            				
            pushFollow(FOLLOW_72);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }


            			markLeaf(elementTypeProvider.getDetailPageField_ColonKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,64,FOLLOW_73); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:2868:3: ( (lv_htmltype_3_0= ruleHTMLTypes ) )
            // PsiInternalEJSL.g:2869:4: (lv_htmltype_3_0= ruleHTMLTypes )
            {
            // PsiInternalEJSL.g:2869:4: (lv_htmltype_3_0= ruleHTMLTypes )
            // PsiInternalEJSL.g:2870:5: lv_htmltype_3_0= ruleHTMLTypes
            {

            					markComposite(elementTypeProvider.getDetailPageField_HtmltypeHTMLTypesParserRuleCall_3_0ElementType());
            				
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


    // $ANTLR start "entryRuleHTMLTypes"
    // PsiInternalEJSL.g:2887:1: entryRuleHTMLTypes returns [Boolean current=false] : iv_ruleHTMLTypes= ruleHTMLTypes EOF ;
    public final Boolean entryRuleHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleHTMLTypes = null;


        try {
            // PsiInternalEJSL.g:2887:51: (iv_ruleHTMLTypes= ruleHTMLTypes EOF )
            // PsiInternalEJSL.g:2888:2: iv_ruleHTMLTypes= ruleHTMLTypes EOF
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
    // PsiInternalEJSL.g:2894:1: ruleHTMLTypes returns [Boolean current=false] : (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes ) ;
    public final Boolean ruleHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean this_SimpleHTMLTypes_0 = null;

        Boolean this_ComplexHTMLTypes_1 = null;


        try {
            // PsiInternalEJSL.g:2895:1: ( (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes ) )
            // PsiInternalEJSL.g:2896:2: (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes )
            {
            // PsiInternalEJSL.g:2896:2: (this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes | this_ComplexHTMLTypes_1= ruleComplexHTMLTypes )
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
                    // PsiInternalEJSL.g:2897:3: this_SimpleHTMLTypes_0= ruleSimpleHTMLTypes
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
                    // PsiInternalEJSL.g:2906:3: this_ComplexHTMLTypes_1= ruleComplexHTMLTypes
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
    // PsiInternalEJSL.g:2918:1: entryRuleSimpleHTMLTypes returns [Boolean current=false] : iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF ;
    public final Boolean entryRuleSimpleHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleSimpleHTMLTypes = null;


        try {
            // PsiInternalEJSL.g:2918:57: (iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF )
            // PsiInternalEJSL.g:2919:2: iv_ruleSimpleHTMLTypes= ruleSimpleHTMLTypes EOF
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
    // PsiInternalEJSL.g:2925:1: ruleSimpleHTMLTypes returns [Boolean current=false] : ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) ) ;
    public final Boolean ruleSimpleHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean lv_htmltype_0_0 = null;


        try {
            // PsiInternalEJSL.g:2926:1: ( ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) ) )
            // PsiInternalEJSL.g:2927:2: ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) )
            {
            // PsiInternalEJSL.g:2927:2: ( (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds ) )
            // PsiInternalEJSL.g:2928:3: (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds )
            {
            // PsiInternalEJSL.g:2928:3: (lv_htmltype_0_0= ruleSimpleHTMLTypeKinds )
            // PsiInternalEJSL.g:2929:4: lv_htmltype_0_0= ruleSimpleHTMLTypeKinds
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
    // PsiInternalEJSL.g:2945:1: entryRuleComplexHTMLTypes returns [Boolean current=false] : iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF ;
    public final Boolean entryRuleComplexHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleComplexHTMLTypes = null;


        try {
            // PsiInternalEJSL.g:2945:58: (iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF )
            // PsiInternalEJSL.g:2946:2: iv_ruleComplexHTMLTypes= ruleComplexHTMLTypes EOF
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
    // PsiInternalEJSL.g:2952:1: ruleComplexHTMLTypes returns [Boolean current=false] : ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' ) ;
    public final Boolean ruleComplexHTMLTypes() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Boolean lv_htmltype_0_0 = null;

        Boolean lv_keyvaluepairs_2_0 = null;

        Boolean lv_keyvaluepairs_4_0 = null;


        try {
            // PsiInternalEJSL.g:2953:1: ( ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' ) )
            // PsiInternalEJSL.g:2954:2: ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' )
            {
            // PsiInternalEJSL.g:2954:2: ( ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')' )
            // PsiInternalEJSL.g:2955:3: ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) ) otherlv_1= '(' ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) ) (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )* otherlv_5= ')'
            {
            // PsiInternalEJSL.g:2955:3: ( (lv_htmltype_0_0= ruleComplexHTMLTypeKinds ) )
            // PsiInternalEJSL.g:2956:4: (lv_htmltype_0_0= ruleComplexHTMLTypeKinds )
            {
            // PsiInternalEJSL.g:2956:4: (lv_htmltype_0_0= ruleComplexHTMLTypeKinds )
            // PsiInternalEJSL.g:2957:5: lv_htmltype_0_0= ruleComplexHTMLTypeKinds
            {

            					markComposite(elementTypeProvider.getComplexHTMLTypes_HtmltypeComplexHTMLTypeKindsEnumRuleCall_0_0ElementType());
            				
            pushFollow(FOLLOW_74);
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
            		
            otherlv_1=(Token)match(input,65,FOLLOW_75); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:2977:3: ( (lv_keyvaluepairs_2_0= ruleKeyValuePair ) )
            // PsiInternalEJSL.g:2978:4: (lv_keyvaluepairs_2_0= ruleKeyValuePair )
            {
            // PsiInternalEJSL.g:2978:4: (lv_keyvaluepairs_2_0= ruleKeyValuePair )
            // PsiInternalEJSL.g:2979:5: lv_keyvaluepairs_2_0= ruleKeyValuePair
            {

            					markComposite(elementTypeProvider.getComplexHTMLTypes_KeyvaluepairsKeyValuePairParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_76);
            lv_keyvaluepairs_2_0=ruleKeyValuePair();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:2992:3: (otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) ) )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==18) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // PsiInternalEJSL.g:2993:4: otherlv_3= ',' ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) )
            	    {

            	    				markLeaf(elementTypeProvider.getComplexHTMLTypes_CommaKeyword_3_0ElementType());
            	    			
            	    otherlv_3=(Token)match(input,18,FOLLOW_75); 

            	    				doneLeaf(otherlv_3);
            	    			
            	    // PsiInternalEJSL.g:3000:4: ( (lv_keyvaluepairs_4_0= ruleKeyValuePair ) )
            	    // PsiInternalEJSL.g:3001:5: (lv_keyvaluepairs_4_0= ruleKeyValuePair )
            	    {
            	    // PsiInternalEJSL.g:3001:5: (lv_keyvaluepairs_4_0= ruleKeyValuePair )
            	    // PsiInternalEJSL.g:3002:6: lv_keyvaluepairs_4_0= ruleKeyValuePair
            	    {

            	    						markComposite(elementTypeProvider.getComplexHTMLTypes_KeyvaluepairsKeyValuePairParserRuleCall_3_1_0ElementType());
            	    					
            	    pushFollow(FOLLOW_76);
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
            	    break loop80;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getComplexHTMLTypes_RightParenthesisKeyword_4ElementType());
            		
            otherlv_5=(Token)match(input,66,FOLLOW_2); 

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
    // PsiInternalEJSL.g:3027:1: entryRuleLink returns [Boolean current=false] : iv_ruleLink= ruleLink EOF ;
    public final Boolean entryRuleLink() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLink = null;


        try {
            // PsiInternalEJSL.g:3027:46: (iv_ruleLink= ruleLink EOF )
            // PsiInternalEJSL.g:3028:2: iv_ruleLink= ruleLink EOF
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
    // PsiInternalEJSL.g:3034:1: ruleLink returns [Boolean current=false] : (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink ) ;
    public final Boolean ruleLink() throws RecognitionException {
        Boolean current = false;

        Boolean this_ExternalLink_0 = null;

        Boolean this_InternalLink_1 = null;


        try {
            // PsiInternalEJSL.g:3035:1: ( (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink ) )
            // PsiInternalEJSL.g:3036:2: (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink )
            {
            // PsiInternalEJSL.g:3036:2: (this_ExternalLink_0= ruleExternalLink | this_InternalLink_1= ruleInternalLink )
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
                    // PsiInternalEJSL.g:3037:3: this_ExternalLink_0= ruleExternalLink
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
                    // PsiInternalEJSL.g:3046:3: this_InternalLink_1= ruleInternalLink
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
    // PsiInternalEJSL.g:3058:1: entryRuleExternalLink returns [Boolean current=false] : iv_ruleExternalLink= ruleExternalLink EOF ;
    public final Boolean entryRuleExternalLink() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleExternalLink = null;


        try {
            // PsiInternalEJSL.g:3058:54: (iv_ruleExternalLink= ruleExternalLink EOF )
            // PsiInternalEJSL.g:3059:2: iv_ruleExternalLink= ruleExternalLink EOF
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
    // PsiInternalEJSL.g:3065:1: ruleExternalLink returns [Boolean current=false] : ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' ) ;
    public final Boolean ruleExternalLink() throws RecognitionException {
        Boolean current = false;

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

        try {
            // PsiInternalEJSL.g:3066:1: ( ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' ) )
            // PsiInternalEJSL.g:3067:2: ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' )
            {
            // PsiInternalEJSL.g:3067:2: ( () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}' )
            // PsiInternalEJSL.g:3068:3: () otherlv_1= 'ExternalLink' otherlv_2= '{' otherlv_3= 'target' otherlv_4= '=' ( (lv_target_5_0= RULE_STRING ) ) otherlv_6= 'linked attribute' otherlv_7= '=' ( ( ruleQualifiedName ) ) (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )? otherlv_12= '}'
            {
            // PsiInternalEJSL.g:3068:3: ()
            // PsiInternalEJSL.g:3069:4: 
            {

            				precedeComposite(elementTypeProvider.getExternalLink_ExternalLinkAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getExternalLink_ExternalLinkKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,67,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getExternalLink_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_77); 

            			doneLeaf(otherlv_2);
            		

            			markLeaf(elementTypeProvider.getExternalLink_TargetKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,68,FOLLOW_23); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getExternalLink_EqualsSignKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,28,FOLLOW_3); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:3103:3: ( (lv_target_5_0= RULE_STRING ) )
            // PsiInternalEJSL.g:3104:4: (lv_target_5_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:3104:4: (lv_target_5_0= RULE_STRING )
            // PsiInternalEJSL.g:3105:5: lv_target_5_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getExternalLink_TargetSTRINGTerminalRuleCall_5_0ElementType());
            				
            lv_target_5_0=(Token)match(input,RULE_STRING,FOLLOW_78); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_target_5_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getExternalLink_LinkedAttributeKeyword_6ElementType());
            		
            otherlv_6=(Token)match(input,69,FOLLOW_23); 

            			doneLeaf(otherlv_6);
            		

            			markLeaf(elementTypeProvider.getExternalLink_EqualsSignKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,28,FOLLOW_40); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:3134:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:3135:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:3135:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:3136:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getExternalLink_LinkedAttributeAttributeCrossReference_8_0ElementType());
            				
            pushFollow(FOLLOW_79);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }

            // PsiInternalEJSL.g:3151:3: (otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==34) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // PsiInternalEJSL.g:3152:4: otherlv_9= 'label' otherlv_10= '=' ( (lv_label_11_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getExternalLink_LabelKeyword_9_0ElementType());
                    			
                    otherlv_9=(Token)match(input,34,FOLLOW_23); 

                    				doneLeaf(otherlv_9);
                    			

                    				markLeaf(elementTypeProvider.getExternalLink_EqualsSignKeyword_9_1ElementType());
                    			
                    otherlv_10=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:3166:4: ( (lv_label_11_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:3167:5: (lv_label_11_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:3167:5: (lv_label_11_0= RULE_STRING )
                    // PsiInternalEJSL.g:3168:6: lv_label_11_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getExternalLink_LabelSTRINGTerminalRuleCall_9_2_0ElementType());
                    					
                    lv_label_11_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

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


            			markLeaf(elementTypeProvider.getExternalLink_RightCurlyBracketKeyword_10ElementType());
            		
            otherlv_12=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:3195:1: entryRuleInternalLink returns [Boolean current=false] : iv_ruleInternalLink= ruleInternalLink EOF ;
    public final Boolean entryRuleInternalLink() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleInternalLink = null;


        try {
            // PsiInternalEJSL.g:3195:54: (iv_ruleInternalLink= ruleInternalLink EOF )
            // PsiInternalEJSL.g:3196:2: iv_ruleInternalLink= ruleInternalLink EOF
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
    // PsiInternalEJSL.g:3202:1: ruleInternalLink returns [Boolean current=false] : ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink ) ;
    public final Boolean ruleInternalLink() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Boolean lv_name_2_0 = null;

        Boolean this_ContextLink_11 = null;


        try {
            // PsiInternalEJSL.g:3203:1: ( ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink ) )
            // PsiInternalEJSL.g:3204:2: ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink )
            {
            // PsiInternalEJSL.g:3204:2: ( ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) ) | this_ContextLink_11= ruleContextLink )
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
                    // PsiInternalEJSL.g:3205:3: ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) )
                    {
                    // PsiInternalEJSL.g:3205:3: ( () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' ) )
                    // PsiInternalEJSL.g:3206:4: () (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' )
                    {
                    // PsiInternalEJSL.g:3206:4: ()
                    // PsiInternalEJSL.g:3207:5: 
                    {

                    					precedeComposite(elementTypeProvider.getInternalLink_InternalLinkAction_0_0ElementType());
                    					doneComposite();
                    					associateWithSemanticElement();
                    				

                    }

                    // PsiInternalEJSL.g:3213:4: (otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}' )
                    // PsiInternalEJSL.g:3214:5: otherlv_1= 'InternalLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= '}'
                    {

                    					markLeaf(elementTypeProvider.getInternalLink_InternalLinkKeyword_0_1_0ElementType());
                    				
                    otherlv_1=(Token)match(input,70,FOLLOW_40); 

                    					doneLeaf(otherlv_1);
                    				
                    // PsiInternalEJSL.g:3221:5: ( (lv_name_2_0= ruleMYID ) )
                    // PsiInternalEJSL.g:3222:6: (lv_name_2_0= ruleMYID )
                    {
                    // PsiInternalEJSL.g:3222:6: (lv_name_2_0= ruleMYID )
                    // PsiInternalEJSL.g:3223:7: lv_name_2_0= ruleMYID
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
                    				
                    otherlv_3=(Token)match(input,16,FOLLOW_77); 

                    					doneLeaf(otherlv_3);
                    				

                    					markLeaf(elementTypeProvider.getInternalLink_TargetKeyword_0_1_3ElementType());
                    				
                    otherlv_4=(Token)match(input,68,FOLLOW_23); 

                    					doneLeaf(otherlv_4);
                    				

                    					markLeaf(elementTypeProvider.getInternalLink_EqualsSignKeyword_0_1_4ElementType());
                    				
                    otherlv_5=(Token)match(input,28,FOLLOW_40); 

                    					doneLeaf(otherlv_5);
                    				
                    // PsiInternalEJSL.g:3257:5: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:3258:6: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:3258:6: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:3259:7: ruleQualifiedName
                    {

                    							if (!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    							markComposite(elementTypeProvider.getInternalLink_TargetPageCrossReference_0_1_5_0ElementType());
                    						
                    pushFollow(FOLLOW_78);
                    ruleQualifiedName();

                    state._fsp--;


                    							doneComposite();
                    						

                    }


                    }


                    					markLeaf(elementTypeProvider.getInternalLink_LinkedAttributeKeyword_0_1_6ElementType());
                    				
                    otherlv_7=(Token)match(input,69,FOLLOW_23); 

                    					doneLeaf(otherlv_7);
                    				

                    					markLeaf(elementTypeProvider.getInternalLink_EqualsSignKeyword_0_1_7ElementType());
                    				
                    otherlv_8=(Token)match(input,28,FOLLOW_40); 

                    					doneLeaf(otherlv_8);
                    				
                    // PsiInternalEJSL.g:3288:5: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:3289:6: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:3289:6: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:3290:7: ruleQualifiedName
                    {

                    							if (!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    							markComposite(elementTypeProvider.getInternalLink_LinkedAttributeAttributeCrossReference_0_1_8_0ElementType());
                    						
                    pushFollow(FOLLOW_20);
                    ruleQualifiedName();

                    state._fsp--;


                    							doneComposite();
                    						

                    }


                    }


                    					markLeaf(elementTypeProvider.getInternalLink_RightCurlyBracketKeyword_0_1_9ElementType());
                    				
                    otherlv_10=(Token)match(input,19,FOLLOW_2); 

                    					doneLeaf(otherlv_10);
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:3315:3: this_ContextLink_11= ruleContextLink
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
    // PsiInternalEJSL.g:3327:1: entryRuleContextLink returns [Boolean current=false] : iv_ruleContextLink= ruleContextLink EOF ;
    public final Boolean entryRuleContextLink() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleContextLink = null;


        try {
            // PsiInternalEJSL.g:3327:53: (iv_ruleContextLink= ruleContextLink EOF )
            // PsiInternalEJSL.g:3328:2: iv_ruleContextLink= ruleContextLink EOF
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
    // PsiInternalEJSL.g:3334:1: ruleContextLink returns [Boolean current=false] : ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' ) ;
    public final Boolean ruleContextLink() throws RecognitionException {
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
        Boolean lv_name_2_0 = null;

        Boolean lv_linkparameters_12_0 = null;


        try {
            // PsiInternalEJSL.g:3335:1: ( ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' ) )
            // PsiInternalEJSL.g:3336:2: ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' )
            {
            // PsiInternalEJSL.g:3336:2: ( () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}' )
            // PsiInternalEJSL.g:3337:3: () otherlv_1= 'InternalcontextLink' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'target' otherlv_5= '=' ( ( ruleQualifiedName ) ) otherlv_7= 'linked attribute' otherlv_8= '=' ( ( ruleQualifiedName ) ) otherlv_10= 'linkparameters' otherlv_11= '{' ( (lv_linkparameters_12_0= ruleLinkParameter ) )* otherlv_13= '}' otherlv_14= '}'
            {
            // PsiInternalEJSL.g:3337:3: ()
            // PsiInternalEJSL.g:3338:4: 
            {

            				precedeComposite(elementTypeProvider.getContextLink_ContextLinkAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getContextLink_InternalcontextLinkKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,71,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:3351:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:3352:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:3352:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:3353:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_77); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getContextLink_TargetKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,68,FOLLOW_23); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getContextLink_EqualsSignKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,28,FOLLOW_40); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:3387:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:3388:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:3388:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:3389:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getContextLink_TargetPageCrossReference_6_0ElementType());
            				
            pushFollow(FOLLOW_78);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }


            			markLeaf(elementTypeProvider.getContextLink_LinkedAttributeKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,69,FOLLOW_23); 

            			doneLeaf(otherlv_7);
            		

            			markLeaf(elementTypeProvider.getContextLink_EqualsSignKeyword_8ElementType());
            		
            otherlv_8=(Token)match(input,28,FOLLOW_40); 

            			doneLeaf(otherlv_8);
            		
            // PsiInternalEJSL.g:3418:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:3419:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:3419:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:3420:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getContextLink_LinkedAttributeAttributeCrossReference_9_0ElementType());
            				
            pushFollow(FOLLOW_80);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }


            			markLeaf(elementTypeProvider.getContextLink_LinkparametersKeyword_10ElementType());
            		
            otherlv_10=(Token)match(input,72,FOLLOW_4); 

            			doneLeaf(otherlv_10);
            		

            			markLeaf(elementTypeProvider.getContextLink_LeftCurlyBracketKeyword_11ElementType());
            		
            otherlv_11=(Token)match(input,16,FOLLOW_9); 

            			doneLeaf(otherlv_11);
            		
            // PsiInternalEJSL.g:3449:3: ( (lv_linkparameters_12_0= ruleLinkParameter ) )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==31) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // PsiInternalEJSL.g:3450:4: (lv_linkparameters_12_0= ruleLinkParameter )
            	    {
            	    // PsiInternalEJSL.g:3450:4: (lv_linkparameters_12_0= ruleLinkParameter )
            	    // PsiInternalEJSL.g:3451:5: lv_linkparameters_12_0= ruleLinkParameter
            	    {

            	    					markComposite(elementTypeProvider.getContextLink_LinkparametersLinkParameterParserRuleCall_12_0ElementType());
            	    				
            	    pushFollow(FOLLOW_9);
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
            	    break loop84;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getContextLink_RightCurlyBracketKeyword_13ElementType());
            		
            otherlv_13=(Token)match(input,19,FOLLOW_20); 

            			doneLeaf(otherlv_13);
            		

            			markLeaf(elementTypeProvider.getContextLink_RightCurlyBracketKeyword_14ElementType());
            		
            otherlv_14=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:3482:1: entryRuleLinkParameter returns [Boolean current=false] : iv_ruleLinkParameter= ruleLinkParameter EOF ;
    public final Boolean entryRuleLinkParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLinkParameter = null;


        try {
            // PsiInternalEJSL.g:3482:55: (iv_ruleLinkParameter= ruleLinkParameter EOF )
            // PsiInternalEJSL.g:3483:2: iv_ruleLinkParameter= ruleLinkParameter EOF
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
    // PsiInternalEJSL.g:3489:1: ruleLinkParameter returns [Boolean current=false] : ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) ) ) ;
    public final Boolean ruleLinkParameter() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_value_6_0=null;
        Boolean lv_name_2_0 = null;


        try {
            // PsiInternalEJSL.g:3490:1: ( ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) ) ) )
            // PsiInternalEJSL.g:3491:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) ) )
            {
            // PsiInternalEJSL.g:3491:2: ( () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) ) )
            // PsiInternalEJSL.g:3492:3: () otherlv_1= 'Parameter' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '=' ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) )
            {
            // PsiInternalEJSL.g:3492:3: ()
            // PsiInternalEJSL.g:3493:4: 
            {

            				precedeComposite(elementTypeProvider.getLinkParameter_LinkParameterAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getLinkParameter_ParameterKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,31,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:3506:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:3507:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:3507:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:3508:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getLinkParameter_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_23);
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
            		
            otherlv_3=(Token)match(input,28,FOLLOW_81); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:3528:3: ( (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) ) | ( (lv_value_6_0= RULE_STRING ) ) )
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
                    // PsiInternalEJSL.g:3529:4: (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) )
                    {
                    // PsiInternalEJSL.g:3529:4: (otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) ) )
                    // PsiInternalEJSL.g:3530:5: otherlv_4= '*Attribute ' ( (otherlv_5= RULE_STRING ) )
                    {

                    					markLeaf(elementTypeProvider.getLinkParameter_AttributeKeyword_4_0_0ElementType());
                    				
                    otherlv_4=(Token)match(input,73,FOLLOW_3); 

                    					doneLeaf(otherlv_4);
                    				
                    // PsiInternalEJSL.g:3537:5: ( (otherlv_5= RULE_STRING ) )
                    // PsiInternalEJSL.g:3538:6: (otherlv_5= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:3538:6: (otherlv_5= RULE_STRING )
                    // PsiInternalEJSL.g:3539:7: otherlv_5= RULE_STRING
                    {

                    							if (!current) {
                    								associateWithSemanticElement();
                    								current = true;
                    							}
                    						

                    							markLeaf(elementTypeProvider.getLinkParameter_AttvalueAttributeCrossReference_4_0_1_0ElementType());
                    						
                    otherlv_5=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    							doneLeaf(otherlv_5);
                    						

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:3556:4: ( (lv_value_6_0= RULE_STRING ) )
                    {
                    // PsiInternalEJSL.g:3556:4: ( (lv_value_6_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:3557:5: (lv_value_6_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:3557:5: (lv_value_6_0= RULE_STRING )
                    // PsiInternalEJSL.g:3558:6: lv_value_6_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getLinkParameter_ValueSTRINGTerminalRuleCall_4_1_0ElementType());
                    					
                    lv_value_6_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_value_6_0);
                    					

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
    // PsiInternalEJSL.g:3578:1: entryRuleExtension returns [Boolean current=false] : iv_ruleExtension= ruleExtension EOF ;
    public final Boolean entryRuleExtension() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleExtension = null;


        try {
            // PsiInternalEJSL.g:3578:51: (iv_ruleExtension= ruleExtension EOF )
            // PsiInternalEJSL.g:3579:2: iv_ruleExtension= ruleExtension EOF
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
    // PsiInternalEJSL.g:3585:1: ruleExtension returns [Boolean current=false] : (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate ) ;
    public final Boolean ruleExtension() throws RecognitionException {
        Boolean current = false;

        Boolean this_ExtensionPackage_0 = null;

        Boolean this_Component_1 = null;

        Boolean this_Module_2 = null;

        Boolean this_Plugin_3 = null;

        Boolean this_Library_4 = null;

        Boolean this_Template_5 = null;


        try {
            // PsiInternalEJSL.g:3586:1: ( (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate ) )
            // PsiInternalEJSL.g:3587:2: (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate )
            {
            // PsiInternalEJSL.g:3587:2: (this_ExtensionPackage_0= ruleExtensionPackage | this_Component_1= ruleComponent | this_Module_2= ruleModule | this_Plugin_3= rulePlugin | this_Library_4= ruleLibrary | this_Template_5= ruleTemplate )
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
                    // PsiInternalEJSL.g:3588:3: this_ExtensionPackage_0= ruleExtensionPackage
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
                    // PsiInternalEJSL.g:3597:3: this_Component_1= ruleComponent
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
                    // PsiInternalEJSL.g:3606:3: this_Module_2= ruleModule
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
                    // PsiInternalEJSL.g:3615:3: this_Plugin_3= rulePlugin
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
                    // PsiInternalEJSL.g:3624:3: this_Library_4= ruleLibrary
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
                    // PsiInternalEJSL.g:3633:3: this_Template_5= ruleTemplate
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
    // PsiInternalEJSL.g:3645:1: entryRuleExtensionPackage returns [Boolean current=false] : iv_ruleExtensionPackage= ruleExtensionPackage EOF ;
    public final Boolean entryRuleExtensionPackage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleExtensionPackage = null;


        try {
            // PsiInternalEJSL.g:3645:58: (iv_ruleExtensionPackage= ruleExtensionPackage EOF )
            // PsiInternalEJSL.g:3646:2: iv_ruleExtensionPackage= ruleExtensionPackage EOF
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
    // PsiInternalEJSL.g:3652:1: ruleExtensionPackage returns [Boolean current=false] : ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' ) ;
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
            // PsiInternalEJSL.g:3653:1: ( ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' ) )
            // PsiInternalEJSL.g:3654:2: ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' )
            {
            // PsiInternalEJSL.g:3654:2: ( () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}' )
            // PsiInternalEJSL.g:3655:3: () otherlv_1= 'Extension package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? otherlv_12= 'extensions' otherlv_13= '{' ( (lv_extensions_14_0= ruleExtension ) )+ otherlv_15= '}' otherlv_16= '}'
            {
            // PsiInternalEJSL.g:3655:3: ()
            // PsiInternalEJSL.g:3656:4: 
            {

            				precedeComposite(elementTypeProvider.getExtensionPackage_ExtensionPackageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getExtensionPackage_ExtensionPackageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,74,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:3669:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:3670:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:3670:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:3671:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getExtensionPackage_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getExtensionPackage_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:3705:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:3706:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:3706:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:3707:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getExtensionPackage_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_20);
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
            		
            otherlv_7=(Token)match(input,19,FOLLOW_84); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:3727:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==76) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // PsiInternalEJSL.g:3728:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getExtensionPackage_LanguagesKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,76,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getExtensionPackage_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_85); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:3742:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( (LA87_0==112) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3743:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:3743:5: (lv_languages_10_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:3744:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getExtensionPackage_LanguagesLanguageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop87;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getExtensionPackage_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_86); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getExtensionPackage_ExtensionsKeyword_9ElementType());
            		
            otherlv_12=(Token)match(input,25,FOLLOW_4); 

            			doneLeaf(otherlv_12);
            		

            			markLeaf(elementTypeProvider.getExtensionPackage_LeftCurlyBracketKeyword_10ElementType());
            		
            otherlv_13=(Token)match(input,16,FOLLOW_87); 

            			doneLeaf(otherlv_13);
            		
            // PsiInternalEJSL.g:3779:3: ( (lv_extensions_14_0= ruleExtension ) )+
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
            	    // PsiInternalEJSL.g:3780:4: (lv_extensions_14_0= ruleExtension )
            	    {
            	    // PsiInternalEJSL.g:3780:4: (lv_extensions_14_0= ruleExtension )
            	    // PsiInternalEJSL.g:3781:5: lv_extensions_14_0= ruleExtension
            	    {

            	    					markComposite(elementTypeProvider.getExtensionPackage_ExtensionsExtensionParserRuleCall_11_0ElementType());
            	    				
            	    pushFollow(FOLLOW_19);
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
            	    if ( cnt89 >= 1 ) break loop89;
                        EarlyExitException eee =
                            new EarlyExitException(89, input);
                        throw eee;
                }
                cnt89++;
            } while (true);


            			markLeaf(elementTypeProvider.getExtensionPackage_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_15=(Token)match(input,19,FOLLOW_20); 

            			doneLeaf(otherlv_15);
            		

            			markLeaf(elementTypeProvider.getExtensionPackage_RightCurlyBracketKeyword_13ElementType());
            		
            otherlv_16=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:3812:1: entryRuleComponent returns [Boolean current=false] : iv_ruleComponent= ruleComponent EOF ;
    public final Boolean entryRuleComponent() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleComponent = null;


        try {
            // PsiInternalEJSL.g:3812:51: (iv_ruleComponent= ruleComponent EOF )
            // PsiInternalEJSL.g:3813:2: iv_ruleComponent= ruleComponent EOF
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
    // PsiInternalEJSL.g:3819:1: ruleComponent returns [Boolean current=false] : ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' ) ;
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
            // PsiInternalEJSL.g:3820:1: ( ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' ) )
            // PsiInternalEJSL.g:3821:2: ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' )
            {
            // PsiInternalEJSL.g:3821:2: ( () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}' )
            // PsiInternalEJSL.g:3822:3: () otherlv_1= 'Component' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )? (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )? otherlv_16= 'sections' otherlv_17= '{' ( (lv_sections_18_0= ruleSection ) )+ otherlv_19= '}' otherlv_20= '}'
            {
            // PsiInternalEJSL.g:3822:3: ()
            // PsiInternalEJSL.g:3823:4: 
            {

            				precedeComposite(elementTypeProvider.getComponent_ComponentAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getComponent_ComponentKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,77,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:3836:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:3837:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:3837:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:3838:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getComponent_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:3872:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:3873:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:3873:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:3874:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getComponent_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_20);
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
            		
            otherlv_7=(Token)match(input,19,FOLLOW_88); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:3894:3: (otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==78) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // PsiInternalEJSL.g:3895:4: otherlv_8= 'Global parameter' otherlv_9= '{' ( (lv_globalParamter_10_0= ruleParameterGroup ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getComponent_GlobalParameterKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,78,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_11); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:3909:4: ( (lv_globalParamter_10_0= ruleParameterGroup ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==37) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3910:5: (lv_globalParamter_10_0= ruleParameterGroup )
                    	    {
                    	    // PsiInternalEJSL.g:3910:5: (lv_globalParamter_10_0= ruleParameterGroup )
                    	    // PsiInternalEJSL.g:3911:6: lv_globalParamter_10_0= ruleParameterGroup
                    	    {

                    	    						markComposite(elementTypeProvider.getComponent_GlobalParamterParameterGroupParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_11);
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
                    	    break loop90;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_89); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:3932:3: (otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}' )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==76) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // PsiInternalEJSL.g:3933:4: otherlv_12= 'languages' otherlv_13= '{' ( (lv_languages_14_0= ruleLanguage ) )* otherlv_15= '}'
                    {

                    				markLeaf(elementTypeProvider.getComponent_LanguagesKeyword_9_0ElementType());
                    			
                    otherlv_12=(Token)match(input,76,FOLLOW_4); 

                    				doneLeaf(otherlv_12);
                    			

                    				markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_9_1ElementType());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_85); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:3947:4: ( (lv_languages_14_0= ruleLanguage ) )*
                    loop92:
                    do {
                        int alt92=2;
                        int LA92_0 = input.LA(1);

                        if ( (LA92_0==112) ) {
                            alt92=1;
                        }


                        switch (alt92) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:3948:5: (lv_languages_14_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:3948:5: (lv_languages_14_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:3949:6: lv_languages_14_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getComponent_LanguagesLanguageParserRuleCall_9_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop92;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_9_3ElementType());
                    			
                    otherlv_15=(Token)match(input,19,FOLLOW_90); 

                    				doneLeaf(otherlv_15);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getComponent_SectionsKeyword_10ElementType());
            		
            otherlv_16=(Token)match(input,79,FOLLOW_4); 

            			doneLeaf(otherlv_16);
            		

            			markLeaf(elementTypeProvider.getComponent_LeftCurlyBracketKeyword_11ElementType());
            		
            otherlv_17=(Token)match(input,16,FOLLOW_91); 

            			doneLeaf(otherlv_17);
            		
            // PsiInternalEJSL.g:3984:3: ( (lv_sections_18_0= ruleSection ) )+
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
            	    // PsiInternalEJSL.g:3985:4: (lv_sections_18_0= ruleSection )
            	    {
            	    // PsiInternalEJSL.g:3985:4: (lv_sections_18_0= ruleSection )
            	    // PsiInternalEJSL.g:3986:5: lv_sections_18_0= ruleSection
            	    {

            	    					markComposite(elementTypeProvider.getComponent_SectionsSectionParserRuleCall_12_0ElementType());
            	    				
            	    pushFollow(FOLLOW_92);
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
            	    if ( cnt94 >= 1 ) break loop94;
                        EarlyExitException eee =
                            new EarlyExitException(94, input);
                        throw eee;
                }
                cnt94++;
            } while (true);


            			markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_13ElementType());
            		
            otherlv_19=(Token)match(input,19,FOLLOW_20); 

            			doneLeaf(otherlv_19);
            		

            			markLeaf(elementTypeProvider.getComponent_RightCurlyBracketKeyword_14ElementType());
            		
            otherlv_20=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:4017:1: entryRuleSection returns [Boolean current=false] : iv_ruleSection= ruleSection EOF ;
    public final Boolean entryRuleSection() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleSection = null;


        try {
            // PsiInternalEJSL.g:4017:49: (iv_ruleSection= ruleSection EOF )
            // PsiInternalEJSL.g:4018:2: iv_ruleSection= ruleSection EOF
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
    // PsiInternalEJSL.g:4024:1: ruleSection returns [Boolean current=false] : (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend ) ;
    public final Boolean ruleSection() throws RecognitionException {
        Boolean current = false;

        Boolean this_Backend_0 = null;

        Boolean this_Frontend_1 = null;


        try {
            // PsiInternalEJSL.g:4025:1: ( (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend ) )
            // PsiInternalEJSL.g:4026:2: (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend )
            {
            // PsiInternalEJSL.g:4026:2: (this_Backend_0= ruleBackend | this_Frontend_1= ruleFrontend )
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
                    // PsiInternalEJSL.g:4027:3: this_Backend_0= ruleBackend
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
                    // PsiInternalEJSL.g:4036:3: this_Frontend_1= ruleFrontend
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
    // PsiInternalEJSL.g:4048:1: entryRuleBackend returns [Boolean current=false] : iv_ruleBackend= ruleBackend EOF ;
    public final Boolean entryRuleBackend() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleBackend = null;


        try {
            // PsiInternalEJSL.g:4048:49: (iv_ruleBackend= ruleBackend EOF )
            // PsiInternalEJSL.g:4049:2: iv_ruleBackend= ruleBackend EOF
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
    // PsiInternalEJSL.g:4055:1: ruleBackend returns [Boolean current=false] : ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) ;
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
            // PsiInternalEJSL.g:4056:1: ( ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) )
            // PsiInternalEJSL.g:4057:2: ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            {
            // PsiInternalEJSL.g:4057:2: ( () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            // PsiInternalEJSL.g:4058:3: () otherlv_1= 'Backend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}'
            {
            // PsiInternalEJSL.g:4058:3: ()
            // PsiInternalEJSL.g:4059:4: 
            {

            				precedeComposite(elementTypeProvider.getBackend_BackendSectionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getBackend_BackendSectionKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,80,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getBackend_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_93); 

            			doneLeaf(otherlv_2);
            		

            			markLeaf(elementTypeProvider.getBackend_PagesKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,81,FOLLOW_4); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getBackend_LeftCurlyBracketKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_94); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:4093:3: ( (lv_pageRef_5_0= rulePageReference ) )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( (LA96_0==82) ) {
                    alt96=1;
                }


                switch (alt96) {
            	case 1 :
            	    // PsiInternalEJSL.g:4094:4: (lv_pageRef_5_0= rulePageReference )
            	    {
            	    // PsiInternalEJSL.g:4094:4: (lv_pageRef_5_0= rulePageReference )
            	    // PsiInternalEJSL.g:4095:5: lv_pageRef_5_0= rulePageReference
            	    {

            	    					markComposite(elementTypeProvider.getBackend_PageRefPageReferenceParserRuleCall_5_0ElementType());
            	    				
            	    pushFollow(FOLLOW_94);
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
            	    break loop96;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getBackend_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_6=(Token)match(input,19,FOLLOW_20); 

            			doneLeaf(otherlv_6);
            		

            			markLeaf(elementTypeProvider.getBackend_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:4126:1: entryRulePageReference returns [Boolean current=false] : iv_rulePageReference= rulePageReference EOF ;
    public final Boolean entryRulePageReference() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePageReference = null;


        try {
            // PsiInternalEJSL.g:4126:55: (iv_rulePageReference= rulePageReference EOF )
            // PsiInternalEJSL.g:4127:2: iv_rulePageReference= rulePageReference EOF
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
    // PsiInternalEJSL.g:4133:1: rulePageReference returns [Boolean current=false] : (otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )? ) ;
    public final Boolean rulePageReference() throws RecognitionException {
        Boolean current = false;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Boolean lv_sect_6_0 = null;


        try {
            // PsiInternalEJSL.g:4134:1: ( (otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )? ) )
            // PsiInternalEJSL.g:4135:2: (otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )? )
            {
            // PsiInternalEJSL.g:4135:2: (otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )? )
            // PsiInternalEJSL.g:4136:3: otherlv_0= '*Page' otherlv_1= ':' ( ( ruleQualifiedName ) ) (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )?
            {

            			markLeaf(elementTypeProvider.getPageReference_PageKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,82,FOLLOW_72); 

            			doneLeaf(otherlv_0);
            		

            			markLeaf(elementTypeProvider.getPageReference_ColonKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,64,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4150:3: ( ( ruleQualifiedName ) )
            // PsiInternalEJSL.g:4151:4: ( ruleQualifiedName )
            {
            // PsiInternalEJSL.g:4151:4: ( ruleQualifiedName )
            // PsiInternalEJSL.g:4152:5: ruleQualifiedName
            {

            					if (!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					markComposite(elementTypeProvider.getPageReference_PagePageCrossReference_2_0ElementType());
            				
            pushFollow(FOLLOW_95);
            ruleQualifiedName();

            state._fsp--;


            					doneComposite();
            				

            }


            }

            // PsiInternalEJSL.g:4167:3: (otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==83) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // PsiInternalEJSL.g:4168:4: otherlv_3= 'from' otherlv_4= ':' ( ( ruleQualifiedName ) ) ( (lv_sect_6_0= ruleSectionReference ) )
                    {

                    				markLeaf(elementTypeProvider.getPageReference_FromKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,83,FOLLOW_72); 

                    				doneLeaf(otherlv_3);
                    			

                    				markLeaf(elementTypeProvider.getPageReference_ColonKeyword_3_1ElementType());
                    			
                    otherlv_4=(Token)match(input,64,FOLLOW_40); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:4182:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:4183:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:4183:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:4184:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getPageReference_PagescrComponentCrossReference_3_2_0ElementType());
                    					
                    pushFollow(FOLLOW_96);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:4199:4: ( (lv_sect_6_0= ruleSectionReference ) )
                    // PsiInternalEJSL.g:4200:5: (lv_sect_6_0= ruleSectionReference )
                    {
                    // PsiInternalEJSL.g:4200:5: (lv_sect_6_0= ruleSectionReference )
                    // PsiInternalEJSL.g:4201:6: lv_sect_6_0= ruleSectionReference
                    {

                    						markComposite(elementTypeProvider.getPageReference_SectSectionReferenceEnumRuleCall_3_3_0ElementType());
                    					
                    pushFollow(FOLLOW_2);
                    lv_sect_6_0=ruleSectionReference();

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
    // PsiInternalEJSL.g:4219:1: entryRuleFrontend returns [Boolean current=false] : iv_ruleFrontend= ruleFrontend EOF ;
    public final Boolean entryRuleFrontend() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleFrontend = null;


        try {
            // PsiInternalEJSL.g:4219:50: (iv_ruleFrontend= ruleFrontend EOF )
            // PsiInternalEJSL.g:4220:2: iv_ruleFrontend= ruleFrontend EOF
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
    // PsiInternalEJSL.g:4226:1: ruleFrontend returns [Boolean current=false] : ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) ;
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
            // PsiInternalEJSL.g:4227:1: ( ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' ) )
            // PsiInternalEJSL.g:4228:2: ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            {
            // PsiInternalEJSL.g:4228:2: ( () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}' )
            // PsiInternalEJSL.g:4229:3: () otherlv_1= 'Frontend section' otherlv_2= '{' otherlv_3= '*Pages' otherlv_4= '{' ( (lv_pageRef_5_0= rulePageReference ) )* otherlv_6= '}' otherlv_7= '}'
            {
            // PsiInternalEJSL.g:4229:3: ()
            // PsiInternalEJSL.g:4230:4: 
            {

            				precedeComposite(elementTypeProvider.getFrontend_FrontendSectionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getFrontend_FrontendSectionKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,84,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getFrontend_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_93); 

            			doneLeaf(otherlv_2);
            		

            			markLeaf(elementTypeProvider.getFrontend_PagesKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,81,FOLLOW_4); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getFrontend_LeftCurlyBracketKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,16,FOLLOW_94); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:4264:3: ( (lv_pageRef_5_0= rulePageReference ) )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==82) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // PsiInternalEJSL.g:4265:4: (lv_pageRef_5_0= rulePageReference )
            	    {
            	    // PsiInternalEJSL.g:4265:4: (lv_pageRef_5_0= rulePageReference )
            	    // PsiInternalEJSL.g:4266:5: lv_pageRef_5_0= rulePageReference
            	    {

            	    					markComposite(elementTypeProvider.getFrontend_PageRefPageReferenceParserRuleCall_5_0ElementType());
            	    				
            	    pushFollow(FOLLOW_94);
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
            	    break loop98;
                }
            } while (true);


            			markLeaf(elementTypeProvider.getFrontend_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_6=(Token)match(input,19,FOLLOW_20); 

            			doneLeaf(otherlv_6);
            		

            			markLeaf(elementTypeProvider.getFrontend_RightCurlyBracketKeyword_7ElementType());
            		
            otherlv_7=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:4297:1: entryRuleModule returns [Boolean current=false] : iv_ruleModule= ruleModule EOF ;
    public final Boolean entryRuleModule() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleModule = null;


        try {
            // PsiInternalEJSL.g:4297:48: (iv_ruleModule= ruleModule EOF )
            // PsiInternalEJSL.g:4298:2: iv_ruleModule= ruleModule EOF
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
    // PsiInternalEJSL.g:4304:1: ruleModule returns [Boolean current=false] : ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' ) ;
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
            // PsiInternalEJSL.g:4305:1: ( ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' ) )
            // PsiInternalEJSL.g:4306:2: ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' )
            {
            // PsiInternalEJSL.g:4306:2: ( () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}' )
            // PsiInternalEJSL.g:4307:3: () otherlv_1= 'Module' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? ( (lv_pageRef_12_0= rulePageReference ) )? otherlv_13= '}'
            {
            // PsiInternalEJSL.g:4307:3: ()
            // PsiInternalEJSL.g:4308:4: 
            {

            				precedeComposite(elementTypeProvider.getModule_ModuleAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getModule_ModuleKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,85,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4321:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:4322:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:4322:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:4323:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getModule_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getModule_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:4357:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:4358:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:4358:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:4359:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getModule_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_20);
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
            		
            otherlv_7=(Token)match(input,19,FOLLOW_97); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:4379:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==76) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // PsiInternalEJSL.g:4380:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getModule_LanguagesKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,76,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getModule_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_85); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:4394:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop99:
                    do {
                        int alt99=2;
                        int LA99_0 = input.LA(1);

                        if ( (LA99_0==112) ) {
                            alt99=1;
                        }


                        switch (alt99) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4395:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:4395:5: (lv_languages_10_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:4396:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getModule_LanguagesLanguageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop99;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getModule_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_94); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:4417:3: ( (lv_pageRef_12_0= rulePageReference ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==82) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // PsiInternalEJSL.g:4418:4: (lv_pageRef_12_0= rulePageReference )
                    {
                    // PsiInternalEJSL.g:4418:4: (lv_pageRef_12_0= rulePageReference )
                    // PsiInternalEJSL.g:4419:5: lv_pageRef_12_0= rulePageReference
                    {

                    					markComposite(elementTypeProvider.getModule_PageRefPageReferenceParserRuleCall_9_0ElementType());
                    				
                    pushFollow(FOLLOW_20);
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
            		
            otherlv_13=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:4443:1: entryRulePlugin returns [Boolean current=false] : iv_rulePlugin= rulePlugin EOF ;
    public final Boolean entryRulePlugin() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePlugin = null;


        try {
            // PsiInternalEJSL.g:4443:48: (iv_rulePlugin= rulePlugin EOF )
            // PsiInternalEJSL.g:4444:2: iv_rulePlugin= rulePlugin EOF
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
    // PsiInternalEJSL.g:4450:1: rulePlugin returns [Boolean current=false] : ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}' ) ;
    public final Boolean rulePlugin() throws RecognitionException {
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
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Boolean lv_name_2_0 = null;

        Boolean lv_manifest_6_0 = null;

        Boolean lv_type_10_0 = null;

        Boolean lv_languages_13_0 = null;

        Boolean lv_localparameters_21_0 = null;


        try {
            // PsiInternalEJSL.g:4451:1: ( ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}' ) )
            // PsiInternalEJSL.g:4452:2: ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}' )
            {
            // PsiInternalEJSL.g:4452:2: ( () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}' )
            // PsiInternalEJSL.g:4453:3: () otherlv_1= 'Plugin' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' otherlv_8= 'Plugintype' otherlv_9= '=' ( (lv_type_10_0= rulePluginKinds ) ) (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )? (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )? (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )? otherlv_23= '}'
            {
            // PsiInternalEJSL.g:4453:3: ()
            // PsiInternalEJSL.g:4454:4: 
            {

            				precedeComposite(elementTypeProvider.getPlugin_PluginAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getPlugin_PluginKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,86,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4467:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:4468:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:4468:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:4469:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getPlugin_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getPlugin_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:4503:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:4504:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:4504:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:4505:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getPlugin_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_20);
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
            		
            otherlv_7=(Token)match(input,19,FOLLOW_98); 

            			doneLeaf(otherlv_7);
            		

            			markLeaf(elementTypeProvider.getPlugin_PlugintypeKeyword_8ElementType());
            		
            otherlv_8=(Token)match(input,87,FOLLOW_23); 

            			doneLeaf(otherlv_8);
            		

            			markLeaf(elementTypeProvider.getPlugin_EqualsSignKeyword_9ElementType());
            		
            otherlv_9=(Token)match(input,28,FOLLOW_99); 

            			doneLeaf(otherlv_9);
            		
            // PsiInternalEJSL.g:4539:3: ( (lv_type_10_0= rulePluginKinds ) )
            // PsiInternalEJSL.g:4540:4: (lv_type_10_0= rulePluginKinds )
            {
            // PsiInternalEJSL.g:4540:4: (lv_type_10_0= rulePluginKinds )
            // PsiInternalEJSL.g:4541:5: lv_type_10_0= rulePluginKinds
            {

            					markComposite(elementTypeProvider.getPlugin_TypePluginKindsEnumRuleCall_10_0ElementType());
            				
            pushFollow(FOLLOW_100);
            lv_type_10_0=rulePluginKinds();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:4554:3: (otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}' )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==76) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // PsiInternalEJSL.g:4555:4: otherlv_11= 'languages' otherlv_12= '{' ( (lv_languages_13_0= ruleLanguage ) )* otherlv_14= '}'
                    {

                    				markLeaf(elementTypeProvider.getPlugin_LanguagesKeyword_11_0ElementType());
                    			
                    otherlv_11=(Token)match(input,76,FOLLOW_4); 

                    				doneLeaf(otherlv_11);
                    			

                    				markLeaf(elementTypeProvider.getPlugin_LeftCurlyBracketKeyword_11_1ElementType());
                    			
                    otherlv_12=(Token)match(input,16,FOLLOW_85); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:4569:4: ( (lv_languages_13_0= ruleLanguage ) )*
                    loop102:
                    do {
                        int alt102=2;
                        int LA102_0 = input.LA(1);

                        if ( (LA102_0==112) ) {
                            alt102=1;
                        }


                        switch (alt102) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4570:5: (lv_languages_13_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:4570:5: (lv_languages_13_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:4571:6: lv_languages_13_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getPlugin_LanguagesLanguageParserRuleCall_11_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_85);
                    	    lv_languages_13_0=ruleLanguage();

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
                    	    break loop102;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPlugin_RightCurlyBracketKeyword_11_3ElementType());
                    			
                    otherlv_14=(Token)match(input,19,FOLLOW_101); 

                    				doneLeaf(otherlv_14);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:4592:3: (otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )* )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==59) ) {
                alt105=1;
            }
            switch (alt105) {
                case 1 :
                    // PsiInternalEJSL.g:4593:4: otherlv_15= '*Entities' ( (otherlv_16= RULE_STRING ) ) (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getPlugin_EntitiesKeyword_12_0ElementType());
                    			
                    otherlv_15=(Token)match(input,59,FOLLOW_3); 

                    				doneLeaf(otherlv_15);
                    			
                    // PsiInternalEJSL.g:4600:4: ( (otherlv_16= RULE_STRING ) )
                    // PsiInternalEJSL.g:4601:5: (otherlv_16= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:4601:5: (otherlv_16= RULE_STRING )
                    // PsiInternalEJSL.g:4602:6: otherlv_16= RULE_STRING
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getPlugin_EntitiesEntityCrossReference_12_1_0ElementType());
                    					
                    otherlv_16=(Token)match(input,RULE_STRING,FOLLOW_102); 

                    						doneLeaf(otherlv_16);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:4617:4: (otherlv_17= ',' ( (otherlv_18= RULE_STRING ) ) )*
                    loop104:
                    do {
                        int alt104=2;
                        int LA104_0 = input.LA(1);

                        if ( (LA104_0==18) ) {
                            alt104=1;
                        }


                        switch (alt104) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4618:5: otherlv_17= ',' ( (otherlv_18= RULE_STRING ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getPlugin_CommaKeyword_12_2_0ElementType());
                    	    				
                    	    otherlv_17=(Token)match(input,18,FOLLOW_3); 

                    	    					doneLeaf(otherlv_17);
                    	    				
                    	    // PsiInternalEJSL.g:4625:5: ( (otherlv_18= RULE_STRING ) )
                    	    // PsiInternalEJSL.g:4626:6: (otherlv_18= RULE_STRING )
                    	    {
                    	    // PsiInternalEJSL.g:4626:6: (otherlv_18= RULE_STRING )
                    	    // PsiInternalEJSL.g:4627:7: otherlv_18= RULE_STRING
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getPlugin_EntitiesEntityCrossReference_12_2_1_0ElementType());
                    	    						
                    	    otherlv_18=(Token)match(input,RULE_STRING,FOLLOW_102); 

                    	    							doneLeaf(otherlv_18);
                    	    						

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

            // PsiInternalEJSL.g:4644:3: (otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}' )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==88) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // PsiInternalEJSL.g:4645:4: otherlv_19= 'parameters' otherlv_20= '{' ( (lv_localparameters_21_0= ruleParameter ) )* otherlv_22= '}'
                    {

                    				markLeaf(elementTypeProvider.getPlugin_ParametersKeyword_13_0ElementType());
                    			
                    otherlv_19=(Token)match(input,88,FOLLOW_4); 

                    				doneLeaf(otherlv_19);
                    			

                    				markLeaf(elementTypeProvider.getPlugin_LeftCurlyBracketKeyword_13_1ElementType());
                    			
                    otherlv_20=(Token)match(input,16,FOLLOW_9); 

                    				doneLeaf(otherlv_20);
                    			
                    // PsiInternalEJSL.g:4659:4: ( (lv_localparameters_21_0= ruleParameter ) )*
                    loop106:
                    do {
                        int alt106=2;
                        int LA106_0 = input.LA(1);

                        if ( (LA106_0==31) ) {
                            alt106=1;
                        }


                        switch (alt106) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4660:5: (lv_localparameters_21_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:4660:5: (lv_localparameters_21_0= ruleParameter )
                    	    // PsiInternalEJSL.g:4661:6: lv_localparameters_21_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getPlugin_LocalparametersParameterParserRuleCall_13_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_9);
                    	    lv_localparameters_21_0=ruleParameter();

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


                    				markLeaf(elementTypeProvider.getPlugin_RightCurlyBracketKeyword_13_3ElementType());
                    			
                    otherlv_22=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_22);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPlugin_RightCurlyBracketKeyword_14ElementType());
            		
            otherlv_23=(Token)match(input,19,FOLLOW_2); 

            			doneLeaf(otherlv_23);
            		

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
    // PsiInternalEJSL.g:4693:1: entryRuleLibrary returns [Boolean current=false] : iv_ruleLibrary= ruleLibrary EOF ;
    public final Boolean entryRuleLibrary() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLibrary = null;


        try {
            // PsiInternalEJSL.g:4693:49: (iv_ruleLibrary= ruleLibrary EOF )
            // PsiInternalEJSL.g:4694:2: iv_ruleLibrary= ruleLibrary EOF
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
    // PsiInternalEJSL.g:4700:1: ruleLibrary returns [Boolean current=false] : ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' ) ;
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
            // PsiInternalEJSL.g:4701:1: ( ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' ) )
            // PsiInternalEJSL.g:4702:2: ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' )
            {
            // PsiInternalEJSL.g:4702:2: ( () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}' )
            // PsiInternalEJSL.g:4703:3: () otherlv_1= 'Library' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )? (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )? (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )? otherlv_24= '}'
            {
            // PsiInternalEJSL.g:4703:3: ()
            // PsiInternalEJSL.g:4704:4: 
            {

            				precedeComposite(elementTypeProvider.getLibrary_LibraryAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getLibrary_LibraryKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,89,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4717:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:4718:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:4718:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:4719:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getLibrary_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:4753:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:4754:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:4754:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:4755:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getLibrary_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_20);
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
            		
            otherlv_7=(Token)match(input,19,FOLLOW_103); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:4775:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==76) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // PsiInternalEJSL.g:4776:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getLibrary_LanguagesKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,76,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_85); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:4790:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop108:
                    do {
                        int alt108=2;
                        int LA108_0 = input.LA(1);

                        if ( (LA108_0==112) ) {
                            alt108=1;
                        }


                        switch (alt108) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4791:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:4791:5: (lv_languages_10_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:4792:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getLibrary_LanguagesLanguageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop108;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_104); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:4813:3: (otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )* )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==59) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // PsiInternalEJSL.g:4814:4: otherlv_12= '*Entities' ( (otherlv_13= RULE_STRING ) ) (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getLibrary_EntitiesKeyword_9_0ElementType());
                    			
                    otherlv_12=(Token)match(input,59,FOLLOW_3); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:4821:4: ( (otherlv_13= RULE_STRING ) )
                    // PsiInternalEJSL.g:4822:5: (otherlv_13= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:4822:5: (otherlv_13= RULE_STRING )
                    // PsiInternalEJSL.g:4823:6: otherlv_13= RULE_STRING
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getLibrary_EntitiesEntityCrossReference_9_1_0ElementType());
                    					
                    otherlv_13=(Token)match(input,RULE_STRING,FOLLOW_105); 

                    						doneLeaf(otherlv_13);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:4838:4: (otherlv_14= ',' ( (otherlv_15= RULE_STRING ) ) )*
                    loop110:
                    do {
                        int alt110=2;
                        int LA110_0 = input.LA(1);

                        if ( (LA110_0==18) ) {
                            alt110=1;
                        }


                        switch (alt110) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4839:5: otherlv_14= ',' ( (otherlv_15= RULE_STRING ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getLibrary_CommaKeyword_9_2_0ElementType());
                    	    				
                    	    otherlv_14=(Token)match(input,18,FOLLOW_3); 

                    	    					doneLeaf(otherlv_14);
                    	    				
                    	    // PsiInternalEJSL.g:4846:5: ( (otherlv_15= RULE_STRING ) )
                    	    // PsiInternalEJSL.g:4847:6: (otherlv_15= RULE_STRING )
                    	    {
                    	    // PsiInternalEJSL.g:4847:6: (otherlv_15= RULE_STRING )
                    	    // PsiInternalEJSL.g:4848:7: otherlv_15= RULE_STRING
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getLibrary_EntitiesEntityCrossReference_9_2_1_0ElementType());
                    	    						
                    	    otherlv_15=(Token)match(input,RULE_STRING,FOLLOW_105); 

                    	    							doneLeaf(otherlv_15);
                    	    						

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

            // PsiInternalEJSL.g:4865:3: (otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}' )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==90) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // PsiInternalEJSL.g:4866:4: otherlv_16= 'classes' otherlv_17= '{' ( (lv_classes_18_0= ruleClass ) )* otherlv_19= '}'
                    {

                    				markLeaf(elementTypeProvider.getLibrary_ClassesKeyword_10_0ElementType());
                    			
                    otherlv_16=(Token)match(input,90,FOLLOW_4); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_10_1ElementType());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_106); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:4880:4: ( (lv_classes_18_0= ruleClass ) )*
                    loop112:
                    do {
                        int alt112=2;
                        int LA112_0 = input.LA(1);

                        if ( (LA112_0==93) ) {
                            alt112=1;
                        }


                        switch (alt112) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4881:5: (lv_classes_18_0= ruleClass )
                    	    {
                    	    // PsiInternalEJSL.g:4881:5: (lv_classes_18_0= ruleClass )
                    	    // PsiInternalEJSL.g:4882:6: lv_classes_18_0= ruleClass
                    	    {

                    	    						markComposite(elementTypeProvider.getLibrary_ClassesClassParserRuleCall_10_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_106);
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
                    	    break loop112;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_10_3ElementType());
                    			
                    otherlv_19=(Token)match(input,19,FOLLOW_107); 

                    				doneLeaf(otherlv_19);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:4903:3: (otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}' )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==91) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // PsiInternalEJSL.g:4904:4: otherlv_20= 'packages' otherlv_21= '{' ( (lv_packages_22_0= rulePackage ) )* otherlv_23= '}'
                    {

                    				markLeaf(elementTypeProvider.getLibrary_PackagesKeyword_11_0ElementType());
                    			
                    otherlv_20=(Token)match(input,91,FOLLOW_4); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getLibrary_LeftCurlyBracketKeyword_11_1ElementType());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_108); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:4918:4: ( (lv_packages_22_0= rulePackage ) )*
                    loop114:
                    do {
                        int alt114=2;
                        int LA114_0 = input.LA(1);

                        if ( (LA114_0==92) ) {
                            alt114=1;
                        }


                        switch (alt114) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:4919:5: (lv_packages_22_0= rulePackage )
                    	    {
                    	    // PsiInternalEJSL.g:4919:5: (lv_packages_22_0= rulePackage )
                    	    // PsiInternalEJSL.g:4920:6: lv_packages_22_0= rulePackage
                    	    {

                    	    						markComposite(elementTypeProvider.getLibrary_PackagesPackageParserRuleCall_11_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_108);
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
                    	    break loop114;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_11_3ElementType());
                    			
                    otherlv_23=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_23);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getLibrary_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_24=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:4952:1: entryRulePackage returns [Boolean current=false] : iv_rulePackage= rulePackage EOF ;
    public final Boolean entryRulePackage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePackage = null;


        try {
            // PsiInternalEJSL.g:4952:49: (iv_rulePackage= rulePackage EOF )
            // PsiInternalEJSL.g:4953:2: iv_rulePackage= rulePackage EOF
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
    // PsiInternalEJSL.g:4959:1: rulePackage returns [Boolean current=false] : ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' ) ;
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
            // PsiInternalEJSL.g:4960:1: ( ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' ) )
            // PsiInternalEJSL.g:4961:2: ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' )
            {
            // PsiInternalEJSL.g:4961:2: ( () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}' )
            // PsiInternalEJSL.g:4962:3: () otherlv_1= 'Package' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )? (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )? otherlv_12= '}'
            {
            // PsiInternalEJSL.g:4962:3: ()
            // PsiInternalEJSL.g:4963:4: 
            {

            				precedeComposite(elementTypeProvider.getPackage_PackageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getPackage_PackageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,92,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:4976:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:4977:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:4977:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:4978:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_109); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:4998:3: (otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}' )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==91) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // PsiInternalEJSL.g:4999:4: otherlv_4= 'packages' otherlv_5= '{' ( (lv_packages_6_0= rulePackage ) )* otherlv_7= '}'
                    {

                    				markLeaf(elementTypeProvider.getPackage_PackagesKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,91,FOLLOW_4); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getPackage_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_108); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:5013:4: ( (lv_packages_6_0= rulePackage ) )*
                    loop116:
                    do {
                        int alt116=2;
                        int LA116_0 = input.LA(1);

                        if ( (LA116_0==92) ) {
                            alt116=1;
                        }


                        switch (alt116) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5014:5: (lv_packages_6_0= rulePackage )
                    	    {
                    	    // PsiInternalEJSL.g:5014:5: (lv_packages_6_0= rulePackage )
                    	    // PsiInternalEJSL.g:5015:6: lv_packages_6_0= rulePackage
                    	    {

                    	    						markComposite(elementTypeProvider.getPackage_PackagesPackageParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_108);
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
                    	    break loop116;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPackage_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_7=(Token)match(input,19,FOLLOW_110); 

                    				doneLeaf(otherlv_7);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5036:3: (otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}' )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==90) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // PsiInternalEJSL.g:5037:4: otherlv_8= 'classes' otherlv_9= '{' ( (lv_classes_10_0= ruleClass ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getPackage_ClassesKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,90,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getPackage_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_106); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:5051:4: ( (lv_classes_10_0= ruleClass ) )*
                    loop118:
                    do {
                        int alt118=2;
                        int LA118_0 = input.LA(1);

                        if ( (LA118_0==93) ) {
                            alt118=1;
                        }


                        switch (alt118) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5052:5: (lv_classes_10_0= ruleClass )
                    	    {
                    	    // PsiInternalEJSL.g:5052:5: (lv_classes_10_0= ruleClass )
                    	    // PsiInternalEJSL.g:5053:6: lv_classes_10_0= ruleClass
                    	    {

                    	    						markComposite(elementTypeProvider.getPackage_ClassesClassParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_106);
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
                    	    break loop118;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPackage_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPackage_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_12=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:5085:1: entryRuleClass returns [Boolean current=false] : iv_ruleClass= ruleClass EOF ;
    public final Boolean entryRuleClass() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleClass = null;


        try {
            // PsiInternalEJSL.g:5085:47: (iv_ruleClass= ruleClass EOF )
            // PsiInternalEJSL.g:5086:2: iv_ruleClass= ruleClass EOF
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
    // PsiInternalEJSL.g:5092:1: ruleClass returns [Boolean current=false] : ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
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
            // PsiInternalEJSL.g:5093:1: ( ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // PsiInternalEJSL.g:5094:2: ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // PsiInternalEJSL.g:5094:2: ( () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // PsiInternalEJSL.g:5095:3: () otherlv_1= 'Class' ( (lv_name_2_0= ruleMYID ) ) (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )? otherlv_5= '{' (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )? (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )? (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            // PsiInternalEJSL.g:5095:3: ()
            // PsiInternalEJSL.g:5096:4: 
            {

            				precedeComposite(elementTypeProvider.getClass_ClassAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getClass_ClassKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,93,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:5109:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:5110:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:5110:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:5111:5: lv_name_2_0= ruleMYID
            {

            					markComposite(elementTypeProvider.getClass_NameMYIDParserRuleCall_2_0ElementType());
            				
            pushFollow(FOLLOW_41);
            lv_name_2_0=ruleMYID();

            state._fsp--;


            					doneComposite();
            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            }


            }

            // PsiInternalEJSL.g:5124:3: (otherlv_3= 'extends' ( ( ruleQualifiedName ) ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==41) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // PsiInternalEJSL.g:5125:4: otherlv_3= 'extends' ( ( ruleQualifiedName ) )
                    {

                    				markLeaf(elementTypeProvider.getClass_ExtendsKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,41,FOLLOW_40); 

                    				doneLeaf(otherlv_3);
                    			
                    // PsiInternalEJSL.g:5132:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:5133:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:5133:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:5134:6: ruleQualifiedName
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
            		
            otherlv_5=(Token)match(input,16,FOLLOW_111); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:5157:3: (otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )* )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==94) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // PsiInternalEJSL.g:5158:4: otherlv_6= '*Class references' ( ( ruleQualifiedName ) ) (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getClass_ClassReferencesKeyword_5_0ElementType());
                    			
                    otherlv_6=(Token)match(input,94,FOLLOW_40); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:5165:4: ( ( ruleQualifiedName ) )
                    // PsiInternalEJSL.g:5166:5: ( ruleQualifiedName )
                    {
                    // PsiInternalEJSL.g:5166:5: ( ruleQualifiedName )
                    // PsiInternalEJSL.g:5167:6: ruleQualifiedName
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markComposite(elementTypeProvider.getClass_ReferencesClassCrossReference_5_1_0ElementType());
                    					
                    pushFollow(FOLLOW_112);
                    ruleQualifiedName();

                    state._fsp--;


                    						doneComposite();
                    					

                    }


                    }

                    // PsiInternalEJSL.g:5182:4: (otherlv_8= ',' ( ( ruleQualifiedName ) ) )*
                    loop121:
                    do {
                        int alt121=2;
                        int LA121_0 = input.LA(1);

                        if ( (LA121_0==18) ) {
                            alt121=1;
                        }


                        switch (alt121) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5183:5: otherlv_8= ',' ( ( ruleQualifiedName ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getClass_CommaKeyword_5_2_0ElementType());
                    	    				
                    	    otherlv_8=(Token)match(input,18,FOLLOW_40); 

                    	    					doneLeaf(otherlv_8);
                    	    				
                    	    // PsiInternalEJSL.g:5190:5: ( ( ruleQualifiedName ) )
                    	    // PsiInternalEJSL.g:5191:6: ( ruleQualifiedName )
                    	    {
                    	    // PsiInternalEJSL.g:5191:6: ( ruleQualifiedName )
                    	    // PsiInternalEJSL.g:5192:7: ruleQualifiedName
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markComposite(elementTypeProvider.getClass_ReferencesClassCrossReference_5_2_1_0ElementType());
                    	    						
                    	    pushFollow(FOLLOW_112);
                    	    ruleQualifiedName();

                    	    state._fsp--;


                    	    							doneComposite();
                    	    						

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

            // PsiInternalEJSL.g:5209:3: (otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )* )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==59) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // PsiInternalEJSL.g:5210:4: otherlv_10= '*Entities' ( (otherlv_11= RULE_STRING ) ) (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )*
                    {

                    				markLeaf(elementTypeProvider.getClass_EntitiesKeyword_6_0ElementType());
                    			
                    otherlv_10=(Token)match(input,59,FOLLOW_3); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:5217:4: ( (otherlv_11= RULE_STRING ) )
                    // PsiInternalEJSL.g:5218:5: (otherlv_11= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:5218:5: (otherlv_11= RULE_STRING )
                    // PsiInternalEJSL.g:5219:6: otherlv_11= RULE_STRING
                    {

                    						if (!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						markLeaf(elementTypeProvider.getClass_EntitiesEntityCrossReference_6_1_0ElementType());
                    					
                    otherlv_11=(Token)match(input,RULE_STRING,FOLLOW_113); 

                    						doneLeaf(otherlv_11);
                    					

                    }


                    }

                    // PsiInternalEJSL.g:5234:4: (otherlv_12= ',' ( (otherlv_13= RULE_STRING ) ) )*
                    loop123:
                    do {
                        int alt123=2;
                        int LA123_0 = input.LA(1);

                        if ( (LA123_0==18) ) {
                            alt123=1;
                        }


                        switch (alt123) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5235:5: otherlv_12= ',' ( (otherlv_13= RULE_STRING ) )
                    	    {

                    	    					markLeaf(elementTypeProvider.getClass_CommaKeyword_6_2_0ElementType());
                    	    				
                    	    otherlv_12=(Token)match(input,18,FOLLOW_3); 

                    	    					doneLeaf(otherlv_12);
                    	    				
                    	    // PsiInternalEJSL.g:5242:5: ( (otherlv_13= RULE_STRING ) )
                    	    // PsiInternalEJSL.g:5243:6: (otherlv_13= RULE_STRING )
                    	    {
                    	    // PsiInternalEJSL.g:5243:6: (otherlv_13= RULE_STRING )
                    	    // PsiInternalEJSL.g:5244:7: otherlv_13= RULE_STRING
                    	    {

                    	    							if (!current) {
                    	    								associateWithSemanticElement();
                    	    								current = true;
                    	    							}
                    	    						

                    	    							markLeaf(elementTypeProvider.getClass_EntitiesEntityCrossReference_6_2_1_0ElementType());
                    	    						
                    	    otherlv_13=(Token)match(input,RULE_STRING,FOLLOW_113); 

                    	    							doneLeaf(otherlv_13);
                    	    						

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

            // PsiInternalEJSL.g:5261:3: (otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}' )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==95) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // PsiInternalEJSL.g:5262:4: otherlv_14= 'methods' otherlv_15= '{' ( (lv_methods_16_0= ruleMethod ) )* otherlv_17= '}'
                    {

                    				markLeaf(elementTypeProvider.getClass_MethodsKeyword_7_0ElementType());
                    			
                    otherlv_14=(Token)match(input,95,FOLLOW_4); 

                    				doneLeaf(otherlv_14);
                    			

                    				markLeaf(elementTypeProvider.getClass_LeftCurlyBracketKeyword_7_1ElementType());
                    			
                    otherlv_15=(Token)match(input,16,FOLLOW_114); 

                    				doneLeaf(otherlv_15);
                    			
                    // PsiInternalEJSL.g:5276:4: ( (lv_methods_16_0= ruleMethod ) )*
                    loop125:
                    do {
                        int alt125=2;
                        int LA125_0 = input.LA(1);

                        if ( (LA125_0==96) ) {
                            alt125=1;
                        }


                        switch (alt125) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5277:5: (lv_methods_16_0= ruleMethod )
                    	    {
                    	    // PsiInternalEJSL.g:5277:5: (lv_methods_16_0= ruleMethod )
                    	    // PsiInternalEJSL.g:5278:6: lv_methods_16_0= ruleMethod
                    	    {

                    	    						markComposite(elementTypeProvider.getClass_MethodsMethodParserRuleCall_7_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_114);
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
                    	    break loop125;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getClass_RightCurlyBracketKeyword_7_3ElementType());
                    			
                    otherlv_17=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_17);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getClass_RightCurlyBracketKeyword_8ElementType());
            		
            otherlv_18=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:5310:1: entryRuleMethod returns [Boolean current=false] : iv_ruleMethod= ruleMethod EOF ;
    public final Boolean entryRuleMethod() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleMethod = null;


        try {
            // PsiInternalEJSL.g:5310:48: (iv_ruleMethod= ruleMethod EOF )
            // PsiInternalEJSL.g:5311:2: iv_ruleMethod= ruleMethod EOF
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
    // PsiInternalEJSL.g:5317:1: ruleMethod returns [Boolean current=false] : ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' ) ;
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
            // PsiInternalEJSL.g:5318:1: ( ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' ) )
            // PsiInternalEJSL.g:5319:2: ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' )
            {
            // PsiInternalEJSL.g:5319:2: ( () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}' )
            // PsiInternalEJSL.g:5320:3: () otherlv_1= 'Method' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )? (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )? otherlv_12= '}'
            {
            // PsiInternalEJSL.g:5320:3: ()
            // PsiInternalEJSL.g:5321:4: 
            {

            				precedeComposite(elementTypeProvider.getMethod_MethodAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getMethod_MethodKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,96,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:5334:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:5335:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:5335:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:5336:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_115); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:5356:3: (otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) ) )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==97) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // PsiInternalEJSL.g:5357:4: otherlv_4= 'Returnvalue' ( (lv_returnvalue_5_0= RULE_ID ) ) otherlv_6= ':' ( (lv_type_7_0= ruleType ) )
                    {

                    				markLeaf(elementTypeProvider.getMethod_ReturnvalueKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,97,FOLLOW_25); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:5364:4: ( (lv_returnvalue_5_0= RULE_ID ) )
                    // PsiInternalEJSL.g:5365:5: (lv_returnvalue_5_0= RULE_ID )
                    {
                    // PsiInternalEJSL.g:5365:5: (lv_returnvalue_5_0= RULE_ID )
                    // PsiInternalEJSL.g:5366:6: lv_returnvalue_5_0= RULE_ID
                    {

                    						markLeaf(elementTypeProvider.getMethod_ReturnvalueIDTerminalRuleCall_4_1_0ElementType());
                    					
                    lv_returnvalue_5_0=(Token)match(input,RULE_ID,FOLLOW_72); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_returnvalue_5_0);
                    					

                    }


                    }


                    				markLeaf(elementTypeProvider.getMethod_ColonKeyword_4_2ElementType());
                    			
                    otherlv_6=(Token)match(input,64,FOLLOW_27); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:5388:4: ( (lv_type_7_0= ruleType ) )
                    // PsiInternalEJSL.g:5389:5: (lv_type_7_0= ruleType )
                    {
                    // PsiInternalEJSL.g:5389:5: (lv_type_7_0= ruleType )
                    // PsiInternalEJSL.g:5390:6: lv_type_7_0= ruleType
                    {

                    						markComposite(elementTypeProvider.getMethod_TypeTypeParserRuleCall_4_3_0ElementType());
                    					
                    pushFollow(FOLLOW_116);
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

            // PsiInternalEJSL.g:5404:3: (otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}' )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==98) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // PsiInternalEJSL.g:5405:4: otherlv_8= 'methodparameters' otherlv_9= '{' ( (lv_methodparameters_10_0= ruleMethodParameter ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getMethod_MethodparametersKeyword_5_0ElementType());
                    			
                    otherlv_8=(Token)match(input,98,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getMethod_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_117); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:5419:4: ( (lv_methodparameters_10_0= ruleMethodParameter ) )*
                    loop128:
                    do {
                        int alt128=2;
                        int LA128_0 = input.LA(1);

                        if ( (LA128_0==99) ) {
                            alt128=1;
                        }


                        switch (alt128) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5420:5: (lv_methodparameters_10_0= ruleMethodParameter )
                    	    {
                    	    // PsiInternalEJSL.g:5420:5: (lv_methodparameters_10_0= ruleMethodParameter )
                    	    // PsiInternalEJSL.g:5421:6: lv_methodparameters_10_0= ruleMethodParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getMethod_MethodparametersMethodParameterParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_117);
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
                    	    break loop128;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getMethod_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getMethod_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_12=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:5453:1: entryRuleMethodParameter returns [Boolean current=false] : iv_ruleMethodParameter= ruleMethodParameter EOF ;
    public final Boolean entryRuleMethodParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleMethodParameter = null;


        try {
            // PsiInternalEJSL.g:5453:57: (iv_ruleMethodParameter= ruleMethodParameter EOF )
            // PsiInternalEJSL.g:5454:2: iv_ruleMethodParameter= ruleMethodParameter EOF
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
    // PsiInternalEJSL.g:5460:1: ruleMethodParameter returns [Boolean current=false] : ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) ;
    public final Boolean ruleMethodParameter() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Boolean lv_type_4_0 = null;


        try {
            // PsiInternalEJSL.g:5461:1: ( ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) ) )
            // PsiInternalEJSL.g:5462:2: ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            {
            // PsiInternalEJSL.g:5462:2: ( () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) ) )
            // PsiInternalEJSL.g:5463:3: () otherlv_1= 'MethodParameter' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ':' ( (lv_type_4_0= ruleType ) )
            {
            // PsiInternalEJSL.g:5463:3: ()
            // PsiInternalEJSL.g:5464:4: 
            {

            				precedeComposite(elementTypeProvider.getMethodParameter_MethodParameterAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getMethodParameter_MethodParameterKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,99,FOLLOW_25); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:5477:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:5478:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:5478:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:5479:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getMethodParameter_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_72); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getMethodParameter_ColonKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,64,FOLLOW_27); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:5501:3: ( (lv_type_4_0= ruleType ) )
            // PsiInternalEJSL.g:5502:4: (lv_type_4_0= ruleType )
            {
            // PsiInternalEJSL.g:5502:4: (lv_type_4_0= ruleType )
            // PsiInternalEJSL.g:5503:5: lv_type_4_0= ruleType
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
    // PsiInternalEJSL.g:5520:1: entryRuleTemplate returns [Boolean current=false] : iv_ruleTemplate= ruleTemplate EOF ;
    public final Boolean entryRuleTemplate() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleTemplate = null;


        try {
            // PsiInternalEJSL.g:5520:50: (iv_ruleTemplate= ruleTemplate EOF )
            // PsiInternalEJSL.g:5521:2: iv_ruleTemplate= ruleTemplate EOF
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
    // PsiInternalEJSL.g:5527:1: ruleTemplate returns [Boolean current=false] : ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' ) ;
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
            // PsiInternalEJSL.g:5528:1: ( ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' ) )
            // PsiInternalEJSL.g:5529:2: ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' )
            {
            // PsiInternalEJSL.g:5529:2: ( () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}' )
            // PsiInternalEJSL.g:5530:3: () otherlv_1= 'Template' ( (lv_name_2_0= ruleMYID ) ) otherlv_3= '{' otherlv_4= 'Manifestation' otherlv_5= '{' ( (lv_manifest_6_0= ruleManifestation ) ) otherlv_7= '}' (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )? (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )? (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )? (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )? otherlv_24= '}'
            {
            // PsiInternalEJSL.g:5530:3: ()
            // PsiInternalEJSL.g:5531:4: 
            {

            				precedeComposite(elementTypeProvider.getTemplate_TemplateAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getTemplate_TemplateKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,100,FOLLOW_40); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:5544:3: ( (lv_name_2_0= ruleMYID ) )
            // PsiInternalEJSL.g:5545:4: (lv_name_2_0= ruleMYID )
            {
            // PsiInternalEJSL.g:5545:4: (lv_name_2_0= ruleMYID )
            // PsiInternalEJSL.g:5546:5: lv_name_2_0= ruleMYID
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_82); 

            			doneLeaf(otherlv_3);
            		

            			markLeaf(elementTypeProvider.getTemplate_ManifestationKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,75,FOLLOW_4); 

            			doneLeaf(otherlv_4);
            		

            			markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_5ElementType());
            		
            otherlv_5=(Token)match(input,16,FOLLOW_83); 

            			doneLeaf(otherlv_5);
            		
            // PsiInternalEJSL.g:5580:3: ( (lv_manifest_6_0= ruleManifestation ) )
            // PsiInternalEJSL.g:5581:4: (lv_manifest_6_0= ruleManifestation )
            {
            // PsiInternalEJSL.g:5581:4: (lv_manifest_6_0= ruleManifestation )
            // PsiInternalEJSL.g:5582:5: lv_manifest_6_0= ruleManifestation
            {

            					markComposite(elementTypeProvider.getTemplate_ManifestManifestationParserRuleCall_6_0ElementType());
            				
            pushFollow(FOLLOW_20);
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
            		
            otherlv_7=(Token)match(input,19,FOLLOW_118); 

            			doneLeaf(otherlv_7);
            		
            // PsiInternalEJSL.g:5602:3: (otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}' )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==76) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // PsiInternalEJSL.g:5603:4: otherlv_8= 'languages' otherlv_9= '{' ( (lv_languages_10_0= ruleLanguage ) )* otherlv_11= '}'
                    {

                    				markLeaf(elementTypeProvider.getTemplate_LanguagesKeyword_8_0ElementType());
                    			
                    otherlv_8=(Token)match(input,76,FOLLOW_4); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_8_1ElementType());
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_85); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:5617:4: ( (lv_languages_10_0= ruleLanguage ) )*
                    loop130:
                    do {
                        int alt130=2;
                        int LA130_0 = input.LA(1);

                        if ( (LA130_0==112) ) {
                            alt130=1;
                        }


                        switch (alt130) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5618:5: (lv_languages_10_0= ruleLanguage )
                    	    {
                    	    // PsiInternalEJSL.g:5618:5: (lv_languages_10_0= ruleLanguage )
                    	    // PsiInternalEJSL.g:5619:6: lv_languages_10_0= ruleLanguage
                    	    {

                    	    						markComposite(elementTypeProvider.getTemplate_LanguagesLanguageParserRuleCall_8_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_85);
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
                    	    break loop130;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_8_3ElementType());
                    			
                    otherlv_11=(Token)match(input,19,FOLLOW_119); 

                    				doneLeaf(otherlv_11);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5640:3: (otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}' )?
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==88) ) {
                alt133=1;
            }
            switch (alt133) {
                case 1 :
                    // PsiInternalEJSL.g:5641:4: otherlv_12= 'parameters' otherlv_13= '{' ( (lv_localparameters_14_0= ruleParameter ) )* otherlv_15= '}'
                    {

                    				markLeaf(elementTypeProvider.getTemplate_ParametersKeyword_9_0ElementType());
                    			
                    otherlv_12=(Token)match(input,88,FOLLOW_4); 

                    				doneLeaf(otherlv_12);
                    			

                    				markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_9_1ElementType());
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_9); 

                    				doneLeaf(otherlv_13);
                    			
                    // PsiInternalEJSL.g:5655:4: ( (lv_localparameters_14_0= ruleParameter ) )*
                    loop132:
                    do {
                        int alt132=2;
                        int LA132_0 = input.LA(1);

                        if ( (LA132_0==31) ) {
                            alt132=1;
                        }


                        switch (alt132) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5656:5: (lv_localparameters_14_0= ruleParameter )
                    	    {
                    	    // PsiInternalEJSL.g:5656:5: (lv_localparameters_14_0= ruleParameter )
                    	    // PsiInternalEJSL.g:5657:6: lv_localparameters_14_0= ruleParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getTemplate_LocalparametersParameterParserRuleCall_9_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_9);
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
                    	    break loop132;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_9_3ElementType());
                    			
                    otherlv_15=(Token)match(input,19,FOLLOW_120); 

                    				doneLeaf(otherlv_15);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5678:3: (otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}' )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==101) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // PsiInternalEJSL.g:5679:4: otherlv_16= 'positions' otherlv_17= '{' ( (lv_positions_18_0= rulePosition ) )* otherlv_19= '}'
                    {

                    				markLeaf(elementTypeProvider.getTemplate_PositionsKeyword_10_0ElementType());
                    			
                    otherlv_16=(Token)match(input,101,FOLLOW_4); 

                    				doneLeaf(otherlv_16);
                    			

                    				markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_10_1ElementType());
                    			
                    otherlv_17=(Token)match(input,16,FOLLOW_121); 

                    				doneLeaf(otherlv_17);
                    			
                    // PsiInternalEJSL.g:5693:4: ( (lv_positions_18_0= rulePosition ) )*
                    loop134:
                    do {
                        int alt134=2;
                        int LA134_0 = input.LA(1);

                        if ( (LA134_0==115) ) {
                            alt134=1;
                        }


                        switch (alt134) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5694:5: (lv_positions_18_0= rulePosition )
                    	    {
                    	    // PsiInternalEJSL.g:5694:5: (lv_positions_18_0= rulePosition )
                    	    // PsiInternalEJSL.g:5695:6: lv_positions_18_0= rulePosition
                    	    {

                    	    						markComposite(elementTypeProvider.getTemplate_PositionsPositionParserRuleCall_10_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_121);
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
                    	    break loop134;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_10_3ElementType());
                    			
                    otherlv_19=(Token)match(input,19,FOLLOW_122); 

                    				doneLeaf(otherlv_19);
                    			

                    }
                    break;

            }

            // PsiInternalEJSL.g:5716:3: (otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}' )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==102) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // PsiInternalEJSL.g:5717:4: otherlv_20= 'cssblocks' otherlv_21= '{' ( (lv_cssblocks_22_0= ruleCssBlock ) )* otherlv_23= '}'
                    {

                    				markLeaf(elementTypeProvider.getTemplate_CssblocksKeyword_11_0ElementType());
                    			
                    otherlv_20=(Token)match(input,102,FOLLOW_4); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getTemplate_LeftCurlyBracketKeyword_11_1ElementType());
                    			
                    otherlv_21=(Token)match(input,16,FOLLOW_123); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:5731:4: ( (lv_cssblocks_22_0= ruleCssBlock ) )*
                    loop136:
                    do {
                        int alt136=2;
                        int LA136_0 = input.LA(1);

                        if ( (LA136_0==121) ) {
                            alt136=1;
                        }


                        switch (alt136) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:5732:5: (lv_cssblocks_22_0= ruleCssBlock )
                    	    {
                    	    // PsiInternalEJSL.g:5732:5: (lv_cssblocks_22_0= ruleCssBlock )
                    	    // PsiInternalEJSL.g:5733:6: lv_cssblocks_22_0= ruleCssBlock
                    	    {

                    	    						markComposite(elementTypeProvider.getTemplate_CssblocksCssBlockParserRuleCall_11_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_123);
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
                    	    break loop136;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_11_3ElementType());
                    			
                    otherlv_23=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_23);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getTemplate_RightCurlyBracketKeyword_12ElementType());
            		
            otherlv_24=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:5765:1: entryRuleManifestation returns [Boolean current=false] : iv_ruleManifestation= ruleManifestation EOF ;
    public final Boolean entryRuleManifestation() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleManifestation = null;


        try {
            // PsiInternalEJSL.g:5765:55: (iv_ruleManifestation= ruleManifestation EOF )
            // PsiInternalEJSL.g:5766:2: iv_ruleManifestation= ruleManifestation EOF
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
    // PsiInternalEJSL.g:5772:1: ruleManifestation returns [Boolean current=false] : ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )? ) ;
    public final Boolean ruleManifestation() throws RecognitionException {
        Boolean current = false;

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
        Boolean lv_authors_3_0 = null;


        try {
            // PsiInternalEJSL.g:5773:1: ( ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )? ) )
            // PsiInternalEJSL.g:5774:2: ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )? )
            {
            // PsiInternalEJSL.g:5774:2: ( () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )? )
            // PsiInternalEJSL.g:5775:3: () otherlv_1= 'authors' otherlv_2= '{' ( (lv_authors_3_0= ruleAuthor ) )+ otherlv_4= '}' (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )? (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )? (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )? (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )? (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )? (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )?
            {
            // PsiInternalEJSL.g:5775:3: ()
            // PsiInternalEJSL.g:5776:4: 
            {

            				precedeComposite(elementTypeProvider.getManifestation_ManifestationAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getManifestation_AuthorsKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,103,FOLLOW_4); 

            			doneLeaf(otherlv_1);
            		

            			markLeaf(elementTypeProvider.getManifestation_LeftCurlyBracketKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,16,FOLLOW_124); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:5796:3: ( (lv_authors_3_0= ruleAuthor ) )+
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
            	    // PsiInternalEJSL.g:5797:4: (lv_authors_3_0= ruleAuthor )
            	    {
            	    // PsiInternalEJSL.g:5797:4: (lv_authors_3_0= ruleAuthor )
            	    // PsiInternalEJSL.g:5798:5: lv_authors_3_0= ruleAuthor
            	    {

            	    					markComposite(elementTypeProvider.getManifestation_AuthorsAuthorParserRuleCall_3_0ElementType());
            	    				
            	    pushFollow(FOLLOW_125);
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
            	    if ( cnt138 >= 1 ) break loop138;
                        EarlyExitException eee =
                            new EarlyExitException(138, input);
                        throw eee;
                }
                cnt138++;
            } while (true);


            			markLeaf(elementTypeProvider.getManifestation_RightCurlyBracketKeyword_4ElementType());
            		
            otherlv_4=(Token)match(input,19,FOLLOW_126); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:5818:3: (otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) ) )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==104) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // PsiInternalEJSL.g:5819:4: otherlv_5= 'creationdate' otherlv_6= '=' ( (lv_creationdate_7_0= RULE_DATE ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_CreationdateKeyword_5_0ElementType());
                    			
                    otherlv_5=(Token)match(input,104,FOLLOW_23); 

                    				doneLeaf(otherlv_5);
                    			

                    				markLeaf(elementTypeProvider.getManifestation_EqualsSignKeyword_5_1ElementType());
                    			
                    otherlv_6=(Token)match(input,28,FOLLOW_127); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:5833:4: ( (lv_creationdate_7_0= RULE_DATE ) )
                    // PsiInternalEJSL.g:5834:5: (lv_creationdate_7_0= RULE_DATE )
                    {
                    // PsiInternalEJSL.g:5834:5: (lv_creationdate_7_0= RULE_DATE )
                    // PsiInternalEJSL.g:5835:6: lv_creationdate_7_0= RULE_DATE
                    {

                    						markLeaf(elementTypeProvider.getManifestation_CreationdateDATETerminalRuleCall_5_2_0ElementType());
                    					
                    lv_creationdate_7_0=(Token)match(input,RULE_DATE,FOLLOW_128); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_creationdate_7_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:5851:3: (otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) ) )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==105) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // PsiInternalEJSL.g:5852:4: otherlv_8= 'copyright' otherlv_9= '=' ( (lv_copyright_10_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_CopyrightKeyword_6_0ElementType());
                    			
                    otherlv_8=(Token)match(input,105,FOLLOW_23); 

                    				doneLeaf(otherlv_8);
                    			

                    				markLeaf(elementTypeProvider.getManifestation_EqualsSignKeyword_6_1ElementType());
                    			
                    otherlv_9=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_9);
                    			
                    // PsiInternalEJSL.g:5866:4: ( (lv_copyright_10_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:5867:5: (lv_copyright_10_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:5867:5: (lv_copyright_10_0= RULE_STRING )
                    // PsiInternalEJSL.g:5868:6: lv_copyright_10_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_CopyrightSTRINGTerminalRuleCall_6_2_0ElementType());
                    					
                    lv_copyright_10_0=(Token)match(input,RULE_STRING,FOLLOW_129); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_copyright_10_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:5884:3: (otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) ) )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==106) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // PsiInternalEJSL.g:5885:4: otherlv_11= 'license' otherlv_12= '=' ( (lv_license_13_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_LicenseKeyword_7_0ElementType());
                    			
                    otherlv_11=(Token)match(input,106,FOLLOW_23); 

                    				doneLeaf(otherlv_11);
                    			

                    				markLeaf(elementTypeProvider.getManifestation_EqualsSignKeyword_7_1ElementType());
                    			
                    otherlv_12=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_12);
                    			
                    // PsiInternalEJSL.g:5899:4: ( (lv_license_13_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:5900:5: (lv_license_13_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:5900:5: (lv_license_13_0= RULE_STRING )
                    // PsiInternalEJSL.g:5901:6: lv_license_13_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_LicenseSTRINGTerminalRuleCall_7_2_0ElementType());
                    					
                    lv_license_13_0=(Token)match(input,RULE_STRING,FOLLOW_130); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_license_13_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:5917:3: (otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) ) )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==107) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // PsiInternalEJSL.g:5918:4: otherlv_14= 'link' otherlv_15= '=' ( (lv_link_16_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_LinkKeyword_8_0ElementType());
                    			
                    otherlv_14=(Token)match(input,107,FOLLOW_23); 

                    				doneLeaf(otherlv_14);
                    			

                    				markLeaf(elementTypeProvider.getManifestation_EqualsSignKeyword_8_1ElementType());
                    			
                    otherlv_15=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_15);
                    			
                    // PsiInternalEJSL.g:5932:4: ( (lv_link_16_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:5933:5: (lv_link_16_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:5933:5: (lv_link_16_0= RULE_STRING )
                    // PsiInternalEJSL.g:5934:6: lv_link_16_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_LinkSTRINGTerminalRuleCall_8_2_0ElementType());
                    					
                    lv_link_16_0=(Token)match(input,RULE_STRING,FOLLOW_131); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_link_16_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:5950:3: (otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) ) )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==108) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // PsiInternalEJSL.g:5951:4: otherlv_17= 'version' otherlv_18= '=' ( (lv_version_19_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_VersionKeyword_9_0ElementType());
                    			
                    otherlv_17=(Token)match(input,108,FOLLOW_23); 

                    				doneLeaf(otherlv_17);
                    			

                    				markLeaf(elementTypeProvider.getManifestation_EqualsSignKeyword_9_1ElementType());
                    			
                    otherlv_18=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_18);
                    			
                    // PsiInternalEJSL.g:5965:4: ( (lv_version_19_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:5966:5: (lv_version_19_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:5966:5: (lv_version_19_0= RULE_STRING )
                    // PsiInternalEJSL.g:5967:6: lv_version_19_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_VersionSTRINGTerminalRuleCall_9_2_0ElementType());
                    					
                    lv_version_19_0=(Token)match(input,RULE_STRING,FOLLOW_132); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_version_19_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:5983:3: (otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) ) )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==36) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // PsiInternalEJSL.g:5984:4: otherlv_20= 'description' otherlv_21= '=' ( (lv_description_22_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getManifestation_DescriptionKeyword_10_0ElementType());
                    			
                    otherlv_20=(Token)match(input,36,FOLLOW_23); 

                    				doneLeaf(otherlv_20);
                    			

                    				markLeaf(elementTypeProvider.getManifestation_EqualsSignKeyword_10_1ElementType());
                    			
                    otherlv_21=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_21);
                    			
                    // PsiInternalEJSL.g:5998:4: ( (lv_description_22_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:5999:5: (lv_description_22_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:5999:5: (lv_description_22_0= RULE_STRING )
                    // PsiInternalEJSL.g:6000:6: lv_description_22_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getManifestation_DescriptionSTRINGTerminalRuleCall_10_2_0ElementType());
                    					
                    lv_description_22_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_description_22_0);
                    					

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
    // PsiInternalEJSL.g:6020:1: entryRuleAuthor returns [Boolean current=false] : iv_ruleAuthor= ruleAuthor EOF ;
    public final Boolean entryRuleAuthor() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleAuthor = null;


        try {
            // PsiInternalEJSL.g:6020:48: (iv_ruleAuthor= ruleAuthor EOF )
            // PsiInternalEJSL.g:6021:2: iv_ruleAuthor= ruleAuthor EOF
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
    // PsiInternalEJSL.g:6027:1: ruleAuthor returns [Boolean current=false] : ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}' ) ;
    public final Boolean ruleAuthor() throws RecognitionException {
        Boolean current = false;

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

        try {
            // PsiInternalEJSL.g:6028:1: ( ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}' ) )
            // PsiInternalEJSL.g:6029:2: ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}' )
            {
            // PsiInternalEJSL.g:6029:2: ( () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}' )
            // PsiInternalEJSL.g:6030:3: () otherlv_1= 'Author' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= '{' (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )? (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )? otherlv_10= '}'
            {
            // PsiInternalEJSL.g:6030:3: ()
            // PsiInternalEJSL.g:6031:4: 
            {

            				precedeComposite(elementTypeProvider.getAuthor_AuthorAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getAuthor_AuthorKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,109,FOLLOW_3); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6044:3: ( (lv_name_2_0= RULE_STRING ) )
            // PsiInternalEJSL.g:6045:4: (lv_name_2_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:6045:4: (lv_name_2_0= RULE_STRING )
            // PsiInternalEJSL.g:6046:5: lv_name_2_0= RULE_STRING
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_133); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:6068:3: (otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) ) )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==110) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // PsiInternalEJSL.g:6069:4: otherlv_4= 'authoremail' otherlv_5= '=' ( (lv_authoremail_6_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getAuthor_AuthoremailKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,110,FOLLOW_23); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getAuthor_EqualsSignKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:6083:4: ( (lv_authoremail_6_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6084:5: (lv_authoremail_6_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6084:5: (lv_authoremail_6_0= RULE_STRING )
                    // PsiInternalEJSL.g:6085:6: lv_authoremail_6_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getAuthor_AuthoremailSTRINGTerminalRuleCall_4_2_0ElementType());
                    					
                    lv_authoremail_6_0=(Token)match(input,RULE_STRING,FOLLOW_134); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_authoremail_6_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6101:3: (otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) ) )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==111) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // PsiInternalEJSL.g:6102:4: otherlv_7= 'authorurl' otherlv_8= '=' ( (lv_authorurl_9_0= RULE_STRING ) )
                    {

                    				markLeaf(elementTypeProvider.getAuthor_AuthorurlKeyword_5_0ElementType());
                    			
                    otherlv_7=(Token)match(input,111,FOLLOW_23); 

                    				doneLeaf(otherlv_7);
                    			

                    				markLeaf(elementTypeProvider.getAuthor_EqualsSignKeyword_5_1ElementType());
                    			
                    otherlv_8=(Token)match(input,28,FOLLOW_3); 

                    				doneLeaf(otherlv_8);
                    			
                    // PsiInternalEJSL.g:6116:4: ( (lv_authorurl_9_0= RULE_STRING ) )
                    // PsiInternalEJSL.g:6117:5: (lv_authorurl_9_0= RULE_STRING )
                    {
                    // PsiInternalEJSL.g:6117:5: (lv_authorurl_9_0= RULE_STRING )
                    // PsiInternalEJSL.g:6118:6: lv_authorurl_9_0= RULE_STRING
                    {

                    						markLeaf(elementTypeProvider.getAuthor_AuthorurlSTRINGTerminalRuleCall_5_2_0ElementType());
                    					
                    lv_authorurl_9_0=(Token)match(input,RULE_STRING,FOLLOW_20); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_authorurl_9_0);
                    					

                    }


                    }


                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getAuthor_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_10=(Token)match(input,19,FOLLOW_2); 

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
    // $ANTLR end "ruleAuthor"


    // $ANTLR start "entryRuleLanguage"
    // PsiInternalEJSL.g:6145:1: entryRuleLanguage returns [Boolean current=false] : iv_ruleLanguage= ruleLanguage EOF ;
    public final Boolean entryRuleLanguage() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleLanguage = null;


        try {
            // PsiInternalEJSL.g:6145:50: (iv_ruleLanguage= ruleLanguage EOF )
            // PsiInternalEJSL.g:6146:2: iv_ruleLanguage= ruleLanguage EOF
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
    // PsiInternalEJSL.g:6152:1: ruleLanguage returns [Boolean current=false] : ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' ) ;
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
            // PsiInternalEJSL.g:6153:1: ( ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' ) )
            // PsiInternalEJSL.g:6154:2: ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' )
            {
            // PsiInternalEJSL.g:6154:2: ( () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}' )
            // PsiInternalEJSL.g:6155:3: () otherlv_1= 'Language' ( (lv_name_2_0= RULE_LANGUAGE_CODE ) ) otherlv_3= '{' (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )? otherlv_8= '}'
            {
            // PsiInternalEJSL.g:6155:3: ()
            // PsiInternalEJSL.g:6156:4: 
            {

            				precedeComposite(elementTypeProvider.getLanguage_LanguageAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getLanguage_LanguageKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,112,FOLLOW_135); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6169:3: ( (lv_name_2_0= RULE_LANGUAGE_CODE ) )
            // PsiInternalEJSL.g:6170:4: (lv_name_2_0= RULE_LANGUAGE_CODE )
            {
            // PsiInternalEJSL.g:6170:4: (lv_name_2_0= RULE_LANGUAGE_CODE )
            // PsiInternalEJSL.g:6171:5: lv_name_2_0= RULE_LANGUAGE_CODE
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
            		
            otherlv_3=(Token)match(input,16,FOLLOW_136); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:6193:3: (otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}' )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==113) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // PsiInternalEJSL.g:6194:4: otherlv_4= 'keyvaluepairs' otherlv_5= '{' ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )* otherlv_7= '}'
                    {

                    				markLeaf(elementTypeProvider.getLanguage_KeyvaluepairsKeyword_4_0ElementType());
                    			
                    otherlv_4=(Token)match(input,113,FOLLOW_4); 

                    				doneLeaf(otherlv_4);
                    			

                    				markLeaf(elementTypeProvider.getLanguage_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_137); 

                    				doneLeaf(otherlv_5);
                    			
                    // PsiInternalEJSL.g:6208:4: ( (lv_keyvaluepairs_6_0= ruleKeyValuePair ) )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==114) ) {
                            alt147=1;
                        }


                        switch (alt147) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6209:5: (lv_keyvaluepairs_6_0= ruleKeyValuePair )
                    	    {
                    	    // PsiInternalEJSL.g:6209:5: (lv_keyvaluepairs_6_0= ruleKeyValuePair )
                    	    // PsiInternalEJSL.g:6210:6: lv_keyvaluepairs_6_0= ruleKeyValuePair
                    	    {

                    	    						markComposite(elementTypeProvider.getLanguage_KeyvaluepairsKeyValuePairParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_137);
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
                    	    break loop147;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getLanguage_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_7=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_7);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getLanguage_RightCurlyBracketKeyword_5ElementType());
            		
            otherlv_8=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:6242:1: entryRuleKeyValuePair returns [Boolean current=false] : iv_ruleKeyValuePair= ruleKeyValuePair EOF ;
    public final Boolean entryRuleKeyValuePair() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleKeyValuePair = null;


        try {
            // PsiInternalEJSL.g:6242:54: (iv_ruleKeyValuePair= ruleKeyValuePair EOF )
            // PsiInternalEJSL.g:6243:2: iv_ruleKeyValuePair= ruleKeyValuePair EOF
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
    // PsiInternalEJSL.g:6249:1: ruleKeyValuePair returns [Boolean current=false] : ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) ) ;
    public final Boolean ruleKeyValuePair() throws RecognitionException {
        Boolean current = false;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_value_4_0=null;

        try {
            // PsiInternalEJSL.g:6250:1: ( ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) ) )
            // PsiInternalEJSL.g:6251:2: ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) )
            {
            // PsiInternalEJSL.g:6251:2: ( () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) ) )
            // PsiInternalEJSL.g:6252:3: () otherlv_1= 'Key' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '=' ( (lv_value_4_0= RULE_STRING ) )
            {
            // PsiInternalEJSL.g:6252:3: ()
            // PsiInternalEJSL.g:6253:4: 
            {

            				precedeComposite(elementTypeProvider.getKeyValuePair_KeyValuePairAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getKeyValuePair_KeyKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,114,FOLLOW_25); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6266:3: ( (lv_name_2_0= RULE_ID ) )
            // PsiInternalEJSL.g:6267:4: (lv_name_2_0= RULE_ID )
            {
            // PsiInternalEJSL.g:6267:4: (lv_name_2_0= RULE_ID )
            // PsiInternalEJSL.g:6268:5: lv_name_2_0= RULE_ID
            {

            					markLeaf(elementTypeProvider.getKeyValuePair_NameIDTerminalRuleCall_2_0ElementType());
            				
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_23); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_name_2_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getKeyValuePair_EqualsSignKeyword_3ElementType());
            		
            otherlv_3=(Token)match(input,28,FOLLOW_3); 

            			doneLeaf(otherlv_3);
            		
            // PsiInternalEJSL.g:6290:3: ( (lv_value_4_0= RULE_STRING ) )
            // PsiInternalEJSL.g:6291:4: (lv_value_4_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:6291:4: (lv_value_4_0= RULE_STRING )
            // PsiInternalEJSL.g:6292:5: lv_value_4_0= RULE_STRING
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
    // PsiInternalEJSL.g:6311:1: entryRulePosition returns [Boolean current=false] : iv_rulePosition= rulePosition EOF ;
    public final Boolean entryRulePosition() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePosition = null;


        try {
            // PsiInternalEJSL.g:6311:50: (iv_rulePosition= rulePosition EOF )
            // PsiInternalEJSL.g:6312:2: iv_rulePosition= rulePosition EOF
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
    // PsiInternalEJSL.g:6318:1: rulePosition returns [Boolean current=false] : ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' ) ;
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
            // PsiInternalEJSL.g:6319:1: ( ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' ) )
            // PsiInternalEJSL.g:6320:2: ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' )
            {
            // PsiInternalEJSL.g:6320:2: ( () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}' )
            // PsiInternalEJSL.g:6321:3: () otherlv_1= 'Templateposition' ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) ) otherlv_4= '{' (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )? otherlv_9= '}'
            {
            // PsiInternalEJSL.g:6321:3: ()
            // PsiInternalEJSL.g:6322:4: 
            {

            				precedeComposite(elementTypeProvider.getPosition_PositionAction_0ElementType());
            				doneComposite();
            				associateWithSemanticElement();
            			

            }


            			markLeaf(elementTypeProvider.getPosition_TemplatepositionKeyword_1ElementType());
            		
            otherlv_1=(Token)match(input,115,FOLLOW_138); 

            			doneLeaf(otherlv_1);
            		
            // PsiInternalEJSL.g:6335:3: ( ( (lv_name_2_0= RULE_POSITION_TYPES ) ) | ( (lv_name_3_0= RULE_ID ) ) )
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
                    // PsiInternalEJSL.g:6336:4: ( (lv_name_2_0= RULE_POSITION_TYPES ) )
                    {
                    // PsiInternalEJSL.g:6336:4: ( (lv_name_2_0= RULE_POSITION_TYPES ) )
                    // PsiInternalEJSL.g:6337:5: (lv_name_2_0= RULE_POSITION_TYPES )
                    {
                    // PsiInternalEJSL.g:6337:5: (lv_name_2_0= RULE_POSITION_TYPES )
                    // PsiInternalEJSL.g:6338:6: lv_name_2_0= RULE_POSITION_TYPES
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
                    // PsiInternalEJSL.g:6354:4: ( (lv_name_3_0= RULE_ID ) )
                    {
                    // PsiInternalEJSL.g:6354:4: ( (lv_name_3_0= RULE_ID ) )
                    // PsiInternalEJSL.g:6355:5: (lv_name_3_0= RULE_ID )
                    {
                    // PsiInternalEJSL.g:6355:5: (lv_name_3_0= RULE_ID )
                    // PsiInternalEJSL.g:6356:6: lv_name_3_0= RULE_ID
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
            		
            otherlv_4=(Token)match(input,16,FOLLOW_139); 

            			doneLeaf(otherlv_4);
            		
            // PsiInternalEJSL.g:6379:3: (otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}' )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==116) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // PsiInternalEJSL.g:6380:4: otherlv_5= 'positionparameters' otherlv_6= '{' ( (lv_positionparameters_7_0= rulePositionParameter ) )* otherlv_8= '}'
                    {

                    				markLeaf(elementTypeProvider.getPosition_PositionparametersKeyword_4_0ElementType());
                    			
                    otherlv_5=(Token)match(input,116,FOLLOW_4); 

                    				doneLeaf(otherlv_5);
                    			

                    				markLeaf(elementTypeProvider.getPosition_LeftCurlyBracketKeyword_4_1ElementType());
                    			
                    otherlv_6=(Token)match(input,16,FOLLOW_140); 

                    				doneLeaf(otherlv_6);
                    			
                    // PsiInternalEJSL.g:6394:4: ( (lv_positionparameters_7_0= rulePositionParameter ) )*
                    loop150:
                    do {
                        int alt150=2;
                        int LA150_0 = input.LA(1);

                        if ( (LA150_0==117) ) {
                            alt150=1;
                        }


                        switch (alt150) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6395:5: (lv_positionparameters_7_0= rulePositionParameter )
                    	    {
                    	    // PsiInternalEJSL.g:6395:5: (lv_positionparameters_7_0= rulePositionParameter )
                    	    // PsiInternalEJSL.g:6396:6: lv_positionparameters_7_0= rulePositionParameter
                    	    {

                    	    						markComposite(elementTypeProvider.getPosition_PositionparametersPositionParameterParserRuleCall_4_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_140);
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
                    	    break loop150;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getPosition_RightCurlyBracketKeyword_4_3ElementType());
                    			
                    otherlv_8=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_8);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPosition_RightCurlyBracketKeyword_5ElementType());
            		
            otherlv_9=(Token)match(input,19,FOLLOW_2); 

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
    // PsiInternalEJSL.g:6428:1: entryRulePositionParameter returns [Boolean current=false] : iv_rulePositionParameter= rulePositionParameter EOF ;
    public final Boolean entryRulePositionParameter() throws RecognitionException {
        Boolean current = false;

        Boolean iv_rulePositionParameter = null;


        try {
            // PsiInternalEJSL.g:6428:59: (iv_rulePositionParameter= rulePositionParameter EOF )
            // PsiInternalEJSL.g:6429:2: iv_rulePositionParameter= rulePositionParameter EOF
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
    // PsiInternalEJSL.g:6435:1: rulePositionParameter returns [Boolean current=false] : (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}' ) ;
    public final Boolean rulePositionParameter() throws RecognitionException {
        Boolean current = false;

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
        Boolean lv_keyvaluepairs_11_0 = null;


        try {
            // PsiInternalEJSL.g:6436:1: ( (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}' ) )
            // PsiInternalEJSL.g:6437:2: (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}' )
            {
            // PsiInternalEJSL.g:6437:2: (otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}' )
            // PsiInternalEJSL.g:6438:3: otherlv_0= 'Position Parameter' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )? (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )? (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )? otherlv_13= '}'
            {

            			markLeaf(elementTypeProvider.getPositionParameter_PositionParameterKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,117,FOLLOW_25); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalEJSL.g:6445:3: ( (lv_name_1_0= RULE_ID ) )
            // PsiInternalEJSL.g:6446:4: (lv_name_1_0= RULE_ID )
            {
            // PsiInternalEJSL.g:6446:4: (lv_name_1_0= RULE_ID )
            // PsiInternalEJSL.g:6447:5: lv_name_1_0= RULE_ID
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
            		
            otherlv_2=(Token)match(input,16,FOLLOW_141); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:6469:3: (otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) ) )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==118) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // PsiInternalEJSL.g:6470:4: otherlv_3= 'divId' otherlv_4= '=' ( (lv_divid_5_0= RULE_ID ) )
                    {

                    				markLeaf(elementTypeProvider.getPositionParameter_DivIdKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,118,FOLLOW_23); 

                    				doneLeaf(otherlv_3);
                    			

                    				markLeaf(elementTypeProvider.getPositionParameter_EqualsSignKeyword_3_1ElementType());
                    			
                    otherlv_4=(Token)match(input,28,FOLLOW_25); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:6484:4: ( (lv_divid_5_0= RULE_ID ) )
                    // PsiInternalEJSL.g:6485:5: (lv_divid_5_0= RULE_ID )
                    {
                    // PsiInternalEJSL.g:6485:5: (lv_divid_5_0= RULE_ID )
                    // PsiInternalEJSL.g:6486:6: lv_divid_5_0= RULE_ID
                    {

                    						markLeaf(elementTypeProvider.getPositionParameter_DividIDTerminalRuleCall_3_2_0ElementType());
                    					
                    lv_divid_5_0=(Token)match(input,RULE_ID,FOLLOW_142); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_divid_5_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6502:3: (otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) ) )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==119) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // PsiInternalEJSL.g:6503:4: otherlv_6= 'Positiontype' otherlv_7= '=' ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) )
                    {

                    				markLeaf(elementTypeProvider.getPositionParameter_PositiontypeKeyword_4_0ElementType());
                    			
                    otherlv_6=(Token)match(input,119,FOLLOW_23); 

                    				doneLeaf(otherlv_6);
                    			

                    				markLeaf(elementTypeProvider.getPositionParameter_EqualsSignKeyword_4_1ElementType());
                    			
                    otherlv_7=(Token)match(input,28,FOLLOW_143); 

                    				doneLeaf(otherlv_7);
                    			
                    // PsiInternalEJSL.g:6517:4: ( (lv_type_8_0= RULE_POSITION_TYPES_NAMES ) )
                    // PsiInternalEJSL.g:6518:5: (lv_type_8_0= RULE_POSITION_TYPES_NAMES )
                    {
                    // PsiInternalEJSL.g:6518:5: (lv_type_8_0= RULE_POSITION_TYPES_NAMES )
                    // PsiInternalEJSL.g:6519:6: lv_type_8_0= RULE_POSITION_TYPES_NAMES
                    {

                    						markLeaf(elementTypeProvider.getPositionParameter_TypePOSITION_TYPES_NAMESTerminalRuleCall_4_2_0ElementType());
                    					
                    lv_type_8_0=(Token)match(input,RULE_POSITION_TYPES_NAMES,FOLLOW_144); 

                    						if(!current) {
                    							associateWithSemanticElement();
                    							current = true;
                    						}
                    					

                    						doneLeaf(lv_type_8_0);
                    					

                    }


                    }


                    }
                    break;

            }

            // PsiInternalEJSL.g:6535:3: (otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}' )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==120) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // PsiInternalEJSL.g:6536:4: otherlv_9= 'CSS keyvaluepairs' otherlv_10= '{' ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )* otherlv_12= '}'
                    {

                    				markLeaf(elementTypeProvider.getPositionParameter_CSSKeyvaluepairsKeyword_5_0ElementType());
                    			
                    otherlv_9=(Token)match(input,120,FOLLOW_4); 

                    				doneLeaf(otherlv_9);
                    			

                    				markLeaf(elementTypeProvider.getPositionParameter_LeftCurlyBracketKeyword_5_1ElementType());
                    			
                    otherlv_10=(Token)match(input,16,FOLLOW_137); 

                    				doneLeaf(otherlv_10);
                    			
                    // PsiInternalEJSL.g:6550:4: ( (lv_keyvaluepairs_11_0= ruleKeyValuePair ) )*
                    loop154:
                    do {
                        int alt154=2;
                        int LA154_0 = input.LA(1);

                        if ( (LA154_0==114) ) {
                            alt154=1;
                        }


                        switch (alt154) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6551:5: (lv_keyvaluepairs_11_0= ruleKeyValuePair )
                    	    {
                    	    // PsiInternalEJSL.g:6551:5: (lv_keyvaluepairs_11_0= ruleKeyValuePair )
                    	    // PsiInternalEJSL.g:6552:6: lv_keyvaluepairs_11_0= ruleKeyValuePair
                    	    {

                    	    						markComposite(elementTypeProvider.getPositionParameter_KeyvaluepairsKeyValuePairParserRuleCall_5_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_137);
                    	    lv_keyvaluepairs_11_0=ruleKeyValuePair();

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


                    				markLeaf(elementTypeProvider.getPositionParameter_RightCurlyBracketKeyword_5_3ElementType());
                    			
                    otherlv_12=(Token)match(input,19,FOLLOW_20); 

                    				doneLeaf(otherlv_12);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getPositionParameter_RightCurlyBracketKeyword_6ElementType());
            		
            otherlv_13=(Token)match(input,19,FOLLOW_2); 

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
    // $ANTLR end "rulePositionParameter"


    // $ANTLR start "entryRuleCssBlock"
    // PsiInternalEJSL.g:6584:1: entryRuleCssBlock returns [Boolean current=false] : iv_ruleCssBlock= ruleCssBlock EOF ;
    public final Boolean entryRuleCssBlock() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleCssBlock = null;


        try {
            // PsiInternalEJSL.g:6584:50: (iv_ruleCssBlock= ruleCssBlock EOF )
            // PsiInternalEJSL.g:6585:2: iv_ruleCssBlock= ruleCssBlock EOF
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
    // PsiInternalEJSL.g:6591:1: ruleCssBlock returns [Boolean current=false] : (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' ) ;
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
            // PsiInternalEJSL.g:6592:1: ( (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' ) )
            // PsiInternalEJSL.g:6593:2: (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' )
            {
            // PsiInternalEJSL.g:6593:2: (otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')' )
            // PsiInternalEJSL.g:6594:3: otherlv_0= 'CssBlock' ( (lv_selector_1_0= RULE_STRING ) ) otherlv_2= '(' (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )? otherlv_7= ')'
            {

            			markLeaf(elementTypeProvider.getCssBlock_CssBlockKeyword_0ElementType());
            		
            otherlv_0=(Token)match(input,121,FOLLOW_3); 

            			doneLeaf(otherlv_0);
            		
            // PsiInternalEJSL.g:6601:3: ( (lv_selector_1_0= RULE_STRING ) )
            // PsiInternalEJSL.g:6602:4: (lv_selector_1_0= RULE_STRING )
            {
            // PsiInternalEJSL.g:6602:4: (lv_selector_1_0= RULE_STRING )
            // PsiInternalEJSL.g:6603:5: lv_selector_1_0= RULE_STRING
            {

            					markLeaf(elementTypeProvider.getCssBlock_SelectorSTRINGTerminalRuleCall_1_0ElementType());
            				
            lv_selector_1_0=(Token)match(input,RULE_STRING,FOLLOW_74); 

            					if(!current) {
            						associateWithSemanticElement();
            						current = true;
            					}
            				

            					doneLeaf(lv_selector_1_0);
            				

            }


            }


            			markLeaf(elementTypeProvider.getCssBlock_LeftParenthesisKeyword_2ElementType());
            		
            otherlv_2=(Token)match(input,65,FOLLOW_145); 

            			doneLeaf(otherlv_2);
            		
            // PsiInternalEJSL.g:6625:3: (otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}' )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==113) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // PsiInternalEJSL.g:6626:4: otherlv_3= 'keyvaluepairs' otherlv_4= '{' ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )* otherlv_6= '}'
                    {

                    				markLeaf(elementTypeProvider.getCssBlock_KeyvaluepairsKeyword_3_0ElementType());
                    			
                    otherlv_3=(Token)match(input,113,FOLLOW_4); 

                    				doneLeaf(otherlv_3);
                    			

                    				markLeaf(elementTypeProvider.getCssBlock_LeftCurlyBracketKeyword_3_1ElementType());
                    			
                    otherlv_4=(Token)match(input,16,FOLLOW_137); 

                    				doneLeaf(otherlv_4);
                    			
                    // PsiInternalEJSL.g:6640:4: ( (lv_keyvaluepairs_5_0= ruleKeyValuePair ) )*
                    loop156:
                    do {
                        int alt156=2;
                        int LA156_0 = input.LA(1);

                        if ( (LA156_0==114) ) {
                            alt156=1;
                        }


                        switch (alt156) {
                    	case 1 :
                    	    // PsiInternalEJSL.g:6641:5: (lv_keyvaluepairs_5_0= ruleKeyValuePair )
                    	    {
                    	    // PsiInternalEJSL.g:6641:5: (lv_keyvaluepairs_5_0= ruleKeyValuePair )
                    	    // PsiInternalEJSL.g:6642:6: lv_keyvaluepairs_5_0= ruleKeyValuePair
                    	    {

                    	    						markComposite(elementTypeProvider.getCssBlock_KeyvaluepairsKeyValuePairParserRuleCall_3_2_0ElementType());
                    	    					
                    	    pushFollow(FOLLOW_137);
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
                    	    break loop156;
                        }
                    } while (true);


                    				markLeaf(elementTypeProvider.getCssBlock_RightCurlyBracketKeyword_3_3ElementType());
                    			
                    otherlv_6=(Token)match(input,19,FOLLOW_146); 

                    				doneLeaf(otherlv_6);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getCssBlock_RightParenthesisKeyword_4ElementType());
            		
            otherlv_7=(Token)match(input,66,FOLLOW_2); 

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
    // PsiInternalEJSL.g:6674:1: entryRuleNUMBER returns [Boolean current=false] : iv_ruleNUMBER= ruleNUMBER EOF ;
    public final Boolean entryRuleNUMBER() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleNUMBER = null;


        try {
            // PsiInternalEJSL.g:6674:48: (iv_ruleNUMBER= ruleNUMBER EOF )
            // PsiInternalEJSL.g:6675:2: iv_ruleNUMBER= ruleNUMBER EOF
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
    // PsiInternalEJSL.g:6681:1: ruleNUMBER returns [Boolean current=false] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final Boolean ruleNUMBER() throws RecognitionException {
        Boolean current = false;

        Token kw=null;
        Token this_INT_1=null;

        try {
            // PsiInternalEJSL.g:6682:1: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // PsiInternalEJSL.g:6683:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // PsiInternalEJSL.g:6683:2: ( (kw= '-' )? this_INT_1= RULE_INT )
            // PsiInternalEJSL.g:6684:3: (kw= '-' )? this_INT_1= RULE_INT
            {
            // PsiInternalEJSL.g:6684:3: (kw= '-' )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==122) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // PsiInternalEJSL.g:6685:4: kw= '-'
                    {

                    				markLeaf(elementTypeProvider.getNUMBER_HyphenMinusKeyword_0ElementType());
                    			
                    kw=(Token)match(input,122,FOLLOW_31); 

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
    // PsiInternalEJSL.g:6704:1: entryRuleQualifiedName returns [Boolean current=false] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final Boolean entryRuleQualifiedName() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleQualifiedName = null;


        try {
            // PsiInternalEJSL.g:6704:55: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // PsiInternalEJSL.g:6705:2: iv_ruleQualifiedName= ruleQualifiedName EOF
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
    // PsiInternalEJSL.g:6711:1: ruleQualifiedName returns [Boolean current=false] : ( ruleMYID (kw= '.' ruleMYID )* ) ;
    public final Boolean ruleQualifiedName() throws RecognitionException {
        Boolean current = false;

        Token kw=null;

        try {
            // PsiInternalEJSL.g:6712:1: ( ( ruleMYID (kw= '.' ruleMYID )* ) )
            // PsiInternalEJSL.g:6713:2: ( ruleMYID (kw= '.' ruleMYID )* )
            {
            // PsiInternalEJSL.g:6713:2: ( ruleMYID (kw= '.' ruleMYID )* )
            // PsiInternalEJSL.g:6714:3: ruleMYID (kw= '.' ruleMYID )*
            {

            			markComposite(elementTypeProvider.getQualifiedName_MYIDParserRuleCall_0ElementType());
            		
            pushFollow(FOLLOW_147);
            ruleMYID();

            state._fsp--;


            			doneComposite();
            		
            // PsiInternalEJSL.g:6721:3: (kw= '.' ruleMYID )*
            loop159:
            do {
                int alt159=2;
                int LA159_0 = input.LA(1);

                if ( (LA159_0==123) ) {
                    alt159=1;
                }


                switch (alt159) {
            	case 1 :
            	    // PsiInternalEJSL.g:6722:4: kw= '.' ruleMYID
            	    {

            	    				markLeaf(elementTypeProvider.getQualifiedName_FullStopKeyword_1_0ElementType());
            	    			
            	    kw=(Token)match(input,123,FOLLOW_40); 

            	    				doneLeaf(kw);
            	    			

            	    				markComposite(elementTypeProvider.getQualifiedName_MYIDParserRuleCall_1_1ElementType());
            	    			
            	    pushFollow(FOLLOW_147);
            	    ruleMYID();

            	    state._fsp--;


            	    				doneComposite();
            	    			

            	    }
            	    break;

            	default :
            	    break loop159;
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
    // PsiInternalEJSL.g:6741:1: entryRuleMYID returns [Boolean current=false] : iv_ruleMYID= ruleMYID EOF ;
    public final Boolean entryRuleMYID() throws RecognitionException {
        Boolean current = false;

        Boolean iv_ruleMYID = null;


        try {
            // PsiInternalEJSL.g:6741:46: (iv_ruleMYID= ruleMYID EOF )
            // PsiInternalEJSL.g:6742:2: iv_ruleMYID= ruleMYID EOF
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
    // PsiInternalEJSL.g:6748:1: ruleMYID returns [Boolean current=false] : ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? ) ;
    public final Boolean ruleMYID() throws RecognitionException {
        Boolean current = false;

        Token kw=null;
        Token this_ID_1=null;

        try {
            // PsiInternalEJSL.g:6749:1: ( ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? ) )
            // PsiInternalEJSL.g:6750:2: ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? )
            {
            // PsiInternalEJSL.g:6750:2: ( (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )? )
            // PsiInternalEJSL.g:6751:3: (kw= '<' )? this_ID_1= RULE_ID (kw= '>' )?
            {
            // PsiInternalEJSL.g:6751:3: (kw= '<' )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==124) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // PsiInternalEJSL.g:6752:4: kw= '<'
                    {

                    				markLeaf(elementTypeProvider.getMYID_LessThanSignKeyword_0ElementType());
                    			
                    kw=(Token)match(input,124,FOLLOW_25); 

                    				doneLeaf(kw);
                    			

                    }
                    break;

            }


            			markLeaf(elementTypeProvider.getMYID_IDTerminalRuleCall_1ElementType());
            		
            this_ID_1=(Token)match(input,RULE_ID,FOLLOW_148); 

            			doneLeaf(this_ID_1);
            		
            // PsiInternalEJSL.g:6767:3: (kw= '>' )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==125) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // PsiInternalEJSL.g:6768:4: kw= '>'
                    {

                    				markLeaf(elementTypeProvider.getMYID_GreaterThanSignKeyword_2ElementType());
                    			
                    kw=(Token)match(input,125,FOLLOW_2); 

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
    // PsiInternalEJSL.g:6780:1: rulePluginKinds returns [Boolean current=false] : ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) ) ;
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
            // PsiInternalEJSL.g:6781:1: ( ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) ) )
            // PsiInternalEJSL.g:6782:2: ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) )
            {
            // PsiInternalEJSL.g:6782:2: ( (enumLiteral_0= 'authenticate' ) | (enumLiteral_1= 'captcha' ) | (enumLiteral_2= 'content' ) | (enumLiteral_3= 'contact' ) | (enumLiteral_4= 'editors' ) | (enumLiteral_5= 'extensions' ) | (enumLiteral_6= 'finder' ) | (enumLiteral_7= 'quick_icons' ) | (enumLiteral_8= 'search' ) | (enumLiteral_9= 'system' ) | (enumLiteral_10= 'user' ) | (enumLiteral_11= 'xml_rpc' ) )
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
                    // PsiInternalEJSL.g:6783:3: (enumLiteral_0= 'authenticate' )
                    {
                    // PsiInternalEJSL.g:6783:3: (enumLiteral_0= 'authenticate' )
                    // PsiInternalEJSL.g:6784:4: enumLiteral_0= 'authenticate'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_AuthenticateEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,126,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:6793:3: (enumLiteral_1= 'captcha' )
                    {
                    // PsiInternalEJSL.g:6793:3: (enumLiteral_1= 'captcha' )
                    // PsiInternalEJSL.g:6794:4: enumLiteral_1= 'captcha'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_CaptchaEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,127,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:6803:3: (enumLiteral_2= 'content' )
                    {
                    // PsiInternalEJSL.g:6803:3: (enumLiteral_2= 'content' )
                    // PsiInternalEJSL.g:6804:4: enumLiteral_2= 'content'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_ContentEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,128,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;
                case 4 :
                    // PsiInternalEJSL.g:6813:3: (enumLiteral_3= 'contact' )
                    {
                    // PsiInternalEJSL.g:6813:3: (enumLiteral_3= 'contact' )
                    // PsiInternalEJSL.g:6814:4: enumLiteral_3= 'contact'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_ContactEnumLiteralDeclaration_3ElementType());
                    			
                    enumLiteral_3=(Token)match(input,129,FOLLOW_2); 

                    				doneLeaf(enumLiteral_3);
                    			

                    }


                    }
                    break;
                case 5 :
                    // PsiInternalEJSL.g:6823:3: (enumLiteral_4= 'editors' )
                    {
                    // PsiInternalEJSL.g:6823:3: (enumLiteral_4= 'editors' )
                    // PsiInternalEJSL.g:6824:4: enumLiteral_4= 'editors'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_EditorsEnumLiteralDeclaration_4ElementType());
                    			
                    enumLiteral_4=(Token)match(input,130,FOLLOW_2); 

                    				doneLeaf(enumLiteral_4);
                    			

                    }


                    }
                    break;
                case 6 :
                    // PsiInternalEJSL.g:6833:3: (enumLiteral_5= 'extensions' )
                    {
                    // PsiInternalEJSL.g:6833:3: (enumLiteral_5= 'extensions' )
                    // PsiInternalEJSL.g:6834:4: enumLiteral_5= 'extensions'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_ExtensionsEnumLiteralDeclaration_5ElementType());
                    			
                    enumLiteral_5=(Token)match(input,25,FOLLOW_2); 

                    				doneLeaf(enumLiteral_5);
                    			

                    }


                    }
                    break;
                case 7 :
                    // PsiInternalEJSL.g:6843:3: (enumLiteral_6= 'finder' )
                    {
                    // PsiInternalEJSL.g:6843:3: (enumLiteral_6= 'finder' )
                    // PsiInternalEJSL.g:6844:4: enumLiteral_6= 'finder'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_FinderEnumLiteralDeclaration_6ElementType());
                    			
                    enumLiteral_6=(Token)match(input,131,FOLLOW_2); 

                    				doneLeaf(enumLiteral_6);
                    			

                    }


                    }
                    break;
                case 8 :
                    // PsiInternalEJSL.g:6853:3: (enumLiteral_7= 'quick_icons' )
                    {
                    // PsiInternalEJSL.g:6853:3: (enumLiteral_7= 'quick_icons' )
                    // PsiInternalEJSL.g:6854:4: enumLiteral_7= 'quick_icons'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_Quick_iconsEnumLiteralDeclaration_7ElementType());
                    			
                    enumLiteral_7=(Token)match(input,132,FOLLOW_2); 

                    				doneLeaf(enumLiteral_7);
                    			

                    }


                    }
                    break;
                case 9 :
                    // PsiInternalEJSL.g:6863:3: (enumLiteral_8= 'search' )
                    {
                    // PsiInternalEJSL.g:6863:3: (enumLiteral_8= 'search' )
                    // PsiInternalEJSL.g:6864:4: enumLiteral_8= 'search'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_SearchEnumLiteralDeclaration_8ElementType());
                    			
                    enumLiteral_8=(Token)match(input,133,FOLLOW_2); 

                    				doneLeaf(enumLiteral_8);
                    			

                    }


                    }
                    break;
                case 10 :
                    // PsiInternalEJSL.g:6873:3: (enumLiteral_9= 'system' )
                    {
                    // PsiInternalEJSL.g:6873:3: (enumLiteral_9= 'system' )
                    // PsiInternalEJSL.g:6874:4: enumLiteral_9= 'system'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_SystemEnumLiteralDeclaration_9ElementType());
                    			
                    enumLiteral_9=(Token)match(input,134,FOLLOW_2); 

                    				doneLeaf(enumLiteral_9);
                    			

                    }


                    }
                    break;
                case 11 :
                    // PsiInternalEJSL.g:6883:3: (enumLiteral_10= 'user' )
                    {
                    // PsiInternalEJSL.g:6883:3: (enumLiteral_10= 'user' )
                    // PsiInternalEJSL.g:6884:4: enumLiteral_10= 'user'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_UserEnumLiteralDeclaration_10ElementType());
                    			
                    enumLiteral_10=(Token)match(input,135,FOLLOW_2); 

                    				doneLeaf(enumLiteral_10);
                    			

                    }


                    }
                    break;
                case 12 :
                    // PsiInternalEJSL.g:6893:3: (enumLiteral_11= 'xml_rpc' )
                    {
                    // PsiInternalEJSL.g:6893:3: (enumLiteral_11= 'xml_rpc' )
                    // PsiInternalEJSL.g:6894:4: enumLiteral_11= 'xml_rpc'
                    {

                    				markLeaf(elementTypeProvider.getPluginKinds_Xml_rpcEnumLiteralDeclaration_11ElementType());
                    			
                    enumLiteral_11=(Token)match(input,136,FOLLOW_2); 

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


    // $ANTLR start "ruleStandardTypeKinds"
    // PsiInternalEJSL.g:6906:1: ruleStandardTypeKinds returns [Boolean current=false] : ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) ) ;
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
            // PsiInternalEJSL.g:6907:1: ( ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) ) )
            // PsiInternalEJSL.g:6908:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) )
            {
            // PsiInternalEJSL.g:6908:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Boolean' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Textfield' ) | (enumLiteral_4= 'Time' ) | (enumLiteral_5= 'Date' ) | (enumLiteral_6= 'Datetime' ) | (enumLiteral_7= 'Link' ) | (enumLiteral_8= 'Image' ) | (enumLiteral_9= 'File' ) | (enumLiteral_10= 'Label' ) )
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
                    // PsiInternalEJSL.g:6909:3: (enumLiteral_0= 'Integer' )
                    {
                    // PsiInternalEJSL.g:6909:3: (enumLiteral_0= 'Integer' )
                    // PsiInternalEJSL.g:6910:4: enumLiteral_0= 'Integer'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_IntegerEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,137,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:6919:3: (enumLiteral_1= 'Boolean' )
                    {
                    // PsiInternalEJSL.g:6919:3: (enumLiteral_1= 'Boolean' )
                    // PsiInternalEJSL.g:6920:4: enumLiteral_1= 'Boolean'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_BooleanEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,138,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:6929:3: (enumLiteral_2= 'Textarea' )
                    {
                    // PsiInternalEJSL.g:6929:3: (enumLiteral_2= 'Textarea' )
                    // PsiInternalEJSL.g:6930:4: enumLiteral_2= 'Textarea'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_TextareaEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,139,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;
                case 4 :
                    // PsiInternalEJSL.g:6939:3: (enumLiteral_3= 'Textfield' )
                    {
                    // PsiInternalEJSL.g:6939:3: (enumLiteral_3= 'Textfield' )
                    // PsiInternalEJSL.g:6940:4: enumLiteral_3= 'Textfield'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_TextfieldEnumLiteralDeclaration_3ElementType());
                    			
                    enumLiteral_3=(Token)match(input,140,FOLLOW_2); 

                    				doneLeaf(enumLiteral_3);
                    			

                    }


                    }
                    break;
                case 5 :
                    // PsiInternalEJSL.g:6949:3: (enumLiteral_4= 'Time' )
                    {
                    // PsiInternalEJSL.g:6949:3: (enumLiteral_4= 'Time' )
                    // PsiInternalEJSL.g:6950:4: enumLiteral_4= 'Time'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_TimeEnumLiteralDeclaration_4ElementType());
                    			
                    enumLiteral_4=(Token)match(input,141,FOLLOW_2); 

                    				doneLeaf(enumLiteral_4);
                    			

                    }


                    }
                    break;
                case 6 :
                    // PsiInternalEJSL.g:6959:3: (enumLiteral_5= 'Date' )
                    {
                    // PsiInternalEJSL.g:6959:3: (enumLiteral_5= 'Date' )
                    // PsiInternalEJSL.g:6960:4: enumLiteral_5= 'Date'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_DateEnumLiteralDeclaration_5ElementType());
                    			
                    enumLiteral_5=(Token)match(input,142,FOLLOW_2); 

                    				doneLeaf(enumLiteral_5);
                    			

                    }


                    }
                    break;
                case 7 :
                    // PsiInternalEJSL.g:6969:3: (enumLiteral_6= 'Datetime' )
                    {
                    // PsiInternalEJSL.g:6969:3: (enumLiteral_6= 'Datetime' )
                    // PsiInternalEJSL.g:6970:4: enumLiteral_6= 'Datetime'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_DatetimeEnumLiteralDeclaration_6ElementType());
                    			
                    enumLiteral_6=(Token)match(input,143,FOLLOW_2); 

                    				doneLeaf(enumLiteral_6);
                    			

                    }


                    }
                    break;
                case 8 :
                    // PsiInternalEJSL.g:6979:3: (enumLiteral_7= 'Link' )
                    {
                    // PsiInternalEJSL.g:6979:3: (enumLiteral_7= 'Link' )
                    // PsiInternalEJSL.g:6980:4: enumLiteral_7= 'Link'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_LinkEnumLiteralDeclaration_7ElementType());
                    			
                    enumLiteral_7=(Token)match(input,144,FOLLOW_2); 

                    				doneLeaf(enumLiteral_7);
                    			

                    }


                    }
                    break;
                case 9 :
                    // PsiInternalEJSL.g:6989:3: (enumLiteral_8= 'Image' )
                    {
                    // PsiInternalEJSL.g:6989:3: (enumLiteral_8= 'Image' )
                    // PsiInternalEJSL.g:6990:4: enumLiteral_8= 'Image'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_ImageEnumLiteralDeclaration_8ElementType());
                    			
                    enumLiteral_8=(Token)match(input,145,FOLLOW_2); 

                    				doneLeaf(enumLiteral_8);
                    			

                    }


                    }
                    break;
                case 10 :
                    // PsiInternalEJSL.g:6999:3: (enumLiteral_9= 'File' )
                    {
                    // PsiInternalEJSL.g:6999:3: (enumLiteral_9= 'File' )
                    // PsiInternalEJSL.g:7000:4: enumLiteral_9= 'File'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_FileEnumLiteralDeclaration_9ElementType());
                    			
                    enumLiteral_9=(Token)match(input,146,FOLLOW_2); 

                    				doneLeaf(enumLiteral_9);
                    			

                    }


                    }
                    break;
                case 11 :
                    // PsiInternalEJSL.g:7009:3: (enumLiteral_10= 'Label' )
                    {
                    // PsiInternalEJSL.g:7009:3: (enumLiteral_10= 'Label' )
                    // PsiInternalEJSL.g:7010:4: enumLiteral_10= 'Label'
                    {

                    				markLeaf(elementTypeProvider.getStandardTypeKinds_LabelEnumLiteralDeclaration_10ElementType());
                    			
                    enumLiteral_10=(Token)match(input,147,FOLLOW_2); 

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


    // $ANTLR start "ruleSectionReference"
    // PsiInternalEJSL.g:7022:1: ruleSectionReference returns [Boolean current=false] : ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) ) ;
    public final Boolean ruleSectionReference() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

        try {
            // PsiInternalEJSL.g:7023:1: ( ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) ) )
            // PsiInternalEJSL.g:7024:2: ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) )
            {
            // PsiInternalEJSL.g:7024:2: ( (enumLiteral_0= '.backend' ) | (enumLiteral_1= '.frontend' ) )
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
                    // PsiInternalEJSL.g:7025:3: (enumLiteral_0= '.backend' )
                    {
                    // PsiInternalEJSL.g:7025:3: (enumLiteral_0= '.backend' )
                    // PsiInternalEJSL.g:7026:4: enumLiteral_0= '.backend'
                    {

                    				markLeaf(elementTypeProvider.getSectionReference_BackendEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,148,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7035:3: (enumLiteral_1= '.frontend' )
                    {
                    // PsiInternalEJSL.g:7035:3: (enumLiteral_1= '.frontend' )
                    // PsiInternalEJSL.g:7036:4: enumLiteral_1= '.frontend'
                    {

                    				markLeaf(elementTypeProvider.getSectionReference_FrontendEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,149,FOLLOW_2); 

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
    // $ANTLR end "ruleSectionReference"


    // $ANTLR start "ruleSimpleHTMLTypeKinds"
    // PsiInternalEJSL.g:7048:1: ruleSimpleHTMLTypeKinds returns [Boolean current=false] : ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) ) ;
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
            // PsiInternalEJSL.g:7049:1: ( ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) ) )
            // PsiInternalEJSL.g:7050:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) )
            {
            // PsiInternalEJSL.g:7050:2: ( (enumLiteral_0= 'Integer' ) | (enumLiteral_1= 'Yes_No_Buttons' ) | (enumLiteral_2= 'Textarea' ) | (enumLiteral_3= 'Text_Field' ) | (enumLiteral_4= 'Datepicker' ) | (enumLiteral_5= 'Imagepicker' ) | (enumLiteral_6= 'Filepicker' ) | (enumLiteral_7= 'Text_Field_NE' ) | (enumLiteral_8= 'Editor' ) )
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
                    // PsiInternalEJSL.g:7051:3: (enumLiteral_0= 'Integer' )
                    {
                    // PsiInternalEJSL.g:7051:3: (enumLiteral_0= 'Integer' )
                    // PsiInternalEJSL.g:7052:4: enumLiteral_0= 'Integer'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_IntegerEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,137,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7061:3: (enumLiteral_1= 'Yes_No_Buttons' )
                    {
                    // PsiInternalEJSL.g:7061:3: (enumLiteral_1= 'Yes_No_Buttons' )
                    // PsiInternalEJSL.g:7062:4: enumLiteral_1= 'Yes_No_Buttons'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_Yes_No_ButtonsEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,150,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:7071:3: (enumLiteral_2= 'Textarea' )
                    {
                    // PsiInternalEJSL.g:7071:3: (enumLiteral_2= 'Textarea' )
                    // PsiInternalEJSL.g:7072:4: enumLiteral_2= 'Textarea'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_TextareaEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,139,FOLLOW_2); 

                    				doneLeaf(enumLiteral_2);
                    			

                    }


                    }
                    break;
                case 4 :
                    // PsiInternalEJSL.g:7081:3: (enumLiteral_3= 'Text_Field' )
                    {
                    // PsiInternalEJSL.g:7081:3: (enumLiteral_3= 'Text_Field' )
                    // PsiInternalEJSL.g:7082:4: enumLiteral_3= 'Text_Field'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_Text_FieldEnumLiteralDeclaration_3ElementType());
                    			
                    enumLiteral_3=(Token)match(input,151,FOLLOW_2); 

                    				doneLeaf(enumLiteral_3);
                    			

                    }


                    }
                    break;
                case 5 :
                    // PsiInternalEJSL.g:7091:3: (enumLiteral_4= 'Datepicker' )
                    {
                    // PsiInternalEJSL.g:7091:3: (enumLiteral_4= 'Datepicker' )
                    // PsiInternalEJSL.g:7092:4: enumLiteral_4= 'Datepicker'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_DatepickerEnumLiteralDeclaration_4ElementType());
                    			
                    enumLiteral_4=(Token)match(input,152,FOLLOW_2); 

                    				doneLeaf(enumLiteral_4);
                    			

                    }


                    }
                    break;
                case 6 :
                    // PsiInternalEJSL.g:7101:3: (enumLiteral_5= 'Imagepicker' )
                    {
                    // PsiInternalEJSL.g:7101:3: (enumLiteral_5= 'Imagepicker' )
                    // PsiInternalEJSL.g:7102:4: enumLiteral_5= 'Imagepicker'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_ImagepickerEnumLiteralDeclaration_5ElementType());
                    			
                    enumLiteral_5=(Token)match(input,153,FOLLOW_2); 

                    				doneLeaf(enumLiteral_5);
                    			

                    }


                    }
                    break;
                case 7 :
                    // PsiInternalEJSL.g:7111:3: (enumLiteral_6= 'Filepicker' )
                    {
                    // PsiInternalEJSL.g:7111:3: (enumLiteral_6= 'Filepicker' )
                    // PsiInternalEJSL.g:7112:4: enumLiteral_6= 'Filepicker'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_FilepickerEnumLiteralDeclaration_6ElementType());
                    			
                    enumLiteral_6=(Token)match(input,154,FOLLOW_2); 

                    				doneLeaf(enumLiteral_6);
                    			

                    }


                    }
                    break;
                case 8 :
                    // PsiInternalEJSL.g:7121:3: (enumLiteral_7= 'Text_Field_NE' )
                    {
                    // PsiInternalEJSL.g:7121:3: (enumLiteral_7= 'Text_Field_NE' )
                    // PsiInternalEJSL.g:7122:4: enumLiteral_7= 'Text_Field_NE'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_Text_Field_NEEnumLiteralDeclaration_7ElementType());
                    			
                    enumLiteral_7=(Token)match(input,155,FOLLOW_2); 

                    				doneLeaf(enumLiteral_7);
                    			

                    }


                    }
                    break;
                case 9 :
                    // PsiInternalEJSL.g:7131:3: (enumLiteral_8= 'Editor' )
                    {
                    // PsiInternalEJSL.g:7131:3: (enumLiteral_8= 'Editor' )
                    // PsiInternalEJSL.g:7132:4: enumLiteral_8= 'Editor'
                    {

                    				markLeaf(elementTypeProvider.getSimpleHTMLTypeKinds_EditorEnumLiteralDeclaration_8ElementType());
                    			
                    enumLiteral_8=(Token)match(input,156,FOLLOW_2); 

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
    // PsiInternalEJSL.g:7144:1: ruleComplexHTMLTypeKinds returns [Boolean current=false] : ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) ) ;
    public final Boolean ruleComplexHTMLTypeKinds() throws RecognitionException {
        Boolean current = false;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

        try {
            // PsiInternalEJSL.g:7145:1: ( ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) ) )
            // PsiInternalEJSL.g:7146:2: ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) )
            {
            // PsiInternalEJSL.g:7146:2: ( (enumLiteral_0= 'Multiselect' ) | (enumLiteral_1= 'Checkbox' ) | (enumLiteral_2= 'Radiobutto' ) )
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
                    // PsiInternalEJSL.g:7147:3: (enumLiteral_0= 'Multiselect' )
                    {
                    // PsiInternalEJSL.g:7147:3: (enumLiteral_0= 'Multiselect' )
                    // PsiInternalEJSL.g:7148:4: enumLiteral_0= 'Multiselect'
                    {

                    				markLeaf(elementTypeProvider.getComplexHTMLTypeKinds_MultiselectEnumLiteralDeclaration_0ElementType());
                    			
                    enumLiteral_0=(Token)match(input,157,FOLLOW_2); 

                    				doneLeaf(enumLiteral_0);
                    			

                    }


                    }
                    break;
                case 2 :
                    // PsiInternalEJSL.g:7157:3: (enumLiteral_1= 'Checkbox' )
                    {
                    // PsiInternalEJSL.g:7157:3: (enumLiteral_1= 'Checkbox' )
                    // PsiInternalEJSL.g:7158:4: enumLiteral_1= 'Checkbox'
                    {

                    				markLeaf(elementTypeProvider.getComplexHTMLTypeKinds_CheckboxEnumLiteralDeclaration_1ElementType());
                    			
                    enumLiteral_1=(Token)match(input,158,FOLLOW_2); 

                    				doneLeaf(enumLiteral_1);
                    			

                    }


                    }
                    break;
                case 3 :
                    // PsiInternalEJSL.g:7167:3: (enumLiteral_2= 'Radiobutto' )
                    {
                    // PsiInternalEJSL.g:7167:3: (enumLiteral_2= 'Radiobutto' )
                    // PsiInternalEJSL.g:7168:4: enumLiteral_2= 'Radiobutto'
                    {

                    				markLeaf(elementTypeProvider.getComplexHTMLTypeKinds_RadiobuttoEnumLiteralDeclaration_2ElementType());
                    			
                    enumLiteral_2=(Token)match(input,159,FOLLOW_2); 

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