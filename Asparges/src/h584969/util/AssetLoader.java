package h584969.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AssetLoader {
	public static BufferedImage loadImage(final String path) {
		try {
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			
			return image;
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
}
