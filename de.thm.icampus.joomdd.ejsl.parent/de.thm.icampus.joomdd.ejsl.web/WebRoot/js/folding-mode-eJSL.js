define(["ace/lib/oop", "xtext-resources/generated/mode-eJSL", "ace/mode/folding/cstyle"], function(oop, eJSLMode, cStyleMode) {
	var FoldMode = cStyleMode.FoldMode;
    var eJSLModeHighlightRules = (new eJSLMode.Mode).HighlightRules;
    	
	var Mode = function() {
		this.HighlightRules = eJSLModeHighlightRules;
		this.foldingRules = new FoldMode();
	};
	oop.inherits(Mode, eJSLMode.Mode);
	
	return {
		Mode: Mode
	};
});
