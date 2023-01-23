import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class FirstCardDeliveryTest {

    String setNewDate() {
        LocalDateTime now = LocalDateTime.now();
        now = now.plus(5, ChronoUnit.DAYS);
        String newDate = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(now);
        return newDate;
    }

    @Test
    void shouldTest() {
        open("http://localhost:9999/");
        String setDate = setNewDate();
        $x("//span[@data-test-id='city']//input").setValue("Казань");
        $x("//*[@data-test-id='date']//input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//*[@data-test-id='date']//input").setValue(setDate);
        $x("//*[@data-test-id='name']//input").setValue("Петров Иван");
        $x("//*[@data-test-id='phone']//input").setValue("+74935167891");

        $x("//*[@data-test-id='agreement']//span[@class='checkbox__box']").click();
        $x("//*[text() = 'Забронировать']").click();

        $x("//*[@data-test-id='notification']").shouldHave(Condition.text("Встреча успешно забронирована на " + setDate), Duration.ofSeconds(15)).shouldBe(visible);
    }
}