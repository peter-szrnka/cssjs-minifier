package hu.szrnkapeter.cssjsminifier.util;

import com.google.javascript.jscomp.CompilationLevel;

/**
 * @author Peter Szrnka
 */
public enum JSCompileType {
	ADVANCED(CompilationLevel.ADVANCED_OPTIMIZATIONS),
	SIMPLE(CompilationLevel.SIMPLE_OPTIMIZATIONS),
	WHITESPACE(CompilationLevel.WHITESPACE_ONLY);
	
	private JSCompileType(CompilationLevel level) {
		this.level = level;
	}
	
	private CompilationLevel level;
	
	public CompilationLevel getLevel() {
		return level;
	}
}