package ua.pasta.pasteproj.service;

import ua.pasta.pasteproj.api.request.PasteboxRequest;
import ua.pasta.pasteproj.api.response.PasteboxResponse;
import ua.pasta.pasteproj.api.response.PasteboxUrlResponse;

import java.util.List;

public interface PasteboxService {
    PasteboxResponse getByHash(String hash);
    List<PasteboxResponse> getLastPublic();
    PasteboxUrlResponse create(PasteboxRequest request);
    boolean deleteById(String hash);
}
