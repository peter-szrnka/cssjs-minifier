package hu.szrnkapeter.cssjsminifier.compressor.js;

import java.util.logging.Logger;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

/**
 * @author Peter Szrnka
 */
public class YuiCompressorErrorReporter implements ErrorReporter {

	private static final Logger LOGGER = Logger.getLogger(YuiCompressorErrorReporter.class.getName());

	@Override
	public void error(final String message, final String sourceName, final int line, final String lineSource,
			final int lineOffset) {
		if (line < 0) {
			LOGGER.severe(message);
		} else {
			LOGGER.severe(() -> String.format("%d: %d: %s", line, lineOffset, message));
		}
	}

	@Override
	public EvaluatorException runtimeError(final String message, final String sourceName, final int line,
			final String lineSource, final int lineOffset) {
		error(message, sourceName, line, lineSource, lineOffset);
		return new EvaluatorException(message);
	}

	@Override
	public void warning(final String message, final String sourceName, final int line, final String lineSource,
			final int lineOffset) {
		if (line < 0) {
			LOGGER.warning(message);
		} else {
			LOGGER.warning(() -> String.format("%d: %d: %s", line, lineOffset, message));
		}
	}
}