package estate.management.com.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
public class PropertyValueRequest {
    @Column(name = "value", nullable = false, length = 100)
    @NotNull(message = "value cannot be null")
    private String value;
    private int advertId;
}
