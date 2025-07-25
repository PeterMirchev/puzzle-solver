package com.puzzlesolver.model;

public class PuzzlePiece {

    private Mat pieceImage;
    private PuzzlePiece matchPiece;
    private double matchScore; // Store the match score for this piece

    // Getters and setters
    public Mat getPieceImage() {
        return pieceImage;
    }

    public void setPieceImage(Mat pieceImage) {
        this.pieceImage = pieceImage;
    }

    public PuzzlePiece getMatchPiece() {
        return matchPiece;
    }

    public void setMatchPiece(PuzzlePiece matchPiece) {
        this.matchPiece = matchPiece;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }
}
