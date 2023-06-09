package hu.szrnkapeter.cssjsminifier.compressor.js;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.javascript.jscomp.AbstractCommandLineRunner;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerOptions.LanguageMode;
import com.google.javascript.jscomp.SourceFile;

import hu.szrnkapeter.cssjsminifier.util.JSCompileType;

/**
 * @author Peter Szrnka
 */
public class GoogleClosureCompilerJSCompressor implements JSCompressor {
	
	private static final Logger LOGGER = Logger.getLogger(GoogleClosureCompilerJSCompressor.class.getName());

	@Override
	public String compress(final String inputFilename, final JSCompileType compileType) {
		final Compiler compiler = new Compiler();
		final CompilerOptions options = new CompilerOptions();
		options.setLanguageIn(LanguageMode.ECMASCRIPT6);
		options.setLanguageOut(LanguageMode.ECMASCRIPT6);

		compileType.getLevel().setOptionsForCompilationLevel(options);

		List<SourceFile> list = new ArrayList<>();

		try {
			list = AbstractCommandLineRunner.getBuiltinExterns(options);
		} catch (final IOException e) {
			LOGGER.severe(e::getMessage);
		}

		list.add(SourceFile.fromFile(new File(inputFilename)));
		compiler.compile(new ArrayList<SourceFile>(), list, options);

		return compiler.toSource();
	}

}
