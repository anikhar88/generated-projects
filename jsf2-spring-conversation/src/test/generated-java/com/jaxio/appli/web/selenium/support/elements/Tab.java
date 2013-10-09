/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-selenium-primefaces:src/test/java/selenium/support/element/Tab.p.vm.java
 */
package com.jaxio.appli.web.selenium.support.elements;

import static com.google.common.collect.Lists.newArrayList;
import static com.palominolabs.xpath.XPathUtils.getXPathString;
import static org.apache.commons.lang.WordUtils.capitalize;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Tab extends ByCustomWebElement {
    private final String idPlural;

    public Tab(String id, String idPlural) {
        super(id);
        this.idPlural = idPlural;
    }

    public void open() {
        webClient.message("Open tab " + id);
        webClient.click(By.xpath("//div[@id='form:tabs']//a[@href='#form:tabs:" + idPlural + "']"));
        webClient.waitUntilDisplayed(By.xpath("//div[@id='form:tabs:" + idPlural + "']"));
    }

    public void add() {
        webClient.message("Add " + id);
        clickInHeaderUsingClass("iconAdd");
    }

    public void search() {
        webClient.message("Search " + id);
        clickInHeaderUsingClass("iconSearch");
    }

    private void clickInHeaderUsingClass(String className) {
        String xpath = "//div[@id='form:tabs:" + idPlural + "']//th[contains(@class,'actions-column')]//div[contains(@class,'" + className + "')]";
        webClient.click(By.xpath(xpath));
    }

    public void view(String value) {
        webClient.message("View " + value);
        webClient.clickLinkTitle("View " + value);
    }

    public void edit(String value) {
        webClient.message("Edit " + value);
        webClient.clickLinkTitle("Edit " + value);
    }

    public void delete(String value) {
        webClient.message("Delete " + value);
        webClient.clickLinkTitle("Remove " + value);
    }

    public void select(String value) {
        webClient.message("Select " + value);
        webClient.clickLinkTitle("Select " + value);
    }

    public void remove(String value) {
        webClient.message("Remove " + value);
        webClient.clickLinkTitle("Remove " + value);
    }

    public void confirmRemove() {
        By yesButton = By.name("form:askForRemove" + capitalize(id) + "DialogYes");
        webClient.click(yesButton);
        webClient.waitUntilInvisible(yesButton);
    }

    public void cancelRemove() {
        By noButton = By.name("form:askForRemove" + capitalize(id) + "DialogNo");
        webClient.click(noButton);
        webClient.waitUntilInvisible(noButton);
    }

    public List<String> column(String columnName) {
        By values = By.xpath("//div[@id='form:tabs:" + idPlural + "']//tr/td[contains(@class," + getXPathString(columnName) + ")]");
        webClient.waitUntilFound(values);
        List<String> ret = newArrayList();
        for (WebElement webElement : webClient.findAll(values)) {
            ret.add(webElement.getText());
        }
        return ret;
    }

    public String columnAt(String columnName, int line) {
        By valueAt = By.xpath("//tbody[@id='form:tabs:" + idPlural + "List_data']//tr[" + line + "]/td[contains(@class," + getXPathString(columnName) + ")]");
        webClient.waitUntilFound(valueAt);
        return webClient.find(valueAt).getText();
    }
}