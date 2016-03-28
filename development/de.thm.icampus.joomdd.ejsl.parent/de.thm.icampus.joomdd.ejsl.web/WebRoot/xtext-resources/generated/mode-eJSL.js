define(["ace/lib/oop", "ace/mode/text", "ace/mode/text_highlight_rules"], function(oop, mText, mTextHighlightRules) {
	var HighlightRules = function() {
		var keywords = "ARCHIVE|Attribute|Author|Boolean|CANCEL|CHECKIN|CLOSE|Checkbox|Class|Component|CssBlock|CustomPage|Datatype|Date|Datepicker|Datetime|DetailsPage|EDIT|Editor|Entity|Entitypackage|ExternalLink|File|Filepicker|HIDE|HTMLBody|ID|INDIVIDUAL|Image|Imagepicker|IndexPage|Integer|InternalLink|InternalcontextLink|Key|Label|Language|Library|Link|Manifestation|Method|MethodParameter|Module|Multiselect|NEW|PUBLISH|Package|PageAction|Parameter|ParameterGroup|Parameters|Plugin|Radiobutton|Reference|Returnvalue|SAVE|SAVE_CLOSE|SAVE_COPY|Select|StaticPage|TRASH|Template|Templateposition|Text_Field|Text_Field_NE|Textarea|Textfield|Time|UNPUBLISH|Yes_No_Buttons|attributes|authenticate|authors|bottom|captcha|center|classes|contact|content|coreFeature|cssblocks|custom|datatypes|details|eJSLModel|editFields|editors|entities|entitypackages|extends|extensions|finder|globalparameters|keyvaluepairs|label|languages|linkparameters|links|list|localparameters|methodparameters|methods|packages|pageactions|pages|parametergroups|parameters|positionparameters|positions|quick_icons|references|search|sections|system|top|user|with|xml_rpc";
		this.$rules = {
			"start": [
				{token: "comment", regex: "\\/\\/.*$"},
				{token: "comment", regex: "\\/\\*", next : "comment"},
				{token: "string", regex: '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'},
				{token: "string", regex: "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"},
				{token: "constant.numeric", regex: "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"},
				{token: "lparen", regex: "[({]"},
				{token: "rparen", regex: "[)}]"},
				{token: "keyword", regex: "\\b(?:" + keywords + ")\\b"}
			],
			"comment": [
				{token: "comment", regex: ".*?\\*\\/", next : "start"},
				{token: "comment", regex: ".+"}
			]
		};
	};
	oop.inherits(HighlightRules, mTextHighlightRules.TextHighlightRules);
	
	var Mode = function() {
		this.HighlightRules = HighlightRules;
	};
	oop.inherits(Mode, mText.Mode);
	Mode.prototype.$id = "xtext/eJSL";
	Mode.prototype.getCompletions = function(state, session, pos, prefix) {
		return [];
	}
	
	return {
		Mode: Mode
	};
});
