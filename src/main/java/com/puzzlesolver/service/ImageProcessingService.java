import org.opencv.*;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageProcessingService {

    // This method will calculate the match percentage between two puzzle pieces
    public double calculateSimilarity(Mat piece1, Mat piece2) {
        // Extract edge features from the puzzle pieces (e.g., histograms or contours)
        double similarityScore = compareHistograms(piece1, piece2);

        // Optionally, use other methods like template matching or contour matching
        // For now, we assume similarityScore ranges from 0 (no match) to 1 (perfect match)

        return similarityScore; // Return the calculated similarity score between 0 and 1
    }

    // Compare histograms of the pieces (this is one method to compare)
    private double compareHistograms(Mat piece1, Mat piece2) {
        // Calculate histograms for both pieces
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();
        Imgproc.calcHist(List.of(piece1), new MatOfInt(0), new Mat(), hist1, new MatOfInt(256), new MatOfFloat(0, 256));
        Imgproc.calcHist(List.of(piece2), new MatOfInt(0), new Mat(), hist2, new MatOfInt(256), new MatOfFloat(0, 256));

        // Normalize histograms
        Core.normalize(hist1, hist1, 0, 1, Core.NORM_MINMAX);
        Core.normalize(hist2, hist2, 0, 1, Core.NORM_MINMAX);

        // Compute correlation between the histograms
        return Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);
    }

    // Assign color based on similarity score
    public String getArrowColor(double similarityScore) {
        // Define color based on similarity score
        if (similarityScore >= 0.95) {
            return "green"; // 100% match
        } else if (similarityScore >= 0.80) {
            return "yellow"; // 80-90% match
        } else if (similarityScore >= 0.50) {
            return "red"; // Low match
        } else {
            return "red"; // Below 50% match, still considered a weak match
        }
    }

    // Method to draw arrows on the image based on matching results
    public void drawArrows(Mat image, List<PuzzlePiece> pieces) {
        for (PuzzlePiece piece : pieces) {
            double matchScore = piece.getMatchScore(); // Assume each piece has a match score
            String arrowColor = getArrowColor(matchScore);

            // Logic to draw arrows between matching pieces (simplified here)
            drawArrowBetweenPieces(image, piece, arrowColor);
        }
    }

    // A placeholder function for drawing arrows (you can implement based on your UI)
    private void drawArrowBetweenPieces(Mat image, PuzzlePiece piece, String color) {
        // Logic to draw an arrow, color it based on 'color', and place it on the image
        // You can use OpenCV functions like line() or arrowedLine() to draw arrows
    }
}
