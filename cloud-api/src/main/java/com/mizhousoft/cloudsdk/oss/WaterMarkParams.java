package com.mizhousoft.cloudsdk.oss;

/**
 * 水印参数
 *
 * @version
 */
public class WaterMarkParams
{
	// 水印内容
	private String text;

	// 水印文字字体大小，单位为磅，缺省值13
	private int fontSize;

	// 字体颜色，缺省为灰色，需设置为十六进制 RGB 格式（例如 #FF0000），需经过 URL 安全的 Base64 编码
	private String fill;

	// 文字透明度，取值1 - 100
	private int dissolve;

	// 文字水印位置，九宫格位置（参见九宫格方位图）
	private String gravity;

	// 水平（横轴）边距，单位为像素，缺省值为0
	private int dx;

	// 垂直（纵轴）边距，单位为像素，缺省值为0
	private int dy;

	// 平铺模式下的水平、垂直间距相对文字水印贴图的宽高百分比，范围为[0,100]，默认10
	private int spacing;

	// 平铺水印功能，可将文字水印平铺至整张图片。值为1时，表示开启平铺水印功能
	private int batch;

	// 当 batch 值为1时生效。文字水印的旋转角度设置，取值范围为0 - 360，默认0
	private int degree;

	// 文字阴影效果，有效值为[0,100]，默认为0，表示无阴影
	private int shadow;

	/**
	 * 获取text
	 * 
	 * @return
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * 设置text
	 * 
	 * @param text
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * 获取fontSize
	 * 
	 * @return
	 */
	public int getFontSize()
	{
		return fontSize;
	}

	/**
	 * 设置fontSize
	 * 
	 * @param fontSize
	 */
	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
	}

	/**
	 * 获取fill
	 * 
	 * @return
	 */
	public String getFill()
	{
		return fill;
	}

	/**
	 * 设置fill
	 * 
	 * @param fill
	 */
	public void setFill(String fill)
	{
		this.fill = fill;
	}

	/**
	 * 获取dissolve
	 * 
	 * @return
	 */
	public int getDissolve()
	{
		return dissolve;
	}

	/**
	 * 设置dissolve
	 * 
	 * @param dissolve
	 */
	public void setDissolve(int dissolve)
	{
		this.dissolve = dissolve;
	}

	/**
	 * 获取gravity
	 * 
	 * @return
	 */
	public String getGravity()
	{
		return gravity;
	}

	/**
	 * 设置gravity
	 * 
	 * @param gravity
	 */
	public void setGravity(String gravity)
	{
		this.gravity = gravity;
	}

	/**
	 * 获取dx
	 * 
	 * @return
	 */
	public int getDx()
	{
		return dx;
	}

	/**
	 * 设置dx
	 * 
	 * @param dx
	 */
	public void setDx(int dx)
	{
		this.dx = dx;
	}

	/**
	 * 获取dy
	 * 
	 * @return
	 */
	public int getDy()
	{
		return dy;
	}

	/**
	 * 设置dy
	 * 
	 * @param dy
	 */
	public void setDy(int dy)
	{
		this.dy = dy;
	}

	/**
	 * 获取spacing
	 * 
	 * @return
	 */
	public int getSpacing()
	{
		return spacing;
	}

	/**
	 * 设置spacing
	 * 
	 * @param spacing
	 */
	public void setSpacing(int spacing)
	{
		this.spacing = spacing;
	}

	/**
	 * 获取batch
	 * 
	 * @return
	 */
	public int getBatch()
	{
		return batch;
	}

	/**
	 * 设置batch
	 * 
	 * @param batch
	 */
	public void setBatch(int batch)
	{
		this.batch = batch;
	}

	/**
	 * 获取degree
	 * 
	 * @return
	 */
	public int getDegree()
	{
		return degree;
	}

	/**
	 * 设置degree
	 * 
	 * @param degree
	 */
	public void setDegree(int degree)
	{
		this.degree = degree;
	}

	/**
	 * 获取shadow
	 * 
	 * @return
	 */
	public int getShadow()
	{
		return shadow;
	}

	/**
	 * 设置shadow
	 * 
	 * @param shadow
	 */
	public void setShadow(int shadow)
	{
		this.shadow = shadow;
	}
}
