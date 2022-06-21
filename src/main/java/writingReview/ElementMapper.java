package writingReview;


public interface ElementMapper {

    String homePage = "https://wallethub.com/join/light";
    String loginTab = "li:nth-of-type(2) > a[role='tab']";
    String emailField = "input#em-ipt";
    String passwordField = "input[type=\"password\"]";
    String loginButton = "button[type=\"button\"]";
    String companyLists = "[aria-controls=\"companies-list-menu\"]";
    String financeAdvisor = "div#companies-list-menu > a:nth-of-type(4)";
    String mattTobben = "div:nth-of-type(1)  h3 > a";
    String gregoryAlan = "div:nth-of-type(5)  h3 > a";
    String getGregoryAlan = "Gregory Alan Carr";
    String fourStar = "div:nth-of-type(5)  .inst-rating.rating-box > svg:nth-of-type(4) > g > path:nth-of-type(1)";
    String fiveStar = "div:nth-of-type(5)  .inst-rating.rating-box > svg:nth-of-type(5)";
    String writeAReview = ".popup-link > a:nth-of-type(1)";
    String setFiveStar = "span#overallRating > a:nth-of-type(5)";
    String reviewComment = "textarea";
    String getReviewComment = "Olympics in Atlanta, Georgia, partly because he was afraid of raising expectations even higher and he did not want to be distracted by interviews and adoring fans who would follow him into stores and restaurants.";
    String reviewCount = "#reviewform .review-bottom:nth-child(5) .green";
    String submitReviewButton = "input[value=\"Submit\"]";
    String editReviewButton = "[href=\"/reviews/?review_action=edit&iid=13259566&id=2141080014\"]";
    String getEditReviewButtonText = "Edit Review";
}
