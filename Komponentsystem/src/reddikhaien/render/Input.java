package reddikhaien.render;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter{
	
	private static enum Actions{
		RELEASED,
		PRESSED,
		HELD
	}
	
	private static Actions[] keys = new Actions[256];
	private static boolean[] keyMap = new boolean[256];
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyChar());
		int code = e.getKeyCode();
		if(code < 0 || code > 255) {
			System.out.println("ukjent tast " + code);
			return;
		}
		keyMap[code] = true;	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyChar());
		int code = e.getKeyCode();
		if (code < 0 || code > 255) {
			System.out.println("ukjent tast " + code);
		}
		keyMap[code] = false;
	}
	
	public void poll() {
		for (int i = 0; i < 256; i++) {
			if (!keyMap[i]) {
				keys[i] = Actions.RELEASED; 
			}
			else {
				if (keys[i] == null || keys[i] == Actions.RELEASED) {
					keys[i] = Actions.PRESSED;
				}
				else {
					keys[i] = Actions.HELD;
				}
			}
		}
	}
	
	public static boolean isReleased(int keycode) {
		return keys[keycode] == Actions.RELEASED || keys[keycode] == null;
	}
	
	public static boolean isPressed(int keycode) {
		return keys[keycode] == Actions.PRESSED;
	}
	
	public static boolean isHeld(int keycode) {
		return keys[keycode] == Actions.HELD || keys[keycode] == Actions.PRESSED;
	}
	
}
