package XTank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private ImageIcon[][] tankImages;
	private List<List<Integer>> tankInfo;
	private List<List<Integer>> bullets;
	private ImageIcon bullet;
	List<Rectangle> obstacles = MapCreator.get();
	Canvas() {
		super();
		//this.you = you;
//		obstacles = new ArrayList<>();
//		obstacles.addAll(MapCreator.get());
		tankInfo = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			List<Integer> temp = new ArrayList<>();
			temp.add(0);
			temp.add(0);
			temp.add(0);
			temp.add(0);
			temp.add(0);
			tankInfo.add(temp);
		}
		bullets = new ArrayList<>();
		setTankImages();
		setBulletImage();
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, 800, 630);
		g.setColor(Color.black);
		g.fillRect(0, 0, 650, 600);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);
		g.drawRect(150, 200, 350, 20);
		g.drawRect(150, 400, 350, 20);
		
		g.setColor(Color.white);
		g.setFont(new Font("Monospaced",Font.BOLD, 15));
		g.drawString("Health", 700,30);
		int i = 0;
		for (List<Integer> l : tankInfo) {
			if (l.get(2) != 0) {
				g.drawString("Player " + i + ": " + l.get(0), 670, 60 + i * 20);
			}
			if (l.get(0) > 0)
				tankImages[l.get(4) - 1][l.get(3) - 1].paintIcon(this, g, l.get(1), l.get(2));
			i++;
		}
		for (List<Integer> b : bullets) {
			bullet.paintIcon(this, g, b.get(0), b.get(1));
		}
		bullets.clear();
	}

	private void setTankImages() {
		tankImages = new ImageIcon[4][4];
		BufferedImage temp;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				try {
					temp = ImageIO.read(Tank.class.getResourceAsStream("tank_" + i + "_direction_" + j + ".png"));
					tankImages[i - 1][j - 1] = new ImageIcon(temp);
				} catch (IOException e) {  System.out.println("Error loading tank images.");  }
			}
		}
	}
	
	private void setBulletImage() {
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(Tank.class.getResourceAsStream("bullet.png"));
		}
		catch (IOException e) {  System.out.println("Error loading tank images.");  }
		bullet = new ImageIcon(temp);
	}

	public void drawStuff(String response) {
		String[] a = response.split("\\+");
		for (String s : a) {
			setTankInfo(s.substring(1), Character.getNumericValue(s.charAt(0)));
		}
		repaint();
	}

	private void setTankInfo(String substring, int num) {
		List<Integer> cur = tankInfo.get(num);
		String[] playerInfo = substring.split(";");
		String[] tank = playerInfo[0].split(",");
		cur.set(0, Integer.parseInt(tank[0]));
		cur.set(1, Integer.parseInt(tank[1]));
		cur.set(2, Integer.parseInt(tank[2]));
		cur.set(3, Integer.parseInt(tank[3]));
		cur.set(4, num);
		for (int i = 1; i < playerInfo.length; i++) {
			String[] temp = playerInfo[i].split(",");
			List<Integer> a = new ArrayList<>();
			a.add(Integer.parseInt(temp[0]));
			a.add(Integer.parseInt(temp[1]));
			bullets.add(a);
		}
	}
}
