define(["ace/lib/oop", "ace/mode/text", "ace/mode/text_highlight_rules"], function(oop, mText, mTextHighlightRules) {
	var HighlightRules = function() {
		var keywords = "Attribute|Author|Boolean|Checkbox|Class|Component|CssBlock|Datapackage|Datatype|Date|Datepicker|Datetime|Default|DetailsPage|Editor|Entity|ExternalLink|File|Filepicker|HTMLBody|ID|Image|Imagepicker|IndexPage|Integer|InternalLink|InternalcontextLink|Key|Label|Language|Library|Link|Manifestation|Method|MethodParameter|Module|Multiselect|Package|Parameter|ParameterGroup|Parameters|Plugin|Plugintype|Positiontype|Radiobutto|Reference|Returnvalue|StaticPage|Template|Templateposition|Text_Field|Text_Field_NE|Textarea|Textfield|Time|Yes_No_Buttons|attributes|authenticate|authoremail|authors|authorurl|captcha|classes|contact|content|copyright|creationdate|cssblocks|datapackages|datatypes|defaultvalue|description|divId|eJSLModel|edit_fields|editors|entities|extends|extensions|filters|finder|from|globalparameters|keyvaluepairs|label|languages|license|link|linkparameters|links|localparameters|lower|methodparameters|methods|packages|pages|parametergroups|parameters|positionparameters|positions|quick_icons|references|search|sections|size|system|target|type|upper|user|version|with|xml_rpc";
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
