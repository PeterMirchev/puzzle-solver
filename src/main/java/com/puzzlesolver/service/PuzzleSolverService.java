package com.puzzlesolver.service;

import com.puzzlesolver.model.PuzzlePiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuzzleSolverService {

    private final ImageProcessingService imageProcessingService;

    @Autowired
    public PuzzleSolverService(ImageProcessingService imageProcessingService) {
        this.imageProcessingService = imageProcessingService;
    }

    // Business logic for solving the puzzle
    public List<PuzzlePiece> solvePuzzle(byte[] imageBytes) {
        // 1. Detect pieces from the image (calls ImageProcessingService)
        List<PuzzlePiece> puzzlePieces = imageProcessingService.detectPuzzlePieces(imageBytes);

        // 2. Additional puzzle-solving logic (matching, organizing, etc.)
        // For now, just return the detected pieces
        return puzzlePieces;
    }
}
