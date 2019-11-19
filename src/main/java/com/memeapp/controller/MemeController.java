package com.memeapp.controller;

import com.memeapp.Model.MemeText;
import com.memeapp.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.RandomAccessFile;


@Controller
@CrossOrigin
public class MemeController {
    @Autowired
    private MemeService memeService;

    @RequestMapping(path = "/getMeme", method = RequestMethod.GET)
    public ResponseEntity<String> getMeme() throws IOException {
        String result = memeService.getMeme();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/editMeme", method = RequestMethod.POST)
    public ResponseEntity<?> editMeme(@RequestBody MemeText memeText) throws IOException {
        memeService.editMeme(memeText.getUpperText(), memeText.getBottomText(), memeText.isBlackText());
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @RequestMapping(path = "/getNewMeme", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageAsResponseEntity() throws IOException {
        RandomAccessFile f = new RandomAccessFile("resources/meme.jpg", "r");
        byte[] b = new byte[(int)f.length()];
        f.readFully(b);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(b, headers, HttpStatus.CREATED);
    }
}
