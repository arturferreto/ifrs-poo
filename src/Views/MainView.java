package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainView extends BaseView {
    public MainView() {
        super();
        setupImage();
    }

    private void setupImage() {
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Public/img.png")));
            JLabel label = new JLabel(new ImageIcon(img));
            getContentPane().add(label);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MainView();
    }
}
