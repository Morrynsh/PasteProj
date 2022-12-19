package ua.pasta.pasteproj.util;

import org.springframework.stereotype.Component;

@Component
public interface TimeConverter {
    public String convertSecondsToTimeformat(long seconds);
}
