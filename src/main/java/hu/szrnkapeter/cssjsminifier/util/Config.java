package hu.szrnkapeter.cssjsminifier.util;

/**
 * @author Peter Szrnka
 */
public class Config {

	private String cssCompressor;
	private String cssFolder;
	private String cssOut;
	private JSCompileType jsCompileType;
	private String jsCompressor;
	private String jsFolder;
	private String jsOut;

	public String getCssCompressor() {
		return cssCompressor;
	}

	public String getCssFolder() {
		return cssFolder;
	}

	public String getCssOut() {
		return cssOut;
	}

	public JSCompileType getJsCompileType() {
		return jsCompileType;
	}

	public String getJsCompressor() {
		return jsCompressor;
	}

	public String getJsFolder() {
		return jsFolder;
	}

	public String getJsOut() {
		return jsOut;
	}

	public void setCssCompressor(final String cssCompressor) {
		this.cssCompressor = cssCompressor;
	}

	public void setCssFolder(final String cssFolder) {
		this.cssFolder = cssFolder;
	}

	public void setCssOut(final String cssOut) {
		this.cssOut = cssOut;
	}

	public void setJsCompileType(final JSCompileType jsCompileType) {
		this.jsCompileType = jsCompileType;
	}

	public void setJsCompressor(final String jsCompressor) {
		this.jsCompressor = jsCompressor;
	}

	public void setJsFolder(final String jsFolder) {
		this.jsFolder = jsFolder;
	}

	public void setJsOut(final String jsOut) {
		this.jsOut = jsOut;
	}
}