package ReviewRating;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import writingReview.ReviewPage;

public class writeAReview {
    private ReviewPage review = new ReviewPage();

    @BeforeTest
    public void setUp(){
        review.startSession();
    }

    @Test
    public void writeReview(){
        review.writeReview();
    }

    @AfterTest
    public void tearDown(){
        review.terminateSession();
    }
}
