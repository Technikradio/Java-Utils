package org.technikradio.universal_tools;

public class Console {
	
	private static int currentColor;
	
	public static final int StyleNormal = 0;
	public static final int StyleBolt = 1;
	public static final int StyleUnderline = 4;
	public static final int StyleBlink = 5;
	public static final int StyleInvert = 7;
	public static final int BackgroundBlack = 40;
	public static final int BackgroundRed = 41;
	public static final int BackgroundGreen = 42;
	public static final int BackgroundYellow = 43;
	public static final int BackgroundBlue = 44;
	public static final int BackgroundMageta = 45;
	public static final int BackgroundCyan = 46;
	public static final int BackgroundLightGray = 47;
	public static final int ForegroundBlack = 130;
	public static final int ForegroundRed = 131;
	public static final int ForegroundGreen = 132;
	public static final int ForegroundBrown = 133;
	public static final int ForegroundBlue = 134;
	public static final int ForegroundMagenta = 135;
	public static final int ForegroundCyan = 136;
	public static final int ForegroundWhite = 137;
	public static final int ForegroundGray = 230;
	public static final int ForegroundLightRed = 231;
	public static final int ForegroundLightGreen = 232;
	public static final int ForegroundYellow = 233;
	public static final int ForegroundLightBlue = 234;
	public static final int ForegroundPink = 235;
	public static final int ForegroundLightCyan = 236;
	public static final int ForegroundLightGray = 230;

	public enum LogType{
		Error,
		Warning,
		Information,
		StdOut
	}
	public static void setColor(int c){
		setColor(c, 0);
	}
	
	public static void setColor(int c, int style){
		resetColor();
		if(c > 200){
			int i = c - 200;
			System.out.println("^[[" + style + ";1" + i + "m");
		} else if(c > 100){
			int i = c - 100;
			System.out.println("^[[" + style + ";0" + i + "m");
		} else {
			System.out.println("^[[" + style + ";" + c + "m");
		}
	}
	
	public static int getCurrentColor(){
		return currentColor;
	}
	
	private static void printInformation(Object sender,
			String Information){
		System.out.print(sender.toString());
		System.out.print("): ");
		System.out.println(Information);
	}

	public static void resetColor(){
		System.out.print("^[[0m");
	}
	
	public static void log(LogType error, Object sender,
			String Information) {
		int c = currentColor;
		if(error == LogType.Error){
			setColor(ForegroundRed);
			System.out.print("[ERROR] :: (");
			printInformation(sender, Information);
		} else if(error == LogType.Information){
			setColor(ForegroundBlue);
			System.out.print("[Information] :: (");
			printInformation(sender, Information);
		} else if(error == LogType.Warning){
			setColor(ForegroundYellow);
			System.out.print("[Warning] :: (");
			printInformation(sender, Information);
		} else {
			setColor(ForegroundBlack);
			System.out.print("[Output] :: (");
			printInformation(sender, Information);
		}
		setColor(c);
	}

}
