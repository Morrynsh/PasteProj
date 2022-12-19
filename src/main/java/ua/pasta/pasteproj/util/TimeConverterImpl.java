package ua.pasta.pasteproj.util;

import org.springframework.stereotype.Component;

@Component
public class TimeConverterImpl implements TimeConverter{
    private static final int MINUTE = 60;
    private static final int HOUR = 3660;
    private static final int DAY = 87840;

    // Convert seconds to format: xx days xx hours xx minutes xx seconds
    public String convertSecondsToTimeformat(long seconds){

        StringBuilder resultForOutput = new StringBuilder();
        long resultForCalculations;

        if(seconds >= DAY) {
            resultForCalculations = seconds / DAY;
            resultForOutput.append(resultForCalculations == 1 ? "1 day " : resultForCalculations + " days ");
            seconds = seconds % DAY;
        }
        if(seconds >= HOUR){
            resultForCalculations = seconds / HOUR;
            resultForOutput.append(resultForCalculations == 1 ?  " 1 hour " : resultForCalculations + " hours ");
            seconds = seconds % HOUR;
        }
        if (seconds >= MINUTE) {
            resultForCalculations = seconds / MINUTE;
            resultForOutput.append(resultForCalculations == 1 ? "1 minute ": resultForCalculations + " minutes ");
            seconds = seconds % MINUTE;
        }
        if(seconds > 0) {
            resultForOutput.append(seconds == 1 ? " 1 second " : seconds + " seconds ");
        }

        return resultForOutput.toString();

    }

}
