package PackageAutomation;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class AutomationTests extends BrowserSetting {

    private SoftAssert softAssert;

    @Test
    public void MailRuSuccessSendingMessage() throws InterruptedException {

        try {

            pageBrowser.navigate("https://mail.ru");

            //Авторизация пользователя

            pageBrowser.locator("//button[@data-testid=\"enter-mail-primary\"]").click();


            //Ищем локатор в DOM

            pageBrowser.frameLocator("iframe[class=\"ag-popup__frame__layout__iframe\"]").
                    locator("input[name=\"username\"]").fill("taskmail123456");

            pageBrowser.keyboard().press("Enter");


            pageBrowser.frameLocator("iframe[class=\"ag-popup__frame__layout__iframe\"]").
                    locator("input[name=\"password\"]").fill("vsemprivet@12345671245etedgvg");

            pageBrowser.keyboard().press("Enter");

            //создаем мягкий assert
            softAssert = new SoftAssert();


            pageBrowser.onRequest(request -> {

                //Проверяем, что http request имеет метод "POST"
                softAssert.assertEquals(request.method(), "POST");

            });

            pageBrowser.onResponse(response -> {

                //Проверка на статус код 200
                softAssert.assertEquals(response.status(), 200);

            });

            //Отправка  письма с сообщением
            pageBrowser.locator("//span[@class=\"compose-button__wrapper\"]").click();


            softAssert.assertAll();

        }

        catch (Exception e) {



            pageBrowser.locator("//div[@class=\"ph-project-promo-close-icon__container svelte-m7oyyo\"]").click();

            pageBrowser.locator("//span[@class=\"compose-button__wrapper\"]").click();

            softAssert.assertAll();

        }


    }

    @DataProvider
    public Object[][] DataTest(){

        Object[][] data={{""}};
        return data;

    }


}