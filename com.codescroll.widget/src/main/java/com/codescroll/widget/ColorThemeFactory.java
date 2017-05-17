package com.codescroll.widget;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

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
		DEFAULT.setBorderColor(new Color(Display.getCurrent(), 143, 143, 143));
		DEFAULT.setBackgroundColor(new Color(Display.getCurrent(), 255, 255, 255));
		DEFAULT.setForegroundColor(new Color(Display.getCurrent(), 235, 235, 235));
		DEFAULT.setSeparatorColor(new Color(Display.getCurrent(), 230, 230, 230));
		DEFAULT.setTextColor(new Color(Display.getCurrent(), 0, 0, 0));
		return DEFAULT;
	}

	private static ColorConfig createPurple() {
		ColorConfig THEME_PURPLE = new ColorConfig();
		THEME_PURPLE.setBorderColor(new Color(Display.getCurrent(), 143, 143, 143));
		THEME_PURPLE.setBackgroundColor(new Color(Display.getCurrent(), 155, 89, 182));
		THEME_PURPLE.setForegroundColor(new Color(Display.getCurrent(), 135, 69, 162));
		THEME_PURPLE.setSeparatorColor(new Color(Display.getCurrent(), 133, 51, 167));
		THEME_PURPLE.setTextColor(new Color(Display.getCurrent(), 255, 255, 255));

		return THEME_PURPLE;
	}

	private static ColorConfig createDark() {
		ColorConfig THEME_DARK = new ColorConfig();
		THEME_DARK.setBorderColor(new Color(Display.getCurrent(), 143, 143, 143));
		THEME_DARK.setBackgroundColor(new Color(Display.getCurrent(), 52, 73, 94));
		THEME_DARK.setForegroundColor(new Color(Display.getCurrent(), 40, 55, 70));
		THEME_DARK.setSeparatorColor(new Color(Display.getCurrent(), 27, 52, 77));
		THEME_DARK.setTextColor(new Color(Display.getCurrent(), 255, 255, 255));

		return THEME_DARK;
	}

	private static ColorConfig createYellow() {
		ColorConfig THEME_YELLOW = new ColorConfig();
		THEME_YELLOW.setBorderColor(new Color(Display.getCurrent(), 143, 143, 143));
		THEME_YELLOW.setBackgroundColor(new Color(Display.getCurrent(), 241, 196, 15));
		THEME_YELLOW.setForegroundColor(new Color(Display.getCurrent(), 220, 170, 13));
		THEME_YELLOW.setSeparatorColor(new Color(Display.getCurrent(), 216, 176, 14));
		THEME_YELLOW.setTextColor(new Color(Display.getCurrent(), 255, 255, 255));

		return THEME_YELLOW;
	}

	private static ColorConfig createEmerald() {
		ColorConfig THEME_EMERALD = new ColorConfig();
		THEME_EMERALD.setBorderColor(new Color(Display.getCurrent(), 143, 143, 143));
		THEME_EMERALD.setBackgroundColor(new Color(Display.getCurrent(), 26, 188, 156));
		THEME_EMERALD.setForegroundColor(new Color(Display.getCurrent(), 0, 138, 106));
		THEME_EMERALD.setSeparatorColor(new Color(Display.getCurrent(), 19, 155, 128));
		THEME_EMERALD.setTextColor(new Color(Display.getCurrent(), 255, 255, 255));

		return THEME_EMERALD;
	}

	private static ColorConfig createSteelbule() {
		ColorConfig THEME_STEELBLUE = new ColorConfig();
		THEME_STEELBLUE.setBorderColor(new Color(Display.getCurrent(), 143, 143, 143));
		THEME_STEELBLUE.setBackgroundColor(new Color(Display.getCurrent(), 52, 152, 219));
		THEME_STEELBLUE.setForegroundColor(new Color(Display.getCurrent(), 32, 138, 199));
		THEME_STEELBLUE.setSeparatorColor(new Color(Display.getCurrent(), 27, 124, 189));
		THEME_STEELBLUE.setTextColor(new Color(Display.getCurrent(), 255, 255, 255));

		return THEME_STEELBLUE;
	}
}
