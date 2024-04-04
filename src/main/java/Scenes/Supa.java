package Scenes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * This Class contains all the logic to scrape image and text from "url" website.
 * JSoup is used for scraping
 */

public class Supa {
    private static final String url = "https://www.britannica.com/technology/compact-disc";

    // Returns the Text that'll be shown in the SceneAboutCD
    public static String getText() {
        try {
            Document document = Jsoup.connect(url).get();
            Element section = document.selectFirst("#ref1"); // Find id="ref1"

            if (section != null) {
                Elements paragraphs = section.select("p"); // Selects <p>
                StringBuilder textBuilder = new StringBuilder();
                for (Element paragraph : paragraphs) {
                    textBuilder.append(paragraph.text()).append("\n");
                }
                return textBuilder.toString().trim();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null; // Won't happen lol
    }

    // Returns the ImageView that'll be shown in the SceneAboutCD
    public static ImageView getImg() {
        try {
            Document doc = Jsoup.connect(url).get();
            Element anchor = doc.select("a.media-overlay-link.card-media.d-flex.align-items-center.justify-content-center").first();
            if (anchor != null) {
                Element img = anchor.select("img").first();
                if (img != null) {
                    String imageUrl = img.absUrl("src");
                    Image image = new Image(imageUrl);
                    return new ImageView(image);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if image URL is not found or any error occurs
    }
}
