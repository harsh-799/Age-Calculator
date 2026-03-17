package com.agefinder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@WebServlet("/calculate")
public class AgeCalculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalDate currDate = LocalDate.now();
        String dateOfBirth = req.getParameter("dob");

        LocalDate userDateOfBirth = LocalDate.parse(dateOfBirth);

        if (userDateOfBirth.isAfter(currDate)){
            resp.sendRedirect("Error.html");
            return;
        }

        Period userAge = Period.between(userDateOfBirth, currDate);

        Boolean isBirthdayToday = userDateOfBirth.getDayOfMonth() == currDate.getDayOfMonth() &&
                userDateOfBirth.getMonth() == currDate.getMonth();

        LocalDate nextBirthday = userDateOfBirth.withYear(currDate.getYear());

        if (nextBirthday.isBefore(currDate) || nextBirthday.isEqual(currDate)){
            nextBirthday = nextBirthday.plusYears(1);
        }

        Long daysLeft = ChronoUnit.DAYS.between(currDate, nextBirthday);

        RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
        req.setAttribute("years",userAge.getYears());
        req.setAttribute("months",userAge.getMonths());
        req.setAttribute("days",userAge.getDays());
        req.setAttribute("daysLeft",daysLeft);
        req.setAttribute("isBirthday",isBirthdayToday);
        dispatcher.forward(req, resp);
    }
}
