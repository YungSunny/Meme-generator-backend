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

        Graphics graphics = image.getGraphics();
        Font font = new Font("Impact", Font.BOLD, 48);
        graphics.setFont(font);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);

        int upperTextStartX = ((currentWidth - fontMetrics.stringWidth(uppperText)) / 2);
        int bottomTextStartX = ((currentWidth - fontMetrics.stringWidth(bottomText)) / 2);
        int upperTextStartY = currentHeight / 8;
        int bottomTextStartY = currentHeight - 20;

        graphics.setColor(color);

        graphics.drawString(uppperText, upperTextStartX, upperTextStartY);
        graphics.drawString(bottomText, bottomTextStartX, bottomTextStartY);
        graphics.dispose();

        ImageIO.write(image, "jpg", new File("resources/meme.jpg"));
    }


}
