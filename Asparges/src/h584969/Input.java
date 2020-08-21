package h584969;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Input implements KeyListener {

	private static final byte FREE = 0;
	private static final byte RELEASED = 1;
	private static final byte PRESSED = 2;
	private static final byte HELD = 3;
	
	private static final byte[] keys = new byte[256];
	private static final byte[] event = new byte[256];
	
	
	static void poll() {
		synchronized (event) {
			for (int i = 0; i < 256; i++) {
				if(keys[i] == FREE) {
					if (event[i] == PRESSED || event[i] == HELD) {
						event[i] = RELEASED;
					}
					else {
						event[i] = FREE;
					}
				}
				else {
					if (event[i] == RELEASED || event[i] == FREE) {
						event[i] = PRESSED;
					}
					else {
						event[i] = HELD;
					}
				}
			}
		}
	}
	
	
	public static boolean isPressed(int key) {
		synchronized (event) {
			return event[key] == PRESSED;
		}
	}	
	
	public static boolean isHeld(int key) {
		synchronized (event) {
			return event[key] == PRESSED || event[key] == HELD;
		}
	}
	
	public static boolean isReleased(int key) {
		synchronized (event) {
			return event[key] == RELEASED;
		}
	}
	public static boolean isFree(int key) {
		synchronized (event) {
			return event[key] == RELEASED || event[key] == HELD;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		byte code = (byte)(e.getKeyCode() & 255);
		keys[code] = HELD;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		byte code = (byte)(e.getKeyCode() & 255);
		keys[code] = FREE;
	}

}
