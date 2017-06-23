package com.orasi.selenium.web.by.angular;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.orasi.utils.Beta;

@Beta
public interface FindsByNGShow {
    WebElement findElementByNGShow(String using);

    List<WebElement> findElementsByNGShow(String using);
}
