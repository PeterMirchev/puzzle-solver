package com.puzzlesolver.controller;

import com.puzzlesolver.model.PuzzlePiece;
import com.puzzlesolver.service.PuzzleSolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageUploadController {

    private final PuzzleSolverService puzzleSolverService;

    @Autowired
    public ImageUploadController(PuzzleSolverService puzzleSolverService) {
        this.puzzleSolverService = puzzleSolverService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<PuzzlePiece>> uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        byte[] imageBytes = image.getBytes();

        // Call the PuzzleSolverService to process the image and solve the puzzle
        List<PuzzlePiece> puzzlePieces = puzzleSolverService.solvePuzzle(imageBytes);

        // Return the detected pieces as a response
        return ResponseEntity.ok(puzzlePieces);
    }
}
