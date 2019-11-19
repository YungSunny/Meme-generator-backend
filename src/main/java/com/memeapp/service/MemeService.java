package com.memeapp.service;

import com.memeapp.repo.MemesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

@Service
public class MemeService {

    @Autowired
    private MemesRepo repo;

    private String currentMemeUrl;
    private int currentWidth;
    private int currentHeight;

    public String getMeme() throws IOException {
        Random random = new Random();
        int length = repo.getMeme().size();
        int n = random.nextInt(length);
        currentMemeUrl = repo.getMeme().get(n).getUrl();
        currentWidth = repo.getMeme().get(n).getWidth();
        currentHeight = repo.getMeme().get(n).getHeight();
        return currentMemeUrl;
    }

    public void editMeme(String uppperText, String bottomText, boolean blackText) throws IOException {
        final BufferedImage image = ImageIO.read(new URL(currentMemeUrl));

        final Color color = blackText ? Color.BLACK : Color.WHITE;

        Graphics g = image.getGraphics();
        Font font = new Font("Impact", Font.PLAIN, 48);
        g.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics(font);

        int upperTextStartX = ((currentWidth - fontMetrics.stringWidth(uppperText)) / 2);
        int bottomTextStartX = ((currentWidth - fontMetrics.stringWidth(bottomText)) / 2);
        int upperTextStartY = currentHeight / 8;
        int bottomTextStartY = currentHeight - 20;

       g.setColor(color);

        g.drawString(uppperText, upperTextStartX, upperTextStartY);
        g.drawString(bottomText, bottomTextStartX, bottomTextStartY);
        g.dispose();

        ImageIO.write(image, "jpg", new File("resources/meme.jpg"));
    }


}
