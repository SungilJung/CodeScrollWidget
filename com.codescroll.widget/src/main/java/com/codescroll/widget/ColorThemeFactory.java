package com.codescroll.widget;

import com.codescroll.widget.util.SWTGraphicUtil;

/**
 * @author pmg
 *
 */
public final class ColorThemeFactory {
	public static final int THEME_DEFAULT = 0;
	public static final int THEME_PURPLE = 1;
	public static final int THEME_DARK = 2;
	public static final int THEME_YELLOW = 3;
	public static final int THEME_EMERALD = 4;
	public static final int THEME_STEELBLUE = 5;

	public static ColorConfig getColorTheme(int theme) {
		ColorConfig colorConfig = null;
		switch (theme) {
		case THEME_PURPLE:
			colorConfig = createPurple();
			break;
		case THEME_DARK:
			colorConfig = createDark();
			break;
		case THEME_YELLOW:
			colorConfig = createYellow();
			break;
		case THEME_EMERALD:
			colorConfig = createEmerald();
			break;
		case THEME_STEELBLUE:
			colorConfig = createSteelbule();
			break;
		default:
			colorConfig = createDefault();
			break;
		}
		return colorConfig;
	}
	
	private static ColorConfig createDefault() {
		ColorConfig DEFAULT = new ColorConfig();
		DEFAULT.setBorderColor(SWTGraphicUtil.getColorSafely( 143, 143, 143));
		DEFAULT.setBackgroundColor(SWTGraphicUtil.getColorSafely( 255, 255, 255));
		DEFAULT.setForegroundColor(SWTGraphicUtil.getColorSafely( 235, 235, 235));
		DEFAULT.setSeparatorColor(SWTGraphicUtil.getColorSafely( 230, 230, 230));
		DEFAULT.setTextColor(SWTGraphicUtil.getColorSafely( 0, 0, 0));
		return DEFAULT;
	}

	private static ColorConfig createPurple() {
		ColorConfig THEME_PURPLE = new ColorConfig();
		THEME_PURPLE.setBorderColor(SWTGraphicUtil.getColorSafely( 143, 143, 143));
		THEME_PURPLE.setBackgroundColor(SWTGraphicUtil.getColorSafely( 155, 89, 182));
		THEME_PURPLE.setForegroundColor(SWTGraphicUtil.getColorSafely( 135, 69, 162));
		THEME_PURPLE.setSeparatorColor(SWTGraphicUtil.getColorSafely( 133, 51, 167));
		THEME_PURPLE.setTextColor(SWTGraphicUtil.getColorSafely( 255, 255, 255));

		return THEME_PURPLE;
	}

	private static ColorConfig createDark() {
		ColorConfig THEME_DARK = new ColorConfig();
		THEME_DARK.setBorderColor(SWTGraphicUtil.getColorSafely( 143, 143, 143));
		THEME_DARK.setBackgroundColor(SWTGraphicUtil.getColorSafely( 52, 73, 94));
		THEME_DARK.setForegroundColor(SWTGraphicUtil.getColorSafely( 40, 55, 70));
		THEME_DARK.setSeparatorColor(SWTGraphicUtil.getColorSafely( 27, 52, 77));
		THEME_DARK.setTextColor(SWTGraphicUtil.getColorSafely( 255, 255, 255));

		return THEME_DARK;
	}

	private static ColorConfig createYellow() {
		ColorConfig THEME_YELLOW = new ColorConfig();
		THEME_YELLOW.setBorderColor(SWTGraphicUtil.getColorSafely( 143, 143, 143));
		THEME_YELLOW.setBackgroundColor(SWTGraphicUtil.getColorSafely( 241, 196, 15));
		THEME_YELLOW.setForegroundColor(SWTGraphicUtil.getColorSafely( 220, 170, 13));
		THEME_YELLOW.setSeparatorColor(SWTGraphicUtil.getColorSafely( 216, 176, 14));
		THEME_YELLOW.setTextColor(SWTGraphicUtil.getColorSafely( 255, 255, 255));

		return THEME_YELLOW;
	}

	private static ColorConfig createEmerald() {
		ColorConfig THEME_EMERALD = new ColorConfig();
		THEME_EMERALD.setBorderColor(SWTGraphicUtil.getColorSafely( 143, 143, 143));
		THEME_EMERALD.setBackgroundColor(SWTGraphicUtil.getColorSafely( 26, 188, 156));
		THEME_EMERALD.setForegroundColor(SWTGraphicUtil.getColorSafely( 0, 138, 106));
		THEME_EMERALD.setSeparatorColor(SWTGraphicUtil.getColorSafely( 19, 155, 128));
		THEME_EMERALD.setTextColor(SWTGraphicUtil.getColorSafely( 255, 255, 255));

		return THEME_EMERALD;
	}

	private static ColorConfig createSteelbule() {
		ColorConfig THEME_STEELBLUE = new ColorConfig();
		THEME_STEELBLUE.setBorderColor(SWTGraphicUtil.getColorSafely( 143, 143, 143));
		THEME_STEELBLUE.setBackgroundColor(SWTGraphicUtil.getColorSafely( 52, 152, 219));
		THEME_STEELBLUE.setForegroundColor(SWTGraphicUtil.getColorSafely( 32, 138, 199));
		THEME_STEELBLUE.setSeparatorColor(SWTGraphicUtil.getColorSafely( 27, 124, 189));
		THEME_STEELBLUE.setTextColor(SWTGraphicUtil.getColorSafely( 255, 255, 255));

		return THEME_STEELBLUE;
	}
}
