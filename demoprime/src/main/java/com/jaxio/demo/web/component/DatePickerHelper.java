/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-primefaces-sd:src/main/java/component/DatePickerHelper.p.vm.java
 */
package com.jaxio.demo.web.component;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Calendar;
import java.util.List;

import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Helper used from the {@link DatePicker} composite component.
 */
@Component
public class DatePickerHelper {

    public List<SelectItem> getYears(int min, int max) {
        List<SelectItem> result = newArrayList();
        for (int years = min; years <= max; years++) {
            String year = "" + years;
            result.add(new SelectItem(year, year));
        }

        return result;
    }

    public List<SelectItem> getMonths() {
        List<SelectItem> result = newArrayList();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1); // prevent protential month shifting when reseting month below

        for (int months = 1; months <= 12; months++) {
            c.set(Calendar.MONTH, months - 1);
            String label = c.getDisplayName(Calendar.MONTH, Calendar.LONG, LocaleContextHolder.getLocale());

            String month = normalize(months);
            result.add(new SelectItem(month, label));
        }

        return result;
    }

    public List<SelectItem> getDays(String ccClientId, boolean appendDayOfWeek) {
        UIInput ccDatepicker = (UIInput) FacesContext.getCurrentInstance().getViewRoot().findComponent(ccClientId);
        UIInput year = (UIInput) ccDatepicker.findComponent("year");
        UIInput month = (UIInput) ccDatepicker.findComponent("month");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1); // prevent potential month shifting when reseting month below
        c.set(Calendar.YEAR, Integer.parseInt((String) year.getLocalValue()));
        c.set(Calendar.MONTH, Integer.parseInt((String) month.getLocalValue()) - 1);

        List<SelectItem> result = newArrayList();

        int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int days = 1; days <= max; days++) {
            String day = normalize(days);
            c.set(Calendar.DAY_OF_MONTH, days);
            StringBuilder sb = new StringBuilder();
            sb.append(day);
            if (appendDayOfWeek) {
                sb.append(" ").append(
                        c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, LocaleContextHolder.getLocale()));
            }
            result.add(new SelectItem(day, sb.toString()));
        }

        return result;
    }

    public List<SelectItem> getHours() {
        List<SelectItem> result = newArrayList();
        for (int hours = 0; hours < 24; hours++) {
            String hour = normalize(hours);
            result.add(new SelectItem(hour, hour));
        }
        return result;
    }

    public List<SelectItem> getMinutes() {
        List<SelectItem> result = newArrayList();
        result.add(new SelectItem("00", "00"));
        result.add(new SelectItem("15", "15"));
        result.add(new SelectItem("30", "30"));
        result.add(new SelectItem("45", "45"));
        return result;
    }

    public static final String normalize(int i) {
        return i < 10 ? "0" + i : "" + i;
    }
}