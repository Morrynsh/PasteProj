package ua.pasta.pasteproj.api.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class PasteboxRequest {

    private String data;
    @Max(value = 300, message = "Cannot be greater than 300!")
    @Min(value = 15, message = "Cannot be less than 15!")
    private Long expirationTimeSeconds;
    private PublicStatus publicStatus;

}
