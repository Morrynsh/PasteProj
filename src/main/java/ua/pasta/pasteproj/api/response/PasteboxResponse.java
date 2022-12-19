package ua.pasta.pasteproj.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteboxResponse {
    private final Long id;
    private final String data;
    private final String hash;
    private final String expirationTime;
    private final boolean isPublic;
}
