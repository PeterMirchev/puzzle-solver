package com.puzzlesolver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/puzzle")
public class PuzzleController {

    @Autowired
    private ImageProcessingService imageProcessingService;

    @PostMapping("/solve")
    public ResponseEntity<String> solvePuzzle(@RequestParam("image") MultipartFile imageFile) {
        try {
            // Convert the uploaded image to a Mat object
            Mat puzzleImage = Imgcodecs.imread(imageFile.getInputStream());

            // Process the image and find puzzle pieces
            List<PuzzlePiece> pieces = imageProcessingService.detectPuzzlePieces(puzzleImage);

            // Calculate match percentages for each piece
            for (PuzzlePiece piece : pieces) {
                double matchScore = imageProcessingService.calculateSimilarity(piece.getPieceImage(), piece.getMatchPiece().getPieceImage());
                piece.setMatchScore(matchScore); // Store the match score in the piece
            }

            // Draw arrows indicating matching with colors (green, yellow, red)
            imageProcessingService.drawArrows(puzzleImage, pieces);

            // Convert the processed Mat object back to a Base64 string
            MatOfByte matOfByte = new MatOfByte();
            Imgcodecs.imencode(".jpg", puzzleImage, matOfByte);
            byte[] byteArray = matOfByte.toArray();
            String base64Image = Base64.getEncoder().encodeToString(byteArray);

            return ResponseEntity.ok(base64Image); // Send back Base64 image data
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
